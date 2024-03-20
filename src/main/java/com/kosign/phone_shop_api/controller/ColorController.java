package com.kosign.phone_shop_api.controller;

import com.kosign.phone_shop_api.payload.color.ColorRequest;
import com.kosign.phone_shop_api.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/color/")
public class ColorController extends PhoneShopResController{

    private final ColorService colorService;

    @GetMapping("getAllColor")
    ResponseEntity<?> getAllColor() {
        return ok(colorService.getAllColor());
    }

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
