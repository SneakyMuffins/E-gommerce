package com.egommerce.demo.api.Admin;

import com.egommerce.demo.annotation.AdminOnly;
import com.egommerce.demo.annotation.RequireAuthorization;
import com.egommerce.demo.model.User.User;
import com.egommerce.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @AdminOnly
    @RequireAuthorization
    @GetMapping("/users")
    public List<User> fetchAllUsers() {
        return adminService.getAllUsers();
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserDetails(@PathVariable Long id, @RequestBody User userUpdates) {
        adminService.updateUserDetails(id, userUpdates);
    }

    @AdminOnly
    @RequireAuthorization
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
    }
}
