package com.devcors.javaacademy.lesson7.dto;

import com.devcors.javaacademy.lesson7.data.entity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String email;
    private String firstname;
    private String lastname;
    private String address;
    private UserRole role;
}
