package com.oocl.cultivation;

import com.oocl.parkingLotException.NotEnoughPositionException;
import com.oocl.parkingLotException.UnrecognizedTicketException;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLots;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) throws NotEnoughPositionException {
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
        throw new UnrecognizedTicketException("Unrecognized parking ticket");
    }
}
