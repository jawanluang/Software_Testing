/* 
 * Demonstrations
 * Topic: Functional test techniques
 * Author: Dan Walker
 */
package edu.depaul.se433.functional;

/**
 * Simple class to demonstrate parameterized tests.
 * @see CalculatorTest
 */
public class Calculator {
  
  private int buffer;

  public long factorial(int n) {
    if (n == 0) {
      return 1;
    }
    return n * factorial(n - 1);
  }
  
  public double peanutPrice(int day, int age, double amount) {
    validate(day, age, amount);
    double basePrice = amount * 7.0;
    double finalPrice = 0.0;
    if (age >= 65) {
      finalPrice = basePrice - (basePrice * 0.25);
    } else {
      if (day == 6) {
        basePrice = amount * 6.5;
      }
      if (amount >= 10.0) {
        finalPrice = basePrice - (basePrice * 0.1);
      } else {
        finalPrice = basePrice;
      }
    }
    return finalPrice;
  }

  private void validate(int day, int age, double amount) {
    if ((day < 1) || (day > 7)) throw new IllegalArgumentException("Bad day value");
    if (age < 18) throw new IllegalArgumentException("Bad age value");
    if (amount < 0.1 || amount > 50.0) throw new IllegalArgumentException("Bad amount value");
  }
}
