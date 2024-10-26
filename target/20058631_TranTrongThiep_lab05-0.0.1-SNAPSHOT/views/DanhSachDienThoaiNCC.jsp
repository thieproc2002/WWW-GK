<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách điện thoại</title>
<style type="text/css">
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 20px;
    }

    h1 {
    	margin-top: 16px;
        text-align: center;
        color: #333;
    }

    form {
        margin: 20px 0;
        text-align: center;
    }

    label {
        margin-right: 10px;
        font-weight: bold;
    }

    select, input[type="text"], input[type="submit"] {
        padding: 10px;
        margin-right: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    input[type="submit"] {
        background-color: #28a745;
        color: white;
        cursor: pointer;
    }

    input[type="submit"]:hover {
        background-color: #218838;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
        background-color: white;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        margin-bottom: 32px;
    }

    th, td {
        padding: 15px;
        text-align: center;
        border: 1px solid #ddd;
    }

    th {
        background-color: #007bff;
        color: white;
    }

    tr:hover {
        background-color: #f1f1f1;
    }

    img {
        width: 150px;
        height: auto;
        border-radius: 5px;
    }
</style>
</head>
<body>
    <h1>Danh Sách Điện Thoại</h1>
    <div>
        <form action="list" method="POST">
            <label for="maNCC">Lọc theo nhà cung cấp</label>
            <select name="maNCC" id="maNCC">
                <option value="tatCa">Tất cả</option>
                <c:forEach var="ncc" items="${listNCC}">
                    <option value="${ncc.maNCC}">${ncc.tenNhaCC}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Lọc">
        </form>
        <form action="list" method="get">
            <label for="search">Tìm kiếm theo MANCC, TENNHACC, DIACHI, SĐT</label>
            <input type="text" id="search" name="search">
            <input type="submit" value="Tìm kiếm">
        </form>
    </div>
    <table>
        <tr>
            <th>Mã điện thoại</th>
            <th>Tên điện thoại</th>
            <th>Mã nhà cung cấp</th>
            <th>Năm sản xuất</th>
            <th>Cấu hình</th>
            <th>Hình ảnh</th>
        </tr>
        <c:forEach var="dt" items="${listDT}">
            <tr>
                <td>${dt.maDT}</td>
                <td>${dt.tenDT}</td>
                <td>${dt.nhaCungCap.maNCC}</td>
                <td>${dt.namSanXuat}</td>
                <td>${dt.cauHinh}</td>
                <td>
                    <img alt="" src="${pageContext.request.contextPath}/uploads/${dt.hinhAnh}">
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
