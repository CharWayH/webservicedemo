package com.charwayh.cxf.test;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * WebService测试接口
 */
@WebService
public interface HelloWorld {
    @WebMethod
    @WebResult(name = "sayHelloRetValue")
     String sayHello(@WebParam(name = "userName") String name, @WebParam(name = "userAge") int age);

}
