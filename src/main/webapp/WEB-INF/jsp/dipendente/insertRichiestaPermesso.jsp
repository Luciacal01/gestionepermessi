<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %> 
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
 <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
 <!doctype html> 
 <html lang="it" class="h-100"> 
 	<head> 
 		<jsp:include page="../header.jsp" /> 
 		<style> 
 		    .error_field { 
 		        color: red;  
 		    } 
 		</style> 
		
 		<title>Inserisci nuova richiesta</title> 
	    
 	</head> 
 	<body class="d-flex flex-column h-100"> 
 		<jsp:include page="../navbar.jsp" /> 
	
 		<!-- Begin page content --> 
 		<main class="flex-shrink-0"> 
 			<div class="container"> 
	
 					<spring:hasBindErrors  name="insert_RichiestaPermesso_attr"> 
 						alert errori 
 						<div class="alert alert-danger " role="alert"> 
 							Attenzione!! Sono presenti errori di validazione 
 						</div> 
 					</spring:hasBindErrors> 
			
 					<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
 					  ${errorMessage} 
 					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button> 
 					</div> 
					
 					<div class='card'> 
 					    <div class='card-header'> 
 					        <h5>Inserisci nuovo dipendente</h5>  
 					    </div> 
 					    <div class='card-body'> 
					    
 					    		<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6> 
			
 								<form:form method="post" modelAttribute="insert_Richiestapermesso_attr" action="saveRichiestaPermesso" novalidate="novalidate" class="row g-3">
									
 									<div class="col-md-12"> 
 										<label for="tipoPermesso" class="form-label">Tipo Permesso: <span class="text-danger">*</span></label> 
 									    <spring:bind path="tipoPermesso"> 
 										    <select class="form-select ${status.error ? 'is-invalid' : ''}" id="tipoPermesso" name="tipoPermesso" required> 
 										    	<option value="" selected> - Selezionare - </option> 
 										      	<option value="FERIE" ${insert_RichiestaPermesso_attr.tipoPermesso == 'FERIE'?'selected':''} >FERIE</option> 
 										      	<option value="MALATTIA" ${insert_RichiestaPermesso_attr.tipoPermesso == 'MALATTIA'?'selected':''} >MALATTIA</option> 
 										    </select>
 									    </spring:bind> 
 									    <form:errors  path="tipoPermesso" cssClass="error_field" /> 
 									</div> 
								
 									<div class="col-md-6" id= "codiceCertificato"> 
 										<label for="codiceCertificato" class="form-label">Codice certificato</label> 
 										<spring:bind path="codiceCertificato"> 
 											<input type="text" name="codiceCertificato" id="codiceCertificato" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il codice del certificato" value="${insert_RichiestaPermesso_attr.codiceCertificato }">
 										</spring:bind> 
 										<form:errors  path="codiceCertificato" cssClass="error_field" /> 
 									</div> 
										
 									<form> 
 									  <div class="form-group" id= "codiceCertificato"> 
 									    <label for="attachment">Certificato allegato</label> 
 									    <spring:bind path="codiceCertificato"> 
 											<input type="file" name="attachment" id="attachment" class="form-control-file" placeholder="Inserire il certificato" value="${insert_RichiestaPermesso_attr.attachment }"> 
 										</spring:bind>
 									  </div> 
 									</form> 
									
 									<div class="col-md-12"> 
 									  <input class="form-check-input" type="checkbox"  id="giornoSingolo" name="giornoSingolo"> 
 									  <label class="form-check-label" for="giornoSingolo">Giorno Singolo</label> 
 									</div> 
								
 									<div class="col-md-6">
 										<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${insert_RichiestaPermesso_attr.dataInizio}' /> 
 										<div class="form-group col-md-6"> 
 											<label for="dataInizio" class="form-label">Data di inizio</label> 
 			                        		<spring:bind path="dataInizio"> 
 				                        		<input class="form-control ${status.error ? 'is-invalid' : ''}" id="dataInizio" type="date" placeholder="dd/MM/yy" 
 				                            		title="formato : gg/mm/aaaa"  name="dataInizio" value="${parsedDate}" > 
 				                            </spring:bind> 
 			                            	<form:errors  path="dataInizio" cssClass="error_field" /> 
 										</div> 
 									</div> 
									
 									<div class="col-md-6">
 										<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${insert_RichiestaPermesso_attr.dataFine}' /> 
 										<div class="form-group col-md-6"> 
 											<label for="dataFine" class="form-label">Data di fine validità</label> 
 			                        		<spring:bind path="dataFine"> 
 				                        		<input class="form-control ${status.error ? 'is-invalid' : ''}" id="dataFine" type="date" placeholder="dd/MM/yy" 
 				                            		title="formato : gg/mm/aaaa"  name="dataFine" value="${parsedDate}" > 
 				                            </spring:bind> 
 			                            	<form:errors  path="dataFine" cssClass="error_field" /> 
 										</div> 
 									</div> 
									
 									<div class="form-group"> 
 									   <label for="note">Note</label> 
 									   <spring:bind path="codiceCertificato"> 
 											<textarea class="form-control${status.error ? 'is-invalid' : ''}" id="note" value="${insert_RichiestaPermesso_attr.note }" rows="3"></textarea> 
 										</spring:bind> 
 									</div> 
									
									
 			<!-- 						<div class="form-row">	 --> 
 			<!-- 							<div class="form-group col-md-6"> --> 
 			<!-- 								<label for="regista.id">Regista</label> --> 
 			<!-- 							    <select class="form-control" id="regista.id" name="regista.id"> --> 
 			<!-- 							    	<option value="" selected> -- Selezionare una voce -- </option> --> 
 			<%-- 							      	<c:forEach items="${registi_list_attribute }" var="registaItem"> --%> 
 			<%-- 							      		<option value="${registaItem.id}" ${insert_film_attr.regista.id == registaItem.id?'selected':''} >${registaItem.nome } ${registaItem.cognome }</option> --%> 
 			<%-- 							      	</c:forEach> --%> 
 			<!-- 							    </select> --> 
 			<!-- 							</div> --> 
 			<!-- 						</div> --> 
 									<div class="col-12"> 
 										<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button> 
 										<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
 									</div>	
 								</form:form> -
								
 								<script type="text/javascript"> 
 										$(document).ready(function(){
 											if($("#tipoPermesso").val() == "MALATTIA"){
												
 												$("#codiceCertificato").show();
 												$("#attachment").show();
 											}
 											else{
												
 												$("#codiceCertificato").hide();
 												$("#attachment").hide();
 											}
										
 											$("#tipoPermesso").change(function() {
												
 												if($("#tipoPermesso").val() == "MALATTIA"){
													
 													$("#codiceCertificato").show();
 													$("#attachment").show();
 												}
 												else{
													
 													$("#codiceCertificato").hide();
 													$("#attachment").hide();
 												}
 											});
											
 										});
 										</script> 
										
 										<script type="text/javascript"> 
 											$(document).ready(function(){
 												if($("#giornoSingolo").is(':checked')){
													
 													$("#dataFine").attr("disabled","disabled");
 												}
 												else{
 													$("#dataFine").removeAttr("disabled");
 												}
												
 												$("#giornoSingolo").change(function() {
													
 													if($("#giornoSingolo").is(':checked')){
														
 														$("#dataFine").attr("disabled","disabled");
 													}
 													else{
 														$("#dataFine").removeAttr("disabled");
 													}
 												});
												
 											});
											</script> 
								
					    
 						end card-body			    
 					    </div> 
 					end card 
 					</div> 
 				end container 
 				</div>	 
		
 		<!-- end main -->	 
 		</main> 
 		<jsp:include page="../footer.jsp" /> 
		
 	</body>
</html>