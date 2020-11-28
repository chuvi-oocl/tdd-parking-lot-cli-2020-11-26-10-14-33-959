package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingManagerTest {
    @Test
    void should_parking_manager_park_cars_in_multiple_parking_lots_when_park_cars_given_multiple_parking_lots_multiple_capacity_no_parking_boys() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot(1));

        List<ParkingBoy> parkingBoys = new ArrayList<>();

        ParkingManager parkingManager = new ParkingManager(parkingLots, parkingBoys);
        Car car1 = new Car();
        Car car2 = new Car();
        //when
        final Ticket ticket1 = parkingManager.park(car1);
        final Ticket ticket2 = parkingManager.park(car2);
        //then
        assertNotNull(ticket1);
        assertNotNull(ticket2);
    }

}
