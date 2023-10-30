package com.sy.equipmentserver.service;

import com.sy.equipmentserver.dao.TbEquipmentMapper;
import com.sy.equipmentserver.pojo.TbEquipment;
import com.sy.equipmentserver.vo.DeleteEquipmentVo;
import com.sy.equipmentserver.vo.ResultVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Resource
    private TbEquipmentMapper equipmentMapper;

    @Override
    public ResultVo listAll() {
        List<TbEquipment> equipmentList = equipmentMapper.selectAll();
        return ResultVo.successVo(equipmentList);
    }

    @Override
    public ResultVo listAllByPage(Integer pageSize, Integer currentPage) {
        //计算每页查询的起始位置
        int start = (currentPage-1)*pageSize;
        //根据页码分页查询数据
        List<TbEquipment> hospitalList = equipmentMapper.listAllByPage(start,pageSize);
        return ResultVo.successVo(hospitalList);
    }

    @Override
    public ResultVo add(TbEquipment equipment) {
        equipmentMapper.insert(equipment);
        return ResultVo.successVo();
    }

    @Override
    public ResultVo deleteBatch(DeleteEquipmentVo deleteHospitalVo) {
        equipmentMapper.deleteBatch(deleteHospitalVo.getIds());
        return ResultVo.successVo();
    }


}
