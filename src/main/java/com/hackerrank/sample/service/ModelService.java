package com.hackerrank.sample.service;

import com.hackerrank.sample.model.Model;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ModelService {
    void deleteAllModels();
    void deleteModelById(String id);

    void createModel(Model model);

    Model getModelById(String id);

    List<Model> getAllModels();

    List<Model> filterModels(String category, String filterStr);

    Map<String, Set<Object>> getFilterOptions(String category);
}
