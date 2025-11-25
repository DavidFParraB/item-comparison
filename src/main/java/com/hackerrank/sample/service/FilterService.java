package com.hackerrank.sample.service;

import com.hackerrank.sample.model.Item;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Provides filtering capabilities for a list of items.
 */
public interface FilterService {

  /**
   * Applies a specified filter to a list of items.
   *
   * @param items     the list of items to be filtered
   * @param filterStr the filter criteria as a string
   * @return a list of items that match the filter criteria
   */
  List<Item> applyFilter(List<Item> items, String filterStr);

  /**
   * Retrieves the available filter options from a list of items.
   *
   * @param items the list of items to extract filter options from
   * @return a map where the key is the filter category and the value is a set of possible filter
   * values
   */
  Map<String, Set<Object>> getFilterOptions(List<Item> items);
}
