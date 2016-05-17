package hu.tomkom.deliveryapp.mock;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.tomkom.deliveryapp.network.NetworkStateHandler;
import hu.tomkom.deliveryapp.network.api.DeliveryApi;
import hu.tomkom.deliveryapp.network.api.RentApi;

@Module
public class MockNetworkModule {

    @Provides
    @Singleton
    public DeliveryApi provideDeliveryApi() {
        return new MockDeliveryApi();
    }

    @Provides
    @Singleton
    public RentApi provideRentApi() {
        return new MockRentApi();
    }

    @Provides
    @Singleton
    public NetworkStateHandler provideNetworkStateHandler() {
        return new MockNetworkStateHandler();
    }

}
