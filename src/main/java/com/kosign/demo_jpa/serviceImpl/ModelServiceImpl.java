package com.kosign.demo_jpa.serviceImpl;

import com.kosign.demo_jpa.entity.Brand;
import com.kosign.demo_jpa.entity.Model;
import com.kosign.demo_jpa.exception.EntityNotFoundException;
import com.kosign.demo_jpa.payload.model.ModelRequest;
import com.kosign.demo_jpa.payload.model.ModelResponse;
import com.kosign.demo_jpa.repository.ModelRepository;
import com.kosign.demo_jpa.service.BrandService;
import com.kosign.demo_jpa.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {


    private final ModelRepository modelRepository;
    private final BrandService brandService;

    @Override
    public Model createNewModel(ModelRequest modelRequest) throws Throwable {
        Brand brand = brandService.getBrandById(modelRequest.getBrandId());
        Model model = Model.builder()
                .modelName(modelRequest.getModelName())
                .brand(brand)
                .build();
        modelRepository.save(model);
        return model;
    }

    @Override
    public List<ModelResponse> getAllModel() {
        List<Model> models = modelRepository.findAll();
        return models.stream()
                .map(model -> ModelResponse.builder()
                        .id(model.getId())
                        .name(model.getModelName())
                        .build()).toList();
    }

    @Override
    public Model getModelById(Integer id) {
        return modelRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Model.class, "id", id.toString()));
    }

    @Override
    public Model updateModelById(Integer id, Model modelUpdate) {
        Model model = getModelById(id);
        model.setModelName(modelUpdate.getModelName());
        return modelRepository.save(model);
    }

    @Override
    public List<Model> getAllModelByBrandId(Integer id) {
        return modelRepository.findModelsByBrandId(id);
    }

}
