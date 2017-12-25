<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="../inc/header.jsp"%>

<div class="container">
	
	<div class="mt-4 mb-5">
        <h3 class="display-4 text-center"> Créer Bulletin de Salaire </h3>
    </div>

	<form:form class="form-horizontal" method="POST" modelAttribute="bulletin">
		<div class="col-md-8 offset-md-1">
			<div class="form-group row">
				<form:label path="periode" class="col-sm-3">Periode</form:label>
	
				<div class="col-sm-9">
					<select class="form-control" id="periode" name="periode" aria-describedby="periode.errors">
					<option value="">--- Select ---</option>
						<c:forEach var="periode" items="${periodeList}">
							<option value="<c:out value="${periode.id}" />"><c:out value="${periode}" /></option>
						</c:forEach>
					</select>
					<div class="has-danger">
						<form:errors path="periode" cssClass="form-control-feedback"/>
					</div>
				</div>
			</div>

			<div class="form-group row">
				<form:label path="matricule" class="col-sm-3">Matricule</form:label>
				<div class="col-sm-9">
					<form:select path="matricule" class="form-control" aria-describedby="matricule.errors">
						<form:option value="" label="--- Select ---" />
						<form:options items="${employeList}" itemValue="matricule"
							itemLabel="matricule" />
					</form:select>
					<div class="has-danger">
						<form:errors path="matricule" cssClass="form-control-feedback"/>
					</div>
				</div>
			</div>

			<div class="form-group row">
				<form:label path="primeExceptionnelle" class="col-sm-3">Prime exceptionnelle</form:label>
				<div class="col-sm-9">
					<form:input path="primeExceptionnelle" class="form-control" placeholder="1200.00 ou 1200" aria-describedby="primeExceptionnelle.errors"/>
					<div class="has-danger">
						<form:errors path="primeExceptionnelle" cssClass="form-control-feedback"/>
					</div>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-sm-2"> </div>
				<div class="col-sm-10">
					<div class="mt-4">
						<button type="submit" class="btn btn-primary float-right">Créer</button>
					</div>
				</div>
			</div>
		</div>
	</form:form>
</div>
<%@include file="../inc/footer.jsp"%>