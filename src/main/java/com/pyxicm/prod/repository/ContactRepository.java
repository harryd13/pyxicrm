package com.pyxicm.prod.repository;

import com.pyxicm.prod.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {
    List<Contact> findAllByUserId(String userId);
}
