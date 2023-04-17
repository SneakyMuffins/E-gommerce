package com.egommerce.demo.service;

import com.egommerce.demo.model.Login.LoginResponse;
import com.egommerce.demo.model.User.User;
import com.egommerce.demo.repository.UserRepository;
import com.egommerce.demo.security.JwtProvider;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

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

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public LoginResponse registerUser(User user) {
        user.setPassword(hashPassword(user.getPassword()));
        User savedUser = userRepository.save(user);

        String token = jwtProvider.generateToken(savedUser);
        return new LoginResponse(token);
    }


    private String hashPassword(String password) {
        return BCrypt.hashpw(password, passHash);
    }

    public boolean checkPassword(User user, String password) {
        String hashedPassword = BCrypt.hashpw(password, passHash);
        return hashedPassword.equals(user.getPassword());
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
}
