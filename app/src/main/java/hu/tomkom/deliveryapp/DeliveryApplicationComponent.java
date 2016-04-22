package hu.tomkom.deliveryapp;

import javax.inject.Singleton;

import dagger.Component;
import hu.tomkom.deliveryapp.ui.UIModule;
import hu.tomkom.deliveryapp.ui.delivery.DeliveryActivity;
import hu.tomkom.deliveryapp.ui.main.MainActivity;
import hu.tomkom.deliveryapp.ui.rent.RentActivity;

@Singleton
@Component(modules = {UIModule.class})
public interface DeliveryApplicationComponent {

    void inject(MainActivity mainActivity);

    void inject(DeliveryActivity deliveryActivity);

    void inject(RentActivity rentActivity);

}
