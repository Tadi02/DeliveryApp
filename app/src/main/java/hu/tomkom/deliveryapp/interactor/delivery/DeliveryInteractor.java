package hu.tomkom.deliveryapp.interactor.delivery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import hu.tomkom.deliveryapp.DeliveryApplication;
import hu.tomkom.deliveryapp.model.Delivery;

public class DeliveryInteractor {

    @Inject
    StorageInteractor storageInteractor;

    private boolean networkAvailable = true;

    public DeliveryInteractor() {
        DeliveryApplication.injector.inject(this);
    }

    public List<Delivery> fetchTodaysDeliveries(){
        if(networkAvailable){
            List<Delivery> deliveries = fetchDeliveriesForDate(new Date());
            saveTodaysDeliveries(deliveries);
            return deliveries;
        }else{
            return storageInteractor.fetchDeliveries();
        }
    }

    public List<Delivery> fetchDeliveriesForDate(Date date){
        return testList();
    }

    public void markDeliveryCompleted(String id){

    }

    private void saveTodaysDeliveries(List<Delivery> deliveries){
        storageInteractor.saveDeliveries(deliveries);
    }

    private List<Delivery> testList(){
        List<Delivery> test = new ArrayList<>();
        Delivery d = new Delivery();
        d.setName("Cica Bt.");
        d.setAddress("Máté utca 4.");
        d.setPhone("+36 30 548 6684");
        d.setCompleted(false);
        d.setTime("12:00");
        d.setRentId("2A");
        test.add(d);
        Delivery d2 = new Delivery();
        d2.setName("Big money Kft.");
        d2.setAddress("Kalap utca 4.");
        d2.setPhone("+36 30 999 6684");
        d2.setCompleted(true);
        d2.setTime("13:00");
        d2.setRentId("2B");
        test.add(d2);
        return test;
    }

}
