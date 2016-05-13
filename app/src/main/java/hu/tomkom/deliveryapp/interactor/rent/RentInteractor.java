package hu.tomkom.deliveryapp.interactor.rent;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        List<Comment> comments = new ArrayList<>();
        Comment comment1 = new Comment();
        Comment comment2 = new Comment();
        comment1.setText("Valamit elfelejtettünk.");
        comment2.setText("Erre bizony emlékezni kell");
        comment1.setTime("2014.02.14 11:00");
        comment2.setTime("2014.02.14 14:00");
        comment1.setId(1L);
        comment2.setId(2L);
        comments.add(comment1);
        comments.add(comment2);
        return comments;
    }

    public void addCommentForRent(Comment comment, String id){
        Logger.getLogger("RentInt").log(Level.INFO, comment.getText());
    }

    public void removeCommentFromRent(String commentId, String rentId){

    }

}
