package hu.tomkom.deliveryapp.ui.main;

import hu.tomkom.deliveryapp.ui.Presenter;

public class MainPresenter extends Presenter<MainScreen> {

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
        screen.showGreeting();
        screen.setDeliveryNumbers(4,2);
    }

}
