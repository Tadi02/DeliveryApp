package hu.tomkom.deliveryapp.network.api;

import java.util.List;

import hu.tomkom.deliveryapp.network.model.Delivery;
import retrofit2.Call;
import retrofit2.http.*;

public interface DeliveryApi {
  
  /**
   * 
   * Fetches deliveries for a specific date format.
   * @param date Date in yyyy-mm-dd format
   * @return Call<List<Delivery>>
   */
  
  @GET("delivery/{date}")
  Call<List<Delivery>> fetchDeliveries(
    @Path("date") String date
  );

  
  /**
   * 
   * Marks the delivery with the given id done
   * @param id Delivery id
   * @return Call<Void>
   */
  
  @POST("delivery/{id}/done")
  Call<Void> markDeliveryDone(
    @Path("id") String id
  );

  
}
