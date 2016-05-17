package hu.tomkom.deliveryapp.network.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

@ApiModel(description = "")
public class Rent   {
  
  @SerializedName("id")
  private String id = null;

  @SerializedName("name")
  private String name = null;
  
  @SerializedName("start")
  private String start = null;
  
  @SerializedName("end")
  private String end = null;
  
  @SerializedName("status")
  private String status = null;
  
  @SerializedName("comments")
  private List<Comment> comments = new ArrayList<Comment>();

  public Rent() {
  }

  public Rent(String id, String name, String start, String end, String status, List<Comment> comments) {
    this.comments = comments;
    this.name = name;
    this.end = end;
    this.id = id;
    this.start = start;
    this.status = status;
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
  public String getStart() {
    return start;
  }
  public void setStart(String start) {
    this.start = start;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getEnd() {
    return end;
  }
  public void setEnd(String end) {
    this.end = end;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public List<Comment> getComments() {
    return comments;
  }
  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Rent rent = (Rent) o;
    return Objects.equals(id, rent.id) &&
        Objects.equals(name, rent.name) &&
        Objects.equals(start, rent.start) &&
        Objects.equals(end, rent.end) &&
        Objects.equals(status, rent.status) &&
        Objects.equals(comments, rent.comments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, start, end, status, comments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Rent {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    start: ").append(toIndentedString(start)).append("\n");
    sb.append("    end: ").append(toIndentedString(end)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    comments: ").append(toIndentedString(comments)).append("\n");
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
