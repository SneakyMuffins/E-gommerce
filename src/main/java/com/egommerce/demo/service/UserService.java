package com.egommerce.demo.service;

import com.egommerce.demo.dao.User.UserDao;
import com.egommerce.demo.exception.UserRegistrationException;
import com.egommerce.demo.model.User;
import com.egommerce.demo.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao UserDao;
    private final JwtProvider jwtProvider;

    @Value("${app.pass.salt}")
    private String passHash;

    @Autowired
    public UserService(@Qualifier("userDao") UserDao UserDao, JwtProvider jwtProvider) {
        this.UserDao = UserDao;
        this.jwtProvider = jwtProvider;
    }

    public User getUserByEmail(String email) {
        return UserDao.selectUserByEmail(email);
    }

    public String registerUser(User user) {
        user.setPassword(hashPassword(user.getPassword()));
        int rowsAffected = UserDao.insertUser(user);

        if (rowsAffected > 0) {
            return jwtProvider.generateToken(user);
        } else {
            throw new UserRegistrationException("Failed to register user");
        }
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, passHash);
    }
}
