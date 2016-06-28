
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.add.form;

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
import com.stpl.app.gcm.itemmanagement.itemabstract.form.FormulaLookUp;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.lookup.CFPLookUp;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.lookup.IFPLookUp;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.lookup.PSLookUp;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.lookup.RSLookUp;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.security.SecurityLogic;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.util.UIUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
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
import org.jboss.logging.Logger;
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
    public TextField itemNo;
    @UiField("itemName")
    public TextField itemName;
    @UiField("itemDesc")
    public TextField itemDesc;
    @UiField("therapeuticClass")
    public TextField therapeuticClass;
    @UiField("brand")
    public TextField brand;
    @UiField(Constants.CONTRACT_HOLDER)
    public CustomTextField contractHolder;
    @UiField("ifp")
    public CustomTextField ifp;
    @UiField("cfp")
    public CustomTextField cfp;
    @UiField("contractNo")
    public CustomTextField contractNo;
    @UiField("contractName")
    public CustomTextField contractName;
    @UiField("priceSchedule")
    public CustomTextField priceSchedule;
    @UiField("customerNo")
    public CustomTextField customerNo;
    @UiField("customerName")
    public CustomTextField customerName;
    @UiField("rebateSchedule")
    public CustomTextField rebateSchedule;
    @UiField("number")
    public TextField number;
    @UiField("massUpdateValue")
    public ComboBox massUpdateValue;
    @UiField("marketType")
    public ComboBox marketType_DTO;
    @UiField("typeDdlb")
    public ComboBox typeDdlb;
    @UiField("field")
    public ComboBox field;
    @UiField("startDate")
    public PopupDateField startDate;
    @UiField("endDate")
    public PopupDateField endDate;
    @UiField("fromDate")
    public PopupDateField fromDate;
    @UiField("toDate")
    public PopupDateField toDate;
    @UiField("massStartDate")
    public PopupDateField massStartDate;
    @UiField("massEndDate")
    public PopupDateField massEndDate;
    @UiField("massUpdateRadio")
    public OptionGroup massUpdateRadio;
    @UiField("searchBtn")
    public Button searchBtn;
    @UiField("populateBtn")
    public Button populateBtn;
    @UiField("resetBtncur")
    public Button resetBtncur;
    @UiField("resetBtn")
    public Button resetBtn;
    @UiField("submitBtncur")
    public Button submitBtncur;
    @UiField("contractVertical")
    public VerticalLayout contractVertical;
    @UiField("allItemsBtn")
    public Button allItemsBtn;
    @UiField("allItemsCombo")
    public ComboBox allItemsCombo;
    @UiField("AddItemDetails")
    public Panel AddItemDetails;
    @UiField("componentInformation")
    public Panel componentInformation;
    @UiField("componentInfoLayout")
    VerticalLayout componentInfoLayout;
    @UiField("startdatelabel")
    public Label startdatelabel;
    @UiField("enddatelabel")
    public Label enddatelabel;
    @UiField("valuelabel")
    public Label valuelabel;
    public static final Logger LOGGER = Logger.getLogger(AddContractSelection.class);
    AddItemDetailsTableLogic addItemTableLogic = new AddItemDetailsTableLogic();
    public ExtPagedTable addItemTable = new ExtPagedTable(addItemTableLogic);
    public List<ItemIndexDto> selecteditemList;
    Object[] componentColumn = {"itemNo", "itemName", "therapyClass", "brand", "status", Constants.START_DATE, Constants.END_DATE, "rebatePlan", "formulaId"};
    String[] componentHeader = {Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date", "Rebate Plan", "Formula Id"};
    Object[] addColumn = {Constants.CHECK_RECORD, "projectionIdLink", "workFlowStatus", Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, "status", "itemStartDate", "itemEndDate", "cpStartDate", "cpEndDate", "contractPrice", "price", "priceProtectionStartDate", "priceProtectionEndDate", "priceToleranceType", "priceTolerance", "priceToleranceFrequency", "priceToleranceInterval", "basePrice", "RSStartDate", "RSEndDate", "formulaId", "rebatePlan", "formulaMethodId", "rebateAmount", "cfpNO", Constants.CFP_NAME, "ifpNo", Constants.IFPNAME, "psNo", Constants.PSNAME, "rsNo", Constants.RSNAME, "rarCategory"};
    String[] addHeader = {"", "Projection ID", "WorkFlow Status", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", "Status", Constants.ITEM_START_DATE, Constants.ITEM_END_DATE, "CP Start Date", "CP End Date", "Contract Price", "Price", "Price Protection Start Date", "Price Protection End Date", "Price Tolerance Type", "Price Tolerance", "Price ToleranceFrequency", "Price Tolerance Interval", "Base Price", "RS Start Date", "RS End Date", "Formula ID", "Rebate Plan", "Formula Method ID", "Rebate Amount", "CFP NO", "CFP Name", Constants.IFP_NO, Constants.IfpNAME, "PS No", " PS Name", "RS No", "RS Name", "RAR Category"};
    AbstractLogic logic = AbstractLogic.getInstance();
    AddItemTableDTO binderDto = new AddItemTableDTO();
    private CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<AddItemTableDTO>(binderDto));
    SelectionDTO selection;
    BeanItemContainer<AbstractContractSearchDTO> itemContractContainer = new BeanItemContainer<AbstractContractSearchDTO>(AbstractContractSearchDTO.class);
    AbstractContractSearchDTO componentInfoDTO = new AbstractContractSearchDTO();
    AbstractComponentInfo component = new AbstractComponentInfo(Constants.RS, selection);
    String massUpdateString = StringUtils.EMPTY;
    boolean isFound = false;
    final StplSecurity stplSecurity = new StplSecurity();
    Map<String, AppPermission> functionHM = new HashMap<String, AppPermission>();
    private String userId = VaadinSession.getCurrent().getAttribute(Constants.USER_ID).toString();
    Map comboToTableMap = new HashMap();
    Map tempTableMap = new HashMap();
    Map fieldAndPropertyMap = new HashMap();
    CustomTextField.ClickListener clickLister;
    @UiField("massUpdateText")
    protected CustomTextField massUpdateText;

    public AddContractSelection() {
        addItemTableLogic.setTempPageLength(5);
    }

    /**
     * Get Content
     *
     * @param selecteditemList
     * @param selection
     * @return component
     */
    public Component getContent(List<ItemIndexDto> selecteditemList, final SelectionDTO selection) throws Exception {
        this.selection = selection;
        this.selecteditemList = selecteditemList;
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(Clara.create(getClass().getResourceAsStream("/item/itemContractSelection.xml"), this));
        massUpdateRadio.addItems("Enable", "Disable");
        contractHolder.focus();
        Panel panel = new Panel();
        panel.setContent(layout);
        field.addItem(Constants.SELECT_ONE);
        LoadField();
        LoadComboToTableMap();
        LoadTempToTableMap();
        loadFieldAndPropertyMap();
        field.setImmediate(true);
        field.setNullSelectionAllowed(true);
        field.setNullSelectionItemId(Constants.SELECT_ONE);
        field.select(Constants.SELECT_ONE);
        configureFields();
        component.replaceComponent();
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
        massUpdateRadio.select("Disable");
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
    }

    @UiHandler("field")
    public void fieldTypeLogic(Property.ValueChangeEvent event) throws SystemException, Exception {
        String processName = String.valueOf(field.getValue());
        massUpdateString = processName;
        massUpdateText.setReadOnly(false);
        massUpdateText.setValue(StringUtils.EMPTY);
        switch (processName) {
            case "Status":
                massUpdateValue.setVisible(true);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(false);
                loadStatus();
                break;
            case "Start Date":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(true);
                massEndDate.setVisible(false);
                populateBtn.setVisible(true);
                valuelabel.setVisible(false);
                startdatelabel.setVisible(true);
                startdatelabel.setValue(ConstantsUtil.MassUpdateConstants.START_DATE.getConstant());
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(false);
                break;
            case "End Date":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(true);
                populateBtn.setVisible(true);
                valuelabel.setVisible(false);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(true);
                massUpdateText.setVisible(false);
                enddatelabel.setValue(ConstantsUtil.MassUpdateConstants.END_DATE.getConstant());
                break;
            case "CP Start Date":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(true);
                massEndDate.setVisible(false);
                populateBtn.setVisible(true);
                valuelabel.setVisible(false);
                startdatelabel.setVisible(true);
                massUpdateText.setVisible(false);
                startdatelabel.setValue(ConstantsUtil.MassUpdateConstants.CP_START_DATE.getConstant());
                enddatelabel.setVisible(false);
                break;
            case "CP End Date":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(true);
                populateBtn.setVisible(true);
                valuelabel.setVisible(false);
                startdatelabel.setVisible(false);
                massUpdateText.setVisible(false);
                startdatelabel.setValue(ConstantsUtil.MassUpdateConstants.CP_END_DATE.getConstant());
                enddatelabel.setVisible(true);
                break;
            case "Contract Price":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(true);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                break;
            case "Price":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(true);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                break;
            case "Price Protection Start Date":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(true);
                massEndDate.setVisible(false);
                populateBtn.setVisible(true);
                valuelabel.setVisible(false);
                startdatelabel.setVisible(true);
                massUpdateText.setVisible(false);
                startdatelabel.setValue(ConstantsUtil.MassUpdateConstants.PRICE_PRODECTION_START_DATE.getConstant());
                enddatelabel.setVisible(false);
                break;
            case "Price Protection End Date":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(true);
                populateBtn.setVisible(true);
                valuelabel.setVisible(false);
                startdatelabel.setVisible(false);
                massUpdateText.setVisible(false);
                enddatelabel.setValue(ConstantsUtil.MassUpdateConstants.PRICE_PRODECTION_END_DATE.getConstant());
                enddatelabel.setVisible(true);
                break;
            case "Price Tolerance Type":
                massUpdateValue.setVisible(true);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(false);
                loadPriceTolerenceType();
                break;
            case "Price Tolerance":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(true);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                break;
            case "Price Tolerance Frequency":
                massUpdateValue.setVisible(true);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(false);
                loadPriceToleranceFrequency();
                break;
            case "Price Tolerance Interval":
                massUpdateValue.setVisible(true);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(false);
                loadPriceToleranceInterval();
                break;
            case "Base Price":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(true);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                break;
            case "RS Start Date":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(true);
                massEndDate.setVisible(false);
                populateBtn.setVisible(true);
                valuelabel.setVisible(false);
                startdatelabel.setVisible(true);
                massUpdateText.setVisible(false);
                startdatelabel.setValue(ConstantsUtil.MassUpdateConstants.RS_START_DATE.getConstant());
                enddatelabel.setVisible(false);
                break;
            case "RS End Date":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(true);
                populateBtn.setVisible(true);
                valuelabel.setVisible(false);
                startdatelabel.setVisible(false);
                massUpdateText.setVisible(false);
                enddatelabel.setValue(ConstantsUtil.MassUpdateConstants.RS_END_DATE.getConstant());
                enddatelabel.setVisible(true);
                break;
            case "Formula ID":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(true);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                massUpdateText.addStyleName("searchicon");
                if (clickLister != null) {
                    massUpdateText.removeClickListener(clickLister);
                }

                clickLister = new CustomTextField.ClickListener() {
                    public void click(CustomTextField.ClickEvent event) {
                        FormulaLookUp formulaLookUp = new FormulaLookUp(massUpdateText);
                        formulaLookUp.addCloseListener(new Window.CloseListener() {
                            @Override
                            public void windowClose(Window.CloseEvent e) {
                                massUpdateText.setReadOnly(false);
                                if (massUpdateText.getData() != null) {
                                    FormulaDTO object = (FormulaDTO) massUpdateText.getData();
                                    massUpdateText.setValue(object.getFormulaNo());
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
            case "Rebate Plan":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(true);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                if (clickLister != null) {
                    massUpdateText.removeClickListener(clickLister);
                }
                clickLister = new CustomTextField.ClickListener() {
                    public void click(CustomTextField.ClickEvent event) {
                        final ComponentLookUp contractNum = new ComponentLookUp("Rebate Plan", "Rebate Plan Lookup", massUpdateText);
                        contractNum.addCloseListener(new Window.CloseListener() {
                            @Override
                            public void windowClose(Window.CloseEvent e) {
                                massUpdateText.setReadOnly(false);
                                if (massUpdateText.getData() != null) {
                                    ComponentLookUpDTO object = (ComponentLookUpDTO) massUpdateText.getData();
                                    massUpdateText.setValue(object.getComponentNo());
                                }
                                massUpdateText.setReadOnly(true);
                            }
                        });

                        UI.getCurrent().addWindow(contractNum);
                    }
                };
                massUpdateText.addClickListener(clickLister);
                massUpdateText.setReadOnly(true);
                break;
            case "Formula Method ID":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(true);
                if (clickLister != null) {
                    massUpdateText.removeClickListener(clickLister);
                }
                massUpdateText.removeStyleName("searchicon");
                massUpdateText.setReadOnly(false);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                break;
            case "Rebate Amount":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(true);
                if (clickLister != null) {
                    massUpdateText.removeClickListener(clickLister);
                }
                massUpdateText.removeStyleName("searchicon");
                massUpdateText.setReadOnly(false);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                break;
        }
        massStartDate.setValue(null);
        massEndDate.setValue(null);
        massUpdateValue.setValue(null);
        massUpdateText.setValue(StringUtils.EMPTY);
    }

    @UiHandler("massUpdateRadio")
    public void massTypeLogic(Property.ValueChangeEvent event) throws SystemException {
        String processName = String.valueOf(massUpdateRadio.getValue());
        if ("Disable".equals(processName)) {
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

    @UiHandler("searchBtn")
    public void searchButton(Button.ClickEvent event) throws SystemException, PortalException, Exception {
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

            MessageBox.showPlain(Icon.INFO, "Error", "Please enter/select search criteria", ButtonId.OK);
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
    public void populateButton(Button.ClickEvent event) throws SystemException, PortalException, Exception {
        if (!massUpdateString.isEmpty()) {
            if (massUpdateValue.getValue() != null || massStartDate.getValue() != null || massEndDate.getValue() != null || (massUpdateText.getValue() != null && !massUpdateText.getValue().isEmpty())) {

                isFound = isPresent();
                if (isFound) {
                    populateLogic();
                }
            } else {
                MessageBox.showPlain(Icon.INFO, "Error", "Please enter a " + massUpdateString + " to Mass Update. ", ButtonId.OK);

            }
        } else {
            MessageBox.showPlain(Icon.INFO, "Error", "Please select a Field to Mass Update. ", ButtonId.OK);

        }
        isFound = false;
    }

    @UiHandler("resetBtn")
    public void resetSearchCriteria(Button.ClickEvent event) throws SystemException, PortalException, Exception {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    binder.setItemDataSource(new BeanItem<AddItemTableDTO>(new AddItemTableDTO()));
                    binder.commit();
                } catch (FieldGroup.CommitException ex) {
                    LOGGER.error(ex.getMessage());
                }
            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage("Confirmation", "Are you sure you want to reset the values in the ITEM Search?");

    }

    @UiHandler("resetBtncur")
    public void resetcurButton(Button.ClickEvent event) throws SystemException, PortalException, Exception {
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
            }
        }.getConfirmationMessage("Confirmation", "Are you sure you want to reset the values in the Add Item Details list view?");

    }

    @UiHandler("submitBtncur")
    public void submitButtonLogic(Button.ClickEvent event) throws SystemException, PortalException, Exception {

        submitButtonLogic();
    }
    boolean isSubmit = false;

    public boolean submitButtonLogic() {
        if (submitButtonCheck()) {
            new AbstractNotificationUtils() {
                @Override
                public void yesMethod() {
                    try {
                        List input = logic.getResultsInput(selection);
                        ItemQueries.itemUpdate(input, "Submitting the contract");
                        selection.getLookup().changeTab();
                        isSubmit = true;
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }

                @Override
                public void noMethod() {
                    isSubmit = false;
                }
            }.getConfirmationMessage("Confirmation", "Are you sure you want to submit the selected contracts?");
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
        LOGGER.info("submitButtonCheck :::: list-->>" + list.size());
        if (AbstractLogic.getCount(list) == 0) {
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
        addItemTableLogic.setPageLength(5);
        addItemTableLogic.setContainerDataSource(itemContractContainer);
        addItemTableLogic.sinkItemPerPageWithPageLength(false);
        addItemTable.setVisibleColumns(addColumn);
        addItemTable.setColumnHeaders(addHeader);
        addItemTable.setFilterBarVisible(true);
        addItemTable.addStyleName(ConstantsUtil.FILTERCOMBOBOX);
        addItemTable.setPageLength(5);
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
        addItemTable.setWidth("1878");
        addItemTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        contractVertical.addComponent(addItemTable);
        HorizontalLayout controls = addItemTableLogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        contractVertical.addComponent(controlLayout);
        addItemTable.setTableFieldFactory(new AddItemContractFieldFactory(selection, addItemTable, fieldAndPropertyMap));
        addItemTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
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
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                if (originatingField instanceof ComboBox) {
                    if (originatingField.getValue() != null) {
                        HelperDTO dto = (HelperDTO) originatingField.getValue();

                        return new SimpleStringFilter(propertyId, String.valueOf(dto.getId()), false, false);
                    } else {
                        return null;
                    }
                }
                return null;
            }

            public void filterRemoved(Object propertyId) {
            }

            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
            }

            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                if (Constants.CHECK_RECORD.equals(propertyId)) {
                    CustomTextField text = new CustomTextField();
                    text.setEnabled(false);
                    text.setImmediate(true);
                    return text;
                }
                if (Constants.MARKET_TYPE.equals(propertyId)) {
                    ComboBox marketTypeDdlb = new ComboBox();
                    logic.LazyLoadDdlb(marketTypeDdlb, "Load Market Type Count", "Load Market Type", true);
                    return marketTypeDdlb;
                }
                if ("status".equals(propertyId)) {
                    ComboBox statusDdlb = new ComboBox();
                    logic.LazyLoadDdlb(statusDdlb, "Load Item Status Count", "Load Item Status", true);
                    return statusDdlb;
                }
                if ("priceToleranceInterval".equals(propertyId)) {
                    ComboBox pricetolerenceintDdlb = new ComboBox();
                    logic.LazyLoadDdlb(pricetolerenceintDdlb, "Load PS_INTERVAL Count", "Load PS_INTERVAL", true);
                    return pricetolerenceintDdlb;
                }
                if ("priceToleranceType".equals(propertyId)) {
                    ComboBox pricetolerencetypeDdlb = new ComboBox();
                    logic.LazyLoadDdlb(pricetolerencetypeDdlb, "Load PS_TYPE Count", "Load PS_TYPE", true);
                    return pricetolerencetypeDdlb;
                }
                if ("priceToleranceFrequency".equals(propertyId)) {
                    ComboBox pricetolerencefreqDdlb = new ComboBox();
                    logic.LazyLoadDdlb(pricetolerencefreqDdlb, "Load PS_FREQ Count", "Load PS_FREQ", true);
                    return pricetolerencefreqDdlb;
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
            public void valueChange(final Property.ValueChangeEvent event) {
                resultsItemClick(event.getProperty().getValue());
            }
        });
        return addItemTable;
    }

    @UiHandler(Constants.CONTRACT_HOLDER)
    public void contractHolder(CustomTextField.ClickEvent event) {
        ComponentLookUp chHolder = new ComponentLookUp("Contract Holder", "Contract Holder Lookup", contractHolder);
        chHolder.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (contractHolder.getData() != null) {
                    ComponentLookUpDTO object = (ComponentLookUpDTO) contractHolder.getData();
                    contractHolder.setValue(object.getComponentName());
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
        CFPLookUp cfpObj = new CFPLookUp(Constants.CFP, cfp);
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
        ComponentLookUp contractNum = new ComponentLookUp("Contract", "Contract Lookup", contractNo);
        contractNum.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (contractNo.getData() != null) {
                    ComponentLookUpDTO object = (ComponentLookUpDTO) contractNo.getData();
                    contractNo.setValue(object.getComponentNo());
                    contractName.setValue(object.getComponentNo());
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
        IFPLookUp ifpObj = new IFPLookUp(Constants.IFP, ifp);
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
        ComponentLookUp contractNameObj = new ComponentLookUp("Contract", "Contract Lookup", contractName);
        contractNameObj.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (contractName.getData() != null) {
                    ComponentLookUpDTO object = (ComponentLookUpDTO) contractName.getData();
                    contractNo.setValue(object.getComponentNo());
                    contractName.setValue(object.getComponentNo());
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
        PSLookUp ps = new PSLookUp(Constants.PS, priceSchedule);
        ps.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (priceSchedule.getData() != null) {
                    ComponentLookUpDTO object = (ComponentLookUpDTO) priceSchedule.getData();
                    priceSchedule.setValue(object.getComponentName());
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
        ComponentLookUp companyNo = new ComponentLookUp("Customer", "Customer Lookup", customerNo);
        companyNo.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (customerNo.getData() != null) {
                    ComponentLookUpDTO object = (ComponentLookUpDTO) customerNo.getData();
                    customerNo.setValue(object.getComponentNo());
                    customerName.setValue(object.getComponentName());
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
        ComponentLookUp companyName = new ComponentLookUp("Customer", "Customer Lookup", customerName);
        companyName.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (customerName.getData() != null) {
                    ComponentLookUpDTO object = (ComponentLookUpDTO) customerName.getData();
                    customerNo.setValue(object.getComponentNo());
                    customerName.setValue(object.getComponentName());
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
            RSLookUp rs = new RSLookUp(Constants.RS, rebateSchedule);
            rs.addCloseListener(new Window.CloseListener() {
                @Override
                public void windowClose(Window.CloseEvent e) {
                    if (rebateSchedule.getData() != null) {
                        ComponentLookUpDTO object = (ComponentLookUpDTO) rebateSchedule.getData();
                        rebateSchedule.setValue(object.getComponentName());
                        binderDto.setRs_SID(object.getMasterSid());
                    }
                }
            });

            UI.getCurrent().addWindow(rs);
        } catch (Exception ex) {
            LOGGER.error("error" + ex.getMessage());
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
     * @return CustomFieldGroup
     */
    private CustomFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<AddItemTableDTO>(binderDto));
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
                isFound = true;
                if (!isHavingValue(massUpdateString)) {
                    new AbstractNotificationUtils() {
                        @Override
                        public void yesMethod() {

                            populateLogic();
                        }

                        @Override
                        public void noMethod() {
                        }
                    }.getConfirmationMessage("Confirmation", "There are values in these fields already. Are you sure you want to replace them?");
                    break;
                }

            }
        }
        if (isChecked) {
            MessageBox.showPlain(Icon.INFO, "Error", "Please select at least one contract to apply the Mass Update to. ", ButtonId.OK);
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
        String columnName = StringUtils.EMPTY;
        String textValue = StringUtils.EMPTY;
        HelperDTO tempDTO;
        Date tempDdate;
        for (Object object : itemId) {
            AbstractContractSearchDTO dto = (AbstractContractSearchDTO) object;
            if (dto.getCheckRecord()) {
                switch (massUpdateString) {
                    case "Status":
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        addItemTable.getItem(object).getItemProperty("status").setValue(tempDTO);
                        columnName = "ITEM_STATUS";
                        value = tempDTO.getId();
                        break;
                    case "Start Date":
                        addItemTable.getItem(object).getItemProperty("itemStartDate").setValue(massStartDate.getValue());
                        columnName = "START_DATE";
                        value = CommonUtils.DBDate.format(massStartDate.getValue());
                        break;
                    case "End Date":
                        tempDdate = dto.getItemStartDate();
                        if (tempDdate != null && massEndDate.getValue().before(tempDdate)) {
                            MessageBox.showPlain(Icon.INFO, "Error", "The End Date must come after the Start Date. Please reenter the End Date. ", ButtonId.OK);
                            break;
                        } else {
                            addItemTable.getItem(object).getItemProperty("itemEndDate").setValue(massEndDate.getValue());
                            columnName = "END_DATE";
                            value = CommonUtils.DBDate.format(massEndDate.getValue());

                        }
                        break;
                    case "CP Start Date":
                        addItemTable.getItem(object).getItemProperty("cpStartDate").setValue(massStartDate.getValue());
                        columnName = "CONTRACT_PRICE_START_DATE";
                        value = CommonUtils.DBDate.format(massStartDate.getValue());
                        break;
                    case "CP End Date":
                        tempDdate = dto.getCpStartDate();
                        if (tempDdate != null && massEndDate.getValue().before(tempDdate)) {
                            MessageBox.showPlain(Icon.INFO, "Error", "The End Date must come after the Start Date. Please reenter the End Date. ", ButtonId.OK);
                            break;
                        } else {
                            addItemTable.getItem(object).getItemProperty("cpEndDate").setValue(massEndDate.getValue());
                            columnName = "CONTRACT_PRICE_END_DATE";
                            value = CommonUtils.DBDate.format(massEndDate.getValue());

                        }
                        break;
                    case "Contract Price":
                        textValue = massUpdateText.getValue();
                        addItemTable.getItem(object).getItemProperty("contractPrice").setValue(textValue);
                        columnName = "CONTRACT_PRICE";
                        value = textValue;
                        break;
                    case "Price":
                        textValue = massUpdateText.getValue();
                        addItemTable.getItem(object).getItemProperty("price").setValue(textValue);
                        columnName = "PRICE";
                        value = textValue;
                        break;
                    case "Price Protection Start Date":
                        addItemTable.getItem(object).getItemProperty("priceProtectionStartDate").setValue(massStartDate.getValue());
                        columnName = "PRICE_PROTECTION_START_DATE";
                        value = CommonUtils.DBDate.format(massStartDate.getValue());
                        break;

                    case "Price Protection End Date":
                        tempDdate = dto.getPriceProtectionStartDate();
                        if (tempDdate != null && massEndDate.getValue().before(tempDdate)) {
                            MessageBox.showPlain(Icon.INFO, "Error", "The End Date must come after the Start Date. Please reenter the End Date. ", ButtonId.OK);
                            break;
                        } else {
                            addItemTable.getItem(object).getItemProperty("priceProtectionEndDate").setValue(massEndDate.getValue());
                            columnName = "PRICE_PROTECTION_END_DATE";
                            value = CommonUtils.DBDate.format(massEndDate.getValue());

                        }
                        break;
                    case "Price Tolerance Type":
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        addItemTable.getItem(object).getItemProperty("priceToleranceType").setValue(tempDTO);
                        columnName = "PRICE_TOLERANCE_TYPE";
                        value = tempDTO.getId();
                        break;
                    case "Price Tolerance":
                        textValue = massUpdateText.getValue();
                        addItemTable.getItem(object).getItemProperty("priceTolerance").setValue(textValue);
                        columnName = "PRICE_TOLERANCE";
                        value = textValue;
                        break;
                    case "Price Tolerance Frequency":
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        addItemTable.getItem(object).getItemProperty("priceToleranceFrequency").setValue(tempDTO);
                        columnName = "PRICE_TOLERANCE_FREQUENCY";
                        value = tempDTO.getId();
                        break;
                    case "Price Tolerance Interval":
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        addItemTable.getItem(object).getItemProperty("priceToleranceInterval").setValue(tempDTO);
                        columnName = "PRICE_TOLERANCE_INTERVAL";
                        value = tempDTO.getId();
                        break;
                    case "Base Price":
                        textValue = massUpdateText.getValue();
                        addItemTable.getItem(object).getItemProperty("basePrice").setValue(textValue);
                        columnName = "BASE_PRICE";
                        value = textValue;
                        break;
                    case "RS Start Date":
                        addItemTable.getItem(object).getItemProperty("RSStartDate").setValue(massStartDate.getValue());
                        columnName = "ITEM_REBATE_START_DATE";
                        value = CommonUtils.DBDate.format(massStartDate.getValue());
                        break;
                    case "RS End Date":
                        tempDdate = dto.getRSStartDate();
                        if (tempDdate != null && massEndDate.getValue().before(tempDdate)) {
                            MessageBox.showPlain(Icon.INFO, "Error", "The End Date must come after the Start Date. Please reenter the End Date. ", ButtonId.OK);
                            break;
                        } else {
                            addItemTable.getItem(object).getItemProperty("RSEndDate").setValue(massEndDate.getValue());
                            columnName = "ITEM_REBATE_END_DATE";
                            value = CommonUtils.DBDate.format(massEndDate.getValue());

                        }
                        break;
                    case "Formula ID":

                        FormulaDTO formulaDto = (FormulaDTO) massUpdateText.getData();
                        textValue = String.valueOf(formulaDto.getFormulaNo());
                        addItemTable.getItem(object).getItemProperty("formulaId").setValue(textValue);
                        columnName = "FORMULA_ID";
                        value = formulaDto.getFormulaSid();
                        break;
                    case "Rebate Plan":
                        massUpdateText.addStyleName("searchicon");
                        ComponentLookUpDTO rebateDto = (ComponentLookUpDTO) massUpdateText.getData();
                        textValue = rebateDto.getComponentNo();
                        addItemTable.getItem(object).getItemProperty("rebatePlan").setValue(textValue);
                        columnName = "REBATE_PLAN_SYSTEM_ID";
                        value = rebateDto.getComponentSid();
                        break;
                    case "Formula Method ID":
                        textValue = massUpdateText.getValue();
                        addItemTable.getItem(object).getItemProperty("formulaMethodId").setValue(textValue);
                        columnName = "FORMULA_METHOD_ID";
                        value = textValue;
                        break;
                    case "Rebate Amount":
                        textValue = massUpdateText.getValue();
                        addItemTable.getItem(object).getItemProperty("rebateAmount").setValue(textValue);
                        columnName = "REBATE_AMOUNT";
                        value = textValue;
                        break;
                }
            }
        }
        list.add(columnName);

        list.add(value);

        list.addAll(getResultsInput(selection));
        logic.massUpdateItemDetails(list, selection);
    }

    private void resetMassUpdate() {
        field.setValue(null);
        massStartDate.setValue(null);
        massEndDate.setValue(null);
        massUpdateValue.setValue(null);
    }

    /*
     * Security
     */
    private void setButtonSecurity() {
        SecurityLogic.isPermitted(functionHM, "searchBtn", searchBtn);
        SecurityLogic.isPermitted(functionHM, "resetBtn", resetBtn);
        SecurityLogic.isPermitted(functionHM, "populateBtn", populateBtn);
        SecurityLogic.isPermitted(functionHM, "resetBtncur", resetBtncur);
        SecurityLogic.isPermitted(functionHM, "submitBtncur", submitBtncur);
    }

    @UiHandler("allItemsBtn")
    public void allItemsButtonLogic(Button.ClickEvent event) throws SystemException {
        AbstractAllItemLookup itemLookup = new AbstractAllItemLookup(selecteditemList);
        UI.getCurrent().addWindow(itemLookup);
    }

    private void loadStatus() throws Exception {
        CommonUtil.getComboBoxByListName(massUpdateValue, UIUtils.STATUS, false);
    }

    private void loadPriceTolerenceType() throws Exception {
        CommonUtil.getComboBoxByListName(massUpdateValue, "PRICE_TOLERANCE_TYPE", false);
    }

    private void loadPriceToleranceFrequency() throws Exception {
        CommonUtil.getComboBoxByListName(massUpdateValue, "PRICE_TOLERANCE_FREQUENCY", false);
    }

    private void loadPriceToleranceInterval() throws Exception {
        CommonUtil.getComboBoxByListName(massUpdateValue, "PRICE_TOLERANCE_INTERVAL", false);
    }

    public static List getResultsInput(SelectionDTO selection) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(selection.getButtonMode());
        return input;
    }

    private void LoadComboToTableMap() {
        comboToTableMap.put("Status", ConstantsUtil.STATUS);
        comboToTableMap.put("Start Date", ConstantsUtil.ITEM_START_DATE);
        comboToTableMap.put("End Date", ConstantsUtil.ITEM_END_DATE);
        comboToTableMap.put("CP Start Date", "cpStartDate");
        comboToTableMap.put("CP End Date", "cpEndDate");
        comboToTableMap.put("Contract Price", "contractPrice");
        comboToTableMap.put("Price", "price");
        comboToTableMap.put("Price Protection Start Date", "priceProtectionStartDate");
        comboToTableMap.put("Price Protection End Date", "priceProtectionEndDate");
        comboToTableMap.put("Price Tolerance", "priceTolerance");
        comboToTableMap.put("Price Tolerance Type", "priceToleranceType");
        comboToTableMap.put("Price Tolerance Frequency", "priceToleranceFrequency");
        comboToTableMap.put("Price Tolerance Interval", "priceToleranceInterval");
        comboToTableMap.put("Base Price", "basePrice");
        comboToTableMap.put("RS Start Date", "RSStartDate");
        comboToTableMap.put("RS End Date", "RSEndDate");
        comboToTableMap.put("Formula ID", "formulaId");
        comboToTableMap.put("Rebate Plan", "rebatePlan");
        comboToTableMap.put("Formula Method ID", "formulaMethodId");
        comboToTableMap.put("Rebate Amount", "rebateAmount");
    }

    private void LoadTempToTableMap() {
        tempTableMap.put("Status", "ITEM_STATUS");
        tempTableMap.put("Start Date", "START_DATE");
        tempTableMap.put("End Date", "END_DATE");
        tempTableMap.put("CP Start Date", "CONTRACT_PRICE_START_DATE");
        tempTableMap.put("CP End Date", "CONTRACT_PRICE_END_DATE");
        tempTableMap.put("Contract Price", "CONTRACT_PRICE");
        tempTableMap.put("Price", "PRICE");
        tempTableMap.put("Price Protection Start Date", "PRICE_PROTECTION_START_DATE");
        tempTableMap.put("Price Protection End Date", "PRICE_PROTECTION_END_DATE");
        tempTableMap.put("Price Tolerance", "PRICE_TOLERANCE");
        tempTableMap.put("Price Tolerance Type", "PRICE_TOLERANCE_TYPE");
        tempTableMap.put("Price Tolerance Frequency", "PRICE_TOLERANCE_FREQUENCY");
        tempTableMap.put("Price Tolerance Interval", "PRICE_TOLERANCE_INTERVAL");
        tempTableMap.put("Base Price", "BASE_PRICE");
        tempTableMap.put("RS Start Date", "ITEM_REBATE_START_DATE");
        tempTableMap.put("RS End Date", "ITEM_REBATE_END_DATE");
        tempTableMap.put("Formula ID", "FORMULA_ID");
        tempTableMap.put("Rebate Plan", "REBATE_PLAN_SYSTEM_ID");
        tempTableMap.put("Formula Method ID", "FORMULA_METHOD_ID");
        tempTableMap.put("Rebate Amount", "REBATE_AMOUNT");
    }

    private void loadFieldAndPropertyMap() {
        fieldAndPropertyMap.put("ITEM_STATUS", ConstantsUtil.STATUS);
        fieldAndPropertyMap.put("START_DATE", ConstantsUtil.ITEM_START_DATE);
        fieldAndPropertyMap.put("END_DATE", ConstantsUtil.ITEM_END_DATE);
        fieldAndPropertyMap.put("CONTRACT_PRICE_START_DATE", "cpStartDate");
        fieldAndPropertyMap.put("CONTRACT_PRICE_END_DATE", "cpEndDate");
        fieldAndPropertyMap.put("CONTRACT_PRICE", "contractPrice");
        fieldAndPropertyMap.put("PRICE", "price");
        fieldAndPropertyMap.put("PRICE_PROTECTION_START_DATE", "priceProtectionStartDate");
        fieldAndPropertyMap.put("PRICE_PROTECTION_END_DATE", "priceProtectionEndDate");
        fieldAndPropertyMap.put("PRICE_TOLERANCE", "priceTolerance");
        fieldAndPropertyMap.put("PRICE_TOLERANCE_TYPE", "priceToleranceType");
        fieldAndPropertyMap.put("PRICE_TOLERANCE_FREQUENCY", "priceToleranceFrequency");
        fieldAndPropertyMap.put("PRICE_TOLERANCE_INTERVAL", "priceToleranceInterval");
        fieldAndPropertyMap.put("BASE_PRICE", "basePrice");
        fieldAndPropertyMap.put("ITEM_REBATE_START_DATE", "RSStartDate");
        fieldAndPropertyMap.put("ITEM_REBATE_END_DATE", "RSEndDate");
        fieldAndPropertyMap.put("FORMULA_ID", "formulaId");
        fieldAndPropertyMap.put("REBATE_PLAN_SYSTEM_ID", "rebatePlan");
        fieldAndPropertyMap.put("FORMULA_METHOD_ID", "formulaMethodId");
        fieldAndPropertyMap.put("REBATE_AMOUNT", "rebateAmount");
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

}
