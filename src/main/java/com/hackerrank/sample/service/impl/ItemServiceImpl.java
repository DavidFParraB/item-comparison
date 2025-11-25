package com.hackerrank.sample.service.impl;

import com.hackerrank.sample.exception.BadResourceRequestException;
import com.hackerrank.sample.exception.NoSuchResourceFoundException;
import com.hackerrank.sample.model.Item;
import com.hackerrank.sample.repository.ItemRepository;
import com.hackerrank.sample.service.FilterService;
import com.hackerrank.sample.service.ItemService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service("ItemService")
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

  private final ItemRepository itemRepository;
  private final FilterService filterService;

  @Override
  public void deleteAllItems() {
    itemRepository.deleteAllInBatch();
  }

  @Override
  public void deleteItemById(String id) {
    Optional<Item> existingItem = itemRepository.findById(id);

    if (existingItem.isEmpty()) {
      throw new NoSuchResourceFoundException("No Item with given id found.");
    }

    itemRepository.deleteById(id);
  }

  @Override
  public void createItem(Item item) {
    Optional<Item> existingItem = itemRepository.findById(item.getId());

    if (existingItem.isPresent()) {
      throw new BadResourceRequestException("Item with same id exists.");
    }

    itemRepository.save(item);
  }

  @Override
  public Item getItemById(String id) {
    Optional<Item> Item = itemRepository.findById(id);

    if (Item.isEmpty()) {
      throw new NoSuchResourceFoundException("No Item with given id found.");
    }

    return Item.get();
  }

  @Override
  public List<Item> getAllItems() {
    return itemRepository.findAll();
  }

  @Override
  public List<Item> filterItems(String category, String filterStr) {
    List<Item> filteredItems = itemRepository.findByCategory(category);
    return filterService.applyFilter(filteredItems, filterStr);
  }

  /**
   * Retrieves filter options for items within a specified category.
   *
   * @param category the category of items for which to retrieve filter options.
   * @return a map where the keys are filter criteria and the values are sets of possible values for
   * each criterion.
   */
  @Override
  public Map<String, Set<Object>> getFilterOptions(String category) {
    List<Item> items = itemRepository.findByCategory(category);
    return filterService.getFilterOptions(items);
  }
}
