<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="../inc/header.jsp"%>

<div class="container">
	<div class="mt-1">
		<h1>Créer Employe</h1>
	</div>

	<form:form class="form-horizontal" method="POST"
		modelAttribute="remunerationEmploye">
		<div class="form-group">
			<form:label path="matricule" class="col-sm-2 control-label">Matricule</form:label>
			<div class="col-sm-10">
				<form:input path="matricule" class="form-control" />
			</div>
		</div>

		<div class="form-group">
			<form:label path="entreprise" class="col-sm-2 control-label">Entreprise</form:label>

			<div class="col-sm-10">
				<form:select path="entreprise" class="form-control">
					<form:option value="NONE" label="--- Select ---" />
					<form:options items="${entrepriseList}" itemValue="id"
						itemLabel="denomination" />
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<form:label path="profilRemuneration" class="col-sm-2 control-label">Profil</form:label>
			<div class="col-sm-10">
				<form:select path="profilRemuneration" class="form-control">
					<form:option value="NONE" label="--- Select ---" />
					<form:options items="${profilList}" itemValue="id" itemLabel="code" />
				</form:select>
			</div>
		</div>

		<div class="form-group">
			<form:label path="grade" class="col-sm-2 control-label">Grade</form:label>
			<div class="col-sm-10">
				<form:select path="grade" class="form-control">
					<form:option value="NONE" label="--- Select ---" />
					<form:options items="${gradeList}" itemValue="id" itemLabel="code" />
				</form:select>
			</div>
		</div>

		<input class="btn-lg btn-primary pull-right" type="submit" value="Ajouter" />

	</form:form>
</div>
<%@include file="../inc/footer.jsp"%>