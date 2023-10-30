package com.sy.equipmentserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@MapperScan(basePackages = "com.sy.equipmentserver.dao")
public class EquipmentServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EquipmentServerApplication.class, args);
    }

}
