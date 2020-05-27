package com.charwayh.cxf.test;

/**
 * WebService测试接口实现类
 */
public class HelloWorldServiceImpl implements HelloWorld {
    @Override
    public String sayHello(String name, int age) {
        return "你好，" + name + "！您的年龄是" + age;
    }
}
