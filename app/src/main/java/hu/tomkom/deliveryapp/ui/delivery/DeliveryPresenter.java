package hu.tomkom.deliveryapp.ui.delivery;

import java.util.Date;

import javax.inject.Inject;

import hu.tomkom.deliveryapp.DeliveryApplication;
import hu.tomkom.deliveryapp.interactor.delivery.DeliveryInteractor;
import hu.tomkom.deliveryapp.model.Delivery;
import hu.tomkom.deliveryapp.ui.Presenter;

public class DeliveryPresenter extends Presenter<DeliveryScreen> {

    @Inject
    DeliveryInteractor deliveryInteractor;

    private Date date = new Date();

    public DeliveryPresenter() {
        DeliveryApplication.injector.inject(this);
    }

    public void setDate(Date date){
        this.date = date;
        screen.showDate(date);
        fetchData();
    }

    public void fetchData(){
        this.screen.showDate(date);
        this.screen.showDeliveries(deliveryInteractor.fetchDeliveriesForDate(this.date));
    }

    public void markDeliveryCompleted(String id){
        deliveryInteractor.markDeliveryCompleted(id);
    }

    public void listItemClicked(){
        screen.navigateToDetails("");
    }

}
