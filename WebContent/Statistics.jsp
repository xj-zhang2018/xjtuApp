<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>寄云的数据</title>
		<%--   <%@ include file="lib.jsp"%> --%>
		
	</head>
	<body>
	 <link href="css/DataCenter/style.css" rel="stylesheet"> 
	
 <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
 


<div class="block-area shortcut-area"  >
                   
                  
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

<div align="center">


<iframe
  width="600"
  height="400"
  seamless
  frameBorder="10"
  scrolling="no"
  src="http://tsdb.neuseer.com:8089/neucloud/explore/druid/153/?form_data=%7B%22datasource%22%3A%22153__druid%22%2C%22viz_type%22%3A%22bar%22%2C%22slice_id%22%3A62%2C%22granularity%22%3A%22one+day%22%2C%22druid_time_origin%22%3Anull%2C%22since%22%3A%2290+days+ago%22%2C%22until%22%3A%22now%22%2C%22metrics%22%3A%5B%22count%22%5D%2C%22groupby%22%3A%5B%22no%22%5D%2C%22limit%22%3A100%2C%22timeseries_limit_metric%22%3A%22count%22%2C%22show_brush%22%3Atrue%2C%22show_legend%22%3Atrue%2C%22show_bar_value%22%3Afalse%2C%22rich_tooltip%22%3Atrue%2C%22contribution%22%3Afalse%2C%22line_interpolation%22%3A%22linear%22%2C%22bar_stacked%22%3Afalse%2C%22bottom_margin%22%3A%22auto%22%2C%22show_controls%22%3Afalse%2C%22x_axis_format%22%3A%22smart_date%22%2C%22y_axis_format%22%3A%22.3s%22%2C%22x_axis_showminmax%22%3Atrue%2C%22reduce_x_ticks%22%3Afalse%2C%22x_axis_label%22%3A%22time%22%2C%22y_axis_label%22%3A%22number%22%2C%22y_axis_bounds%22%3A%5B0%2C1200%5D%2C%22y_log_scale%22%3Afalse%2C%22rolling_type%22%3A%22None%22%2C%22time_compare%22%3Anull%2C%22num_period_compare%22%3A%22%22%2C%22period_ratio_type%22%3A%22growth%22%2C%22resample_how%22%3Anull%2C%22resample_rule%22%3Anull%2C%22resample_fillmethod%22%3Anull%2C%22filters%22%3A%5B%5D%2C%22having_filters%22%3A%5B%5D%7D&standalone=true&height=400&kiosk=true"
>
</iframe>


<iframe
  width="600"
  height="400"
  seamless
  frameBorder="10"
  scrolling="no"
  src="http://tsdb.neuseer.com:8089/neucloud/explore/druid/153/?form_data=%7B%22datasource%22%3A%22153__druid%22%2C%22viz_type%22%3A%22pie%22%2C%22slice_id%22%3A69%2C%22granularity%22%3A%22one+day%22%2C%22druid_time_origin%22%3A%22%22%2C%22since%22%3A%227+days+ago%22%2C%22until%22%3A%22now%22%2C%22metrics%22%3A%5B%22count%22%5D%2C%22groupby%22%3A%5B%22id%22%5D%2C%22limit%22%3A50%2C%22pie_label_type%22%3A%22key%22%2C%22donut%22%3Afalse%2C%22show_legend%22%3Atrue%2C%22labels_outside%22%3Atrue%2C%22filters%22%3A%5B%5D%2C%22having_filters%22%3A%5B%5D%7D&standalone=true&height=400&kiosk=true"
>
</iframe>




<iframe
  width="600"
  height="400"
  seamless
  frameBorder="10"
  scrolling="no"
  src="http://tsdb.neuseer.com:8089/neucloud/explore/druid/153/?form_data=%7B%22datasource%22%3A%22153__druid%22%2C%22viz_type%22%3A%22compare%22%2C%22slice_id%22%3A68%2C%22granularity%22%3A%22week%22%2C%22druid_time_origin%22%3Anull%2C%22since%22%3A%2290+days+ago%22%2C%22until%22%3A%22now%22%2C%22metrics%22%3A%5B%22count%22%5D%2C%22groupby%22%3A%5B%5D%2C%22limit%22%3A50%2C%22timeseries_limit_metric%22%3A%22count%22%2C%22x_axis_format%22%3A%22smart_date%22%2C%22y_axis_format%22%3A%22.3s%22%2C%22rolling_type%22%3A%22None%22%2C%22time_compare%22%3Anull%2C%22num_period_compare%22%3A%22%22%2C%22period_ratio_type%22%3A%22growth%22%2C%22resample_how%22%3Anull%2C%22resample_rule%22%3Anull%2C%22resample_fillmethod%22%3Anull%2C%22filters%22%3A%5B%5D%2C%22having_filters%22%3A%5B%5D%7D&standalone=true&height=400&kiosk=true"
>
</iframe>


<iframe
  width="600"
  height="400"
  seamless
  frameBorder="10"
  scrolling="no"
  src="http://tsdb.neuseer.com:8089/neucloud/explore/druid/153/?form_data=%7B%22datasource%22%3A%22153__druid%22%2C%22viz_type%22%3A%22word_cloud%22%2C%22slice_id%22%3A64%2C%22granularity%22%3A%22one+day%22%2C%22druid_time_origin%22%3Anull%2C%22since%22%3A%227+days+ago%22%2C%22until%22%3A%22now%22%2C%22series%22%3A%22id%22%2C%22metric%22%3A%22count%22%2C%22limit%22%3A50%2C%22size_from%22%3A%2220%22%2C%22size_to%22%3A%22150%22%2C%22rotation%22%3A%22flat%22%2C%22filters%22%3A%5B%5D%2C%22having_filters%22%3A%5B%5D%7D&standalone=true&height=400&kiosk=true"
>
</iframe>


<iframe
  width="600"
  height="400"
  seamless
  frameBorder="10"
  scrolling="no"
  src="http://tsdb.neuseer.com:8089/neucloud/explore/druid/153/?form_data=%7B%22datasource%22%3A%22153__druid%22%2C%22viz_type%22%3A%22bubble%22%2C%22slice_id%22%3A66%2C%22granularity%22%3A%22week%22%2C%22druid_time_origin%22%3A%22%22%2C%22since%22%3A%2228+days+ago%22%2C%22until%22%3A%22now%22%2C%22series%22%3Anull%2C%22entity%22%3A%22id%22%2C%22size%22%3A%22sum__no%22%2C%22limit%22%3A50%2C%22show_legend%22%3Atrue%2C%22max_bubble_size%22%3A%2250%22%2C%22x%22%3A%22count%22%2C%22x_axis_format%22%3A%22.3s%22%2C%22x_axis_label%22%3A%22%22%2C%22x_log_scale%22%3Afalse%2C%22y%22%3A%22min__no%22%2C%22y_axis_format%22%3A%22.3s%22%2C%22y_axis_label%22%3A%22%22%2C%22y_log_scale%22%3Afalse%2C%22filters%22%3A%5B%5D%2C%22having_filters%22%3A%5B%5D%7D&standalone=true&height=400&kiosk=true"
>
</iframe>



</div>


	
	</body>
</html>
