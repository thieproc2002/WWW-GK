<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

		h1{
			margin-top: 16px;
		}
        th, td {
            padding: 8px 12px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Quản lý điện thoại</h1>
        <table>
            <tr>
                <th>Mã điện thoại</th>
                <th>Tên điện thoại</th>
                <th>Mã nhà cung cấp</th>
                <th>Năm sản xuất</th>
                <th>Cấu hình</th>
                <th>Hình ảnh</th>
                <th>Action</th>
            </tr>
            <c:forEach var="dt" items="${listDT}">
                <tr>
                    <td>${dt.maDT}</td>
                    <td>${dt.tenDT}</td>
                    <td>${dt.nhaCungCap.maNCC}</td>
                    <td>${dt.namSanXuat}</td>
                    <td>${dt.cauHinh}</td>
                    <td>
                        <img alt="Hình ảnh" src="${pageContext.request.contextPath}/uploads/${dt.hinhAnh}" width="50" height="50">
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/manage?id=${dt.maDT}" class="button">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
