package vn.hoangdung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hoangdung.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
