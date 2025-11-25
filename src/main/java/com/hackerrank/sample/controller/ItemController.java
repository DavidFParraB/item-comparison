
package com.hackerrank.sample.controller;


import com.hackerrank.sample.dto.ErrorResponseDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ItemController {

  private final ItemService itemService;

  @PostMapping(value = "/item", consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create a new item", description = "Creates a new item with the provided details.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Item created successfully"),
      @ApiResponse(responseCode = "400", description = "Item with same id exists.",
          content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
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
      @ApiResponse(responseCode = "404", description = "Item not found",
          content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
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
  @Operation(summary = "Get Item by ID", description = "Retrieves an Item by its ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Item found",
          content = @Content(schema = @Schema(implementation = Item.class))),
      @ApiResponse(responseCode = "404", description = "Item not found",
          content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
  })
  public Item getItemById(
      @Parameter(description = "ID of the Item to be retrieved", required = true) @PathVariable String id) {
    return itemService.getItemById(id);
  }

  @GetMapping("/item/filter")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      summary = "Filter Items",
      description = "Filters items based on the specified category and criteria."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Filtered list of items",
          content = @Content(array = @ArraySchema(schema = @Schema(implementation = Item.class)))),
      @ApiResponse(responseCode = "400", description = "Invalid filter criteria",
          content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
  })
  public List<Item> filterItems(
      @RequestParam @Parameter(description = "Category to filter items by", required = true) String category,
      @RequestParam @Parameter(description = "Criteria to apply for filtering", required = true) String criteria) {
    return itemService.filterItems(category, criteria);
  }

  @GetMapping("/item/filter-options")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get filter options", description = "Retrieves available filter options based on Item characteristics.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "400", description = "Required request parameter",
          content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
  })
  public Map<String, Set<Object>> getFilterOptions(
      @RequestParam @Parameter(description = "Category to filter items by", required = true) String category) {
    return itemService.getFilterOptions(category);
  }
}
