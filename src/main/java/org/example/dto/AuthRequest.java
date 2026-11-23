package org.example.dto;

import lombok.Data;
import lombok.Generated;

@Data
@Generated
public class AuthRequest {

    private String username;
    private String password;

    public AuthRequest() {
    }

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
