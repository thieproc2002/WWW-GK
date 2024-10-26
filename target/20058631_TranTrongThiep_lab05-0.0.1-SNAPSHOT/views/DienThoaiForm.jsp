<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm điện thoại</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
        	margin-top: 48px;
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        .group-input {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
        }

        input[type="text"],
        select,
        input[type="file"] {
            width: 100%;
            padding: 12px; /* Tăng padding để input to hơn */
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px; /* Tăng font size để dễ đọc */
        }

        .error {
            color: red;
            font-size: 14px;
            margin-top: 5px;
            display: block;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 18px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <form action="dien-thoai" method="POST" enctype="multipart/form-data">
        <div class="container">
            <h1>Thêm điện thoại</h1>
            <div class="content-container">
                <div class="group-input">
                    <c:forEach var="violation" items="${errors}">
                        <c:if test="${ violation.propertyPath == 'tenDT' }">
                             <span class="error">${violation.message}</span>
                        </c:if>
                    </c:forEach>
                    <label for="tenDT">Tên điện thoại</label>
                    <input type="text" id="tenDT" name="tenDT"/>
                </div>
                
                <div class="group-input">
                    <c:forEach var="violation" items="${errors}">
                        <c:if test="${ violation.propertyPath == 'namSanXuat' }">
                             <span class="error">${violation.message}</span>
                        </c:if>
                    </c:forEach>
                    <c:if test="${not empty errorNamSX }">
                        <span class="error">${errorNamSX}</span>
                    </c:if>
                    <label for="namSX">Năm sản xuất</label>
                    <input type="text" id="namSX" name="namSX"/>
                </div>
                
                <div class="group-input">
                    <c:forEach var="violation" items="${errors}">
                        <c:if test="${ violation.propertyPath == 'cauHinh' }">
                             <span class="error">${violation.message}</span>
                        </c:if>
                    </c:forEach>
                    <label for="cauHinh">Cấu hình</label>
                    <input type="text" id="cauHinh" name="cauHinh"/>
                </div>
                
                <div class="group-input">
                    <label for="maNCC">Nhà cung cấp</label>
                    <select name="maNCC" id="maNCC">
                        <c:forEach var="ncc" items="${listNCC}">
                            <option value="${ncc.maNCC}">
                                ${ncc.tenNhaCC}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="group-input">
                    <c:forEach var="violation" items="${errors}">
                        <c:if test="${ violation.propertyPath == 'hinhAnh' }">
                             <span class="error">${violation.message}</span>
                        </c:if>
                    </c:forEach>
                    <label for="hinhAnh">Hình ảnh</label>
                    <input type="file" id="hinhAnh" name="hinhAnh"/>
                </div>
                <div>
                    <input type="submit" value="Thêm"/>
                </div>
            </div>
        </div>
    </form>
</body>
</html>
