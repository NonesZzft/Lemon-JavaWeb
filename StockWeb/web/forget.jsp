<%--
  Created by IntelliJ IDEA.
  User: zhaowenteng
  Date: 11/26/19
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <script type="application/x-javascript">
        addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); }
    </script>
    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel='stylesheet' type='text/css' />
    <!-- Custom CSS -->
    <link href="css/style.css" rel='stylesheet' type='text/css' />
    <!-- Graph CSS -->
    <link href="css/font-awesome.css" rel="stylesheet">
    <!-- jQuery -->
    <link
            href='https://fonts.googleapis.com/css?family=Roboto:700,500,300,100italic,100,400'
            rel='stylesheet' type='text/css'>
    <!-- lined-icons -->
    <link rel="stylesheet" href="css/icon-font.min.css" type='text/css' />
    <!-- //lined-icons -->
    <script src="js/jquery-1.10.2.min.js"></script>
    <!--clock init-->
</head>



<body>

<div class="error_page">
    <!--/login-top-->

    <div class="error-top">
        <div class="login">
            <h3 class="inner-tittle t-inner">Login</h3>
            <form action="login.jsp" method="post">
                <table>
                    <tr>
                        <td><label for="email">Email:</label></td>
                        <td><input type="text" name="email" id="email"></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <!--//login-top-->
</div>

<!--//login-->
<!--footer section start-->
<div class="footer">
    <div class="error-btn">
        <a class="read fourth" href="login.jsp">Return to Home</a>
    </div>
    <p>Copyright &copy; 2016.Company name All rights reserved.</p>
</div>
<!--footer section end-->
<!--/404-->
<!--js -->
<script src="js/jquery.nicescroll.js"></script>
<script src="js/scripts.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>
</body>

</html>
