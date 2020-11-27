package com.oocl.cultivation;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ParkingBoyTest {
    @Test
    void should_parking_boy_call_parking_lot_park_function_once_when_park_the_car_given_call_parking_boy_park_function() throws NotEnoughPositionException {
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
    void should_parking_boy_call_parking_lot_fetch_function_once_when_park_the_car_given_call_parking_boy_fetch_function() throws UnrecognizedTicketException {
        //given
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Ticket ticket = new Ticket();
        //when
        parkingBoy.fetch(ticket);
        //then
        verify(parkingLot, times(1)).fetch(ticket);
    }
}
