package com.oocl.cultivation;

import com.oocl.parkingLotException.NotEnoughPositionException;
import com.oocl.parkingLotException.UnrecognizedTicketException;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
            if (parkedBy == null) {
                throw new NotEnoughPositionException("Not Enough Position");
            }
            return parkedBy.park(car);
        }
    }

    @Override
    public Car fetch(Ticket ticket){
        try {
            return super.fetch(ticket);
        } catch (UnrecognizedTicketException e) {
            AtomicReference<Car> car = new AtomicReference<>();
            this.parkingBoys.forEach(parkingBoy ->{
                try{
                    car.set(parkingBoy.fetch(ticket));
                } catch (UnrecognizedTicketException ignored) {

                }
            });
            return car.get();
        }
    }
}
