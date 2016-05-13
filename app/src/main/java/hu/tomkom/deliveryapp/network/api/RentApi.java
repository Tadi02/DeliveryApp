package hu.tomkom.deliveryapp.network.api;

import hu.tomkom.deliveryapp.network.model.NewComment;
import hu.tomkom.deliveryapp.network.model.Rent;
import retrofit2.Call;
import retrofit2.http.*;

public interface RentApi {
  
  /**
   * 
   * Fetches rent for given id
   * @param id Rent id
   * @return Call<Rent>
   */
  
  @GET("rent/{id}")
  Call<Rent> fetchRent(
    @Path("id") String id
  );

  
  /**
   * 
   * Adds a new comment
   * @param id Rent id
   * @param newComment New comment
   * @return Call<Rent>
   */
  
  @POST("rent/{id}/comment")
  Call<Rent> addComment(
    @Path("id") String id, @Body NewComment newComment
  );

  
  /**
   * 
   * Removes a comment
   * @param id Rent id
   * @param commentId Comment id
   * @return Call<Rent>
   */
  
  @DELETE("rent/{id}/comment/{commentId}")
  Call<Rent> removeComment(
    @Path("id") String id, @Path("commentId") String commentId
  );

  
}
