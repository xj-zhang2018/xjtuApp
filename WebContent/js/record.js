
      // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('line'));
        // 指定图表的配置项和数据
        var option = {
            title : {
                text : '收支明细折线图',
                subtext : 'demo'
            },
            tooltip : {
                show : true,
                trigger : 'axis'
            },
            legend : {
                data : [ "金额" ]
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
                        show : true
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
                name : '金额                       ', //Y轴提示
                type : 'value',
                //min: 0,
                //max: 30,
                // interval: 1,   //Y轴数据跨度              
                axisLabel : {
                    formatter : '{value} 元' //Y轴单位
                }
            } ],
            series : [ {
                "name" : "金额",
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
                            position : 'botton',
                            formatter : '\n{b}\n{c}（元）'
                        }
                    }
                }
            } //第一条折线
            ]
        };
        
        
        
        
        
        // 使用刚指定的配置项和数据显示图表。        
        myChart.setOption(option);
        //myChart.showLoading();
        var come = 1;
        getData(come);
      
        function getInfo() {
            var income = $("input[id='option1']:checked").val();
            var outcome = $("input[id='option2']:checked").val();
            if (income != null && outcome == null) {
                come = income;
            }
            if (income == null && outcome != null) {
                come = outcome;
            }
            if (income != null && outcome != null) {
                alert("只能选一种查看！");
            }
            getData(come);
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
        
        
        
        
        
        $(function() {
            $('input:radio[name="options"]').change(function() {
            	getInfo();
            });
        });

        
        
 