package ch9;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class GetFirstBookKind2 {

	public static void main(String[] args) throws Exception {
		// DOM 파서 생성
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true); 
		DocumentBuilder builder = factory.newDocumentBuilder();

		// XML 문서 파싱하기
		Document document = builder.parse("ch9/bml.xml");

		// 루트 엘리먼트 참조 얻기
		Element eRoot = document.getDocumentElement();

		// 첫번째 book 엘리먼트의  kind 속성값  변경
		Element eBook = (Element) eRoot.getFirstChild();
		String strKind = eBook.getAttribute("kind");
		System.out.println(strKind);
		eBook.setAttribute("kind", "computer");
		// == eBook.getAttributeNode("kind").setNodeValue("computer"); 
		System.out.println(eBook.getAttribute("kind"));

		// 첫번째 book 엘리먼트에  새로운 속성 추가
		eBook.setAttribute("publishDate", "20090529");
		System.out.println(eBook.getAttribute("publishDate"));

	}

}
