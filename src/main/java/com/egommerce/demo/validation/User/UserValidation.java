package com.egommerce.demo.validation.User;

import com.egommerce.demo.exception.UserRegistrationException;
import com.egommerce.demo.model.User;
import com.egommerce.demo.service.UserService;

public class UserValidation {
    private final User user;
    private final UserService userService;

    public UserValidation(User user, UserService userService) {
        this.user = user;
        this.userService = userService;
    }

    public void validate() throws UserRegistrationException {
        validateUsername(user.getUsername());
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
    }

    public void validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new UserRegistrationException("Username is required.");
        }

        if (username.length() > 32 || username.length() < 3) {
            throw new UserRegistrationException("The Username length should be between 3 and 32 characters");
        }
    }

    public void validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new UserRegistrationException("Email is required.");
        }

        String regex = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
        if (!email.matches(regex)) {
            throw new UserRegistrationException("Invalid email format.");
        }

        if (userService.getUserByEmail(email) != null) {
            throw new UserRegistrationException("Email already exists.");
        }
    }

    public void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new UserRegistrationException("Password is required.");
        }

        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$";
        if (!password.matches(regex)) {
            throw new UserRegistrationException("Invalid password format. " +
                    "Password must contain at least 8 characters, at least one uppercase letter, " +
                    "one lowercase letter, one number, and one special character.");
        }
    }
}
