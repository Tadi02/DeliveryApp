package hu.tomkom.deliveryapp;

import javax.inject.Singleton;

import dagger.Component;
import hu.tomkom.deliveryapp.ui.UIModule;
import hu.tomkom.deliveryapp.ui.main.MainActivity;

@Singleton
@Component(modules = {UIModule.class})
public interface DeliveryApplicationComponent {

    void inject(MainActivity mainActivity);

}
