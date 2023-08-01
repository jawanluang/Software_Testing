/* 
 * Demonstrations
 * Topic: Functional test techniques
 * Author: Dan Walker
 */
package edu.depaul.se433.functional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StdDevTest {
  @Test
  void simpleTest() {
    double[] values = new double[] {600, 470, 170, 430, 300};
    StdDev stdDev = new StdDev();
    double result = stdDev.calculate(values);
    assertEquals(147.32, result, 1e-2);
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/values.csv", numLinesToSkip = 0)
  @DisplayName("Test the standard deviation operation against hand calculated values")
  void stdDevTest(double expected, String values) {
    StdDev stdDev = new StdDev();
    double result = stdDev.calculate(doubles(values));
    assertEquals(expected, result, 1e-2);
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/num-values.csv", numLinesToSkip = 0)
  @DisplayName("Test the standard deviation operation against hand calculated values")
  void stdDevTest2(double expected, double ... values) {
    StdDev stdDev = new StdDev();
    assertThrows(NumberFormatException.class, () -> stdDev.calculate(values), "xxxmessage");
  }

  private double[] doubles(String elementsStr) {
    String[] elements = elementsStr.split(",");
    double[] result = new double[elements.length];
    for (int i=0; i < elements.length; i++) {
      result[i] = Double.parseDouble(elements[i]);
    }
    return result;
  }
}
