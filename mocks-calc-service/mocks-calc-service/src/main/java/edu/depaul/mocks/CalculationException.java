/* 
 * In class exercise
 * Topic: Mocks
 * Author: Dan Walker
 */
package edu.depaul.mocks;

/**
 * All errors from the CalculationServer result in this
 * exception being thrown.
 */
public class CalculationException extends RuntimeException {
  public CalculationException(String msg) {
    super(msg);
  }
}
