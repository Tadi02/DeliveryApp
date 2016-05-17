package hu.tomkom.deliveryapp.network.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.google.gson.annotations.SerializedName;




@ApiModel(description = "")
public class Delivery   {
  
  @SerializedName("id")
  private String id = null;
  
  @SerializedName("name")
  private String name = null;
  
  @SerializedName("address")
  private String address = null;
  
  @SerializedName("phone")
  private String phone = null;
  
  @SerializedName("type")
  private String type = null;
  
  @SerializedName("completed")
  private Boolean completed = null;
  
  @SerializedName("time")
  private String time = null;
  
  @SerializedName("rentId")
  private String rentId = null;

  public Delivery() {
  }

  public Delivery(String id, String name, String rentId, String time, String address, String phone, Boolean completed, String type) {
    this.address = address;
    this.completed = completed;
    this.id = id;
    this.name = name;
    this.phone = phone;
    this.rentId = rentId;
    this.time = time;
    this.type = type;
  }
  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Boolean getCompleted() {
    return completed;
  }
  public void setCompleted(Boolean completed) {
    this.completed = completed;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getTime() {
    return time;
  }
  public void setTime(String time) {
    this.time = time;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getRentId() {
    return rentId;
  }
  public void setRentId(String rentId) {
    this.rentId = rentId;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Delivery delivery = (Delivery) o;
    return Objects.equals(id, delivery.id) &&
        Objects.equals(name, delivery.name) &&
        Objects.equals(address, delivery.address) &&
        Objects.equals(phone, delivery.phone) &&
        Objects.equals(type, delivery.type) &&
        Objects.equals(completed, delivery.completed) &&
        Objects.equals(time, delivery.time) &&
        Objects.equals(rentId, delivery.rentId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, address, phone, type, completed, time, rentId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Delivery {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    completed: ").append(toIndentedString(completed)).append("\n");
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
    sb.append("    rentId: ").append(toIndentedString(rentId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
