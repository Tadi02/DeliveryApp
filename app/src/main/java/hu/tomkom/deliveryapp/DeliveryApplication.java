package hu.tomkom.deliveryapp;


import android.app.Application;

import hu.tomkom.deliveryapp.ui.UIModule;

public class DeliveryApplication extends Application {

    public static DeliveryApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();
        injector = DaggerDeliveryApplicationComponent.builder().uIModule(new UIModule(this)).build();
    }
}
