package com.stpl.app.contract.global.dto;

import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.dashboard.dto.TempPricingDTO;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.global.logic.CFPSearchLogic;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.stpl.app.contract.global.util.CommonUtils;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.CommonUIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.app.contract.util.QueryUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;

/**
 * The Class ItemPricingGenerator.
 */
public class ItemPricingGenerator extends DefaultFieldFactory {

    /**
     * The commons util.
     */
    private final CommonUtils commonsUtil = new CommonUtils();
    /**
     * The Constant REGEX_NUM.
     */
    public static final String REGEX_NUM = "([|0-9]*.[0-9]{1,2})";
    /**
     * The Constant REGEX_ERROR.
     */
    public static final String REGEX_ERROR = "Please Enter Only Numbers with two decimal places";
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ItemPricingGenerator.class);

    private BeanItemContainer<TempPricingDTO> saveContainer;

    private List<HelperDTO> priceTypeList;
    private List<HelperDTO> ptTypeList;
    private List<HelperDTO> ptIntervalList;
    private List<HelperDTO> ptfrequencyList;
    private List<HelperDTO> itemStatusList;
    ExtPagedTable itemDetailsTable;

    boolean updateFlag = false;

    Object[] dates;

    Object[] psDates;

    SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");

    Map<String, List> tempDate;

    List<Date> tempDateList = new ArrayList<>();
    Map<Integer, String> priceType;
    SessionDTO sessionDTO;

    private CFPSearchLogic cfpLogic;

    private String userId;

    private String sessionId;

    List checkUpdate = new ArrayList();

    List checkSelect = new ArrayList();

    private int check;

    /**
     *
     * @param saveContainer
     * @param dates
     * @param psDates
     * @param tempDate
     * @param sessionDTO
     * @param itemDetailsTable
     */
    public ItemPricingGenerator(BeanItemContainer<TempPricingDTO> saveContainer, final Object[] dates, final Object[] psDates, final Map<String, List> tempDate, SessionDTO sessionDTO, ExtPagedTable itemDetailsTable) {
        this.saveContainer = saveContainer;
        this.dates = dates;
        this.tempDate = tempDate;
        this.psDates = psDates;
        this.sessionDTO = sessionDTO;
        this.itemDetailsTable = itemDetailsTable;
        try {
            priceTypeList = ContractUtils.getInstance().getPriceType();
            ptTypeList = ContractUtils.getInstance().getPriceToleranceType();
            ptIntervalList = ContractUtils.getInstance().getPriceToleranceInterval();
            ptfrequencyList = ContractUtils.getInstance().getPriceToleranceFrequency();
            itemStatusList = ContractUtils.getInstance().getItemStatus();
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            LOGGER.error(errorMsg);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        }
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
        try {
            final TempPricingDTO temp = (TempPricingDTO) itemId;
            tempDateList.add(temp.getStartDate());
            tempDateList.add(temp.getEndDate());
            tempDateList.add(temp.getCpStartDate());
            tempDateList.add(temp.getCpEndDate());
            tempDate.put(temp.getItemId(), tempDateList);
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            sessionId = sessionDTO.getUiSessionId();

            if (Constants.CHECK_BOX.equals(propertyId)) {
                final CheckBox checkbox = new CheckBox();
                checkbox.setValue(temp.getCheckbox());
                checkbox.setReadOnly(false);
                checkbox.setId("contractdashboardcheckbox");
                checkbox.addValueChangeListener(new Property.ValueChangeListener() {

                    @Override
                    public void valueChange(ValueChangeEvent event) {
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
                                itemDetailsTable.setCurrentPage(itemDetailsTable.getCurrentPage());
                                itemDetailsTable.setColumnCheckBox(ConstantsUtils.CHECK_BOX, true, true);
                            } else {
                                itemDetailsTable.setCurrentPage(itemDetailsTable.getCurrentPage());
                                itemDetailsTable.setColumnCheckBox(ConstantsUtils.CHECK_BOX, true, false);
                            }

                        }
                        IfpLogic.updateTempCheck(sessionDTO, temp.getTempItemPriceRebateSystemId(), checkbox.getValue());

                    }
                });
                return checkbox;
            }

            if (Constants.CP_S_DATE.equals(propertyId)) {

                final PopupDateField cpStartDate = new PopupDateField();
                cpStartDate.setRequired(true);
                cpStartDate.setImmediate(true);
                cpStartDate.setDateFormat(Constants.MM_DD_YYYY);
                cpStartDate.setRequiredError("CP Start Date should  be present");
                cpStartDate.setDescription(Constants.DATE);
                cpStartDate.setValue(temp.getCpStartDate());
                attachListeners(cpStartDate, Constants.CP_S_DATE, itemId, temp);
                return cpStartDate;
            }

            if (Constants.PRICE_TYPE.equals(propertyId)) {

                final ComboBox comboBox;

                Field tempField = temp.getDTOValue(Constants.PRICE_TYPE);
                if (tempField != null && tempField instanceof ComboBox) {
                    comboBox = (ComboBox) tempField;
                } else {
                    comboBox = new ComboBox();

                    Container priceTypeContainer = new BeanItemContainer(HelperDTO.class, priceTypeList);
                    comboBox.setContainerDataSource(priceTypeContainer);
                    comboBox.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
                    comboBox.setImmediate(true);
                    comboBox.setNullSelectionAllowed(true);
                    comboBox.setNullSelectionItemId(new HelperDTO(0, ConstantsUtils.SELECT_ONE));
                    comboBox.setValue(temp.getPriceType());

                    List<HelperDTO> contractPriceTypeList = IfpLogic.getCPPriceTypeResults();
                    if(!contractPriceTypeList.isEmpty() && temp.getPriceType().equals(contractPriceTypeList.get(0)) && !"0.00".equals(temp.getPrice())){
                        comboBox.setReadOnly(true);
                    }
                    comboBox.addValueChangeListener(new Property.ValueChangeListener() {
                        @Override
                        public void valueChange(Property.ValueChangeEvent event) {
                            try {
                                temp.setPriceType(comboBox.getValue() != null ? (HelperDTO) comboBox.getValue() : new HelperDTO(0, ConstantsUtils.SELECT_ONE));
                                if (updateFlag) {
                                    temp.setRevisionDate(new Date());
                                }
                                Field priceField = temp.getDTOValue(Constants.PRICE);
                                if (priceField != null) {
                                    if (temp.getPriceType() != null && !"contractprice".equalsIgnoreCase(temp.getPriceType().getDescription().replace(" ", ""))) {
                                        priceField.setValue(StringUtils.EMPTY);
                                        priceField.setReadOnly(true);
                                    } else {
                                        priceField.setReadOnly(false);
                                        if ("contractprice".equalsIgnoreCase(temp.getPriceType().getDescription().replace(" ", "")) && !"".equals(temp.getPrice())) {
                                            comboBox.setReadOnly(true);
                                        }
                                    }
                                }

                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        }
                    });
                    temp.addDTOValue(Constants.PRICE_TYPE, comboBox);

                }

                return comboBox;
            }

            if ("globalitemstatus".equals(propertyId)) {
                final ComboBox globalItemStatus = new ComboBox();
                globalItemStatus.setNullSelectionAllowed(true);
                globalItemStatus.setNullSelectionItemId(Constants.ZEROSTRING);
                globalItemStatus.addItem(Constants.ZEROSTRING);
                globalItemStatus.setItemCaption(Constants.ZEROSTRING, Constants.SELECT_ONE);
                globalItemStatus.setImmediate(true);
                ContractUtils.getInstance().getPriceTypeNative(globalItemStatus, itemStatusList);

                if (StringUtils.isEmpty(temp.getGlobalitemstatus())) {
                    globalItemStatus.select(Constants.ZEROSTRING);
                } else {
                    globalItemStatus.setValue(temp.getGlobalitemstatus());
                    globalItemStatus.setDescription(globalItemStatus.getItemCaption(temp.getGlobalitemstatus()));
                }
                globalItemStatus.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * Will execute ,when the value of
                     * companyFamilyPlanEndDate's value changed.
                     */
                    public void valueChange(final ValueChangeEvent event) {
                        if (updateFlag) {
                            temp.setRevisionDate(new Date());
                        }
                        if (StringUtils.EMPTY.equals(temp.getGlobalitemstatus())) {
                            globalItemStatus.setValue(Constants.ZEROSTRING);
                        } else {
                            globalItemStatus.setValue(temp.getGlobalitemstatus());
                            saveContainer.addItem(itemId);
                        }
                    }
                });
                return globalItemStatus;
            }

            if (Constants.PRICE_TOLERANCE_TYPE.equals(propertyId)) {
                final ComboBox priceToleranceType = new ComboBox();
                priceToleranceType.setImmediate(true);
                priceToleranceType.setNullSelectionAllowed(true);
                priceToleranceType.setNullSelectionItemId(Constants.SELECT_ONE);
                priceToleranceType.addItem(Constants.SELECT_ONE);
                priceToleranceType.setDescription(String.valueOf(temp.getPriceToleranceType()));
                ContractUtils.getInstance().getNativeSelect(priceToleranceType, ptTypeList);
                if (StringUtils.isEmpty(temp.getPriceToleranceType())) {
                    priceToleranceType.setValue(Constants.SELECT_ONE);
                } else {

                    priceToleranceType.setValue(temp.getPriceToleranceType());
                    priceToleranceType.setDescription(priceToleranceType.getItemCaption(temp.getPriceToleranceType()));
                }
                priceToleranceType.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * Will execute ,when the value of
                     * companyFamilyPlanEndDate's value changed.
                     */
                    public void valueChange(final ValueChangeEvent event) {
                        priceToleranceType.setDescription(priceToleranceType.getItemCaption(temp.getPriceToleranceType()));
                        if (updateFlag) {
                            temp.setRevisionDate(new Date());
                        }
                        saveContainer.addItem(itemId);

                    }
                });
                return priceToleranceType;
            }

            if (Constants.PRICE_TOLERANCE_INTERVAL.equals(propertyId)) {
                final ComboBox priceToleranceInterval = new ComboBox();
                priceToleranceInterval.setImmediate(true);
                priceToleranceInterval.setNullSelectionAllowed(true);
                priceToleranceInterval.setNullSelectionItemId(Constants.ZEROSTRING);
                priceToleranceInterval.addItem(Constants.ZEROSTRING);
                priceToleranceInterval.setItemCaption(Constants.ZEROSTRING, Constants.SELECT_ONE);
                ContractUtils.getInstance().getPriceTypeNative(priceToleranceInterval, ptIntervalList);
                if (!temp.getPriceToleranceInterval().equals(Constants.NULL)) {
                    if (StringUtils.isBlank(temp.getPriceToleranceInterval()) || Integer.parseInt(temp.getPriceToleranceInterval()) == 0) {
                        priceToleranceInterval.select(0);
                    } else {

                        priceToleranceInterval.setValue(Integer.parseInt(temp.getPriceToleranceInterval()));
                        priceToleranceInterval.setDescription(priceToleranceInterval.getItemCaption(temp.getPriceToleranceInterval()));

                    }
                }
                priceToleranceInterval.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * Will execute ,when the value of
                     * companyFamilyPlanEndDate's value changed.
                     */
                    public void valueChange(final ValueChangeEvent event) {
                        priceToleranceInterval.setDescription(priceToleranceInterval.getItemCaption(temp.getPriceToleranceInterval()));
                        if (updateFlag) {
                            temp.setRevisionDate(new Date());
                        }

                        saveContainer.addItem(itemId);

                    }
                });
                return priceToleranceInterval;
            }

            if (Constants.PRICE_TOLERANCE_FREQUENCY.equals(propertyId)) {
                final ComboBox priceToleranceFrequency = new ComboBox();
                priceToleranceFrequency.setImmediate(true);
                priceToleranceFrequency.setNullSelectionAllowed(true);
                priceToleranceFrequency.setNullSelectionItemId(Constants.ZEROSTRING);
                priceToleranceFrequency.addItem(Constants.ZEROSTRING);
                priceToleranceFrequency.setItemCaption(Constants.ZEROSTRING, Constants.SELECT_ONE);
                ContractUtils.getInstance().getPriceTypeNative(priceToleranceFrequency, ptfrequencyList);
                if (StringUtils.isEmpty(temp.getPriceToleranceFrequency()) || Integer.parseInt(temp.getPriceToleranceFrequency()) == 0) {
                    priceToleranceFrequency.select(0);
                } else {

                    priceToleranceFrequency.setValue(Integer.parseInt(temp.getPriceToleranceFrequency()));
                    priceToleranceFrequency.setDescription(priceToleranceFrequency.getItemCaption(temp.getPriceToleranceFrequency()));
                }
                priceToleranceFrequency.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * Will execute ,when the value of
                     * companyFamilyPlanEndDate's value changed.
                     */
                    public void valueChange(final ValueChangeEvent event) {
                        priceToleranceFrequency.setDescription(priceToleranceFrequency.getItemCaption(temp.getPriceToleranceFrequency()));
                        if (updateFlag) {
                            temp.setRevisionDate(new Date());
                        }

                        saveContainer.addItem(itemId);

                    }
                });
                return priceToleranceFrequency;
            }

            if (Constants.CP_E_DATE.equals(propertyId)) {

                final PopupDateField cpEndDate = new PopupDateField();
                cpEndDate.setImmediate(true);
                cpEndDate.setDateFormat(Constants.MM_DD_YYYY);
                cpEndDate.setDescription(Constants.DATE);
                cpEndDate.setValue(temp.getCpEndDate());
                attachListeners(cpEndDate, Constants.CP_E_DATE, itemId, temp);
                return cpEndDate;
            }
            if (Constants.PP_START_DATE.equals(propertyId)) {

                final PopupDateField ppStartDate = new PopupDateField();
                ppStartDate.setImmediate(true);
                ppStartDate.setDateFormat(Constants.MM_DD_YYYY);
                ppStartDate.setDescription(Constants.DATE);
                ppStartDate.setValue(temp.getPpStartDate());
                ppStartDate.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * Will execute, when the ppStartDate's value is changed.
                     */
                    public void valueChange(final ValueChangeEvent event) {
                        if (updateFlag) {
                            temp.setRevisionDate(new Date());
                        }
                        saveContainer.addItem(itemId);
                        ppStartDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(ppStartDate.getValue()));
                    }
                });
                return ppStartDate;
            }
            if (Constants.PP_END_DATE.equals(propertyId)) {

                final PopupDateField ppEndDate = new PopupDateField();
                ppEndDate.setImmediate(true);
                ppEndDate.setDateFormat(Constants.MM_DD_YYYY);
                ppEndDate.setDescription(Constants.DATE);
                ppEndDate.setValue(temp.getPpEndDate());
                ppEndDate.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * Will execute,when the ppEndDate's value is changed.
                     */
                    public void valueChange(final ValueChangeEvent event) {
                        if (updateFlag) {
                            temp.setRevisionDate(new Date());
                        }
                        saveContainer.addItem(itemId);
                        ppEndDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(ppEndDate.getValue()));
                    }
                });
                return ppEndDate;
            }
            if (Constants.CONTRACT_PRICE.equalsIgnoreCase(propertyId.toString())) {

                final TextField contractprice = new TextField();
                contractprice.setImmediate(true);
                contractprice.addValidator(new RegexpValidator(REGEX_NUM, REGEX_ERROR));
                contractprice.setValue(temp.getContractprice());
                contractprice.setDescription(contractprice.getValue());
                contractprice.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * Will execute,when the endDate's value is changed.
                     */
                    public void valueChange(final ValueChangeEvent event) {
                        contractprice.setDescription(contractprice.getValue());
                        if (updateFlag) {
                            temp.setRevisionDate(new Date());
                        }
                        temp.setContractprice(contractprice.getValue());
                        saveContainer.addItem(itemId);

                    }
                });
                return contractprice;
            }
            if (Constants.BASE_PRICE.equals(propertyId)) {

                final TextField basePrice = new TextField();
                basePrice.setImmediate(true);
                basePrice.addValidator(new RegexpValidator(REGEX_NUM, REGEX_ERROR));
                basePrice.setValue(temp.getBasePrice());
                basePrice.setDescription(basePrice.getValue());
                basePrice.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * Will execute,when the endDate's value is changed.
                     */
                    public void valueChange(final ValueChangeEvent event) {
                        basePrice.setDescription(basePrice.getValue());
                        if (updateFlag) {
                            temp.setRevisionDate(new Date());
                        }
                        temp.setBasePrice(basePrice.getValue());
                        saveContainer.addItem(itemId);

                    }
                });
                return basePrice;
            }
            if (Constants.PRICE.equals(propertyId)) {
                final TextField price;
                Field tempField = temp.getDTOValue(Constants.PRICE);
                if (tempField != null && tempField instanceof TextField) {
                    price = (TextField) tempField;
                } else {
                    price = new TextField();
                    IfpLogic psLogic = new IfpLogic();
                    price.setImmediate(true);
                    price.addValidator(new RegexpValidator(REGEX_NUM, REGEX_ERROR));
                    price.setValue(temp.getPrice());
                    price.setDescription(price.getValue());
                    final List<HelperDTO> priceTypeList = psLogic.getCPPriceTypeResults();
                    if (temp.getPriceType() == null || "Contract Price".equalsIgnoreCase(temp.getPriceType().getDescription()) || "-Select One-".equalsIgnoreCase(temp.getPriceType().getDescription())) {
                        price.setReadOnly(false);
                    } else {
                        price.setReadOnly(true);
                    }
                    price.addValueChangeListener(new Property.ValueChangeListener() {

                        /**
                         * Will execute,when the endDate's value is changed.
                         */
                        public void valueChange(final ValueChangeEvent event) {
                            price.setDescription(price.getValue());
                            if (updateFlag) {
                                temp.setRevisionDate(new Date());
                            }
                            temp.setPrice(price.getValue());

                            Field priceTypeField = temp.getDTOValue(Constants.PRICE_TYPE);
                            if (priceTypeField != null) {
                                if (event.getProperty().getValue() != null && !"".equals(event.getProperty().getValue()) && !"0".equals(event.getProperty().getValue().toString())) {
                                    priceTypeField.setValue(priceTypeList.get(0));
                                    priceTypeField.setReadOnly(true);
                                } else {
                                    priceTypeField.setReadOnly(false);
                                    priceTypeField.setValue(new HelperDTO(0, com.stpl.app.serviceUtils.Constants.SELECT_ONE));
                                }
                            }
                        }
                    });
                    temp.addDTOValue(Constants.PRICE, price);
                }
                return price;
            }
            if (Constants.PRICE_TOLERANCE.equals(propertyId)) {

                final TextField priceTolerance = new TextField();
                priceTolerance.setImmediate(true);
                priceTolerance.setRequired(true);
                priceTolerance.setRequiredError("Base price should be present");
                priceTolerance.addValidator(new RegexpValidator(REGEX_NUM, REGEX_ERROR));
                priceTolerance.setValue(temp.getPriceTolerance());
                priceTolerance.setDescription(priceTolerance.getValue());
                priceTolerance.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * Will execute,when the endDate's value is changed.
                     */
                    public void valueChange(final ValueChangeEvent event) {
                        priceTolerance.setDescription(priceTolerance.getValue());
                        if (updateFlag) {
                            temp.setRevisionDate(new Date());
                        }
                        temp.setPriceTolerance(priceTolerance.getValue());
                        saveContainer.addItem(itemId);

                    }
                });
                return priceTolerance;
            }
            if ("suggestedPrice".equals(propertyId)) {
                final TextField suggestedPrice = new TextField();
                suggestedPrice.setImmediate(true);
                suggestedPrice.addValidator(new RegexpValidator(REGEX_NUM, REGEX_ERROR));
                suggestedPrice.setValue(temp.getBasePrice());
                suggestedPrice.setDescription(suggestedPrice.getValue());
                suggestedPrice.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * Will execute,when the endDate's value is changed.
                     */
                    public void valueChange(final ValueChangeEvent event) {
                        suggestedPrice.setDescription(suggestedPrice.getValue());
                        if (updateFlag) {
                            temp.setRevisionDate(new Date());
                        }
                        temp.setSuggestedPrice(suggestedPrice.getValue());
                        saveContainer.addItem(itemId);
                    }
                });
                return suggestedPrice;
            }
            updateFlag = true;
            return null;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    /**
     * Gets the commons util.
     *
     * @return the commons util
     */
    public CommonUtils getCommonsUtil() {
        return commonsUtil;
    }

    public void attachListeners(final AbstractField field, final String component, final Object itemId, final TempPricingDTO temp){
        field.setImmediate(true);
        field.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                try {
                    if (Constants.START_DATE.equals(component)) {
                        tempDateList = tempDate.get(temp.getItemId());
                        if (((PopupDateField) field).getValue().before((Date) dates[0])) {
                            AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be before " + format.format(dates[0]));
                            detachListeners(field);
                            ((PopupDateField) field).setValue(tempDateList.get(0));
                            attachListeners(field, component, itemId, temp);
                            return;
                        } else if ((Date) dates[1] != null && ((PopupDateField) field).getValue().after((Date) dates[1])) {
                            AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be after " + format.format(dates[1]));
                            detachListeners(field);
                            ((PopupDateField) field).setValue(tempDateList.get(0));
                            attachListeners(field, component, itemId, temp);
                            return;
                        } else {
                            if (updateFlag) {
                                temp.setRevisionDate(new Date());
                            }
                            temp.setStartDate(((PopupDateField) field).getValue());
                            saveContainer.addItem(itemId);
                            ((PopupDateField) field).setDescription(CommonUIUtils.convert2DigitTo4DigitYear(((PopupDateField) field).getValue()));
                        }
                    } else if (Constants.END_DATE.equals(component)) {
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
                            if (updateFlag) {
                                temp.setRevisionDate(new Date());
                            }
                            temp.setEndDate(((PopupDateField) field).getValue());
                            saveContainer.addItem(itemId);
                            ((PopupDateField) field).setDescription(CommonUIUtils.convert2DigitTo4DigitYear(((PopupDateField) field).getValue()));
                        }
                    } else if (Constants.CP_S_DATE.equals(component)) {
                        tempDateList = tempDate.get(temp.getItemId());
                        if (((PopupDateField) field).getValue() != null && (((PopupDateField) field).getValue().before((Date) psDates[0]))) {
                            AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be before " + format.format(psDates[0]));
                            detachListeners(field);
                            ((PopupDateField) field).setValue(tempDateList.get(NumericConstants.TWO));
                            attachListeners(field, component, itemId, temp);
                            return;
                        } else if (((PopupDateField) field).getValue() != null && (Date) psDates[1] != null && ((PopupDateField) field).getValue().after((Date) psDates[1])) {
                            AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be after " + format.format(psDates[1]));
                            detachListeners(field);
                            ((PopupDateField) field).setValue(tempDateList.get(NumericConstants.TWO));
                            attachListeners(field, component, itemId, temp);
                            return;
                        } else {
                            if (updateFlag) {
                                temp.setRevisionDate(new Date());
                            }
                            temp.setCpStartDate(((PopupDateField) field).getValue());
                            saveContainer.addItem(itemId);
                            ((PopupDateField) field).setDescription(CommonUIUtils.convert2DigitTo4DigitYear(((PopupDateField) field).getValue()));
                        }
                    } else if (Constants.CP_E_DATE.equals(component)) {
                        tempDateList = tempDate.get(temp.getItemId());
                        if ((Date) psDates[1] != null && ((PopupDateField) field).getValue().after((Date) psDates[1])) {
                            detachListeners(field);
                            ((PopupDateField) field).setValue(tempDateList.get(NumericConstants.THREE) != null ? tempDateList.get(NumericConstants.THREE) : null);
                            AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "End date cannot be after " + format.format(psDates[1]));
                            attachListeners(field, component, itemId, temp);
                            return;
                        } else if (((PopupDateField) field).getValue().before((Date) psDates[0])) {
                            detachListeners(field);
                            ((PopupDateField) field).setValue(tempDateList.get(NumericConstants.THREE) != null ? tempDateList.get(NumericConstants.THREE) : null);
                            AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "End date cannot be before " + format.format(psDates[0]));
                            attachListeners(field, component, itemId, temp);
                            return;
                        } else {
                            if (updateFlag) {
                                temp.setRevisionDate(new Date());
                            }
                            temp.setCpEndDate(((PopupDateField) field).getValue());
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
