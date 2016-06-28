/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.forecastdashboard.ui.form;

import com.stpl.app.forecastdashboard.logic.CommonLogic;
import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.model.Lang;
import com.vaadin.addon.ipcforliferay.LiferayIPC;
import com.vaadin.addon.ipcforliferay.event.LiferayIPCEvent;
import com.vaadin.addon.ipcforliferay.event.LiferayIPCEventListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.List;

/**
 *
 * @author Lokeshwari
 */
public class DashBoardGraph extends VerticalLayout implements View {
 
 private final Resource graphImage = new ThemeResource("../../icons/chart-4 (1).png");
    LiferayIPC liferayIPC;
   public Button graphIcon=new Button();
   TabSheet tabSheet = new TabSheet();
   Panel panel = new Panel();
     public DashBoardGraph(LiferayIPC liferayIPC) {
         
         this.liferayIPC=liferayIPC;
       // init();
        createTabSheet();
        configureChartUI();
    }
     private void init() {
         
         graphIcon.setIcon(graphImage);
         graphIcon.setWidth("300px");
         graphIcon.setHeight("300px");
         graphIcon.setCaption("Sales Projection Results");
         HorizontalLayout hlayout1 = new HorizontalLayout();
         HorizontalLayout hlayout2 = new HorizontalLayout();
         HorizontalLayout hlayout3 = new HorizontalLayout();
         HorizontalLayout hlayout4 = new HorizontalLayout();
         HorizontalLayout layout1 = new HorizontalLayout();
         HorizontalLayout layout2 = new HorizontalLayout();
         VerticalLayout vlayout = new VerticalLayout();
         vlayout.setSizeFull();
         Panel panel = new Panel();
//         SPRChart sprChart = new SPRChart();
//         PRChart pRChart = new PRChart();
//         PPAChart pPAChart = new PPAChart();
//         DPRChart dprChart = new DPRChart();
//         hlayout1.addComponent(sprChart.configureFields());
//         hlayout2.addComponent(dprChart.configureFields());
//         hlayout3.addComponent(pPAChart.configureFields());
//         hlayout4.addComponent(pRChart.configureFields());
         layout1.addComponent(hlayout1);
         layout1.addComponent(hlayout2);

         layout2.addComponent(hlayout3);
         layout2.addComponent(hlayout4);

         vlayout.addComponent(layout1);
         vlayout.addComponent(layout2);
         vlayout.setSpacing(true);
         vlayout.setMargin(true);
         vlayout.addComponent(graphIcon);
         panel.setContent(vlayout);
//         panel.setCaption("Forecast_Non-Mandated_Projection_001");

         setMargin(true);
         setSpacing(true);
         addComponent(panel);
    }
     
    public void createTabSheet() {
         liferayIPC.addLiferayIPCEventListener("reciveProjection",
                new LiferayIPCEventListener() {
                    public void eventReceived(LiferayIPCEvent event) {
//                        System.out.println("comming inside ");
//                        Notification.show("Got event " + event.getEventId()
//                                + " with data " + event.getData());
                        String values[]=event.getData().split("-");
                        int proId=Integer.parseInt(values[0]);
                        removeComponent(panel);
                        addComponent(getTab(proId,values[1],values[2]));
                    }
                });
        CommonLogic logic = new CommonLogic();
        List<String> result=logic.getProjectionId();
        int proId=Integer.parseInt(result.get(0));
        String projName=result.get(1);
        String projType=result.get(2);
//        System.out.println("proId" + proId);
       
//        MarginInfo margininfo = new MarginInfo(false, true, false, true);
//        setMargin(margininfo);
//        setSpacing(true);
        addComponent(getTab(proId,projName,projType));
        addStyleName("mainLayout");
        
     }
    
  private Panel getTab(int projId,String projName,String projType){
        panel = new Panel();
        tabSheet = new TabSheet();
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheet.setImmediate(true);
        if("Non Mandated".equals(projType)){
            SPRChart sprChart = new SPRChart(projId,projType);
            PRChart pRChart = new PRChart(projId,projType);
            PPAChart pPAChart = new PPAChart(projId);
            DPRChart dprChart = new DPRChart(projId,projType);
            tabSheet.addTab(sprChart.configureFields(),"Sales Projection Results", null, 0);
            tabSheet.addTab(dprChart.configureFields(),"Discount Projection Results", null, 1);
            tabSheet.addTab(pPAChart.configureFields(),"PPA Projection Results", null, 2);
            tabSheet.addTab(pRChart.configureFields(),"Projection Results", null,3);
        }else if("Mandated".equals(projType)){
            SPRChart sprChart = new SPRChart(projId,projType);
            PRChart pRChart = new PRChart(projId,projType);
            DPRChart dprChart = new DPRChart(projId,projType);
            tabSheet.addTab(sprChart.configureFields(),"Sales Projection Results", null, 0);
            tabSheet.addTab(dprChart.configureFields(),"Discount Projection Results", null, 1);
            tabSheet.addTab(pRChart.configureFields(),"Projection Results", null,2);
        }else if("AccrualRateProjection".equals(projType)){
            AccrualSalesChart accrualSalesChart = new AccrualSalesChart(projId);
            AccrualRateChart accrualRateChart = new AccrualRateChart(projId);
            tabSheet.addTab(accrualSalesChart.configureFields(),"Accrual Sales", null, 0);
            tabSheet.addTab(accrualRateChart.configureFields(),"Accrual Rate", null, 1);
        }else if("Returns".equals(projType)){
            ReturnsSalesProjectionChart returnsSalesChart = new ReturnsSalesProjectionChart(projId);
            tabSheet.addTab(returnsSalesChart.configureFields(),"Returns Sales", null, 0);
        }
       
      
        panel.setContent(tabSheet);
        panel.setCaption(projName);
    return panel;
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
