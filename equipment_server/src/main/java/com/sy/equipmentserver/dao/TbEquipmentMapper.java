package com.sy.equipmentserver.dao;

import com.sy.equipmentserver.pojo.TbEquipment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbEquipmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbEquipment record);

    TbEquipment selectByPrimaryKey(Integer id);

    List<TbEquipment> selectAll();

    int updateByPrimaryKey(TbEquipment record);

    List<TbEquipment> listAllByPage(@Param("start") int start, @Param("pageSize") Integer pageSize);

    void deleteBatch(@Param("ids") Integer[] ids);
}