package com.austin.smartschoolapi.repository.oauthrepository;


import com.austin.smartschoolapi.model.auth.OauthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OauthClientDetailsRepo extends JpaRepository<OauthClientDetails, Long> {

    Optional<OauthClientDetails> findByClientId(String clientId);
//    Optional<OauthClientDetails> findByUsername(String clientId);
}
