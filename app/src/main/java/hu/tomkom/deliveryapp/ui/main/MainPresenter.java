package hu.tomkom.deliveryapp.ui.main;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import hu.tomkom.deliveryapp.DeliveryApplication;
import hu.tomkom.deliveryapp.interactor.delivery.DeliveryInteractor;
import hu.tomkom.deliveryapp.interactor.delivery.StorageInteractor;
import hu.tomkom.deliveryapp.interactor.delivery.event.DeliveryMarkedEvent;
import hu.tomkom.deliveryapp.interactor.delivery.event.FetchDeliveriesEvent;
import hu.tomkom.deliveryapp.model.Delivery;
import hu.tomkom.deliveryapp.network.NetworkStateHandler;
import hu.tomkom.deliveryapp.network.event.NetworkStateChangedEvent;
import hu.tomkom.deliveryapp.ui.Presenter;

public class MainPresenter extends Presenter<MainScreen> {

    @Inject
    Executor networkExecutor;

    @Inject
    DeliveryInteractor deliveryInteractor;

    @Inject
    StorageInteractor storageInteractor;

    @Inject
    NetworkStateHandler networkStateHandler;

    private boolean networkAvailable = true;

    public MainPresenter() {
        DeliveryApplication.injector.inject(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
        networkAvailable = networkStateHandler.isNetworkAvailable();
    }

    public void fetchData() {
        if (networkAvailable) {
            networkExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    deliveryInteractor.fetchTodaysDeliveries();
                }
            });
        } else {
            List<Delivery> deliveries = storageInteractor.fetchDeliveries();
            screen.showTodaysDeliveries(filterDeliveries(deliveries));
            calculateNumbers(deliveries);
            screen.showNetworkWarning();
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final FetchDeliveriesEvent event) {
        if (event.isSuccess() && event.isTodayRequest()) {
            List<Delivery> deliveries = deliveryInteractor.parseDeliveries(event.getDeliveries());
            screen.showTodaysDeliveries(filterDeliveries(deliveries));
            saveTodaysDeliveries(deliveries);
            calculateNumbers(deliveries);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final DeliveryMarkedEvent event) {
        if (event.isSuccess() && event.getSource().equals("MAIN")) {
            fetchData();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final NetworkStateChangedEvent event) {
        this.networkAvailable = event.isNetworkAvailable();
        if (screen != null) {
            fetchData();
        }
    }

    public void markDeliveryCompleted(final String id) {
        if (networkAvailable) {
            networkExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    deliveryInteractor.markDeliveryCompleted(id, "MAIN");
                }
            });
        } else {
            screen.showNetworkWarning();
        }
    }

    public void listItemClicked(String id) {
        if (networkAvailable) {
            screen.navigateToDetails(id);
        } else {
            screen.showNetworkWarning();
        }
    }

    private List<Delivery> filterDeliveries(List<Delivery> deliveries) {
        List<Delivery> filtered = new ArrayList<>();
        for (Delivery delivery : deliveries) {
            if (!delivery.isCompleted()) {
                filtered.add(delivery);
            }
        }
        return filtered;
    }

    private void calculateNumbers(List<Delivery> deliveries) {
        int completed = 0;
        int remaining = 0;
        for (Delivery delivery : deliveries) {
            if (delivery.isCompleted()) {
                completed++;
            } else {
                remaining++;
            }
        }
        screen.setDeliveryNumbers(completed, remaining);
    }

    private void saveTodaysDeliveries(List<Delivery> deliveries) {
        storageInteractor.saveDeliveries(deliveries);
    }

}
