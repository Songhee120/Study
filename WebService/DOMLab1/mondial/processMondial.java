package mondial;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import java.io.*;

public class processMondial {
	static final String inputFile = "mondial/mondial-sample.xml";  	
									// 나중에 "mondial/mondial.xml"로 변경해서 테스트 
	static final String outputFile = "mondial/result.xml";
	
	public static void main(String[] args) throws Exception {
		// DOM 파서 생성
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = factory.newDocumentBuilder();

		// XML 문서 파싱하기
		Document document = builder.parse(inputFile);

		Element mondial = document.getDocumentElement();
		
		// 대륙별 인구를 계산 및 출력 (3번)
		computePopulationsOfContinents(mondial);					
		
		// 종교별 신자 수를 계산 및 출력 (4번)
		// computeBelieversOfReligions(mondial);	
				
		// 국가별 정보 검색 및 출력 (1번)
		processCountries1(mondial);	
		
		// 국가별 정보 검색 및 DOM 트리 수정 (2번)
		processCountries2(mondial);	
		
		// 처리 결과 출력을 위한 변환기 생성
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();

		// 출력 포맷 설정: XML 선언과 문서 유형 선언 내용 설정하기
		transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		// transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
		// "mondial.dtd");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		
		// DOMSource 객체 생성
		DOMSource source = new DOMSource(document);

		// StreamResult 객체 생성
		StreamResult result = new StreamResult(new File(outputFile));

		// 파일로 저장하기
		transformer.transform(source, result);
		
		System.out.println();
		System.out.println(outputFile + "로 저장되었습니다.");
	}

	public static void processCountries1(Element mondial) {
		//mondial 하위의 모든 요소들을 한 번씩 가져옴
		for (Node ch = mondial.getFirstChild(); ch != null; ch = ch.getNextSibling()) {
			if(ch.getNodeName().equals("country")) {	//ch: <country>
				Element country = (Element)ch;
				
				//1-1 국가 이름
				//country 엘리먼트의 첫번째 자식을 타고 내려가 데이터를 출력함
				Node name = country.getFirstChild();	// <name>Albania</name>
				Text txt = (Text)name.getFirstChild();
				String countryName = txt.getData();		// "Albania"
				System.out.println(countryName);
				
				//1-2 국가 면적
				//area속성의 값을 가져와 데이터를 출력함
				String areaValue = country.getAttribute("area");
				System.out.println("면적: " + areaValue);
				
				//1-3 인구
				//populationList에 population태그들을 가져와 첫 번째 엘리먼트의 값을 출력
				NodeList populationList = country.getElementsByTagName("population");
				if(populationList != null) {
					String populationValue = populationList.item(0).getTextContent();
					System.out.println("인구: " + populationValue);
				}
				
				//1-4 수도(capital 속성이 없는 경우도 있음)
				//capital속성의 값을 가져와 해당 값을 id값으로 갖는 엘리먼트 값을 출력
				String capitalId = country.getAttribute("capital");
				if(capitalId != null) {
					Element capital = country.getOwnerDocument().getElementById(capitalId);
					String capitalName = capital.getFirstChild().getTextContent();
					System.out.println("수도: " + capitalName);
				}
			}
			
			System.out.println();
		}
		
	} 
	
	public static void processCountries2(Element mondial) {
		NodeList list = mondial.getElementsByTagName("country");
		
		for (int i = 0; i < list.getLength(); i++) {
			
			Element country = (Element)list.item(i);
			Document document = country.getOwnerDocument();
			
			//2-2 국가 면적
			//country에 area 자식 노드 추가해주기
			Element area = document.createElement("area");
			String areaValue = country.getAttribute("area");
			Text areaText = document.createTextNode(areaValue);
			area.appendChild(areaText);	
			
			//2-3 인구
			//area 뒤에 형제노드로 poplulation 추가해주기
			Node population = country.getFirstChild().getNextSibling();
			country.insertBefore(area, population);
			
			//2-4 수도
			//country의 마지막 자식으로 추가
			Element capital = null;
			String capitalId = country.getAttribute("capital");
			if(!capitalId.isEmpty()) {
				capital = country.getOwnerDocument().getElementById(capitalId);
				country.appendChild(capital);
			}
			
			//2-5 속성 삭제
			//NamedNoeMap타입의 객체에 속성을 하나씩 가져와서 remove
			NamedNodeMap attrMap = country.getAttributes();
			while (attrMap.getLength() > 0) {
				Node attrNode = attrMap.item(0);
				country.removeAttributeNode((Attr) attrNode);
			}
			
			//2-6 country외의 다른 노드 모두 삭제
			//ch의 nodeNamep이 country인지 아닌지에 따라 분기처리
			Node ch = population.getNextSibling();
			while(ch != null) {
				if(!ch.getNodeName().equals("country")) {
					Node nextChild = ch.getNextSibling();	//미리 nextChild 구해두기
					
					//element를 못찾을 경우 DOMException 발생
					try {
						mondial.removeChild(ch);
						ch = nextChild;
					} catch (DOMException  e) {
						ch = ch.getNextSibling();
					}
					
				} else {
					ch = ch.getNextSibling();
				}
			}
			
		}
	} 
	
	public static void computePopulationsOfContinents(Node mondial) {

		// ...
		
		// 계산된 각 대륙의 인구를 출력
		printPopulationsOfContinents();
	}

	public static void printPopulationsOfContinents() {
	
	}
	
}