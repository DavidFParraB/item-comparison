package com.hackerrank.sample.dto;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponseDTO   {
  private int code;
  private String description;
}
