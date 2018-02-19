
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.add.form;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.globalchange.fieldfactory.AddItemContractFieldFactory;
import com.stpl.app.gcm.itemmanagement.add.dto.AddItemTableDTO;
import com.stpl.app.gcm.itemmanagement.add.logic.tablelogic.AddItemDetailsTableLogic;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import com.stpl.app.gcm.itemmanagement.index.form.ItemManagementLookup;
import com.stpl.app.gcm.itemmanagement.index.logic.ItemLogic;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.AbstractContractSearchDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentLookUpDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.FormulaDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.AbstractAllItemLookup;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.AbstractComponentInfo;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.ComponentLookUp;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.NEPLookup;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.lookup.CFPLookUp;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.lookup.IFPLookUp;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.lookup.PSLookUp;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.lookup.RSLookUp;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.DISABLE;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;

/**
 *
 * @author srithar
 */
public class AddContractSelection extends CustomComponent {

    @UiField("itemNo")
    private TextField itemNo;
    @UiField("itemName")
    private TextField itemName;
    @UiField("itemDesc")
    private TextField itemDesc;
    @UiField("therapeuticClass")
    private TextField therapeuticClass;
    @UiField("brand")
    private TextField brand;
    @UiField(Constants.CONTRACT_HOLDER)
    private CustomTextField vCntHolder;
    @UiField("ifp")
    private CustomTextField ifp;
    @UiField("cfp")
    private CustomTextField cfp;
    @UiField("contractNo")
    private CustomTextField vCntNo;
    @UiField("contractName")
    private CustomTextField vCntName;
    @UiField("priceSchedule")
    private CustomTextField vPriceSchdle;
    @UiField("customerNo")
    private CustomTextField vCustNo;
    @UiField("customerName")
    private CustomTextField vCustName;
    @UiField("rebateSchedule")
    private CustomTextField rsSchedule;
    @UiField("number")
    private TextField number;
    @UiField("massUpdateValue")
    private ComboBox massUpdateValue;
    @UiField("marketType")
    private ComboBox marketType_DTO;
    @UiField("typeDdlb")
    private ComboBox typeDdlb;
    @UiField("field")
    private ComboBox field;
    
    @UiField("massStartDate")
    private PopupDateField massStartDate;
    @UiField("massEndDate")
    private PopupDateField massEndDate;
    @UiField("massUpdateRadio")
    private OptionGroup massUpdateRadio;
    @UiField("searchBtn")
    private Button searchBtn;
    @UiField("populateBtn")
    private Button populateBtn;
    @UiField("resetBtncur")
    private Button resetBtncur;
    @UiField("resetBtn")
    private Button resetBtn;
    @UiField("submitBtncur")
    private Button submitBtncur;
    @UiField("contractVertical")
    private VerticalLayout contractVertical;
    
    @UiField("allItemsCombo")
    private ComboBox allItemsCombo;
    
    @UiField("componentInfoLayout")
    private VerticalLayout componentInfoLayout;
    @UiField("startdatelabel")
    private Label startdatelabel;
    @UiField("enddatelabel")
    private Label enddatelabel;
    @UiField("valuelabel")
    private Label valuelabel;
    @UiField("baseWacPriceType")
    private ComboBox baseWacPriceType;
    @UiField("baseWacManual")
    protected TextField baseWacManual;
    @UiField("baseWacDate")
    private PopupDateField baseWacDate;
    private static final Logger LOGGER = LoggerFactory.getLogger(AddContractSelection.class);
    private final AddItemDetailsTableLogic addItemTableLogic = new AddItemDetailsTableLogic();
    
    private final ExtPagedTable addItemTable = new ExtPagedTable(addItemTableLogic);
    private static final String SEARCHICON = "searchicon";
    private List<ItemIndexDto> selecteditemList;
    
    private final Object[] addColumn = {Constants.CHECK_RECORD, "projectionIdLink", "workFlowStatus", Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO,
        Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, Constants.STATUS_S, "itemStartDate",
        "itemEndDate", StringConstantsUtil.CP_START_DATE, StringConstantsUtil.CP_END_DATE, Constants.PRICE_TYPE_PROPERTY,
        StringConstantsUtil.PRICE_PROPERTY, Constants.PRICE_PROTECTION_STATUS_PROPERTY, Constants.PRICE_PROTECTION_START_DATE_PROPERTY, Constants.PRICE_PROTECTION_END_DATE_PROPERTY,
        Constants.MEASUREMENT_PRICE_PROPERTY, Constants.NEP_PROPERTY, Constants.NEP_FORMULA_PROPERTY, Constants.BASE_PRICE_PROPERTY,Constants.BASELINE_WAC_PROPERTY,
        Constants.BASELINE_NET_WAC_PROPERTY, Constants.NET_BASELINE_WAC_FORMULA_PROPERTY, Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_PROPERTY, Constants.NET_SUBSEQUENT_PERIOD_PRICE_PROPERTY, Constants.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA_PROPERTY, 
        Constants.PRICE_TOLERANCE_INTERVAL, Constants.PRICE_TOLERANCE_FREQUENCY_PROPERTY, Constants.PRICE_TOLERANCE_TYPE_PROPERTY, Constants.PRICE_TOLERANCE_PROPERTY,
        Constants.MAX_INCREMENTAL_CHANGE_PROPERTY, Constants.RESET_ELIGIBLE_PROPERTY, Constants.RESET_TYPE_PROPERTY, Constants.RESET_DATE_PROPERTY, Constants.RESET_INTERVAL_PROPERTY, Constants.RESET_FREQUENCY_PROPERTY,
        Constants.RESET_PRICE_TYPE_PROPERTY, Constants.NET_RESET_PRICE_TYPE_PROPERTY, Constants.NET_RESET_PRICE_FORMULA_PROPERTY, Constants.NET_PRICE_TYPE_PROPERTY, Constants.NET_PRICE_TYPE_FORMULA_PROPERTY,
        "cfpNO", Constants.CFP_NAME, "ifpNo", Constants.IFPNAME, "psNo", Constants.PSNAME, "rsNo", Constants.RSNAME, "rarCategory"};

    private final String[] addHeader = {StringUtils.EMPTY, Constants.PROJECTION_ID_HEADER, Constants.WORK_FLOW_STATUS_HEADER, Constants.CONTRACT_HOLDER_HEADER, Constants.CONTRACT_NO_HEADER,
        Constants.CONTRACT_NAME_HEADER, Constants.MARKET_TYPE_HEADER, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER, Constants.STATUS_FIELD, Constants.ITEM_START_DATE,
        Constants.ITEM_END_DATE, StringConstantsUtil.CP_START_DATE_LABEL, StringConstantsUtil.CP_END_DATE_LABEL, Constants.PRICE_TYPE_LABEL,
        StringConstantsUtil.PRICE_LABEL, Constants.PRICE_PROTECTION_STATUS_LABEL, Constants.PRICE_PROTECTION_START_DATE_LABEL, Constants.PRICE_PROTECTION_END_DATE_LABEL,
        Constants.MEASUREMENT_PRICE_LABLE_NAME, Constants.NEP_LABLE_NAME, Constants.NEP_FORMULA_LABLE_NAME,  Constants.BASE_PRICE_TYPE_LABLE_NAME ,Constants.BASELINE_WAC_LABLE_NAME,
        Constants.BASELINE_NET_WAC_LABLE_NAME, Constants.NET_BASELINE_WAC_FORMULA_LABLE_NAME, Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_LABLE_NAME,Constants.NET_SUBSEQUENT_PERIOD_PRICE_LABLE_NAME, Constants.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA_LABLE_NAME,
        Constants.PRICE_TOLERANCE_INTERVAL_LABEL, Constants.PRICE_TOLERANCE_FREQUENCY_LABEL, Constants.PRICE_TOLERANCE_TYPE_LABEL, Constants.PRICE_TOLERANCE_LABEL,
        Constants.MAX_INCREMENTAL_CHANGE_LABEL,  Constants.RESET_ELIGIBLE_LABLE_NAME, Constants.RESET_TYPE_LABEL, Constants.RESET_DATE_LABEL, Constants.RESET_INTERVAL_LABEL, Constants.RESET_FREQUENCY_LABEL,
        Constants.RESET_PRICE_TYPE_LABLE_NAME, Constants.NET_RESET_PRICE_TYPE_LABLE_NAME, Constants.NET_RESET_PRICE_FORMULA_LABLE_NAME,  Constants.NET_PRICE_TYPE_LABLE_NAME,  Constants.NET_PRICE_TYPE_FORMULA_LABLE_NAME,
        Constants.CFP_NO_HEADER, Constants.CFP_NAME_HEADER, Constants.IFP_NO, Constants.IFP_NAME_LABEL, Constants.PS_NO_LABEL, Constants.PS_NAME_LABEL, Constants.RS_NO_HEADER, Constants.RS_NAME_LABEL, Constants.RAR_CATEGORY_HEADER};
    
