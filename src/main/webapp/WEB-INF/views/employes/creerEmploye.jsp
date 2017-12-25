
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@include file="../inc/header.jsp" %>
		

 <div class="container">
 	
	<div class="mt-4 mb-5">
        <h3 class="display-4 text-center"> Ajouter un employé </h3>
    </div>
	
	
	<form:form class="form-horizontal" method="POST" modelAttribute="remunerationEmploye">
		<div class="col-md-7 offset-md-2">
			<div class="form-group row">
				<form:label path="matricule" class="col-sm-2 col-form-label">Matricule</form:label>
				<div class="col-sm-10">
					<form:input path="matricule" class="form-control" aria-describedby="matricule.errors" />
					<div class="has-danger">
						<form:errors path="matricule" cssClass="form-control-feedback"/>
					</div>
				</div>
			</div>
	
			<div class="form-group row">
				<form:label path="entreprise" class="col-sm-2 col-form-label">Entreprise</form:label>
	
				<div class="col-sm-10">
					<form:select path="entreprise" class="form-control" aria-describedby="entreprise.errors">
						<form:option value="" label="--- Select ---" />
						<form:options items="${entrepriseList}" itemValue="id"
							itemLabel="denomination" />
					</form:select>
					<div class="has-danger">
						<form:errors path="entreprise" cssClass="form-control-feedback"/>
					</div>
				</div>
			</div>

			<div class="form-group row">
				<form:label path="profilRemuneration" class="col-sm-2 col-form-label">Profil</form:label>
				<div class="col-sm-10">
					<form:select path="profilRemuneration" class="form-control" aria-describedby="profilRemuneration.errors">
						<form:option value="" label="--- Select ---" />
						<form:options items="${profilList}" itemValue="id" itemLabel="code" />
					</form:select>
					<div class="has-danger">
						<form:errors path="profilRemuneration" cssClass="form-control-feedback"/>
					</div>
				</div>
			</div>

			<div class="form-group row">
				<form:label path="grade" class="col-sm-2 col-form-label">Grade</form:label>
				<div class="col-sm-10">
					<form:select path="grade" class="form-control" aria-describedby="grade.errors">
						<form:option value="" label="--- Select ---" />
						<form:options items="${gradeList}" itemValue="id" itemLabel="code" />
					</form:select>
					<div class="has-danger">
						<form:errors path="grade" cssClass="form-control-feedback"/>
					</div>
				</div>
			</div>

			<div class="mt-4">
				<button type="submit" class="btn btn-primary float-right">Ajouter</button>
			</div>
		</div>		
	</form:form>
</div>

<%@include file="../inc/footer.jsp" %>