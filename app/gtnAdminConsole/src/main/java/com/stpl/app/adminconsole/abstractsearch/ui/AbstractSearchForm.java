/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.abstractsearch.ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.adminconsole.abstractsearch.dto.SearchCriteriaDTO;
import com.stpl.app.adminconsole.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.adminconsole.abstractsearch.logic.AbstractSearchLogic;
import com.stpl.app.adminconsole.abstractsearch.logic.tableLogic.AbstractSearchTableLogic;
import com.stpl.app.adminconsole.abstractsearch.util.MessageUtil;
import com.stpl.app.adminconsole.abstractsearch.util.ValidationUtil;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.discount.logic.DiscountLogic;
import com.stpl.app.adminconsole.discount.ui.view.DiscountAddView;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.CommonUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.app.adminconsole.util.Message;
import com.stpl.app.adminconsole.util.ResponsiveUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

/**
 *
 * @author Manasa
 */
public class AbstractSearchForm extends CustomComponent {

    @UiField("text1")
    private TextField text1;

    @UiField("text2")
    private TextField text2;

    @UiField("text3")
    private TextField text3;

    @UiField("text4")
    private TextField text4;

    @UiField("text5")
    private TextField text5;

    @UiField("text6")
    private TextField text6;

    @UiField("text7")
    private TextField text7;

    @UiField("text8")
    private TextField text8;

    @UiField("combo1")
    private ComboBox combo1;

    @UiField("combo2")
    private ComboBox combo2;

    @UiField("combo3")
    private ComboBox combo3;

    @UiField("label4")
    private Label label4;

    @UiField("label5")
    private Label label5;

    @UiField("label6")
    private Label label6;

    @UiField("label7")
    private Label label7;

    @UiField("label8")
    private Label label8;

    @UiField("label9")
    private Label label9;

    @UiField("label10")
    private Label label10;

    @UiField("label11")
    private Label label11;

    @UiField("label12")
    private Label label12;

    @UiField("label13")
    private Label label13;

    @UiField("label14")
    private Label label14;

    @UiField("label15")
    private Label label15;

    @UiField("label16")
    private Label label16;

    @UiField("label17")
    private Label label17;

    @UiField("option1")
    private OptionGroup option1;

    @UiField("option2")
    private OptionGroup option2;

    @UiField("date1")
    private PopupDateField date1;

    @UiField("date2")
    private PopupDateField date2;

    @UiField("date3")
    private PopupDateField date3;

    @UiField("date4")
    private PopupDateField date4;

    @UiField("excel")
    public Button excel;

    @UiField("tableLayout")
    private VerticalLayout tableLayout;

    @UiField("controlLayout")
    private HorizontalLayout controlLayout;

    @UiField("resetBtn")
    private Button resetBtn;

    @UiField("auditSearchBtn")
    private Button auditSearchBtn;

    @UiField("saveBtn")
    private Button saveBtn;

    @UiField("addBtn")
    private Button addBtn;

    @UiField("editBtn")
    private Button editBtn;

    @UiField("viewBtn")
    private Button viewBtn;

    @UiField("copyBtn")
    private Button copyBtn;

    @UiField("deleteBtn")
    private Button deleteBtn;

    @UiField("autoUpdateBtn")
    private Button autoUpdateBtn;

    @UiField("processTrackingBtn")
    private Button processTrackingBtn;

    @UiField("resetButton")
    private Button resetButton;

    @UiField("cssLayout")
    private CssLayout cssLayout;

    @UiField("buttonLayout")
    private HorizontalLayout buttonLayout;

