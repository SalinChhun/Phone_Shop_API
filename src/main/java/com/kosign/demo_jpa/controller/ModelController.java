package com.kosign.demo_jpa.controller;


import com.kosign.demo_jpa.dto.ModelDTO;
import com.kosign.demo_jpa.entity.Model;
import com.kosign.demo_jpa.payload.model.ModelRequest;
import com.kosign.demo_jpa.payload.model.ModelResponse;
import com.kosign.demo_jpa.service.ModelService;
import com.kosign.demo_jpa.util.ModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.servlet.function.ServerResponse.ok;

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
