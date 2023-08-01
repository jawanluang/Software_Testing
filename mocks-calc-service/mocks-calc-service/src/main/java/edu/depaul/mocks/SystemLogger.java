/* 
 * In class exercise
 * Topic: Mocks
 * Author: Dan Walker
 */
package edu.depaul.mocks;

public class SystemLogger implements Logger {
  
  @Override
  public void log(String msg) {
    System.out.println(msg);
  }

}
