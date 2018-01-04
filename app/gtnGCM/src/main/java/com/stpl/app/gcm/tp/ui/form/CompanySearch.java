
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.ui.form;

import com.stpl.app.gcm.common.CommonLogic;
import org.asi.container.ExtTreeContainer;
import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.dto.IdDescriptionDTO;
import com.stpl.app.gcm.tp.dto.TradingPartnerDTO;
import com.stpl.app.gcm.tp.logic.CommmonLogic;
import com.stpl.app.gcm.tp.logic.CompanySearchLogic;
import com.stpl.app.gcm.tp.tablelogic.CompanySearchTableLogic;
import com.stpl.app.gcm.tp.logic.ContractSelectionLogic;
import static com.stpl.app.gcm.tp.ui.form.CustomerSelection.getUserName;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.*;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.EXCEL_IMAGE_PATH;
import com.stpl.app.gcm.util.Constants.MessageConstants;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.CheckBox;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.Component;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtCustomTable.ColumnCheckListener;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.TableFieldFactory;
import com.vaadin.v7.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Lokeshwari
 */
public class CompanySearch extends VerticalLayout {

    @UiField("companySearchTableLayout")
    public VerticalLayout companySearchTableLayout;
    @UiField("companyType")
    public ComboBox companyType;
    @UiField("companyCategory")
    public ComboBox companyCategory;
    @UiField("tradeClass")
    public ComboBox tradeClass;
    @UiField("identifierType")
    public ComboBox identifierType;
    @UiField("resetBtn1")
    public Button reset;
    @UiField("companyId")
    public TextField companyId;
    @UiField("companyName")
    public TextField companyName;
    @UiField("companyNo")
    public TextField companyNo;
    @UiField("identifier")
    public TextField identifier;
    @UiField("parentNo")
    private CustomTextField parentNo;
    @UiField("parentName")
    private CustomTextField parentName;
    @UiField("deleteBtn")
    public Button deleteButton;
    @UiField("addBtn")
    public Button addButton;
    /**
     * The excel btn.
     */
    @UiField("excelBtn")
    public Button excelBtn;
    @UiField("searchBtn")
    public Button searchBtn;
    @UiField("editBtn")
    public Button editButton;
    @UiField("transferBtn")
    public Button transferButton;
    @UiField("resetBtn2")
    public Button resetBtn2;
    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(CompanySearch.class);
    private final CommonUtil commonUtil = CommonUtil.getInstance();
    private String updateType = StringUtils.EMPTY;
    private final CompanySearchTableLogic companyLogic = new CompanySearchTableLogic();
    private final StplSecurity stplSecurity = new StplSecurity();
    public ExtPagedTable companySearchResultsTable = new ExtPagedTable(companyLogic);
    private final BeanItemContainer<TradingPartnerDTO> companyResultsContainer = new BeanItemContainer<>(TradingPartnerDTO.class);
    private final ExtTreeContainer<TradingPartnerDTO> resultsLazyContainer = new ExtTreeContainer<>(TradingPartnerDTO.class);
    public TradingPartnerDTO tpDto = new TradingPartnerDTO();
    private final ErrorLabel errorMsg = new ErrorLabel();
    public static final String SEARCH_BTN = "searchBtn";
    public static final String PLEASE_SELECT_A_VALUE_IN_THE_RESULTS_LIST = "Please select a value in the Results list view then try again.";
    public static final String CHECK = "check";
    /**
     * The data selection binder.
     */
    public ErrorfulFieldGroup dataSelectionBinder = new ErrorfulFieldGroup(new BeanItem<>(tpDto));
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    private final SessionDTO session = new SessionDTO();
    private int parentCompanySid;
    private ParentCompanyLookup parentCompanyLookup = null;
    private String searchSessionId = StringUtils.EMPTY;
    private final CompanySearchLogic companySearchLogic = new CompanySearchLogic();

    public CompanySearch(String updateType) {
        addComponent(Clara.create(getClass().getResourceAsStream("/TradingPartner/companySearch.xml"), this));
        this.updateType = updateType;
        getBinder();
        configureFields();
        configureSecurityPermissions();
    }

