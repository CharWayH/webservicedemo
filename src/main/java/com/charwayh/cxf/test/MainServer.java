package com.charwayh.cxf.test;

        import org.apache.cxf.endpoint.Server;
        import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * WebService服务端
 */
public class MainServer {
    public static void main(String[] args) {
        //创建JaxWsServer工厂bean
        JaxWsServerFactoryBean jaxWsServerFactoryBean = new JaxWsServerFactoryBean();
        //设置url地址
        jaxWsServerFactoryBean.setAddress("http://localhost:9999/test");
        //调用实现类
        jaxWsServerFactoryBean.setServiceClass(HelloWorldServiceImpl.class);
        //开启工厂
        Server server = jaxWsServerFactoryBean.create();
        //开启服务
        server.start();
    }
}