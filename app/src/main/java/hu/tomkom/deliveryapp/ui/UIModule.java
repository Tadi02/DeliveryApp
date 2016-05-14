package hu.tomkom.deliveryapp.ui;

import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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

    @Provides
    @Singleton
    public Executor provideNetworkExecutor() {
        return Executors.newFixedThreadPool(1);
    }
}
