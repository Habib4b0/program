/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.forecastdashboard.ui.form;

import com.stpl.app.forecastdashboard.logic.ChartCommonLogic;
import com.stpl.app.forecastdashboard.logic.CommonLogic;
import com.stpl.app.forecastdashboard.utils.CommonUtils;
import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Lang;
import com.vaadin.addon.ipcforliferay.LiferayIPC;
import com.vaadin.addon.ipcforliferay.event.LiferayIPCEvent;
import com.vaadin.addon.ipcforliferay.event.LiferayIPCEventListener;
import com.vaadin.data.Property;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Lokeshwari
 */
public class DashBoardGraph extends VerticalLayout implements View {
 
 private final Resource graphImage = new ThemeResource("../../icons/chart-4 (1).png");
    LiferayIPC liferayIPC;
   public Button graphIcon=new Button();
   private int proId=0;
   private String projName=StringUtils.EMPTY;
   private String projType=StringUtils.EMPTY;
   String panelCaption = StringUtils.EMPTY;
   String panelCaptionUnit = StringUtils.EMPTY;
   Panel panel = new Panel();
   Panel panelUnit = new Panel();
   List list=Collections.EMPTY_LIST;
   List<CommonLevelDto> dpr=Collections.EMPTY_LIST;
   List<CommonLevelDto> finalList=Collections.EMPTY_LIST;
   List<String> xasis=Collections.EMPTY_LIST;
   ChartCommonLogic logic2 = new ChartCommonLogic();
   private ChartType chartCategory=ChartType.COLUMN;
   private ChartType chartCategoryUnit=ChartType.COLUMN;
   
   HorizontalLayout layout = new HorizontalLayout();
   HorizontalLayout hLayout = new HorizontalLayout();
   HorizontalLayout hLayoutUnit = new HorizontalLayout();
   VerticalLayout vlLayout=new VerticalLayout();
   VerticalLayout vLayout = new VerticalLayout();
   VerticalLayout vLayoutUnit = new VerticalLayout();
   TabSheet tabSheet = new TabSheet();
   TabSheet tabSheetUnit = new TabSheet();
   private boolean isStack = false;
   private boolean unitSatck =false;
    public DashBoardGraph(LiferayIPC liferayIPC) {
         
         this.liferayIPC=liferayIPC;
       // init();
        createTabSheet();
        configureChartUI();
    }     
     
