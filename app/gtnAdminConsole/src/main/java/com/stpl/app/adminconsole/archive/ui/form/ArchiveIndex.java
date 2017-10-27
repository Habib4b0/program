package com.stpl.app.adminconsole.archive.ui.form;

import org.jboss.logging.Logger;
import com.stpl.app.adminconsole.archive.dto.ArchiveDTO;
import com.stpl.app.adminconsole.archive.logic.ArchiveLogic;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ResponsiveUtils;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;
import com.stpl.app.adminconsole.util.CommonUtils;

public class ArchiveIndex extends CustomComponent implements View {

    private static final Logger LOGGER = Logger.getLogger(ArchiveIndex.class);

    public final Object[] archiveTable = new Object[]{"fieldName"};

    public final String[] archiveHeader = new String[]{"Field Name"};

    public final Object[] archiveColumns = new Object[]{"value"};

    public final String[] archiveHeader1 = new String[]{"Value"};

    private final ErrorLabel errorMsg = new ErrorLabel();

    private final ComboBox businessProcess = new ComboBox();

    private final ComboBox moduleddlb = new ComboBox();

    private PopupDateField from = new PopupDateField();

    private PopupDateField toDate = new PopupDateField();

    private ExtPagedFilterTable fieldTable = new ExtPagedFilterTable();

    private ExtPagedFilterTable resultsColumnTable = new ExtPagedFilterTable();

    private final Button archive = new Button("ARCHIVE");

    private final Button delete = new Button("DELETE");

    private final Button reset = new Button("RESET");

    private final BeanItemContainer<ArchiveDTO> resultsBean = new BeanItemContainer<>(ArchiveDTO.class);

    private final BeanItemContainer<ArchiveDTO> valueBean = new BeanItemContainer<>(ArchiveDTO.class);
    Map<String, String> tableMap = new HashMap<>();

    public PopupDateField getFrom() {
        return from;
    }

    public void setFrom(final PopupDateField from) {
        this.from = from;
    }

    public PopupDateField getToDate() {
        return toDate;
    }

    public void setToDate(final PopupDateField toDate) {
        this.toDate = toDate;
    }

    public ExtFilterTable getResults() {
        return fieldTable;
    }

    public void setResults(final ExtPagedFilterTable results) {
        this.fieldTable = results;
    }

    public ExtFilterTable getResultsColumnTable() {
        return resultsColumnTable;
    }

