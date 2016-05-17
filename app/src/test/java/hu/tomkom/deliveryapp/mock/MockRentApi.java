package hu.tomkom.deliveryapp.mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hu.tomkom.deliveryapp.network.api.RentApi;
import hu.tomkom.deliveryapp.network.model.Comment;
import hu.tomkom.deliveryapp.network.model.NewComment;
import hu.tomkom.deliveryapp.network.model.Rent;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Path;

public class MockRentApi implements RentApi {

    List<Rent> rents = new ArrayList<>();

    public MockRentApi() {
        rents.add(new Rent("1", "Cica Bt.", "2016-05-04", "2016-05-11", "READY", new ArrayList<Comment>()));
        rents.add(new Rent("2", "Kutya Kft.", "2016-05-05", "2016-05-06", "READY", new ArrayList<Comment>()));
        rents.add(new Rent("3", "Macska Bt.", "2016-05-04", "2016-05-08", "READY", new ArrayList<Comment>()));
        rents.add(new Rent("4", "Laci és társa", "2016-05-15", "2016-05-21", "READY", new ArrayList<Comment>()));
        rents.add(new Rent("5", "Change Ltd.", "2016-05-15", "2016-05-25", "READY", new ArrayList<Comment>()));
        rents.add(new Rent("6", "Big Money Bt.", "2016-06-04", "2016-06-11", "READY", new ArrayList<Comment>()));
    }

    public static String getValidRentId(){
        return "1";
    }

    @Override
    public Call<Rent> fetchRent(@Path("id") final String id) {
        Call<Rent> call = new Call<Rent>() {
            @Override
            public Response<Rent> execute() throws IOException {
                return Response.success(getRent(id));
            }

            @Override
            public void enqueue(Callback<Rent> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<Rent> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
        return call;
    }

    @Override
    public Call<Rent> addComment(@Path("id") final String id, @Body final NewComment newComment) {
        Call<Rent> call = new Call<Rent>() {
            @Override
            public Response<Rent> execute() throws IOException {
                addCommentToRent(id, newComment);
                return Response.success(getRent(id));
            }

            @Override
            public void enqueue(Callback<Rent> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<Rent> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
        return call;
    }

    @Override
    public Call<Rent> removeComment(@Path("id") final String id, @Path("commentId") final String commentId) {
        Call<Rent> call = new Call<Rent>() {
            @Override
            public Response<Rent> execute() throws IOException {
                removeCommentFromRent(id, commentId);
                return Response.success(getRent(id));
            }

            @Override
            public void enqueue(Callback<Rent> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<Rent> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
        return call;
    }


    private Rent getRent(String rentId) {
        for (Rent rent : rents) {
            if (rent.getId().equals(rentId)) {
                return rent;
            }
        }
        return null;
    }

    private void addCommentToRent(String rentId, NewComment newComment) {
        Comment comment = new Comment();
        comment.setTime(newComment.getTime());
        comment.setText(newComment.getText());
        for (Rent rent : rents) {
            if (rent.getId().equals(rentId)) {
                comment.setId(String.valueOf(determineNewId(rent.getComments())));
                rent.getComments().add(comment);
            }
        }
    }

    private void removeCommentFromRent(String rentId, String commentId) {
        System.out.println(commentId);
        for (Rent rent : rents) {
            if (rent.getId().equals(rentId)) {
                Iterator<Comment> i = rent.getComments().iterator();
                while (i.hasNext())
                    if (i.next().getId().equals(commentId)) {
                        i.remove();
                        break;
                    }
            }
        }
    }

    private int determineNewId(List<Comment> comments) {
        int max = 1;
        for (Comment comment : comments) {
            if (Integer.valueOf(comment.getId()) >= max) {
                max = Integer.valueOf(comment.getId()) + 1;
            }
        }
        return max;
    }

}
