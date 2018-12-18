  

var myChart
var come="SET2"

$(document).ready(function () {
      // 基于准备好的dom，初始化echarts实例
    myChart = echarts.init(document.getElementById('line'));
        // 指定图表的配置项和数据
        var option = {
            title : {
                text : '通道数据值',
                subtext : ''
            },
            tooltip : {
                show : true,
                trigger : 'axis'
            },
            legend : {
                data : [ "value" ]
            //图例
            },
            dataZoom : [ {
                type : 'inside', //支持鼠标滚动缩放
                start : 0, //默认数据初始缩放范围为10%到90%
                end : 100
            } ],
            toolbox : {
                show : true,
                feature : {
                    mark : {
                        show : false
                    },
                    dataView : {
                        show : true,
                        readOnly : false
                    },
                    magicType : {
                        show : true,
                        type : [ 'bar', 'line' ]
                    },
                    restore : {
                        show : true
                    },
                    saveAsImage : {
                        show : true
                    }
                }
            },
            calculable : true,
            xAxis : [ {
                type : 'category',
                boundaryGap : false,
                data : []
            } ],
            yAxis : [ {
                name : 'value', //Y轴提示
                type : 'value',
                //min: 0,
                //max: 30,
                // interval: 1,   //Y轴数据跨度              
                axisLabel : {
                   /* formatter : '{value}' //Y轴单位
*/                }
            } ],
            series : [ {
                "name" : "value",
                "type" : "line",
                "data" : [],
                "smooth" : true, //主题--线条平滑
                "barWidth" : "70", //柱子宽度
                "symbol" : 'emptycircle', //设置折线图中表示每个坐标点的符号；emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形   
                "markPoint" : {
                    data : [ {
                        type : 'max',
                        name : '最大值'
                    }, {
                        type : 'min',
                        name : '最小值'
                    } ]
                },
                "markLine" : {
                    data : [ {
                        type : 'average',
                        name : '平均值'
                    } ]
                },
                //设置柱状图和节点的颜色
                itemStyle : {
                    normal : {
                        color : '#228B22',
                        //设置折线的颜色
                        lineStyle : {
                            color : '#228B22'
                        },
                        //以下为柱状图顶部是否显示，显示位置和显示格式的设置了
                        label : {
                            show : true,
                            textStyle : {
                                color : '#00CD66'
                            },
                           /* position : 'botton',
                            formatter : '\n{b}\n{c}（）'*/
                        }
                    }
                }
            } //第一条折线
            ]
        };

        
        
        
        
        // 使用刚指定的配置项和数据显示图表。        
        myChart.setOption(option);
        //myChart.showLoading();
        getData(come);
      
   
        
        $(function() {
            $('input:radio[name="options"]').change(function() {
            	getInfo();
            });
        });
        
        
        
        
        
        
        
        

}); 
        
function changeChart(){ 
	//获取被选中的option标签 
	var now = $('select  option:selected').val(); 
	var come1=now
	   getData(come1);
}
	


//从后台获取数据的操作

function getData(come) {
	var begin = document.getElementById("beginTime").value.toString();
	var end = document.getElementById("endTime").value;
	begin=begin.replace(/T/g," ")
	end=end.replace(/T/g," ")
	var option = myChart.getOption();           
	$.ajax({
		type : "post",
		async : false, //同步执行  
		url : "Echarts",
		data : {
			isIncome : come,
			id : "${orderCount.accountid}",
			type : "${orderCount.accountType}",
			beginTime:begin,
			endTime:end
		},
		dataType : "json", //返回数据形式为json  
		success : function(result) {
			if (result) {
				option.legend.data = result.legend;
				option.xAxis[0].data = result.category;
				option.series[0].data = result.series[0].data;
				myChart.hideLoading();
				myChart.setOption(option);
				myChart.hideLoading();
			}
		},
		error : function(errorMsg) {
			alert("图表请求数据失败!");
			//myChart.hideLoading();
			myChart.showLoading();
		}
	});
}