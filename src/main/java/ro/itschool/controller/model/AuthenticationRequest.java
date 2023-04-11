package ro.itschool.controller.model;

import lombok.*;

@Setter
@Getter
public class AuthenticationRequest {
    private String email;
    private String password;
}
