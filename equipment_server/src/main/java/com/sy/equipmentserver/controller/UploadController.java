package com.sy.equipmentserver.controller;

import com.sy.equipmentserver.vo.ResultVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin
public class UploadController {

    //获取配置文件中配置的路径
    @Value("${custom.fileDir}")
    private String fileDir;

    /**
     * MultipartFile:接收前端传递的文件
     * upload:跟前端传递的参数名保持一致
     *
     * 文件上传：1. fastdfs【分布式文件上传】
     *          2. 第三方
     *          3. 云服务器创建存储资源的文件夹，然后上传【小型项目】
     */
    @RequestMapping("/upload")
    public ResultVo upload(MultipartFile upload) throws IOException {
        //原始的文件名称
        String originalFilename = upload.getOriginalFilename();
        //生成一个不会重复的文件名
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String fileName = sdf.format(new Date())+getSuffix(originalFilename);
        //先判断存储图片的文件夹是否存在
        File file = new File(fileDir);
        if(!file.exists()){
            file.mkdirs();
        }
        upload.transferTo(new File(fileDir+fileName));
        return ResultVo.successVo("img/"+fileName);
    }
    // xx.jpg
    public static String getSuffix(String originalFilename){
        //取最后一个 . 的下标
        int index = originalFilename.lastIndexOf(".");
        //substring(index):从index开始截取到末尾
        return originalFilename.substring(index);
    }
}
