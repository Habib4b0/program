/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.periodConfiguration.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.adminconsole.util.StringConstantUtils;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.periodConfiguration.dto.PeriodConfigDTO;
import com.stpl.app.adminconsole.periodConfiguration.logic.PeriodConfigLogic;
import com.stpl.app.adminconsole.periodConfiguration.logic.PeriodConfigTableLogic;
import com.stpl.app.adminconsole.periodConfiguration.dto.PeridConfigFilterGenerator;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.CommonUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import static com.stpl.app.adminconsole.util.ResponsiveUtils.getResponsiveControls;
import com.stpl.app.adminconsole.util.ValidationUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import static com.vaadin.server.Sizeable.UNITS_PERCENTAGE;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author Mahesh.James
 */
public class PeriodConfigSearchIndex extends CustomComponent implements View {

    private static final Logger LOGGER = Logger.getLogger(PeriodConfigSearchIndex.class);

    public ErrorfulFieldGroup forecastBinder;
    SessionDTO sessionDTO;

    @UiField("errorMsg")
    private ErrorLabel errorMsg;

    @UiField("module")
    private Label module;

    @UiField("businessProcessLabel")
    private Label businessProcessLabel;

    @UiField("companyLabel")
    private Label companyLabel;

    @UiField("businessUnitLabel")
    private Label businessUnitLabel;

    @UiField("periodViewLabel")
    private Label periodViewLabel;

    @UiField("modules")
    private ComboBox modules;

    @UiField("businessProcess")
    private ComboBox businessProcess;

    @UiField("company")
    private ComboBox company;

    @UiField("businessUnit")
    private ComboBox businessUnit;

    @UiField("periodView")
    private OptionGroup periodView;

    @UiField("fromPeriod")
    private Panel fromPeriod;
    @UiField("toPeriod")
    private Panel toPeriod;

    @UiField("fromMode")
    private ComboBox fromMode;

    @UiField("toMode")
    private ComboBox toMode;

    @UiField("fromDefaultMode")
    private ComboBox fromDefaultMode;

    @UiField("toDefaultMode")
    private ComboBox toDefaultMode;

    @UiField("fromFrequency")
    private ComboBox fromFrequency;

    @UiField("toFrequency")
    private ComboBox toFrequency;

    @UiField("fromDefaultFrequency")
    private ComboBox fromDefaultFrequency;

    @UiField("toDefaultFrequency")
    private ComboBox toDefaultFrequency;

    @UiField("fromPeriodGrid")
    private GridLayout fromPeriodGrid;

    @UiField("fromPeriodDate")
    private PopupDateField fromPeriodDate;

    @UiField("fromDefaultPeriodDate")
    private PopupDateField fromDefaultPeriodDate;

    @UiField("toDefaultPeriodDate")
    private PopupDateField toDefaultPeriodDate;

    @UiField("toPeriodDate")
    private PopupDateField toPeriodDate;

    @UiField("toPeriodGrid")
    private GridLayout toPeriodGrid;

    public PeriodConfigDTO forecastDTO = new PeriodConfigDTO();
//
    private PeriodConfigLogic periodConfigLogic = new PeriodConfigLogic();

    @UiField("resultsTable")
    private Panel resultsTable;

    @UiField("selectionPanel")
    private Panel selectionPanel;

    @UiField("periodConfig")
    private Panel periodConfig;

    @UiField("excelExport")
    private Button excelExport;

    @UiField("fromDefaultDate")
    private TextField fromDefaultDate;

    @UiField("fromDate")
    private TextField fromDate;

    @UiField("toDefaultDate")
    private TextField toDefaultDate;

    @UiField("toDate")
    private TextField toDate;

    @UiField("saveBtn")
    private Button saveBtn;

    @UiField("resetBtn")
    private Button resetBtn;
    @UiField("tableLayout")
    VerticalLayout tableLayout;
    private HorizontalLayout controlLayout = new HorizontalLayout();
    PeriodConfigTableLogic tableLogic = new PeriodConfigTableLogic();
    ExtPagedTable results = new ExtPagedTable(tableLogic);
    private ExtFilterTable excelTable;
    private BeanItemContainer<PeriodConfigDTO> excelTableBean;
    CommonUtil commonUtil = new CommonUtil();
    CommonSecurityLogic commonSecurity = new CommonSecurityLogic();

    private TextField fromTextField = new TextField();
    private TextField defaultfromTextField = new TextField();

    private TextField toTextField = new TextField();
    private TextField defaulttoTextField = new TextField();
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    PeriodConfigDTO periodConfigDTO = new PeriodConfigDTO();

    String historicalDdlbValue = StringUtils.EMPTY;
    String futureDdlbValue = StringUtils.EMPTY;
    boolean autoflag = false;
    boolean fromdefaultautoflag = false;
    boolean toModeautoflag = false;
    boolean toDefaultModeautoflag = false;

    /**
     * The results bean.
     */
    public BeanItemContainer<PeriodConfigDTO> resultsBean = new BeanItemContainer<>(PeriodConfigDTO.class);

    public static final String FROM_DEF_PERIOD_VALUE = "fromDefPeriodValue";
    public static final String TO_FREQUENCY_NAME = "toFrequencyName";
    public static final String TO_PERIOD_VALUE = "toPeriodValue";
    /**
     * The Constant DISCOUNT_TABLE_COLUMNS.
     */
    public final Object[] forecastTableColumns = new Object[]{StringConstantUtils.MODULE_NAME, StringConstantUtils.BUSCINESS_PROCESS_NAME, StringConstantUtils.COMPANY_NAME, StringConstantUtils.BUCINSESS_UNIT_NAME, StringConstantUtils.PERIOD_VIEW_NAME, StringConstantUtils.VERSION_NO, StringConstantUtils.CREATED_BY, StringConstantUtils.ACTIVE_FLAG, StringConstantUtils.FROM_MODE_NAME, StringConstantUtils.FROM_FREQUENCY_NAME, StringConstantUtils.FROM_PERIOD_VALUE, StringConstantUtils.FROM_PERIOD_DATE, StringConstantUtils.FROM_DEF_MODE_NAME,
        StringConstantUtils.FROM_DEF_FREQUENCY_NAME, FROM_DEF_PERIOD_VALUE, StringConstantUtils.FROM_DEF_PERIOD_DATE, StringConstantUtils.TO_MODE_NAME, TO_FREQUENCY_NAME, TO_PERIOD_VALUE, StringConstantUtils.TO_PERIOD_DATE,
        StringConstantUtils.TO_DEF_MODE_NAME, StringConstantUtils.TO_DEF_FREQUENCY_NAME, StringConstantUtils.TO_DEF_PERIOD_VALUE, StringConstantUtils.TO_DEF_PERIOD_DATE};

