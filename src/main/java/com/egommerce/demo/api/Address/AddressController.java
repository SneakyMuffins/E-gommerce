package com.egommerce.demo.api.Address;

import com.egommerce.demo.annotation.RequireAuthorization;
import com.egommerce.demo.exception.AddressValidationException;
import com.egommerce.demo.model.Address.Address;
import com.egommerce.demo.model.User.User;
import com.egommerce.demo.security.JwtProvider;
import com.egommerce.demo.service.AddressService;
import com.egommerce.demo.service.UserService;
import com.egommerce.demo.validation.Address.AddressValidation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
@CrossOrigin
public class AddressController {
    private final AddressService addressService;
    private final JwtProvider jwtProvider;
    private final UserService userService;


    @Autowired
    public AddressController(AddressService addressService, JwtProvider jwtProvider, UserService userService) {
        this.addressService = addressService;
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    @RequireAuthorization
    @GetMapping
    public ResponseEntity<List<Address>> getAddresses(HttpServletRequest request) {
        User authenticatedUser = userService.getAuthenticatedUser(request);

        if (authenticatedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<Address> addresses = addressService.findAllByUserId(authenticatedUser.getId());
        return ResponseEntity.ok(addresses);
    }

    @RequireAuthorization
    @GetMapping("/{addressId}")
    public ResponseEntity<Address> getAddress(HttpServletRequest request, @PathVariable Long addressId) {
        User authenticatedUser = userService.getAuthenticatedUser(request);

        if (authenticatedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Address address = addressService.findById(addressId);

        AddressValidation addressValidation = new AddressValidation(address, authenticatedUser);

        try {
            addressValidation.validateOwnership();

            return ResponseEntity.ok(address);
        } catch (AddressValidationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequireAuthorization
    @PostMapping
    public ResponseEntity<String> postAddress(HttpServletRequest request, @RequestBody Address newAddress) {
        User authenticatedUser = userService.getAuthenticatedUser(request);

        if (authenticatedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }

        // Set the user of the address
        newAddress.setUser(authenticatedUser);

        AddressValidation addressValidation = new AddressValidation(newAddress);
        try {
            addressValidation.validate();
            Address savedAddress = addressService.save(newAddress);
            return ResponseEntity.ok().body("Address created with id " + savedAddress.getId());
        } catch (AddressValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequireAuthorization
    @PatchMapping("/{addressId}")
    public ResponseEntity<String> editAddress(HttpServletRequest request, @PathVariable Long addressId, @RequestBody Address updatedAddress) {
        Address address = addressService.findById(addressId);
        User authenticatedUser = userService.getAuthenticatedUser(request);

        if (authenticatedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }

        if (address == null) {
            return ResponseEntity.notFound().build();
        }

        AddressValidation addressValidation = new AddressValidation(address, authenticatedUser);

        try {
            addressValidation.validateOwnership();

            // Only update the fields that were provided in the request
            if (updatedAddress.getName() != null) {
                address.setName(updatedAddress.getName());
            }
            if (updatedAddress.getStreetAddress() != null) {
                address.setStreetAddress(updatedAddress.getStreetAddress());
            }
            if (updatedAddress.getCity() != null) {
                address.setCity(updatedAddress.getCity());
            }
            if (updatedAddress.getState() != null) {
                address.setState(updatedAddress.getState());
            }
            if (updatedAddress.getZipCode() != null) {
                address.setZipCode(updatedAddress.getZipCode());
            }
            if (updatedAddress.getCountry() != null) {
                address.setCountry(updatedAddress.getCountry());
            }

            Address updated = addressService.save(address);
            return ResponseEntity.ok().body("Address updated with id " + updated.getId());
        } catch (AddressValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @RequireAuthorization
    @DeleteMapping("/{addressId}")
    public ResponseEntity<String> deleteAddress(HttpServletRequest request, @PathVariable Long addressId) {
        Address address = addressService.findById(addressId);
        User authenticatedUser = userService.getAuthenticatedUser(request);

        if (authenticatedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }

        if (address == null) {
            return ResponseEntity.notFound().build();
        }

        AddressValidation addressValidation = new AddressValidation(address, authenticatedUser);

        try {
            addressValidation.validateOwnership();

            addressService.deleteById(addressId);
            return ResponseEntity.ok().body("Address deleted successfully");
        } catch (AddressValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
