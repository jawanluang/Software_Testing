/* 
 * Demonstrations
 * Topic: Functional test techniques
 * Author: Dan Walker
 */
package edu.depaul.se433.functional;

import java.util.Arrays;

/**
 * Calculates standard deviation for a given array of values.  Used to
 * demonstrate CsvFileSource.  See StdDevTest
 */
public class StdDev {
  public double calculate(double[] values) {
    double mean = mean(values);
    double ss = sumSqr(values, mean);
    double variance = ss / values.length;
    return Math.sqrt(variance);
  }

  private double mean(double[] values) {
    return Arrays.stream(values).average().orElse(Double.NaN);
  }


  private double sumSqr(double[] values, double mean) {
    double[] diffs = new double[values.length];
    for (int i = 0; i < values.length; i++) {
      double diff = values[i] - mean;
      diffs[i] = diff * diff;
    }
    return Arrays.stream(diffs).sum();
  }
}
