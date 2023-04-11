package ro.itschool.controller.model;

import lombok.*;

@Setter
@Getter
public class RegisterRequest {
    private String email;
    private String password;
    private String fullName;
    //can contain other fields like firstname, lastname, etc
}
