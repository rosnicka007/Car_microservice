package com.devcors.javaacademy.lesson7.service;

import com.devcors.javaacademy.lesson7.data.entity.Car;
import com.devcors.javaacademy.lesson7.data.repository.CarRepository;
import com.devcors.javaacademy.lesson7.dto.CarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    // service method for making a list of all cars
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // service method for searching a car by id
    public Optional<Car> getCarById(Integer id) {
        return carRepository.findById(id);
    }

    // service method for adding a new car
    public Car addCar(CarDto carDto) {
        Car car = Car.builder()
                .year(carDto.getYear())
                .brand(carDto.getBrand())
                .licencePlate(carDto.getLicencePlate())
                .type(carDto.getType())
                .color(carDto.getColor())
                .build();

        return carRepository.save(car);

    }

    // service method for updating information about a car
    public Optional<Car> updatedCar(Integer id, CarDto updatedCarDto) {
        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()) {
            Car existingCar = optionalCar.get();

            existingCar.setYear(updatedCarDto.getYear());
            existingCar.setBrand(updatedCarDto.getBrand());
            existingCar.setLicencePlate(updatedCarDto.getLicencePlate());
            existingCar.setType(updatedCarDto.getType());
            existingCar.setColor(updatedCarDto.getColor());

            Car updatedCar = carRepository.save(existingCar);
            return Optional.of(updatedCar);
        } else {
            return Optional.empty();

        }
    }

    // service method for deleting a car
    // (Viktore přiznávám, že to použití boolean není z mé hlavy a musela jsem si nechat poradit)
    public boolean deleteCar(Integer id) {

        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // service method for searching a car by a brand
    public List<Car> getCarsByBrand(String brandName) {
        return carRepository.findByBrand(brandName);
    }


}

