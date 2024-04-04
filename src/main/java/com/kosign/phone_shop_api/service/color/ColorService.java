package com.kosign.phone_shop_api.service.color;

import com.kosign.phone_shop_api.entity.color.Color;
import com.kosign.phone_shop_api.payload.color.ColorRequest;

import java.util.List;

public interface ColorService {
    void createNewColor(ColorRequest color);

    Color getColorById(Integer id);

    List<Color> getAllColor();
}
