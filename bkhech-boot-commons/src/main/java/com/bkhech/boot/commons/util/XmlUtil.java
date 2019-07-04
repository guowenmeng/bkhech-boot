package com.bkhech.boot.commons.util;

import com.bkhech.boot.commons.bean.TestBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author guowm[guowm@5fun.com]
 * @date 2019/7/4
 * dom4j 解析xml
 */
public class XmlUtil {

    /**
     * xml文档Document转对象
     * @param document
     * @param clazz
     * @return
     */
    private static Object getObject(Document document,Class<?> clazz) {
        Object obj=null;
        //获取根节点
        Element root = document.getRootElement();
        try {
            //创建对象
            obj=clazz.newInstance();
            List<Element> properties=root.elements();
            for(Element pro:properties){
                String propertyname=pro.getName();
                //获取属性名(首字母大写)
                propertyname = propertyname.substring(0, 1).toUpperCase() + propertyname.substring(1);
                String propertyvalue=pro.getText();
                Method m = obj.getClass().getMethod("set"+propertyname,String.class);
                m.invoke(obj,propertyvalue);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    /**
     * xml字符串转对象
     * @param xmlString
     * @param clazz
     * @return
     */
    public static Object getObject(String xmlString,Class<?> clazz) throws DocumentException {
        Document document=null;
        try {
            document = DocumentHelper.parseText(xmlString);
        } catch (DocumentException e) {
            throw new DocumentException("获取Document异常.", e);
        }
        //获取根节点
        return getObject(document,clazz);
    }
    /**
     * 对象转xml文件
     * @param b
     * @return
     */
    private static Document getDocument(Object b) {
        Document document = DocumentHelper.createDocument();
        try {
            // 创建根节点元素
            Element root = document.addElement(b.getClass().getSimpleName());
            // 获取实体类b的所有属性，返回Field数组
            Field[] field = b.getClass().getDeclaredFields();
            // 遍历所有有属性
            for (int j = 0; j < field.length; j++) {
                // 获取属属性的名字
                String name = field[j].getName();
                //去除串行化序列属性
                if (!name.equals("serialVersionUID")) {
                    // 将属性的首字符大写，方便构造get，set方法
                    String getName = name.substring(0, 1).toUpperCase() + name.substring(1);
                    Method method = b.getClass().getMethod("get" + getName);
                    // System.out.println("属性get方法返回值类型:" + m.getReturnType());
                    // 获取属性值
                    String propertiesValue = (String)method.invoke(b);
                    Element element = root.addElement(name);
                    element.setText(propertiesValue.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * 对象转xml格式的字符串
     * @param b
     * @return
     */
    public static String getXmlString(Object b){
        return getDocument(b).asXML();
    }

//    public static void main(String[] args) {
//        TestBean testBean = new TestBean();
//        testBean.setIdCard("13324w34232");
//        testBean.setName("bkhech");
//        testBean.setSex("1");
//        //调用方法把对象转xml格式
//        System.out.println(XmlUtil.getXmlString(testBean));
//        //调用getXmlString（）方法把xml格式重新转换为对象
//        try {
//            System.out.println(XmlUtil.getObject(XmlUtil.getXmlString(testBean), TestBean.class));
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//    }

}
