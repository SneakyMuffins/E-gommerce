package com.egommerce.demo.api.authentication;

//import com.egommerce.demo.model.LoginRequest;
//import com.egommerce.demo.model.LoginResponse;
//import com.egommerce.demo.model.User;
import com.egommerce.demo.service.UserService;
import com.egommerce.demo.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @PostMapping
//    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
//        String email = loginRequest.getEmail();
//        String password = loginRequest.getPassword();
//
//        // Validate user's credentials
//        User user = userService.getUserByEmail(email);
//
//        if (user == null) {
//            return ResponseEntity.badRequest().body(new LoginResponse("Invalid email or password"));
//        }
//
//        String hashedPassword = user.getPassword();
//        if (!userService.checkPassword(password, hashedPassword, user.getSalt())) {
//            return ResponseEntity.badRequest().body(new LoginResponse("Invalid email or password"));
//        }
//
//        // Generate JWT token
//        String token = jwtProvider.generateToken(user);
//
//        LoginResponse response = new LoginResponse(token);
//        return ResponseEntity.ok(response);
//    }
}