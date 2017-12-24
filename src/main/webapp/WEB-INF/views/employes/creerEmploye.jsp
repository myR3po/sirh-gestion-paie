
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@include file="../inc/header.jsp" %>
		

 <div class="container body-content">
	<div class="row mt-1">
		<h1>Créer Employé</h1>
	</div>

	<form:form class="form-horizontal" method="POST"
		modelAttribute="remunerationEmploye">
		<div class="form-group row">
			<form:label path="matricule" class="col-sm-2 col-form-label">Matricule</form:label>
			<div class="col-sm-10">
				<form:input path="matricule" class="form-control" />
				<div class="has-error">
					<form:errors path="matricule" cssClass="help-block"/>
				</div>
			</div>
		</div>

		<div class="form-group row">
			<form:label path="entreprise" class="col-sm-2 col-form-label">Entreprise</form:label>

			<div class="col-sm-10">
				<form:select path="entreprise" class="form-control">
					<form:option value="" label="--- Select ---" />
					<form:options items="${entrepriseList}" itemValue="id"
						itemLabel="denomination" />
				</form:select>
				<div class="has-error">
					<form:errors path="entreprise" cssClass="help-block"/>
				</div>
			</div>
		</div>
		<div class="form-group row">
			<form:label path="profilRemuneration" class="col-sm-2 col-form-label">Profil</form:label>
			<div class="col-sm-10">
				<form:select path="profilRemuneration" class="form-control">
					<form:option value="" label="--- Select ---" />
					<form:options items="${profilList}" itemValue="id" itemLabel="code" />
				</form:select>
				<div class="has-error">
					<form:errors path="profilRemuneration" cssClass="help-block"/>
				</div>
			</div>
		</div>

		<div class="form-group row">
			<form:label path="grade" class="col-sm-2 col-form-label">Grade</form:label>
			<div class="col-sm-10">
				<form:select path="grade" class="form-control">
					<form:option value="" label="--- Select ---" />
					<form:options items="${gradeList}" itemValue="id" itemLabel="code" />
				</form:select>
				<div class="has-error">
					<form:errors path="grade" cssClass="help-block"/>
				</div>
			</div>
		</div>

		<input class="btn btn-primary float-right" type="submit" value="Ajouter" />

	</form:form>
</div>

<%@include file="../inc/footer.jsp" %>