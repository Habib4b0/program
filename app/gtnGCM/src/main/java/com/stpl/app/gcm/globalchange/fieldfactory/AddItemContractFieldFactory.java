/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.globalchange.fieldfactory;

import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.add.dto.AddItemTableDTO;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.AbstractContractSearchDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentLookUpDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.FormulaDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.ComponentLookUp;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.FormulaLookUp;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.util.Constants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TableFieldFactory;
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

    List<AddItemTableDTO> selectedItemList = new ArrayList<AddItemTableDTO>();
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
            check.setImmediate(true);
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
            itemStatus.setImmediate(true);
            abstractLogic.loaDDLB(itemStatus, "STATUS", "HELPER_TABLE", false, "helperTableQuery");
            itemStatus.addFocusListener(new FieldEvents.FocusListener() {
                public void focus(FieldEvents.FocusEvent event) {

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
            itemstartDate.setImmediate(true);
            itemstartDate.setRequired(true);
            itemstartDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
            itemstartDate.addStyleName(ConstantsUtil.ALIGN_CENTER);

            itemstartDate.addFocusListener(new FieldEvents.FocusListener() {
                public void focus(FieldEvents.FocusEvent event) {
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
                                        MessageBox.showPlain(Icon.ERROR, "Start Date cannot come before the End Date", "You cannot proceed with this Item Start Date since it does not come after the End Date you have entered on the previous screen.", ButtonId.OK);
                                    } else {
                                        MessageBox.showPlain(Icon.ERROR, "Start Date cannot come before the End Date", "You cannot proceed with this Item Start Date since it does not come after the Item End Date.", ButtonId.OK);
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
            itemendDate.setImmediate(true);
            itemendDate.addStyleName(ConstantsUtil.ALIGN_CENTER);
            itemendDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
            itemendDate.addFocusListener(new FieldEvents.FocusListener() {
                public void focus(FieldEvents.FocusEvent event) {
                    Property.ValueChangeListener valueChangeListner = new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setCaseNo(0);
                                Date startDate = dto.getStartDate();
                                if (startDate != null && itemendDate.getValue() != null && itemendDate.getValue().before(startDate)) {
                                    itemendDate.setValue(null);
                                    MessageBox.showPlain(Icon.ERROR, "Start Date cannot come before the End Date", "You cannot proceed with this Item End Date since it does not come after the Start Date.", ButtonId.OK);
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
            cpstartDate.setImmediate(true);
            cpstartDate.addStyleName(ConstantsUtil.ALIGN_CENTER);
            cpstartDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
            cpstartDate.addFocusListener(new FieldEvents.FocusListener() {
                public void focus(FieldEvents.FocusEvent event) {
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
            cpendDate.setImmediate(true);
            cpendDate.addStyleName(ConstantsUtil.ALIGN_CENTER);
            cpendDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
            cpendDate.addFocusListener(new FieldEvents.FocusListener() {
                public void focus(FieldEvents.FocusEvent event) {
                    Property.ValueChangeListener valueChangeListner = new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setCaseNo(0);
                                Date startDate = abstractLogic.getStartDateCheck(dto, selection, "CONTRACT_PRICE_START_DATE");
                                if (startDate != null && cpendDate.getValue() != null && cpendDate.getValue().before(startDate)) {
                                    cpendDate.setValue(null);
                                    MessageBox.showPlain(Icon.ERROR, "Start Date cannot come before the End Date", "You cannot proceed with this CP End Date since it does not come after the CP Start Date.", ButtonId.OK);
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
            priceProtectionStartDate.setImmediate(true);
            priceProtectionStartDate.addStyleName(ConstantsUtil.ALIGN_CENTER);
            priceProtectionStartDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
            priceProtectionStartDate.addFocusListener(new FieldEvents.FocusListener() {
                public void focus(FieldEvents.FocusEvent event) {
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
            priceProtectionEndDate.setImmediate(true);
            priceProtectionEndDate.addStyleName(ConstantsUtil.ALIGN_CENTER);
            priceProtectionEndDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
            priceProtectionEndDate.addFocusListener(new FieldEvents.FocusListener() {
                public void focus(FieldEvents.FocusEvent event) {
                    Property.ValueChangeListener valueChangeListner = new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setCaseNo(0);
                                Date startDate = abstractLogic.getStartDateCheck(dto, selection, "PRICE_PROTECTION_START_DATE");
                                if (startDate != null && priceProtectionEndDate.getValue() != null && priceProtectionEndDate.getValue().before(startDate)) {
                                    priceProtectionEndDate.setValue(null);
                                    MessageBox.showPlain(Icon.ERROR, "Start Date cannot come before the End Date", "You cannot proceed with this Price Protection End Date since it does not come after the Price Protection Start Date.", ButtonId.OK);
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
            priceToleranceType.setImmediate(true);
            abstractLogic.loaDDLB(priceToleranceType, "PRICE_TOLERANCE_TYPE", "HELPER_TABLE", false, "helperTableQuery");
            priceToleranceType.addFocusListener(new FieldEvents.FocusListener() {
                public void focus(FieldEvents.FocusEvent event) {
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
            priceTolerance.setImmediate(true);
            priceTolerance.addFocusListener(new FieldEvents.FocusListener() {
                public void focus(FieldEvents.FocusEvent event) {
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
            priceTolerance.addValidator(
                    new RegexpValidator(ConstantsUtil.NUMERIC, "Only Numeric characters can be entered"));

            return priceTolerance;

        }

        if (propertyId.equals("priceToleranceFrequency")) {
            final ComboBox priceToleranceFrequency = new ComboBox();
            priceToleranceFrequency.setImmediate(true);
            abstractLogic.loaDDLB(priceToleranceFrequency, "PRICE_TOLERANCE_FREQUENCY", "HELPER_TABLE", false, "helperTableQuery");
            priceToleranceFrequency.addFocusListener(new FieldEvents.FocusListener() {
                public void focus(FieldEvents.FocusEvent event) {
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
            priceToleranceInterval.setImmediate(true);
            abstractLogic.loaDDLB(priceToleranceInterval, "PRICE_TOLERANCE_INTERVAL", "HELPER_TABLE", false, "helperTableQuery");
            priceToleranceInterval.addFocusListener(new FieldEvents.FocusListener() {
                public void focus(FieldEvents.FocusEvent event) {
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
            basePrice.setImmediate(true);
            basePrice.addValidator(new RegexpValidator(ConstantsUtil.NUMERIC, "Only Numeric characters can be entered"));
            basePrice.addFocusListener(new FieldEvents.FocusListener() {
                public void focus(FieldEvents.FocusEvent event) {
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
            final CustomTextField Price = new CustomTextField();
            Price.setImmediate(true);
            Price.addValidator(new RegexpValidator(ConstantsUtil.NUMERIC, "Only Numeric characters can be entered"));
            Price.addFocusListener(new FieldEvents.FocusListener() {
                public void focus(FieldEvents.FocusEvent event) {
                    Price.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setPrice(Price.getValue());
                                dto.setColumnName("PRICE");
                                dto.setCaseNo(NumericConstants.FIFTEEN);
                                if (dto.getCheckRecord()) {
                                    populateLogic(Price.getValue(), dto.getColumnName());
                                    saveTempPopulateItemDetails(dto);
                                } else {
                                    saveTempItemDetails(dto);
                                }
                                valueChange = true;
                            }

                        }
                    });
                    Price.removeFocusListener(this);
                }
            });
            return Price;
        }
        if (propertyId.equals("contractPrice")) {
            final CustomTextField Contractprice = new CustomTextField();
            Contractprice.setImmediate(true);
            Contractprice.addValidator(new RegexpValidator(ConstantsUtil.NUMERIC, "Only Numeric characters can be entered"));
            Contractprice.addFocusListener(new FieldEvents.FocusListener() {
                public void focus(FieldEvents.FocusEvent event) {
                    Contractprice.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (Contractprice.getValue() != null && valueChange) {
                                    valueChange = false;
                                    AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                    dto.setContractPrice(Contractprice.getValue());
                                    dto.setColumnName("CONTRACT_PRICE");
                                    dto.setCaseNo(NumericConstants.EIGHTEEN);
                                    if (dto.getCheckRecord()) {
                                        populateLogic(Contractprice.getValue(), dto.getColumnName());
                                        saveTempPopulateItemDetails(dto);
                                    } else {
                                        saveTempItemDetails(dto);
                                    }
                                    valueChange = true;

                            }
                        }
                    });
                    Contractprice.removeFocusListener(this);
                }
            });

            return Contractprice;
        }

        if (propertyId.equals("RSStartDate")) {
            final PopupDateField rsstartDate = new PopupDateField();
            rsstartDate.setImmediate(true);
            rsstartDate.setRequired(true);
            rsstartDate.addStyleName(ConstantsUtil.ALIGN_CENTER);
            rsstartDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
            rsstartDate.addFocusListener(new FieldEvents.FocusListener() {
                public void focus(FieldEvents.FocusEvent event) {
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
            rsendDate.setImmediate(true);
            rsendDate.addFocusListener(new FieldEvents.FocusListener() {
                public void focus(FieldEvents.FocusEvent event) {
                    Property.ValueChangeListener valueChangeListner = new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (valueChange) {
                                valueChange = false;
                                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                dto.setCaseNo(0);
                                Date startDate = abstractLogic.getStartDateCheck(dto, selection, "ITEM_REBATE_START_DATE");
                                if (startDate != null && rsendDate.getValue() != null && rsendDate.getValue().before(startDate)) {
                                    rsendDate.setValue(null);
                                    MessageBox.showPlain(Icon.ERROR, "Start Date cannot come before the End Date", "You cannot proceed with this RS End Date since it does not come after the RS Start Date.", ButtonId.OK);
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
            formulaId.setImmediate(true);
            formulaId.setReadOnly(true);
            formulaId.addStyleName("searchicon");
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
            rebatePlan.setImmediate(true);
            rebatePlan.addStyleName("searchicon");
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
            formulaMethodId.setImmediate(true);
            formulaMethodId.addValidator(new RegexpValidator(ConstantsUtil.NUMERIC, "Only Numeric characters can be entered"));
            formulaMethodId.addFocusListener(new FieldEvents.FocusListener() {
                public void focus(FieldEvents.FocusEvent event) {
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
            rebateAmount.setImmediate(true);
            rebateAmount.addValidator(new RegexpValidator(ConstantsUtil.NUMERIC, "Only Numeric characters can be entered"));
            rebateAmount.addFocusListener(new FieldEvents.FocusListener() {
                public void focus(FieldEvents.FocusEvent event) {
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
        return null;
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

    public void populateLogic(Object massUpdateString, Object ColumnName) {
        Collection itemId = contractSelectionTable.getItemIds();
        for (Object object : itemId) {
            AbstractContractSearchDTO dto = (AbstractContractSearchDTO) object;
            if (dto.getCheckRecord()) {
                contractSelectionTable.getItem(object).getItemProperty(tempTableMap.get(ColumnName)).setValue(massUpdateString);
            }
        }
    }
}
