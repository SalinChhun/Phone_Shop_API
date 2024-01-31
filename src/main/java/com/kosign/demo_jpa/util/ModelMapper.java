package com.kosign.demo_jpa.util;

import com.kosign.demo_jpa.Response.Model.ModelRes;
import com.kosign.demo_jpa.dto.ModelDTO;
import com.kosign.demo_jpa.entity.Model;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

    public static Model toModel(ModelDTO modelDTO) {
        Model model = new Model();
        model.setModelName(modelDTO.getModelName());
        return model;
    }

    public static Model modelRes(ModelRes modelRes) {
        Model model = new Model();
        model.setId(modelRes.getId());
        model.setModelName(model.getModelName());
        return model;
    }
}
