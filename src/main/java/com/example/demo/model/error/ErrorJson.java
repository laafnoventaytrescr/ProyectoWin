package com.example.demo.model.error;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorJson{                                             //Modelo de error para generarlo en formato Json
   
	   @JsonProperty("message")
	   private String message;
	   @JsonProperty("status_code")
	   private int statusCode;
	   @JsonProperty("uri")
	   private String uriRequested;

	   public ErrorJson(int statusCode, String message, String uriRequested) {
	       this.message = message;
	       this.statusCode = statusCode;
	       this.uriRequested = uriRequested;
	   }

	   public String getMessage() {
	       return this.message;
	   }

	   public int getStatusCode() {
	       return this.statusCode;
	   }

	   public String getUriRequested() {
	       return this.uriRequested;
	   }

}