    protected void configureFields() {
        try {
            CompanySearchLogic logic = new CompanySearchLogic();
            List<IdDescriptionDTO> resultList;
            resultList = logic.loadIdentifierTypeDdlb();
            logic.setIdDescription(resultList, identifierType);
            resultList.clear();

            commonUtil.loadComboBox(companyCategory, UiUtils.COMPANY_CATEGORY, false);
            commonUtil.loadComboBox(companyType, UiUtils.COMPANY_TYPE, false);
            commonUtil.loadComboBox(tradeClass, UiUtils.COMPANY_TRADE_CLASS, false);

            companySearchTableLayout.addComponent(companySearchResultsTable);
            companyLogic.setPageLength(10);
            HorizontalLayout hLayout;
            hLayout = companyLogic.createControls();
            companySearchTableLayout.addComponent(hLayout);
            excelBtn.setIcon(excelExportImage);
            configureCompanySearchResultsTable();

            parentNo.addClickListener(new CustomTextField.ClickListener() {

                @Override
                public void click(CustomTextField.ClickEvent event) {
                    parentCompanyLookup = new ParentCompanyLookup(parentNo, parentName, parentCompanySid);
                    UI.getCurrent().addWindow(parentCompanyLookup);
                }
            });

            parentName.addClickListener(new CustomTextField.ClickListener() {

                @Override
                public void click(CustomTextField.ClickEvent event) {
                    parentCompanyLookup = new ParentCompanyLookup(parentNo, parentName, parentCompanySid);
                    UI.getCurrent().addWindow(parentCompanyLookup);
                }
            });

            if (TRADING_PARTNER_REMOVE.getConstant().equals(updateType)) {
                addButton.setEnabled(false);
                editButton.setEnabled(false);
                transferButton.setEnabled(false);
                deleteButton.setEnabled(true);
            } else if (ADD_TRADING_PARTNER.getConstant().equals(updateType)) {
                addButton.setEnabled(true);
                editButton.setVisible(false);
                transferButton.setVisible(false);
                deleteButton.setVisible(false);

            } else if (TRANSFER_TRADING_PARTNER.getConstant().equals(updateType) || PROJECTION_DETAILS_TRANSFER.getConstant().equals(updateType)) {
                addButton.setEnabled(false);
                editButton.setEnabled(false);
                transferButton.setEnabled(true);
                deleteButton.setEnabled(false);
            } else if (TRADING_PARTNER_UPDATE.getConstant().equals(updateType)) {
                addButton.setEnabled(false);
                editButton.setEnabled(true);
                transferButton.setEnabled(false);
                deleteButton.setEnabled(false);

            }
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }

    public void configureCompanySearchResultsTable() {
        final CommmonLogic logic = new CommmonLogic();
        companySearchResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        companySearchResultsTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        companySearchResultsTable.setHeight(NumericConstants.FOUR_HUNDRED, Unit.PIXELS);
        companySearchResultsTable.setItemsPerPage(10);
        companySearchResultsTable.setSortEnabled(false);
        companyLogic.setContainerDataSource(companyResultsContainer);

        companySearchResultsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.getItemId() != null) {
                    List<String> selectedCompany = new ArrayList<>();
                    TradingPartnerDTO tpDTO = (TradingPartnerDTO) event.getItemId();
                    session.setCompanyNo(tpDTO.getCompanyNo());
                    session.setCompanyName(tpDTO.getCompanyName());
                    session.setCompanyType(tpDTO.getCompanyType());
                    session.setCompanyCategory(tpDTO.getCompanyCategory());
                    session.setTradeClass(tpDTO.getTradeClass());
                    if (ADD_TRADING_PARTNER.getConstant().equals(updateType)) {
                        selectedCompany.add(tpDTO.getCompanySystemId());
                        session.setCompanyMasterSids(selectedCompany);
                    } else {
                        selectedCompany.add(tpDTO.getCompanyName());
                        session.setCompanyMasterSids(selectedCompany);
                    }
                }
            }
        });

        companySearchResultsTable.setFilterGenerator(new ExtFilterGenerator() {

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
                if ("tradeClass".equals(propertyId)) {
                    try {
                        ComboBox tmpComboTradeClass = new ComboBox();
                        commonUtil.loadComboBox(tmpComboTradeClass, UiUtils.COMPANY_TRADE_CLASS, true);
                        return tmpComboTradeClass;
                    } catch (Exception ex) {
                        LOGGER.error("",ex);
                    }
                }
                if ("companyType".equals(propertyId)) {
                    try {
                        ComboBox tmpCompanyType = new ComboBox();
                        commonUtil.loadComboBox(tmpCompanyType, UiUtils.COMPANY_TYPE, true);
                        return tmpCompanyType;
                    } catch (Exception ex) {
                        LOGGER.error("",ex);
                    }
                }
                if ("companyCategory".equals(propertyId)) {
                    try {
                        ComboBox tmpCompanyCategory = new ComboBox();
                        commonUtil.loadComboBox(tmpCompanyCategory, UiUtils.COMPANY_CATEGORY, true);
                        return tmpCompanyCategory;
                    } catch (Exception ex) {
                        LOGGER.error("",ex);
                    }
                }
                if ("state".equals(propertyId)) {
                    try {
                        ComboBox state = new ComboBox();
                        commonUtil.loadComboBox(state, UiUtils.STATE, true);
                        return state;
                    } catch (Exception ex) {
                        LOGGER.error("",ex);
                    }                           
                }
                if (propertyId.equals(CHECK)) {
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

        companySearchResultsTable.setTableFieldFactory(new TableFieldFactory() {

            @Override
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                if (propertyId.equals(CHECK)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    check.setImmediate(true);
                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        @Override
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            int count = logic.callCompanyUpdate(check.getValue(), (TradingPartnerDTO) itemId, updateType, searchSessionId);
                            if (count == 0) {
                                logic.callCompanyInsert(check.getValue(), (TradingPartnerDTO) itemId, updateType, searchSessionId);
                            }

                            if (!check.getValue()) {

                                companySearchResultsTable.removeColumnCheckListener(checkListener);
                                companySearchResultsTable.setColumnCheckBox(CHECK, true, false);
                                companySearchResultsTable.addColumnCheckListener(checkListener);
                            }
                        }
                    });
                    return check;
                }
                return null;
            }
        });

        companySearchResultsTable.addColumnCheckListener(checkListener);
        if (ADD_TRADING_PARTNER.getConstant().equals(updateType)) {
            companySearchResultsTable.setVisibleColumns(Constants.getInstance().tpCompanySearchColumns);
            companySearchResultsTable.setColumnHeaders(Constants.getInstance().tpCompanySearchHeaders);
            companySearchResultsTable.setSelectable(true);
        } else {
            companySearchResultsTable.setVisibleColumns(Constants.getInstance().companySearchColumns);
            companySearchResultsTable.setColumnHeaders(Constants.getInstance().companySearchHeaders);
            companySearchResultsTable.setSelectable(false);
        }

        companySearchResultsTable.setFilterBarVisible(true);
        companySearchResultsTable.addStyleName("filterbar");
        companySearchResultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        companySearchResultsTable.setEditable(true);

        for (Object object : companySearchResultsTable.getVisibleColumns()) {
            if (String.valueOf(object).contains("Date")) {
                companySearchResultsTable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
            }
        }
    }

    @UiHandler("resetBtn1")
    public void resetSearchCriteria(Button.ClickEvent event) {
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
                    companySearchReset();
                } catch (Exception ex) {
                    LOGGER.error("",ex);
                }
            }
        }.getConfirmationMessage("Reset Confirmation", "Are you sure you want to reset the Company Search?");
    }

    public void companySearchReset() {
        companyType.setValue(Constants.SELECT_ONE);
        companyCategory.setValue(Constants.SELECT_ONE);
        tradeClass.setValue(Constants.SELECT_ONE);
        identifierType.setValue(Constants.SELECT_ONE);
        companyId.setValue(StringUtils.EMPTY);
        companyName.setValue(StringUtils.EMPTY);
        companyNo.setValue(StringUtils.EMPTY);
        identifier.setValue(StringUtils.EMPTY);
        parentNo.setValue(StringUtils.EMPTY);
        parentName.setValue(StringUtils.EMPTY);
        parentCompanyLookup = null;
    }

    @UiHandler("deleteBtn")
    public void deleteBtn(Button.ClickEvent event) throws CloneNotSupportedException {
        if (TRADING_PARTNER_REMOVE.getConstant().equals(updateType)) {
            int count = CompanySearchLogic.getCompanyCount(searchSessionId);
            ContractSelectionLogic logic = new ContractSelectionLogic();
            session.setCompanyMasterSids(logic.getSelectedCompaniesList(searchSessionId));
            if (count > 0) {
                int count1 = logic.getDataCount(session.getCompanyMasterSids(), false);
                if (count1 > 0) {
                    SessionDTO sessionDto = createSession(updateType);
                    sessionDto.setSearchSessionId(searchSessionId);
                    sessionDto.setModuleName(TRADING_PARTNER_REMOVE.getConstant());
                        RemoveTPForm removeForm = new RemoveTPForm(sessionDto);
                    UI.getCurrent().addWindow(removeForm);
                } else {
                    AbstractNotificationUtils.getErrorNotification("No Valid Contracts", "There is no Contract attached with the selected Company ");
                    return;
                }

            } else {
                AbstractNotificationUtils.getErrorNotification("Remove Error", PLEASE_SELECT_A_VALUE_IN_THE_RESULTS_LIST);
            }
        }
    }

    @UiHandler("addBtn")
    public void addBtn(Button.ClickEvent event) throws CloneNotSupportedException {
        if (ADD_TRADING_PARTNER.getConstant().equals(updateType)) {
            if (companySearchResultsTable.getValue() != null) {
                TradingPartnerDTO dto = (TradingPartnerDTO) companySearchResultsTable.getValue();
                SessionDTO sessionDto = createSession(updateType);
                sessionDto.setModuleName(ADD_TRADING_PARTNER.getConstant());
                List<String> companyIds = new ArrayList<>();
                companyIds.add(dto.getCompanySystemId());
                sessionDto.setCompanyMasterSids(companyIds);
                AddTPForm removeForm = new AddTPForm(sessionDto);
                UI.getCurrent().addWindow(removeForm);
            } else {
                AbstractNotificationUtils.getErrorNotification(" Error", PLEASE_SELECT_A_VALUE_IN_THE_RESULTS_LIST);
            }
        }
    }

    @UiHandler("transferBtn")
    public void transferBtn(Button.ClickEvent event) throws CloneNotSupportedException {
        ContractSelectionLogic logic = new ContractSelectionLogic();
        try {
            int count = CompanySearchLogic.getCompanyCount(searchSessionId);
            session.setCompanyMasterSids(logic.getSelectedCompaniesList(searchSessionId));
            if (TRANSFER_TRADING_PARTNER.getConstant().equals(updateType) || PROJECTION_DETAILS_TRANSFER.getConstant().equals(updateType)) {
                if (count > 0) {
                    int count1 = 0;
                    if (TRANSFER_TRADING_PARTNER.getConstant().equals(updateType)) {
                        count1 = logic.getDataCount(session.getCompanyMasterSids(), false);
                    } else {
                        count1 = 1;
                    }

                    if (count1 > 0) {
                        SessionDTO sessionDto = createSession(updateType);
                        sessionDto.setSearchSessionId(searchSessionId);
                        if (TRANSFER_TRADING_PARTNER.getConstant().equals(updateType)) {
                            sessionDto.setModuleName(TRANSFER_TRADING_PARTNER.getConstant());
                        } else {
                            sessionDto.setModuleName(PROJECTION_DETAILS_TRANSFER.getConstant());
                        }
                        TransferTPForm transferTp = new TransferTPForm(sessionDto);
                        UI.getCurrent().addWindow(transferTp);
                    } else {
                        if (session.getCompanyMasterSids().size() == 1) {
                            AbstractNotificationUtils.getErrorNotification("Transfer Error", "The selected Company do not belong to any Contract. \n Please refine your selection to only include a Company that is attached to a Contract. ");
                        } else {
                            AbstractNotificationUtils.getErrorNotification("Transfer Error", "The selected Companies do not belong to the same Contract. \n Please refine your selection to only include Companies that are on the same Contract. ");
                        }
                        return;
                    }
                } else {
                    AbstractNotificationUtils.getErrorNotification(MessageConstants.NO_TP_SELECTED.getConstant(), MessageConstants.NO_TP_SELECTED_BODY.getConstant());
                }
            }
        } catch (CloneNotSupportedException | IllegalArgumentException | NullPointerException e) {
            LOGGER.error("",e);
        }
    }

    @UiHandler("editBtn")
    public void editBtn(Button.ClickEvent event) throws CloneNotSupportedException {
        if (TRADING_PARTNER_UPDATE.getConstant().equals(updateType)) {
            int count = CompanySearchLogic.getCompanyCount(searchSessionId);
            ContractSelectionLogic logic = new ContractSelectionLogic();
            session.setCompanyMasterSids(logic.getSelectedCompaniesList(searchSessionId));
            if (count > 0) {
                int count1 = logic.getDataCount(session.getCompanyMasterSids(), false);
                if (count1 > 0) {
                    SessionDTO sessionDto = createSession(updateType);
                    sessionDto.setSearchSessionId(searchSessionId);
                    sessionDto.setModuleName(TRADING_PARTNER_UPDATE.getConstant());
                    UpdateTPForm updateTPForm = new UpdateTPForm(sessionDto);
                    UI.getCurrent().addWindow(updateTPForm);
                } else {
                    AbstractNotificationUtils.getErrorNotification("No Valid Contracts", "There is no Contract attached with the selected Company ");
                    return;
                }

            } else {
                AbstractNotificationUtils.getErrorNotification("Update Error", PLEASE_SELECT_A_VALUE_IN_THE_RESULTS_LIST);
            }
        }
    }

    @UiHandler("searchBtn")
    public void searchBtnLogic(Button.ClickEvent event) {
        if (companyLogic.getFilters() != null) {
            companyLogic.getFilters().clear();
        }
        companyResultsContainer.removeAllItems();
        companySearchResultsTable.setContainerDataSource(companyResultsContainer);

        String recordLockStatus = StringUtils.EMPTY;
        Map<Integer, String> users;
        String userid= "";
        if (ADD_TRADING_PARTNER.getConstant().equals(updateType)) {
            companySearchResultsTable.setVisibleColumns(Constants.getInstance().tpCompanySearchColumns);
            companySearchResultsTable.setColumnHeaders(Constants.getInstance().tpCompanySearchHeaders);

        } else {
            companySearchResultsTable.setVisibleColumns(Constants.getInstance().companySearchColumns);
            companySearchResultsTable.setColumnHeaders(Constants.getInstance().companySearchHeaders);
        }

        //To bring only the placeholder companies
        if (PROJECTION_DETAILS_TRANSFER.getConstant().equals(updateType)) {
            recordLockStatus = Constants.ZEROSTRING;
        }

        if (resultsLazyContainer != null) {
            resultsLazyContainer.removeAllItems();
        }
        companySearchResultsTable.removeAllItems();
        if (StringUtils.isBlank(companyId.getValue()) && StringUtils.isBlank(companyName.getValue()) && StringUtils.isBlank(companyNo.getValue())
                && StringUtils.isBlank(identifier.getValue()) && StringUtils.isBlank(parentNo.getValue()) && StringUtils.isBlank(parentName.getValue())
                && Constants.NULL.equalsIgnoreCase(String.valueOf(companyType.getValue())) && Constants.NULL.equalsIgnoreCase(String.valueOf(companyCategory.getValue()))
                && Constants.NULL.equalsIgnoreCase(String.valueOf(tradeClass.getValue())) && Constants.NULL.equalsIgnoreCase(String.valueOf(identifierType.getValue()))) {
            AbstractNotificationUtils.getAlertNotification(MessageConstants.NO_SEARCH_CRITERIA_TITLE.getConstant(),
                    MessageConstants.NO_SEARCH_CRITERIA_BODY.getConstant());
        } else {
            try {
                dataSelectionBinder.commit();
                String parentCompanyNo = parentNo.getValue() != null ? parentNo.getValue() : StringUtils.EMPTY;
                String parentCompanyName = parentName.getValue() != null ? parentName.getValue() : StringUtils.EMPTY;
                CompanySearchLogic logic = new CompanySearchLogic();
                logic.clearTempTable(searchSessionId);
                createSearchSessionId();
                users = getUserName();
                for (Map.Entry<Integer, String> entry : users.entrySet()) {
                    if (entry.getValue().contains("ETL")) {
                        userid = entry.getKey().toString();
                    }
                }
                logic.insertIntoTempTable(searchSessionId,  updateType);
                tpDto.setReset(Boolean.FALSE);
                companyLogic.loadSetData(tpDto, parentCompanyNo, parentCompanyName, recordLockStatus, searchSessionId);
                if (!companyLogic.isRecordPresent()) {
                    AbstractNotificationUtils.getErrorNotification("No Results Found",
                            "There are no results that match the search criteria. Please try again.");
                } else {
                    CommonUIUtils.getMessageNotification("Search Completed");
                }
            } catch (CommitException commit) {
                LOGGER.error("",commit);
            }
        }
    }

    private ErrorfulFieldGroup getBinder() {
        dataSelectionBinder.bindMemberFields(this);
        dataSelectionBinder.setItemDataSource(new BeanItem<>(tpDto));
        dataSelectionBinder.setBuffered(true);
        dataSelectionBinder.setErrorDisplay(errorMsg);
        return dataSelectionBinder;
    }

    private SessionDTO createSession(String moduleName) throws CloneNotSupportedException {
        SessionDTO sessionDTO = session.clone();
        sessionDTO = CommonUtils.attachSessionId(sessionDTO);
        sessionDTO.setModuleName(moduleName);
        return sessionDTO;
    }

    @UiHandler("excelBtn")
    public void excelExport(Button.ClickEvent event) {
        try {
            if (companyResultsContainer.size() > 0) {
                createWorkSheet("Company_Details", companySearchResultsTable);
            }

        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }

    public void createWorkSheet(String moduleName, ExtFilterTable resultTable) throws PortalException, SystemException, NoSuchMethodException, IllegalAccessException,  InvocationTargetException {

        String parentCompanyNo = parentNo.getValue() != null ? parentNo.getValue() : StringUtils.EMPTY;
        String parentCompanyName = parentName.getValue() != null ? parentName.getValue() : StringUtils.EMPTY;
        long recordCount = 0;
        String recordLockStatus = StringUtils.EMPTY;
        List<String> visibleList = Arrays.asList(resultTable.getColumnHeaders()).subList(ADD_TRADING_PARTNER.getConstant().equals(updateType) ? 0 : 1, resultTable.getVisibleColumns().length);
        if (PROJECTION_DETAILS_TRANSFER.getConstant().equals(updateType)) {
            recordLockStatus = Constants.ZEROSTRING;
        }
        if (resultTable.size() != 0) {
            recordCount = companySearchLogic.companySearchCount(tpDto, parentCompanyNo, parentCompanyName, companyLogic.getFilters(), recordLockStatus, searchSessionId);
        }
        ExcelExportforBB.createWorkSheet(visibleList.toArray(new String[visibleList.size()]), recordCount, this, UI.getCurrent(), moduleName.toUpperCase());

    }

    @UiHandler("resetBtn2")
    public void resetListView(Button.ClickEvent event) {
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

                    TradingPartnerDTO selectedTpDto = new TradingPartnerDTO();
                    selectedTpDto.setReset(Boolean.TRUE);
                    companySearchResultsTable.resetFilters();
                    companyLogic.loadSetData(selectedTpDto, StringUtils.EMPTY, StringUtils.EMPTY, Constants.ZEROSTRING, session.getSearchSessionId());
                    companyLogic.isRecordPresent();
                } catch (Exception ex) {
                    LOGGER.error("",ex);
                }
            }
        }.getConfirmationMessage("Reset Confirmation", "Are you sure you want to reset the Results list view?");
    }

    private final ColumnCheckListener checkListener = new ColumnCheckListener() {
        @Override
        public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
            if (event.isChecked()) {

                checkClearAll(event.isChecked());
            }
        }

    };

    public void checkClearAll(boolean checkValue) {

        List<TradingPartnerDTO> containerList = resultsLazyContainer.getBeans();

        for (TradingPartnerDTO dto : containerList) {
            companySearchResultsTable.getContainerProperty(dto, CHECK).setValue(checkValue);
        }
    }

    private void createSearchSessionId() {
        searchSessionId = CommonUtils.createSessionId();
    }

    // CSV Export. Do not remove the below method
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException,  InvocationTargetException {
        LOGGER.debug("Entering createWorkSheetContent with start " + start + " end " + end);
        String parentCompanyNo = parentNo.getValue() != null ? parentNo.getValue() : StringUtils.EMPTY;
        String parentCompanyName = parentName.getValue() != null ? parentName.getValue() : StringUtils.EMPTY;
        String recordLockStatus = StringUtils.EMPTY;
        List visibleList = Arrays.asList(companySearchResultsTable.getVisibleColumns()).subList(ADD_TRADING_PARTNER.getConstant().equals(updateType) ? 0 : 1, companySearchResultsTable.getVisibleColumns().length);
        if (PROJECTION_DETAILS_TRANSFER.getConstant().equals(updateType)) {
            recordLockStatus = Constants.ZEROSTRING;
        }
        try {
            if (end != 0) {
                final List<TradingPartnerDTO> searchList = (List<TradingPartnerDTO>) companySearchLogic.searchCompaniesLazy(tpDto, start, end, companyLogic.getSortByColumns(), parentCompanyNo, parentCompanyName, companyLogic.getFilters(), recordLockStatus, searchSessionId, false);
                ExcelExportforBB.createFileContent(visibleList.toArray(), searchList, printWriter);
            }
        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }

     public void configureSecurityPermissions() {
        try {
            if (ADD_TRADING_PARTNER.getConstant().equals(updateType)) {
                Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID)), "GCM-Customer Management", "Add Customer", "Add Customer Screen");
                searchBtn.setVisible(CommonLogic.isButtonVisibleAccess(SEARCH_BTN, functionHM));
                reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", functionHM));
                resetBtn2.setVisible(CommonLogic.isButtonVisibleAccess("resetBtn2", functionHM));
                addButton.setVisible(CommonLogic.isButtonVisibleAccess("addBtn", functionHM));
                
            } else {
                Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID)), "GCM-Customer Management", "Customer Management", "Landing  Screen");
                searchBtn.setVisible(CommonLogic.isButtonVisibleAccess(SEARCH_BTN, functionHM));
                reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", functionHM));
                resetBtn2.setVisible(CommonLogic.isButtonVisibleAccess("resetBtn2", functionHM));
                transferButton.setVisible(CommonLogic.isButtonVisibleAccess("transferBtn", functionHM));
                searchBtn.setVisible(CommonLogic.isButtonVisibleAccess(SEARCH_BTN, functionHM));
                addButton.setVisible(CommonLogic.isButtonVisibleAccess("addBtn", functionHM));
                editButton.setVisible(CommonLogic.isButtonVisibleAccess("editBtn", functionHM));
                deleteButton.setVisible(CommonLogic.isButtonVisibleAccess("deleteBtn", functionHM));
            }
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }


}