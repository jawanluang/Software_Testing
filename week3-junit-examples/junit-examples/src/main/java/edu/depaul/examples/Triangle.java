/* 
 * Assignment: #3
 * Topic: Classifying Triangles
 * Author: Dan Walker
 */
package edu.depaul.examples;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static edu.depaul.examples.TriangleType.EQUILATERAL;
import static edu.depaul.examples.TriangleType.ISOSCELES;
import static edu.depaul.examples.TriangleType.SCALENE;

/**
 * A class to classify a set of side lengths as one of the 3 types
 * of triangle: equilateral, isosceles, or scalene.
 *
 * You should not be able to create an invalid Triangle.  The
 * constructor throws an IllegalArgumentException if the input
 * cannot be interpreted as a triangle for any reason.
 */
public class Triangle {

  private static final Logger logger = LoggerFactory.getLogger(Triangle.class);
  private int[] sides = new int[3];

  /**
   * Define as private so that it is not a valid
   * choice.
   */
  private Triangle() {}

  public Triangle(String[] args) {
    validateArgs(args);

    parseArgs(args);

    validateLength();
  }

  public TriangleType classify() {
    TriangleType result;
    if ((sides[0] == sides[1]) && (sides[1] == sides[2])) {
      result = EQUILATERAL;
    } else if ((sides[0] == sides[1])
        || (sides[1] == sides[2])) {
      result = ISOSCELES;
    } else {
      result = SCALENE;
    }
    logger.debug("classified as: " + result);
    return result;
  }

//  @Override
//  public boolean equals(Object other) {
//    if (! (other instanceof Triangle)) {
//      return false;
//    }
//    Triangle otherTriangle = (Triangle) other;
//    return Arrays.equals(this.sides, otherTriangle.sides);
//  }

  private void parseArgs(String[] args) {
    // throws IllegalArgumentException on a failed parse
    for (int i = 0; i < args.length; i++) {
      sides[i] = Integer.parseInt(args[i]);
    }
  }

  private void validateArgs(String[] args) {
    if ((args == null) || (args.length != 3)) {
      throw new IllegalArgumentException("Must have 3 elements");
    }
  }

  private void validateLength() {
    for (int i = 0; i < sides.length; i++) {
      if (sides[i] > 400) {
        throw new IllegalArgumentException("max size is 400");
      }
    }
  }

//  public String toString() {
//    return String.format("%s, %s, %s", sides[0], sides[1], sides[2]);
//  }

}