    public void setResultsColumnTable(final ExtPagedFilterTable resultsColumnTable) {
        this.resultsColumnTable = resultsColumnTable;
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public Object[] getArchiveTable() {
        return archiveTable;
    }

    public String[] getArchiveHeader() {
        return archiveHeader;
    }

    public Object[] getArchiveColumns() {
        return archiveColumns;
    }

    public String[] getArchiveHeader1() {
        return archiveHeader1;
    }

    public ErrorLabel getErrorMsg() {
        return errorMsg;
    }

    public ComboBox getBusinessProcess() {
        return businessProcess;
    }

    public Button getArchive() {
        return archive;
    }

    public Button getDelete() {
        return delete;
    }

    public BeanItemContainer<ArchiveDTO> getResultsBean() {
        return resultsBean;
    }

    public BeanItemContainer<ArchiveDTO> getResultsBean1() {
        return valueBean;
    }

    public ArchiveIndex() {
        super();
        init();
    }

    public final void init() {
        LOGGER.debug("init Method Started ");
        setCompositionRoot(addToContent());
        LOGGER.debug("init Method Ended ");
    }

    private Component addToContent() {
        LOGGER.debug("addToContent method started ");
        final VerticalLayout content = new VerticalLayout();
        content.setSpacing(true);
        content.addComponent(errorMsg);
        content.addComponent(addFirstPanel());
        content.addComponent(addResultsTables());
        content.addComponent(addButtons());
        configureFields();
        LOGGER.debug("addToContent method RETURNS content-Component");

        return content;
    }

    private Panel addFirstPanel() {
        LOGGER.debug("addFirstPanel Method Started ");
        final VerticalLayout vLayout = new VerticalLayout();
        final Panel panel = new Panel();
        final GridLayout gridLayout = new GridLayout(4, 4);
        gridLayout.setMargin(true);
        gridLayout.setSpacing(true);
        gridLayout.addComponent(new Label("Module :"));
        gridLayout.addComponent(moduleddlb);

        gridLayout.addComponent(new Label("Business Process :"));
        gridLayout.addComponent(businessProcess);
        gridLayout.addComponent(new Label("From:"));
        gridLayout.addComponent(from);
        gridLayout.addComponent(new Label("To:"));
        gridLayout.addComponent(toDate);

        vLayout.addComponent(gridLayout);
        vLayout.setMargin(true);
        panel.setContent(gridLayout);
        panel.setWidth("100%");
        panel.setCaption("Archive Search Criteria ");
        LOGGER.debug("addFirstPanel Method RETURNS panel ");
        return panel;
    }

    private Panel addResultsTables() {
        final Panel tablesPanel = new Panel();
        final GridLayout tableLayout = new GridLayout(NumericConstants.TWO, 1);
        final VerticalLayout verticalLayout1 = new VerticalLayout();
        verticalLayout1.setMargin(true);
        final VerticalLayout verticalLayout2 = new VerticalLayout();
        verticalLayout2.setMargin(true);
        verticalLayout1.addComponent(addResultsTable());
        verticalLayout1.addComponent(ResponsiveUtils.getResponsiveControls(fieldTable.createControls()));
        verticalLayout2.addComponent(addResultsColumnTable());
        verticalLayout2.addComponent(ResponsiveUtils.getResponsiveControls(resultsColumnTable.createControls()));
        tableLayout.addComponent(verticalLayout1);
        tableLayout.addComponent(verticalLayout2);

        tablesPanel.setWidth("100%");
        tablesPanel.setContent(tableLayout);
        return tablesPanel;
    }

    private Panel addResultsTable() {
        LOGGER.debug("addResultsTables method started ");
        final Panel panel = new Panel("Table Fields");
        fieldTable.markAsDirty();
        fieldTable.setFilterBarVisible(true);
        fieldTable.setFilterDecorator(new ExtDemoFilterDecorator());
        fieldTable.setContainerDataSource(resultsBean);
        fieldTable.setVisibleColumns(archiveTable);
        fieldTable.setColumnHeaders(archiveHeader);
        fieldTable.setPageLength(NumericConstants.FIVE);
        fieldTable.setWidth("450px");
        fieldTable.setImmediate(true);
        fieldTable.setSelectable(true);
        fieldTable.sinkItemPerPageWithPageLength(false);
        fieldTable.addValueChangeListener(new Property.ValueChangeListener() {

            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                Object obj = fieldTable.getValue();
                if (obj != null) {
                    try {
                        ArchiveDTO dto = (ArchiveDTO) obj;
                        String tableName = dto.getTableName();
                        String fieldName = dto.getFieldName();
                        List<ArchiveDTO> list = new ArchiveLogic().getLevelValues(tableName, fieldName);
                        valueBean.removeAllItems();
                        valueBean.addAll(list);
                    } catch (SystemException ex) {
                        LOGGER.error(ex);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            }
        });
        panel.setContent(fieldTable);
        LOGGER.debug("addResultsTable method RETURNS Table-results ");
        return panel;
    }

    private Panel addResultsColumnTable() {
        LOGGER.debug("addResultsColumnTable method started ");
        final Panel panel = new Panel("Field Values");
        resultsColumnTable.markAsDirty();
        resultsColumnTable.setFilterBarVisible(true);
        resultsColumnTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsColumnTable.setContainerDataSource(valueBean);

        resultsColumnTable.setVisibleColumns(archiveColumns);
        resultsColumnTable.setColumnHeaders(archiveHeader1);
        resultsColumnTable.setPageLength(NumericConstants.FIVE);
        resultsColumnTable.sinkItemPerPageWithPageLength(false);
        resultsColumnTable.setWidth("450px");

        resultsColumnTable.setImmediate(true);
        resultsColumnTable.setSelectable(true);
        panel.setContent(resultsColumnTable);
        LOGGER.debug("addResultsColumnTable method RETURNS Table-results ");
        return panel;
    }

    private Component addButtons() {
        LOGGER.debug("addButtons method Started ");
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.addComponent(archive);
        archive.setEnabled(true);
        layout.addComponent(delete);
        delete.setEnabled(true);
        layout.addComponent(reset);
        LOGGER.debug("addButtons method RETURNS layout ");

        return layout;
    }

    public void configureFields() {
        LOGGER.debug("configureFields started");
        moduleddlb.setNullSelectionAllowed(true);
        moduleddlb.addItem(ConstantsUtils.SELECT_ONE);
        moduleddlb.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        moduleddlb.setImmediate(true);
        moduleddlb.setRequired(true);
        loadModule();
        loadTable();
        businessProcess.setNullSelectionAllowed(true);
        businessProcess.addItem(ConstantsUtils.SELECT_ONE);
        businessProcess.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        businessProcess.setImmediate(true);
        businessProcess.setRequired(true);
        moduleddlb.focus();
        moduleddlb.addValueChangeListener(new Property.ValueChangeListener() {

            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                if (event.getProperty().getValue() != null) {
                    String module = event.getProperty().getValue().toString();
                    if (module.equals("Global Files")) {
                        IndexedContainer con = new IndexedContainer();
                        con.addItem("Company Master");
                        con.addItem("Item Master");
                        con.addItem("IFP");
                        con.addItem("CFP");
                        con.addItem("Rebate Plan");
                        con.addItem("Rebate Schedule");
                        businessProcess.setContainerDataSource(con);
                    }
                    if (module.equals("Contract Management")) {
                        IndexedContainer con = new IndexedContainer();
                        con.addItem("Contract Header");
                        con.addItem("Contract Dashboard");
                        businessProcess.setContainerDataSource(con);
                    }
                    if (module.equals("Transaction Mangement")) {
                        IndexedContainer con = new IndexedContainer();
                        con.addItem("Audit Inbound");
                        con.addItem("Sales");
                        con.addItem(CommonUtils.FORECAST);
                        businessProcess.setContainerDataSource(con);
                    }
                    if (module.equals("Admin Console")) {
                        IndexedContainer con = new IndexedContainer();
                        con.addItem("Item group Master");
                        con.addItem("Customer group Master");
                        con.addItem("File Management");
                        con.addItem("Hierarchy Definition");
                        con.addItem("Relationship Builder");
                        businessProcess.setContainerDataSource(con);
                    }
                    if (module.equals(CommonUtils.FORECAST)) {
                        IndexedContainer con = new IndexedContainer();
                        con.addItem("Non-Mandated");
                        con.addItem("Mandated");
                        businessProcess.setContainerDataSource(con);
                    }
                } else {
                    IndexedContainer con = new IndexedContainer();
                    businessProcess.setContainerDataSource(con);
                    businessProcess.addItem(ConstantsUtils.SELECT_ONE);
                }
            }
        });

        businessProcess.addValueChangeListener(new Property.ValueChangeListener() {

            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                if (event.getProperty().getValue() != null) {
                    String tableName = event.getProperty().getValue().toString();
                    String table = tableMap.get(tableName);
                    try {
                        final List<ArchiveDTO> list = new ArchiveLogic().getTableName(table);
                        resultsBean.removeAllItems();
                        resultsBean.addAll(list);
                    } catch (SystemException ex) {
                        LOGGER.error(ex);
                        java.util.logging.Logger.getLogger(ArchiveIndex.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                        java.util.logging.Logger.getLogger(ArchiveIndex.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    IndexedContainer con = new IndexedContainer();
                    businessProcess.setContainerDataSource(con);
                }
            }
        });

        archive.addClickListener(new ClickListener() {
            @Override

            public void buttonClick(final ClickEvent event) {
                Object obj = fieldTable.getValue();
                Object ValueObj = resultsColumnTable.getValue();
                if (businessProcess.getValue() == null || StringUtils.EMPTY.equals(String.valueOf(businessProcess.getValue()))) {
                    AbstractNotificationUtils.getErrorNotification(CommonUtils.ARCHIVE_ERROR, "Business Process should be selected");
                    return;
                }
                if (obj == null) {
                    AbstractNotificationUtils.getErrorNotification(CommonUtils.ARCHIVE_ERROR, "Please select Field Name");
                    return;
                }
                if (ValueObj == null) {
                    Date fromdate = from.getValue();
                    Date to = toDate.getValue();
                    if (fromdate == null || to == null) {
                        AbstractNotificationUtils.getErrorNotification(CommonUtils.ARCHIVE_ERROR, "Please check Date or Value");
                    }
                } else {
                    MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to archive record "
                            + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {

                                @SuppressWarnings("PMD")
                                public void buttonClicked(final ButtonId buttonId) {
                                    if (buttonId.name().equals(ConstantsUtils.YES)) {
                                        Notification.show("Record Archived sucessfully");
                                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.CONFORMATION, "Record Archived sucessfully.", ButtonId.OK);
                                    }
                                }
                            }, ButtonId.YES, ButtonId.NO);

                }
            }
        });
        delete.addClickListener(new ClickListener() {
            @Override

            public void buttonClick(final ClickEvent event) {
                Object obj = fieldTable.getValue();
                Object ValueObj = resultsColumnTable.getValue();
                if (obj == null) {
                    AbstractNotificationUtils.getErrorNotification(CommonUtils.ARCHIVE_ERROR, "Please select Field Name");
                    return;
                }
                if (ValueObj == null) {
                    Date fromdate = from.getValue();
                    Date to = toDate.getValue();
                    if (fromdate == null || to == null) {
                        AbstractNotificationUtils.getErrorNotification(CommonUtils.ARCHIVE_ERROR, "Please check Date or Value");
                    }
                } else {
                    MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to delete the selected record "
                            + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {

                                @SuppressWarnings("PMD")
                                public void buttonClicked(final ButtonId buttonId) {
                                    if (buttonId.name().equals(ConstantsUtils.YES)) {

                                    }
                                }
                            }, ButtonId.YES, ButtonId.NO);
                    Notification.show("Record Deleted sucessfully");
                }
            }
        });

        reset.addClickListener(new ClickListener() {
            @Override

            public void buttonClick(final ClickEvent event) {
                MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to reset the page to default/previous values "
                        + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {
                            /**
                             * Called when reset button is clicked
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(ConstantsUtils.YES)) {
                                    resultsBean.removeAllItems();
                                    valueBean.removeAllItems();
                                    toDate.setValue(null);
                                    from.setValue(null);
                                    moduleddlb.setValue(null);
                                    businessProcess.setValue(null);
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
            }
        });
    }

    private void loadModule() {
        moduleddlb.addItem("Global Files");
        moduleddlb.addItem("Contract Management");
        moduleddlb.addItem("Transaction Mangement");
        moduleddlb.addItem("Admin Console");
        moduleddlb.addItem(CommonUtils.FORECAST);
    }

    private void loadTable() {
        tableMap.put("Company Master", "COMPANY_MASTER");
        tableMap.put("Item Master", "ITEM_MASTER");
        tableMap.put("IFP", "IFP_MODEL");
        tableMap.put("CFP", "CFP_MODEL");
        tableMap.put("Rebate Schedule", "RS_MODEL");
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    @Override
    public void enter(final ViewChangeEvent event) {
        return;
    }
}
