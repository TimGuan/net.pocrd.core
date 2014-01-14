﻿package net.pocrd.entity;

import java.lang.reflect.Method;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import net.pocrd.define.ApiOpenState;
import net.pocrd.define.SecurityType;
import net.pocrd.define.Serializer;
import net.pocrd.entity.ReturnCode.CodeInfo;
import net.pocrd.util.CDataString;

/**
 * 接口信息
 */
@XmlRootElement
public class ApiMethodInfo {
    /**
     * 未知资源
     */
    public static final ApiMethodInfo UnknownMethod = new ApiMethodInfo();

    static {
        UnknownMethod.methodName = "Unknown";
        UnknownMethod.description = "未知资源";
        UnknownMethod.errorCodes = new CodeInfo[] {};
        UnknownMethod.parameterInfos = null;
        UnknownMethod.proxyMethodInfo = null;
        UnknownMethod.securityLevel = SecurityType.None;
    }

    /**
     * 返回值类型
     */
    public Class<?>                   returnType;

    /**
     * 返回值类型对应的序列化工具
     */
    @XmlTransient
    public Serializer<?>              serializer;

    /**
     * 方法名称
     */
    public String                     methodName;

    /**
     * 方法调用说明
     */
    public String                     description;

    /**
     * 方法需要的安全级别
     */
    public SecurityType               securityLevel = SecurityType.None;

    /**
     * 资源所属组名
     */
    public String                     groupName;

    /**
     * 方法状态
     */
    public ApiOpenState               state         = ApiOpenState.OPEN;

    /**
     * 返回值格式定义
     */
    @XmlElementWrapper(name = "returnInfo")
    @XmlElement(name = "entity")
    public CDataString[]                   returnInfo    = null;

    /**
     * 参数类型
     */
    @XmlElementWrapper(name = "parameterInfos")
    @XmlElement(name = "parameterInfo")
    public ApiParameterInfo[]         parameterInfos;

    /**
     * 该方法可能抛出的业务异常的errorcode集合
     */
    @XmlElementWrapper(name = "errorCodes")
    @XmlElement(name = "apiCode")
    public CodeInfo[]                      errorCodes;
    
    /**
     * 该方法可能抛出的业务异常的errorcode int集合, 用于二分查找
     */
    @XmlTransient
    public int[] errors;

    /**
     * 所代理的方法的信息
     */
    @XmlTransient
    public Method                     proxyMethodInfo;
}