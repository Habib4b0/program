/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.common.CommonUtil;
import com.stpl.app.accountclose.dto.BaseRateDTO;
import com.stpl.app.accountclose.dto.FixedDollarDTO;
import com.stpl.app.accountclose.dto.FixedDollarSaveDTO;
import com.stpl.app.accountclose.dto.TreeDTO;
import com.stpl.app.accountclose.gtnbalancereport.dto.DataSelectionDTO;
import com.stpl.app.accountclose.gtnbalancereport.utils.DataSelectionUtil;
import com.stpl.app.accountclose.logic.CustomerProductTreeLogic;
import com.stpl.app.accountclose.logic.FixedDollarCalculationLogic;
import com.stpl.app.accountclose.logic.FixedDollarDataSelectionLogic;
import com.stpl.app.accountclose.logic.FixedDollarResultsTreeLogic;
import com.stpl.app.accountclose.security.StplSecurity;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import com.stpl.app.accountclose.utils.CommonUtils;
import com.stpl.app.accountclose.utils.Constants;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.QUARTERLY;
import com.stpl.app.accountclose.utils.Constants.IndicatorConstants;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.OptionGroup;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.*;
import static com.stpl.app.accountclose.utils.Constants.LabelConstants.MODE_SEARCH;
import com.stpl.app.accountclose.utils.ErrorCodes;
import com.stpl.app.accountclose.utils.HeaderUtils;
import com.stpl.app.accountclose.utils.QueryConstants;
import com.stpl.app.accountclose.utils.QueryUtils;
import com.stpl.app.parttwo.model.AcFdAdjustmentSelection;
import com.stpl.app.parttwo.model.AccClosureMaster;
import com.stpl.app.parttwo.service.AcFdAdjustmentSelectionLocalServiceUtil;
import com.stpl.app.parttwo.service.AccClosureMasterLocalServiceUtil;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer.DataStructureMode;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author alok.v
 */
public class FixedDollarAdjustmentDetails extends CustomComponent implements View {

    SessionDTO session;
    @UiField("viewTypeRadio")
    public OptionGroup viewTypeRadio;
    @UiField("adjimpviewTypeRadio")
    public OptionGroup adjimpviewTypeRadio;
    @UiField("releaseTypeRadio")
    public OptionGroup releaseTypeRadio;
    @UiField("salesBasis")
    public ComboBox salesBasis;
    @UiField("customerGroup")
    private CustomTextField customerGroup;
    @UiField("productGroup")
    private CustomTextField productGroup;
    @UiField("companyId")
    public ComboBox company;
    @UiField("marketType")
    public ComboBox marketType;
    @UiField("acctType")
    public ComboBox acctType;
    @UiField("acctSubType")
    public ComboBox acctSubType;
    @UiField("adjustmentType")
    public ComboBox adjustmentType;
    @UiField("accrualPeriod")
    public ComboBox accrualPeriod;
    @UiField("contract")
    public ComboBox contract;
    @UiField("product")
    public ComboBox product;
    @UiField("ndc")
    public ComboBox ndc;
    @UiField("allocationMethod")
    public ComboBox allocationMethod;
    @UiField("filterDdlb")
    public ComboBox filterDdlb;
    @UiField("glImpactDate")
    public PopupDateField glImpactDate;
    @UiField("suggestedAdj")
    private CustomTextField suggestedAdj;
    @UiField("fromDate")
    private ComboBox fromDate;
    @UiField("toDate")
    private ComboBox toDate;
    @UiField("startDate")
    private TextField startDate;
    @UiField("businessDays")
    private TextField businessDays;
    @UiField("releaseAmount")
    private TextField releaseAmount;
    @UiField("rateCorrection")
    private TextField rateCorrection;
    @UiField("totFixedDollAdj")
    private TextField totFixedDollAdj;
    /**
     * Reset Button
     */
    @UiField("resetBtn1")
    public Button resetBtn1;
    @UiField("resetBtn2")
    public Button resetBtn2;
    @UiField("resetBtn3")
    public Button resetBtn3;

    @UiField("adjustmentImpactLayout")
    public VerticalLayout adjustmentImpactLayout;
    @UiField("calculateBtn")
    public Button calculateBtn;
    @UiField("populateBtn1")
    public Button populateBtn1;
    @UiField("populateBtn2")
    public Button populateBtn2;
    @UiField("availableTableLayout")
    public VerticalLayout availableTableLayout;
    @UiField("resultsTableLayout")
    public VerticalLayout resultsTableLayout;
    HelperDTO ddlbDefaultValue = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
    CommonLogic commonLogic = new CommonLogic();
    private ExtTreeContainer<TreeDTO> availTreeContainer = new ExtTreeContainer<>(TreeDTO.class);
    public ExtFilterTable adjustmentImpactTable = new ExtFilterTable();
    private BeanItemContainer<FixedDollarDTO> adjustmentImpactContainer = new BeanItemContainer<>(FixedDollarDTO.class);
    final FixedDollarCalculationLogic fdLogic = new FixedDollarCalculationLogic();
    TreeDTO dto = new TreeDTO();
    private String custGroupSid;
    private String prodGroupSid;
    CustomerProductTreeLogic logic = new CustomerProductTreeLogic();
    public ExtPagedTreeTable availableCustomer = new ExtPagedTreeTable(logic);
    final List<TreeDTO> selecteditemList = new ArrayList<>();
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO rightDTO;
    HeaderUtils utils = new HeaderUtils();
    FixedDollarResultsTreeLogic fdResultLogic = new FixedDollarResultsTreeLogic();
    public ExtPagedTreeTable resultsTable = new ExtPagedTreeTable(fdResultLogic);
    CustomTableHeaderDTO fullResultHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO rightResultDTO;
    private ExtTreeContainer<FixedDollarDTO> resultsTreeContainer = new ExtTreeContainer<FixedDollarDTO>(FixedDollarDTO.class,DataStructureMode.MAP);
    HeaderUtils hUtil = new HeaderUtils();
    FixedDollarDTO fixedDollarDTO;
    FixedDollarDTO fdDto = new FixedDollarDTO();
    DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    private final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    Integer accountMasterId = 0;
    @UiField("exportBtn")
    Button exportBtn;
    ExtTreeContainer<FixedDollarDTO> excelContainer = new ExtTreeContainer<FixedDollarDTO>(FixedDollarDTO.class,DataStructureMode.MAP);
    ExtFilterTreeTable excelTable = new ExtFilterTreeTable();
    /* The Excel header Dto (Right Header) */
    CustomTableHeaderDTO excelHeader = new CustomTableHeaderDTO();
    String calculatorvalue;
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(FixedDollarAdjustmentDetails.class);
    List<FixedDollarDTO> fdList = new ArrayList<FixedDollarDTO>();
    FixedDollarDataSelectionLogic fdaLogic = new FixedDollarDataSelectionLogic();
    String sessionId;
    CommonUtils comutils = new CommonUtils();
    boolean status = false;
    String finalAllocationMethod = StringUtils.EMPTY;
    CommonUtil commonUtil = CommonUtil.getInstance();
    ErrorCodes ErrorCodes;
    CustomFieldGroup fixedDollarBinder;

    public Component getContent() {
        LOGGER.info("getContent method starts");
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(Clara.create(getClass().getResourceAsStream("/fixedDollarAdjustmentDetails.xml"), this));
        Panel panel = new Panel();
        panel.setContent(layout);
        LOGGER.info("getContent method ends");
        return panel;
    }

    /**
     *
     * @param session
     * @param fixedDollarDTO
     */
    public FixedDollarAdjustmentDetails(final SessionDTO session, final FixedDollarDTO fixedDollarDTO, boolean flag) {

        this.session = session;
        this.fixedDollarDTO = fixedDollarDTO;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/fixedDollarAdjustmentDetails.xml"), this));
        configureFields(flag);
        try {
            addSecurityForButtons();
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (PortalException ex) {
            LOGGER.error(ex);
        }
    }

    public void addSecurityForButtons() throws SystemException, PortalException {
        LOGGER.info("Entering addToContent");
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, Constants.FIXED_DOLLAR_ADJUSTMENT + "," + "Adjustment Detail");
        if ((functionCfpHM.get(Constants.Populate)) != null && ((AppPermission) functionCfpHM.get(Constants.Populate)).isFunctionFlag()) {
            populateBtn1.setVisible(true);
        } else {
            populateBtn1.setVisible(false);
        }
        if ((functionCfpHM.get(Constants.Populate1)) != null && ((AppPermission) functionCfpHM.get(Constants.Populate1)).isFunctionFlag()) {
            populateBtn2.setVisible(true);
        } else {
            populateBtn2.setVisible(false);
        }
        if ((functionCfpHM.get(Constants.CALCULATE)) != null && ((AppPermission) functionCfpHM.get(Constants.CALCULATE)).isFunctionFlag()) {
            calculateBtn.setVisible(true);
        } else {
            calculateBtn.setVisible(false);
        }
        LOGGER.info("Ending addToContent");
    }

