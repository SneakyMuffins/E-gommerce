package com.egommerce.demo.api.Authentication;

import com.egommerce.demo.model.Login.LoginRequest;
import com.egommerce.demo.model.Login.LoginResponse;
import com.egommerce.demo.model.User.User;
import com.egommerce.demo.service.UserService;
import com.egommerce.demo.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
@CrossOrigin
public class LoginController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Autowired
    public LoginController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        User user = userService.getUserByEmail(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(null, "Invalid email or password"));
        }

        if (!userService.checkPassword(user, password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(null, "Invalid email or password"));
        }

        String token = jwtProvider.generateToken(user);

        LoginResponse response = new LoginResponse(token);
        return ResponseEntity.ok(response);
    }
}
