/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.global.dto;

import com.stpl.app.contract.abstractsearch.util.ConstantUtil;
import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.dashboard.dto.NepFormulaLookUpDTO;
import com.stpl.app.contract.dashboard.dto.TempPricingDTO;
import com.stpl.app.contract.dashboard.ui.lazyload.PriceTypeCriteria;
import com.stpl.app.contract.dashboard.ui.lazyload.PriceTypeLazyContainer;
import com.stpl.app.contract.dashboard.ui.lookup.NetPriceTypeFormulaLookup;
import com.stpl.app.contract.dashboard.ui.lookup.NetSalesFormulaLookup;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.CommonUIUtils;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.serviceUtils.Constants;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.serviceUtils.ErrorCodes;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;

/**
 *
 * @author sathyaseelan.v
 */
public class PriceProtectionFieldFactory extends DefaultFieldFactory{
    /**
     * Default serial version ID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(PriceProtectionFieldFactory.class.getName());
    HelperDTO psnullDTO = new HelperDTO();
    public HelperDTO getPsnullDTO() {
        return psnullDTO;
    }

    public void setPsnullDTO(HelperDTO psnullDTO) {
        this.psnullDTO = psnullDTO;
    }
    private final BeanItemContainer<TempPricingDTO> itemDetailsResultBean;
    ExtPagedTable table;
    CommonUtil commonUtil=CommonUtil.getInstance();
    NetPriceTypeFormulaLookup formulaLookup =null;
    String value="";
    Object[] dates;
    Map<String, List> tempDate;
    List<Date> tempDateList = new ArrayList<>();
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
    SessionDTO sessionDTO;
     final CustomFieldGroup contractMasterBinder;
    public PriceProtectionFieldFactory(ExtPagedTable table, final BeanItemContainer<TempPricingDTO> itemDetailsResultBean, final Object[] dates, final Map<String, List> tempDate,SessionDTO sessionDTO,CustomFieldGroup contractMasterBinder) {
        this.table = table;
        this.itemDetailsResultBean = itemDetailsResultBean;
        this.dates = dates;
        this.tempDate = tempDate;
        this.sessionDTO = sessionDTO;
        this.contractMasterBinder=contractMasterBinder;
    }
    /**
     * This method is used to set the components to the column in the Table
     * container.
     *
     * @param container the container
     * @param itemId the item id
     * @param propertyId the property id
     * @param uiContext the ui context
     * @return the field<?>
     */
    public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {
        try {
            final TempPricingDTO psDTO = (TempPricingDTO) itemId;
            tempDateList.add(psDTO.getPriceProtectionStartDate());
            tempDateList.add(psDTO.getPriceProtectionEndDate());
            tempDate.put(psDTO.getItemId(), tempDateList);
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            if ("checkbox".equals(propertyId)) {
                final CheckBox checkbox = new CheckBox();
                checkbox.setReadOnly(false);
                checkbox.setValue(psDTO.getCheckbox());
                checkbox.setId("contractdashboardcheckbox");
                checkbox.setImmediate(true);
                checkbox.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (psDTO.getCheckbox() != null) {

                            IfpLogic.updateTempCheck(sessionDTO,psDTO.getTempItemPriceRebateSystemId(),checkbox.getValue());
                            boolean isCheckedAll = IfpLogic.checkTempCheckedAll(sessionDTO);
                            table.setColumnCheckBox(com.stpl.app.contract.util.Constants.CHECK_BOX, true, isCheckedAll);
                        }
                    }
                });
                return checkbox;
            } else if ("priceTolerance".equals(propertyId)) {
                final TextField priceTolerance = new TextField();
                priceTolerance.setImmediate(true);
                priceTolerance.addValidator(new RegexpValidator("([|0-9]*.[0-9]{1,2})", "Please Enter Only Numbers with two decimal places"));
                priceTolerance.addStyleName("align-right");
                priceTolerance.setValidationVisible(true);
                priceTolerance.setValue(psDTO.getPriceTolerance());
                priceTolerance.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            if(String.valueOf(priceTolerance.getValue()).matches(ConstantUtil.SPECIAL_CHAR)||String.valueOf(priceTolerance.getValue()).isEmpty()
                                    ||String.valueOf(priceTolerance.getValue()).equals("null")){
                                if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                                }
                                contractMasterBinder.getErrorDisplay().clearError();
                            }else{
                                contractMasterBinder.getErrorDisplay().setError("Please Enter Numbers with two decimal places in Price Tolerace");
                            }
                            
                        }
                    }
                });
                priceTolerance.addTextChangeListener(new TextChangeListener() {
                    private static final long serialVersionUID = 8721337946386845992L;

                    public void textChange(FieldEvents.TextChangeEvent event) {
                        updateChangedInfo(userId, itemId);
                    }
                });
                return priceTolerance;
            } else if ("ppPriceToleranceType".equals(propertyId)) {
                final ComboBox tolerance = new ComboBox();
                commonUtil.loadComboBox(tolerance, UIUtils.PRICE_TOLERANCE_TYPE, false);
                tolerance.select(psDTO.getPpPriceToleranceType());
                tolerance.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                            updateChangedInfoDdlb(itemId);
                        }
                    }
                });
                return tolerance;
            } else if ("ppPriceToleranceInterval".equals(propertyId)) {
                final ComboBox toleranceInterval = new ComboBox();
                commonUtil.loadComboBox(toleranceInterval, UIUtils.PRICE_TOLERANCE_INTERVAL, false);
                toleranceInterval.select(psDTO.getPpPriceToleranceInterval());
                toleranceInterval.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            psDTO.setPpPriceToleranceInterval((HelperDTO)toleranceInterval.getValue());
                            if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                            updateChangedInfoDdlb(itemId);
                        }
                    }
                });
                return toleranceInterval;
            } else if ("ppPriceToleranceFrequency".equals(propertyId)) {
                final ComboBox frequency = new ComboBox();
                commonUtil.loadComboBox(frequency, UIUtils.PRICE_TOLERANCE_FRERQUENCY, false);
                frequency.setValue(psDTO.getPpPriceToleranceFrequency());
                frequency.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                            updateChangedInfoDdlb(itemId);
                        }
                    }
                });
                return frequency;
            } else if ("price".equals(propertyId)) {
                final TextField price = new TextField();
                price.setImmediate(true);
                price.setValue(psDTO.getPrice());
                price.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (!"0".equals(event.getProperty().getValue()) && !"".equals(event.getProperty().getValue())) {
                            if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                            itemDetailsResultBean.getItem(itemId).getItemProperty(ConstantUtil.PRICE_TYPE).setValue(new HelperDTO(0, Constants.SELECT_ONE));
                            table.getItem(itemId).getItemProperty(ConstantUtil.PRICE_TYPE).setReadOnly(true);
                            itemDetailsResultBean.getItem(itemId).getItemProperty(ConstantUtil.PRICE_TYPE).setReadOnly(true);
                        }else{
                            table.getItem(itemId).getItemProperty(ConstantUtil.PRICE_TYPE).setReadOnly(false);
                            itemDetailsResultBean.getItem(itemId).getItemProperty(ConstantUtil.PRICE_TYPE).setReadOnly(false);
                        }
                    }
                   
                });
                price.addTextChangeListener(new TextChangeListener() {
                    private static final long serialVersionUID = 8721337946386845992L;

                    public void textChange(FieldEvents.TextChangeEvent event) {
                        updateChangedInfo(userId, itemId);
                    }
                });

                return price;
            } else if ("contractPrice".equals(propertyId)) {
                final TextField contractPrice = new TextField();
                contractPrice.setRequired(true);
                contractPrice.setRequiredError("Contract price should be present");
                contractPrice.setImmediate(true);
                contractPrice.setValue(psDTO.getContractPrice());
                contractPrice.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null && !itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                        }
                    }
                });
                contractPrice.addTextChangeListener(new TextChangeListener() {
                    private static final long serialVersionUID = 8721337946386845992L;

                    public void textChange(FieldEvents.TextChangeEvent event) {
                        updateChangedInfo(userId, itemId);
                    }
                });
                return contractPrice;
            } else if ("basePrice".equals(propertyId)) {
                final TextField basePrice = new TextField();
                basePrice.setImmediate(true);
                basePrice.setValue(psDTO.getBasePrice());
                basePrice.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                             if (String.valueOf(basePrice.getValue()).matches(ConstantUtil.SPECIAL_CHAR)||String.valueOf(basePrice.getValue()).isEmpty()
                                    ||String.valueOf(basePrice.getValue()).equals("null")) {
                                updateChangedInfo(userId, itemId);
                                if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                                contractMasterBinder.getErrorDisplay().clearError();
                            } else {
                                contractMasterBinder.getErrorDisplay().setError("Please Enter Numbers with two decimal places in BasePrice");
                            }
                
                        }
                    }
                });
                basePrice.addTextChangeListener(new TextChangeListener() {
                    private static final long serialVersionUID = 8721337946386845992L;

                    public void textChange(FieldEvents.TextChangeEvent event) {
                        updateChangedInfo(userId, itemId);
                    }
                });
                return basePrice;
            } else if ("nep".equals(propertyId)) {
                final TextField nep = new TextField();
                nep.setImmediate(true);
                nep.setValue(psDTO.getNep());
                nep.addStyleName("align-right");
                nep.setReadOnly(false);
                nep.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null && !itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                        }
                    }
                });
                nep.addTextChangeListener(new TextChangeListener() {
                    private static final long serialVersionUID = 8721337946386845992L;

                    public void textChange(FieldEvents.TextChangeEvent event) {
                        updateChangedInfo(userId, itemId);
                    }
                });
                return nep;
            } else if ("nepFormula".equals(propertyId)) {
                final CustomTextField nepFormula = new CustomTextField();
                nepFormula.setImmediate(true);
                nepFormula.addStyleName(ConstantUtil.SEARCHICON);
                nepFormula.setValue(psDTO.getNepFormula());
                nepFormula.addClickListener(new CustomTextField.ClickListener() {
                    /**
                     * Method used for formulaNo
                     */
                    public void click(final CustomTextField.ClickEvent event) {

                        try {                            
                            
                            final NetSalesFormulaLookup nepFormulaLookup = new NetSalesFormulaLookup(true, nepFormula);
                            UI.getCurrent().addWindow(nepFormulaLookup);
                            nepFormulaLookup.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {                                       
                                    NepFormulaLookUpDTO dto = nepFormulaLookup.getNepFormulaDTO();
                                    if (nepFormulaLookup.isSelected()) {
                                        ((TempPricingDTO) itemId).setNepFormulaId(dto.getNepFormulaSystemID());
                                        if (!itemDetailsResultBean.containsId(itemId)) {
                                            itemDetailsResultBean.addItem(itemId);
                                        }
                                        nepFormula.setValue(dto.getNepFormulaName());
                                    }else{
                                    nepFormula.setValue(StringUtils.EMPTY);
                                    }
                                }
                            });
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                });
                return nepFormula;
            } else if ("netBasePriceFormula".equals(propertyId)) {
                final CustomTextField nepFormula = new CustomTextField();
                nepFormula.setImmediate(true);
                nepFormula.addStyleName(ConstantUtil.SEARCHICON);
                nepFormula.setValue(psDTO.getNetBasePriceFormula());
                nepFormula.addClickListener(new CustomTextField.ClickListener() {
                    /**
                     * Method used for formulaNo
                     */
                    public void click(final CustomTextField.ClickEvent event) {

                        try {                            
                            
                            final NetSalesFormulaLookup nepFormulaLookup = new NetSalesFormulaLookup(true, nepFormula);
                            UI.getCurrent().addWindow(nepFormulaLookup);
                            nepFormulaLookup.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    if (nepFormulaLookup.isSelected()) {
                                        NepFormulaLookUpDTO dto = nepFormulaLookup.getNepFormulaDTO();
                                        ((TempPricingDTO) itemId).setNetBasePriceFormulaID(dto.getNepFormulaSystemID());
                                        if (!itemDetailsResultBean.containsId(itemId)) {
                                            itemDetailsResultBean.addItem(itemId);
                                        }
                                        nepFormula.setValue(dto.getNepFormulaName());
                                    } else {
                                        nepFormula.setValue(StringUtils.EMPTY);
                                    }
                                }
                            });
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                });
                return nepFormula;
            }else if ("netSubsequentPriceFormula".equals(propertyId)) {
                final CustomTextField nepFormula = new CustomTextField();
                nepFormula.setImmediate(true);
                nepFormula.addStyleName(ConstantUtil.SEARCHICON);
                nepFormula.setValue(psDTO.getNetResetPriceFormula());
                nepFormula.addClickListener(new CustomTextField.ClickListener() {
                    /**
                     * Method used for formulaNo
                     */
                    public void click(final CustomTextField.ClickEvent event) {

                        try {                            
                            
                            final NetSalesFormulaLookup nepFormulaLookup = new NetSalesFormulaLookup(true, nepFormula);
                            UI.getCurrent().addWindow(nepFormulaLookup);
                            nepFormulaLookup.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    if (nepFormulaLookup.isSelected()) {
                                        NepFormulaLookUpDTO dto = nepFormulaLookup.getNepFormulaDTO();
                                        ((TempPricingDTO) itemId).setNetSubsequentPriceFormulaID(dto.getNepFormulaSystemID());
                                        if (!itemDetailsResultBean.containsId(itemId)) {
                                            itemDetailsResultBean.addItem(itemId);
                                        }
                                        nepFormula.setValue(dto.getNepFormulaName());
                                    } else {
                                        nepFormula.setValue(StringUtils.EMPTY);
                                    }
                                }
                            });
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                });
                return nepFormula;
            }else if ("netResetPriceFormula".equals(propertyId)) {
                final CustomTextField nepFormula = new CustomTextField();
                nepFormula.setImmediate(true);
                nepFormula.addStyleName(ConstantUtil.SEARCHICON);
                nepFormula.setValue(psDTO.getNetResetPriceFormula());
                nepFormula.addClickListener(new CustomTextField.ClickListener() {
                    /**
                     * Method used for formulaNo
                     */
                    public void click(final CustomTextField.ClickEvent event) {
                        try {
                            final NetSalesFormulaLookup nepFormulaLookup = new NetSalesFormulaLookup(true, nepFormula);
                            UI.getCurrent().addWindow(nepFormulaLookup);
                            nepFormulaLookup.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    if (nepFormulaLookup.isSelected()) {
                                        NepFormulaLookUpDTO dto = nepFormulaLookup.getNepFormulaDTO();
                                        ((TempPricingDTO) itemId).setNetResetPriceFormulaID(dto.getNepFormulaSystemID());
                                        if (!itemDetailsResultBean.containsId(itemId)) {
                                            itemDetailsResultBean.addItem(itemId);
                                        }
                                        nepFormula.setValue(dto.getNepFormulaName());
                                    } else {
                                        nepFormula.setValue(StringUtils.EMPTY);
                                    }
                                }
                            });
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                });
                return nepFormula;
            }else if ("maxIncrementalChange".equals(propertyId)) {
                final TextField maxIncChange = new TextField();
                maxIncChange.setImmediate(true);
                maxIncChange.setValue(psDTO.getMaxIncrementalChange());
                maxIncChange.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            if (String.valueOf(maxIncChange.getValue()).matches(ConstantUtil.SPECIAL_CHAR)||String.valueOf(maxIncChange.getValue()).isEmpty()
                                    ||String.valueOf(maxIncChange.getValue()).equals("null")) {
                                updateChangedInfo(userId, itemId);
                                if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                                 contractMasterBinder.getErrorDisplay().clearError();
                            } else {
                                contractMasterBinder.getErrorDisplay().setError("Please Enter Numbers with two decimal places in Max Incremental Change");
                            }
                        }
                    }
                });
                maxIncChange.addTextChangeListener(new TextChangeListener() {
                    private static final long serialVersionUID = 8721337946386845992L;

                    public void textChange(FieldEvents.TextChangeEvent event) {
                        updateChangedInfo(userId, itemId);
                    }
                });
                return maxIncChange;
            } else if ("suggestedPrice".equals(propertyId)) {
                final TextField suggestedPrice = new TextField();
                suggestedPrice.setImmediate(true);
                suggestedPrice.setValue(psDTO.getSuggestedPrice());
                suggestedPrice.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            updateChangedInfo(userId, itemId);
                            if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                        }
                    }
                });
                suggestedPrice.addTextChangeListener(new TextChangeListener() {
                    private static final long serialVersionUID = 8721337946386845992L;

                    public void textChange(FieldEvents.TextChangeEvent event) {
                        updateChangedInfo(userId, itemId);
                    }
                });
                return suggestedPrice;
            }else if ("priceProtectionStartDate".equals(propertyId)) {
                final PopupDateField startDate = new PopupDateField();
                startDate.setDescription(ConstantsUtils.DATE_DES);
                startDate.setImmediate(true);
                startDate.setDateFormat(Constants.DATE_FORMAT);
                startDate.setValue(psDTO.getPriceProtectionStartDate());
                attachListeners(startDate, propertyId.toString(), itemId, psDTO, userId);
                return startDate;
            } else if ("priceProtectionEndDate".equals(propertyId)) {
                final PopupDateField endDate = new PopupDateField();
                endDate.setDescription(ConstantsUtils.DATE_DES);
                endDate.setDateFormat(Constants.DATE_FORMAT);
                endDate.setImmediate(true);
                endDate.setValue(psDTO.getPriceProtectionEndDate());
                attachListeners(endDate, propertyId.toString(), itemId, psDTO, userId);
                return endDate;
            } else if ("contractPriceStartDate".equals(propertyId)) {
                final PopupDateField startDate = new PopupDateField();
                startDate.setDescription(ConstantsUtils.DATE_DES);
                startDate.setDateFormat(Constants.DATE_FORMAT);
                startDate.setImmediate(true);

                startDate.setRequired(true);
                startDate.setValue(psDTO.getContractPriceStartDate());
                startDate.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            startDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(startDate.getValue()));
                            updateChangedInfo(userId, itemId);
                            if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                        }
                    }
                });
                return startDate;
            } else if ("contractPriceEndDate".equals(propertyId)) {
                final PopupDateField endDate = new PopupDateField();
                endDate.setDescription(ConstantsUtils.DATE_DES);
                endDate.setDateFormat(Constants.DATE_FORMAT);
                endDate.setImmediate(true);
                endDate.setValue(psDTO.getContractPriceEndDate());
                endDate.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            endDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(endDate.getValue()));
                            updateChangedInfo(userId, itemId);
                            if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                        }
                    }
                });
                return endDate;
            } else if ( "priceProtectionPriceType".equals(propertyId)  ) {
                psnullDTO.setId(0);
                psnullDTO.setDescription(ConstantsUtils.SELECT_ONE);
                final ComboBox priceProtectionPriceType = new ComboBox();
                priceProtectionPriceType.setPageLength(NumericConstants.SEVEN);
                priceProtectionPriceType.setImmediate(true);
                priceProtectionPriceType.setNullSelectionAllowed(true);
                priceProtectionPriceType.setInputPrompt(ConstantsUtils.SELECT_ONE);
                priceProtectionPriceType.setNullSelectionItemId(psnullDTO);
                priceProtectionPriceType.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
                priceProtectionPriceType.markAsDirty();
                final LazyContainer priceTypeContainer = new LazyContainer(HelperDTO.class, new PriceTypeLazyContainer(psDTO.getPriceProtectionPriceType()), new PriceTypeCriteria());
                priceTypeContainer.setMinFilterLength(0);
                priceProtectionPriceType.setContainerDataSource(priceTypeContainer);
                priceProtectionPriceType.setValue(psDTO.getPriceType());

                priceProtectionPriceType.addValueChangeListener(new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                                if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                            updateChangedInfoDdlb(itemId);
                        }
                    }
                });
                return priceProtectionPriceType;
            }else if ("priceProtectionStatus".equals(propertyId)) {
                final ComboBox priceProtectionStatus = new ComboBox();
                commonUtil.loadComboBox(priceProtectionStatus, UIUtils.STATUS, false);
                priceProtectionStatus.setValue(psDTO.getPriceProtectionStatus());
                priceProtectionStatus.addValueChangeListener(new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                            updateChangedInfoDdlb(itemId);
                        }
                    }
                });
                return priceProtectionStatus;
            } else if ("itemStatus".equals(propertyId)) {
                final ComboBox itemStatus = new ComboBox();
                commonUtil.loadComboBox(itemStatus, UIUtils.STATUS, false);
                itemStatus.setValue(psDTO.getItemStatus());
                itemStatus.addValueChangeListener(new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                            updateChangedInfoDdlb(itemId);
                        }
                    }
                });
                return itemStatus;
            } else if ("pricePlanID".equals(propertyId)) {
                final CustomTextField pricePlanID = new CustomTextField();
                pricePlanID.setImmediate(true);
                pricePlanID.addStyleName(ConstantUtil.SEARCHICON);
                pricePlanID.setEnabled(false);
                pricePlanID.setValue(psDTO.getMaxIncrementalChange());
                pricePlanID.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            updateChangedInfo(userId, itemId);
                            if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                        }
                    }
                });
                pricePlanID.addTextChangeListener(new TextChangeListener() {
                    private static final long serialVersionUID = 8721337946386845992L;

                    public void textChange(FieldEvents.TextChangeEvent event) {
                        updateChangedInfo(userId, itemId);
                    }
                });
                return pricePlanID;
            } else if ("resetType".equals(propertyId)) {
                final ComboBox resetType = new ComboBox();
                commonUtil.loadComboBox(resetType, UIUtils.RESET_TYPE, false);
                resetType.setValue(psDTO.getResetType());
                resetType.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                            updateChangedInfoDdlb(itemId);
                        }
                    }
                });
                return resetType;
            } else if ("resetInterval".equals(propertyId)) {
                final ComboBox resetInterval = new ComboBox();
                commonUtil.loadComboBox(resetInterval, UIUtils.PRICE_TOLERANCE_INTERVAL, false);
                resetInterval.setValue(psDTO.getResetInterval());
                resetInterval.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                            updateChangedInfoDdlb(itemId);
                        }
                    }
                });
                return resetInterval;
            } else if ("resetFrequency".equals(propertyId)) {
                final ComboBox resetFrequency = new ComboBox();
                commonUtil.loadComboBox(resetFrequency, UIUtils.PRICE_TOLERANCE_FRERQUENCY, false);
                resetFrequency.setValue(psDTO.getResetFrequency());
                resetFrequency.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                            updateChangedInfoDdlb(itemId);
                        }
                    }
                });
                return resetFrequency;
            }  else if ("basePriceItemPriceType".equals(propertyId)) {
                psnullDTO.setId(0);
                psnullDTO.setDescription(ConstantsUtils.SHOW_ALL);
                final ComboBox basePriceItemPriceType = new ComboBox();
                basePriceItemPriceType.setPageLength(NumericConstants.SEVEN);
                basePriceItemPriceType.setImmediate(true);
                basePriceItemPriceType.setNullSelectionAllowed(true);
                basePriceItemPriceType.setInputPrompt(ConstantsUtils.SHOW_ALL);
                basePriceItemPriceType.setNullSelectionItemId(psnullDTO);
                basePriceItemPriceType.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
                basePriceItemPriceType.markAsDirty();
                final LazyContainer priceTypeContainer = new LazyContainer(HelperDTO.class, new PriceTypeLazyContainer(psDTO.getPriceProtectionPriceType()), new PriceTypeCriteria());
                priceTypeContainer.setMinFilterLength(0);
                basePriceItemPriceType.setContainerDataSource(priceTypeContainer);
                basePriceItemPriceType.setValue(psDTO.getBasePriceItemPriceType().getDescription());                
                basePriceItemPriceType.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                            updateChangedInfoDdlb(itemId);
                        }
                    }
                });
                return basePriceItemPriceType;
            }else if ("resetPriceType".equals(propertyId)) {
                psnullDTO.setId(0);
                psnullDTO.setDescription(ConstantsUtils.SHOW_ALL);
                final ComboBox basePriceItemPriceType = new ComboBox();
                basePriceItemPriceType.setPageLength(NumericConstants.SEVEN);
                basePriceItemPriceType.setImmediate(true);
                basePriceItemPriceType.setNullSelectionAllowed(true);
                basePriceItemPriceType.setInputPrompt(ConstantsUtils.SHOW_ALL);
                basePriceItemPriceType.setNullSelectionItemId(psnullDTO);
                basePriceItemPriceType.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
                basePriceItemPriceType.markAsDirty();
                final LazyContainer priceTypeContainer = new LazyContainer(HelperDTO.class, new PriceTypeLazyContainer(psDTO.getResetPriceType()), new PriceTypeCriteria());
                priceTypeContainer.setMinFilterLength(0);
                basePriceItemPriceType.setContainerDataSource(priceTypeContainer);
                basePriceItemPriceType.setValue(psDTO.getResetPriceType());
                basePriceItemPriceType.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            ((TempPricingDTO) itemId).setResetPriceType((HelperDTO) basePriceItemPriceType.getValue());
                            if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                            updateChangedInfoDdlb(itemId);
                        }
                    }
                });
                return basePriceItemPriceType;
            }else if ("subsequentPeriodPriceType".equals(propertyId)) {
                psnullDTO.setId(0);
                psnullDTO.setDescription(ConstantsUtils.SHOW_ALL);
                final ComboBox basePriceItemPriceType = new ComboBox();
                basePriceItemPriceType.setPageLength(NumericConstants.SEVEN);
                basePriceItemPriceType.setImmediate(true);
                basePriceItemPriceType.setNullSelectionAllowed(true);
                basePriceItemPriceType.setInputPrompt(ConstantsUtils.SHOW_ALL);
                basePriceItemPriceType.setNullSelectionItemId(psnullDTO);
                basePriceItemPriceType.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
                basePriceItemPriceType.markAsDirty();
                final LazyContainer priceTypeContainer = new LazyContainer(HelperDTO.class, new PriceTypeLazyContainer(psDTO.getSubsequentPeriodPriceType()), new PriceTypeCriteria());
                priceTypeContainer.setMinFilterLength(0);
                basePriceItemPriceType.setContainerDataSource(priceTypeContainer);
                basePriceItemPriceType.setValue(psDTO.getSubsequentPeriodPriceType());                
                basePriceItemPriceType.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                            updateChangedInfoDdlb(itemId);
                        }
                    }
                });
                return basePriceItemPriceType;
            }else if ("basePriceType".equals(propertyId)) {
                final ComboBox comboBox = new ComboBox();
                commonUtil.loadComboBox(comboBox, UIUtils.BASE_PRICE_TYPE, false);
                comboBox.setValue(psDTO.getBasePriceType());
                comboBox.setImmediate(true);
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(Constants.SELECT_ONE);
                
                comboBox.addFocusListener(new FieldEvents.FocusListener() {
                    @Override
                    public void focus(FieldEvents.FocusEvent event) {
                        HelperDTO focusVal = (HelperDTO)(comboBox.getValue());
                        psDTO.setSelectedBasePrice(focusVal);
                        comboBox.addValueChangeListener(new Property.ValueChangeListener() {
                            @Override
                            public void valueChange(Property.ValueChangeEvent event) {
                                HelperDTO blurVal = (HelperDTO)(comboBox.getValue());
                                if (!psDTO.getSelectedBasePrice().equals(blurVal)) {
                                    psDTO.setSelectedBasePrice(blurVal == null ? new HelperDTO(0, ConstantsUtils.SELECT_ONE): blurVal);
                                    if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                                    updateChangedInfoDdlb(itemId);
                                    table.setRefresh(true);
                                }
                                comboBox.removeValueChangeListener(this);
                            }
                        });
                    }
                });

                comboBox.select(Constants.SELECT_ONE);
                return comboBox;
            } else if("netBasePrice".equals(propertyId)){
                final ComboBox comboBox = new ComboBox();                
                commonUtil.loadComboBox(comboBox, UIUtils.LOCKED_STATUS, false);
                comboBox.setValue(psDTO.getNetBasePrice()); 
                comboBox.addValueChangeListener(new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                        updateChangedInfoDdlb(itemId);
                    }
                });
                return comboBox;
                } else if("netSubsequentPeriodPrice".equals(propertyId)){
                final ComboBox comboBox = new ComboBox();                
                commonUtil.loadComboBox(comboBox, UIUtils.LOCKED_STATUS, false);
                comboBox.setValue(psDTO.getNetSubsequentPeriodPrice());
                comboBox.addValueChangeListener(new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                        updateChangedInfoDdlb(itemId);
                    }
                });
                return comboBox;
                }else if("netResetPriceType".equals(propertyId)){
                final ComboBox comboBox = new ComboBox();                
                commonUtil.loadComboBox(comboBox, UIUtils.LOCKED_STATUS, false);
                comboBox.setValue(psDTO.getNetResetPriceType()); 
                comboBox.addValueChangeListener(new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                        updateChangedInfoDdlb(itemId);
                    }
                });
                return comboBox;
                }else if("resetEligible".equals(propertyId)){
                final ComboBox comboBox = new ComboBox();                
                commonUtil.loadComboBox(comboBox, UIUtils.LOCKED_STATUS, false);
                comboBox.setValue(psDTO.getResetEligible()); 
                comboBox.addValueChangeListener(new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                        updateChangedInfoDdlb(itemId);
                    }
                });
                return comboBox;
                }else if("netPriceType".equals(propertyId)){
                final ComboBox comboBox = new ComboBox();                
                commonUtil.loadComboBox(comboBox, UIUtils.LOCKED_STATUS, false);
                comboBox.setValue(psDTO.getNetPriceType());
                comboBox.addValueChangeListener(new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                        updateChangedInfoDdlb(itemId);
                    }
                });
                return comboBox;
                } else if ("basePriceValue".equals(propertyId)) {
                if (Constants.MANUAL.equals(psDTO.getBasePriceType().getDescription())) {
                    psDTO.setBasePriceValue(StringUtils.EMPTY);
                    final TextField basePrice = new TextField();
                    basePrice.setImmediate(true);
                    basePrice.setEnabled(true);
                    basePrice.setValue("basePriceValue".equals(propertyId) ? String.valueOf(psDTO.getBasePriceValue()) : StringUtils.EMPTY);
                    basePrice.addValueChangeListener(new Property.ValueChangeListener() {
                        @Override
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (String.valueOf(basePrice.getValue()).matches(ConstantUtil.SPECIAL_CHAR)||String.valueOf(basePrice.getValue()).isEmpty()
                                    ||String.valueOf(basePrice.getValue()).equals("null")) {
                                psDTO.setBasePriceEntry(ContractUtils.checkNullValues(basePrice.getValue()) ? "0" : basePrice.getValue());
                                if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                                contractMasterBinder.getErrorDisplay().clearError();
                            } else {
                                contractMasterBinder.getErrorDisplay().setError("Please Enter Numbers with two decimal places in BasePrice");
                            }
                        }
                    });
                    psDTO.setBasePriceValue(psDTO.getBasePriceEntry());
                    return basePrice;
                } else if (Constants.DATE.equals(psDTO.getBasePriceType().getDescription())) {
                     psDTO.setBasePriceValue(StringUtils.EMPTY);
                    final PopupDateField date = new PopupDateField();
                    date.setDescription(ConstantsUtils.DATE_DES);
                    date.setDateFormat(Constants.DATE_FORMAT);
                    date.setImmediate(true);
                    date.setValue(psDTO.getBasePriceDate());
                    date.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (event.getProperty().getValue() != null) {
                                ((TempPricingDTO) itemId).setBasePriceDate(date.getValue());
                                date.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(date.getValue()));
                                updateChangedInfo(userId, itemId);
                                if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                            }
                        }
                    });
                    psDTO.setBasePriceValue(psDTO.getBasePriceDate());
                    return date;
                } else if (ContractUtils.FIELD_PRICE_TYPE.equals(psDTO.getBasePriceType().getDescription())) {
                    psnullDTO.setId(0);
                    psnullDTO.setDescription(ConstantsUtils.SELECT_ONE);
                     psDTO.setBasePriceValue(StringUtils.EMPTY);
                    final ComboBox priceType = new ComboBox(); 
                    priceType.setPageLength(NumericConstants.SEVEN);
                    priceType.setImmediate(true);
                    priceType.setNullSelectionAllowed(true);
                    priceType.setInputPrompt(ConstantsUtils.SELECT_ONE);
                    priceType.setNullSelectionItemId(psnullDTO);
                    priceType.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
                    priceType.markAsDirty();
                    final LazyContainer priceTypeContainer = new LazyContainer(HelperDTO.class, new PriceTypeLazyContainer(psDTO.getBasePriceItemPriceType()), new PriceTypeCriteria());
                    priceTypeContainer.setMinFilterLength(0);
                    priceType.setContainerDataSource(priceTypeContainer);
                    priceType.setValue(psDTO.getBasePriceItemPriceType());
                    priceType.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (event.getProperty().getValue() != null) {
                                ((TempPricingDTO)itemId).setBasePriceItemPriceType((HelperDTO) priceType.getValue());
                                if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                                updateChangedInfoDdlb(itemId);
                            }
                        }
                    });
                    psDTO.setBasePriceValue(psDTO.getBasePriceItemPriceType());
                    return priceType;
                }else{
                    TextField textField=new TextField();
                    textField.setValue("");
                    textField.setReadOnly(true);
                }

            } else if ("netPriceTypeFormula".equals(propertyId)) {
                final CustomTextField netPriceTypeFormula = new CustomTextField();
                netPriceTypeFormula.setImmediate(true);
                netPriceTypeFormula.addStyleName(ConstantUtil.SEARCHICON);
                netPriceTypeFormula.setValue(psDTO.getNetPriceTypeFormula());
                netPriceTypeFormula.addClickListener(new CustomTextField.ClickListener() {
                    /**
                     * Method used for formulaNo
                     */
                    public void click(final CustomTextField.ClickEvent event) {
                        try {                            
                            
                            final NetSalesFormulaLookup nepFormulaLookup = new NetSalesFormulaLookup(true, netPriceTypeFormula);
                            UI.getCurrent().addWindow(nepFormulaLookup);
                            nepFormulaLookup.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                        
                                    NepFormulaLookUpDTO dto = nepFormulaLookup.getNepFormulaDTO();
                                        ((TempPricingDTO)itemId).setNetPriceTypeFormulaID(String.valueOf(dto.getNepFormulaSystemID()));
                                        if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                                        netPriceTypeFormula.setValue(dto.getNepFormulaName());      
                                }
                            });
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                });
                return netPriceTypeFormula;
                        } else if ("createdDate".equals(propertyId) || "resetDate".equals(propertyId) || "basePriceDate".equals(propertyId)) {
                final PopupDateField startDate = new PopupDateField();
                startDate.setDescription(ConstantsUtils.DATE_DES);
                startDate.setImmediate(true);
                startDate.setDateFormat(Constants.DATE_FORMAT);
                startDate.setValue("createdDate".equals(propertyId) ?
                        psDTO.getCreatedDate() : psDTO.getResetDate());
                startDate.addStyleName("datefieldcentered");
                return startDate;
            } 
            return null;

        }catch (Exception exception) {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {    
                /**            
                 * The method is triggered when a button of the message box is   
                 * pressed .        
                 *          
                 * @param buttonId The buttonId of the pressed button.     
                 */           
                @SuppressWarnings("PMD")           
                public void buttonClicked(final ButtonId buttonId) { 
                    return;
                }           
            }, ButtonId.OK);  
            msg.getButton(ButtonId.OK).focus();
            LOGGER.error(exception);

        }
        /**
         * The method is triggered when a button of the message box is
         * pressed .
         *
         * @param buttonId The buttonId of the pressed button.
         */
        final Field field = super.createField(container, itemId, propertyId, uiContext);
        field.setReadOnly(true);
                return field;
    }

    public Object updateChangedInfo(String userId, Object itemId) {
            String psDetailsSysId = String.valueOf(((TempPricingDTO) itemId).getPriceScheduleDetailsSystemId());
            if ((!"null".equals(psDetailsSysId)) && (!"0".equals(psDetailsSysId))) {
                ((TempPricingDTO) itemId).setModifiedBy(userId);
                ((TempPricingDTO) itemId).setModifiedDate(new Date());
                ((TempPricingDTO) itemId).setRevisionDate(new Date());
                ((TempPricingDTO) itemId).setOperation("U");
            }
        return itemId;
    }

    public Object updateChangedInfoDdlb(Object itemId) {
       
            String psDetailsSysId = String.valueOf(((TempPricingDTO) itemId).getPriceScheduleDetailsSystemId());
            if ((!"null".equals(psDetailsSysId)) && (!"0".equals(psDetailsSysId))) {
                ((TempPricingDTO) itemId).setOperation("U");
            }
       
        return itemId;
    }
    public Object updateChangedInfoDdlbPrice(Object itemId, String price) {
       
            String psDetailsSysId = String.valueOf(((TempPricingDTO) itemId).getPriceScheduleDetailsSystemId());
            if ((!"null".equals(psDetailsSysId)) && (!"0".equals(psDetailsSysId))) {
                ((TempPricingDTO) itemId).setOperation("U");
            }
        ((TempPricingDTO) itemId).setPrice(price);
        return itemId;
    }
    
    public void attachListeners(final AbstractField field, final String component, final Object itemId, final TempPricingDTO temp, final String userId) {
        field.setImmediate(true);
        field.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                try {
                    if ("priceProtectionStartDate".equals(component)) {
                        tempDateList = tempDate.get(temp.getItemId());
                        if (((PopupDateField) field).getValue().before((Date) dates[0])) {
                            AbstractNotificationUtils.getErrorNotification(ConstantUtil.POPULATE_ERROR, "Start date cannot be before " + format.format(dates[0]));
                            detachListeners(field);
                            ((PopupDateField) field).setValue(tempDateList.get(0));
                            attachListeners(field, component, itemId, temp, userId);
                            return;
                        } else if ((Date) dates[1] != null && ((PopupDateField) field).getValue().after((Date) dates[1])) {
                            AbstractNotificationUtils.getErrorNotification(ConstantUtil.POPULATE_ERROR, "Start date cannot be after " + format.format(dates[1]));
                            detachListeners(field);
                            ((PopupDateField) field).setValue(tempDateList.get(0));
                            attachListeners(field, component, itemId, temp, userId);
                            return;
                        } else {
                            ((PopupDateField) field).setDescription(CommonUIUtils.convert2DigitTo4DigitYear(((PopupDateField) field).getValue()));
                            updateChangedInfo(userId, itemId);
                            if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
                        }
                    } else if ("priceProtectionEndDate".equals(component)) {
                        tempDateList = tempDate.get(temp.getItemId());
                        if ((Date) dates[1] != null && ((PopupDateField) field).getValue().after((Date) dates[1])) {
                            detachListeners(field);
                            ((PopupDateField) field).setValue(tempDateList.get(1) != null ? tempDateList.get(1) : null);
                            AbstractNotificationUtils.getErrorNotification(ConstantUtil.POPULATE_ERROR, "End date cannot be after " + format.format(dates[1]));
                            attachListeners(field, component, itemId, temp, userId);
                            return;
                        } else if (((PopupDateField) field).getValue().before((Date) dates[0])) {
                            detachListeners(field);
                            ((PopupDateField) field).setValue(tempDateList.get(1) != null ? tempDateList.get(1) : null);
                            AbstractNotificationUtils.getErrorNotification(ConstantUtil.POPULATE_ERROR, "End date cannot be before " + format.format(dates[0]));
                            attachListeners(field, component, itemId, temp, userId);
                            return;
                        } else {
                            ((PopupDateField) field).setDescription(CommonUIUtils.convert2DigitTo4DigitYear(((PopupDateField) field).getValue()));
                            updateChangedInfo(userId, itemId);
                            if(!itemDetailsResultBean.containsId(itemId)) {
                                    itemDetailsResultBean.addItem(itemId);
                            }
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
