/*
 * Assignment: #3
 * Topic: JUnit examples
 * Author: Dan Walker
 */
package edu.depaul.examples;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Little tests that demonstrate JUnit features
 */
public class JUnitExamples {

  @Test
  @DisplayName("assertTrue demonstration")
  void demonstrateAssertTrue() {
    int result = 10 % 4;
    assertTrue(2 == result);
  }

  @Test
  @DisplayName("assertFalse demonstration")
  void demonstrateAssertFalse() {
    int result = 10 % 5;

    assertFalse(2 == result);
  }



  @Test
  @DisplayName("assertNull demonstration")
  void demonstrateAssertNull() {
    Triangle triangle =  new Triangle(new String[] {"3", "3", "3"});

    assertNull(triangle);
  }




  @Test
  @DisplayName("assertNull demonstration")
  void demonstrateAssertNotNull() {
    Triangle triangle =  new Triangle(new String[] {"3", "3", "3"});

    assertNotNull(triangle, "constructor should work");
  }


  @Test
  @DisplayName("assertSame demonstration")
  void demonstrateAssertSame() {
    Triangle triangle = new Triangle(new String[] {"3", "3", "3"});
    Triangle myRef = triangle;
    Triangle myCopy = new Triangle(new String[] {"3", "3", "3"});

    // watch out for effect of toString()
    assertSame(triangle, myCopy);
  }

  @Test
  @DisplayName("assertNotSame demonstration")
  void demonstrateAssertNotSame() {
    Triangle triangle = new Triangle(new String[] {"3", "3", "3"});
    Triangle myRef = triangle;
    Triangle myCopy = new Triangle(new String[] {"3", "3", "3"});

    // watch out for effect of toString()
    assertNotSame(triangle, myCopy);
  }

  @Test
  @DisplayName("assertEquals demonstration with Triangles")
  void demonstrateAssertEquals() {
    Triangle triangle = new Triangle(new String[] {"3", "3", "3"});
    Triangle myRef = triangle;
    Triangle myCopy = new Triangle(new String[] {"3", "3", "3"});

    assertEquals(triangle, myCopy);
  }

  @Test
  @DisplayName("assertNotEquals demonstration")
  void demonstrateAssertNotEquals() {
    Triangle triangle = new Triangle(new String[] {"3", "3", "3"});
    Triangle other = new Triangle(new String[] {"3", "4", "5"});

    assertNotEquals(triangle, other);
  }

  @Test
  @DisplayName("assertEquals demonstration ith doubles")
  void demonstrateAssertDoubleEquals() {
    double expected = 100.0;
    double actual = 99.99995;


    assertEquals(expected, actual, 1e-4);  // 0.0001
  }

  @Test
  @DisplayName("assertArrayEquals demonstration")
  void demonstrateAssertArraysEquals() {
    int[] expected = {1,2,3,4};
    int[] actual = {1,2,3,5};

    assertArrayEquals(expected, actual);
  }

  @Test
  @DisplayName("assertArrayEquals demonstration")
  void demonstrateAssertArraysEquals2() {
    int[][] expected = {{1,2,3,4},{5,5,5}};
    int[][] actual = {{1,2,3,5}, {5,5,6}};

    // notice it only catches the first difference, similar to the problem
    // with multiple asserts
    assertArrayEquals(expected, actual);
  }

  @Test
  @DisplayName("assertThrows demonstration")
  void demonstrateAssertThrows() {
    assertThrows(IllegalArgumentException.class, () -> new Triangle(new String[] {"3", "3","3"}));
  }

  @Test
  @DisplayName("assertThrows demonstration")
  void demonstrateAssertDoesNotThrow() {
    assertDoesNotThrow(() -> new Triangle(new String[] {})); //"3", "3","3"}));
  }

  @Test
  @DisplayName("test error message 1")
  void demonstrateFail() {
    try {
      Triangle t = new Triangle(new String[] {});
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Must have 3 elements", e.getMessage());
    }
  }

  @Test
  @DisplayName("Better test error message")
  void demonstrateFail2() {
    Exception e = assertThrows(IllegalArgumentException.class, () -> new Triangle(new String[] {}));
    assertEquals("Must have 3 elements", e.getMessage());
  }

  @Test
  @DisplayName("Demonstrate assertAll")
  void demoAssertAll() {
    Point p = new Point(10);
    assertAll(
        () -> assertEquals(10, p.getX()),
        () -> assertEquals(10, p.getY())
    );
  }

}
