package hu.tomkom.deliveryapp.ui.rent;

import javax.inject.Inject;

import hu.tomkom.deliveryapp.interactor.rent.RentInteractor;
import hu.tomkom.deliveryapp.ui.Presenter;

public class RentPresenter extends Presenter<RentScreen> {

    @Inject
    RentInteractor rentInteractor;

}
