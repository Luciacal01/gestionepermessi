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
		
							<form  method="post" action="${pageContext.request.contextPath}/admin/listAllDipendenti" class="row g-3">
							
								<div class="col-md-6">
									<label for="nome" class="form-label">Nome</label>
									<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" >
								</div>
								
								<div class="col-md-6">
									<label for="cognome" class="form-label">Cognome</label>
									<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" >
								</div>
								
								<div class="col-md-6">
									<label for="codiceFiscale" class="form-label">Codice Fiscale</label>
									<input type="text" name="codiceFiscale" id="codiceFiscale" class="form-control" placeholder="Inserire il codice fiscale" >
								</div>
								
								<div class="col-md-6">
									<label for="email" class="form-label">Email</label>
									<input type="text" class="form-control" name="email" id="email" placeholder="Inserire email" >
								</div>
								
								<div class="col-md-6">
									<label for="dataNascita" class="form-label">Data di Nascita</label>
	                        		<input class="form-control" id="dateCreated" type="date" placeholder="dd/MM/yy"
	                            		title="formato : gg/mm/aaaa"  name="dataNascita" >
								</div>
								
								<div class="col-md-6">
									<label for="dataAssunzione" class="form-label">Data di Assunzione</label>
	                        		<input class="form-control" id="dataAssunzione" type="date" placeholder="dd/MM/yy"
	                            		title="formato : gg/mm/aaaa"  name="dataAssunzione" >
								</div>
								
								<div class="col-md-6">
									<label for="dataDimissioni" class="form-label">Data di Dimissione</label>
	                        		<input class="form-control" id="dataDimissioni" type="date" placeholder="dd/MM/yy"
	                            		title="formato : gg/mm/aaaa"  name="dataDimissioni" >
								</div>
								
								<div class="col-md-3">
									<label for="sesso" class="form-label">Sesso</label>
									    <select class="form-select " id="sesso" name="sesso" >
									    	<option value="" selected> - Selezionare - </option>
									      	<option value="M" >MASCHIO</option>
									    	<option value="F">FEMMINA</option>
								    	</select>
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
								
								<div class="col-12">	
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
									<input class="btn btn-outline-warning" type="reset" value="Ripulisci">

								</div>
		
								
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