    private AbstractSearchTableLogic tableLogic = new AbstractSearchTableLogic();
    private ExtPagedTable resultTable = new ExtPagedTable(tableLogic);
    private BeanItemContainer<SearchResultsDTO> resultBean = new BeanItemContainer<>(SearchResultsDTO.class);
    private final Map<String, String> SecuritymoduleName = new HashMap<>();
    private CommonUtils commonUtil = CommonUtils.getInstance();
    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSearchForm.class);
    private final AbstractSearchLogic searchLogic = new AbstractSearchLogic();
    public static ResourceBundle columnBundle = ResourceBundle.getBundle("properties.tableColumns");
    private DiscountLogic discountLogic = new DiscountLogic();
    private String moduleName = StringUtils.EMPTY;
    private final CommonUtils commonsUtil = new CommonUtils();
    public static final String NAME = ConstantsUtils.EMPTY;
    public static final String ERROR_MSG = "No Record has been selected.  Please select a Record and try again.";
    /**
     * The ErrorLabel.
     */
    @UiField("errorMsg")
    public ErrorLabel errorMsg;

    private final ErrorfulFieldGroup binder;

    private int versionNo;

    private String searchCriteria = ConstantsUtils.EMPTY;
    /**
     * The Excel table.
     */
    private ExtFilterTable excelTable;
    private BeanItemContainer<SearchResultsDTO> excelTableBean;
    private SessionDTO sessionDTO;
    /**
     * The Constructor.
     *
     * @param moduleName
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public AbstractSearchForm(String moduleName, final SessionDTO sessionDTO) throws SystemException, PortalException {
        super();
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/abstractsearchform.xml"), this));
        binder = getBinder();
        this.moduleName = moduleName;
        this.sessionDTO = sessionDTO;
        init();
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    public ErrorfulFieldGroup getBinder() {
        final SearchCriteriaDTO bean = new SearchCriteriaDTO();
        final ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<>(bean));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
        return binder;
    }

    /**
     * Inits the.
     *
     * @throws com.liferay.portal.kernel.exception.PortalException
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public void init() throws PortalException, SystemException {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = sessionDTO.getUserId();
        final Map<String, AppPermission> functionCompanyHM = stplSecurity.getBusinessFunctionPermission(userId, securityName() + "," + "Index Screen");
        permissionforButton(functionCompanyHM);
        configureFields();
        configureTable();
    }

    /**
     * Method has configuration for the fields.
     */
    public void configureFields() throws SystemException {
        try {
            LOGGER.debug("Enters configureFields() ");

            option1.addItem(CommonUtils.PRIMARY);
            option1.addItem("Secondary");
            option1.select(CommonUtils.PRIMARY);
            option1.addItem(CommonUtils.PRIMARY);
            option1.setImmediate(true);

            option2.addItem(CommonUtils.PRIMARY);
            option2.addItem("Secondary");
            option2.select(CommonUtils.PRIMARY);
            option2.addItem(CommonUtils.PRIMARY);
            option2.setImmediate(true);

            configureLayout(moduleName);
            fieldValidation(moduleName);
            for (java.lang.reflect.Field field : this.getClass().getDeclaredFields()) {
                if (field.get(this) instanceof Label && ((Label) field.get(this)).isVisible()) {
                    setLabelName(field.get(this), field.getName() + "_" + moduleName);
                    ResponsiveUtils.makeLabel((Label) field.get(this), false);

                } else if (field.get(this) instanceof TextField && ((TextField) field.get(this)).isVisible()) {
                        commonUtil.textValidation(field.get(this), ((TextField) field.get(this)).getData());
                        ((TextField) field.get(this)).setValidationVisible(true);

                }
            }

            excel.setIcon(excelExportImage);
            excel.setDescription("Export to excel");
            excel.setIconAlternateText("Excel export");
            excel.setHtmlContentAllowed(true);
            LOGGER.debug("Exits configureFields() ");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * To Set Label Value
     *
     * @param obj
     * @param key
     */
    private void setLabelName(Object obj, String key) {
        try {
            Label tempObj = (Label) obj;
            if (ValidationUtil.getLabel(key) != null && StringUtils.isNotEmpty(ValidationUtil.getLabel(key))) {
                tempObj.setValue(ValidationUtil.getLabel(key));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void configureTable() {

        tableLayout.addComponent(resultTable);
        ResponsiveUtils.getResponsiveControls(tableLogic.createControls(), controlLayout);
        tableLayout.addComponent(controlLayout);

        tableLogic.setContainerDataSource(resultBean);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);

        setTableDefaultConfig(resultTable, moduleName);
        resultTable.setSelectable(true);
        resultTable.markAsDirty();
        resultTable.setComponentError(null);
        resultTable.setFilterBarVisible(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.setValidationVisible(false);
        resultTable.addStyleName("filterbar");

        resultTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                itemselectLogic(event);
            }
        });
    }

    public void itemselectLogic(final ItemClickEvent event) {
        try {
            if (event.isDoubleClick()) {
                SearchResultsDTO searchForm1 = (SearchResultsDTO) event.getItemId();
                sessionDTO.setSystemId(Integer.valueOf(searchForm1.getSystemID()));
                    if (searchForm1.isRecordLockStatus()) {

                        if (!itemStatusCheck()) {
                            sessionDTO.setMode(ConstantsUtils.VIEW);

                            viewLogic();
                        } else {
                            sessionDTO.setMode(ConstantsUtils.EDIT);
                            editLogic();
                        }
                    } else {
                        sessionDTO.setMode(ConstantsUtils.EDIT);
                        editLogic();
                    }
                }
            resultTable.setValue(null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

        }
    }

    public boolean itemStatusCheck() {
        try {

            final String userId = sessionDTO.getUserId();
            boolean etlCheck = commonsUtil.checkETLUser(Integer.valueOf(userId));

            return etlCheck;
        } catch (Exception ex) {
           LOGGER.error(ex.getMessage());
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1015));
        }
        return false;
    }

    @UiHandler("searchBtn")
    public void btnSearchLogic(Button.ClickEvent event) {
        binder.getErrorDisplay().clearError();
        editBtn.setEnabled(true);
        copyBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
        List<Object> collapsedColumns = new ArrayList<>();
        for (Object item : resultTable.getVisibleColumns()) {
            if (resultTable.isColumnCollapsed(item)) {
                collapsedColumns.add(item);
            }
        }
        if (!searchLogic.checkSearchCriteria(binder)) {
            AbstractNotificationUtils.getWarningNotification(MessageUtil.getMessage(Message.SEARCH_CRITERIA_HEADER), MessageUtil.getMessage(Message.SEARCH_CRITERIA_MESSAGE));
        } else {

            try {
                binder.commit();
                searchCriteria = ConstantsUtils.SEARCH;
                tableLogic.configureSearchData(binder, this.moduleName, searchCriteria);
                resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
                resultTable.setImmediate(true);
                resultTable.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
                resultTable.addStyleName("TableCheckBox");
                resultTable.setSelectable(true);
                if (tableLogic.isResultsEmpty()) {
                    CommonUIUtils.successNotification(ConstantsUtils.NO_RESULSTS);
                } else {
                    sessionDTO.setMode("no");
                    CommonUIUtils.successNotification(ConstantsUtils.SEARCH_COMPLETED);
                }

                resultTable.markAsDirtyRecursive();

            } catch (FieldGroup.CommitException commit) {
                tableLogic.clearAll();
                tableLogic.getFilters().clear();
                resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
                LOGGER.error(commit.getMessage());
            } catch (Exception exception) {
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004));
                LOGGER.error(exception.getMessage());
            }
        }

    }

    public String securityName() {
        SecuritymoduleName.put(ConstantsUtils.DEDUCTION_GROUPING, "Deduction Grouping");

        return SecuritymoduleName.get(moduleName);

    }

    @UiHandler("auditSearchBtn")
    public void auditSearchBtnLogic(Button.ClickEvent event) {
        binder.getErrorDisplay().clearError();
        List<Object> collapsedColumns = new ArrayList<>();
        for (Object item : resultTable.getVisibleColumns()) {
            if (resultTable.isColumnCollapsed(item)) {
                collapsedColumns.add(item);
            }
        }
        if (!searchLogic.checkSearchCriteria(binder)) {
            AbstractNotificationUtils.getWarningNotification(MessageUtil.getMessage(Message.SEARCH_CRITERIA_HEADER), MessageUtil.getMessage(Message.SEARCH_CRITERIA_MESSAGE));
        } else {

            try {
                binder.commit();
                searchCriteria = "auditSearch";
                tableLogic.configureSearchData(binder, this.moduleName, searchCriteria);
                resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
                resultTable.setImmediate(true);
                resultTable.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
                resultTable.addStyleName("TableCheckBox");
                resultTable.setSelectable(true);
                if (tableLogic.isResultsEmpty()) {
                    CommonUIUtils.successNotification(ConstantsUtils.NO_RESULSTS);
                } else {
                    CommonUIUtils.successNotification(ConstantsUtils.SEARCH_COMPLETED);
                    editBtn.setEnabled(false);
                    copyBtn.setEnabled(false);
                    deleteBtn.setEnabled(false);
                }

                resultTable.markAsDirtyRecursive();

            } catch (FieldGroup.CommitException commit) {
                tableLogic.clearAll();
                tableLogic.getFilters().clear();
                resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
                LOGGER.error(commit.getMessage());
            } catch (Exception exception) {
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004));
                LOGGER.error(exception.getMessage());
            }
        }

    }

    private void configureLayout(String moduleName) throws PortalException, SystemException {

        if (ConstantsUtils.DEDUCTION_GROUPING.equals(moduleName)) {
            label4.setVisible(false);
            combo1.setVisible(false);
            label5.setVisible(false);
            combo2.setVisible(false);
            label6.setVisible(false);
            option1.setVisible(false);
            label7.setVisible(false);
            text4.setVisible(false);
            label8.setVisible(false);
            text5.setVisible(false);
            label9.setVisible(false);
            text6.setVisible(false);
            label10.setVisible(false);
            date1.setVisible(false);
            label11.setVisible(false);
            date2.setVisible(false);
            label12.setVisible(false);
            option2.setVisible(false);
            label13.setVisible(false);
            date3.setVisible(false);
            label14.setVisible(false);
            date4.setVisible(false);
            label15.setVisible(false);
            combo3.setVisible(false);
            label16.setVisible(false);
            text7.setVisible(false);
            label17.setVisible(false);
            text8.setVisible(false);
            autoUpdateBtn.setVisible(false);
            processTrackingBtn.setVisible(false);
            saveBtn.setVisible(false);
            auditSearchBtn.setVisible(false);
            copyBtn.setVisible(false);
            resetBtn.setVisible(false);
        }
    }

    public void btnAddLogic() {
        try {
            sessionDTO.setMode(ConstantsUtils.ADD);
            if (ConstantsUtils.DEDUCTION_GROUPING.equalsIgnoreCase(moduleName)) {
                sessionDTO.setLogic(ConstantsUtils.ADD_LOWER_CASE);
                getUI().getNavigator().navigateTo(DiscountAddView.NAME);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void editLogic() {
        sessionDTO.setMode(ConstantsUtils.EDIT);
        if (ConstantsUtils.DEDUCTION_GROUPING.equalsIgnoreCase(moduleName)) {
            sessionDTO.setFromViewPage(ConstantsUtils.EDIT);
            sessionDTO.setLogic("edit");
            getUI().getNavigator().navigateTo(DiscountAddView.NAME);
        }
    }

    private void viewLogic() {
        sessionDTO.setMode(ConstantsUtils.VIEW);
        if (ConstantsUtils.DEDUCTION_GROUPING.equalsIgnoreCase(moduleName)) {
            sessionDTO.setLogic("view");
            sessionDTO.setVersionNo(versionNo);
            getUI().getNavigator().navigateTo(DiscountAddView.NAME);
        }
    }

    private Object[] getColumns(boolean isColumns, String key) {

        return (columnBundle.getString(isColumns ? "columns" + key : "headers" + key)).split(",");

    }

    public void setTableDefaultConfig(ExtPagedTable resultTable, String key) {

        resultTable.setVisibleColumns(getColumns(true, key));
        resultTable.setColumnHeaders(Arrays.copyOf(getColumns(false, key), getColumns(false, key).length, String[].class));
        resultTable.markAsDirtyRecursive();
        resultTable.setImmediate(true);
        resultTable.setWidth(NumericConstants.HUNDRED, UNITS_PERCENTAGE);

    }

    public void deleteBtnLogic() {
        LOGGER.debug("deleteButtonClickLogic method Started ");
        if (resultTable.getValue() == null) {
            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, ERROR_MSG, ButtonId.OK);
        } else {
            final SearchResultsDTO searchForm = (SearchResultsDTO) resultTable.getValue();
            String string = "Are you sure you want to delete record ";
            String message = null;
            if (ConstantsUtils.CUSTOMER_GROUP_MASTER.equals(moduleName)) {
                /*GAL-802*/ message = string + searchForm.getCompanyGroupName();
            } else if (ConstantsUtils.ITEM_GROUP_MASTER.equals(moduleName)) {
                message = string + searchForm.getItemGroupName();
            } else if (ConstantsUtils.HIERARCHY_DEF.equals(moduleName)) {
                message = string + searchForm.getHierarchyName();
            } else if (ConstantsUtils.RELATIONSHIP_BUILDER.equals(moduleName)) {
                /*GAL-808*/ message = string + searchForm.getRelationshipName();
            } else if (ConstantsUtils.DEDUCTION_GROUPING.equals(moduleName)) {
                message = string + searchForm.getDiscountName();
            }

            MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, message
                    + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {
                        /**
                         * Adds the button click listener.
                         *
                         *
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {

                            if (buttonId.name().equals(ConstantsUtils.YES)) {
                                try {

                                    sessionDTO.setSystemId(Integer.valueOf(searchForm.getSystemID()));
                                    int systemId = sessionDTO.getSystemId();
                                    if (ConstantsUtils.DEDUCTION_GROUPING.equals(moduleName)) {
                                        final String deletedDeductionGroupName = discountLogic.deletedeductionGroup(systemId);
                                        if (deletedDeductionGroupName != null && !ConstantsUtils.EMPTY.equals(deletedDeductionGroupName)) {
                                            CommonUIUtils.getMessageNotification(searchForm.getDiscountName() + CommonUtils.SUCCESSFULLY_DELETED);
                                        }
                                    }
                                    resultTable.removeItem(resultTable.getValue());
                                } catch (SystemException e) {
                                    final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
                                    LOGGER.error(errorMsg);
                                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                                } catch (PortalException e) {
                                    LOGGER.error(e.getMessage());
                                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4004));
                                } catch (Exception e) {
                                    LOGGER.error(e.getMessage());
                                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4004));
                                }

                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);
        }
        LOGGER.debug("deleteButtonClickLogic method ENDED ");
    }

    @UiHandler("resetBtn")
    public void btnResetLogic(Button.ClickEvent event) {

        MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to reset the page to default/previous values?", new MessageBoxListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            public void buttonClicked(final ButtonId buttonId) {
                if (buttonId.name().equalsIgnoreCase("YES")) {
                    LOGGER.debug("Entering Reset operation");
                    binder.getErrorDisplay().clearError();
                    binder.setItemDataSource(new BeanItem<>(new SearchCriteriaDTO()));
                    editBtn.setEnabled(true);
                    copyBtn.setEnabled(true);
                    deleteBtn.setEnabled(true);
                    tableLogic.clearAll();
                    tableLogic.setReset(true);
                    tableLogic.setRequiredCount(true);
                    tableLogic.setCurrentPage(1);
                    resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
                    LOGGER.debug("Ending Reset operation");
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    @UiHandler("resetButton")
    public void btnResetButtonLogic(Button.ClickEvent event) {

        MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to reset the page to default/previous values?", new MessageBoxListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            public void buttonClicked(final ButtonId buttonId) {
                if (buttonId.name().equalsIgnoreCase("YES")) {
                    LOGGER.debug("Entering Reset operation");
                    binder.getErrorDisplay().clearError();
                    binder.setItemDataSource(new BeanItem<>(new SearchCriteriaDTO()));
                    tableLogic.clearAll();
                    tableLogic.setReset(true);
                    tableLogic.setRequiredCount(true);
                    tableLogic.setCurrentPage(1);
                    resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
                    LOGGER.debug("Ending Reset operation");
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    private void fieldValidation(String moduleName) {

        if (ConstantsUtils.ITEM_GROUP_MASTER.equals(moduleName)) {
            text1.focus();
            text1.setData("maxlengthvalidationhundred,maxlengthvalidationitemgroupname,null,null");
            text2.setData("maxlengthvalidationhundred,maxlengthvalidationitemgroupno,null,null");
            text3.setData("maxlengthvalidationhundred,maxlengthvalidationitemgroupdescription,null,null");

        } else if (ConstantsUtils.HIERARCHY_DEF.equals(moduleName)) {

            date3.setDateFormat(CommonUtils.MMDDYYYY);
            date4.setDateFormat(CommonUtils.MMDDYYYY);

        } else if (ConstantsUtils.RELATIONSHIP_BUILDER.equals(moduleName)) {
            text1.focus();
            text1.setData("maxlengthvalidationhundred,maxlengthvalidationrelationshipname,null,null");
            text4.setData("maxlengthvalidationhundred,maxlengthvalidationrelationshipdescription,null,null");
            date1.setDateFormat(CommonUtils.MMDDYYYY);
            date2.setDateFormat(CommonUtils.MMDDYYYY);
            date3.setDateFormat(CommonUtils.MMDDYYYY);
            date4.setDateFormat(CommonUtils.MMDDYYYY);

        }

    }

    /**
     * Excel button logic.
     *
     * @param event the event
     */
    @UiHandler("excel")
    public void excelButtonLogic(Button.ClickEvent event) {
        try {
            binder.getErrorDisplay().clearError();
            binder.commit();
            String excelName = moduleName.toUpperCase();
            String key = "excel_" + moduleName;
            configureExcelResultTable();
            loadExcelTable(moduleName, binder, searchCriteria);
            if (ValidationUtil.getLabel(key) != null && StringUtils.isNotEmpty(ValidationUtil.getLabel(key))) {
                excelName = ValidationUtil.getLabel(key);
            }
            excelName = excelName.replaceAll(" ", StringUtils.EMPTY);
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.EXCEL_CLOSE, "true");
            ExcelExport excelExport = new ExcelExport(new ExtCustomTableHolder(excelTable), excelName, excelName, excelName + ".xls", false);
            excelExport.export();
            tableLayout.removeComponent(excelTable);
        } catch (FieldGroup.CommitException commit) {
            LOGGER.error(commit.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

    }

    /**
     * To configure Excel Results Table
     */
    private void configureExcelResultTable() {
        excelTableBean = new BeanItemContainer<>(SearchResultsDTO.class);
        excelTable = new ExtFilterTable();
        tableLayout.addComponent(excelTable);
        excelTable.setVisible(true);
        excelTable.setContainerDataSource(excelTableBean);
        excelTable.setVisibleColumns(getColumns(true, moduleName));
        excelTable.setColumnHeaders(Arrays.copyOf(getColumns(false, moduleName), getColumns(false, moduleName).length, String[].class));
        excelTable.markAsDirtyRecursive();

    }

    /**
     * To load excel Table similar to Table in UI
     *
     * @param tableFieldLookUpDTO
     * @throws Exception
     */
    private void loadExcelTable(String moduleName, ErrorfulFieldGroup binder, String searchCriteria) throws SystemException, ParseException, PortalException {
        excelTableBean.removeAllItems();
        if (resultTable.size() != 0) {
            int count = searchLogic.getCountBasedOnModules(binder, 0, 0, true, null, null, moduleName, searchCriteria);
            List resultList = new ArrayList();

            if (count != 0) {
                resultList = searchLogic.getSearchResultsBasedOnModules(binder, 0, count, false, null, null, moduleName, searchCriteria);
            }
            excelTableBean.addAll(resultList);
        }
    }

    private void permissionforButton(Map<String, AppPermission> functionCompanyHM) {

        if (functionCompanyHM.get(ConstantsUtils.MAIN_ADD_BUTTON) != null && ((AppPermission) functionCompanyHM.get(ConstantsUtils.MAIN_ADD_BUTTON)).isFunctionFlag()) {
            addbutton();
        } else {
            buttonLayout.removeComponent(addBtn);
        }
        if (functionCompanyHM.get(ConstantsUtils.EDIT_BUTTON) != null && ((AppPermission) functionCompanyHM.get(ConstantsUtils.EDIT_BUTTON)).isFunctionFlag()) {
            editButton();
        } else {
            buttonLayout.removeComponent(editBtn);
        }
        if (functionCompanyHM.get(ConstantsUtils.VIEW_BUTTON) != null && ((AppPermission) functionCompanyHM.get(ConstantsUtils.VIEW_BUTTON)).isFunctionFlag()) {
            viewButton();
        } else {
            buttonLayout.removeComponent(viewBtn);
        }
        if (functionCompanyHM.get(ConstantsUtils.COPY_BUTTON) != null && ((AppPermission) functionCompanyHM.get(ConstantsUtils.COPY_BUTTON)).isFunctionFlag()) {
            copyButton();
        } else {
            buttonLayout.removeComponent(copyBtn);
        }
        if (functionCompanyHM.get(ConstantsUtils.DELETE_BUTTON) != null && ((AppPermission) functionCompanyHM.get(ConstantsUtils.DELETE_BUTTON)).isFunctionFlag()) {
            deleteButton();
        } else {
            buttonLayout.removeComponent(deleteBtn);
        }

    }

    private void addbutton() {
        addBtn.addClickListener(new Button.ClickListener() {
            /**
             * Method is called when add button is clicked
             */
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    sessionDTO.setMode(ConstantsUtils.ADD);
                    if (ConstantsUtils.DEDUCTION_GROUPING.equalsIgnoreCase(moduleName)) {
                        sessionDTO.setSystemId(0);
                        sessionDTO.setLogic(ConstantsUtils.ADD_LOWER_CASE);
                        getUI().getNavigator().navigateTo(DiscountAddView.NAME);
                    }
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());

                }
            }
        });
    }

    private void editButton() {
        editBtn.addClickListener(new Button.ClickListener() {
            /**
             * Method is called when add button is clicked
             */
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {

                    if (resultTable.getValue() == null) {
                        AbstractNotificationUtils.getErrorNotification("Edit Error", "Please select a record to edit");
                    } else {
                        SearchResultsDTO searchForm = (SearchResultsDTO) resultTable.getValue();
                        sessionDTO.setMode(ConstantsUtils.EDIT);
                        sessionDTO.setSystemId(Integer.valueOf(searchForm.getSystemID()));
                        sessionDTO.setVersionNo(searchForm.getVersionNo());
                            if (searchForm.isRecordLockStatus()) {
                                if (itemStatusCheck()) {
                                    editLogic();
                                    resultTable.unselect(resultTable.getValue());
                                } else {
                                    AbstractNotificationUtils.getInfoNotification("Access Denied", "You do not have permission to edit this record");
                                }
                            } else {
                                editLogic();
                                resultTable.unselect(resultTable.getValue());
                            }
                    }
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
            }
        });
    }

    private void viewButton() {
        viewBtn.addClickListener(new Button.ClickListener() {
            /**
             * Method is called when add button is clicked
             */
            @Override
            public void buttonClick(Button.ClickEvent event) {

                try {
                    if (resultTable.getValue() == null) {

                        AbstractNotificationUtils.getErrorNotification("View Error", "Please select a record to view");
                    } else {
                        sessionDTO.setMode(ConstantsUtils.VIEW);
                        SearchResultsDTO searchForm = (SearchResultsDTO) resultTable.getValue();
                        sessionDTO.setSystemId(Integer.valueOf(searchForm.getSystemID()));
                        sessionDTO.setVersionNo(searchForm.getVersionNo());
                        viewLogic();
                    }
                } catch (Exception ex) {
                   LOGGER.error(ex.getMessage());
                }
                resultTable.unselect(resultTable.getValue());
            }
        });
    }

    private void copyButton() {
        copyBtn.addClickListener(new Button.ClickListener() {
            /**
             * Method is called when add button is clicked
             */
            @Override
            public void buttonClick(Button.ClickEvent event) {

                if (resultTable.getValue() == null) {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, ERROR_MSG, ButtonId.OK);
                } else {
                    final SearchResultsDTO searchForm = (SearchResultsDTO) resultTable.getValue();
                    MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to copy record " + searchForm.getRelationshipName() +
                             ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {
                                /**
                                 * Adds the button click listener.
                                 *
                                 *
                                 */
                                @SuppressWarnings("PMD")
                                public void buttonClicked(final ButtonId buttonId) {
                                    if (buttonId.name().equals(ConstantsUtils.YES)) {
                                        try {

                                            sessionDTO.setSystemId(Integer.valueOf(searchForm.getSystemID()));
                                            sessionDTO.setVersionNo(searchForm.getVersionNo());
                                            sessionDTO.setLogic("copy");
                                        } catch (Exception e) {
                                            LOGGER.error(e.getMessage());
                                        }
                                    }
                                }
                            }, ButtonId.YES, ButtonId.NO);
                }
            }
        });
    }

    private void deleteButton() {
        deleteBtn.addClickListener(new Button.ClickListener() {
            /**
             * Method is called when add button is clicked
             */
            @Override
            public void buttonClick(Button.ClickEvent event) {

                LOGGER.debug("deleteButton method Started ");
                if (resultTable.getValue() == null) {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, ERROR_MSG, ButtonId.OK);
                } else {
                    final SearchResultsDTO searchForm = (SearchResultsDTO) resultTable.getValue();
                    deleteLogic(searchForm);
                }
                LOGGER.debug("deleteButton method ENDED ");
            }
        });
    }

    private void deleteLogic(final SearchResultsDTO searchForm) {
        String string = "Are you sure you want to delete record ";
        String message = null;
        if (ConstantsUtils.CUSTOMER_GROUP_MASTER.equals(moduleName)) {
            /*GAL-802*/ message = string + searchForm.getCompanyGroupName();
        } else if (ConstantsUtils.ITEM_GROUP_MASTER.equals(moduleName)) {
            message = string + searchForm.getItemGroupName();
        } else if (ConstantsUtils.HIERARCHY_DEF.equals(moduleName)) {
            message = string + searchForm.getHierarchyName();
        } else if (ConstantsUtils.RELATIONSHIP_BUILDER.equals(moduleName)) {
            /*GAL-808*/ message = string + searchForm.getRelationshipName();
        } else if (ConstantsUtils.DEDUCTION_GROUPING.equals(moduleName)) {
            message = string + searchForm.getDiscountName();
        }

        MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, message
                + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {
            /**
             * Adds the button click listener.
             *
             *
             */
            @SuppressWarnings("PMD")
            public void buttonClicked(final ButtonId buttonId) {

                if (buttonId.name().equals(ConstantsUtils.YES)) {
                    try {

                        sessionDTO.setSystemId(Integer.valueOf(searchForm.getSystemID()));
                        int systemId = sessionDTO.getSystemId();
                        if (ConstantsUtils.DEDUCTION_GROUPING.equals(moduleName)) {
                            final String deletedDeductionGroupName = discountLogic.deletedeductionGroup(systemId);
                            if (deletedDeductionGroupName != null && !ConstantsUtils.EMPTY.equals(deletedDeductionGroupName)) {
                                CommonUIUtils.getMessageNotification(searchForm.getDiscountName() + CommonUtils.SUCCESSFULLY_DELETED);
                            }
                        }

                        resultTable.removeItem(resultTable.getValue());
                        resultTable.unselect(resultTable.getValue());
                    } catch (SystemException e) {
                        LOGGER.error(errorMsg.getErrorMessage().toString());
                    } catch (PortalException e) {
                        LOGGER.error(e.getMessage());
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage());
                    }

                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    public void removeOnViewChange() {
        tableLogic.getContainerDataSource().removeAllItems();
        resultTable.removeAllItems();
        resultBean.removeAllItems();
    }
}
