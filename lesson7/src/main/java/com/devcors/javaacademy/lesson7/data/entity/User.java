package com.devcors.javaacademy.lesson7.data.entity;

import com.devcors.javaacademy.lesson7.data.entity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_")
public class User {

    @GeneratedValue
    @Id
    private Long id;

    private String email;
    private String firstname;
    private String lastname;
    private String address;
    @Enumerated
    private UserRole role;

}
