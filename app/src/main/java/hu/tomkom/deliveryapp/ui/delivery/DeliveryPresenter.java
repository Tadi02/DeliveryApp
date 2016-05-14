package hu.tomkom.deliveryapp.ui.delivery;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import hu.tomkom.deliveryapp.DeliveryApplication;
import hu.tomkom.deliveryapp.interactor.delivery.DeliveryInteractor;
import hu.tomkom.deliveryapp.interactor.delivery.event.FetchDeliveriesEvent;
import hu.tomkom.deliveryapp.model.Delivery;
import hu.tomkom.deliveryapp.ui.Presenter;

public class DeliveryPresenter extends Presenter<DeliveryScreen> {

    @Inject
    DeliveryInteractor deliveryInteractor;

    @Inject
    Executor networkExecutor;

    private Date date = new Date();

    public DeliveryPresenter() {
        DeliveryApplication.injector.inject(this);
        EventBus.getDefault().register(this);
    }

    public void setDate(Date date){
        this.date = date;
        screen.showDate(date);
        fetchData();
    }

    public void fetchData(){
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                deliveryInteractor.fetchDeliveriesForDate(date);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final FetchDeliveriesEvent event) {
        if(event.isSuccess() && !event.isTodayRequest()){
            List<Delivery> deliveries = deliveryInteractor.parseDeliveries(event.getDeliveries());
            this.screen.showDeliveries(deliveries);
            this.screen.showDate(date);
        }
    }

    public void markDeliveryCompleted(String id){
        deliveryInteractor.markDeliveryCompleted(id);
    }

    public void listItemClicked(String id){
        screen.navigateToDetails(id);
    }

}
