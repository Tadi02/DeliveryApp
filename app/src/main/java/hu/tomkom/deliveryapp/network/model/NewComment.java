package hu.tomkom.deliveryapp.network.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.google.gson.annotations.SerializedName;




@ApiModel(description = "")
public class NewComment   {

  @SerializedName("time")
  private String time = null;
  
  @SerializedName("text")
  private String text = null;

  public NewComment() {
  }

  public NewComment(String time, String text) {
    this.time = time;
    this.text = text;
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
  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewComment newComment = (NewComment) o;
    return Objects.equals(time, newComment.time) &&
        Objects.equals(text, newComment.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(time, text);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewComment {\n");
    
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
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
