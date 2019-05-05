package com.hb.springboot.service.impl;

import com.hb.springboot.annotation.Log;
import com.hb.springboot.enums.OperateType;
import com.hb.springboot.enums.OperateUnit;
import com.hb.springboot.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Log(detail = "通过手机号[{{tel}}]获取用户名",level = 3,operateUnit = OperateUnit.USER,operateType = OperateType.SELECT)
    @Override
    public String findUserName(String tel) {
        System.out.println("tel:" + tel);
        return "ZhangSan";
    }
}
