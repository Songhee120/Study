package ch9;

//JAXP 패키지
import javax.xml.parsers.*;

public class CreateDOMParser {
	public static void main(String[] args) throws Exception {
		//DOM 파서 공장 생성
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//DOM 파서 생성
		DocumentBuilder parser = factory.newDocumentBuilder();
		
		System.out.println("DOM 파서 객체 생성 성공:" + parser);
	}
}
