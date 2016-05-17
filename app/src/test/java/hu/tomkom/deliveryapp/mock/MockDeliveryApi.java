package hu.tomkom.deliveryapp.mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hu.tomkom.deliveryapp.network.api.DeliveryApi;
import hu.tomkom.deliveryapp.network.model.Delivery;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

public class MockDeliveryApi implements DeliveryApi {

    private List<Delivery> deliveries = new ArrayList<>();

    private static Delivery notDone = new Delivery("1","Cica Bt.","1","14:00","Fa utca 7.","+36 30 358 4488",false, "IN");

    public MockDeliveryApi() {
        deliveries.add(notDone);
        deliveries.add(new Delivery("2","Kutya Kft.","2","8:00","Erdő utca 8.","+36 10 358 4488",false, "IN"));
        deliveries.add(new Delivery("3","Macska Bt.","3","10:00","Pacsirta tér 7.","+36 30 41 4488",true, "OUT"));
        deliveries.add(new Delivery("4","Laci és társa","4","7:00","Kendő út 27.","+36 30 428 4488",false, "IN"));
        deliveries.add(new Delivery("5","Change Ltd.","5","11:00","Fa utca 7.","+36 30 358 4388",false, "IN"));
        deliveries.add(new Delivery("6","Big Money Bt.","6","12:00","Kalap út 17.","+36 344 458 4488",true, "OUT"));
    }

    public static String getNotDoneDeliveryId(){
        notDone = new Delivery("1","Cica Bt.","1","14:00","Fa utca 7.","+36 30 358 4488",false, "IN");
        return "1";
    }

    @Override
    public Call<List<Delivery>> fetchDeliveries(@Path("date") String date) {
        Call<List<Delivery>> call = new Call<List<Delivery>>() {
            @Override
            public Response<List<Delivery>> execute() throws IOException {
                return Response.success(deliveries);
            }

            @Override
            public void enqueue(Callback<List<Delivery>> callback) {

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
            public Call<List<Delivery>> clone() {
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
    public Call<Void> markDeliveryDone(@Path("id") final String id) {
        Call<Void> call = new Call<Void>() {
            @Override
            public Response<Void> execute() throws IOException {
                markDone(id);
                return Response.success(null);
            }

            @Override
            public void enqueue(Callback<Void> callback) {

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
            public Call<Void> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
        return call;
    }

    public void markDone(String id){
        for(Delivery delivery: deliveries){
            if(delivery.getId().equals(id)){
                delivery.setCompleted(true);
            }
        }
    }
}
