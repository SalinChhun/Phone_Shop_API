package com.kosign.demo_jpa.service;

import com.kosign.demo_jpa.entity.Model;
import com.kosign.demo_jpa.payload.model.ModelRequest;
import com.kosign.demo_jpa.payload.model.ModelResponse;

import java.util.List;

public interface ModelService {

    Model createNewModel(ModelRequest modelRequest) throws Throwable;

    List<ModelResponse> getAllModel();

    Model getModelById(Integer id);

    Model updateModelById(Integer id, Model model);

    List<Model> getAllModelByBrandId(Integer id);

}
