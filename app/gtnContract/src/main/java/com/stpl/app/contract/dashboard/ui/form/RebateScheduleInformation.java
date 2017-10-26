/**
 *
 */
package com.stpl.app.contract.dashboard.ui.form;

import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.common.util.HelperListUtil;
import com.stpl.app.contract.contractheader.logic.ContractHeaderLogic;
import com.stpl.app.contract.dashboard.dto.NetSalesRuleLookupDto;
import com.stpl.app.contract.dashboard.dto.RuleDTO;
import com.stpl.app.contract.dashboard.ui.lookup.ParentLookUp;
import com.stpl.app.contract.dashboard.ui.lookup.ParentLookUP2;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.customtextfield.CustomTextField;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.app.contract.dashboard.dto.TempRebateDTO;
import com.stpl.app.contract.dashboard.ui.lookup.NetSalesRuleLookup;
import com.stpl.app.contract.dashboard.util.ExcelExportUtil;
import com.stpl.app.contract.global.dto.RsItemDetailsDTO;
import com.stpl.app.contract.global.util.CommonUtils;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.CommonUIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.UISecurityUtil;
import com.stpl.ifs.util.ValidationUtils;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.lang.reflect.InvocationTargetException;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

/**
 * @author sibi
 *
 */
public class RebateScheduleInformation extends CustomComponent {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final static Logger LOGGER = Logger.getLogger(RebateScheduleInformation.class);

    /**
     * The rebate schedule system id.
     */
    public final TextField rebateScheduleSystemId = new TextField();

    @UiField("cssLayout")
    private CssLayout cssLayout;

    @UiField("cssLayout1")
    private CssLayout cssLayout1;

    @UiField("cssLayout2")
    private CssLayout cssLayout2;
    
    @UiField("rebateScheduleIdLb")
    private Label rebateScheduleIdLb;

    @UiField("rebateScheduleNoLb")
    private Label rebateScheduleNoLb;

    @UiField("rebateScheduleNameLb")
    private Label rebateScheduleNameLb;

    @UiField("rebateScheduleStatusLb")
    private Label rebateScheduleStatusLb;

    @UiField("rebateScheduleTypeLb")
    private Label rebateScheduleTypeLb;

    @UiField("rebateProgramTypeLb")
    private Label rebateProgramTypeLb;

    @UiField("rebateScheduleCategoryLb")
    private Label rebateScheduleCategoryLb;

    @UiField("tradeClassLb")
    private Label tradeClassLb;

    @UiField("rebateScheduleTransRefNoLb")
    private Label rebateScheduleTransRefNoLb;

    @UiField("rebateScheduleTransRefNameLb")
    private Label rebateScheduleTransRefNameLb;

    @UiField("rebateScheduleAliasLb")
    private Label rebateScheduleAliasLb;

    @UiField("rebateScheduleDesignationLb")
    private Label rebateScheduleDesignationLb;

    @UiField("parentRebateScheduleIdLb")
    private Label parentRebateScheduleIdLb;

    @UiField("parentRebateScheduleNameLb")
    private Label parentRebateScheduleNameLb;

    @UiField("interestBearingBasisLb")
    private Label interestBearingBasisLb;

    @UiField("validationProfileLb")
    private Label validationProfileLb;

    @UiField("rebatePlanLevelLb")
    private Label rebatePlanLevelLb;

    @UiField("paymentMethodLb")
    private Label paymentMethodLb;

    @UiField("rebateFrequencyLb")
    private Label rebateFrequencyLb;

    @UiField("paymentTermsLb")
    private Label paymentTermsLb;

    @UiField("calendarLb")
    private Label calendarLb;

    @UiField("paymentGracePeriodLb")
    private Label paymentGracePeriodLb;

    @UiField("udc1Lb")
    private Label udc1Lb;

    @UiField("udc2Lb")
    private Label udc2Lb;

    @UiField("udc3Lb")
    private Label udc3Lb;

    @UiField("udc4Lb")
    private Label udc4Lb;

    @UiField("udc5Lb")
    private Label udc5Lb;

    @UiField("udc6Lb")
    private Label udc6Lb;
    
    @UiField("rebateScheduleId")
    private TextField rebateScheduleId;

    @UiField("rebateScheduleNo")
    private TextField rebateScheduleNo;

    @UiField("rebateScheduleName")
    private TextField rebateScheduleName;

    @UiField("rebateScheduleStatus")
    private CustomComboBox rebateScheduleStatus;

    @UiField("rebateScheduleType")
    private CustomComboBox rebateScheduleType;

    @UiField("rebateProgramType")
    private CustomComboBox rebateProgramType;

    @UiField("rebateScheduleCategory")
    private CustomComboBox rebateScheduleCategory;

    @UiField("tradeClass")
    private CustomComboBox tradeClass;

    @UiField("rebateScheduleTransRefNo")
    private CustomTextField rebateScheduleTransRefNo;

    @UiField("rebateScheduleTransRefName")
    private CustomTextField rebateScheduleTransRefName;

    @UiField("rebateScheduleAlias")
    private CustomTextField rebateScheduleAlias;

    @UiField("rebateScheduleDesignation")
    private CustomComboBox rebateScheduleDesignation;

    @UiField("parentRebateScheduleId")
    private CustomTextField parentRebateScheduleId;

    @UiField("parentRsName")
    private CustomTextField parentRebateScheduleName;

    @UiField("interestBearingBasisInfo")
    private CustomComboBox interestBearingBasis;

    @UiField("itemRebateStartDate")
    private PopupDateField itemRebateStartDate;

    @UiField("itemRebateEndDate")
    private PopupDateField itemRebateEndDate;

    @UiField("paymentMethod")
    private CustomComboBox paymentMethod;

    @UiField("rebateFrequency")
    private CustomComboBox rebateFrequency;

    @UiField("paymentFrequency")
    private CustomComboBox paymentFrequency;

    @UiField("paymentTerms")
    private CustomComboBox paymentTerms;

    @UiField("calendar")
    private CustomComboBox calendar;

    @UiField("paymentGracePeriod")
    private CustomTextField paymentGracePeriod;

    @UiField("udc1")
    private CustomComboBox udc1;

    @UiField("udc2")
    private CustomComboBox udc2;

