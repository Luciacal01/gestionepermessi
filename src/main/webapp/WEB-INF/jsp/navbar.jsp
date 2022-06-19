<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header>
  <!-- Fixed navbar -->
 <nav class="navbar navbar-expand-lg navbar-dark bg-primary" aria-label="Eighth navbar example">
    <div class="container">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample07" aria-controls="navbarsExample07" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExample07">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/home">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="dropdown07" data-bs-toggle="dropdown" aria-expanded="false">Dropdown</a>
            <ul class="dropdown-menu" aria-labelledby="dropdown07">
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/home">Home</a></li>
            </ul> 
          </li>
           <sec:authorize access="hasRole('ADMIN')">
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Gestione Utenze</a>
		        <div class="dropdown-menu" aria-labelledby="dropdown01">
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/listUtente">Lista utente</a>
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/searchUtente">Ricerca Utenti</a>
		        </div>
		      </li>
		      
		      <li class="nav-item">
            		<a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/admin/searchDipendente">Ricerca Richieste Permesso</a>
          	  </li>
		   </sec:authorize>
		   
		   <sec:authorize access="hasRole('BO_USER')">
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Gestione Utenze</a>
		        <div class="dropdown-menu" aria-labelledby="dropdown01">
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/backoffice/listDipendenti">Lista dipendenti</a>
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/backoffice/insertDipendente">Inserisci dipendente</a>
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/backoffice/searchDipendente">Ricerca Dipendente</a>
		        </div>
		      </li>
		      
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Gestione Messaggi</a>
		        <div class="dropdown-menu" aria-labelledby="dropdown01">
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/messaggio/">Lista messaggi</a>
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/messaggio/search">Ricerca Messaggi</a>
		        </div>
		      </li>
		   </sec:authorize>
		   
		   <sec:authorize access="hasRole('DIPENDENTE_USER')">
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Gestione Richieste permesso</a>
		        <div class="dropdown-menu" aria-labelledby="dropdown01">
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/richiestapermesso/">lista richieste permessi</a>
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/richiestapermesso/search">Ricerca richieste permessi</a>
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/Dipendente/insertRichiesta">Inserisci richiesta permesso</a>
		        </div>
		      </li>
		      
		   </sec:authorize>
		   
        </ul>
      </div>
      <sec:authorize access="isAuthenticated()">
	      <div class="col-md-3 text-end">
	       	<p class="navbar-text">Utente: ${userInfo.username } (${userInfo.getDipendenteDTO().nome } ${userInfo.getDipendenteDTO().cognome } )
	      </div>
      </sec:authorize>
      
      <div class="collapse navbar-collapse text-right" id="navbarsExample07">
      	<ul class="navbar-nav me-auto mb-2 mb-lg-0">
      		   <sec:authorize access="isAuthenticated()">
			   <li class="nav-item dropdown ">
		        <a class="nav-link dropdown-toggle " href="#" id="dropdown01" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Account</a>
		        <div class="dropdown-menu " aria-labelledby="dropdown01">
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a>
		          <a class="dropdown-item"  href="${pageContext.request.contextPath}/reset">Reset Password</a>
		        </div>
		      </li>
      		</sec:authorize>	
      	</ul>
      </div>
     
    </div>
  </nav>
  
  
</header>
