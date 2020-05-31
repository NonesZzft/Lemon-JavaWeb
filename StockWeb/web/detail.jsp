<%--
  Created by IntelliJ IDEA.
  User: zhaowenteng
  Date: 12/3/19
  Time: 09:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="main.java.bean.Stock" %>
<%@ page import="java.util.List" %>
<html>

<head>
    <%
        Integer currentPage = 1, count = 0, pageCount = 1, i = 0;
    %>
    <title>Detail</title>
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
    <!-- jQuery
        <link href='https://fonts.googleapis.com/css?family=Roboto:700,500,300,100italic,100,400' rel='stylesheet' type='text/css'>
        -->
    <!-- lined-icons -->
    <link rel="stylesheet" href="css/icon-font.min.css" type='text/css' />
    <!-- //lined-icons -->
    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/amcharts.js"></script>
    <script src="js/serial.js"></script>
    <script src="js/light.js"></script>
    <script src="js/radar.js"></script>
    <link href="css/barChart.css" rel='stylesheet' type='text/css' />
    <link href="css/fabochart.css" rel='stylesheet' type='text/css' />
    <!--clock init-->
    <script src="js/css3clock.js"></script>
    <!--Easy Pie Chart-->
    <!--skycons-icons-->
    <script src="js/skycons.js"></script>

    <script src="js/jquery.easydropdown.js"></script>

    <!--//skycons-icons-->
    <!--seach box css-->
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/style_search.css">

</head>
<%
    System.out.println(this.getClass() + ".jsp called ...");
