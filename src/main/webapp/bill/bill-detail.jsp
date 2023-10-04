<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Sona Template">
    <meta name="keywords" content="Sona, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sona | Template</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Cabin:400,500,600,700&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="../hotel/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="../hotel/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="../hotel/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="../hotel/css/flaticon.css" type="text/css">
    <link rel="stylesheet" href="../hotel/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="../hotel/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="../hotel/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="../hotel/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="../hotel/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="../hotel/css/style.css" type="text/css">
    <link rel="stylesheet" href="../bill/snippet.css" type="text/css">
    <link rel="stylesheet" href="../owlcarousel/assets/owl.carousel.min.css">
    <link rel="stylesheet" href="../owlcarousel/assets/owl.theme.default.min.css">
</head>

<body>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>

<!-- Offcanvas Menu Section Begin -->
<div class="offcanvas-menu-overlay"></div>
<div class="canvas-open">
    <i class="icon_menu"></i>
</div>
<div class="offcanvas-menu-wrapper">
    <div class="canvas-close">
        <i class="icon_close"></i>
    </div>
    <div class="search-icon search-switch">
        <i class="icon_search"></i>
    </div>
    <div class="header-configure-area">
        <div class="language-option">
            <img src="../hotel/img/flag.jpg" alt="">
            <span>EN <i class="fa fa-angle-down"></i></span>
            <div class="flag-dropdown">
                <ul>
                    <li><a href="#">Zi</a></li>
                    <li><a href="#">Fr</a></li>
                </ul>
            </div>
        </div>
        <a href="#" class="bk-btn">Booking Now</a>
    </div>
    <nav class="mainmenu mobile-menu">
        <ul>
            <li class="active"><a href="../hotel/index.jsp">Home</a></li>
            <li><a href="../hotel/rooms.jsp">Rooms</a></li>
            <li><a href="../hotel/about-us.jsp">About Us</a></li>
            <li><a href="../hotel/room-details.jsp">Pages</a>
                <ul class="dropdown">
                    <li><a href="../hotel/room-details.jsp">Room Details</a></li>
                    <li><a href="../hotel/blog-details.jsp">Blog Details</a></li>
                    <li><a href="#">Family Room</a></li>
                    <li><a href="#">Premium Room</a></li>
                </ul>
            </li>
            <li><a href="../hotel/blog.jsp">News</a></li>
            <li><a href="../hotel/contact.jsp">Contact</a></li>
        </ul>
    </nav>
    <div id="mobile-menu-wrap"></div>
    <div class="top-social">
        <a href="#"><i class="fa fa-facebook"></i></a>
        <a href="#"><i class="fa fa-twitter"></i></a>
        <a href="#"><i class="fa fa-tripadvisor"></i></a>
        <a href="#"><i class="fa fa-instagram"></i></a>
    </div>
    <ul class="top-widget">
        <li><i class="fa fa-phone"></i> (12) 345 67890</li>
        <li><i class="fa fa-envelope"></i> info.colorlib@gmail.com</li>
    </ul>
</div>
<!-- Offcanvas Menu Section End -->

<!-- Header Section Begin -->
<header class="header-section header-normal">
    <div class="top-nav">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <ul class="tn-left">
                        <li><i class="fa fa-phone"></i> (12) 345 67890</li>
                        <li><i class="fa fa-envelope"></i> info.colorlib@gmail.com</li>
                    </ul>
                </div>
                <div class="col-lg-6">
                    <div class="tn-right">
                        <div class="top-social">
                            <a href="#"><i class="fa fa-facebook"></i></a>
                            <a href="#"><i class="fa fa-twitter"></i></a>
                            <a href="#"><i class="fa fa-tripadvisor"></i></a>
                            <a href="#"><i class="fa fa-instagram"></i></a>
                        </div>
                        <a href="#" class="bk-btn">Booking Now</a>
                        <div class="language-option">
                            <img src="../hotel/img/flag.jpg" alt="">
                            <span>EN <i class="fa fa-angle-down"></i></span>
                            <div class="flag-dropdown">
                                <ul>
                                    <li><a href="#">Zi</a></li>
                                    <li><a href="#">Fr</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="menu-item">
        <div class="container">
            <div class="row">
                <div class="col-lg-2">
                    <div class="logo">
                        <a href="../hotel/index.jsp">
                            <img src="../hotel/img/logo.png" alt="">
                        </a>
                    </div>
                </div>
                <div class="col-lg-10">
                    <div class="nav-menu">
                        <nav class="mainmenu">
                            <ul>
                                <li><a href="../hotel/index.jsp">Home</a></li>
                                <li class="active"><a href="../hotel/rooms.jsp">Rooms</a></li>
                                <li><a href="../hotel/about-us.jsp">About Us</a></li>
                                <li><a href="../hotel/room-details.jsp">Pages</a>
                                    <ul class="dropdown">
                                        <li><a href="../hotel/room-details.jsp">Room Details</a></li>
                                        <li><a href="../hotel/blog-details.jsp">Blog Details</a></li>
                                        <li><a href="#">Family Room</a></li>
                                        <li><a href="#">Premium Room</a></li>
                                    </ul>
                                </li>
                                <li><a href="../hotel/blog.jsp">News</a></li>
                                <li><a href="../hotel/contact.jsp">Contact</a></li>
                            </ul>
                        </nav>
                        <div class="nav-right search-switch">
                            <i class="icon_search"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Header End -->

