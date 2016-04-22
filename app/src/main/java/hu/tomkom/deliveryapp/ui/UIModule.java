package hu.tomkom.deliveryapp.ui;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.tomkom.deliveryapp.ui.delivery.DeliveryPresenter;
import hu.tomkom.deliveryapp.ui.main.MainPresenter;
import hu.tomkom.deliveryapp.ui.rent.RentPresenter;

@Module
public class UIModule {

    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public MainPresenter provideMainPresenter(){
        return new MainPresenter();
    }

    @Provides
    @Singleton
    public DeliveryPresenter provideDeliveryPresenter(){
        return new DeliveryPresenter();
    }

    @Provides
    @Singleton
    public RentPresenter provideRentPresenter(){
        return new RentPresenter();
    }
}
