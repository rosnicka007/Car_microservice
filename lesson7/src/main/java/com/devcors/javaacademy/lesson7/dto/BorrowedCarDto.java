package com.devcors.javaacademy.lesson7.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BorrowedCarDto {
    private Long userId;
    private Integer carId;
}
