package com.oocl.cultivation;

import com.oocl.parkingLotException.NotEnoughPositionException;
import com.oocl.parkingLotException.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SuperSmartParkingBoyTest {

    @Test
    void should_parking_boy_park_car_in_multiple_parking_lots_when_park_multiple_cars_given_multiple_parking_lots_multiple_capacity() throws NotEnoughPositionException {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot(1));
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car car1 = new Car();
        Car car2 = new Car();
        //when
        final Ticket ticket1 = superSmartParkingBoy.park(car1);
        final Ticket ticket2 = superSmartParkingBoy.park(car2);
        //then
        assertNotNull(ticket1);
        assertNotNull(ticket2);
    }

    //Skipped test, same as standard parking boy
    //- parking in first lot


    @Test
    void should_parked_at_higher_free_ratio_parking_lot_function_when_park_1_car_given_multiple_parking_lots_with_1st_parking_lot_have_more_free_space_2nd_parking_lot_have_higher_free_ratio_in_capacity() throws NotEnoughPositionException, UnrecognizedTicketException {
        //given
        //1st lot: 3/6, free space: 3, free ratio: 0.5
        //2nd lot: 1/3, free space: 2, free ratio: 0.66
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot firstParkingLot = new ParkingLot(6);
        parkingLots.add(firstParkingLot);
        firstParkingLot.park(new Car());
        firstParkingLot.park(new Car());
        firstParkingLot.park(new Car());

        ParkingLot secondParkingLot = new ParkingLot(3);
        parkingLots.add(secondParkingLot);
        secondParkingLot.park(new Car());

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        Car car = new Car();

        //when
        final Ticket ticket = superSmartParkingBoy.park(car);
        final Car actual = secondParkingLot.fetch(ticket);
        //then
        assertEquals(car, actual);
    }


    @Test
    void should_return_NotEnoughPositionException_function_park_car_given_multiple_parking_lots_with_no_free_space() throws NotEnoughPositionException {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot(1));

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        //when

        final Ticket ticket1 = superSmartParkingBoy.park(car1);
        final Ticket ticket2 = superSmartParkingBoy.park(car2);
        final NotEnoughPositionException notEnoughPositionException = assertThrows(NotEnoughPositionException.class,
                () -> superSmartParkingBoy.park(car3)
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
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car car = new Car();
        superSmartParkingBoy.park(new Car());
        final Ticket ticket = superSmartParkingBoy.park(car);
        //when
        Car actual = superSmartParkingBoy.fetch(ticket);
        //then
        assertEquals(car, actual);
    }

    @Test
    void should_return_unrecognized_ticket_exception_when_fetch_car_given_multiple_parking_lots_with_fake_ticket() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot(1));
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        final Ticket ticket = new Ticket();
        //when
        final UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> superSmartParkingBoy.fetch(ticket)
        );
        //then
        assertEquals("Unrecognized parking ticket", unrecognizedTicketException.getMessage());
    }
}
