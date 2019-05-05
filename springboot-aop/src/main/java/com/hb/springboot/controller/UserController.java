package com.hb.springboot.controller;

import com.hb.springboot.beans.OperationLog;
import com.hb.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/findUserNameByTel")
    public String findUserNameByTel(@RequestParam("tel") String tel){
        return userService.findUserName(tel);
    }

    @RequestMapping("/search")
    public OperationLog search(){

        OperationLog operationLog = new OperationLog();
        //operationLog.setRunTime(time);
        //operationLog.setReturnValue(JSON.toJSONString(obj));
        operationLog.setId(UUID.randomUUID().toString());
        //operationLog.setArgs(JSON.toJSONString(jp.getArgs()));
        operationLog.setCreateTime(new Date());
        //operationLog.setMethod(signature.getDeclaringTypeName() + "." + signature.getName());
        operationLog.setUserId("#{currentUserId}");
        operationLog.setUserName("#{currentUserName}");

        return operationLog;
    }
}
