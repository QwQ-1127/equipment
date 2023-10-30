package com.sy.equipmentserver.controller;

import com.sy.equipmentserver.pojo.TbEquipment;
import com.sy.equipmentserver.service.EquipmentService;
import com.sy.equipmentserver.vo.DeleteEquipmentVo;
import com.sy.equipmentserver.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController//@Controller+@ResponseBody
@RequestMapping("/equipment")
@CrossOrigin
public class EquipmentController {
    @Resource
    private EquipmentService equipmentService;

    @RequestMapping("/listAll")
    public ResultVo listAll(){
        return equipmentService.listAll();
    }

    @RequestMapping("/add")
    public ResultVo add(@RequestBody TbEquipment equipment){
        return equipmentService.add(equipment);
    }

    @RequestMapping("/deleteBatch")
    public ResultVo deleteBatch(@RequestBody DeleteEquipmentVo deleteHospitalVo){
        return equipmentService.deleteBatch(deleteHospitalVo);
    }

    @RequestMapping("/listAllByPage/{pageSize}/{currentPage}")
    public ResultVo listAllByPage(@PathVariable("pageSize") Integer pageSize,
                                  @PathVariable("currentPage") Integer currentPage){
        return equipmentService.listAllByPage(pageSize,currentPage);
    }

}
