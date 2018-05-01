<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
			
		<link type="text/css"
			rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/table-data.css" />
		
		
		
	  <script type="text/javascript">
		
			var dataToDraw = []
			
			  window.onload = function () {
			    var chart = new CanvasJS.Chart("chartContainer",
			    {
			
			      title:{
			      text: "Pomiar Temperatury"
			      },
			       data: [
			      {
			        type: "line",
			
			        dataPoints: dataToDraw
			      }
			      ]
			    });
			
			    chart.render();
			  }
	  	</script>
		<script type="text/javascript" src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
  
	</head>
			
<body>
	
	<div class="container">
	
		<div id="ban" class="banner">
			<img width=100% height=100: src="<c:url value="/resources/images/banner.png"/>"/>
    	</div>
	    
	    <div class="header">
		    	<h2>Wszystkie pomiary</h2>
	    </div>
	    
		<div class="asideContent">

			<div class="aside">

				<dl>
					<dt><a href="/login/loginOk">Strona glowna</a></dt>
					<dt><a href="/person/showPerson">Pokaz osoby</a></dt>
					<dt><a href="/login/showLogin">Ostatnie logowania</a></dt>
					<dt><a href="/measurement/showMeasurement">Dostepne pomiary</a></dt>

					<br><br><br>

					<dt><a href="/person/showFormForAdd">Dodaj uzytkownika</a></dt>
					<dt><a href="/">Wyloguj</a></dt>

				</dl>
			</div>
		    
		    <div id="cont" class="content">
		    
		    	
		    	<br><br><br>
		    
		    
				<!-- loop over and print our node --> 
				<c:forEach var="tempNode" items="${actualMeasurement}">
					
					<script>
						<!-- fill array --> 					  
						dataToDraw.push({
							x: ${tempNode.nodeX},
							y: ${tempNode.nodeY}}
						);
					</script>
		
				</c:forEach>
				
				
				<div id="chartContainer" style="height: 300px; width: 100%;">
				</div>
				

				<br><br><br>

				<a href="/measurement/showMeasurement">Strona glowna</a>
				
			</div>
		
		    
		    <div class="footer">
				<h4>Copyright � Damian Polchlopek. All Rights Reserved.</h4>
		    </div>

		</div>
	</div>		
		
	
</body>


</html>
