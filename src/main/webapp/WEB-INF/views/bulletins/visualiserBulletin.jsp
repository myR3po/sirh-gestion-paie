<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="../inc/header.jsp"%>

<div class="container-fluid">
	<div class="mt-1">
		<h1>Bulletin de Salaire</h1>
	</div>
	
	<div class="row marketing">
        <div class="col-lg-6">
        
			<p></p>
			<p></p>
			<h4>Entreprise</h4>
			<p> <c:out value="${bulletinView.bulletin.remunerationEmploye.entreprise.denomination}" /></p>
			<p>Siret : <c:out value="${bulletinView.bulletin.remunerationEmploye.entreprise.siret}" /></p>             
        </div>

        <div class="col-lg-6">
          <h4>Periode</h4>
          <p>Du <c:out value="${bulletinView.bulletin.periode.dateDebut}" /> au <c:out value="${bulletinView.bulletin.periode.dateFin}" /></p>

          <h4></h4>
          <p>Matricule : <c:out value="${bulletinView.bulletin.remunerationEmploye.matricule}" /></p>
        </div>
      </div>

	<h3>Salaire</h3>
	<table class="table table-striped table-bordered">
		<thead class="thead-default">
			<tr>
				<th>Rubriques</th>
				<th>Base</th>
				<th>Taux Salarial</th>
				<th>Montant Salarial</th>
				<th>Taux patronal</th>
				<th>Cot patronales</th>
			</tr>
		</thead>
		

		
		<tbody>
				<tr>
					<td><c:out value="Salaire de base" /></td>
					<td><c:out value="${bulletinView.bulletin.remunerationEmploye.grade.nbHeuresBase}" /></td>
					<td><c:out value="${bulletinView.bulletin.remunerationEmploye.grade.tauxBase}" /></td>
					<td><c:out value="${bulletinView.calculRemuneration.salaireDeBase}" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="${bulletinView.calculRemuneration.totalCotisationsPatronales}" /></td>
				</tr>
				
				<tr>
					<td><c:out value="Prime excep." /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="${bulletinView.bulletin.primeExceptionnelle}" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
				</tr>
				
				<tr>
					<td><c:out value=" " /></td>
					<td><c:out value=" " /></td>
					<td><c:out value=" " /></td>
					<td><c:out value=" " /></td>
					<td><c:out value=" " /></td>
					<td><c:out value=" " /></td>
				</tr>
				
				<tr>
					<td><c:out value="Salaire Brut" /></td>
					<td></td>
					<td></td>
					<td><c:out value="${bulletinView.calculRemuneration.salaireBrut}" /></td>
					<td><c:out value="" /></td>
					<td></td>
				</tr>
				
		</tbody>
	</table>
	
	<h3>Cotisations</h3>
	<table class="table table-striped table-bordered">
		<thead class="thead-default">
			<tr>
				<th>Rubriques</th>
				<th>Base</th>
				<th>Taux Salarial</th>
				<th>Montant Salarial</th>
				<th>Taux patronal</th>
				<th>Cot patronales</th>
			</tr>
		</thead>
		<tbody>		
			<c:forEach var="row" items="${bulletinView.cotisationsNonImposables}">
				<tr>
					<td><c:out value="${row.rubrique}" /></td>
					<td><c:out value="${bulletinView.calculRemuneration.salaireBrut}" /></td>
					<td><c:out value="${row.tauxSalarial}" /></td>
					<td><c:out value="${row.montantSalarial}" /></td>
					<td><c:out value="${row.tauxPatronal}" /></td>
					<td><c:out value="${row.cotisationsPatronales}" /></td>
				</tr>
			</c:forEach>
			
				<tr>
					<td><c:out value="Total Retenue" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="${bulletinView.calculRemuneration.totalRetenueSalarial}" /></td>
					<td><c:out value=" " /></td>
					<td><c:out value="${bulletinView.calculRemuneration.totalCotisationsPatronales}" /></td>
				</tr>
		</tbody>
	</table>

	<h3>Net Imposable : <c:out value="${bulletinView.calculRemuneration.netImposable}" /></h3>
	<table class="table table-striped table-bordered">
		<thead class="thead-default">
			<tr>
				<th>Rubriques</th>
				<th>Base</th>
				<th>Taux Salarial</th>
				<th>Montant Salarial</th>
				<th>Taux patronal</th>
				<th>Cot patronales</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="cotisation" items="${bulletinView.cotisationsImposables}">
				<tr>
					<td><c:out value="${cotisation.rubrique}" /></td>
					<td><c:out value="${bulletinView.calculRemuneration.salaireBrut}" /></td>
					<td><c:out value="${cotisation.tauxSalarial}" /></td>
					<td><c:out value="${cotisation.montantSalarial}" /></td>
					<td><c:out value="${cotisation.tauxPatronal}" /></td>
					<td><c:out value="${cotisation.cotisationsPatronales}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<h3 class="float-right">Net A PAYER : <c:out value="${bulletinView.calculRemuneration.netAPayer}" /></h3>
</div>
<%@include file="../inc/footer.jsp"%>