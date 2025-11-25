package org.paymentwallet.userservice.repository;

import org.paymentwallet.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User , UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findUserById(UUID id);

    void deleteUserById(UUID id);
}
