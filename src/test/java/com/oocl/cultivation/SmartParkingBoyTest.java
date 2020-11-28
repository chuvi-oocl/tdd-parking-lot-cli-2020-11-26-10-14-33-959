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

public class SmartParkingBoyTest {
    @Test
    void should_parking_boy_park_car_in_multiple_parking_lots_when_park_multiple_cars_given_multiple_parking_lots_multiple_capacity() throws NotEnoughPositionException {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot(1));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car1 = new Car();
        Car car2 = new Car();
        //when
        final Ticket ticket1 = smartParkingBoy.park(car1);
        final Ticket ticket2 = smartParkingBoy.park(car2);
        //then
        assertNotNull(ticket1);
        assertNotNull(ticket2);
    }

    //Skipped test, same as standard parking boy
    //- parking in first lot

    @Test
    void should_parked_at_2nd_parking_lot_function_when_park_1_car_given_multiple_parking_lots_with_2nd_parking_lot_have_more_free_capacity() throws NotEnoughPositionException, UnrecognizedTicketException {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(2)); //1st lot

        ParkingLot secondParkingLot = new ParkingLot(2);
        parkingLots.add(secondParkingLot);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Car car1 = new Car(); //parked in 1st lot
        Car car2 = new Car();
        smartParkingBoy.park(car1);

        final Ticket ticket = smartParkingBoy.park(car2);
        //when
        final Car actual = secondParkingLot.fetch(ticket);
        //then
        assertEquals(car2, actual);
    }

    @Test
    void should_return_NotEnoughPositionException_function_park_car_given_multiple_parking_lots_with_no_free_space() throws NotEnoughPositionException {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot(1));

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        //when

        final Ticket ticket1 = smartParkingBoy.park(car1);
        final Ticket ticket2 = smartParkingBoy.park(car2);
        final NotEnoughPositionException notEnoughPositionException = assertThrows(NotEnoughPositionException.class,
                () -> smartParkingBoy.park(car3)
        );
        //then
        assertNotNull(ticket1);
        assertNotNull(ticket2);
        assertEquals("Not Enough Position", notEnoughPositionException.getMessage());
    }

    //Skipped checking if fetching from which parking lot, as checked where the car parked
    @Test
    void should_return_car_when_fetch_car_given_multiple_parking_lots_with_parked_in_not_first_lot() throws NotEnoughPositionException, UnrecognizedTicketException {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        ParkingLot secondParkingLot = new ParkingLot(1);
        parkingLots.add(secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        smartParkingBoy.park(new Car());
        final Ticket ticket = smartParkingBoy.park(car);
        //when
        Car actual = smartParkingBoy.fetch(ticket);
        //then
        assertEquals(car, actual);
    }

    @Test
    void should_return_unrecognized_ticket_exception_when_fetch_car_given_multiple_parking_lots_with_fake_ticket() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot(1));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        final Ticket ticket = new Ticket();
        //when
        final UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> smartParkingBoy.fetch(ticket)
        );
        //then
        assertEquals("Unrecognized parking ticket", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_unrecognized_ticket_exception_when_fetch_car_given_multiple_parking_lots_parked_in_non_first_lot_with_used_ticket() throws NotEnoughPositionException, UnrecognizedTicketException {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(2));
        parkingLots.add(new ParkingLot(2));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        smartParkingBoy.park(new Car());
        final Ticket ticket = smartParkingBoy.park(new Car());

        smartParkingBoy.fetch(ticket);
        //when
        final UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> smartParkingBoy.fetch(ticket)
        );
        //then
        assertEquals("Unrecognized parking ticket", unrecognizedTicketException.getMessage());
    }
}
