package com.egommerce.demo.service;

import com.egommerce.demo.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final UserService userService;

    @Autowired
    public AdminService(UserService userService) {
        this.userService = userService;
    }

    public List<User> getAllUsers() {
        return userService.findAll();
    }

    public void updateUserDetails(Long id, User userUpdates) {
        userService.updateUserDetails(id, userUpdates);
    }

    public void deleteUser(Long userId) {
        User user = userService.findById(userId);

        if (user != null) {
            userService.deleteUser(userId);
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }
}
