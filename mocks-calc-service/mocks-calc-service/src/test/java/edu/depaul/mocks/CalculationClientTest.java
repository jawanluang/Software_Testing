/* 
 * In class exercise
 * Topic: Mocks
 * Author: Dan Walker
 */
package edu.depaul.mocks;

import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculationClientTest {

  @Test
  @DisplayName("When calculation is successful, returns value produced by server(")
  void testSuccessfulAdd() {
    
    CalculationServer mockServer = mock(CalculationServer.class);
    when(mockServer.calculate(any(Query.class))).thenReturn(new Response(StatusType.OK, 25.0));
    
    Logger logger = new SystemLogger();
    CalculationClient client = new CalculationClient(mockServer, logger);
    double result = client.add(0.0, 0.0);  // just to prove client isn't adding for itself
    assertEquals(25.0, result, 1e-2);
  }

  /**
   * Verify that when the server returns an error Response, the client
   * logs it.
   */
  @Test
  @DisplayName("When calculation fails, the client logs the result")
  public void testLogResult() {
    CalculationServer mockServer = mock(CalculationServer.class);
    Logger mockLogger = mock(Logger.class);
    String logMessage = "test-log-message";
    when(mockServer.calculate(any(Query.class))).thenReturn(new Response(logMessage));

    CalculationClient client = new CalculationClient(mockServer, mockLogger);
    client.add(10, 15);
    verify(mockLogger).log(endsWith(logMessage));
  }
  

  @Test
  @DisplayName("examine a mock")
  void examineMock() {
    CalculationServer mockServer = mock(CalculationServer.class);
    mockServer.calculate(new Query("XOR", 1.0, 2.0));
    String secret = mockServer.getSecret();

    // put a break point on line below so you can examine the mock server
    System.out.println("pause");
  }

  @Test
  @DisplayName("Demonstrate mockito thenThrow")
  void demoThenThrow() {
    CalculationServer mockServer = mock(CalculationServer.class);
    when(mockServer.calculate(any(Query.class))).thenThrow(new RuntimeException("failed"));
    
    assertThrows(RuntimeException.class, () -> mockServer.calculate(new Query("stuff", 1, 1)));
  }
}
