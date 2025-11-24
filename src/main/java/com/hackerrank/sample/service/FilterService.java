package com.hackerrank.sample.service;

import com.hackerrank.sample.controller.request.Filter;
import com.hackerrank.sample.model.Characteristic;
import com.hackerrank.sample.model.Model;
import com.hackerrank.sample.model.util.FilterParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FilterService {

  private final List<FilterStrategy> strategiesList;

  public List<Model> applyFilter(List<Model> articulos, String filterStr) {

    List<Filter> constraints = FilterParser.parseFilterString(filterStr);
    List<Model> resultFiltered = new ArrayList<>(articulos);
    for (Filter filter : constraints) {
      FilterStrategy strategy = strategiesList.stream()
          .filter(s -> s.aplicaPara(filter.getKey()))
          .findFirst()
          .orElseThrow(() -> new IllegalArgumentException(
              "Clave de filtro no soportada: " + filter.getKey()));

      resultFiltered = articulos.stream()
          .filter(articulo -> {
            Object valorArticulo = extraerValor(articulo, filter.getKey());

            if (valorArticulo == null) {
              return false;
            }

            return strategy.evaluar(
                valorArticulo,
                filter.getOperator(),
                filter.getValue()
            );
          })
          .collect(Collectors.toList());
    }
    return resultFiltered;
  }

  private Object extraerValor(Model articulo, String clave) {
    return switch (clave) {
      case "price" -> articulo.getPrice();
      case "rating" -> articulo.getRating();
      default -> articulo.getCharacteristics().stream()
          .filter(c -> c.getClave().equalsIgnoreCase(clave)).findFirst()
          .map(Characteristic::getValor)
          .orElse(null);
    };
  }

  public Map<String, Set<Object>> getFilterOptions(List<Model> models) {

    Map<String, Set<Object>> filterOptions = new HashMap<>();

    for (Model model : models) {
      for (Characteristic characteristic : model.getCharacteristics()) {
        filterOptions
            .computeIfAbsent(characteristic.getClave(), k -> new HashSet<>())
            .add(characteristic.getValor());
      }
    }

    return filterOptions;
  }
}
