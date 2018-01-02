<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>PAIE</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<body>
	<nav
		class="navbar navbar-toggleable-md navbar-inverse bg-inverse">
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarCollapse"
			aria-controls="navbarCollapse" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<a class="navbar-brand" href="<c:url value="/mvc/"/>">SIRH
			GESTION-PAIE</a>
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="<c:url value="/employes/lister" context="/paie/mvc"/>">Employés </a></li>
				<li class="nav-item"><a class="nav-link" href="<c:url value="/bulletins/lister" context="/paie/mvc"/>">Bulletins</a></li>
			</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="isAuthenticated()">
					<li class="nav-item"> <a class="nav-link" href="#">Hello, <sec:authentication property="principal.username" /></a></li>
					<li class="nav-item"> <a class="nav-link" href="<c:url value="/logout" context="/paie/mvc"/>">logout</a></li>
				</sec:authorize>
		    </ul>
			
		</div>
	</nav>
	
	
	
	${page.request.contextPath}