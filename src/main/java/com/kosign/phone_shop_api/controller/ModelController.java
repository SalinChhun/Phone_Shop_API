package com.kosign.phone_shop_api.controller;


import com.kosign.phone_shop_api.dto.ModelDTO;
import com.kosign.phone_shop_api.entity.Model;
import com.kosign.phone_shop_api.payload.model.ModelRequest;
import com.kosign.phone_shop_api.service.ModelService;
import com.kosign.phone_shop_api.util.ModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/model/")
public class ModelController extends PhoneShopResController {

    @Autowired
    private ModelService modelService;

    @PostMapping("createNewModel")
    public ResponseEntity<?> saveProduct(@RequestBody ModelRequest modelRequest) throws Throwable {
//        Model model = modelService.createNewModel(modelRequest);
//        return ResponseEntity.ok(model);
        modelService.createNewModel(modelRequest);
        return ok();
    }

    @GetMapping("getAllModel")
    public ResponseEntity<?> getAllModel() {
        return ResponseEntity.ok(modelService.getAllModel());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getModelById(@PathVariable Integer id) {
        Model model = modelService.getModelById(id);
        return ResponseEntity.ok(model);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateModelById(@PathVariable Integer id, @RequestBody ModelDTO modelDTO) {
        Model model = ModelMapper.toModel(modelDTO);
        Model updatedModel = modelService.updateModelById(id, model);
        return ResponseEntity.ok(updatedModel);
    }

}
