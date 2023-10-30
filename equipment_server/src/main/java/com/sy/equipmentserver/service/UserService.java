package com.sy.equipmentserver.service;

import com.sy.equipmentserver.pojo.TbUser;
import com.sy.equipmentserver.vo.ResultVo;

public interface UserService {
    ResultVo regist(TbUser user);

    ResultVo login(TbUser user);

    ResultVo show(TbUser user);

    ResultVo change(TbUser user);
}
