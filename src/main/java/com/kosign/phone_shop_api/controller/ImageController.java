package com.kosign.phone_shop_api.controller;

import com.kosign.phone_shop_api.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ImageController extends PhoneShopResController{

    private final FileService fileService;

    @PostMapping("/image")
    public ResponseEntity<?> upload(@RequestParam("file") List<MultipartFile> multipartFile) {
        List<String> fileUrl = new ArrayList<>();
        for (MultipartFile file: multipartFile) {
            fileService.upload(file);
            fileUrl.add(fileService.upload(file));
        }
        return ok(fileUrl);
    }
}
