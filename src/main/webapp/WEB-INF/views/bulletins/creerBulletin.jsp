<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="../inc/header.jsp"%>

<div class="container">
	<div class="mt-1">
		<h1>Créer Bulletin</h1>
	</div>

	<form:form class="form-horizontal" method="POST"
		modelAttribute="bulletin">

		<div class="form-group">
			<form:label path="periode" class="col-sm-2 control-label">Periode</form:label>

			<div class="col-sm-10">
				<select class="form-control" id="periode" name="periode">
				<option value="">--- Select ---</option>
					<c:forEach var="periode" items="${periodeList}">
						<option value="<c:out value="${periode.id}" />"><c:out value="${periode}" /></option>
					</c:forEach>
				</select>
				<div class="has-error">
					<form:errors path="periode" cssClass="help-block"/>
				</div>
			</div>
		</div>
		<div class="form-group">
			<form:label path="matricule" class="col-sm-2 control-label">Matricule</form:label>
			<div class="col-sm-10">
				<form:select path="matricule" class="form-control">
					<form:option value="" label="--- Select ---" />
					<form:options items="${employeList}" itemValue="matricule"
						itemLabel="matricule" />
				</form:select>
				<div class="has-error">
					<form:errors path="matricule" cssClass="help-block"/>
				</div>
			</div>
		</div>

				<div class="form-group">
			<form:label path="primeExceptionnelle" class="col-sm-2 control-label">Prime exceptionnelle</form:label>
			<div class="col-sm-10">
				<form:input path="primeExceptionnelle" class="form-control" placeholder="1200.00 ou 1200"/>
				<div class="has-error">
					<form:errors path="primeExceptionnelle" cssClass="help-block"/>
				</div>
			</div>

		</div>

		<input class="btn btn-primary float-right" type="submit"
			value="Ajouter" />

	</form:form>
</div>
<%@include file="../inc/footer.jsp"%>