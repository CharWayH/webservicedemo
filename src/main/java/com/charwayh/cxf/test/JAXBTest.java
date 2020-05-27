package com.charwayh.cxf.test;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

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