    @UiField("udc3")
    private CustomComboBox udc3;

    @UiField("udc4")
    private CustomComboBox udc4;

    @UiField("udc5")
    private CustomComboBox udc5;

    @UiField("udc6")
    private CustomComboBox udc6;

    @UiField("calculationType")
    private CustomComboBox calculationType;

    @UiField("calculationLevel")
    private CustomComboBox calculationLevel;

    @UiField("evaluationRuleType")
    private CustomComboBox evaluationRuleType;

    @UiField("evaluationRuleLevel")
    private CustomComboBox evaluationRuleLevel;

    @UiField("calculationRuleLevel")
    private CustomComboBox calculationRuleLevel;

    @UiField("evaluationRuleAssociation")
    private CustomTextField evaluationRuleAssociation;

    @UiField("calculationRule")
    private CustomTextField calculationRule;
    /**
     * View Option group
     */
    @UiField("view")
    private OptionGroup view;
    /**
     * View Label
     */
    @UiField("viewLB")
    private Label viewLB;
    /**
     * Level option group
     */
    @UiField("level")
    protected OptionGroup level;

    @UiField("deductionInclusion")
    private ComboBox deductionInclusion;

    @UiField("rebateInfo")
    private VerticalLayout rebateInfo;

    @UiField("processOption")
    private VerticalLayout processOption;

    @UiField("rebateSetup")
    private Panel rebateSetupLayout;

    @UiField("paymentLevel")
    private ComboBox paymentLevel;

    @UiField("interestBearingIndicator")
    private ComboBox interestBearingIndicator;

    @UiField("interestBearingIndicatorddlb")
    private ComboBox interestBearingIndicatorddlb;

    @UiField("interestBearingBasisInfo")
    private ComboBox interestBearingBasisInfo;

    @UiField("rebateRuleType")
    private ComboBox rebateRuleType;

    @UiField("validationProfile")
    private ComboBox validationProfile;

    @UiField("rebateProcessingType")
    private ComboBox rebateProcessingType;

    @UiField("interestBearingBasis")
    private ComboBox interestBearingBasisDDLB;

    @UiField("availableRulesTable")
    private ExtFilterTable availableRulesTable;

    @UiField("selectedRulesTable")
    private ExtFilterTable selectedRulesTable;

    @UiField("moveRight")
    private Button moveRight;

    @UiField("moveLeft")
    private Button moveLeft;

    @UiField("moveAll")
    private Button moveAll;

    @UiField("removeAll")
    private Button removeAll;

    @UiField("verticalButtonLayout")
    private VerticalLayout verticalButtonLayout;

    @UiField("contractHeaderTable")
    private Panel contractHeaderTable;

    @UiField("hlayout2")
    private HorizontalLayout hlayout2;

    private BeanItemContainer<RuleDTO> availableBean = new BeanItemContainer<RuleDTO>(RuleDTO.class);

    Object[] visibleColumns = {"ruleNo", "ruleName", Constants.RULE_VERSION};
    String[] headers = {"Rule No", "Rule Name", Constants.VERSION};

    @UiField("table")
    private ExtPagedFilterTable table;

    private BeanItemContainer<RsItemDetailsDTO> container = new BeanItemContainer<RsItemDetailsDTO>(RsItemDetailsDTO.class);

    ParentLookUP2 lookUp = null;
    ParentLookUp parentLookUp = null;

    CommonUIUtils commonUIUtils = new CommonUIUtils();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    /**
     * The rs item details dto.
     */
    private RsItemDetailsDTO rsItemDetailsDTO = new RsItemDetailsDTO();

    /**
     * The contract master binder.
     */
    private CustomFieldGroup contractBinder;

    /**
     * The rs details results bean.
     */
    private BeanItemContainer<TempRebateDTO> rsDetailsResultsBean;

    /**
     * The rebate binder.
     */
    private CustomFieldGroup rebateBinder;

    /**
     * The Rebate Plan Level Change Flag.
     */
    private boolean rpLevelChange;

    /**
     * The rebate plan level id.
     */

