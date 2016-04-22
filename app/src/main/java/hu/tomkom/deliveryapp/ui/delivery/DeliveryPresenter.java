package hu.tomkom.deliveryapp.ui.delivery;

import javax.inject.Inject;

import hu.tomkom.deliveryapp.interactor.delivery.DeliveryInteractor;
import hu.tomkom.deliveryapp.ui.Presenter;

public class DeliveryPresenter extends Presenter<DeliveryScreen> {

    @Inject
    DeliveryInteractor deliveryInteractor;

}
