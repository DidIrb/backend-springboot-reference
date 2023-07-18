package com.template.example.models;

public class Message {
  // passing data to message
  private int code;
  private String body;
  private String message;
  private String description;
  
  // constructor

  public Message(int code, String body, String message, String description) {
    this.code = code;
    this.body = body;
    this.message = message;
    this.description = description;
  }

  // getters and setters

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}

