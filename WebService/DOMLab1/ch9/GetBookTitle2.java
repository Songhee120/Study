package ch9;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class GetBookTitle2 {

	public static void main(String[] args) throws Exception {
		// DOM 파서 생성
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true); 
		DocumentBuilder builder = factory.newDocumentBuilder();

		// XML 문서 파싱하기
		Document document = builder.parse("ch9/bml.xml");

		// 책 제목들만 뽑아오기
		NodeList nlTitles = document.getElementsByTagName("title");
		int items = nlTitles.getLength();
		for(int i=0; i<items; i++) {
			Element eTitle = (Element) nlTitles.item(i);
			Text tTitle = (Text) eTitle.getFirstChild();
			String strTitle = tTitle.getData();
			System.out.println(strTitle);
		}
	}

}
