package com.sy.equipmentserver.service;

import com.sy.equipmentserver.pojo.TbEquipment;
import com.sy.equipmentserver.vo.DeleteEquipmentVo;
import com.sy.equipmentserver.vo.ResultVo;

public interface EquipmentService {
    ResultVo listAll();

    ResultVo listAllByPage(Integer pageSize, Integer currentPage);

    ResultVo add(TbEquipment equipment);

    ResultVo deleteBatch(DeleteEquipmentVo deleteHospitalVo);
}
