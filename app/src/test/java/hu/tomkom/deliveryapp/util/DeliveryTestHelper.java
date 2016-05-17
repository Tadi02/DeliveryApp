package hu.tomkom.deliveryapp.util;

import java.util.List;

import hu.tomkom.deliveryapp.model.Delivery;

public class DeliveryTestHelper {

    public static boolean checkIfDeliveryMarkedDone(List<Delivery> deliveries, String id){
        for(Delivery delivery: deliveries){
            if(String.valueOf(delivery.getId()).equals(id)){
                return delivery.isCompleted();
            }
        }
        return false;
    }

    public static boolean checkIfDeliveryPresent(List<Delivery> deliveries, String id){
        for(Delivery delivery: deliveries){
            if(String.valueOf(delivery.getId()).equals(id)){
                return true;
            }
        }
        return false;
    }
}
