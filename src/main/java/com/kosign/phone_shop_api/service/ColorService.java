package com.kosign.phone_shop_api.service;

import com.kosign.phone_shop_api.entity.Color;
import com.kosign.phone_shop_api.payload.color.ColorRequest;

import java.util.List;

public interface ColorService {
    void createNewColor(ColorRequest color);

    Color getColorById(Integer id);

    List<Color> getAllColor();
}
