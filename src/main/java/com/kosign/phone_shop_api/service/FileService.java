package com.kosign.phone_shop_api.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public String upload(MultipartFile multipartFile);
}
