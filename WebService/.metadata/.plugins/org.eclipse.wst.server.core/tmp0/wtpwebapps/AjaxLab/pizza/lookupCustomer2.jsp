<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="example.ajax.pizza.*,java.util.*"%>
<%-- <%@ page import="com.fasterxml.jackson.databind.*"%>   --%>
<%! @SuppressWarnings("unchecked") %>
<%
// application에서 "custMap" 객체 검색
Map<String, Customer> custMap = (Map<String, Customer>)application.getAttribute("custMap"); 

if (custMap == null) {	// "custMap"이 존재하지 않으면 새로 생성
	//Set up some customers to use 
	custMap = new HashMap<String, Customer>();
	custMap.put("010-111-1111", 
				new Customer("Doug Henderson",
					"7804 Jumping Hill Lane",
					"Dallas", "Texas", "75218")
	);
	custMap.put("010-222-2222", 
				new Customer("Mary Jenkins",
					"7081 Teakwood #24C",
					"Dallas", "Texas", "75182")
	);
	custMap.put("010-333-3333", 
				new Customer("John Jacobs",
					"234 East Rutherford Drive",
					"Topeka", "Kansas", "66608")
	);
	custMap.put("010-444-4444", 
				new Customer("Happy Traum",
					"876 Links Circle",
					"Topeka", "Kansas", "66608")
	);	
	application.setAttribute("custMap", custMap);	// "custMap"을 application에 저장
}

String phone = request.getParameter("phone");
System.out.println("phone number: " + phone);   

//find a customer having the given phone number
Customer c = custMap.get(phone);	// "custMap"에서 검색
if (c != null) {				// unregistered customer	
	String result = c.getName() + "\n" + c.getAddress();
	System.out.println("result: " + result);
	out.clearBuffer();
	out.print(result);
}
else {
	response.setStatus(400);		// bad request
	response.addHeader("Status", "Unregistered customer");
}

/*
// 참고: Jackson2를 이용한 object-to-JSON 변환

ObjectMapper mapper = new ObjectMapper();
String result2 = mapper.writeValueAsString(c);
System.out.println(result2);		// 변환 결과 확인
*/
%>