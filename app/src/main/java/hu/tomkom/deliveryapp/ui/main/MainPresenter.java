package hu.tomkom.deliveryapp.ui.main;

import java.util.List;

import javax.inject.Inject;

import hu.tomkom.deliveryapp.DeliveryApplication;
import hu.tomkom.deliveryapp.interactor.delivery.DeliveryInteractor;
import hu.tomkom.deliveryapp.model.Delivery;
import hu.tomkom.deliveryapp.ui.Presenter;

public class MainPresenter extends Presenter<MainScreen> {

    @Inject
    DeliveryInteractor deliveryInteractor;

    public MainPresenter() {
        DeliveryApplication.injector.inject(this);
    }

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
    }

    public void fetchData(){
        List<Delivery> deliveries = deliveryInteractor.fetchTodaysDeliveries();
        screen.showTodaysDeliveries(deliveries);
        calculateNumbers(deliveries);
    }

    public void markDeliveryCompleted(String id){
        deliveryInteractor.markDeliveryCompleted(id);
        fetchData();
    }

    public void listItemClicked(){
        screen.navigateToDetails("");
    }

    private void calculateNumbers(List<Delivery> deliveries){
        screen.setDeliveryNumbers(1,2);
    }

}
