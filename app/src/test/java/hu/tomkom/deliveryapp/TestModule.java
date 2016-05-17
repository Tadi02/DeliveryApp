package hu.tomkom.deliveryapp;

import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Provides;
import dagger.Module;
import hu.tomkom.deliveryapp.ui.delivery.DeliveryPresenter;
import hu.tomkom.deliveryapp.ui.main.MainPresenter;
import hu.tomkom.deliveryapp.ui.rent.RentPresenter;
import hu.tomkom.deliveryapp.util.UiExecutor;

@Module
public class TestModule {

    private Context context;

    public TestModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
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
        return new UiExecutor();
    }

}
