package ch9;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class GetFirstBookInfo {

	public static void main(String[] args) throws Exception {
		// DOM 파서 생성
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true); 
		DocumentBuilder builder = factory.newDocumentBuilder();

		// XML 문서 파싱하기
		Document document = builder.parse("ch9/bml.xml");

		// 루트 엘리먼트 참조 얻기
		Element eRoot = document.getDocumentElement();

		// 첫번째 book 엘리먼트 정보 얻기
		Element eBook = (Element) eRoot.getFirstChild();
		Element eTitle = (Element) eBook.getFirstChild();
		Text tTitle = (Text) eTitle.getFirstChild();
		String strTitle = tTitle.getData();
		System.out.println("제목: " + strTitle);

		Element eAuthor = (Element) eTitle.getNextSibling();
		Text tAuthor = (Text) eAuthor.getFirstChild();
		String strAuthor = tAuthor.getData();
		System.out.println("저자: " + strAuthor);

		Element ePublisher = (Element) eAuthor.getNextSibling();
		Text tPublisher = (Text) ePublisher.getFirstChild();
		String strPublisher = tPublisher.getData();
		System.out.println("출판사: " + strPublisher);

		Element ePrice = (Element) ePublisher.getNextSibling();
		Text tPrice = (Text) ePrice.getFirstChild();
		String strPrice = tPrice.getData();
		System.out.println("가격: " + strPrice);
	}

}
