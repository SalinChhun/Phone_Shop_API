package com.kosign.phone_shop_api.serviceImpl;

import com.kosign.phone_shop_api.entity.Color;
import com.kosign.phone_shop_api.exception.EntityNotFoundException;
import com.kosign.phone_shop_api.payload.color.ColorRequest;
import com.kosign.phone_shop_api.repository.ColorRepository;
import com.kosign.phone_shop_api.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;


    @Override
    public void createNewColor(ColorRequest colorRequest) {
        Color color = Color.builder()
                .colorName(colorRequest.getColorName())
                .build();
        colorRepository.save(color);
    }

    @Override
    public Color getColorById(Integer id) {
        return colorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Color.class, "id", id.toString()));
    }
}
