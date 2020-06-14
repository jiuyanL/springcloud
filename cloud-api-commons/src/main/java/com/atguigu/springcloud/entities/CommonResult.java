package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//json封装体  CommonResult
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private  Integer code ;//状态编码 类似于404
    private  String message;
    private  T data;//结果集
    public  CommonResult(Integer id,String message){
        this(id,message,null);
    }
}
