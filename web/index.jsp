<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body>

<!-- Slideshow container -->
<div class="slideshow-container">

    <!-- Full-width images with number and caption text -->
    <div class="mySlides fade">
        <div class="numbertext">1 / 3</div>
        <img src="img/img1.jpg" style="width:100%; height: 50%">
        <div class="text">Caption Text</div>
    </div>

    <div class="mySlides fade">
        <div class="numbertext">2 / 3</div>
        <img src="img/img2.jpg" style="width:100%; height: 50%">
        <div class="text">Caption Two</div>
    </div>

    <div class="mySlides fade">
        <div class="numbertext">3 / 3</div>
        <img src="img/img3.jpg" style="width:100%; height: 50%">
        <div class="text">Caption Three</div>
    </div>

    <!-- Next and previous buttons -->
    <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
    <a class="next" onclick="plusSlides(1)">&#10095;</a>
</div>
<br>
  <div class=newDiv><div/>
<p>new P tag</p>
<!-- The dots/circles -->
<div style="text-align:center">
    <span class="dot" onclick="currentSlide(1)"></span>
    <span class="dot" onclick="currentSlide(2)"></span>
    <span class="dot" onclick="currentSlide(3)"></span>
</div>




<%
    String msg = "";
    if (session.getAttribute("msg") != null) {
        msg = (String) session.getAttribute("msg");
        session.removeAttribute("msg");
    }

%>
<p style="color: red;text-align: center">
    <%=msg%>
</p>
<div>
    <form class="box" action="/login" method="post">
        <h1>Login</h1>
        <input type="text" name="email" placeholder="Email" required>
        <input type="password" name="password" placeholder="Password" required>
        <input type="submit" name="" value="Login">
    </form>

</div>
<%--<p id="pid" class="pclas" style="width: 100px; height: 100px; border: 1px solid black"></p>--%>
<%--<butten id="hide">Hide</butten>--%>
<%--<butten id="show">Show</butten>--%>
</body>

<script src="js/jquery-3.5.1.mun.js" type="text/javascript"></script>
<script src="js/slider.js" type="text/javascript"></script>
    <script>


     // $(document).ready(function () {
     //
     //     $('#hide').on('click', function () {
     //         $("#pid").hide()
     //
     //     })
     //     $('#show').on('click', function () {
     //         $("#pid").show()
     //
     //     })
     // })


</script>
</html>
