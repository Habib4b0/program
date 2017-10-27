package com.stpl.app.adminconsole.hierarchybuilder.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.adminconsole.hierarchybuilder.dto.TableFieldLookUpDTO;
import com.stpl.app.adminconsole.hierarchybuilder.logic.HierarchyBuilderLogic;
import com.stpl.app.adminconsole.hierarchybuilder.logic.tableLogic.TableFieldLogic;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.app.adminconsole.util.ResponsiveUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * The Class TableFieldNameLookUp.
 */
public final class TableFieldNameLookUp extends Window {

    private final ErrorLabel errorMsg = new ErrorLabel();

    @UiField("tableName")
    private TextField tableName;

    @UiField(FIELD_NAME)
    private TextField fieldName;

    private TextField tempTable;

    private TextField tempField;

    private ExtFilterTable excelTable;
    private BeanItemContainer<TableFieldLookUpDTO> excelTableBean;
    private ExtFilterTable excelFieldTable;
    private BeanItemContainer<TableFieldLookUpDTO> excelFieldBean;

    private CustomFieldGroup binder;

    private List<TableFieldLookUpDTO> lookUpList = new ArrayList<>();

    private List<TableFieldLookUpDTO> fieldList = new ArrayList<>();

    private static final Logger LOGGER = Logger.getLogger(TableFieldNameLookUp.class);

    private final BeanItemContainer<TableFieldLookUpDTO> fieldBean = new BeanItemContainer<>(TableFieldLookUpDTO.class);

    @UiField("excelExportTables")
    private Button excelExportTables;

    @UiField("excelExportFields")
    private Button excelExportFields;

    @UiField("tableResultsLayout")
    private VerticalLayout tableResultsLayout;

    @UiField("tableResultsControlLayout")
    private HorizontalLayout tableResultsControlLayout;

    @UiField("fieldResultsLayout")
    private VerticalLayout fieldResultsLayout;

    @UiField("fieldResultsControlLayout")
    private HorizontalLayout fieldResultsControlLayout;

    TableFieldLogic tableLogic = new TableFieldLogic();
    protected ExtPagedTable resultTable = new ExtPagedTable(tableLogic);
    TableFieldLogic tableLogicForFieldName = new TableFieldLogic();
    protected ExtPagedTable fieldTable = new ExtPagedTable(tableLogicForFieldName);

    protected final BeanItemContainer<TableFieldLookUpDTO> resultsContainer = new BeanItemContainer<>(TableFieldLookUpDTO.class);

    private final ThemeResource excelImage = new ThemeResource("../../icons/excel.png");

    @UiField("searchBtn")
    private Button searchBtn;

    @UiField("updateBtn")
    private Button updateBtn;

    @UiField("closeBtn")
    private Button closeBtn;

    TableFieldLookUpDTO tableLookUpDTO = new TableFieldLookUpDTO();

    TableFieldLookUpDTO fieldLookUpDTO = new TableFieldLookUpDTO();

    HierarchyBuilderLogic logic = new HierarchyBuilderLogic();

    public TextField getTempTable() {
        return tempTable;
    }

    public void setTempTable(final TextField tempTable) {
        this.tempTable = tempTable;
    }

    public TextField getTempField() {
        return tempField;
    }

    public void setTempField(final TextField tempField) {
        this.tempField = tempField;
    }

    public List<TableFieldLookUpDTO> getLookUpList() {
        return lookUpList;
    }

    public void setLookUpList(final List<TableFieldLookUpDTO> lookUpList) {
        this.lookUpList = lookUpList;
    }

    /**
     * Gets the field list.
     *
     * @return the field list
     */
    public List<TableFieldLookUpDTO> getFieldList() {
        return fieldList;
    }

    public void setFieldList(final List<TableFieldLookUpDTO> fieldList) {
        this.fieldList = fieldList;
    }

    public ErrorLabel getErrorMsg() {
        return errorMsg;
    }

    public TextField getTableName() {
        return tableName;
    }

    public TextField getFieldName() {
        return fieldName;
    }

    public BeanItemContainer<TableFieldLookUpDTO> getFieldBean() {
        return fieldBean;
    }

    public Button getExcelExportTables() {
        return excelExportTables;
    }

    public Button getExcelExportFields() {
        return excelExportFields;
    }