    /**
     * The Constant DISCOUNT_TABLE_HEADER.
     */
    public final String[] forecastTableHeader = new String[]{"Module", "Business Process", "Company", "Business Unit", "Period View", "Version", "Created By", "Active Flag",
        " Mode", " Frequency", " Period", " Date",
        " Default Mode", " Default Frequency", " Default Period", " Default Date",
        " Mode", " Frequency", " Period", " Date",
        " Default Mode", " Default Frequency", " Default Period", " Default Date",};

    /**
     * Instantiates a new PeriodConfigSearchIndex .
     *
     * @param forecastBinder the forecast binder
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public PeriodConfigSearchIndex(final SessionDTO sessionDTO) throws SystemException, PortalException {

        super();
        this.forecastBinder = new ErrorfulFieldGroup(new BeanItem<>(forecastDTO));
        this.sessionDTO = sessionDTO;
        try {
            init();
            configureFields();
        } catch (Exception e) {

            LOGGER.error(e);
        }
    }

    /**
     * Inits the.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void init() throws SystemException, PortalException {
        try {
            LOGGER.debug("PeriodConfigSearchIndex init method is started");
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/periodConfiguration.xml"), this));
            configureTable();
            getBinder();
            LOGGER.debug("PeriodConfigSearchIndex init method ends");

            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(sessionDTO.getUserId());
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, StringConstantUtils.PERIOD_CONFIGURATION + "," + StringConstantUtils.RESULTS_SCREEN);
            if (functionHM.get("saveBtn") != null && !((AppPermission) functionHM.get("saveBtn")).isFunctionFlag()) {
                saveBtn.setVisible(false);
            } else {
                saveBtn.setVisible(true);
            }
            if (functionHM.get("resetBtn") != null && !((AppPermission) functionHM.get("resetBtn")).isFunctionFlag()) {
                resetBtn.setVisible(false);
            } else {
                resetBtn.setVisible(true);
            }
            if (functionHM.get("excelExport") != null && !((AppPermission) functionHM.get("excelExport")).isFunctionFlag()) {
                excelExport.setVisible(false);
            } else {
                excelExport.setVisible(true);
            }

            Map<String, AppPermission> functionHMforFields = stplSecurity.getBusinessFieldPermission(userId, StringConstantUtils.PERIOD_CONFIGURATION + "," + "Landing Screen");
            configureFieldPermission(functionHMforFields);
            if (functionHMforFields.get("modules") != null && !((AppPermission) functionHMforFields.get("modules")).isFunctionFlag()) {
                modules.setVisible(false);
                module.setVisible(false);

            } else {
                modules.setVisible(true);
                module.setVisible(true);

            }
            if (functionHMforFields.get("businessProcess") != null && !((AppPermission) functionHMforFields.get("businessProcess")).isFunctionFlag()) {
                businessProcess.setVisible(false);
                businessProcessLabel.setVisible(false);
            } else {
                businessProcess.setVisible(true);
                businessProcessLabel.setVisible(true);

            }
            if (functionHMforFields.get("company") != null && !((AppPermission) functionHMforFields.get("company")).isFunctionFlag()) {
                company.setVisible(false);
                companyLabel.setVisible(false);

            } else {
                company.setVisible(true);
                companyLabel.setVisible(true);

            }
            if (functionHMforFields.get("businessUnit") != null && !((AppPermission) functionHMforFields.get("businessUnit")).isFunctionFlag()) {
                businessUnit.setVisible(false);
                businessUnitLabel.setVisible(false);

            } else {
                businessUnit.setVisible(true);
                businessUnitLabel.setVisible(true);

            }
            if (functionHMforFields.get("periodView") != null && !((AppPermission) functionHMforFields.get("periodView")).isFunctionFlag()) {
                periodView.setVisible(false);
                periodViewLabel.setVisible(false);
            } else {
                periodView.setVisible(true);
                periodViewLabel.setVisible(true);

            }

            Map<String, AppPermission> functionHMforOtherFields = stplSecurity.getFieldOrColumnPermission(userId, StringConstantUtils.PERIOD_CONFIGURATION + "," + "Landing Screen-From Period", false);
            configureFieldPermissionOtherFields(functionHMforOtherFields);
            if (functionHMforFields.get("fromMode") != null && !((AppPermission) functionHMforFields.get("fromMode")).isFunctionFlag()) {
                fromMode.setVisible(false);
            } else {
                fromMode.setVisible(true);
            }
            if (functionHMforFields.get("fromDefaultMode") != null && !((AppPermission) functionHMforFields.get("fromDefaultMode")).isFunctionFlag()) {
                fromDefaultMode.setVisible(false);
            } else {
                fromDefaultMode.setVisible(true);
            }
            if (functionHMforFields.get("fromFrequency") != null && !((AppPermission) functionHMforFields.get("fromFrequency")).isFunctionFlag()) {
                fromFrequency.setVisible(false);
            } else {
                fromFrequency.setVisible(true);
            }
            if (functionHMforFields.get("fromDefaultFrequency") != null && !((AppPermission) functionHMforFields.get("fromDefaultFrequency")).isFunctionFlag()) {
                fromDefaultFrequency.setVisible(false);
            } else {
                fromDefaultFrequency.setVisible(true);
            }
            if (functionHMforFields.get(StringConstantUtils.FROM_PERIOD_DATE) != null && !((AppPermission) functionHMforFields.get(StringConstantUtils.FROM_PERIOD_DATE)).isFunctionFlag()) {
                fromPeriodDate.setVisible(false);
            } else {
                fromPeriodDate.setVisible(true);
            }
            if (functionHMforFields.get("fromDefaultPeriodDate") != null && !((AppPermission) functionHMforFields.get("fromDefaultPeriodDate")).isFunctionFlag()) {
                fromDefaultPeriodDate.setVisible(false);
            } else {
                fromDefaultPeriodDate.setVisible(true);
            }
            if (functionHMforFields.get("fromDate") != null && !((AppPermission) functionHMforFields.get("fromDate")).isFunctionFlag()) {
                fromDate.setVisible(false);
            } else {
                fromDate.setVisible(true);
            }
            if (functionHMforFields.get("fromDefaultDate") != null && !((AppPermission) functionHMforFields.get("fromDefaultDate")).isFunctionFlag()) {
                fromDefaultDate.setVisible(false);
            } else {
                fromDefaultDate.setVisible(true);
            }
            if (functionHMforOtherFields.get("toMode") != null && !((AppPermission) functionHMforOtherFields.get("toMode")).isFunctionFlag()) {
                toMode.setVisible(false);
            } else {
                toMode.setVisible(true);
            }
            if (functionHMforOtherFields.get("toDefaultMode") != null && !((AppPermission) functionHMforOtherFields.get("toDefaultMode")).isFunctionFlag()) {
                toDefaultMode.setVisible(false);
            } else {
                toDefaultMode.setVisible(true);
            }
            if (functionHMforOtherFields.get("toFrequency") != null && !((AppPermission) functionHMforOtherFields.get("toFrequency")).isFunctionFlag()) {
                toFrequency.setVisible(false);
            } else {
                toFrequency.setVisible(true);
            }
            if (functionHMforOtherFields.get("toDefaultFrequency") != null && !((AppPermission) functionHMforOtherFields.get("toDefaultFrequency")).isFunctionFlag()) {
                toDefaultFrequency.setVisible(false);
            } else {
                toDefaultFrequency.setVisible(true);
            }
            if (functionHMforOtherFields.get(StringConstantUtils.TO_PERIOD_DATE) != null && !((AppPermission) functionHMforOtherFields.get(StringConstantUtils.TO_PERIOD_DATE)).isFunctionFlag()) {
                toPeriodDate.setVisible(false);
            } else {
                toPeriodDate.setVisible(true);
            }
            if (functionHMforOtherFields.get("toDefaultPeriodDate") != null && !((AppPermission) functionHMforOtherFields.get("toDefaultPeriodDate")).isFunctionFlag()) {
                toDefaultPeriodDate.setVisible(false);
            } else {
                toDefaultPeriodDate.setVisible(true);
            }
            if (functionHMforOtherFields.get("toDate") != null && !((AppPermission) functionHMforOtherFields.get("toDate")).isFunctionFlag()) {
                toDate.setVisible(false);
            } else {
                toDate.setVisible(true);
            }
            if (functionHMforOtherFields.get("toDefaultDate") != null && !((AppPermission) functionHMforOtherFields.get("toDefaultDate")).isFunctionFlag()) {
                toDefaultDate.setVisible(false);
            } else {
                toDefaultDate.setVisible(true);
            }
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, StringConstantUtils.PERIOD_CONFIGURATION + com.stpl.app.adminconsole.util.ConstantsUtils.COMMA + StringConstantUtils.RESULTS_SCREEN, false);
            List<Object> resultList = periodConfigLogic.getFieldsForSecurity(StringConstantUtils.PERIOD_CONFIGURATION, StringConstantUtils.RESULTS_SCREEN);
            Object[] obj = forecastTableColumns;
            TableResultCustom tableResultCustom = commonSecurity.getTableColumnsPermission(resultList, obj, fieldIfpHM, CommonSecurityLogic.ADD);
            if (tableResultCustom.getObjResult().length > 0) {
                results.markAsDirty();
                results.setContainerDataSource(resultsBean);
                results.setVisibleColumns(tableResultCustom.getObjResult());
                results.setColumnHeaders(tableResultCustom.getObjResultHeader());
            } else {
                tableLayout.setVisible(false);
            }
        } catch (Exception e) {
            LOGGER.info(e);
        }

    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private ErrorfulFieldGroup getBinder() {
        LOGGER.debug("getBinder method started");
        forecastBinder.bindMemberFields(this);
        forecastBinder.setItemDataSource(new BeanItem<>(forecastDTO));
        forecastBinder.setBuffered(true);
        forecastBinder.setErrorDisplay(errorMsg);
        LOGGER.debug("getBinder method returns getBinder");
        return forecastBinder;
    }

    private void configureFieldPermission(final Map<String, AppPermission> functionHMforFields) {
        LOGGER.debug("Entering configurePermission");
        try {
            List<Object> resultList = periodConfigLogic.getFieldsForSecurity(StringConstantUtils.PERIOD_CONFIGURATION, "Landing Screen");
            commonSecurity.removeComponentOnPermission(resultList, controlLayout, functionHMforFields, CommonSecurityLogic.ADD);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending configurePermission");
    }

    private void configureFieldPermissionOtherFields(final Map<String, AppPermission> functionHMforOtherFields) {
        LOGGER.debug("Entering configurePermission");
        try {
            List<Object> resultList = periodConfigLogic.getFieldsForSecurity(StringConstantUtils.PERIOD_CONFIGURATION, "Landing Screen-From Period");
            commonSecurity.removeComponentOnPermission(resultList, controlLayout, functionHMforOtherFields, CommonSecurityLogic.ADD);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending configurePermission");
    }

    /**
     * (non-Javadoc).
     *
     * @param event the event
     * @see
     * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        return;
    }

    /**
     * Configure fields.
     */
    private void configureFields() throws SystemException {
        CommonUtils commonUtil = CommonUtils.getInstance();
//        PERIODCONFIG_MODE
//                PERIODCONFIG_FREQUENCY fromMode toMode  fromDefaultMode
//                        toDefaultMode
        commonUtil.setUserInfo();
        commonUtil.loadComboBox(modules, "PERIODCONFIG_MODULES", false);
        commonUtil.loadComboBox(fromMode, StringConstantUtils.PERIODCONFIG_MODE, false);
        commonUtil.loadComboBox(toMode, StringConstantUtils.PERIODCONFIG_MODE, false);
        commonUtil.loadComboBox(fromDefaultMode, StringConstantUtils.PERIODCONFIG_MODE, false);
        commonUtil.loadComboBox(toDefaultMode, StringConstantUtils.PERIODCONFIG_MODE, false);

        commonUtil.loadComboBox(fromFrequency, StringConstantUtils.PERIODCONFIG_FREQUENCY, false);
        commonUtil.loadComboBox(toFrequency, StringConstantUtils.PERIODCONFIG_FREQUENCY, false);
        commonUtil.loadComboBox(fromDefaultFrequency, StringConstantUtils.PERIODCONFIG_FREQUENCY, false);
        commonUtil.loadComboBox(toDefaultFrequency, StringConstantUtils.PERIODCONFIG_FREQUENCY, false);

        periodConfigLogic.getCompanyList(company);
        periodConfigLogic.getBusinessUnitList(businessUnit);
        periodConfigLogic.getTransactionList(businessProcess);
        selectionPanel.setSizeFull();

        periodView.addItem(StringConstantUtils.SINGLE);
        periodView.addItem("Multiple");
        periodView.select(StringConstantUtils.SINGLE);
        toPeriod.setEnabled(false);

        fromPeriodDate.addStyleName(StringConstantUtils.ALIGNCENTER);
        fromDefaultPeriodDate.addStyleName(StringConstantUtils.ALIGNCENTER);
        toPeriodDate.addStyleName(StringConstantUtils.ALIGNCENTER);
        toDefaultPeriodDate.addStyleName(StringConstantUtils.ALIGNCENTER);

        fromDate.addStyleName(StringConstantUtils.ALIGNCENTER);
        fromDefaultDate.addStyleName(StringConstantUtils.ALIGNCENTER);
        toDate.addStyleName(StringConstantUtils.ALIGNCENTER);
        toDefaultDate.addStyleName(StringConstantUtils.ALIGNCENTER);

        toTextField.addStyleName(StringConstantUtils.ALIGNRIGHT);
        fromTextField.addStyleName(StringConstantUtils.ALIGNRIGHT);
        defaultfromTextField.addStyleName(StringConstantUtils.ALIGNRIGHT);
        defaulttoTextField.addStyleName(StringConstantUtils.ALIGNRIGHT);
        results.setFilterGenerator(new PeriodConfigLogic());

        periodView.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * The value change
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                if (periodView.getValue().equals(StringConstantUtils.SINGLE)) {
                    resetToPeriod();
                    toPeriod.setEnabled(false);
                } else {
                    toPeriod.setEnabled(true);

                }

            }
        });

        periodConfig.setCaption("Period Configuration:");
        periodConfig.setSizeFull();

        fromMode.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * The value change
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                try {
                    if (fromMode.getValue() != null) {

                        if (getDescription(fromMode).equals("Auto")) {
                            fromPeriodGrid.replaceComponent(fromPeriodDate, fromTextField);
                            fromTextField.setValue("");
                            fromFrequency.setEnabled(true);
                            autoflag = true;
                        } else {
                            fromFrequency.setValue(null);
                            fromFrequency.setEnabled(false);
                            if (!fromPeriodGrid.getComponent(1, NumericConstants.TWO).equals(fromPeriodDate)) {
                                fromPeriodDate.setVisible(true);
                                fromPeriodGrid.replaceComponent(fromTextField, fromPeriodDate);
                            }
                            fromPeriodDate.setValue(null);
                            autoflag = false;

                        }
                        fromDate.setValue("");
                    } else {
                        fromFrequency.setValue(null);
                        fromFrequency.setEnabled(true);
                        if (autoflag) {
                            fromPeriodGrid.replaceComponent(fromTextField, fromPeriodDate);
                        }
                        fromPeriodDate.setValue(null);

                    }
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        });

        fromDefaultMode.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * The value change
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                if (fromDefaultMode.getValue() != null) {
                    if (getDescription(fromDefaultMode).equals("Auto")) {
                        fromPeriodGrid.replaceComponent(fromDefaultPeriodDate, defaultfromTextField);
                        defaultfromTextField.setValue("");
                        fromDefaultFrequency.setEnabled(true);
                        fromdefaultautoflag = true;
                    } else {
                        fromDefaultFrequency.setValue(null);
                        fromDefaultFrequency.setEnabled(false);
                        if (!fromPeriodGrid.getComponent(NumericConstants.THREE, NumericConstants.TWO).equals(fromDefaultPeriodDate)) {
                            fromPeriodGrid.replaceComponent(defaultfromTextField, fromDefaultPeriodDate);
                        }
                        fromDefaultPeriodDate.setValue(null);
                        fromdefaultautoflag = false;
                    }
                    fromDefaultDate.setValue("");
                } else {
                    fromDefaultFrequency.setValue(null);
                    fromDefaultFrequency.setEnabled(true);
                    if (fromdefaultautoflag) {
                        fromPeriodGrid.replaceComponent(defaultfromTextField, fromDefaultPeriodDate);
                    }
                    fromDefaultPeriodDate.setValue(null);
                }
            }
        });

        toMode.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * The value change
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                if (toMode.getValue() != null) {
                    if (getDescription(toMode).equals("Auto")) {
                        toPeriodGrid.replaceComponent(toPeriodDate, toTextField);
                        toTextField.setValue("");
                        toFrequency.setEnabled(true);
                        toModeautoflag = true;
                    } else {
                        toFrequency.setValue(null);
                        toFrequency.setEnabled(false);
                        if (!toPeriodGrid.getComponent(1, NumericConstants.TWO).equals(toPeriodDate)) {
                            toPeriodGrid.replaceComponent(toTextField, toPeriodDate);
                        }
                        toPeriodDate.setValue(null);

                        toModeautoflag = false;
                    }
                    toDate.setValue("");
                } else {
                    toFrequency.setValue(null);
                    toFrequency.setEnabled(true);
                    if (toModeautoflag) {
                        toPeriodGrid.replaceComponent(toTextField, toPeriodDate);
                    }
                    toPeriodDate.setValue(null);

                }
            }
        });

        toDefaultMode.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * The value change
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                if (toDefaultMode.getValue() != null) {
                    if (getDescription(toDefaultMode).equals("Auto")) {
                        toPeriodGrid.replaceComponent(toDefaultPeriodDate, defaulttoTextField);
                        defaulttoTextField.setValue("");
                        toDefaultFrequency.setEnabled(true);
                        toDefaultModeautoflag = true;
                    } else {
                        toDefaultFrequency.setValue(null);
                        toDefaultFrequency.setEnabled(false);
                        if (!toPeriodGrid.getComponent(NumericConstants.THREE, NumericConstants.TWO).equals(toDefaultPeriodDate)) {
                            toPeriodGrid.replaceComponent(defaulttoTextField, toDefaultPeriodDate);
                        }
                        toDefaultPeriodDate.setValue(null);

                        toDefaultModeautoflag = false;
                    }
                    toDefaultDate.setValue("");
                } else {
                    toDefaultFrequency.setValue(null);
                    toDefaultFrequency.setEnabled(true);
                    if (toDefaultModeautoflag) {
                        toPeriodGrid.replaceComponent(defaulttoTextField, toDefaultPeriodDate);
                    }
                    toDefaultPeriodDate.setValue(null);

                }
            }
        });

        fromPeriodDate.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                if (fromPeriodDate.getValue() != null) {
                    fromDate.setValue(df.format(new Date(fromPeriodDate.getValue().getYear(), fromPeriodDate.getValue().getMonth(), 01)));
                } else {
                    fromDate.setValue("");
                }
            }
        });
        fromTextField.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * The value change
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                if (fromTextField.getValue() != null && !ConstantsUtils.EMPTY.equals(fromTextField.getValue())) {
                    fromDate.setValue(df.format(getDate(Integer.parseInt(fromTextField.getValue()), true)));
                } else {
                    fromDate.setValue("");
                }
            }
        });

        fromDefaultPeriodDate.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                if (fromDefaultPeriodDate.getValue() != null) {
                    fromDefaultDate.setValue(df.format(new Date(fromDefaultPeriodDate.getValue().getYear(), fromDefaultPeriodDate.getValue().getMonth(), 01)));

                } else {
                    fromDefaultDate.setValue("");
                }
            }
        });

        defaultfromTextField.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * The value change
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                defaultfromTextField.setValue(defaultfromTextField.getValue().trim());
                if (defaultfromTextField.getValue() != null && !ConstantsUtils.EMPTY.equals(defaultfromTextField.getValue())) {
                    fromDefaultDate.setValue(df.format(getDate(Integer.parseInt(defaultfromTextField.getValue()), true)));
                } else {
                    fromDefaultDate.setValue("");
                }
            }
        });

        toPeriodDate.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                if (toPeriodDate.getValue() != null) {
                    toDate.setValue(df.format(getToDate(toPeriodDate.getValue())));
                } else {
                    toDate.setValue("");
                }
            }
        });

        toTextField.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * The value change
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                if (toTextField.getValue() != null && !ConstantsUtils.EMPTY.equals(toTextField.getValue())) {
                    toDate.setValue(df.format(getDate(Integer.parseInt(toTextField.getValue()), false)));
                } else {
                    toDate.setValue("");
                }

            }
        });

        toDefaultPeriodDate.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                if (toDefaultPeriodDate.getValue() != null) {
                    toDefaultDate.setValue(df.format(getToDate(toDefaultPeriodDate.getValue())));
                } else {
                    toDefaultDate.setValue("");
                }
            }
        });

        defaulttoTextField.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * The value change
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                if (defaulttoTextField.getValue() != null && !ConstantsUtils.EMPTY.equals(defaulttoTextField.getValue())) {

                    toDefaultDate.setValue(df.format(getDate(Integer.parseInt(defaulttoTextField.getValue()), false)));
                } else {
                    toDefaultDate.setValue("");
                }
            }
        });
        saveBtn.addClickListener(new Button.ClickListener() {
            /**
             * The button click
             */

            public void buttonClick(final Button.ClickEvent event) {

                try {
                    if (((!fromTextField.getValue().matches(ValidationUtils.NEGATIVE_INTEGER_VALIDATION)) && (!defaultfromTextField.getValue().matches(ValidationUtils.NEGATIVE_INTEGER_VALIDATION)))
                            || toTextField.getValue().isEmpty() ? false : ((!toTextField.getValue().matches(ValidationUtils.NUMERIC_VALIDATION_VAR)) && (!defaulttoTextField.getValue().matches(ValidationUtils.NUMERIC_VALIDATION_VAR)))) {
                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.SAVE_CRITERIA, "The value entered in the ‘Period’ field should be before the system date when in the ‘From’ or ‘Historical’ group boxes, and should be after the system date when in the ‘To’ group box.", ButtonId.OK);
                        return;
                    }
                    forecastBinder.commit();
                    if (modules.getValue() != null && businessProcess.getValue() != null && company.getValue() != null && businessUnit.getValue() != null
                            && checkNotNull(fromMode) && (getDescription(fromMode).equals("Auto") ? checkNotNull(fromFrequency) : true)
                            && (getDescription(fromMode).equals("Auto") ? (fromTextField.getValue() != null && !fromTextField.getValue().equals("")) : fromPeriodDate.getValue() != null)
                            && checkNotNull(fromDefaultMode) && (getDescription(fromDefaultMode).equals("Auto") ? checkNotNull(fromDefaultFrequency) : true)
                            && (getDescription(fromDefaultMode).equals("Auto") ? (defaultfromTextField.getValue() != null && !defaultfromTextField.getValue().equals("")) : fromDefaultPeriodDate.getValue() != null)
                            && (String.valueOf(periodView.getValue()).equals(StringConstantUtils.SINGLE) ? true
                            : checkNotNull(toMode) && (getDescription(toMode).equals("Auto") ? checkNotNull(toFrequency) : true)
                            && (getDescription(toMode).equals("Auto") ? (toTextField.getValue() != null && !toTextField.getValue().equals("")) : toPeriodDate.getValue() != null)
                            && checkNotNull(toDefaultMode) && (getDescription(toDefaultMode).equals("Auto") ? checkNotNull(toDefaultFrequency) : true)
                            && (getDescription(toDefaultMode).equals("Auto") ? (defaulttoTextField.getValue() != null && !defaulttoTextField.getValue().equals("")) : toDefaultPeriodDate.getValue() != null))) {

                        if (!validateFromDefaultdates()) {
                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.SAVE_CRITERIA, "The ‘From Period’ default date cannot be before the ‘From Period’ date.", ButtonId.OK);
                            return;
                        }
                        if (!validateFromperiodbeforeCurrent()) {
                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.SAVE_CRITERIA, "Please ensure that the value in the ‘Default Date’ field is within the ‘Date’ range.", ButtonId.OK);
                            return;
                        }
                        if (!String.valueOf(periodView.getValue()).equals(StringConstantUtils.SINGLE)) {
                            if (!validateToDefaultdates()) {
                                MessageBox.showPlain(Icon.INFO, ConstantsUtils.SAVE_CRITERIA, "The ‘To Period’ default date cannot be after the ‘To Period’ date.", ButtonId.OK);
                                return;
                            }
                            if (!validateDateRange()) {
                                MessageBox.showPlain(Icon.INFO, ConstantsUtils.SAVE_CRITERIA, "The ‘To Period’ date cannot be before the ‘From Period’ date.", ButtonId.OK);
                                return;
                            }
                            if (!validateToDefaultFromandTodates()) {
                                MessageBox.showPlain(Icon.INFO, ConstantsUtils.SAVE_CRITERIA, "The ‘To Period’ default date cannot be before the ‘From Period’ default date.", ButtonId.OK);
                                return;
                            }
                        }

                        if (!validateStartDate()) {

                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.SAVE_CRITERIA, "Current-3 years of history alone will be available.", ButtonId.OK);
                            return;

                        }

                        // Are you sure you want to reset the page to default values? 
                        new AbstractNotificationUtils() {
                            @Override
                            /**
                             * No method
                             */
                            public void noMethod() {
                                return;
                            }

                            @Override
                            /**
                             * Yes method
                             */
                            public void yesMethod() {
                                try {
                                    periodConfigDTO.setModulesId(getId(modules));
                                    periodConfigDTO.setBuscinessProcessTypeID(getId(businessProcess));
                                    periodConfigDTO.setCompanySid(getId(company));
                                    periodConfigDTO.setBuCompanySysId(getId(businessUnit));

                                    periodConfigDTO.setFromModeId(getId(fromMode));
                                    periodConfigDTO.setFromDefModeId(getId(fromDefaultMode));
                                    periodConfigDTO.setFromFrequencyId(checkNotNull(fromFrequency) ? getId(fromFrequency) : 0);
                                    periodConfigDTO.setFromDefFrequencyId(checkNotNull(fromDefaultFrequency) ? getId(fromDefaultFrequency) : 0);
                                    periodConfigDTO.setFromPeriod(fromTextField.getValue());
                                    periodConfigDTO.setFromPeriodDate(fromPeriodDate.getValue());
                                    periodConfigDTO.setFromPeriodDateValue(fromDate.getValue());
                                    periodConfigDTO.setFromDefPeriod(defaultfromTextField.getValue());
                                    periodConfigDTO.setFromDefPeriodDate(fromDefaultPeriodDate.getValue());
                                    periodConfigDTO.setFromDefPeriodDateValue(fromDefaultDate.getValue());

                                    periodConfigDTO.setPeriodViewName(periodConfigLogic.getPeriodViewMap().get(String.valueOf(periodView.getValue())));
                                    if (String.valueOf(periodView.getValue()).equalsIgnoreCase(("Multiple"))) {
                                        periodConfigDTO.setToModeId(checkNotNull(toMode) ? getId(toMode) : 0);
                                        periodConfigDTO.setToDefModeId(checkNotNull(toDefaultMode) ? getId(toDefaultMode) : 0);
                                        periodConfigDTO.setToFrequencyId(checkNotNull(toFrequency) ? getId(toFrequency) : 0);
                                        periodConfigDTO.setToDefFrequencyId(checkNotNull(toDefaultFrequency) ? getId(toDefaultFrequency) : 0);
                                        periodConfigDTO.setToPeriod(toTextField.getValue());
                                        periodConfigDTO.setToPeriodDate(toPeriodDate.getValue());
                                        periodConfigDTO.setToPeriodDateValue(toDate.getValue());
                                        periodConfigDTO.setToDefPeriod(defaulttoTextField.getValue());
                                        periodConfigDTO.setToDefPeriodDate(toDefaultPeriodDate.getValue());
                                        periodConfigDTO.setToDefPeriodDateValue(toDefaultDate.getValue());
                                    }
                                    periodConfigLogic.savePeriodConfig(periodConfigDTO);
                                    tableLogic.configureSearchData();
                                    CommonUIUtils.getMessageNotification(getDescription(businessProcess) + " business process Saved Successfully.");
                                } catch (Exception e) {
                                    LOGGER.error(e);
                                }
                            }
                        }.getConfirmationMessage(" Confirmation", " Are you sure you want to Save the Period Configuration? ");

                    } else {
                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.SAVE_CRITERIA, "Please ensure all mandatory fields are populated.", ButtonId.OK);
                        return;
                    }
                } catch (Exception ex) {

                    LOGGER.error(ex);
                }

            }
        });

        resetBtn.addClickListener(new Button.ClickListener() {
            /**
             * The button click
             */
            public void buttonClick(final Button.ClickEvent event) {

                // Are you sure you want to reset the page to default values? 
                new AbstractNotificationUtils() {
                    @Override
                    /**
                     * No method
                     */
                    public void noMethod() {
                        return;
                    }

                    @Override
                    /**
                     * Yes method
                     */
                    public void yesMethod() {
                        try {
                            resetBtnLogic();
                        } catch (Exception e) {
                            LOGGER.error(e);
                        }
                    }
                }.getConfirmationMessage(" Confirmation", "Are you sure you want to reset the page to default values? ");

            }
        });

        toTextField.addValidator(new RegexpValidator(ValidationUtils.NUMERIC_VALIDATION_VAR, "Can contains  Positive  Numbers and 0"));

        fromTextField.addValidator(new RegexpValidator(ValidationUtils.NEGATIVE_INTEGER_VALIDATION, "Can contains  Negative Numbers and 0"));

        defaultfromTextField.addValidator(new RegexpValidator(ValidationUtils.NEGATIVE_INTEGER_VALIDATION, "Can contains  Negative Numbers and 0"));

        defaulttoTextField.addValidator(new RegexpValidator(ValidationUtils.NUMERIC_VALIDATION_VAR, "Can contains  Positive  Numbers and 0"));

        modules.focus();

        fromPeriodDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        fromDefaultPeriodDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        toPeriodDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        toDefaultPeriodDate.setDateFormat(ConstantsUtils.DATE_FORMAT);

        excelExport.setDescription(ConstantsUtils.EXCEL_EXPORT);
        excelExport.setIcon(new ThemeResource("../../icons/excel.png"));
        excelExport.setStyleName("link");

        loadGrid();
