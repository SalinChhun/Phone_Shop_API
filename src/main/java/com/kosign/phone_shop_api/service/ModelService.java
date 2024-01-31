package com.kosign.phone_shop_api.service;

import com.kosign.phone_shop_api.entity.Model;
import com.kosign.phone_shop_api.payload.model.ModelRequest;
import com.kosign.phone_shop_api.payload.model.ModelResponse;

import java.util.List;

public interface ModelService {

    Model createNewModel(ModelRequest modelRequest) throws Throwable;

    List<ModelResponse> getAllModel();

    Model getModelById(Integer id);

    Model updateModelById(Integer id, Model model);

    List<Model> getAllModelByBrandId(Integer id);

}