    private final AbstractLogic logic = AbstractLogic.getInstance();
    private final AddItemTableDTO binderDto = new AddItemTableDTO();
    private static final String CONFIRMATION_HEADER = "Confirmation";
    private final ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<>(binderDto));
    private SelectionDTO selection;
    private final BeanItemContainer<AbstractContractSearchDTO> itemContractContainer = new BeanItemContainer<>(AbstractContractSearchDTO.class);
    private AbstractContractSearchDTO componentInfoDTO = new AbstractContractSearchDTO();
    private final AbstractComponentInfo component = new AbstractComponentInfo(Constants.RS, selection);
    private String massUpdateString = StringUtils.EMPTY;
    private boolean isFound = false;
    private final StplSecurity stplSecurity = new StplSecurity();
    private final Map comboToTableMap = new HashMap();
    private final Map tempTableMap = new HashMap();
    private final Map fieldAndPropertyMap = new HashMap();
    private CustomTextField.ClickListener clickLister;
    @UiField("massUpdateText")
    protected CustomTextField massUpdateText;

    public AddContractSelection() {
        addItemTableLogic.setTempPageLength(NumericConstants.FIVE);
    }

    /**
     * Get Content
     *
     * @param selecteditemList
     * @param selection
     * @return component
     */
    public Component getContent(List<ItemIndexDto> selecteditemList, final SelectionDTO selection) {
        this.selection = selection;
        this.selecteditemList = selecteditemList == null ? selecteditemList : new ArrayList<>(selecteditemList);
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(Clara.create(getClass().getResourceAsStream("/item/itemContractSelection.xml"), this));
        massUpdateRadio.addItems("Enable", DISABLE.getConstant());
        vCntHolder.focus();
        Panel panel = new Panel();
        panel.setContent(layout);
        field.addItem(Constants.SELECT_ONE);
        LoadField();
        LoadComboToTableMap();
        LoadTempToTableMap();
        loadFieldAndPropertyMap();
        field.setNullSelectionAllowed(true);
        field.setNullSelectionItemId(Constants.SELECT_ONE);
        field.select(Constants.SELECT_ONE);
        configureFields();
        component.replaceComponent();
        configureSecurityPermissions();
        return panel;
    }

    /**
     * Configure Fields
     */
    private void configureFields() {
        getBinder();
        configureItemTable();
        loadSelectionCriteria();
        addComponentInfoPanel();
        loadMarketType();
        loadAliasType();
        massUpdateRadio.select(DISABLE.getConstant());
        massUpdateRadio.setImmediate(Boolean.TRUE);
        massUpdateValue.setReadOnly(Boolean.TRUE);
        massUpdateValue.setVisible(Boolean.FALSE);
        massStartDate.setReadOnly(Boolean.TRUE);
        massEndDate.setReadOnly(Boolean.TRUE);
        massStartDate.setVisible(Boolean.FALSE);
        massEndDate.setVisible(Boolean.FALSE);
        massUpdateText.setReadOnly(Boolean.TRUE);
        populateBtn.setEnabled(false);
        allItemsCombo.addItem("NO");
        allItemsCombo.select("NO");
        allItemsCombo.setReadOnly(true);
        visibilityOptions();
    }

    @UiHandler("field")
    public void fieldTypeLogic(Property.ValueChangeEvent event) {
        String processName = String.valueOf(field.getValue());
        massUpdateString = processName;  
        massUpdateText.setReadOnly(false);
        massUpdateText.setValue(StringUtils.EMPTY);

        if (null != processName) {
            switch (processName) {
                case Constants.PRICE_TYPE_LABEL:
                case Constants.MEASUREMENT_PRICE_LABLE_NAME:
                case Constants.RESET_PRICE_TYPE_LABLE_NAME:
                case Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_LABLE_NAME:
                    loadValueddlbField();
                    loadPriceType(massUpdateValue, false);
                    break;
                case Constants.NET_PRICE_TYPE_LABLE_NAME:
                case Constants.NET_RESET_PRICE_TYPE_LABLE_NAME:
                case Constants.RESET_ELIGIBLE_LABLE_NAME:
                case Constants.NET_SUBSEQUENT_PERIOD_PRICE_LABLE_NAME:
                case Constants.BASELINE_NET_WAC_LABLE_NAME:
                    loadValueddlbField();
                    CommonUtil.loadComboBoxForGCM(massUpdateValue, Constants.LOCKED_STATUS_LISTNAME, false);
                    break;
                case StringConstantsUtil.PRICE_LABEL:
                case Constants.NEP_LABLE_NAME:
                case Constants.PRICE_TOLERANCE_LABEL:
                case Constants.MAX_INCREMENTAL_CHANGE_LABEL:
                    loadValueddlbTextField();
                    massUpdateText.removeStyleName(SEARCHICON);
                    massUpdateText.removeClickListener(clickLister);
                    break;
                case Constants.ITEM_START_DATE:
                case Constants.ITEM_END_DATE:
                case StringConstantsUtil.CP_START_DATE_LABEL:
                case StringConstantsUtil.CP_END_DATE_LABEL:
                case Constants.PRICE_PROTECTION_START_DATE_LABEL:
                case Constants.PRICE_PROTECTION_END_DATE_LABEL:
                case Constants.RESET_DATE_LABEL:
                    loadValueddlbDateField(processName);
                    break;
                case Constants.STATUS_FIELD:
                case Constants.PRICE_PROTECTION_STATUS_LABEL:
                    loadValueddlbField();
                    loadStatus();
                    break;
                case Constants.RESET_FREQUENCY_LABEL:
                case Constants.PRICE_TOLERANCE_FREQUENCY_LABEL:
                    loadValueddlbField();
                    loadPriceToleranceFrequency();
                    break;
                case Constants.PRICE_TOLERANCE_INTERVAL_LABEL:
                case Constants.RESET_INTERVAL_LABEL:
                    loadValueddlbField();
                    loadPriceToleranceInterval();
                    break;
                case Constants.BASE_PRICE_TYPE_LABLE_NAME:
                    loadValueddlbField();
                    CommonUtil.loadComboBoxForGCM(massUpdateValue, Constants.BASE_PRICE_TYPE_COLUMN_NAME, false);
                    break;
                case Constants.PRICE_TOLERANCE_TYPE_LABEL:
                    loadValueddlbField();
                    loadPriceTolerenceType();
                    break;
                case Constants.RESET_TYPE_LABEL:
                    loadValueddlbField();
                    CommonUtil.loadComboBoxForGCM(massUpdateValue, Constants.RESET_TYPE_COLUMN_NAME, false);
                    break;
                case Constants.NEP_FORMULA_LABLE_NAME:
                    loadValueddlbTextField();
                    massUpdateText.addStyleName(SEARCHICON);
                    if (clickLister != null) {
                        massUpdateText.removeClickListener(clickLister);
                    }
                    clickLister = new CustomTextField.ClickListener() {
                        @Override
                        public void click(CustomTextField.ClickEvent event) {
                            NEPLookup formulaLookUp = new NEPLookup(massUpdateText, Constants.NEP_FORMULA_LABLE_NAME);
                            formulaLookUp.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    massUpdateText.setReadOnly(false);
                                    if (massUpdateText.getData() != null) {
                                        FormulaDTO object = (FormulaDTO) massUpdateText.getData();
                                        massUpdateText.setValue(object.getFormulaName());
                                    }
                                    massUpdateText.setReadOnly(true);
                                }
                            });
                            UI.getCurrent().addWindow(formulaLookUp);
                        }
                    };
                    massUpdateText.addClickListener(clickLister);
                    massUpdateText.setReadOnly(true);
                    break;
                case Constants.NET_BASELINE_WAC_FORMULA_LABLE_NAME:
                    loadValueddlbTextField();
                    massUpdateText.addStyleName(SEARCHICON);
                    if (clickLister != null) {
                        massUpdateText.removeClickListener(clickLister);
                    }
                    clickLister = new CustomTextField.ClickListener() {
                        @Override
                        public void click(CustomTextField.ClickEvent event) {
                            NEPLookup formulaLookUp = new NEPLookup(massUpdateText, Constants.NET_BASELINE_WAC_FORMULA_LABLE_NAME);
                            formulaLookUp.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    massUpdateText.setReadOnly(false);
                                    if (massUpdateText.getData() != null) {
                                        FormulaDTO object = (FormulaDTO) massUpdateText.getData();
                                        massUpdateText.setValue(object.getFormulaName());
                                    }
                                    massUpdateText.setReadOnly(true);
                                }
                            });
                            UI.getCurrent().addWindow(formulaLookUp);
                        }
                    };
                    massUpdateText.addClickListener(clickLister);
                    massUpdateText.setReadOnly(true);
                    break;
                case Constants.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA_LABLE_NAME:
                    loadValueddlbTextField();
                    massUpdateText.addStyleName(SEARCHICON);
                    if (clickLister != null) {
                        massUpdateText.removeClickListener(clickLister);
                    }
                    clickLister = new CustomTextField.ClickListener() {
                        @Override
                        public void click(CustomTextField.ClickEvent event) {
                            NEPLookup formulaLookUp = new NEPLookup(massUpdateText, Constants.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA_LABLE_NAME);
                            formulaLookUp.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    massUpdateText.setReadOnly(false);
                                    if (massUpdateText.getData() != null) {
                                        FormulaDTO object = (FormulaDTO) massUpdateText.getData();
                                        massUpdateText.setValue(object.getFormulaName());
                                    }
                                    massUpdateText.setReadOnly(true);
                                }
                            });
                            UI.getCurrent().addWindow(formulaLookUp);
                        }
                    };
                    massUpdateText.addClickListener(clickLister);
                    massUpdateText.setReadOnly(true);
                    break;
                case Constants.NET_RESET_PRICE_FORMULA_LABLE_NAME:
                    loadValueddlbTextField();
                    massUpdateText.addStyleName(SEARCHICON);
                    if (clickLister != null) {
                        massUpdateText.removeClickListener(clickLister);
                    }
                    clickLister = new CustomTextField.ClickListener() {
                        @Override
                        public void click(CustomTextField.ClickEvent event) {
                            NEPLookup formulaLookUp = new NEPLookup(massUpdateText, Constants.NET_RESET_PRICE_FORMULA_LABLE_NAME);
                            formulaLookUp.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    massUpdateText.setReadOnly(false);
                                    if (massUpdateText.getData() != null) {
                                        FormulaDTO object = (FormulaDTO) massUpdateText.getData();
                                        massUpdateText.setValue(object.getFormulaName());
                                    }
                                    massUpdateText.setReadOnly(true);
                                }
                            });
                            UI.getCurrent().addWindow(formulaLookUp);
                        }
                    };
                    massUpdateText.addClickListener(clickLister);
                    massUpdateText.setReadOnly(true);
                    break;
                case Constants.NET_PRICE_TYPE_FORMULA_LABLE_NAME:
                    loadValueddlbTextField();
                    massUpdateText.addStyleName(SEARCHICON);
                    if (clickLister != null) {
                        massUpdateText.removeClickListener(clickLister);
                    }
                    clickLister = new CustomTextField.ClickListener() {
                        @Override
                        public void click(CustomTextField.ClickEvent event) {
                            NEPLookup formulaLookUp = new NEPLookup(massUpdateText, Constants.NET_PRICE_TYPE_LABLE_NAME);
                            formulaLookUp.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    massUpdateText.setReadOnly(false);
                                    if (massUpdateText.getData() != null) {
                                        FormulaDTO object = (FormulaDTO) massUpdateText.getData();
                                        massUpdateText.setValue(object.getFormulaName());
                                    }
                                    massUpdateText.setReadOnly(true);
                                }
                            });
                            UI.getCurrent().addWindow(formulaLookUp);
                        }
                    };
                    massUpdateText.addClickListener(clickLister);
                    massUpdateText.setReadOnly(true);
                    break;
                default:
                    break;
            }
        }
        massStartDate.setValue(null);
        massEndDate.setValue(null);
        massUpdateValue.setValue(null);
        massUpdateText.setValue(StringUtils.EMPTY);
    }

    @UiHandler("massUpdateRadio")
    public void massTypeLogic(Property.ValueChangeEvent event) {
        String processName = String.valueOf(massUpdateRadio.getValue());
        if (DISABLE.getConstant().equals(processName)) {
            resetMassUpdate();
            field.setReadOnly(true);
            massStartDate.setReadOnly(true);
            massEndDate.setReadOnly(true);
            massUpdateValue.setReadOnly(true);
            populateBtn.setEnabled(false);

        }
        if ("Enable".equals(processName)) {
            field.setReadOnly(false);
            massStartDate.setReadOnly(false);
            massEndDate.setReadOnly(false);
            massUpdateValue.setReadOnly(false);
            populateBtn.setEnabled(true);

        }

    }
    @UiHandler("massUpdateValue")
    public void basePriceTypeLogic(Property.ValueChangeEvent event) {
        String processName = String.valueOf(massUpdateValue.getValue());
        if (Constants.BASE_PRICE_TYPE_LABLE_NAME.equals(String.valueOf(field.getValue()))) {
            baseWacDate.setValue(null);
            baseWacPriceType.setValue(null);
            baseWacManual.setValue(StringUtils.EMPTY);
            if (Constants.SELECT_ONE.equals(processName) || Constants.NULL.equals(processName)){
                baseWacManual.setVisible(false);
                baseWacPriceType.setVisible(false);
                baseWacDate.setVisible(false);
            } else if (Constants.MANUAL_LABLE_NAME.equals(processName)) {
                baseWacManual.setVisible(true);
                baseWacPriceType.setVisible(false);
                baseWacDate.setVisible(false);
            } else if (Constants.DATE_LABLE_NAME.equals(processName)) {
                baseWacDate.setVisible(true);
                baseWacManual.setVisible(false);
                baseWacPriceType.setVisible(false);
            } else if (Constants.PRICE_TYPE_LABEL.equals(processName)) {
                baseWacPriceType.setVisible(true);
                baseWacManual.setVisible(false);
                baseWacDate.setVisible(false);
                loadPriceType(baseWacPriceType, false);
            }
        }
    }

    @UiHandler("searchBtn")
    public void searchButton(Button.ClickEvent event) throws FieldGroup.CommitException {
        ItemManagementLookup.waitForSave(selection);
        binder.commit();
        checkrecord();
        selection.setCountQueryName("Add Load Contract Count");
        selection.setDataQueryName("Add Load Contract");
        selection.setReset(true);
        if ((binderDto.getContractHolder() == null || binderDto.getContractHolder().isEmpty()) && (binderDto.getMarketType_DTO() == null)
                && (binderDto.getCfp() == null || binderDto.getCfp().isEmpty()) && (binderDto.getContractNo() == null || binderDto.getContractNo().isEmpty())
                && (binderDto.getStartDate() == null) && (binderDto.getEndDate() == null)
                && (binderDto.getIfp() == null || binderDto.getIfp().isEmpty())
                && (binderDto.getContractName() == null || binderDto.getContractName().isEmpty()) && (binderDto.getPriceSchedule() == null || binderDto.getPriceSchedule().isEmpty())
                && (binderDto.getCustomerNo() == null || binderDto.getCustomerNo().isEmpty()) && (binderDto.getCustomerName() == null || binderDto.getCustomerName().isEmpty())
                && (binderDto.getRebateSchedule() == null || binderDto.getRebateSchedule().isEmpty())
                && (binderDto.getTypeDdlb() == null) && (binderDto.getNumber() == null || binderDto.getNumber().isEmpty())
                && (binderDto.getToDate() == null) && (binderDto.getFromDate() == null)) {

            MessageBox.showPlain(Icon.INFO, Constants.ERROR, "Please enter/select search criteria", ButtonId.OK);
        } else {
            if (!addItemTableLogic.loadSetData(binderDto, selection, false, selecteditemList)) {

                AbstractNotificationUtils.getErrorNotification("No Matching Records",
                        "There were no records matching the search criteria.  Please try again.");
            } else {
                Notification.show("Search Completed");
            }
        }
    }

    public Boolean checkrecord() {
        List input = AbstractLogic.getResultsInput(selection);
        String queryname = "checkrecord condition check";
        Boolean isUpdated = ItemQueries.itemUpdate(input, queryname);
        return isUpdated;

    }

    @UiHandler("populateBtn")
    public void populateButton(Button.ClickEvent event) {
        if (!massUpdateString.isEmpty()) {
            if (massUpdateValue.getValue() != null || massStartDate.getValue() != null || massEndDate.getValue() != null || (massUpdateText.getValue() != null && !massUpdateText.getValue().isEmpty())) {

                isFound = isPresent();
                if (isFound) {
                        populateLogic();
                    }
            } else {
                MessageBox.showPlain(Icon.INFO, Constants.ERROR, "Please enter a " + massUpdateString + " to Mass Update. ", ButtonId.OK);

            }
        } else {
            MessageBox.showPlain(Icon.INFO, Constants.ERROR, "Please select a Field to Mass Update. ", ButtonId.OK);

        }
        isFound = false;
    }

    @UiHandler("resetBtn")
    public void resetSearchCriteria(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    binder.setItemDataSource(new BeanItem<>(new AddItemTableDTO()));
                    binder.commit();
                } catch (FieldGroup.CommitException ex) {
                    LOGGER.error("",ex);
                }
            }

            @Override
            public void noMethod() {
                return;
            }
        }.getConfirmationMessage(CONFIRMATION_HEADER, "Are you sure you want to reset the values in the ITEM Search?");

    }

    @UiHandler("resetBtncur")
    public void resetcurButton(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                addItemTable.resetFilters();
                selection.setReset(false);
                ItemLogic.resetContractDetails(selection);
                addItemTableLogic.loadSetData(binderDto, selection, false, selecteditemList);
            }

            @Override
            public void noMethod() {
                return;
            }
        }.getConfirmationMessage(CONFIRMATION_HEADER, "Are you sure you want to reset the values in the Add Item Details list view?");

    }

    @UiHandler("submitBtncur")
    public void submitButtonLogic(Button.ClickEvent event) {

            submitButtonLogic();
        }
    private boolean isSubmit = false;

    public boolean submitButtonLogic() {
        if (submitButtonCheckRecord() && submitButtonCheck()) {
            new AbstractNotificationUtils() {
                @Override
                public void yesMethod() {
                    try {
                        List input = AbstractLogic.getResultsInput(selection);
                        ItemQueries.itemUpdate(input, "Submitting the contract");
                        selection.getLookup().changeTab();
                        if (selection.getButtonMode().equals(ConstantsUtil.ADD)) {
                            selection.setIsItemAddTab(true);
                        }
                        isSubmit = true;
                    } catch (Exception ex) {
                        LOGGER.error("",ex);
                    }
                }

                @Override
                public void noMethod() {
                    isSubmit = false;
                }
            }.getConfirmationMessage(CONFIRMATION_HEADER, "Are you sure you want to submit the selected contracts?");
        } else {
            isSubmit = false;
            AbstractNotificationUtils.getErrorNotification("Required Fields Error",
                    "Not all the required fields are filled.  Please try again.");
        }
        return isSubmit;

    }

    private Boolean submitButtonCheck() {
        List input = AbstractLogic.getResultsInput(selection);
        List<Object[]> list = ItemQueries.getItemData(input, "Submit condition check", null);
        LOGGER.debug("submitButtonCheck :::: list-->>" + list.size());
        if (AbstractLogic.getCount(list) == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public Boolean submitButtonCheckRecord() {
        List inputList = AbstractLogic.getResultsInput(selection);
        List<Object[]> list = ItemQueries.getAppData(inputList, "Submit Check one Item condition", null);
        if (AbstractLogic.getCount(list) > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Load Selection Criteria
     */
    private void loadSelectionCriteria() {
        if (selecteditemList != null && selecteditemList.size() == 1) {
            ItemIndexDto dto = selecteditemList.get(0);
            itemNo.setValue(dto.getItemNo());
            itemName.setValue(dto.getItemName());
            itemDesc.setValue(dto.getItemDesc());
            therapeuticClass.setValue(dto.getTherapeuticClass());
            brand.setValue(dto.getBrand());
        }
        itemNo.setReadOnly(Boolean.TRUE);
        itemName.setReadOnly(Boolean.TRUE);
        itemDesc.setReadOnly(Boolean.TRUE);
        therapeuticClass.setReadOnly(Boolean.TRUE);
        brand.setReadOnly(Boolean.TRUE);
    }

    /**
     * Configure Table
     *
     * @return
     */
    Component configureItemTable() {
        addItemTableLogic.setPageLength(NumericConstants.FIVE);
        addItemTableLogic.setContainerDataSource(itemContractContainer);
        addItemTableLogic.sinkItemPerPageWithPageLength(false);
        addItemTable.setVisibleColumns(addColumn);
        addItemTable.setColumnHeaders(addHeader);
        addItemTable.setFilterBarVisible(true);
        addItemTable.addStyleName(ConstantsUtil.FILTERCOMBOBOX);
        addItemTable.setPageLength(NumericConstants.FIVE);
        addItemTable.setFilterDecorator(new ExtDemoFilterDecorator());
        for (Object object : addItemTable.getVisibleColumns()) {
            if (String.valueOf(object).contains("Date")) {
                addItemTable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
                addItemTable.setConverter(object, new DateToStringConverter());
            }
        }
        addItemTable.setEditable(Boolean.TRUE);
        addItemTable.markAsDirty();
        addItemTable.setSelectable(true);
        addItemTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        contractVertical.addComponent(addItemTable);
        HorizontalLayout controls = addItemTableLogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        contractVertical.addComponent(controlLayout);
        addItemTable.setTableFieldFactory(new AddItemContractFieldFactory(selection, addItemTable, fieldAndPropertyMap));
        addItemTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection itemList = addItemTable.getItemIds();
                for (Object obj : itemList) {
                    AbstractContractSearchDTO dto = (AbstractContractSearchDTO) obj;
                    dto.setCheckRecord(event.isChecked());
                    addItemTable.getContainerProperty(obj, event.getPropertyId()).setValue(event.isChecked());
                    logic.checkAllInsert(event.isChecked(), selection);
                }
            }
        });

        addItemTable.setFilterGenerator(new ExtFilterGenerator() {
            @Override
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            @Override
            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                if (originatingField instanceof ComboBox) {
                    if (originatingField.getValue() != null) {
                        Object id=originatingField.getValue();
                        if(originatingField.getValue() instanceof HelperDTO){
                            HelperDTO dto = (HelperDTO) originatingField.getValue();
                            id=dto.getId();
                        }

                        return new SimpleStringFilter(propertyId, String.valueOf(id), false, false);
                    } else {
                        return null;
                    }
                }
                return null;
            }

            @Override
            public void filterRemoved(Object propertyId) {
                return;
            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                return;
            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            @Override
            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                if (Constants.CHECK_RECORD.equals(propertyId)) {
                    CustomTextField text = new CustomTextField();
                    text.setEnabled(false);
                    return text;
                }
                if (Constants.MARKET_TYPE.equals(propertyId)) {
                    ComboBox marketTypeDdlb = new ComboBox();
                    logic.LazyLoadDdlb(marketTypeDdlb, "Load Market Type Count", "Load Market Type", true);
                    return marketTypeDdlb;
                }
                if (Constants.STATUS_S.equals(propertyId)) {
                    ComboBox statusDdlb = new ComboBox();
                    logic.LazyLoadDdlb(statusDdlb, "Load Item Status Count", "Load Item Status", true);
                    return statusDdlb;
                }
                if (Constants.PRICE_TOLERANCE_INTERVAL.equals(propertyId)) {
                    ComboBox pricetolerenceintDdlb = new ComboBox();
                    logic.LazyLoadDdlb(pricetolerenceintDdlb, "Load PS_INTERVAL Count", "Load PS_INTERVAL", true);
                    return pricetolerenceintDdlb;
                }
                if (Constants.PRICE_TOLERANCE_TYPE_PROPERTY.equals(propertyId)) {
                    ComboBox pricetolerencetypeDdlb = new ComboBox();
                    logic.LazyLoadDdlb(pricetolerencetypeDdlb, "Load PS_TYPE Count", "Load PS_TYPE", true);
                    return pricetolerencetypeDdlb;
                }
                if (Constants.PRICE_TOLERANCE_FREQUENCY_PROPERTY.equals(propertyId)) {
                    ComboBox pricetolerencefreqDdlb = new ComboBox();
                    logic.LazyLoadDdlb(pricetolerencefreqDdlb, "Load PS_FREQ Count", "Load PS_FREQ", true);
                    return pricetolerencefreqDdlb;
                }
                if (Constants.PRICE_PROTECTION_STATUS_PROPERTY.equals(propertyId)) {
                    ComboBox priceProtectionDdlb = new ComboBox();
                    CommonUtil.loadComboBoxForGCM(priceProtectionDdlb, UiUtils.STATUS, true);
                    return priceProtectionDdlb;
                }
                if (Constants.BASE_PRICE_PROPERTY.equals(propertyId)) {
                    ComboBox basePriceType = new ComboBox();
                    CommonUtil.loadComboBoxForGCM(basePriceType, Constants.BASE_PRICE_TYPE_COLUMN_NAME, true);
                    return basePriceType;
                }
                if (Constants.PRICE_TYPE_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    loadPriceType(comboBox, true);
                    return comboBox;
                }
                if (Constants.MEASUREMENT_PRICE_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    loadPriceType(comboBox, true);
                    return comboBox;
                }
                if (Constants.RESET_PRICE_TYPE_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    loadPriceType(comboBox, true);
                    return comboBox;
                }
                if (Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    loadPriceType(comboBox, true);
                    return comboBox;
                }
                if (Constants.RESET_ELIGIBLE_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    CommonUtil.loadComboBoxForGCM(comboBox, Constants.LOCKED_STATUS_LISTNAME, true);
                    return comboBox;
                }
                if (Constants.RESET_TYPE_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    CommonUtil.loadComboBoxForGCM(comboBox, Constants.RESET_TYPE_COLUMN_NAME, true);
                    return comboBox;
                }
                if (Constants.RESET_INTERVAL_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    CommonUtil.loadComboBoxForGCM(comboBox, StringConstantsUtil.PRICE_TOLERANCE_INTERVAL_LABEL, true);
                    return comboBox;
                }
                if (Constants.RESET_FREQUENCY_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    CommonUtil.loadComboBoxForGCM(comboBox, StringConstantsUtil.PRICE_TOLERANCE_FREQUENCY_LABEL, true);
                    return comboBox;
                }
                if (Constants.NET_RESET_PRICE_TYPE_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    CommonUtil.loadComboBoxForGCM(comboBox, Constants.LOCKED_STATUS_LISTNAME, true);
                    return comboBox;
                }
                if (Constants.NET_PRICE_TYPE_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    CommonUtil.loadComboBoxForGCM(comboBox, Constants.LOCKED_STATUS_LISTNAME, true);
                    return comboBox;
                }
                if (Constants.NET_SUBSEQUENT_PERIOD_PRICE_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    CommonUtil.loadComboBoxForGCM(comboBox, Constants.LOCKED_STATUS_LISTNAME, true);
                    return comboBox;
                }
                if (Constants.BASELINE_NET_WAC_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    CommonUtil.loadComboBoxForGCM(comboBox, Constants.LOCKED_STATUS_LISTNAME, true);
                    return comboBox;
                }
                return null;
            }
        });
        addItemTable.setFilterBarVisible(true);
        addItemTable.setFilterDecorator(new ExtDemoFilterDecorator());

        addItemTable.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method called when available results value is changed.
             */
            @SuppressWarnings("PMD")
            @Override
            public void valueChange(final Property.ValueChangeEvent event) {
                resultsItemClick(event.getProperty().getValue());
            }
        });
        addItemTable.setFilterFieldVisible(Constants.CHECK_RECORD, false);
        return addItemTable;
    }

    @UiHandler(Constants.CONTRACT_HOLDER) 
    public void contractHolder(CustomTextField.ClickEvent event) {
        ComponentLookUp chHolder = new ComponentLookUp("Contract Holder", "Contract Holder Lookup", vCntHolder);
        chHolder.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (vCntHolder.getData() != null) {
                    ComponentLookUpDTO object = (ComponentLookUpDTO) vCntHolder.getData();
                    vCntHolder.setValue(object.getComponentName());
                    binderDto.setContractHolder_SID(object.getMasterSid());
                }
            }
        });

        UI.getCurrent().addWindow(chHolder);
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("cfp")
    public void companyFamilyPlan(CustomTextField.ClickEvent event) {
        CFPLookUp cfpObj = new CFPLookUp(cfp);
        cfpObj.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (cfp.getData() != null) {
                    ComponentLookUpDTO object = (ComponentLookUpDTO) cfp.getData();
                    cfp.setValue(object.getComponentName());
                    binderDto.setCfp_SID(object.getMasterSid());
                }
            }
        });

        UI.getCurrent().addWindow(cfpObj);
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("contractNo")
    public void contractNo(CustomTextField.ClickEvent event) {
        ComponentLookUp contractNum = new ComponentLookUp("Contract", "Contract Lookup", vCntNo);
        contractNum.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (vCntNo.getData() != null) {
                    ComponentLookUpDTO object = (ComponentLookUpDTO) vCntNo.getData();
                    vCntNo.setValue(object.getComponentNo());
                    vCntName.setValue(object.getComponentNo());
                    binderDto.setContractNo_SID(object.getMasterSid());
                    binderDto.setContractName_SID(object.getMasterSid());
                }
            }
        });

        UI.getCurrent().addWindow(contractNum);
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("ifp")
    public void itemFamilyPlan(CustomTextField.ClickEvent event) {
        IFPLookUp ifpObj = new IFPLookUp(ifp);
        ifpObj.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (ifp.getData() != null) {
                    ComponentLookUpDTO object = (ComponentLookUpDTO) ifp.getData();
                    ifp.setValue(object.getComponentName());
                    binderDto.setIfp_SID(object.getMasterSid());
                }
            }
        });

        UI.getCurrent().addWindow(ifpObj);
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("contractName")
    public void contractName(CustomTextField.ClickEvent event) {
        ComponentLookUp contractNameObj = new ComponentLookUp("Contract", "Contract Lookup", vCntName);
        contractNameObj.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (vCntName.getData() != null) {
                    ComponentLookUpDTO object = (ComponentLookUpDTO) vCntName.getData();
                    vCntNo.setValue(object.getComponentNo());
                    vCntName.setValue(object.getComponentNo());
                    binderDto.setContractNo_SID(object.getMasterSid());
                    binderDto.setContractName_SID(object.getMasterSid());
                }
            }
        });

        UI.getCurrent().addWindow(contractNameObj);
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("priceSchedule")
    public void priceSchedule(CustomTextField.ClickEvent event) {
        PSLookUp ps = new PSLookUp(vPriceSchdle);
        ps.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (vPriceSchdle.getData() != null) {
                    ComponentLookUpDTO object = (ComponentLookUpDTO) vPriceSchdle.getData();
                    vPriceSchdle.setValue(object.getComponentName());
                    binderDto.setPs_SID(object.getMasterSid());
                }
            }
        });

        UI.getCurrent().addWindow(ps);
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("customerNo")
    public void customerNo(CustomTextField.ClickEvent event) {
        ComponentLookUp companyNo = new ComponentLookUp("Customer", "Customer Lookup", vCustNo);
        companyNo.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (vCustNo.getData() != null) {
                    ComponentLookUpDTO object = (ComponentLookUpDTO) vCustNo.getData();
                    vCustNo.setValue(object.getComponentNo());
                    vCustName.setValue(object.getComponentName());
                    binderDto.setCustomer_SID(object.getMasterSid());
                }
            }
        });

        UI.getCurrent().addWindow(companyNo);
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("customerName")
    public void customerName(CustomTextField.ClickEvent event) {
        ComponentLookUp companyName = new ComponentLookUp("Customer", "Customer Lookup", vCustName);
        companyName.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (vCustName.getData() != null) {
                    ComponentLookUpDTO object = (ComponentLookUpDTO) vCustName.getData();
                    vCustNo.setValue(object.getComponentNo());
                    vCustName.setValue(object.getComponentName());
                    binderDto.setCustomer_SID(object.getMasterSid());
                }
            }
        });

        UI.getCurrent().addWindow(companyName);
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("rebateSchedule")
    public void rebateSchedule(CustomTextField.ClickEvent event) {
        try {
            RSLookUp rs = new RSLookUp(rsSchedule);
            rs.addCloseListener(new Window.CloseListener() {
                @Override
                public void windowClose(Window.CloseEvent e) {
                    if (rsSchedule.getData() != null) {
                        ComponentLookUpDTO object = (ComponentLookUpDTO) rsSchedule.getData();
                        rsSchedule.setValue(object.getComponentName());
                        binderDto.setRs_SID(object.getMasterSid());
                    }
                }
            });

            UI.getCurrent().addWindow(rs);
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }

    /**
     * component Info
     */
    private void addComponentInfoPanel() {
        componentInfoLayout.addComponent(component);
    }

    /**
     * Load Alias Type
     */
    private void loadAliasType() {
        logic.LazyLoadDdlb(typeDdlb, "Load Alias Type Count", "Load Alias Type", false);
    }

    /**
     * Load Market Type
     */
    private void loadMarketType() {
        logic.LazyLoadDdlb(marketType_DTO, "Load Market Type Count", "Load Market Type", false);
    }

    /**
     * get Binder
     *
     * @return ErrorfulFieldGroup
     */
    private ErrorfulFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<>(binderDto));
        binder.setBuffered(true);
        return binder;
    }

    public void insertToTempTable() {
        List input = new ArrayList();
        input.add(AbstractLogic.getItemIds(selecteditemList));
        input.add(selection.getSessionId());
        input.add(selection.getButtonMode());
        if (binderDto.getContractNo_SID() != null && !binderDto.getContractNo_SID().isEmpty()) {
            input.add(binderDto.getContractNo_SID().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getContractName_SID() != null && !binderDto.getContractName_SID().isEmpty()) {
            input.add(binderDto.getContractName_SID().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (binderDto.getStartDate() != null) {
            input.add(" AND ( cm.START_DATE >= '" + CommonUtils.DBDate.format(binderDto.getItemStartDate()) + "')");
        } else {
            input.add(" ");
        }

        if (binderDto.getEndDate() != null) {
            input.add(" AND ( CM.END_DATE <= '" + CommonUtils.DBDate.format(binderDto.getEndDate()) + "')");
        } else {
            input.add(" ");
        }
        input.add(AbstractLogic.getItemIds(selecteditemList));
        if (binderDto.getContractHolder_SID() != null && !binderDto.getContractHolder_SID().isEmpty()) {
            input.add(binderDto.getContractHolder_SID().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (binderDto.getMarketType_DTO() != null) {
            input.add(binderDto.getMarketType_DTO().getId());
        } else {
            input.add("%");
        }

        if (binderDto.getCfp_SID() != null && !binderDto.getCfp_SID().isEmpty()) {
            input.add(binderDto.getCfp_SID().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getCustomer_SID() != null && !binderDto.getCustomer_SID().isEmpty()) {
            input.add(binderDto.getCustomer_SID().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getCustomer_SID() != null && !binderDto.getCustomer_SID().isEmpty()) {
            input.add(binderDto.getCustomer_SID().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (binderDto.getIfp_SID() != null && !binderDto.getIfp_SID().isEmpty()) {
            input.add(binderDto.getIfp_SID().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getPs_SID() != null && !binderDto.getPs_SID().isEmpty()) {
            input.add(binderDto.getPs_SID().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getRs_SID() != null && !binderDto.getRs_SID().isEmpty()) {
            input.add(binderDto.getRs_SID().replace("*", "%"));
        } else {
            input.add("%");
        }

        ItemQueries.itemUpdate(input, "Add Temp Insert query");
    }

    protected void resultsItemClick(final Object obj) {
        if (obj == null) {
            componentInfoDTO = null;
        } else {
            componentInfoDTO = (AbstractContractSearchDTO) obj;
            selection.setContractSid(componentInfoDTO.getContractSid());
            selection.setCompanySid(componentInfoDTO.getCompanySid());
            selection.setCfpContractSid(componentInfoDTO.getCfpContractSid());
            selection.setIfpConteractSid(componentInfoDTO.getIfpConteractSid());
            selection.setPsContractSid(componentInfoDTO.getPsContractSid());
            selection.setRsContractSid(componentInfoDTO.getRsContractSid());
            if (selection.getButtonMode().equals(ConstantsUtil.ADD)) {
                selection.setIsItemAddTab(true);
            }
            component.fireComponentListener(Constants.RS, selection);
        }
    }

    private boolean isPresent() {
        Collection itemId = addItemTable.getItemIds();
        boolean isChecked = true;
        for (Object object : itemId) {
            AbstractContractSearchDTO dto = (AbstractContractSearchDTO) object;
            if (dto.getCheckRecord()) {
                isChecked = false;
                isFound = isHavingValue(massUpdateString);
                if (!isFound) {
                    new AbstractNotificationUtils() {
                        @Override
                        public void yesMethod() {

                            populateLogic();
                        }

                        @Override
                        public void noMethod() {
                            return;
                        }
                    }.getConfirmationMessage(CONFIRMATION_HEADER, "There are values in these fields already. Are you sure you want to replace them?");
                    break;
                }

            }
        }
        if (isChecked) {
            MessageBox.showPlain(Icon.INFO, Constants.ERROR, "Please select at least one contract to apply the Mass Update to. ", ButtonId.OK);
        }
        return isFound;

    }

    /**
     * Populate logic
     */
    public void populateLogic() {

        Collection itemId = addItemTable.getItemIds();
        List list = new ArrayList();
        Object value = null;
        Object baseLineValue = null;
        String columnName = StringUtils.EMPTY;
        String baseLineColumnName = StringUtils.EMPTY;
        String textValue;
        String baseLineTextValue;
        HelperDTO tempDTO;
        Date tempDdate;
        for (Object object : itemId) {
            AbstractContractSearchDTO dto = (AbstractContractSearchDTO) object;
            if (dto.getCheckRecord()) {
                switch (massUpdateString) {
                    case Constants.STATUS_FIELD:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        addItemTable.getItem(object).getItemProperty(Constants.STATUS_S).setValue(tempDTO);
                        columnName = StringConstantsUtil.ITEM_STATUS_COLUMN;
                        value = tempDTO.getId();
                        break;
                    case Constants.ITEM_START_DATE:
                        addItemTable.getItem(object).getItemProperty("itemStartDate").setValue(massStartDate.getValue());
                        columnName = StringConstantsUtil.START_DATE_COLUMN;
                        value = CommonUtils.DBDate.format(massStartDate.getValue());
                        break;
                    case Constants.ITEM_END_DATE:
                        tempDdate = dto.getItemStartDate();
                        if (tempDdate != null && massEndDate.getValue().before(tempDdate)) {
                            MessageBox.showPlain(Icon.INFO, Constants.ERROR, StringConstantsUtil.END_DATE_AFTER_START_DATE, ButtonId.OK);
                            break;
                        } else {
                            addItemTable.getItem(object).getItemProperty("itemEndDate").setValue(massEndDate.getValue());
                            columnName = StringConstantsUtil.END_DATE_COLUMN;
                            value = CommonUtils.DBDate.format(massEndDate.getValue());

                        }
                        break;
                    case StringConstantsUtil.CP_START_DATE_LABEL:
                        addItemTable.getItem(object).getItemProperty(StringConstantsUtil.CP_START_DATE).setValue(massStartDate.getValue());
                        columnName = StringConstantsUtil.CONTRACT_PRICE_START_DATE_COLUMN;
                        value = CommonUtils.DBDate.format(massStartDate.getValue());
                        break;
                    case StringConstantsUtil.CP_END_DATE_LABEL:
                        tempDdate = dto.getCpStartDate();
                        if (tempDdate != null && massEndDate.getValue().before(tempDdate)) {
                            MessageBox.showPlain(Icon.INFO, Constants.ERROR, StringConstantsUtil.END_DATE_AFTER_START_DATE, ButtonId.OK);
                            break;
                        } else {
                            addItemTable.getItem(object).getItemProperty(StringConstantsUtil.CP_END_DATE).setValue(massEndDate.getValue());
                            columnName = StringConstantsUtil.CONTRACT_PRICE_END_DATE_COLUMN;
                            value = CommonUtils.DBDate.format(massEndDate.getValue());

                        }
                        break;
                        
                     case Constants.PRICE_TYPE_LABEL:
                        value = massUpdateValue.getValue();
                        addItemTable.getItem(object).getItemProperty(Constants.PRICE_TYPE_PROPERTY).setValue(value);
                        columnName = Constants.PRICE_TYPE_COLUMN_NAME;
                        break;   
                    case StringConstantsUtil.PRICE_LABEL:
                        textValue = massUpdateText.getValue();
                        addItemTable.getItem(object).getItemProperty(StringConstantsUtil.PRICE_PROPERTY).setValue(textValue);
                        columnName = StringConstantsUtil.PRICE_COLUMN;
                        value = textValue;
                        break;
                    case Constants.PRICE_PROTECTION_STATUS_LABEL:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        addItemTable.getItem(object).getItemProperty(Constants.PRICE_PROTECTION_STATUS_PROPERTY).setValue(tempDTO);
                        columnName = Constants.PRICE_PROTECTION_STATUS_COLUMN_NAME;
                        value = tempDTO.getId();
                        break;    
                    case Constants.PRICE_PROTECTION_START_DATE_LABEL:
                        addItemTable.getItem(object).getItemProperty(Constants.PRICE_PROTECTION_START_DATE_PROPERTY).setValue(massStartDate.getValue());
                        columnName = StringConstantsUtil.PRICE_PROTECTION_START_DATE_COLUMN;
                        value = CommonUtils.DBDate.format(massStartDate.getValue());
                        break;

                    case Constants.PRICE_PROTECTION_END_DATE_LABEL:
                        tempDdate = dto.getPriceProtectionStartDate();
                        if (tempDdate != null && massEndDate.getValue().before(tempDdate)) {
                            MessageBox.showPlain(Icon.INFO, Constants.ERROR, StringConstantsUtil.END_DATE_AFTER_START_DATE, ButtonId.OK);
                            break;
                        } else {
                            addItemTable.getItem(object).getItemProperty(Constants.PRICE_PROTECTION_END_DATE_PROPERTY).setValue(massEndDate.getValue());
                            columnName = StringConstantsUtil.PRICE_PROTECTION_END_DATE_LABEL;
                            value = CommonUtils.DBDate.format(massEndDate.getValue());

                        }
                        break;
                    case Constants.MEASUREMENT_PRICE_LABLE_NAME:
                        value =  massUpdateValue.getValue();
                        addItemTable.getItem(object).getItemProperty(Constants.MEASUREMENT_PRICE_PROPERTY).setValue(value);
                        columnName = Constants.MEASUREMENT_PRICE_COLUMN_NAME;
                        break;
                    case Constants.NEP_LABLE_NAME:
                        textValue = massUpdateText.getValue();
                        addItemTable.getItem(object).getItemProperty(Constants.NEP_PROPERTY).setValue(textValue);
                        columnName = Constants.NEP_COLUMN_NAME;
                        value = textValue;
                        break;
                    case Constants.NEP_FORMULA_LABLE_NAME:
                        FormulaDTO nepForumulaDto = (FormulaDTO) massUpdateText.getData();
                        textValue = String.valueOf(nepForumulaDto.getFormulaName());
                        addItemTable.getItem(object).getItemProperty(Constants.NEP_FORMULA_PROPERTY).setValue(textValue);
                        columnName = Constants.NEP_FORMULA_COLUMN_NAME;
                        value = textValue;
                        break;
                    case Constants.BASE_PRICE_TYPE_LABLE_NAME:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        addItemTable.getItem(object).getItemProperty(Constants.BASE_PRICE_PROPERTY).setValue(tempDTO);
                        columnName = Constants.BASE_PRICE_TYPE_COLUMN_NAME;
                        value = tempDTO.getId();
                        if (Constants.MANUAL_LABLE_NAME.equals(tempDTO.getDescription())) {
                            baseLineTextValue = baseWacManual.getValue();
                            addItemTable.getItem(object).getItemProperty(Constants.BASELINE_WAC_MANUAL_LABLE_NAME).setValue(baseLineTextValue);
                            baseLineColumnName = Constants.BASELINE_WAC_MANUAL_COLUMN_NAME;
                            logic.updateBaseLineWacColumn(baseLineColumnName, baseLineTextValue, dto, selection);
                        } else if (Constants.DATE_LABLE_NAME.equals(tempDTO.getDescription())) {
                            addItemTable.getItem(object).getItemProperty(Constants.BASELINE_WAC_DATE_LABLE_NAME).setValue(baseWacDate.getValue());
                            baseLineColumnName = Constants.BASELINE_WAC_DATE_COLUMN_NAME;
                            baseLineValue = CommonUtils.DBDate.format(baseWacDate.getValue());
                            logic.updateBaseLineWacColumn(baseLineColumnName, baseLineValue, dto, selection);
                        } else if (Constants.PRICE_TYPE_LABEL.equals(tempDTO.getDescription())) {
                            baseLineValue = baseWacPriceType.getValue();
                            addItemTable.getItem(object).getItemProperty(Constants.BASELINE_WAC_PRICE_TYPE_LABLE_NAME).setValue(baseLineValue);
                            baseLineColumnName = Constants.BASELINE_WAC_PRICE_TYPE_COLUMN_NAME;
                            logic.updateBaseLineWacColumn(baseLineColumnName, baseLineValue, dto, selection);
                        }
                        break;
                    case Constants.BASELINE_NET_WAC_LABLE_NAME:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        addItemTable.getItem(object).getItemProperty(Constants.BASELINE_NET_WAC_PROPERTY).setValue(tempDTO);
                        columnName = Constants.BASELINE_NET_WAC_COLUMN_NAME;
                        value = tempDTO.getId();
                        break;
                    case Constants.NET_BASELINE_WAC_FORMULA_LABLE_NAME:
                        FormulaDTO netBaselineWACFormulaDto = (FormulaDTO) massUpdateText.getData();
                        textValue = String.valueOf(netBaselineWACFormulaDto.getFormulaName());
                        addItemTable.getItem(object).getItemProperty(Constants.NET_BASELINE_WAC_FORMULA_PROPERTY).setValue(textValue);
                        columnName = Constants.NET_BASELINE_WAC_FORMULA_COLUMN_NAME;
                        value = textValue;
                        break;
                    case Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_LABLE_NAME:
                        value = massUpdateValue.getValue();
                        addItemTable.getItem(object).getItemProperty(Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_PROPERTY).setValue(value);
                        columnName = Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_COLUMN_NAME;
                        break;
                    case Constants.NET_SUBSEQUENT_PERIOD_PRICE_LABLE_NAME:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        addItemTable.getItem(object).getItemProperty(Constants.NET_SUBSEQUENT_PERIOD_PRICE_PROPERTY).setValue(tempDTO);
                        columnName = Constants.NET_SUBSEQUENT_PERIOD_PRICE_COLUMN_NAME;
                        value = tempDTO.getId();
                        break;
                    case Constants.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA_LABLE_NAME:
                        FormulaDTO netSubsequentPeriodPriceFormulaDto = (FormulaDTO) massUpdateText.getData();
                        textValue = String.valueOf(netSubsequentPeriodPriceFormulaDto.getFormulaName());
                        addItemTable.getItem(object).getItemProperty(Constants.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA_PROPERTY).setValue(textValue);
                        columnName = Constants.NET_SUBSEQUENT_PRICE_FORMULA_COLUMN_NAME;
                        value = textValue;
                        break;
                    case Constants.PRICE_TOLERANCE_TYPE_LABEL:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        addItemTable.getItem(object).getItemProperty(Constants.PRICE_TOLERANCE_TYPE_PROPERTY).setValue(tempDTO);
                        columnName = StringConstantsUtil.PRICE_TOLERANCE_TYPE_LABEL;
                        value = tempDTO.getId();
                        break;
                    case Constants.PRICE_TOLERANCE_LABEL:
                        textValue = massUpdateText.getValue();
                        addItemTable.getItem(object).getItemProperty(Constants.PRICE_TOLERANCE_PROPERTY).setValue(textValue);
                        columnName = StringConstantsUtil.PRICE_TOLERANCE_COLUMN;
                        value = textValue;
                        break;
                    case Constants.PRICE_TOLERANCE_FREQUENCY_LABEL:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        addItemTable.getItem(object).getItemProperty(Constants.PRICE_TOLERANCE_FREQUENCY_PROPERTY).setValue(tempDTO);
                        columnName = StringConstantsUtil.PRICE_TOLERANCE_FREQUENCY_LABEL;
                        value = tempDTO.getId();
                        break;
                    case Constants.PRICE_TOLERANCE_INTERVAL_LABEL:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        addItemTable.getItem(object).getItemProperty(Constants.PRICE_TOLERANCE_INTERVAL).setValue(tempDTO);
                        columnName = StringConstantsUtil.PRICE_TOLERANCE_INTERVAL_LABEL;
                        value = tempDTO.getId();
                        break;
                    case Constants.MAX_INCREMENTAL_CHANGE_LABEL:
                        textValue = massUpdateText.getValue();
                        addItemTable.getItem(object).getItemProperty(Constants.MAX_INCREMENTAL_CHANGE_PROPERTY).setValue(textValue);
                        columnName = Constants.MAX_INCREMENTAL_CHANGE_COLUMN_NAME;
                        value = textValue;
                        break;
                       case Constants.RESET_ELIGIBLE_LABLE_NAME:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        addItemTable.getItem(object).getItemProperty(Constants.RESET_ELIGIBLE_PROPERTY).setValue(tempDTO);
                        columnName = Constants.RESET_ELIGIBLE_COLUMN_NAME;
                        value = tempDTO.getId();
                        break; 
                       case Constants.RESET_TYPE_LABEL:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        addItemTable.getItem(object).getItemProperty(Constants.RESET_TYPE_PROPERTY).setValue(tempDTO);
                        columnName = Constants.RESET_TYPE_COLUMN_NAME;
                        value = tempDTO.getId();
                        break; 
                    case Constants.RESET_DATE_LABEL:
                        addItemTable.getItem(object).getItemProperty(Constants.RESET_DATE_PROPERTY).setValue(massStartDate.getValue());
                        columnName = Constants.RESET_DATE_COLUMN_NAME;
                        value = CommonUtils.DBDate.format(massStartDate.getValue());
                        break;
                    case Constants.RESET_INTERVAL_LABEL:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        addItemTable.getItem(object).getItemProperty(Constants.RESET_INTERVAL_PROPERTY).setValue(tempDTO);
                        columnName = Constants.RESET_INTERVAL_COLUMN_NAME;
                        value = tempDTO.getId();
                        break;
                    case Constants.RESET_FREQUENCY_LABEL:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        addItemTable.getItem(object).getItemProperty(Constants.RESET_FREQUENCY_PROPERTY).setValue(tempDTO);
                        columnName = Constants.RESET_FREQUENCY_COLUMN_NAME;
                        value = tempDTO.getId();
                        break;
                    case Constants.RESET_PRICE_TYPE_LABLE_NAME:
                        value = massUpdateValue.getValue();
                        addItemTable.getItem(object).getItemProperty(Constants.RESET_PRICE_TYPE_PROPERTY).setValue(value);
                        columnName = Constants.RESET_PRICE_TYPE_COLUMN_NAME;
                        break;
                    case Constants.NET_RESET_PRICE_TYPE_LABLE_NAME:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        addItemTable.getItem(object).getItemProperty(Constants.NET_RESET_PRICE_TYPE_PROPERTY).setValue(tempDTO);
                        columnName = Constants.NET_RESET_PRICE_TYPE_COLUMN_NAME;
                        value = tempDTO.getId();
                        break;
                    case Constants.NET_RESET_PRICE_FORMULA_LABLE_NAME:
                        FormulaDTO netResetPriceFormulaDto = (FormulaDTO) massUpdateText.getData();
                        textValue = netResetPriceFormulaDto.getFormulaName();
                        addItemTable.getItem(object).getItemProperty(Constants.NET_RESET_PRICE_FORMULA_PROPERTY).setValue(textValue);
                        columnName = Constants.NET_RESET_PRICE_FORMULA_COLUMN_NAME;
                        value = textValue;
                        break;
                    case Constants.NET_PRICE_TYPE_LABLE_NAME:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        addItemTable.getItem(object).getItemProperty(Constants.NET_PRICE_TYPE_PROPERTY).setValue(tempDTO);
                        columnName = Constants.NET_PRICE_TYPE_COLUMN_NAME;
                        value = tempDTO.getId();
                        break;
                    case Constants.NET_PRICE_TYPE_FORMULA_LABLE_NAME:
                        FormulaDTO netPriceTypeFormulaDto = (FormulaDTO) massUpdateText.getData();
                        textValue = String.valueOf(netPriceTypeFormulaDto.getFormulaName());
                        addItemTable.getItem(object).getItemProperty(Constants.NET_PRICE_TYPE_FORMULA_PROPERTY).setValue(textValue);
                        columnName = Constants.NET_PRICE_TYPE_FORMULA_COLUMN_NAME;
                        value = textValue;
                        break;
                    default:
                        break;
                }
            }
        }
        list.add(columnName);

        list.add(value);

        list.addAll(getResultsInput(selection));
        logic.massUpdateItemDetails(list);
        addItemTable.getContainerLogic().setCurrentPage(addItemTable.getContainerLogic().getCurrentPage()); 
    }

    private void resetMassUpdate() {
        field.setValue(null);
        massStartDate.setValue(null);
        massEndDate.setValue(null);
        massUpdateValue.setValue(null);
    }

    @UiHandler("allItemsBtn")
    public void allItemsButtonLogic(Button.ClickEvent event) {
        AbstractAllItemLookup itemLookup = new AbstractAllItemLookup(selecteditemList);
        UI.getCurrent().addWindow(itemLookup);
    }

    private void loadStatus() {
        CommonUtil.getComboBoxByListName(massUpdateValue, UiUtils.STATUS, false);
    }

    private void loadPriceTolerenceType() {
        CommonUtil.getComboBoxByListName(massUpdateValue, StringConstantsUtil.PRICE_TOLERANCE_TYPE_LABEL, false);
    }

    private void loadPriceToleranceFrequency() {
        CommonUtil.getComboBoxByListName(massUpdateValue, StringConstantsUtil.PRICE_TOLERANCE_FREQUENCY_LABEL, false);
    }

    private void loadPriceToleranceInterval() {
        CommonUtil.getComboBoxByListName(massUpdateValue, StringConstantsUtil.PRICE_TOLERANCE_INTERVAL_LABEL, false);
    }

    public void loadResetTypeListName() {
        CommonUtil.loadComboBoxForGCM(massUpdateValue, Constants.RESET_TYPE_COLUMN_NAME, false);
    }

    public void loadLockedStatusListName() {
        CommonUtil.loadComboBoxForGCM(massUpdateValue, Constants.LOCKED_STATUS_LISTNAME, false);
    }

    public void loadBasePriceTypeListName() {
        CommonUtil.loadComboBoxForGCM(massUpdateValue, Constants.BASE_PRICE_TYPE_COLUMN_NAME, false);
    }
    

    public static void loadPriceType(ComboBox priceType, boolean isFilter) {
        String query = "SELECT ITEM_PRICING_QUALIFIER_SID,ITEM_PRICING_QUALIFIER_NAME FROM ITEM_PRICING_QUALIFIER WHERE ITEM_PRICING_QUALIFIER_NAME IS NOT NULL AND ITEM_PRICING_QUALIFIER_NAME <> '' ";
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        priceType.removeAllItems();
        priceType.setContainerDataSource(null);//removing container
        priceType.setItemCaptionPropertyId(null);
        priceType.addItem(0);
        priceType.setItemCaption(0, isFilter ? GlobalConstants.getShowAll() : GlobalConstants.getSelectOne());
        if (isFilter) {
            priceType.setNullSelectionAllowed(true);
            priceType.setNullSelectionItemId(0);
        } else {
            priceType.setNullSelectionAllowed(false);
        }
        for (Object[] objects : list) {
            priceType.addItem((int) objects[0]);
            priceType.setItemCaption((int) objects[0], objects[1].toString());
        }
    }

    public static List getResultsInput(SelectionDTO selection) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(selection.getButtonMode());
        return input;
    }

    private void LoadComboToTableMap() {
        comboToTableMap.put(Constants.STATUS_FIELD, ConstantsUtil.STATUS);
        comboToTableMap.put(Constants.START_DATE_HEADER, ConstantsUtil.ITEM_START_DATE);
        comboToTableMap.put(Constants.END_DATE_HEADER, ConstantsUtil.ITEM_END_DATE);
        comboToTableMap.put(StringConstantsUtil.CP_START_DATE_LABEL, StringConstantsUtil.CP_START_DATE);
        comboToTableMap.put(StringConstantsUtil.CP_END_DATE_LABEL, StringConstantsUtil.CP_END_DATE);
        comboToTableMap.put(StringConstantsUtil.CONTRACT_PRICE_LABEL, StringConstantsUtil.CONTRACT_PRICE_PROPERTY);
        comboToTableMap.put(StringConstantsUtil.PRICE_LABEL, StringConstantsUtil.PRICE_PROPERTY);
        comboToTableMap.put(Constants.PRICE_PROTECTION_START_DATE_LABEL, Constants.PRICE_PROTECTION_START_DATE_PROPERTY);
        comboToTableMap.put(Constants.PRICE_PROTECTION_END_DATE_LABEL, Constants.PRICE_PROTECTION_END_DATE_PROPERTY);
        comboToTableMap.put(Constants.PRICE_TOLERANCE_LABEL, Constants.PRICE_TOLERANCE_PROPERTY);
        comboToTableMap.put(Constants.PRICE_TOLERANCE_TYPE_LABEL, Constants.PRICE_TOLERANCE_TYPE_PROPERTY);
        comboToTableMap.put(Constants.PRICE_TOLERANCE_FREQUENCY_LABEL, Constants.PRICE_TOLERANCE_FREQUENCY_PROPERTY);
        comboToTableMap.put(Constants.PRICE_TOLERANCE_INTERVAL_LABEL, Constants.PRICE_TOLERANCE_INTERVAL);
        comboToTableMap.put(StringConstantsUtil.BASE_PRICE_LABEL, StringConstantsUtil.BASE_PRICE_PROPERTY);
        comboToTableMap.put(StringConstantsUtil.RS_START_DATE_LABEL_CAPS, StringConstantsUtil.RS_START_DATE_LABEL);
        comboToTableMap.put(StringConstantsUtil.RS_END_DATE_LABEL, StringConstantsUtil.RS_END_DATE_COLUMN);
        comboToTableMap.put(Constants.FORMULA_ID_LABEL, Constants.FORMULA_ID_PROPERTY);
        comboToTableMap.put(Constants.REBATE_PLAN_LABEL, Constants.REBATE_PLAN_PROPERTY);
        comboToTableMap.put(StringConstantsUtil.FORMULA_METHOD_ID_LABEL, StringConstantsUtil.FORMULA_METHOD_ID_PROPERTY);
        comboToTableMap.put(Constants.NEP_COLUMN_NAME, Constants.NEP_PROPERTY);
        comboToTableMap.put(Constants.PRICE_PROTECTION_STATUS_LABEL, Constants.PRICE_PROTECTION_STATUS_PROPERTY);
        comboToTableMap.put(Constants.NEP_FORMULA_LABLE_NAME, Constants.NEP_FORMULA_PROPERTY);
        comboToTableMap.put(Constants.MAX_INCREMENTAL_CHANGE_LABEL, Constants.MAX_INCREMENTAL_CHANGE_PROPERTY);
        comboToTableMap.put(Constants.RESET_ELIGIBLE_LABLE_NAME, Constants.RESET_ELIGIBLE_PROPERTY);
        comboToTableMap.put(Constants.RESET_TYPE_LABEL, Constants.RESET_TYPE_PROPERTY);
        comboToTableMap.put(Constants.RESET_DATE_LABEL, Constants.RESET_DATE_PROPERTY);
        comboToTableMap.put(Constants.RESET_INTERVAL_LABEL, Constants.RESET_INTERVAL_PROPERTY);
        comboToTableMap.put(Constants.RESET_FREQUENCY_LABEL, Constants.RESET_FREQUENCY_PROPERTY);
        comboToTableMap.put(Constants.NET_PRICE_TYPE_LABLE_NAME, Constants.NET_PRICE_TYPE_PROPERTY);
        comboToTableMap.put(Constants.NET_PRICE_TYPE_FORMULA_LABLE_NAME, Constants.NET_PRICE_TYPE_FORMULA_PROPERTY);
        comboToTableMap.put(Constants.RESET_PRICE_TYPE_LABLE_NAME, Constants.RESET_PRICE_TYPE_PROPERTY);
        comboToTableMap.put(Constants.NET_RESET_PRICE_TYPE_LABLE_NAME, Constants.NET_RESET_PRICE_TYPE_PROPERTY);
        comboToTableMap.put(Constants.NET_RESET_PRICE_FORMULA_LABLE_NAME, Constants.NET_RESET_PRICE_FORMULA_PROPERTY);
        comboToTableMap.put(Constants.BASE_PRICE_TYPE_LABLE_NAME, Constants.BASE_PRICE_PROPERTY);
        comboToTableMap.put(Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_LABLE_NAME, Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_PROPERTY);
        comboToTableMap.put(Constants.NET_SUBSEQUENT_PERIOD_PRICE_LABLE_NAME, Constants.NET_SUBSEQUENT_PERIOD_PRICE_PROPERTY);
        comboToTableMap.put(Constants.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA_LABLE_NAME, "netSubsequentPriceFormulaId");
        comboToTableMap.put(Constants.NET_BASELINE_WAC_FORMULA_LABLE_NAME, "netBaselineWacFormulaId");
        comboToTableMap.put(Constants.BASELINE_NET_WAC_LABLE_NAME, Constants.BASELINE_NET_WAC_PROPERTY);
        comboToTableMap.put(Constants.PRICE_TYPE_LABEL, Constants.PRICE_TYPE_PROPERTY);
        comboToTableMap.put(Constants.MEASUREMENT_PRICE_LABLE_NAME, Constants.MEASUREMENT_PRICE_PROPERTY);
    }

    private void LoadTempToTableMap() {
        tempTableMap.put(Constants.STATUS_FIELD, StringConstantsUtil.ITEM_STATUS_COLUMN);
        tempTableMap.put(Constants.START_DATE_HEADER, StringConstantsUtil.START_DATE_COLUMN);
        tempTableMap.put(Constants.END_DATE_HEADER, StringConstantsUtil.END_DATE_COLUMN);
        tempTableMap.put(Constants.ITEM_START_DATE, StringConstantsUtil.START_DATE_COLUMN);
        tempTableMap.put(Constants.ITEM_END_DATE, StringConstantsUtil.END_DATE_COLUMN);
        tempTableMap.put(StringConstantsUtil.CP_START_DATE_LABEL, StringConstantsUtil.CONTRACT_PRICE_START_DATE_COLUMN);
        tempTableMap.put(StringConstantsUtil.CP_END_DATE_LABEL, StringConstantsUtil.CONTRACT_PRICE_END_DATE_COLUMN);
        tempTableMap.put(StringConstantsUtil.CONTRACT_PRICE_LABEL, StringConstantsUtil.CONTRACT_PRICE_COLUMN);
        tempTableMap.put(StringConstantsUtil.PRICE_LABEL, StringConstantsUtil.PRICE_COLUMN);
        tempTableMap.put(Constants.PRICE_PROTECTION_START_DATE_LABEL, StringConstantsUtil.PRICE_PROTECTION_START_DATE_COLUMN);
        tempTableMap.put(Constants.PRICE_PROTECTION_END_DATE_LABEL, StringConstantsUtil.PRICE_PROTECTION_END_DATE_LABEL);
        tempTableMap.put(Constants.PRICE_TOLERANCE_LABEL, StringConstantsUtil.PRICE_TOLERANCE_COLUMN);
        tempTableMap.put(Constants.PRICE_TOLERANCE_TYPE_LABEL, StringConstantsUtil.PRICE_TOLERANCE_TYPE_LABEL);
        tempTableMap.put(Constants.PRICE_TOLERANCE_FREQUENCY_LABEL, StringConstantsUtil.PRICE_TOLERANCE_FREQUENCY_LABEL);
        tempTableMap.put(Constants.PRICE_TOLERANCE_INTERVAL_LABEL, StringConstantsUtil.PRICE_TOLERANCE_INTERVAL_LABEL);
        tempTableMap.put(StringConstantsUtil.BASE_PRICE_LABEL, StringConstantsUtil.BASE_PRICE_COLUMN);
        tempTableMap.put(StringConstantsUtil.RS_START_DATE_LABEL_CAPS, StringConstantsUtil.ITEM_REBATE_START_DATE_LABEL);
        tempTableMap.put(StringConstantsUtil.RS_END_DATE_LABEL, StringConstantsUtil.ITEM_REBATE_END_DATE_LABEL);
        tempTableMap.put(Constants.FORMULA_ID_LABEL, StringConstantsUtil.FORMULA_ID_LABEL);
        tempTableMap.put(Constants.REBATE_PLAN_LABEL, StringConstantsUtil.REBATE_PLAN_SYSTEM_ID_LABEL);
        tempTableMap.put(StringConstantsUtil.FORMULA_METHOD_ID_LABEL, StringConstantsUtil.FORMULA_METHOD_ID_COLUMN);
        tempTableMap.put(Constants.REBATE_AMOUNT_LABEL, StringConstantsUtil.REBATE_AMOUNT_COLUMN);
        tempTableMap.put(Constants.NEP_LABLE_NAME, Constants.NEP_COLUMN_NAME);
        tempTableMap.put(Constants.PRICE_PROTECTION_STATUS_LABEL, Constants.PRICE_PROTECTION_STATUS_COLUMN_NAME);
        tempTableMap.put(Constants.NEP_FORMULA_LABLE_NAME, Constants.NEP_FORMULA_COLUMN_NAME);
        tempTableMap.put(Constants.MAX_INCREMENTAL_CHANGE_LABEL, Constants.MAX_INCREMENTAL_CHANGE_COLUMN_NAME);
        tempTableMap.put(Constants.RESET_ELIGIBLE_LABLE_NAME, Constants.RESET_ELIGIBLE_COLUMN_NAME);
        tempTableMap.put(Constants.RESET_TYPE_LABEL, Constants.RESET_TYPE_COLUMN_NAME);
        tempTableMap.put(Constants.RESET_DATE_LABEL, Constants.RESET_DATE_COLUMN_NAME);
        tempTableMap.put(Constants.RESET_INTERVAL_LABEL, Constants.RESET_INTERVAL_COLUMN_NAME);
        tempTableMap.put(Constants.RESET_FREQUENCY_LABEL, Constants.RESET_FREQUENCY_COLUMN_NAME);
        tempTableMap.put(Constants.NET_PRICE_TYPE_LABLE_NAME, Constants.NET_PRICE_TYPE_COLUMN_NAME);
        tempTableMap.put(Constants.NET_PRICE_TYPE_FORMULA_LABLE_NAME, Constants.NET_PRICE_TYPE_FORMULA_COLUMN_NAME);
        tempTableMap.put(Constants.RESET_PRICE_TYPE_LABLE_NAME, Constants.RESET_PRICE_TYPE_COLUMN_NAME);
        tempTableMap.put(Constants.NET_RESET_PRICE_TYPE_LABLE_NAME, Constants.NET_RESET_PRICE_TYPE_COLUMN_NAME);
        tempTableMap.put(Constants.NET_RESET_PRICE_FORMULA_LABLE_NAME, Constants.NET_RESET_PRICE_FORMULA_COLUMN_NAME);
        tempTableMap.put(Constants.BASE_PRICE_TYPE_LABLE_NAME, Constants.BASE_PRICE_TYPE_COLUMN_NAME);
        tempTableMap.put(Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_LABLE_NAME, Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_COLUMN_NAME);
        tempTableMap.put(Constants.NET_SUBSEQUENT_PERIOD_PRICE_LABLE_NAME, Constants.NET_SUBSEQUENT_PERIOD_PRICE_COLUMN_NAME);
        tempTableMap.put(Constants.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA_LABLE_NAME, Constants.NET_SUBSEQUENT_PRICE_FORMULA_COLUMN_NAME);
        tempTableMap.put(Constants.NET_BASELINE_WAC_FORMULA_LABLE_NAME, Constants.NET_BASELINE_WAC_FORMULA_COLUMN_NAME);
        tempTableMap.put(Constants.BASELINE_NET_WAC_LABLE_NAME, Constants.BASELINE_NET_WAC_COLUMN_NAME);
        tempTableMap.put(Constants.PRICE_TYPE_LABEL, Constants.PRICE_TYPE_COLUMN_NAME);
        tempTableMap.put(Constants.MEASUREMENT_PRICE_LABLE_NAME, Constants.MEASUREMENT_PRICE_COLUMN_NAME);
        tempTableMap.put(Constants.BASELINE_WAC_LABLE_NAME, Constants.BASELINE_WAC_MANUAL_COLUMN_NAME);
        tempTableMap.put(Constants.BASELINE_WAC_LABLE_NAME, Constants.BASELINE_WAC_DATE_COLUMN_NAME);
        tempTableMap.put(Constants.BASELINE_WAC_LABLE_NAME, Constants.BASELINE_WAC_PRICE_TYPE_COLUMN_NAME);
    }

    private void loadFieldAndPropertyMap() {
        fieldAndPropertyMap.put(StringConstantsUtil.ITEM_STATUS_COLUMN, ConstantsUtil.STATUS);
        fieldAndPropertyMap.put(StringConstantsUtil.START_DATE_COLUMN, ConstantsUtil.ITEM_START_DATE);
        fieldAndPropertyMap.put(StringConstantsUtil.END_DATE_COLUMN, ConstantsUtil.ITEM_END_DATE);
        fieldAndPropertyMap.put(StringConstantsUtil.CONTRACT_PRICE_START_DATE_COLUMN, StringConstantsUtil.CP_START_DATE);
        fieldAndPropertyMap.put(StringConstantsUtil.CONTRACT_PRICE_END_DATE_COLUMN, StringConstantsUtil.CP_END_DATE);
        fieldAndPropertyMap.put(StringConstantsUtil.CONTRACT_PRICE_COLUMN, StringConstantsUtil.CONTRACT_PRICE_PROPERTY);
        fieldAndPropertyMap.put(StringConstantsUtil.PRICE_COLUMN, StringConstantsUtil.PRICE_PROPERTY);
        fieldAndPropertyMap.put(StringConstantsUtil.PRICE_PROTECTION_START_DATE_COLUMN, Constants.PRICE_PROTECTION_START_DATE_PROPERTY);
        fieldAndPropertyMap.put(StringConstantsUtil.PRICE_PROTECTION_END_DATE_LABEL, Constants.PRICE_PROTECTION_END_DATE_PROPERTY);
        fieldAndPropertyMap.put(StringConstantsUtil.PRICE_TOLERANCE_COLUMN, Constants.PRICE_TOLERANCE_PROPERTY);
        fieldAndPropertyMap.put(StringConstantsUtil.PRICE_TOLERANCE_TYPE_LABEL, Constants.PRICE_TOLERANCE_TYPE_PROPERTY);
        fieldAndPropertyMap.put(StringConstantsUtil.PRICE_TOLERANCE_FREQUENCY_LABEL, Constants.PRICE_TOLERANCE_FREQUENCY_PROPERTY);
        fieldAndPropertyMap.put(StringConstantsUtil.PRICE_TOLERANCE_INTERVAL_LABEL, Constants.PRICE_TOLERANCE_INTERVAL);
        fieldAndPropertyMap.put(StringConstantsUtil.BASE_PRICE_COLUMN, StringConstantsUtil.BASE_PRICE_PROPERTY);
        fieldAndPropertyMap.put(StringConstantsUtil.ITEM_REBATE_START_DATE_LABEL, StringConstantsUtil.RS_START_DATE_LABEL);
        fieldAndPropertyMap.put(StringConstantsUtil.ITEM_REBATE_END_DATE_LABEL, StringConstantsUtil.RS_END_DATE_COLUMN);
        fieldAndPropertyMap.put(StringConstantsUtil.FORMULA_ID_LABEL, Constants.FORMULA_ID_PROPERTY);
        fieldAndPropertyMap.put(StringConstantsUtil.REBATE_PLAN_SYSTEM_ID_LABEL, Constants.REBATE_PLAN_PROPERTY);
        fieldAndPropertyMap.put(StringConstantsUtil.FORMULA_METHOD_ID_COLUMN, StringConstantsUtil.FORMULA_METHOD_ID_PROPERTY);
        fieldAndPropertyMap.put(StringConstantsUtil.REBATE_AMOUNT_COLUMN, Constants.REBATE_AMOUNT_PROPERTY);
        fieldAndPropertyMap.put(Constants.NEP_COLUMN_NAME, Constants.NEP_PROPERTY);
        fieldAndPropertyMap.put(Constants.PRICE_PROTECTION_STATUS_COLUMN_NAME, Constants.PRICE_PROTECTION_STATUS_PROPERTY);
        fieldAndPropertyMap.put(Constants.NEP_FORMULA_COLUMN_NAME, Constants.NEP_FORMULA_PROPERTY);
        fieldAndPropertyMap.put(Constants.MAX_INCREMENTAL_CHANGE_COLUMN_NAME, Constants.MAX_INCREMENTAL_CHANGE_PROPERTY);
        fieldAndPropertyMap.put(Constants.RESET_ELIGIBLE_COLUMN_NAME, Constants.RESET_ELIGIBLE_PROPERTY);
        fieldAndPropertyMap.put(Constants.RESET_TYPE_COLUMN_NAME, Constants.RESET_TYPE_PROPERTY);
        fieldAndPropertyMap.put(Constants.RESET_DATE_COLUMN_NAME, Constants.RESET_DATE_PROPERTY);
        fieldAndPropertyMap.put(Constants.RESET_INTERVAL_COLUMN_NAME, Constants.RESET_INTERVAL_PROPERTY);
        fieldAndPropertyMap.put(Constants.RESET_FREQUENCY_COLUMN_NAME, Constants.RESET_FREQUENCY_PROPERTY);
        fieldAndPropertyMap.put(Constants.NET_PRICE_TYPE_COLUMN_NAME, Constants.NET_PRICE_TYPE_PROPERTY);
        fieldAndPropertyMap.put(Constants.NET_PRICE_TYPE_FORMULA_COLUMN_NAME, Constants.NET_PRICE_TYPE_FORMULA_PROPERTY);
        fieldAndPropertyMap.put(Constants.RESET_PRICE_TYPE_COLUMN_NAME, Constants.RESET_PRICE_TYPE_PROPERTY);
        fieldAndPropertyMap.put(Constants.NET_RESET_PRICE_TYPE_COLUMN_NAME, Constants.NET_RESET_PRICE_TYPE_PROPERTY);
        fieldAndPropertyMap.put(Constants.NET_RESET_PRICE_FORMULA_COLUMN_NAME, Constants.NET_RESET_PRICE_FORMULA_PROPERTY);
        fieldAndPropertyMap.put(Constants.BASE_PRICE_TYPE_COLUMN_NAME, Constants.BASE_PRICE_PROPERTY);
        fieldAndPropertyMap.put(Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_COLUMN_NAME, Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_PROPERTY);
        fieldAndPropertyMap.put(Constants.NET_SUBSEQUENT_PERIOD_PRICE_COLUMN_NAME, Constants.NET_SUBSEQUENT_PERIOD_PRICE_PROPERTY);
        fieldAndPropertyMap.put(Constants.NET_SUBSEQUENT_PRICE_FORMULA_COLUMN_NAME, "netSubsequentPriceFormulaId");
        fieldAndPropertyMap.put(Constants.NET_BASELINE_WAC_FORMULA_COLUMN_NAME, Constants.NET_BASELINE_WAC_FORMULA_PROPERTY);
        fieldAndPropertyMap.put(Constants.BASELINE_NET_WAC_COLUMN_NAME, Constants.BASELINE_NET_WAC_PROPERTY);
        fieldAndPropertyMap.put(Constants.PRICE_TYPE_COLUMN_NAME, Constants.PRICE_TYPE_PROPERTY);
        fieldAndPropertyMap.put(Constants.MEASUREMENT_PRICE_COLUMN_NAME, Constants.MEASUREMENT_PRICE_PROPERTY);
        fieldAndPropertyMap.put(Constants.BASELINE_WAC_MANUAL_COLUMN_NAME, Constants.BASELINE_WAC_MANUAL_LABLE_NAME);
        fieldAndPropertyMap.put(Constants.BASELINE_WAC_DATE_COLUMN_NAME, Constants.BASELINE_WAC_DATE_LABLE_NAME);
        fieldAndPropertyMap.put(Constants.BASELINE_WAC_PRICE_TYPE_COLUMN_NAME, Constants.BASELINE_WAC_PRICE_TYPE_LABLE_NAME);
    }
    
    public void LoadField() {
        field.addItems(Arrays.asList(ConstantsUtil.MassUpdateConstants.values()));
    }

    private boolean isHavingValue(String columnName) {
        List input = getResultsInput(selection);
        input.add(tempTableMap.get(columnName));
        List<Object[]> list = ItemQueries.getItemData(input, "Mass Update data check", null);
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            return obj == null ? Boolean.TRUE : (Integer) obj == 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        return Boolean.TRUE;

    }

    private void configureSecurityPermissions() {
        try {
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(selection.getUserId()), "GCM-Item Management", "Item Add", "Contract Selection Tab");
            searchBtn.setVisible(CommonLogic.isButtonVisibleAccess("searchBtn", functionHM));
            resetBtn.setVisible(CommonLogic.isButtonVisibleAccess("resetBtn", functionHM));
            populateBtn.setVisible(CommonLogic.isButtonVisibleAccess("populateBtn", functionHM));
            resetBtncur.setVisible(CommonLogic.isButtonVisibleAccess("resetBtncur", functionHM));
            submitBtncur.setVisible(CommonLogic.isButtonVisibleAccess("submitBtncur", functionHM));
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }
    public void loadValueddlbField() {
        massUpdateValue.setVisible(true);
        massStartDate.setVisible(false);
        massEndDate.setVisible(false);
        populateBtn.setVisible(true);
        valuelabel.setVisible(true);
        startdatelabel.setVisible(false);
        enddatelabel.setVisible(false);
        massUpdateText.setVisible(false);
        visibilityOptions();
    }

    public void loadValueddlbTextField() {
        massUpdateValue.setVisible(false);
        massStartDate.setVisible(false);
        massEndDate.setVisible(false);
        startdatelabel.setVisible(false);
        enddatelabel.setVisible(false);
        massUpdateText.setVisible(true);
        populateBtn.setVisible(true);
        valuelabel.setVisible(true);
        visibilityOptions();
    }

    public void loadValueddlbDateField(String processName) {
        massUpdateValue.setVisible(false);
        populateBtn.setVisible(true);
        valuelabel.setVisible(false);
        massUpdateText.setVisible(false);
        visibilityOptions();

        switch (processName) {
            case Constants.ITEM_START_DATE:
                startDateVisibility();
                startdatelabel.setValue(ConstantsUtil.MassUpdateConstants.ITEM_START_DATE.getConstant());
                break;
            case Constants.ITEM_END_DATE:
                endDateVisibility();
                enddatelabel.setValue(ConstantsUtil.MassUpdateConstants.ITEM_END_DATE.getConstant());
                break;
            case StringConstantsUtil.CP_START_DATE_LABEL:
                startDateVisibility();
                startdatelabel.setValue(ConstantsUtil.MassUpdateConstants.CP_START_DATE.getConstant());
                break;
            case StringConstantsUtil.CP_END_DATE_LABEL:
                endDateVisibility();
                enddatelabel.setValue(ConstantsUtil.MassUpdateConstants.CP_END_DATE.getConstant());
                break;
            case Constants.PRICE_PROTECTION_START_DATE_LABEL:
                startDateVisibility();
                startdatelabel.setValue(ConstantsUtil.MassUpdateConstants.PRICE_PRODECTION_START_DATE.getConstant());
                break;
            case Constants.PRICE_PROTECTION_END_DATE_LABEL:
                endDateVisibility();
                enddatelabel.setValue(ConstantsUtil.MassUpdateConstants.PRICE_PRODECTION_END_DATE.getConstant());
                break;
            case Constants.RESET_DATE_LABEL:
                startDateVisibility();
                startdatelabel.setValue(ConstantsUtil.MassUpdateConstants.RESET_DATE.getConstant());
                break;
            default:
                break;
        }
    }

    public void endDateVisibility() {
        massStartDate.setVisible(false);
        massEndDate.setVisible(true);
        startdatelabel.setVisible(false);
        enddatelabel.setVisible(true);
    }

    public void startDateVisibility() {
        massStartDate.setVisible(true);
        massEndDate.setVisible(false);
        startdatelabel.setVisible(true);
        enddatelabel.setVisible(false);
    }
    public void visibilityOptions() {
        baseWacPriceType.setVisible(false);
        baseWacManual.setVisible(false);
        baseWacDate.setVisible(false);
    }
    }
