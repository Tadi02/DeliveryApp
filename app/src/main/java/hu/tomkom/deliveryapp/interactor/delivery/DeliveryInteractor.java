package hu.tomkom.deliveryapp.interactor.delivery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hu.tomkom.deliveryapp.DeliveryApplication;
import hu.tomkom.deliveryapp.model.Delivery;

public class DeliveryInteractor {

    private boolean networkAvailable = false;

    public List<Delivery> fetchTodaysDeliveries(){
        if(networkAvailable){
            List<Delivery> deliveries = fetchDeliveriesForDate(new Date());
            saveTodaysDeliveries(deliveries);
            return deliveries;
        }else{
            return new ArrayList<>();
        }
    }

    public List<Delivery> fetchDeliveriesForDate(Date date){
        return new ArrayList<>();
    }

    public void markDeliveryCompleted(String id){

    }

    private void saveTodaysDeliveries(List<Delivery> deliveries){

    }

}
