package com.oocl.cultivation;

import com.oocl.parkingLotException.NotEnoughPositionException;
import com.oocl.parkingLotException.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingManagerTest {
    @Test
    void should_parking_manager_park_cars_in_multiple_parking_lots_when_park_cars_given_multiple_parking_lots_multiple_capacity_no_parking_boys() throws NotEnoughPositionException {
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

    @Test
    void should_call_parking_boy_park_function_when_park_car_given_manager_have_no_free_space_have_parking_boy_with_free_space() throws NotEnoughPositionException, UnrecognizedTicketException {
        //given
        List<ParkingLot> managerParkingLots = new ArrayList<>();
        List<ParkingLot> parkingBoyParkingLots = new ArrayList<>();
        parkingBoyParkingLots.add(new ParkingLot(1));

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingBoyParkingLots);
        parkingBoys.add(parkingBoy);

        ParkingManager parkingManager = new ParkingManager(managerParkingLots, parkingBoys);

        Car car = new Car();
        //when
        final Ticket ticket = parkingManager.park(car);
        Car actual = parkingBoy.fetch(ticket);
        //then
        assertEquals(car, actual);
    }

    @Test
    void should_call_non_first_parking_boy_park_function_when_park_car_given_manager_have_no_free_space_have_multiple_parking_boys_non_first_boy_have_free_space() throws NotEnoughPositionException, UnrecognizedTicketException {
        //given
        List<ParkingLot> managerParkingLots = new ArrayList<>();
        List<ParkingLot> parkingBoyParkingLots = new ArrayList<>();
        parkingBoyParkingLots.add(new ParkingLot(1));

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(new ParkingBoy(new ArrayList<>()));
        ParkingBoy parkingBoy = new ParkingBoy(parkingBoyParkingLots);
        parkingBoys.add(parkingBoy);

        ParkingManager parkingManager = new ParkingManager(managerParkingLots, parkingBoys);

        Car car = new Car();
        //when
        final Ticket ticket = parkingManager.park(car);
        Car actual = parkingBoy.fetch(ticket);
        //then
        assertEquals(car, actual);
    }

    //Skipped testing parking manager throw error, duplicated with general parking boy
}
