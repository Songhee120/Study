package mondial;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import java.io.*;

public class processMondial {
	static final String inputFile = "mondial/mondial-sample.xml";  	
									// ���߿� "mondial/mondial.xml"�� �����ؼ� �׽�Ʈ 
	static final String outputFile = "mondial/result.xml";
	
	public static void main(String[] args) throws Exception {
		// DOM �ļ� ����
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = factory.newDocumentBuilder();

		// XML ���� �Ľ��ϱ�
		Document document = builder.parse(inputFile);

		Element mondial = document.getDocumentElement();
		
		// ����� �α��� ��� �� ��� (3��)
		computePopulationsOfContinents(mondial);					
		
		// ������ ���� ���� ��� �� ��� (4��)
		// computeBelieversOfReligions(mondial);	
				
		// ������ ���� �˻� �� ��� (1��)
		processCountries1(mondial);	
		
		// ������ ���� �˻� �� DOM Ʈ�� ���� (2��)
		processCountries2(mondial);	
		
		// ó�� ��� ����� ���� ��ȯ�� ����
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();

		// ��� ���� ����: XML ����� ���� ���� ���� ���� �����ϱ�
		transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		// transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
		// "mondial.dtd");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		
		// DOMSource ��ü ����
		DOMSource source = new DOMSource(document);

		// StreamResult ��ü ����
		StreamResult result = new StreamResult(new File(outputFile));

		// ���Ϸ� �����ϱ�
		transformer.transform(source, result);
		
		System.out.println();
		System.out.println(outputFile + "�� ����Ǿ����ϴ�.");
	}

	public static void processCountries1(Element mondial) {
		//mondial ������ ��� ��ҵ��� �� ���� ������
		for (Node ch = mondial.getFirstChild(); ch != null; ch = ch.getNextSibling()) {
			if(ch.getNodeName().equals("country")) {	//ch: <country>
				Element country = (Element)ch;
				
				//1-1 ���� �̸�
				//country ������Ʈ�� ù��° �ڽ��� Ÿ�� ������ �����͸� �����
				Node name = country.getFirstChild();	// <name>Albania</name>
				Text txt = (Text)name.getFirstChild();
				String countryName = txt.getData();		// "Albania"
				System.out.println(countryName);
				
				//1-2 ���� ����
				//area�Ӽ��� ���� ������ �����͸� �����
				String areaValue = country.getAttribute("area");
				System.out.println("����: " + areaValue);
				
				//1-3 �α�
				//populationList�� population�±׵��� ������ ù ��° ������Ʈ�� ���� ���
				NodeList populationList = country.getElementsByTagName("population");
				if(populationList != null) {
					String populationValue = populationList.item(0).getTextContent();
					System.out.println("�α�: " + populationValue);
				}
				
				//1-4 ����(capital �Ӽ��� ���� ��쵵 ����)
				//capital�Ӽ��� ���� ������ �ش� ���� id������ ���� ������Ʈ ���� ���
				String capitalId = country.getAttribute("capital");
				if(capitalId != null) {
					Element capital = country.getOwnerDocument().getElementById(capitalId);
					String capitalName = capital.getFirstChild().getTextContent();
					System.out.println("����: " + capitalName);
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
			
			//2-2 ���� ����
			//country�� area �ڽ� ��� �߰����ֱ�
			Element area = document.createElement("area");
			String areaValue = country.getAttribute("area");
			Text areaText = document.createTextNode(areaValue);
			area.appendChild(areaText);	
			
			//2-3 �α�
			//area �ڿ� �������� poplulation �߰����ֱ�
			Node population = country.getFirstChild().getNextSibling();
			country.insertBefore(area, population);
			
			//2-4 ����
			//country�� ������ �ڽ����� �߰�
			Element capital = null;
			String capitalId = country.getAttribute("capital");
			if(!capitalId.isEmpty()) {
				capital = country.getOwnerDocument().getElementById(capitalId);
				country.appendChild(capital);
			}
			
			//2-5 �Ӽ� ����
			//NamedNoeMapŸ���� ��ü�� �Ӽ��� �ϳ��� �����ͼ� remove
			NamedNodeMap attrMap = country.getAttributes();
			while (attrMap.getLength() > 0) {
				Node attrNode = attrMap.item(0);
				country.removeAttributeNode((Attr) attrNode);
			}
			
			//2-6 country���� �ٸ� ��� ��� ����
			//ch�� nodeNamep�� country���� �ƴ����� ���� �б�ó��
			Node ch = population.getNextSibling();
			while(ch != null) {
				if(!ch.getNodeName().equals("country")) {
					Node nextChild = ch.getNextSibling();	//�̸� nextChild ���صα�
					
					//element�� ��ã�� ��� DOMException �߻�
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
		
		// ���� �� ����� �α��� ���
		printPopulationsOfContinents();
	}

	public static void printPopulationsOfContinents() {
	
	}
	
}