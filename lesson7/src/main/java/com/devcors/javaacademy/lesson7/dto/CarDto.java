package com.devcors.javaacademy.lesson7.dto;

import com.devcors.javaacademy.lesson7.data.entity.enums.CarColor;
import com.devcors.javaacademy.lesson7.data.entity.enums.CarType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private Short year;
    private String brand;
    private String licencePlate;
    private CarType type;
    private CarColor color;
}