<!-- Breadcrumb Section Begin -->
<div class="breadcrumb-section">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb-text">
                    <h2>YOUR ORDER</h2>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Breadcrumb Section End -->

<!-- Room Details Section Begin -->
<section class="room-details-section spad">
                <div class="containers d-lg-flex">
                    <div class="box-1 bg-light user">
                        <div class="d-flex align-items-center mb-3">
                            <img src="../hotel/img/room/avatar/${user.img}"
                                 class="pic rounded-circle" alt="">
                            <p class="ps-2 name">${user.name}</p>
                        </div>
                        <div class="box-inner-1 pb-3 mb-3 ">
                            <div class="d-flex justify-content-between mb-3 user details">
                                <p class="fw-bold">${room.name}</p>
                                <p class="fw-lighter"><span class="fas fa-dollar-sign"></span>${room.price}$</p>
                            </div>
                            <div class="owl-carousel owl-theme">
                                <c:forEach var="img" items="${room.images}">
                                    <img class="owl-lazy" data-src="../hotel/img/room/${img.url}" alt="" style="width: 100%; height: 100%;">
                                </c:forEach>
                            </div>


                            <p class="dis info my-3">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate quos ipsa
                                sed officiis odio
                            </p>
                            <div class="radiobtn">
                                <input type="radio" name="box" id="one">
                                <input type="radio" name="box" id="two">
                                <input type="radio" name="box" id="three">
                                <label for="one" class="box py-2 first">
                                    <div class="d-flex align-items-start">
                                        <span class="circle"></span>
                                        <div class="course">
                                            <div class="d-flex align-items-center justify-content-between mb-2">
                                    <span class="fw-bold">
                                        Collection 01
                                    </span>
                                                <span class="fas fa-dollar-sign">29</span>
                                            </div>
                                            <span>10 x Presets. Released in 2018</span>
                                        </div>
                                    </div>
                                </label>
                                <label for="two" class="box py-2 second">
                                    <div class="d-flex">
                                        <span class="circle"></span>
                                        <div class="course">
                                            <div class="d-flex align-items-center justify-content-between mb-2">
                                    <span class="fw-bold">
                                        Collection 01
                                    </span>
                                                <span class="fas fa-dollar-sign">29</span>
                                            </div>
                                            <span>10 x Presets. Released in 2018</span>
                                        </div>
                                    </div>
                                </label>
                                <label for="three" class="box py-2 third">
                                    <div class="d-flex">
                                        <span class="circle"></span>
                                        <div class="course">
                                            <div class="d-flex align-items-center justify-content-between mb-2">
                                    <span class="fw-bold">
                                        Collection 01
                                    </span>
                                                <span class="fas fa-dollar-sign">29</span>
                                            </div>
                                            <span>10 x Presets. Released in 2018</span>
                                        </div>
                                    </div>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="box-2">
                        <div class="box-inner-2">
                            <div>
                                <p class="fw-bold">Payment Details</p>
                                <p class="dis mb-3">Complete your purchase by providing your payment details</p>
                            </div>
                            <form action="">
                                <div class="mb-3">
                                    <p class="dis fw-bold mb-2">Email address</p>
                                    <input class="form-control" type="email" value="luke@skywalker.com">
                                </div>
                                <div>
                                    <p class="dis fw-bold mb-2">Card details</p>
                                    <div class="d-flex align-items-center justify-content-between card-atm border rounded">
                                        <div class="fab fa-cc-visa ps-3"></div>
                                        <input type="text" class="form-control" placeholder="Card Details">
                                        <div class="d-flex w-50">
                                            <input type="text" class="form-control px-0" placeholder="MM/YY">
                                            <input type="password" maxlength=3 class="form-control px-0" placeholder="CVV">
                                        </div>
                                    </div>
                                    <div class="my-3 cardname">
                                        <p class="dis fw-bold mb-2">Cardholder name</p>
                                        <input class="form-control" type="text">
                                    </div>
                                    <div class="address">
                                        <p class="dis fw-bold mb-3">Billing address</p>
                                        <select class="form-select" aria-label="Default select example">
                                            <option selected hidden>United States</option>
                                            <option value="1">India</option>
                                            <option value="2">Australia</option>
                                            <option value="3">Canada</option>
                                        </select>
                                        <div class="d-flex">
                                            <input class="form-control zip" type="text" placeholder="ZIP">
                                            <input class="form-control state" type="text" placeholder="State">
                                        </div>
                                        <div class=" my-3">
                                            <p class="dis fw-bold mb-2">VAT Number</p>
                                            <div class="inputWithcheck">
                                                <input class="form-control" type="text" value="GB012345B9">
                                                <span class="fas fa-check"></span>

                                            </div>
                                        </div>
                                        <div class="d-flex flex-column dis">
                                            <div class="d-flex align-items-center justify-content-between mb-2">
                                                <p>Subtotal</p>
                                                <p><span class="fas fa-dollar-sign"></span>33.00</p>
                                            </div>
                                            <div class="d-flex align-items-center justify-content-between mb-2">
                                                <p>VAT<span>(20%)</span></p>
                                                <p><span class="fas fa-dollar-sign"></span>2.80</p>
                                            </div>
                                            <div class="d-flex align-items-center justify-content-between mb-2">
                                                <p class="fw-bold">Total</p>
                                                <p class="fw-bold"><span class="fas fa-dollar-sign"></span>35.80</p>
                                            </div>
                                            <div class="btn btn-primary mt-2">Pay<span class="fas fa-dollar-sign px-1"></span>35.80
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
</section>
<!-- Room Details Section End -->

