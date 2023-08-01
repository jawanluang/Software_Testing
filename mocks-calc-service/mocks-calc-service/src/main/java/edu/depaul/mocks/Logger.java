/* 
 * In class exercise
 * Topic: Mocks
 * Author: Dan Walker
 */
package edu.depaul.mocks;

/**
 * Define a simplified logger for demonstration purposes. For real
 * world logging consider slf4j backed by log4j or logback.
 */
public interface Logger {
  
  public void log(String msg);

}
