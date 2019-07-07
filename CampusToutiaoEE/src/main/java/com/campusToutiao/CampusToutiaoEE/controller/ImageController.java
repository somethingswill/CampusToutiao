package com.campusToutiao.CampusToutiaoEE.controller;

import com.campusToutiao.CampusToutiaoEE.utils.ToutiaoUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

@RestController
public class ImageController {

    /**
     * 访问图片
     *
     * @param imageName
     * @param response
     */
    @GetMapping("/image")
    public void getImage(@RequestParam("name") String imageName,
                         HttpServletResponse response) {
        try {
            // 告知浏览器写回的数据格式是图片
            response.setContentType("image/jpeg");
            // 以字节流的形式写回给浏览器
            StreamUtils.copy(new FileInputStream(new File(ToutiaoUtils.UPLOADED_IMAGE_DIR + imageName)), response.getOutputStream());
        } catch (Exception e) {
            System.out.println("读取图片错误" + imageName + e.getMessage());
        }
    }
}
