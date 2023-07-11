package com.egommerce.demo.api.Admin.Seller;

import com.egommerce.demo.annotation.AdminOnly;
import com.egommerce.demo.annotation.RequireAuthorization;
import com.egommerce.demo.model.Category.Category;
import com.egommerce.demo.model.Seller.Seller;
import com.egommerce.demo.service.Admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/sellers")
@CrossOrigin
public class SellerController {

    private final AdminService adminService;

    @Autowired
    public SellerController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<Seller> getAllSellers() {
        return adminService.getAllSellers();
    }

    @GetMapping("{id}")
    public Seller getSellerById(@PathVariable Long id) {
        return adminService.getSellerById(id);
    }

    @AdminOnly
    @RequireAuthorization
    @PostMapping
    public ResponseEntity<Object> createSeller(@RequestBody Seller seller) {
        try {
            adminService.createSeller(seller);

            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @AdminOnly
    @RequireAuthorization
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateSellerDetails(@PathVariable Long id, @RequestBody Seller sellerUpdates) {
        try {
            adminService.updateSellerDetails(id, sellerUpdates);

            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @AdminOnly
    @RequireAuthorization
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSeller(@PathVariable Long id) {
        try {
            adminService.deleteSeller(id);

            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
