package com.hackerrank.sample.model.util;


import com.hackerrank.sample.exception.BadResourceRequestException;
import com.hackerrank.sample.model.Filter;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class FilterParser {

  /**
   * Parses a string representation of filters into a list of {@link Filter} objects.
   *
   * @param filterString the string containing filters, where each filter is separated by a semicolon
   *                     and each filter consists of a key, operator, and value separated by commas.
   * @return a list of {@link Filter} objects parsed from the input string.
   * @throws BadResourceRequestException if the filter string format is incorrect.
   */
  public static List<Filter> parseFilterString(String filterString) {
    List<Filter> filterRequests = new ArrayList<>();

    try {
      String[] filters = filterString.split(";");
      for (String filter : filters) {
        String[] parts = filter.split(",");
        if (parts.length == 3) {
          String key = parts[0];
          String operator = parts[1];
          String value = parts[2];
          Filter filterRequest = Filter.builder()
              .key(key).operator(operator).value(value)
              .build();
          filterRequests.add(filterRequest);
        }
      }
    } catch (IndexOutOfBoundsException e) {
      log.error("Error parsing filter: {}", e.getMessage());
        throw new BadResourceRequestException("Incorrect filter format. It should be: key, operator, value");
    }

    return filterRequests;
  }
}
