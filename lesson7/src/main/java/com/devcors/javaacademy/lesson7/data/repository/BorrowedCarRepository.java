package com.devcors.javaacademy.lesson7.data.repository;

import com.devcors.javaacademy.lesson7.data.entity.BorrowedCar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowedCarRepository extends JpaRepository<BorrowedCar, Integer> {
    BorrowedCar findByUserIdAndCarId(Long userId, Integer carId);
    List<BorrowedCar> findByUserId(Long userId);
}


