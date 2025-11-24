package com.hackerrank.sample.controller;


import com.hackerrank.sample.model.Item;
import com.hackerrank.sample.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import jakarta.validation.Valid;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ItemController {

  private ItemService itemService;

  @GetMapping("/")
  @ResponseBody
  @Operation(summary = "Home Page", description = "Returns the default home page message.")
  public String home() {
    return "Default Java 21 Project Home Page";
  }

  @PostMapping(value = "/item", consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create a new item", description = "Creates a new item with the provided details.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Item created successfully")
  })
  public void createNewItem(
      @RequestBody @Valid @Parameter(description = "Item object to be created", required = true,
          content = @Content(schema = @Schema(implementation = Item.class))) Item item) {
    itemService.createItem(item);
  }

  @DeleteMapping("/erase")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Delete all Items", description = "Deletes all Items from the database.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "All Items deleted successfully")
  })
  public void deleteAllItems() {
    itemService.deleteAllItems();
  }

  @DeleteMapping("/item/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Delete Item by ID", description = "Deletes a Item by its ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Item deleted successfully"),
      @ApiResponse(responseCode = "404", description = "Item not found")
  })
  public void deleteItemById(
      @Parameter(description = "ID of the Item to be deleted", required = true) @PathVariable String id) {
    itemService.deleteItemById(id);
  }

  @GetMapping("/item")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get all Items", description = "Retrieves all Items from the database.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of Items",
          content = @Content(array = @ArraySchema(schema = @Schema(implementation = Item.class))))
  })
  public List<Item> getAllItems() {
    return itemService.getAllItems();
  }

  @GetMapping("/item/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get Item by ID", description = "Retrieves a Item by its ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Item found",
          content = @Content(schema = @Schema(implementation = Item.class))),
      @ApiResponse(responseCode = "404", description = "Item not found")
  })
  public Item getItemById(
      @Parameter(description = "ID of the Item to be retrieved", required = true) @PathVariable String id) {
    return itemService.getItemById(id);
  }

  @GetMapping("/item/filter")
  @ResponseStatus(HttpStatus.OK)
  public List<Item> filterItems(@RequestParam String category, @RequestParam String criteria) {
    return itemService.filterItems(category, criteria);
  }

  @GetMapping("/item/filter-options")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get filter options", description = "Retrieves available filter options based on Item characteristics.")
  public Map<String, Set<Object>> getFilterOptions(@RequestParam String category) {
    return itemService.getFilterOptions(category);
  }
}