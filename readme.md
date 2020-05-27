# WebService

### 简介

1. WebService即web服务，它是一种**跨编程语言、跨操作系统**平台的远程调用技术

2. Java共有三种WebService规范，分别是**JAX-WS(JAX-RPC)、JAX-RS**、JAXM&SAAJ
   - JAX-WS(Java API for XML WebService):一组XML WebService的Java API。允许开发者使用RPC-oriented或message-oriented来实现WebService
   - JAX-RS(Java API for RESTful WebService):是一个Java的应用程序接口
3. WebService三要素:soap、wsdl、uddi



#### 1.soap协议

1. SOAP即简单对象访问协议(Simple Object Access Protocol)，它是用于交换XML编码信息的轻量级协议。它有三个主要方面:XML-envelope为描述信息内容和如何处理内容定义了框架，将程序对象编码成为XML对象的规则，执行远程过程调用(RPC)的约定。SOAP可以运行在任何其他传输协议上
2. SOAP作为一个基于XML语言的协议用于有网上传输数据
3. SOAP=在HTTP的基础上+XML数据
4. SOAP是基于HTTP的
5. SOAP的组成如下
   - Envelope 必须的部分，以XML的根元素出现
   - Headers 可选部分
   - Body 必须部分。包含要执行的服务器的方法，和发送到服务器的数据
   - Fault 可选部分。提供处理此消息所发生的的错误信息



#### 2.wsdl说明书

WSDL:Web Service描述语言。就是用机器能阅读的方式提供的一个正式描述文档，它是基于XML的。用于描述WebService及其函数、参数和方法。因为是基于xml的所以人类和机器都可以阅读



#### 3.UDDI

Universal Description Discovery and Integration

统一描述、发现和集成WebService的技术

它是一个基于XML的跨平台的描述规范，可以使世界范围内的企业在互联网上发布自己所提供的服务。简单的说，UDDI是访问各种WSDL的一个门面（可以参考设计模式中的门面模式）。



### 服务端使用

1. 导入依赖

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web-services</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.cxf</groupId>
            <artifactId>cxf-rt-frontend-jaxws</artifactId>
            <version>3.1.6</version>
        </dependency>
        <dependency>
            <groupId>org.apache.cxf</groupId>
            <artifactId>cxf-rt-transports-http</artifactId>
            <version>3.1.6</version>
        </dependency>
        <dependency>
            <groupId>org.apache.cxf</groupId>
            <artifactId>cxf-rt-transports-http-jetty</artifactId>
            <version>3.2.13</version>
        </dependency>
```

2. 服务端接口

```java
import javax.jws.WebService;

@WebService
public interface HelloWorld {
    public String sayHello(String name, int age);
}
```

3. 服务端实现类

```java
public class HelloWorldServiceImpl implements HelloWorld {
    @Override
    public String sayHello(String name, int age) {
        return "你好，" + name + "！您的年龄是" + age;
    }
}
```

4. 创建服务端主类

```java
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
```

5. 用soapUI进行测试





### 客户端使用

1. 创建客户端主类

```java
public class ClientTest {
    public static void main(String[] args) {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress("http://localhost:9999/test");
        jaxWsProxyFactoryBean.setServiceClass(HelloWorld.class);
        HelloWorld helloWorld  = (HelloWorld)jaxWsProxyFactoryBean.create();
        String result = helloWorld.sayHello("charwayh", 25);
        System.out.println(result);
    }
}
```



一次WebService的调用不是方法的调用，而是soap消息之间的输入输出



SOAP小结:

1. 客户端到UDDI上寻找Service目录
2. 客户端获得WSDL文件
3. 客户端通过WSDL文件的约数和规范创建SOAP客户端
4. 客户端通过SOAP访问Service



WSDL报文整体结构

```xml
<definitions>
  <types>定义WebService使用的数据类型</types>
  <message>每个消息均由一个或多个部件组成，可以把它当做是java中一个方法的调用</message>
  <portType>它类似于java中一个类(接口)
  <operation>接口中定义的方法</operation>
  </portType>
  <bindling>为每个端口定义消息格式和协议细节</bindling>
</definitions>
```





```java
@WebService
public interface HelloWorld {
  	//Web方法
    @WebMethod
  	//返回值结果的别名
    @WebResult(name="sayHelloRetValue")
  	//Web入参的别名
    public String sayHello(@WebParam(name="userName") String name,@WebParam(name="userAge") int age);

}
```





### JAXB

Java Architechture for XML Binding的缩写，提供了快捷的方式将**Java对象与XML进行转换**



Unmarshaller类:将XML数据反序列化为新创将建的Java内容树的进程，并可在解组时有选择的验证XML数据

Marshaller类:将Java内容树序列化回XML数据的过程 



创建Book类

```java
@XmlRootElement
public class Book {
    private long id;
    private String bookName;
    private double price;

    public Book() {
    }

    public Book(long id, String bookName, double price) {
        this.id = id;
        this.bookName = bookName;
        this.price = price;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
```



```java
public class JAXBTest {
    /**
     * POJO→XML
     * @throws JAXBException
     */
    public static void myMarshaller() throws JAXBException {
        //创建Book类的JAXB实例
        JAXBContext jaxbContext = JAXBContext.newInstance(Book.class);
        //创建marshaller
        Marshaller marshaller = jaxbContext.createMarshaller();
        //设置xml缩进，字符集为utf-8
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING,"utf-8");
        //添加book对象并输出 
        marshaller.marshal(new Book(11,"java宝典",25.5d),System.out);
    }

    /**
     * XML→POJO
     * @throws JAXBException
     */
    public static void myUnmarshaller() throws JAXBException {
        //xml
        String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?><book><bookName>java宝典</bookName><id>11</id><price>25.5</price></book>";
        //创建Book类的JAXB实例
        JAXBContext jaxbContext = JAXBContext.newInstance(Book.class);
        //创建unmarshaller
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        //XML→POJO
        Book book = (Book)unmarshaller.unmarshal(new StringReader(xmlString));
        System.out.println(book);

    }

    public static void main(String[] args) throws JAXBException {
        //myMarshaller();
        myUnmarshaller();
    }
}
```








