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
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    Table notificationTable = new Table();

    final HorizontalLayout out = new HorizontalLayout();

    Button test = new Button("Test");

    final Panel panel = new Panel();

    private final Button notification = new Button();

    private final Button recentlySaved = new Button();

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

    final VerticalLayout content = new VerticalLayout();

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

//        layout.addComponent(recentlySaved);
        layout.addComponent(approvedTask);
        layout.addComponent(recentlySaved);
        layout.addComponent(rejectedTask);
        layout.addComponent(pendingTask);
        layout.addComponent(notification);
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
        out.setSpacing(true);
        out.setWidth("100%");
        resultsBean.removeAllItems();
        List<TaskManagementDTO> results = new ArrayList<TaskManagementDTO>();
        try {
            results = logic.getRecentlySavedProjection();
        } catch (SystemException ex) {
            Logger.getLogger(TaskManagementForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultsBean.addAll(results);
        resultsTable.setContainerDataSource(resultsBean);
        resultsTable.setVisibleColumns(RESULTS_COLUMNS);
        resultsTable.setColumnHeaders(RESULTS_HEADER);
        out.removeAllComponents();
        out.addComponent(resultsTable);
//        panel.setCaption("Recently Saved Tasks");
        panel.setContent(out);
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

        notification.setIcon(new ThemeResource("../../icons/notification-icon.png"));
//        notification.setIcon(new ThemeResource("../../icons/bell.png"));
        notification.setCaption("Notification");

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

        notification.setStyleName("link");
        recentlySaved.setStyleName("link");
        approvedTask.setStyleName("link");
        rejectedTask.setStyleName("link");
        pendingTask.setStyleName("link");
        showHide.setStyleName("link");

        notification.addStyleName("remove-Text-Decoration");
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
        notificationTable.setWidth("100%");
        notificationTable.setSelectable(true);
        notificationTable.addContainerProperty("S.No", Integer.class, null);
        notificationTable.addContainerProperty("Notifications", String.class, null);
        notificationTable.setPageLength(4);
        
        removeProperty();
        show = true;
        showHide.setIcon(new ThemeResource("../../icons/down-arrow.png"));

        addTaskMgtTable();
        notification.addClickListener(new Button.ClickListener() {
            /**
             * Click listener for Button
             */
            @SuppressWarnings("PMD")
            @Override
            public void buttonClick(final Button.ClickEvent event) {
                removeProperty();
                out.removeAllComponents();

                String notifications = "";
                String prefix = " has been ";
                
                List<TaskManagementDTO> results = new ArrayList<TaskManagementDTO>();
                try {
                    results = logic.getTopSubmittedProjections();
                } catch (SystemException ex) {
                    Logger.getLogger(TaskManagementForm.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (results != null && !results.isEmpty()) {
                    int i = 1;
                    String suffix = "";
                    for (TaskManagementDTO dto : results) {
                        if(dto.getStatus().equals("")){
                            prefix = " is ";
                            suffix = "";
                        }else{
                            suffix = " by " + dto.getModifiedBy();
                        }
                        notifications = dto.getForecastName() + prefix + dto.getStatus() + suffix;
                        notificationTable.addItem(new Object[]{i, notifications}, i++);
                    }
                }

                out.addComponent(notificationTable);
                panel.setContent(out);
                setActiveButton(event);
                showHide(true);

            }
        });

        recentlySaved.addClickListener(new Button.ClickListener() {
            /**
             * Click listener for Button
             */
            @SuppressWarnings("PMD")
            @Override
            public void buttonClick(final Button.ClickEvent event) {
                removeProperty();
                resultsBean.removeAllItems();
                List<TaskManagementDTO> results = new ArrayList<TaskManagementDTO>();
                try {
                    results = logic.getRecentlySavedProjection();
                } catch (SystemException ex) {
                    Logger.getLogger(TaskManagementForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                resultsBean.addAll(results);
                resultsTable.setContainerDataSource(resultsBean);
                resultsTable.setVisibleColumns(RESULTS_COLUMNS);
                resultsTable.setColumnHeaders(RESULTS_HEADER);
                out.removeAllComponents();
                out.addComponent(resultsTable);
                panel.setContent(out);
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
                resultsBean.removeAllItems();
                List<TaskManagementDTO> results = new ArrayList<TaskManagementDTO>();
                try {
                    results = logic.getRecentPendingProjection();
                } catch (SystemException ex) {
                    Logger.getLogger(TaskManagementForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                resultsBean.addAll(results);
                resultsTable.setContainerDataSource(resultsBean);
                resultsTable.setVisibleColumns(RESULTS_COLUMNS);
                resultsTable.setColumnHeaders(RESULTS_HEADER);
                out.removeAllComponents();
                out.addComponent(resultsTable);
                panel.setContent(out);
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
                resultsBean.removeAllItems();
                List<TaskManagementDTO> results = new ArrayList<TaskManagementDTO>();
                try {
                    results = logic.getRecentApprovedProjection();
                } catch (SystemException ex) {
                    Logger.getLogger(TaskManagementForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                resultsBean.addAll(results);
                resultsTable.setContainerDataSource(resultsBean);
                resultsTable.setVisibleColumns(RESULTS_COLUMNS);
                resultsTable.setColumnHeaders(RESULTS_HEADER);
                out.removeAllComponents();
                out.addComponent(resultsTable);
                panel.setContent(out);
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
                resultsBean.removeAllItems();
                List<TaskManagementDTO> results = new ArrayList<TaskManagementDTO>();
                try {
                    results = logic.getRejectedProjections();
                } catch (SystemException ex) {
                    Logger.getLogger(TaskManagementForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                resultsBean.addAll(results);
                resultsTable.setContainerDataSource(resultsBean);
                resultsTable.setVisibleColumns(RESULTS_COLUMNS);
                resultsTable.setColumnHeaders(RESULTS_HEADER);
                out.removeAllComponents();
                out.addComponent(resultsTable);
                panel.setContent(out);
                setActiveButton(event);
                showHide(true);
            }
        });
        resultsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                TaskManagementDTO dto = (TaskManagementDTO) event.getItemId();
                CommonUtils.getLiferayIPC().sendEvent("reciveProjection", dto.getProjectionId() + "-" + dto.getForecastName()+"-"+dto.getForecastType());
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
        removePropertyForButton(notification);
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
}
