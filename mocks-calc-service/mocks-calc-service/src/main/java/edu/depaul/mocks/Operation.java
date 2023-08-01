/* 
 * In class exercise
 * Topic: Mocks
 * Author: Dan Walker
 */
package edu.depaul.mocks;

/**
 *
 */
public interface Operation {
  public double execute(double left, double right);
}

class Add implements Operation {
  
  @Override
  public double execute(double left, double right) {
    return left + right;
  }
}

class Subtract implements Operation {
  
  @Override
  public double execute(double left, double right) {
    return left - right;
  }
}

class Multiply implements Operation {
  
  @Override
  public double execute(double left, double right) {
    return left * right;
  }
}

class Divide implements Operation {
  
  @Override
  public double execute(double left, double right) {
    if (right != 0.0) {
      return left / right;
    } else {
      throw new CalculationException("divide-by-zero");
    }
  }
}