    public ThemeResource getExcelImage() {
        return excelImage;
    }

    public void setBinder(final CustomFieldGroup binder) {
        this.binder = binder;
    }

    /**
     * Instantiates a new table field name look up.
     *
     * @param tableName the table name
     * @param fieldName the field name
     */
    public TableFieldNameLookUp(final TextField tableName, final TextField fieldName) {
        super("Table/Field Lookup");
        LOGGER.debug("TableFieldNameLookUp constructor started");
        this.tempTable = tableName;
        this.tempField = fieldName;
        init();
        LOGGER.debug("TableFieldNameLookUp constructor Ended");
    }

    /**
     * Inits the.
     */
    public void init() {
        LOGGER.debug("init method started");
        center();
        setClosable(true);
        setModal(true);
        setWidth("1200px");
        setHeight("710px");
        setContent(Clara.create(getClass().getResourceAsStream("/clara/tableFieldNameLookup.xml"), this));
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        getBinder();
        configureFields();
        addTable();
        addFieldTable();
        LOGGER.debug("init method Ended");
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     * @throws Exception the exception
     */
    private CustomFieldGroup getBinder() {
        LOGGER.debug("getBinder method started");
        binder = new CustomFieldGroup(new BeanItem<>(new TableFieldLookUpDTO()));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
        LOGGER.debug("getBinder method returns binder");
        return binder;
    }

    /**
     * Adds the search reset buttons.
     *
     * @return the horizontal layout
     */
    public HorizontalLayout addSearchResetButtons() {
        LOGGER.debug("addSearchResetButtons method started");
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setStyleName("responsiveTabGrid");
        layout.setSpacing(true);
        layout.setMargin(true);
        LOGGER.debug("addSearchResetButtons method returns layout");
        return layout;

    }

    /**
     * Update button.
     *
     * @return the button
     */
    @UiHandler("updateBtn")
    public void updateBtnLogic(Button.ClickEvent event) {
        LOGGER.debug("updateButton method started");
        binder.getErrorDisplay().clearError();
        if (resultTable.getValue() == null) {
            MessageBox.showPlain(Icon.ERROR, "Update", "Please select a Table Name.", ButtonId.OK);
            return;
        } else {
            if (fieldTable.getValue() == null) {
                MessageBox.showPlain(Icon.ERROR, "Update", "Please select a field Name.", ButtonId.OK);
                return;
            } else {
                final TableFieldLookUpDTO forTableName = (TableFieldLookUpDTO) resultTable.getValue();
                final TableFieldLookUpDTO forFieldName = (TableFieldLookUpDTO) fieldTable.getValue();
                tempTable.setValue(String.valueOf(forTableName.getTableName()));
                tempField.setValue(String.valueOf(forFieldName.getFieldName()));
                close();
            }
        }
        LOGGER.debug("updateButton method Ended");

    }

    /**
     * Close button.
     *
     * @return the button
     */
    @UiHandler("closeBtn")
    public void closeBtnLogic(Button.ClickEvent event) {
        close();
    }

    /**
     * Adds the table.
     *
     * @return the filter table
     */
    public void addTable() {
        LOGGER.debug("addTable method started");
        tableResultsLayout.addComponent(resultTable);
        tableLogic.setContainerDataSource(resultsContainer);
        resultTable.setSelectable(true);
        tableLogic.setPageLength(NumericConstants.FIVE);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultTable.setCaption("Table Results");
        resultTable.setVisibleColumns(CommonUIUtil.getInstance().tableLookupColumns);
        resultTable.setColumnHeaders(CommonUIUtil.getInstance().tableLookupHeader);
        resultTable.setFilterBarVisible(true);
        resultTable.setSizeFull();
        resultTable.setImmediate(true);
        resultTable.setPageLength(NumericConstants.FIVE);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.addStyleName("filtertable");
        resultTable.addStyleName("table-header-normal");
        resultTable.setWidth("450px");
        ResponsiveUtils.getResponsiveControls(tableLogic.createControls(), tableResultsControlLayout);
        tableResultsLayout.addComponent(tableResultsControlLayout);
        resultTable.addItemClickListener(new ItemClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                LOGGER.debug("In addTable table.addItemClickListener Started");
                try {
                    final TableFieldLookUpDTO dto = (TableFieldLookUpDTO) event.getItemId();
                    fieldLookUpDTO = (TableFieldLookUpDTO) event.getItemId();
                    tableLogicForFieldName.fireSetData(dto, false, ConstantsUtils.FIELD);

                    String filterValue = String.valueOf(fieldTable.getFilterFieldValue(FIELD_NAME));
                    fieldTable.setFilterFieldValue(FIELD_NAME, ConstantsUtils.EMPTY);

                    if (!filterValue.equals("null") && !filterValue.equals(ConstantsUtils.EMPTY)) {
                        fieldTable.setFilterFieldValue(FIELD_NAME, filterValue);
                    }

                } catch (Exception ex) {
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
                    LOGGER.error(ex);
                }
                LOGGER.debug("In addTable table.addItemClickListener Ended");
            }
        });
        LOGGER.debug("addTable method returns table");

    }

    /**
     * Adds the field table.
     *
     * @return the filter table
     */
    public void addFieldTable() {
        LOGGER.debug("addFieldTable method started");
        fieldResultsLayout.addComponent(fieldTable);
        tableLogicForFieldName.setContainerDataSource(fieldBean);
        fieldTable.setSelectable(true);
        tableLogicForFieldName.setPageLength(NumericConstants.FIVE);
        tableLogicForFieldName.sinkItemPerPageWithPageLength(false);
        fieldTable.setCaption("Field Results");
        fieldTable.setVisibleColumns(CommonUIUtil.getInstance().fieldLookupColumn);
        fieldTable.setColumnHeaders(CommonUIUtil.getInstance().fieldLookupHeader);
        fieldTable.setFilterBarVisible(true);
        fieldTable.setSizeFull();
        fieldTable.setImmediate(true);
        fieldTable.setPageLength(NumericConstants.FIVE);
        fieldTable.setFilterDecorator(new ExtDemoFilterDecorator());
        fieldTable.addStyleName("filtertable");
        fieldTable.addStyleName("table-header-normal");
        fieldTable.setWidth("450px");
        ResponsiveUtils.getResponsiveControls(tableLogicForFieldName.createControls(), fieldResultsControlLayout);
        fieldResultsLayout.addComponent(fieldResultsControlLayout);
        LOGGER.debug("addFieldTable method returns Filtertable");
    }

    /**
     * Search button.
     *
     * @return the button
     */
    private void searchButtonLogic() {
        LOGGER.debug("SearchButton method started");
        try {
            binder.getErrorDisplay().clearError();
            binder.commit();
            final String table1 = tableName.getValue();
            final String field = fieldName.getValue();
            if (!ConstantsUtils.EMPTY.equals(table1) || !ConstantsUtils.EMPTY.equals(field)) {

                tableLookUpDTO.setFieldName(field);
                tableLookUpDTO.setTableName(table1);
                tableLogic.fireSetData(tableLookUpDTO, false, ConstantsUtils.TABLE);

                if (resultsContainer.size() == 0) {
                    MessageBox.showPlain(Icon.ERROR, "No Matching Records", "No results found. Please try Again", ButtonId.OK);
                }

            } else {
                MessageBox.showPlain(Icon.ERROR, "Error", "Please Enter Table Name/Field Name ", ButtonId.OK);
            }

        } catch (Exception ex) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4005));
            LOGGER.error(ex);
        }

        LOGGER.debug("SearchButton method Ended");

    }

    /**
     * Configure fields.
     */
    public void configureFields() {
        LOGGER.debug("configureFields method started");
        setCloseShortcut(ShortcutAction.KeyCode.ESCAPE);
        tableName.focus();
        tableName.setImmediate(true);
        tableName.setValue(tempTable.getValue());
        fieldName.setImmediate(true);
        fieldName.setValue(tempField.getValue());

        excelExportTables.setIcon(excelImage);
        excelExportTables.setStyleName("link");
        excelExportTables.setIconAlternateText(ConstantsUtils.EXCEL_EXPORT);
        excelExportTables.setDescription(ConstantsUtils.EXCEL_EXPORT);

        excelExportFields.setIconAlternateText(ConstantsUtils.EXCEL_EXPORT);
        excelExportFields.setDescription(ConstantsUtils.EXCEL_EXPORT);
        excelExportFields.setIcon(excelImage);
        excelExportFields.setStyleName("link");

        excelExportTables.addClickListener(new ClickListener() {
            /**
             * Called when an icon has been clicked.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields excelExportTables.addClickListener Started");
                if (resultTable.size() > ConstantsUtils.ZERO_NUM) {
                    try {
                        configureExcelResultTable();
                        loadExcelTable(tableLookUpDTO);
                        ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(excelTable), "Table", "Table", "Table.xls", false);
                        excel.export();
                        tableResultsLayout.removeComponent(excelTable);
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                } else {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No results to Export", ButtonId.OK);
                }
                LOGGER.debug("In configureFields excelExportTables.addClickListener Ended");
            }
        });

        excelExportFields.addClickListener(new ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields excelExportFields.addClickListener Started");
                if (fieldTable.size() > ConstantsUtils.ZERO_NUM) {
                    try {
                        configureExcelFieldTable();
                        loadExcelFieldTable(fieldLookUpDTO);
                        ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(excelFieldTable), "Field Table", "Field Table", "Field Table.xls", false);
                        excel.export();
                        fieldResultsLayout.removeComponent(excelFieldTable);
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }

                } else {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No results to Export", ButtonId.OK);
                }
                LOGGER.debug("In configureFields excelExportFields.addClickListener Ended");
            }
        });
        LOGGER.debug("configureFields method Ended");
        loadData();
    }

    @UiHandler("searchBtn")
    public void searchBtnLogic(Button.ClickEvent event) {
        LOGGER.debug("In SearchButton btnSearch.addClickListener Started");
        searchButtonLogic();
        LOGGER.debug("In SearchButton btnSearch.addClickListener Ended");
    }

    public void loadData() {
        if (StringUtils.isNotBlank(tableName.getValue()) && StringUtils.isNotBlank(fieldName.getValue())) {
            searchButtonLogic();
        }
    }

    /**
     * To configure Excel Results Table
     */
    private void configureExcelResultTable() {
        excelTableBean = new BeanItemContainer<>(TableFieldLookUpDTO.class);
        excelTable = new ExtFilterTable();
        tableResultsLayout.addComponent(excelTable);
        excelTable.setVisible(false);
        excelTable.setContainerDataSource(excelTableBean);
        excelTable.setVisibleColumns(CommonUIUtil.getInstance().tableLookupColumns);
        excelTable.setColumnHeaders(CommonUIUtil.getInstance().tableLookupHeader);

    }

    /**
     * To load excel Table similar to Table in UI
     *
     * @param tableFieldLookUpDTO
     * @throws Exception
     */
    private void loadExcelTable(TableFieldLookUpDTO tableFieldLookUpDTO) throws SystemException, PortalException{
        excelTableBean.removeAllItems();
        int count = logic.getTableCount(tableFieldLookUpDTO, null);
        List<TableFieldLookUpDTO> resultList = logic.getUniqueTable(tableFieldLookUpDTO, 0, count, null, null);
        excelTableBean.addAll(resultList);
    }

    /**
     * To configure excel field Table similar to Field Table in UI
     */
    private void configureExcelFieldTable() {
        excelFieldBean = new BeanItemContainer<>(TableFieldLookUpDTO.class);
        excelFieldTable = new ExtFilterTable();
        fieldResultsLayout.addComponent(excelFieldTable);
        excelFieldTable.setVisible(false);
        excelFieldTable.setContainerDataSource(excelFieldBean);
        excelFieldTable.setVisibleColumns(CommonUIUtil.getInstance().fieldLookupColumn);
        excelFieldTable.setColumnHeaders(CommonUIUtil.getInstance().fieldLookupHeader);

    }

    /**
     * To load excel field Table similar to Field Table in UI
     *
     * @param tableFieldLookUpDTO
     * @throws Exception
     */
    private void loadExcelFieldTable(TableFieldLookUpDTO tableFieldLookUpDTO) throws SystemException, PortalException {
        excelFieldBean.removeAllItems();
        int count = logic.getFieldNameCount(tableFieldLookUpDTO.getTableName(), null);
        List<TableFieldLookUpDTO> resultList = logic.getTableName(tableFieldLookUpDTO.getTableName(), 0, count, null, null);
        excelFieldBean.addAll(resultList);
    }
        public static final String FIELD_NAME = "fieldName";
}
