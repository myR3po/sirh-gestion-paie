<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="../inc/header.jsp"%>

<div class="container">

	<div class="mt-4 mb-5">
        <h3 class="display-4 text-center"> Liste des employés </h3>
    </div>

	<div class="row mb-4">
		<div class="col-12">			
			<a class="btn btn-primary float-right" role="button" href="<c:url value="/employes/creer" context="/paie/mvc"/>">Ajouter employé</a>
		</div>
	</div>

	<div class="row">
		<div class="col-12">	
			<table class="table table-bordered table-responsive-sm">
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
	</div>
</div>
<%@include file="../inc/footer.jsp"%>