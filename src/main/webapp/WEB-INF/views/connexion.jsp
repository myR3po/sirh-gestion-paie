<%@ page language="java" pageEncoding="UTF-8"%>

<%@include file="inc/header.jsp"%>

<div class="container">

	<div class="mt-4 mb-5">
        <h3 class="display-4 text-center"> Connexion </h3>
    </div>
	
	<!-- Spring Security s'attend aux paramètres "username" et "password" -->
	<form method="post">
		<sec:csrfInput/>
		<input type="text" name="username"/> 
		<input type="password" name="password" /> 
		<input type="submit" value="Se connecter">
	</form>
	
	
	
	<!-- en cas d'erreur un paramètre "error" est créé par Spring Security -->
	<c:if test="${param.error !=null}">
		Erreur d'authentification
	</c:if>
	
</div>
<!-- /.container -->

<%@include file="inc/footer.jsp"%>