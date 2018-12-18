package org.xjtu.demo;
import java.util.ArrayList;
import java.util.List;

public class Echarts {
    public List<String> legend = new ArrayList<String>();
    public List<String> category = new ArrayList<String>();//������  
    public List<Series> series = new ArrayList<Series>();//������  


    public Echarts(List<String> legendList, List<String> categoryList, List<Series> seriesList) {  
        super();  
        this.legend = legendList;  
        this.category = categoryList;  
        this.series = seriesList;  
    }  

}
