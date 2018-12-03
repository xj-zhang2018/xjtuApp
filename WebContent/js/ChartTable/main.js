var arr = new Array();
var pie = new Array();
var status='NoReady';  /*用来标记加载的状态*/
// Change Chart type function
function ChangeChartType(chart, series, newType) {
	newType = newType.toLowerCase();
	for (var i = 0; i < series.length; i++) {
		var srs = series[0];
		try {
			srs.chart.addSeries({
				type : newType,
				stack : srs.stack,
				yaxis : srs.yaxis,
				name : srs.name,
				color : srs.color,
				data : srs.options.data
			}, false);
			series[0].remove();
		} catch (e) {
		}
	}
}

// Two charts definition
var chart1, chart2;

// Once DOM (document) is finished loading
$(document).ready(
		function() {

			// First chart initialization
			chart1 = new Highcharts.Chart({
				credits : {
					enabled : false
				},// 去掉highcharts.com商标
				exporting : {
					enabled : false
				},// 去掉chart不必要属性
				tooltip : {
					xDateFormat : '%Y-%m-%d, %A'// 鼠标移动到趋势线上时显示的日期格式
				},
				chart : {
					renderTo : 'chart_1',
					type : 'column',
					height : 350,
				},
				title : {
					text : '月内每日访问量统计'
				},
				xAxis : {
					categories : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月',
							'八月', '九月', '十月' ]
				},
				yAxis : {
					title : {
						text : 'Interviewed'
					}
				},
				series : null
			});

			
			chart2 = new Highcharts.Chart({
				chart : {
					renderTo : 'chart_2',
					plotBackgroundColor : null,
					plotBorderWidth : null,
					plotShadow : false,
					height : 350,
				},
				title : {
					text : '访问比例'
				},
				tooltip : {
					pointFormat : '<b>{point.percentage}%</b>',
					percentageDecimals : 1
				},
				plotOptions : {
					pie : {
						allowPointSelect : true,
						cursor : 'pointer',
						dataLabels : {
							enabled : false
						},
						showInLegend : true
					}
				},
				series : [ {
					type : 'pie',
					name : 'Dev #1',
					data : null
				} ]
			});
			// Switchers (of the Chart1 type) - onclick handler
			$('.switcher').click(function() {
				var newType = $(this).attr('id');
				ChangeChartType(chart1, chart1.series, newType);
			});
		});

function showChart2() {
	var beginDate = document.getElementById("beginTime").value;
	var endDate = document.getElementById("endTime").value;
	$.post("FindLogByHour.action", {
		"beginDate" : beginDate,
		"endDate" : endDate
	}, function(res, b, c) {
		var str = JSON.stringify(res.days);
		var days = eval('(' + str + ')');

		for (var i = 0; i < days.length; i++) {
			var temp = new Array();
			var pie_temp = new Array();
			var tt = days[i].dayNum.split(" ")[0];

			var count = days[i].visitedCount;
			temp.push(Date.UTC(tt.split("-")[0], tt.split("-")[1] - 1, tt
					.split("-")[2]));
			temp.push(parseInt(count));
			pie_temp.push(tt.split("-")[1] + "月" + tt.split("-")[2] + "号");
			pie_temp.push(count);
			arr.push(temp);
			pie.push(pie_temp);
			 status='Ready'; /*数据加载完毕*/
		}
		
		if(status=='Ready'){
			reDraw();
		}else{
			alert("数据加载失败");
		}
		
	});
}
// 开始绘制

function reDraw() {
	//alert("redraw的字符串是" + arr);

	chart1 = new Highcharts.Chart({
		credits : {
			enabled : false
		},// 去掉highcharts.com商标
		exporting : {
			enabled : false
		}, // 去掉chart不必要属性
		title : {
			text : '月内每日访问量统计'
		},
		tooltip : {
			xDateFormat : '%Y-%m-%d, %A'// 鼠标移动到趋势线上时显示的日期格式
		},
		chart : {
			renderTo : 'chart_1',
			type : 'column',
			height : 350,
		},
		xAxis : {
			type : 'datetime',
			dateTimeLabelFormats : {
				day : '%Y-%m-%d',
			// second: '%H:%M:%S',
			// minute: '%e. %b %H:%M',
			// hour: '%b/%e %H:%M',
			// day: '%e日/%b',
			// week: '%e. %b',
			// month: '%b %y',
			// year: '%Y'
			}
		/*
		 * , tickInterval: 1*24 * 3600 * 1000//间隔2天
		 */},
		yAxis : {
			title : {
				text : '访问数',
			},
			min : '0'
		},
		series : [ {
			name : '访问数',
			data : arr
		} ]
	});

	chart2 = new Highcharts.Chart({
		chart : {
			renderTo : 'chart_2',
			plotBackgroundColor : null,
			plotBorderWidth : null,
			plotShadow : false,
			height : 350,
		},
		title : {
			text : '月访问量统计饼状图'
		},
		tooltip : {
			pointFormat : '<b>{point.percentage}%</b>',
			percentageDecimals : 1
		},
		plotOptions : {
			pie : {
				allowPointSelect : true,
				cursor : 'pointer',
				dataLabels : {
					enabled : false
				},
				showInLegend : true
			}
		},
		series : [ {
			type : 'pie',
			name : 'Dev #1',
			data : pie
		} ]
	});

	arr=[];//每次绘制完毕将数据置空
	pie =[];
		
}
