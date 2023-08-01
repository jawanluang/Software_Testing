/* 
 * In class exercise
 * Topic: Mocks
 * Author: Dan Walker
 */
package edu.depaul.mocks;

/**
 * Defines a message sent to the CalculationServer.  Contains
 * operators and operands defining a requested calculation
 */
public class Query {
  
  private String operation;
  private double leftValue;
  private double rightValue;
  
  
  public Query(String operation, double leftValue, double rightValue) {
    this.operation = operation;
    this.leftValue = leftValue;
    this.rightValue = rightValue;
  }

  public String operation() {
    return operation;
  }

  public double leftValue() {
    return leftValue;
  }

  public double rightValue() {
    return rightValue;
  }
}
