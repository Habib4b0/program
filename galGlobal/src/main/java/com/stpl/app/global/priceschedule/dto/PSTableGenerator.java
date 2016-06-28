package com.stpl.app.global.priceschedule.dto;

import com.stpl.app.global.common.ui.lookup.FormulaLookup;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.priceschedule.logic.PSLogic;
import com.stpl.app.global.priceschedule.ui.form.NEPFormulaLookUp;
import com.stpl.app.global.priceschedule.ui.lazyload.PriceTypeCriteria;
import com.stpl.app.global.priceschedule.ui.lazyload.PriceTypeLazyContainer;
import com.stpl.app.global.priceschedule.util.PsUtils;
import com.stpl.app.global.priceschedule.util.UIUtils;
import com.stpl.app.global.rebateschedule.dto.RSFormulaDTO;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.GeneralCommonUtils;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.TextChangeListener;
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
import java.text.DecimalFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;

// TODO: Auto-generated Javadoc
/**
 * This class contains the Table field factory generation based on container
 * properties.
 *
 * @author manikanta
 */
public class PSTableGenerator extends DefaultFieldFactory {

    /**
     * Default serial version ID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(PSTableGenerator.class.getName());
        private static final DecimalFormat FORMATDECIMAL = new DecimalFormat("0.00");
    private boolean reset;
    HelperDTO psnullDTO = new HelperDTO();

    public HelperDTO getPsnullDTO() {
        return psnullDTO;
    }

    public void setPsnullDTO(HelperDTO psnullDTO) {
        this.psnullDTO = psnullDTO;
    }
    private final BeanItemContainer<PSIFPDTO> itemDetailsResultBean;
    private final String mode;
    ExtPagedTable table;
    CommonUtil commonUtil = CommonUtil.getInstance();
    FormulaLookup formulaLookup = null;
    PSDTO priceScheduleDTO;

    public PSTableGenerator(ExtPagedTable table, final BeanItemContainer<PSIFPDTO> itemDetailsResultBean, final String mode, final PSDTO psDTO) {
        this.table = table;
        this.itemDetailsResultBean = itemDetailsResultBean;
        this.mode = mode;
        this.priceScheduleDTO = psDTO;
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
            final PSIFPDTO psDTO = (PSIFPDTO) itemId;
final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            reset = true;
            if ("checkRecord".equals(propertyId)) {
                final CheckBox checkbox = new CheckBox();
                checkbox.setReadOnly(false);
                checkbox.setValue(psDTO.getCheckRecord());
                checkbox.setImmediate(true);
                checkbox.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (psDTO.getCheckRecord() != null) {
                            itemDetailsResultBean.addItem(itemId);
                        }
                    }
                });
                return checkbox;
            } else if ("priceType".equals(propertyId)) {
                psnullDTO.setId(0);
                psnullDTO.setDescription(ConstantsUtils.SELECT_ONE);
                final ComboBox priceType = new ComboBox();
                priceType.setPageLength(7);
                priceType.setImmediate(true);
                priceType.setNullSelectionAllowed(true);
                priceType.setInputPrompt(ConstantsUtils.SELECT_ONE);
                priceType.setNullSelectionItemId(psnullDTO);
                priceType.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
                priceType.markAsDirty();
                final LazyContainer priceTypeContainer = new LazyContainer(HelperDTO.class, new PriceTypeLazyContainer(psDTO.getPriceType()), new PriceTypeCriteria());
                priceTypeContainer.setMinFilterLength(0);
                priceType.setContainerDataSource(priceTypeContainer);
                priceType.setValue(psDTO.getPriceType());
                priceType.addValueChangeListener(new Property.ValueChangeListener() {

                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        try {
                            HelperDTO helperDTO = (HelperDTO) event.getProperty().getValue();
                            if (event.getProperty().getValue() != null && !helperDTO.equals(GeneralCommonUtils.NULL_HELPER_DTO)) {
                                itemDetailsResultBean.addItem(updateChangedInfoDdlbPrice(userId, itemId, ConstantsUtils.ZERO));
                                itemDetailsResultBean.getItem(itemId).getItemProperty("price").setValue("0.00");
                                table.getItem(itemId).getItemProperty("price").setReadOnly(true);
                                itemDetailsResultBean.getItem(itemId).getItemProperty("price").setReadOnly(true);
                            } else if (event.getProperty().getValue() == null) {
                                table.getItem(itemId).getItemProperty("price").setReadOnly(false);
                                itemDetailsResultBean.getItem(itemId).getItemProperty("price").setReadOnly(false);
                                itemDetailsResultBean.addItem(updateChangedInfoDdlbPrice(userId, itemId, ConstantsUtils.ZERO));
                            }
                        } catch (Exception ex) {
                            LOGGER.error(ex);

                        }
                    }
                });
                return priceType;
            } else if ("priceTolerance".equals(propertyId)) {
                final TextField priceTolerance = new TextField();
                priceTolerance.setImmediate(true);
                priceTolerance.addValidator(new RegexpValidator("([|0-9]*.[0-9]{1,2})", "Please Enter Only Numbers with two decimal places"));
                priceTolerance.setValidationVisible(true);
                priceTolerance.setValue(psDTO.getPriceTolerance());
                priceTolerance.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            itemDetailsResultBean.addItem(itemId);
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
            } else if ("priceToleranceType".equals(propertyId)) {
                final ComboBox tolerance = new ComboBox();
                commonUtil.loadComboBox(tolerance, UIUtils.PRICE_TOLERANCE_TYPE, false);
                tolerance.select(psDTO.getPriceToleranceType());
                tolerance.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            itemDetailsResultBean.addItem(itemId);
                            updateChangedInfoDdlb(userId, itemId);
                        }
                    }
                });
                return tolerance;
            } else if ("priceToleranceInterval".equals(propertyId)) {
                final ComboBox toleranceInterval = new ComboBox();
                commonUtil.loadComboBox(toleranceInterval, UIUtils.PRICE_TOLERANCE_INTERVAL, false);
                toleranceInterval.select(psDTO.getPriceToleranceInterval());
                toleranceInterval.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            itemDetailsResultBean.addItem(itemId);
                            updateChangedInfoDdlb(userId, itemId);
                        }
                    }
                });
                return toleranceInterval;
            } else if ("priceToleranceFrequency".equals(propertyId)) {
                final ComboBox frequency = new ComboBox();
                commonUtil.loadComboBox(frequency, UIUtils.PRICE_TOLERANCE_FRERQUENCY, false);
                frequency.setValue(psDTO.getPriceToleranceFrequency());
                frequency.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            itemDetailsResultBean.addItem(itemId);
                            updateChangedInfoDdlb(userId, itemId);
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
                        if (!ConstantsUtils.ZERO.equals(event.getProperty().getValue()) && !"".equals(event.getProperty().getValue())) {
                            itemDetailsResultBean.addItem(itemId);
                            itemDetailsResultBean.getItem(itemId).getItemProperty("priceType").setValue(new HelperDTO(0, Constants.SELECT_ONE));
                            table.getItem(itemId).getItemProperty("priceType").setReadOnly(true);
                            itemDetailsResultBean.getItem(itemId).getItemProperty("priceType").setReadOnly(true);
                        } else {
                            table.getItem(itemId).getItemProperty("priceType").setReadOnly(false);
                            itemDetailsResultBean.getItem(itemId).getItemProperty("priceType").setReadOnly(false);
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
                        if (event.getProperty().getValue() != null) {
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

            } else if ("nep".equals(propertyId)) {
                final TextField nep = new TextField();
                nep.setImmediate(true);
                nep.setValue(psDTO.getNep());
                nep.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            updateChangedInfo(userId, itemId);
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
                nepFormula.addStyleName(ConstantsUtils.SEARCH_SYLENAME);
                nepFormula.setValue(psDTO.getNepFormula());
                nepFormula.addClickListener(new CustomTextField.ClickListener() {
                    /**
                     * Method used for formulaNo
                     */
                    public void click(final CustomTextField.ClickEvent event) {

                        try {

                            final NEPFormulaLookUp nepFormulaLookup = new NEPFormulaLookUp("NEP");
                            UI.getCurrent().addWindow(nepFormulaLookup);

                            nepFormulaLookup.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {

                                    PSNepFormulaDTO dto = nepFormulaLookup.getNepFormulaDTO();
                                    ((PSIFPDTO) itemId).setNepFormulaSid(dto.getNepFormulaSystemID());
                                    itemDetailsResultBean.addItem(itemId);
                                    nepFormula.setValue(dto.getNepFormulaName());
                                }
                            });
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                });
                return nepFormula;
            } else if ("netBasePriceFormulaName".equals(propertyId) || "netSubsequentPriceFormulaName".equals(propertyId)
                    || ConstantsUtils.NET_RESET_PRICE_FORMULA_NAME.equals(propertyId) || "netPriceTypeFormulaName".equals(propertyId)) {
                final CustomTextField formulaLookup = new CustomTextField();
                formulaLookup.setImmediate(true);
                formulaLookup.setValue("");
                formulaLookup.addStyleName(ConstantsUtils.SEARCH_SYLENAME);
                formulaLookup.setValue(psDTO.getNepFormula());
                if ("netPriceTypeFormulaName".equals(propertyId)) {
                    formulaLookup.setValue(psDTO.getNetPriceTypeFormulaName());
                }
                formulaLookup.addClickListener(new CustomTextField.ClickListener() {
                    /**
                     * Method used for formulaNo
                     */
                    public void click(final CustomTextField.ClickEvent event) {

                        try {

                            final NEPFormulaLookUp nepFormulaLookup = new NEPFormulaLookUp("");
                            UI.getCurrent().addWindow(nepFormulaLookup);

                            nepFormulaLookup.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {

                                    PSNepFormulaDTO dto = nepFormulaLookup.getNepFormulaDTO();
                                    switch (String.valueOf(propertyId)) {
                                        case "netBasePriceFormulaName":
                                            psDTO.setNetBasePriceFormulaId(dto.getNepFormulaSystemID());
                                            psDTO.setNetBasePriceFormulaName(dto.getNepFormulaName());
                                            break;
                                        case "netSubsequentPriceFormulaName":
                                            psDTO.setNetSubsequentPriceFormulaId(dto.getNepFormulaSystemID());
                                            psDTO.setNetSubsequentPriceFormulaName(dto.getNepFormulaName());
                                            break;
                                        case ConstantsUtils.NET_RESET_PRICE_FORMULA_NAME:
                                            psDTO.setNetResetPriceFormulaId(dto.getNepFormulaSystemID());
                                            psDTO.setNetResetPriceFormulaName(dto.getNepFormulaName());                                        
                                            break;
                                            
                                        case "netPriceTypeFormulaName":
                                            psDTO.setNetPriceTypeFormula(String.valueOf(dto.getNepFormulaSystemID()));
                                            psDTO.setNetPriceTypeFormulaName(dto.getNepFormulaName());                                        
                                            break;       
                                    }
                                    itemDetailsResultBean.addItem(itemId);
                                    if(ConstantsUtils.NET_RESET_PRICE_FORMULA_NAME.equals(propertyId)){
                                    formulaLookup.setReadOnly(false);
                                    formulaLookup.setValue(dto.getNepFormulaName());
                                        formulaLookup.setReadOnly(Constants.NO.equals(psDTO.getResetEligible()));
                                    }else{
                                     formulaLookup.setValue(dto.getNepFormulaName());
                                    }
                                }
                            });
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                });
                return formulaLookup;

            } else if ("maxIncrementalChange".equals(propertyId)) {
                final TextField maxIncChange = new TextField();
                maxIncChange.setImmediate(true);
                maxIncChange.setValue(psDTO.getMaxIncrementalChange());
                maxIncChange.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            updateChangedInfo(userId, itemId);
                            itemDetailsResultBean.addItem(itemId);
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
                            itemDetailsResultBean.addItem(itemId);
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
            } else if ("priceProtectionStartDate".equals(propertyId)) {
                final PopupDateField startDate = new PopupDateField();
                startDate.setDescription(ConstantsUtils.DATE_DES);
                startDate.setImmediate(true);
                startDate.setDateFormat(Constants.DATE_FORMAT);
                startDate.setValue(psDTO.getPriceProtectionStartDate());
                startDate.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            startDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(startDate.getValue()));
                            updateChangedInfo(userId, itemId);
                            itemDetailsResultBean.addItem(itemId);
                        }
                    }
                });
                return startDate;
            } else if ("priceProtectionEndDate".equals(propertyId)) {
                final PopupDateField endDate = new PopupDateField();
                endDate.setDescription(ConstantsUtils.DATE_DES);
                endDate.setDateFormat(Constants.DATE_FORMAT);
                endDate.setImmediate(true);
                endDate.setValue(psDTO.getPriceProtectionEndDate());
                endDate.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            endDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(endDate.getValue()));
                            updateChangedInfo(userId, itemId);
                            itemDetailsResultBean.addItem(itemId);
                        }
                    }
                });
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
                            itemDetailsResultBean.addItem(itemId);
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
                            itemDetailsResultBean.addItem(itemId);
                        }
                    }
                });
                return endDate;
            } else if ("revisionDate".equals(propertyId)) {

            } else if ("resetEligible".equals(propertyId)) {
                final ComboBox resetEligible = new ComboBox();
                  commonUtil.loadComboBox(resetEligible, ConstantsUtils.LOCKED_STATUS, false);
                container.getContainerProperty(itemId, ConstantsUtils.RESET_DATE).setReadOnly(reset);
                resetEligible.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            itemDetailsResultBean.addItem(itemId);
                            updateChangedInfoDdlb(userId, itemId);
                            String resetProperty = String.valueOf(event.getProperty().getValue());
                            if ("Yes".equalsIgnoreCase(resetProperty)) {
                                reset = false;
                            } else if("No".equalsIgnoreCase(resetProperty)){
                                reset = true;
                            }
                            container.getContainerProperty(itemId, ConstantsUtils.RESET_DATE).setReadOnly(reset);
                            container.getContainerProperty(itemId, ConstantsUtils.RESET_PRICE_TYPE).setReadOnly(reset);
                            container.getContainerProperty(itemId, ConstantsUtils.NET_RESET_PRICE_FORMULA_NAME).setReadOnly(reset);
                            container.getContainerProperty(itemId, ConstantsUtils.NET_PRICE_TYPE).setReadOnly(reset);
                                     
                        }
                    }
                });
                return resetEligible;
            } else if ("priceProtectionPriceType".equals(propertyId)) {
                psnullDTO.setId(0);
                psnullDTO.setDescription(ConstantsUtils.SELECT_ONE);
                final ComboBox priceProtectionPriceType = new ComboBox();
                priceProtectionPriceType.setPageLength(7);
                priceProtectionPriceType.setImmediate(true);
                priceProtectionPriceType.setNullSelectionAllowed(true);
                priceProtectionPriceType.setInputPrompt(ConstantsUtils.SELECT_ONE);
                priceProtectionPriceType.setNullSelectionItemId(psnullDTO);
                priceProtectionPriceType.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
                priceProtectionPriceType.markAsDirty();
                final LazyContainer priceTypeContainer = new LazyContainer(HelperDTO.class, new PriceTypeLazyContainer(psDTO.getPriceType()), new PriceTypeCriteria());
                priceTypeContainer.setMinFilterLength(0);
                priceProtectionPriceType.setContainerDataSource(priceTypeContainer);
                priceProtectionPriceType.setValue(psDTO.getPriceType());

                priceProtectionPriceType.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            itemDetailsResultBean.addItem(itemId);
                            updateChangedInfoDdlb(userId, itemId);
                        }
                    }
                });
                return priceProtectionPriceType;
            } else if ("priceProtectionStatus".equals(propertyId)) {
                final ComboBox priceProtectionStatus = new ComboBox();
                commonUtil.loadComboBox(priceProtectionStatus, UIUtils.STATUS, false);
                priceProtectionStatus.setValue(psDTO.getPriceProtectionStatus());
                priceProtectionStatus.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            itemDetailsResultBean.addItem(itemId);
                            updateChangedInfoDdlb(userId, itemId);
                        }
                    }
                });
                return priceProtectionStatus;
            } else if ("itemStatus".equals(propertyId)) {
                final ComboBox itemStatus = new ComboBox();
                commonUtil.loadComboBox(itemStatus, UIUtils.STATUS, false);
                itemStatus.setValue(psDTO.getItemStatus());
                itemStatus.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            itemDetailsResultBean.addItem(itemId);
                            updateChangedInfoDdlb(userId, itemId);
                        }
                    }
                });
                return itemStatus;
            } else if ("pricePlanID".equals(propertyId)) {
                final CustomTextField pricePlanID = new CustomTextField();
                pricePlanID.setImmediate(true);
                pricePlanID.addStyleName(ConstantsUtils.SEARCH_SYLENAME);
                pricePlanID.setEnabled(false);
                pricePlanID.setValue(psDTO.getMaxIncrementalChange());
                pricePlanID.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            updateChangedInfo(userId, itemId);
                            itemDetailsResultBean.addItem(itemId);
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
                            itemDetailsResultBean.addItem(itemId);
                            updateChangedInfoDdlb(userId, itemId);
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
                            itemDetailsResultBean.addItem(itemId);
                            updateChangedInfoDdlb(userId, itemId);
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
                            itemDetailsResultBean.addItem(itemId);
                            updateChangedInfoDdlb(userId, itemId);
                        }
                    }
                });
                return resetFrequency;
            } else if (ConstantsUtils.NET_PRICE_TYPE.equals(propertyId)) {
                final ComboBox netPriceType = new ComboBox();
               commonUtil.loadComboBox(netPriceType, ConstantsUtils.LOCKED_STATUS, false);
                netPriceType.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            itemDetailsResultBean.addItem(itemId);
                            updateChangedInfoDdlb(userId, itemId);
                        }
                    }
                });
                return netPriceType;
            } else if ("createdDate".equals(propertyId) || "attachedDate".equals(propertyId) || ConstantsUtils.RESET_DATE.equals(propertyId)) {
                final PopupDateField startDate = new PopupDateField();
                startDate.setDescription(ConstantsUtils.DATE_DES);
                startDate.setImmediate(true);
                startDate.setDateFormat(Constants.DATE_FORMAT);

                startDate.addValueChangeListener(new Property.ValueChangeListener() {

                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        itemDetailsResultBean.addItem(itemId);
                    }
                });
                startDate.setValue("createdDate".equals(propertyId)
                        ? psDTO.getCreatedDate() : "attachedDate".equals(propertyId) ? psDTO.getAttachedDate() : psDTO.getResetDate());
                startDate.addStyleName("datefieldcentered");
                return startDate;
            } else if ("basePriceType".equals(propertyId)) {
                final ComboBox comboBox = new ComboBox();
                commonUtil.loadComboBox(comboBox, "BASE_PRICE_TYPE", false);
                comboBox.addFocusListener(new FieldEvents.FocusListener() {
                    @Override
                    public void focus(FieldEvents.FocusEvent event) {
                        HelperDTO focusVal =(HelperDTO)(comboBox.getValue()!=null? comboBox.getValue() :new HelperDTO(0, ConstantsUtils.SELECT_ONE));
                        psDTO.setSelectedBasePrice(focusVal);
                        comboBox.addBlurListener(new FieldEvents.BlurListener() {
                            @Override
                            public void blur(FieldEvents.BlurEvent event) {
                                HelperDTO blurVal = (HelperDTO)(comboBox.getValue()!=null? comboBox.getValue() :"");
                                if (!psDTO.getSelectedBasePrice().equals(blurVal)) {
                                    psDTO.setSelectedBasePrice(blurVal == null ? new HelperDTO(0, ConstantsUtils.SELECT_ONE): blurVal);
                                    itemDetailsResultBean.addItem(itemId);
                                    updateChangedInfoDdlb(userId, itemId);
                                    table.setRefresh(true);
                                }
                                comboBox.removeBlurListener(this);
                            }
                        });
                    }
                });

                return comboBox;
            } else if (ConstantsUtils.NET_BASE_PRICE.equals(propertyId) || "netSubsequentPeriodPrice".equals(propertyId) || "netResetPriceType".equals(propertyId)) {
                final ComboBox comboBox = new ComboBox();                
            commonUtil.loadComboBox(comboBox, ConstantsUtils.LOCKED_STATUS, false);
                comboBox.addValueChangeListener(new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        itemDetailsResultBean.addItem(itemId);
                        updateChangedInfoDdlb(userId, itemId);
                    }
                });
                comboBox.setValue(ConstantsUtils.NET_BASE_PRICE.equals(propertyId) ? psDTO.getNetBasePrice() : "netSubsequentPeriodPrice".equals(propertyId) ? psDTO.getNetSubsequentPeriodPrice() : psDTO.getNetResetPriceType());
                return comboBox;

            } else if (ConstantsUtils.BASE_PRICE_VALUE.equals(propertyId)) {
                

                if (Constants.MANUAL.equals(psDTO.getBasePriceType().getDescription())) {
                
                    psDTO.setBasePriceValue(StringUtils.EMPTY);
                    final TextField basePrice = new TextField();
                    basePrice.setImmediate(true);
                    basePrice.setEnabled(true);
                    basePrice.setValue(ConstantsUtils.BASE_PRICE_VALUE.equals(propertyId) ? String.valueOf(psDTO.getBasePriceValue()) : null);
                    basePrice.addValueChangeListener(new Property.ValueChangeListener() {
                        @Override
                        public void valueChange(Property.ValueChangeEvent event) {
                            psDTO.setBasePriceEntry(Double.valueOf(PsUtils.checkNullValues(basePrice.getValue()) ? "0.00" : basePrice.getValue()));
                            itemDetailsResultBean.addItem(itemId);
                        }
                    });
                    psDTO.setBasePriceValue(FORMATDECIMAL.format(psDTO.getBasePriceEntry()));
                    return basePrice;
                } else if (Constants.DATE.equals(psDTO.getBasePriceType().getDescription())) {
                    
                    psDTO.setBasePriceValue(StringUtils.EMPTY);
                    final PopupDateField date = new PopupDateField();
                    date.setImmediate(true);
                    date.setDateFormat(Constants.DATE_FORMAT);
                    date.addStyleName("datefieldcentered");
                    date.setValue(psDTO.getBasePriceDate());
                    date.addValueChangeListener(new Property.ValueChangeListener() {
                        @Override
                        public void valueChange(Property.ValueChangeEvent event) {
                            psDTO.setBasePriceDate(date.getValue());
                            itemDetailsResultBean.addItem(itemId);
                        }
                    });  
                    
                    psDTO.setBasePriceValue(psDTO.getBasePriceDate());
                    return date;
                } else if (Constants.PRICE_TYPE.equals(psDTO.getBasePriceType().getDescription())) {                    

                    psDTO.setBasePriceValue(StringUtils.EMPTY);
                    final ComboBox comboBox = new ComboBox();
                    PSLogic psLogic = new PSLogic();                    
                    Container priceTypeContainer = new BeanItemContainer(HelperDTO.class, psLogic.getConfiguredPriceTypes(priceScheduleDTO.getItemPricingQualifierMap(), false));
                    comboBox.setContainerDataSource(priceTypeContainer);
                    comboBox.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
                    comboBox.setImmediate(true);
                    comboBox.setNullSelectionAllowed(true);
                    comboBox.setNullSelectionItemId(new HelperDTO(0, ConstantsUtils.SELECT_ONE));                    
                    comboBox.setValue(psDTO.getBasePriceItemPriceType());
                    comboBox.addValueChangeListener(new Property.ValueChangeListener() {
                        @Override
                        public void valueChange(Property.ValueChangeEvent event) {
                            psDTO.setBasePriceItemPriceType(comboBox.getValue() != null ? (HelperDTO)comboBox.getValue() : new HelperDTO(0, ConstantsUtils.SELECT_ONE));
                            itemDetailsResultBean.addItem(itemId);
                            updateChangedInfoDdlb(userId, itemId); 
                        }
                    });
                    psDTO.setBasePriceValue(psDTO.getBasePriceItemPriceType());
                    return comboBox;
                }

            } else if (ConstantsUtils.RESET_PRICE_TYPE.equals(propertyId) || "subsequentPeriodPriceType".equals(propertyId)) {

                final ComboBox comboBox = new ComboBox();
                comboBox.setImmediate(true);
                PSLogic psLogic = new PSLogic();
                Container priceTypeContainer = new BeanItemContainer(HelperDTO.class, psLogic.getConfiguredPriceTypes(priceScheduleDTO.getItemPricingQualifierMap(), false));
                comboBox.setContainerDataSource(priceTypeContainer);
                comboBox.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(new HelperDTO(0, ConstantsUtils.SELECT_ONE));
                comboBox.addValueChangeListener(new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        itemDetailsResultBean.addItem(itemId);
                        updateChangedInfoDdlb(userId, itemId);
                    }
                });
                 comboBox.setReadOnly(false);
                comboBox.setValue(ConstantsUtils.RESET_PRICE_TYPE.equals(propertyId) ? psDTO.getResetPriceType() : psDTO.getSubsequentPeriodPriceType());
                comboBox.setReadOnly(Constants.NO.equals(psDTO.getResetEligible()));

                return comboBox;
            }

        } catch (SystemException ex) {
           LOGGER.error(ex);
        } catch (PortalException portException) {
            LOGGER.error(portException);
        } catch (Exception exception) {
            LOGGER.error(exception);

        }
        final Field field = super.createField(container, itemId, propertyId, uiContext);
        field.setReadOnly(true);
        return field;
    }

    public Object updateChangedInfo(String userId, Object itemId) {
        if (ConstantsUtils.EDIT.equals(mode)) {
            String psDetailsSysId = String.valueOf(((PSIFPDTO) itemId).getPriceScheduleDetailsSystemId());
            if ((!ConstantsUtils.NULL.equals(psDetailsSysId)) && (!ConstantsUtils.ZERO.equals(psDetailsSysId))) {
                ((PSIFPDTO) itemId).setModifiedBy(userId);
                ((PSIFPDTO) itemId).setModifiedDate(new Date());
                ((PSIFPDTO) itemId).setRevisionDate(new Date());
                ((PSIFPDTO) itemId).setOperation(ConstantsUtils.U);
            }
        }
        return itemId;
    }

    public Object updateChangedInfoDdlb(String userId, Object itemId) {
        if (ConstantsUtils.EDIT.equals(mode)) {
            String psDetailsSysId = String.valueOf(((PSIFPDTO) itemId).getPriceScheduleDetailsSystemId());
            if ((!ConstantsUtils.NULL.equals(psDetailsSysId)) && (!ConstantsUtils.ZERO.equals(psDetailsSysId))) {
                ((PSIFPDTO) itemId).setOperation(ConstantsUtils.U);
            }
        }
        return itemId;
    }
    public Object updateChangedInfoDdlbPrice(String userId, Object itemId, String price) {
        if (ConstantsUtils.EDIT.equals(mode)) {
            String psDetailsSysId = String.valueOf(((PSIFPDTO) itemId).getPriceScheduleDetailsSystemId());
            if ((!ConstantsUtils.NULL.equals(psDetailsSysId)) && (!ConstantsUtils.ZERO.equals(psDetailsSysId))) {
                ((PSIFPDTO) itemId).setOperation(ConstantsUtils.U);
            }
        }
        ((PSIFPDTO) itemId).setPrice(price);
        return itemId;
    }

}
