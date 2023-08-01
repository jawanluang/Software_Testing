/* 
 * In class exercise
 * Topic: Mocks
 * Author: Dan Walker
 */
package edu.depaul.mocks;

/**
 * Simulate a client server environment where the client is
 * requesting calculations from a calculation server
 */
public class CalculationApp {

  public static void main(String[] args) {
    CalculationServer server = new CalculationServer();
    Logger logger = new SystemLogger();
    CalculationClient client = new CalculationClient(server, logger);
    
    // try addition
    System.out.println(client.add(10, 20));
    
    // try division
    System.out.println(client.divide(10, 0));
    
  }
}
