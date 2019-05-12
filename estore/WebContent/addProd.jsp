<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
.divform{
	position: absolute;
	text-align: center;
	top: 50%;
	left: 50%;
	margin-top: -200px;
	margin-left: -150px;
}
</style>
<title>Estore_添加商品</title>
</head>
<body style="text-align: center;">
<h1>Estore_添加商品</h1><hr>
<div class="divform">
<form action="/AddprodServlet" method="POST" enctype="multipart/form-data" >
	<table border="1">
		<tr>
			<td>商品名称</td>
			<td><input type="text" name="name"/></td>
		</tr>
		<tr>
			<td>商品价格</td>
			<td><input type="text" name="price"/></td>
		</tr>
		<tr>
			<td>商品种类</td>
			<td>
				<select name="category">
					<option value="电子数码">电子数码</option>
					<option value="图书杂志">图书杂志</option>
					<option value="床上用品 ">床上用品</option>
					<option value="日用百货">日用百货</option>
					<option value="大型家电">大型家电</option>
					<option value="家用武器">家用武器</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>商品库存</td>
			<td><input type="text" name=" pnum"/></td>
		</tr>
		<tr>
			<td>商品图片</td>
			<td><input type="file" name="imgurl"/></td>
		</tr>
		<tr>
			<td>商品描述</td>
			<td><textarea name="description" rows="6" cols="40"></textarea></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="添加商品"/></td>
		</tr>
	</table>
</form>
</div>
</body>
</html>