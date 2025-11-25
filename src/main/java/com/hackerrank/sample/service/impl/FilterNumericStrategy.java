package com.hackerrank.sample.service.impl;

import com.hackerrank.sample.config.AppConfig;
import com.hackerrank.sample.exception.BadResourceRequestException;
import com.hackerrank.sample.service.FilterStrategy;
import com.hackerrank.sample.util.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
@Log4j2
@Component
@AllArgsConstructor
public class FilterNumericStrategy implements FilterStrategy {

  private final AppConfig appConfig;

  @Override
  public boolean applyBy(String key) {
    return key.matches(appConfig.getNumericStrategyRegex());
  }

  @Override
  public boolean validate(Object itemValue, String operator, Object referenceValue) {
    log.info("Numeric filter:, operator={}, itemValue={}, referenceValue={}",
        operator, itemValue, referenceValue);
    try {
      double artValue = Double.parseDouble(itemValue.toString());
      double refValue = Double.parseDouble(referenceValue.toString());

      return switch (operator.toUpperCase()) {
        case Constants.GREATER_THAN -> // >
            artValue > refValue;
        case Constants.LESS_THAN ->  // <
            artValue < refValue;
        case Constants.EQUALS_TO -> // =
            artValue == refValue;
        default ->
          throw new BadResourceRequestException("Unsupported filter operator: " + operator);
      };
    } catch (NumberFormatException e) {
      log.error("Error converting values to numeric: {}", e.getMessage());
      throw new BadResourceRequestException("Incorrect value format. Must be a number.");
    }
  }
}
