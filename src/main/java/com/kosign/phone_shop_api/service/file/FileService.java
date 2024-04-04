package com.kosign.phone_shop_api.service.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public String upload(MultipartFile multipartFile);
}
