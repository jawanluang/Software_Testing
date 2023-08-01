/* 
 * In class exercise
 * Topic: Mocks
 * Author: Dan Walker
 */
package edu.depaul.mocks;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Not really a test.  Used to demonstrate mock behavior
 */
class DemoMocks {

  @Test
  void demoMocks() {
    CalculationServer realServer = new CalculationServer();
    CalculationServer mockServer = mock(CalculationServer.class);

    // mock behavior when overrides are provided
    when(mockServer.getSecret()).thenReturn("mock secret");
    String realSecret = realServer.getSecret();
    String mockSecret = mockServer.getSecret();
    System.out.println("Real secret: " + realSecret);
    System.out.println("Mock secret: " + mockSecret);

    // mock behavior when no override is provided
    System.out.println("Real start date: " + realServer.getStartDate());
    System.out.println("Mock start date: " + mockServer.getStartDate());

    Response response = mockServer.calculate(null);
    System.out.println("Mock calculate response: " + response);

    response = realServer.calculate(null);
    System.out.println("Real calculate response: " + response);
  }
}
