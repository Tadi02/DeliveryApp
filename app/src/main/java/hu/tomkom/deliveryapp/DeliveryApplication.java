package hu.tomkom.deliveryapp;

import com.crashlytics.android.Crashlytics;
import com.orm.SugarApp;

import hu.tomkom.deliveryapp.ui.UIModule;
import io.fabric.sdk.android.Fabric;

public class DeliveryApplication extends SugarApp {

    public static DeliveryApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        injector = DaggerDeliveryApplicationComponent.builder().uIModule(new UIModule(this)).build();
    }
}
