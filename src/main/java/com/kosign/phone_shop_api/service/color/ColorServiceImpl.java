package com.kosign.phone_shop_api.service.color;

import com.kosign.phone_shop_api.entity.color.Color;
import com.kosign.phone_shop_api.exception.EntityNotFoundException;
import com.kosign.phone_shop_api.payload.color.ColorRequest;
import com.kosign.phone_shop_api.entity.color.ColorRepository;
import com.kosign.phone_shop_api.service.color.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Color> getAllColor() {
        return colorRepository.findAll();
    }
}
