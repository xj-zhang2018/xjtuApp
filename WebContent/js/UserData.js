var pie = new Array();
var status='NoReady';  /*用来标记加载的状态*/
var chart2;

var CpuChart

$(document).ready(
		function() {
			 
			chart2 = new Highcharts.chart('pieChart', {
			    chart: {
			        type: 'pie',
			        options3d: { 
			            enabled: true,
			            alpha: 45,
			            beta: 0
			        }
			    },
			    title: {
			        text: '角色分布比例'
			    },
			    tooltip: {
			        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			    },
			    plotOptions: {
			        pie: {
			            allowPointSelect: true,
			            cursor: 'pointer',
			            depth: 35,
			            dataLabels: {
			                enabled: true,
			                format: '{point.name}'
			            }
			        }
			    },
			    series: [{
			        type: 'pie',
			        name: 'Browser share',
			        data: [
			            ['Firefox', 45.0],
			            ['IE', 26.8],
			            {
			                name: 'Chrome',
			                y: 12.8,
			                sliced: true,
			                selected: true
			            },
			            ['Safari', 8.5],
			            ['Opera', 6.2],
			            ['Others', 0.7]
			        ]
			    }]
			});
			HandleData();
		
		});





function HandleData() {
	
	
	for (var i = 0; i < 10; i++) {
			var pie_temp = new Array();
			pie_temp.push("roleName1"+i);
			pie_temp.push(10);
			pie.push(pie_temp);
		}
	
	  chart2.series[0].setData(pie);
}
	
	