
package com.hackerrank.sample.service.impl;

import com.hackerrank.sample.exception.BadResourceRequestException;
import com.hackerrank.sample.model.Characteristic;
import com.hackerrank.sample.model.Item;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class FilterServiceImplTest {

  @Autowired
  private FilterServiceImpl filterService;

  @Test
  public void testApplyFilter() {

    Item item = new Item();
    item.setId("MLA415");
    item.setName("Smart TV Básico 32");
    item.setUrl("...");
    item.setDescription("Modelo compacto, ideal para cocinas o dormitorios.");
    item.setPrice(199.99);
    item.setRating(3.9);
    item.setCategory("Monitores");

    List<Characteristic> characteristics = Arrays.asList(
        new Characteristic("size", "32"),
        new Characteristic("resolution", "HD"),
        new Characteristic("technology", "LED"),
        new Characteristic("brand", "HISENSE")
    );
    item.setCharacteristics(characteristics);

    var result = filterService.getFilterOptions(List.of(item));
    Assert.assertEquals(4, result.size());
  }
  

  @Test
  public void shouldThrowBadResourceRequestExceptionWhenFilterKeyIsUnsupported() {
    Item item = new Item();
    item.setId("MLA415");
    item.setName("Smart TV Básico 32");
    item.setUrl("...");
    item.setDescription("Modelo compacto, ideal para cocinas o dormitorios.");
    item.setPrice(199.99);
    item.setRating(3.9);
    item.setCategory("Monitores");

    List<Characteristic> characteristics = Arrays.asList(
        new Characteristic("size", "32"),
        new Characteristic("resolution", "HD"),
        new Characteristic("technology", "LED"),
        new Characteristic("brand", "HISENSE")
    );
    item.setCharacteristics(characteristics);

    String unsupportedFilterStr = "unsupportedKey,equals,value";
    try {
      filterService.applyFilter(List.of(item), unsupportedFilterStr);
    } catch (BadResourceRequestException e) {
      Assert.assertEquals("Unsupported filter key unsupportedKey", e.getMessage());
    }
  }
  
  @Test
  public void shouldReturnFilteredItemsWhenApplyFilterIsCalled() {
    Item item = new Item();
    item.setId("MLA415");
    item.setName("Smart TV Básico 32");
    item.setUrl("...");
    item.setDescription("Modelo compacto, ideal para cocinas o dormitorios.");
    item.setPrice(199.99);
    item.setRating(3.9);
    item.setCategory("Monitores");

    List<Characteristic> characteristics = Arrays.asList(
        new Characteristic("size", "32"),
        new Characteristic("resolution", "HD"),
        new Characteristic("technology", "LED"),
        new Characteristic("brand", "HISENSE")
    );
    item.setCharacteristics(characteristics);

    String filterStr = "brand,EQUALS_TO,HISENSE;rating,GREATER_THAN,3.0;price,LESS_THAN,200.00";

    List<Item> result = filterService.applyFilter(List.of(item), filterStr);
    Assert.assertFalse(result.isEmpty());
  }

  @Test
  public void shouldReturnEmptyListWhenNoItemsMatchFilterCriteria() {
    Item item1 = new Item();
    item1.setId("MLA415");
    item1.setName("Smart TV Básico 32");
    item1.setUrl("...");
    item1.setDescription("Modelo compacto, ideal para cocinas o dormitorios.");
    item1.setPrice(199.99);
    item1.setRating(null);
    item1.setCategory("Monitores");

    List<Characteristic> characteristics1 = Arrays.asList(
        new Characteristic("size", "32"),
        new Characteristic("resolution", "HD"),
        new Characteristic("technology", "LED"),
        new Characteristic("brand", "HISENSE")
    );
    item1.setCharacteristics(characteristics1);

    String filterStr = "rating,EQUALS_TO,4.0;brand,EQUALS_TO,Samsung";
    List<Item> result = filterService.applyFilter(List.of(item1), filterStr);
    Assert.assertTrue(result.isEmpty());
  }

}
