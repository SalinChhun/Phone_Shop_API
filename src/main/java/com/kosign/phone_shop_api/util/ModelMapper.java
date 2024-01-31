package com.kosign.phone_shop_api.util;

import com.kosign.phone_shop_api.Response.Model.ModelRes;
import com.kosign.phone_shop_api.dto.ModelDTO;
import com.kosign.phone_shop_api.entity.Model;
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
