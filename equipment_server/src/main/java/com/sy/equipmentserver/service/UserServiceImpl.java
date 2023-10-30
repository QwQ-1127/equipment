package com.sy.equipmentserver.service;

import com.sy.equipmentserver.dao.TbUserMapper;
import com.sy.equipmentserver.pojo.TbEquipment;
import com.sy.equipmentserver.pojo.TbUser;
import com.sy.equipmentserver.vo.ResultVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private TbUserMapper userMapper;

    @Override
    public ResultVo regist(TbUser user) {
        user.setStatus("用户");
        //先根据手机号查询是否存在
        TbUser selectUser = userMapper.getUserByPhone(user.getUserphone());
        if(selectUser!=null){
            return ResultVo.errorVo(500,"手机号已存在");
        }else{
            //插入数据库
            userMapper.insert(user);
            return ResultVo.successVo();
        }
    }

    @Override
    public ResultVo login(TbUser user) {
        TbUser selectUser = userMapper.login(user);
        if(selectUser!=null){
            return ResultVo.successVo(selectUser);
        }else{
            return ResultVo.errorVo(501,"账号或密码输入错误");
        }

    }

    @Override
    public ResultVo show(TbUser user) {
        TbUser selectUser = userMapper.getUserByPhone(user.getUserphone());
        return ResultVo.successVo(selectUser);
    }

    @Override
    public ResultVo change(TbUser user) {
        userMapper.change(user.getId(),user.getPassword());
        return ResultVo.successVo();
    }


}
