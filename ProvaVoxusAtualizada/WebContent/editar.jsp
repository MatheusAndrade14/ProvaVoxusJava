<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Valor</title>
</head>
<body>
<form action="ControllerEditar" method="post">

<fieldset>
<legend><h1>Editar Valor</h1></legend>

<p><label>Valor Atual</label></p>
<input type="numeric" placeholder="Digite o valor atual" pattern="[0-9,.-,]+$" required name="valor">

<input type="text" value="${cd}" style="display: none" required name="id">

<p><input type="submit" value="Editar"></p>

</fieldset>
</form>
</body>
</html>