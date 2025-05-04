package org.example.Service;


import org.example.Entity.User;
import org.example.Entity.VerificationToken;
import org.example.Repository.UserRepository;
import org.example.Repository.VerificationTokenRepository;
import org.example.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationService implements UserDetailsService {


    @Autowired
    private PasswordEncoder _passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository _verificationTokenRepository;


    public User registerUser(UserDTO user) {

        User dbUser = new User();
        dbUser.setUsername(user.getUserName());
        dbUser.setPassword(_passwordEncoder.encode(user.getPassword()));
        dbUser.setEnabled(false);
        dbUser.setRole("USER");

        return userRepository.save(dbUser);
        // Logic to handle user registration
        // Here you can save the user to the database, send a confirmation email, etc.
        // Return a success message or redirect to a confirmation page
    }

    public String loginUser(UserDTO user) {
        // Logic to handle user login
        return "Login successful for user: " + user.getUserName();
    }

    public String logoutUser(UserDTO user) {
        // Logic to handle user logout
        return "Logout successful for user: " + user.getUserName();
    }

    public String deleteUser(UserDTO user) {
        // Logic to handle user deletion
        return "User deleted successfully: " + user.getUserName();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, null);

    }

    public void registerVerificationToken(User registeredUser, String generatedToken) {
       VerificationToken token = new VerificationToken();
        token.setToken(generatedToken);
        token.setUser(registeredUser);
        Long expiryDate = System.currentTimeMillis() + 60 * 60 * 1000; // 1 hour expiration
        token.setExpiryDate(new Date(expiryDate));
        _verificationTokenRepository.save(token);
    }

    public boolean validateRegistrationToken(String token) {
        VerificationToken verificationToken = _verificationTokenRepository.findByToken(token);
        if (verificationToken != null) {
            Date expiryDate = verificationToken.getExpiryDate();
            if (expiryDate != null && expiryDate.after(new Date())) {
                return true; // Token is valid
            }
        }
        return false; // Token is invalid or expired
    }
}
