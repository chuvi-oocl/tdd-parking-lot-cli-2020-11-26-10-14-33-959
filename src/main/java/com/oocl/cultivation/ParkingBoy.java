package com.oocl.cultivation;

import com.oocl.parkingLotException.NotEnoughPositionException;
import com.oocl.parkingLotException.UnrecognizedTicketException;

public class ParkingBoy {
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket park(Car car) throws NotEnoughPositionException {
        return this.parkingLot.park(car);
    }

    public Car fetch(Ticket ticket) throws UnrecognizedTicketException {
        return this.parkingLot.fetch(ticket);
    }
}
