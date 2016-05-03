package hu.tomkom.deliveryapp.ui.delivery;

import java.util.Date;
import java.util.List;

import hu.tomkom.deliveryapp.model.Delivery;

public interface DeliveryScreen {

    void showDeliveries(List<Delivery> deliveries);

    void navigateToDetails(String id);

    void showDate(Date date);
}