%>
<body>
    <div class="page-container" style="overflow:scroll;">
        <!--/content-inner-->
        <div class="left-content">
            <div class="inner-content">
                <!-- header-starts -->
                <div class="header-section">
                    <!--menu-right-->
                    <div class="top_menu">
                        <div class="main-search">
                            <form>
                                <input type="text" value="Search" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'Search';}" class="text"
                                />
                                <input type="submit" value="">
                            </form>
                            <div class="close">
                                <img src="images/cross.png" />
                            </div>
                        </div>
                        <script type="text/javascript">
                            $('.main-search').hide();
                            $('button').click(function () {
                                    $('.main-search').show();
                                    $('.main-search text').focus();
                                }
                            );
                            $('.close').click(function () {
                                $('.main-search').hide();
                            });
                        </script>
                        <!--/profile_details-->
                        <div class="profile_details_left">
                            <ul class="nofitications-dropdown">
                                <li class="dropdown note dra-down">
                                    <script type="text/javascript">

                                        function DropDown(el) {
                                            this.dd = el;
                                            this.placeholder = this.dd.children('span');
                                            this.opts = this.dd.find('ul.dropdown > li');
                                            this.val = '';
                                            this.index = -1;
                                            this.initEvents();
                                        }
                                        DropDown.prototype = {
                                            initEvents: function () {
                                                var obj = this;

                                                obj.dd.on('click', function (event) {
                                                    $(this).toggleClass('active');
                                                    return false;
                                                });

                                                obj.opts.on('click', function () {
                                                    var opt = $(this);
                                                    obj.val = opt.text();
                                                    obj.index = opt.index();
                                                    obj.placeholder.text(obj.val);
                                                });
                                            },
                                            getValue: function () {
                                                return this.val;
                                            },
                                            getIndex: function () {
                                                return this.index;
                                            }
                                        }

                                        $(function () {

                                            var dd = new DropDown($('#dd'));

                                            $(document).click(function () {
                                                // all dropdowns
                                                $('.wrapper-dropdown-3').removeClass('active');
                                            });

                                        });

                                    </script>
                                </li>
                                <li class="dropdown note" style="float: right;">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                        <i class="fa fa-envelope-o"></i>
                                        <span class="badge">3</span>
                                    </a>


                                    <ul class="dropdown-menu two first">
                                        <li>
                                            <div class="notification_header">
                                                <h3>You have 3 new messages </h3>
                                            </div>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <div class="user_img">
                                                    <img src="images/1.jpg" alt="">
                                                </div>
                                                <div class="notification_desc">
                                                    <p>Lorem ipsum dolor sit amet</p>
                                                    <p>
                                                        <span>1 hour ago</span>
                                                    </p>
                                                </div>
                                                <div class="clearfix"></div>
                                            </a>
                                        </li>
                                        <li class="odd">
                                            <a href="#">
                                                <div class="user_img">
                                                    <img src="images/in.jpg" alt="">
                                                </div>
                                                <div class="notification_desc">
                                                    <p>Lorem ipsum dolor sit amet </p>
                                                    <p>
                                                        <span>1 hour ago</span>
                                                    </p>
                                                </div>
                                                <div class="clearfix"></div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <div class="user_img">
                                                    <img src="images/in1.jpg" alt="">
                                                </div>
                                                <div class="notification_desc">
                                                    <p>Lorem ipsum dolor sit amet </p>
                                                    <p>
                                                        <span>1 hour ago</span>
                                                    </p>
                                                </div>
                                                <div class="clearfix"></div>
                                            </a>
                                        </li>
                                        <li>
                                            <div class="notification_bottom">
                                                <a href="#">See all messages</a>
                                            </div>
                                        </li>
                                    </ul>
                                </li>

                                <div class="clearfix"></div>
                            </ul>
                        </div>
                        <div class="clearfix"></div>
                        <!--//profile_details-->
                    </div>
                    <!--//menu-right-->
                    <div class="clearfix"></div>
                </div>
                <!-- //header-ends -->


                <%
                    String name = (String) request.getAttribute("StockName");
                %>

                <!--outter-wp-->
                <div class="outter-wp">
                    <!--sub-heard-part-->
                    <div class="sub-heard-part">
                        <ol class="breadcrumb m-b-0">
                            <li>
                                <a href="StockListServlet?currentPage=1">Stock</a>
                            </li>
                            <li class="active">Stock Detail</li>
                        </ol>
                    </div>
                    <!--//sub-heard-part-->
                    <div class="graph-visual tables-main">
                        <h3 class="inner-tittle two">Input Quantity</h3>
                        <div class="graph">
                            <div class="tables">

                                <table class="table table-bordered">
                                    <tbody>
                                    <form action="BuyServlet?name=<%=name%>&schedule=false&time=30" method="post">
                                        <tr>
                                            <th style="text-align: center;background:#f1fafb">quantity</th>
                                            <td colspan="3" class="inputinfo"><input name="quantity" style="color: grey; background-color: transparent; border: 0px"
                                                                         maxlength="45" type="text">
                                            </td>
                                            <td><input type="submit" value="BUY"/></td>
                                        </tr>
                                    </form>
                                    <form action="BuyServlet?name=<%=name%>&schedule=true" method="post">
                                        <tr>
                                            <th style="text-align: center;background:#f1fafb">quantity</th>
                                            <td class="inputinfo"><input name="quantity" style="color: grey; background-color: transparent; border: 0px"
                                                                         maxlength="45" type="text">
                                            </td>
                                            <th style="text-align: center;background:#f1fafb">wait time</th>
                                            <td class="inputinfo"><input name="time" style="color: grey; background-color: transparent; border: 0px"
                                                                         maxlength="45" type="text">
                                            </td>
                                            <td><input type="submit" value="SCHEDULE"/></td>
                                        </tr>
                                    </form>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>
                    <!--//graph-visual-->


                    <div class="graph-visual tables-main">
                        <h3 class="inner-tittle two">stock list</h3>

                        <%
                            //获取当前页号
                            currentPage = (Integer) request.getAttribute("currentPage");
                            //获取记录总数
                            count = (Integer) request.getAttribute("count");
                            //获取接收到的request中的学生记录列表
                            List<Stock> list = (List<Stock>) session.getAttribute("list");

                            if (currentPage.equals(null)) {
                                currentPage = 1;
                            }
                            if (count.equals(null)) {
                                count = 0;
                            }
                            pageCount = (count-1) / 100 + 1;
                        %>

                        <div class="graph">
                            <div class="tables">

                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <form action="DetailServlet?currentPage=0&search=5year&name=<%=name%>" method="post">
                                            <input type="submit" value="Past 5 years" class="btn-search">
                                        </form>
                                        <form action="DetailServlet?currentPage=0&search=year&name=<%=name%>" method="post">
                                            <input type="submit" value="Year-to-date" class="btn-search">
                                        </form>
                                        <form action="DetailServlet?currentPage=0&search=month&name=<%=name%>" method="post">
                                            <input type="submit" value="Month-to-date" class="btn-search">
                                        </form>
                                        <form action="DetailServlet?currentPage=0&search=past&name=<%=name%>" method="post">
                                            <input type="submit" value="Past week" class="btn-search">
                                        </form>
                                        <form action="DetailServlet?currentPage=0&search=current&name=<%=name%>" method="post">
                                            <input type="submit" value="Current week" class="btn-search">
                                        </form>
                                        <form action="DetailServlet?currentPage=0&search=last&name=<%=name%>" method="post">
                                            <input type="submit" value="Last business day" class="btn-search">
                                        </form>
                                    </tr>
                                    </thead>
                                </table>

                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th style="text-align: center;background:#f1fafb" colspan="1">#</th>
                                        <th style="text-align: center;background:#f1fafb" colspan="2">name</th>
                                        <th style="text-align: center;background:#f1fafb" colspan="2">price</th>
                                        <th style="text-align: center;background:#f1fafb" colspan="2">quantity</th>
                                        <th style="text-align: center;background:#f1fafb" colspan="2">time</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%
                                        for (i = (currentPage - 1) * 100; i < currentPage * 100 && i < list.size(); i++) {
                                            if(i > count - 1) {
                                                break;
                                            }
                                    %>
                                    <tr>
                                        <th scope="row" style="text-align: center;background:#f1fafb" colspan="1"><%=i+1%></th>
                                        <td colspan="2" style="text-align: center;"><%=list.get(i).getName()%></td>
                                        <td colspan="2" style="text-align: center;"><%=list.get(i).getPrice()%></td>
                                        <td colspan="2" style="text-align: center;"><%=list.get(i).getQuantity()%></td>
                                        <td colspan="2" style="text-align: center;"><%=list.get(i).getTime()%></td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div align="center">
                            <%
                                if(currentPage == 1) {
                            %>
                            <span class="unuse">[First Page]</span>
                            <span class="unuse">[Previous Page]</span>
                            <%
                            } else {
                            %>
                            <span><a href="DetailServlet?currentPage=1&name=<%=name%>">[First Page]</a></span>
                            <span><a href="DetailServlet?currentPage=<%=currentPage - 1%>&name=<%=name%>">[Previous Page]</a> </span>
                            <%
                                }
                            %>
                            <%
                                for (i = 1; i <= pageCount; i++) {
                                    if (i == currentPage) {
                            %>
                            <span class="currentPage"><%=i%></span>
                            <%
                            } else {
                            %>
                            <span><a href="DetailServlet?currentPage=<%=i%>&name=<%=name%>"><%=i%></a></span>
                            <%
                                    }
                                }
                            %>
                            <%
                                if (currentPage == pageCount) {
                            %>
                            <span class="unuse">[Next Page]</span> <span class="unuse">[Last Page]</span>
                            <%
                            } else {
                            %>
                            <span><a href="DetailServlet?currentPage=<%=currentPage + 1%>&name=<%=name%>">[Next Page]</a> </span>
                            <span><a href="DetailServlet?currentPage=<%=pageCount%>&name=<%=name%>">[Last Page]</a> </span>
                            <%
                                }
                            %>
                            <select name="currentPage">
                                <%
                                    for (i = 1; i <= pageCount; i++) {
                                %>
                                <option value="<%=i%>" <%=(i == currentPage ? "selected" : "")%>>
                                    <%=i%>
                                </option>
                                <%
                                    }
                                %>
                            </select>
                            <input type="submit" name="jspGo" value="GO" />
                        </div>
                    </div>
                    <!--//graph-visual-->
                </div>
                <!--//outer-wp-->
            </div>
            <!--footer section start-->
            <footer>
                <p>Copyright &copy; 2018.Company name All rights reserved.</p>
            </footer>
            <!--footer section end-->
        </div>
    </div>
    <!--//content-inner-->

    <!--/sidebar-menu-->
    <div class="sidebar-menu">
        <header class="logo">
            <a href="#" class="sidebar-icon">
                <span class="fa fa-bars"></span>
            </a>
            <a href="home.jsp">
                <span id="logo">
                    <h1>LEMON</h1>
                </span>
                <!--<img id="logo" src="" alt="Logo"/>-->
            </a>
        </header>
        <div style="border-top:1px solid rgba(69, 74, 84, 0.7)"></div>
        <!--/down-->
        <div class="down">
            <a href="home.jsp">
                <span class=" name-caret"><%=session.getAttribute("UserNameSession") %></span>
            </a>
            <p>You are welcome!</p>
            <ul>
                <li>
                    <a class="tooltips" href="home.jsp">
                        <span>Profile</span>
                        <i class="lnr lnr-user"></i>
                    </a>
                </li>
                <li>
                    <a class="tooltips" href="home.jsp">
                        <span>Settings</span>
                        <i class="lnr lnr-cog"></i>
                    </a>
                </li>
                <li>
                    <a class="tooltips" href="LogoutServlet">
                        <span>Log out</span>
                        <i class="lnr lnr-power-switch"></i>
                    </a>
                </li>
            </ul>
        </div>
        <!--//down-->
        <div class="menu">
            <ul id="menu">
                <li>
                    <a href="home.jsp">
                        <i class="fa fa-tachometer"></i>
                        <span>home</span>
                    </a>
                </li>
                <li id="menu-academico">
                    <a href="StockListServlet?currentPage=1">
                        <i class="fa fa-table"></i>
                        <span>stock</span>
                        <!-- <span class="fa fa-angle-right" style="float: right"></span> -->
                    </a>
                </li>
                <li>
                    <a href="ProfileServlet?save=false">
                        <i class="fa fa-table"></i>
                        <span>profile</span>
                    </a>
                </li>
                <li>
                    <a href="BankServlet">
                        <i class="fa fa-table"></i>
                        <span>bank</span>
                    </a>
                </li>
                <li>
                    <a href="HistoryServlet">
                        <i class="fa fa-table"></i>
                        <span>history</span>
                    </a>
                </li>

            </ul>
        </div>
    </div>
    <div class="clearfix"></div>

    <script>
        var toggle = true;

        $(".sidebar-icon").click(function () {
            if (toggle) {
                $(".page-container").addClass("sidebar-collapsed").removeClass("sidebar-collapsed-back");
                $("#menu span").css({ "position": "absolute" });
            }
            else {
                $(".page-container").removeClass("sidebar-collapsed").addClass("sidebar-collapsed-back");
                setTimeout(function () {
                    $("#menu span").css({ "position": "relative" });
                }, 400);
            }

            toggle = !toggle;
        });
    </script>
    <!--js -->
    <link rel="stylesheet" href="css/vroom.css">
    <script type="text/javascript" src="js/vroom.js"></script>
    <script type="text/javascript" src="js/TweenLite.min.js"></script>
    <script type="text/javascript" src="js/CSSPlugin.min.js"></script>
    <script src="js/jquery.nicescroll.js"></script>
    <script src="js/scripts.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
</body>

</html>

