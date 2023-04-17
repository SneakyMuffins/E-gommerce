package com.egommerce.demo.api.Address;

import com.egommerce.demo.annotation.RequireAuthorization;
import com.egommerce.demo.model.Address.Address;
import com.egommerce.demo.security.JwtProvider;
import com.egommerce.demo.service.AddressService;
import com.egommerce.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public ResponseEntity<String> login(@RequestBody Address newAddress) {
        return ResponseEntity.ok().build();
    }
}
