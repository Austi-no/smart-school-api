package com.austin.smartschoolapi.repository.oauthrepository;

import com.austin.smartschoolapi.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);

}


