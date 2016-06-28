/**
 *
 */
package com.stpl.app.contract.dashboard.ui.form;

import com.stpl.addons.tableexport.TemporaryFileDownloadResource;
import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.common.util.HelperListUtil;
import com.stpl.app.contract.contractheader.logic.ContractHeaderLogic;
import com.stpl.app.contract.dashboard.dto.NepFormulaLookUpDTO;
import com.stpl.app.contract.dashboard.dto.NetSalesRuleLookupDto;
import com.stpl.app.contract.dashboard.dto.PriceProtectionFormulaDTO;
import com.stpl.app.contract.dashboard.dto.RebateDetailsGenerator;
import com.stpl.app.contract.dashboard.dto.RebatePlanDTO;
import com.stpl.app.contract.dashboard.dto.TempRebateDTO;
import com.stpl.app.contract.dashboard.ui.lazyload.LazyLoadCriteria;
import com.stpl.app.contract.dashboard.ui.lazyload.TempRebateContainer;
import com.stpl.app.contract.dashboard.ui.lazyload.TempViewRebateContainer;
import com.stpl.app.contract.dashboard.ui.lookup.FormulaLookup;
import com.stpl.app.contract.dashboard.ui.lookup.NetSalesFormulaLookup;
import com.stpl.app.contract.dashboard.ui.lookup.NetSalesRuleLookup;
import com.stpl.app.contract.dashboard.ui.lookup.RebatePlanLookup;
import com.stpl.app.contract.dashboard.ui.lookup.RsDeductionLookup;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.dashboard.util.ExcelExportUtil;
import com.stpl.app.contract.global.dto.RSItemTableGenerator;
import com.stpl.app.contract.global.dto.RebatePlanNameContainer;
import com.stpl.app.contract.global.dto.RebatePlanNameCriteria;
import com.stpl.app.contract.global.dto.RsDeductionLookupDto;
import com.stpl.app.contract.global.dto.RsItemDetailsDTO;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.stpl.app.contract.global.util.CommonUtils;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.CHFunctionNameUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.app.contract.util.ResponsiveUtils;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.CustomePagedFilterTable;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.ifs.util.UISecurityUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * @author sibi
 *
 */
public class RebateSetup extends CustomComponent {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private final static Logger LOGGER = Logger.getLogger(RebateSetup.class);
    
    @UiField("hlayout")
    private HorizontalLayout hlayout;
    
    @UiField("cssLayout")
    private CssLayout cssLayout;
    
    @UiField("resultsTableLayout")
    private VerticalLayout resultsTableLayout;
    
    @UiField("massCheck")
    private OptionGroup massCheck;
    
    @UiField("massField")
    private CustomComboBox massField;
    
    @UiField("massDate")
    private PopupDateField massDate;
    
    @UiField("massValue")
    private TextField massValue;
    
    @UiField("rebatePlanNameDdlb")
    private CustomComboBox rebatePlanNameDdlb;
    
    @UiField("btnPopulate")
    private Button btnPopulate;
    @UiField("btnRemove")
    private Button btnRemove;
    
    @UiField("btnAllPopulate")
    private Button btnAllPopulate;
    
    @UiField("itemDetailsTable")
    private CustomePagedFilterTable itemDetailsTable;
    
    @UiField("viewItemDetailsTable")
    private Table viewItemDetailsTable;
    /**
     * Record checkbox
     */
    @UiField("record")
    private OptionGroup record;
    @UiField("resultsPanel")
    private Panel resultsPanel;

    /**
     * The rs item details dto.
     */
    private RsItemDetailsDTO rsItemDetailsDTO = new RsItemDetailsDTO();
    
    com.stpl.app.contract.util.CommonUIUtils commonUtils = new com.stpl.app.contract.util.CommonUIUtils();

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
     * The field mass.
     */
    private String fieldMass = "";

    /**
     * The map.
     */
    private final Map<String, String> map = new HashMap<String, String>();

    /**
     * The ifp logic.
     */
    private final IfpLogic ifpLogic;
    
    private LazyBeanItemContainer tempLazyContainer;
    
    LazyLoadCriteria lazyLoadCriteria = new LazyLoadCriteria();
    
    final boolean isEditable;
    SessionDTO sessionDTO;
    final ContractHeaderLogic contractLogic = new ContractHeaderLogic();
    /**
     * *
     * CommonSecurityLogic instance
     */
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    
    final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
    
    private final Map<Integer, Boolean> reloadMap = new HashMap<Integer, Boolean>();
    
    TempViewRebateContainer viewRebateContainer;
    TempRebateContainer rebateContainer;
    
    private Map<String, List> tempDate;
    
    Date[] dates = new Date[2];

    /**
     * The rebate list.
     */
    private final List<TempRebateDTO> rebateList = new ArrayList<TempRebateDTO>();
    
    private final Map<Integer, Boolean> reloadVerticalLayoutTabThreeMap = new HashMap<Integer, Boolean>();
    List<Object> resultList;
    Map<String, AppPermission> contractDashboard;
    String mode = StringUtils.EMPTY;
    @UiField("massLookup")
    private CustomTextField massLookup;
    @UiField("valueLB")
    private Label valueLB;
    RebatePlanLookup rebatePlanLookup = null;
    FormulaLookup formulaLookup = null;
    NetSalesFormulaLookup netSalesLookup = null;
    RsDeductionLookup rsDeductionLookup;
    NetSalesRuleLookup calculationLookup = null;
    HelperListUtil helperList = HelperListUtil.getInstance();

    /**
     * The Constructor.
     *
     * @param rebateScheduleMaster the rebate schedule master
     * @param rebateBinder the rebate binder
     * @param rsDetailsResultsBean the rs details results bean
     */
    public RebateSetup(final RsItemDetailsDTO rsItemDetailsDTO, final CustomFieldGroup rebateBinder, final BeanItemContainer<TempRebateDTO> rsDetailsResultsBean, final CustomFieldGroup contractBinder,
            final boolean isEditable, final SessionDTO sessionDTO) throws SystemException, PortalException, Exception {
        super();
        this.rsItemDetailsDTO = rsItemDetailsDTO;
        this.contractBinder = contractBinder;
        this.rsDetailsResultsBean = rsDetailsResultsBean;
        this.rebateBinder = rebateBinder;
        this.isEditable = isEditable;
        this.sessionDTO = sessionDTO;
        ifpLogic = new IfpLogic(this.sessionDTO);
        LOGGER.info("Entering RebateScheduleAddForm");
        this.setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-dashboard/rebate-setup.xml"), this));
        init();
        LOGGER.info("End of RebateScheduleAddForm");
    }
    
    public void loadDates(Date startDate, Date endDate) {
        dates[0] = startDate;
        dates[1] = endDate;
    }
    
    private void init() {
        try {
            btnRemove.setVisible(isEditable);
            record.addItems("Current", "History", "Future");
            record.addValueChangeListener(new Property.ValueChangeListener() {
                
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    String value = String.valueOf(record.getValue());
                    if (!isEditable) {
                        loadTempRs(value);
                        loadBasedOnCalculationType(String.valueOf(rebateBinder.getField("calculationType").getValue()));
                    } else {
                        rebateContainer.setRecord(value);
                        itemDetailsTable.setCurrentPage(1);
                    }
                }
            });
            
            final StplSecurity stplSecurity = new StplSecurity();
            contractDashboard = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + "," + "Rebate Setup", false);
            addResponsiveGrid(contractDashboard);
            if (isEditable) {
                mode = ConstantsUtils.EDIT;
                configureButton();
                configureFields();
                addItemDetailsTable();
            } else {
                mode = ConstantsUtils.VIEW;
                configureFieldsOnView();
                addItemDetailsTableForView();
                viewItemDetailsTable.setReadOnly(true);
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }
    
    private void configureFieldsOnView() {
        hlayout.setVisible(false);
        itemDetailsTable.setVisible(false);
    }
    
