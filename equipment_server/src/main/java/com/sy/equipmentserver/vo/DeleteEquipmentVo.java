package com.sy.equipmentserver.vo;

import lombok.Data;

/**
 * 接收批量删除医院的id数组
 */
@Data
public class DeleteEquipmentVo {
    private Integer[] ids;
}
