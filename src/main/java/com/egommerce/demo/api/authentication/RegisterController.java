package com.egommerce.demo.api.authentication;

import com.egommerce.demo.exception.UserRegistrationException;
import com.egommerce.demo.model.User;
import com.egommerce.demo.service.UserService;
import com.egommerce.demo.validation.User.UserValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/register")
@CrossOrigin
@RestController
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        UserValidation userValidation = new UserValidation(user, userService);

        try {
            userValidation.validate();

            return ResponseEntity.ok(userService.registerUser(user));
        } catch (UserRegistrationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
