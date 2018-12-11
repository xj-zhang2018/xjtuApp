<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>集群资源监控</title>
		<%--   <%@ include file="lib.jsp"%> --%>
		
	</head>
	<body>
	 <link href="css/DataCenter/style.css" rel="stylesheet"> 
	
 <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script src="js/highcharts.js"></script>
<script src="js/highcharts-3d.js"></script>
<script src="js/exporting.js"></script>
 <script src="js/DataCenter.js"></script>
<script src="js/highcharts-more.js"></script>
<script src="js/solid-gauge.js"></script>
<script src="js/dark-blue.js"></script> 
 <script src="js/cpu.js"></script>
 <%-- <jsp:include page="DataCenterLeft.jsp"></jsp:include>  --%>
<!-- 服务器性能参数的获取部分 -->




<%-- <%@ include file="head.jsp"%> --%>

<div class="block-area shortcut-area">
                    <a class="shortcut tile" href="network.jsp">
                        <img src="css/shortcuts/connections.png" alt="">
                        <small class="t-overflow">用户数据</small>
                    </a>
                    <a class="shortcut tile" href="chart.jsp">
                        <img src="css/shortcuts/twitter.png" alt="">
                        <small class="t-overflow">计算集群数据</small>
                    </a>
                   
                   
                    <a class="shortcut tile" href="#">
                        <img src="css/shortcuts/money.png" alt="">
                        <small class="t-overflow">HADOOP</small>
                    </a>
                    <a class="shortcut tile" href="network.jsp">
                        <img src="css/shortcuts/reports.png" alt="">
                        <small class="t-overflow">网络数据</small>
                    </a>
                </div>




<div style="width:80%%; height: 550px; margin: 0 auto">
    <div id="cpu-speed" style="width: 25%; height: 200px; float: left;margin-left: 10%"></div>
    <div id="container-rpm" style="width: 25%; height: 200px; float: left;margin-left: 2%"></div>
    <div id="container-memory" style="width: 25%; height: 200px; float:right;margin-right: 10%"></div>
 <div id="container" style="height: 370px;width:80%;margin: 0 auto"  ></div>
</div>
	
	
	
	 
	
	</body>
</html>
