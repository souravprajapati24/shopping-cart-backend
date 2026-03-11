package com.sourav.shoppingcart.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
