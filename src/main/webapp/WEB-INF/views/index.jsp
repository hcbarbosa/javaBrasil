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
	<h2 class="center">Validador</h2>	
		<div class="row">
			<div class="col-md-3">
				<c:url value="/validador" var="validador"/>
				<a href="${validador}" class="btn btn-info">Validar entrada</a>
			</div>
		</div>
	<h2 class="center">Conversor</h2>	
		<div class="row">
			<div class="col-md-3">
				<c:url value="/conversor" var="conversor"/>
				<a href="${conversor }" class="btn btn-info">Converter Números</a>
			</div>
		</div>
	<h2 class="center">Cotação</h2>	
		<div class="row">
			<div class="col-md-3">
				<c:url value="/cotacao" var="cotacao"/>
				<a href="${cotacao }" class="btn btn-info">Converter USD/BRL</a>
			</div>
		</div>
	<h2 class="center">CEP</h2>	
		<div class="row">
			<div class="col-md-3">
				<c:url value="/cep" var="cep"/>
				<a href="${cep }" class="btn btn-info">Consultar CEP</a>
			</div>
		</div>
</body>
</html>