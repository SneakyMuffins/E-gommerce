package com.egommerce.demo.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeederRunner implements CommandLineRunner {

    private final CategorySeeder categorySeeder;
    private final SellerSeeder sellerSeeder;
    private final ProductSeeder productSeeder;
    private final UserSeeder userSeeder;

    @Autowired
    public SeederRunner(CategorySeeder categorySeeder, SellerSeeder sellerSeeder, ProductSeeder productSeeder, UserSeeder userSeeder) {
        this.categorySeeder = categorySeeder;
        this.sellerSeeder = sellerSeeder;
        this.productSeeder = productSeeder;
        this.userSeeder = userSeeder;
    }

    @Override
    public void run(String... args) {
        categorySeeder.seed();
        sellerSeeder.seed();
        productSeeder.seed();
        userSeeder.seed();
    }
}
