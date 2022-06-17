package xpath;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XPathEvaluator {
//	private static String xmlDoc = "books.xml";
	private static String xmlDoc = "mondial.xml";

	public static void main(String[] args) throws Exception {
		// InputSource 객체 생성
		InputSource xmlSource = new InputSource(xmlDoc);
		
		// XPath 객체 생성
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		
		while (true) {			
			// XPath 식 입력
			System.out.print("XPath Expression: ");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		    String xpathExpr = in.readLine();
			
		    if (xpathExpr.equals("")) {
		    	System.out.println("Quits..."); break;
		    }
		    
		    // XPath 식 실행
		    NodeList nl = (NodeList)xpath.evaluate(xpathExpr, xmlSource, XPathConstants.NODESET);
	
		    // 결과 출력
		    for(int i=0; i<nl.getLength(); i++)
			{
		    	if (nl.item(i) instanceof Element) {
		    		Element element = (Element) nl.item(i);
		    		System.out.println(element.getTextContent());
		    	}
		    	else if (nl.item(i) instanceof Attr) {
		    		Attr attr = (Attr) nl.item(i);
		    		System.out.println(attr.getValue());
		    	}
			}
		    System.out.println();
		}		
	}
}
