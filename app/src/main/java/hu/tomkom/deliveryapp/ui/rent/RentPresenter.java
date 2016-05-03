package hu.tomkom.deliveryapp.ui.rent;

import javax.inject.Inject;

import hu.tomkom.deliveryapp.interactor.rent.RentInteractor;
import hu.tomkom.deliveryapp.model.Comment;
import hu.tomkom.deliveryapp.model.Rent;
import hu.tomkom.deliveryapp.ui.Presenter;

public class RentPresenter extends Presenter<RentScreen> {

    @Inject
    RentInteractor rentInteractor;

    private String rentId = "";

    public void fetchData(){
        Rent rent = rentInteractor.fetchRent(rentId);
        screen.showRentData(rent);
        screen.showComments(rent.getComments());
    }

    public void addComment(Comment comment){
        rentInteractor.addCommentForRent(comment,rentId);
        fetchData();
    }

    public void removeComment(String commentId){
        rentInteractor.removeCommentFromRent(commentId,rentId);
        fetchData();
    }

}
