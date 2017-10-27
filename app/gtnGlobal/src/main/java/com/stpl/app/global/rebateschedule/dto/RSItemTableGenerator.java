package com.stpl.app.global.rebateschedule.dto;

import com.stpl.app.global.cfp.logic.CFPSearchLogic;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.item.util.CommonUtils;
import com.stpl.app.global.common.ui.lookup.FormulaLookup;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.company.util.QueryUtils;
import com.stpl.app.global.netsalesformula.dto.NetSalesRuleLookupDto;
import com.stpl.app.global.netsalesformula.ui.form.NetSalesRuleLookUp;
import com.stpl.app.global.rebateschedule.logic.tablelogic.RebateSetupTableLogic;
import com.stpl.app.global.rebateschedule.ui.lookup.NetSalesFormulaLookup;
import com.stpl.app.global.rebateschedule.ui.lookup.RsDeductionLookup;
import com.stpl.app.global.rebateschedule.ui.lookup.RebatePlanLookup;
import com.stpl.app.global.rebateschedule.util.RsUtils;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.app.util.ValidationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class for generating Rebate Schedule Item Table .
 */
public class RSItemTableGenerator extends DefaultFieldFactory {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -6530100792521678931L;

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(RSItemTableGenerator.class);

    /**
     * The commons util.
     */
    private final CommonUtils commonsUtil = new CommonUtils();
    /**
     * The name.
     */
    public ComboBox formulaId;

    private CommonUtil commonUtil = CommonUtil.getInstance();

    /**
     * The name.
     */
    private String name = "";

    /**
     * The formula name.
     */
    public TextField formulaName;

    /**
     * The Rebate Plan Level
     */
    public String rebatePlanLevel;

    /**
     * The formula type.
     */
    private CustomTextField formulaNo;

    private TextField formulaMethodId;

    private HashMap rpNameMap = new HashMap();
    private HashMap rpAmtMap = new HashMap();
    private ExtPagedTable table;
    private BeanItemContainer itemResultBean;
    HelperListUtil helperList = HelperListUtil.getInstance();
    private RebateSetupTableLogic rebateSetupTableLogic;
    private String userId;
    private String sessionId;
    List checkUpdate = new ArrayList();
    List checkSelect = new ArrayList();
    private int check;
    SessionDTO sessionDTO;

    /**
     * RSItemTableGenerator for generating fields based on table fields.
     *
     * @param table
     * @param itemResultBean
     * @param rebateSetupTableLogic
     * @param rebatePlanLevel
     */
    public RSItemTableGenerator(ExtPagedTable table, BeanItemContainer itemResultBean, String rebatePlanLevel, RebateSetupTableLogic rebateSetupTableLogic, SessionDTO sessionDTO) {
        this.itemResultBean = itemResultBean;
        this.rebatePlanLevel = rebatePlanLevel;
        this.table = table;
        this.rebateSetupTableLogic = rebateSetupTableLogic;
        this.sessionDTO = sessionDTO;
    }

