package com.oocl.cultivation;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ParkingBoyTest {
    @Test
    void should_parking_boy_call_parking_lot_park_function_once_when_park_the_car_given_call_parking_boy_park_function() {
        //given
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        //when
        parkingBoy.park(car);
        //then
        verify(parkingLot, times(1)).park(car);
    }
    @Test
    void should_return_a_parking_ticket_when_park_the_car_given_a_car_and_parking_lot(){
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        //when
        final Ticket ticket = parkingLot.park(car);
        //then
        assertNotNull(ticket);
    }
    @Test
    void should_only_one_car_parked_when_park_multiple_cars_given_parking_lot_with_1_capacity(){
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        //when
        final Ticket ticket1 = parkingLot.park(car1);
        final Ticket ticket2 = parkingLot.park(car2);
        //then
        assertNotNull(ticket1);
        assertNull(ticket2);
    }
}
