package com.hackerrank.sample.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class AppConfig {

  @Value("${app.string.strategy.regex:technology|resolution|brand}")
  private String stringStrategyRegex;

  @Value("${app.numeric.strategy.regex:price|size|rating}")
  private String numericStrategyRegex;
}