    private void configureButton() throws PortalException, SystemException, Exception {
        
        final StplSecurity stplSecurity = new StplSecurity();
        final Map<String, AppPermission> funContractHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + "," + "Rebate Setup");
        if ((funContractHM.get(CHFunctionNameUtils.RebatePopulate) != null) && !((AppPermission) funContractHM.get(CHFunctionNameUtils.RebatePopulate)).isFunctionFlag()) {
            btnPopulate.setVisible(false);
        } else {
            addBtnPopulate();
        }
        if ((funContractHM.get(CHFunctionNameUtils.RebatePopulateAll) != null) && !((AppPermission) funContractHM.get(CHFunctionNameUtils.RebatePopulateAll)).isFunctionFlag()) {
            btnAllPopulate.setVisible(false);
        } else {
            addBtnAllPopulate();
        }
        
    }

    /**
     * Configure fields.
     */
    public void configureFields() throws SystemException, Exception {
        LOGGER.info("Entering configureFields method");
        try {
            
            massCheck.addItem("Enable");
            massCheck.addItem("Disable");
            massCheck.setValue("Disable");
            massCheck.setImmediate(true);
            massCheck.addStyleName("horizontaladd");
            new CommonUtils().getSelectNull(massField);
            massField.setNullSelectionAllowed(true);
            massField.setNullSelectionItemId(Constants.SELECT_ONE);
            
            massField.addItem("Rebate Plan Name");
            massField.addItem("Start Date");
            massField.addItem("End Date");
            massField.setImmediate(true);
            massField.select(Constants.SELECT_ONE);
            massField.setEnabled(false);
            
            rebatePlanNameDdlb.setVisible(false);
            rebatePlanNameDdlb.setPageLength(7);
            rebatePlanNameDdlb.setImmediate(true);
            rebatePlanNameDdlb.setNullSelectionAllowed(true);
            rebatePlanNameDdlb.setInputPrompt(Constants.SELECT_ONE);
            HelperDTO NULL_HELPER_DTO = new HelperDTO(Constants.SELECT_ONE);
            rebatePlanNameDdlb.setNullSelectionItemId(NULL_HELPER_DTO);
            rebatePlanNameDdlb.setItemCaptionPropertyId(Constants.DESCRIPTION);
            rebatePlanNameDdlb.markAsDirty();
            final LazyContainer rebatePlanContainer = new LazyContainer(HelperDTO.class, new RebatePlanNameContainer((HelperDTO) rebatePlanNameDdlb.getValue()), new RebatePlanNameCriteria());
            rebatePlanContainer.setMinFilterLength(0);
            rebatePlanNameDdlb.setContainerDataSource(rebatePlanContainer);
            rebatePlanNameDdlb.select(NULL_HELPER_DTO);
            rebatePlanNameDdlb.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in end date, function will be
                 * executed.
                 */
                public void valueChange(final ValueChangeEvent event) {
                    rebatePlanNameDdlb.setDescription(String.valueOf(rebatePlanNameDdlb.getValue()));
                }
            });
            
            massValue.setVisible(false);
            massValue.setImmediate(true);
            
            massDate.setDateFormat(Constants.MM_DD_YYYY);
            massDate.setImmediate(true);
            massDate.setValidationVisible(true);
            massDate.setVisible(false);
            massDate.setId("RebateMassDate");
            massDate.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used to click text field and its listener.
                 */
                public void valueChange(final ValueChangeEvent event) {
                    massDate.setDescription(com.stpl.app.contract.util.CommonUIUtils.convert2DigitTo4DigitYear(massDate.getValue()));
                }
            });
            massValue.setImmediate(true);
            massValue.setVisible(false);
            
            massField.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used massField for value change listener
                 */
                public void valueChange(final ValueChangeEvent event) {
                    try {
                        final String value = String.valueOf(massField.getValue());
                        CommonUtil commonUtil = new CommonUtil();
                        massField.setDescription(value);
                        if (ContractUtils.REBATE_AMOUNT.equals(value) || "Bundle No".equals(value) || ContractUtils.EVALUATION_RULE_BUNDLE.equals(value) || ContractUtils.CALCULATION_RULE_BUNDLE.equals(value)) {
                            valueLB.setVisible(true);
                            massValue.setValue("");
                            massValue.setVisible(true);
                            massDate.setVisible(false);
                            rebatePlanNameDdlb.setVisible(false);
                            massLookup.setVisible(false);
                            btnPopulate.setEnabled(true);
                            btnAllPopulate.setEnabled(true);
                        } else if (ContractUtils.ITEM_REBATE_START_DATE.equals(value) || ContractUtils.ITEM_REBATE_END_DATE.equals(value)) {
                            massValue.setVisible(false);
                            valueLB.setVisible(true);
                            massDate.setValue(null);
                            massDate.setVisible(true);
                            rebatePlanNameDdlb.setVisible(false);
                            massLookup.setVisible(false);
                            btnPopulate.setEnabled(true);
                            
                        } else if ("RS Status".equals(value)) {
                            massLookup.setVisible(false);
                            massValue.setVisible(false);
                            massDate.setVisible(false);
                            rebatePlanNameDdlb.setVisible(true);
                            valueLB.setVisible(true);
                            commonUtil.loadComboBox(rebatePlanNameDdlb, "STATUS", false);
                            btnPopulate.setEnabled(true);
                            btnAllPopulate.setEnabled(true);
                        } else if ("Formula Type".equals(value)) {
                            massLookup.setVisible(false);
                            massValue.setValue("");
                            massValue.setVisible(true);
                            massDate.setVisible(false);
                            rebatePlanNameDdlb.setVisible(false);
                            valueLB.setVisible(true);
//                          commonUtil.loadComboBox(rebatePlanNameDdlb, "STATUS", false);
                            btnPopulate.setEnabled(true);
                            btnAllPopulate.setEnabled(true);
                        } else if ("Rebate Plan No".equals(value) || "Formula No".equals(value) || ContractUtils.NET_SALES_FORMULA.equals(value) || "Deduction Calendar No".equals(value)
                                || ContractUtils.NET_SALES_RULE.equals(value) || ContractUtils.EVALUATION_RULE.equals(value) || ContractUtils.CALCULATION_RULE.equals(value)) {
                            massValue.setVisible(false);
                            massDate.setVisible(false);
                            rebatePlanNameDdlb.setVisible(false);
                            massLookup.setVisible(true);
                            valueLB.setVisible(true);
                            massLookup.setValue(StringUtils.EMPTY);
                        } else {
                            valueLB.setVisible(false);
                            massValue.setVisible(false);
                            massDate.setVisible(false);
                            rebatePlanNameDdlb.setVisible(false);
                            massLookup.setVisible(false);
                        }
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }
            });
            
            massLookup.addClickListener(new CustomTextField.ClickListener() {
                
                @Override
                public void click(CustomTextField.ClickEvent event) {
                    try {
                        
                        if ("Rebate Plan No".equals(massField.getValue().toString())) {
                            if (rebatePlanLookup == null) {
                                rebatePlanLookup = new RebatePlanLookup();
                                UI.getCurrent().addWindow(rebatePlanLookup);
                                rebatePlanLookup.addCloseListener(new Window.CloseListener() {
                                    @Override
                                    public void windowClose(Window.CloseEvent e) {
                                        if (rebatePlanLookup.isSelected()) {
                                            RebatePlanDTO rebatePlanDTO = rebatePlanLookup.getSelectedItem();
                                            massLookup.setValue(rebatePlanLookup.getSelectedItem().getRebatePlanNo());
                                            final Map<String, String> map = new HashMap<>();
                                            map.put("rebatePlanNo", rebatePlanDTO.getRebatePlanNo());
                                            map.put("rebatePlanSystemId", rebatePlanDTO.getRebatePlanSystemId());
                                            map.put("rebatePlanName", rebatePlanDTO.getRebatePlanName());
                                            massLookup.setData(map);
                                        }
                                        rebatePlanLookup = null;
                                    }
                                });
                            }
                            
                        } else if (ContractUtils.NET_SALES_FORMULA.equals(massField.getValue().toString())) {
                            if (netSalesLookup == null) {
                                netSalesLookup = new NetSalesFormulaLookup(true, massLookup);
                                UI.getCurrent().addWindow(netSalesLookup);
                                netSalesLookup.addCloseListener(new Window.CloseListener() {
                                    @Override
                                    public void windowClose(Window.CloseEvent e) {
                                        if (netSalesLookup.isSelected()) {
                                            NepFormulaLookUpDTO rebatePlanDTO = netSalesLookup.getSelectedItem();
                                            massLookup.setValue(rebatePlanDTO.getNepFormulaNo());
                                            final Map<String, String> map = new HashMap<>();
                                            map.put("netSalesFormulaNo", rebatePlanDTO.getNepFormulaNo());
                                            map.put("systemID", String.valueOf(rebatePlanDTO.getNepFormulaSystemID()));
                                            map.put("netSalesFormulaName", rebatePlanDTO.getNepFormulaName());
                                            massLookup.setData(map);
                                        }
                                        netSalesLookup = null;
                                    }
                                });
                            }
                        } else if ("Deduction Calendar No".equals(massField.getValue().toString())) {
                            if (rsDeductionLookup == null) {
                                rsDeductionLookup = new RsDeductionLookup(massLookup);
                                UI.getCurrent().addWindow(rsDeductionLookup);
                                rsDeductionLookup.addCloseListener(new Window.CloseListener() {
                                    @Override
                                    public void windowClose(Window.CloseEvent e) {
                                        if (rsDeductionLookup.isSelected) {
                                            RsDeductionLookupDto deductionDto = rsDeductionLookup.getDeductionDto();
                                            final Map<String, String> map = new HashMap<>();
                                            map.put(Constants.DEDUCTION_CALENDAR_NO, deductionDto.getDeductionNo());
                                            map.put("deductionSystemId", deductionDto.getDeductionSystemId());
                                            map.put(Constants.DEDUCTION_CALENDAR_NAME, deductionDto.getDeductionName());
                                            massLookup.setData(map);
                                        }
                                        rsDeductionLookup = null;
                                    }
                                });
                            }
                        } else if (ContractUtils.NET_SALES_RULE.equals(massField.getValue().toString()) || ContractUtils.EVALUATION_RULE.equals(massField.getValue().toString()) || ContractUtils.CALCULATION_RULE.equals(massField.getValue().toString())) {
                            String windowName = "";
                            HelperDTO tempDto = new HelperDTO();
                            String tempMassField = "";
                            if (ContractUtils.NET_SALES_RULE.equals(massField.getValue().toString())) {
                                tempMassField = "Net Sales";
                                windowName = "Net Sales Rule LookUp ";
                            } else if (ContractUtils.CALCULATION_RULE.equals(massField.getValue().toString())) {
                                tempMassField = "Calculation";
                                windowName = "Calculation Rule LookUp ";
                            } else if (ContractUtils.EVALUATION_RULE.equals(massField.getValue().toString())) {
                                tempMassField = "Evaluation";
                                windowName = "Evaluation Rule LookUp ";
                            }
                            for (HelperDTO helperDto : helperList.getListNameMap().get("RULE_TYPE")) {
                                if (helperDto.getDescription().equalsIgnoreCase(tempMassField)) {
                                    tempDto.setId(helperDto.getId());
                                    tempDto.setDescription(helperDto.getDescription());
                                    break;
                                }
                            }
                            calculationLookup = new NetSalesRuleLookup(tempDto, windowName);
                            UI.getCurrent().addWindow(calculationLookup);
                            calculationLookup.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    
                                    if (calculationLookup.isSelected) {
                                        NetSalesRuleLookupDto searchDTO = calculationLookup.getSelectedItem();
                                        massLookup.setValue(searchDTO.getRuleName());
                                        final Map<String, String> map = new HashMap<>();
                                        if (ContractUtils.NET_SALES_RULE.equals(massField.getValue().toString())) {
                                            map.put(Constants.NET_SALES_RULE, searchDTO.getRuleName());
                                            map.put("systemID", searchDTO.getRuleSystemId());
                                        } else if (ContractUtils.CALCULATION_RULE.equals(massField.getValue().toString())) {
                                            map.put(Constants.CALCULATION_RULE, searchDTO.getRuleName());
                                            map.put("systemID", searchDTO.getRuleSystemId());
                                        } else if (ContractUtils.EVALUATION_RULE.equals(massField.getValue().toString())) {
                                            map.put(Constants.EVALUATION_RULE, searchDTO.getRuleName());
                                            map.put("systemID", searchDTO.getRuleSystemId());
                                        }
                                        massLookup.setData(map);
                                        calculationLookup.setData(searchDTO.getRuleSystemId());
                                    }
                                }
                            });
                        } else {
                            if (formulaLookup == null) {
                                formulaLookup = new FormulaLookup();
                                UI.getCurrent().addWindow(formulaLookup);
                                formulaLookup.addCloseListener(new Window.CloseListener() {
                                    @Override
                                    public void windowClose(Window.CloseEvent e) {
                                        if (formulaLookup.isSelected()) {
                                            PriceProtectionFormulaDTO rSFormulaDTO = formulaLookup.getSelectedItem();
                                            massLookup.setValue(rSFormulaDTO.getFormulaNo());
                                            final Map<String, String> map = new HashMap<>();
                                            map.put("formulaNo", rSFormulaDTO.getFormulaNo());
                                            map.put("formulaName", rSFormulaDTO.getFormulaName());
                                            map.put("formulaID", rSFormulaDTO.getFormulaID());
                                            massLookup.setData(map);
                                        }
                                        formulaLookup = null;
                                    }
                                });
                            }
                        }
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }
                    
                }
            });
            
            massCheck.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used to mass check logic and its listener.
                 */
                public void valueChange(final ValueChangeEvent event) {
                    massCheckOnChangeEvent(event.getProperty().getValue());
                }
            });
            
            map.put("Start Date", Constants.START_DATE);
            map.put("End Date", Constants.END_DATE);
            map.put("Bundle No", Constants.Bundle_No);
            map.put("Rebate Plan Name", Constants.RP_NAME);
            map.put("RS Status", "attachedStatus");
            map.put("Formula Type", Constants.FORMULA_TYPE);
            map.put("Rebate Plan No", "Rebate Plan No");
            map.put("Formula No", "Formula No");
            map.put("Net Sales Formula", ContractUtils.NET_SALES_FORMULA);
            map.put("Net Sales Rule", ContractUtils.NET_SALES_RULE);
            map.put("Evaluation Rule", ContractUtils.EVALUATION_RULE);
            map.put("Evaluation Rule Bundle", ContractUtils.EVALUATION_RULE_BUNDLE);
            map.put("Calculation Rule", ContractUtils.CALCULATION_RULE);
            map.put("Calculation Rule Bundle", ContractUtils.CALCULATION_RULE_BUNDLE);
            map.put("Deduction Calendar No", "Deduction Calendar No");
            
            massDate.setDescription(Constants.DATE);
            valueLB.setPrimaryStyleName("labelsize");
            LOGGER.info("End of configureFields method");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());;
        }
    }

    /**
     * Adds the btn populate.
     *
     * @return the button
     */
    public Button addBtnPopulate() {
        LOGGER.info("Entering addBtnPopulate method");
        
        btnPopulate.setReadOnly(true);
        btnPopulate.setErrorHandler(new ErrorHandler() {
            /**
             * Method used to click populate button logic and its listener.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });
        
        btnPopulate.addClickListener(new ClickListener() {
            /**
             * Method used to click the text field and its listener.
             */
            
            public void buttonClick(final ClickEvent event) {
                
                try {
                    boolean tempflag = true;
                    LOGGER.info("Entering btnPopulate buttonClick method");
                    contractBinder.getErrorDisplay().clearError();
                    contractBinder.commit();
                    if (massField.getValue() != null) {
                        try {
                            String value = StringUtils.EMPTY;
                            final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                            IfpLogic.saveToTempRebate(rsDetailsResultsBean.getItemIds(), false);
                            fieldMass = map.get(massField.getValue());
                            if (fieldMass.equals(Constants.Bundle_No)) {
                                if (StringUtils.isEmpty(massValue.getValue())) {
                                    contractBinder.getErrorDisplay().setError("Please Enter a value and try again. ");
                                    return;
                                } else if (!massValue.getValue().matches(Constants.alphaNumericChars)) {
                                    contractBinder.getErrorDisplay().setError("Bundle No can contain only Alphanumeric values");
                                    return;
                                } else {
                                    value = massValue.getValue();
                                }
                            } else if (fieldMass.equals(Constants.REBATE_AMOUNT)) {
                                if (StringUtils.isEmpty(massValue.getValue())) {
                                    contractBinder.getErrorDisplay().setError("Please Enter a value and try again. ");
                                    return;
                                } else {
                                    value = massValue.getValue();
                                }
                                
                            } else if (fieldMass.equals(Constants.START_DATE)) {
                                if (massDate.getValue() == null) {
                                    contractBinder.getErrorDisplay().setError("Please provide date and try again.");
                                    return;
                                }
                                if (massDate.getValue() == null) {
                                    contractBinder.getErrorDisplay().setError("Please provide date and try again.");
                                } else if (massDate.getValue().before((Date) dates[0])) {
                                    AbstractNotificationUtils.getErrorNotification("Populate Error", "Start date cannot be before " + new SimpleDateFormat("MM/dd/YYYY").format((Date) dates[0]));
                                    return;
                                } else if (dates[1] != null && massDate.getValue().after((Date) dates[1])) {
                                    AbstractNotificationUtils.getErrorNotification("Populate Error", "Start date cannot be after " + new SimpleDateFormat("MM/dd/YYYY").format((Date) dates[1]));
                                    return;
                                } else {
                                    value = fmt.format(massDate.getValue());
                                }
                                
                            } else if (fieldMass.equals(Constants.END_DATE)) {
                                if (massDate.getValue() == null) {
                                    contractBinder.getErrorDisplay().setError("Please provide date and try again.");
                                    return;
                                } else if (dates[1] != null && massDate.getValue().after((Date) dates[1])) {
                                    AbstractNotificationUtils.getErrorNotification("Populate Error", "End date cannot be after " + new SimpleDateFormat("MM/dd/YYYY").format((Date) dates[1]));
                                    return;
                                } else if (massDate.getValue().before((Date) dates[0])) {
                                    AbstractNotificationUtils.getErrorNotification("Populate Error", "End date cannot be before " + new SimpleDateFormat("MM/dd/YYYY").format((Date) dates[0]));
                                    return;
                                } else {
                                    value = fmt.format(massDate.getValue());
                                }
                            } else if (fieldMass.equals(Constants.RP_NAME)) {
                                if (rebatePlanNameDdlb.getValue() == null) {
                                    contractBinder.getErrorDisplay().setError("Please Select a value and try again.");
                                    return;
                                } else {
                                    value = String.valueOf(((HelperDTO) rebatePlanNameDdlb.getValue()).getId());
                                }
                            } else if (fieldMass.equals(Constants.FORMULA_TYPE)) {
                                if (rebatePlanNameDdlb.getValue() == null) {
                                    contractBinder.getErrorDisplay().setError("Please Select a value and try again.");
                                    return;
                                } else {
                                    value = String.valueOf(((HelperDTO) rebatePlanNameDdlb.getValue()).getId());
                                }
                            } else if (fieldMass.equals("Rebate Plan No") || fieldMass.equals("Formula No")) {
                                if (StringUtils.isEmpty(massLookup.getValue())) {
                                    contractBinder.getErrorDisplay().setError("Please Select a value and try again.");
                                    return;
                                } else {
                                    IfpLogic.saveToTempRebate(rsDetailsResultsBean.getItemIds(), false);
                                    ifpLogic.massPopulateForLookUp(fieldMass, massLookup.getData(), Boolean.FALSE);
                                    tempflag = false;
                                }
                            } else if ("Deduction Calendar No".equals(fieldMass)) {
                                Map<String, String> deductionMap = (Map) massLookup.getData();
                                ifpLogic.massPopulateDeductionLookUp(fieldMass, deductionMap, true);
                                massLookup.setReadOnly(false);
                                massLookup.setValue(StringUtils.EMPTY);
                                tempflag = false;
                            } else if (ContractUtils.NET_SALES_FORMULA.equals(fieldMass)) {
                                massLookup.setReadOnly(false);
                                Map<String, String> netSalesMap = (Map) massLookup.getData();
                                ifpLogic.massPopulateNetSalesLookUp(fieldMass, netSalesMap, true);
                                massLookup.setValue(StringUtils.EMPTY);
                                tempflag = false;
                            } else if (ContractUtils.NET_SALES_RULE.equals(massField.getValue().toString()) || ContractUtils.EVALUATION_RULE.equals(massField.getValue().toString()) || ContractUtils.CALCULATION_RULE.equals(massField.getValue().toString())) {
                                Map<String, String> rulemap = (Map) massLookup.getData();
                                ifpLogic.massPopulateRuleLookUps(fieldMass, rulemap, true);
                                massLookup.setValue(StringUtils.EMPTY);
                                tempflag = false;
                            } else if (ContractUtils.EVALUATION_RULE_BUNDLE.equalsIgnoreCase(massField.getValue().toString())) {
                                if (StringUtils.isEmpty(massValue.getValue())) {
                                    contractBinder.getErrorDisplay().setError("Please Enter a value and try again. ");
                                    return;
                                } else if (!massValue.getValue().matches(Constants.DOUBLE_CHECK)) {
                                    contractBinder.getErrorDisplay().setError(ContractUtils.EVALUATION_RULE_BUNDLE + " can contain only numeric values");
                                    return;
                                } else {
                                    value = massValue.getValue();
                                }
                            } else if (ContractUtils.CALCULATION_RULE_BUNDLE.equals(massField.getValue().toString())) {
                                if (StringUtils.isEmpty(massValue.getValue())) {
                                    contractBinder.getErrorDisplay().setError("Please Enter a value and try again. ");
                                    return;
                                } else if (!massValue.getValue().matches(Constants.DOUBLE_CHECK)) {
                                    contractBinder.getErrorDisplay().setError(ContractUtils.CALCULATION_RULE_BUNDLE + " can contain only numeric values");
                                    return;
                                } else {
                                    value = massValue.getValue();
                                }
                            } else if (fieldMass.equals("attachedStatus")) {
                                if (rebatePlanNameDdlb.getValue() == null) {
                                    contractBinder.getErrorDisplay().setError("Please Select a value and try again.");
                                    return;
                                } else {
                                    value = String.valueOf(((HelperDTO) rebatePlanNameDdlb.getValue()).getId());
                                }
                            }
                            if (tempflag) {
                                IfpLogic.saveToTempRebate(rsDetailsResultsBean.getItemIds(), false);
                            }
                            rsDetailsResultsBean.removeAllItems();
                            if (tempflag) {
                              ifpLogic.populateToTempRebate(massField.getValue(), value, Boolean.FALSE);
                            }
                            loadTempIfp();
                        } catch (SystemException ex) {
                            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                            LOGGER.error(errorMsg);
                            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                        } catch (PortalException ex) {
                            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                            LOGGER.error(errorMsg);
                        }
                        
                    } else {
                        
                        AbstractNotificationUtils.getErrorNotification("Populate Error", Constants.POPULATE_MSG);
                    }
                    LOGGER.info("End of btnPopulate buttonClick method");
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                }
            }
        });
        LOGGER.info("End of addBtnPopulate method");
        return btnPopulate;
        
    }

    /**
     * Adds the btn all populate.
     *
     * @return the button
     */
    public Button addBtnAllPopulate() {
        LOGGER.info("Entering addBtnAllPopulate method");
        btnAllPopulate.setReadOnly(true);
        btnAllPopulate.setErrorHandler(new ErrorHandler() {
            /**
             * Method used to click text field and its listener.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });
        
        btnAllPopulate.addClickListener(new ClickListener() {
            /**
             * Method used to populate all button and its listener.
             */
            
            public void buttonClick(final ClickEvent event) {
                try {
                    boolean tempFlag = true;
                    LOGGER.info("Entering btnAllPopulate buttonClick method");
                    contractBinder.getErrorDisplay().clearError();
                    contractBinder.commit();
                    if (massField.getValue() != null) {
                        try {
                            String value = StringUtils.EMPTY;
                            final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                            fieldMass = map.get(massField.getValue());
                            if (fieldMass.equals(Constants.Bundle_No)) {
                                if (StringUtils.isEmpty(massValue.getValue())) {
                                    contractBinder.getErrorDisplay().setError("Please Enter a value and try again. ");
                                    return;
                                } else if (!massValue.getValue().matches(Constants.alphaNumericChars)) {
                                    contractBinder.getErrorDisplay().setError("Bundle# can contain only Alphanumeric values");
                                    return;
                                } else {
                                    value = massValue.getValue();
                                }
                            } else if (fieldMass.equals(Constants.REBATE_AMOUNT)) {
                                if (StringUtils.isEmpty(massValue.getValue())) {
                                    contractBinder.getErrorDisplay().setError("Please Enter a value and try again. ");
                                    return;
                                } else if (!massValue.getValue().matches(Constants.DOUBLE_CHECK)) {
                                    contractBinder.getErrorDisplay().setError("Rebate Amount can contain only numeric values");
                                    return;
                                } else {
                                    value = massValue.getValue();
                                }
                                
                            } else if (fieldMass.equals(Constants.START_DATE)) {
                                if (massDate.getValue() == null) {
                                    contractBinder.getErrorDisplay().setError("Please provide date and try again.");
                                    return;
                                }
                                if (massDate.getValue() == null) {
                                    contractBinder.getErrorDisplay().setError("Please provide date and try again.");
                                } else if (massDate.getValue().before((Date) dates[0])) {
                                    AbstractNotificationUtils.getErrorNotification("Populate Error", "Start date cannot be before " + new SimpleDateFormat("MM/dd/YYYY").format((Date) dates[0]));
                                    return;
                                } else if (dates[1] != null && massDate.getValue().after((Date) dates[1])) {
                                    AbstractNotificationUtils.getErrorNotification("Populate Error", "Start date cannot be after " + new SimpleDateFormat("MM/dd/YYYY").format((Date) dates[1]));
                                    return;
                                } else {
                                    value = fmt.format(massDate.getValue());
                                }
                                
                            } else if (fieldMass.equals(Constants.END_DATE)) {
                                if (massDate.getValue() == null) {
                                    contractBinder.getErrorDisplay().setError("Please provide date and try again.");
                                    return;
                                } else if (dates[1] != null && massDate.getValue().after((Date) dates[1])) {
                                    AbstractNotificationUtils.getErrorNotification("Populate Error", "End date cannot be after " + new SimpleDateFormat("MM/dd/YYYY").format((Date) dates[1]));
                                    return;
                                } else if (massDate.getValue().before((Date) dates[0])) {
                                    AbstractNotificationUtils.getErrorNotification("Populate Error", "End date cannot be before " + new SimpleDateFormat("MM/dd/YYYY").format((Date) dates[0]));
                                    return;
                                } else {
                                    value = fmt.format(massDate.getValue());
                                }
                            } else if (fieldMass.equals(Constants.RP_NAME)) {
                                if (rebatePlanNameDdlb.getValue() == null) {
                                    contractBinder.getErrorDisplay().setError("Please Select a value and try again.");
                                    return;
                                } else {
                                    value = String.valueOf(((HelperDTO) rebatePlanNameDdlb.getValue()).getId());
                                }
                            } else if (fieldMass.equals(Constants.FORMULA_TYPE)) {
                                if (rebatePlanNameDdlb.getValue() == null) {
                                    contractBinder.getErrorDisplay().setError("Please Select a value and try again.");
                                    return;
                                } else {
                                    value = String.valueOf(((HelperDTO) rebatePlanNameDdlb.getValue()).getId());
                                }
                            } else if (fieldMass.equals("Rebate Plan No") || fieldMass.equals("Formula No")) {
                                if (StringUtils.isEmpty(massLookup.getValue())) {
                                    contractBinder.getErrorDisplay().setError("Please Select a value and try again.");
                                    return;
                                } else {
                                    ifpLogic.massPopulateForLookUp(fieldMass, massLookup.getData(), Boolean.TRUE);
                                    tempFlag = false;
                                }
                            } else if (ContractUtils.NET_SALES_FORMULA.equals(fieldMass)) {
                                Map<String, String> netSalesMap = (Map) massLookup.getData();
                                ifpLogic.massPopulateNetSalesLookUp(fieldMass, netSalesMap, true);
                                massLookup.setValue(StringUtils.EMPTY);
                                tempFlag = false;
                            } else if (ContractUtils.NET_SALES_RULE.equals(massField.getValue().toString()) || ContractUtils.EVALUATION_RULE.equals(massField.getValue().toString()) || ContractUtils.CALCULATION_RULE.equals(massField.getValue().toString())) {
                                Map<String, String> rulemap = (Map) massLookup.getData();
                                ifpLogic.massPopulateRuleLookUps(fieldMass, rulemap, true);
                                massLookup.setValue(StringUtils.EMPTY);
                                tempFlag = false;
                            } else if (ContractUtils.EVALUATION_RULE_BUNDLE.equals(massField.getValue().toString())) {
                                if (StringUtils.isEmpty(massValue.getValue())) {
                                    contractBinder.getErrorDisplay().setError("Please Enter a value and try again. ");
                                    return;
                                } else if (!massValue.getValue().matches(Constants.DOUBLE_CHECK)) {
                                    contractBinder.getErrorDisplay().setError(ContractUtils.EVALUATION_RULE_BUNDLE + " can contain only numeric values");
                                    return;
                                } else {
                                    value = massValue.getValue().trim();
                                }
                            } else if (ContractUtils.CALCULATION_RULE_BUNDLE.equals(massField.getValue().toString())) {
                                if (StringUtils.isEmpty(massValue.getValue())) {
                                    contractBinder.getErrorDisplay().setError("Please Enter a value and try again. ");
                                    return;
                                } else if (!massValue.getValue().matches(Constants.DOUBLE_CHECK)) {
                                    contractBinder.getErrorDisplay().setError(ContractUtils.CALCULATION_RULE_BUNDLE + " can contain only numeric values");
                                    return;
                                } else {
                                    value = massValue.getValue().trim();
                                }
                            } else if (fieldMass.equals("attachedStatus")) {
                                if (rebatePlanNameDdlb.getValue() == null) {
                                    contractBinder.getErrorDisplay().setError("Please Select a value and try again.");
                                    return;
                                } else {
                                    value = String.valueOf(((HelperDTO) rebatePlanNameDdlb.getValue()).getId());
                                }
                            } else if ("Deduction Calendar No".equals(fieldMass)) {
                                Map<String, String> deductionMap = (Map) massLookup.getData();
                                ifpLogic.massPopulateDeductionLookUp(fieldMass, deductionMap, true);
                                massLookup.setReadOnly(false);
                                massLookup.setValue(StringUtils.EMPTY);
                                tempFlag = false;
                            }
                            if (tempFlag) {
                                IfpLogic.saveToTempRebate(rsDetailsResultsBean.getItemIds(), false);
                            }
                            rsDetailsResultsBean.removeAllItems();
                            if (tempFlag) {
                                ifpLogic.populateToTempRebate(massField.getValue(), value, Boolean.TRUE);
                            }
                            loadTempIfp();
                        } catch (SystemException ex) {
                            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                            LOGGER.error(errorMsg);
                            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                        } catch (PortalException ex) {
                            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                            LOGGER.error(errorMsg);
                        }
                        
                    } else {
                        
                        AbstractNotificationUtils.getErrorNotification("Populate Error", Constants.POPULATE_MSG);
                    }
                    LOGGER.info("End of btnAllPopulate buttonClick method");
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                }
            }
        });
        LOGGER.info("End of addBtnAllPopulate method");
        return btnAllPopulate;
        
    }
    
    public void loadTempIfp() {
        
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            tempLazyContainer = new LazyBeanItemContainer(TempRebateDTO.class, new TempRebateContainer(itemDetailsTable, rsDetailsResultsBean, false, sessionDTO),
                    lazyLoadCriteria);
            final Map<String, AppPermission> contractDashboard = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + "," + "Rebate Setup", false);
            
            List<Object> resultList = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, "Rebate Setup");
            Object[] obj = CommonUIUtils.ITEM_DETAILS_COLUMNS_IN_RS;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, contractDashboard, Constants.EDIT);
            itemDetailsTable.setVisibleColumns(tableResultCustom.getObjResult());
            itemDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            rsDetailsResultsBean.removeAllItems();
            tempDate = new HashMap<String, List>();
            loadBasedOnCalculationType(String.valueOf(rebateBinder.getField("calculationType").getValue()));
            itemDetailsTable.setTableFieldFactory(new RSItemTableGenerator(itemDetailsTable, rsDetailsResultsBean, sessionDTO, tempDate, dates));
            itemDetailsTable.setEditable(true);
            itemDetailsTable.setCurrentPage(itemDetailsTable.getCurrentPage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        
    }

    /**
     * Adds the item details table.
     *
     * @return the vertical layout
     */
    public void addItemDetailsTable() {
        
        LOGGER.info("Entering addItemDetailsTable method");
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            contractDashboard = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + "," + "Rebate Setup", false);
            resultList = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, "Rebate Setup");
            Object[] obj = CommonUIUtils.ITEM_DETAILS_COLUMNS_IN_RS;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, contractDashboard, Constants.EDIT);
            itemDetailsTable.markAsDirty();
            rebateContainer = new TempRebateContainer(itemDetailsTable, rsDetailsResultsBean, false, sessionDTO);
            tempLazyContainer = new LazyBeanItemContainer(TempRebateDTO.class, rebateContainer,
                    lazyLoadCriteria);
            itemDetailsTable.setContainerDataSource(tempLazyContainer);
            itemDetailsTable.setVisibleColumns(tableResultCustom.getObjResult());
            itemDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            itemDetailsTable.setColumnCheckBox("checkbox", true, false);
            itemDetailsTable.setPageLength(5);
            itemDetailsTable.sinkItemPerPageWithPageLength(false);
            itemDetailsTable.setImmediate(true);
            itemDetailsTable.addStyleName("filterbar");
            itemDetailsTable.addStyleName("valo-theme-extfiltertable");
            itemDetailsTable.setSelectable(true);
            itemDetailsTable.setSizeFull();
            tempDate = new HashMap<String, List>();
            itemDetailsTable.setTableFieldFactory(new RSItemTableGenerator(itemDetailsTable, rsDetailsResultsBean, sessionDTO, tempDate, dates));
            itemDetailsTable.setFilterBarVisible(true);
            itemDetailsTable.setFilterGenerator(new RebateDetailsGenerator());
            itemDetailsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            itemDetailsTable.setFilterFieldVisible("checkbox", false);
            itemDetailsTable.setEditable(true);
            itemDetailsTable.addStyleName("TableCheckBox");
            itemDetailsTable.setErrorHandler(new ErrorHandler() {
                /**
                 * Method used to click text field and its listener.
                 */
                @SuppressWarnings("PMD")
                public void error(final com.vaadin.server.ErrorEvent event) {
                    LOGGER.error(event.toString());
                }
            });
            HorizontalLayout tempLayout = ResponsiveUtils.getResponsiveControls(itemDetailsTable.createControls());
            resultsTableLayout.addComponent(tempLayout);
            itemDetailsTable.setColumnCollapsingAllowed(true);
            itemDetailsTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
                
                @Override
                public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                    if ("checkbox".equals(event.getPropertyId().toString())) {
                        if (event.isChecked()) {
                            try {
                                IfpLogic.saveToTempRebate(rsDetailsResultsBean.getItemIds(), false);
                                rsDetailsResultsBean.removeAllItems();
                                ifpLogic.populateToTempRebate("CheckBox", 1, Boolean.TRUE);
                                itemDetailsTable.setCurrentPage(itemDetailsTable.getCurrentPage());
                                itemDetailsTable.setColumnCheckBox("checkbox", true, true);
                            } catch (PortalException ex) {
                                LOGGER.error(ex.getMessage());
                            } catch (SystemException ex) {
                                LOGGER.error(ex.getMessage());
                            }
                            
                        } else {
                            try {
                                IfpLogic.saveToTempRebate(rsDetailsResultsBean.getItemIds(), false);
                                rsDetailsResultsBean.removeAllItems();
                                ifpLogic.populateToTempRebate("CheckBox", 0, Boolean.TRUE);
                                itemDetailsTable.setCurrentPage(itemDetailsTable.getCurrentPage());
                                itemDetailsTable.setColumnCheckBox("checkbox", true, true);
                            } catch (PortalException ex) {
                                LOGGER.error(ex.getMessage());
                            } catch (SystemException ex) {
                                LOGGER.error(ex.getMessage());
                            }
                            
                        }
                    }
                }
            });
            ContractUtils.getCustomizedComboBox((ComboBox) ((CssLayout) tempLayout.getComponent(0)).getComponent(1));
            LOGGER.info("End of addItemDetailsTable method");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
    
    public void setDefaultTableWidth(final CustomePagedFilterTable table) {
        
        try {
            table.setColumnCollapsingAllowed(true);
            int browserWidth = Page.getCurrent().getBrowserWindowWidth();
            if (browserWidth > 1516) {
                lazyLoadCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getSixColumns(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                lazyLoadCriteria.setCustomDirty(true);
            } else if (browserWidth < 1516 && browserWidth > 978) {
                lazyLoadCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                lazyLoadCriteria.setCustomDirty(true);
            } else if (browserWidth < 978 && browserWidth > 600) {
                lazyLoadCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                
                if (tempLazyContainer != null && tempLazyContainer.getItemIds().isEmpty()) {
                    for (Object propertyId : getCollapsibleColumns978Px(table)) {
                        table.setColumnCollapsed(propertyId, true);
                    }
                } else {
                    for (Object propertyId : getCollapsibleColumns600Px(table)) {
                        table.setColumnCollapsed(propertyId, true);
                    }
                }
                lazyLoadCriteria.setCustomDirty(true);
            } else if (browserWidth < 600 && browserWidth > 480) {
                lazyLoadCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns600Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                lazyLoadCriteria.setCustomDirty(true);
            } else if (browserWidth < 480 && browserWidth > 320) {
                lazyLoadCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns480Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                lazyLoadCriteria.setCustomDirty(true);
            } else if (browserWidth < 320) {
                lazyLoadCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns480Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                lazyLoadCriteria.setCustomDirty(true);
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }
    
    private static Object[] getCollapsibleColumns480Px(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(propertyIds));
        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        propertyIds = list.toArray(new Object[list.size()]);
        
        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }
        
        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }
        
        return propertyIds;
    }
    
    private static Object[] getCollapsibleColumns978Px(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(propertyIds));
        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        list.remove(propertyIds[3]);
        propertyIds = list.toArray(new Object[list.size()]);
        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }
        
        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }
        return propertyIds;
    }
    
    private static String[] getCollapsibleColumns600Px(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }
    
    private static String[] getCollapsibleColumnsDefault1515Px(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        list.remove(propertyIds[3]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }
    
    private static Object[] getSixColumns(CustomePagedFilterTable table) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(visibleColumns));
        for (int i = 0; i < 6; i++) {
            list.remove(propertyIds[i]);
        }
        propertyIds = list.toArray(new Object[list.size()]);
        
        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }
        
        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }
        
        return propertyIds;
    }
    
    public void addResponsiveSearchTableCollapse(final CustomePagedFilterTable table) {
        reloadMap.put(1516, true);
        reloadMap.put(978, true);
        reloadMap.put(600, true);
        reloadMap.put(480, true);
        reloadMap.put(320, true);
        reloadMap.put(0, true);
        
        try {
            
            table.setColumnCollapsingAllowed(true);
            
            Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
                @Override
                public void browserWindowResized(final Page.BrowserWindowResizeEvent event) {
                    
                    int browserWidth = Page.getCurrent().getBrowserWindowWidth();
                    if (browserWidth > 1516 && reloadMap.get(1516)) {
                        lazyLoadCriteria.setCustomDirty(false);
                        
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getSixColumns(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth > 1516);
                        }
                        reloadMap.put(1516, false);
                        reloadMap.put(978, true);
                        reloadMap.put(600, true);
                        reloadMap.put(480, true);
                        reloadMap.put(320, true);
                        reloadMap.put(0, true);
                        lazyLoadCriteria.setCustomDirty(true);
                    } else if (browserWidth < 1516 && browserWidth > 978 && reloadMap.get(978)) {
                        lazyLoadCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < 1516);
                        }
                        
                        reloadMap.put(1516, true);
                        reloadMap.put(978, false);
                        reloadMap.put(600, true);
                        reloadMap.put(480, true);
                        reloadMap.put(320, true);
                        reloadMap.put(0, true);
                        lazyLoadCriteria.setCustomDirty(true);
                    } else if (browserWidth < 978 && browserWidth > 600 && reloadMap.get(600)) {
                        lazyLoadCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        
                        if (tempLazyContainer != null && tempLazyContainer.getItemIds().isEmpty()) {
                            for (Object propertyId : getCollapsibleColumns978Px(table)) {
                                table.setColumnCollapsed(propertyId, true);
                            }
                        } else {
                            for (Object propertyId : getCollapsibleColumns600Px(table)) {
                                table.setColumnCollapsed(propertyId, true);
                            }
                        }
                        
                        reloadMap.put(1516, true);
                        reloadMap.put(978, true);
                        reloadMap.put(600, false);
                        reloadMap.put(480, true);
                        reloadMap.put(320, true);
                        reloadMap.put(0, true);
                        lazyLoadCriteria.setCustomDirty(true);
                    } else if (browserWidth < 600 && browserWidth > 480 && reloadMap.get(480)) {
                        lazyLoadCriteria.setCustomDirty(false);       // --> Disables reloading the container
                        for (Object propertyId : table.getVisibleColumns()) {
                            // table is refreshed when container reloading is disabled
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumns600Px(table)) {
                            // table is refreshed when container reloading is disabled
                            table.setColumnCollapsed(propertyId, true);
                        }
                        reloadMap.put(1516, true);
                        reloadMap.put(978, true);
                        reloadMap.put(600, true);
                        reloadMap.put(480, false);
                        reloadMap.put(320, true);
                        reloadMap.put(0, true);
                        lazyLoadCriteria.setCustomDirty(true);            // --> Enables reloading the container
                    } else if (browserWidth < 480 && browserWidth > 320 && reloadMap.get(320)) {
                        lazyLoadCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumns480Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < 480);
                        }
                        reloadMap.put(1516, true);
                        reloadMap.put(978, true);
                        reloadMap.put(600, true);
                        reloadMap.put(480, true);
                        reloadMap.put(320, false);
                        reloadMap.put(0, true);
                        lazyLoadCriteria.setCustomDirty(true);
                    } else if (browserWidth < 320 && reloadMap.get(0)) {
                        lazyLoadCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumns480Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < 320);
                        }
                        reloadMap.put(1516, true);
                        reloadMap.put(978, true);
                        reloadMap.put(600, true);
                        reloadMap.put(480, true);
                        reloadMap.put(320, true);
                        reloadMap.put(0, false);
                        lazyLoadCriteria.setCustomDirty(true);
                    }
                    
                }
            });
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());;
        }
    }
    
    public void removeItemFromRsDetailsResultsBean() {
        rsDetailsResultsBean.removeAllItems();
    }
    
    public void focusMassCheck() {
        this.massCheck.focus();
    }
    
    public void btnExportLogic() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering btnExportLogic method");
        
        createWorkSheet();
        LOGGER.info("End of btnExportLogic method");
    }
    
    public void createWorkSheet() throws SystemException, PortalException, Exception {
        BufferedWriter writer = null;
        try {
            LOGGER.info("Entering createWorkSheet method");
            final long recordCount = ifpLogic.getLazySelectedItemCount(null);
            final int maxRecords = ExcelExportUtil.MAX_RECORDS;
            int worksheetCount = (int) (recordCount / maxRecords);
            if (recordCount % maxRecords != 0 || recordCount < maxRecords) {
                worksheetCount++;
                
            }
            int remainingCount;
            int start = 0;
            int end = 0;
            final File tempFile = File.createTempFile("tmp" + VaadinSession.getCurrent().getAttribute(Constants.SESSION_ID), ExcelExportUtil.CSV);
            writer = new BufferedWriter(new FileWriter(tempFile, true));
            PrintWriter pwValue = new PrintWriter(writer);
            createHeaderRow(pwValue);
            pwValue.flush();
            pwValue.close();
            writer.close();
            for (int worksheetNo = 1; worksheetNo <= worksheetCount; worksheetNo++) {
                try {
                    
                    writer = new BufferedWriter(new FileWriter(tempFile, true));
                    pwValue = new PrintWriter(writer);
                    
                    if (recordCount < maxRecords) {
                        remainingCount = (int) recordCount;
                    } else {
                        remainingCount = maxRecords;
                    }
                    
                    do {
                        if (remainingCount > ExcelExportUtil.ITERATION_COUNT) {
                            end = end + ExcelExportUtil.ITERATION_COUNT;
                        } else {
                            end = end + remainingCount;
                        }
                        
                        if (end > ExcelExportUtil.LIMIT) {
                            break;
                        }
                        
                        createWorkSheetContent(start, end, pwValue);
                        final int diff = end - start;
                        remainingCount = remainingCount - diff;
                        start = end;
                        
                    } while (remainingCount > 0);
                    
                } finally {
                    pwValue.flush();
                    pwValue.close();
                    writer.close();
                    
                }
            }
            sendConvertedFileToUser(getUI(), tempFile, "Rebate_Details" + ExcelExportUtil.CSV);
            LOGGER.info("End of createWorkSheet method");
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
            throw new SystemException(ex);
        } finally {
            writer.close();
        }
    }

    /**
     * Creates the header row.
     *
     * @param pwValue the pw value
     */
    public void createHeaderRow(final PrintWriter pwValue) {
        LOGGER.info("Entering createHeaderRow method");
        final String[] header = itemDetailsTable.getColumnHeaders();
        for (int headerCount = 1; headerCount < header.length; headerCount++) {
            if (headerCount < header.length - 1) {
                pwValue.print(header[headerCount] + ExcelExportUtil.COMMA);
            } else {
                pwValue.println(header[headerCount]);
            }
        }
        LOGGER.info("End of createHeaderRow method");
    }

    /**
     * Creates the work sheet content.
     *
     * @param start
     * @param pwValue the pw value
     * @param end
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter pwValue) throws PortalException, SystemException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        LOGGER.info("Entering createWorkSheetContent method");
        List<Object[]> exportCompany = ifpLogic.getLazyItemRebateDeatils(start, end, null, false, null, Boolean.FALSE);
        List<TempRebateDTO> items = ifpLogic.getCustomizedRebateDTO(exportCompany, record.getValue().toString());
        Object[] columns = itemDetailsTable.getVisibleColumns();
        columns = ArrayUtils.removeElement(columns, Constants.CHECK_BOX);
        ExcelExportforBB.createFileContent(columns, items, pwValue);
        LOGGER.info("End of createWorkSheetContent method");
    }

    /**
     * Send converted file to user.
     *
     * @param app the app
     * @param fileToExport the file to export
     * @param exportFileName the export file name
     * @return true, if send converted file to user
     * @throws java.io.FileNotFoundException
     */
    public boolean sendConvertedFileToUser(final UI app, final File fileToExport, final String exportFileName) throws FileNotFoundException {
        LOGGER.info("Entering sendConvertedFileToUser method");
        
        TemporaryFileDownloadResource resource;
        resource = new TemporaryFileDownloadResource(app, exportFileName, ExcelExportUtil.EXCEL_MIME_TYPE, fileToExport);
        LOGGER.info("resource name" + resource.getFilename());
        Page.getCurrent().open(resource, ExcelExportUtil.WINDOW_NAME, true);
        LOGGER.info("End of sendConvertedFileToUser method");
        return true;
    }
    
    public void resetFields() {
        rsDetailsResultsBean.removeAllItems();
        massField.setValue(null);
        massValue.setValue(StringUtils.EMPTY);
        massDate.setValue(null);
        massValue.setValue(StringUtils.EMPTY);
        loadTempIfp();
    }

    /**
     * Mass check on change event.
     *
     * @param value the value
     */
    protected void massCheckOnChangeEvent(final Object value) {
        LOGGER.info("Entering massCheckOnChangeEvent method");
        
        if (value != null && Constants.ENABLE.equals(value.toString())) {
            
            massField.setEnabled(true);
            /*
             * massValue.setEnabled(true); massDate.setEnabled(true);
             * btnPopulate.setEnabled(true);
             */
        } else if (value != null && Constants.DISABLE.equals(value.toString())) {
            
            massField.setEnabled(false);
            massField.select(Constants.SELECT_ONE);
            rebatePlanNameDdlb.setVisible(false);
            massValue.setVisible(false);
            massDate.setVisible(false);
            btnPopulate.setEnabled(false);
            btnAllPopulate.setEnabled(false);
            if (rsDetailsResultsBean != null) {
                final List<TempRebateDTO> rsList = rsDetailsResultsBean.getItemIds();
                
                for (int i = 0; i < rsList.size(); i++) {
                    final TempRebateDTO dto = rsList.get(i);
                    if (dto.getCheckbox()) {
                        dto.setCheckbox(false);
                    }
                    rebateList.add(dto);
                }
                if (!(rebateList.isEmpty())) {
                    rsDetailsResultsBean.removeAllItems();
                    rsDetailsResultsBean.addAll(rebateList);
                }
                
            }
            
        }
        LOGGER.info("End of massCheckOnChangeEvent method");
    }

    /**
     * Adds the item details table.
     *
     * @return the vertical layout
     */
    public void addItemDetailsTableForView() {
        try {
            LOGGER.info("Entering addItemDetailsTable method");
            final StplSecurity stplSecurity = new StplSecurity();
            final Map<String, AppPermission> contractDashboard = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + "," + "Rebate Setup", false);
            List<Object> resultList = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, "Rebate Setup");
            Object[] obj = ContractUtils.ITEM_DETAILS_VIEW_COLUMNS_IN_RS;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, contractDashboard, Constants.ViewMode);
            viewItemDetailsTable.setVisible(true);
            viewItemDetailsTable.markAsDirty();
            viewItemDetailsTable.setContainerDataSource(rsDetailsResultsBean);
            viewItemDetailsTable.setVisibleColumns(tableResultCustom.getObjResult());
            viewItemDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            viewItemDetailsTable.setPageLength(7);
            viewItemDetailsTable.setImmediate(true);
            viewItemDetailsTable.setSelectable(false);
            viewItemDetailsTable.setSizeFull();
            viewItemDetailsTable.setWidth("99%");
            
            viewItemDetailsTable.setErrorHandler(new ErrorHandler() {
                /**
                 * Invoked when an error occurs.
                 */
                @SuppressWarnings("PMD")
                public void error(final com.vaadin.server.ErrorEvent event) {
                    LOGGER.error(event.toString());
                }
            });
            ResponsiveUtils.addNaviButton(viewItemDetailsTable);
            
            LOGGER.info("End of addItemDetailsTable method");
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        
    }
    
    public void loadTempRs(String value) {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final Map<String, AppPermission> contractDashboard = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + "," + "Rebate Setup", false);
            List<Object> resultList = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, "Rebate Setup");
            Object[] obj = ContractUtils.ITEM_DETAILS_VIEW_COLUMNS_IN_RS;
            viewRebateContainer = new TempViewRebateContainer(sessionDTO);
            viewRebateContainer.setRecord(value);
            tempLazyContainer = new LazyBeanItemContainer(TempRebateDTO.class, viewRebateContainer, new LazyLoadCriteria());
            
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, contractDashboard, Constants.ViewMode);
            
            viewItemDetailsTable.setContainerDataSource(tempLazyContainer);
            viewItemDetailsTable.setVisibleColumns(tableResultCustom.getObjResult());
            viewItemDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }
    
    private void setDefaultResolution(Table table) {
        int browserWidth = Page.getCurrent().getBrowserWindowWidth();
        if (browserWidth > 1516) {
            getCollapsibleColumnsDefault(table);
        } else if (browserWidth < 1516 && browserWidth > 1300) {
            getCollapsibleColumnsDefault(table);
        } else if (browserWidth < 1300 && browserWidth > 1024) {
            getCollapsibleColumnsDefault(table);
        } else if (browserWidth < 1024 && browserWidth > 978) {
            getCollapsibleColumns978Px(table);
        } else if (browserWidth < 978 && browserWidth > 800) {
            if (table.getItemIds().isEmpty()) {
                getCollapsibleColumns978Px(table);
            } else {
                getCollapsibleColumnsTwoData(table);
            }
        } else if (browserWidth < 800 && browserWidth > 480) {
            if (table.getItemIds().isEmpty()) {
                getCollapsibleColumns978Px(table);
            } else {
                getCollapsibleColumnsTwoData(table);
            }
        } else if (browserWidth < 480 && browserWidth > 380) {
            getCollapsibleColumns480Px(table);
        } else if (browserWidth < 380) {
            if (defaultColumnsVisible(table)) {
                for (Object propertyId : getCollapsibleOneColumn(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            }
        }
        
    }
    
    private static Object[] getCollapsibleColumns480Px(Table table) {
        table.setColumnCollapsingAllowed(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(propertyIds));
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new Object[list.size()]);
        
        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }
        
        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }
        
        return propertyIds;
    }
    
    private static Object[] getCollapsibleColumns978Px(Table table) {
        table.setColumnCollapsingAllowed(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(propertyIds));
        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        list.remove(propertyIds[3]);
        propertyIds = list.toArray(new Object[list.size()]);
        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }
        
        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }
        return propertyIds;
    }
    
    private static Object[] getCollapsibleColumnsTwoData(Table table) {
        table.setColumnCollapsingAllowed(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(propertyIds));
        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        propertyIds = list.toArray(new Object[list.size()]);
        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }
        
        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }
        return propertyIds;
    }
    
    private static boolean defaultColumnsVisible(Table table) {
        boolean result = true;
        for (Object propertyId : getCollapsibleOneColumn(table)) {
            if (table.isColumnCollapsed(propertyId) == Page.getCurrent()
                    .getBrowserWindowWidth() < 800) {
                result = false;
            }
        }
        return result;
    }
    
    private static Object[] getCollapsibleOneColumn(Table table) {
        table.setColumnCollapsingAllowed(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        propertyIds = list.toArray(new Object[list.size()]);
        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }
        
        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }
        
        return propertyIds;
    }
    
    private static Object[] getCollapsibleColumnsDefault(Table table) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(visibleColumns));
        for (int i = 0, j = 8; i < j; i++) {
            list.remove(propertyIds[i]);
        }
        propertyIds = list.toArray(new Object[list.size()]);
        
        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }
        
        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }
        
        return propertyIds;
    }
    
    private void addResponsiveGrid(Map<String, AppPermission> contractDashboard) {
        LOGGER.info("Entering configurePermission");
        try {
            List<Object> resultList = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, "Rebate Setup");
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, contractDashboard, Constants.EDIT);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        
    }

    /**
     * Loads the Table columns and Mass Update Fields based on Calculation Type.
     *
     * @param calculationType
     */
    public void loadBasedOnCalculationType(String calculationType) {
        Object[] obj;
        TableResultCustom tableResultCustom;
        configureMassField();
        calculationType = calculationType == null || "null".equals(calculationType) ? "default" : calculationType;
        resultList = resultList != null && !resultList.isEmpty() ? resultList : contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, "Rebate Setup");
        switch (calculationType) {
            case ContractUtils.CALC_FORMULA:
                obj = ContractUtils.REBATE_SETUP_FORMULA;
                tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, contractDashboard, mode);
                if (tableResultCustom.getObjResult().length == 0) {
                    itemDetailsTable.setVisible(false);
                }
                if (isEditable) {
                    itemDetailsTable.setVisibleColumns(isEditable ? tableResultCustom.getObjResult() : Arrays.asList(tableResultCustom.getObjResult()).subList(1, tableResultCustom.getObjResult().length).toArray());
                    itemDetailsTable.setColumnHeaders(isEditable ? tableResultCustom.getObjResultHeader() : Arrays.asList(tableResultCustom.getObjResultHeader()).subList(1, tableResultCustom.getObjResultHeader().length).toArray(new String[tableResultCustom.getObjResultHeader().length - 1]));
                } else {
                    viewItemDetailsTable.setVisibleColumns(isEditable ? tableResultCustom.getObjResult() : Arrays.asList(tableResultCustom.getObjResult()).subList(1, tableResultCustom.getObjResult().length).toArray());
                    viewItemDetailsTable.setColumnHeaders(isEditable ? tableResultCustom.getObjResultHeader() : Arrays.asList(tableResultCustom.getObjResultHeader()).subList(1, tableResultCustom.getObjResultHeader().length).toArray(new String[tableResultCustom.getObjResultHeader().length - 1]));
                }
                resultsPanel.setWidth("100%");
                massField.addItem(ContractUtils.FORMULA_TYPE);
                massField.addItem("Formula No");
                massField.addItem(ContractUtils.NET_SALES_FORMULA);
                massField.addItem(ContractUtils.NET_SALES_RULE);
                massField.addItem(ContractUtils.EVALUATION_RULE);
                massField.addItem(ContractUtils.CALCULATION_RULE);
                massField.addItem(ContractUtils.EVALUATION_RULE_BUNDLE);
                massField.addItem(ContractUtils.CALCULATION_RULE_BUNDLE);
                break;
            case ContractUtils.CALC_REBATE_PLAN:
                obj = ContractUtils.REBATE_SETUP_REBATE_PLAN;
                tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, contractDashboard, mode);
                if (tableResultCustom.getObjResult().length == 0) {
                    itemDetailsTable.setVisible(false);
                }
                if (isEditable) {
                    itemDetailsTable.setVisibleColumns(isEditable ? tableResultCustom.getObjResult() : Arrays.asList(tableResultCustom.getObjResult()).subList(1, tableResultCustom.getObjResult().length).toArray());
                    itemDetailsTable.setColumnHeaders(isEditable ? tableResultCustom.getObjResultHeader() : Arrays.asList(tableResultCustom.getObjResultHeader()).subList(1, tableResultCustom.getObjResultHeader().length).toArray(new String[tableResultCustom.getObjResultHeader().length - 1]));
                } else {
                    viewItemDetailsTable.setVisibleColumns(isEditable ? tableResultCustom.getObjResult() : Arrays.asList(tableResultCustom.getObjResult()).subList(1, tableResultCustom.getObjResult().length).toArray());
                    viewItemDetailsTable.setColumnHeaders(isEditable ? tableResultCustom.getObjResultHeader() : Arrays.asList(tableResultCustom.getObjResultHeader()).subList(1, tableResultCustom.getObjResultHeader().length).toArray(new String[tableResultCustom.getObjResultHeader().length - 1]));
                }
                resultsPanel.setWidth("100%");
                massField.addItem("Bundle No");
                massField.addItem("Rebate Plan No");
                massField.addItem(ContractUtils.NET_SALES_FORMULA);
                massField.addItem(ContractUtils.NET_SALES_RULE);
                massField.addItem(ContractUtils.EVALUATION_RULE);
                massField.addItem(ContractUtils.CALCULATION_RULE);
                massField.addItem(ContractUtils.EVALUATION_RULE_BUNDLE);
                massField.addItem(ContractUtils.CALCULATION_RULE_BUNDLE);
                break;
            case ContractUtils.CALC_DEDUCTION_CALENDAR:
                obj = ContractUtils.REBATE_SETUP_DEDUCTION_CALENDAR;
                tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, contractDashboard, mode);
                if (tableResultCustom.getObjResult().length == 0) {
                    itemDetailsTable.setVisible(false);
                }
                if (isEditable) {
                    itemDetailsTable.setVisibleColumns(isEditable ? tableResultCustom.getObjResult() : Arrays.asList(tableResultCustom.getObjResult()).subList(1, tableResultCustom.getObjResult().length).toArray());
                    itemDetailsTable.setColumnHeaders(isEditable ? tableResultCustom.getObjResultHeader() : Arrays.asList(tableResultCustom.getObjResultHeader()).subList(1, tableResultCustom.getObjResultHeader().length).toArray(new String[tableResultCustom.getObjResultHeader().length - 1]));
                } else {
                    viewItemDetailsTable.setVisibleColumns(isEditable ? tableResultCustom.getObjResult() : Arrays.asList(tableResultCustom.getObjResult()).subList(1, tableResultCustom.getObjResult().length).toArray());
                    viewItemDetailsTable.setColumnHeaders(isEditable ? tableResultCustom.getObjResultHeader() : Arrays.asList(tableResultCustom.getObjResultHeader()).subList(1, tableResultCustom.getObjResultHeader().length).toArray(new String[tableResultCustom.getObjResultHeader().length - 1]));
                }
                resultsPanel.setWidth("100%");
                massField.addItem("Deduction Calendar No");
                massField.addItem(ContractUtils.EVALUATION_RULE);
                massField.addItem(ContractUtils.CALCULATION_RULE);
                massField.addItem(ContractUtils.EVALUATION_RULE_BUNDLE);
                massField.addItem(ContractUtils.CALCULATION_RULE_BUNDLE);
                break;
            default:
                obj = ContractUtils.REBATE_SETUP_DEFAULT;
                tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, contractDashboard, mode);
                if (tableResultCustom.getObjResult().length == 0) {
                    itemDetailsTable.setVisible(false);
                }
                if (isEditable) {
                    itemDetailsTable.setVisibleColumns(isEditable ? tableResultCustom.getObjResult() : Arrays.asList(tableResultCustom.getObjResult()).subList(1, tableResultCustom.getObjResult().length).toArray());
                    itemDetailsTable.setColumnHeaders(isEditable ? tableResultCustom.getObjResultHeader() : Arrays.asList(tableResultCustom.getObjResultHeader()).subList(1, tableResultCustom.getObjResultHeader().length).toArray(new String[tableResultCustom.getObjResultHeader().length - 1]));
                } else {
                    viewItemDetailsTable.setVisibleColumns(isEditable ? tableResultCustom.getObjResult() : Arrays.asList(tableResultCustom.getObjResult()).subList(1, tableResultCustom.getObjResult().length).toArray());
                    viewItemDetailsTable.setColumnHeaders(isEditable ? tableResultCustom.getObjResultHeader() : Arrays.asList(tableResultCustom.getObjResultHeader()).subList(1, tableResultCustom.getObjResultHeader().length).toArray(new String[tableResultCustom.getObjResultHeader().length - 1]));
                }
                resultsPanel.setWidth("75%");
                LOGGER.info("Calculation type is not selected in Information Tab");
                break;
        }
        if (!isEditable) {
            Object object[] = itemDetailsTable.getVisibleColumns();
            List viewList = new ArrayList();
            for (int i = 1; i < object.length - 1; i++) {
                viewList.add(object[i]);
            }
            itemDetailsTable.setVisibleColumns(viewList.toArray());
            itemDetailsTable.setVisibleColumns(viewList.toArray());
        }
        itemDetailsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        itemDetailsTable.setFilterFieldVisible("checkbox", false);
        
        for (Object object : itemDetailsTable.getVisibleColumns()) {
            if (String.valueOf(object).contains("Date")) {
                itemDetailsTable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
            }
        }
    }
    
    public void configureMassField() {
        massField.removeAllItems();
        massField.setNullSelectionAllowed(true);
        massField.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        massField.addItem(ConstantsUtils.SELECT_ONE);
        massField.addItem("RS Status");
        massField.addItem("Start Date");
        massField.addItem("End Date");
        massField.select(ConstantsUtils.SELECT_ONE);
        massField.setDescription((String) massField.getValue());
        massDate.setValue(null);
    }

    /**
     * Remove button method
     *
     * @param event
     */
    @UiHandler("btnRemove")
    public void removeBtnLogic(Button.ClickEvent event) {
              try {
            ifpLogic.saveToTempRebate(rsDetailsResultsBean.getItemIds(), false);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        final int count = ifpLogic.isEmpty(sessionDTO, Boolean.FALSE);
        if (count > 0) {
            final MessageBox msg = MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to remove the record from the Contract?", new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    if ("YES".equals(buttonId.name())) {
                        try {
                            if (rsDetailsResultsBean.size() > 0) {
                                ifpLogic.saveToTempRebate(rsDetailsResultsBean.getItemIds(), false);
                                rsDetailsResultsBean.removeAllItems();
                            }
                            List<Object[]> returnList = ifpLogic.getLazyItemRebateDeatils(0, count, null, false, record.getValue().toString(), Boolean.TRUE);
                            List<TempRebateDTO> list = ifpLogic.getCustomizedRebateDTO(returnList, record.getValue().toString());
                            for (TempRebateDTO temp : list) {
                                if (ifpLogic.validateCCPActuals(temp.getItemSystemId()) != 0) {
                                    AbstractNotificationUtils.getErrorNotification("Halt", "The selected record " + temp.getItemName() + " cannot be removed as there are Actuals associated to it.");
                                    return;
                                }
                            }
                            for (TempRebateDTO temp : list) {
                                ifpLogic.removeItem(Integer.valueOf(temp.getTempItemPriceRebateSystemId()));
                            }
                            itemDetailsTable.setCurrentPage(1);
                        } catch (Exception ex) {
                            LOGGER.error(ex.getMessage());
                        }
                    } else {
                        //Do nothing
                    }
                }
            }, ButtonId.YES, ButtonId.NO);
            msg.getButton(ButtonId.YES).focus();
            
        } else {
            AbstractNotificationUtils.getErrorNotification("Select error", "Please check mark a row for removal");
            return;
        }
    }
}
