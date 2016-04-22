package hu.tomkom.deliveryapp.ui.main;

import java.util.List;

import hu.tomkom.deliveryapp.model.Delivery;

public interface MainScreen {

    void showGreeting();

    void setDeliveryNumbers(int completed, int remaining);

    void showTodaysDeliveries(List<Delivery> deliveries);

}
