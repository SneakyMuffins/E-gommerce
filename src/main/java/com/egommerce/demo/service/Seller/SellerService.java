package com.egommerce.demo.service.Seller;

import com.egommerce.demo.exception.ResourceNotFoundException;
import com.egommerce.demo.model.Seller.Seller;
import com.egommerce.demo.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {
    private final SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public void createSeller(Seller seller) {
        sellerRepository.save(seller);
    }

    public Seller getSellerById(Long id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seller not found with id " + id));
    }

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Seller updateSellerDetails(Long id, Seller sellerUpdates) {
        Seller seller = getSellerById(id);
        if (seller != null) {
            seller.setName(sellerUpdates.getName());
            sellerRepository.save(seller);
        } else {
            throw new ResourceNotFoundException("Seller not found with id " + id);
        }

        return seller;
    }

    public void deleteSeller(Long id) {
        Seller seller = getSellerById(id);
        sellerRepository.delete(seller);
    }
}
