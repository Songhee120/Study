package ch9;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class GetFirstBookKind {

	public static void main(String[] args) throws Exception {
		// DOM 파서 생성
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true); 
		DocumentBuilder builder = factory.newDocumentBuilder();

		// XML 문서 파싱하기
		Document document = builder.parse("ch9/bml.xml");

		// 루트 엘리먼트 참조 얻기
		Element eRoot = document.getDocumentElement();

		// 첫번째 book 엘리먼트 속성값 얻기
		Element eBook = (Element) eRoot.getFirstChild();
		String strKind = eBook.getAttribute("kind");

		System.out.println(strKind);
	}

}
