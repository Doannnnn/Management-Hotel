<%--
  Created by IntelliJ IDEA.
  User: MSI GAMING
  Date: 29/09/2023
  Time: 10:58 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="/auth/assets/css/style.css"></link>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
  <script src="https://cdn.jsdelivr.net/npm/toastr@2.1.4/build/toastr.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/toastr@2.1.4/build/toastr.min.css" rel="stylesheet">
  <style>
    .error-message {
      display: none;
      color: red;
      margin-top: 5px;
    }
  </style>
</head>
<body>
<h2>Sign in</h2>
<c:if test="${message != null}">
  <h6 class="d-none" id="message">${message}</h6>
</c:if>
<div class="container" id="container">
  <div class="form-container sign-up-container">
    <form action="/auth?action=register" method="POST">
      <h1>Create Account</h1>
      <div class="social-container">
        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
        <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
      </div>
      <span>or use your email for registration</span>
      <input type="text" placeholder="Name"  name="name"  required/>
      <input type="email" placeholder="Email" name="email" required/>
      <input type="text" placeholder="Address" name="address" required/>
      <input type="password" placeholder="Password" id="re_password" name="re_password" >
      <input type="password" placeholder="Re_Password" id="password" name="password" onblur="checkPassword()" required />
      <div id="passwordMatchError" class="error-message">Mật khẩu không trùng khớp</div>
      <button>Sign Up</button>
    </form>
  </div>
  <div class="form-container sign-in-container">
    <form action="/auth" method="POST" >
      <h1>Sign in</h1>
      <div class="social-container">
        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
        <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
      </div>
      <span>or use your account</span>
      <input type="email" placeholder="Email" name="username" required/>
      <input type="password" placeholder="Password" name="password" required/>
      <a href="#">Forgot your password?</a>
      <button>Sign In</button>
    </form>
  </div>
  <div class="overlay-container">
    <div class="overlay">
      <div class="overlay-panel overlay-left">
        <h1>Welcome Back!</h1>
        <p>To keep connected with us please login with your personal info</p>
        <button class="ghost" id="signIn">Sign In</button>
      </div>
      <div class="overlay-panel overlay-right">
        <h1>Hello, Friend!</h1>
        <p>Enter your personal details and start journey with us</p>
        <button class="ghost" id="signUp">Sign Up</button>
      </div>
    </div>
  </div>
</div>
<script>
  const message = document.getElementById('message');
  if (message !== null && message.innerHTML) {
    toastr.success(message.innerHTML);
  }
</script>
<script>
  const signUpButton = document.getElementById('signUp');
  const signInButton = document.getElementById('signIn');
  const container = document.getElementById('container');

  signUpButton.addEventListener('click', () => {
    container.classList.add('right-panel-active');
  });

  signInButton.addEventListener('click', () => {
    container.classList.remove('right-panel-active');
  });

  function checkPassword() {
    var password = document.getElementById('password').value;
    var rePassword = document.getElementById('re_password').value;
    var errorDiv = document.getElementById('passwordMatchError');

    if (rePassword !== password) {
      errorDiv.style.display = 'block';
    } else {
      errorDiv.style.display = 'none';
    }
  }
</script>
</body>
</html>
