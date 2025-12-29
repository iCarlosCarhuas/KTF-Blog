package com.app.ktf.blog.dto.auth;

import lombok.Data;
import java.util.Set;

@Data
public class SignupRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Set<String> role;
}
