package com.egommerce.demo.api.Admin.User;

import com.egommerce.demo.annotation.AdminOnly;
import com.egommerce.demo.annotation.RequireAuthorization;
import com.egommerce.demo.exception.UserRegistrationException;
import com.egommerce.demo.model.User.User;
import com.egommerce.demo.service.Admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/users")
@CrossOrigin
public class UserController {

    private final AdminService adminService;

    @Autowired
    public UserController(AdminService adminService) {
        this.adminService = adminService;
    }

    @AdminOnly
    @RequireAuthorization
    @GetMapping
    public List<User> fetchAllUsers() {
        return adminService.getAllUsers();
    }

    @AdminOnly
    @RequireAuthorization
    @GetMapping("/{id}")
    public User fetchUserById(@PathVariable Long id) {
        return adminService.findUserById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateUserDetails(@PathVariable Long id, @RequestBody User userUpdates) {
        try {
            adminService.updateUserDetails(id, userUpdates);

            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getCause() instanceof UserRegistrationException) {
                String errorMessage = e.getCause().getMessage();
                return ResponseEntity.badRequest().body(errorMessage);
            } else {
                throw e;
            }
        }
    }

    @AdminOnly
    @RequireAuthorization
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
    }
}
