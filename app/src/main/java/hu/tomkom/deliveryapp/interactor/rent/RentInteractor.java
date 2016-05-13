package hu.tomkom.deliveryapp.interactor.rent;

import java.util.ArrayList;
import java.util.List;

import hu.tomkom.deliveryapp.model.Comment;
import hu.tomkom.deliveryapp.model.Rent;
import hu.tomkom.deliveryapp.model.RentStatus;

public class RentInteractor {

    public Rent fetchRent(String id){
        Rent rent = new Rent();
        rent.setName("Teszt Bt");
        rent.setStatus(RentStatus.READY);
        rent.setStart("2015-05-07");
        rent.setEnd("2015-08-17");
        rent.setComments(new ArrayList<Comment>());
        return rent;
    }

    public List<Comment> fetchCommentsForRent(String id){
        return new ArrayList<>();
    }

    public void addCommentForRent(Comment comment, String id){

    }

    public void removeCommentFromRent(String commentId, String rentId){

    }

}