    public void createTabSheet() {
         
//        liferayIPC.addLiferayIPCEventListener("reciveProjection",
//        final OptionGroup chartType = new OptionGroup();
//        chartType.addStyleName("horizontal");
//        chartType.addItems(CommonUtils.BAR_CHART, CommonUtils.LINE_CHART, CommonUtils.PIE_CHART);
//        chartType.select(CommonUtils.BAR_CHART);
//        chartType.addValueChangeListener(new Property.ValueChangeListener() {
//
//            @Override
//            public void valueChange(Property.ValueChangeEvent event) {
//                System.out.println("String.valueOf(event.getProperty().getValue()):=================>"+String.valueOf(event.getProperty().getValue()));
//                if (CommonUtils.LINE_CHART.equals(String.valueOf(event.getProperty().getValue()))) {
//                    chartCategory = ChartType.LINE;
//                    removeComponent(vlLayout);
//                    vlLayout = new VerticalLayout();
//                    layout = new HorizontalLayout();
//                    layout.setSizeFull();
//                    layout.addComponent(getSalesTab(proId, projName, projType, ChartType.LINE));
//                    layout.addComponent(getUnitTab(proId, projName, projType, ChartType.LINE));
//                    vlLayout.addComponent(layout);
//                    vlLayout.addComponent(chartType);
//                    addComponent(vlLayout);
//                } else if (CommonUtils.BAR_CHART.equals(String.valueOf(event.getProperty().getValue()))) {
//                    chartCategory = ChartType.COLUMN;
//                    removeComponent(vlLayout);
//                    vlLayout = new VerticalLayout();
//                    layout = new HorizontalLayout();
//                    layout.setSizeFull();
//                    layout.addComponent(getSalesTab(proId, projName, projType, ChartType.COLUMN));
//                    layout.addComponent(getUnitTab(proId, projName, projType, ChartType.COLUMN));
//                    vlLayout.addComponent(layout);
//                    vlLayout.addComponent(chartType);
//                    addComponent(vlLayout);
//                } else {
//                    chartCategory = ChartType.PIE;
//                    removeComponent(vlLayout);
//                    vlLayout = new VerticalLayout();
//                    layout = new HorizontalLayout();
//                    layout.setSizeFull();
//                    layout.addComponent(getSalesTab(proId, projName, projType, ChartType.PIE));
//                    layout.addComponent(getUnitTab(proId, projName, projType, ChartType.PIE));
//                    vlLayout.addComponent(layout);
//                    vlLayout.addComponent(chartType);
//                    addComponent(vlLayout);
//
//                }
//
//            }    
//         });
         liferayIPC.addLiferayIPCEventListener("reciveProjection",
                new LiferayIPCEventListener() {
                    public void eventReceived(LiferayIPCEvent event) {
//                        System.out.println("comming inside ");
//                        Notification.show("Got event " + event.getEventId()
//                      
                        String values[]=event.getData().split("~");
                        
                        proId=Integer.parseInt(values[0]);
                        System.out.println("====values of two=====================>>>>"+values[2]);
                        list = logic2.getResults(proId, "ANNUAL", values[2]);
                        if ("Non Mandated".equals(values[2])) {
                            System.out.println("==one====================");
                            dpr = (List<CommonLevelDto>) list.get(0);
                            finalList = (List<CommonLevelDto>) list.get(1);
                            xasis = (List<String>) list.get(2);
                        } else if ("Mandated".equals(values[2])) {
                            System.out.println("===two==========================");
                            finalList = (List<CommonLevelDto>) list.get(0);
                            xasis = (List<String>) list.get(1);
                        } else if ("CFF".equals(values[2])) {
                            System.out.println("==three================================");
                            finalList = (List<CommonLevelDto>) list.get(0);
                            xasis = (List<String>) list.get(1);
                        }
//                        System.out.println("finalList.size();=========================:"+finalList.size());
                        removeComponent(layout);
                        isStack = false;
                        unitSatck =false;
                        proId=Integer.parseInt(values[0]);
                        projName=values[1];
                        projType=values[2];
                        removeComponent(vlLayout);
                        vlLayout= new VerticalLayout();
                        layout = new HorizontalLayout();
                        layout.setSizeFull();
                        layout.addComponent(getSalesTab(proId,projName,projType));
                        layout.addComponent(getUnitTab(proId,projName,projType));
                        vlLayout.addComponent(layout);
//                        vlLayout.addComponent(chartType);
                        addComponent(vlLayout);
                    }
                });
        CommonLogic logic = new CommonLogic();
        List<String> result=logic.getTopProjectionId("Z","\'Non Mandated\',\'Mandated\',\'CFF\'");
        proId=Integer.parseInt(result.get(0));
        projName=result.get(1);
        projType=result.get(2);
//        System.out.println("proId" + proId);

//        MarginInfo margininfo = new MarginInfo(false, true, false, true);
//        setMargin(margininfo);
//        setSpacing(true);
        layout = new HorizontalLayout();
        layout.setSizeFull();
       
        list = logic2.getResults(proId, "ANNUAL", projType);
        if ("Non Mandated".equals(projType)) {
            dpr = (List<CommonLevelDto>) list.get(0);
            finalList = (List<CommonLevelDto>) list.get(1);
            xasis = (List<String>) list.get(2);
        } else if ("Mandated".equals(projType)) {
            finalList = (List<CommonLevelDto>) list.get(0);
            xasis = (List<String>) list.get(1);
        }
        else if("CFF".equals(projType)){
            finalList = (List<CommonLevelDto>) list.get(0);
            xasis = (List<String>) list.get(1);
        }
        
        layout.addComponent(getSalesTab(proId,projName,projType));
        layout.addComponent(getUnitTab(proId,projName,projType));
        
        
        vlLayout.addComponent(layout);
//        vlLayout.addComponent(chartType);
        addComponent(vlLayout);
        addStyleName("mainLayout");

    }
    
