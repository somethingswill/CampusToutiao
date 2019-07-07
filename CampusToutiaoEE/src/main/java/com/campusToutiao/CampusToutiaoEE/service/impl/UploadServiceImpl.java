package com.campusToutiao.CampusToutiaoEE.service.impl;

import com.campusToutiao.CampusToutiaoEE.service.IUploadService;
import com.campusToutiao.CampusToutiaoEE.utils.ToutiaoUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class UploadServiceImpl implements IUploadService {

    @Override
    public String uploadImage(MultipartFile file, String imageNamePrefix) throws IOException {
        // 找到 . 的位置
        int dotPos = file.getOriginalFilename().lastIndexOf(".");
        if (dotPos < 0) {
            return null;
        }
        // 图片扩展名
        String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();
        if (!ToutiaoUtils.isFileAllowed(fileExt)) {
            return null;
        }
        // 重命名图片
        String fileName = imageNamePrefix + ToutiaoUtils.uuidStr() + "." + fileExt;

        File imageFile = new File(ToutiaoUtils.UPLOADED_IMAGE_DIR + fileName);
        // 如果文件夹不存在先去创建文件夹，再创建文件
        if(!imageFile.exists()){
            //先得到文件的上级目录，并创建上级目录，在创建文件
            imageFile.getParentFile().mkdir();
            //创建文件
            imageFile.createNewFile();

        }
        Files.copy(file.getInputStream(), imageFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        // 图片访问路径  127.0.0.1:8080/image?name=xxx.jpg
        // return ToutiaoUtils.TOUTIAO_DOMAIN  + "image?name=" + fileName;
        return "/api/image?name=" + fileName;
    }

}
