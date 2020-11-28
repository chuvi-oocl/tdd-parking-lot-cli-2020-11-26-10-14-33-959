package com.oocl.cultivation;

import com.oocl.parkingLotException.NotEnoughPositionException;

import java.util.List;

public class ParkingManager extends ParkingBoy {
    private List<ParkingBoy> parkingBoys;

    public ParkingManager(List<ParkingLot> parkingLots, List<ParkingBoy> parkingBoys) {
        super(parkingLots);
        this.parkingBoys = parkingBoys;
    }

    @Override
    public Ticket park(Car car) throws NotEnoughPositionException{
        try {
            return super.park(car);
        } catch (NotEnoughPositionException e) {
            return parkingBoys.get(0).park(car);
        }
    }
}
