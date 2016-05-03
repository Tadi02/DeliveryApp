package hu.tomkom.deliveryapp.interactor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.tomkom.deliveryapp.interactor.delivery.DeliveryInteractor;
import hu.tomkom.deliveryapp.interactor.delivery.StorageInteractor;
import hu.tomkom.deliveryapp.interactor.rent.RentInteractor;

@Module
public class InteractorModule {

    @Provides
    @Singleton
    public DeliveryInteractor provideDeliveryInteractor(){
        return new DeliveryInteractor();
    }

    @Provides
    @Singleton
    public RentInteractor provideRentInteractor(){
        return new RentInteractor();
    }

    @Provides
    @Singleton
    public StorageInteractor provideStorageInteractor() {return new StorageInteractor();}

}
