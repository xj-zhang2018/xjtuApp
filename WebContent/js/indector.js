/*计算方差等参数的图表数据*/
var rxbps;
var speed;
var txbps;
$(document).ready(function () {
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
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
                    	GetPerformanceForLinux();
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
function GetPerformanceForLinux(){
		
		var obj={type:"POST",url:"GetParm"};
		
		$.post("GetParm", { datapoint: "sensor5"},
				   function(parm){
			txbps=parm.max;
		
				   });
	}







