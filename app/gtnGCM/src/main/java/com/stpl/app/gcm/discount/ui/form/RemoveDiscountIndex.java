
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.ui.form;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.common.CommonUtil;
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
import com.stpl.app.gcm.util.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.NumericConstants;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.CheckBox;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.TableFieldFactory;
import com.vaadin.v7.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public CustomTextField cntHolder;
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
    private final RebateTableLogic tableLogic = new RebateTableLogic();
    private final ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    private final List<RemoveDiscountDto> selecteditemList = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(RemoveDiscountIndex.class);
    public String screenName = StringUtils.EMPTY;
    /* The bean used to load Start Period */
    final private BeanItemContainer<String> marketTypeBean = new BeanItemContainer<>(String.class);
    private final DiscountLogic discountLogic = new DiscountLogic();
    private final RemoveDiscountDto removeDiscountDto = new RemoveDiscountDto();
    private final CommonUtil commonUtil = CommonUtil.getInstance();
    public boolean checkAllFlag = false;
    /**
     * Binder for DataSelection.
     */
    final private ErrorfulFieldGroup discountChBinder = new ErrorfulFieldGroup(new BeanItem<>(removeDiscountDto));
    /**
     * Bean container for result table.
     */
    private final BeanItemContainer<RemoveDiscountDto> resultsContainer = new BeanItemContainer<>(RemoveDiscountDto.class);

    public RemoveDiscountIndex(String screenName, SessionDTO session) {
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
    private ErrorfulFieldGroup getBinder() {
        LOGGER.debug("Entering getBinder");
        discountChBinder.bindMemberFields(this);
        discountChBinder.setItemDataSource(new BeanItem<>(new RemoveDiscountDto()));
        discountChBinder.setBuffered(true);
        LOGGER.debug("Ending getBinder");
        return discountChBinder;
    }

    /**
     * Configure Scheduled Processes Table
     *
     */
    protected void configureFields() {
        try {
            createSessionID();
            if (ADD_DISCOUNT.getConstant().equals(screenName)) {
                addBtn.setEnabled(true);
                removeBtn.setEnabled(false);
            } else {
                addBtn.setEnabled(false);
                removeBtn.setEnabled(true);
            }
            marketTypeBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            marketTypeBean.addAll(discountLogic.getMarketType());
            marketType.setContainerDataSource(marketTypeBean);
            marketType.setNullSelectionAllowed(true);
            marketType.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            marketType.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            promoteTpToChDtoTableLayout.addComponent(resultsTable); // Removed below lines for CEL-810
            contractstartDate.setValidationVisible(true);
            contractstartDate.setDateFormat(Constants.DATE_FORMAT);
            contractstartDate.addStyleName(Constants.DATE_FIEILD_CENTER);
            contractendDate.setValidationVisible(true);
            contractendDate.setDateFormat(Constants.DATE_FORMAT);
            contractendDate.addStyleName(Constants.DATE_FIEILD_CENTER);
            commonUtil.loadComboBox(aliastype, UiUtils.CONTRACT_ALIAS_TYPE, false);
            configureAccrualResultsTable();
            aliasStartDate.setValidationVisible(true);
            aliasStartDate.setDateFormat(Constants.DATE_FORMAT);
            aliasStartDate.addStyleName(Constants.DATE_FIEILD_CENTER);
            aliasEndDate.setValidationVisible(true);
            aliasEndDate.setDateFormat(Constants.DATE_FORMAT);
            aliasEndDate.addStyleName(Constants.DATE_FIEILD_CENTER);
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }
    
    public void createSessionID() {
        final SimpleDateFormat fmtID = new SimpleDateFormat("hhmmssms");
        Date sessionDate = new Date();
        removeDiscountDto.setSessionId(Integer.valueOf(fmtID.format(sessionDate)));
        removeDiscountDto.setUserId(Integer.valueOf(String.valueOf(VaadinSession.getCurrent().getAttribute("userId"))));
    }

    /**
     * Configuring the Accrual Results Table
     *
     */
    public void configureAccrualResultsTable() {
        resultsTable.setContainerDataSource(resultsContainer);
        resultsTable.setEditable(true);
        resultsTable.setVisibleColumns(Constants.getInstance().discountSearchColumns);
        resultsTable.setColumnHeaders(Constants.getInstance().discountSearchHeaders);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        promoteTpToChDtoTableLayout.addComponent(tableLogic.createControls());
        resultsTable.setColumnCheckBox(Constants.CHECK_RECORD, Boolean.TRUE);
        for (Object propertyId : resultsTable.getVisibleColumns()) {
            resultsTable.setColumnWidth(propertyId, NumericConstants.ONE_SIX_EIGHT);
        }

        Object[] objColumn = Constants.getInstance().discountSearchColumns;
        for (Object objColumn1 : objColumn) {
            String value = objColumn1.toString();
            if (value.endsWith("Date")) {
                resultsTable.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
                resultsTable.setConverter(value, new DateToStringConverter());
            }
        }
        resultsTable.setFilterGenerator(new ExtFilterGenerator() {

            @Override
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            @Override
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

            @Override
            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                if (Constants.MARKET_TYPE.equals(propertyId)) {
                    ComboBox marketType = new ComboBox();
                    List resultList = discountLogic.getMarketType();
                    new DiscountLogic().setIdDescription(resultList, marketType);
                    resultList.clear();
                    return marketType;
                }

                if (propertyId.equals("checkRecord")) {
                    CheckBox checkRec = new CheckBox();
                    checkRec.setVisible(false);
                    return checkRec;
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
        });
        resultsTable.setTableFieldFactory(new TableFieldFactory() {
            @Override
            public Field<?> createField(Container container, final Object itemId, final Object propertyId, Component uiContext) {

                if (propertyId.equals(Constants.CHECK_RECORD)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    if(checkAllFlag){
                        for (RemoveDiscountDto temp : resultsContainer.getItemIds()) {
                            resultsContainer.getItem(temp).getItemProperty(Constants.CHECK_RECORD).setValue(checkAllFlag);
                        }
                    }
                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        @Override
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            boolean isCheck = ((CheckBox) event.getComponent()).getValue();
                            RemoveDiscountDto dto = (RemoveDiscountDto) itemId;
                            dto.setCheckRecord(isCheck);
                            List<Object> list = getCheckedRecord(dto);
                            if (list.isEmpty() || !list.contains(dto.getContractSid())) {
                                insertIntoTable(dto);
                            }
                            updateTable(dto);
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
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                for (RemoveDiscountDto temp : resultsContainer.getItemIds()) {
                    resultsContainer.getItem(temp).getItemProperty(event.getPropertyId()).setValue(event.isChecked());
                    checkAllFlag = (boolean) resultsContainer.getItem(temp).getItemProperty(event.getPropertyId()).getValue();
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
                resultsTable.setColumnWidth(property, NumericConstants.SIXTY);
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
        LOGGER.debug("Entered remove method" + selecteditemList.size());
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
            AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.NO_RECORDS_SELECTED_HEADER,
                    "Please checkmark a Contract to continue.");
        }
        LOGGER.debug("Ending remove method");
    }

    /**
     * Update Type Logic
     *
     * @param event
     */
    @UiHandler("searchBtn")
    public void searchBtnClick(Button.ClickEvent event) {
        LOGGER.debug("Entered search method");

        if (isSearch(discountChBinder)) {
            selecteditemList.removeAll(selecteditemList.subList(0, selecteditemList.size()));
            removeDiscountDto.setSearch(Boolean.TRUE);
            tableLogic.setContainerDataSource(resultsContainer);
            resultsTable.setVisibleColumns(Constants.getInstance().discountSearchColumns);
            resultsTable.setColumnHeaders(Constants.getInstance().discountSearchHeaders);
            if (!tableLogic.loadSetData(removeDiscountDto, discountChBinder)) {
                AbstractNotificationUtils.getErrorNotification("No Matching Records",
                        "There were no records matching the search criteria.  Please try again.");
            }
        } else {
            AbstractNotificationUtils.getErrorNotification(Constants.MessageConstants.NO_SEARCH_CRITERIA_TITLE.getConstant(), Constants.MessageConstants.NO_SEARCH_CRITERIA_TP.getConstant());
        }
        LOGGER.debug("Ending search method");
    }

    /**
     * Update Type Logic
     *
     * @param event
     */
    @UiHandler("addBtn")
    public void addBtnClick(Button.ClickEvent event) throws SystemException{
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
                AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.NO_RECORDS_SELECTED_HEADER,
                        "Please ensure all checked Contracts have the same Contract Number.");
            }

        } else {
            AbstractNotificationUtils.getErrorNotification(StringConstantsUtil.NO_RECORDS_SELECTED_HEADER,
                    "Please checkmark a Contract to continue.");
        }

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        return;
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("cfpName")
    public void cfpLookup(CustomTextField.ClickEvent event) {
        LOGGER.debug("Entered cfpLookup method");
        CfpLookUp cfpLookup = new CfpLookUp(cfpName, false);
        cfpLookup.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                return;
            }
        });

        getUI().addWindow(cfpLookup);
        LOGGER.debug("Ending cfpLookup method");
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("ifpName")
    public void ifpLookup(CustomTextField.ClickEvent event) {
        LOGGER.debug("Entered ifpLookup method");
        IfpLookUp ifpLookup = new IfpLookUp(ifpName, false);
        ifpLookup.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                return;
            }
        });

        getUI().addWindow(ifpLookup);
        LOGGER.debug("Ending ifpLookup method");
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("psName")
    public void psLookup(CustomTextField.ClickEvent event) {
        LOGGER.debug("Entered psLookup method");
        PsLookUp ifpLookup = new PsLookUp(psName, false);
        ifpLookup.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                return;
            }
        });

        getUI().addWindow(ifpLookup);
        LOGGER.debug("Ending psLookup method");
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("rsName")
    public void rsLookup(CustomTextField.ClickEvent event) {
        LOGGER.debug("Entered rsLookup method");
        RsLookUp ifpLookup = new RsLookUp(rsName);
        ifpLookup.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                return;
            }
        });

        getUI().addWindow(ifpLookup);
        LOGGER.debug("Ending rsLookup method");
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("customer")
    public void customerLookup(CustomTextField.ClickEvent event) {
        LOGGER.debug("Entered customerLookup method");
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
        LOGGER.debug("Ending customerLookup method");

    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("item")
    public void itemLookup(CustomTextField.ClickEvent event) {
        LOGGER.debug("Entered itemLookup method");
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
        LOGGER.debug("Ending itemLookup method");
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler(Constants.CONTRACT_HOLDER)
    public void contractHolder(CustomTextField.ClickEvent event) {
        LOGGER.debug("Entered contractHolder method");
        ComponentLookUp chHolder = new ComponentLookUp("Contract Holder", "Contract Holder Lookup", cntHolder);
        chHolder.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (cntHolder.getData() != null) {
                    ComponentLookUpDTO object = (ComponentLookUpDTO) cntHolder.getData();
                    cntHolder.setValue(object.getComponentName());
                }
            }
        });

        UI.getCurrent().addWindow(chHolder);
        LOGGER.debug("Ending contractHolder method");
    }

    /**
     * Reset button logic
     *
     * @param event
     */
    @UiHandler("resetBtn")
    public void resetBtnClick(Button.ClickEvent event) {
        LOGGER.debug("Entered reset method");
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
                      removeDiscountDto.setSearch(Boolean.FALSE);
                    tableLogic.loadSetData(removeDiscountDto, discountChBinder);
                } catch (Exception ex) {
                    LOGGER.error("",ex);
                }
            }
        }.getConfirmationMessage("Reset Conformation", "Are you sure you want to reset the page to default/previous values?");

        LOGGER.debug("Ending reset method");
    }

    /**
     * Reset button logic
     *
     * @param event
     */
    @UiHandler("resetSearch")
    public void resetSearchClick(Button.ClickEvent event) {
        LOGGER.debug("Entered reset method");
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
                discountChBinder.setItemDataSource(new BeanItem<>(new RemoveDiscountDto()));
            }
        }.getConfirmationMessage("Reset Conformation", "Are you sure you want to reset the page to default/previous values?");

        LOGGER.debug("Ending reset method");
    }

    public Boolean isSearch(final ErrorfulFieldGroup binder) {
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

    private void insertIntoTable(RemoveDiscountDto dto) {
        dto.setSessionId(removeDiscountDto.getSessionId());
        dto.setUserId(removeDiscountDto.getUserId());
        String query = SQlUtil.getQuery("insert_temp_table_query");
        query = query.replace("@CONTRACT_MASTER_SID", dto.getContractSid() + StringUtils.EMPTY);
        query = query.replace("@USER_ID", dto.getUserId() + StringUtils.EMPTY);
        query = query.replace("@SESSION_ID", dto.getSessionId() + StringUtils.EMPTY);
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    private void updateTable(RemoveDiscountDto dto) {
        dto.setSessionId(removeDiscountDto.getSessionId());
        dto.setUserId(removeDiscountDto.getUserId());
        String queryName = "remove_discount_update_checkRecord_value";
        ItemQueries.updateDataByDTOforRemoveDiscount(queryName, dto);
    }

    public List<Object> getCheckedRecord(RemoveDiscountDto dto) {
        List<Object> contractList;
        String query = SQlUtil.getQuery("remove_discount_get_checkRecord_value");
        query = query.replace("@CONTRACT_MASTER_SID", dto.getContractSid() + StringUtils.EMPTY);
        query = query.replace("@USER_ID", removeDiscountDto.getUserId() + StringUtils.EMPTY);
        query = query.replace("@SESSION_ID", removeDiscountDto.getSessionId() + StringUtils.EMPTY);
        contractList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return contractList;
    }
}
