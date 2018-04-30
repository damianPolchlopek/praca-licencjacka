<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

	<head>
	
	<title> Strona glowna</title>
	
	<!-- reference our style sheet -->
	
	<link type="text/css"
			rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/background.css" />
	
	<link type="text/css"
			rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/main-panel.css" />
			
	</head>
			
<body>
	
	<div class="container">
	
		<div id="ban" class="banner">
			<img width=100% height=100: src="<c:url value="/resources/images/banner.png"/>"/>
    	</div>
	    
	    <div class="header">
		    	<h2>STRONA GLOWNA APLIKACJI</h2>
	    </div>
	    
		<div class="asideContent">
	
			<div class="aside">

				<dl>
					<dt><a href="/praca-licencjacka/login/loginOk">Strona glowna</a></dt>
					<dt><a href="/praca-licencjacka/person/showPerson">Pokaz osoby</a></dt>
					<dt><a href="/praca-licencjacka/login/showLogin">Ostatnie logowania</a></dt>
					<dt><a href="/praca-licencjacka/measurement/showMeasurement">Dostepne pomiary</a></dt>
					
					<br><br><br>
					
					<dt><a href="/praca-licencjacka/person/showFormForAdd">Dodaj uzytkownika</a></dt>
					<dt><a href="/praca-licencjacka">Wyloguj</a></dt>

				</dl>
		    </div>
		    
		    <div id="outer" class="content">
				<div id="inner">
					<label id="tekstDiv">Czesc glowna systemu</label>
				</div>	
		    </div>
	    
	    </div>
	    
	    <div class="footer">
			<h4>Copyright © Damian Polchlopek. All Rights Reserved.</h4>
	    </div>


	</div>

	
</body>


</html>
