package com.hackerrank.sample.service;

import com.hackerrank.sample.model.Item;
import java.util.List;
import java.util.Map;
import java.util.Set;


public interface FilterService {

  List<Item> applyFilter(List<Item> items, String filterStr);

  Map<String, Set<Object>> getFilterOptions(List<Item> items) ;
}
