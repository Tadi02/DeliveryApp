package hu.tomkom.deliveryapp;

import javax.inject.Singleton;

import dagger.Component;
import hu.tomkom.deliveryapp.interactor.InteractorModule;
import hu.tomkom.deliveryapp.interactor.delivery.DeliveryInteractor;
import hu.tomkom.deliveryapp.interactor.rent.RentInteractor;
import hu.tomkom.deliveryapp.network.NetworkModule;
import hu.tomkom.deliveryapp.ui.UIModule;
import hu.tomkom.deliveryapp.ui.delivery.DeliveryActivity;
import hu.tomkom.deliveryapp.ui.delivery.DeliveryPresenter;
import hu.tomkom.deliveryapp.ui.main.MainActivity;
import hu.tomkom.deliveryapp.ui.main.MainPresenter;
import hu.tomkom.deliveryapp.ui.rent.RentActivity;
import hu.tomkom.deliveryapp.ui.rent.RentPresenter;

@Singleton
@Component(modules = {UIModule.class, InteractorModule.class, NetworkModule.class})
public interface DeliveryApplicationComponent {

    void inject(MainActivity mainActivity);

    void inject(DeliveryActivity deliveryActivity);

    void inject(RentActivity rentActivity);

    void inject(DeliveryPresenter deliveryPresenter);

    void inject(MainPresenter mainPresenter);

    void inject(RentPresenter rentPresenter);

    void inject(DeliveryInteractor deliveryInteractor);

    void inject(RentInteractor rentInteractor);

}
