<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<style type="text/css">
	* {
		margin: 0;
		padding: 0;
		box-sizing: border-box;
	} 
	.container {
		display: flex;
		flex-direction: column;
		align-items: center;
		width: 100vw;
		height: 100vh;
		overflow-x: hidden;
		border: 3px solid black;
	}
	.logo{
		width: 100%;
		height: 160px;
	}
	.content{
		flex: 1;
	}
	.link{
		display: flex; 
		gap: 7px; 
		border-bottom: 3px solid black;
		border-top: 3px solid black;
		width: 100%;
		justify-content: center;
		padding: 4px;
		font-size: 20px;
		font-weight: bold;
	}
	.footer {
	display: flex; 
		width: 100%;
		border-top: 3px solid black;
		justify-content: center;
		padding: 4px;
		font-size: 20px;
		font-weight: bold;
		
	}
	
	a{
		color: black;
	}
</style>
</head>
<body>
	<div class="container">
		<img class="logo" alt="" src="${pageContext.request.contextPath}/images/logo/logo.png">
		<div class="link">
			<a href="${pageContext.request.contextPath}/show-view?show=list" >Danh sách sản phẩm</a>
			<span> | </span>
			<a href="${pageContext.request.contextPath}/show-view?show=add" >Thêm sản phẩm mới</a>
			<span> | </span>
			<a href="${pageContext.request.contextPath}/show-view?show=manage" >Chức năng quản lý</a>
		</div>
		<div class="content">
			<c:if test="${not empty page }">
				<c:import url="/views/${page}"/>
			</c:if>
		</div>
		<div class="footer">
			<p>Trần Vũ Duy - 21026331 - DHKTPM17ATT</p>
		</div>
	</div>
</body>
</html>