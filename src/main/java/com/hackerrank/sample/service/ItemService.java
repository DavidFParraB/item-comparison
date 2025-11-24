package com.hackerrank.sample.service;

import com.hackerrank.sample.model.Item;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ItemService {
    void deleteAllItems();

    void deleteItemById(String id);

    void createItem(Item item);

    Item getItemById(String id);

    List<Item> getAllItems();

    List<Item> filterItems(String category, String filterStr);

    Map<String, Set<Object>> getFilterOptions(String category);
}
