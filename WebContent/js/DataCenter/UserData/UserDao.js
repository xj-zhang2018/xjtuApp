var flag=true;
var arr = new Array();
var status='NoReady';
var chart;
$(document).ready(function () {
	$(".selemenu").click(function(){
	$(this).next().slideToggle();
	$(this).parents().siblings().find(".citylist,.citylist2").slideUp();
	if($("#chart").css("display")=='block')
		$("#chart").css("display","none");
	})
$(".citylist span").click(function(){
	var text=$(this).text();
	$(this).parent().prev().html(text);
	$(this).parent().prev().css("color","#666")
	$(this).parent().fadeOut();
	if($("#chart").css("display")=='block')
		$("#chart").css("display","none");
	})

  $(function(){
    $(document).not($(".selectbox")).click(function(){
        $(".citylist,.citylist2").slideUp();
	 })
	 $(".selectbox").click(function(event){
        event.stopPropagation();
        if($("#chart").css("display")=='block')
    		$("#chart").css("display","none");
    })
	})
	
	
	
	//统计图
chart = new Highcharts.Chart({
		credits : {
			enabled : false
		},
		exporting : {
			enabled : false
		},
		tooltip : {
			xDateFormat : '%Y-%m-%d, %A'// 鼠标移动到趋势线上时显示的日期格式
		},
		chart : {
			renderTo : 'chart',
			type : 'line',
			height : 350,
			/* backgroundColor:'#fff'*/
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
	
	
});
function search(){
/*	alert("查询数据");*/
	var SeclectType;
	var Year;
	var Month;
	

	var divs=$(".selemenu").each(function(key,value){
      
        if(key==0){
        	if($(this).text()=="Register")SeclectType=2;
        	else if($(this).text()=="Login")SeclectType=1;
        	else {
        		SeclectType=$(this).text();
        	}
        }
        
        else if(key==1){
        	Year=$(this).text();
        }
        
        else if(key==2){
        	Month=$(this).text();
        }
        else{
        	return;
        }
	});
	Verfy(SeclectType,Year,Month);
	if(!flag)return;
	findAllLog(SeclectType,Year,Month);
}




function findAllLog(type,year,startMonth) {
	var beginDate=year+"-"+startMonth+"-01"+"T00:00:00";
	var endDate=year+"-"+startMonth+"-30"+"T23:59:59";
	
	$.post("FindUserData.action", {
		"beginDate" : beginDate,
		"endDate" :endDate,
		"type":type
	
	}, function(res, b, c) {
		var logs = JSON.stringify(res.days);
		var days = eval('(' + logs + ')');
		
		var logs2 = JSON.stringify(res.days2);
		var days2 = eval('(' + logs2 + ')');
		/*alert("注册类型2的数据是"+days2);*/
		
	/*	alert("days==="+days)*/
		
		
		if(type==1){
			for (var i = 0; i < days.length; i++) {
				var temp = new Array();
				var tt = days[i].dayNum.split(" ")[0];
				var count = days[i].visitedCount;
				temp.push(Date.UTC(tt.split("-")[0], tt.split("-")[1] - 1, tt
						.split("-")[2]));
				temp.push(parseInt(count));
				arr.push(temp);
				status='Ready';
			}
		}
		if(type==2){
		for (var i = 0; i < days2.length; i++) {
			var temp = new Array();
			var tt = days2[i].dayNum.split(" ")[0];
			var count = days2[i].visitedCount;
			temp.push(Date.UTC(tt.split("-")[0], tt.split("-")[1] - 1, tt
					.split("-")[2]));
			temp.push(parseInt(count));
			arr.push(temp);
			status='Ready';
		}
		}
		
		if(status=='Ready'){
			reDraw();
		}else{
			alert("数据加载失败");
			return;
		}
		
		
		
	});
	
}





function Verfy(type,year,month){
	if(isNaN(type)){
		alert("请选择查询类型")
		flag=false;
		
	}
	if(isNaN(year)){
		alert("请选择查询年份")
		flag=false;
	
	}
	if(isNaN(month)){
		alert("请选择查询月份")
		flag=false;
	}
	
}



function reDraw() {
	
	
	if($("#chart").css("display")=='none')
		$("#chart").css("display","block");
		else
	   $("#chart").css("display","none");
	chart = new Highcharts.Chart({
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
			renderTo : 'chart',
			type : 'line',
			height : 350,
		},
		xAxis : {
			type : 'datetime',
			dateTimeLabelFormats : {
				day : '%m-%d',
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
	arr =[];
		
}





