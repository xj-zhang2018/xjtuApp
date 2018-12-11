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

$(document).ready(function () {
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });

    Highcharts.chart('container', {
        chart: {
            type: 'spline',
            animation: Highcharts.svg, // don't animate in old IE
            marginRight: 10,
            events: {
                load: function () {
                	 
                    var series = this.series[0];
                    setInterval(function () {
                    	GetPerformanceForLinux();
                    	
                    	
                        var x = (new Date()).getTime(), // current time
                            y =cpuRatio;
                        series.addPoint([x, y], true, true);
                    }, 3000);
                }
            }
        },
        title: {
            text: 'Cpu使用率'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        yAxis: {
            title: {
                text: 'Value'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                    Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                    Highcharts.numberFormat(this.y, 2);
            }
        },
        legend: {
            enabled: false
        },
        exporting: {
            enabled: false
        },
        series: [{
            name: 'CpuUsageRation',
            data: (function () {
                // generate an array of random data
                var data = [],
                    time = (new Date()).getTime(),
                    i;

                for (i = -19; i <= 0; i += 1) {
                    data.push({
                        x: time + i * 1000,
                        y: Math.random()*100
                    });
                }
                return data;
            }())
        }]
    });
});



/*linux性能信息*/
function GetPerformanceForLinux(){
		
		var obj={type:"GET",url:"network"};
		$.ajax(obj).done(function(res){
			cpuRatio=res.cpuUsage;
			memUsage=res.memUsage;
			diskUsage=res.diskUsage;
			
			
		});
	}





/*windows性能信息*/
function GetPerformance(){

	var obj={type:"GET",url:"network"};
	$.ajax(obj).done(function(res){
		cpuRatio=res.cpuUsage;
		memUsage=res.memUsage;
		diskUsage=res.diskUsage;
	});
}



