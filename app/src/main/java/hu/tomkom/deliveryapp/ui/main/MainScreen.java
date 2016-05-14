package hu.tomkom.deliveryapp.ui.main;

import java.util.List;

import hu.tomkom.deliveryapp.model.Delivery;

public interface MainScreen {

    void setDeliveryNumbers(int completed, int remaining);

    void showTodaysDeliveries(List<Delivery> deliveries);

    void navigateToDetails(String id);

    void showNetworkWarning();

}
