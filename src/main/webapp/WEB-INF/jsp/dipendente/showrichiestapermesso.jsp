<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it" class="h-100">
<head>
	<!-- Common imports in pages -->
	<jsp:include page="../header.jsp" />
	<title>Visualizza Richiesta permesso</title>
	
</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp" />
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  	<div class="container">
			
			<div class='card'>
			    <div class='card-header'>
			        Visualizza dettaglio
			    </div>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Tipo permesso:</dt>
					  <dd class="col-sm-9">${show_RichiestaPermesso_attr.tipoPermesso}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Approvato:</dt>
					  <dd class="col-sm-9">${show_RichiestaPermesso_attr.approvato}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Codice certificato:</dt>
					  <dd class="col-sm-9">${show_RichiestaPermesso_attr.codiceCertificato}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">note:</dt>
					  <dd class="col-sm-9">${show_RichiestaPermesso_attr.note}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Data inizio:</dt>
					  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_RichiestaPermesso_attr.dataInizio}" /></dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Data fine:</dt>
					  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_RichiestaPermesso_attr.dataFine}" /></dd>
			    	</dl>
			    	
			    <!-- end card body -->
			    </div>
			    
			    <div class='card-footer'>
			        <a href="${pageContext.request.contextPath }/admin/listDipendenti" class='btn btn-outline-secondary' style='width:80px'>
			            <i class='fa fa-chevron-left'></i> Back
			        </a>
			    </div>
			<!-- end card -->
			</div>	
	
		<!-- end container -->  
		</div>
		
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>