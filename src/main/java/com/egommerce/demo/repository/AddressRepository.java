package com.egommerce.demo.repository;

import com.egommerce.demo.model.Address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("addressRepository")
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByIdAndUser_Id(Long id, String userId);

    List<Address> findAllByUser_Id(String userId);

    void deleteByIdAndUser_Id(Long id, String userId);
}
