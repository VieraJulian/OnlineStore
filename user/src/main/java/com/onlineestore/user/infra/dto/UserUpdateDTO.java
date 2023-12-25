package com.onlineestore.user.infra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateDTO {

    private Long id;
    private String username;
    private String password;
    private String newPassword;
    private String email;
    private String address;
    private String role;
}
