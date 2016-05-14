package hu.tomkom.deliveryapp.interactor.rent.event;

import hu.tomkom.deliveryapp.network.model.Rent;

public abstract class RentChangeEvent {

    private boolean success = false;

    private Rent rent;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }
}
