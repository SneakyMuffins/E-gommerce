package com.egommerce.demo.service.Address;

import com.egommerce.demo.exception.AddressValidationException;
import com.egommerce.demo.exception.ResourceNotFoundException;
import com.egommerce.demo.exception.UserNotFoundException;
import com.egommerce.demo.model.Address.Address;
import com.egommerce.demo.model.User.User;
import com.egommerce.demo.repository.AddressRepository;
import com.egommerce.demo.utility.EntityUpdater;
import com.egommerce.demo.validation.Address.AddressValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final EntityUpdater entityUpdater;

    @Autowired
    public AddressService(AddressRepository addressRepository, EntityUpdater entityUpdater) {
        this.addressRepository = addressRepository;
        this.entityUpdater = entityUpdater;
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

    public Address updateAddressDetails(Long id, Address addressUpdates, User authenticatedUser) {
        Address address = findById(id);
        if (address != null) {
            Address updatedAddress = entityUpdater.updateEntity(address, addressUpdates);

            try {
                AddressValidation addressValidation = new AddressValidation(updatedAddress, authenticatedUser);
                addressValidation.validate();

                save(updatedAddress);
            } catch (AddressValidationException e) {
                throw new AddressValidationException(e.getMessage());
            }
        } else {
            throw new UserNotFoundException(id);
        }

        return address;
    }
}
