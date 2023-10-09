<%--
  Created by IntelliJ IDEA.
  User: MSI GAMING
  Date: 02/10/2023
  Time: 3:00 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Đổi mật khẩu</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="/auth/assets/css/change.css"></link>
</head>
</head>
<body>
<div class="container">
    <h2 style=" text-align: center">Đổi mật khẩu</h2>
    <form action="/auth?action=change-password" method="post">
        <div class="social-container">
            <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
            <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
            <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
        </div>
        <span>Enter your new password</span>
        <div class="form-group" style=" text-align: center">
            <label for="email">Địa chỉ email:</label>
            <input type="email" class="form-control" id="email" name="email" value="${param.email}" required readonly>
        </div>
        <input type="password" placeholder="Password" id="re_password" name="re_password" required>
        <div id="passwordError" class="error-message" style="display: none;"></div>
        <input type="password" placeholder="Re_Password" id="password" name="password" onblur="checkPassword()" required />
        <div id="passwordMatchError" class="error-message">Mật khẩu không trùng khớp</div>
        <button>Đổi mật khẩu</button>
    </form>
</div>
    <script src="/auth/assets/js/validex.js"></script>
</body>
</html>