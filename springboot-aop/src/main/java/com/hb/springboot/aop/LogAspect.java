package com.hb.springboot.aop;

import com.alibaba.fastjson.JSON;
import com.hb.springboot.annotation.Log;
import com.hb.springboot.beans.OperationLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Aspect
public class LogAspect {

    @Pointcut("@annotation(com.hb.springboot.annotation.Log)")
    public void operateLog() {
    }

    /**
     * 环绕增强，相当于MethodInterceptor
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("operateLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object obj = null;

        long time = System.currentTimeMillis();
        try {
            obj = pjp.proceed();
            time = System.currentTimeMillis() - time;
            return obj;
        } finally {
            try {
                //方法执行完成后增加日志
                addOperationLog(pjp, obj, time);
            } catch (Exception e) {
                System.out.println("LogAspect 操作失败: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void addOperationLog(JoinPoint jp, Object obj, long time) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        OperationLog operationLog = new OperationLog();
        operationLog.setRunTime(time);
        operationLog.setReturnValue(JSON.toJSONString(obj));
        operationLog.setId(UUID.randomUUID().toString());
        operationLog.setArgs(JSON.toJSONString(jp.getArgs()));
        operationLog.setCreateTime(new Date());
        operationLog.setMethod(signature.getDeclaringTypeName() + "." + signature.getName());
        operationLog.setUserId("#{currentUserId}");
        operationLog.setUserName("#{currentUserName}");

        Log log = signature.getMethod().getAnnotation(Log.class);
        if (log != null) {
            operationLog.setLevel(log.level());
            operationLog.setDescribe(getDetail(((MethodSignature) jp.getSignature()).getParameterNames(), jp.getArgs(), log));
            operationLog.setOperationType(log.operateType().getValue());
            operationLog.setOperationUnit(log.operateUnit().getValue());
        }
        //operateLogService.insert(operationLog);
        System.out.println("记录日志:" + operationLog.toString());
    }

    /**
     * 对当前登录用户和占位符处理
     *
     * @param argName 方法参数名称数组
     * @param args    方法参数数组
     * @param log     注解信息
     * @return 返回处理后的描述
     */
    private String getDetail(String[] argName, Object[] args, Log log) {
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < argName.length; i++) {
            map.put(argName[i], args[i]);
        }
        String detail = log.detail();
        try {
            detail = "'" + "#{currentUserName}" + "'=>" + log.detail();
            for (Map.Entry entry : map.entrySet()) {
                Object k = entry.getKey();
                Object v = entry.getValue();
                detail = detail.replace("{{" + k + "}}", JSON.toJSONString(v));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    @Before("operateLog()")
    public void doBeforeAdvice(JoinPoint jp) {
        System.out.println("进入方法前执行");
    }

    /**
     * 处理完请求，返回内容
     *
     * @param obj
     */
    @AfterReturning(returning = "obj", pointcut = "operateLog()")
    public void doAfterReturning(Object obj) {
        System.out.println("方法的返回值: " + obj);
    }

    /**
     * 后置异常通知
     *
     * @param jp
     */
    @AfterThrowing("operateLog()")
    public void throwss(JoinPoint jp) {
        System.out.println("方法异常时执行......");
    }

    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     *
     * @param jp
     */
    @After("operateLog()")
    public void after(JoinPoint jp) {
        System.out.println("方法最后执行......");
    }

}
