package com.campusToutiao.CampusToutiaoEE.utils;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 通用工具类
 */
public class ToutiaoUtils {

    // ticket 前缀
    public static String USER_TICKET_PREFIX = "TOUTIAO_USER_TICKET:";

    // news的like前缀
    public static String NEWS_LIKE_PREFIX = "TOUTIAO_NEWS_LIKE:";
    // news的dislike前缀
    public static String NEWS_DISLIKE_PREFIX = "TOUTIAO_NEWS_DISLIKE:";
    // comment的like前缀
    public static String COMMENT_LIKE_PREFIX = "TOUTIAO_COMMENT_LIKE:";
    // comment的dislike前缀
    public static String COMMENT_DISLIKE_PREFIX = "TOUTIAO_COMMENT_DISLIKE:";


    // 服务器地址
    public static String TOUTIAO_DOMAIN = "127.0.0.1:8080/";
    // 图片上传路径
    public static String UPLOADED_IMAGE_DIR = "D:/upload/";
    // 合法的图片扩展名
    public static String[] IMAGE_FILE_EXTD = new String[]{"png", "bmp", "jpg", "jpeg"};

    public static boolean isFileAllowed(String fileName) {
        for (String ext : IMAGE_FILE_EXTD) {
            if (ext.equals(fileName)) {
                return true;
            }
        }
        return false;
    }

    // md5加密
    public static String MD5(String key) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    // 32位uuid字符串
    public static String uuidStr() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    // 实体对象转成Map
    public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