<!-- Footer Section Begin -->
<footer class="footer-section">
    <div class="container">
        <div class="footer-text">
            <div class="row">
                <div class="col-lg-4">
                    <div class="ft-about">
                        <div class="logo">
                            <a href="#">
                                <img src="../hotel/img/footer-logo.png" alt="">
                            </a>
                        </div>
                        <p>We inspire and reach millions of travelers<br/> across 90 local websites</p>
                        <div class="fa-social">
                            <a href="#"><i class="fa fa-facebook"></i></a>
                            <a href="#"><i class="fa fa-twitter"></i></a>
                            <a href="#"><i class="fa fa-tripadvisor"></i></a>
                            <a href="#"><i class="fa fa-instagram"></i></a>
                            <a href="#"><i class="fa fa-youtube-play"></i></a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 offset-lg-1">
                    <div class="ft-contact">
                        <h6>Contact Us</h6>
                        <ul>
                            <li>(12) 345 67890</li>
                            <li>info.colorlib@gmail.com</li>
                            <li>856 Cordia Extension Apt. 356, Lake, United State</li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-3 offset-lg-1">
                    <div class="ft-newslatter">
                        <h6>New latest</h6>
                        <p>Get the latest updates and offers.</p>
                        <form action="#" class="fn-form">
                            <input type="text" placeholder="Email">
                            <button type="submit"><i class="fa fa-send"></i></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="copyright-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-7">
                    <ul>
                        <li><a href="#">Contact</a></li>
                        <li><a href="#">Terms of use</a></li>
                        <li><a href="#">Privacy</a></li>
                        <li><a href="#">Environmental Policy</a></li>
                    </ul>
                </div>
                <div class="col-lg-5">
                    <div class="co-text">
                        <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                            Copyright &copy;<script>document.write(new Date().getFullYear());</script>
                            All rights reserved | This template is made with <i class="fa fa-heart"
                                                                                aria-hidden="true"></i> by <a
                                    href="https://colorlib.com" target="_blank">Colorlib</a>
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- Footer Section End -->

<!-- Search model Begin -->
<div class="search-model">
    <div class="h-100 d-flex align-items-center justify-content-center">
        <div class="search-close-switch"><i class="icon_close"></i></div>
        <form class="search-model-form">
            <input type="text" id="search-input" placeholder="Search here.....">
        </form>
    </div>
</div>
<!-- Search model end -->

<!-- Js Plugins -->
<script src="../hotel/js/jquery-3.3.1.min.js"></script>
<script src="../hotel/js/bootstrap.min.js"></script>
<script src="../hotel/js/jquery.magnific-popup.min.js"></script>
<script src="../hotel/js/jquery.nice-select.min.js"></script>
<script src="../hotel/js/jquery-ui.min.js"></script>
<script src="../hotel/js/jquery.slicknav.js"></script>
<script src="../hotel/js/owl.carousel.min.js"></script>
<script src="../hotel/js/main.js"></script>
<script src="../owlcarousel/jquery.min.js"></script>
<script src="../owlcarousel/owl.carousel.min.js"></script>
<script>
    $('.owl-carousel').owlCarousel({
        items:1,
        lazyLoad:true,
        loop:true,
        margin:10
    });
</script>
</body>

</html>