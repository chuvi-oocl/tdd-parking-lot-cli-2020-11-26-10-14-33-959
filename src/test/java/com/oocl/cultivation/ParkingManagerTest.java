package com.oocl.cultivation;

import com.oocl.parkingLotException.NotEnoughPositionException;
import com.oocl.parkingLotException.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void should_return_NotEnoughPositionException_function_park_car_given_multiple_parking_lots_multiple_parking_boys_with_no_free_space() throws NotEnoughPositionException {
        //given
        List<ParkingLot> managerParkingLots = new ArrayList<>();
        managerParkingLots.add(new ParkingLot(1));

        List<ParkingLot> parkingBoyParkingLots = new ArrayList<>();
        parkingBoyParkingLots.add(new ParkingLot(1));


        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(new ParkingBoy(parkingBoyParkingLots));

        ParkingManager parkingManager = new ParkingManager(managerParkingLots, parkingBoys);

        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        //when

        final Ticket ticket1 = parkingManager.park(car1);
        final Ticket ticket2 = parkingManager.park(car2);
        final NotEnoughPositionException notEnoughPositionException = assertThrows(NotEnoughPositionException.class,
                () -> parkingManager.park(car3)
        );
        //then
        assertNotNull(ticket1);
        assertNotNull(ticket2);
        assertEquals("Not Enough Position", notEnoughPositionException.getMessage());
    }


    @Test
    void should_return_ticket_when_park_car_given_manager_have_no_free_space_have_smart_parking_boy_with_free_space() throws NotEnoughPositionException {
        //given
        List<ParkingLot> managerParkingLots = new ArrayList<>();
        List<ParkingLot> parkingBoyParkingLots = new ArrayList<>();
        parkingBoyParkingLots.add(new ParkingLot(1));

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingBoyParkingLots);
        parkingBoys.add(parkingBoy);

        ParkingManager parkingManager = new ParkingManager(managerParkingLots, parkingBoys);

        Car car = new Car();
        //when
        final Ticket ticket = parkingManager.park(car);
        //then
        assertNotNull(ticket);
    }


    @Test
    void should_return_ticket_when_park_car_given_manager_have_no_free_space_have_super_smart_parking_boy_with_free_space() throws NotEnoughPositionException {
        //given
        List<ParkingLot> managerParkingLots = new ArrayList<>();
        List<ParkingLot> parkingBoyParkingLots = new ArrayList<>();
        parkingBoyParkingLots.add(new ParkingLot(1));

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        SuperSmartParkingBoy parkingBoy = new SuperSmartParkingBoy(parkingBoyParkingLots);
        parkingBoys.add(parkingBoy);

        ParkingManager parkingManager = new ParkingManager(managerParkingLots, parkingBoys);

        Car car = new Car();
        //when
        final Ticket ticket = parkingManager.park(car);
        //then
        assertNotNull(ticket);
    }

    @Test
    void should_return_car_when_fetch_car_given_multiple_parking_lots_parked_at_manager() throws NotEnoughPositionException, UnrecognizedTicketException {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot(1));

        List<ParkingBoy> parkingBoys = new ArrayList<>();

        ParkingManager parkingManager = new ParkingManager(parkingLots, parkingBoys);
        Car car = new Car();
        final Ticket ticket = parkingManager.park(car);
        //when
        Car actual = parkingManager.fetch(ticket);
        //then
        assertEquals(car, actual);
    }


    @Test
    void should_return_car_when_fetch_car_given_multiple_parking_lots_parked_at_one_parking_boy_of_manager() throws NotEnoughPositionException, UnrecognizedTicketException {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingBoyLot = new ParkingLot(1);
        parkingLots.add(parkingBoyLot);

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(new ParkingBoy(parkingLots));

        ParkingManager parkingManager = new ParkingManager(new ArrayList<>(), parkingBoys);
        Car car = new Car();
        final Ticket ticket = parkingBoyLot.park(car);
        //when
        Car actual = parkingManager.fetch(ticket);
        //then
        assertEquals(car, actual);
    }

    @Test
    void should_return_car_when_fetch_car_given_multiple_parking_lots_parked_at_non_first_parking_boy_of_manager() throws NotEnoughPositionException, UnrecognizedTicketException {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingBoyLot = new ParkingLot(1);
        parkingLots.add(parkingBoyLot);

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(new ParkingBoy(new ArrayList<>()));
        parkingBoys.add(new ParkingBoy(parkingLots));

        ParkingManager parkingManager = new ParkingManager(new ArrayList<>(), parkingBoys);
        Car car = new Car();
        final Ticket ticket = parkingBoyLot.park(car);
        //when
        Car actual = parkingManager.fetch(ticket);
        //then
        assertEquals(car, actual);
    }


    @Test
    void should_return_unrecognized_ticket_exception_when_fetch_car_given_multiple_parking_lots_boys_with_fake_ticket() {
        //given
        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(new ParkingBoy(new ArrayList<>()));
        parkingBoys.add(new ParkingBoy(new ArrayList<>()));

        ParkingManager parkingManager = new ParkingManager(new ArrayList<>(), parkingBoys);

        final Ticket ticket = new Ticket();
        //when
        final UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> parkingManager.fetch(ticket)
        );
        //then
        assertEquals("Unrecognized parking ticket", unrecognizedTicketException.getMessage());
    }
}