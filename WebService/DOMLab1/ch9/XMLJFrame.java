package ch9;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.net.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;


public class XMLJFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	//필드
	public JTextField txtURL,txtTitle,txtKind,txtAuthor,txtPublisher,txtPrice;
	public JButton btnSearch, btnLoad;
	public JButton btnFirst, btnPrevious, btnNext, btnLast;
	public JButton btnNew, btnModify, btnAdd, btnSave, btnClose;

	public Document xmlDoc;
	public Element ePrevious, eCurrent, eNext;
	public boolean isAddReady;	
	
	//생성자
	public XMLJFrame() {
		this.setTitle("XML 문서 뷰어");
		
		//북쪽 패널 구성하기
		JPanel pNorth = new JPanel(new BorderLayout());
		pNorth.setBackground(Color.white);

		JLabel pNorthlblNorth = new JLabel("BML 문서 뷰어");
		pNorthlblNorth.setFont(new Font("돋움",Font.PLAIN|Font.BOLD, 18));
		pNorthlblNorth.setBorder(new EmptyBorder(20,20,20,20));
		pNorthlblNorth.setHorizontalAlignment(JLabel.CENTER);

		JPanel pNorthpCenter = new JPanel(new BorderLayout());
		pNorthpCenter.setBorder(new EmptyBorder(20,20,20,20));
		
		JPanel pNorthpCenterpCenter = new JPanel();
		TitledBorder tb1 = new TitledBorder("문서 경로 입력");
		tb1.setTitleFont(new Font("돋움",Font.PLAIN, 13));
		pNorthpCenterpCenter.setBorder(tb1);				
		
		txtURL = new JTextField(25);
		btnSearch = new JButton("찾아보기...");
		btnLoad = new JButton("로딩");
		
		pNorthpCenterpCenter.add(txtURL);
		pNorthpCenterpCenter.add(btnSearch);
		pNorthpCenterpCenter.add(btnLoad);
		
		pNorthpCenter.add("Center", pNorthpCenterpCenter);

		pNorth.add("North", pNorthlblNorth);
		pNorth.add("Center", pNorthpCenter);

		//중앙 패널 구성하기
		JPanel pCenter = new JPanel(new BorderLayout());
		pCenter.setBorder(new EmptyBorder(0,20,20,0));			
		
		JPanel pCenterpCenter = new JPanel(new BorderLayout());
		TitledBorder tb2 = new TitledBorder("책 정보");
		tb2.setTitleFont(new Font("돋움",Font.PLAIN, 13));
		pCenterpCenter.setBorder(tb2);
		
		JPanel pCenterpCenterpWest = new JPanel(new GridLayout(5,1,10,10));
		pCenterpCenterpWest.setBorder(new EmptyBorder(10,10,10,10));	
		
		JLabel lblTitle = new JLabel("책제목");
		JLabel lblKind = new JLabel("종류");
		JLabel lblAuthor = new JLabel("저자");
		JLabel lblPublisher = new JLabel("출판사");
		JLabel lblPrice = new JLabel("가격");
		
		pCenterpCenterpWest.add(lblTitle);
		pCenterpCenterpWest.add(lblKind);
		pCenterpCenterpWest.add(lblAuthor);
		pCenterpCenterpWest.add(lblPublisher);
		pCenterpCenterpWest.add(lblPrice);
			
		JPanel pCenterpCenterpCenter = new JPanel(new GridLayout(5,1,10,10));
		pCenterpCenterpCenter.setBorder(new EmptyBorder(10,10,10,10));
	
		txtTitle = new JTextField(20);
		txtKind = new JTextField(20);
		txtAuthor = new JTextField(20);
		txtPublisher = new JTextField(20);
		txtPrice = new JTextField(20);
		
	    txtTitle.setEditable(false);
	    txtKind.setEditable(false);
	    txtAuthor.setEditable(false);
	    txtPublisher.setEditable(false);
	    txtPrice.setEditable(false);
		
		pCenterpCenterpCenter.add(txtTitle);
		pCenterpCenterpCenter.add(txtKind);
		pCenterpCenterpCenter.add(txtAuthor);
		pCenterpCenterpCenter.add(txtPublisher);
		pCenterpCenterpCenter.add(txtPrice);					
		
		pCenterpCenter.add("West", pCenterpCenterpWest);
		pCenterpCenter.add("Center", pCenterpCenterpCenter);
			
		pCenter.add("Center", pCenterpCenter);

		//남쪽 패널 구성하기	
		JPanel pSouth = new JPanel(new BorderLayout());	
			
		JPanel pSouthpCenter = new JPanel(new GridLayout(1,4));	
		pSouthpCenter.setBorder(new EmptyBorder(0,20,20,55));	
					
		btnFirst = new JButton("처음");
		btnPrevious = new JButton("이전");
		btnNext = new JButton("다음");
		btnLast = new JButton("마지막");
		
		pSouthpCenter.add(btnFirst);
		pSouthpCenter.add(btnPrevious);
		pSouthpCenter.add(btnNext);
		pSouthpCenter.add(btnLast);

		JPanel pSouthpEast = new JPanel(new GridLayout(1,1));
		pSouthpEast.setBorder(new EmptyBorder(0,1,20,20));
			
		btnClose = new JButton("닫기");				
		pSouthpEast.add(btnClose);
		
		pSouth.add("Center", pSouthpCenter);
		pSouth.add("East", pSouthpEast);	
		

		//동쪽 패널 구성하기
		JPanel pEast = new JPanel(new GridLayout());
		pEast.setBorder(new EmptyBorder(0,20,20,20));	
		
		JPanel pEastpCenter = new JPanel(new GridLayout(4,1,0,30));	
		btnNew = new JButton("새 XML 문서");
		btnModify = new JButton("수정");
		btnAdd = new JButton("추가");
		btnSave = new JButton("저장");
			
		pEastpCenter.add(btnNew);
		pEastpCenter.add(btnModify);
		pEastpCenter.add(btnAdd);
		pEastpCenter.add(btnSave);			
		
		pEast.add("Center", pEastpCenter);

		getContentPane().add("North", pNorth);
		getContentPane().add("Center", pCenter);
		getContentPane().add("South", pSouth);
		getContentPane().add("East",pEast);

		eventControl();				
	}

	//메서드
	public void eventControl() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);	
			}	
		});	
		
		btnSearch.addActionListener(this);
		btnLoad.addActionListener(this);
		
		btnFirst.addActionListener(this);
		btnPrevious.addActionListener(this);
		btnNext.addActionListener(this);
		btnLast.addActionListener(this);
		
		btnNew.addActionListener(this);
		btnModify.addActionListener(this);
		btnAdd.addActionListener(this);
		btnSave.addActionListener(this);
		btnClose.addActionListener(this);		
	}

	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj==btnSearch)			btnSearch_Click();
		if(obj==btnLoad) 			btnLoad_Click();
		if(obj==btnFirst) 			btnFirst_Click();
		if(obj==btnPrevious)		btnPrevious_Click();
		if(obj==btnNext)			btnNext_Click();
		if(obj==btnLast)			btnLast_Click();
		if(obj==btnNew)				btnNew_Click();
		if(obj==btnModify)			btnModify_Click();
		if(obj==btnAdd)				btnAdd_Click();
		if(obj==btnSave)			btnSave_Click();
		if(obj==btnClose)			btnClose_Click();
	}

	public void btnSearch_Click() {
		// 로컬 파일을 찾기위해 열기 다이얼로그 실행
		JFileChooser fc = new JFileChooser();
		int buttonKind = fc.showOpenDialog(this);
		if(buttonKind==JFileChooser.CANCEL_OPTION) {
			return;
		}
		// 선택한 파일을 나타내기
		txtURL.setText(fc.getSelectedFile().getAbsolutePath());
	}
	
	public void btnLoad_Click() {
		try {
			// 파서 공장 생성
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// WhiteSpace를 무시함(XML문서에 문서유형선언이 있어야 동작함)
			factory.setIgnoringElementContentWhitespace(true);
			// 파서 생성
			DocumentBuilder parser = factory.newDocumentBuilder();

			// 원격 파일인지 조사
			if(txtURL.getText().indexOf("http://")!=-1) {
				// 웹서버상의 XML 문서 파일일 경우
				URL url = new URL(txtURL.getText());
				InputStream is = url.openStream();			
				xmlDoc = parser.parse(is);
			} else if(!txtURL.getText().trim().equals("")) {
				// 로컬 XML 문서 파일일 경우			
				xmlDoc = parser.parse(txtURL.getText());
			} else {
				return;
			}
			
			// 루트엘리먼트 객체참조 얻기
			Element eRoot = xmlDoc.getDocumentElement();			
			// 첫번째 book 엘리먼트 객체참조 얻기
			Element eBook = (Element) eRoot.getFirstChild();
			
			// 네비게이션 초기화
			ePrevious = null;
			eCurrent = null;
			eNext = null;
			txtTitle.setText("");
			txtKind.setText("");
			txtAuthor.setText("");
			txtPublisher.setText("");
			txtPrice.setText("");
			
			// 책정보를 디스플레이하기 위해 displayBook 메소드 호출
			displayBook(eBook);			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(this, "경로가 잘못되었거나, 웹서버에 접속이 되지 않았습니다.");	
		}
	}
		
	public void btnFirst_Click() {
		if(xmlDoc==null) return;
	    // 루트 엘리먼트 객체참조 얻기
	    Element eRoot = xmlDoc.getDocumentElement();  	      
	   	// 첫번째 book 엘리먼트 객체참조 얻기
	   	Element eBook = (Element)eRoot.getFirstChild();	   	 
	    // 책정보를 디스플레이하기 위해 displayBook 메소드 호출
	    displayBook(eBook);		
	}
	
	public void btnPrevious_Click() {
		// 책정보를 디스플레이하기 위해 displayBook 메소드 호출
		displayBook(ePrevious);	
	}
	
	public void btnNext_Click() {
		displayBook(eNext);	
	}
	
	public void btnLast_Click() {
		if(xmlDoc==null) return;
	    // 루트 엘리먼트 객체참조 얻기
	    Element eRoot = xmlDoc.getDocumentElement();   
	    // 첫번째 book 엘리먼트 객체참조 얻기
	   	Element eBook = (Element)eRoot.getLastChild();    
	   	// 책정보를 디스플레이하기 위해 displayBook 메소드 호출
	    displayBook(eBook);			
	}

	public void btnNew_Click() {
		try {
			// 파서 공장 생성
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			// 파서 생성
			DocumentBuilder parser = dbFactory.newDocumentBuilder();
			// 새로운 Document 객체 생성
			Document newDoc = parser.newDocument();
			
			// 루트 엘리먼트 객체 생성
			Element eRoot = newDoc.createElement("booklist");
			newDoc.appendChild(eRoot);
			
			// 저장대상인 DOMSource 객체 생성하기
			DOMSource source = new DOMSource(newDoc);
			
			// 저장할 파일에 대한 StreamResult 객체 생성하기
			JFileChooser fc = new JFileChooser();
			int buttonKind = fc.showSaveDialog(this);
			if(buttonKind==JFileChooser.CANCEL_OPTION) {
				return;
			}
			String strNewFile = fc.getSelectedFile().getAbsolutePath();
			StreamResult result = new StreamResult(new File(strNewFile));

			// 변화기 공장 생성
			TransformerFactory tFactory = TransformerFactory.newInstance();
			// 변환기 생성
			Transformer transformer = tFactory.newTransformer();
			
			// XML 선언과 문서 유형 선언 내용 설정하기
			transformer.setOutputProperty(OutputKeys.ENCODING, "euc-kr");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://localhost:8080/ch9/bml.dtd");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");				
				
			// DOM 객체에서 XML 문서 파일로 변환하기(저장하기)
			transformer.transform(source, result);
			
			//저장한 파일 로딩
			txtURL.setText(strNewFile);
			btnLoad_Click();			
		} catch(Exception e) {
			e.printStackTrace();	
		}
	}
	
	public void btnModify_Click() {
		//수정 여부 
		if(eCurrent==null) {
			return;
		} else if(!txtTitle.isEditable()) {
		    txtTitle.setEditable(true);
		    txtKind.setEditable(true);
		    txtAuthor.setEditable(true);
		    txtPublisher.setEditable(true);
		    txtPrice.setEditable(true);
		    return;
		}
		
	    txtTitle.setEditable(false);
	    txtKind.setEditable(false);
	    txtAuthor.setEditable(false);
	    txtPublisher.setEditable(false);
	    txtPrice.setEditable(false);

		// kind 속성값 수정
		eCurrent.setAttribute("kind", txtKind.getText());		
		
		// title 엘리먼트의 컨텐트 내용 수정
		Element eTitle = (Element)eCurrent.getFirstChild();
		Text tTitle = (Text)eTitle.getFirstChild();
		tTitle.setData(txtTitle.getText());		
		
		// author 엘리먼트의 컨텐트 내용 수정
		Element eAuthor = (Element)eTitle.getNextSibling();
		Text tAuthor = (Text)eAuthor.getFirstChild();
		tAuthor.setData(txtAuthor.getText());		
		
		// publisher 엘리먼트의 컨텐트 내용 수정
		Element ePublisher = (Element)eAuthor.getNextSibling();
		Text tPublisher = (Text)ePublisher.getFirstChild();
		tPublisher.setData(txtPublisher.getText());		
			
		// price 엘리먼트의 컨텐트 내용 수정
		Element ePrice = (Element)ePublisher.getNextSibling();
		Text tPrice = (Text)ePrice.getFirstChild();
		tPrice.setData(txtPrice.getText());			
	}
	
	public void btnAdd_Click() {
		//추가 가능 여부
		if(xmlDoc==null) return;
		
	    //처음 클릭했을 때 내용을 비우고 메소드 종료
	    if(isAddReady==false) {
	      txtTitle.setEditable(true);
	      txtTitle.setText("제목 입력");
	      
	      txtKind.setEditable(true);
	      txtKind.setText("종류 입력");
	      
	      txtAuthor.setEditable(true);
	      txtAuthor.setText("저자 입력");
	      
	      txtPublisher.setEditable(true);
	      txtPublisher.setText("출판사 입력");
	      
	      txtPrice.setEditable(true);
	      txtPrice.setText("가격 입력");
	      
	      isAddReady = true;
	      return;
	  	}    
	  	
	    //두번째 클릭했을 경우 아래 추가 코드 실행
	    txtTitle.setEditable(false);
	    txtKind.setEditable(false);
	    txtAuthor.setEditable(false);
	    txtPublisher.setEditable(false);
	    txtPrice.setEditable(false);
	    isAddReady = false;       
	    
	    //title 엘리먼트 객체 생성
	    Element eTitle = xmlDoc.createElement("title");	    
    	Text tTitle = xmlDoc.createTextNode(txtTitle.getText());	    
    	eTitle.appendChild(tTitle);
	    
	    //author 엘리먼트 객체 생성
	    Element eAuthor = xmlDoc.createElement("author");
    	Text tAuthor = xmlDoc.createTextNode(txtAuthor.getText());
    	eAuthor.appendChild(tAuthor);
	    
	    //publisher 엘리먼트 객체 생성
	    Element ePublisher = xmlDoc.createElement("publisher");
    	Text tPublisher = xmlDoc.createTextNode(txtPublisher.getText());
	    ePublisher.appendChild(tPublisher);
	    
	    //price 엘리먼트 객체 생성
	    Element ePrice = xmlDoc.createElement("price");
    	Text tPrice = xmlDoc.createTextNode(txtPrice.getText());
    	ePrice.appendChild(tPrice);
	    
	    //book 엘리먼트 객체 생성
	    Element eBook = xmlDoc.createElement("book");
	    
	    //속성 추가
	    eBook.setAttribute("kind", txtKind.getText());
	    
	    //book 엘리먼트 객체에 자식 엘리먼트 객체 붙이기
	    eBook.appendChild(eTitle);
	    eBook.appendChild(eAuthor);
	    eBook.appendChild(ePublisher);
	    eBook.appendChild(ePrice);
	    
	    //루트 엘리먼트 객체참조 얻기
	    Element eRoot = xmlDoc.getDocumentElement();
	    
	    //book 엘리먼트 객체를 루트 엘리먼트 객체에 붙이기
	    eRoot.appendChild(eBook);
	    
	    //추가하기 전에 보았던 책 내용을 디스플레이
	    displayBook(eBook);
	}
	
	public void btnSave_Click() {
		try {
			// 변화기 공장 생성			
			TransformerFactory factory = TransformerFactory.newInstance();
			// 변환기 생성
			Transformer transformer = factory.newTransformer();
			
			// XML 선언과 문서 유형 선언 내용 설정하기
			transformer.setOutputProperty(OutputKeys.ENCODING, "euc-kr");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://localhost:8080/ch9/bml.dtd");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			// 저장대상인 DOMSource 객체 생성하기	
			DOMSource source = new DOMSource(xmlDoc);
				
			// 저장할 파일에 대한 StreamResult 객체 생성하기				
			if(txtURL.getText().indexOf("http://")!=-1) {
				JFileChooser fc = new JFileChooser();
				fc.showSaveDialog(this);
				txtURL.setText(fc.getSelectedFile().getAbsolutePath());
			} else if(txtURL.getText().trim().equals("")) {
				return;
			}
			StreamResult result = new StreamResult(new File(txtURL.getText()));	
				
			// DOM 객체에서 XML 문서 파일로 변환하기(저장하기)
			transformer.transform(source, result);
		} catch(Exception e) {
			e.printStackTrace();	
		}
	}
	
	public void btnClose_Click() {
		System.exit(0);	
	}
	
	public void displayBook(Element eBook) {	
		if(eBook==null) {
			//book 엘리먼트 객체참조가 없을 경우 메소드 종료
			return;			
		} else {
			//현재 book 엘리먼트의 이전 형제 book 엘리먼트 객체참조를
			//ePrevious에 저장 -- 이전 버튼 코드에서 사용
			ePrevious = (Element)eBook.getPreviousSibling();
			
			//현재 book 엘리먼트의 객체참조를 eCurrent에 저장
			eCurrent = eBook;
			
			//현재 book 엘리먼트의 다음 형제 book 엘리먼트 객체참조를
			//eNext에 저장 -- 다음 버튼 코드에서 사용
			eNext = (Element)eBook.getNextSibling();
		}

		//kind 속성값을 얻어내어 디스플레이
		txtKind.setText(eCurrent.getAttribute("kind"));

	    //title 엘리먼트의 컨텐트 내용을 얻어내어 디스플레이
	    Element eTitle = (Element)eCurrent.getFirstChild();
	    Text tTitle = (Text)eTitle.getFirstChild();
	    txtTitle.setText(tTitle.getData());
    
	    //author 엘리먼트의 컨텐트 내용을 얻어내어 디스플레이
	    Element eAuthor = (Element)eTitle.getNextSibling();
	    Text tAuthor = (Text)eAuthor.getFirstChild();
	    txtAuthor.setText(tAuthor.getData());
    
	    //publisher 엘리먼트의 컨텐트 내용을 얻어내어 디스플레이
	    Element ePublisher = (Element)eAuthor.getNextSibling();
	    Text tPublisher = (Text)ePublisher.getFirstChild();
	    txtPublisher.setText(tPublisher.getData());
    
	    //price 엘리먼트의 컨텐트 내용을 얻어내어 디스플레이
	    Element ePrice = (Element)ePublisher.getNextSibling();
	    Text tPrice = (Text)ePrice.getFirstChild();
	    txtPrice.setText(tPrice.getData());		
	}

	//메인 메서드
	public static void main(String args[]) {
		// XMLJFrame 객체 생성
		XMLJFrame frame = new XMLJFrame();
		// 윈도우 룩앤필로 변경 
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(frame);
	    } catch(Exception e) {
	    }
		// Frame 사이즈 주기
		frame.setSize(550,470);
		// Frame 사이즈 고정
		frame.setResizable(false);
		// Frame 보여 주기
		frame.setVisible(true);
	}
}