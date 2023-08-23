package com.devcors.javaacademy.lesson7.service;

import com.devcors.javaacademy.lesson7.data.entity.BorrowedCar;
import com.devcors.javaacademy.lesson7.data.repository.BorrowedCarRepository;
import com.devcors.javaacademy.lesson7.dto.BorrowedCarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowedCarService {

    @Autowired
    private BorrowedCarRepository borrowedCarRepository;

    // service method for borrowing a car
    public void borrowCar(BorrowedCarDto borrowedCarDto) {
        BorrowedCar borrowedCar = BorrowedCar.builder()
                .userId(borrowedCarDto.getUserId())
                .carId(borrowedCarDto.getCarId())
                .build();

        borrowedCarRepository.save(borrowedCar);
    }

    // service methods for returning car by user-id and car-id

    //step 1 - checking if the car is borrowed or not
    public boolean isCarBorrowed(Long userId, Integer carId) {
        BorrowedCar borrowedCar = borrowedCarRepository.findByUserIdAndCarId(userId, carId);
        return borrowedCar != null;
    }
    //step 2 - returning borrowed car = deleting car
    public void returnCar(BorrowedCarDto borrowedCarDto) {
        BorrowedCar borrowedCar = borrowedCarRepository.findByUserIdAndCarId(
                borrowedCarDto.getUserId(),
                borrowedCarDto.getCarId()
        );
        if (borrowedCar != null) {
            borrowedCarRepository.delete(borrowedCar);
        }
    }

    // service method for making a list of borrowed car(s) by user
    public List<BorrowedCar> getBorrowedCarsByUserId(Long userId) {
        return borrowedCarRepository.findByUserId(userId);
    }



}

