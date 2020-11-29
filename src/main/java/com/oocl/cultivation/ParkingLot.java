package com.oocl.cultivation;

import com.oocl.parkingLotException.NotEnoughPositionException;
import com.oocl.parkingLotException.UnrecognizedTicketException;

import java.util.HashMap;

public class ParkingLot {
    private final int capacity;
    private final HashMap<Ticket, Car> parkingCar;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkingCar = new HashMap<>();
    }

    public Ticket park(Car car) throws NotEnoughPositionException {
        if (getAvailableSpace() <= 0) {
            throw new NotEnoughPositionException("Not Enough Position");
        }
        Ticket ticket = new Ticket();
        parkingCar.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) throws UnrecognizedTicketException {
        Car fetchingCar = parkingCar.get(ticket);
        if (fetchingCar == null) {
            throw new UnrecognizedTicketException("Unrecognized parking ticket");
        }
        parkingCar.remove(ticket);
        return fetchingCar;
    }

    public boolean isAbleParking() {return capacity > parkingCar.size();}

    public int getAvailableSpace() {
        return (capacity - parkingCar.size());
    }

    public double getFreeSpaceRatio() {
        return capacity == 0 ? 0 : ((float) (capacity - parkingCar.size()) / capacity);
    }
}
