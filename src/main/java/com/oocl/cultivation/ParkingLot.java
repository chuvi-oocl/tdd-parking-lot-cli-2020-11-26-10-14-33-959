package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final int capacity;
    private final List<Car> parkingCar;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkingCar = new ArrayList<>();
    }

    public Ticket park(Car car) {
        if (parkingCar.size() >= capacity) {
            return null;
        }
        parkingCar.add(car);
        return new Ticket();
    }
}
