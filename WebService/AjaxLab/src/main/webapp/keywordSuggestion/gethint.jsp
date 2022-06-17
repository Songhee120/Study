<%@ page contentType="text/html; charset=utf-8"%>
<%
	// Array with names
	String[] names = { "Anna", "Brittany", "Cinderella", "Diana", "Eva", "Fiona", "Gunda", "Hege", "Inga",
			"Johanna", "Kitty", "Linda", "Nina", "Ophelia", "Petunia", "Amanda", "Raquel", "Cindy", "Doris",
			"Eve", "Evita", "Sunniva", "Tove", "Unni", "Violet", "Liza", "Elizabeth", "Ellen", "Wenche",
			"Vicky" };
	// get the q parameter from URL 
	String q = request.getParameter("q");
	String hint = null;
	if (q != null) {
		// lookup all hints from array  
		q = q.toLowerCase();
		for (int i = 0; i < names.length; i++) {
			if (names[i].toLowerCase().startsWith(q) == true) {
				if (hint == null) hint = names[i];
				else hint = hint + ", " + names[i];
			}
		}
	}
	if (hint == null) hint = "no suggestion";
	pageContext.setAttribute("hint", hint);
%>
${hint}
