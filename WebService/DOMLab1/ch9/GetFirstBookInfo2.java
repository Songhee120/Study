package ch9;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class GetFirstBookInfo2 {

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

		for(Node ch = eBook.getFirstChild(); ch != null; ch = ch.getNextSibling())
		{			
			if (ch.getNodeName().equals("title"))	
		         System.out.print("제목: ");
			else if (ch.getNodeName().equals("author"))	
		         System.out.print("저자: ");
			else if (ch.getNodeName().equals("publisher"))	
		         System.out.print("출판사: ");
			else if (ch.getNodeName().equals("price"))	
		         System.out.print("가격: ");		
			System.out.println(ch.getFirstChild().getNodeValue());
		}

	}

}
