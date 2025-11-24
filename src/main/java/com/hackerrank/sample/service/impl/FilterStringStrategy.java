package com.hackerrank.sample.service.impl;

import com.hackerrank.sample.service.FilterStrategy;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class FilterStringStrategy implements FilterStrategy {

  @Override
  public boolean aplicaPara(String clave) {
    return clave.equalsIgnoreCase("technology") || clave.equalsIgnoreCase("resolution");
  }

  @Override
  public boolean evaluar(Object valorArticulo, String operador, Object valorReferencia) {
    log.info("Evaluando filtro String:, operador={}, valorArticulo={}, valorReferencia={}",
        operador, valorArticulo, valorReferencia);
    try {
      if ("IGUAL".equalsIgnoreCase(operador)) { // =
        return valorArticulo.toString().equalsIgnoreCase(valorReferencia.toString());
      }
      return false;
    } catch (NumberFormatException e) {
      log.error("Error al convertir los valores a Double: {}", e.getMessage());
      return false;
    }
  }
}
