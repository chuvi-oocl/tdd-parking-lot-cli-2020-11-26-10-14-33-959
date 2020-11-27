package com.oocl.cultivation;

import com.oocl.parkingLotException.NotEnoughPositionException;
import com.oocl.parkingLotException.UnrecognizedTicketException;

import java.util.List;

public class ParkingBoy {
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
    }

    public Ticket park(Car car) throws NotEnoughPositionException {
        return this.parkingLot.park(car);
    }

    public Car fetch(Ticket ticket) throws UnrecognizedTicketException {
        return this.parkingLot.fetch(ticket);
    }
}
