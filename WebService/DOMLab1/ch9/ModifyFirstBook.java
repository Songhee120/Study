package ch9;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class ModifyFirstBook {

	public static void main(String[] args) throws Exception {
		// DOM 파서 생성
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true); 
		DocumentBuilder builder = factory.newDocumentBuilder();

		// XML 문서 파싱하기
		Document document = builder.parse("ch9/bml.xml");

		// 루트 엘리먼트 참조 얻기
		Element eRoot = document.getDocumentElement();

		// 첫번째 책 제목 수정하기
		Element eBook = (Element) eRoot.getFirstChild();
		Element eTitle = (Element) eBook.getFirstChild();
		Text tTitle = (Text) eTitle.getFirstChild();
		tTitle.setData("성공을 위한 열쇠");
		System.out.println("수정후 제목: " + tTitle.getData());

		// 첫번째 책 종류 수정하기
		eBook.setAttribute("kind", "소설");
		System.out.println("수정후 종류: " + eBook.getAttribute("kind"));
	}

}
