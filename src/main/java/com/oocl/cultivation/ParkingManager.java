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
    public Ticket park(Car car) throws NotEnoughPositionException {
        try {
            return super.park(car);
        } catch (NotEnoughPositionException e) {
            ParkingBoy parkedBy = this.parkingBoys.stream().filter(ParkingBoy::isAbleParking).findFirst().orElse(null);
            return parkedBy.park(car);
        }
    }
}
