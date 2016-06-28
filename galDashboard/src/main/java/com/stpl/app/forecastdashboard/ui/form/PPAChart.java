/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastdashboard.ui.form;

//import com.stpl.app.nonmandated.dto.ProjectionSelectionDTO;
import com.stpl.app.forecastdashboard.logic.CommonLogic;
import com.stpl.app.forecastdashboard.utils.CommonUtils;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.Lang;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.data.Property;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jboss.logging.Logger;

/**
 *
 * @author srithar
 */
public class PPAChart {

    Map<String,PeriodDTO> periodMap=new HashMap<String,PeriodDTO>();
    VerticalLayout mainLayout = new VerticalLayout();
    Chart chart = new Chart(ChartType.COLUMN);
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SPRChart.class);

    private int fromDate = 0;
    private int toDate = 0;
    private int proId=0;
    /**
     * The Constructor.
     *
     * @param dto the dto
     * @param frequency the frequency
     * @param history the history
     */
    public PPAChart(int proId) {
    this.proId=proId;
    }

    /**
     * Gets the chart.
     *
     * @return the chart
     */
     public Component getChart(int from, int to) {
        LOGGER.info("Entering getChart method ");
        chart = new Chart(ChartType.COLUMN);
        CommonLogic logic = new CommonLogic();
        Map<String,PeriodDTO> periodMap=new HashMap<String,PeriodDTO>();
        List<String> xSet = new ArrayList<String>();
        List finalList = logic.getPPAResultChartData(proId);
        periodMap = (Map<String,PeriodDTO>) finalList.get(0);
      
        final Configuration conf = chart.getConfiguration();
        
        
        ListSeries listSeries1 = new ListSeries();
        ListSeries listSeries2 = new ListSeries();
        ListSeries listSeries3 = new ListSeries();
        ListSeries listSeries4 = new ListSeries();
        
        conf.setTitle("PPA Projection Results");
        conf.setSubTitle("Projections");
        conf.disableCredits();
        conf.setExporting(Boolean.TRUE);

        final XAxis xAxis = new XAxis();
        xAxis.setTitle("Year");
        final YAxis yAxis = new YAxis();
        yAxis.setMin(0);
        yAxis.setTitle("Discount Dollar");
        conf.addyAxis(yAxis);

        final Legend legend = new Legend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setBackgroundColor("#FFFFFF");
        legend.setHorizontalAlign(HorizontalAlign.RIGHT);
        legend.setVerticalAlign(VerticalAlign.BOTTOM);
//        legend.setX(100);
//        legend.setY(90);
//        legend.setFloating(true);
        legend.setShadow(true);
      
        conf.setLegend(legend);

        final Tooltip tooltip = new Tooltip();
//        tooltip.setFormatter("this.x +': '+'$ '+ this.y ");
//        tooltip.setPointFormat("$ {point.y:%.2f}");
        tooltip.setPointFormat("{point.y:,.2f}");
        
        conf.setTooltip(tooltip);

        final PlotOptionsColumn plot = new PlotOptionsColumn();
        plot.setPointPadding(0.2);
        plot.setBorderWidth(0);
        
         listSeries1.setName("Discount $ Per Unit");

         listSeries2.setName("Discount %");

         listSeries3.setName("Unit Volume");

         listSeries4.setName("Total Discount Amount");
         
         
               
        List<Integer> keyList = new ArrayList<Integer>();
        for (Object key : periodMap.keySet()) {
            int tempValue = Integer.parseInt(String.valueOf(key));
            if (from != 0 && to != 0) {
                if (tempValue >= from && tempValue <= to) {
                    keyList.add(Integer.parseInt(String.valueOf(key)));
                }
            } else if (from != 0 && to == 0) {
                if (tempValue >= from) {
                    keyList.add(Integer.parseInt(String.valueOf(key)));
                }

            } else if (from == 0 && to != 0) {
                if (tempValue <= to) {
                    keyList.add(Integer.parseInt(String.valueOf(key)));
                }

             } else if(from==0&&to==0) {
                keyList.add(Integer.parseInt(String.valueOf(key)));
            }

        }

        Collections.sort(keyList);

        for (Integer key : keyList) {
            xSet.add(String.valueOf(key));
        }
        
         

       for(String key:xSet)
       {
           PeriodDTO pDto=periodMap.get(key);
         
           listSeries1.addData(Double.valueOf(pDto.getDiscountPerUnit()));
           listSeries2.addData(Double.valueOf(pDto.getDiscountPer()));
           listSeries3.addData(Double.valueOf(pDto.getUnitVolume()));
           listSeries4.addData(Double.valueOf(pDto.getTotDiscountAmount()));
          // xSet.add(key);
       
       }
          
        xAxis.setCategories(Arrays.copyOf(xSet.toArray(), xSet.toArray().length, String[].class));  
            
        conf.addSeries(listSeries1);
        conf.addSeries(listSeries2);
        conf.addSeries(listSeries3);
        conf.addSeries(listSeries4);
            
        conf.addxAxis(xAxis);

        chart.drawChart(conf);

        LOGGER.info("End of getChart method ");
        return chart;
    }
    
       public Component configureFields() {

        
        CommonLogic logic = new CommonLogic();
      
        List<String> xSet = new ArrayList<String>();
        List finalList = logic.getPPAResultChartData(proId);
        periodMap = (Map<String,PeriodDTO>) finalList.get(0);
       

        HorizontalLayout subLayout = new HorizontalLayout();
        final ComboBox fromPeriod = new ComboBox();
        final ComboBox toPeriod = new ComboBox();
       fromPeriod.addItem("-Select One-");
        fromPeriod.setNullSelectionItemId("-Select One-");
        toPeriod.addItem("-Select One-");
        toPeriod.setNullSelectionItemId("-Select One-");

        fromPeriod.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty().getValue() != null) {
                    fromDate = Integer.parseInt(String.valueOf(event.getProperty().getValue()));
                } else {
                    fromDate = 0;
                }
                if (toDate != 0) {
                    if (fromDate > toDate) {
                        fromPeriod.select("-Select One-");
                    }
                }

                mainLayout.removeComponent(chart);
                mainLayout.addComponent(getChart(fromDate, toDate));

            }
        });
        toPeriod.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
               
                if (event.getProperty().getValue() != null) {
                    toDate = Integer.parseInt(String.valueOf(event.getProperty().getValue()));
                } else {
                    toDate = 0;
                }
                if (toDate != 0) {
                    if (toDate < fromDate) {
                        toPeriod.select("-Select One-");
                    }
                }

               
                mainLayout.removeComponent(chart);
                mainLayout.addComponent(getChart(fromDate, toDate));
            }
        });
        Label fromLabel = new Label("From Period : ");
        CommonUtils.formatLabel(fromLabel);
        Label toLabel = new Label("To Period : ");
        CommonUtils.formatLabel(toLabel);
        
        subLayout.addComponent(fromLabel);
        subLayout.addComponent(fromPeriod);
        subLayout.addComponent(toLabel);
        subLayout.addComponent(toPeriod);
        subLayout.setSpacing(true);
        subLayout.setMargin(true);

        mainLayout.addComponent(subLayout);
        mainLayout.addComponent(getChart(fromDate, toDate));
        
        Set<String> keySet = periodMap.keySet();
        List<Integer> keyList = new ArrayList<Integer>();
        
       for (String key : keySet) {
            keyList.add(Integer.parseInt(key));
          
        }
        Collections.sort(keyList);

        for (Integer key : keyList) {
            fromPeriod.addItem(key);
            toPeriod.addItem(key);
        }
        
        
        return mainLayout;
    }

  
}