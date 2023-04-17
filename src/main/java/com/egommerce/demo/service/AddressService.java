package com.egommerce.demo.service;

import com.egommerce.demo.exception.ResourceNotFoundException;
import com.egommerce.demo.model.Address.Address;
import com.egommerce.demo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public Address findByIdAndUserId(Long id, String userId) {
        return addressRepository.findByIdAndUser_Id(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id " + id + " and user id " + userId));
    }

    public List<Address> findAllByUserId(String userId) {
        return addressRepository.findAllByUser_Id(userId);
    }

    public void deleteByIdAndUserId(Long id, String userId) {
        addressRepository.deleteByIdAndUser_Id(id, userId);
    }
}
