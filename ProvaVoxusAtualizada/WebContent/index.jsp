<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagamentos</title>
</head>
<body style="text-align: center;
	font: normal 15pt arial;
">

<form action="Controller" method="get">
<fieldset>
<legend><h1>Cadastro</h1></legend>

<p><label>Titulo</label></p>
<input type="text" placeholder="Digite um titulo" required name="titulo">

<p><label>Valor</label></p>
<input type="numeric" placeholder="Digite um valor" pattern="[0-9,.-,]+$" required name="valor">

<p><label>Data</label></p>
<input type="text" onkeyup="var v = this.value;if (v.match(/^\d{2}$/) !== null) {this.value = v + '/';} else if (v.match(/^\d{2}\/\d{2}$/) !== null) {this.value = v + '/';}" 
placeholder="Ex.: dd/mm/aaaa" required name="data">

<p><label>Comentário</label></p>
<input type="text" placeholder="Digite um comentario"  name="comentarios">

<p><input type="submit" value="Cadastrar"></p>
</fieldset>
</form>

 <form action="upload" method="post" enctype="multipart/form-data">
 <fieldset>
 <legend><h1>Cadastrar via upload</h1></legend>
			<input type="file" name="arquivo"><br>
			<input type="submit" value="Upload">
			</fieldset>
			</form>
			
			<h1>Listar</h1>
			<form action="ControllerListar" method="get">
			<input type="submit" value="Listar">
			</form>
</body>
</html>