package se.mah.da401a_assignment_2;

/**
 * Created by ksango on 01/12/14.
 */
public class ChatMessage {

  String message;
  String from;
  String time;

  public ChatMessage() {

  }

  public ChatMessage(String message, String from, String time) {
    this.message = message;
    this.from = from;
    this.time = time;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

}
