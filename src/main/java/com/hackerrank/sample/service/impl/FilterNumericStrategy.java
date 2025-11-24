package com.hackerrank.sample.service.impl;

import com.hackerrank.sample.service.FilterStrategy;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
@Log4j2
@Component
public class FilterNumericStrategy implements FilterStrategy {

  @Override
  public boolean applyBy(String key) {
    return key.equalsIgnoreCase("price") || key.equalsIgnoreCase("size") || key.equalsIgnoreCase("rating");
  }

  @Override
  public boolean validate(Object itemValue, String operator, Object referenceValue) {
    log.info("Evaluando filtro numerico:, operador={}, valorArticulo={}, valorReferencia={}",
        operator, itemValue, referenceValue);
    try {
      double artValue = Double.parseDouble(itemValue.toString());
      double refValue = Double.parseDouble(referenceValue.toString());

      return switch (operator.toUpperCase()) {
        case "MAYOR_QUE" -> // >
            artValue > refValue;
        case "MENOR_QUE" -> // <
            artValue < refValue;
        case "IGUAL" -> // =
            artValue == refValue;
        default ->
          // Manejo de error o estrategia no soportada
            false;
      };
    } catch (NumberFormatException e) {
      log.error("Error al convertir los valores a Double: {}", e.getMessage());
      return false;
    }
  }
}
