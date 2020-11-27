package com.oocl.cultivation;

public class ParkingBoy {
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket park(Car car) {
        return this.parkingLot.park(car);
    }

    public Ticket fetch(Ticket ticket) {
        return null;
    }
}
