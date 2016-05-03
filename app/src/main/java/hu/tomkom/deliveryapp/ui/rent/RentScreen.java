package hu.tomkom.deliveryapp.ui.rent;

import java.util.List;

import hu.tomkom.deliveryapp.model.Comment;
import hu.tomkom.deliveryapp.model.Rent;

public interface RentScreen {

    void showRentData(Rent rent);

    void showComments(List<Comment> comments);

}
