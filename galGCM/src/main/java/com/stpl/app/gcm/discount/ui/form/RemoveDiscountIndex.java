
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.ui.form;

import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.common.QueryUtils;
import com.stpl.app.gcm.discount.dto.ContractsDetailsDto;
import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import com.stpl.app.gcm.discount.logic.DiscountLogic;
import com.stpl.app.gcm.discount.logic.RebateTableLogic;
import com.stpl.app.gcm.discount.ui.layout.AddDiscountWindow;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentLookUpDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.ComponentLookUp;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.ADD_DISCOUNT;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author vigneshkanna
 */
public class RemoveDiscountIndex extends CustomComponent implements View {

    private SessionDTO session = new SessionDTO();
    @UiField("promoteTpToChDtoTableLayout")
    public VerticalLayout promoteTpToChDtoTableLayout;
    @UiField("marketType")
    public ComboBox marketType;
    @UiField(Constants.CONTRACT_HOLDER)
    public CustomTextField contractHolder;
    @UiField("cfpName")
    public CustomTextField cfpName;
    @UiField("contractNo")
    public TextField contractNo;
    @UiField("contractName")
    public TextField contractName;
    @UiField("ifpName")
    public CustomTextField ifpName;
    @UiField("psName")
    public CustomTextField psName;
    @UiField("customer")
    public CustomTextField customer;
    @UiField("item")
    public CustomTextField item;
    @UiField("rsName")
    public CustomTextField rsName;
    @UiField("contractstartDate")
    public PopupDateField contractstartDate;
    @UiField("contractendDate")
    public PopupDateField contractendDate;
    @UiField("aliastype")
    public ComboBox aliastype;
    @UiField("aliasnumber")
    public TextField aliasnumber;
    @UiField("aliasStartDate")
    public PopupDateField aliasStartDate;
    @UiField("aliasEndDate")
    public PopupDateField aliasEndDate;
    @UiField("resetBtn")
    public Button resetBtn;
    @UiField("removeBtn")
    public Button removeBtn;
    @UiField("addBtn")
    public Button addBtn;
    @UiField("searchBtn")
    public Button searchBtn;
    RebateTableLogic tableLogic = new RebateTableLogic();
    private ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    final List<RemoveDiscountDto> selecteditemList = new ArrayList<RemoveDiscountDto>();
    private static final Logger LOGGER = Logger.getLogger(RemoveDiscountIndex.class);
    public String screenName = StringUtils.EMPTY;
    /* The bean used to load Start Period */
    final private BeanItemContainer<String> marketTypeBean = new BeanItemContainer<String>(String.class);
    DiscountLogic discountLogic = new DiscountLogic();
    RemoveDiscountDto removeDiscountDto = new RemoveDiscountDto();
    CommonUtil commonUtil = CommonUtil.getInstance();
    UiUtils UIUtils = new UiUtils();
    /**
     * Binder for DataSelection.
     */
    final private CustomFieldGroup discountChBinder = new CustomFieldGroup(new BeanItem<RemoveDiscountDto>(removeDiscountDto));
    /**
     * The table bean id.
     */
    private Object tableBeanId;
    /**
     * The tree bean id.
     */
    private Object treeBeanId;
    /**
     * The table bean.
     */
    private ContractsDetailsDto tableBean;
    private static final BeanItem<?> NULL_OBJECT = null;
    /**
     * Bean container for result table.
     */
    private BeanItemContainer<RemoveDiscountDto> resultsContainer = new BeanItemContainer<RemoveDiscountDto>(RemoveDiscountDto.class);

    /**
     * The results lazy container.
     */
    QueryUtils queryUtils = new QueryUtils();
    List<String> marketTypeList = new ArrayList<String>();

