<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100">
<head>
	<jsp:include page="../header.jsp" />
	<title>Pagina dei risultati</title>
	
</head>
<body class="d-flex flex-column h-100">
	<jsp:include page="../navbar.jsp" />
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  <div class="container">
	
			<div class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }" role="alert">
			  ${successMessage}
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
			</div>
			<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
			  ${errorMessage}
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
			</div>
			
			<div class='card'>
			    <div class='card-header'>
			        <h5>Lista dei risultati</h5> 
			    </div>
			    <div class='card-body'>
			    	<a href="${pageContext.request.contextPath}/backoffice/searchDipendente" class='btn btn-outline-secondary' >
				            <i class='fa fa-chevron-left'></i> Torna alla Ricerca
				        </a>
			    
			        <div class='table-responsive'>
			            <table class='table table-striped ' >
			                <thead>
			                    <tr>
			                        <th>Tipo permesso</th>
			                        <th>Data inizio</th>
			                        <th>Data fine</th>
			                        <th>Codice certificato</th>
			                        <th>Note</th>
			                        <th>Azioni</th>
			                    </tr>
			                </thead>
			                <tbody>
			                	<c:forEach items="${richiestepermessi_list_attribute }" var="richiestapermessoItem">
									<tr>
										<td>${richiestapermessoItem.tipoPermesso }</td>
										<td>${richiestapermessoItem.dataInizio }</td>
										<td>${richiestapermessoItem.dataFine }</td>
										<td>${richiestapermessoItem.codiceCertificato }</td>
										<td>${richiestapermessoItem.note }</td>
										
										<td>
											<a class="btn btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/backoffice/showdipendente/${dipendenteItem.id}">Visualizza</a>
											<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="${pageContext.request.contextPath}/backoffice/editDipendente/${dipendenteItem.id}">Edit</a>
											<a id="changeStatoLink_#_${dipendenteItem.id }" class="btn btn-outline-${dipendenteItem.isAttivo()?'danger':'success'} btn-sm link-for-modal" data-bs-toggle="modal" data-bs-target="#confirmOperationModal"  >${dipendenteItem.isAttivo()?'Disabilita':'Abilita'}</a>
<%-- 											<a id="resetPasswordLink_#_${utenteItem.id}" class="btn btn-outline-success btn-sm link-for-modal" data-bs-toggle="modal" data-bs-target="#confirmOperationModalPassword"  >Reset Password</a> --%>
										</td>
									</tr>
								</c:forEach>
			                </tbody>
			            </table> 
			        </div>
			   
				<!-- end card-body -->			   
			    </div>
			</div>	
	
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
	
	
	<!-- Modal -->
	<div class="modal fade" id="confirmOperationModal" tabindex="-1"  aria-labelledby="confirmOperationModalLabel"
	    aria-hidden="true">
	    <div class="modal-dialog" >
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="confirmOperationModalLabel">Conferma Operazione</h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	            </div> 
	            <div class="modal-body">
	                Continuare con l'operazione?
	            </div>
	            <form method="post" action="${pageContext.request.contextPath}/backoffice/cambiaStato" >
		            <div class="modal-footer">
		            	<input type="hidden" name="idDipendenteForChangingStato" id="idDipendenteForChangingStato">
		                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Chiudi</button>
		                <input type="submit" value="Continua"  class="btn btn-primary">
		            </div>
	            </form>
	        </div>
	    </div>
	</div>
	<!-- end Modal -->
	<script type="text/javascript">
		<!-- aggancio evento click al conferma del modal  -->
		$(".link-for-modal").click(function(){
			<!-- mi prendo il numero che poi sarà l'id. Il 18 è perché 'changeStatoLink_#_' è appunto lungo 18  -->
			var callerId = $(this).attr('id').substring(18);
			<!-- imposto nell'hidden del modal l'id da postare alla servlet -->
			$('#idDipendenteForChangingStato').val(callerId);
		});
	</script>
	
	<!-- Modal -->
	<div class="modal fade" id="confirmOperationModalPassword" tabindex="-1"  aria-labelledby="confirmOperationModalLabel"
	    aria-hidden="true">
	    <div class="modal-dialog" >
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="confirmOperationModalLabel">Conferma Operazione</h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	            </div> 
	            <div class="modal-body">
	                Continuare con l'operazione?
	            </div>
	            <form method="post" action="${pageContext.request.contextPath}/admin/resetPassword" >
		            <div class="modal-footer">
		            	<input type="hidden" name="idUtenteForResetpassword" id="idUtenteForResetpassword">
		                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Chiudi</button>
		                <input type="submit" value="Continua"  class="btn btn-primary">
		            </div>
	            </form>
	        </div>
	    </div>
	</div>
	<!-- end Modal -->
	<script type="text/javascript">
		<!-- aggancio evento click al conferma del modal  -->
		$(".link-for-modal").click(function(){
			<!-- mi prendo il numero che poi sarà l'id. Il 18 è perché 'changeStatoLink_#_' è appunto lungo 18  -->
			var callerId = $(this).attr('id').substring(20);
			<!-- imposto nell'hidden del modal l'id da postare alla servlet -->
			$('#idUtenteForResetpassword').val(callerId);
		});
	</script>
	
</body>
</html>