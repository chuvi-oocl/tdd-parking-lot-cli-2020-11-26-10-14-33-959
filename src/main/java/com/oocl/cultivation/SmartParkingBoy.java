package com.oocl.cultivation;

import com.oocl.parkingLotException.NotEnoughPositionException;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) throws NotEnoughPositionException {
        ParkingLot parkAtResult;
        parkAtResult = getParkingLots().stream().reduce(new ParkingLot(0), (parkAt, parkingLot) ->
                (parkAt.availableSpace() >= parkingLot.availableSpace()) ? parkAt : parkingLot
        );
        return parkAtResult.park(car);
    }
}
