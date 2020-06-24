<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listar</title>
</head>
<body style="text-align: center;
	font: normal 15pt arial;
">

<h1>Lista de Pagamentos</h1>

<p>${msg}</p>

		<c:if test="${lista !=null}">

			<table style="margin: auto;" border="1">
				<tr>
					<th>Titulo</th>
					<th>Valor</th>
					<th>Imposto <br> Externo</th>
					<th>Data</th>
					<th>Comentario</th>
					<th>Opções</th>
					


				</tr>
				<c:forEach items="${lista}" var="venda">
					<tr>

						<td>${venda.getTitulo()}</td>
						<td>${venda.getValor()}</td>
						<td>${venda.getImpostoExterno()}</td>
						<td>${venda.getData()}</td>
						<td>${venda.getComentarios()}</td>
						
						<td><div>
								<form action="ControllerApoio" method="post">
									<input type="text" name="cod" value="${venda.getId()}"
										style="display: none;" > <input type="submit"
										value="Editar" class="btn"
										style="width: -webkit-fill-available; border-radius: 0px !important;">
								</form>
								<form action="ControllerExcluir" method="post">
									<input type="text" name="cod" value="${venda.getId()}"
										style="display: none;"> <input style="width: -webkit-fill-available;" type="submit"
										value="Excluir" class="btn1">
								</form>
							</div></td>
						
						</tr></c:forEach></table></c:if>
						<form action="ControllerFechar" method="get">
						<input type="submit" value="Fechar Conexão">
						</form>
</body>
</html>