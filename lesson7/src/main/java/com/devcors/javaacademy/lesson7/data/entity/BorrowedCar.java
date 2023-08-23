package com.devcors.javaacademy.lesson7.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class BorrowedCar {

    @GeneratedValue
    @Id
    private Integer borrowedCarId;

    private Long userId;
    private Integer carId;


}


