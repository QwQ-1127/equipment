package com.sy.equipmentserver.dao;

import com.sy.equipmentserver.pojo.TbUser;
import java.util.List;

public interface TbUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbUser record);

    TbUser selectByPrimaryKey(Integer id);

    List<TbUser> selectAll();

    int updateByPrimaryKey(TbUser record);

    TbUser getUserByPhone(String userphone);

    TbUser login(TbUser user);

    int change(Integer id, String password);
}