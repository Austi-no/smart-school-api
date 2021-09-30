package com.austin.smartschoolapi.repository;

import com.austin.smartschoolapi.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {
}
