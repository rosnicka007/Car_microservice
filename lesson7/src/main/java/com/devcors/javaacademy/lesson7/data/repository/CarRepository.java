package com.devcors.javaacademy.lesson7.data.repository;

import com.devcors.javaacademy.lesson7.data.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findByBrand(String brandName);


}
