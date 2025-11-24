package com.hackerrank.sample.service.impl;

import com.hackerrank.sample.service.FilterStrategy;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
@Log4j2
@Component
public class FilterNumericStrategy implements FilterStrategy {

  @Override
  public boolean aplicaPara(String clave) {
    return clave.equalsIgnoreCase("price") || clave.equalsIgnoreCase("size") || clave.equalsIgnoreCase("rating");
  }

  @Override
  public boolean evaluar(Object valorArticulo, String operador, Object valorReferencia) {
    log.info("Evaluando filtro numerico:, operador={}, valorArticulo={}, valorReferencia={}", operador, valorArticulo, valorReferencia);
    try {
      double artValue = Double.parseDouble(valorArticulo.toString());
      double refValue = Double.parseDouble(valorReferencia.toString());

      switch (operador.toUpperCase()) {
        case "MAYOR_QUE": // >
          return artValue > refValue;
        case "MENOR_QUE": // <
          return artValue < refValue;
        case "IGUAL": // =
          return artValue == refValue;
        default:
          // Manejo de error o estrategia no soportada
          return false;
      }
    } catch (NumberFormatException e) {
      log.error("Error al convertir los valores a Double: {}", e.getMessage());
      return false;
    }
  }
}
