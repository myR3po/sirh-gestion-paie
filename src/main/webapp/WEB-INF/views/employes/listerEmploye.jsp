<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="../inc/header.jsp"%>

<div class="container">
	<div class="mt-1">
		<h1>Lister Employé</h1>
	</div>

	
		<a class="btn btn-primary float-right" href="<c:url value="/employes/creer" context="/paie/mvc"/>">Ajouter employé</a>
	
	
	
		<table class="table table-bordered table-responsive-sm">
			<caption>Liste des employés</caption>
			<thead class="thead-default">
				<tr>
					<th>Date/heure de création</th>
					<th>Matricule</th>
					<th>Grade</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="employe" items="${employes}">
					<tr>
						<td><c:out value="${employe.dateCreationFormat}" /></td>
						<td><c:out value="${employe.matricule}" /></td>
						<td><c:out value="${employe.grade.code}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
</div>
<%@include file="../inc/footer.jsp"%>