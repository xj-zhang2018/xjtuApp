var pie = new Array();
var status='NoReady';  /*用来标记加载的状态*/
var chart2;
var cpuRatio=23.0;
var maxMemory=2204672;
var freeMemory=500000;
var usedMemory=maxMemory-freeMemory;
var totalMemorySize;
var usedMemory2;
var gaugeOptions;
var chartSpeed;
var chartRpm;
var chartDisk;
var memUsage=40;
var diskUsage=60;
var CpuChart

$(document).ready(
		function() {
		
			
	 gaugeOptions = {

				    chart: {
				        type: 'solidgauge'
				    },

				    title: null,

				    pane: {
				        center: ['50%', '85%'],
				        size: '140%',
				        startAngle: -90,
				        endAngle: 90,
				        background: {
				            backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || '#EEE',
				            innerRadius: '60%',
				            outerRadius: '100%',
				            shape: 'arc'
				        }
				    },

				    tooltip: {
				        enabled: false
				    },

				    // the value axis
				    yAxis: {
				        stops: [
				            [0.1, '#55BF3B'], // green
				            [0.5, '#DDDF0D'], // yellow
				            [0.9, '#DF5353'] // red
				        ],
				        lineWidth: 0,
				        minorTickInterval: null,
				        tickAmount: 2,
				        title: {
				            y: -70
				        },
				        labels: {
				            y: 16
				        }
				    },

				    plotOptions: {
				        solidgauge: {
				            dataLabels: {
				                y: 5,
				                borderWidth: 0,
				                useHTML: true
				            }
				        }
				    }
				};

	 
		// linux The speed gauge
		chartSpeed = Highcharts.chart('cpu-speed', Highcharts.merge(gaugeOptions, {
		    yAxis: {
		        min: 0,
		        max: 100,
		        title: {
		            text: ' '
		        }
		    },

		    credits: {
		        enabled: false
		    },

		    series: [{
		        name: 'Speed',
		        data: [cpuRatio],
		        dataLabels: {
		            format: '<div style="text-align:center"><span style="font-size:25px;color:' +
		                ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
		                   '<span style="font-size:12px;color:silver">%</span></div>'
		        },
		        tooltip: {
		            valueSuffix: '%'
		        }
		    }]

		}));
		
	
		// linux The disk gauge
		chartDisk = Highcharts.chart('container-rpm', Highcharts.merge(gaugeOptions, {
		    yAxis: {
		        min: 0,
		        max: 100,
		        title: {
		            text: ' '
		        }
		    },

		    credits: {
		        enabled: false
		    },

		    series: [{
		        name: 'Speed',
		        data: [diskUsage],
		        dataLabels: {
		            format: '<div style="text-align:center"><span style="font-size:25px;color:' +
		                ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
		                   '<span style="font-size:12px;color:silver">%</span></div>'
		        },
		        tooltip: {
		            valueSuffix: '%'
		        }
		    }]

		}));
		
	 
		// linux The memory gauge
		chartMemory = Highcharts.chart('container-memory', Highcharts.merge(gaugeOptions, {
		    yAxis: {
		        min: 0,
		        max: 100,
		        title: {
		            text: ' '
		        }
		    },

		    credits: {
		        enabled: false
		    },

		    series: [{
		        name: 'Speed',
		        data: [memUsage],
		        dataLabels: {
		            format: '<div style="text-align:center"><span style="font-size:25px;color:' +
		                ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
		                   '<span style="font-size:12px;color:silver">%</span></div>'
		        },
		        tooltip: {
		            valueSuffix: '%'
		        }
		    }]

		}));
		
		
		// Bring life to the dials
				setInterval(function () {
				    // Speed
					 var point;
				       
				    GetPerformanceForLinux();
					
				    if (chartSpeed) {
				        point = chartSpeed.series[0].points[0];
				        point.update(cpuRatio);
				    }
				    
				    // RPM
				    if (chartDisk) {
				        point = chartDisk.series[0].points[0];
				        point.update(diskUsage);
				       
				    }
				    // chartMemory
				    if (chartMemory) {
				        point = chartMemory.series[0].points[0];
				        point.update(memUsage);
				    }
				},5000);
		});







/*windows性能信息*/
function GetPerformance(){
alert("集群性能参数");
	$.post("network",null 
			, function(res, b, c) {
		var str = JSON.stringify(res.monitorInfo);
		var monitorInfo = eval('(' + str + ')');
		alert("集群信息:"+"cpu占有率=" + monitorInfo.cpuRatio+"可使用内存=" + monitorInfo.totalMemory);
		alert("集群信息:"+"剩余内存=" + monitorInfo.freeMemory+"最大可使用内存" + monitorInfo.maxMemory);
		alert("集群信息:"+"操作系统" + monitorInfo.osName+"总的物理内存=" + monitorInfo.totalMemorySize);
		alert("集群信息:"+"剩余的物理内存=" + monitorInfo.freePhysicalMemorySize+"已使用的物理内存=" + monitorInfo.usedMemory+"线程总数=" + monitorInfo.totalThread);
		
		cpuRatio=monitorInfo.cpuRatio;
		 maxMemory=monitorInfo.maxMemory;
		 freeMemory=monitorInfo.freeMemory;
		 usedMemory=maxMemory-freeMemory;
		 totalMemorySize=monitorInfo.totalMemorySize;
		 usedMemory2=monitorInfo.usedMemory;
	});

		}

/*linux性能信息*/
function GetPerformanceForLinux(){
		
		var obj={type:"GET",url:"network"};
		$.ajax(obj).done(function(res){
			cpuRatio=res.cpuUsage;
			memUsage=res.memUsage;
			diskUsage=res.diskUsage;
		});
	}


