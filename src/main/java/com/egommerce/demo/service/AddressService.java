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

    public Address findById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id " + id));
    }

    public List<Address> findAllByUserId(Long userId) {
        return addressRepository.findAllByUser_Id(userId);
    }

    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}
