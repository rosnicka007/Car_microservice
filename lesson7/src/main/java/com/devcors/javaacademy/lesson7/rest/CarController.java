package com.devcors.javaacademy.lesson7.rest;

import com.devcors.javaacademy.lesson7.data.entity.Car;
import com.devcors.javaacademy.lesson7.dto.CarDto;
import com.devcors.javaacademy.lesson7.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    // method for making a list of all cars
    @GetMapping
    public ResponseEntity<List<Car>> getCars() {
        List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok().body(cars);
    }

    // method for searching a car by id
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Integer id) {
        Optional<Car> carOptional = carService.getCarById(id);

        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            return ResponseEntity.ok().body(car);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // method for adding a new car
    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody CarDto carDto) {

        Car newCar = carService.addCar(carDto);
        return ResponseEntity.status(HttpStatus.OK).body(newCar);
    }

    // method for updating information about a car
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Integer id, @RequestBody CarDto updatedCarDto) {
        Optional<Car> updatedCar = carService.updatedCar(id, updatedCarDto);

        if (updatedCar.isPresent()) {
            return ResponseEntity.ok().body(updatedCar.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Car id " + id + " does not exist.");
        }
    }

    // method for deleting a car selected by id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Integer id) {
        boolean deleted = carService.deleteCar(id);

        if (deleted) {
           return ResponseEntity.ok("Car id " + id + " was deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Car id " + id + "does not exist.");
        }
    }

    //method for filtering cars by their brand
    @GetMapping("/filter")
    private List<Car> getCarsByBrandName(@RequestParam("brandName") String brandName) {
        return carService.getCarsByBrand(brandName);
    }
    }


