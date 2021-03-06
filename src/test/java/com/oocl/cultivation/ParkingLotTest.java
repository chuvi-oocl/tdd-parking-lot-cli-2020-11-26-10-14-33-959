package com.oocl.cultivation;

import com.oocl.parkingLotException.NotEnoughPositionException;
import com.oocl.parkingLotException.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ParkingLotTest {
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
    void should_return_a_parking_ticket_when_park_the_car_given_a_car_and_parking_lot() throws NotEnoughPositionException {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        //when
        final Ticket ticket = parkingLot.park(car);
        //then
        assertNotNull(ticket);
    }

    @Test
    void should_only_one_car_parked_when_park_multiple_cars_given_parking_lot_with_1_capacity() throws NotEnoughPositionException {
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        //when
        final Ticket ticket1 = parkingLot.park(car1);
        final NotEnoughPositionException notEnoughPositionException = assertThrows(NotEnoughPositionException.class,
                () -> parkingLot.park(car2)
        );
        //then
        assertNotNull(ticket1);
        assertEquals("Not Enough Position", notEnoughPositionException.getMessage());
    }

    @Test
    void should_return_car_when_fetch_car_given_parking_ticket_parking_lot_that_parked_the_car() throws NotEnoughPositionException, UnrecognizedTicketException {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        final Ticket ticket = parkingLot.park(car);
        //when
        final Car actual = parkingLot.fetch(ticket);
        //then
        assertEquals(car, actual);
    }

    @Test
    void should_park_multiple_cars_when_park_multiple_cars_given_parking_lot_with_multiple_capacity() throws NotEnoughPositionException {
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        ParkingLot parkingLot = new ParkingLot(3);
        //when
        final Ticket ticket1 = parkingLot.park(car1);
        final Ticket ticket2 = parkingLot.park(car2);
        final Ticket ticket3 = parkingLot.park(car3);
        //then
        assertNotNull(ticket1);
        assertNotNull(ticket2);
        assertNotNull(ticket3);
    }

    @Test
    void should_return_null_given_parking_ticket_parking_lot_that_not_parked_the_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        final Ticket ticket = new Ticket();
        //when
        final UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> parkingLot.fetch(ticket)
        );
        //then
        assertEquals("Unrecognized parking ticket", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_null_when_fetch_car_given_parking_ticket_used() throws NotEnoughPositionException, UnrecognizedTicketException{
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        final Ticket ticket = parkingLot.park(car);
        parkingLot.fetch(ticket);
        //when
        final UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> parkingLot.fetch(ticket)
        );
        //then
        assertEquals("Unrecognized parking ticket", unrecognizedTicketException.getMessage());
    }
}
