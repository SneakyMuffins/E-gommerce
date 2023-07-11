package com.egommerce.demo.seeder;

import com.egommerce.demo.model.User.User;
import com.egommerce.demo.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder {

    private final UserService userService;

    @Autowired
    public UserSeeder(UserService userService) {
        this.userService = userService;
    }

    public void seed() {
        if (userService.findAll().isEmpty()) {
            // Create regular user
            User regularUser = new User("Test user", "Option123!", "test@gmail.com");
            userService.registerUser(regularUser);

            // Create admin user
            User adminUser = new User("Admin user", "Option123!", "admin@gmail.com");
            adminUser.setAdmin(true);
            userService.save(adminUser); // Save the admin user directly

            System.out.println("User seeding completed.");
        }
    }
}
