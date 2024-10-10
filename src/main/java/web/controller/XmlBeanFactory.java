package web.controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import javax.xml.parsers.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlBeanFactory { // xml을 읽는 클래스 
	
	HashMap<String, Controller> beans = new HashMap<>();
	
	private static HashMap<String, Object> beans2 = new HashMap<>();
	
	public HashMap<String, Controller> getBeans() {
		return beans;
	}
	
	public static HashMap<String, Object> getBeans2() {
		return beans2;
	}
	
	public XmlBeanFactory(String controller_path, String model_path) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser(); // SAXParser return
		
		parser.parse(model_path, new MyDefaultHandler2()); // file 경로와 처리하는 handler
		parser.parse(controller_path, new MyDefaultHandler()); // controller가 service에 의존하고 있으므로 나중에 parsing
		
		System.out.println(beans);
		System.out.println(beans2);
	}

	// controller 용 handler
	class MyDefaultHandler extends DefaultHandler {
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes)
				throws SAXException { // qName : qualified name, tag name 
			// TODO Auto-generated method stub
			if ("bean".equals(qName)) {
				String id = attributes.getValue("id");
				String className = attributes.getValue("class");
				// className을 통해 클래스화 할 수 있음
				try {
					Constructor c =  Class.forName(className).getConstructor(); // reflection 후 생성자 생성 
					Controller o = (Controller) c.newInstance(); // object type 객체 반환 
					beans.put(id, o);
					// 여러 클래스들이 생기기 때문에 모아둘 자료 구조 필요 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
	}
	
	// model (Service, Dao) 용 handler
	class MyDefaultHandler2 extends DefaultHandler {
			
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes)
					throws SAXException { // qName : qualified name, tag name 
			// TODO Auto-generated method stub
			if ("bean".equals(qName)) {
				String id = attributes.getValue("id");
				String className = attributes.getValue("class");
				// className을 통해 클래스화 할 수 있음
				try {
					Constructor c =  Class.forName(className).getConstructor(); // reflection 후 생성자 생성 
					Object o = c.newInstance(); // object type 객체 반환 
					beans2.put(id, o);
					// 여러 클래스들이 생기기 때문에 모아둘 자료 구조 필요 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
	}
}
