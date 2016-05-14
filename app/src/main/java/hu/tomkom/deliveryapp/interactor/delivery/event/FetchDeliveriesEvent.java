package hu.tomkom.deliveryapp.interactor.delivery.event;

import java.util.List;

import hu.tomkom.deliveryapp.network.model.Delivery;


public class FetchDeliveriesEvent {

    private List<Delivery> deliveries;

    private boolean success = false;

    private String type = "COMMON";

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isTodayRequest(){
        if(type.equals("TODAY")){
            return true;
        }
        return false;
    }
}
