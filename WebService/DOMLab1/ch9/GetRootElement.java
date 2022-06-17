package ch9;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class GetRootElement {

	public static void main(String[] args) throws Exception {
		// DOM 파서 생성
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		// XML 문서 파싱하기
		Document document = builder.parse("ch9/bml.xml");

		// 루트 엘리먼트 참조 얻기
		Element eRoot = document.getDocumentElement();

		// 루트 엘리먼트 이름 출력
		System.out.println(eRoot.getTagName());
	}

}
