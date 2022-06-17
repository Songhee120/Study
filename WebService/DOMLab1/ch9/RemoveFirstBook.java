package ch9;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class RemoveFirstBook {

	public static void main(String[] args) throws Exception {
		// DOM 파서 생성
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true); 
		DocumentBuilder builder = factory.newDocumentBuilder();

		// XML 문서 파싱하기
		Document document = builder.parse("ch9/bml.xml");

		// 루트 엘리먼트 참조 얻기
		Element eRoot = document.getDocumentElement();

		// 루트 엘리먼트의 첫번째 자식 엘리먼트인 book 엘리먼트 제거
		Element eBook = (Element) eRoot.getFirstChild();
		eRoot.removeChild(eBook);

		// 제거후 루트 엘리먼트의 첫번째 자식 엘리먼트 정보 얻기
		eBook = (Element) eRoot.getFirstChild();
		Element eTitle = (Element) eBook.getFirstChild();
		Text tTitle = (Text) eTitle.getFirstChild();
		String strTitle = tTitle.getData();

		System.out.println(strTitle);
	}

}