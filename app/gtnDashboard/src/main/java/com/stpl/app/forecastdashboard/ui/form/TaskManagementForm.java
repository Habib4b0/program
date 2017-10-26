/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastdashboard.ui.form;

import com.stpl.app.forecastdashboard.dto.TaskManagementDTO;
import com.stpl.app.forecastdashboard.logic.TaskManagementLogic;
import com.stpl.app.forecastdashboard.utils.CommonUtils;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.vaadin.jouni.dom.Dom;

/**
 *
 * @author nandhakumar
 */
public class TaskManagementForm extends CustomComponent implements View {

    /**
     * The space.
     */
    private final Label space = new Label("&nbsp;", ContentMode.HTML);

    Table resultsTable = new Table();

//    Table notificationTable = new Table();

    final HorizontalLayout out = new HorizontalLayout();

    Button test = new Button("Test");

    final Panel panel = new Panel();

//    private final Button notification = new Button();

    private final Button recentlySaved = new Button();
    
    private final OptionGroup all = new OptionGroup();
    
    private final Button approvedTask = new Button();

    private final Button rejectedTask = new Button();

    private final Button pendingTask = new Button();

    public static final Object[] RESULTS_COLUMNS = new Object[]{
        "forecastName","forecastType" ,"customerHierarchy",
        //        "customerLevel", 
        "productHierarchy",
        //        "productLevel", 
        "createdDate", "modifiedDate", "createdBy"};
    /**
     * The Constant CUSTOMER_GROUP_RESULTS_HEADER.
     */
    public static final String[] RESULTS_HEADER = new String[]{
        "Forecast Name","Forecast Type", "Customer Hierarchy",
        //        "Customer Level", 
        "Product Hierarchy",
        //        "Product Level", 
        "Creation Date", "Modified Date", "Created By"};

    private final BeanItemContainer<TaskManagementDTO> resultsBean = new BeanItemContainer<TaskManagementDTO>(TaskManagementDTO.class);

//    private final BeanItemContainer<String> notificationBean = new BeanItemContainer<String>(String.class);
    TaskManagementLogic logic = new TaskManagementLogic();
    String type = StringUtils.EMPTY;
    String existingValue = StringUtils.EMPTY;
    final VerticalLayout content = new VerticalLayout();
     String value1 = "\'Non Mandated\',\'Mandated\',\'CFF\'";
    final HorizontalLayout flavours = new HorizontalLayout();
    
    final VerticalLayout flavours1 = new VerticalLayout();

    Button showHide = new Button();

    Label notificationLabel = new Label("No pending notifications", ContentMode.HTML);

    Button activeButton = new Button();

    boolean show = true;

    public TaskManagementForm() throws SystemException, Exception {
        super();
        init();
        configureFields();

    }

    /**
     * Inits the.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public void init() throws SystemException, Exception {

        space.setHeight("20");
        setCompositionRoot(addToContent());

    }

    /**
     * Adds the to content.
     *
     * @return the component
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private Component addToContent() throws SystemException, Exception {

        space.setHeight("20");
//        content.setSpacing(true);
//        MarginInfo margininfo = new MarginInfo(false, true, false, true);
//        content.setMargin(margininfo);
        content.addComponent(addTaskInfo());
//        content.addComponent(addTaskMgtTable());
        content.addStyleName("mainLayout");
        //  getBinder();

        //  LOGGER.info("addToContent Method Returns Layout");
        return content;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    /**
     * Adds the relationship info.
     *
     * @return the panel
     */
    private Panel addTaskInfo() {

        final Panel panel = new Panel();
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);

