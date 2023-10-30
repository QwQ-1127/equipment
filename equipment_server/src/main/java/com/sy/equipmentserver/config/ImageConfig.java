package com.sy.equipmentserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 存放在磁盘的静态资源不在服务器上，不能直接被前端访问，
 * 所以我们可以通过配置 虚拟的地址映射到真实的文件存在位置的地址
 */
@Configuration
public class ImageConfig implements WebMvcConfigurer {

    /**
     * 文件真实存在位置
     */
    @Value("${custom.fileDir}")
    private String fileDir;
    /**
     * 虚拟映射路径
     */
    @Value("${custom.imgPath}")
    private String imgPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //虚拟路径
        registry.addResourceHandler(imgPath)
                //真实位置  file:D:/upload_img/
                .addResourceLocations(ResourceUtils.FILE_URL_PREFIX+fileDir);
    }

}
