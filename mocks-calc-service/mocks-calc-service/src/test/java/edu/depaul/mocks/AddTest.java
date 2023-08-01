/* 
 * In class exercise
 * Topic: Mocks
 * Author: Dan Walker
 */
package edu.depaul.mocks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AddTest {

  @Test
  @DisplayName("Add happy path test")
  void testAdd() {
    Add adder = new Add();
    double result = adder.execute(1.0, 2.0);
    assertEquals(3.0, result, 1e-2);
  }

  @Test
  @DisplayName("A value plus it's negation should equal zero")
  void testAddNegative() {
    Add adder = new Add();
    double result = adder.execute(1.0, -1.0);
    assertEquals(0.0, result, 1e-2);
  }
}