    /**
     * Method to create required fields for UI table based on property Id .
     *
     * @param container the container
     * @param itemId the item id
     * @param propertyId the property id
     * @param uiContext the ui context
     * @return the field
     */
    public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {

        try {
            final ItemDetailsDTO dto = (ItemDetailsDTO) itemId;
            if (propertyId.equals(ConstantsUtils.CHECK_BOX)) {
                final CheckBox checkbox = new CheckBox();
                checkbox.setReadOnly(false);
                checkbox.setValue(dto.getCheckbox());
                checkbox.setImmediate(true);
                userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
                sessionId = sessionDTO.getUiSessionId();

                checkbox.addValueChangeListener(new Property.ValueChangeListener() {

                    public void valueChange(Property.ValueChangeEvent event) {
                        if (dto.getCheckbox() != null) {
                            itemResultBean.addItem(itemId);
                            check = dto.getCheckbox() ? 1 : 0;
                            checkUpdate = new ArrayList();

                            checkUpdate.add(check);
                            checkUpdate.add(sessionId);
                            checkUpdate.add(userId);
                            checkUpdate.add(dto.getItemId());

                            QueryUtils.updateAppData(checkUpdate, "RSCheckUpdate");
                            checkUpdate = new ArrayList();

                            checkUpdate.add(sessionId);
                            checkUpdate.add(userId);

                            checkSelect = new ArrayList();

                            checkSelect = QueryUtils.getAppData(checkUpdate, "RSCheckSelect", null);

                            if (checkSelect.size() == 0) {
                                table.setCurrentPage(table.getCurrentPage());
                                table.setColumnCheckBox(ConstantsUtils.CHECK_BOX, true, true);
                            } else {
                                table.setCurrentPage(table.getCurrentPage());
                                table.setColumnCheckBox(ConstantsUtils.CHECK_BOX, true, false);
                            }

                        }
                    }
                });
                return checkbox;
            }
            if (propertyId.equals(ConstantsUtils.START_DATE)) {

                final PopupDateField startDate = new PopupDateField();
                startDate.setDescription(ConstantsUtils.DATE_DES);
                startDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
                startDate.setImmediate(true);
                startDate.addStyleName("datefieldcentered");
                startDate.setValue(dto.getStartDate());
                startDate.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * After changing the value in start date, function will be
                     * executed.
                     */
                    @SuppressWarnings("PMD")
                    public void valueChange(final ValueChangeEvent event) {
                       valueChangeListener(  startDate, itemId);

                    }
                });
                return startDate;
            }
            if (propertyId.equals(ConstantsUtils.END_DATE)) {

                final PopupDateField endDate = new PopupDateField();
                endDate.setDescription(ConstantsUtils.DATE_DES);
                endDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
                endDate.addStyleName("datefieldcentered");
                endDate.setImmediate(true);
                endDate.setValue(dto.getEndDate());
                endDate.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * After changing the value in end date, function will be
                     * executed.
                     */
                    public void valueChange(final ValueChangeEvent event) {

                        itemResultBean.addItem(itemId);
                        endDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(endDate.getValue()));

                    }
                });
                return endDate;
            }

            if (propertyId.equals(ConstantsUtils.ATTACHED_STATUS)) {
                ComboBox attachedStatus = new ComboBox();
                commonUtil.loadComboBox(attachedStatus, "STATUS", false);
                attachedStatus.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * After changing the value in end date, function will be
                     * executed.
                     */
                    public void valueChange(final ValueChangeEvent event) {
                            itemResultBean.addItem(itemId);
                        
                    }
                });
                return attachedStatus;
            }

            if (propertyId.equals(ConstantsUtils.FORMULA_NO)) {
                formulaNo = new CustomTextField();
                formulaNo.setImmediate(true);
                formulaNo.setValue(dto.getFormulaNo());
                formulaNo.addStyleName(ConstantsUtils.SEARCH_SYLENAME);

                formulaNo.addClickListener(new CustomTextField.ClickListener() {
                    /**
                     * Method used for formulaNo
                     */
                    public void click(final CustomTextField.ClickEvent event) {
                        formulaNoCustomTextFieldLister(dto, itemId, propertyId, container);

                    }
                });
                return formulaNo;
            }
            if (propertyId.equals(ConstantsUtils.FORMULA_NAME)) {
                formulaName = new TextField();
                formulaName.setImmediate(true);
                formulaName.setValue(dto.getFormulaName());
                formulaName.setReadOnly(true);
                return formulaName;
            }

            if (propertyId.equals(ConstantsUtils.FORMULA_METHD_ID)) {
                formulaMethodId = new TextField();
                formulaMethodId.setImmediate(true);
                formulaMethodId.setValue(dto.getFormulaMethodId());
                return formulaMethodId;
            }

            if (propertyId.equals(RsUtils.REBATE_PLAN_NO)) {
                final CustomTextField customTextField = new CustomTextField();
                customTextField.setImmediate(true);
                customTextField.setValue(dto.getRebatePlanNo() == null || "null".equals(dto.getRebatePlanNo()) ? StringUtils.EMPTY : dto.getRebatePlanNo());
                customTextField.addStyleName(ConstantsUtils.SEARCH_SYLENAME);
                customTextField.addClickListener(new CustomTextField.ClickListener() {

                    @Override
                    public void click(CustomTextField.ClickEvent event) {
           
                            final RebatePlanLookup rebatePlanLookup = new RebatePlanLookup();
                            UI.getCurrent().addWindow(rebatePlanLookup);
                            rebatePlanLookup.addCloseListener(new Window.CloseListener() {

                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    if (rebatePlanLookup.isSelected()) {
                                        RebatePlanDTO rebatePlanDTO = rebatePlanLookup.getSelectedItem();
                                        customTextField.setValue(rebatePlanDTO.getRebatePlanNo());
                                        container.getContainerProperty(itemId, propertyId).setValue(rebatePlanDTO.getRebatePlanNo());
                                        table.getContainerProperty(itemId, "rebatePlanName").setValue(rebatePlanDTO.getRebatePlanName());
                                        customTextField.setData(rebatePlanDTO.getRebatePlanSystemId());
                                        table.getItem(itemId).getItemProperty("rebatePlanSystemId").setValue(rebatePlanDTO.getRebatePlanSystemId());
                                        itemResultBean.addItem(itemId);
                                    }
                                }
                            });
                       
                    }
                });
                return customTextField;
            }

            if (propertyId.equals(ConstantsUtils.REBATE_AMOUNT)) {
                final TextField rebateAmount = new TextField();
                rebateAmount.setRequired(true);
                rebateAmount.setRequiredError("Rebate Amount should be present");
                rebateAmount.setValidationVisible(true);
                rebateAmount.addValidator(new StringLengthValidator(" Rebate Amount should be less than 38 Characters", 0, NumericConstants.THIRTY_EIGHT, true));
                rebateAmount.addValidator(new RegexpValidator(ValidationUtils.REBATE_SCHEDULE_AMT, ValidationUtils.NUM_VALID_MSG));
                rebateAmount.setValue(dto.getRebateAmount());
                if ("Single Rebate Plan".equals(rebatePlanLevel)) {
                    rebateAmount.setEnabled(false);
                }
                rebateAmount.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * After changing the value in end date, function will be
                     * executed.
                     */
                    public void valueChange(final ValueChangeEvent event) {
                    
                            itemResultBean.addItem(itemId);
                            if (StringUtils.isBlank(rebateAmount.getValue())) {
                                rebateAmount.setValue("0.0");
                            }
                            String bundle = (String) container.getItem(itemId).getItemProperty(ConstantsUtils.REBATE_BUNDLE_NO).getValue();
                            String rsAmount = (String) container.getItem(itemId).getItemProperty(ConstantsUtils.REBATE_AMOUNT).getValue();
                            rpAmtMap.put(bundle, rsAmount);
                       
                    }
                });
                return rebateAmount;
            }

            if (propertyId.equals(ConstantsUtils.REBATE_BUNDLE_NO)) {
                final TextField bundleNo = new TextField();
                bundleNo.setId("BundleNumber");
                bundleNo.setValue((String) container.getItem(itemId).getItemProperty(ConstantsUtils.REBATE_BUNDLE_NO).getValue());
                bundleNo.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * After changing the value in end date, function will be
                     * executed.
                     */
                    public void valueChange(final ValueChangeEvent event) {
                      

                            itemResultBean.addItem(itemId);
                            String bundle = (String) container.getItem(itemId).getItemProperty(ConstantsUtils.REBATE_BUNDLE_NO).getValue();
                            if (StringUtils.isNotBlank(event.getProperty().getValue().toString())) {
                                HelperDTO rpName = (HelperDTO) rpNameMap.get(bundle);
                                String rpAmount = (String) rpAmtMap.get(bundle);
                                if (rpAmount != null) {
                                    container.getItem(itemId).getItemProperty(ConstantsUtils.REBATE_AMOUNT).setValue(rpAmount);
                                }
                                if (rpName != null) {
                                    container.getItem(itemId).getItemProperty(ConstantsUtils.REBATE_PLAN_NAME).setValue(rpName);
                                }
                            }
                        
                    }
                });
                return bundleNo;
            }
            if (propertyId.equals(ConstantsUtils.DEDUCTION_CALENDAR_NO)) {
                final CustomTextField deductionCalendarNo = new CustomTextField();
                deductionCalendarNo.setImmediate(true);
                deductionCalendarNo.setValue(dto.getFormulaNo());
                deductionCalendarNo.addStyleName(ConstantsUtils.SEARCH_SYLENAME);

                deductionCalendarNo.addClickListener(new CustomTextField.ClickListener() {
                    /**
                     * Method used for formulaNo
                     */
                    public void click(final CustomTextField.ClickEvent event) {

                            final RsDeductionLookup deductionLookup = new RsDeductionLookup(deductionCalendarNo);
                            UI.getCurrent().addWindow(deductionLookup);
                            deductionLookup.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    if (deductionLookup.isSelected) {
                                        RsDeductionLookupDto deductionDto = deductionLookup.getDeductionDto();
                                        container.getContainerProperty(itemId, propertyId).setValue(deductionDto.getDeductionNo());
                                        table.getContainerProperty(itemId, ConstantsUtils.DEDUCTION_CALENDAR_NAME).setValue(deductionDto.getDeductionName());
                                        deductionCalendarNo.setData(deductionDto.getDeductionSystemId());
                                        table.getItem(itemId).getItemProperty("deductionSystemId").setValue(deductionDto.getDeductionSystemId());
                                        itemResultBean.addItem(itemId);
                                        rebateSetupTableLogic.clearFilters();
                                        rebateSetupTableLogic.clearSortByColumns();
                                        rebateSetupTableLogic.setCurrentPage(rebateSetupTableLogic.getCurrentPage());
                                    }

                                }
                            });
                        
                    }
                });
                return deductionCalendarNo;
            }

            if (propertyId.equals(ConstantsUtils.NET_SALES_FORMULA_NO)) {
                final CustomTextField netSalesFormulaNo = new CustomTextField();
                netSalesFormulaNo.setImmediate(true);
                netSalesFormulaNo.addStyleName(ConstantsUtils.SEARCH_SYLENAME);

                netSalesFormulaNo.addClickListener(new CustomTextField.ClickListener() {
                    /**
                     * Method used for formulaNo
                     */
                    public void click(final CustomTextField.ClickEvent event) {

                      
                            final NetSalesFormulaLookup netSalesLookup = new NetSalesFormulaLookup(netSalesFormulaNo);
                            UI.getCurrent().addWindow(netSalesLookup);
                            netSalesLookup.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    if (netSalesLookup.isSelected) {
                                        NetSalesFormulaDTO netSalesDto = netSalesLookup.getNetSalesDTO();
                                        container.getContainerProperty(itemId, propertyId).setValue(netSalesDto.getNetSalesFormulaNo());
                                        table.getContainerProperty(itemId, ConstantsUtils.NET_SALES_FORMULA_NAME).setValue(netSalesDto.getNetSalesFormulaName());
                                        netSalesFormulaNo.setData(netSalesDto.getSystemID());
                                        table.getItem(itemId).getItemProperty("systemID").setValue(netSalesDto.getSystemID());
                                        itemResultBean.addItem(itemId);
                                    }

                                }
                            });
                        
                    }
                });
                return netSalesFormulaNo;
            }

            if (propertyId.equals(ConstantsUtils.NET_SALES_RULE)) {
                final CustomTextField netSalesRule = new CustomTextField();
                netSalesRule.setImmediate(true);
                netSalesRule.addStyleName(ConstantsUtils.SEARCH_SYLENAME);

                netSalesRule.addClickListener(new CustomTextField.ClickListener() {
                    @Override
                    public void click(CustomTextField.ClickEvent event) {
                   
                            HelperDTO tempDto = new HelperDTO();
                            for (HelperDTO helperDto : helperList.getListNameMap().get(ConstantsUtils.RULE_TYPE_LIST)) {
                                if (helperDto.getDescription().equalsIgnoreCase("Net Sales")) {
                                    tempDto.setId(helperDto.getId());
                                    tempDto.setDescription(helperDto.getDescription());
                                    break;
                                }
                            }

                            final NetSalesRuleLookUp netSalesLookup = new NetSalesRuleLookUp(netSalesRule, tempDto, "Net Sales Rule Lookup", ConstantsUtils.REBATE_SCHEDULE);
                            UI.getCurrent().addWindow(netSalesLookup);
                            netSalesLookup.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    if (netSalesLookup.isSelected()) {
                                        NetSalesRuleLookupDto searchDTO = netSalesLookup.getSelectedItem();

                                        container.getContainerProperty(itemId, propertyId).setValue(searchDTO.getRuleName());
                                        netSalesLookup.setData(searchDTO.getRuleSystemId());
                                        table.getItem(itemId).getItemProperty("netSalesSystemId").setValue(searchDTO.getRuleSystemId());
                                        itemResultBean.addItem(itemId);
                                        netSalesRule.setValue(searchDTO.getRuleNo());

                                    }
                                }
                            });
                       
                    }
                });

                return netSalesRule;
            }
            if (propertyId.equals(ConstantsUtils.EVALUATION_RULE)) {
                final CustomTextField evaluationRule = new CustomTextField();
                evaluationRule.setImmediate(true);
                evaluationRule.addStyleName(ConstantsUtils.SEARCH_SYLENAME);

                evaluationRule.addClickListener(new CustomTextField.ClickListener() {
                    @Override
                    public void click(CustomTextField.ClickEvent event) {
                    
                            HelperDTO tempDto = new HelperDTO();
                            for (HelperDTO helperDto : helperList.getListNameMap().get(ConstantsUtils.RULE_TYPE_LIST)) {
                                if (helperDto.getDescription().equalsIgnoreCase("Evaluation")) {
                                    tempDto.setId(helperDto.getId());
                                    tempDto.setDescription(helperDto.getDescription());
                                    break;
                                }
                            }

                            final NetSalesRuleLookUp netSalesLookup = new NetSalesRuleLookUp(evaluationRule, tempDto, "Evaluation Rule Lookup", ConstantsUtils.REBATE_SCHEDULE);
                            UI.getCurrent().addWindow(netSalesLookup);
                            netSalesLookup.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    if (netSalesLookup.isSelected()) {
                                        NetSalesRuleLookupDto searchDTO = netSalesLookup.getSelectedItem();

                                        container.getContainerProperty(itemId, propertyId).setValue(searchDTO.getRuleName());
                                        netSalesLookup.setData(searchDTO.getRuleSystemId());
                                        table.getItem(itemId).getItemProperty("evaluationSystemId").setValue(searchDTO.getRuleSystemId());
                                        itemResultBean.addItem(itemId);
                                        evaluationRule.setValue(searchDTO.getRuleNo());

                                    }
                                }
                            });
                       
                    }
                });

                return evaluationRule;
            }
            if (propertyId.equals(ConstantsUtils.CALCULATION_RULE)) {
                final CustomTextField calculationRule = new CustomTextField();
                calculationRule.setImmediate(true);
                calculationRule.addStyleName(ConstantsUtils.SEARCH_SYLENAME);

                calculationRule.addClickListener(new CustomTextField.ClickListener() {
                    @Override
                    public void click(CustomTextField.ClickEvent event) {
                            HelperDTO tempDto = new HelperDTO();
                            for (HelperDTO helperDto : helperList.getListNameMap().get(ConstantsUtils.RULE_TYPE_LIST)) {
                                if (helperDto.getDescription().equalsIgnoreCase("Calculation")) {
                                    tempDto.setId(helperDto.getId());
                                    tempDto.setDescription(helperDto.getDescription());
                                    break;
                                }
                            }

                            final NetSalesRuleLookUp netSalesLookup = new NetSalesRuleLookUp(calculationRule, tempDto, "Calculation Rule Lookup", ConstantsUtils.REBATE_SCHEDULE);
                            UI.getCurrent().addWindow(netSalesLookup);
                            netSalesLookup.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    if (netSalesLookup.isSelected()) {
                                        NetSalesRuleLookupDto searchDTO = netSalesLookup.getSelectedItem();

                                        container.getContainerProperty(itemId, propertyId).setValue(searchDTO.getRuleName());
                                        netSalesLookup.setData(searchDTO.getRuleSystemId());
                                        table.getItem(itemId).getItemProperty("calculationSystemId").setValue(searchDTO.getRuleSystemId());
                                        itemResultBean.addItem(itemId);
                                        calculationRule.setValue(searchDTO.getRuleNo());

                                    }
                                }
                            });
                        
                    }
                });

                return calculationRule;
            }

            if (propertyId.equals(ConstantsUtils.EVALUATION_RULE_BUNDLE)) {
                final TextField evaluationRuleBundle = new TextField();
                evaluationRuleBundle.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * After changing the value in end date, function will be
                     * executed.
                     */
                    public void valueChange(final ValueChangeEvent event) {
                        
                            itemResultBean.addItem(itemId);

                       
                    }
                });

                return evaluationRuleBundle;
            }
            if (propertyId.equals(ConstantsUtils.CALCULATION_RULE_BUNDLE)) {
                final TextField calculationRuleBundle = new TextField();
                calculationRuleBundle.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * After changing the value in end date, function will be
                     * executed.
                     */
                    public void valueChange(final ValueChangeEvent event) {
                        
                            itemResultBean.addItem(itemId);

                       
                    }
                });
                return calculationRuleBundle;
            }

        } catch (Exception exception) {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing          
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
            LOGGER.error(exception);
            exception.printStackTrace();

        }

        return null;

    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the commons util.
     *
     * @return the commons util
     */
    public CommonUtils getCommonsUtil() {
        return commonsUtil;
    }

    /**
     * Gets the formula id.
     *
     * @return the formula id
     */
    public ComboBox getFormulaId() {
        return formulaId;
    }

    /**
     * Sets the formula id.
     *
     * @param formulaId the new formula id
     */
    public void setFormulaId(final ComboBox formulaId) {
        this.formulaId = formulaId;
    }

    /**
     * Gets the formula name.
     *
     * @return the formula name
     */
    public TextField getFormulaName() {
        return formulaName;
    }

    /**
     * Sets the formula name.
     *
     * @param formulaName the new formula name
     */
    public void setFormulaName(final TextField formulaName) {
        this.formulaName = formulaName;
    }

    public Object updateChanged(Object itemId, String formulaNo, String formulaName, String formulaType) {

        ((ItemDetailsDTO) itemId).setFormulaNo(formulaNo);
        ((ItemDetailsDTO) itemId).setFormulaName(formulaName);
        ((ItemDetailsDTO) itemId).setFormulaType(formulaType);

        return itemId;
    }
