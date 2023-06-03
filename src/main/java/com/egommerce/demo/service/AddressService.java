package com.egommerce.demo.service;

import com.egommerce.demo.annotation.ExcludeUpdate;
import com.egommerce.demo.exception.ResourceNotFoundException;
import com.egommerce.demo.exception.UserNotFoundException;
import com.egommerce.demo.model.Address.Address;
import com.egommerce.demo.model.User.User;
import com.egommerce.demo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
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

    public Address updateAddressDetails(Long id, Address addressUpdates) {
        Address address = findById(id);
        if (address != null) {
            Field[] fields = addressUpdates.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(addressUpdates);

                    if (value != null && !field.isAnnotationPresent(ExcludeUpdate.class)) {
                        Field userField = address.getClass().getDeclaredField(field.getName());
                        userField.setAccessible(true);
                        userField.set(address, value);
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            save(address);
        } else {
            throw new UserNotFoundException(id);
        }

        return address;
    }
}