//ColumnAlignment("month", ExtCustomTable.Align.LEFT)
        excelExport.addClickListener(new Button.ClickListener() {
            /**
             * Method is called when available excel export button is clicked
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In configureFields selectedResultsExcelExport.addClickListener started");
                try {
                    configureExcelResultTable();
                    loadExcelTable();
                    ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(excelTable), StringConstantUtils.PERIOD_CONFIGURATION, StringConstantUtils.PERIOD_CONFIGURATION, "Period Configuration.xls", false);
                    excel.export();
                    tableLayout.removeComponent(excelTable);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
                LOGGER.debug("In configureFields selectedResultsExcelExport.addClickListener Ended");
            }
        });

        final Date gtsDate = new CommonUtils().getCurrentGTSToDate(ConstantsUtils.EX_FACTORY_SALES);
        final Calendar gtsCal = Calendar.getInstance();
        gtsCal.setTime(gtsDate);

    }

    /**
     * Reset btn logic.
     */
    private void resetBtnLogic() {

        try {
            modules.setValue(null);
            businessProcess.setValue(null);
            company.setValue(null);
            businessUnit.setValue(null);
            if ("Auto".equals(String.valueOf(fromMode.getValue()))) {
                fromPeriodGrid.replaceComponent(fromTextField, fromPeriodDate);

            }
            if ("Auto".equals(String.valueOf(fromDefaultMode.getValue()))) {
                fromPeriodGrid.replaceComponent(defaultfromTextField, fromDefaultPeriodDate);
            }
            fromMode.setValue(null);
            fromDefaultMode.setValue(null);

            fromFrequency.setValue(null);
            fromDefaultFrequency.setValue(null);

            fromPeriodDate.setValue(null);
            fromDefaultPeriodDate.setValue(null);
            fromTextField.setValue(ConstantsUtils.EMPTY);
            defaultfromTextField.setValue(ConstantsUtils.EMPTY);

            fromDate.setValue(ConstantsUtils.EMPTY);
            fromDefaultDate.setValue(ConstantsUtils.EMPTY);
            periodView.select(StringConstantUtils.SINGLE);

            loadGrid();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    private void resetToPeriod() {
        if ("Auto".equals(String.valueOf(toMode.getValue()))) {
            toPeriodGrid.replaceComponent(toTextField, toPeriodDate);

        }
        if ("Auto".equals(String.valueOf(toDefaultMode.getValue()))) {
            toPeriodGrid.replaceComponent(defaulttoTextField, toDefaultPeriodDate);
        }
        toMode.setValue(null);
        toDefaultMode.setValue(null);

        toFrequency.setValue(null);
        toDefaultFrequency.setValue(null);

        toDefaultPeriodDate.setValue(null);
        toPeriodDate.setValue(null);
        toTextField.setValue(ConstantsUtils.EMPTY);
        defaulttoTextField.setValue(ConstantsUtils.EMPTY);
        toDate.setValue(ConstantsUtils.EMPTY);
        toDefaultDate.setValue(ConstantsUtils.EMPTY);

    }

    /**
     * TO load the grid
     */
    private void loadGrid() {
        try {
            tableLogic.configureSearchData();
            results.setFilterDecorator(new ExtDemoFilterDecorator());
            results.setFilterGenerator(new PeridConfigFilterGenerator());
            results.setImmediate(true);
            results.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
            results.setHeight("520px");
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
        Object[] objColumn = forecastTableColumns;
        for (Object objColumn1 : objColumn) {
            String value = objColumn1.toString();
            if (value.endsWith("Date") || value.contains("fromPeriod") || value.contains("toPeriod")) {
                results.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
            }
        }
        results.setEditable(true);
        results.setTableFieldFactory(new DefaultFieldFactory() {

            private static final long serialVersionUID = 1L;

            public Field<?> createField(final Container container,
                    final Object itemId, final Object propertyId,
                    final Component uiContext) {
                try {

                    if (String.valueOf(propertyId).endsWith("Date")) {
                        final PopupDateField date = new PopupDateField();
                        date.setDateFormat(ConstantsUtils.DATE_FORMAT);
                        date.setEnabled(false);
                        return date;
                    }

                } catch (Exception exception) {
                    LOGGER.error(exception);
                }
                return null;

            }
        });

        results.setSelectable(true);
        results.markAsDirty();

        results.setFilterBarVisible(true);
        results.setFilterDecorator(new ExtDemoFilterDecorator());
        results.setFilterGenerator(new PeridConfigFilterGenerator());
        results.setValidationVisible(false);
        results.addStyleName("filterbar");

    }

    public void setTableDefaultConfig() {
        try {

            Map<Object, Object[]> doubleHeaderMap = new HashMap<>();
            results.setVisibleColumns(forecastTableColumns);
            results.setColumnHeaders(forecastTableHeader);

            results.setDoubleHeaderVisible(true);
            results.setDoubleHeaderVisibleColumns(new Object[]{StringConstantUtils.GROUP1, StringConstantUtils.GROUP2, StringConstantUtils.GROUP3});
            results.setDoubleHeaderColumnHeaders(new String[]{"", "From Period", "To Period"});
            doubleHeaderMap.put(StringConstantUtils.GROUP1, new Object[]{StringConstantUtils.MODULE_NAME, StringConstantUtils.BUSCINESS_PROCESS_NAME, StringConstantUtils.COMPANY_NAME, StringConstantUtils.BUCINSESS_UNIT_NAME, StringConstantUtils.PERIOD_VIEW_NAME, StringConstantUtils.VERSION_NO, StringConstantUtils.CREATED_BY, StringConstantUtils.ACTIVE_FLAG});
            doubleHeaderMap.put(StringConstantUtils.GROUP2, new Object[]{StringConstantUtils.FROM_MODE_NAME, StringConstantUtils.FROM_FREQUENCY_NAME, StringConstantUtils.FROM_PERIOD_VALUE, StringConstantUtils.FROM_PERIOD_DATE, StringConstantUtils.FROM_DEF_MODE_NAME, StringConstantUtils.FROM_DEF_FREQUENCY_NAME, FROM_DEF_PERIOD_VALUE, StringConstantUtils.FROM_DEF_PERIOD_DATE});

            doubleHeaderMap.put(StringConstantUtils.GROUP3, new Object[]{StringConstantUtils.TO_MODE_NAME, TO_FREQUENCY_NAME, TO_PERIOD_VALUE, StringConstantUtils.TO_PERIOD_DATE, StringConstantUtils.TO_DEF_MODE_NAME, StringConstantUtils.TO_DEF_FREQUENCY_NAME, StringConstantUtils.TO_DEF_PERIOD_VALUE, StringConstantUtils.TO_DEF_PERIOD_DATE});

            results.setDoubleHeaderMap(doubleHeaderMap);
            results.setDoubleHeaderColumnAlignment(StringConstantUtils.GROUP1, ExtCustomTable.Align.CENTER);
            results.setDoubleHeaderColumnAlignment(StringConstantUtils.GROUP2, ExtCustomTable.Align.CENTER);
            results.setDoubleHeaderColumnAlignment(StringConstantUtils.GROUP3, ExtCustomTable.Align.CENTER);

            results.markAsDirtyRecursive();
            results.setImmediate(true);
            results.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
            results.setHeight("250px");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * To configure Excel Results Table
     */
    private void configureExcelResultTable() {
        Map<Object, Object[]> doubleHeaderMap = new HashMap<>();
        excelTableBean
                = new BeanItemContainer<>(PeriodConfigDTO.class
                );
        excelTable = new ExtFilterTable();

        tableLayout.addComponent(excelTable);

        excelTable.setVisible(
                false);
        excelTable.setContainerDataSource(excelTableBean);

        excelTable.setVisibleColumns(forecastTableColumns);

        excelTable.setColumnHeaders(forecastTableHeader);

        excelTable.setDoubleHeaderVisible(
                true);
        excelTable.setDoubleHeaderVisibleColumns(new Object[]{StringConstantUtils.GROUP1, StringConstantUtils.GROUP2, StringConstantUtils.GROUP3});
        excelTable.setDoubleHeaderColumnHeaders(new String[]{"",
            "From Period", "To Period"});
        doubleHeaderMap.put(StringConstantUtils.GROUP1, new Object[]{StringConstantUtils.MODULE_NAME, StringConstantUtils.BUSCINESS_PROCESS_NAME, StringConstantUtils.COMPANY_NAME, StringConstantUtils.BUCINSESS_UNIT_NAME, StringConstantUtils.PERIOD_VIEW_NAME, StringConstantUtils.VERSION_NO, StringConstantUtils.CREATED_BY, StringConstantUtils.ACTIVE_FLAG});
        doubleHeaderMap.put(StringConstantUtils.GROUP2, new Object[]{StringConstantUtils.FROM_MODE_NAME, StringConstantUtils.FROM_FREQUENCY_NAME, StringConstantUtils.FROM_PERIOD_VALUE, StringConstantUtils.FROM_PERIOD_DATE, StringConstantUtils.FROM_DEF_MODE_NAME, StringConstantUtils.FROM_DEF_FREQUENCY_NAME, FROM_DEF_PERIOD_VALUE, StringConstantUtils.FROM_DEF_PERIOD_DATE});

        doubleHeaderMap.put(StringConstantUtils.GROUP3, new Object[]{StringConstantUtils.TO_MODE_NAME, TO_FREQUENCY_NAME, TO_PERIOD_VALUE, StringConstantUtils.TO_PERIOD_DATE, StringConstantUtils.TO_DEF_MODE_NAME, StringConstantUtils.TO_DEF_FREQUENCY_NAME, StringConstantUtils.TO_DEF_PERIOD_VALUE, StringConstantUtils.TO_DEF_PERIOD_DATE});
        excelTable.setDoubleHeaderMap(doubleHeaderMap);

        excelTable.markAsDirtyRecursive();

    }

    /**
     * To load excel Table similar to Table in UI
     *
     * @param tableFieldLookUpDTO
     * @throws Exception
     */
    private void loadExcelTable() {
        excelTableBean.removeAllItems();
        if (results.size() != 0) {
            PeriodConfigLogic logic = new PeriodConfigLogic();
            int recordCount = (Integer) logic.searchForecast(0, 0, null, null, true);
            List<PeriodConfigDTO> resultList = (List<PeriodConfigDTO>) logic.searchForecast(0, recordCount, null, null, false);
            excelTableBean.addAll(resultList);
        }
    }

    private Date getDate(int inputPeriod, boolean isFrom) {
        int months = inputPeriod % NumericConstants.TWELVE;
        int year = inputPeriod / NumericConstants.TWELVE;
        Date date = new Date();
        date.setYear(date.getYear() + year);
        date.setMonth(date.getMonth() + months);
        if (isFrom) {
            date.setDate(01);
        } else {
            date.setMonth(date.getMonth() + 1);
            date.setDate(01);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            date = calendar.getTime();
        }

        return date;
    }

    private Date getToDate(Date date) {
        Date tempDate = new Date(date.getTime());
        tempDate.setDate(01);
        Calendar calendar = Calendar.getInstance();
        tempDate.setMonth(tempDate.getMonth() + 1);
        calendar.setTime(tempDate);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        tempDate = calendar.getTime();

        return tempDate;
    }

    public boolean checkNotNull(ComboBox field) {
        return field.getValue() != null;
    }

    public String getDescription(ComboBox field) {

        return ((HelperDTO) field.getValue()).getDescription();
    }

    public int getId(ComboBox field) {
        return ((HelperDTO) field.getValue()).getDescription().contains("Balance Summary Report") ? -((HelperDTO) field.getValue()).getId() : ((HelperDTO) field.getValue()).getId();
    }

    private boolean validateToDefaultdates() {
        Date temptoDate = new Date(toDate.getValue());
        Date temptoDefDate = new Date(toDefaultDate.getValue());
        if ((temptoDate.getTime() == temptoDefDate.getTime() || temptoDefDate.before(temptoDate))) {
            return true;

        } else {

            return false;

        }

    }

    public boolean validateDateRange() {
        String toValue = toDate.getValue();
        Date tempToDate = new Date();
        Date currentDate = new Date();
        currentDate.setDate(1);

        String fromValue = fromDate.getValue();
        Date tempFromDate = new Date(fromValue);
        if (!toValue.isEmpty()) {
            tempToDate = new Date(toValue);
        }

        if ((tempFromDate.before(currentDate) || (tempFromDate.getMonth() == currentDate.getMonth() && tempFromDate.getYear() == currentDate.getYear()))
                && ((tempFromDate.getMonth() == tempToDate.getMonth() && tempFromDate.getYear() == tempToDate.getYear()) || (tempFromDate.before(tempToDate)))) {
            return true;

        } else {
            return false;
        }

    }

    public boolean validateStartDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR) - NumericConstants.THREE, 1, 1);
        Date currentMinusThree = cal.getTime();
        String fromValue = fromDate.getValue();
        Date tempFromDate = new Date(fromValue);
        if (tempFromDate.before(currentMinusThree)) {
            return false;
        } else {
            return true;
        }

    }

    private boolean validateFromperiodbeforeCurrent() {
        Date tempfromDate = new Date(fromDate.getValue());
        Date tempfromDefDate = new Date(fromDefaultDate.getValue());
        Date current = new Date();
        Boolean t = (tempfromDate.before(current) || (current.getMonth() == tempfromDate.getMonth() && current.getYear() == tempfromDate.getYear())) && (tempfromDefDate.before(current) || (current.getMonth() == tempfromDefDate.getMonth() && current.getYear() == tempfromDefDate.getYear()));
        return t;
    }

    private boolean validateFromDefaultdates() {
        Date tempfromDate = new Date(fromDate.getValue());
        Date tempfromDefDate = new Date(fromDefaultDate.getValue());
        return (tempfromDate.getTime() == tempfromDefDate.getTime() || tempfromDefDate.after(tempfromDate));
    }

    private boolean validateToDefaultFromandTodates() {
        Date tempfromDefDate = new Date(fromDefaultDate.getValue());
        Date temptoDefDate = new Date(toDefaultDate.getValue());
        if ((tempfromDefDate.getTime() == temptoDefDate.getTime() || tempfromDefDate.before(temptoDefDate))) {
            return true;
        } else {
            return false;

        }

    }

}
