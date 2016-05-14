package hu.tomkom.deliveryapp.interactor.delivery;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import hu.tomkom.deliveryapp.DeliveryApplication;
import hu.tomkom.deliveryapp.interactor.delivery.event.DeliveryMarkedEvent;
import hu.tomkom.deliveryapp.interactor.delivery.event.FetchDeliveriesEvent;
import hu.tomkom.deliveryapp.model.Delivery;
import hu.tomkom.deliveryapp.model.DeliveryType;
import hu.tomkom.deliveryapp.network.api.DeliveryApi;
import retrofit2.Call;
import retrofit2.Response;

public class DeliveryInteractor {

    @Inject
    DeliveryApi deliveryApi;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public DeliveryInteractor() {
        DeliveryApplication.injector.inject(this);
    }

    public void fetchTodaysDeliveries(){
        fetchDeliveriesForDate(new Date(), "TODAY");
    }

    public void fetchDeliveriesForDate(Date date){
        fetchDeliveriesForDate(date, "COMMON");
    }

    private void fetchDeliveriesForDate(Date date, String type){
        Call<List<hu.tomkom.deliveryapp.network.model.Delivery>> deliveryCall = deliveryApi.fetchDeliveries(dateFormat.format(date));
        FetchDeliveriesEvent event = new FetchDeliveriesEvent();
        try {
            Response<List<hu.tomkom.deliveryapp.network.model.Delivery>> response = deliveryCall.execute();
            if (response.code() != 200) {
                throw new Exception("Result code is not 200");
            }
            event.setSuccess(true);
            event.setDeliveries(response.body());
            event.setType(type);
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            EventBus.getDefault().post(event);
        }
    }

    public void markDeliveryCompleted(String id, String source){
        Call<Void> call = deliveryApi.markDeliveryDone(id);
        DeliveryMarkedEvent event = new DeliveryMarkedEvent();
        event.setSource(source);
        try {
            Response<Void> response = call.execute();
            if (response.code() != 200) {
                throw new Exception("Result code is not 200");
            }
            event.setSuccess(true);
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            EventBus.getDefault().post(event);
        }
    }

    public List<Delivery> parseDeliveries(List<hu.tomkom.deliveryapp.network.model.Delivery> deliveries){
        List<Delivery> result = new ArrayList<>();
        for(hu.tomkom.deliveryapp.network.model.Delivery delivery : deliveries){
            Delivery converted = new Delivery();
            converted.setRentId(delivery.getRentId());
            converted.setId(Long.parseLong(delivery.getId()));
            converted.setName(delivery.getName());
            converted.setAddress(delivery.getAddress());
            converted.setCompleted(delivery.getCompleted());
            converted.setPhone(delivery.getPhone());
            converted.setTime(delivery.getTime());
            converted.setType(DeliveryType.valueOf(delivery.getType()));
            result.add(converted);
        }
        return result;
    }

}
