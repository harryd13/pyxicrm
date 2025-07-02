package com.pyxicm.prod.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
