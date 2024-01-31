package com.kosign.demo_jpa.service;

import com.kosign.demo_jpa.entity.Color;
import com.kosign.demo_jpa.payload.color.ColorRequest;

public interface ColorService {
    void createNewColor(ColorRequest color);

    Color getColorById(Integer id);
}
