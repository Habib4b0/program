/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.globalchange.fieldfactory;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.add.dto.AddItemTableDTO;
import com.stpl.app.gcm.itemmanagement.add.form.AddContractSelection;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.AbstractContractSearchDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentLookUpDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.FormulaDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.ComponentLookUp;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.FormulaLookUp;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.NEPLookup;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.validator.RegexpValidator;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.TableFieldFactory;
import com.vaadin.v7.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

/**
 *
 * @author mohamed.hameed
 */
public class AddItemContractFieldFactory implements TableFieldFactory {

    List<AddItemTableDTO> selectedItemList = new ArrayList<>();
    AbstractLogic logic = AbstractLogic.getInstance();
    SelectionDTO selection;
    public ExtPagedTable contractSelectionTable;
    Map tempTableMap = new HashMap();
    boolean valueChange = true;

    public AddItemContractFieldFactory(final SelectionDTO selection, ExtPagedTable contractSelectionTable, Map tempTableMap) {
        this.selection = selection;
        this.contractSelectionTable = contractSelectionTable;
        this.tempTableMap = tempTableMap;
    }

    public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
        final AbstractContractSearchDTO mainDto = (AbstractContractSearchDTO) itemId;
        final AbstractLogic abstractLogic = AbstractLogic.getInstance();
        if (propertyId.equals(Constants.CHECK_RECORD)) {
            final ExtCustomCheckBox check = new ExtCustomCheckBox();
            if (!mainDto.getWorkFlowStatus().trim().isEmpty()) {
                check.setVisible(false);
            } else {
                check.setVisible(true);
                check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                    public void click(ExtCustomCheckBox.ClickEvent event) {
                        AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                        dto.setCheckRecord(check.getValue());
                        dto.setColumnName("CHECK_RECORD");
                        dto.setCaseNo(NumericConstants.SEVENTEEN);
                        saveTempItemDetails(dto);
                    }
                });
            }
            return check;
        }
        if (propertyId.equals("status")) {
            final ComboBox itemStatus = new ComboBox();
            CommonUtil.getComboBoxByListName(itemStatus, UiUtils.STATUS, false);
            itemStatus.addFocusListener(new FocusListener() {
                @Override
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                      itemStatus.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                Object itemStatusValue = itemStatus.getValue();
                                HelperDTO helperDto = null;
                                if (itemStatusValue != null) {
                                    helperDto = (HelperDTO) itemStatus.getValue();
                                }
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setStatus(helperDto);
                                dto.setColumnName("ITEM_STATUS");
                                dto.setCaseNo(NumericConstants.SIX);
                                valueChange = false;
                                if (dto.getCheckRecord()) {
                                    populateLogic(itemStatus.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;

                            }
                        }
                    });
                    itemStatus.removeFocusListener(this);
                }
            });

            return itemStatus;
        }

        if (propertyId.equals("itemStartDate")) {
            final PopupDateField itemstartDate = new PopupDateField();
            itemstartDate.setRequired(true);
            itemstartDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
            itemstartDate.addStyleName(ConstantsUtil.ALIGN_CENTER);

            itemstartDate.addFocusListener(new FocusListener() {
                @Override
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    Property.ValueChangeListener valueChangeListner = new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setCaseNo(0);
                                Date startDate = dto.getItemEndDate();
                                if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER)) {
                                    startDate = abstractLogic.getStartDateCheck(dto, selection, "START_DATE");
                                }

                                if (itemstartDate.getValue() != null && startDate != null && itemstartDate.getValue().after(startDate)) {
                                    itemstartDate.setValue(null);
                                    if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER)) {
                                        MessageBox.showPlain(Icon.ERROR, StringConstantsUtil.START_DATE_HEADER, "You cannot proceed with this Item Start Date since it does not come after the End Date you have entered on the previous screen.", ButtonId.OK);
                                    } else {
                                        MessageBox.showPlain(Icon.ERROR, StringConstantsUtil.START_DATE_HEADER, "You cannot proceed with this Item Start Date since it does not come after the Item End Date.", ButtonId.OK);
                                    }
                                } else {
                                    dto.setStartDate(itemstartDate.getValue());
                                    dto.setColumnName("START_DATE");
                                    dto.setCaseNo(1);
                                    if (dto.getCheckRecord()) {
                                        populateLogic(itemstartDate.getValue(), dto.getColumnName());
                                        saveTempPopulateItemDetails(dto);
                                    } else {
                                        saveTempItemDetails(dto);
                                    }
                                }
                                valueChange = true;
                            }

                        }
                    };
                    itemstartDate.addValueChangeListener(valueChangeListner);
                    valueChangeListner.valueChange(null);
                    itemstartDate.removeFocusListener(this);
                }
            });

            return itemstartDate;
        }
        if (propertyId.equals("itemEndDate")) {
            final PopupDateField itemendDate = new PopupDateField();
            itemendDate.addStyleName(ConstantsUtil.ALIGN_CENTER);
            itemendDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
            itemendDate.addFocusListener(new FocusListener() {
                @Override
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                     Property.ValueChangeListener valueChangeListner = new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setCaseNo(0);
                                Date startDate = dto.getStartDate();
                                if (startDate != null && itemendDate.getValue() != null && itemendDate.getValue().before(startDate)) {
                                    itemendDate.setValue(null);
                                    MessageBox.showPlain(Icon.ERROR, StringConstantsUtil.START_DATE_HEADER, "You cannot proceed with this Item End Date since it does not come after the Start Date.", ButtonId.OK);
                                } else {
                                    dto.setEndDate(itemendDate.getValue());
                                    dto.setColumnName("END_DATE");
                                    dto.setCaseNo(NumericConstants.TWO);
                                    if (dto.getCheckRecord()) {
                                        populateLogic(itemendDate.getValue(), dto.getColumnName());
                                        saveTempPopulateItemDetails(dto);
                                    } else {
                                        saveTempItemDetails(dto);
                                    }
                                }
                                valueChange = true;
                            }
                        }
                    };
                    itemendDate.addValueChangeListener(valueChangeListner);
                    valueChangeListner.valueChange(null);
                    itemendDate.removeFocusListener(this);
                }
            });

            return itemendDate;
        }
        if (propertyId.equals("cpStartDate")) {
            final PopupDateField cpstartDate = new PopupDateField();
            cpstartDate.addStyleName(ConstantsUtil.ALIGN_CENTER);
            cpstartDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
            cpstartDate.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    Property.ValueChangeListener valueChangeListner = new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setStartDate(cpstartDate.getValue());
                                dto.setColumnName("CONTRACT_PRICE_START_DATE");
                                dto.setCaseNo(NumericConstants.FOUR);
                                if (dto.getCheckRecord()) {
                                    populateLogic(cpstartDate.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }
                        }
                    };
                    cpstartDate.addValueChangeListener(valueChangeListner);
                    valueChangeListner.valueChange(null);
                    cpstartDate.removeFocusListener(this);

                }
            });

            return cpstartDate;
        }
        if (propertyId.equals("cpEndDate")) {
            final PopupDateField cpendDate = new PopupDateField();
            cpendDate.addStyleName(ConstantsUtil.ALIGN_CENTER);
            cpendDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
            cpendDate.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    Property.ValueChangeListener valueChangeListner = new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setCaseNo(0);
                                Date startDate = abstractLogic.getStartDateCheck(dto, selection, "CONTRACT_PRICE_START_DATE");
                                if (startDate != null && cpendDate.getValue() != null && cpendDate.getValue().before(startDate)) {
                                    cpendDate.setValue(null);
                                    MessageBox.showPlain(Icon.ERROR, StringConstantsUtil.START_DATE_HEADER, "You cannot proceed with this CP End Date since it does not come after the CP Start Date.", ButtonId.OK);
                                } else {
                                    dto.setEndDate(cpendDate.getValue());
                                    dto.setColumnName("CONTRACT_PRICE_END_DATE");
                                    dto.setCaseNo(NumericConstants.FIVE);
                                    if (dto.getCheckRecord()) {
                                        populateLogic(cpendDate.getValue(), dto.getColumnName());
                                        saveTempPopulateItemDetails(dto);
                                    } else {
                                        saveTempItemDetails(dto);
                                    }
                                }
                                valueChange = true;
                            }

                        }
                    };
                    cpendDate.addValueChangeListener(valueChangeListner);
                    valueChangeListner.valueChange(null);
                    cpendDate.removeFocusListener(this);
                }
            });

            return cpendDate;
        }

        if (propertyId.equals("priceProtectionStartDate")) {
            final PopupDateField priceProtectionStartDate = new PopupDateField();
            priceProtectionStartDate.addStyleName(ConstantsUtil.ALIGN_CENTER);
            priceProtectionStartDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
            priceProtectionStartDate.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    Property.ValueChangeListener valueChangeListner = new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setPriceProtectionStartDate(priceProtectionStartDate.getValue());
                                dto.setColumnName("PRICE_PROTECTION_START_DATE");
                                dto.setCaseNo(NumericConstants.EIGHT);
                                if (dto.getCheckRecord()) {
                                    populateLogic(priceProtectionStartDate.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }

                        }
                    };
                    priceProtectionStartDate.addValueChangeListener(valueChangeListner);
                    valueChangeListner.valueChange(null);
                    priceProtectionStartDate.removeFocusListener(this);
                }
            });

            return priceProtectionStartDate;
        }
        if (propertyId.equals("priceProtectionEndDate")) {
            final PopupDateField priceProtectionEndDate = new PopupDateField();
            priceProtectionEndDate.addStyleName(ConstantsUtil.ALIGN_CENTER);
            priceProtectionEndDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
            priceProtectionEndDate.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    Property.ValueChangeListener valueChangeListner = new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setCaseNo(0);
                                Date startDate = abstractLogic.getStartDateCheck(dto, selection, "PRICE_PROTECTION_START_DATE");
                                if (startDate != null && priceProtectionEndDate.getValue() != null && priceProtectionEndDate.getValue().before(startDate)) {
                                    priceProtectionEndDate.setValue(null);
                                    MessageBox.showPlain(Icon.ERROR, StringConstantsUtil.START_DATE_HEADER, "You cannot proceed with this Price Protection End Date since it does not come after the Price Protection Start Date.", ButtonId.OK);
                                } else {
                                    dto.setPriceProtectionEndDate(priceProtectionEndDate.getValue());
                                    dto.setColumnName("PRICE_PROTECTION_END_DATE");
                                    dto.setCaseNo(NumericConstants.NINE);
                                    if (dto.getCheckRecord()) {
                                        populateLogic(priceProtectionEndDate.getValue(), dto.getColumnName());
                                        saveTempPopulateItemDetails(dto);
                                    } else {
                                        saveTempItemDetails(dto);
                                    }
                                }
                                valueChange = true;
                            }

                        }
                    };
                    priceProtectionEndDate.addValueChangeListener(valueChangeListner);
                    valueChangeListner.valueChange(null);
                    priceProtectionEndDate.removeFocusListener(this);
                }
            });

            return priceProtectionEndDate;
        }
        if (propertyId.equals("priceToleranceType")) {
            final ComboBox priceToleranceType = new ComboBox();
            CommonUtil.getComboBoxByListName(priceToleranceType, "PRICE_TOLERANCE_TYPE", false);
            priceToleranceType.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    priceToleranceType.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                if (priceToleranceType.getValue() != null) {
                                    Object itemStatusValue = priceToleranceType.getValue();
                                    HelperDTO helperDto = null;
                                    if (itemStatusValue != null) {
                                        helperDto = (HelperDTO) priceToleranceType.getValue();
                                    }
                                    AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                    dto.setPriceToleranceType(helperDto);
                                    dto.setColumnName("PRICE_TOLERANCE_TYPE");
                                    dto.setCaseNo(NumericConstants.TEN);
                                    if (dto.getCheckRecord()) {
                                        populateLogic(priceToleranceType.getValue(), dto.getColumnName());
                                        saveTempPopulateItemDetails(dto);
                                    } else {
                                        saveTempItemDetails(dto);
                                    }
                                }
                                valueChange = true;
                            }
                        }
                    });
                    priceToleranceType.removeFocusListener(this);
                }
            });

            return priceToleranceType;
        }
        if (propertyId.equals("priceTolerance")) {
            final CustomTextField priceTolerance = new CustomTextField();
            priceTolerance.addValidator(new RegexpValidator(ConstantsUtil.NUMERIC, StringConstantsUtil.ONLY_NUMERIC_CHARACTERS_CAN_BE_ENTERED));
            priceTolerance.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    priceTolerance.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (priceTolerance.getValue() != null && !priceTolerance.getValue().trim().isEmpty() && valueChange) {
                                    valueChange = false;
                                    AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                    dto.setPriceTolerance(priceTolerance.getValue());
                                    dto.setColumnName("PRICE_TOLERANCE");
                                    dto.setCaseNo(NumericConstants.SEVEN);
                                    if (dto.getCheckRecord()) {
                                        populateLogic(priceTolerance.getValue(), dto.getColumnName());
                                        saveTempPopulateItemDetails(dto);
                                    } else {
                                        saveTempItemDetails(dto);
                                    }
                                    valueChange = true;
                            }
                        }
                    });
                    priceTolerance.removeFocusListener(this);
                }
            });
            priceTolerance.addValidator(new RegexpValidator(ConstantsUtil.NUMERIC, StringConstantsUtil.ONLY_NUMERIC_CHARACTERS_CAN_BE_ENTERED));

            return priceTolerance;

        }

        if (propertyId.equals("priceToleranceFrequency")) {
            final ComboBox priceToleranceFrequency = new ComboBox();
            CommonUtil.getComboBoxByListName(priceToleranceFrequency, "PRICE_TOLERANCE_FREQUENCY", false);
            priceToleranceFrequency.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    priceToleranceFrequency.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                Object itemStatusValue = priceToleranceFrequency.getValue();
                                HelperDTO helperDto = null;
                                if (itemStatusValue != null) {
                                    helperDto = (HelperDTO) priceToleranceFrequency.getValue();
                                }

                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setPriceToleranceFrequency(helperDto);
                                dto.setColumnName("PRICE_TOLERANCE_FREQUENCY");
                                dto.setCaseNo(NumericConstants.TWELVE);
                                if (dto.getCheckRecord()) {
                                    populateLogic(priceToleranceFrequency.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }

                        }
                    });
                    priceToleranceFrequency.removeFocusListener(this);
                }
            });
            return priceToleranceFrequency;
        }
        if (propertyId.equals("priceToleranceInterval")) {
            final ComboBox priceToleranceInterval = new ComboBox();
            CommonUtil.getComboBoxByListName(priceToleranceInterval, "PRICE_TOLERANCE_INTERVAL", false);
            priceToleranceInterval.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    priceToleranceInterval.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (priceToleranceInterval.getValue() != null && valueChange) {
                                    valueChange = false;
                                    Object itemStatusValue = priceToleranceInterval.getValue();
                                    HelperDTO helperDto = null;
                                    if (itemStatusValue != null) {
                                        helperDto = (HelperDTO) priceToleranceInterval.getValue();
                                    }
                                    AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                    dto.setPriceToleranceInterval(helperDto);
                                    dto.setColumnName("PRICE_TOLERANCE_INTERVAL");
                                    dto.setCaseNo(NumericConstants.ELEVEN);
                                    if (dto.getCheckRecord()) {
                                        populateLogic(priceToleranceInterval.getValue(), dto.getColumnName());
                                        saveTempPopulateItemDetails(dto);
                                    } else {
                                        saveTempItemDetails(dto);
                                    }
                                    valueChange = true;
                            }
                        }
                    });
                    priceToleranceInterval.removeFocusListener(this);
                }
            });

            return priceToleranceInterval;
        }
        if (propertyId.equals("basePrice")) {
            final CustomTextField basePrice = new CustomTextField();
            basePrice.addValidator(new RegexpValidator(ConstantsUtil.NUMERIC, StringConstantsUtil.ONLY_NUMERIC_CHARACTERS_CAN_BE_ENTERED));
            basePrice.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    basePrice.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (basePrice.getValue() != null && valueChange) {
                                    valueChange = false;
                                    AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                    dto.setBasePrice(basePrice.getValue());
                                    dto.setColumnName("BASE_PRICE");
                                    dto.setCaseNo(NumericConstants.THIRTEEN);
                                    if (dto.getCheckRecord()) {
                                        populateLogic(basePrice.getValue(), dto.getColumnName());
                                        saveTempPopulateItemDetails(dto);
                                    } else {
                                        saveTempItemDetails(dto);
                                    }
                                    valueChange = true;
                            }
                        }
                    });
                    basePrice.removeFocusListener(this);
                }
            });

            return basePrice;
        }
        if (propertyId.equals("price")) {
            final CustomTextField price = new CustomTextField();
            price.addValidator(new RegexpValidator(ConstantsUtil.NUMERIC, StringConstantsUtil.ONLY_NUMERIC_CHARACTERS_CAN_BE_ENTERED));
            price.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    price.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setPrice(price.getValue());
                                dto.setColumnName("PRICE");
                                dto.setCaseNo(NumericConstants.FIFTEEN);
                                if (dto.getCheckRecord()) {
                                    populateLogic(price.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }

                        }
                    });
                    price.removeFocusListener(this);
                }
            });
            return price;
        }
        if (propertyId.equals("contractPrice")) {
            final CustomTextField contractPrice = new CustomTextField();
            contractPrice.addValidator(new RegexpValidator(ConstantsUtil.NUMERIC, StringConstantsUtil.ONLY_NUMERIC_CHARACTERS_CAN_BE_ENTERED));
            contractPrice.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    contractPrice.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (contractPrice.getValue() != null && valueChange) {
                                    valueChange = false;
                                    AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                    dto.setContractPrice(contractPrice.getValue());
                                    dto.setColumnName("CONTRACT_PRICE");
                                    dto.setCaseNo(NumericConstants.EIGHTEEN);
                                    if (dto.getCheckRecord()) {
                                        populateLogic(contractPrice.getValue(), dto.getColumnName());
                                        saveTempPopulateItemDetails(dto);
                                    } else {
                                        saveTempItemDetails(dto);
                                    }
                                    valueChange = true;

                            }
                        }
                    });
                    contractPrice.removeFocusListener(this);
                }
            });

            return contractPrice;
        }

        if (propertyId.equals("RSStartDate")) {
            final PopupDateField rsstartDate = new PopupDateField();
            rsstartDate.setRequired(true);
            rsstartDate.addStyleName(ConstantsUtil.ALIGN_CENTER);
            rsstartDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
            rsstartDate.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    Property.ValueChangeListener valueChangeListner = new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setStartDate(rsstartDate.getValue());
                                dto.setColumnName("ITEM_REBATE_START_DATE");
                                dto.setCaseNo(NumericConstants.NINETEEN);
                                if (dto.getCheckRecord()) {
                                    populateLogic(rsstartDate.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }
                        }
                    };
                    rsstartDate.addValueChangeListener(valueChangeListner);
                    valueChangeListner.valueChange(null);
                    rsstartDate.removeFocusListener(this);
                }
            });

            return rsstartDate;
        }
        if (propertyId.equals("RSEndDate")) {
            final PopupDateField rsendDate = new PopupDateField();
            rsendDate.addStyleName(ConstantsUtil.ALIGN_CENTER);
            rsendDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
            rsendDate.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    Property.ValueChangeListener valueChangeListner = new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setCaseNo(0);
                                Date startDate = abstractLogic.getStartDateCheck(dto, selection, "ITEM_REBATE_START_DATE");
                                if (startDate != null && rsendDate.getValue() != null && rsendDate.getValue().before(startDate)) {
                                    rsendDate.setValue(null);
                                    MessageBox.showPlain(Icon.ERROR, StringConstantsUtil.START_DATE_HEADER, "You cannot proceed with this RS End Date since it does not come after the RS Start Date.", ButtonId.OK);
                                } else {
                                    dto.setEndDate(rsendDate.getValue());
                                    dto.setColumnName("ITEM_REBATE_END_DATE");
                                    dto.setCaseNo(NumericConstants.TWENTY);
                                    if (dto.getCheckRecord()) {
                                        populateLogic(rsendDate.getValue(), dto.getColumnName());
                                        saveTempPopulateItemDetails(dto);
                                    } else {
                                        saveTempItemDetails(dto);
                                    }
                                }
                                valueChange = true;
                            }
                        }
                    };
                    rsendDate.addValueChangeListener(valueChangeListner);
                    valueChangeListner.valueChange(null);
                    rsendDate.removeFocusListener(this);
                }
            });

            return rsendDate;
        }
        if (propertyId.equals("formulaId")) {
            final CustomTextField formulaId = new CustomTextField();
            formulaId.setReadOnly(true);
            formulaId.addStyleName(Constants.SEARCH_ICON);
            formulaId.addClickListener(new CustomTextField.ClickListener() {
                public void click(CustomTextField.ClickEvent event) {
                    FormulaLookUp formulaLookUp = new FormulaLookUp(formulaId);
                    formulaLookUp.addCloseListener(new Window.CloseListener() {
                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            formulaId.setReadOnly(false);
                            if (formulaId.getData() != null) {
                                FormulaDTO object = (FormulaDTO) formulaId.getData();
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setTempSid(object.getFormulaSid());
                                dto.setColumnName("FORMULA_ID");
                                dto.setCaseNo(NumericConstants.TWENTY_FOUR);
                                formulaId.setValue(object.getFormulaNo());
                                if (dto.getCheckRecord()) {
                                    populateLogic(formulaId.getValue(), "FORMULA_ID");
                                    saveTempPopulateItemDetails(dto);
                                }

                            }
                            formulaId.setReadOnly(true);
                        }
                    });
                    UI.getCurrent().addWindow(formulaLookUp);
                }
            });
            formulaId.setReadOnly(true);
            return formulaId;
        }
        if (propertyId.equals("rebatePlan")) {
            final CustomTextField rebatePlan = new CustomTextField();
            rebatePlan.addStyleName(Constants.SEARCH_ICON);
            rebatePlan.setReadOnly(true);
            rebatePlan.addClickListener(new CustomTextField.ClickListener() {
                public void click(CustomTextField.ClickEvent event) {
                    final ComponentLookUp contractNum = new ComponentLookUp("Rebate Plan", "Rebate Plan Lookup", rebatePlan);
                    contractNum.addCloseListener(new Window.CloseListener() {
                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            rebatePlan.setReadOnly(false);
                            if (rebatePlan.getData() != null) {
                                ComponentLookUpDTO object = (ComponentLookUpDTO) rebatePlan.getData();
                                rebatePlan.setValue(object.getComponentNo());
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setTempSid(Integer.valueOf(object.getMasterSid()));
                                dto.setColumnName("REBATE_PLAN_SYSTEM_ID");
                                dto.setCaseNo(NumericConstants.TWENTY_THREE);
                                if (dto.getCheckRecord()) {
                                    populateLogic(rebatePlan.getValue(), "REBATE_PLAN_SYSTEM_ID");
                                    saveTempPopulateItemDetails(dto);
                                }
                            }
                            rebatePlan.setReadOnly(true);
                        }
                    });

                    UI.getCurrent().addWindow(contractNum);
                }
            });
            rebatePlan.setReadOnly(true);
            return rebatePlan;
        }
        if (propertyId.equals("formulaMethodId")) {
            final CustomTextField formulaMethodId = new CustomTextField();
            formulaMethodId.addValidator(new RegexpValidator(ConstantsUtil.NUMERIC, StringConstantsUtil.ONLY_NUMERIC_CHARACTERS_CAN_BE_ENTERED));
            formulaMethodId.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    formulaMethodId.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (formulaMethodId.getValue() != null && valueChange) {
                                    valueChange = false;
                                    AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                    dto.setFormulaMethodId(formulaMethodId.getValue());
                                    dto.setColumnName("FORMULA_METHOD_ID");
                                    dto.setCaseNo(NumericConstants.TWENTY_TWO);
                                    if (dto.getCheckRecord()) {
                                        populateLogic(formulaMethodId.getValue(), dto.getColumnName());
                                        saveTempPopulateItemDetails(dto);
                                    } else {
                                        saveTempItemDetails(dto);
                                    }
                                    valueChange = true;

                            }
                        }
                    });
                    formulaMethodId.removeFocusListener(this);
                }
            });

            return formulaMethodId;
        }
        if (propertyId.equals("rebateAmount")) {
            final CustomTextField rebateAmount = new CustomTextField();
            rebateAmount.addValidator(new RegexpValidator(ConstantsUtil.NUMERIC, StringConstantsUtil.ONLY_NUMERIC_CHARACTERS_CAN_BE_ENTERED));
            rebateAmount.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    rebateAmount.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setRebateAmount(rebateAmount.getValue());
                                dto.setColumnName("REBATE_AMOUNT");
                                dto.setCaseNo(NumericConstants.TWENTY_ONE);
                                if (dto.getCheckRecord()) {
                                    populateLogic(rebateAmount.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }
                        }
                    });
                    rebateAmount.removeFocusListener(this);
                }
            });

            return rebateAmount;
        }
        
        
        if (Constants.PRICE_PROTECTION_STATUS_PROPERTY.equals(propertyId)) {
            final ComboBox priceProtectionStatus = new ComboBox();
            CommonUtil.loadComboBoxForGCM(priceProtectionStatus, Constants.STATUS, false);
            priceProtectionStatus.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    priceProtectionStatus.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                Object itemStatusValue = priceProtectionStatus.getValue();
                                HelperDTO helperDto = null;
                                if (itemStatusValue != null) {
                                    helperDto = (HelperDTO) priceProtectionStatus.getValue();
                                }
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setPriceProtectionStatus(helperDto);
                                dto.setColumnName(Constants.PRICE_PROTECTION_STATUS_COLUMN_NAME);
                                dto.setCaseNo(NumericConstants.TWENTY_SIX);
                                if (dto.getCheckRecord()) {
                                    populateLogic(priceProtectionStatus.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }
                        }
                    });
                    priceProtectionStatus.removeFocusListener(this);
                }
            });
            return priceProtectionStatus;
        }
        if (Constants.NEP_PROPERTY.equals(propertyId)) {
            final CustomTextField nep = new CustomTextField();
            nep.addValidator(new RegexpValidator(ConstantsUtil.NUMERIC, StringConstantsUtil.ONLY_NUMERIC_CHARACTERS_CAN_BE_ENTERED));
            nep.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    nep.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setNep(nep.getValue());
                                dto.setColumnName(Constants.NEP_COLUMN_NAME);
                                dto.setCaseNo(NumericConstants.TWENTY_FIVE);
                                if (dto.getCheckRecord()) {
                                    populateLogic(nep.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }
                        }
                    });
                    nep.removeFocusListener(this);
                }
            });
            return nep;
        }
        if (Constants.NEP_FORMULA_PROPERTY.equals(propertyId)) {
            final CustomTextField nepFormula = new CustomTextField();
            nepFormula.setReadOnly(true);
            nepFormula.addStyleName(Constants.SEARCH_ICON);
            nepFormula.addClickListener(new CustomTextField.ClickListener() {
                public void click(CustomTextField.ClickEvent event) {
                    NEPLookup formulaLookUp = new NEPLookup(nepFormula, Constants.NEP_FORMULA_LABLE_NAME);
                    formulaLookUp.addCloseListener(new Window.CloseListener() {
                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            nepFormula.setReadOnly(false);
                            if (nepFormula.getData() != null) {
                                FormulaDTO object = (FormulaDTO) nepFormula.getData();
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setTempSid(object.getFormulaSid());
                                dto.setNepFormula(object.getFormulaName());
                                dto.setColumnName(Constants.NEP_FORMULA_COLUMN_NAME);
                                dto.setCaseNo(NumericConstants.TWENTY_SEVEN);
                                nepFormula.setValue(object.getFormulaName());
                                if (dto.getCheckRecord()) {
                                    populateLogic(nepFormula.getValue(), Constants.NEP_FORMULA_COLUMN_NAME);
                                    saveTempPopulateItemDetails(dto);
                                }

                            }
                            nepFormula.setReadOnly(true);
                        }
                    });
                    UI.getCurrent().addWindow(formulaLookUp);
                }
            });
            nepFormula.setReadOnly(true);
            return nepFormula;
        }
        if (Constants.MAX_INCREMENTAL_CHANGE_PROPERTY.equals(propertyId)) {
            final CustomTextField maxIncrementalChange = new CustomTextField();
            maxIncrementalChange.addValidator(new RegexpValidator(ConstantsUtil.NUMERIC, StringConstantsUtil.ONLY_NUMERIC_CHARACTERS_CAN_BE_ENTERED));
            maxIncrementalChange.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    maxIncrementalChange.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setMaxIncrementalChange(maxIncrementalChange.getValue());
                                dto.setColumnName(Constants.MAX_INCREMENTAL_CHANGE_COLUMN_NAME);
                                dto.setCaseNo(NumericConstants.TWENTY_EIGHT);
                                if (dto.getCheckRecord()) {
                                    populateLogic(maxIncrementalChange.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }
                        }
                    });
                    maxIncrementalChange.removeFocusListener(this);
                }
            });
            return maxIncrementalChange;
        }
        if (Constants.RESET_ELIGIBLE_PROPERTY.equals(propertyId)) {
            final ComboBox resetEligible = new ComboBox();
            CommonUtil.loadComboBoxForGCM(resetEligible, Constants.LOCKED_STATUS_LISTNAME, false);
            resetEligible.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    resetEligible.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                Object value = resetEligible.getValue();
                                HelperDTO helperDto = null;
                                if (value != null) {
                                    helperDto = (HelperDTO) resetEligible.getValue();
                                }
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setResetEligible(helperDto);
                                dto.setColumnName(Constants.RESET_ELIGIBLE_COLUMN_NAME);
                                dto.setCaseNo(NumericConstants.TWENTY_NINE);
                                if (dto.getCheckRecord()) {
                                    populateLogic(resetEligible.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }
                        }
                    });
                    resetEligible.removeFocusListener(this);
                }
            });
            return resetEligible;
        }
        if (Constants.RESET_TYPE_PROPERTY.equals(propertyId)) {
            final ComboBox resetType = new ComboBox();
            CommonUtil.loadComboBoxForGCM(resetType, Constants.RESET_TYPE_LISTNAME, false);
            resetType.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    resetType.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                Object value = resetType.getValue();
                                HelperDTO helperDto = null;
                                if (value != null) {
                                    helperDto = (HelperDTO) resetType.getValue();
                                }
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setResetType(helperDto);
                                dto.setColumnName(Constants.RESET_TYPE_COLUMN_NAME);
                                dto.setCaseNo(NumericConstants.THIRTY);
                                if (dto.getCheckRecord()) {
                                    populateLogic(resetType.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }
                        }
                    });
                    resetType.removeFocusListener(this);
                }
            });
            return resetType;
        }

        if (Constants.RESET_DATE_PROPERTY.equals(propertyId)) {
            final PopupDateField resetDate = new PopupDateField();
            resetDate.setRequired(true);
            resetDate.addStyleName(ConstantsUtil.ALIGN_CENTER);
            resetDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
            resetDate.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    Property.ValueChangeListener valueChangeListner = new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setResetDate(resetDate.getValue());
                                dto.setColumnName(Constants.RESET_DATE_COLUMN_NAME);
                                dto.setCaseNo(NumericConstants.THIRTY_ONE);
                                if (dto.getCheckRecord()) {
                                    populateLogic(resetDate.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }
                        }
                    };
                    resetDate.addValueChangeListener(valueChangeListner);
                    valueChangeListner.valueChange(null);
                    resetDate.removeFocusListener(this);
                }
            });

            return resetDate;
        }

        if (Constants.RESET_INTERVAL_PROPERTY.equals(propertyId)) {
            final ComboBox resetInterval = new ComboBox();
            CommonUtil.loadComboBoxForGCM(resetInterval, StringConstantsUtil.PRICE_TOLERANCE_INTERVAL_LABEL, false);
            resetInterval.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    resetInterval.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                Object value = resetInterval.getValue();
                                HelperDTO helperDto = null;
                                if (value != null) {
                                    helperDto = (HelperDTO) resetInterval.getValue();
                                }
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setResetInterval(helperDto);
                                dto.setColumnName(Constants.RESET_INTERVAL_COLUMN_NAME);
                                dto.setCaseNo(NumericConstants.THIRTY_TWO);
                                if (dto.getCheckRecord()) {
                                    populateLogic(resetInterval.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }
                        }
                    });
                    resetInterval.removeFocusListener(this);
                }
            });
            return resetInterval;
        }
        if (Constants.RESET_FREQUENCY_PROPERTY.equals(propertyId)) {
            final ComboBox resetFrequency = new ComboBox();
            CommonUtil.loadComboBoxForGCM(resetFrequency, StringConstantsUtil.PRICE_TOLERANCE_FREQUENCY_LABEL, false);
            resetFrequency.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    resetFrequency.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                Object value = resetFrequency.getValue();
                                HelperDTO helperDto = null;
                                if (value != null) {
                                    helperDto = (HelperDTO) resetFrequency.getValue();
                                }
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setResetFrequency(helperDto);
                                dto.setColumnName(Constants.RESET_FREQUENCY_COLUMN_NAME);
                                dto.setCaseNo(NumericConstants.THIRTY_THREE);
                                if (dto.getCheckRecord()) {
                                    populateLogic(resetFrequency.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }
                        }
                    });
                    resetFrequency.removeFocusListener(this);
                }
            });
            return resetFrequency;
        }
        if (Constants.NET_PRICE_TYPE_PROPERTY.equals(propertyId)) {
            final ComboBox netPriceType = new ComboBox();
            CommonUtil.loadComboBoxForGCM(netPriceType, Constants.LOCKED_STATUS_LISTNAME, false);
            netPriceType.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    netPriceType.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                Object value = netPriceType.getValue();
                                HelperDTO helperDto = null;
                                if (value != null) {
                                    helperDto = (HelperDTO) netPriceType.getValue();
                                }
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setNetPriceType(helperDto);
                                dto.setColumnName(Constants.NET_PRICE_TYPE_COLUMN_NAME);
                                dto.setCaseNo(NumericConstants.THIRTY_FOUR);
                                if (dto.getCheckRecord()) {
                                    populateLogic(netPriceType.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }
                        }
                    });
                    netPriceType.removeFocusListener(this);
                }
            });
            return netPriceType;
        }
        if (Constants.NET_PRICE_TYPE_FORMULA_PROPERTY.equals(propertyId)) {
            final CustomTextField netPriceTypeFormula = new CustomTextField();
            netPriceTypeFormula.setReadOnly(true);
            netPriceTypeFormula.addStyleName(Constants.SEARCH_ICON);
            netPriceTypeFormula.addClickListener(new CustomTextField.ClickListener() {
                public void click(CustomTextField.ClickEvent event) {
                    NEPLookup formulaLookUp = new NEPLookup(netPriceTypeFormula, Constants.NET_PRICE_TYPE_FORMULA_LABLE_NAME);
                    formulaLookUp.addCloseListener(new Window.CloseListener() {
                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            netPriceTypeFormula.setReadOnly(false);
                            if (netPriceTypeFormula.getData() != null) {
                                FormulaDTO object = (FormulaDTO) netPriceTypeFormula.getData();
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setTempSid(object.getFormulaSid());
                                dto.setNetPriceTypeFormula(object.getFormulaName());
                                dto.setColumnName(Constants.NET_PRICE_TYPE_FORMULA_COLUMN_NAME);
                                dto.setCaseNo(NumericConstants.THIRTY_FIVE);
                                netPriceTypeFormula.setValue(object.getFormulaNo());
                                if (dto.getCheckRecord()) {
                                    populateLogic(netPriceTypeFormula.getValue(), Constants.NET_PRICE_TYPE_FORMULA_COLUMN_NAME);
                                    saveTempPopulateItemDetails(dto);
                                }

                            }
                            netPriceTypeFormula.setReadOnly(true);
                        }
                    });
                    UI.getCurrent().addWindow(formulaLookUp);
                }
            });
            netPriceTypeFormula.setReadOnly(true);
            return netPriceTypeFormula;
        }

        if (Constants.RESET_PRICE_TYPE_PROPERTY.equals(propertyId)) {
            final ComboBox resetPriceType = new ComboBox();
            final AbstractContractSearchDTO dto = loadPricetype(itemId, resetPriceType);
            resetPriceType.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    resetPriceType.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                Object value = resetPriceType.getValue();
                                dto.setResetPriceType((int) value);
                                dto.setColumnName(Constants.RESET_PRICE_TYPE_COLUMN_NAME);
                                dto.setCaseNo(NumericConstants.THIRTY_SIX);
                                if (dto.getCheckRecord()) {
                                    populateLogic(resetPriceType.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }
                        }
                    });
                    resetPriceType.removeFocusListener(this);
                }
            });
            return resetPriceType;
        }

        if (Constants.NET_RESET_PRICE_TYPE_PROPERTY.equals(propertyId)) {
            final ComboBox netResetPriceType = new ComboBox();
            CommonUtil.loadComboBoxForGCM(netResetPriceType, Constants.LOCKED_STATUS_LISTNAME, false);
            netResetPriceType.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    netResetPriceType.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                Object value = netResetPriceType.getValue();
                                HelperDTO helperDto = null;
                                if (value != null) {
                                    helperDto = (HelperDTO) netResetPriceType.getValue();
                                }

                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setNetResetPriceType(helperDto);
                                dto.setColumnName(Constants.NET_RESET_PRICE_TYPE_COLUMN_NAME);
                                dto.setCaseNo(NumericConstants.THIRTY_SEVEN);
                                if (dto.getCheckRecord()) {
                                    populateLogic(netResetPriceType.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }
                        }
                    });
                    netResetPriceType.removeFocusListener(this);
                }
            });
            return netResetPriceType;
        }
        if ("netResetPriceFormula".equals(propertyId)) {
            final CustomTextField netResetPriceFormulaId = new CustomTextField();
            netResetPriceFormulaId.setReadOnly(true);
            netResetPriceFormulaId.addStyleName(Constants.SEARCH_ICON);
            netResetPriceFormulaId.addClickListener(new CustomTextField.ClickListener() {
                public void click(CustomTextField.ClickEvent event) {
                    NEPLookup formulaLookUp = new NEPLookup(netResetPriceFormulaId, Constants.NET_RESET_PRICE_FORMULA_LABLE_NAME);
                    formulaLookUp.addCloseListener(new Window.CloseListener() {
                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            netResetPriceFormulaId.setReadOnly(false);
                            if (netResetPriceFormulaId.getData() != null) {
                                FormulaDTO object = (FormulaDTO) netResetPriceFormulaId.getData();
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setTempSid(object.getFormulaSid());
                                dto.setNetResetPriceFormula(object.getFormulaName());
                                dto.setColumnName(Constants.NET_RESET_PRICE_FORMULA_COLUMN_NAME);
                                dto.setCaseNo(NumericConstants.THIRTY_EIGHT);
                                netResetPriceFormulaId.setValue(object.getFormulaNo());
                                if (dto.getCheckRecord()) {
                                    populateLogic(netResetPriceFormulaId.getValue(), Constants.NET_RESET_PRICE_FORMULA_COLUMN_NAME);
                                    saveTempPopulateItemDetails(dto);
                                }

                            }
                            netResetPriceFormulaId.setReadOnly(true);
                        }
                    });
                    UI.getCurrent().addWindow(formulaLookUp);
                }
            });
            netResetPriceFormulaId.setReadOnly(true);
            return netResetPriceFormulaId;
        }

        if (Constants.BASE_PRICE_PROPERTY.equals(propertyId)) {
            final ComboBox basePriceType = new ComboBox();
            CommonUtil.loadComboBoxForGCM(basePriceType, Constants.BASE_PRICE_TYPE_LISTNAME, false);
            basePriceType.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    basePriceType.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                Object value = basePriceType.getValue();
                                HelperDTO helperDto = null;
                                if (value != null) {
                                    helperDto = (HelperDTO) basePriceType.getValue();
                                }
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setBasePriceType(helperDto);
                                dto.setColumnName(Constants.BASE_PRICE_TYPE_COLUMN_NAME);
                                dto.setCaseNo(NumericConstants.THIRTY_NINE);
                                if (dto.getCheckRecord()) {
                                    populateLogic(basePriceType.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                                contractSelectionTable.getContainerLogic().setCurrentPage(1);
                                contractSelectionTable.setRefresh(true);
                            }
                        }
                    });
                    basePriceType.removeFocusListener(this);
                }
            });
            return basePriceType;
        }
        if (Constants.BASELINE_WAC_PROPERTY.equals(propertyId)) {
            AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
            if (Constants.MANUAL_LABLE_NAME.equals(dto.getBasePriceType().getDescription().trim())) {
                final TextField baseLineWacManual = new TextField();
                baseLineWacManual.addValidator(new RegexpValidator(ConstantsUtil.NUMERIC, StringConstantsUtil.ONLY_NUMERIC_CHARACTERS_CAN_BE_ENTERED));
                baseLineWacManual.addBlurListener(new BlurListener() {
                    @Override
                    public void blur(FieldEvents.BlurEvent event) {
                          AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                        dto.setBaseLineWacManual(baseLineWacManual.getValue());
                        dto.setColumnName(Constants.BASELINE_WAC_MANUAL_COLUMN_NAME);
                        dto.setCaseNo(NumericConstants.FORTY_SEVEN);
                        if (dto.getCheckRecord()) {
                            populateLogic(baseLineWacManual.getValue(), dto.getColumnName());
                            saveTempPopulateItemDetails(dto);
                        } else {
                            saveTempItemDetails(dto);
                        }
                    }
                });
                return baseLineWacManual;
            } else if (Constants.DATE_LABLE_NAME.equals(dto.getBasePriceType().getDescription())) {
                final PopupDateField baseLineWacResetDate = new PopupDateField();
                baseLineWacResetDate.setRequired(true);
                baseLineWacResetDate.addStyleName(ConstantsUtil.ALIGN_CENTER);
                baseLineWacResetDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
                baseLineWacResetDate.addBlurListener(new BlurListener() {
                    @Override
                    public void blur(FieldEvents.BlurEvent event) {
                        AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                        dto.setBaseLineWacDate(baseLineWacResetDate.getValue());
                        dto.setColumnName(Constants.BASELINE_WAC_DATE_COLUMN_NAME);
                        dto.setCaseNo(NumericConstants.FORTY_EIGHT);
                        if (dto.getCheckRecord()) {
                            populateLogic(baseLineWacResetDate.getValue(), dto.getColumnName());
                            saveTempPopulateItemDetails(dto);
                        } else {
                            saveTempItemDetails(dto);
                        }
                    }
                });
                return baseLineWacResetDate;
            } else if (Constants.PRICE_TYPE_LABEL.equals(dto.getBasePriceType().getDescription())) {
                final ComboBox baseLineWacPriceType = new ComboBox();
                final AbstractContractSearchDTO dtoPriceType = loadPricetype(itemId, baseLineWacPriceType);
                baseLineWacPriceType.addBlurListener(new BlurListener() {
                    @Override
                    public void blur(FieldEvents.BlurEvent event) {
                        Object value = baseLineWacPriceType.getValue();
                        dtoPriceType.setBaseLineWacPriceType((int) value);
                        dtoPriceType.setColumnName(Constants.BASELINE_WAC_PRICE_TYPE_COLUMN_NAME);
                        dtoPriceType.setCaseNo(NumericConstants.FORTY_NINE);
                        if (dtoPriceType.getCheckRecord()) {
                            populateLogic(baseLineWacPriceType.getValue(), dtoPriceType.getColumnName());
                            saveTempPopulateItemDetails(dtoPriceType);
                        } else {
                            saveTempItemDetails(dtoPriceType);
                        }
                    }
                });
                return baseLineWacPriceType;
            }
        }

        if (Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_PROPERTY.equals(propertyId)) {
            final ComboBox subsequentPeriodPriceType = new ComboBox();
            final AbstractContractSearchDTO dto = loadPricetype(itemId, subsequentPeriodPriceType);
            subsequentPeriodPriceType.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    subsequentPeriodPriceType.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                Object value = subsequentPeriodPriceType.getValue();
                                dto.setSubsequentPeriodPriceType((int) value);
                                dto.setColumnName(Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_COLUMN_NAME);
                                dto.setCaseNo(NumericConstants.FORTY);
                                if (dto.getCheckRecord()) {
                                    populateLogic(subsequentPeriodPriceType.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }
                        }
                    });
                    subsequentPeriodPriceType.removeFocusListener(this);
                }
            });
            return subsequentPeriodPriceType;
        }
        if (Constants.NET_SUBSEQUENT_PERIOD_PRICE_PROPERTY.equals(propertyId)) {
            final ComboBox netSubsequentPeriodPrice = new ComboBox();
            CommonUtil.loadComboBoxForGCM(netSubsequentPeriodPrice, Constants.LOCKED_STATUS_LISTNAME, false);
            netSubsequentPeriodPrice.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    netSubsequentPeriodPrice.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                Object value = netSubsequentPeriodPrice.getValue();
                                HelperDTO helperDto = null;
                                if (value != null) {
                                    helperDto = (HelperDTO) netSubsequentPeriodPrice.getValue();
                                }

                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setNetSubsequentPeriodPrice(helperDto);
                                dto.setColumnName(Constants.NET_SUBSEQUENT_PERIOD_PRICE_COLUMN_NAME);
                                dto.setCaseNo(NumericConstants.FORTY_ONE);
                                if (dto.getCheckRecord()) {
                                    populateLogic(netSubsequentPeriodPrice.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }
                        }
                    });
                    netSubsequentPeriodPrice.removeFocusListener(this);
                }
            });
            return netSubsequentPeriodPrice;
        }

        if ("netSubsequentPeriodPriceFormula".equals(propertyId)) {
            final CustomTextField netSubsequentPriceFormulaId = new CustomTextField();
            netSubsequentPriceFormulaId.setReadOnly(true);
            netSubsequentPriceFormulaId.addStyleName(Constants.SEARCH_ICON);
            netSubsequentPriceFormulaId.addClickListener(new CustomTextField.ClickListener() {
                public void click(CustomTextField.ClickEvent event) {
                    NEPLookup formulaLookUp = new NEPLookup(netSubsequentPriceFormulaId, Constants.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA_LABLE_NAME);
                    formulaLookUp.addCloseListener(new Window.CloseListener() {
                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            netSubsequentPriceFormulaId.setReadOnly(false);
                            if (netSubsequentPriceFormulaId.getData() != null) {
                                FormulaDTO object = (FormulaDTO) netSubsequentPriceFormulaId.getData();
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setTempSid(object.getFormulaSid());
                                dto.setNetSubsequentPeriodPriceFormula(object.getFormulaName());
                                
                                dto.setColumnName(Constants.NET_SUBSEQUENT_PRICE_FORMULA_COLUMN_NAME);
                                dto.setCaseNo(NumericConstants.FORTY_TWO);
                                netSubsequentPriceFormulaId.setValue(object.getFormulaName());
                                if (dto.getCheckRecord()) {
                                    populateLogic(netSubsequentPriceFormulaId.getValue(), Constants.NET_SUBSEQUENT_PRICE_FORMULA_COLUMN_NAME);
                                    saveTempPopulateItemDetails(dto);
                                }

                            }
                            netSubsequentPriceFormulaId.setReadOnly(true);
                        }
                    });
                    UI.getCurrent().addWindow(formulaLookUp);
                }
            });
            netSubsequentPriceFormulaId.setReadOnly(true);
            return netSubsequentPriceFormulaId;
        }
        if (Constants.NET_BASELINE_WAC_FORMULA_PROPERTY.equals(propertyId)) {
            final CustomTextField netBaselineWacFormulaId = new CustomTextField();
            netBaselineWacFormulaId.setReadOnly(true);
            netBaselineWacFormulaId.addStyleName(Constants.SEARCH_ICON);
            netBaselineWacFormulaId.addClickListener(new CustomTextField.ClickListener() {
                public void click(CustomTextField.ClickEvent event) {
                    NEPLookup formulaLookUp = new NEPLookup(netBaselineWacFormulaId, Constants.NET_BASELINE_WAC_FORMULA_LABLE_NAME);
                    formulaLookUp.addCloseListener(new Window.CloseListener() {
                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            netBaselineWacFormulaId.setReadOnly(false);
                            if (netBaselineWacFormulaId.getData() != null) {
                                FormulaDTO object = (FormulaDTO) netBaselineWacFormulaId.getData();
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setTempSid(object.getFormulaSid());
                                dto.setNetBaselineWACFormula(object.getFormulaName());
                                dto.setColumnName(Constants.NET_BASELINE_WAC_FORMULA_COLUMN_NAME);
                                dto.setCaseNo(NumericConstants.FORTY_THREE);
                                netBaselineWacFormulaId.setValue(object.getFormulaNo());
                                if (dto.getCheckRecord()) {
                                    populateLogic(netBaselineWacFormulaId.getValue(), Constants.NET_BASELINE_WAC_FORMULA_COLUMN_NAME);
                                    saveTempPopulateItemDetails(dto);
                                }

                            }
                            netBaselineWacFormulaId.setReadOnly(true);
                        }
                    });
                    UI.getCurrent().addWindow(formulaLookUp);
                }
            });
            netBaselineWacFormulaId.setReadOnly(true);
            return netBaselineWacFormulaId;
        }
        if (Constants.BASELINE_NET_WAC_PROPERTY.equals(propertyId)) {
            final ComboBox baselineNetWAC = new ComboBox();
            CommonUtil.loadComboBoxForGCM(baselineNetWAC, Constants.LOCKED_STATUS_LISTNAME, false);
            baselineNetWAC.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    baselineNetWAC.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                Object value = baselineNetWAC.getValue();
                                HelperDTO helperDto = null;
                                if (value != null) {
                                    helperDto = (HelperDTO) baselineNetWAC.getValue();
                                }

                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setBaselineNetWAC(helperDto);
                                dto.setColumnName(Constants.BASELINE_NET_WAC_COLUMN_NAME);
                                dto.setCaseNo(NumericConstants.FORTY_FOUR);
                                if (dto.getCheckRecord()) {
                                    populateLogic(baselineNetWAC.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }
                        }
                    });
                    baselineNetWAC.removeFocusListener(this);
                }
            });
            return baselineNetWAC;
        }
        if (Constants.PRICE_TYPE_PROPERTY.equals(propertyId)) {
            final ComboBox priceType = new ComboBox();
            final AbstractContractSearchDTO dto = loadPricetype(itemId, priceType);
            priceType.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    priceType.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                Object value = priceType.getValue();
                                dto.setPriceType((int) value);
                                dto.setColumnName(Constants.PRICE_TYPE_COLUMN_NAME);
                                dto.setCaseNo(NumericConstants.FORTY_FIVE);
                                if (dto.getCheckRecord()) {
                                    populateLogic(priceType.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }
                        }
                    });
                    priceType.removeFocusListener(this);
                }
            });
            return priceType;
        }

        if (Constants.MEASUREMENT_PRICE_PROPERTY.equals(propertyId)) {
            final ComboBox measurementPrice = new ComboBox();
            final AbstractContractSearchDTO dto = loadPricetype(itemId, measurementPrice);
            measurementPrice.addFocusListener(new FocusListener() {
                public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                    measurementPrice.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                Object value = measurementPrice.getValue();
                                dto.setMeasurementPrice((int) value);
                                dto.setColumnName(Constants.MEASUREMENT_PRICE_COLUMN_NAME);
                                dto.setCaseNo(NumericConstants.FORTY_SIX);
                                if (dto.getCheckRecord()) {
                                    populateLogic(measurementPrice.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }
                        }
                    });
                    measurementPrice.removeFocusListener(this);
                }
            });
            return measurementPrice;
        }
        return null;
    }

    public AbstractContractSearchDTO loadPricetype(final Object itemId, final ComboBox name) {
        final AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
        AddContractSelection.loadPriceType(name, false);
        return dto;
    }

    private void saveTempItemDetails(final AbstractContractSearchDTO dto) {
        boolean flag = logic.getEditedItemDetails(dto, selection);
        if (!flag) {
            MessageBox.showPlain(Icon.ERROR, "Error", "Please Type / Select valid input.Then try again", ButtonId.OK);
            return;
        }
    }

    private void saveTempPopulateItemDetails(final AbstractContractSearchDTO dto) {
        boolean flag = logic.getEditedPopulateItemDetails(dto, selection);
        if (!flag) {
            MessageBox.showPlain(Icon.ERROR, "Error", "Please Type / Select valid input.Then try again", ButtonId.OK);
            return;
        }
    }

    public void populateLogic(Object massUpdateString, Object columnName) {
        Collection itemId = contractSelectionTable.getItemIds();
        for (Object object : itemId) {
            AbstractContractSearchDTO dto = (AbstractContractSearchDTO) object;
            if (dto.getCheckRecord()) {
                contractSelectionTable.getItem(object).getItemProperty(tempTableMap.get(columnName)).setValue(massUpdateString);
            }
        }
    }
}
