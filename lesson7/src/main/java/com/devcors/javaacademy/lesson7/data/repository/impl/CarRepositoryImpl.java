/*package com.devcors.javaacademy.lesson6.data.repository.impl;

import com.devcors.javaacademy.lesson6.data.entity.Car;
import com.devcors.javaacademy.lesson6.data.entity.enums.CarColor;
import com.devcors.javaacademy.lesson6.data.entity.enums.CarType;
import com.devcors.javaacademy.lesson6.data.repository.CarRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class  CarRepositoryImpl implements CarRepository {

    private List<Car> cars = List.of(
            Car.builder()
                    .id(1)
                    .brand("BMW")
                    .year((short) 1999)
                    .licencePlate("4H44444")
                    .color(CarColor.BLACK)
                    .type(CarType.HATCHBACK)
                    .build(),
            Car.builder()
                    .id(2)
                    .brand("Audi")
                    .year((short) 2002)
                    .licencePlate("5H55555")
                    .color(CarColor.PINK)
                    .type(CarType.COMBI)
                    .build()
    );

    @Override
    public List<Car> findAll() {
        return cars;
    }

    @Override
    public Optional<Car> findById(Integer id) {
        return cars.stream().filter(car -> id.equals(car.getId())).findFirst();
    }

}*/
