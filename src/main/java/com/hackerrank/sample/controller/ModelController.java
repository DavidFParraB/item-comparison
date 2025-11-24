package com.hackerrank.sample.controller;

import com.hackerrank.sample.model.Model;
import com.hackerrank.sample.service.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.HashMap;
import java.util.List;
import jakarta.validation.Valid;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ModelController {

  @Autowired
  private ModelService modelService;

  @GetMapping("/")
  @ResponseBody
  @Operation(summary = "Home Page", description = "Returns the default home page message.")
  public String home() {
    return "Default Java 21 Project Home Page";
  }

  @PostMapping(value = "/model", consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create a new model", description = "Creates a new model with the provided details.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Model created successfully")
  })
  public void createNewModel(
      @RequestBody @Valid @Parameter(description = "Model object to be created", required = true,
          content = @Content(schema = @Schema(implementation = Model.class))) Model model) {
    modelService.createModel(model);
  }

  @DeleteMapping("/erase")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Delete all models", description = "Deletes all models from the database.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "All models deleted successfully")
  })
  public void deleteAllModels() {
    modelService.deleteAllModels();
  }

  @DeleteMapping("/model/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Delete model by ID", description = "Deletes a model by its ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Model deleted successfully"),
      @ApiResponse(responseCode = "404", description = "Model not found")
  })
  public void deleteModelById(
      @Parameter(description = "ID of the model to be deleted", required = true) @PathVariable String id) {
    modelService.deleteModelById(id);
  }

  @GetMapping("/model")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get all models", description = "Retrieves all models from the database.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of models",
          content = @Content(array = @ArraySchema(schema = @Schema(implementation = Model.class))))
  })
  public List<Model> getAllModels() {
    return modelService.getAllModels();
  }

  @GetMapping("/model/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get model by ID", description = "Retrieves a model by its ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Model found",
          content = @Content(schema = @Schema(implementation = Model.class))),
      @ApiResponse(responseCode = "404", description = "Model not found")
  })
  public Model getModelById(
      @Parameter(description = "ID of the model to be retrieved", required = true) @PathVariable String id) {
    return modelService.getModelById(id);
  }

  @GetMapping("/model/filter")
  @ResponseStatus(HttpStatus.OK)
  public List<Model> filterModels(@RequestParam String category, @RequestParam String filter) {
    return modelService.filterModels(category, filter);
  }

  @GetMapping("/model/filter-options")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get filter options", description = "Retrieves available filter options based on model characteristics.")
  public Map<String, Set<Object>> getFilterOptions(@RequestParam String category) {
    return modelService.getFilterOptions(category);
  }
}