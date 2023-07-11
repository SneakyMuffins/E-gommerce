package com.egommerce.demo.seeder;

import com.egommerce.demo.model.Seller.Seller;
import com.egommerce.demo.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SellerSeeder {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerSeeder(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public void seed() {
        if (sellerRepository.count() > 0) {
            return;
        }

        Seller seller1 = new Seller("Razer");
        Seller seller2 = new Seller("SteelSeries");
        Seller seller3 = new Seller("1upkeyboards");
        Seller seller4 = new Seller("G.SKILL");
        Seller seller5 = new Seller("Ducky");

        sellerRepository.save(seller1);
        sellerRepository.save(seller2);
        sellerRepository.save(seller3);
        sellerRepository.save(seller4);
        sellerRepository.save(seller5);
    }
}
