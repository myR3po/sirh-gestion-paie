<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="../inc/header.jsp"%>

<div class="container">

<c:out value="${errMsg}" />
<a class="nav-link" href="<c:url value="/mvc/"/>">revenir en arrière</a>
</div>
<%@include file="../inc/footer.jsp"%>