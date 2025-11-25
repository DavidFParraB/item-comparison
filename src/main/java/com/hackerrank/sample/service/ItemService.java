
package com.hackerrank.sample.service;

import com.hackerrank.sample.model.Item;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Service for managing items in the database.
 */
public interface ItemService {

  /**
   * Removes all items from the database.
   */
  void deleteAllItems();

  /**
   * Removes the item with the given ID from the database.
   * @param id the ID of the item to be deleted
   */
  void deleteItemById(String id);

  /**
   * Creates a new item in the database.
   * @param item the item to be created
   */
  void createItem(Item item);

  /**
   * Retrieves an item by its ID.
   * @param id the ID of the item to retrieve
   * @return the item with the given ID, or null if it does not exist
   */
  Item getItemById(String id);

  /**
   * Retrieves all items from the database.
   * @return a list of all items in the database
   */
  List<Item> getAllItems();

  /**
   * Filters items based on a given category and filter criteria.
   *
   * @param category the category of items to filter
  */
  List<Item> filterItems(String category, String filterStr);

  /**
   * Retrieves filter options for a given category.
   *
   * @param category the category for which to retrieve filter options
   * @return a map where the key is a filter type and the value is a set of possible filter values
   */
  Map<String, Set<Object>> getFilterOptions(String category);
}
