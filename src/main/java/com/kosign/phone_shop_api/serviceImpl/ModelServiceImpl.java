package com.kosign.phone_shop_api.serviceImpl;

import com.kosign.phone_shop_api.entity.Brand;
import com.kosign.phone_shop_api.entity.Model;
import com.kosign.phone_shop_api.exception.EntityNotFoundException;
import com.kosign.phone_shop_api.payload.model.ModelRequest;
import com.kosign.phone_shop_api.payload.model.ModelResponse;
import com.kosign.phone_shop_api.repository.ModelRepository;
import com.kosign.phone_shop_api.service.BrandService;
import com.kosign.phone_shop_api.service.ModelService;
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
