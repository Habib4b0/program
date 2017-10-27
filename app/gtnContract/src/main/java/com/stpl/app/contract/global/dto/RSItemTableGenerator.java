package com.stpl.app.contract.global.dto;

import com.stpl.app.contract.abstractsearch.util.ConstantUtil;
import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.common.util.HelperListUtil;
import com.stpl.app.contract.dashboard.dto.NepFormulaLookUpDTO;
import com.stpl.app.contract.dashboard.dto.NetSalesRuleLookupDto;
import com.stpl.app.contract.dashboard.dto.PriceProtectionFormulaDTO;
import com.stpl.app.contract.dashboard.dto.RebatePlanDTO;
import com.stpl.app.contract.dashboard.dto.TempRebateDTO;
import com.stpl.app.contract.dashboard.ui.lookup.FormulaLookup;
import com.stpl.app.contract.dashboard.ui.lookup.NetSalesFormulaLookup;
import com.stpl.app.contract.dashboard.ui.lookup.NetSalesRuleLookup;
import com.stpl.app.contract.dashboard.ui.lookup.RebateFormulaPopup;
import com.stpl.app.contract.dashboard.ui.lookup.RebatePlanLookup;
import com.stpl.app.contract.dashboard.ui.lookup.RsDeductionLookup;
import com.stpl.app.contract.global.logic.CFPSearchLogic;
import com.stpl.app.contract.global.logic.IfpLogic;
import static com.stpl.app.contract.global.logic.RebateScheduleLogic.getImtdFormulaDescList;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.CommonUIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.QueryUtil;
import com.stpl.app.model.FormulaDetailsMaster;
import com.stpl.app.service.FormulaDetailsMasterLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.CustomePagedFilterTable;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.jboss.logging.Logger;

/**
 * The Class RSItemTableGenerator.
 */
public class RSItemTableGenerator extends DefaultFieldFactory {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(RSItemTableGenerator.class);
    RebateFormulaPopup lookUp = null;
    CustomePagedFilterTable table;
    private BeanItemContainer<TempRebateDTO> saveContainer;
    SessionDTO sessionDTO;
    private CommonUtil commonUtil = CommonUtil.getInstance();
    Map<String, List> tempDate;
    List<Date> tempDateList = new ArrayList<>();
    Object[] dates;
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
    HelperListUtil helperList = HelperListUtil.getInstance();
    private HashMap rpNameMap = new HashMap();
    private HashMap rpAmtMap = new HashMap();
    private CFPSearchLogic cfpLogic;
    private String userId;
    private String sessionId;
    List checkUpdate = new ArrayList();
    List checkSelect = new ArrayList();
    private int check;

    public RSItemTableGenerator(CustomePagedFilterTable table, BeanItemContainer<TempRebateDTO> saveContainer, final SessionDTO sessionDTO, final Map<String, List> tempDate, final Object[] dates) {
        this.saveContainer = saveContainer;
        this.table = table;
        this.sessionDTO = sessionDTO;
        this.tempDate = tempDate;
        this.dates = dates;
    }

