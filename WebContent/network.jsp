<%@ page language="java" 
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>网络性能监控</title>
<%-- <%@ include file="lib.jsp"%> --%>

<link href="css/DataCenter/style.css" rel="stylesheet"> 
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script src="js/highcharts.js"></script>
<script src="js/exporting.js"></script>
 <script src="js/network.js"></script>
<!--  <script src="js/DataCenter/code/themes/dark-blue.js"></script> -->
  <script src="js/cpu.js"></script>
<%--   <jsp:include page="DataCenterLeft.jsp"></jsp:include>   --%>
</head>
<body>

<%-- <%@ include file="head.jsp"%> --%>

<div class="block-area shortcut-area">
                    <a class="shortcut tile" href="DataCenter.jsp">
                        <img src="css/shortcuts/connections.png" alt="">
                        <small class="t-overflow">主页</small>
                    </a>
                   
                    <a class="shortcut tile" href="neuseer.jsp">
                        <img src="css/shortcuts/connections.png" alt="">
                        <small class="t-overflow">用户数据</small>
                    </a>
                    <a class="shortcut tile" href="chart.jsp">
                        <img src="css/shortcuts/twitter.png" alt="">
                        <small class="t-overflow">集群性能</small>
                    </a>
                   
                   
                    <a class="shortcut tile" href="Statistics.jsp">
                        <img src="css/shortcuts/money.png" alt="">
                        <small class="t-overflow">统计数据</small>
                    </a>
                    <a class="shortcut tile" href="network.jsp">
                        <img src="css/shortcuts/reports.png" alt="">
                        <small class="t-overflow">网络数据</small>
                    </a>
                </div>









 <div id="container" style="min-width: 310px; height: 200px; margin: 0 auto;width: 80%;float:right;margin-right:10%"></div>
 <div id="container-speed" style="min-width: 310px; height: 200px; margin: 0 auto;width: 80%;float:right;margin-right:10%"></div>
 <div id="container-rx" style="min-width: 310px; height: 200px; margin: 0 auto;width: 80%;float:right;margin-top: 10px;margin-right:10%"></div>
 <div id="container-tx" style="min-width: 310px; height: 200px; margin: 0 auto;width: 80%;float:right;margin-top: 10px;margin-right:10%"></div>





</body>
</html>