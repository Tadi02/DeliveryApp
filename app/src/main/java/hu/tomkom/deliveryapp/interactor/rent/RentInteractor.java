package hu.tomkom.deliveryapp.interactor.rent;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import hu.tomkom.deliveryapp.DeliveryApplication;
import hu.tomkom.deliveryapp.interactor.rent.event.AddCommentEvent;
import hu.tomkom.deliveryapp.interactor.rent.event.FetchRentEvent;
import hu.tomkom.deliveryapp.interactor.rent.event.RemoveCommentEvent;
import hu.tomkom.deliveryapp.model.Comment;
import hu.tomkom.deliveryapp.model.RentStatus;
import hu.tomkom.deliveryapp.network.api.RentApi;
import hu.tomkom.deliveryapp.network.model.NewComment;
import hu.tomkom.deliveryapp.network.model.Rent;
import retrofit2.Call;
import retrofit2.Response;

public class RentInteractor {

    @Inject
    RentApi rentApi;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public RentInteractor() {
        DeliveryApplication.injector.inject(this);
    }

    public void fetchRent(String id){
        Call<Rent> call = rentApi.fetchRent(id);
        FetchRentEvent event = new FetchRentEvent();
        try {
            Response<Rent> response = call.execute();
            if (response.code() != 200) {
                throw new Exception("Result code is not 200");
            }
            event.setSuccess(true);
            event.setRent(response.body());
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            EventBus.getDefault().post(event);
        }
    }

    public void addCommentForRent(Comment comment, String id){
        comment.setTime(dateFormat.format(new Date()));
        Call<Rent> call = rentApi.addComment(id, toNetworkComment(comment));
        AddCommentEvent event = new AddCommentEvent();
        try {
            Response<Rent> response = call.execute();
            if (response.code() != 200) {
                throw new Exception("Result code is not 200");
            }
            event.setSuccess(true);
            event.setRent(response.body());
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            EventBus.getDefault().post(event);
        }
    }

    public void removeCommentFromRent(String commentId, String rentId){
        Call<Rent> call = rentApi.removeComment(rentId, commentId);
        RemoveCommentEvent event = new RemoveCommentEvent();
        try {
            Response<Rent> response = call.execute();
            if (response.code() != 200) {
                throw new Exception("Result code is not 200");
            }
            event.setSuccess(true);
            event.setRent(response.body());
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            EventBus.getDefault().post(event);
        }
    }

    public hu.tomkom.deliveryapp.model.Rent parseRent(Rent rent){
        return new hu.tomkom.deliveryapp.model.Rent(rent.getName(), rent.getStart(), rent.getEnd(), RentStatus.valueOf(rent.getStatus()), parseComments(rent.getComments()));
    }

    public List<Comment> parseComments(List<hu.tomkom.deliveryapp.network.model.Comment> comments){
        List<Comment> result = new ArrayList<>();
        for(hu.tomkom.deliveryapp.network.model.Comment comment : comments){
            result.add(new Comment(Long.valueOf(comment.getId()), comment.getTime(), comment.getText()));
        }
        return result;
    }

    public NewComment toNetworkComment(Comment comment){
        return new NewComment(comment.getTime(), comment.getText());
    }

}
