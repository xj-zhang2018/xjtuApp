<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.*"%>
<%-- <%@ page import="java.util.Map.Entry" %>
<%	Map<String, String[]> map = request.getParameterMap(); 
	Set<Entry<String, String[]>> set = map.entrySet();  
    Iterator<Entry<String, String[]>> it = set.iterator();
    String params = new String(); 
	while (it.hasNext()) {  
		Entry<String, String[]> entry = it.next();
		params+=(entry.getKey()+":");  
		for (String i : entry.getValue()) {  
			params+=(i+",");  
		}
		params = params.substring(0,params.length()-1);
		params+=(";");
	} 
	if(!params.equals("")){
		params = params.substring(0,params.length()-1);
	}
	%> --%>
<script type="text/javascript" src="js/util.js"></script>
<script type="text/javascript">
	var user = '';
	var sceName = '';
	$(function() {
		user = '<s:property value="#session.XfinityUser"/>';
		if (user == '') {
			// not login
			sceName = '<s:property value="#session.user"/>';
			if(sceName==''){
				$('#loginLink').removeClass('hidden');
				$('#registryLink').removeClass('hidden');
			}else{
				$('#userLink').removeClass('hidden');
				$('#userNameA').text(sceName);
				$('#logoutLink').removeClass('hidden');
			}
		} else {
			// user login
			//set current url cookie	
			var name = '<s:property value="#session.XfinityUser.name"/>';

			$('#userLink').removeClass('hidden');
			$('#userNameA').text(name);
			$('#logoutLink').removeClass('hidden');
		}
	});
	var loginhead = function() {
		var curUrl = window.location.href;
		var is404 = /404.html/;
		var isLogin = /login.jsp/;
		var isRegister = /register.jsp/;
		var isFindPwd = /findPwd.jsp/;
		if (isLogin.test(curUrl) === true || isRegister.test(curUrl) === true
				|| isFindPwd.test(curUrl) === true) {
			Xfinity.Util.post('${pageContext.request.contextPath}/login.jsp?sc=0');
		} else {
			Xfinity.Util
					.post('${pageContext.request.contextPath}/login.jsp?redirect_url='
							+ escape(curUrl)+'&sc=0');
		}
	};
	var logout = function() {
		jQuery.ajax({
			url : "logout.action",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			type : "post",
			success : function(a, b, c) {
				location.reload(true);
			},
			error : function() {
				location.reload(true);
			}
		});
	};
	
	function checkPlatform(){
		if(user!=''){
			window.location.href= "myXfinity.jsp";
		}else if(sceName!=''){
			window.location.href="jsp/sce/mySCE.jsp";
		}else{
			window.location.href="login.jsp";
		}
	};
</script>

<div id="header" class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar-ex-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

			<a href="${pageContext.request.contextPath}/index.jsp"><img
				alt="Xfinity HPC Cloud"
				src="${pageContext.request.contextPath}/images/logo.png"
				height="50px" style="padding:5px">
			</a>
		</div>

		<div class="collapse navbar-collapse" id="navbar-ex-collapse">
			<ul id="_head" class="nav navbar-nav navbar-left">

				<li id="_homePage"><a
					href="${pageContext.request.contextPath}/index.jsp">首页<br>
				</a>
				</li>
				<li id="_myXfinity"><a
					href="javascript:void(0);" onclick="javascript:checkPlatform()">我的社区</a>
				</li>
				<li id="_support" class="hidden"><a
					href="${pageContext.request.contextPath}/support/supportUserReq.jsp">我的社区</a>
				</li>
				<li id="_platformManager" class="hidden"><a
					href="${pageContext.request.contextPath}/superadmin/userJobStatistic.jsp">我的社区</a>
				</li>
				<li id="_appCenter"><a
					href="${pageContext.request.contextPath}/appCenter.jsp">应用中心</a>
				</li>
				<li id="_resCenter"><a
					href="${pageContext.request.contextPath}/resCenter.jsp">资源中心</a>
				</li>
				<li id="_dataCenter"><a
					href="${pageContext.request.contextPath}/DataCenter.jsp">数据中心</a>
				</li>

				<li id="_help"><a href="https://xfinityhpccloud.uservoice.com/">在线反馈</a>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li id="userLink" class="hidden"><a id="userNameA"
					href="${pageContext.request.contextPath}/userInfo.jsp"><br>
				</a>
				</li>
				<li id="logoutLink" class="hidden"><a
					href="javascript:void(0);" onclick="javascript:logout()">退出<br>
				</a>
				</li>
				<li id="loginLink" class="hidden"><a href="javascript:void(0);"
					onclick="javascript:loginhead()">登录<br> </a>
				</li>
				<li id="registryLink" class="hidden"><a
					href="${pageContext.request.contextPath}/register.jsp">注册<br>
				</a>
				</li>
			</ul>

		</div>
	</div>
</div>