package com.hackerrank.sample.service;

public interface FilterStrategy {

  /**
   * Define whether this strategy can handle the specification KEY (e.g., "Price", "RAM").
   */
  boolean applyBy(String key);

  /**
   * Evaluates whether the item value meets the filtering condition.
   *
   * @param itemValue      The value of the item field (e.g., 1200.00).
   * @param operator       The comparison type (e.g., ">", "<", "=").
   * @param referenceValue The value to compare against (e.g., 500.00).
   * @return true if the item meets the condition.
   */
  boolean validate(Object itemValue, String operator, Object referenceValue);
}
