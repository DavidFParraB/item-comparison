package com.hackerrank.sample.service;

public interface FilterStrategy {

  /**
   * Define si esta estrategia puede manejar la CLAVE de especificación (ej. "Precio", "RAM").
   */
  boolean aplicaPara(String clave);

  /**
   * Evalúa si el valor del artículo cumple con la condición de filtrado.
   * @param valorArticulo El valor del campo del artículo (ej. 1200.00).
   * @param operador El tipo de comparación (ej. ">", "<", "=").
   * @param valorReferencia El valor contra el que se compara (ej. 500.00).
   * @return true si el artículo cumple la condición.
   */
  boolean evaluar(Object valorArticulo, String operador, Object valorReferencia);
}
