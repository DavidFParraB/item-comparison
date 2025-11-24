package com.hackerrank.sample.service.impl;

import com.hackerrank.sample.service.FilterStrategy;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class FilterStringStrategy implements FilterStrategy {

  @Override
  public boolean applyBy(String key) {
    return key.equalsIgnoreCase("technology") || key.equalsIgnoreCase("resolution") || key.equalsIgnoreCase("brand");
  }

  @Override
  public boolean validate(Object itemValue, String operator, Object referenceValue) {
    log.info("Filter: operator={}, itemValue={}, referenceValue={}",
        operator, itemValue, referenceValue);
    if ("IGUAL".equalsIgnoreCase(operator)) { // =
      return itemValue.toString().equalsIgnoreCase(referenceValue.toString());
    }
    return false;
  }
}
