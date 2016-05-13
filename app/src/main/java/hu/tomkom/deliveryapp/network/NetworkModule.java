package hu.tomkom.deliveryapp.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.tomkom.deliveryapp.network.api.CommentApi;
import hu.tomkom.deliveryapp.network.api.DeliveryApi;
import hu.tomkom.deliveryapp.network.api.RentApi;
import retrofit2.Retrofit;

@Module
public class NetworkModule {

    private Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080").build();

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

}
