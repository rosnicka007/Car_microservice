package com.devcors.javaacademy.lesson7.rest;

import com.devcors.javaacademy.lesson7.data.entity.BorrowedCar;
import com.devcors.javaacademy.lesson7.dto.BorrowedCarDto;
import com.devcors.javaacademy.lesson7.service.BorrowedCarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/{userId}/car/")
public class BorrowedCarController {
    private final BorrowedCarService borrowedCarService;

    public BorrowedCarController(BorrowedCarService borrowedCarService) {
        this.borrowedCarService = borrowedCarService;
    }

    // method for borrowing a car
    @PutMapping("/borrow/{carId}")
    public ResponseEntity<?> borrowCar(@PathVariable Long userId, @PathVariable Integer carId) {
        BorrowedCarDto borrowedCarDto = BorrowedCarDto.builder()
                .userId(userId)
                .carId(carId)
                .build();

        borrowedCarService.borrowCar(borrowedCarDto);
        System.out.println("Car " + carId + " was borrowed by user " + userId);
        return ResponseEntity.ok("Car was borrowed.");
    }

    // method for returning a car
    @PutMapping("/return/{carId}")
    public ResponseEntity<?> returnCar(@PathVariable Long userId, @PathVariable Integer carId) {

        if (borrowedCarService.isCarBorrowed(userId, carId)) {
            BorrowedCarDto borrowedCarDto = BorrowedCarDto.builder()
                    .userId(userId)
                    .carId(carId)
                    .build();

            borrowedCarService.returnCar(borrowedCarDto);
            System.out.println("Car " + carId + " was returned by user " + userId);
            return ResponseEntity.ok("Car was returned.");
        } else {
            System.out.println("Car " + carId + " was not borrowed by user " + userId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car was not borrowed by the user.");
        }
    }

    // method for making a list of borrowed cars by user
    @GetMapping()
    public ResponseEntity<List<BorrowedCar>> getBorrowedCarsByUserId(@PathVariable Long userId) {
        List<BorrowedCar> borrowedCars = borrowedCarService.getBorrowedCarsByUserId(userId);
        if (!borrowedCars.isEmpty()) {
            return ResponseEntity.ok(borrowedCars);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        }
    }
}



