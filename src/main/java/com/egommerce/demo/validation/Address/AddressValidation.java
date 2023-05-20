package com.egommerce.demo.validation.Address;

import com.egommerce.demo.exception.AddressValidationException;
import com.egommerce.demo.model.Address.Address;
import com.egommerce.demo.model.User.User;

public class AddressValidation {
    private final Address address;
    private User authenticatedUser;

    public AddressValidation(Address address) {
        this.address = address;
    }

    public AddressValidation(Address address, User authenticatedUser) {
        this.address = address;
        this.authenticatedUser = authenticatedUser;
    }

    public void validate() throws AddressValidationException {
        if (authenticatedUser != null) {
            validateOwnership();
        }

        validateName(address.getName());
        validateStreetAddress(address.getStreetAddress());
        validateCity(address.getCity());
        validateState(address.getState());
        validateZipCode(address.getZipCode());
        validateCountry(address.getCountry());
    }

    public void validateOwnership() throws AddressValidationException {
        if (address.getUser() == null || !address.getUser().equals(authenticatedUser)) {
            throw new AddressValidationException("You do not have permission to edit this address.");
        }
    }

    public void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new AddressValidationException("Name is required.");
        }
    }

    public void validateStreetAddress(String streetAddress) {
        if (streetAddress == null || streetAddress.isEmpty()) {
            throw new AddressValidationException("Street address is required.");
        }
    }

    public void validateCity(String city) {
        if (city == null || city.isEmpty()) {
            throw new AddressValidationException("City is required.");
        }
    }

    public void validateState(String state) {
        if (state == null || state.isEmpty()) {
            throw new AddressValidationException("State is required.");
        }
    }

    public void validateZipCode(String zipCode) {
        if (zipCode == null || zipCode.isEmpty()) {
            throw new AddressValidationException("Zip code is required.");
        }

        String regex = "^\\d{5}(-\\d{4})?$";
        if (!zipCode.matches(regex)) {
            throw new AddressValidationException("Invalid zip code format. " +
                    "Zip code must be in the format 12345 or 12345-6789.");
        }
    }

    public void validateCountry(String country) {
        if (country == null || country.isEmpty()) {
            throw new AddressValidationException("Country is required.");
        }
    }
}
