package com.hackerrank.sample.service.impl;

import com.hackerrank.sample.exception.BadResourceRequestException;
import com.hackerrank.sample.exception.NoSuchResourceFoundException;
import com.hackerrank.sample.model.Item;
import com.hackerrank.sample.repository.ItemRepository;
import com.hackerrank.sample.service.FilterService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceImplTest {

  @Mock
  private ItemRepository itemRepository;

  @Mock
  private FilterService filterService;

  @InjectMocks
  private ItemServiceImpl itemService;

  @Test
  public void shouldThrowNoSuchResourceFoundExceptionWhenDeleteItemByIdIsCalledWithNonExistentId() {
    String nonExistentId = "non-existent-id";
    Boolean result = Boolean.FALSE;
    when(itemRepository.findById(nonExistentId)).thenReturn(Optional.empty());
    try {
      itemService.deleteItemById(nonExistentId);
    } catch (NoSuchResourceFoundException e) {
      result = Boolean.TRUE;
    }
    Assert.assertTrue(result);
  }

  @Test
  public void shouldSuccessfullyDeleteItemWhenDeleteItemByIdIsCalledWithExistingId() {

    String existingId = "existing-id";
    Item item = new Item();
    item.setId(existingId);
    when(itemRepository.findById(existingId)).thenReturn(Optional.of(item));

    itemService.deleteItemById(existingId);

    verify(itemRepository).deleteById(existingId);
  }

  @Test
  public void shouldThrowBadResourceRequestExceptionWhenCreateItemIsCalledWithExistingId() {
    Boolean result = Boolean.FALSE;
    String existingId = "existing-id";
    Item existingItem = new Item();
    existingItem.setId(existingId);
    when(itemRepository.findById(existingId)).thenReturn(Optional.of(existingItem));

    try {
      itemService.createItem(existingItem);
    } catch (BadResourceRequestException e) {
      result = Boolean.TRUE;
    }
    Assert.assertTrue(result);
  }

  @Test
  public void shouldSuccessfullySaveNewItemWhenCreateItemIsCalledWithUniqueId() {
    String uniqueId = "unique-id";
    Item newItem = new Item();
    newItem.setId(uniqueId);
    when(itemRepository.findById(uniqueId)).thenReturn(Optional.empty());

    itemService.createItem(newItem);
    verify(itemRepository).save(newItem);
  }

  @Test
  public void shouldReturnAllItemsWhenGetAllItemsIsCalled() {
    Item item1 = new Item();
    Item item2 = new Item();
    List<Item> items = Arrays.asList(item1, item2);
    when(itemRepository.findAll()).thenReturn(items);
    List<Item> result = itemService.getAllItems();
    Assert.assertEquals(items, result);
  }

  @Test
  public void shouldReturnFilteredItemsWhenFilterItemsIsCalled() {
    // Arrange
    String category = "category-1";
    String filterStr = "filter-string";
    Item item1 = new Item();
    item1.setCategory(category);
    Item item2 = new Item();
    item2.setCategory("different-category");
    List<Item> items = Arrays.asList(item1, item2);
    when(itemRepository.findByCategory(category)).thenReturn(Arrays.asList(item1));
    when(filterService.applyFilter(any(), anyString())).thenReturn(Arrays.asList(item1));

    List<Item> result = itemService.filterItems(category, filterStr);

    Assert.assertEquals(Arrays.asList(item1), result);
  }

  @Test
  public void shouldReturnFilterOptionsWhenGetFilterOptionsIsCalled() {
    String category = "category-1";
    Item item1 = new Item();
    item1.setCategory(category);
    Item item2 = new Item();
    item2.setCategory("different-category");
    List<Item> items = Arrays.asList(item1, item2);
    when(itemRepository.findByCategory(category)).thenReturn(items);
    when(filterService.getFilterOptions(any())).thenReturn(Collections.emptyMap());

    Map<String, Set<Object>> result = itemService.getFilterOptions(category);

    Assert.assertEquals(Collections.emptyMap(), result);
  }

  @Test
  public void deleteAllItemsWhenDeleteAllItemsIsCalled() {
    itemService.deleteAllItems();
    verify(itemRepository).deleteAllInBatch();
  }

  @Test
  public void getItemByIdShouldReturnItemWhenIdExists() {
    String existingId = "existing-id";
    Item item = new Item();
    item.setId(existingId);
    when(itemRepository.findById(existingId)).thenReturn(Optional.of(item));

    Item result = itemService.getItemById(existingId);

    Assert.assertEquals(item, result);
  }

  @Test
  public void getItemByIdShouldReturnNullWhenIdDoesNotExist() {
    Boolean result = Boolean.FALSE;
    String nonExistentId = "non-existent-id";
    when(itemRepository.findById(nonExistentId)).thenReturn(Optional.empty());
    try {
    Item itemResult = itemService.getItemById(nonExistentId);
    } catch (NoSuchResourceFoundException e) {
      result = Boolean.TRUE;
    }
    Assert.assertTrue(result);
  }
}
