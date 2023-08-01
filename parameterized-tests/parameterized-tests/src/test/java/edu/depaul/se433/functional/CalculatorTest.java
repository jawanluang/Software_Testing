/* 
 * Demonstrations
 * Topic: Functional test techniques
 * Author: Dan Walker
 */
package edu.depaul.se433.functional;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Demonstrate the use of Junit5 parameterized tests
 */
public class CalculatorTest {
  
  @ParameterizedTest(name = "{1}! should == {0}")
  @MethodSource("provideExpectedAndInput")
  @DisplayName("Test the factorial operation against hand calculated values")
    public void testFactorialAgainstExpected(int expected, int input) {
      Calculator calc = new Calculator();
      assertEquals(expected, calc.factorial(input));
    }
  
  private static Stream<Arguments> provideExpectedAndInput() {
    return Stream.of(
      Arguments.of(1,    0),
      Arguments.of(1,    1),
      Arguments.of(2,    2),
      Arguments.of(24,   4),
      Arguments.of(5040, 7)
    );
  }

  @ParameterizedTest
  @MethodSource("providePeanutInput")
  @DisplayName("Test the peanut pricing function with hand calculated values")
    public void testPeanutPricing(int dayOfPurchase, int age, double peanutsAmount, double expected) {
      Calculator calc = new Calculator();
      assertEquals(expected, calc.peanutPrice(dayOfPurchase, age, peanutsAmount), 1e-2);
    }
  
  private static Stream<Arguments> providePeanutInput() {
    return Stream.of(
      //           day age amt   expected
      Arguments.of(6,  50, 5.5,  35.75),
      Arguments.of(6,  80, 35.0, 183.75),
      Arguments.of(6,  50, 35.0, 204.75),
      Arguments.of(6,  80, 5.5,  28.87),
      Arguments.of(3,  50, 5.5,  38.5),
      Arguments.of(3,  50, 35.0, 220.5),
      Arguments.of(3,  80, 5.5,  28.87),
      Arguments.of(3,  80, 35.0, 183.75));
  }

  @ParameterizedTest
  @MethodSource("provideMixedPeanutInput")
  @DisplayName("Robust Test the peanut pricing function with hand calculated values")
  public void mixedTestPeanutPricing(int dayOfPurchase, int age, double peanutsAmount, double expected) {
    Calculator calc = new Calculator();
    if (expected > 0) {
      assertEquals(expected, calc.peanutPrice(dayOfPurchase, age, peanutsAmount), 1e-2);
    } else {
      assertThrows(IllegalArgumentException.class, () -> calc.peanutPrice(dayOfPurchase, age, peanutsAmount));
    }
  }

  private static Stream<Arguments> provideMixedPeanutInput() {
    return Stream.of(
        //           day age amt   expected
        Arguments.of(6,  50, 5.5,  35.75),
        Arguments.of(6,  80, 35.0, 183.75),
        Arguments.of(6,  50, 35.0, 204.75),
        Arguments.of(6,  80, 5.5,  28.87),
        Arguments.of(3,  50, 5.5,  38.5),
        Arguments.of(3,  50, 35.0, 220.5),
        Arguments.of(3,  80, 5.5,  28.87),
        Arguments.of(3,  80, 35.0, 183.75),
        Arguments.of(8,  50, 35.0, -1),
        Arguments.of(3,  12, 5.5,  -1),
        Arguments.of(3,  80, 90.0, -1));
  }

  @ParameterizedTest
  @MethodSource("provideInvalidPeanutInput")
  @DisplayName("Invalid Test the peanut pricing function with hand calculated values")
  public void testInvalidPeanutPricing(int dayOfPurchase, int age, double peanutsAmount) {
    Calculator calc = new Calculator();
    assertThrows(IllegalArgumentException.class, () -> calc.peanutPrice(dayOfPurchase, age, peanutsAmount));
  }

  private static Stream<Arguments> provideInvalidPeanutInput() {
    return Stream.of(
        //           day age amt
        Arguments.of(8,  50, 35.0),
        Arguments.of(3,  12, 5.5),
        Arguments.of(3,  80, 90.0));
  }

  @ParameterizedTest
  @MethodSource("provideBADPeanutInput")
  @DisplayName("BAD Test the peanut pricing function with hand calculated values")
  public void BADTestPeanutPricing(double actual, double expected) {
    assertEquals(expected, actual, 1e-2);
  }

  private static Stream<Arguments> provideBADPeanutInput() {
    return Stream.of(
        Arguments.of(new Calculator().peanutPrice(6,50,5.5), 35.75),
        Arguments.of(new Calculator().peanutPrice(6, 80, 35.0), 183.75),
        Arguments.of(new Calculator().peanutPrice(6, 50, 35.0),204.75));
  }
}