    protected void configureFields(boolean flag) {
        try {
            glImpactDate.setDateFormat("MM/dd/yyyy");
            releaseTypeRadio.setImmediate(true);
            releaseTypeRadio.addItem(PERCENTAGE.getConstant());
            releaseTypeRadio.addItem(DOLLAR.getConstant());
            releaseTypeRadio.select(PERCENTAGE.getConstant());
            releaseTypeRadio.setEnabled(true);

            salesBasis.addItem(IndicatorConstants.SELECT_ONE.getConstant());
            salesBasis.setNullSelectionAllowed(true);
            salesBasis.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());
            commonUtil.loadComboBox(salesBasis, "SALES_BASIS", false);

            company.addItem(IndicatorConstants.SELECT_ONE.getConstant());
            company.setNullSelectionAllowed(true);
            company.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());
            company = commonLogic.commonLoadingDdlb(company, StringUtils.EMPTY, "companyForm");

            marketType.addItem(IndicatorConstants.SELECT_ONE.getConstant());
            marketType.select(SELECT_ONE.getConstant());

            acctType.addItem(IndicatorConstants.SELECT_ONE.getConstant());
            acctType.setNullSelectionAllowed(true);
            acctType.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());

            acctSubType.addItem(IndicatorConstants.SELECT_ONE.getConstant());
            acctSubType.setNullSelectionAllowed(true);
            acctSubType.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());

            adjustmentType.addItem(IndicatorConstants.SELECT_ONE.getConstant());
            adjustmentType.setNullSelectionAllowed(true);
            adjustmentType.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());

            commonUtil.loadComboBox(adjustmentType, "ADJUSTMENT_TYPE", false);
            accrualPeriod.addItem(IndicatorConstants.SELECT_ONE.getConstant());
            accrualPeriod.setNullSelectionAllowed(true);
            accrualPeriod.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());
            accrualPeriod.setValue(IndicatorConstants.SELECT_ONE.getConstant());
            DataSelectionUtil.configureTimeDdlb(accrualPeriod, new ComboBox(), null, null, MODE_SEARCH.getConstant(), "DS", QUARTERLY.getConstant());

            contract.addItem(IndicatorConstants.SELECT_ONE.getConstant());
            contract.setNullSelectionAllowed(true);
            contract.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());

            product.addItem(IndicatorConstants.SELECT_ONE.getConstant());
            product.setNullSelectionAllowed(true);
            product.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());

            ndc.addItem(IndicatorConstants.SELECT_ONE.getConstant());
            ndc.setNullSelectionAllowed(true);
            ndc.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());
            ndc.addItem(NDC8.getConstant());
            ndc.addItem(NDC10.getConstant());
            ndc.addItem(NDC11.getConstant());

            allocationMethod.addItem(IndicatorConstants.SELECT_ONE.getConstant());
            allocationMethod.setNullSelectionAllowed(true);
            allocationMethod.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());
            commonUtil.loadComboBox(allocationMethod, "ALLOCATION_METHOD", false);

            filterDdlb.addItem(IndicatorConstants.ALL.getConstant());
            filterDdlb.setNullSelectionAllowed(true);
            filterDdlb.setNullSelectionItemId(IndicatorConstants.ALL.getConstant());

            Panel avail = new Panel();
            avail.setContent(availableCustomer);
            availableTableLayout.addComponent(availableCustomer);

            configureCustomerProductTable();
            HorizontalLayout controls = logic.createControls();
            HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
            availableTableLayout.addComponent(controlLayout);
            availableTableLayout.setSpacing(Boolean.TRUE);

            Panel result = new Panel();
            result.setContent(resultsTable);

            resultsTableLayout.addComponent(result);
            fixedDollarResultsTable("Auto Reconcile");
            HorizontalLayout resControls = fdResultLogic.createControls();
            HorizontalLayout resControlLayout = CommonLogic.getResponsiveControls(resControls);
            resultsTableLayout.addComponent(resControlLayout);
            resultsTableLayout.setSpacing(Boolean.TRUE);
            adjustmentImpactLayout.addComponent(adjustmentImpactTable);
            configureAdjustmentImpactTable();
            fromDate.addItem(IndicatorConstants.SELECT_ONE.getConstant());
            fromDate.setNullSelectionAllowed(true);
            fromDate.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());
            fromDate.setValue(IndicatorConstants.SELECT_ONE.getConstant());
            DataSelectionUtil.configureMethodlogyTimeDdlb(fromDate, new ComboBox(), null, null, MODE_SEARCH.getConstant(), "DS", QUARTERLY.getConstant());

            toDate.addItem(IndicatorConstants.SELECT_ONE.getConstant());
            toDate.setNullSelectionAllowed(true);
            toDate.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());
            fromDate.setValue(IndicatorConstants.SELECT_ONE.getConstant());
            DataSelectionUtil.configureMethodlogyTimeDdlb(toDate, new ComboBox(), null, null, MODE_SEARCH.getConstant(), "DS", QUARTERLY.getConstant());

            glImpactDate.addValueChangeListener(new Property.ValueChangeListener() {
                public void valueChange(Property.ValueChangeEvent event) {
                    if (glImpactDate.getValue() != null) {
                        Date glImpDate = glImpactDate.getValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
                        Date currentDate = new Date();
                        String formattedCurrDate = sdf.format(currentDate);
                        String formattedGlDate = sdf.format(glImpDate);
                        try {
                            String currDate = df.format(currentDate);
                            String glDate = df.format(glImpDate);
                            int dateCheckResult = compareDatesByCompareTo(df, df.parse(glDate), df.parse(currDate));
                            if (dateCheckResult == 0) {
                                startDate.setValue(formattedCurrDate);
                                businessDays.setValue("1");
                            }
                            if (dateCheckResult == 2) {
                                startDate.setValue(formattedCurrDate);
                                businessDays.setValue("1");
                            }
                            if (dateCheckResult == 3) {
                                startDate.setValue(formattedGlDate);
                                int businessDaysValue = getNoOfBusinessDays(sdf, sdf.parse(formattedCurrDate), sdf.parse(formattedGlDate));
                                businessDays.setValue(String.valueOf(businessDaysValue));
                            }

                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                }
            });
            viewTypeRadio.setImmediate(true);
            viewTypeRadio.addItem(CUSTOMER.getConstant());
            viewTypeRadio.addItem(PRODUCT.getConstant());
            viewTypeRadio.select(CUSTOMER.getConstant());
            viewTypeRadio.setMultiSelect(false);
            adjimpviewTypeRadio.setImmediate(true);
            adjimpviewTypeRadio.addItem(SUMMARY.getConstant());
            adjimpviewTypeRadio.addItem(DETAIL.getConstant());
            adjimpviewTypeRadio.select(SUMMARY.getConstant());
            adjimpviewTypeRadio.setMultiSelect(false);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }

        sessionId = FixedDollarDataSelectionLogic.createSessionId();

        if (!comutils.getNull(String.valueOf(session.getAcctCloserMasterId())) && session.getAcctCloserMasterId() != 0 && !flag) {
            if ("Withdrawn".equalsIgnoreCase(session.getWorkflowStatus())) {

                status = true;
            }
            loadEditValues(status);
        }
        if (flag) {
            DataSelectionDTO selectionDTO = session.getDataSelectionDTO();
            company.setValue(StringUtils.EMPTY + selectionDTO.getCompanySid());
            marketType.setValue(StringUtils.EMPTY + selectionDTO.getMarketType());
            acctType.setValue(StringUtils.EMPTY + selectionDTO.getDeductionType());
            acctSubType.setValue(selectionDTO.getDeductionSubType());
            contract.setValue(selectionDTO.getContractSid());
        }
    }

    /**
     * Date Comparison Logic
     *
     * @param df
     * @param oldDate
     * @param newDate
     * @return
     */
    public static int compareDatesByCompareTo(DateFormat df, Date oldDate, Date newDate) {

        int result = 1;
        if (oldDate.compareTo(newDate) == 0) {
            result = 0;
        }

        if (oldDate.compareTo(newDate) < 0) {
            result = 2;
        }

        if (oldDate.compareTo(newDate) > 0) {
            result = 3;
        }
        return result;
    }

    /**
     * No of Business Days Logic
     *
     * @param sdf
     * @param currDate
     * @param glDate
     * @return
     */
    public static int getNoOfBusinessDays(DateFormat sdf, Date currDate, Date glDate) {

        int noOfBusinessDays = 0;
        String glImpactDate = sdf.format(glDate);
        String currentDate = sdf.format(currDate);
        try {
            Calendar start = Calendar.getInstance();
            start.setTime(sdf.parse(currentDate));
            Calendar end = Calendar.getInstance();
            end.setTime(sdf.parse(glImpactDate));

            while (!start.after(end)) {
                int day = start.get(Calendar.DAY_OF_WEEK);
                if ((day != Calendar.SATURDAY) && (day != Calendar.SUNDAY)) {
                    noOfBusinessDays++;
                }
                start.add(Calendar.DATE, 1);
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return noOfBusinessDays;
    }

    /**
     * Configure Customer/Product Table
     *
     */
    public void configureCustomerProductTable() {
        fullHeader = new CustomTableHeaderDTO();
        logic.setControlTable(availableCustomer);
        rightDTO = utils.getFixedDollarCustProdTableColumns(fullHeader);
        availTreeContainer.setColumnProperties(rightDTO.getProperties());
        logic.setPageLength(8);
        logic.setContainerDataSource(availTreeContainer);
        logic.sinkItemPerPageWithPageLength(false);
        availableCustomer.setWidth(100, Unit.PERCENTAGE);
        availableCustomer.setHeight(100, Unit.PERCENTAGE);
        availableCustomer.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        availableCustomer.setEditable(true);
        availableCustomer.setVisibleColumns(rightDTO.getSingleColumns().toArray());
        availableCustomer.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));
        availableCustomer.setColumnCheckBox("checkRecord", Boolean.TRUE);

        availableCustomer.setTableFieldFactory(new TableFieldFactory() {
            public Field<?> createField(Container container, final Object itemId, final Object propertyId, Component uiContext) {

                if (propertyId.equals("checkRecord")) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();

                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        public void click(ExtCustomCheckBox.ClickEvent event) {

                            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                            int count = fdaLogic.callFixedDollarUpdate(check.getValue(), (TreeDTO) itemId, "Fixed Dollar", userId, sessionId, dto.getContractSid(), dto.getItemSid());
                            if (count == 0) {
                                fdaLogic.callFixedDollarInsert(check.getValue(), (TreeDTO) itemId, "Fixed Dollar", userId, sessionId, dto.getContractSid(), dto.getItemSid());
                            }
                            TreeDTO trDto = (TreeDTO) itemId;
                            Boolean checkValue = check.getValue();
                            trDto.addBooleanProperties(propertyId, checkValue);

                            availableCustomer.setRefresh(false);
                            updateCheckForParentLevels(itemId, 5, checkValue);
                            availableCustomer.setRefresh(true);
                            if (checkValue) {
                                if (!selecteditemList.contains(itemId)) {

                                    selecteditemList.add((TreeDTO) itemId);
                                }
                            } else {
                                selecteditemList.remove(itemId);
                            }
                        }
                    });
                    return check;
                }
                return null;
            }
        });
        availableCustomer.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection list = availableCustomer.getItemIds();
                for (Object obj : list) {
                    availTreeContainer.getContainerProperty(obj, event.getPropertyId()).setValue(event.isChecked());
                    String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                    int count = fdaLogic.callFixedDollarUpdate(event.isChecked(), (TreeDTO) obj, "Fixed Dollar", userId, sessionId, dto.getContractSid(), dto.getItemSid());
                    if (count == 0) {
                        fdaLogic.callFixedDollarInsert(event.isChecked(), (TreeDTO) obj, "Fixed Dollar", userId, sessionId, dto.getContractSid(), dto.getItemSid());
                    }
                }

            }
        });

        for (Object property : availableCustomer.getVisibleColumns()) {

            availableCustomer.setColumnWidth(property, 100);
            availableCustomer.setColumnAlignment(property, ExtCustomTable.ALIGN_CENTER);

        }

        availableCustomer.addStyleName("table-header-normal");
        availableCustomer.addStyleName(VALO_THEME_EXTFILTERING_TABLE);

    }

    /**
     * Updating the Checked Record
     *
     * @param dto
     * @return
     */
    
    /**
     * Updating Checked Record for Parent Level
     *
     * @param itemId
     * @param updatedRecordsNo
     * @param checkValue
     */
    private void updateCheckForParentLevels(Object itemId, int updatedRecordsNo, Boolean checkValue) {
        TreeDTO tempDto = (TreeDTO) itemId;
        boolean check = tempDto.getUncheckCount() == 0;
        tempDto.addBooleanProperties("checkRecord", checkValue);
        if (checkValue) {
            logic.getContainerDataSource().getContainerProperty(itemId, "checkRecord").setValue(checkValue);
        }
    }

    public void fixedDollarResultsTable(String allocationMethodValue) {
        fullResultHeader = new CustomTableHeaderDTO();
        final String Salesbasis = String.valueOf(salesBasis.getValue());

        rightResultDTO = hUtil.getFixedDollarResultsTableRightColumns(fullResultHeader, allocationMethodValue, Salesbasis);

        resultsTreeContainer = new ExtTreeContainer<>(FixedDollarDTO.class);
        resultsTreeContainer.setColumnProperties(rightResultDTO.getProperties());
        fdResultLogic.setPageLength(8);
        fdResultLogic.setControlTable(resultsTable);
        fdResultLogic.setContainerDataSource(resultsTreeContainer);
        fdResultLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setWidth(100, Unit.PERCENTAGE);
        fdResultLogic.setRefresh(true);
        resultsTable.markAsDirty();
        resultsTable.setVisible(true);
        resultsTable.setEditable(false);
        resultsTable.setVisibleColumns(rightResultDTO.getSingleColumns().toArray());
        resultsTable.setColumnHeaders(rightResultDTO.getSingleHeaders().toArray(new String[rightResultDTO.getSingleHeaders().size()]));
        resultsTable.setColumnCollapsingAllowed(true);
        resultsTable.setColumnCollapsed("autoAccruals", true);
        resultsTable.setColumnCollapsed("manualAdjustment", true);
        resultsTable.setColumnCollapsed("paymentTrueUp", true);
        resultsTable.setColumnCollapsed("other", true);
        resultsTable.setColumnCollapsed("projectedDisc", true);
        resultsTable.setColumnCollapsed("percentageAccrualToProj", true);
        resultsTable.setColumnCollapsed("percentageActualToProj", true);
        resultsTable.setColumnExpandIcon("accrualPeriodAccruals", false);

        resultsTable.addColumnExpandIconListener(new ExtCustomTable.ColumnExpandIconListener() {
            @Override
            public void columnExpandIcon(ExtCustomTable.ColumnExpandIconEvent event) {
                setExpandIconAction(event.getPropertyId(), !event.isExpanded(), fixedDollarDTO);
            }
        });

        resultsTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection list = resultsTable.getItemIds();
                for (Object obj : list) {
                    resultsTreeContainer.getContainerProperty(obj, event.getPropertyId()).setValue(event.isChecked());
                }

            }
        });
        for (Object property : resultsTable.getVisibleColumns()) {

            resultsTable.setColumnAlignment(property, ExtCustomTable.ALIGN_RIGHT);

        }

        for (Object property : resultsTable.getVisibleColumns()) {
            resultsTable.setColumnWidth(property, 120);

        }
    }

    public void configureAdjustmentImpactTable() {
        adjustmentImpactTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        adjustmentImpactTable.setWidth("990px");
        adjustmentImpactTable.setHeight("250px");
        adjustmentImpactTable.setPageLength(5);
        adjustmentImpactTable.setImmediate(true);
        adjustmentImpactTable.setSelectable(true);
        adjustmentImpactTable.setContainerDataSource(adjustmentImpactContainer);
        adjustmentImpactTable.setVisibleColumns(Constants.FD_ADJ_IMPACT_COLUMNS);
        adjustmentImpactTable.setColumnHeaders(Constants.FD_ADJ_IMPACT_HEADERS);
    }

    public void setExpandIconAction(Object propertyId, boolean collapsed, FixedDollarDTO projSel) {
        resultsTable.setColumnCollapsed("autoAccruals", collapsed);
        resultsTable.setColumnCollapsed("manualAdjustment", collapsed);
        resultsTable.setColumnCollapsed("paymentTrueUp", collapsed);
        resultsTable.setColumnCollapsed("other", collapsed);
        resultsTable.setColumnCollapsed("projectedDisc", collapsed);
        resultsTable.setColumnCollapsed("percentageAccrualToProj", collapsed);
        resultsTable.setColumnCollapsed("percentageActualToProj", collapsed);

    }

    /*Load Market Type*/
    @UiHandler("companyId")
    public void loadMarketDdlb(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Entering loadMarketDdlb method");
        if (company.getValue() != null) {
            commonUtil.loadComboBox(marketType, "CONTRACT_TYPE", false);
        } else {
            marketType.setValue(null);
        }
        LOGGER.info("End of loadMarketDdlb method");
    }

    /*Load Account Type*/
    @UiHandler("marketType")
    public void loadDiscDdlb(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Entering loadDiscDdlb method");

        if (marketType.getValue() != null) {
            HelperDTO markettype = (HelperDTO) marketType.getValue();
            acctType = commonLogic.commonLoadingDdlb(acctType, String.valueOf(markettype.getId()), "acctTypeForm");
        } else {
            acctType.setValue(null);
        }
    }

    /*Load Account SubType*/
    @UiHandler("acctType")
    public void acctType(Property.ValueChangeEvent event) throws Exception {
        if (acctType.getValue() != null) {
            String id = (String.valueOf(acctType.getValue()));
            LOGGER.info("Entering acctType ValueChange method id=" + id);
            acctSubType = commonLogic.commonLoadingDdlb(acctSubType, id, "acctSubTypeForm");
            contract = commonLogic.commonLoadingDdlb(contract, id, "contractForm");
            product = commonLogic.commonLoadingDdlb(product, id, "productForm");
            LOGGER.info("End of acctType ValueChange method");
        } else {
            acctSubType.setValue(null);
            contract.setValue(null);
            product.setValue(null);
        }
    }

    /**
     * Loading Available Customer Products
     *
     */
    public void LoadCustomerProductTree() {
        LOGGER.info("Entering getProcessedTree method");
        try {
            setValues(dto);
            logic.setData(dto);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("End of getProcessedTree method" + availTreeContainer.size());
    }

    /**
     * Setting Criteria Values
     *
     * @param dto
     */
    public void setValues(TreeDTO dto) {
        String contractDto = String.valueOf(contract.getValue());
        dto.setContractSid(contractDto);
        String itemDto = String.valueOf(product.getValue());
        dto.setItemSid(itemDto);
        if (custGroupSid != null && custGroupSid != StringUtils.EMPTY && custGroupSid != "null") {
            dto.setCustGroupSid(custGroupSid);
        }
        if (prodGroupSid != null && prodGroupSid != StringUtils.EMPTY && prodGroupSid != "null") {
            dto.setPrdGroupSid(prodGroupSid);
        }

    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    /**
     * Customer Group Popup
     *
     * @param event
     */
    @UiHandler("customerGroup")
    public void customerGroup(CustomTextField.ClickEvent event) {

        List<String> companiesLevel = new ArrayList<String>();
        List<String> companies = null;
        FixedDollarCalculationLogic logic = new FixedDollarCalculationLogic();
        final FixedDollarCustomerGroupLookup customerGroupLookupWindow = new FixedDollarCustomerGroupLookup("Customer Group", "Customer Group Lookup", customerGroup, StringUtils.EMPTY, custGroupSid);
        UI.getCurrent().addWindow(customerGroupLookupWindow);
        customerGroupLookupWindow.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                custGroupSid = customerGroupLookupWindow.getGroupSid();

            }
        });
    }

    @UiHandler("suggestedAdj")
    public void suggestedAdj(CustomTextField.ClickEvent event) {
        BaseRateDTO baseRateDTO = new BaseRateDTO();
        FixedDollarDTO fixedDto = new FixedDollarDTO();
        fixedDto = loadvalues(fixedDto);
        baseRateDTO.setSessionId(session.getSessionId());
        FixedDollarCalculationLogic logic = new FixedDollarCalculationLogic();
        List<String> ccpIds = new ArrayList<String>();
        for (int i = 0; i < fdList.size(); i++) {
            FixedDollarDTO test = fdList.get(i);
            ccpIds.add(test.getCcpSid());
        }

        final BaseRateLookup baseRateLookup = new BaseRateLookup(session, baseRateDTO, ccpIds, fdDto.getAccountClosureMasterSid(), fixedDto);
        UI.getCurrent().addWindow(baseRateLookup);
        baseRateLookup.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                suggestedAdj.setValue(baseRateLookup.getBaseRateCalculation().getCalcultedValue());
                if (allocationMethod.getValue() != null && "Rate Adjustment".equals(allocationMethod.getValue().toString())) {
                    rateCorrection.setValue(baseRateLookup.getBaseRateCalculation().getCalcultedValue());
                    totFixedDollAdj.setValue(baseRateLookup.getBaseRateCalculation().getCalcultedValue());
                }

            }
        });
    }

    /**
     * Product Group Popup
     *
     * @param event
     */
    @UiHandler("productGroup")
    public void productGroup(CustomTextField.ClickEvent event) {

        List<String> companiesLevel = new ArrayList<String>();
        List<String> companies = null;
        FixedDollarCalculationLogic logic = new FixedDollarCalculationLogic();
        final FixedDollarProductGroupLookUp prodGroupLookupWindow = new FixedDollarProductGroupLookUp("Product Group", "Product Group Lookup", productGroup, StringUtils.EMPTY, prodGroupSid);
        UI.getCurrent().addWindow(prodGroupLookupWindow);
        prodGroupLookupWindow.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                prodGroupSid = prodGroupLookupWindow.getGroupSid();

            }
        });

    }

    /**
     * Reset Button Functionality
     *
     * @param event
     */
    @UiHandler("resetBtn1")
    public void resetBtn1Logic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, commonUtil.getHeaderMessage(), commonUtil.getResetMessage(), new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    try {
                        fixedDollarDataSelectReset();
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    /**
     * Reset Button Logic
     *
     */
    public void fixedDollarDataSelectReset() {
        company.setValue("0");
        marketType.setValue(null);
        acctType.setValue("0");
        acctSubType.setValue("0");
        contract.setValue("0");
        accrualPeriod.setValue(IndicatorConstants.SELECT_ONE.getConstant());
        adjustmentType.select(ddlbDefaultValue);
        customerGroup.setValue(StringUtils.EMPTY);
        productGroup.setValue(StringUtils.EMPTY);
        product.setValue("0");
        ndc.setValue(IndicatorConstants.SELECT_ONE.getConstant());
        custGroupSid = StringUtils.EMPTY;
        prodGroupSid = StringUtils.EMPTY;
    }

    /**
     * Reset Button Functionality
     *
     * @param event
     */
    @UiHandler("resetBtn2")
    public void resetBtn2Logic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, commonUtil.getHeaderMessage(), commonUtil.getResetMessage1(), new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    try {
                        custmerProdTableReset();
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    /**
     * Table Container Reset Logic
     */
    public void custmerProdTableReset() {

        Collection list = availableCustomer.getItemIds();
        for (Object obj : list) {
            availTreeContainer.getContainerProperty(obj, "checkRecord").setValue(false);
        }
    }

    /**
     * Reset Button Functionality
     *
     * @param event
     */
    @UiHandler("resetBtn3")
    public void resetBtn3Logic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, commonUtil.getHeaderMessage(), commonUtil.getResetMessage(), new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if ("YES".equalsIgnoreCase(buttonId.name())) {
                    try {
                        adjustmentSectionReset();
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    /**
     * Adjustment Section Reset Logic
     *
     */
    public void adjustmentSectionReset() {
        allocationMethod.setValue(null);
        salesBasis.setValue(null);
        suggestedAdj.setValue(StringUtils.EMPTY);
        fromDate.setValue(IndicatorConstants.SELECT_ONE.getConstant());
        toDate.setValue(IndicatorConstants.SELECT_ONE.getConstant());
        startDate.setValue(StringUtils.EMPTY);
        releaseTypeRadio.select(PERCENTAGE.getConstant());
        businessDays.setValue(StringUtils.EMPTY);
        releaseAmount.setValue(StringUtils.EMPTY);
        rateCorrection.setValue(StringUtils.EMPTY);
        totFixedDollAdj.setValue(StringUtils.EMPTY);
        glImpactDate.setValue(null);
        salesBasis.setEnabled(true);
        fromDate.setEnabled(true);
        toDate.setEnabled(true);
        rateCorrection.setEnabled(true);
        businessDays.setEnabled(true);
    }

    /**
     * The Calculate Button Logic - To do the calculation based on selected
     * allocation method
     *
     * @param event
     */
    @UiHandler("calculateBtn")
    public void calculateBtnLogic(Button.ClickEvent event) throws SystemException, ParseException {
        fdDto.setCalculate("1");
        try {
            if (allocationMethod.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1001"), CommonUtil.getMessage("FDA_MSG_1002"));
                return;
            } else if (allocationMethod.getValue() != null && "Sales".equals(allocationMethod.getValue().toString()) && "null".equals(String.valueOf(salesBasis.getValue()))) {
                AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1003"), CommonUtil.getMessage("FDA_MSG_1004"));
            } else if ("Sales".equals(allocationMethod.getValue().toString()) && ("null".equals(String.valueOf(fromDate.getValue())) || "null".equals(String.valueOf(toDate.getValue())))) {
                AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1005"), CommonUtil.getMessage("FDA_MSG_1006"));
            } else if (allocationMethod.getValue() != null && "Rate Adjustment".equals(allocationMethod.getValue().toString()) && "null".equals(String.valueOf(salesBasis.getValue()))) {
                AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1003"), CommonUtil.getMessage("FDA_MSG_1004"));
            } else if ("Accruals".equals(allocationMethod.getValue().toString()) && ("null".equals(String.valueOf(fromDate.getValue())) || "null".equals(String.valueOf(toDate.getValue())))) {
                AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1005"), CommonUtil.getMessage("FDA_MSG_1006"));
            } else if ("Actuals".equals(allocationMethod.getValue().toString()) && ("null".equals(String.valueOf(fromDate.getValue())) || "null".equals(String.valueOf(toDate.getValue())))) {
                AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1005"), CommonUtil.getMessage("FDA_MSG_1006"));
            } else {
                if (glImpactDate.getValue() != null && StringUtils.isNotBlank(String.valueOf(glImpactDate.getValue()))) {

                    if (suggestedAdj.getValue() != null && suggestedAdj.getValue() != StringUtils.EMPTY && releaseAmount.getValue() != StringUtils.EMPTY && releaseAmount.getValue() != null) {
                        Integer suggestedValue = Integer.parseInt(suggestedAdj.getValue().toString());
                        Integer ReleaseamountValue = Integer.parseInt(releaseAmount.getValue().toString());
                        Integer calculatedValue = 0;
                        if (releaseTypeRadio.getValue().equals("%")) {
                            calculatedValue = (suggestedValue * ReleaseamountValue) / 100;
                        }
                        if (releaseTypeRadio.getValue().equals("$")) {

                            if ("-".equals(releaseAmount.getValue().toString().charAt(0))) {
                                calculatedValue = suggestedValue - ReleaseamountValue;
                            } else {
                                calculatedValue = suggestedValue + ReleaseamountValue;
                            }
                        }
                        totFixedDollAdj.setValue(calculatedValue.toString());
                    }

                    String accountClosureMasterSid = fdDto.getAccountClosureMasterSid();
                    HelperDTO allocMethod = (HelperDTO) (allocationMethod.getValue());
                    final String method = allocMethod.getDescription();
                    Map<String, String> input = new HashMap();
                    input.put(QueryConstants.AC_MASTER_SID, accountClosureMasterSid);
                    QueryUtils.executeUpdateQuery(input, "deleteAcFdAdjustmentSelection");
                    AcFdAdjustmentSelection adjustselection = AcFdAdjustmentSelectionLocalServiceUtil.createAcFdAdjustmentSelection(0);
                    adjustselection.setAccClosureMasterSid(Integer.valueOf(accountClosureMasterSid));
                    adjustselection.setAllocationMethod(allocMethod.getId());
                    adjustselection.setCalculationFlag(true);

                    if (AUTO_RECONCILE.getConstant().equals(method)) {

                        String glDate = format.format(glImpactDate.getValue());
                        Date date = (Date) format.parse(glDate);
                        adjustselection.setGlImpactDate(date);
                        if (suggestedAdj.getValue() != null && suggestedAdj.getValue() != StringUtils.EMPTY) {
                            adjustselection.setSuggestedAdj(Double.parseDouble(suggestedAdj.getValue()));
                        }
                        Date SDate = (Date) format.parse(startDate.getValue());

                        adjustselection.setStartDate(SDate);

                        if (releaseTypeRadio.getValue().equals("%")) {
                            adjustselection.setReleaseType(false);
                        }
                        if (releaseTypeRadio.getValue().equals("$")) {
                            adjustselection.setReleaseType(true);
                        }
                        adjustselection.setBusinessDays(Integer.valueOf(businessDays.getValue()));

                        if (releaseAmount.getValue() != null && releaseAmount.getValue() != StringUtils.EMPTY && !releaseAmount.getValue().isEmpty()) {
                            adjustselection.setReleaseAmount(Double.parseDouble(releaseAmount.getValue().toString()));
                        }
                        if (totFixedDollAdj.getValue() != null && totFixedDollAdj.getValue() != StringUtils.EMPTY) {
                            adjustselection.setTotalFixedDollarAdj(Double.parseDouble(totFixedDollAdj.getValue().toString()));
                        }
                    } else if (SALES.getConstant().equals(method)) {
                        String glDate = format.format(glImpactDate.getValue());
                        Date date = (Date) format.parse(glDate);
                        adjustselection.setGlImpactDate(date);

                        Date SDate = (Date) format.parse(startDate.getValue());

                        adjustselection.setStartDate(SDate);
                        if (releaseTypeRadio.getValue().equals("%")) {
                            adjustselection.setReleaseType(false);
                        }
                        if (releaseTypeRadio.getValue().equals("$")) {
                            adjustselection.setReleaseType(true);
                        }
                        if (suggestedAdj.getValue() != null && suggestedAdj.getValue() != StringUtils.EMPTY) {
                            adjustselection.setSuggestedAdj(Double.parseDouble(suggestedAdj.getValue()));
                        }
                        adjustselection.setSalesBasis(((HelperDTO) (salesBasis.getValue())).getId());
                        adjustselection.setBusinessDays(Integer.valueOf(businessDays.getValue()));
                        if (releaseAmount.getValue() != null && releaseAmount.getValue() != StringUtils.EMPTY && !releaseAmount.getValue().isEmpty()) {
                            adjustselection.setReleaseAmount(Double.parseDouble(releaseAmount.getValue().toString()));
                        }

                        if (totFixedDollAdj.getValue() != null && totFixedDollAdj.getValue() != StringUtils.EMPTY) {
                            adjustselection.setTotalFixedDollarAdj(Double.parseDouble(totFixedDollAdj.getValue().toString()));
                        }
                        String forecastfromdate = fromDate.getValue().toString();
                        String forecasttodate = toDate.getValue().toString();
                        adjustselection.setMethodologyStartDate(forecastfromdate);
                        adjustselection.setMethodologyEndDate(forecasttodate);

                    } else if (RATE_ADJUSTMENT.getConstant().equals(method)) {
                        if (suggestedAdj.getValue() != null && suggestedAdj.getValue() != StringUtils.EMPTY) {
                            adjustselection.setSuggestedAdj(Double.parseDouble(suggestedAdj.getValue()));
                        }
                        String glDate = format.format(glImpactDate.getValue());
                        Date date = (Date) format.parse(glDate);
                        adjustselection.setGlImpactDate(date);

                        Date SDate = (Date) format.parse(startDate.getValue());

                        adjustselection.setStartDate(SDate);
                        if (releaseTypeRadio.getValue().equals("%")) {
                            adjustselection.setReleaseType(false);
                        }
                        if (releaseTypeRadio.getValue().equals("$")) {
                            adjustselection.setReleaseType(true);
                        }
                        if (releaseAmount.getValue() != null && releaseAmount.getValue() != StringUtils.EMPTY && !releaseAmount.getValue().isEmpty()) {
                            adjustselection.setReleaseAmount(Double.parseDouble(releaseAmount.getValue().toString()));
                        }
                        adjustselection.setSalesBasis(((HelperDTO) (salesBasis.getValue())).getId());
                        adjustselection.setBusinessDays(Integer.valueOf(businessDays.getValue()));

                        if (totFixedDollAdj.getValue() != null && totFixedDollAdj.getValue() != StringUtils.EMPTY) {
                            adjustselection.setTotalFixedDollarAdj(Double.parseDouble(totFixedDollAdj.getValue().toString()));
                        }
                        String forecastfromdate = fromDate.getValue().toString();
                        String forecasttodate = toDate.getValue().toString();
                        adjustselection.setMethodologyStartDate(forecastfromdate);
                        adjustselection.setMethodologyEndDate(forecasttodate);
                        if (rateCorrection.getValue() != null && rateCorrection.getValue() != StringUtils.EMPTY) {
                            adjustselection.setRateCorrection(Double.parseDouble(rateCorrection.getValue().toString()));

                        } else {
                            if (suggestedAdj.getValue() != null && suggestedAdj.getValue() != StringUtils.EMPTY) {
                                adjustselection.setRateCorrection(Double.parseDouble(suggestedAdj.getValue()));
                            }
                        }

                    } else if (ACCRUALS.getConstant().equals(method)) {
                        if (suggestedAdj.getValue() != null && suggestedAdj.getValue() != StringUtils.EMPTY) {
                            adjustselection.setSuggestedAdj(Double.parseDouble(suggestedAdj.getValue()));
                        }
                        if (releaseAmount.getValue() != null && releaseAmount.getValue() != StringUtils.EMPTY && !releaseAmount.getValue().isEmpty()) {
                            adjustselection.setReleaseAmount(Double.parseDouble(releaseAmount.getValue().toString()));
                        }

                        String glDate = format.format(glImpactDate.getValue());
                        Date date = (Date) format.parse(glDate);
                        adjustselection.setGlImpactDate(date);

                        Date SDate = (Date) format.parse(startDate.getValue());

                        adjustselection.setStartDate(SDate);
                        if (releaseTypeRadio.getValue().equals("%")) {
                            adjustselection.setReleaseType(false);
                        }
                        if (releaseTypeRadio.getValue().equals("$")) {
                            adjustselection.setReleaseType(true);
                        }

                        adjustselection.setBusinessDays(Integer.valueOf(businessDays.getValue()));

                        if (totFixedDollAdj.getValue() != null && totFixedDollAdj.getValue() != StringUtils.EMPTY) {
                            adjustselection.setTotalFixedDollarAdj(Double.parseDouble(totFixedDollAdj.getValue().toString()));
                        }
                        String forecastfromdate = fromDate.getValue().toString();
                        String forecasttodate = toDate.getValue().toString();
                        adjustselection.setMethodologyStartDate(forecastfromdate);
                        adjustselection.setMethodologyEndDate(forecasttodate);

                    } else if (ACTUALS.getConstant().equals(method)) {
                        if (suggestedAdj.getValue() != null && suggestedAdj.getValue() != StringUtils.EMPTY) {
                            adjustselection.setSuggestedAdj(Double.parseDouble(suggestedAdj.getValue()));
                        }
                        if (releaseAmount.getValue() != null && releaseAmount.getValue() != StringUtils.EMPTY && !releaseAmount.getValue().isEmpty()) {
                            adjustselection.setReleaseAmount(Double.parseDouble(releaseAmount.getValue().toString()));
                        }
                        String glDate = format.format(glImpactDate.getValue());
                        Date date = (Date) format.parse(glDate);
                        adjustselection.setGlImpactDate(date);

                        Date SDate = (Date) format.parse(startDate.getValue());

                        adjustselection.setStartDate(SDate);
                        if (releaseTypeRadio.getValue().equals("%")) {
                            adjustselection.setReleaseType(false);
                        }
                        if (releaseTypeRadio.getValue().equals("$")) {
                            adjustselection.setReleaseType(true);
                        }

                        adjustselection.setBusinessDays(Integer.valueOf(businessDays.getValue()));

                        if (totFixedDollAdj.getValue() != null && totFixedDollAdj.getValue() != StringUtils.EMPTY) {
                            adjustselection.setTotalFixedDollarAdj(Double.parseDouble(totFixedDollAdj.getValue().toString()));
                        }
                        String forecastfromdate = fromDate.getValue().toString();
                        String forecasttodate = toDate.getValue().toString();
                        adjustselection.setMethodologyStartDate(forecastfromdate);
                        adjustselection.setMethodologyEndDate(forecasttodate);

                    }

                    AcFdAdjustmentSelectionLocalServiceUtil.addAcFdAdjustmentSelection(adjustselection);

                    resultsTable.removeAllItems();
                    Object[] orderedArgs = {accountClosureMasterSid};
                    CommonLogic.callProcedure("PRC_AC_FIXED_DOLLAR_ADJUST", orderedArgs);
                    loadFixedDollarResultsTreeAtCalculation();
                    Setadjustmentimpacttable(fdDto.getAccountClosureMasterSid());
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    /**
     * The Populate Button Logic - To populate the Customer/Product Details
     * based on selected data
     *
     * @param event
     */
    @UiHandler("populateBtn1")
    public void populateBtn1Logic(Button.ClickEvent event) {
        String companyVal = String.valueOf(company.getValue());

        if ((StringUtils.isBlank(String.valueOf(company.getValue())) || ("null".equals(companyVal)))
                || (StringUtils.isBlank(String.valueOf(marketType.getValue())) || ("null".equals(String.valueOf(marketType.getValue()))))
                || (StringUtils.isBlank(String.valueOf(acctType.getValue())) || ("null".equals(String.valueOf(acctType.getValue()))))
                || (StringUtils.isBlank(String.valueOf(adjustmentType.getValue())) || ("null".equals(String.valueOf(adjustmentType.getValue()))))
                || (StringUtils.isBlank(String.valueOf(accrualPeriod.getValue())) || ("null".equals(String.valueOf(accrualPeriod.getValue()))))
                || (StringUtils.isBlank(String.valueOf(contract.getValue())) || ("null".equals(String.valueOf(contract.getValue()))))
                || (StringUtils.isBlank(String.valueOf(product.getValue())) || ("null".equals(String.valueOf(product.getValue()))))
                || (StringUtils.isBlank(String.valueOf(ndc.getValue())) || ("null".equals(String.valueOf(ndc.getValue()))))) {

            AbstractNotificationUtils.getErrorNotification(commonUtil.getMessage("FDA_MSG_1007"), commonUtil.getMessage("FDA_MSG_1008"));

        } else {

            LoadCustomerProductTree();
        }
    }

    /**
     * The Populate Button Click Logic
     *
     * @param event
     */
    @UiHandler("populateBtn2")
    public void populateBtn2Logic(Button.ClickEvent event) {
        resultsTreeContainer.removeAllItems();
        Collection<?> checkList = availableCustomer.getItemIds();
        Boolean flag = false;
        fdList.isEmpty();
        fdList.clear();
        for (Object item : checkList) {
            Boolean checked = (Boolean) availTreeContainer.getContainerProperty(item, Constants.CHECK_RECORD).getValue();

            if (checked) {
                String comSid = String.valueOf(availTreeContainer.getContainerProperty(item, "comapnySid").getValue());
                String itemSid = String.valueOf(availTreeContainer.getContainerProperty(item, "itemSid").getValue());
                String ccpSid = String.valueOf(availTreeContainer.getContainerProperty(item, "ccpSid").getValue());

                setFdValues(ccpSid, comSid);

                flag = true;
            }
        }
        if (!flag) {
            AbstractNotificationUtils.getErrorNotification(commonUtil.getMessage("FDA_MSG_1001"), commonUtil.getMessage("FDA_MSG_1009"));
            return;
        }

        saveFixedDollarDataSelection();
        fdDto.setCalculate("0");
        loadFixedDollarResultsTree();
    }

    public void setFdValues(String ccpSid, String comSid) {

        fdDto = new FixedDollarDTO();
        fdDto.setCcpSid(ccpSid);
        fdDto.setCurrentCCpid(ccpSid);
        fdDto.setContractSid(dto.getContractSid());
        fdDto.setItemSid(dto.getItemSid());
        fdDto.setCompanyMasterSid(comSid);
        fdDto.setSessionId(sessionId);
        fdList.add(fdDto);

    }

    /**
     * Insert Fixed Dollar Selection to Account Close Master Table
     *
     */
    public void saveFixedDollarDataSelection() {
        saveFixedDollarDetails();
    }

    public void saveFixedDollarDetails() {
        FixedDollarSaveDTO saveDto = new FixedDollarSaveDTO();
        int marKVal = ((HelperDTO) (marketType.getValue())).getId();
        String acctTypeVal = String.valueOf(acctType.getValue());
        try {

            saveDto.setGlCompanyMasterSid(Integer.valueOf(company.getValue().toString()));
            saveDto.setContractType(marKVal);
            saveDto.setRsType(Integer.valueOf(acctTypeVal));
            saveDto.setRebateProgramType(acctSubType.getValue() != null && !"null".equals(String.valueOf(acctSubType.getValue())) ? Integer.valueOf(acctSubType.getValue().toString()) : 0);
            saveDto.setAdjustmentType(adjustmentType.getValue() != null && !"null".equals(String.valueOf(adjustmentType.getValue())) ? ((HelperDTO) adjustmentType.getValue()).getId() : 0);
            saveDto.setAccrualPeriod(accrualPeriod.getValue().toString());
            if (custGroupSid != null && !StringUtils.EMPTY.equals(custGroupSid)) {
                saveDto.setCompanyGroupSid(custGroupSid);
            } else {
                saveDto.setCompanyGroupSid(null);
            }
            saveDto.setContractMasterSid(contract.getValue() != null && !"null".equals(String.valueOf(contract.getValue())) ? Integer.valueOf(String.valueOf(contract.getValue())) : 0);
            if (prodGroupSid != null && !StringUtils.EMPTY.equals(prodGroupSid)) {
                saveDto.setItemGroupSid(prodGroupSid);
            } else {
                saveDto.setItemGroupSid(null);
            }
            saveDto.setItemMasterSid(product.getValue() != null && !"null".equals(String.valueOf(product.getValue())) ? Integer.valueOf(String.valueOf(product.getValue())) : 0);
            saveDto.setProductIdentifier(String.valueOf(ndc.getValue()));
            saveDto.setWorkflowStatus(0);
            saveDto.setModuleType("FIXED_DOLLAR");

            int accClosureMasterSid = fdLogic.saveFixedDollarDetails(saveDto, fdList);
            fdDto.setAccountClosureMasterSid(String.valueOf(accClosureMasterSid));
            setAccountMasterId(accClosureMasterSid);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Method to Load Fixed Dollar Results
     *
     */
    public void loadFixedDollarResultsTree() {
        String ccpid = StringUtils.EMPTY;
        for (int i = 0; i < fdList.size(); i++) {
            FixedDollarDTO test = fdList.get(i);
            ccpid = ccpid + test.getCcpSid() + ",";
        }
        if (ccpid != StringUtils.EMPTY) {
            ccpid = ccpid.substring(0, ccpid.length() - 1);
        }
        fdDto.setSelectedCCplist(ccpid);
        LOGGER.info("Entering loadFixedDollarResultsTree method");
        try {
            fdResultLogic.setData(fdDto);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("End of loadFixedDollarResultsTree method" + availTreeContainer.size());
    }

    public void setDataToDTO() {
        FixedDollarDTO dto = new FixedDollarDTO();
        dto.setCompanyId(String.valueOf(company.getValue()));
        dto.setMarketType(String.valueOf(marketType.getValue()));
        dto.setAcctType(String.valueOf(acctType.getValue()));
        dto.setAcctSubType(String.valueOf(acctSubType.getValue()));
        dto.setAdjustmentType(String.valueOf(adjustmentType.getValue()));
        dto.setAccrualPeriod(String.valueOf(accrualPeriod.getValue()));
        dto.setCustomerGroup(String.valueOf(customerGroup.getValue()));
        dto.setContract(String.valueOf(contract.getValue()));
        dto.setProductGroup(String.valueOf(productGroup.getValue()));
        dto.setProduct(String.valueOf(product.getValue()));
        dto.setProductIdentifier(String.valueOf(ndc.getValue()));
    }

    public void detachListeners(final AbstractField field) {

        List<Property.ValueChangeListener> listeners;

        listeners = (List<Property.ValueChangeListener>) field.getListeners(Property.ValueChangeEvent.class);
        for (final Property.ValueChangeListener object : listeners) {
            field.removeValueChangeListener(object);
        }

    }

    public void attachListeners(final AbstractField field) {

        field.setImmediate(true);
        field.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                try {
                    acctType(event);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });

    }

    @UiHandler("allocationMethod")
    public void allocationMethodmethod(Property.ValueChangeEvent event) throws Exception {
        if (allocationMethod.getValue() != null) {
            final String method = String.valueOf(allocationMethod.getValue());
            if (AUTO_RECONCILE.getConstant().equals(method)) {

                if (session.getAcctCloserMasterId() == 0) {
                    salesBasis.setEnabled(false);
                    fromDate.setEnabled(false);
                    toDate.setEnabled(false);
                    rateCorrection.setEnabled(false);
                    businessDays.setEnabled(true);
                }
                fixedDollarResultsTable("Auto Reconcile");
            } else if (SALES.getConstant().equals(method)) {
                if (session.getAcctCloserMasterId() == 0) {
                    salesBasis.setEnabled(true);
                    fromDate.setEnabled(true);
                    toDate.setEnabled(true);
                    rateCorrection.setEnabled(false);
                    businessDays.setEnabled(true);
                }
            } else if (RATE_ADJUSTMENT.getConstant().equals(method)) {
                if (session.getAcctCloserMasterId() == 0) {
                    salesBasis.setEnabled(true);
                    fromDate.setEnabled(true);
                    toDate.setEnabled(true);
                    rateCorrection.setEnabled(true);
                    businessDays.setEnabled(true);
                }
            } else if (ACCRUALS.getConstant().equals(method)) {
                if (session.getAcctCloserMasterId() == 0) {
                    salesBasis.setEnabled(false);
                    fromDate.setEnabled(true);
                    toDate.setEnabled(true);
                    rateCorrection.setEnabled(false);
                    businessDays.setEnabled(true);
                }
                fixedDollarResultsTable("Accruals");
            } else if (ACTUALS.getConstant().equals(method)) {
                if (session.getAcctCloserMasterId() == 0) {
                    salesBasis.setEnabled(false);
                    fromDate.setEnabled(true);
                    toDate.setEnabled(true);
                    rateCorrection.setEnabled(false);
                    businessDays.setEnabled(true);
                }
                fixedDollarResultsTable("Actuals");
            } else if (MANUAL.getConstant().equals(method)) {
                if (session.getAcctCloserMasterId() == 0) {
                    salesBasis.setEnabled(true);
                    fromDate.setEnabled(true);
                    toDate.setEnabled(true);
                    rateCorrection.setEnabled(false);
                    businessDays.setEnabled(true);
                }
                fixedDollarResultsTable("Auto Reconcile");
            }
        }
    }

    @UiHandler("salesBasis")
    public void salesBasismethod(Property.ValueChangeEvent event) throws Exception {

        if (allocationMethod.getValue() != null) {
            final String method = String.valueOf(allocationMethod.getValue());
            fixedDollarResultsTable(method);
        }
    }

    public Integer getAccountMasterId() {
        return accountMasterId;
    }

    public void setAccountMasterId(Integer accountMasterId) {
        this.accountMasterId = accountMasterId;
    }

    @UiHandler("exportBtn")
    public void excelBtnClick(Button.ClickEvent event) {

        try {
            boolean firstGenerated = false;
            resultsTableLayout.addComponent(excelTable);
            excelTable.setVisible(false);
            excelContainer = new ExtTreeContainer<FixedDollarDTO>(FixedDollarDTO.class,DataStructureMode.MAP);
            excelContainer.setColumnProperties(fullResultHeader.getProperties());
            excelTable.setContainerDataSource(excelContainer);

            excelTable.setVisibleColumns(fullResultHeader.getSingleColumns().toArray());
            excelTable.setColumnHeaders(fullResultHeader.getSingleHeaders().toArray(new String[fullResultHeader.getSingleHeaders().size()]));
            fdDto.setLevelNo(1);

            excelContainer.setColumnProperties(rightResultDTO.getProperties());

            List<FixedDollarDTO> report = fdaLogic.getConfigureFixedDollarLoadData(new Object(), fdDto);

            excelContainer.removeAllItems();
            loadDataToContainer(report, null);

            Map<String, String> formatter = new HashMap<String, String>();
            ExcelExport export = new ExcelExport(new ExtCustomTableHolder(excelTable), "Adjustment Detail", "Adjustment Detail", "FDA.xls", false);
            export.export();
            resultsTableLayout.removeComponent(excelTable);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        resultsTableLayout.removeComponent(excelTable);

    }

    @UiHandler("viewTypeRadio")
    public void viewTypeRadiochange(Property.ValueChangeEvent event) throws Exception {
        if (resultsTreeContainer.size() > 0) {
            resultsTreeContainer.removeAllItems();
            resultsTable.removeAllItems();
            if (viewTypeRadio.getValue().toString().equalsIgnoreCase("Product")) {

                filterDdlb.addItem(NDC8.getConstant());
                filterDdlb.addItem(NDC10.getConstant());
                filterDdlb.addItem(NDC11.getConstant());
                fdDto.setViewtype("1");

            }
            if (viewTypeRadio.getValue().toString().equalsIgnoreCase("Customer")) {

                filterDdlb.removeItem(NDC8.getConstant());
                filterDdlb.removeItem(NDC10.getConstant());
                filterDdlb.removeItem(NDC11.getConstant());
                fdDto.setViewtype("0");

            }
            loadFixedDollarResultsTree();
        }

    }

    @UiHandler("filterDdlb")
    public void filterDdlbchange(Property.ValueChangeEvent event) throws Exception {

        if (viewTypeRadio.getValue().toString().equalsIgnoreCase("Product")) {

            fdDto.setViewtype("1");

        }
        if (viewTypeRadio.getValue().toString().equalsIgnoreCase("Customer")) {

            fdDto.setViewtype("0");

        }
        loadFixedDollarResultsTree();
    }

    public void loadFixedDollarResultsTreeAtCalculation() {
        try {

            resultsTreeContainer.removeAllItems();
            resultsTable.removeAllItems();
            fdResultLogic.setData(fdDto);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void Setadjustmentimpacttable(String accountClosureMasterSid) {
        Map<String, String> input = new HashMap();
        input.put(QueryConstants.AC_MASTER_SID, accountClosureMasterSid);
        adjustmentImpactTable.removeAllItems();
        adjustmentImpactContainer.removeAllItems();
        List DTOlist = new ArrayList();
        List list = (List) QueryUtils.executeSelectQuery(input, "setAdjustmentImpact");
        if (list != null) {

            for (int i = 0; i < list.size(); i++) {
                FixedDollarDTO DTO = new FixedDollarDTO();
                Object[] obje = (Object[]) list.get(i);
                DTO.setYear(obje[0].toString());
                DTO.setActuals(obje[1].toString());
                DTO.setAccruals(obje[2].toString());
                DTO.setRemainingEstimate(obje[3].toString());
                DTO.setProjectedRemainingEstimate(obje[4].toString());
                DTOlist.add(DTO);
            }

        }
        adjustmentImpactContainer.addAll(DTOlist);
        configureAdjustmentImpactTable();
    }

    public void saveAccruals() {

        LOGGER.info("Entering saveAccruals Procedure" + fdDto.getAccountClosureMasterSid());

        Object[] orderedArgs = {fdDto.getAccountClosureMasterSid()};
        CommonLogic.callProcedure("PRC_AC_ACCRUAL_DETAILS_INSERT", orderedArgs);

        LOGGER.info("Ending saveAccruals Procedure");

    }

    public FixedDollarDTO loadvalues(FixedDollarDTO dto) {

        dto.setCompanyMasterSid(String.valueOf(company.getItemCaption(company.getValue())));
        dto.setMarketType(String.valueOf(marketType.getItemCaption(marketType.getValue())));
        dto.setAcctType(String.valueOf(acctType.getItemCaption(acctType.getValue())));
        dto.setAcctSubType(String.valueOf(acctSubType.getItemCaption(acctSubType.getValue())));
        dto.setContractSid(String.valueOf(contract.getItemCaption(contract.getValue())));
        dto.setProduct(String.valueOf(product.getItemCaption(product.getValue())));
        dto.setProductIdentifier(String.valueOf(ndc.getItemCaption(ndc.getValue())));
        dto.setCustomerGroup(customerGroup.getValue());
        dto.setProductGroup(productGroup.getValue());
        dto.setMethodology(String.valueOf(allocationMethod.getValue()));
        return dto;

    }

    public void loadDataToContainer(List<FixedDollarDTO> report, Object parentId) {
        try {
            LOGGER.info("Inside loadDataToContainer" + report.size());
            for (FixedDollarDTO dto : report) {

                excelContainer.addBean(dto);
                if (parentId != null) {
                    excelContainer.setParent(dto, parentId);
                }

                if (dto.getParent() == 1) {
                    excelContainer.setChildrenAllowed(dto, true);
                    addLowerLevelsForExport(dto);
                } else {
                    excelContainer.setChildrenAllowed(dto, false);

                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ended loadDataToContainer");

    }

    public void addLowerLevelsForExport(Object id) {
        LOGGER.info("Inside addLowerLevelsForExport");
        List<FixedDollarDTO> report = fdaLogic.getConfigureFixedDollarLoadData(id, fdDto);
        loadDataToContainer(report, id);
        LOGGER.info("Ended addLowerLevelsForExport");
    }

    public void loadEditValues(boolean status) {
        LOGGER.info("Entering loadEditValues" + session.getAcctCloserMasterId());
        try {
            if ("Withdrawn".equalsIgnoreCase(session.getWorkflowStatus())) {
                populateBtn2.setEnabled(false);
                populateBtn1.setEnabled(false);
                resetBtn1.setEnabled(false);
                resetBtn2.setEnabled(false);
            } else {
                populateBtn2.setEnabled(status);
                populateBtn1.setEnabled(status);
                resetBtn1.setEnabled(status);
                resetBtn2.setEnabled(status);

            }
            resetBtn3.setEnabled(status);
            calculateBtn.setEnabled(status);
            company.setEnabled(status);
            marketType.setEnabled(status);
            acctType.setEnabled(status);
            acctSubType.setEnabled(status);
            adjustmentType.setEnabled(status);
            accrualPeriod.setEnabled(status);
            customerGroup.setEnabled(status);
            contract.setEnabled(status);
            productGroup.setEnabled(status);
            product.setEnabled(status);
            ndc.setEnabled(status);
            allocationMethod.setEnabled(status);
            glImpactDate.setEnabled(status);
            suggestedAdj.setEnabled(status);
            startDate.setEnabled(status);
            fromDate.setEnabled(status);
            toDate.setEnabled(status);
            businessDays.setEnabled(status);
            releaseAmount.setEnabled(status);
            rateCorrection.setEnabled(status);
            totFixedDollAdj.setEnabled(status);
            AccClosureMaster accMaster = AccClosureMasterLocalServiceUtil.getAccClosureMaster(session.getAcctCloserMasterId());
            company.setValue(StringUtils.EMPTY + accMaster.getGlCompanyMasterSid());

            marketType.setValue(StringUtils.EMPTY + accMaster.getContractType());
            acctType.setValue(StringUtils.EMPTY + accMaster.getRsType());

            acctSubType.setValue(accMaster.getRsCategory());
            contract.setValue(accMaster.getContractMasterSid());
            accrualPeriod.setValue(accMaster.getAccrualPeriod());
            adjustmentType.setValue(accMaster.getAdjustmentType());
            product.setValue(accMaster.getItemMasterSid());
            ndc.setValue(accMaster.getProductIdentifier());
            dto.setAccSidFromworkflow("1");
            dto.setAccSidFromworkflowvalue(String.valueOf(session.getAcctCloserMasterId()));
            LoadCustomerProductTree();
            Map<String, String> input = new HashMap();
            input.put(QueryConstants.AC_MASTER_SID, String.valueOf(session.getAcctCloserMasterId()));
            List list1 = (List) QueryUtils.executeSelectQuery(input, "getAcFdAdjustmentSelection");
            Object[] obj = (Object[]) list1.get(0);

            if (obj[1] != null && !obj[1].toString().equalsIgnoreCase("0")) {
                salesBasis.setValue(obj[1].toString());

            } else {
                salesBasis.setEnabled(false);

            }
            fromDate.setValue(obj[2].toString());
            toDate.setValue(obj[3].toString());
            Date SDate = (Date) FORMAT.parse(obj[4].toString());
            glImpactDate.setValue(SDate);
            Date SDate1 = (Date) FORMAT.parse(obj[5].toString());
            String date2 = format.format(SDate1);
            startDate.setValue(date2);
            businessDays.setValue(obj[6].toString());
            if (obj[7] != null) {
                rateCorrection.setValue(String.format("%.0f", new BigDecimal(obj[7].toString())));
            }
            if (obj[8] != null) {
                suggestedAdj.setValue(String.format("%.0f", new BigDecimal(obj[8].toString())));
            }

            releaseTypeRadio.removeAllItems();
            releaseTypeRadio.addItem(PERCENTAGE.getConstant());
            releaseTypeRadio.addItem(DOLLAR.getConstant());
            if (obj[9].toString().equalsIgnoreCase("false")) {
                releaseTypeRadio.select(PERCENTAGE.getConstant());
            }
            if (obj[9].toString().equalsIgnoreCase("true")) {
                releaseTypeRadio.select(DOLLAR.getConstant());

            }
            if (obj[10] != null) {
                releaseAmount.setValue(String.format("%.0f", new BigDecimal(obj[10].toString())));
            }
            totFixedDollAdj.setValue(String.format("%.0f", new BigDecimal(obj[11].toString())));
            allocationMethod.setValue(obj[0].toString());
            fdDto.setContractSid(String.valueOf(accMaster.getContractMasterSid()));
            fdDto.setItemSid(String.valueOf(accMaster.getItemMasterSid()));
            fdDto.setCalculate("1");
            fdDto.setAccountClosureMasterSid(String.valueOf(session.getAcctCloserMasterId()));
            loadFixedDollarResultsTree();
            Setadjustmentimpacttable(String.valueOf(session.getAcctCloserMasterId()));

        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ending loadEditValues");
    }

    public String getFinalAllocationMethod() {
        return finalAllocationMethod;
    }

    public void setFinalAllocationMethod(String finalAllocationMethod) {
        this.finalAllocationMethod = finalAllocationMethod;
    }
}
