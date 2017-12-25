<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="../inc/header.jsp"%>

<div class="container">

	<div class="mt-4 mb-5">
        <h3 class="display-4 text-center"> Liste des Bulletins </h3>
    </div>

	<div class="row mb-4">
		<div class="col-12">
			<a class="btn btn-primary float-right" role="button" href="<c:url value="/bulletins/creer" context="/paie/mvc"/>">Créer un nouveau bulletin</a>
		</div>
	</div>
	

	<table class="table thead-default table-bordered">
		<thead>
			<tr>
				<th>Date/heure de création</th>
				<th>Période</th>
				<th>Matricule</th>
				<th>Salaire brut</th>
				<th>Net Imposable</th>
				<th>Net A Payer</th>
				<th>Actions</th>
			</tr>
		</thead>
				
		<tbody>
			<c:forEach var="bulletin" items="${bulletins}">
				<tr>
					<td><c:out value="${bulletin.key.dateCreationFormat}" /></td>
					<td><c:out value="${bulletin.key.periode}" /></td>
					<td><c:out value="${bulletin.key.remunerationEmploye.matricule}" /></td>
					<td><c:out value="${bulletin.value.salaireBrut}" /></td>
					<td><c:out value="${bulletin.value.netImposable}" /></td>
					<td><c:out value="${bulletin.value.netAPayer}" /></td>
					<td><a href="<c:url value="/bulletins/visualiser" context="/paie/mvc">
								  	<c:param name="id" value="${bulletin.key.id}"/>
								 </c:url>
					">visualiser</a> </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<%@include file="../inc/footer.jsp"%>