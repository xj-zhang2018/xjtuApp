<%@ page language="java"     pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
      <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
        <meta name="format-detection" content="telephone=no">
        <meta charset="UTF-8"> 
        <meta name="description" content="Violate Responsive Admin Template">
        <meta name="keywords" content="Super Admin, Admin, Template, Bootstrap">
        <title>数据中心</title>  
        <!-- CSS -->
        <link href="css/DataCenter/bootstrap.min.css" rel="stylesheet">
         <link href="css/DataCenter/style.css" rel="stylesheet"> 
             
             
             
             
       <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="js/jquery-ui.min.js"></script> 
        <script type="text/javascript" src="js/jquery.easing.1.3.js"></script> 
      
        <script src="js/bootstrap.min.js"></script>
        <!-- Charts -->
        <script src="js/jquery.flot.js"></script>
        <script src="js/jquery.flot.time.js"></script> 
        <script src="js/jquery.flot.animator.min.js"></script> 
        <script src="js/jquery.flot.resize.min.js"></script> 
        <script src="js/sparkline.min.js"></script> 
        <script src="js/easypiechart.js"></script> 
       <!-- <script src="../js/DataCenter/js/charts.js"></script> -->
        <!-- Map -->
        
        <script src="js/jvectormap.min.js"></script> 
        <script src="js/usa.js"></script>
        
        
        <script src="js/icheck.js"></script> 
        <script src="js/scroll.min.js"></script> 
        <script src="js/calendar.min.js"></script> 
        <script src="js/feeds.min.js"></script> 
        
        <script src="js/highcharts.js"></script>
        <script src="js/exporting.js"></script>
        <script src="js/dark-unica.js"></script>  
        
            <script src="js/UserData.js"></script> 
             <script src="js/cpu.js"></script> 
         <script src="js/network.js"></script> 
            <script src="js/DataCenter.js"></script>  
        
        

<script src="js/highcharts-3d.js"></script>
<script src="js/highcharts-more.js"></script>
<script src="js/solid-gauge.js"></script>
    </head>
    <body  > 
        <header >
      
        </header>
        
        
        <section id="main" >
            <!-- Content -->
            <section id="content" class="container">
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
                <hr class="whiter" />
                <hr class="whiter" />
                <!-- Main Widgets -->
                <div class="block-area">
                    <div class="row">
                        <div class="col-md-8">
                            <!-- Main Chart -->
                            <div class="tile">
                                <h2 class="tile-title">cpu性能</h2>
                                <div class="tile-config dropdown">
                                    <a data-toggle="dropdown" href="" class="tile-menu"></a>
                                    <ul class="dropdown-menu pull-right text-right">
                                        <li><a class="tile-info-toggle" href="">Chart Information</a></li>
                                        <li><a href="">Refresh</a></li>
                                        <li><a href="">Settings</a></li>
                                    </ul>
                                </div>
                                <div class="p-10">
                                     

       <div id="container" class="main-chart" style="height: 250px"></div>
   <div id="container-rx"   style="display: none"></div>
     <div id="container-speed" style="display: none"></div>

                                </div>  
                            </div>
    
                            <!-- Pies -->
                            <div class="tile text-center">
                          
                      <div class="tile-dark p-10">
                            <div id="container-memory" class="pie-chart-tiny" >
                                    </div>       
                                  <div id="cpu-speed" class="pie-chart-tiny" style="display: none">
                                    </div>
                                  <div id="container-rpm" class="pie-chart-tiny" >
                                    </div>
                                </div>        

                            </div>

                        
                            <div class="clearfix"></div>
                        </div>
                        
                        <div class="col-md-4">
                            <!-- USA Map -->
                            <div class="tile">
                                <h2 class="tile-title">用户角色比例</h2>
                                <div class="tile-config dropdown">
                                    <a data-toggle="dropdown" href="" class="tile-menu"></a>
                                    <ul class="dropdown-menu pull-right text-right">
                                        <li><a href="">Refresh</a></li>
                                        <li><a href="">Settings</a></li>
                                    </ul>
                                </div>
                          
                             <div id="pieChart" style="width:326px;height: 400px"></div> 
                            </div>
    
                            <!-- Dynamic Chart -->
                            <div class="tile">
                                <h2 class="tile-title">网卡发送比特数</h2>
                                <div class="tile-config dropdown">
                                    <a data-toggle="dropdown" href="" class="tile-menu"></a>
                                    <ul class="dropdown-menu pull-right text-right">
                                        <li><a href="">Refresh</a></li>
                                        <li><a href="">Settings</a></li>
                                    </ul>
                                </div>
                                <div class="p-t-10 p-r-5 p-b-5">
                                   <!--  <div id="dynamic-chart" style="height: 200px"></div> -->
                                 <!--  <div id="container" style="height: 200px"  ></div> -->
                                  <div id="container-tx" style="height: 200px"  ></div>
                               
                               
                                </div>
                            </div>
                                    </div>
                                </div>
                            </div>
                     
                 
            </section>
        </section>
   
    </body>
   
</html>
