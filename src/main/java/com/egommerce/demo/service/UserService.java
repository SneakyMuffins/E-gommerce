package com.egommerce.demo.service;

import com.egommerce.demo.model.Login.LoginResponse;
import com.egommerce.demo.model.User.User;
import com.egommerce.demo.repository.UserRepository;
import com.egommerce.demo.security.JwtProvider;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Value("${app.pass.salt}")
    private String passHash;

    @Autowired
    public UserService(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public LoginResponse registerUser(User user) {
        user.setPassword(hashPassword(user.getPassword()));
        user.setAdmin(false); // By default, a registered user is not an admin
        User savedUser = userRepository.save(user);

        String token = jwtProvider.generateToken(savedUser);
        return new LoginResponse(token);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, passHash);
    }

    public boolean checkPassword(User user, String password) {
        String hashedPassword = BCrypt.hashpw(password, passHash);
        return hashedPassword.equals(user.getPassword());
    }

    public User getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token != null && jwtProvider.validateToken(token)) {
            String userEmail = jwtProvider.getEmailFromToken(token);
            return userRepository.findByEmail(userEmail);
        }
        return null;
    }

    public boolean authorizeUserAccessFromToken(String token) {
        String userEmail;
        User validUser;

        try {
            userEmail = jwtProvider.getEmailFromToken(token);
        } catch (SignatureException e) {
            return false;
        }

        validUser = getUserByEmail(userEmail);

        if (validUser != null && token != null) {
            try {
                return jwtProvider.validateToken(token);
            } catch (SignatureException e) {
                return false;
            }
        }

        return false;
    }

    public boolean isAdminUser(User user) {
        return user.isAdmin();
    }
}
