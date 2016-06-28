
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.ui.form;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.dto.IdDescriptionDTO;
import com.stpl.app.gcm.tp.dto.TradingPartnerDTO;
import com.stpl.app.gcm.tp.logic.CommmonLogic;
import com.stpl.app.gcm.tp.logic.CompanySearchLogic;
import com.stpl.app.gcm.tp.tablelogic.CompanySearchTableLogic;
import com.stpl.app.gcm.tp.logic.ContractSelectionLogic;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.*;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.EXCEL_IMAGE_PATH;
import com.stpl.app.gcm.util.Constants.MessageConstants;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.ExtCustomTable.ColumnCheckListener;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
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
    public Button deleteBtn;
    @UiField("addBtn")
    public Button addBtn;
    /**
     * The excel btn.
     */
    @UiField("excelBtn")
    public Button excelBtn;
    @UiField("searchBtn")
    public Button searchBtn;
    @UiField("editBtn")
    public Button editBtn;
    @UiField("transferBtn")
    public Button transferBtn;
    @UiField("resetBtn2")
    public Button resetBtn2;
    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(CompanySearch.class);
    CommonUtil commonUtil = CommonUtil.getInstance();
    String updateType = StringUtils.EMPTY;
    CompanySearchTableLogic companyLogic = new CompanySearchTableLogic();

    UiUtils UIUtils = new UiUtils();
    public ExtPagedTable companySearchResultsTable = new ExtPagedTable(companyLogic);
    private BeanItemContainer<TradingPartnerDTO> companyResultsContainer = new BeanItemContainer<TradingPartnerDTO>(TradingPartnerDTO.class);
    CustomTreeContainer<TradingPartnerDTO> resultsLazyContainer = new CustomTreeContainer<TradingPartnerDTO>(TradingPartnerDTO.class);
    public TradingPartnerDTO tpDto = new TradingPartnerDTO();
    final ErrorLabel errorMsg = new ErrorLabel();
    /**
     * The data selection binder.
     */
    public CustomFieldGroup dataSelectionBinder = new CustomFieldGroup(new BeanItem<TradingPartnerDTO>(tpDto));
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    SessionDTO session = new SessionDTO();
    int parentCompanySid;
    ParentCompanyLookup parentCompanyLookup = null;
    private CustomTreeContainer<TradingPartnerDTO> contractExcelResultBean = new CustomTreeContainer<TradingPartnerDTO>(TradingPartnerDTO.class);
    private ExtCustomTable companyViewTable;
    private String searchSessionId = StringUtils.EMPTY;
    CompanySearchLogic logic = new CompanySearchLogic();

    public CompanySearch(String updateType) {
        addComponent(Clara.create(getClass().getResourceAsStream("/TradingPartner/companySearch.xml"), this));
        this.updateType = updateType;
        getBinder();
        configureFields();
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
            HorizontalLayout hLayout = new HorizontalLayout();
            hLayout = companyLogic.createControls();
            companySearchTableLayout.addComponent(hLayout);
            excelBtn.setIcon(excelExportImage);
            configureCompanySearchResultsTable();

            parentNo.addClickListener(new CustomTextField.ClickListener() {

                public void click(CustomTextField.ClickEvent event) {
                    parentCompanyLookup = new ParentCompanyLookup(parentNo, parentName, parentCompanySid);
                    UI.getCurrent().addWindow(parentCompanyLookup);
                }
            });

            parentName.addClickListener(new CustomTextField.ClickListener() {

                public void click(CustomTextField.ClickEvent event) {
                    parentCompanyLookup = new ParentCompanyLookup(parentNo, parentName, parentCompanySid);
                    UI.getCurrent().addWindow(parentCompanyLookup);
                }
            });

            if (TRADING_PARTNER_REMOVE.getConstant().equals(updateType)) {
                addBtn.setEnabled(false);
                editBtn.setEnabled(false);
                transferBtn.setEnabled(false);
                deleteBtn.setEnabled(true);
            } else if (ADD_TRADING_PARTNER.getConstant().equals(updateType)) {
                addBtn.setEnabled(true);
                editBtn.setVisible(false);
                transferBtn.setVisible(false);
                deleteBtn.setVisible(false);

            } else if (TRANSFER_TRADING_PARTNER.getConstant().equals(updateType) || PROJECTION_DETAILS_TRANSFER.getConstant().equals(updateType)) {
                addBtn.setEnabled(false);
                editBtn.setEnabled(false);
                transferBtn.setEnabled(true);
                deleteBtn.setEnabled(false);
            } else if (TRADING_PARTNER_UPDATE.getConstant().equals(updateType)) {
                addBtn.setEnabled(false);
                editBtn.setEnabled(true);
                transferBtn.setEnabled(false);
                deleteBtn.setEnabled(false);

            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage() + " - in configureFields");
        }
    }

    public void configureCompanySearchResultsTable() {
        final CommmonLogic logic = new CommmonLogic();
        companySearchResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        companySearchResultsTable.setWidth(100, Unit.PERCENTAGE);
        companySearchResultsTable.setHeight(400, Unit.PIXELS);
        companySearchResultsTable.setPageLength(5);
        companySearchResultsTable.setSortEnabled(false);
        companyLogic.setContainerDataSource(companyResultsContainer);

        companySearchResultsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            public void itemClick(ItemClickEvent event) {
                if (event.getItemId() != null) {
                    List<String> selectedCompany = new ArrayList<String>();
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
                CompanySearchLogic companyLogic = new CompanySearchLogic();
                List<IdDescriptionDTO> resultList = new ArrayList<IdDescriptionDTO>();
                if ("tradeClass".equals(propertyId)) {
                    try {
                        ComboBox tradeClass = new ComboBox();
                        commonUtil.loadComboBox(tradeClass, UIUtils.COMPANY_TRADE_CLASS, true);
                        return tradeClass;
                    } catch (Exception ex) {
                         LOGGER.error(ex.getMessage());
                    }
                }
                if ("companyType".equals(propertyId)) {
                    try {
                        ComboBox companyType = new ComboBox();
                        commonUtil.loadComboBox(companyType, UIUtils.COMPANY_TYPE, true);
                        return companyType;
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }
                if ("companyCategory".equals(propertyId)) {
                    try {
                        ComboBox companyCategory = new ComboBox();
                        commonUtil.loadComboBox(companyCategory, UIUtils.COMPANY_CATEGORY, true);
                        return companyCategory;
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }
                if ("state".equals(propertyId)) {
                    try {
                        ComboBox state = new ComboBox();
                        commonUtil.loadComboBox(state, UIUtils.STATE, true);
                        return state;
                    } catch (Exception ex) {
                          LOGGER.error(ex.getMessage());
                    }
                }
                if (propertyId.equals("check")) {
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

        companySearchResultsTable.setTableFieldFactory(new TableFieldFactory() {

            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                if (propertyId.equals("check")) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    check.setImmediate(true);
                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            int count = logic.callCompanyUpdate(check.getValue(), (TradingPartnerDTO) itemId, updateType, searchSessionId);
                            if (count == 0) {
                                logic.callCompanyInsert(check.getValue(), (TradingPartnerDTO) itemId, updateType, searchSessionId);
                            }

                            if (!check.getValue()) {

                                companySearchResultsTable.removeColumnCheckListener(checkListener);
                                companySearchResultsTable.setColumnCheckBox("check", true, false);
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
            companySearchResultsTable.setVisibleColumns(Constants.TP_COMPANY_SEARCH_COLUMNS);
            companySearchResultsTable.setColumnHeaders(Constants.TP_COMPANY_SEARCH_HEADERS);
            companySearchResultsTable.setSelectable(true);
        } else {
            companySearchResultsTable.setVisibleColumns(Constants.COMPANY_SEARCH_COLUMNS);
            companySearchResultsTable.setColumnHeaders(Constants.COMPANY_SEARCH_HEADERS);
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
                    LOGGER.error(ex.getMessage() + " - in resetBtn");
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
            session.setCompanyMasterSids(logic.getSelectedCompaniesList(searchSessionId, updateType));
            if (count > 0) {
                int count1 = logic.getDataCount(session.getCompanyMasterSids(), false);
                if (count1 > 0) {
                    SessionDTO session = createSession(updateType);
                    session.setSearchSessionId(searchSessionId);
                    session.setModuleName(TRADING_PARTNER_REMOVE.getConstant());
                    RemoveTPForm removeForm = new RemoveTPForm(session);
                    UI.getCurrent().addWindow(removeForm);
                } else {
                    AbstractNotificationUtils.getErrorNotification("No Valid Contracts", "There is no Contract attached with the selected Company ");
                    return;
                }

            } else {
                AbstractNotificationUtils.getErrorNotification("Remove Error", "Please select a value in the Results list view then try again.");
            }
        }
    }

    @UiHandler("addBtn")
    public void addBtn(Button.ClickEvent event) throws CloneNotSupportedException {
        if (ADD_TRADING_PARTNER.getConstant().equals(updateType)) {
            if (companySearchResultsTable.getValue() != null) {
                TradingPartnerDTO dto = (TradingPartnerDTO) companySearchResultsTable.getValue();
                SessionDTO session = createSession(updateType);
                session.setModuleName(ADD_TRADING_PARTNER.getConstant());
                List<String> companyIds = new ArrayList<String>();
                companyIds.add(dto.getCompanySystemId());
                session.setCompanyMasterSids(companyIds);
                AddTPForm removeForm = new AddTPForm(session);
                UI.getCurrent().addWindow(removeForm);
            } else {
                AbstractNotificationUtils.getErrorNotification(" Error", "Please select a value in the Results list view then try again.");
            }
        }
    }

    @UiHandler("transferBtn")
    public void transferBtn(Button.ClickEvent event) throws CloneNotSupportedException {
        ContractSelectionLogic logic = new ContractSelectionLogic();
        try {
            int count = CompanySearchLogic.getCompanyCount(searchSessionId);
            session.setCompanyMasterSids(logic.getSelectedCompaniesList(searchSessionId, updateType));
            if (TRANSFER_TRADING_PARTNER.getConstant().equals(updateType) || PROJECTION_DETAILS_TRANSFER.getConstant().equals(updateType)) {
                if (count > 0) {
                    int count1 = 0;
                    if (TRANSFER_TRADING_PARTNER.getConstant().equals(updateType)) {
                        count1 = logic.getDataCount(session.getCompanyMasterSids(), false);
                    } else {
                        count1 = 1;
                    }

                    if (count1 > 0) {
                        SessionDTO session = createSession(updateType);
                        session.setSearchSessionId(searchSessionId);
                        if (TRANSFER_TRADING_PARTNER.getConstant().equals(updateType)) {
                            session.setModuleName(TRANSFER_TRADING_PARTNER.getConstant());
                        } else {
                            session.setModuleName(PROJECTION_DETAILS_TRANSFER.getConstant());
                        }
                        TransferTPForm transferTp = new TransferTPForm(session);
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
        } catch (Exception e) {
           LOGGER.error(e);
        }
    }

    @UiHandler("editBtn")
    public void editBtn(Button.ClickEvent event) throws CloneNotSupportedException {
        if (TRADING_PARTNER_UPDATE.getConstant().equals(updateType)) {
            int count = CompanySearchLogic.getCompanyCount(searchSessionId);
            ContractSelectionLogic logic = new ContractSelectionLogic();
            session.setCompanyMasterSids(logic.getSelectedCompaniesList(searchSessionId, updateType));
            if (count > 0) {
                int count1 = logic.getDataCount(session.getCompanyMasterSids(), false);
                if (count1 > 0) {
                    SessionDTO session = createSession(updateType);
                    session.setSearchSessionId(searchSessionId);
                    session.setModuleName(TRADING_PARTNER_UPDATE.getConstant());
                    UpdateTPForm updateTPForm = new UpdateTPForm(session);
                    UI.getCurrent().addWindow(updateTPForm);
                } else {
                    AbstractNotificationUtils.getErrorNotification("No Valid Contracts", "There is no Contract attached with the selected Company ");
                    return;
                }

            } else {
                AbstractNotificationUtils.getErrorNotification("Update Error", "Please select a value in the Results list view then try again.");
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
        if (ADD_TRADING_PARTNER.getConstant().equals(updateType)) {
            companySearchResultsTable.setVisibleColumns(Constants.TP_COMPANY_SEARCH_COLUMNS);
            companySearchResultsTable.setColumnHeaders(Constants.TP_COMPANY_SEARCH_HEADERS);

        } else {
            companySearchResultsTable.setVisibleColumns(Constants.COMPANY_SEARCH_COLUMNS);
            companySearchResultsTable.setColumnHeaders(Constants.COMPANY_SEARCH_HEADERS);
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
                logic.insertIntoTempTable(searchSessionId, recordLockStatus, updateType);
                tpDto.setReset(Boolean.FALSE);
                companyLogic.loadSetData(tpDto, parentCompanyNo, parentCompanyName, recordLockStatus, searchSessionId);
                if (!companyLogic.isRecordPresent()) {
                    AbstractNotificationUtils.getErrorNotification("No Results Found",
                            "There are no results that match the search criteria. Please try again.");
                } else {
                    CommonUIUtils.getMessageNotification("Search Completed");
                }
            } catch (CommitException commit) {
                LOGGER.error(commit.getMessage() + " at commit");
            }
        }
    }

    private CustomFieldGroup getBinder() {
        dataSelectionBinder.bindMemberFields(this);
        dataSelectionBinder.setItemDataSource(new BeanItem<TradingPartnerDTO>(tpDto));
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
            LOGGER.error(e.getMessage() + "at excel export");
        }
    }

    public void createWorkSheet(String moduleName, ExtFilterTable resultTable) throws SystemException, PortalException, Exception {

        String parentCompanyNo = parentNo.getValue() != null ? parentNo.getValue() : StringUtils.EMPTY;
        String parentCompanyName = parentName.getValue() != null ? parentName.getValue() : StringUtils.EMPTY;
        long recordCount = 0;
        String recordLockStatus = StringUtils.EMPTY;
        List<String> visibleList = Arrays.asList(resultTable.getColumnHeaders()).subList(ADD_TRADING_PARTNER.getConstant().equals(updateType)?0:1, resultTable.getVisibleColumns().length);
        if (PROJECTION_DETAILS_TRANSFER.getConstant().equals(updateType)) {
            recordLockStatus = Constants.ZEROSTRING;
        }
        if (resultTable.size() != 0) {
            recordCount = logic.companySearchCount(tpDto, parentCompanyNo, parentCompanyName, companyLogic.getFilters(), recordLockStatus, searchSessionId);
        }
        ExcelExportforBB.createWorkSheet(visibleList.toArray(new String[visibleList.size()]), recordCount, this, UI.getCurrent(), moduleName.toUpperCase());

    }

    @UiHandler("resetBtn2")
    public void resetListView(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
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
                    LOGGER.error(ex.getMessage() + " - in resetBtn");
                }
            }
        }.getConfirmationMessage("Reset Confirmation", "Are you sure you want to reset the Results list view?");
    }

    ColumnCheckListener checkListener = new ColumnCheckListener() {
        @Override
        public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
            if (event.isChecked()) {

                checkClearAll(event.isChecked());
            }
        }

    };

    public void checkClearAll(boolean checkValue) {

        List<TradingPartnerDTO> containerList = resultsLazyContainer.getItemIds();

        for (TradingPartnerDTO dto : containerList) {
            companySearchResultsTable.getContainerProperty(dto, "check").setValue(checkValue);
        }
    }

    private void createSearchSessionId() {
        searchSessionId = CommonUtils.createSessionId();
    }

    // CSV Export. Do not remove the below method
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, Exception {
        LOGGER.info("Entering createWorkSheetContent with start "+start+" end "+end);
        String parentCompanyNo = parentNo.getValue() != null ? parentNo.getValue() : StringUtils.EMPTY;
        String parentCompanyName = parentName.getValue() != null ? parentName.getValue() : StringUtils.EMPTY;
        String recordLockStatus = StringUtils.EMPTY;
        List visibleList = Arrays.asList(companySearchResultsTable.getVisibleColumns()).subList(ADD_TRADING_PARTNER.getConstant().equals(updateType)?0:1, companySearchResultsTable.getVisibleColumns().length);
        if (PROJECTION_DETAILS_TRANSFER.getConstant().equals(updateType)) {
            recordLockStatus = Constants.ZEROSTRING;
        }
        try {
            if (end != 0) {
                final List<TradingPartnerDTO> searchList = (List<TradingPartnerDTO>) logic.searchCompaniesLazy(tpDto, start, end, companyLogic.getSortByColumns(), parentCompanyNo, parentCompanyName, companyLogic.getFilters(), recordLockStatus, searchSessionId,false);
                ExcelExportforBB.createFileContent(visibleList.toArray(), searchList, printWriter);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