    public RemoveDiscountIndex(String screenName, SessionDTO session) throws Exception {
        this.screenName = screenName;
        this.session = session;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/discount/removeDiscountIndex.xml"), this));
        configureFields();
        getBinder();
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     * @throws StplR2Exception the stpl r2 exception
     */
    private CustomFieldGroup getBinder() {
        LOGGER.info("Entering getBinder");
        discountChBinder.bindMemberFields(this);
        discountChBinder.setItemDataSource(new BeanItem<RemoveDiscountDto>(new RemoveDiscountDto()));
        discountChBinder.setBuffered(true);
        LOGGER.info("Ending getBinder");
        return discountChBinder;
    }

    /**
     * Configure Scheduled Processes Table
     *
     */
    protected void configureFields() {
        try {
            if (ADD_DISCOUNT.getConstant().equals(screenName)) {
                addBtn.setEnabled(true);
                removeBtn.setEnabled(false);
            } else {
                addBtn.setEnabled(false);
                removeBtn.setEnabled(true);
            }
            marketTypeBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            marketTypeBean.addAll(discountLogic.getMarketType());
            marketType.setImmediate(true);
            marketType.setContainerDataSource(marketTypeBean);
            marketType.setNullSelectionAllowed(true);
            marketType.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            marketType.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            promoteTpToChDtoTableLayout.addComponent(resultsTable);
            promoteTpToChDtoTableLayout.addComponent(tableLogic.createControls());
            tableLogic.setContainerDataSource(resultsContainer);
            tableLogic.setPageLength(10);
            tableLogic.sinkItemPerPageWithPageLength(false);
            contractstartDate.setImmediate(true);
            contractstartDate.setValidationVisible(true);
            contractstartDate.setDateFormat(Constants.DATE_FORMAT);
            contractstartDate.addStyleName(Constants.DATE_FIEILD_CENTER);
            contractendDate.setImmediate(true);
            contractendDate.setValidationVisible(true);
            contractendDate.setDateFormat(Constants.DATE_FORMAT);
            contractendDate.addStyleName(Constants.DATE_FIEILD_CENTER);
            BeanItemContainer<HelperDTO> temp = new BeanItemContainer<HelperDTO>(HelperDTO.class);
            commonUtil.loadComboBox(aliastype, UiUtils.CONTRACT_ALIAS_TYPE, false);
            configureAccrualResultsTable();
            aliasStartDate.setImmediate(true);
            aliasStartDate.setValidationVisible(true);
            aliasStartDate.setDateFormat(Constants.DATE_FORMAT);
            aliasStartDate.addStyleName(Constants.DATE_FIEILD_CENTER);
            aliasEndDate.setImmediate(true);
            aliasEndDate.setValidationVisible(true);
            aliasEndDate.setDateFormat(Constants.DATE_FORMAT);
            aliasEndDate.addStyleName(Constants.DATE_FIEILD_CENTER);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    /**
     * Configuring the Accrual Results Table
     *
     */
    public void configureAccrualResultsTable() {
        resultsTable.setContainerDataSource(resultsContainer);
        resultsTable.setEditable(true);
        resultsTable.setVisibleColumns(Constants.DISCOUNT_SEARCH_COLUMNS);
        resultsTable.setColumnHeaders(Constants.DISCOUNT_SEARCH_HEADERS);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultsTable.setColumnCheckBox(Constants.CHECK_RECORD, Boolean.TRUE);
        for (Object propertyId : resultsTable.getVisibleColumns()) {
            resultsTable.setColumnWidth(propertyId, 168);
        }

        Object[] objColumn = Constants.DISCOUNT_SEARCH_COLUMNS;
        for (Object objColumn1 : objColumn) {
            String value = objColumn1.toString();
            if (value.endsWith("Date")) {
                resultsTable.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
                resultsTable.setConverter(value, new DateToStringConverter());
            }
        }
        resultsTable.setFilterGenerator(new ExtFilterGenerator() {

            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                if (originatingField instanceof ComboBox) {
                    if (originatingField.getValue() != null) {
                        return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
                    } else {
                        return null;
                    }
                }
                return null;
            }

            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                if (Constants.MARKET_TYPE.equals(propertyId)) {
                    ComboBox marketType = new ComboBox();
                    List resultList = discountLogic.getMarketType();
                    new DiscountLogic().setIdDescription(resultList, marketType);
                    resultList.clear();
                    return marketType;
                }

                if (propertyId.equals("checkRecord")) {
                    TextField checkRec = new TextField();
                    checkRec.setEnabled(false);
                    checkRec.setWidth("100");
                    return checkRec;
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
        });
        resultsTable.setTableFieldFactory(new TableFieldFactory() {
            public Field<?> createField(Container container, final Object itemId, final Object propertyId, Component uiContext) {

                if (propertyId.equals(Constants.CHECK_RECORD)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();

                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            boolean isCheck = ((CheckBox) event.getComponent()).getValue();
                            insertIntoTable(itemId, isCheck);
                            if (isCheck) {
                                if (!selecteditemList.contains(itemId)) {
                                    selecteditemList.add((RemoveDiscountDto) itemId);
                                }
                            } else {
                                selecteditemList.remove(itemId);
                            }
                        }
                    });
                    return check;
                }
                return null;
            }
        });

        resultsTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                for (RemoveDiscountDto temp : resultsContainer.getItemIds()) {
                    resultsContainer.getItem(temp).getItemProperty(event.getPropertyId()).setValue(event.isChecked());
                    if (!selecteditemList.contains(temp)) {
                        selecteditemList.add(temp);
                    }
                }
                if (!event.isChecked()) {
                    selecteditemList.clear();
                }
            }
        });

        for (Object property : resultsTable.getVisibleColumns()) {
            if (String.valueOf(property).contains(Constants.CHECK_RECORD)) {
                resultsTable.setColumnWidth(property, 60);
            }
        }
        resultsTable.setFilterBarVisible(true);
        resultsTable.addStyleName("filterbar");
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
    }

    /**
     * Update Type Logic
     *
     * @param event
     */
    @UiHandler("removeBtn")
    public void removeBtnClick(Button.ClickEvent event) {
        LOGGER.info("Entered remove method" + selecteditemList.size());
        boolean check = true;
        if (selecteditemList.size() > 0) {
            int contractSys = selecteditemList.get(0).getContractSid();
            for (int i = 0; i < selecteditemList.size(); i++) {
                if (contractSys != selecteditemList.get(i).getContractSid()) {
                    check = false;
                    break;
                }
            }
            if (check) {
                RemoveDiscountLookUp removeDiscount = new RemoveDiscountLookUp(selecteditemList, removeDiscountDto.getUserId(), removeDiscountDto.getSessionId());
                UI.getCurrent().addWindow(removeDiscount);
            } else {
                AbstractNotificationUtils.getErrorNotification("Different Contracts Selected",
                        "Please ensure all checked Contracts have the same Contract Number.");
            }

        } else {
            AbstractNotificationUtils.getErrorNotification("No Records Selected",
                    "Please checkmark a Contract to continue.");
        }
        LOGGER.info("Ending remove method");
    }

    /**
     * Update Type Logic
     *
     * @param event
     */
    @UiHandler("searchBtn")
    public void searchBtnClick(Button.ClickEvent event) {
        LOGGER.info("Entered search method");

        if (isSearch(discountChBinder)) {
            DiscountLogic.createSessionID(removeDiscountDto);
            selecteditemList.removeAll(selecteditemList.subList(0, selecteditemList.size()));
            removeDiscountDto.setReset(Boolean.FALSE);
            if (!tableLogic.loadSetData(removeDiscountDto, discountChBinder)) {
                AbstractNotificationUtils.getErrorNotification("No Matching Records",
                        "There were no records matching the search criteria.  Please try again.");
            }
        } else {
            AbstractNotificationUtils.getErrorNotification(Constants.MessageConstants.NO_SEARCH_CRITERIA_TITLE.getConstant(), Constants.MessageConstants.NO_SEARCH_CRITERIA_TP.getConstant());
        }
        LOGGER.info("Ending search method");
    }

    /**
     * Update Type Logic
     *
     * @param event
     */
    @UiHandler("addBtn")
    public void addBtnClick(Button.ClickEvent event) throws Exception {
        boolean check = true;
        if (selecteditemList.size() > 0) {
            int contractSys = selecteditemList.get(0).getContractSid();
            for (int i = 0; i < selecteditemList.size(); i++) {
                if (contractSys != selecteditemList.get(i).getContractSid()) {
                    check = false;
                }
            }
            if (check) {
                AddDiscountWindow addDiscount = new AddDiscountWindow(session, selecteditemList);
                UI.getCurrent().addWindow(addDiscount);
            } else {
                AbstractNotificationUtils.getErrorNotification("No Records Selected",
                        "Please ensure all checked Contracts have the same Contract Number.");
            }

        } else {
            AbstractNotificationUtils.getErrorNotification("No Records Selected",
                    "Please checkmark a Contract to continue.");
        }

    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("cfpName")
    public void cfpLookup(CustomTextField.ClickEvent event) {
        LOGGER.info("Entered cfpLookup method");
        CfpLookUp cfpLookup = new CfpLookUp(cfpName, false);
        cfpLookup.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {

            }
        });

        getUI().addWindow(cfpLookup);
        LOGGER.info("Ending cfpLookup method");
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("ifpName")
    public void ifpLookup(CustomTextField.ClickEvent event) {
        LOGGER.info("Entered ifpLookup method");
        IfpLookUp ifpLookup = new IfpLookUp(ifpName, false);
        ifpLookup.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {

            }
        });

        getUI().addWindow(ifpLookup);
        LOGGER.info("Ending ifpLookup method");
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("psName")
    public void psLookup(CustomTextField.ClickEvent event) {
        LOGGER.info("Entered psLookup method");
        PsLookUp ifpLookup = new PsLookUp(psName, false);
        ifpLookup.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {

            }
        });

        getUI().addWindow(ifpLookup);
        LOGGER.info("Ending psLookup method");
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("rsName")
    public void rsLookup(CustomTextField.ClickEvent event) {
        LOGGER.info("Entered rsLookup method");
        RsLookUp ifpLookup = new RsLookUp(rsName);
        ifpLookup.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {

            }
        });

        getUI().addWindow(ifpLookup);
        LOGGER.info("Ending rsLookup method");
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("customer")
    public void customerLookup(CustomTextField.ClickEvent event) {
        LOGGER.info("Entered customerLookup method");
        ComponentLookUp companyName = new ComponentLookUp("Customer", "Customer Lookup", customer);
        companyName.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (customer.getData() != null) {
                    ComponentLookUpDTO object = (ComponentLookUpDTO) customer.getData();
                    customer.setValue(object.getComponentNo());
                    customer.setValue(object.getComponentName());
                }
            }
        });

        UI.getCurrent().addWindow(companyName);
        LOGGER.info("Ending customerLookup method");

    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("item")
    public void itemLookup(CustomTextField.ClickEvent event) {
        LOGGER.info("Entered itemLookup method");
        ComponentLookUp companyName = new ComponentLookUp("Item", "Item Lookup", item);
        companyName.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (item.getData() != null) {
                    ComponentLookUpDTO object = (ComponentLookUpDTO) item.getData();
                    item.setValue(object.getComponentNo());
                    item.setValue(object.getComponentName());
                }
            }
        });

        UI.getCurrent().addWindow(companyName);
        LOGGER.info("Ending itemLookup method");
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler(Constants.CONTRACT_HOLDER)
    public void contractHolder(CustomTextField.ClickEvent event) {
        LOGGER.info("Entered contractHolder method");
        ComponentLookUp chHolder = new ComponentLookUp("Contract Holder", "Contract Holder Lookup", contractHolder);
        chHolder.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (contractHolder.getData() != null) {
                    ComponentLookUpDTO object = (ComponentLookUpDTO) contractHolder.getData();
                    contractHolder.setValue(object.getComponentName());
                }
            }
        });

        UI.getCurrent().addWindow(chHolder);
        LOGGER.info("Ending contractHolder method");
    }

    /**
     * Reset button logic
     *
     * @param event
     */
    @UiHandler("resetBtn")
    public void resetBtnClick(Button.ClickEvent event) {
        LOGGER.info("Entered reset method");
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                // do nothing
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                try {
                    removeDiscountDto.setReset(Boolean.TRUE);
                    tableLogic.loadSetData(removeDiscountDto, discountChBinder);
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage() + " - in table resetBtn");
                }
            }
        }.getConfirmationMessage("Reset Conformation", "Are you sure you want to reset the page to default/previous values?");

        LOGGER.info("Ending reset method");
    }

    /**
     * Reset button logic
     *
     * @param event
     */
    @UiHandler("resetSearch")
    public void resetSearchClick(Button.ClickEvent event) {
        LOGGER.info("Entered reset method");
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                // do nothing
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                discountChBinder.setItemDataSource(new BeanItem<RemoveDiscountDto>(new RemoveDiscountDto()));
            }
        }.getConfirmationMessage("Reset Conformation", "Are you sure you want to reset the page to default/previous values?");

        LOGGER.info("Ending reset method");
    }

    public Boolean isSearch(final CustomFieldGroup binder) {
        Boolean flag = false;
        if (getNull(binder.getField(Constants.CONTRACT_HOLDER).getValue().toString())) {
            flag = true;
        }
        if (getNull(String.valueOf(binder.getField(Constants.MARKET_TYPE).getValue()))
                && !Constants.IndicatorConstants.SELECT_ONE.getConstant().equals(binder.getField(Constants.MARKET_TYPE).getValue().toString())) {
            flag = true;
        }
        if (getNull(binder.getField(Constants.CFP_NAME).getValue().toString())) {
            flag = true;

        }
        if (getNull(binder.getField(Constants.CONTRACT_NO).getValue().toString())) {
            flag = true;

        }
        if (getNull(String.valueOf(binder.getField("contractstartDate").getValue()))) {
            flag = true;

        }
        if (getNull(String.valueOf(binder.getField("contractendDate").getValue()))) {
            flag = true;

        }
        if (getNull(binder.getField(Constants.IFPNAME).getValue().toString())) {
            flag = true;

        }
        if (getNull(binder.getField(Constants.CONTRACT_NAME).getValue().toString())) {
            flag = true;

        }
        if (getNull(binder.getField(Constants.PSNAME).getValue().toString())) {
            flag = true;

        }

        if (getNull(binder.getField(Constants.RSNAME).getValue().toString())) {
            flag = true;

        }
        if (getNull(String.valueOf(binder.getField("aliastype").getValue()))
                && !Constants.IndicatorConstants.SELECT_ONE.getConstant().equals(binder.getField("aliastype").getValue())) {
            flag = true;
        }

        if (getNull(String.valueOf(binder.getField("aliasnumber").getValue()))
                && !Constants.IndicatorConstants.SELECT_ONE.getConstant().equals(binder.getField("aliasnumber").getValue().toString())) {
            flag = true;
        }

        if (getNull(String.valueOf(binder.getField("aliasStartDate").getValue()))) {
            flag = true;
        }

        if (getNull(String.valueOf(binder.getField("aliasEndDate").getValue()))) {
            flag = true;
        }
        if (getNull(binder.getField("item").getValue().toString())) {
            flag = true;
        }
        if (getNull(binder.getField("customer").getValue().toString())) {
            flag = true;
        }

        return flag;
    }

    public boolean getNull(String value) {
        boolean check = false;
        if (!StringUtils.EMPTY.equals(value) && !Constants.NULL.equals(value) && value != null) {
            check = true;
        }
        return check;
    }

    private void insertIntoTable(Object itemId, Boolean value) {
        RemoveDiscountDto dto = (RemoveDiscountDto) itemId;
        dto.setSessionId(removeDiscountDto.getSessionId());
        dto.setUserId(removeDiscountDto.getUserId());
        String queryName = StringUtils.EMPTY;
        if (value) {
            queryName = "rd.insertIntoTempTable";
        } else {
            queryName = "rd.updateTempTable";
        }
        ItemQueries.updateDataByDTO(queryName, dto);
    }
}
