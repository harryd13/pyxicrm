package com.pyxicm.prod.service;

import com.pyxicm.prod.model.Contact;
import com.pyxicm.prod.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public List<Contact> getAllContacts(String userId) {
        return contactRepository.findAllByUserId(userId);
    }

    public Contact createContact(Contact contact, String userId) {
        contact.setUserId(userId);
        contact.setCreatedAt(Instant.now());
        return contactRepository.save(contact);
    }

    public Optional<Contact> updateContact(String id, Contact updated, String userId) {
        return contactRepository.findById(id)
                .filter(c -> c.getUserId().equals(userId))
                .map(existing -> {
                    existing.setName(updated.getName());
                    existing.setPhone(updated.getPhone());
                    existing.setTags(updated.getTags());
                    return contactRepository.save(existing);
                });
    }

    public boolean deleteContact(String id, String userId) {
        return contactRepository.findById(id)
                .filter(c -> c.getUserId().equals(userId))
                .map(c -> {
                    contactRepository.delete(c);
                    return true;
                })
                .orElse(false);
    }
}
