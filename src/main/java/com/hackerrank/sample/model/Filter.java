package com.hackerrank.sample.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Filter {
  private String key;
  private String operator;
  private Object value;
}