package hu.tomkom.deliveryapp.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.tomkom.deliveryapp.network.api.CommentApi;
import hu.tomkom.deliveryapp.network.api.DeliveryApi;
import hu.tomkom.deliveryapp.network.api.RentApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://192.168.1.2:8080").build();

    @Provides
    @Singleton
    public DeliveryApi provideDeliveryApi(){
        return retrofit.create(DeliveryApi.class);
    }

    @Provides
    @Singleton
    public CommentApi provideCommentApi(){
        return retrofit.create(CommentApi.class);
    }

    @Provides
    @Singleton
    public RentApi provideRentApi(){
        return retrofit.create(RentApi.class);
    }

    @Provides
    @Singleton
    public NetworkStateHandler provideNetworkStateHandler() {
        return new NetworkStateHandler();
    }

}
