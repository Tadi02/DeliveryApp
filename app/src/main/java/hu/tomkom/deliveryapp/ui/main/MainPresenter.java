package hu.tomkom.deliveryapp.ui.main;

import javax.inject.Inject;

import hu.tomkom.deliveryapp.interactor.delivery.DeliveryInteractor;
import hu.tomkom.deliveryapp.ui.Presenter;

public class MainPresenter extends Presenter<MainScreen> {

    @Inject
    DeliveryInteractor deliveryInteractor;

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
        screen.showGreeting();
        screen.setDeliveryNumbers(4,2);
    }

}