    final boolean isEditable;
    /**
     * The common util.
     */
    final ContractHeaderLogic contractLogic = new ContractHeaderLogic();
    private CommonUtil commonUtil = CommonUtil.getInstance();
    final StplSecurity stplSecurity = new StplSecurity();
    final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));

    boolean valueChange = false;

    SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
    Object[] dates = new Object[NumericConstants.TWO];
    SessionDTO sessionDTO;
    HelperListUtil helperList = HelperListUtil.getInstance();
    private TextField calculationSystemId = new TextField();
    private TextField evaluationSystemId = new TextField();
    RebateSetup rebateSetup = null;
    
    @UiField("excelBtn")
    private Button excelExport;

    private Object[] HISTORY_COLUMN = {"rebateScheduleId", "rebateScheduleNo", "rebateScheduleName", "rebateScheduleStatus", "rebateScheduleType", "rebateProgramType", "rebateScheduleCategory", "tradeClass",
        "rebateScheduleDesignation", "rebateScheduleAlias", "udc1", "udc4", "parentRebateScheduleId", "rebateScheduleTransRefNo", "udc2", "udc5", "parentRebateScheduleName",
        "rebateScheduleTransRefName", "udc3", "udc6", "deductionInclusion", "calendar", "rebateFrequency", "paymentLevel", "paymentFrequency", "paymentGracePeriod",
        "paymentTerms", "paymentMethod", "interestBearingBasis", "evaluationRuleLevel", "evaluationRuleType", "evaluationRuleAssociation", "interestBearingIndicator",
        "calculationRuleLevel", "calculationType", "calculationRule", "calculationLevel", "modifiedDate"};

    private Object[] PROCESSING_COLUMN = {"rebateProcessingType", "validationProfile", "interestBearingIndicator", "interestBearingBasis", "ruleNo", "ruleName", Constants.RULE_VERSION};


    final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * The Constructor.
     *
     * @param rebateScheduleMaster the rebate schedule master
     * @param rebateBinder the rebate binder
     * @param rsDetailsResultsBean the rs details results bean
     */
    public RebateScheduleInformation(final RsItemDetailsDTO rsItemDetailsDTO, final CustomFieldGroup rebateBinder, final BeanItemContainer<TempRebateDTO> rsDetailsResultsBean,
            final CustomFieldGroup contractBinder, final boolean isEditable, final SessionDTO sessionDTO) throws SystemException, PortalException {
        super();
        LOGGER.debug("Entering RebateScheduleAddForm");
        this.rsItemDetailsDTO = rsItemDetailsDTO;
        this.contractBinder = contractBinder;
        this.rsDetailsResultsBean = rsDetailsResultsBean;
        this.rebateBinder = rebateBinder;
        this.isEditable = isEditable;
        this.sessionDTO = sessionDTO;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-dashboard/rebate-schedule-information.xml"), this));
        init();
        if (rebateSetup == null) {
            LOGGER.debug("Initialising RebateSetup");
            try {
                rebateSetup = new RebateSetup(rsItemDetailsDTO, rebateBinder, this.rsDetailsResultsBean, this.contractBinder, isEditable, this.sessionDTO);
            } catch (Exception ex) {
              LOGGER.error(ex);
            }
            LOGGER.debug("Entering RebateScheduleAddForm");
        }
        rebateSetupLayout.setContent(rebateSetup);
        LOGGER.debug("End of RebateScheduleAddForm");
    }

    /**
     * Inits the.
     */
    public void init() {
        LOGGER.debug("Entering init method");
        try {
            addResponsiveTab();
            configureFields();
            configureProcessingOption();
            configureBinder();
            configureTable();
            valueChange = true;
        } catch (Exception ex) {
               LOGGER.error(ex);
        }
        LOGGER.debug("End of init method");
    }

    public void loadDates(Date startDate, Date endDate){
        dates[0] = startDate;
        dates[1] = endDate;
        itemRebateStartDate.validate();
        itemRebateEndDate.validate();
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private void configureBinder() {
        LOGGER.debug("Entering getBinder method");
        rebateBinder.bindMemberFields(this);
        rebateBinder.setItemDataSource(new BeanItem<RsItemDetailsDTO>(rsItemDetailsDTO));
        rebateBinder.setBuffered(true);
        rebateScheduleTransRefNo.setValue(Constants.NULL.equalsIgnoreCase(rsItemDetailsDTO.getRebateScheduleTransRefNo()) ? StringUtils.EMPTY : rsItemDetailsDTO.getRebateScheduleTransRefNo());
        LOGGER.debug("End of getBinder method");
    }

    /**
     * Table for Historical data
     */
    private void configureTable() {
        table.setVisible(true);
        table.markAsDirty();
        table.setContainerDataSource(container);
        table.setPageLength(NumericConstants.SEVEN);
        table.setImmediate(true);
        table.setReadOnly(Boolean.TRUE);
        table.setFilterBarVisible(Boolean.TRUE);
        table.setSizeFull();
    }

    private void addResponsiveTab() throws PortalException, SystemException {
        final Map<String, AppPermission> contractDashboard = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + Constants.COMMA + Constants.RS_INFORMATION, false);
        List<Object> resultList = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, Constants.RS_INFORMATION);
        commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, contractDashboard, Constants.EDIT);
        LOGGER.debug("Entering configurePermission");
        try {
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, contractDashboard, Constants.EDIT);
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout1, contractDashboard, Constants.EDIT);
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout2, contractDashboard, Constants.EDIT);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Configuring process layouts
     */
    private void configureProcessingOption() throws PortalException, SystemException {
        final StplSecurity stplSecurity = new StplSecurity();
        final Map<String, AppPermission> contractDashboard = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + Constants.COMMA + Constants.RS_INFORMATION, false);
        List<Object> resultList = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, Constants.RS_INFORMATION);
        Object[] obj = visibleColumns;
        TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, contractDashboard, Constants.EDIT);
        if (tableResultCustom.getObjResult().length == 0) {
            availableRulesTable.setVisible(false);
            selectedRulesTable.setVisible(false);
        }
        availableRulesTable.setHeight("150px");
        availableRulesTable.setCaption("Available Rules");
        availableRulesTable.setContainerDataSource(availableBean);
        availableRulesTable.setVisibleColumns(tableResultCustom.getObjResult());
        availableRulesTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        availableRulesTable.setWidth("350px");
        moveRight.setWidth(Constants.WIDTH);
        moveLeft.setWidth(Constants.WIDTH);
        moveAll.setWidth(Constants.WIDTH);
        removeAll.setWidth(Constants.WIDTH);
        verticalButtonLayout.setComponentAlignment(moveRight, Alignment.MIDDLE_CENTER);
        verticalButtonLayout.setComponentAlignment(moveLeft, Alignment.MIDDLE_CENTER);
        verticalButtonLayout.setComponentAlignment(moveAll, Alignment.MIDDLE_CENTER);
        verticalButtonLayout.setComponentAlignment(removeAll, Alignment.MIDDLE_CENTER);
        verticalButtonLayout.addStyleName("NavButtonsForIFP");
        verticalButtonLayout.addStyleName("dataTransferGridForIFP");

        selectedRulesTable.setContainerDataSource(availableBean);
        selectedRulesTable.setVisibleColumns(tableResultCustom.getObjResult());
        selectedRulesTable.setColumnHeaders(tableResultCustom.getObjResultHeader());

        selectedRulesTable.setHeight("150px");
        selectedRulesTable.setWidth("350px");
        selectedRulesTable.setCaption("Selected Rules");
        hlayout2.setComponentAlignment(verticalButtonLayout, Alignment.MIDDLE_CENTER);

        availableRulesTable.setColumnHeader(Constants.RULE_VERSION, Constants.VERSION);
        selectedRulesTable.setColumnHeader(Constants.RULE_VERSION, Constants.VERSION);

        moveRight.setCaption(">");
        moveLeft.setCaption("<");
        moveAll.setCaption(">>");
        removeAll.setCaption("<<");
    }

    /**
     * Configure fields.
     */
    public void configureFields() throws SystemException {
        LOGGER.debug("Entering configureFields method");
        try {
            final Map<String, AppPermission> contractDashboard = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + Constants.COMMA + "Rebate History", false);
            List<Object> resultList = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, "Rebate History");
            Object[] obj = HISTORY_COLUMN;
            final TableResultCustom tableResultCustomHistory = commonSecurityLogic.getTableColumnsPermission(resultList, obj, contractDashboard, Constants.EDIT);
            obj = PROCESSING_COLUMN;
            final TableResultCustom tableResultCustomProcessing = commonSecurityLogic.getTableColumnsPermission(resultList, obj, contractDashboard, Constants.EDIT);

            rebateInfo.setVisible(Boolean.TRUE);
            processOption.setVisible(Boolean.FALSE);
            rebateSetupLayout.setVisible(Boolean.FALSE);
            contractHeaderTable.setVisible(Boolean.FALSE);

            view.addItems(Constants.HISTORY, Constants.CURRENT, Constants.PENDING);
            view.select(Constants.CURRENT);

            level.addItems(Constants.HEADER, Constants.DETAILS, Constants.PROCESSING_OPTION);
            level.select(Constants.HEADER);
            btnExportLogic();
            excelExport.setVisible(false);
            view.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(ValueChangeEvent event) {
                    excelExport.setVisible(true);
                    if (Constants.HISTORY.equals(view.getValue().toString())) {
                        if (Constants.HEADER.equals(level.getValue().toString())) {
                            rebateInfo.setVisible(Boolean.FALSE);
                            processOption.setVisible(Boolean.FALSE);
                            contractHeaderTable.setVisible(Boolean.TRUE);
                            table.setVisibleColumns(tableResultCustomHistory.getObjResult());
                            table.setColumnHeaders(tableResultCustomHistory.getObjResultHeader());
                        } else if (Constants.PROCESSING_OPTION.equals(level.getValue().toString())) {
                            rebateInfo.setVisible(Boolean.FALSE);
                            processOption.setVisible(Boolean.FALSE);
                            contractHeaderTable.setVisible(Boolean.TRUE);
                            table.setVisibleColumns(tableResultCustomProcessing.getObjResult());
                            table.setColumnHeaders(tableResultCustomProcessing.getObjResultHeader());
                        }
                    } else if (Constants.CURRENT.equals(view.getValue().toString()) || Constants.PENDING.equals(view.getValue().toString())) {
                        if (Constants.HEADER.equals(level.getValue().toString())) {
                            rebateInfo.setVisible(Boolean.TRUE);
                            processOption.setVisible(Boolean.FALSE);
                            contractHeaderTable.setVisible(Boolean.FALSE);
                        } else if (Constants.PROCESSING_OPTION.equals(level.getValue().toString())) {
                            rebateInfo.setVisible(Boolean.FALSE);
                            processOption.setVisible(Boolean.TRUE);
                            contractHeaderTable.setVisible(Boolean.FALSE);
                        }
                    }
                    if ((Constants.HEADER.equals(level.getValue().toString()) || Constants.PROCESSING_OPTION.equals(level.getValue().toString())) && !String.valueOf(view.getValue()).equals("History")) {
                        excelExport.setVisible(false);
                    }
                }
            });

            level.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(ValueChangeEvent event) {
                    excelExport.setVisible(true);
                    if (Constants.HEADER.equals(level.getValue().toString())) {
                        view.setVisible(Boolean.TRUE);
                        viewLB.setVisible(Boolean.TRUE);
                        view.select(Constants.CURRENT);
                        rebateInfo.setVisible(Boolean.TRUE);
                        processOption.setVisible(Boolean.FALSE);
                        rebateSetupLayout.setVisible(Boolean.FALSE);

                    } else if (Constants.DETAILS.equals(level.getValue().toString())) {
                        view.setVisible(Boolean.FALSE);
                        viewLB.setVisible(Boolean.FALSE);
                        rebateInfo.setVisible(Boolean.FALSE);
                        processOption.setVisible(Boolean.FALSE);
                        rebateSetupLayout.setVisible(Boolean.TRUE);
                        contractHeaderTable.setVisible(Boolean.FALSE);
                        loadRebateSetupTab();

                    } else if (Constants.PROCESSING_OPTION.equals(level.getValue().toString())) {
                        view.setVisible(Boolean.TRUE);
                        viewLB.setVisible(Boolean.TRUE);
                        view.select(Constants.CURRENT);
                        rebateInfo.setVisible(Boolean.FALSE);
                        processOption.setVisible(Boolean.TRUE);
                        rebateSetupLayout.setVisible(Boolean.FALSE);

                    }
                     if ((Constants.HEADER.equals(level.getValue().toString()) || Constants.PROCESSING_OPTION.equals(level.getValue().toString())) && !String.valueOf(view.getValue()).equals("History")) {
                        excelExport.setVisible(false);
                    }
                }
            });

            parentRebateScheduleName.setImmediate(true);
            parentRebateScheduleName.setReadOnly(true);

            rebateScheduleId.setImmediate(true);
            rebateScheduleId.setValidationVisible(true);
            rebateScheduleId.setRequired(true);
            rebateScheduleId.setRequiredError("Rebate Schedule ID should be entered on Rebate tab ");
            rebateScheduleId.addValidator(new StringLengthValidator(" Rebate Schedule ID should be less than 38 Characters", 0, NumericConstants.THIRTY_EIGHT, true));
            rebateScheduleId.setDescription(rebateScheduleId.getValue());
            rebateScheduleId.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used to click the text field and its listener.
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final ValueChangeEvent event) {

                    rebateScheduleId.setDescription(rebateScheduleId.getValue());
                }
            });
            rebateScheduleId.focus();
            rebateScheduleNo.setImmediate(true);
            rebateScheduleNo.setValidationVisible(true);
            rebateScheduleNo.setRequired(true);
            rebateScheduleNo.setRequiredError("Rebate Schedule No should be entered on Rebate Schedule Information tab");
            rebateScheduleNo.addValidator(new StringLengthValidator(" Rebate Schedule No should be less than 50 Characters", 0, NumericConstants.FIFTY, true));
            rebateScheduleNo.setDescription(rebateScheduleNo.getValue());
            rebateScheduleNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used to click text field and its listener.
                 */
                public void valueChange(final ValueChangeEvent event) {

                    rebateScheduleNo.setDescription(rebateScheduleNo.getValue());
                }
            });

            rebateScheduleName.setImmediate(true);
            rebateScheduleName.setValidationVisible(true);
            rebateScheduleName.setRequired(true);
            rebateScheduleName.setRequiredError("Rebate Schedule Name should be entered on Rebate tab");
            rebateScheduleName.addValidator(new StringLengthValidator("Rebate Schedule Name should be less than 100 Characters", 0, NumericConstants.HUNDRED, true));
            rebateScheduleName.setDescription(rebateScheduleName.getValue());
            rebateScheduleName.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used to click the text field and its listener.
                 */
                public void valueChange(final ValueChangeEvent event) {

                    rebateScheduleName.setDescription(rebateScheduleName.getValue());
                }
            });

            commonUtil.loadComboBox(paymentLevel, "PAYMENT_LEVEL", false);
            commonUtil.loadComboBox(interestBearingIndicator, "INTEREST_BEARING_INDICATOR", false);
            commonUtil.loadComboBox(interestBearingIndicatorddlb, "INTEREST_BEARING_INDICATOR", false);
            commonUtil.loadComboBox(interestBearingBasisInfo, "INTEREST_BEARING_BASIS", false);
            commonUtil.loadComboBox(rebateRuleType, "REBATE_RULE_TYPE", false);
            commonUtil.loadComboBox(deductionInclusion, "LOCKED_STATUS", false);
            commonUtil.loadComboBox(validationProfile, "RS_VALIDATION_PROFILE", false);
            commonUtil.loadComboBox(interestBearingBasisDDLB, "INTEREST_BEARING_BASIS", false);
            commonUtil.loadComboBox(rebateProcessingType, "REBATE_PROCESSING_TYPE", false);

            commonUtil.loadComboBox(rebateScheduleStatus, CommonUtils.STATUS, false);
            rebateScheduleStatus.setRequired(true);
            rebateScheduleStatus.setRequiredError("Rebate Schedule Status should be selected on Rebate tab");

            commonUtil.loadComboBox(rebateScheduleType, CommonUtils.RS_TYPE, false);
            rebateScheduleType.setRequired(true);
            rebateScheduleType.setRequiredError("Rebate Schedule Type should be selected on Rebate tab");

            commonUtil.loadComboBox(rebateProgramType, CommonUtils.REBATE_PROGRAM_TYPE, false);
            rebateProgramType.setRequired(true);
            rebateProgramType.setRequiredError("Rebate Program Type should be selected on Rebate tab");

            commonUtil.loadComboBox(paymentMethod, CommonUtils.PAYMENT_METHOD, false);
            paymentMethod.setRequired(true);
            paymentMethod.setRequiredError("Payment Method should be selected on Rebate tab");

            commonUtil.loadComboBox(rebateFrequency, CommonUtils.REBATE_FREQUENCY, false);

            commonUtil.loadComboBox(paymentFrequency, CommonUtils.PAYMENT_FREQUENCY, false);
            paymentFrequency.setRequired(true);
            paymentFrequency.setRequiredError("Payment Frequency should be selected on Rebate tab");

            commonUtil.loadComboBox(paymentTerms, CommonUtils.PAYMENT_TERMS, false);
            try {

                commonUtil.loadComboBox(calendar, CommonUtils.CALENDAR, false);
                calendar.setRequired(true);
                calendar.setRequiredError("Calendar should be selected on Rebate tab");
            } catch (Exception e) {
                 LOGGER.error(e);
            }
            paymentGracePeriod.setImmediate(true);
            paymentGracePeriod.setValidationVisible(true);
            paymentGracePeriod.addValidator(new RegexpValidator(ValidationUtils.numericValidation, ValidationUtils.numericValidationMessage));
            paymentGracePeriod.addValidator(new StringLengthValidator("Rebate Schedule Reference Name should be less than 10 Characters", 0, NumericConstants.TEN, true));
            paymentGracePeriod.setDescription(paymentGracePeriod.getValue());
            paymentGracePeriod.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used to click the text field and its listener.
                 */
                public void valueChange(final ValueChangeEvent event) {

                    paymentGracePeriod.setDescription(paymentGracePeriod.getValue());
                }
            });

            rebateScheduleAlias.addValidator(new StringLengthValidator("Rebate Schedule Alias length should be less than 50 characters", 0, NumericConstants.FIFTY, true));
            rebateScheduleAlias.addValidator(
                    new RegexpValidator("([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*", "Rebate Schedule Alias Allowed Special characters are @,*,#,.,$,&,_,-"));
            rebateScheduleAlias.setImmediate(true);
            rebateScheduleAlias.setValidationVisible(true);
            rebateScheduleAlias.setDescription(rebateScheduleAlias.getValue());

            rebateScheduleAlias.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used to set description name by using value change
                 * listener for ContractID
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final ValueChangeEvent event) {
                    LOGGER.debug("valueChange(ValueChangeEvent event) rebateScheduleAlias=" + rebateScheduleAlias.getValue());

                    rebateScheduleAlias.setDescription(rebateScheduleAlias.getValue());
                }
            });

            itemRebateStartDate.setValidationVisible(true);
            itemRebateStartDate.setImmediate(true);
            itemRebateStartDate.addValidator(new DateValidator("Start date should be before End date in Rebate Tab","StartDate"));
            itemRebateStartDate.setRequired(true);
            itemRebateStartDate.setRequiredError("Start Date should be selected on Rebate Schedule Information tab");
            itemRebateStartDate.setDateFormat(Constants.MM_DD_YYYY);
            attachListeners(itemRebateStartDate, "START_DATE");

            itemRebateEndDate.setValidationVisible(true);
            itemRebateEndDate.setImmediate(true);
            itemRebateEndDate.addValidator(new DateValidator("End date should be greater than start date in Rebate Tab","EndDate"));
            itemRebateEndDate.setDateFormat(Constants.MM_DD_YYYY);
            attachListeners(itemRebateEndDate, "END_DATE");

            commonUtil.loadComboBox(udc1, CommonUtils.UDC1, false);
            commonUtil.loadComboBox(udc2, CommonUtils.UDC2, false);
            commonUtil.loadComboBox(udc3, CommonUtils.UDC3, false);
            commonUtil.loadComboBox(udc4, CommonUtils.UDC4, false);
            commonUtil.loadComboBox(udc5, CommonUtils.UDC5, false);
            commonUtil.loadComboBox(udc6, CommonUtils.UDC6, false);
            commonUtil.loadComboBox(rebateScheduleCategory, CommonUtils.RS_CATEGORY, false);
            rebateScheduleCategory.setRequired(true);
            rebateScheduleCategory.setRequiredError("Rebate Schedule Category should be selected on Rebate tab");
            commonUtil.loadComboBox(tradeClass, CommonUtils.TRADE_CLASS, false);

            commonUtil.loadComboBox(rebateScheduleDesignation, CommonUtils.RS_DESIGNATION, false);

            rebateScheduleDesignation.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used to click textfield and its listener.
                 */
                public void valueChange(final ValueChangeEvent event) {

                    String value;
                    if (rebateScheduleDesignation.getValue() == null) {
                        value = "";
                    } else {
                        value = rebateScheduleDesignation.getValue().toString();
                    }
                    if (Constants.CHILD.equals(value)) {

                        parentRebateScheduleId.setEnabled(true);
                    } else {
                        parentRebateScheduleId.setEnabled(false);
                    }
                }
            });

            parentRebateScheduleId.addStyleName(Constants.SEARCH_ICON_STYLE);
            parentRebateScheduleId.setImmediate(true);
            parentRebateScheduleId.setDescription(parentRebateScheduleId.getValue());
            parentRebateScheduleId.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used to click text field parentRebateScheduleId and
                 * its listener.
                 */
                public void valueChange(final ValueChangeEvent event) {
                    parentRebateScheduleName.setReadOnly(false);
                    parentRebateScheduleId.setDescription(parentRebateScheduleId.getValue());
                    parentRebateScheduleName.setReadOnly(true);

                }
            });

            parentRebateScheduleId.addClickListener(new CustomTextField.ClickListener() {
                /**
                 * Method used to click text field and its listener.
                 */
                public void click(CustomTextField.ClickEvent event) {
                    try {
                        if (lookUp == null) {
                            lookUp = new ParentLookUP2(parentRebateScheduleId, parentRebateScheduleName);
                            UI.getCurrent().addWindow(lookUp);
                            lookUp.addCloseListener(new Window.CloseListener() {
                                @Override
                                /**
                                 * Method used to click text field lookUp logic
                                 * and its listener.
                                 */
                                @SuppressWarnings("PMD")
                                public void windowClose(final Window.CloseEvent e) {
                                    parentRebateScheduleName.focus();
                                    parentRebateScheduleName.setReadOnly(false);
                                    parentRebateScheduleId.setDescription(parentRebateScheduleId.getValue());
                                    parentRebateScheduleName.setReadOnly(true);
                                    lookUp = null;
                                }
                            });
                        }
                    } catch (SystemException ex) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsg);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    } 

                }
            });

            rebateScheduleTransRefNo.addStyleName(Constants.SEARCH_ICON_STYLE);
            rebateScheduleTransRefNo.setImmediate(true);
            rebateScheduleTransRefNo.setDescription(rebateScheduleTransRefNo.getValue());
            rebateScheduleTransRefNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used to click text field and its listener.
                 */
                public void valueChange(final ValueChangeEvent event) {

                    rebateScheduleTransRefNo.setDescription(rebateScheduleTransRefNo.getValue());
                }
            });

            rebateScheduleTransRefNo.addClickListener(new CustomTextField.ClickListener() {
                /**
                 * Method used to click the text field and its listener.
                 */
                public void click(CustomTextField.ClickEvent event) {

                    try {
                        /**
                         * if(value.equals("child")) {
                         */
                        if (parentLookUp == null) {
                            parentLookUp = new ParentLookUp(rebateScheduleTransRefNo, rebateScheduleTransRefName);
                            UI.getCurrent().addWindow(parentLookUp);
                            parentLookUp.addCloseListener(new Window.CloseListener() {
                                @Override
                                /**
                                 * Method used to click the text field and its
                                 * listener.
                                 */
                                @SuppressWarnings("PMD")
                                public void windowClose(final Window.CloseEvent e) {
                                    rebateScheduleTransRefName.focus();
                                    parentLookUp = null;
                                }
                            });
                        }
                    } catch (SystemException ex) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsg);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    } 

                }
            });

            commonUtil.loadComboBox(interestBearingBasis, "INTEREST_BEARING_BASIS", false);
            itemRebateStartDate.setDescription(Constants.DATE);
            itemRebateEndDate.setDescription(Constants.DATE);
            commonUtil.loadComboBox(calculationType, CommonUtils.CALCULATION_TYPE, false);
            commonUtil.loadComboBox(calculationLevel, CommonUtils.CALCULATION_LEVEL, false);
            commonUtil.loadComboBox(evaluationRuleType, CommonUtils.EVALUATION_RULE_TYPE, false);
            commonUtil.loadComboBox(calculationRuleLevel, "RULE_LEVEL", false);
            commonUtil.loadComboBox(evaluationRuleLevel, "RULE_LEVEL", false);
            evaluationRuleAssociation.addStyleName(Constants.SEARCH_ICON_STYLE);
            evaluationRuleAssociation.setImmediate(true);
            evaluationRuleAssociation.setDescription(evaluationRuleAssociation.getValue());
            evaluationRuleAssociation.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used to click text field and its listener.
                 */
                public void valueChange(final ValueChangeEvent event) {

                    evaluationRuleAssociation.setDescription(evaluationRuleAssociation.getValue());
                }
            });

            evaluationRuleAssociation.addClickListener(new CustomTextField.ClickListener() {
                /**
                 * Method used to click the text field and its listener.
                 */
                public void click(CustomTextField.ClickEvent event) {

                    try {
                        HelperDTO tempDto = new HelperDTO();
                        for (HelperDTO helperDto : helperList.getListNameMap().get("RULE_TYPE")) {
                            if (helperDto.getDescription().equalsIgnoreCase("Evaluation")) {
                                tempDto.setId(helperDto.getId());
                                tempDto.setDescription(helperDto.getDescription());
                                break;
                            }
                        }
                        final NetSalesRuleLookup netSalesLookup = new NetSalesRuleLookup(evaluationRuleAssociation, tempDto, "Evaluation Rule Lookup");
                        UI.getCurrent().addWindow(netSalesLookup);
                        netSalesLookup.addCloseListener(new Window.CloseListener() {
                            @Override
                            public void windowClose(Window.CloseEvent e) {
                                if (netSalesLookup.isSelected()) {
                                    NetSalesRuleLookupDto searchDTO = netSalesLookup.getSelectedItem();
                                    evaluationRuleAssociation.setValue(searchDTO.getRuleName());
                                    evaluationSystemId.setValue(searchDTO.getRuleSystemId());
                                } else {
                                    evaluationRuleAssociation.setValue(StringUtils.EMPTY);
                                    evaluationSystemId.setValue(String.valueOf(0));
                                }
                            }
                        });
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            });
            calculationRule.addStyleName(Constants.SEARCH_ICON_STYLE);
            calculationRule.setImmediate(true);
            calculationRule.setDescription(calculationRule.getValue());
            calculationRule.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used to click text field and its listener.
                 */
                public void valueChange(final ValueChangeEvent event) {

                    calculationRule.setDescription(calculationRule.getValue());
                }
            });

            calculationRule.addClickListener(new CustomTextField.ClickListener() {
                /**
                 * Method used to click the text field and its listener.
                 */
                public void click(CustomTextField.ClickEvent event) {
                    try {
                        HelperDTO tempDto = new HelperDTO();
                        for (HelperDTO helperDto : helperList.getListNameMap().get("RULE_TYPE")) {
                            if (helperDto.getDescription().equalsIgnoreCase("Calculation")) {
                                tempDto.setId(helperDto.getId());
                                tempDto.setDescription(helperDto.getDescription());
                                break;
                            }
                        }
                        final NetSalesRuleLookup netSalesLookup = new NetSalesRuleLookup(calculationRule, tempDto, "Calculation Rule Lookup");
                        UI.getCurrent().addWindow(netSalesLookup);
                        netSalesLookup.addCloseListener(new Window.CloseListener() {
                            @Override
                            public void windowClose(Window.CloseEvent e) {
                                if (netSalesLookup.isSelected()) {
                                    NetSalesRuleLookupDto searchDTO = netSalesLookup.getSelectedItem();
                                    calculationRule.setValue(searchDTO.getRuleName());
                                    calculationSystemId.setValue(searchDTO.getRuleSystemId());
                                } else {
                                    calculationRule.setValue(StringUtils.EMPTY);
                                    calculationSystemId.setValue(String.valueOf(0));
                                }
                            }
                        });
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }

                }
            });
            LOGGER.debug("End of configureFields method");
        } catch (Exception e) {
            LOGGER.error(e);
        }
        
        excelExport.setIcon(new ThemeResource(ExcelExportUtil.EXCEL_EXPORT_IMAGE));
        excelExport.setStyleName("link");
        excelExport.setDescription(Constants.EXCEL_EXPORT);
        excelExport.setIconAlternateText(Constants.EXCEL_EXPORT);
    }

    /**
     * The Class DateValidator.
     */
    @SuppressWarnings("rawtypes")
    public class DateValidator extends AbstractValidator {
        String field;
        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * The Constructor.
         */
        public DateValidator() {
            super("");
        }

        /**
         * The Constructor.
         *
         * @param errorMessage the error message
         */
        public DateValidator(final String errorMessage, final String field) {
            super(errorMessage);
            this.field = field;
        }

        /**
         * Method used to click the text field validate and its listener.
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         */
        @Override
        public void validate(final Object value) throws Validator.InvalidValueException {
            LOGGER.debug("Entering validate method");

            if (itemRebateStartDate.getValue() != null && itemRebateEndDate.getValue() != null) {
                if (itemRebateStartDate.getValue().after(itemRebateEndDate.getValue())) {
                    throw new Validator.InvalidValueException("End date should be greater than start date in Rebate Tab");
                } else if (itemRebateStartDate.getValue().equals(itemRebateEndDate.getValue())) {
                    throw new Validator.InvalidValueException("Start date and End date are equal in Rebate Tab");
                }
            }
            if (dates.length > 0) {
                if ("StartDate".equals(field) && itemRebateStartDate.getValue() != null) {
                    if (dates[0] != null && itemRebateStartDate.getValue().before((Date) dates[0])) {
                        throw new Validator.InvalidValueException("Select Rebate Start date after " + format.format(dates[0]));
                    } else if (dates[1] != null && itemRebateStartDate.getValue().after((Date) dates[1])) {
                        throw new Validator.InvalidValueException("Select Rebate Start date before " + format.format(dates[1]));
                    }
                }
                if ("EndDate".equals(field) && itemRebateEndDate.getValue() != null && dates[1] != null && itemRebateEndDate.getValue().after((Date) dates[1])) {
                        throw new Validator.InvalidValueException("Select Rebate End date before " + format.format(dates[1]));
                }
            }
            LOGGER.debug("End of validate method");
        }

        /**
         * Method return boolean isValidValue .
         *
         * @param value the value
         * @return true, if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {
            LOGGER.debug("Entering isValidValue method");

            if (itemRebateStartDate.getValue() != null && itemRebateEndDate.getValue() != null) {
                return itemRebateStartDate.getValue().compareTo(itemRebateEndDate.getValue()) <= 0;
            }
            LOGGER.debug("End of isValidValue method");
            return true;
        }

        /**
         * Method used to get type.
         *
         * @return the type
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    public boolean isRpLevelChange() {
        return rpLevelChange;
    }

    public void setRpLevelChange(boolean rpLevelChange) {
        this.rpLevelChange = rpLevelChange;
    }

    public void focusRebateScheduleId() {
        this.rebateScheduleId.focus();
    }
    public PopupDateField getItemRebateStartDate() {
        return itemRebateStartDate;
    }

    public void setItemRebateStartDate(PopupDateField itemRebateStartDate) {
        this.itemRebateStartDate = itemRebateStartDate;
    }

    public PopupDateField getItemRebateEndDate() {
        return itemRebateEndDate;
    }

    public void setItemRebateEndDate(PopupDateField itemRebateEndDate) {
        this.itemRebateEndDate = itemRebateEndDate;
    }

    public void attachListeners(final AbstractField field, final String component) {
        field.setImmediate(true);
        field.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                try {
                    if (valueChange) {
                        if (component.equals(Constants.START_DATE_CAPS)) {
                            if (event != null) {
                                Date contractSD = (Date) dates[0];
                                if (!((PopupDateField) field).getValue().before(contractSD) && dates[1] == null) {
                                    ((PopupDateField) field).setDescription(CommonUtils.convertDateToString(((PopupDateField) field).getValue()));
                                } else if (((PopupDateField) field).getValue().before(contractSD)) {
                                    AbstractNotificationUtils.getWarningNotification("Start Date", "Start Date cannot be before " + format.format(contractSD));
                                    detachListeners(field);
                                    ((PopupDateField) field).setValue(null);
                                    attachListeners(field, Constants.START_DATE_CAPS);
                                } else if (dates[1] != null && ((PopupDateField) field).getValue().after((Date) dates[1])) {
                                    AbstractNotificationUtils.getWarningNotification("Start Date", "Start Date cannot be after " + format.format((Date) dates[1]));
                                    detachListeners(field);
                                    ((PopupDateField) field).setValue(null);
                                    attachListeners(field, Constants.START_DATE_CAPS);
                                }
                            }
                        } else if (component.equals(Constants.END_DATE_CAPS) && event != null) {
                                Date contractSD = (Date) dates[0];
                                if ((Date) dates[1] == null && !((PopupDateField) field).getValue().before(contractSD)) {
                                    ((PopupDateField) field).setDescription(CommonUtils.convertDateToString(((PopupDateField) field).getValue()));
                                } else if (((PopupDateField) field).getValue().before(contractSD)) {
                                    AbstractNotificationUtils.getWarningNotification("End Date", "End Date cannot be before " + format.format(contractSD));
                                    detachListeners(field);
                                    ((PopupDateField) field).setValue(null);
                                    attachListeners(field, Constants.END_DATE_CAPS);
                                } else if (dates[1] != null && ((PopupDateField) field).getValue().after((Date) dates[1])) {
                                    AbstractNotificationUtils.getWarningNotification("End Date", "End Date cannot be after " + format.format((Date) dates[1]));
                                    detachListeners(field);
                                    ((PopupDateField) field).setValue(null);
                                    attachListeners(field, Constants.END_DATE_CAPS);
                                }
                        }
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });
    }

    /**
     * method for detaching listener.
     *
     * @param field
     */
    public void detachListeners(final AbstractField field) {

        List<Property.ValueChangeListener> listeners;

        listeners = (List<Property.ValueChangeListener>) field.getListeners(Property.ValueChangeEvent.class);
        for (final Property.ValueChangeListener object : listeners) {
            field.removeValueChangeListener(object);
        }

    }

    public void loadRebateSetupTab() {
        try {
            if (isEditable) {
                rebateSetup.focusMassCheck();
                rebateSetup.removeItemFromRsDetailsResultsBean();
                rebateSetup.loadTempIfp();
                Date startDate = itemRebateStartDate.getValue() == null ? null : fmt.parse(fmt.format(itemRebateStartDate.getValue()));
                Date endDate = itemRebateEndDate.getValue() == null ? null : fmt.parse(fmt.format(itemRebateEndDate.getValue()));
                rebateSetup.loadDates(startDate, endDate);
                rebateSetup.loadBasedOnCalculationType(String.valueOf(rebateBinder.getField("calculationType").getValue()));
            } else {
                rebateSetup.loadTempRs(StringUtils.EMPTY);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public void btnExportLogic() {
        LOGGER.debug("Entering btnExportLogic method");
        try {
                excelExport.addClickListener(new Button.ClickListener() {
            /**
             * Invoked when a button is clicked
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {
                 if (Constants.DETAILS.equals(level.getValue().toString())) {
                     try {
                         rebateSetup.btnExportLogic();
                     } catch (SystemException ex) {
                         java.util.logging.Logger.getLogger(RebateScheduleInformation.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (PortalException ex) {
                         java.util.logging.Logger.getLogger(RebateScheduleInformation.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (Exception ex) {
                         java.util.logging.Logger.getLogger(RebateScheduleInformation.class.getName()).log(Level.SEVERE, null, ex);
                     }
            } else if (Constants.HEADER.equals(level.getValue().toString()) || Constants.PROCESSING_OPTION.equals(level.getValue().toString())) {
                excelExportLogic();
            }
            }
        });
           
        } catch (Exception ex) {
              LOGGER.error(ex);
        }
        LOGGER.debug("End of btnExportLogic method");
    }

    /**
     * Excel Export Logic
     *
     * @throws PortalException
     * @throws SystemException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws Exception
     */
    public void excelExportLogic() {
        LOGGER.debug("Entering excelExportLogic");
        try {
            if (Constants.HISTORY.equals(view.getValue().toString())) {
                createWorkSheet();
            } else {
                MessageBox.showPlain(Icon.INFO, "Excel Export", "Excel is not supported for " + view.getValue().toString(), new MessageBoxListener() {
                    /**
                     * After clicking button, function will be executed.
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {

                    }
                }, ButtonId.OK);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending excelExportLogic");
    }

    /**
     * This method is used for creating worksheet
     *
     * @throws PortalException
     * @throws SystemException
     * @throws IllegalArgumentException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws Exception
     */
    private void createWorkSheet() throws PortalException, SystemException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        LOGGER.debug("Entering createWorkSheet");
        int recordCount = 0;
        ExcelExportforBB.createWorkSheet(table.getColumnHeaders(), recordCount, this, getUI(), "Rebate");
        LOGGER.debug("Ending createWorkSheet");
    }

    /**
     *
     * @param start
     * @param end
     * @param printWriter
     * @throws PortalException
     * @throws SystemException
     */
    public void createWorkSheetContent() throws PortalException, SystemException {
        LOGGER.debug("Entering createWorkSheetContent");

        LOGGER.debug("Ending createWorkSheetContent");
    }
    public String textFieldEmptyChecks() {
        List<String> textFieldName = new ArrayList<>();
        String footerMsg = " should be entered on Rebate Schedule Information tab";
        if (rebateScheduleNo.getValue().isEmpty()) {
            textFieldName.add("Rebate Schedule No");
        }
        if (rebateScheduleName.getValue().isEmpty()) {
            textFieldName.add("Rebate Schedule Name");
        }
        
        return !textFieldName.isEmpty() ? StringUtils.join(textFieldName, ",") + footerMsg : StringUtils.EMPTY;
    }
}
