package com.kosign.demo_jpa.serviceImpl;

import com.kosign.demo_jpa.entity.Color;
import com.kosign.demo_jpa.exception.EntityNotFoundException;
import com.kosign.demo_jpa.payload.color.ColorRequest;
import com.kosign.demo_jpa.repository.ColorRepository;
import com.kosign.demo_jpa.service.ColorService;
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
