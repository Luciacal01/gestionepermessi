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
		
	    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jqueryUI/jquery-ui.min.css" />
		<style>
			.ui-autocomplete-loading {
				background: white url("../assets/img/jqueryUI/anim_16x16.gif") right center no-repeat;
			}
			.error_field {
		        color: red; 
		    }
		</style>
		<title>Inserisci nuovo dipendente</title>
	    
	</head>
	<body class="d-flex flex-column h-100">
		<jsp:include page="../navbar.jsp" />
		
		<!-- Begin page content -->
		<main class="flex-shrink-0">
			<div class="container">
		
<%-- 					se l'attributo in request ha errori --%>
<%-- 					<spring:hasBindErrors  name="film_regista_attr"> --%>
<%-- 						alert errori --%>
<!-- 						<div class="alert alert-danger " role="alert"> -->
<!-- 							Attenzione!! Sono presenti errori di validazione -->
<!-- 						</div> -->
<%-- 					</spring:hasBindErrors> --%>
				
					<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">   
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					
					<div class='card'>
					    <div class='card-header'>
					        <h5>Inserisci nuovo dipendente</h5> 
					    </div>
					    <div class='card-body'>
			
								<form:form method="post" modelAttribute="insert_dipendente_attr" action="saveDipendente" novalidate="novalidate" class="row g-3">
								
									<div class="col-md-6">
										<label for="nome" class="form-label">Nome</label>
										<spring:bind path="nome">
											<input type="text" name="nome" id="nome" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il nome" value="${insert_dipendente_attr.nome }">
										</spring:bind>
										<form:errors  path="nome" cssClass="error_field" />
									</div>
										
									<div class="col-md-6">
										<label for="cognome" class="form-label">Cognome</label>
										<spring:bind path="cognome">
											<input type="text" name="cognome" id="cognome" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il cognome" value="${insert_dipendente_attr.cognome }">
										</spring:bind>
										<form:errors  path="cognome" cssClass="error_field" />
									</div>
									
									<div class="col-md-6">
										<label for="codiceFiscale" class="form-label">Codice Fiscale</label>
										<spring:bind path="codiceFiscale">
											<input type="text" name="codiceFiscale" id="codiceFiscale" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il codice fiscale" value="${insert_dipendente_attr.codiceFiscale }">
										</spring:bind>
										<form:errors  path="codiceFiscale" cssClass="error_field" />
									</div>
									
									<div class="col-md-6">	
										<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${insert_dipendente_attr.dataNascita}' />
										<div class="form-group col-md-6">
											<label for="dataNascita" class="form-label">Data di nascita</label>
			                        		<spring:bind path="dataNascita">
				                        		<input class="form-control ${status.error ? 'is-invalid' : ''}" id="dataNascita" type="date" placeholder="dd/MM/yy"
				                            		title="formato : gg/mm/aaaa"  name="dataNascita" value="${parsedDate}" >
				                            </spring:bind>
			                            	<form:errors  path="dataNascita" cssClass="error_field" />
										</div>
									</div>
									
									<div class="col-md-6">	
										<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${insert_dipendente_attr.dataAssunzione}' />
										<div class="form-group col-md-6">
											<label for="dataAssunzione" class="form-label">Data di assunzione</label>
			                        		<spring:bind path="dataAssunzione">
				                        		<input class="form-control ${status.error ? 'is-invalid' : ''}" id="dataAssunzione" type="date" placeholder="dd/MM/yy"
				                            		title="formato : gg/mm/aaaa"  name="dataAssunzione" value="${parsedDate}" >
				                            </spring:bind>
			                            	<form:errors  path="dataAssunzione" cssClass="error_field" />
										</div>
									</div>
									
									<div class="col-md-6">	
										<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${insert_dipendente_attr.dataDimissioni}' />
										<div class="form-group col-md-6">
											<label for="dataDimissioni" class="form-label">Data di dimissioni</label>
			                        		<spring:bind path="dataDimissioni">
				                        		<input class="form-control ${status.error ? 'is-invalid' : ''}" id="dataDimissioni" type="date" placeholder="dd/MM/yy"
				                            		title="formato : gg/mm/aaaa"  name="dataDimissioni" value="${parsedDate}" >
				                            </spring:bind>
			                            	<form:errors  path="dataDimissioni" cssClass="error_field" />
										</div>
									</div>
										
									<div class="col-md-3">
									<label for="sesso" class="form-label">Sesso <span class="text-danger">*</span></label>
								    <spring:bind path="sesso">
									    <select class="form-select ${status.error ? 'is-invalid' : ''}" id="sesso" name="sesso" required>
									    	<option value="" selected> - Selezionare - </option>
									      	<option value="MASCHIO" ${insert_regista_attr.sesso == 'MASCHIO'?'selected':''} >M</option>
									      	<option value="FEMMINA" ${insert_regista_attr.sesso == 'FEMMINA'?'selected':''} >F</option>
									    </select>
								    </spring:bind>
								    <form:errors  path="sesso" cssClass="error_field" />
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
									</div>
										
									
								</form:form>	
								
					    
						<!-- end card-body -->			   
					    </div>
					<!-- end card -->
					</div>
				<!-- end container -->
				</div>	
		
		<!-- end main -->	
		</main>
		<jsp:include page="../footer.jsp" />
		
	</body>
</html>