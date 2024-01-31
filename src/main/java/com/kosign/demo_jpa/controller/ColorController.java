package com.kosign.demo_jpa.controller;

import com.kosign.demo_jpa.entity.Color;
import com.kosign.demo_jpa.payload.color.ColorRequest;
import com.kosign.demo_jpa.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/color/")
public class ColorController extends PhoneShopResController{

    private final ColorService colorService;
    @PostMapping("createNewColor")
    ResponseEntity<?> createNewColor(@RequestBody ColorRequest colorRequest) {
        colorService.createNewColor(colorRequest);
        return ok();
    }

    @GetMapping("{id}")
    ResponseEntity<?> getColorById(@PathVariable Integer id) {
        return ResponseEntity.ok(colorService.getColorById(id));
    }
}