public void valueChangeListener( PopupDateField startDate,Object itemId){
     try {
                            startDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(startDate.getValue()));
                            itemResultBean.addItem(itemId);
                        } catch (Exception ex) {
                            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                            LOGGER.error(errorMsg);
                            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
                                /**
                                 * The method is triggered when a button of the
                                 * message box is pressed .
                                 *
                                 * @param buttonId The buttonId of the pressed
                                 * button.
                                 */
                                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing              
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        }
    }

    public void windowCloseListener(FormulaLookup formulaLookup,Object itemId, final Object propertyId,final Container container) {
        try {
            if (formulaLookup.isSelected()) {
                RSFormulaDTO rSFormulaDTO = formulaLookup.getSelectedItem();
                itemResultBean.addItem(updateChanged(itemId, rSFormulaDTO.getFormulaNo(), rSFormulaDTO.getFormulaName(), rSFormulaDTO.getFormulaType() == null ? StringUtils.EMPTY : rSFormulaDTO.getFormulaType().getDescription()));
                table.setCurrentPage(table.getCurrentPage());
            }
            if (formulaLookup.isSelected()) {
                RSFormulaDTO rSFormulaDTO = formulaLookup.getSelectedItem();
                formulaNo.setValue(rSFormulaDTO.getFormulaNo());
                container.getContainerProperty(itemId, propertyId).setValue(rSFormulaDTO.getFormulaNo());
                table.getContainerProperty(itemId, "formulaName").setValue(rSFormulaDTO.getFormulaName());
                ((ItemDetailsDTO) itemId).setFormulaId(rSFormulaDTO.getFormulaID());
                itemResultBean.addItem(itemId);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
    
    public void formulaNoCustomTextFieldLister(ItemDetailsDTO dto,final Object itemId, final Object propertyId,final Container container){
         try {
                            final FormulaLookup formulaLookup = new FormulaLookup(dto.getItemId());
                            UI.getCurrent().addWindow(formulaLookup);
                            formulaLookup.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                     windowCloseListener( formulaLookup, itemId,   propertyId,  container);
                                   
                                }
                            });
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
    }
}
