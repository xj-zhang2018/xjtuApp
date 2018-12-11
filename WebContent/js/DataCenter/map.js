
var arr=new Array();
var status='NoReady';
var map;
$(function () {  
  var arr=[];
    $('#container').highcharts('Map', {  
        title : {  
            text : 'xxx量统计'  
        },  
        subtitle : {  
            text : '地区分布'  
        },  
        mapNavigation: {  
            enabled: true,  
            buttonOptions: {  
                verticalAlign: 'bottom'  
            }  
        },  
        colorAxis: {  
            min: 0  
        },  
        series : [{  
            data : arr,  
            mapData: Highcharts.maps['countries/china'],  
            joinBy: 'hc-key',  
            name: 'xxx分布',  
            states: {  
                hover: {  
                    color: '#BADA55'  
                }  
            },  
            dataLabels: {  
                enabled: true,  
                format: '{point.name}'  
            }  
        }]  
    });  
});  
      
 function Cluster() {
		var start=3;
		var size=10;
		alert("集群性能查询");
		$.post("listsomeAll.action",{
				"start":start,
				"size":size
		}, function(res, b, c) {
			/*var str = JSON.stringify(res.clusterlist);*/	  
			 arr = JSON.stringify(res.data);
			 status='Ready';
		
			 if(status=='Ready')
					reDraw();
				else{
					alert("没有数据");
					return;
				}
		
		});
		
		
	}
 
 function reDraw(){
	 alert("绘图数据"+arr);
	  $('#container').highcharts('Map', {  
	        title : {  
	            text : 'xxx量统计'  
	        },  
	        subtitle : {  
	            text : '地区分布'  
	        },  
	        mapNavigation: {  
	            enabled: true,  
	            buttonOptions: {  
	                verticalAlign: 'bottom'  
	            }  
	        },  
	        colorAxis: {  
	            min: 0  
	        },  
	        series : [{  
	            data : arr,  
	            mapData: Highcharts.maps['countries/china'],  
	            joinBy: 'hc-key',  
	            name: 'xxx分布',  
	            states: {  
	                hover: {  
	                    color: '#BADA55'  
	                }  
	            },  
	            dataLabels: {  
	                enabled: true,  
	                format: '{point.name}'  
	            }  
	        }]  
	    });  
	 
	 
	 
 }
 
 