  private Panel getSalesTab(final int projId,final String projName,final String projType){
      
        final OptionGroup chartType = new OptionGroup();
        chartType.addStyleName("horizontal");
        chartType.addItems(CommonUtils.BAR_CHART, CommonUtils.LINE_CHART, CommonUtils.PIE_CHART);
        chartType.select(CommonUtils.BAR_CHART);
        chartType.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                System.out.println("String.valueOf(event.getProperty().getValue()):=================>"+String.valueOf(event.getProperty().getValue()));
                if (CommonUtils.LINE_CHART.equals(String.valueOf(event.getProperty().getValue()))) {
                    chartCategory = ChartType.LINE;
                    isStack=false;
                } else if (CommonUtils.BAR_CHART.equals(String.valueOf(event.getProperty().getValue()))) {
                    chartCategory = ChartType.COLUMN;
                    isStack=false;
                } else if(CommonUtils.STACK_CHART.equals(String.valueOf(event.getProperty().getValue()))){
                    isStack=true;
                    chartCategory = ChartType.COLUMN;
                }else {
                    chartCategory = ChartType.PIE;
                    isStack=false;
                }
                tabSheet = new TabSheet();
                tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
                tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
                tabSheet.setImmediate(true);
                if ("Non Mandated".equals(projType)) {
                    SPRChart sprChart = new SPRChart(projId, projType, finalList, xasis, chartCategory,isStack);
                    PRChart pRChart = new PRChart(projId, projType, finalList, xasis, chartCategory,isStack);
                    DPRChart dprChart = new DPRChart(projId, projType, dpr, xasis, chartCategory,isStack);

                    tabSheet.addTab(sprChart.configureFields(), "Sales", null, 0);
                    tabSheet.addTab(dprChart.configureFields(), "Discount", null, 1);
                    tabSheet.addTab(pRChart.configureFields(), "Net Sales & Profitability", null, 2);
                } else if ("Mandated".equals(projType)) {

                    SPRChart sprChart = new SPRChart(projId, projType, finalList, xasis, chartCategory,isStack);
                    PRChart pRChart = new PRChart(projId, projType, finalList, xasis, chartCategory,isStack);
                    MDPRChart dprChart = new MDPRChart(projId, projType, "Sales", finalList, xasis, chartCategory,isStack);
                    tabSheet.addTab(sprChart.configureFields(), "Sales", null, 0);
                    tabSheet.addTab(dprChart.configureFields(), "Discount", null, 1);
                    tabSheet.addTab(pRChart.configureFields(), "Net Sales & Profitability", null, 2);
                } else if ("CFF".equals(projType)) {
                    CFFSalesChart cffResultsChart = new CFFSalesChart(projId, projType, finalList, xasis, chartCategory,isStack);
                    tabSheet.addTab(cffResultsChart.configureFields(), "Net Sales & Profitability", null, 0);
                }
                vLayout.removeAllComponents();
                hLayout.removeAllComponents();
                vLayout.addComponent(tabSheet);
                chartType.select(chartCategory);
                Label chartOptionLable = new Label("Chart Options: ");
                CommonUtils.formatLabel(chartOptionLable);
                hLayout.addComponent(chartOptionLable);
                hLayout.setSpacing(true);
                hLayout.setMargin(true);
                hLayout.addComponent(chartType);
                vLayout.addComponent(hLayout);
            }    
         });
        tabSheet = new TabSheet();
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheet.setImmediate(true);
        System.out.println("chartCategory at 242 is ========"+chartCategory);
        if("Non Mandated".equals(projType)){
         System.out.println("xasis:================>"+xasis.size());
            SPRChart sprChart = new SPRChart(projId,projType,finalList,xasis,ChartType.COLUMN,isStack);
            PRChart pRChart = new PRChart(projId,projType,finalList,xasis,ChartType.COLUMN,isStack);
            DPRChart dprChart = new DPRChart(projId,projType,dpr,xasis,ChartType.COLUMN,isStack);

            tabSheet.addTab(sprChart.configureFields(),"Sales", null, 0);
            tabSheet.addTab(dprChart.configureFields(),"Discount", null, 1);
            tabSheet.addTab(pRChart.configureFields(),"Net Sales & Profitability", null,2);
        }else if("Mandated".equals(projType)){

            SPRChart sprChart = new SPRChart(projId,projType,finalList,xasis,ChartType.COLUMN,isStack);
            PRChart pRChart = new PRChart(projId,projType,finalList,xasis,ChartType.COLUMN,isStack);
            MDPRChart dprChart = new MDPRChart(projId,projType,"Sales",finalList,xasis,ChartType.COLUMN,isStack);
            tabSheet.addTab(sprChart.configureFields(),"Sales", null, 0);
            tabSheet.addTab(dprChart.configureFields(),"Discount", null, 1);
            tabSheet.addTab(pRChart.configureFields(),"Net Sales & Profitability", null,2);
        } else if("CFF".equals(projType)){
           CFFSalesChart cffResultsChart = new CFFSalesChart(projId,projType,finalList,xasis,ChartType.COLUMN,isStack);
           tabSheet.addTab(cffResultsChart.configureFields(),"Net Sales & Profitability", null, 0);
        }   
        vLayout.removeAllComponents();
        hLayout.removeAllComponents();
        vLayout.addComponent(tabSheet);
        Label chartOptionLable = new Label("Chart Options: ");
        CommonUtils.formatLabel(chartOptionLable);
        hLayout.addComponent(chartOptionLable);
        hLayout.setSpacing(true);
        hLayout.setMargin(true);
        hLayout.addComponent(chartType);
        vLayout.addComponent(hLayout);

        panel.setContent(vLayout);
        panel.setWidth("50%");
        panel.setCaption(projName+" Sales");
        tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {

            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                Tab tab = (Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                if("Discount".equals(tab.getCaption())) {
                    panel.setCaption(projName+" Amount");
                } else {
                    panel.setCaption(projName+" Sales");
                }
            }
        });
    return panel;
    }
  
  private Panel getUnitTab(final int projId,final String projName,final String projType){
               
        final OptionGroup chartType = new OptionGroup();
        chartType.addStyleName("horizontal");
        chartType.addItems(CommonUtils.BAR_CHART, CommonUtils.LINE_CHART, CommonUtils.PIE_CHART);
        chartType.select(CommonUtils.BAR_CHART);
        chartType.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                System.out.println("String.valueOf(event.getProperty().getValue()):=================>"+String.valueOf(event.getProperty().getValue()));
                if (CommonUtils.LINE_CHART.equals(String.valueOf(event.getProperty().getValue()))) {
                    unitSatck=false;
                    chartCategoryUnit = ChartType.LINE;
                } else if (CommonUtils.BAR_CHART.equals(String.valueOf(event.getProperty().getValue()))) {
                    chartCategoryUnit = ChartType.COLUMN;
                    unitSatck=false;
                }else if(CommonUtils.STACK_CHART.equals(String.valueOf(event.getProperty().getValue()))){
                    unitSatck=true;
                    chartCategoryUnit = ChartType.COLUMN;
                } else {
                    chartCategoryUnit = ChartType.PIE;
                    unitSatck=false;
                }
                tabSheetUnit = new TabSheet();
                tabSheetUnit.addStyleName(ValoTheme.TABSHEET_FRAMED);
                tabSheetUnit.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
                tabSheetUnit.setImmediate(true);
                if ("Non Mandated".equals(projType)) {
                    SPRUnitChart sprChart = new SPRUnitChart(projId, projType, finalList, xasis, chartCategoryUnit,unitSatck);
                    PRUnitChart pRChart = new PRUnitChart(projId, projType, finalList, xasis, chartCategoryUnit,unitSatck);
                    DPRUnitChart dprChart = new DPRUnitChart(projId, projType, dpr, xasis, chartCategoryUnit,unitSatck);

                    tabSheetUnit.addTab(sprChart.configureFields(), "Sales", null, 0);
                    tabSheetUnit.addTab(dprChart.configureFields(), "Discount", null, 1);
                    tabSheetUnit.addTab(pRChart.configureFields(), "Net Sales & Profitability", null, 2);
                } else if ("Mandated".equals(projType)) {

                    SPRUnitChart sprChart = new SPRUnitChart(projId, projType, finalList, xasis, chartCategoryUnit,unitSatck);
                    PRUnitChart pRChart = new PRUnitChart(projId, projType, finalList, xasis, chartCategoryUnit,unitSatck);
                    MDPRChart dprChart = new MDPRChart(projId, projType, "Units", finalList, xasis, chartCategoryUnit,unitSatck);
                    tabSheetUnit.addTab(sprChart.configureFields(), "Sales", null, 0);
                    tabSheetUnit.addTab(dprChart.configureFields(), "Discount", null, 1);
                    tabSheetUnit.addTab(pRChart.configureFields(), "Net Sales & Profitability", null, 2);
                } else if ("CFF".equals(projType)) {
                    CFFUnitChart cffResultsChart = new CFFUnitChart(projId, projType, finalList, xasis, chartCategoryUnit,unitSatck);
                    tabSheetUnit.addTab(cffResultsChart.configureFields(), "Net Sales & Profitability", null, 0);
                }
                vLayoutUnit.removeAllComponents();
                hLayoutUnit.removeAllComponents();
                vLayoutUnit.addComponent(tabSheetUnit);
                chartType.select(chartCategoryUnit);
                Label chartOptionLable = new Label("Chart Options: ");
                CommonUtils.formatLabel(chartOptionLable);
                hLayoutUnit.addComponent(chartOptionLable);
                hLayoutUnit.setSpacing(true);
                hLayoutUnit.setMargin(true);
                hLayoutUnit.addComponent(chartType);
                vLayoutUnit.addComponent(hLayoutUnit);
            }    
         });
        tabSheetUnit=new  TabSheet();
        tabSheetUnit.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheetUnit.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheetUnit.setImmediate(true);        
        if("Non Mandated".equals(projType)){
            System.out.println("xasis:================>"+xasis.size());
            SPRUnitChart sprChart = new SPRUnitChart(projId,projType,finalList,xasis,ChartType.COLUMN,unitSatck);
            PRUnitChart pRChart = new PRUnitChart(projId,projType,finalList,xasis,ChartType.COLUMN,unitSatck);
            DPRUnitChart dprChart = new DPRUnitChart(projId,projType,dpr,xasis,ChartType.COLUMN,unitSatck);
            tabSheetUnit.addTab(sprChart.configureFields(),"Sales", null, 0);
            tabSheetUnit.addTab(dprChart.configureFields(),"Discount", null, 1);
            tabSheetUnit.addTab(pRChart.configureFields(),"Net Sales & Profitability", null,2);
        }else if("Mandated".equals(projType)){
            SPRUnitChart sprChart = new SPRUnitChart(projId,projType,finalList,xasis,ChartType.COLUMN,unitSatck);
            PRUnitChart pRChart = new PRUnitChart(projId,projType,finalList,xasis,ChartType.COLUMN,unitSatck);
            MDPRChart dprChart = new MDPRChart(projId,projType,"Units",finalList,xasis,ChartType.COLUMN,unitSatck);
            tabSheetUnit.addTab(sprChart.configureFields(),"Sales", null, 0);
            tabSheetUnit.addTab(dprChart.configureFields(),"Discount", null, 1);
            tabSheetUnit.addTab(pRChart.configureFields(),"Net Sales & Profitability", null,2);
        }else if("CFF".equals(projType)){
           CFFUnitChart cffResultsChart = new CFFUnitChart(projId,projType,finalList,xasis,ChartType.COLUMN,unitSatck);
           tabSheetUnit.addTab(cffResultsChart.configureFields(),"Net Sales & Profitability", null, 0);
        }   
        vLayoutUnit.removeAllComponents();
        hLayoutUnit.removeAllComponents();
        vLayoutUnit.addComponent(tabSheetUnit);
        Label chartOptionLable = new Label("Chart Options: ");
        CommonUtils.formatLabel(chartOptionLable);
        hLayoutUnit.addComponent(chartOptionLable);
        hLayoutUnit.setSpacing(true);
        hLayoutUnit.setMargin(true);
        hLayoutUnit.addComponent(chartType);
        vLayoutUnit.addComponent(hLayoutUnit);
       
      
        panelUnit.setContent(vLayoutUnit);
        panelUnit.setWidth("50%");
        panelUnit.setCaption(projName+" Units");
        tabSheetUnit.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {

            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                Tab tab = (Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                if("Discount".equals(tab.getCaption())) {
                    panelUnit.setCaption(projName+" Rate");
                } else {
                    panelUnit.setCaption(projName+" Units");
                }
            }
        });
    return panelUnit;
    }
     
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
       
    
    }

    private void configureChartUI() {
        Lang a = new Lang();
        a.setThousandsSep(",");
        ChartOptions.get().setLang(a);
    }

    
}
