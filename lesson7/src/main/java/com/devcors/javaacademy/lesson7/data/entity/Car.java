package com.devcors.javaacademy.lesson7.data.entity;

import com.devcors.javaacademy.lesson7.data.entity.enums.CarColor;
import com.devcors.javaacademy.lesson7.data.entity.enums.CarType;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="year_")
    private Short year;
    private String brand;
    private String licencePlate;
    @Enumerated
    private CarType type;
    @Enumerated
    private CarColor color;

}
