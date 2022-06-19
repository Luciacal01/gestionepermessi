<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100" >
	<head>
		<jsp:include page="../header.jsp" />
		<style>
			    .error_field {
			        color: red; 
			    }
			</style>
		<title>Ricerca</title>
		
	    
	</head>
	<body class="d-flex flex-column h-100">
		<!-- Fixed navbar -->
		<jsp:include page="../navbar.jsp"></jsp:include>
		
		<!-- Begin page content -->
		<main class="flex-shrink-0">
		  <div class="container">
		  
		  	<%-- se l'attributo in request ha errori --%>
						<spring:hasBindErrors  name="search_utente_attr">
							<%-- alert errori --%>
							<div class="alert alert-danger " role="alert">
								Attenzione!! Sono presenti errori di validazione
							</div>
						</spring:hasBindErrors>
				  
				  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
						  ${errorMessage}
						  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
						</div>
		
				<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
				  ${errorMessage}
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
				    <span aria-hidden="true">&times;</span>
				  </button>
				</div>
				
				<div class='card'>
				    <div class='card-header'>
				        <h5>Ricerca elementi</h5> 
				    </div>
				    <div class='card-body'>
		
							<form  method="post" action="${pageContext.request.contextPath}/Dipendente/listRichieste" class="row g-3">
								
								<div class="col-md-12">
									<label for="tipoPermesso" class="form-label">Tipo Permesso:</label>
									    <select class="form-select " id="tipoPermesso" name="tipoPermesso" >
									    	<option value="" selected> - Selezionare - </option>
									      	<option value="MALATTIA" >MALATTIA</option>
									    	<option value="FERIE">FERIE</option>
								    	</select>
								</div>
								
								<div class="col-md-6">
									<label for="codiceCertificato" class="form-label">Codice Certificato</label>
									<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il Codice del Certificato" >
								</div>
			
								<div class="col-md-6">
									<label for="dataInizio" class="form-label">Data di Inizio</label>
	                        		<input class="form-control" id="dateInizio" type="date" placeholder="dd/MM/yy"
	                            		title="formato : gg/mm/aaaa"  name="dataInizio" >
								</div>
								
								<div class="col-md-6">
									<label for="dataFine" class="form-label">Data di Fine</label>
	                        		<input class="form-control" id="dataFine" type="date" placeholder="dd/MM/yy"
	                            		title="formato : gg/mm/aaaa"  name="dataFine" >
								</div>
								
								
								<div class="col-12"> 
 									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button> 
 									<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
 								</div>	
								
	<%-- 							 checkbox ruoli 	 --%>
	<%-- 								facendolo con i tag di spring purtroppo viene un po' spaginato quindi aggiungo class 'a mano'	 --%>
	<!-- 								<div class="col-md-6 form-check" id="ruoliDivId"> -->
	<!-- 									<p>Ruoli:</p> -->
	<%-- 									<c:forEach var="ruoloItem" items="${ruoli_totali_attr}"> --%>
	<!-- 										<div class='form-check'> -->
	<%-- 											 <input name="ruoliIds" class="form-check-input" type="checkbox" value="${ruoloItem.id }" id="flexCheckDefault-${ruoloItem.id }"> --%>
	<%-- 											  <label class="form-check-label" for="flexCheckDefault-${ruoloItem.id }"> --%>
	<%-- 											   ${ruoloItem.codice } --%>
	<!-- 											  </label> -->
	<!-- 										</div> -->
	<%-- 									</c:forEach> --%>
	<!-- 								</div> -->
	<%-- 								fine checkbox ruoli 	 --%>
								
								
		
								
							</form>
				    
					<!-- end card-body -->			   
				    </div>
				</div>	
		
			</div>
		<!-- end container -->	
		</main>
		<jsp:include page="../footer.jsp" />
		
	</body>
</html>