package hu.tomkom.deliveryapp.interactor.delivery;

import com.google.common.collect.Lists;

import java.util.List;

import hu.tomkom.deliveryapp.model.Delivery;

public class StorageInteractor {

    public void saveDeliveries(List<Delivery> deliveries){
        truncateDeliveries();
        Delivery.saveInTx(deliveries);
    }

    public List<Delivery> fetchDeliveries(){
        return Lists.newArrayList(Delivery.findAll(Delivery.class));
    }

    private void truncateDeliveries(){
        Delivery.deleteAll(Delivery.class);
    }

}
