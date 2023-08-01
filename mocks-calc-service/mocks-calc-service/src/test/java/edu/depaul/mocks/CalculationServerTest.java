/* 
 * In class exercise
 * Topic: Mocks
 * Author: Dan Walker
 */
package edu.depaul.mocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CalculationServerTest {

  @Test
  @DisplayName("Should successfully add 2 numbers")
  void addTest() {
    CalculationServer server = new CalculationServer();
    Query add = new Query("ADD", 5, 7);
    Response resp = server.calculate(add);
    assertEquals(12.0, resp.getResult(), 1e-2);
  }

  @Test
  @DisplayName("Should return UNKNOWN_OPERATION when operation has not been implemented")
  void testUnknown() {
    CalculationServer server = new CalculationServer();
    Query xor = new Query("XOR", 5, 7);
    Response resp = server.calculate(xor);
    assertEquals(StatusType.UNKNOWN_OPERATION, resp.getStatus());
  }

  @Test
  @DisplayName("Should handle custom operations correctly")
  void testCustomOperations() {

    // TODO: create and configure a custom operation here
    Operation op = mock(Operation.class);
    when(op.execute(5, 7)).thenReturn(100.0);

    Map<String, Operation> ops = new HashMap<>();
    ops.put("TEST", op);
    CalculationServer server = new CalculationServer(ops);
    Query xor = new Query("TEST", 5, 7);
    Response resp = server.calculate(xor);
    assertEquals(100, resp.getResult());
  }
}
