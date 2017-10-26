/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.serverlogging.form;

import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.serverlogging.dto.LoggingDto;
import com.stpl.app.adminconsole.serverlogging.logic.LoggingTableLogic;
import com.stpl.app.adminconsole.serverlogging.logic.SearchLogic;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import static com.stpl.app.adminconsole.util.ResponsiveUtils.getResponsiveControls;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import static com.vaadin.server.Sizeable.UNITS_PERCENTAGE;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.File;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author Karthik.Raja
 */
public class LoggingSearchIndex extends CustomComponent implements View {

    private static final Logger LOGGER = Logger.getLogger(LoggingSearchIndex.class);
    final SearchLogic searchLogic = SearchLogic.getInstance();
    @UiField("saveBtn")
    private Button saveBtn;
    @UiField("resetBtn")
    private Button resetBtn;
    @UiField("viewBtn")
    private Button viewBtn;
    @UiField("deleteBtn")
    private Button deleteBtn;
    private Button downloadBtn=new Button("DOWNLOAD");
    @UiField("buttonLayout2")
    private HorizontalLayout buttonLayout2;
    public LoggingDto loggingDTO = new LoggingDto();
    @UiField("logDestination")
    private TextField logDestination;
    @UiField("messagesSelected")
    private TextField messagesSelected;
    /**
     * The results bean.
     */
    public BeanItemContainer<LoggingDto> resultsBean = new BeanItemContainer<LoggingDto>(LoggingDto.class);
    /**
     * The Constant LOGGING_TABLE_COLUMNS.
     */
    public static final Object[] LOGGING_TABLE_COLUMNS = new Object[]{
        "logDestination", "active", "messagesSelected"};
    /**
     * The Constant LOGGING_TABLE_HEADER.
     */
    public static final String[] LOGGING_TABLE_HEADER = new String[]{"Log Destination", "Active", "Messages Selected"};
    @UiField("tableLayout")
    VerticalLayout tableLayout;
    @UiField("buttonLayout")
    HorizontalLayout buttonLayout;
    private HorizontalLayout controlLayout = new HorizontalLayout();
    LoggingTableLogic tableLogic = new LoggingTableLogic();
    ExtPagedTable results = new ExtPagedTable(tableLogic);
    CommonUtil commonUtil = new CommonUtil();
    public String logDestinationValue;
    private boolean isActive = false;
    /**
     *
     * /**
     * The Constant NULLOBJECT.
     */
    private static final BeanItem<?> NULLOBJECT = null;

    /**
     * Instantiates a new LoggingSearchIndex
     */
    public LoggingSearchIndex()  {

        super();
        try {
            init();
            configureFields();
            loadGrid();
            addButtonListeners();
        } catch (Exception e) {

            LOGGER.error(e);
        }
    }

    /**
     * Inits the.
     */
    private void init() {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/LoggingSearchIndex.xml"), this));
        configureTable();
        downloadBtn = new Button("DOWNLOAD");
        buttonLayout2.addComponent(downloadBtn);
    }

    /**
     * (non-Javadoc).
     *
     * @param event the event
     * @see
     * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
    }

    /**
     * Configure fields.
     */
    private void configureFields()  {
        logDestination.setValue(StringUtils.EMPTY);
        messagesSelected.setValue(StringUtils.EMPTY);
    }

    /**
     * TO load the grid
     */
    private void loadGrid() {
        try {
            tableLogic.configureSearchData();
            results.setImmediate(true);
            results.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
            results.addStyleName("TableCheckBox");
            results.setSelectable(true);
            results.markAsDirtyRecursive();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void configureTable() {

        tableLayout.addComponent(results);
        getResponsiveControls(tableLogic.createControls(), controlLayout);
        tableLayout.addComponent(controlLayout);

        tableLogic.setContainerDataSource(resultsBean);
        tableLogic.setPageLength(NumericConstants.FIFTEEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        setTableDefaultConfig();
        results.setSelectable(true);
        results.markAsDirty();
        results.setValidationVisible(false);
        results.addStyleName("filterbar");
        results.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             *
             *
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                LOGGER.debug("In results valueChange");
                resultsItemClick(event.getProperty().getValue());
            }
        });
        
    }

    public void setTableDefaultConfig() {
        try {
            results.setVisibleColumns(LOGGING_TABLE_COLUMNS);
            results.setColumnHeaders(LOGGING_TABLE_HEADER);
            results.markAsDirtyRecursive();
            results.setImmediate(true);
            results.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
            results.setHeight("250px");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void addButtonListeners() {
        saveBtn.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                if (logDestination.getValue() != null && !logDestination.getValue().isEmpty()) {
                    searchLogic.addLogFile(logDestination.getValue(),
                            messagesSelected.getValue() == null || messagesSelected.getValue().isEmpty() ? "---" : messagesSelected.getValue(), false);
                    tableLogic.configureSearchData();
                    AbstractNotificationUtils.getInfoNotification("Success", "Log File Added Scucessfully");
                } else {
                    AbstractNotificationUtils.getErrorNotification("Mandatory Field", "Please Enter Log Destination");
                }
            }
        });
        viewBtn.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                if (logDestinationValue != null) {
                    if (isActive) {
                        VaadinSession.getCurrent().setAttribute("LOG_DESTINATION", logDestinationValue);
                        UI.getCurrent().getNavigator().navigateTo(ViewLog.NAME);
                    } else {
                        AbstractNotificationUtils.getErrorNotification("Error", "Selected Record is inactive");
                    }
                } else {
                    AbstractNotificationUtils.getErrorNotification("Error", "Please Select any record");
                }
            }
        });
        deleteBtn.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                if (logDestinationValue != null) {
                    searchLogic.addLogFile(logDestinationValue, StringUtils.EMPTY, true);
                    tableLogic.configureSearchData();
                    AbstractNotificationUtils.getInfoNotification("Success", "Log File Removed Scucessfully");
                } else {
                    AbstractNotificationUtils.getErrorNotification("Error", "Please Select any record");
                }
            }
        });
        resetBtn.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                messagesSelected.setValue(ConstantsUtils.EMPTY);
                logDestination.setValue(ConstantsUtils.EMPTY);
            }
        });
    }

    protected void resultsItemClick(final Object obj) {
        if (obj == null) {
            logDestinationValue = null;
            isActive = false;
        } else {
            BeanItem<?> targetItem;
            if (obj instanceof BeanItem<?>) {
                targetItem = (BeanItem<?>) obj;
            } else if (obj instanceof LoggingDto) {
                targetItem = new BeanItem<LoggingDto>((LoggingDto) obj);
            } else {
                targetItem = NULLOBJECT;
            }
            isActive = ((LoggingDto) targetItem.getBean()).getActive().equalsIgnoreCase("Yes");
            logDestinationValue = ((LoggingDto) targetItem.getBean()).getLogDestination();
            if(isActive){
                buttonLayout2.removeComponent(downloadBtn);
                downloadBtn = new Button("DOWNLOAD");
                Resource res = new FileResource(new File(logDestinationValue));
                FileDownloader fd = new FileDownloader(res);
                fd.extend(downloadBtn);
                buttonLayout2.addComponent(downloadBtn);
            }
        }
    }
}
