package com.egommerce.demo.repository;

import com.egommerce.demo.model.Address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("addressRepository")
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAll();
    Optional<Address> findById(Long id);

    List<Address> findAllByUser_Id(Long userId);

    void deleteById(Long id);
}
