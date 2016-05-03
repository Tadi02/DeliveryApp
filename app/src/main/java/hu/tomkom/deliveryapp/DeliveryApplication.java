package hu.tomkom.deliveryapp;

import com.orm.SugarApp;

import hu.tomkom.deliveryapp.ui.UIModule;

public class DeliveryApplication extends SugarApp {

    public static DeliveryApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();
        injector = DaggerDeliveryApplicationComponent.builder().uIModule(new UIModule(this)).build();
    }
}
