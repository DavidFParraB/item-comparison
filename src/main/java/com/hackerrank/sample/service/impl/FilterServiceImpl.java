package com.hackerrank.sample.service.impl;

import com.hackerrank.sample.exception.BadResourceRequestException;
import com.hackerrank.sample.model.Filter;
import com.hackerrank.sample.model.Characteristic;
import com.hackerrank.sample.model.Item;
import com.hackerrank.sample.model.util.FilterParser;
import com.hackerrank.sample.service.FilterService;
import com.hackerrank.sample.service.FilterStrategy;
import com.hackerrank.sample.util.Constants;
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
public class FilterServiceImpl implements FilterService {

  private final List<FilterStrategy> strategiesList;

  @Override
  public List<Item> applyFilter(List<Item> items, String filterStr) {

    List<Filter> constraints = FilterParser.parseFilterString(filterStr);
    List<Item> resultFiltered = new ArrayList<>(items);
    for (Filter filter : constraints) {
      FilterStrategy strategy = strategiesList.stream()
          .filter(s -> s.applyBy(filter.getKey()))
          .findFirst()
          .orElseThrow(() -> new BadResourceRequestException(
              "Unsupported filter key " + filter.getKey()));

      resultFiltered = items.stream()
          .filter(item -> {
            Object itemValue = extractValue(item, filter.getKey());

            if (itemValue == null) {
              return false;
            }

            return strategy.validate(
                itemValue,
                filter.getOperator(),
                filter.getValue()
            );
          })
          .collect(Collectors.toList());
    }
    return resultFiltered;
  }

  private Object extractValue(Item item, String key) {
    return switch (key) {
      case Constants.PRICE -> item.getPrice();
      case Constants.RATING -> item.getRating();
      default -> item.getCharacteristics().stream()
          .filter(c -> c.getClave().equalsIgnoreCase(key)).findFirst()
          .map(Characteristic::getValor)
          .orElse(null);
    };
  }
  @Override
  public Map<String, Set<Object>> getFilterOptions(List<Item> items) {

    Map<String, Set<Object>> filterOptions = new HashMap<>();

    for (Item item : items) {
      for (Characteristic characteristic : item.getCharacteristics()) {
        filterOptions
            .computeIfAbsent(characteristic.getClave(), k -> new HashSet<>())
            .add(characteristic.getValor());
      }
    }

    return filterOptions;
  }
}
