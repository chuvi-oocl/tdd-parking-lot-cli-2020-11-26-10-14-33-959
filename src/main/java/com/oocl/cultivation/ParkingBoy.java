package com.oocl.cultivation;

import com.oocl.parkingLotException.NotEnoughPositionException;
import com.oocl.parkingLotException.UnrecognizedTicketException;

import java.util.List;

public class ParkingBoy {
    private ParkingLot parkingLot;
    private List<ParkingLot> parkingLots;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) throws NotEnoughPositionException {
        if(parkingLot != null){
            return this.parkingLot.park(car);
        }
        for (ParkingLot parkingLot : this.parkingLots) {
            try {
                return parkingLot.park(car);
            } catch (NotEnoughPositionException ignored) {
            }
        }
        throw new NotEnoughPositionException("Not Enough Position");
    }

    public Car fetch(Ticket ticket) throws UnrecognizedTicketException {
        for (ParkingLot parkingLot : this.parkingLots) {
            try {
                return parkingLot.fetch(ticket);
            } catch (UnrecognizedTicketException ignored) {
            }
        }
        return null;
    }
}
