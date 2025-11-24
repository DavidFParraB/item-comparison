package com.hackerrank.sample.service.impl;

import com.hackerrank.sample.exception.BadResourceRequestException;
import com.hackerrank.sample.exception.NoSuchResourceFoundException;
import com.hackerrank.sample.model.Model;
import com.hackerrank.sample.repository.ModelRepository;
import com.hackerrank.sample.service.FilterService;
import com.hackerrank.sample.service.ModelService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("modelService")
public class ModelServiceImpl implements ModelService {
    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private FilterService filterService;

    @Override
    public void deleteAllModels() {
        modelRepository.deleteAllInBatch();
    }

    @Override
    public void deleteModelById(String id) {
        modelRepository.deleteById(id);
    }

    @Override
    public void createModel(Model model) {
        Optional<Model> existingModel = modelRepository.findById(model.getId());

        if (existingModel.isPresent()) {
            throw new BadResourceRequestException("Model with same id exists.");
        }

        modelRepository.save(model);
    }

    @Override
    public Model getModelById(String id) {
        Optional<Model> model = modelRepository.findById(id);

        if (model.isEmpty()) {
            throw new NoSuchResourceFoundException("No model with given id found.");
        }

        return model.get();
    }

    @Override
    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    @Override
    public List<Model> filterModels(String category, String filterStr) {
        List<Model> filteredModels = modelRepository.findByCategory(category);
        return filterService.applyFilter(filteredModels, filterStr);
    }

    @Override
    public Map<String, Set<Object>> getFilterOptions(String category) {
      List<Model> models = modelRepository.findByCategory(category);
      return filterService.getFilterOptions(models);
    }
}
