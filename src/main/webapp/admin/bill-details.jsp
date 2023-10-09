<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 09/10/2023
  Time: 11:09 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="card">
    <div class="card-body mx-4">
        <div class="container">
            <p class="my-5 mx-5" style="font-size: 30px;">BILL</p>
            <c:forEach var="bill" items="${bills}">
                <div class="row">
                    <ul class="list-unstyled">
                        <li class="text-black">John Doe</li>
                        <li class="text-muted mt-1"><span class="text-black">Invoice</span> #12345</li>
                        <li class="text-black mt-1">April 17 2021</li>
                    </ul>
                    <hr>
                    <div class="col-xl-10">
                        <p>Pro Package</p>
                    </div>
                    <div class="col-xl-2">
                        <p class="float-end">$199.00
                        </p>
                    </div>
                    <hr>
                </div>
                <div class="row">
                    <div class="col-xl-10">
                        <p>Consulting</p>
                    </div>
                    <div class="col-xl-2">
                        <p class="float-end">$100.00
                        </p>
                    </div>
                    <hr>
                </div>
                <div class="row">
                    <div class="col-xl-10">
                        <p>Support</p>
                    </div>
                    <div class="col-xl-2">
                        <p class="float-end">$10.00
                        </p>
                    </div>
                    <hr style="border: 2px solid black;">
                </div>
                <div class="row text-black">

                    <div class="col-xl-12">
                        <p class="float-end fw-bold">Total: $10.00
                        </p>
                    </div>
                    <hr style="border: 2px solid black;">
                </div>
                <div class="text-center" style="margin-top: 90px;">
                    <a><u class="text-info">View in browser</u></a>
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. </p>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>