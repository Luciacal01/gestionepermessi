<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="it" class="h-100" >
	<head>

	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	
	   <title>Cancella Elemento</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Cancella elemento</h5>
					    </div>
					    
						<form:form method="post" action="${pageContext.request.contextPath}/Dipendente/removeRichiestaPermesso" class="row g-3" novalidate="novalidate">
					    
						    <div class='card-body'>
						    	<dl class="row">
								  <dt class="col-sm-3 text-right">Id:</dt>
								  <dd class="col-sm-9">${delete_richiestapermesso_attr.id}</dd>
						    	</dl>
						    	
						    	<dl class="row">
								  <dt class="col-sm-3 text-right">Tipo Permesso :</dt>
								  <dd class="col-sm-9">${delete_satellite_attr.tipoPermesso}</dd>
						    	</dl>
						    	
						    	<dl class="row">
								  <dt class="col-sm-3 text-right">Note:</dt>
								  <dd class="col-sm-9">${delete_richiestapermesso_attr.note}</dd>
						    	</dl>
						    	
						    	<dl class="row">
								  <dt class="col-sm-3 text-right">Codice Certificato:</dt>
								  <dd class="col-sm-9">${delete_richiestapermesso_attr.codiceCertificato}</dd>
						    	</dl>
						    	
						    	<dl class="row">
								  <dt class="col-sm-3 text-right">Data di Inizio:</dt>
								  <dd class="col-sm-9"><fmt:formatDate type="date" value = "${delete_richiestapermesso_attr.dataInizio}" /></dd>
								  <form:errors  path="dataLancio" cssClass="error_field" />
						    	</dl>
						    	
						    	<dl class="row">
								  <dt class="col-sm-3 text-right">Data di Fine:</dt>
								  <dd class="col-sm-9"><fmt:formatDate type="date" value = "${delete_richiestapermesso_attr.dataFine}" /></dd>
								  <form:errors  path="dataRientro" cssClass="error_field" />
						    	</dl>

						    	
						    	
						    </div>
					    <!-- end card body -->
					    
					    <div class="col-12">
					    	<input type="hidden" name="idRichiestapermesso" value="${delete_richiestapermesso_attr.id }"> 
						    <button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
					    </div>
					<!-- end card -->
					</form:form>
					</div>	
					
			  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>