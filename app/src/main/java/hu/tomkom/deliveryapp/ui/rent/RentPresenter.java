package hu.tomkom.deliveryapp.ui.rent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import hu.tomkom.deliveryapp.DeliveryApplication;
import hu.tomkom.deliveryapp.interactor.delivery.event.FetchDeliveriesEvent;
import hu.tomkom.deliveryapp.interactor.rent.RentInteractor;
import hu.tomkom.deliveryapp.interactor.rent.event.FetchRentEvent;
import hu.tomkom.deliveryapp.model.Comment;
import hu.tomkom.deliveryapp.model.Delivery;
import hu.tomkom.deliveryapp.model.Rent;
import hu.tomkom.deliveryapp.ui.Presenter;

public class RentPresenter extends Presenter<RentScreen> {

    @Inject
    Executor networkExecutor;

    @Inject
    RentInteractor rentInteractor;

    private String rentId;

    public RentPresenter() {
        DeliveryApplication.injector.inject(this);
        EventBus.getDefault().register(this);
    }

    public void fetchData(){
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                rentInteractor.fetchRent(rentId);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final FetchRentEvent event) {
        if(event.isSuccess()){
            Rent rent = rentInteractor.parseRent(event.getRent());
            screen.showRentData(rent);
            screen.showComments(rent.getComments());
        }
    }

    public void addComment(String text){
        Comment comment = new Comment();
        comment.setText(text);
        rentInteractor.addCommentForRent(comment,rentId);
        fetchData();
    }

    public void removeComment(String commentId){
        rentInteractor.removeCommentFromRent(commentId,rentId);
        fetchData();
    }

    public void setRentId(String rentId) {
        this.rentId = rentId;
    }
}
