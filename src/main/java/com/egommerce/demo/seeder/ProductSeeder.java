package com.egommerce.demo.seeder;

import com.egommerce.demo.model.Category.Category;
import com.egommerce.demo.model.Product.Product;
import com.egommerce.demo.model.Seller.Seller;
import com.egommerce.demo.repository.CategoryRepository;
import com.egommerce.demo.repository.ProductRepository;
import com.egommerce.demo.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductSeeder {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SellerRepository sellerRepository;

    @Autowired
    public ProductSeeder(ProductRepository productRepository, CategoryRepository categoryRepository,
                         SellerRepository sellerRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.sellerRepository = sellerRepository;
    }

    public void seed() {
        if (productRepository.count() == 0) {
            List<Category> categories = categoryRepository.findAll();
            List<Seller> sellers = sellerRepository.findAll();

            Product[] products = {
                    new Product("Razer BlackWidow Elite Mechanical Gaming Keyboard", categories.get(0),
                            sellers.get(0), 149.99, "Mechanical gaming keyboard with Razer Green switches, RGB lighting, and programmable macros.",
                            "https://m.media-amazon.com/images/I/81oLMQUj4fL.jpg", 10),
                    new Product("SteelSeries Apex Pro TKL Mechanical Gaming Keyboard", categories.get(0),
                            sellers.get(1), 179.99, "Compact tenkeyless (TKL) mechanical gaming keyboard with SteelSeries OmniPoint switches and customizable per-key RGB lighting.",
                            "https://m.media-amazon.com/images/I/81KHRaChNAL.jpg", 8),
                    new Product("1upkeyboards 60% DIY Kit", categories.get(0),
                            sellers.get(2), 99.99, "Do-it-yourself (DIY) kit for building a 60% custom mechanical keyboard, includes PCB, case, and plate.",
                            "https://1upkeyboards.com/wp-content/uploads/2022/11/Case-1upkeyboards-Level60-01.jpg", 5),
                    new Product("G.SKILL KM360 Compact Mechanical Keyboard", categories.get(0),
                            sellers.get(3), 89.99, "Compact tenkeyless (TKL) mechanical keyboard with G.SKILL mechanical switches and white backlighting.",
                            "https://m.media-amazon.com/images/I/71tFUnJdOKL.jpg", 15),
                    new Product("Ducky One 2 Mini RGB Mechanical Keyboard", categories.get(0),
                            sellers.get(4), 129.99, "Compact 60% mechanical keyboard with Ducky PBT keycaps, RGB lighting, and multiple switch options.",
                            "https://www.duckychannel.com.tw/upload/2019_05_112/20190511120502dgnhbl7NU1.png", 12),
                    new Product("GMK Nautilus Keycap Set", categories.get(1),
                            sellers.get(0), 139.99, "High-quality keycap set featuring the GMK Nautilus colorway, compatible with Cherry MX switches.",
                            "https://zambumon.files.wordpress.com/2020/01/zambumon_nautilus_2_zambumon_jules_jumbo.png?w=1440", 10),
                    new Product("SteelSeries Pudding Keycaps", categories.get(1),
                            sellers.get(1), 39.99, "Translucent double-shot keycaps with a pudding-style design, allows for enhanced RGB lighting effects.",
                            "https://m.media-amazon.com/images/I/6146LELlqZL.jpg", 20),
                    new Product("1upkeyboards SA Pulse Keycap Set", categories.get(1),
                            sellers.get(2), 89.99, "Sculpted SA profile keycap set in the iconic Pulse colorway, perfect for enthusiasts.",
                            "https://massdrop-s3.imgix.net/product-images/pulse-sa-keycap-set/FP/HbdTI2QDOZSyTDJTEEkQ_Untitled1%20copy%20page.jpg?auto=format&fm=jpg&fit=fill&w=500&h=333&bg=f0f0f0&dpr=1&chromasub=444&q=70", 15),
                    new Product("G.SKILL Crystal Crown Keycap Set", categories.get(1),
                            sellers.get(3), 49.99, "Unique and stylish keycap set with crystal-like transparent keycaps for an eye-catching look.",
                            "https://www.gskill.com/img/overview/cckeycap/04-cckeycap-standard-ansi-104-na-english-layout-black.jpg", 8),
                    new Product("Ducky Joker Keycap Set", categories.get(1),
                            sellers.get(4), 89.99, "Vibrant and colorful keycap set featuring the iconic Joker theme, compatible with Cherry MX switches.",
                            "https://www.duckychannel.com.tw/upload/2019_05_182/20190518115129l2wddrh543.jpg", 12),
                    new Product("Razer Green Mechanical Switches", categories.get(2),
                            sellers.get(0), 9.99, "Tactile and clicky mechanical switches with an audible click sound and optimized actuation force.",
                            "https://assets2.razerzone.com/images/pnx.assets/c8e21a9671b4187db2dcf6abfb717552/razer-green-linear-mechanical-switch.png", 50),
                    new Product("SteelSeries OmniPoint RGB Switches", categories.get(2),
                            sellers.get(1), 14.99, "Linear mechanical switches with adjustable actuation distance and customizable per-key RGB lighting.",
                            "https://media.steelseriescdn.com/thumbs/filer_public/b1/d1/b1d1b0d3-30d5-4477-bb8f-bad442cfb894/webprim_apexpro.png__1200x627_crop-fit_optimize_subsampling-2.png", 40),
                    new Product("1upkeyboards Zealios V2 Switches", categories.get(2),
                            sellers.get(2), 8.99, "Premium tactile switches with a smooth and responsive feel, perfect for mechanical keyboards.",
                            "https://m.media-amazon.com/images/I/416KlVK2YHL.jpg", 35),
                    new Product("G.SKILL Crystal Crown Switches", categories.get(2),
                            sellers.get(3), 11.99, "Transparent mechanical switches with crystal-like housings for a unique and elegant appearance.",
                            "https://www.gskill.com/_upload/images/160259323411.png", 20),
                    new Product("Ducky Silent Red Switches", categories.get(2),
                            sellers.get(4), 13.99, "Red mechanical switches with duck-like housings for a unique and elegant appearance.",
                            "https://media.ldlc.com/r1600/ld/products/00/05/96/58/LD0005965872_0005965889.jpg", 20),
                    new Product("1upkeyboards DZ60 RGB Hot-Swap PCB", categories.get(3),
                            sellers.get(2), 49.99, "A 60% size PCB with RGB backlighting and hot-swap sockets for easy switch customization.",
                            "https://kbdfans.com/cdn/shop/products/DZ60RGBWKL6.jpg?v=1627521072", 10),
                    new Product("G.SKILL KM360 TKL PCB", categories.get(3),
                            sellers.get(3), 39.99, "A tenkeyless (TKL) PCB designed for custom keyboard builds, offering customizable features and compatibility.",
                            "https://www.techpowerup.com/review/g-skill-km360-keyboard-crystal-crown-keycaps/images/disassembly-5.jpg", 15),
                    new Product("Drop Alt High-Profile PCB", categories.get(3),
                            sellers.get(1), 89.99, "The high-profile version of the Drop Alt PCB, designed for a more elevated typing experience.",
                            "https://m.media-amazon.com/images/I/81x0Q4fA2TS.jpg", 8),
                    new Product("KBDfans DZ65 RGB PCB", categories.get(3),
                            sellers.get(0), 59.99, "A compact 65% PCB with customizable RGB lighting and support for various layout options.",
                            "https://kbdfans.com/cdn/shop/products/DZ65GRBv3PCB7.jpg?v=1622618647", 12),
                    new Product("YMDK 96% PCB", categories.get(3),
                            sellers.get(4), 49.99, "A 96% size PCB for building a custom keyboard with dedicated arrow keys and navigation cluster.",
                            "https://m.media-amazon.com/images/I/71Bkgjw1zML._AC_UF894,1000_QL80_.jpg", 20),
                    new Product("KBDFans Tofu65 Aluminum Case", categories.get(4),
                            sellers.get(0), 119.99, "A premium aluminum case designed for the 65% form factor, providing a sturdy and stylish enclosure for your custom keyboard.",
                            "https://www.maxgaming.com/bilder/artiklar/20858.jpg?m=1643296415", 10),
                    new Product("Drop CTRL High-Profile Aluminum Case", categories.get(4),
                            sellers.get(1), 149.99, "An elevated high-profile aluminum case designed for the Drop CTRL keyboard, offering a premium look and feel.",
                            "https://mechanicalkeyboards.com/shop/images/products/large_9047_large_KBDTofu65case0157_main.jpg", 8),
                    new Product("1upkeyboards 60% Acrylic Case", categories.get(4),
                            sellers.get(2), 29.99, "A clear acrylic case for 60% keyboards, allowing for a transparent and minimalist aesthetic.",
                            "https://1upkeyboards.com/wp-content/uploads/2022/11/Case-1upkeyboards-Level60-Lift-Kit-02.jpg", 15),
                    new Product("G.SKILL KM360 TKL Plastic Case", categories.get(4),
                            sellers.get(3), 34.99, "A durable plastic case designed specifically for the G.SKILL KM360 TKL keyboard.",
                            "https://www.gskill.com/_upload/images/157649065910.png", 12),
                    new Product("Ducky One 2 Mini ABS Keycap Set", categories.get(4),
                            sellers.get(4), 39.99, "A replacement ABS keycap set compatible with the Ducky One 2 Mini keyboard, featuring various color options.",
                            "https://m.media-amazon.com/images/I/61alCa+VYTS._AC_UF894,1000_QL80_.jpg", 20)
            };

            for (Product product : products) {
                productRepository.save(product);
            }

            System.out.println("Product seeding completed.");
        }
    }
}
