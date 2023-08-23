package com.devcors.javaacademy.lesson7;

import com.devcors.javaacademy.lesson7.data.entity.BorrowedCar;
import com.devcors.javaacademy.lesson7.data.repository.BorrowedCarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = Lesson7Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class BorrowedCarControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private BorrowedCarRepository borrowedCarRepository;

    @AfterEach
    void afterEach() {borrowedCarRepository.deleteAll();}

    private static final Long userId_1 = 777L;
    private static final Long userId_2 = 999L;

    private static final Integer carId_1 = 11;
    private static final Integer carId_2 = 22;
    private static final Integer carId_3 = 33;

    private static final BorrowedCar BORROWED_CAR_1 = BorrowedCar.builder()
            .userId(userId_1)
            .carId(carId_1)
            .build();

    private static final BorrowedCar BORROWED_CAR_2 = BorrowedCar.builder()
            .userId(userId_2)
            .carId(carId_2)
            .build();

    private static final BorrowedCar BORROWED_CAR_3 = BorrowedCar.builder()
            .userId(userId_1)
            .carId(carId_3)
            .build();


    @Test
    void borrowCarShouldReturnOk() {
        webTestClient.put()
                .uri("/{userId}/car/borrow/{carId}", userId_1, carId_1)
                .bodyValue(BORROWED_CAR_1)
                .exchange()
                .expectStatus()
                .isOk();

        List<BorrowedCar> borrowedCars = borrowedCarRepository.findAll();
        assertFalse(CollectionUtils.isEmpty(borrowedCars));
        assertEquals(1, borrowedCars.size());
        BorrowedCar borrowedCar = borrowedCars.get(0);

        assertEquals(userId_1, borrowedCar.getUserId());
        assertEquals(carId_1, borrowedCar.getCarId());
    }

    @Test
    void returnCarShouldReturnOk() {
        borrowedCarRepository.save(BORROWED_CAR_1);

        webTestClient.put()
                .uri("/{userId}/car/return/{carId}", userId_1, carId_1)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(response -> assertEquals("Car was returned.", response));

        List<BorrowedCar> borrowedCars = borrowedCarRepository.findAll();
        assertTrue(CollectionUtils.isEmpty(borrowedCars));
    }

    @Test
    void returnCarShouldReturnNotFound() {
        borrowedCarRepository.save(BORROWED_CAR_2);

        webTestClient.put()
                .uri("/{userId}/car/return/{carId}", userId_1, carId_1)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class)
                .value(response -> assertEquals("Car was not borrowed by the user.", response));
    }


    @Test
    void getBorrowedCarsByUserShouldReturnOk() {
        borrowedCarRepository.save(BORROWED_CAR_1);
        borrowedCarRepository.save(BORROWED_CAR_2);
        borrowedCarRepository.save(BORROWED_CAR_3);

        List<BorrowedCar> borrowedCars = webTestClient.get()
                .uri("/{userId}/car/", userId_1)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(BorrowedCar.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(borrowedCars);
        assertEquals(2, borrowedCars.size());
    }
}
