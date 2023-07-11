package com.egommerce.demo.seeder;

import com.egommerce.demo.model.Address.Address;
import com.egommerce.demo.model.User.User;
import com.egommerce.demo.service.Address.AddressService;
import com.egommerce.demo.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressSeeder {

    private final AddressService addressService;
    private final UserService userService;

    @Autowired
    public AddressSeeder(AddressService addressService, UserService userService) {
        this.addressService = addressService;
        this.userService = userService;
    }

    public void seed() {
        if (addressService.findAll().isEmpty()) {
            // Create address for regular user
            User regularUser = userService.getUserByEmail("test@gmail.com");
            if (regularUser != null) {
                Address regularUserAddress = new Address("John's address", "123 Main Streets of Zimbabwe", "New York", "NY", "10001", "USA");
                regularUserAddress.setUser(regularUser);
                addressService.save(regularUserAddress);
            }

            // Create address for admin user
            User adminUser = userService.getUserByEmail("admin@gmail.com");
            if (adminUser != null) {
                Address adminUserAddress = new Address("John's Girlfriend's address", "456 Broad Avenue", "Los Angeles", "CA", "90001", "USA");
                adminUserAddress.setUser(adminUser);
                addressService.save(adminUserAddress);
            }

            System.out.println("Address seeding completed.");
        }
    }
}
