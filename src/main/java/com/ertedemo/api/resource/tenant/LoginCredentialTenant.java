package com.ertedemo.api.resource.tenant;

import lombok.Data;

@Data
public class LoginCredentialTenant {
    private String email;
    private String password;
}