package com.pyxicm.prod.controller;

import com.pyxicm.prod.exception.NotFoundException;
import com.pyxicm.prod.exception.UnauthorizedException;
import com.pyxicm.prod.model.Contact;
import com.pyxicm.prod.security.JwtUtil;
import com.pyxicm.prod.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;
    private final JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<List<Contact>> getContacts(@RequestHeader("Authorization") String authHeader) {
        String userId = jwtUtil.extractUserId(authHeader.replace("Bearer ", ""));
        return ResponseEntity.ok(contactService.getAllContacts(userId));
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact,
                                                 @RequestHeader("Authorization") String authHeader) {
        System.out.println("📨 Incoming POST /contacts");
        System.out.println("Authorization header: " + authHeader);
        String userId = jwtUtil.extractUserId(authHeader.replace("Bearer ", ""));
        System.out.println("✅ Extracted userId: " + userId);
        return ResponseEntity.ok(contactService.createContact(contact, userId));
    }
    @GetMapping("/test")
    public String test() {
        System.out.println("🧪 Test endpoint hit");
        return "Contact endpoint works!";
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable String id,
                                                 @RequestBody Contact contact,
                                                 @RequestHeader("Authorization") String authHeader) {
        String userId = jwtUtil.extractUserId(authHeader.replace("Bearer ", ""));

        return contactService.updateContact(id, contact, userId)
                .map(updated -> ResponseEntity.ok(updated))
                .orElseThrow(() -> new UnauthorizedException("You are not allowed to update this contact or it does not exist."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable String id,
                                                @RequestHeader("Authorization") String authHeader) {
        String userId = jwtUtil.extractUserId(authHeader.replace("Bearer ", ""));

        if (contactService.deleteContact(id, userId)) {
            return ResponseEntity.ok("Deleted");
        } else {
            throw new UnauthorizedException("You are not allowed to delete this contact or it does not exist.");
        }
    }
}