    /**
     * Creates the field based on PropertyId.
     *
     * @param container the container
     * @param itemId the item id
     * @param propertyId the property id
     * @param uiContext the ui context
     * @return the field
     */
    public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {
        final TempRebateDTO temp = (TempRebateDTO) itemId;
        tempDateList.add(temp.getRebateStartDate());
        tempDateList.add(temp.getRebateEndDate());
        tempDate.put(temp.getItemId(), tempDateList);
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        sessionId = sessionDTO.getUiSessionId();
        if (Constants.CHECK_BOX.equals(propertyId)) {
            final ExtCustomCheckBox checkbox = new ExtCustomCheckBox();
            checkbox.setValue(temp.getCheckbox());
            checkbox.setReadOnly(false);
            checkbox.setId("contractdashboardcheckbox");
            checkbox.addClickListener(new ExtCustomCheckBox.ClickListener() {

                @Override
                public void click(ExtCustomCheckBox.ClickEvent event) {
                    temp.getTempItemPriceRebateSystemId();
                    IfpLogic.updateTempCheck(sessionDTO,temp.getTempItemPriceRebateSystemId(),checkbox.getValue());
                }
            }
            );
            checkbox.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(ValueChangeEvent event) {

                    saveContainer.addItem(itemId);
                    IfpLogic.updateTempCheck(sessionDTO, temp.getTempItemPriceRebateSystemId(), checkbox.getValue());
                    

                    if (temp.getCheckbox() != null) {
                        saveContainer.addItem(itemId);
                        check = temp.getCheckbox() ? 1 : 0;

                        checkUpdate = new ArrayList();

                        checkUpdate.add(check);
                        checkUpdate.add(sessionId);
                        checkUpdate.add(userId);
                        checkUpdate.add(temp.getTempItemPriceRebateSystemId());

                        QueryUtil.updateAppData(checkUpdate, "ItemPricingCheckUpdate");
                        
                        checkUpdate = new ArrayList();

                        checkUpdate.add(sessionId);
                        checkUpdate.add(userId);

                        checkSelect = new ArrayList();

                        checkSelect = QueryUtil.getAppData(checkUpdate, "ItemPricingCheckSelect", null);

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

        if (ConstantUtil.REBATE_START_DATE.equals(propertyId)) {
            try {
                final PopupDateField startDate = new PopupDateField();
                startDate.setRequired(true);
                startDate.setImmediate(true);
                startDate.setDateFormat(Constants.MM_DD_YYYY);
                startDate.setRequiredError("Start Date should  be present");
                startDate.setDescription(Constants.DATE);
                startDate.setValue(temp.getRebateStartDate());
                attachListeners(startDate, ConstantUtil.REBATE_START_DATE, itemId, temp);
                return startDate;
            } catch (Exception ex) {
                LOGGER.error(ex);
                return null;
            }
        }
        if (ConstantUtil.REBATE_END_DATE.equals(propertyId)) {
            try {
                final PopupDateField endDate = new PopupDateField();
                endDate.setDateFormat(Constants.MM_DD_YYYY);
                endDate.setImmediate(true);
                endDate.setDescription(Constants.DATE);
                endDate.setValue(temp.getRebateEndDate());
                attachListeners(endDate, ConstantUtil.REBATE_END_DATE, itemId, temp);
                return endDate;

            } catch (Exception ex) {
                LOGGER.error(ex);
                return null;
            }
        }

        if (Constants.FORMULA_ID.equals(propertyId)) {
            CustomTextField formulaId = new CustomTextField();
            formulaId.setImmediate(true);
            formulaId.addStyleName(Constants.SEARCH_ICON_STYLE);
            formulaId.setReadOnly(false);
            formulaId.setValue(temp.getFormulaId());
            formulaId.setReadOnly(true);
            formulaId.addClickListener(new CustomTextField.ClickListener() {
                /**
                 * Method used for formulaNo
                 */
                public void click(final CustomTextField.ClickEvent event) {

                    int imtdContRsdSid = Integer.valueOf(temp.getTempItemPriceRebateSystemId());
                    int itemSid = Integer.parseInt(temp.getItemSystemId());
                    if (lookUp == null) {
                        lookUp = new RebateFormulaPopup(imtdContRsdSid, itemSid, sessionDTO);
                        UI.getCurrent().addWindow(lookUp);
                        lookUp.addCloseListener(new Window.CloseListener() {
                            /**
                             * Adding close listener to lookup window.
                             */
                            @SuppressWarnings("PMD")
                            public void windowClose(final Window.CloseEvent event) {
                                try {
                                    DynamicQuery query = DynamicQueryFactoryUtil.forClass(FormulaDetailsMaster.class);
                                    List<Integer> formulaIdList = getImtdFormulaDescList(Integer.parseInt(temp.getItemSystemId()), sessionDTO);
                                    List<String> newList = new ArrayList<>(formulaIdList.size());
                                    for (Integer myInt : formulaIdList) {
                                        newList.add(String.valueOf(myInt));
                                    }
                                    if (!formulaIdList.isEmpty()) {
                                        query.add(RestrictionsFactoryUtil.in(Constants.FORMULA_ID, newList));
                                        query.add(RestrictionsFactoryUtil.eq(Constants.ITEM_ID, temp.getItemId()));
                                        query.add(RestrictionsFactoryUtil.le(Constants.START_DATE, new Date()));
                                        query.addOrder(OrderFactoryUtil.desc(Constants.START_DATE));
                                        List<FormulaDetailsMaster> companyTradeClass = FormulaDetailsMasterLocalServiceUtil.dynamicQuery(query);
                                        if (companyTradeClass != null && !companyTradeClass.isEmpty()) {

                                            String formulaNo = companyTradeClass.get(0).getFormulaNo();
                                            String formulaId = String.valueOf(companyTradeClass.get(0).getFormulaId());
                                            saveContainer.addItem(updateChanged(itemId, formulaNo, formulaId, companyTradeClass.get(0).getFormulaDesc()));
                                            table.setCurrentPage(table.getCurrentPage());
                                        } else {
                                            saveContainer.addItem(updateChanged(itemId, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY));
                                            table.setCurrentPage(table.getCurrentPage());
                                        }
                                    } else {
                                        saveContainer.addItem(updateChanged(itemId, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY));
                                        table.setCurrentPage(table.getCurrentPage());
                                    }
                                    lookUp = null;
                                } catch (SystemException ex) {
                                    LOGGER.error(ex);
                                }

                            }
                        });

                    }
                }
            });
            return formulaId;
        }
        if (Constants.REBATE_AMOUNT.equals(propertyId)) {
            final TextField rebateAmount = new TextField();
            rebateAmount.setRequired(true);
            rebateAmount.setRequiredError("Rebate Amount should be present");
            rebateAmount.setValue(temp.getRebateAmount());
            rebateAmount.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Will execute,when the endDate's value is changed.
                 */
                public void valueChange(final ValueChangeEvent event) {
                    if (!String.valueOf(rebateAmount.getValue()).isEmpty() || !String.valueOf(rebateAmount.getValue()).matches(Constants.DOUBLE_CHECK)) {
                        temp.setRebateAmount(rebateAmount.getValue());
                    } else {
                        temp.setRebateAmount("0");
                    }
                    saveContainer.addItem(itemId);

                }
            });
            return rebateAmount;
        }

        if (propertyId.equals(Constants.REBATE_BUNDLE_NO)) {
            final TextField bundleNo = new TextField();
            bundleNo.setValue((String) container.getItem(itemId).getItemProperty(Constants.REBATE_BUNDLE_NO).getValue());
            bundleNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in end date, function will be
                 * executed.
                 */
                public void valueChange(final ValueChangeEvent event) {
                    try {
                        saveContainer.addItem(itemId);
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
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            });
            return bundleNo;
        }
        if (propertyId.equals(ConstantsUtils.ATTACHED_STATUS)) {
            ComboBox attachedStatus = new ComboBox();
            try {
                commonUtil.loadComboBox(attachedStatus, "STATUS", false);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
            attachedStatus.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in end date, function will be
                 * executed.
                 */
                public void valueChange(final ValueChangeEvent event) {
                    try {
                        saveContainer.addItem(itemId);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            });
            return attachedStatus;
        }

        if (propertyId.equals(ConstantsUtils.FORMULA_NO)) {
            final CustomTextField formulaNo = new CustomTextField();
            formulaNo.setImmediate(true);
            formulaNo.setReadOnly(false);
            formulaNo.setValue(temp.getFormulaNo());
            formulaNo.setReadOnly(true);
            formulaNo.addStyleName(Constants.SEARCH_ICON_STYLE);

            formulaNo.addClickListener(new CustomTextField.ClickListener() {
                /**
                 * Method used for formulaNo
                 */
                public void click(final CustomTextField.ClickEvent event) {

                    try {
                        final FormulaLookup formulaLookup = new FormulaLookup(temp.getItemId());
                        UI.getCurrent().addWindow(formulaLookup);
                        formulaLookup.addCloseListener(new Window.CloseListener() {
                            @Override
                            public void windowClose(Window.CloseEvent e) {
                                if (formulaLookup.isSelected()) {
                                    PriceProtectionFormulaDTO rSFormulaDTO = formulaLookup.getSelectedItem();
                                    formulaNo.setReadOnly(false);
                                    formulaNo.setValue(rSFormulaDTO.getFormulaNo());
                                    formulaNo.setReadOnly(true);
                                    container.getContainerProperty(itemId, propertyId).setValue(rSFormulaDTO.getFormulaNo());
                                    table.getContainerProperty(itemId, "formulaName").setValue(rSFormulaDTO.getFormulaName());
                                    table.getItem(itemId).getItemProperty("formulaSystemId").setValue(String.valueOf(rSFormulaDTO.getFormulaID()));
                                    saveContainer.addItem(updateChanged(itemId, rSFormulaDTO.getFormulaID(), rSFormulaDTO.getFormulaNo(), rSFormulaDTO.getFormulaName()));
                                    saveContainer.addItem(itemId);
                                }
                            }
                        });
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            });
            return formulaNo;
        }

        if (propertyId.equals(Constants.EVALUATION_RULE)) {
            final CustomTextField evaluationRule = new CustomTextField();
            evaluationRule.setImmediate(true);
            evaluationRule.addStyleName(Constants.SEARCH_ICON_STYLE);
            evaluationRule.addClickListener(new CustomTextField.ClickListener() {
                @Override
                public void click(CustomTextField.ClickEvent event) {
                    try {
                        HelperDTO tempDto = new HelperDTO();
                        for (HelperDTO helperDto : helperList.getListNameMap().get(ConstantUtil.RULE_TYPE_LIST)) {
                            if (helperDto.getDescription().equalsIgnoreCase("Evaluation")) {
                                tempDto.setId(helperDto.getId());
                                tempDto.setDescription(helperDto.getDescription());
                                break;
                            }
                        }

                        final NetSalesRuleLookup netSalesLookup = new NetSalesRuleLookup(evaluationRule, tempDto, "Evaluation Rule Lookup");
                        UI.getCurrent().addWindow(netSalesLookup);
                        netSalesLookup.addCloseListener(new Window.CloseListener() {
                            @Override
                            public void windowClose(Window.CloseEvent e) {
                                if (netSalesLookup.isSelected()) {
                                    NetSalesRuleLookupDto searchDTO = netSalesLookup.getSelectedItem();
                                    container.getContainerProperty(itemId, propertyId).setValue(searchDTO.getRuleName());
                                    netSalesLookup.setData(searchDTO.getRuleSystemId());
                                    table.getItem(itemId).getItemProperty("evaluationSystemId").setValue(searchDTO.getRuleSystemId());
                                    saveContainer.addItem(itemId);
                                }
                            }
                        });
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            });
            evaluationRule.setReadOnly(true);
            return evaluationRule;
        }

        if (propertyId.equals(Constants.NET_SALES_FORMULA_NO)) {
            final CustomTextField netSalesFormulaNo = new CustomTextField();
            netSalesFormulaNo.setImmediate(true);
            netSalesFormulaNo.addStyleName(Constants.SEARCH_ICON_STYLE);
            netSalesFormulaNo.addClickListener(new CustomTextField.ClickListener() {
                /**
                 * Method used for formulaNo
                 */
                public void click(final CustomTextField.ClickEvent event) {

                    try {
                        final NetSalesFormulaLookup netSalesLookup = new NetSalesFormulaLookup(true, netSalesFormulaNo);
                        UI.getCurrent().addWindow(netSalesLookup);
                        netSalesLookup.addCloseListener(new Window.CloseListener() {
                            @Override
                            public void windowClose(Window.CloseEvent e) {
                                if (netSalesLookup.isSelected) {
                                    NepFormulaLookUpDTO netSalesDto = netSalesLookup.getNepFormulaDTO();
                                    container.getContainerProperty(itemId, propertyId).setValue(netSalesDto.getNepFormulaNo());
                                    table.getContainerProperty(itemId, Constants.NET_SALES_FORMULA_NAME).setValue(netSalesDto.getNepFormulaName());
                                    netSalesFormulaNo.setData(netSalesDto.getNepFormulaSystemID());
                                    table.getContainerProperty(itemId, "systemID").setValue(String.valueOf(netSalesDto.getNepFormulaSystemID()));
                                    saveContainer.addItem(itemId);
                                }

                            }
                        });
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            });
            netSalesFormulaNo.setReadOnly(true);
            return netSalesFormulaNo;
        }

        if (propertyId.equals(Constants.NET_SALES_RULE)) {
            final CustomTextField netSalesRule = new CustomTextField();
            netSalesRule.setImmediate(true);
            netSalesRule.addStyleName(Constants.SEARCH_ICON_STYLE);

            netSalesRule.addClickListener(new CustomTextField.ClickListener() {
                @Override
                public void click(CustomTextField.ClickEvent event) {
                    try {
                        HelperDTO tempDto = new HelperDTO();
                        for (HelperDTO helperDto : helperList.getListNameMap().get(ConstantUtil.RULE_TYPE_LIST)) {
                            if (helperDto.getDescription().equalsIgnoreCase("Net Sales")) {
                                tempDto.setId(helperDto.getId());
                                tempDto.setDescription(helperDto.getDescription());
                                break;
                            }
                        }
                        final NetSalesRuleLookup netSalesLookup = new NetSalesRuleLookup(netSalesRule, tempDto, "Net Sales Rule Lookup");
                        UI.getCurrent().addWindow(netSalesLookup);
                        netSalesLookup.addCloseListener(new Window.CloseListener() {
                            @Override
                            public void windowClose(Window.CloseEvent e) {
                                if (netSalesLookup.isSelected()) {
                                    NetSalesRuleLookupDto searchDTO = netSalesLookup.getSelectedItem();
                                    container.getContainerProperty(itemId, propertyId).setValue(searchDTO.getRuleName());
                                    netSalesLookup.setData(searchDTO.getRuleSystemId());
                                    table.getItem(itemId).getItemProperty("netSalesSystemId").setValue(searchDTO.getRuleSystemId());
                                    saveContainer.addItem(itemId);
                                }
                            }
                        });
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            });
            netSalesRule.setReadOnly(true);
            return netSalesRule;
        }

        if (propertyId.equals(Constants.EVALUATION_RULE_BUNDLE)) {
            final TextField evaluationRuleBundle = new TextField();
            evaluationRuleBundle.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in end date, function will be
                 * executed.
                 */
                public void valueChange(final ValueChangeEvent event) {
                    try {
                        saveContainer.addItem(itemId);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            });

            return evaluationRuleBundle;
        }
        if (propertyId.equals(Constants.CALCULATION_RULE_BUNDLE)) {
            final TextField calculationRuleBundle = new TextField();
            calculationRuleBundle.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in end date, function will be
                 * executed.
                 */
                public void valueChange(final ValueChangeEvent event) {
                    try {
                        saveContainer.addItem(itemId);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            });
            return calculationRuleBundle;
        }

        if (propertyId.equals(Constants.CALCULATION_RULE)) {
            final CustomTextField calculationRule = new CustomTextField();
            calculationRule.setImmediate(true);
            calculationRule.addStyleName(Constants.SEARCH_ICON_STYLE);
            calculationRule.addClickListener(new CustomTextField.ClickListener() {
                @Override
                public void click(CustomTextField.ClickEvent event) {
                    try {
                        HelperDTO tempDto = new HelperDTO();
                        for (HelperDTO helperDto : helperList.getListNameMap().get(ConstantUtil.RULE_TYPE_LIST)) {
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
                                    container.getContainerProperty(itemId, propertyId).setValue(searchDTO.getRuleName());
                                    netSalesLookup.setData(searchDTO.getRuleSystemId());
                                    table.getItem(itemId).getItemProperty("calculationSystemId").setValue(searchDTO.getRuleSystemId());
                                    saveContainer.addItem(itemId);
                                }
                            }
                        });
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            });
            calculationRule.setReadOnly(true);
            return calculationRule;
        }

        if (propertyId.equals(ConstantsUtils.REBATE_PLAN_NO)) {
            final CustomTextField customTextField = new CustomTextField();
            customTextField.setImmediate(true);
            customTextField.setValue(temp.getRebatePlanNo() == null || "null".equals(temp.getRebatePlanNo()) ? StringUtils.EMPTY : temp.getRebatePlanNo());
            customTextField.addStyleName(Constants.SEARCH_ICON_STYLE);
            customTextField.addClickListener(new CustomTextField.ClickListener() {

                @Override
                public void click(CustomTextField.ClickEvent event) {
                    try {
                        final RebatePlanLookup rebatePlanLookup = new RebatePlanLookup();
                        UI.getCurrent().addWindow(rebatePlanLookup);
                        rebatePlanLookup.addCloseListener(new Window.CloseListener() {

                            @Override
                            public void windowClose(Window.CloseEvent e) {
                                if (rebatePlanLookup.isSelected()) {
                                    RebatePlanDTO rebatePlanDTO = rebatePlanLookup.getSelectedItem();
                                    customTextField.setReadOnly(false);
                                    customTextField.setValue(rebatePlanDTO.getRebatePlanNo());
                                    customTextField.setReadOnly(true);
                                    container.getContainerProperty(itemId, propertyId).setValue(rebatePlanDTO.getRebatePlanNo());
                                    table.getContainerProperty(itemId, "rebatePlanName").setValue(rebatePlanDTO.getRebatePlanName());
                                    customTextField.setData(rebatePlanDTO.getRebatePlanSystemId());
                                    table.getItem(itemId).getItemProperty("rebatePlanSystemId").setValue(rebatePlanDTO.getRebatePlanSystemId());
                                    saveContainer.addItem(itemId);
                                    table.setRefresh(true);
                                }
                            }
                        });
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            });
            customTextField.setReadOnly(true);
            return customTextField;
        }

        if (propertyId.equals(Constants.DEDUCTION_CALENDAR_NO)) {
            final CustomTextField deductionCalendarNo = new CustomTextField();
            deductionCalendarNo.setImmediate(true);
            deductionCalendarNo.setReadOnly(false);
            deductionCalendarNo.setValue(temp.getFormulaNo());
            deductionCalendarNo.setReadOnly(true);
            deductionCalendarNo.addStyleName(Constants.SEARCH_ICON_STYLE);

            deductionCalendarNo.addClickListener(new CustomTextField.ClickListener() {
                /**
                 * Method used for formulaNo
                 */
                public void click(final CustomTextField.ClickEvent event) {

                    try {
                        final RsDeductionLookup deductionLookup = new RsDeductionLookup(deductionCalendarNo);
                        UI.getCurrent().addWindow(deductionLookup);
                        deductionLookup.addCloseListener(new Window.CloseListener() {
                            @Override
                            public void windowClose(Window.CloseEvent e) {
                                if (deductionLookup.isSelected) {
                                    RsDeductionLookupDto deductionDto = deductionLookup.getDeductionDto();
                                    container.getContainerProperty(itemId, propertyId).setValue(deductionDto.getDeductionNo());
                                    table.getContainerProperty(itemId, Constants.DEDUCTION_CALENDAR_NAME).setValue(deductionDto.getDeductionName());
                                    deductionCalendarNo.setData(deductionDto.getDeductionSystemId());
                                    table.getItem(itemId).getItemProperty("deductionSystemId").setValue(deductionDto.getDeductionSystemId());
                                    saveContainer.addItem(itemId);
                                    table.setRefresh(true);
                                }
                            }
                        });
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            });
            return deductionCalendarNo;
        }

        if (propertyId.equals(ConstantsUtils.FORMULA_TYPE)) {
            ComboBox formulaType = new ComboBox();
            formulaType.addItem("-Select One-");
            formulaType.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in end date, function will be
                 * executed.
                 */
                public void valueChange(final ValueChangeEvent event) {
                    try {
                        saveContainer.addItem(itemId);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            });
            return formulaType;
        }

        return null;
    }

    public Object updateChanged(Object itemId, String formulaNo, String formulaId, String formulaName) {
        ((TempRebateDTO) itemId).setFormulaId(formulaId);
        ((TempRebateDTO) itemId).setFormulaNo(formulaNo);
        ((TempRebateDTO) itemId).setFormulaName(formulaName);
        return itemId;
    }

    public void attachListeners(final AbstractField field, final String component, final Object itemId, final TempRebateDTO temp) {
        field.setImmediate(true);
        field.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                try {
                    if (ConstantUtil.REBATE_START_DATE.equals(component)) {
                        tempDateList = tempDate.get(temp.getItemId());
                        if (((PopupDateField) field).getValue() != null && ((PopupDateField) field).getValue().before((Date) dates[0])) {
                            AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be before " + format.format(dates[0]));
                            detachListeners(field);
                            ((PopupDateField) field).setValue(tempDateList.get(0));
                            attachListeners(field, component, itemId, temp);
                            return;
                        } else if (((PopupDateField) field).getValue() != null && (Date) dates[1] != null && ((PopupDateField) field).getValue().after((Date) dates[1])) {
                            AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be after " + format.format(dates[1]));
                            detachListeners(field);
                            ((PopupDateField) field).setValue(tempDateList.get(0));
                            attachListeners(field, component, itemId, temp);
                            return;
                        } else {
                            temp.setRebateStartDate(((PopupDateField) field).getValue());
                            saveContainer.addItem(itemId);
                            ((PopupDateField) field).setDescription(CommonUIUtils.convert2DigitTo4DigitYear(((PopupDateField) field).getValue()));
                        }
                    } else if (ConstantUtil.REBATE_END_DATE.equals(component)) {
                        tempDateList = tempDate.get(temp.getItemId());
                        if ((Date) dates[1] != null && ((PopupDateField) field).getValue().after((Date) dates[1])) {
                            detachListeners(field);
                            ((PopupDateField) field).setValue(tempDateList.get(1) != null ? tempDateList.get(1) : null);
                            AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "End date cannot be after " + format.format(dates[1]));
                            attachListeners(field, component, itemId, temp);
                            return;
                        } else if (((PopupDateField) field).getValue().before((Date) dates[0])) {
                            detachListeners(field);
                            ((PopupDateField) field).setValue(tempDateList.get(1) != null ? tempDateList.get(1) : null);
                            AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "End date cannot be before " + format.format(dates[0]));
                            attachListeners(field, component, itemId, temp);
                            return;
                        } else {
                            temp.setRebateEndDate(((PopupDateField) field).getValue());
                            saveContainer.addItem(itemId);
                            ((PopupDateField) field).setDescription(CommonUIUtils.convert2DigitTo4DigitYear(((PopupDateField) field).getValue()));
                        }
                    }

                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });

    }

    public void detachListeners(final AbstractField field) {

        List<Property.ValueChangeListener> listeners;

        listeners = (List<Property.ValueChangeListener>) field.getListeners(Property.ValueChangeEvent.class);
        for (final Property.ValueChangeListener object : listeners) {
            field.removeValueChangeListener(object);
        }
    }
}
