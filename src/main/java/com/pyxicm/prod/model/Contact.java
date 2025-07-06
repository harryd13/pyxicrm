package com.pyxicm.prod.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {

    @Id
    private String id;

    private String name;
    private String phone;
    private List<String> tags;
    private Instant createdAt;

    private String userId;
}
