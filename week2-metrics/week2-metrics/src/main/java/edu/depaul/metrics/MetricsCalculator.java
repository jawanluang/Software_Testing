package edu.depaul.metrics;

import java.util.Arrays;

/**
 * Provides calculations for:
 * MTBF
 * MTTR
 * Availability
 * Affected users
 * Reliability
 */
public class MetricsCalculator {

  public MetricsCalculator() {}

  /**
   * Calculate Mean Time Between Failures
   * @param events an array of downtime durations example: {7, 3}
   *               represents two events that took 7 and 3 minutes
   * @param scope the duration of the total time being considered.
   *              for example, scope of 10080 would be 1 week in minutes
   * @return the calculated MTBF for the given events and scope
   */
  public double getMTBF(int[] events, int scope) {
    double downTime = Arrays.stream(events).sum();
    double upTime = scope - downTime;

    if (events.length == 0)
      throw new IllegalArgumentException("An array of downtime events cannot be empty or null");
    if (scope <= 0)
      throw new IllegalArgumentException("Scope cannot be a negative number or zero");

    for (int event : events) {
      if (event <= 0)
        throw new IllegalArgumentException("A downtime event cannot be less than or equal to 0 or null");
      if (event > scope)
        throw new IllegalArgumentException("A downtime event cannot be larger than the scope");
    }

    if (upTime < 0)
      throw new IllegalArgumentException("System uptime is negative due to total downtime exceeding scope");

    return upTime / events.length;
  }

  /**
   * Identical inputs the getMTBF() above
   * @return the calculated MTTR
   */
  public double getMTTR(int[] events, int scope) {
    double downTime = Arrays.stream(events).sum();

    if (events.length == 0)
      throw new IllegalArgumentException("An array of downtime events cannot be empty or null");
    if (scope <= 0)
      throw new IllegalArgumentException("Scope cannot be a negative number or zero");

    for (int event : events) {
      if (event <= 0)
        throw new IllegalArgumentException("A downtime event cannot be less than or equal to 0 or null");
      if (event > scope)
        throw new IllegalArgumentException("A downtime event cannot be larger than the scope");
    }

    return downTime / events.length;
  }

  /**
   * Identical inputs the getMTBF() above
   * @return the calculated availability
   */
  public double getAvailability(int[] events, int scope) {
    double downTime = Arrays.stream(events).sum();
    double upTime = scope - downTime;

    if (events.length == 0)
      throw new IllegalArgumentException("An array of downtime events cannot be empty or null");
    if (scope <= 0)
      throw new IllegalArgumentException("Scope cannot be a negative number or zero");

    for (int event : events) {
      if (event <= 0)
        throw new IllegalArgumentException("A downtime event cannot be less than or equal to 0 or null");
      if (event > scope)
        throw new IllegalArgumentException("A downtime event cannot be larger than the scope");
    }

    if (upTime < 0)
      throw new IllegalArgumentException("System uptime is negative due to total downtime exceeding scope");

    return (upTime / scope) * 100;
  }

  /**
   * Calculate the predicted number of affected users, given the session
   * information provided.
   * @param sessionsPerHour in general, the number of sessions per hour
   * @param sessionLength the average duration, in minutes of a single session
   * @param downTime the number of minutes the system was down
   * @return total number of user affected.  Includes dropped sessions as well
   * as # of users unable to connect during the downtime
   */
  public double getAffectedUsers(int sessionsPerHour, int sessionLength, double downTime) {
    double currentUsers = ((double)sessionsPerHour / 60) * sessionLength;
    double deniedUsers = ((double)sessionsPerHour / 60) * downTime;

    if (sessionsPerHour < 0 || sessionLength < 0 || downTime < 0)
      throw new IllegalArgumentException("There are one or more negative numbers in the parameters");
    if (downTime == 0 || sessionLength == 0 || sessionsPerHour == 0)
      throw new IllegalArgumentException("There are one or more inputs that equal zero");

    return currentUsers + deniedUsers;
  }

  /**
   * Calculates the reliability of a system over a give period of time (scope)
   * The first 3 parameters are identical to those given in getAffectedUsers
   * @param scope the total amount of time for the calculation, given in hours
   * @return Estimated reliability of a system
   */
  public double getReliability(int sessionsPerHour, int sessionLength, double downTime, int scope) {
    double totalSessions = ((double)sessionsPerHour / 60) * (scope * 60);
    double failedSessions = getAffectedUsers(sessionsPerHour, sessionLength, downTime);
    double successfulSessions = totalSessions - failedSessions;

    if (scope <= 0)
      throw new IllegalArgumentException("Scope cannot be a negative number or zero");
    if(downTime > scope * 60)
      throw new IllegalArgumentException("A downtime event cannot be larger than the scope");

    return (successfulSessions / totalSessions) * 100;
  }
}
