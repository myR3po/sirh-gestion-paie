<%@include file="../inc/header.jsp"%>

<div class="container">

	<div class="text-center">

		<h1 class="display-4">Oops!</h1>
		<h2>Tu n'as pas le droit</h2>
		<a href="<c:url value="/bulletins/lister" context="/paie/mvc"/>"
			class="btn btn-primary btn-lg"> revenir en arrière</a>

	</div>
</div>
<%@include file="../inc/footer.jsp"%>