/* 
 * In class exercise
 * Topic: Mocks
 * Author: Dan Walker
 */
package edu.depaul.mocks;

/**
 * Represents the result from an operation carried out
 * by the CalculationServer
 */
public class Response {
  private StatusType status;
  private double result;
  private String message;
  
  public Response(StatusType status, double result) {
    this.status = status;
    this.result = result;
  }
  
  public Response(StatusType status, String message) {
    this.status = status;
    this.message = message;
  }
  
  public Response(String message) {
    status = StatusType.INTERNAL_ERROR;
    this.message = message;
  }

  public StatusType getStatus() {
    return status;
  }

  public double getResult() {
    return result;
  }
  
  public String getMessage() {
    return message;
  }

  public String toString() {
    return "Response(" + status + ", " + message + ", " + result + ")";
  }
}
