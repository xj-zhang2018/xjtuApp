<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<base href="<%=basePath%>" />
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="keywords" content="xfinity" />
<meta name="description" content="xfinity hpc cloud" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<link rel='SHORTCUT ICON' type='image/ico' href='favicon.ico' />
<link rel='icon' type='image/png' href='favicon.ico' />
<link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="lib/font-awesome/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css">

<link rel="stylesheet" href="lib/jquery/jquery-ui/jquery-ui.css">
<script type="text/javascript" src="lib/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="lib/jquery/jquery-ui/jquery-ui.js" ></script>

<script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="lib/jquery.bootstrap.min.js"></script>
<script type="text/javascript" src="js/message.js"></script>
<script type="text/javascript" src="js/constant.js"></script>
<script type="text/javascript" src="js/myCookieUtil.js"></script>
<script type="text/javascript" src="js/UserUtil.js"></script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
   <script src="lib/ie-support/html5shiv.min.js"></script>
   <script src="lib/ie-support/respond.min.js"></script>
 <![endif]-->

<!-- font -->
<style type="text/css">
img,body,html {
	FONT-FAMILY: "Microsoft Yahei", Arial, Helvetica, sans-serif
}
</style>

<!-- for modal dialog, we can move the page when dialog is show. -->
<style type="text/css">
.modal-open {
	overflow: auto;
}
</style>

<!-- for back to top button -->
<style type="text/css">
.back-to-top {
	cursor: pointer;
	position: fixed;
	bottom: 20px;
	right: 0px;
	display: none;
}
</style>
<script type="text/javascript">

	// support ie8 add string trim function
	if (!String.prototype.trim) {
		String.prototype.trim = function() {
			return this.replace(/(^\s*)|(\s*$)/g, "");
		}
		String.prototype.ltrim = function() {
			return this.replace(/(^\s*)/g, "");
		}
		String.prototype.rtrim = function() {
			return this.replace(/(\s*$)/g, "");
		}
	}
	// end support ie8

	$(document)
			.ready(
					function() {
						$(
								'<a id="back-to-top" href="#" class="btn btn-primary btn-lg back-to-top" role="button" title="回顶部" data-toggle="tooltip" data-placement="left"><span class="glyphicon glyphicon-chevron-up"></span></a>')
								.appendTo("body");

						$(window).scroll(function() {
							if ($(this).scrollTop() > 50) {
								$('#back-to-top').fadeIn();
							} else {
								$('#back-to-top').fadeOut();
							}
						});
						// scroll body to 0px on click
						$('#back-to-top').click(function() {
							$('#back-to-top').tooltip('hide');
							$('body,html').animate({
								scrollTop : 0
							}, 800);
							return false;
						});
						$('#back-to-top').tooltip('show');
					});
</script>