        layout.addComponent(approvedTask);
        layout.addComponent(rejectedTask);
        layout.addComponent(pendingTask);
        layout.addComponent(recentlySaved);
//        layout.addComponent(notification);
        layout.addComponent(showHide);
        panel.setContent(layout);
        panel.setSizeFull();
        panel.setStyleName("center");
//        panel.setCaption("Task Management");
        return panel;
    }

    /**
     * Adds the builder window.
     *
     * @return the component
     */
    private Component addTaskMgtTable() {

        panel.setWidth("100%");
        out.setMargin(true);
//        out.setSpacing(true);
        out.setWidth("100%");
        flavours.setWidth("100%");
        resultsBean.removeAllItems();
        type="approvedTask";
        List<TaskManagementDTO> results = new ArrayList<TaskManagementDTO>();
        try {
//            results = logic.getRecentApprovedProjection(value1);type,valueType,"P"
             results = logic.getProjectionsStatus( "Z",value1, "Approved");
        } catch (SystemException ex) {
            Logger.getLogger(TaskManagementForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultsBean.addAll(results);
        resultsTable.setContainerDataSource(resultsBean);
        resultsTable.setVisibleColumns(RESULTS_COLUMNS);
        resultsTable.setColumnHeaders(RESULTS_HEADER);
        out.removeAllComponents();
        flavours.setMargin(true);
        all.setMultiSelect(true);
        all.setImmediate(true);
        all.addStyleName("horizontal");
        all.addItems("All","Commercial","Government","CFF");
        flavours.addComponent(all);
        out.addComponent(resultsTable);
        flavours1.setSpacing(true);
        flavours1.addComponent(flavours);
        flavours1.addComponent(out);
//        panel.setCaption("Recently Saved Tasks");
        panel.setContent(flavours1);
        panel.setSizeFull();
        return panel;
    }

    /**
     * Configure fields.
     *
     * @throws Exception
     * @throws SystemException
     */
    private void configureFields() throws SystemException, Exception {

//        notification.setIcon(new ThemeResource("../../icons/notification-icon.png"));
////        notification.setIcon(new ThemeResource("../../icons/bell.png"));
//        notification.setCaption("Notification");

        recentlySaved.setIcon(new ThemeResource("../../icons/saved_task-icon.png"));
//        recentlySaved.setIcon(new ThemeResource("../../icons/recent.png"));
        recentlySaved.setCaption("Recently Saved Tasks");

        approvedTask.setIcon(new ThemeResource("../../icons/approved_task-icon.png"));
//        approvedTask.setIcon(new ThemeResource("../../icons/approve.png"));
        approvedTask.setCaption("Approved Tasks");

        rejectedTask.setIcon(new ThemeResource("../../icons/rejected_task-icon.png"));
//        rejectedTask.setIcon(new ThemeResource("../../icons/reject.png"));
        rejectedTask.setCaption("Rejected Tasks");

        pendingTask.setIcon(new ThemeResource("../../icons/pending_task-icon.png"));
//        pendingTask.setIcon(new ThemeResource("../../icons/pending.png"));
        pendingTask.setCaption("Pending Tasks");

//        notification.setStyleName("link");
        recentlySaved.setStyleName("link");
        approvedTask.setStyleName("link");
        rejectedTask.setStyleName("link");
        pendingTask.setStyleName("link");
        showHide.setStyleName("link");

//        notification.addStyleName("remove-Text-Decoration");
        recentlySaved.addStyleName("remove-Text-Decoration");
        approvedTask.addStyleName("remove-Text-Decoration");
        rejectedTask.addStyleName("remove-Text-Decoration");
        pendingTask.addStyleName("remove-Text-Decoration");

        resultsTable.markAsDirty();
//        resultsTable.setFilterBarVisible(true);
//        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setPageLength(4);
        resultsTable.setContainerDataSource(resultsBean);
        resultsTable.setVisibleColumns(RESULTS_COLUMNS);
        resultsTable.setColumnHeaders(RESULTS_HEADER);
        resultsTable.setHeight("210px");
        resultsTable.setWidth("100%");
        resultsTable.setImmediate(true);
        resultsTable.setSelectable(true);
        out.addStyleName("animated animation-fadeInDown");
//        notificationTable.setWidth("100%");
//        notificationTable.setSelectable(true);
//        notificationTable.addContainerProperty("S.No", Integer.class, null);
//        notificationTable.addContainerProperty("Notifications", String.class, null);
//        notificationTable.setPageLength(4);
        
        removeProperty();
        show = true;
        showHide.setIcon(new ThemeResource("../../icons/down-arrow.png"));

        addTaskMgtTable();
//        notification.addClickListener(new Button.ClickListener() {
//            /**
//             * Click listener for Button
//             */
//            @SuppressWarnings("PMD")
//            @Override
//            public void buttonClick(final Button.ClickEvent event) {
//                removeProperty();
//                out.removeAllComponents();
//
//                String notifications = "";
//                String prefix = " has been ";
//                
//                List<TaskManagementDTO> results = new ArrayList<TaskManagementDTO>();
//                try {
//                    results = logic.getTopSubmittedProjections();
//                } catch (SystemException ex) {
//                    Logger.getLogger(TaskManagementForm.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//                if (results != null && !results.isEmpty()) {
//                    int i = 1;
//                    String suffix = "";
//                    for (TaskManagementDTO dto : results) {
//                        if(dto.getStatus().equals("")){
//                            prefix = " is ";
//                            suffix = "";
//                        }else{
//                            suffix = " by " + dto.getModifiedBy();
//                        }
//                        notifications = dto.getForecastName() + prefix + dto.getStatus() + suffix;
//                        notificationTable.addItem(new Object[]{i, notifications}, i++);
//                    }
//                }
//
//                out.addComponent(notificationTable);
//                panel.setContent(out);
//                setActiveButton(event);
//                showHide(true);
//
//            }
//        });

        recentlySaved.addClickListener(new Button.ClickListener() {
            /**
             * Click listener for Button
             */
            @SuppressWarnings("PMD")
            @Override
            public void buttonClick(final Button.ClickEvent event) {
                removeProperty();
                 type="recentlySaved";
                resultsBean.removeAllItems();
                List<TaskManagementDTO> results = new ArrayList<TaskManagementDTO>();
                try {
//                    results = logic.getForecastCFFRecentlySavedProjection(value1);
                      results = logic.getProjectionsStatus("S",value1,type);  
                } catch (SystemException ex) {
                    Logger.getLogger(TaskManagementForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                resultsBean.addAll(results);
                resultsTable.setContainerDataSource(resultsBean);
                resultsTable.setVisibleColumns(RESULTS_COLUMNS);
                resultsTable.setColumnHeaders(RESULTS_HEADER);
                out.removeAllComponents();
                out.addComponent(resultsTable);
                panel.setContent(flavours1);
                setActiveButton(event);
                showHide(true);
            }
        });

        pendingTask.addClickListener(new Button.ClickListener() {
            /**
             * Click listener for Button
             */
            @SuppressWarnings("PMD")
            @Override
            public void buttonClick(final Button.ClickEvent event) {
                removeProperty();
                type="pendingTask";
                resultsBean.removeAllItems();
                List<TaskManagementDTO> results = new ArrayList<TaskManagementDTO>();
                try {
                    if(existingValue.isEmpty()){
                 
//                    results = logic.getRecentPendingProjection(value1);
                    results = logic.getProjectionsStatus("P" ,value1,"Pending" );
                    }else{
//                      results = logic.getRecentPendingProjection(existingValue);
                      results = logic.getProjectionsStatus("P" ,existingValue,"Pending" );
                    }
                   
                } catch (SystemException ex) {
                    Logger.getLogger(TaskManagementForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                resultsBean.addAll(results);
                resultsTable.setContainerDataSource(resultsBean);
                resultsTable.setVisibleColumns(RESULTS_COLUMNS);
                resultsTable.setColumnHeaders(RESULTS_HEADER);
                out.removeAllComponents();
                out.addComponent(resultsTable);
                panel.setContent(flavours1);
                setActiveButton(event);
                showHide(true);
            }
        });

        approvedTask.addClickListener(new Button.ClickListener() {
            /**
             * Click listener for Button
             */
            @SuppressWarnings("PMD")
            @Override
            public void buttonClick(final Button.ClickEvent event) {
                removeProperty();
                type="approvedTask";
                resultsBean.removeAllItems();
                List<TaskManagementDTO> results = new ArrayList<TaskManagementDTO>();
                try {
                    if(existingValue.isEmpty()){
                   
//                    results = logic.getRecentApprovedProjection(value1);
                     results =logic.getProjectionsStatus("Z" ,value1,"Approved");
                    }else{
//                       results = logic.getRecentApprovedProjection(existingValue);
                         results =logic.getProjectionsStatus("Z" ,existingValue,"Approved");
                    }
                } catch (SystemException ex) {
                    Logger.getLogger(TaskManagementForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                resultsBean.addAll(results);
                resultsTable.setContainerDataSource(resultsBean);
                resultsTable.setVisibleColumns(RESULTS_COLUMNS);
                resultsTable.setColumnHeaders(RESULTS_HEADER);
                out.removeAllComponents();
                out.addComponent(resultsTable);
                panel.setContent(flavours1);
                setActiveButton(event);
                showHide(true);
            }
        });

        rejectedTask.addClickListener(new Button.ClickListener() {
            /**
             * Click listener for Button
             */
            @SuppressWarnings("PMD")
            @Override
            public void buttonClick(final Button.ClickEvent event) {

                removeProperty();
                type="rejectedTask";
                resultsBean.removeAllItems();
                List<TaskManagementDTO> results = new ArrayList<TaskManagementDTO>();
                try {
                     if(existingValue.isEmpty()){
                  
//                    results = logic.getRejectedProjections(value1);
                    results =logic.getProjectionsStatus("R" ,value1,"Rejected");
                     }else{
//                       results = logic.getRejectedProjections(existingValue);    
                         results =logic.getProjectionsStatus("R" ,existingValue,"Rejected");
                     }
                } catch (SystemException ex) {
                    Logger.getLogger(TaskManagementForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                resultsBean.addAll(results);
                resultsTable.setContainerDataSource(resultsBean);
                resultsTable.setVisibleColumns(RESULTS_COLUMNS);
                resultsTable.setColumnHeaders(RESULTS_HEADER);
                out.removeAllComponents();
                out.addComponent(resultsTable);
                panel.setContent(flavours1);
                setActiveButton(event);
                showHide(true);
            }
        });
        resultsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                TaskManagementDTO dto = (TaskManagementDTO) event.getItemId();
                String value = dto.getForecastType();
                
                if("Commercial".equals(value)){
                    value = "Non Mandated";
                }else if("Government".equals(value)){
                    value = "Mandated";
                }
                System.out.println("IPC EVENT ============"+value);
                CommonUtils.getLiferayIPC().sendEvent("reciveProjection", dto.getProjectionId() + "~" + dto.getForecastName()+"~"+value);
            }
        });
        showHide.addClickListener(new Button.ClickListener() {
            /**
             * Click listener for Button
             */
            @SuppressWarnings("PMD")
            @Override
            public void buttonClick(final Button.ClickEvent event) {
                removeProperty();
                showHide(show);
            }
        });
        all.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                List<TaskManagementDTO> results = new ArrayList<TaskManagementDTO>();
                List<String> forecastType= new ArrayList<>();
                String forecast=StringUtils.EMPTY;
                String value = String.valueOf(all.getValue());
                Object[] typeArray = ((Set)all.getValue()).toArray();
                String valueType=StringUtils.EMPTY;
                
                for(int i=0;i<typeArray.length;i++){
                     if(String.valueOf(typeArray[i]).equals("Government")){
                       forecast="'Mandated'";
                     }
                     else if(String.valueOf(typeArray[i]).equals("Commercial")){
                         forecast="'Non Mandated'"; 
                     }else if(String.valueOf(typeArray[i]).equals("CFF")){
                         forecast="'CFF'"; 
                     }
                     if(!String.valueOf(typeArray[i]).equals("All")){
                    if(i==0){
                        valueType=forecast;
                     }else{
                        valueType+=","+forecast;
                    }
                }else{
                    valueType=value1;    
                        }
                }
                  existingValue=valueType;
                if(value.contains(",")){
                
                all.unselect("All");
                all.setItemEnabled("All", false);
                
                }else if(value.contains("All")){
                    all.setItemEnabled("All", true);
                    all.setItemEnabled("CFF", false);
                    all.setItemEnabled("Government", false);
                    all.setItemEnabled("Commercial", false);
                    if(value.contains("All") && type.equals("recentlySaved")){
                    try {
//                    results = logic.getForecastCFFRecentlySavedProjection(value1);
                     results = logic.getProjectionsStatus("S",value1,type);   
                } catch (SystemException ex) {
                    Logger.getLogger(TaskManagementForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }else{
                        results = recentlySubmitted(results,value,forecastType,valueType);
                    }
                  
                }
                    else if(value.contains("CFF") || value.contains("Government") || value.contains("Commercial")){
                         all.unselect("All");
                         all.setItemEnabled("All", false);
                    }
                else {
                    all.setItemEnabled("All", true);
                    all.setItemEnabled("CFF", true);
                    all.setItemEnabled("Government", true);
                    all.setItemEnabled("Commercial", true);  
                }
               
              results = recentlySubmitted(results,value,forecastType,valueType);
               resultsBean.removeAllItems();
                resultsBean.addAll(results);
                resultsTable.setContainerDataSource(resultsBean);
                resultsTable.setVisibleColumns(RESULTS_COLUMNS);
                resultsTable.setColumnHeaders(RESULTS_HEADER);
                out.removeAllComponents();
                out.addComponent(resultsTable);
                panel.setContent(flavours1);
               

            }
        });
    }

    private void showHide(boolean showTemp) {
        if (showTemp) {
            System.out.println(" inside show ");
            content.removeComponent(panel);
            content.addComponent(panel);
            show = false;
            showHide.setIcon(new ThemeResource("../../icons/up-arrow.png"));
            addPropertyValues(activeButton);
        } else {
            content.removeComponent(panel);
            show = true;
            showHide.setIcon(new ThemeResource("../../icons/down-arrow.png"));
        }
    }

    private void removeProperty() {

        removePropertyForButton(recentlySaved);
        removePropertyForButton(approvedTask);
        removePropertyForButton(rejectedTask);
        removePropertyForButton(pendingTask);
//        removePropertyForButton(notification);
    }

    private void setActiveButton(Button.ClickEvent event) {
        activeButton = event.getButton();
    }

    private void removePropertyForButton(Button button) {
        new Dom(button).getStyle().setProperty("box-shadow", "auto");
        new Dom(button).getStyle().setProperty("color", "#197DE2");
        new Dom(button).getStyle().setProperty("-webkit-box-shadow", "none");
        new Dom(button).getStyle().setProperty("background", "none");
    }

    private void addPropertyValues(Button button) {
        new Dom(button).getStyle().setProperty("box-shadow", "none");
        new Dom(button).getStyle().setProperty("color", "rgb(255, 255, 255)");
        new Dom(button).getStyle().setProperty("-webkit-box-shadow", "none");
        new Dom(button).getStyle().setProperty("background", "linear-gradient(to bottom,  #87cae6 0%,#008ec9 100%)");
    }
    
    public List<TaskManagementDTO> recentlySubmitted(List<TaskManagementDTO> results,String value,List<String> forecastType,String valueType){
        if(valueType.isEmpty()){
            valueType=value1;
        }
            if(type.equals("pendingTask")){
                try { 
//                    results = logic.getRecentPendingProjection(valueType);
                    results = logic.getProjectionsStatus("P",valueType,type);
                    
                     } catch (SystemException ex) {
                    Logger.getLogger(TaskManagementForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                    else if(type.equals("rejectedTask")){
                        try { 
//                    results = logic.getRejectedProjections(valueType);
                     results = logic.getProjectionsStatus("R",valueType,type);       
                    
                     } catch (SystemException ex) {
                    Logger.getLogger(TaskManagementForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
                    }else if(type.equals("approvedTask")){
                      try { 
//                    results = logic.getRecentApprovedProjection(valueType);
                      results = logic.getProjectionsStatus("Z",valueType,type);
                     } catch (SystemException ex) {
                    Logger.getLogger(TaskManagementForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }else if(type.equals("recentlySaved")){
                    try { 
//                    results = logic.getForecastCFFRecentlySavedProjection(valueType);
                       results = logic.getProjectionsStatus("S",valueType,type);
                     } catch (SystemException ex) {
                    Logger.getLogger(TaskManagementForm.class.getName()).log(Level.SEVERE, null, ex);
                }    
                        }
                   
        
          return results;
    }
    
}
