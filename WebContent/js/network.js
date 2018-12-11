var rxbps;
var speed;
var txbps;
$(document).ready(function () {
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });
    Highcharts.chart('container-speed', {
        chart: {
            type: 'spline',
            animation: Highcharts.svg, // don't animate in old IE
            marginRight: 10,
            events: {
                load: function () {
                    var series = this.series[0];
                    setInterval(function () {
                    	GetNetWorkDetail();
                    	var x = (new Date()).getTime(), // current time
                         y =speed;
                        series.addPoint([x, y], true, true);
                    }, 2000);
                }
            }
        },
        title: {
            text: '网卡处理数据比特数'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        yAxis: {
            title: {
                text: 'Mb/s'
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
            name: 'speed',
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
    
    
    
    
    Highcharts.chart('container-rx', {
        chart: {
            type: 'spline',
            animation: Highcharts.svg, // don't animate in old IE
            marginRight: 10,
            events: {
                load: function () {
                    var series = this.series[0];
                    setInterval(function () {
                    	GetNetWorkDetail();
                        var x = (new Date()).getTime(), // current time
                            y =rxbps;
                        series.addPoint([x, y], true, true);
                    }, 2000);
                }
            }
        },
        title: {
            text: '接收数据比特数'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        yAxis: {
            title: {
                text: 'Mbps'
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
            name: 'ReceviedData',
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
    
    
 
	
    
    
    
    
    Highcharts.chart('container-tx', {
        chart: {
            type: 'spline',
            animation: Highcharts.svg, // don't animate in old IE
            marginRight: 10,
            events: {
                load: function () {
                    var series = this.series[0];
                    setInterval(function () {
                    	GetNetWorkDetail();
                        var x = (new Date()).getTime(), // current time
                            y =txbps;
                        series.addPoint([x, y], true, true);
                    }, 2000);
                }
            }
        },
        title: {
            text: '发送数据比特数'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        yAxis: {
            title: {
                text: 'Mbps'
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
            name: 'SendData',
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
function GetNetWorkDetail(){
		/*alert("网络性能监控");*/
	
	var obj={type:"GET",url:"networkDatail"};
	$.ajax(obj).done(function(NetWorkParas){
		rxbps=NetWorkParas.rxbps;
		 speed=NetWorkParas.speed;
	     txbps=NetWorkParas.txbps;
		
	});
	
	}



