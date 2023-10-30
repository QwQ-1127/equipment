package com.sy.equipmentserver.vo;

import lombok.Data;

@Data
public class ResultVo {

    private int code = 200;
    private String msg = "success";
    private Object data;

    private ResultVo(){}
    public static ResultVo successVo(Object data){
        ResultVo resultVo = new ResultVo();
        resultVo.setData(data);
        return resultVo;
    }
    public static ResultVo successVo(){
        return new ResultVo();
    }

    public static ResultVo errorVo(int code,String msg){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }
}
