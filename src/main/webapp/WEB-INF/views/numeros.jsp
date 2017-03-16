<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Java Brasil</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<style>
body {
	background: black !important;
	color: white
}

.input-group-addon {
	background: black !important;
	border: black !important;
}

input {
	color: black;
}
</style>

</head>
<body>
	<h2 class="center">Conversor números - palavras</h2>
	<form:form action="converterNumeros" method="Post" modelAttribute="entradaValor">
		<div class="row">
			<div class="col-md-3">
				<div class="input-group">
					<span class="input-group-addon" id="lbEntrada">Entrada:</span> 
					<input name="texto" type="number" step="0.01" value="${texto }"/>
				</div>
			</div>
		</div>
		<br/>
		<div class="row">
			<div class="col-md-3 col-md-offset-1">
				<input type="submit" class="btn btn-info" value="Validar">
			</div>
		</div>
	</form:form>
	<div class="row">
		<div class="col-md-6">
			<h4>
				<span class="text-${estilo} ">${ resposta}</span>
			</h4>
		</div>
	</div>
</body>
</html>