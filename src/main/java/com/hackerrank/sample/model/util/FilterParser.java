package com.hackerrank.sample.model.util;


import com.hackerrank.sample.model.Filter;
import java.util.ArrayList;
import java.util.List;

public class FilterParser {

  public static List<Filter> parseFilterString(String filterString) {
    List<Filter> filterRequests = new ArrayList<>();

    String[] filters = filterString.split(";");
    for (String filter : filters) {
      String[] parts = filter.split(",");
      if (parts.length == 3) {
        String key = parts[0];
        String operator = parts[1];
        String value = parts[2];
        Filter filterRequest = new Filter(key,operator, value);
        filterRequests.add(filterRequest);
      }
    }

    return filterRequests;
  }
}
