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
    private final AddressSeeder addressSeeder;
    private final OrderSeeder orderSeeder;

    @Autowired
    public SeederRunner(CategorySeeder categorySeeder, SellerSeeder sellerSeeder, ProductSeeder productSeeder, 
                        UserSeeder userSeeder, AddressSeeder addressSeeder, OrderSeeder orderSeeder) {
        this.categorySeeder = categorySeeder;
        this.sellerSeeder = sellerSeeder;
        this.productSeeder = productSeeder;
        this.userSeeder = userSeeder;
        this.addressSeeder = addressSeeder;
        this.orderSeeder = orderSeeder;
    }

    @Override
    public void run(String... args) {
        categorySeeder.seed();
        sellerSeeder.seed();
        productSeeder.seed();
        userSeeder.seed();
        addressSeeder.seed();
        orderSeeder.seed();
    }
}
