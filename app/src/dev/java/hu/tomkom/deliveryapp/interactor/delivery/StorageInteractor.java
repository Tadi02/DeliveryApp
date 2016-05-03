package hu.tomkom.deliveryapp.interactor.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import hu.tomkom.deliveryapp.model.Delivery;

public class StorageInteractor {

    private List<Delivery> db = new ArrayList<>();

    public StorageInteractor() {
        Logger.getLogger("StorageInteractor").log(Level.INFO, "Using InMemory DB");
    }

    public void saveDeliveries(List<Delivery> deliveries){
        truncateDeliveries();
        db.addAll(deliveries);
    }

    public List<Delivery> fetchDeliveries(){
        return new ArrayList<>(db);
    }

    private void truncateDeliveries(){
        db.clear();
    }

}
