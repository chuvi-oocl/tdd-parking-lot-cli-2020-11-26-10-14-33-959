package com.oocl.cultivation;

import java.util.HashMap;

public class ParkingLot {
    private final int capacity;
    private final HashMap<Ticket, Car> parkingCar;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkingCar = new HashMap<>();
    }

    public Ticket park(Car car) {
        if (parkingCar.size() >= capacity) {
            return null;
        }
        Ticket ticket = new Ticket();
        parkingCar.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        Car fetchingCar = parkingCar.get(ticket);
        parkingCar.remove(ticket);
        return fetchingCar;
    }
}
