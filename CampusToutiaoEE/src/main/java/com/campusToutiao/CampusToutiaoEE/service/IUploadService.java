package com.campusToutiao.CampusToutiaoEE.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUploadService {

    String uploadImage(MultipartFile file, String imageNamePrefix) throws IOException;

}
