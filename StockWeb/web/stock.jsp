<%@ page import="main.java.bean.Stock" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: zhaowenteng
  Date: 11/17/19
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <%
        Integer currentPage = 1, count = 0, pageCount = 1, i = 0;
    %>
    <title>Stock</title>
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




                <!--outter-wp-->
                <div class="outter-wp">
                    <!--sub-heard-part-->
                    <div class="sub-heard-part">
                        <ol class="breadcrumb m-b-0">
                            <li class="active">Stock</li>
                        </ol>
                    </div>
                    <!--//sub-heard-part-->
                    <div class="graph-visual tables-main">
                        <h3 class="inner-tittle two">stock list</h3>

                        <!--search box-->
                        <div class="searchbox">
                            <div class="mod_select">
                                <div class="select_box">
                                    <span class="select_txt">name</span>
                                    <span class="select-icon"></span>
                                    <ul class="option">
                                        <li>name</li>
                                        <li>price</li>
                                        <li>time</li>
                                    </ul>
                                </div>
                            </div>
                            <form action="StockListServlet?currentPage=0" method="post">
                                <input type="hidden" name="type" value="name" id="select_value">
                                <input type="text" name="info" id="searchPlaceholder" class="import" placeholder="please input stock name" style="background-color: transparent;color:grey">
                                <input type="submit" value="SEARCH" class="btn-search">
                            </form>
                        </div>
                        <script src="js/jquery.min.js"></script>
                        <script>
                            $(function(){
                                $(".select_box").click(function(event){
                                    event.stopPropagation();
                                    $(this).find(".option").toggle();
                                    $(this).parent().siblings().find(".option").hide();
                                });
                                $(document).click(function(event){
                                    var eo=$(event.target);
                                    if($(".select_box").is(":visible") && eo.attr("class")!="option" && !eo.parent(".option").length)
                                        $('.option').hide();
                                });
                                $(".option li").click(function(){
                                    var check_value=$(this).text();
                                    var value0 = $('.option li:eq(0)').html();
                                    var value1 = $('.option li:eq(1)').html();
                                    var value2 = $('.option li:eq(2)').html();
                                    $(this).parent().siblings(".select_txt").text(check_value);
                                    $("#select_value").val(check_value);
                                    if(check_value == value0) {
                                        $('#searchPlaceholder').prop('placeholder','please input stock name');
                                    }else if(check_value == value1) {
                                        $('#searchPlaceholder').prop('placeholder','please input stock price');
                                    }else if(check_value == value2) {
                                        $('#searchPlaceholder').prop('placeholder','please input stock time');
                                    }
                                });
                            })
                        </script>

                        <!--//search box-->

                        <%
                            currentPage = (Integer) request.getAttribute("currentPage");
                            count = (Integer) request.getAttribute("count");
                            List<Stock> list = (List<Stock>) session.getAttribute("list");

                            if (currentPage.equals(null)) {
                                currentPage = 1;
                            }
                            if (count.equals(null)) {
                                count = 0;
                            }
                            pageCount = (count-1) / 20 + 1;
                        %>

                        <div class="graph">
                            <div class="tables">

                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th style="text-align: center;background:#f1fafb" colspan="1">#</th>
                                            <form action="StockSortServlet?type=name&currentPage=0" method="post">
                                                <th style="text-align: center;background:#f1fafb" colspan="2"><input name="name" type="submit" value="name"></th>
                                            </form>
                                            <form action="StockSortServlet?type=price&currentPage=0" method="post">
                                                <th style="text-align: center;background:#f1fafb" colspan="2"><input name="price" type="submit" value="price"></th>
                                            </form>
                                            <form action="StockSortServlet?type=quantity&currentPage=0" method="post">
                                                <th style="text-align: center;background:#f1fafb" colspan="2"><input name="quantity" type="submit" value="quantity"></th>
                                            </form>
                                            <form action="StockSortServlet?type=time&currentPage=0" method="post">
                                                <th style="text-align: center;background:#f1fafb" colspan="2"><input name="time" type="submit" value="time"></th>
                                            </form>
                                            <th style="text-align: center;background:#f1fafb" colspan="2">view detail</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <%
                                        for (i = (currentPage - 1) * 20; i < currentPage * 20 && i < list.size(); i++) {
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
                                        <form action="DetailServlet?currentPage=0&name=<%=list.get(i).getName()%>" method="post">
                                            <td colspan="2" style="text-align: center;">
                                                <input type="submit" value="View Detail">
                                            </td>
                                        </form>
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
                            <span><a href="StockListServlet?currentPage=1">[First Page]</a></span>
                            <span><a href="StockListServlet?currentPage=<%=currentPage - 1%>">[Previous Page]</a> </span>
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
                            <span><a href="StockListServlet?currentPage=<%=i%>"><%=i%></a></span>
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
                            <span><a href="StockListServlet?currentPage=<%=currentPage + 1%>">[Next Page]</a> </span>
                            <span><a href="StockListServlet?currentPage=<%=pageCount%>">[Last Page]</a> </span>
                            <%
                                }
                            %>
                            <form action="StockListServlet" style="display: inline;">
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
                                <input type="submit" class="btn" name="jspGo" value="GO" />
                            </form>
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
