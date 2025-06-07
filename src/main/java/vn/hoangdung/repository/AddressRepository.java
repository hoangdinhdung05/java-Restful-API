package vn.hoangdung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hoangdung.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
