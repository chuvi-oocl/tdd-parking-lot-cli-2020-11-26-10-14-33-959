package com.oocl.cultivation;

import com.oocl.parkingLotException.NotEnoughPositionException;
import com.oocl.parkingLotException.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class StandardParkingBoyTest {
    @Test
    void should_parking_boy_park_car_in_multiple_parking_lots_when_park_multiple_cars_given_multiple_parking_lots_multiple_capacity_function() throws NotEnoughPositionException {
        //given
        List<ParkingLot> parkingLots= new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car1 = new Car();
        Car car2 = new Car();
        //when
        final Ticket ticket1 = parkingBoy.park(car1);
        final Ticket ticket2 = parkingBoy.park(car2);
        //then
        assertNotNull(ticket1);
        assertNotNull(ticket2);
    }
    @Test
    void should_only_two_car_parked_when_park_3_cars_given_2_parking_lots_with_1_capacity_each() throws NotEnoughPositionException {
        //given
        List<ParkingLot> parkingLots= new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        //when
        final Ticket ticket1 = parkingBoy.park(car1);
        final Ticket ticket2 = parkingBoy.park(car2);
        final NotEnoughPositionException notEnoughPositionException = assertThrows(NotEnoughPositionException.class,
                () -> parkingBoy.park(car3)
        );
        //then
        assertNotNull(ticket1);
        assertNotNull(ticket2);
        assertEquals("Not Enough Position", notEnoughPositionException.getMessage());
    }
    @Test
    void should_call_first_parking_lot_parked_function_when_park_1_car_given_multiple_parking_lots_with_1st_parking_lot_have_free_capacity() throws NotEnoughPositionException {
        //given
        List<ParkingLot> parkingLots= new ArrayList<>();
        ParkingLot firstParkingLot = Mockito.mock(ParkingLot.class);
        parkingLots.add(firstParkingLot);
        parkingLots.add(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        //when
        parkingBoy.park(car);
        //then
        verify(firstParkingLot, times(1)).park(car);
    }
    @Test
    void should_call_2nd_parking_lot_parked_function_when_park_1_car_given_multiple_parking_lots_with_1st_parking_lot_full_in_capacity_and_2nd_parking_lot_have_free_capacity() throws NotEnoughPositionException {
        //given
        List<ParkingLot> parkingLots= new ArrayList<>();
        ParkingLot secondParkingLot = Mockito.mock(ParkingLot.class);
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car1 = new Car();
        Car car2 = new Car();
        parkingBoy.park(car1);
        //when
        parkingBoy.park(car2);
        //then
        verify(secondParkingLot, times(1)).park(car2);
    }
    @Test
    void should_return_car_when_fetch_car_given_multiple_parking_lots_with_parking_ticket_that_parked_the_car() throws NotEnoughPositionException, UnrecognizedTicketException {
        //given
        List<ParkingLot> parkingLots= new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        final Ticket ticket = parkingBoy.park(car);
        //when
        final Car actual = parkingBoy.fetch(ticket);
        //then
        assertEquals(car, actual);
    }
}
