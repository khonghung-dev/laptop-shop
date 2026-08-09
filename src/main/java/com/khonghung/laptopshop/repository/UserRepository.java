package com.khonghung.laptopshop.repository;

import com.khonghung.laptopshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    List<User> findOneByEmail(String email);
    Optional <User> findById(Long id);
    void deleteById(Long id);
}
