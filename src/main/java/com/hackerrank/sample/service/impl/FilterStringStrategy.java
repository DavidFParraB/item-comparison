package com.hackerrank.sample.service.impl;

import com.hackerrank.sample.config.AppConfig;
import com.hackerrank.sample.service.FilterStrategy;
import com.hackerrank.sample.util.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@AllArgsConstructor
public class FilterStringStrategy implements FilterStrategy {

  private final AppConfig appConfig;
  @Override
  public boolean applyBy(String key) {
    return key.matches(appConfig.getStringStrategyRegex());
  }

  @Override
  public boolean validate(Object itemValue, String operator, Object referenceValue) {
    log.info("Filter: operator={}, itemValue={}, referenceValue={}",
        operator, itemValue, referenceValue);
    if (Constants.EQUALS_TO.equalsIgnoreCase(operator)) {
      return itemValue.toString().equalsIgnoreCase(referenceValue.toString());
    }
    return false;
  }
}
