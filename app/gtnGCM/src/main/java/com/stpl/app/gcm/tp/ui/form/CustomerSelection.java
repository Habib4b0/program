package com.stpl.app.gcm.tp.ui.form;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.CommonUtil;
import static com.stpl.app.gcm.discount.ui.form.ExistingDiscountTab.LOGGER;
import com.stpl.app.gcm.security.StplSecurity;
import static com.stpl.app.gcm.security.StplSecurity.userMap;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.dto.CompanyLinkDTO;
import com.stpl.app.gcm.tp.dto.IdDescriptionDTO;
import com.stpl.app.gcm.tp.dto.TradingPartnerDTO;
import com.stpl.app.gcm.tp.logic.CompanySearchLogic;
import com.stpl.app.gcm.tp.logic.ContractSelectionLogic;
import com.stpl.app.gcm.tp.tablelogic.CompanySearchTableLogic;
import com.stpl.app.gcm.tp.tablelogic.LinkedCompaniesTableLogic;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.PROJECTION_DETAILS_TRANSFER;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.TAB_CURRENT_CONTRACT;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.TAB_TRANSFER_CONTRACT;
import com.stpl.app.gcm.util.Constants.MessageConstants;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CsvExportforPagedTable;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
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
 * @author Sriram
 */
public class CustomerSelection extends VerticalLayout {

    @UiField("selectedCustomersTableLayout")
    public VerticalLayout selectedCustomersTableLayout;

    @UiField("companySearchResultsLayout")
    public VerticalLayout companySearchResultsLayout;

    @UiField("transferCustomerTableLayout")
    public VerticalLayout transferCustomerTableLayout;

    @UiField("selectedCustomersExport")
    public Button selectedCustomersExport;

    @UiField("searchResultsExport")
    public Button searchResultsExport;

    @UiField("transferCustomersExport")
    public Button transferCustomersExport;
    @UiField("resetBtn")
    public Button resetBtn;
    @UiField("searchBtn")
    public Button searchBtn;
    @UiField("listViewResetBtn")
    public Button listViewResetBtn;
    @UiField("transferBtn")
    public Button transferBtn;
    @UiField("removeBtn")
    public Button removeBtn;

    @UiField("companyId")
    public TextField companyId;
    @UiField("companyName")
    public TextField companyName;
    @UiField("companyCategory")
    public ComboBox companyCategory;
    @UiField("identifierType")
    public ComboBox identifierType;
    @UiField("parentNo")
    private CustomTextField parentNo;
    @UiField("companyType")
    public ComboBox companyType;
    @UiField("companyNo")
    public TextField companyNo;
    @UiField("tradeClass")
    public ComboBox tradeClass;
    @UiField("identifier")
    public TextField identifier;
    @UiField("parentName")
    private CustomTextField parentName;

    ParentCompanyLookup parentCompanyLookup = null;
    int parentCompanySid;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CustomerSelection.class);
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    final transient StplSecurity stplSecurity = new StplSecurity();
    transient Map<String, AppPermission> functionHM = new HashMap<>();

    TransferTPForm transferTpForm;
    SessionDTO session;

    transient CompanySearchLogic logic = new CompanySearchLogic();
    transient List<IdDescriptionDTO> resultList;
    transient CompanySearchTableLogic selectedCustomersLogic = new CompanySearchTableLogic();
    transient CompanySearchTableLogic companyLogic = new CompanySearchTableLogic();
    transient LinkedCompaniesTableLogic transferCustomerTableLogic = new LinkedCompaniesTableLogic();

    public ExtPagedTable selectedCustomersTable = new ExtPagedTable(selectedCustomersLogic);
    public ExtPagedTable companySearchResultsTable = new ExtPagedTable(companyLogic);
    public ExtPagedTable transferCustomerTable = new ExtPagedTable(transferCustomerTableLogic);

    private BeanItemContainer<TradingPartnerDTO> selectedCustomersContainer = new BeanItemContainer<>(TradingPartnerDTO.class);
    private BeanItemContainer<TradingPartnerDTO> companyResultsContainer = new BeanItemContainer<>(TradingPartnerDTO.class);
    private BeanItemContainer<CompanyLinkDTO> transferCustomerContainer = new BeanItemContainer<>(CompanyLinkDTO.class);

    boolean isPlaceholdersAssociated = false;
    boolean transferFlag = false;
    private String linkedCustomersSessionId = CommonUtils.createSessionId();
    TradingPartnerDTO fromCompany;
    TradingPartnerDTO toCompany;

    String customerSearchSessionId = StringUtils.EMPTY;

    public TradingPartnerDTO tpDto = new TradingPartnerDTO();
    transient public CompanyLinkDTO companyDto = new CompanyLinkDTO();
    final ErrorLabel errorMsg = new ErrorLabel();
    transient CommonUtil commonUtil = CommonUtil.getInstance();

    /**
     * The data selection binder.
     */
    public CustomFieldGroup dataSelectionBinder = new CustomFieldGroup(new BeanItem<>(tpDto));

    @UiField("Excellayout")
    public HorizontalLayout Excellayout;
    @UiField("resultExcelLayout")
    public HorizontalLayout resultExcelLayout;
    @UiField("transferExcelLayout")
    public HorizontalLayout transferExcelLayout;

    public CustomerSelection(SessionDTO session, TransferTPForm form) {
        addComponent(Clara.create(getClass().getResourceAsStream("/TradingPartner/customerSelection.xml"), this));
        this.transferTpForm = form;
        this.session = session;
        configureFields();
        dataSelectionBinder = getBinder();
        configureSecurityPermissionsForProjectionDetails();
    }

    public void configureFields() {
        try {
            LOGGER.debug(" Entering configureFields");
            selectedCustomersExport.setIcon(excelExportImage);
            searchResultsExport.setIcon(excelExportImage);
            transferCustomersExport.setIcon(excelExportImage);

            selectedCustomersTableLayout.addComponent(selectedCustomersTable);
            companySearchResultsLayout.addComponent(companySearchResultsTable);
            transferCustomerTableLayout.addComponent(transferCustomerTable);
            commonUtil.loadComboBox(tradeClass, UiUtils.COMPANY_TRADE_CLASS, false);
            commonUtil.loadComboBox(companyCategory, UiUtils.COMPANY_CATEGORY, false);
            commonUtil.loadComboBox(companyType, UiUtils.COMPANY_TYPE, false);

            resultList = logic.loadIdentifierTypeDdlb();
            logic.setIdDescription(resultList, identifierType);
            identifierType.setNullSelectionAllowed(true);
            identifierType.select(Constants.SELECT_ONE);
            resultList.clear();

            selectedCustomersTable.setContainerDataSource(selectedCustomersContainer);
            selectedCustomersLogic.isProjSelected=true;
            selectedCustomersLogic.setContainerDataSource(selectedCustomersContainer);
            selectedCustomersLogic.sinkItemPerPageWithPageLength(false);
            companySearchResultsTable.setContainerDataSource(companyResultsContainer);
            companyLogic.sinkItemPerPageWithPageLength(false);
            companyLogic.setContainerDataSource(companyResultsContainer);
            transferCustomerTable.setContainerDataSource(transferCustomerContainer);
            transferCustomerTableLogic.setContainerDataSource(transferCustomerContainer);
            transferCustomerTableLogic.sinkItemPerPageWithPageLength(false);

            transferCustomerTable.setFilterGenerator(new ExtFilterGenerator() {
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
                    //empty
                }

                public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                    //empty
                }

                public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                    return null;
                }

                public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                    return null;
                }
            });
            transferCustomerTable.setFilterBarVisible(true);
            transferCustomerTable.setFilterDecorator(new ExtDemoFilterDecorator());

            selectedCustomersTable.setVisibleColumns(Constants.getInstance().companySearchColumnsWithoutCheck);
            selectedCustomersTable.setColumnHeaders(Constants.getInstance().companySearchHeadersWithoutCheck);
            companySearchResultsTable.setVisibleColumns(Constants.getInstance().companySearchColumnsWithoutCheck);
            companySearchResultsTable.setColumnHeaders(Constants.getInstance().companySearchHeadersWithoutCheck);
            transferCustomerTable.setVisibleColumns(Constants.getInstance().linkedCompanyColumns);
            transferCustomerTable.setColumnHeaders(Constants.getInstance().linkedCompanyHeaders);

            configureTable(selectedCustomersTable);
            configureTable(companySearchResultsTable);
            configureTable(transferCustomerTable);
            transferCustomerTable.setEditable(true);

            HorizontalLayout layout1 = selectedCustomersLogic.createControls();
            selectedCustomersTableLayout.addComponent(layout1);

            HorizontalLayout layout2;
            layout2 = companyLogic.createControls();
            companySearchResultsLayout.addComponent(layout2);

            HorizontalLayout layout3;
            layout3 = transferCustomerTableLogic.createControls();
            transferCustomerTableLayout.addComponent(layout3);

            selectedCustomersTable.addValueChangeListener(new Property.ValueChangeListener() {

                public void valueChange(Property.ValueChangeEvent event) {
                    if (event.getProperty().getValue() != null) {
                        fromCompany = (TradingPartnerDTO) event.getProperty().getValue();
                    } else {
                        fromCompany = null;
                    }
                }
            });

            selectedCustomersTable.setFilterGenerator(new ExtFilterGenerator() {
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
                
                public void filterRemoved(Object propertyId) {
                    return;
                }
                
                public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                    return;
                }
                
                public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                    return null;
                }
                
                public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                try {
                        if (propertyId.equals("companyType")) {
                            ComboBox companyType = new ComboBox();
                            companyType.setImmediate(true);
                            companyType.setWidth(NumericConstants.HUNDRED,Unit.PERCENTAGE);
                            companyType.setHeight("39px");
                            commonUtil.loadComboBox(companyType, UiUtils.COMPANY_TYPE, true);
                            return companyType;
                        }
                        if (propertyId.equals("companyCategory")) {
                            ComboBox companyCategory = new ComboBox();
                            companyCategory.setImmediate(true);
                            companyCategory.setWidth(NumericConstants.HUNDRED,Unit.PERCENTAGE);
                            companyCategory.setHeight("39px");
                            commonUtil.loadComboBox(companyCategory, UiUtils.COMPANY_CATEGORY, true);
                            return companyCategory;
                        }
                        if (propertyId.equals("tradeClass")) {
                            ComboBox tradeClass = new ComboBox();
                            tradeClass.setImmediate(true);
                            tradeClass.setWidth(NumericConstants.HUNDRED,Unit.PERCENTAGE);
                            tradeClass.setHeight("39px");
                            commonUtil.loadComboBox(tradeClass, UiUtils.COMPANY_TRADE_CLASS, true);
                            return tradeClass;
                        }
                        if (propertyId.equals("state")) {
                            ComboBox state = new ComboBox();
                            state.setImmediate(true);
                            state.setWidth(NumericConstants.HUNDRED,Unit.PERCENTAGE);
                            state.setHeight("39px");
                            commonUtil.loadComboBox(state, UiUtils.STATE, true);
                            return state;
                        }
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                    return null;
                }
            });
            selectedCustomersTable.setFilterBarVisible(true);
            selectedCustomersTable.setFilterDecorator(new ExtDemoFilterDecorator());

            companySearchResultsTable.addValueChangeListener(new Property.ValueChangeListener() {

                public void valueChange(Property.ValueChangeEvent event) {
                    if (event.getProperty().getValue() != null) {
                        toCompany = (TradingPartnerDTO) event.getProperty().getValue();
                    } else {
                        toCompany = null;
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
                
                public void filterRemoved(Object propertyId) {
                    return;
                }
                
                public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                    return;
                }
                
                public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                    return null;
                }
                
                public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                  try {
                        if (propertyId.equals("companyType")) {
                            ComboBox companyType = new ComboBox();
                            companyType.setImmediate(true);
                            companyType.setWidth(NumericConstants.HUNDRED,Unit.PERCENTAGE);
                            companyType.setHeight("39px");
                            commonUtil.loadComboBox(companyType, UiUtils.COMPANY_TYPE, true);
                            return companyType;
                        }
                        if (propertyId.equals("companyCategory")) {
                            ComboBox companyCategory = new ComboBox();
                            companyCategory.setImmediate(true);
                            companyCategory.setWidth(NumericConstants.HUNDRED,Unit.PERCENTAGE);
                            companyCategory.setHeight("39px");
                            commonUtil.loadComboBox(companyCategory, UiUtils.COMPANY_CATEGORY, true);
                            return companyCategory;
                        }
                        if (propertyId.equals("tradeClass")) {
                            ComboBox tradeClass = new ComboBox();
                            tradeClass.setImmediate(true);
                            tradeClass.setWidth(NumericConstants.HUNDRED,Unit.PERCENTAGE);
                            tradeClass.setHeight("39px");
                            commonUtil.loadComboBox(tradeClass, UiUtils.COMPANY_TRADE_CLASS, true);
                            return tradeClass;
                        }
                        if (propertyId.equals("state")) {
                            ComboBox state = new ComboBox();
                            state.setImmediate(true);
                            state.setWidth(NumericConstants.HUNDRED,Unit.PERCENTAGE);
                            state.setHeight("39px");
                            commonUtil.loadComboBox(state, UiUtils.STATE, true);
                            return state;
                        }
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                    return null;
                        }
            });
            companySearchResultsTable.setFilterBarVisible(true);
            companySearchResultsTable.setFilterDecorator(new ExtDemoFilterDecorator());

            transferCustomerTable.setTableFieldFactory(new TableFieldFactory() {

                public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                    if (propertyId.equals("check")) {
                        final ExtCustomCheckBox check = new ExtCustomCheckBox();
                        check.setImmediate(true);
                        check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                            public void click(ExtCustomCheckBox.ClickEvent event) {
                                if (itemId != null) {
                                    CompanyLinkDTO transferCustomerDto = (CompanyLinkDTO) itemId;
                                    logic.updateCheckRecord(transferCustomerDto.getFromCompanyMasterSid(), transferCustomerDto.getToCompanyMasterSid(), check.getValue(), linkedCustomersSessionId);

                                }
                            }
                        });
                        return check;
                    }
                    return null;
                }
            });

            // To Load the selected customers table in projection details transfer module
            TradingPartnerDTO selectedTpDto = new TradingPartnerDTO();
            selectedTpDto.setCompanyMasterSids(session.getCompanyMasterSids());
            selectedTpDto.setReset(false);
            selectedTpDto.setCompanyName("*");
            selectedTpDto.setCompanyRestrictionSessionId(linkedCustomersSessionId);
            selectedCustomersLogic.loadSetData(selectedTpDto, StringUtils.EMPTY, StringUtils.EMPTY, Constants.ZEROSTRING, session.getSearchSessionId());
            selectedCustomersLogic.setCurrentPage(1);
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
            LOGGER.debug("Exiting configureFields");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        transferCustomerTable.setFilterFieldVisible("check", false);
    }

    public void configureTable(ExtPagedTable pagedTable) {
        pagedTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        pagedTable.setSelectable(true);
        pagedTable.setImmediate(true);
        pagedTable.setSizeFull();
        pagedTable.setSelectable(true);
        pagedTable.setPageLength(NumericConstants.FIVE);
    }

    private CustomFieldGroup getBinder() {
        dataSelectionBinder.bindMemberFields(this);
        dataSelectionBinder.setItemDataSource(new BeanItem<>(tpDto));
        dataSelectionBinder.setBuffered(true);
        dataSelectionBinder.setErrorDisplay(errorMsg);
        return dataSelectionBinder;
    }

    @UiHandler("selectedCustomersExport")
    public void excelExport(Button.ClickEvent event) {
        LOGGER.debug("Invoked selectedCustomersExport");
        try {
            CsvExportforPagedTable.createWorkSheet(selectedCustomersTable.getColumnHeaders(), selectedCustomersTable.getVisibleColumns(), selectedCustomersLogic, "Selected Customers");

        } catch (Exception e) {
             LOGGER.error(e);
        }
        LOGGER.debug("Exiting selectedCustomersExport");
    }

    @UiHandler("searchResultsExport")
    public void resultsexcelExport(Button.ClickEvent event) {
        LOGGER.debug("Invoked searchResultsExport");
        try {
            CsvExportforPagedTable.createWorkSheet(companySearchResultsTable.getColumnHeaders(), companySearchResultsTable.getVisibleColumns(), companyLogic, "Customer Search");
        } catch (Exception e) {
             LOGGER.error(e);
        }
        LOGGER.debug("Exiting searchResultsExport");
    }

    @UiHandler("transferCustomersExport")
    public void transferexcelExport(Button.ClickEvent event) {
        LOGGER.debug("Invoked transferCustomersExport");
        try {
            List tempVisibleHeaders = new ArrayList(Arrays.asList(transferCustomerTable.getColumnHeaders()));
            tempVisibleHeaders.remove(0);
            String[] visibleHeaders = Arrays.copyOf(tempVisibleHeaders.toArray(), tempVisibleHeaders.size(), String[].class);

            List tempVisibleColumns = new ArrayList(Arrays.asList(transferCustomerTable.getVisibleColumns()));
            tempVisibleColumns.remove(0);
            Object[] visibleColumns = tempVisibleColumns.toArray();

            CsvExportforPagedTable.createWorkSheet(visibleHeaders, visibleColumns, transferCustomerTableLogic, "Transfer Customers");
        } catch (Exception e) {
             LOGGER.error(e);
        }
        LOGGER.debug("Exiting transferCustomersExport");
    }

    @UiHandler("searchBtn")
    public void searchBtnLogic(Button.ClickEvent event) {
        LOGGER.debug(" Entering searchBtnLogic");
        companyResultsContainer.removeAllItems();
        Map<Integer, String> users;
        String recordLockStatus = "1";
        String userid= "";

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
                logic.clearTempTable(customerSearchSessionId);
                customerSearchSessionId = createSearchSessionId();
                users = getUserName();
                for (Map.Entry<Integer, String> entry : users.entrySet()) {
                    if (entry.getValue().contains("ETL")) {
                        userid = entry.getKey().toString();
                    }
                }
                logic.insertIntoTempTablecustomer(customerSearchSessionId,  PROJECTION_DETAILS_TRANSFER.getConstant());
                tpDto.setReset(Boolean.FALSE);
                tpDto.setCompanyRestrictionSessionId(linkedCustomersSessionId);
                companyLogic.loadSetData(tpDto, parentCompanyNo, parentCompanyName, recordLockStatus, customerSearchSessionId);
                if (!companyLogic.isRecordPresent()) {
                    AbstractNotificationUtils.getErrorNotification("No Results Found",
                            "There are no results that match the search criteria. Please try again.");
                } else {
                    CommonUIUtils.getMessageNotification("Search Completed");
                }
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        LOGGER.debug("Exiting searchBtnLogic");
    }

    
    
     public static Map<Integer, String> getUserName()  {
        try {
            LOGGER.debug("Enters getUserName method");
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
            List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
            for (User user : userList) {
                userMap.put(Long.valueOf(user.getUserId()).intValue(), user.getFullName());
            }
            LOGGER.debug("End of getUserName method");
           
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
         return userMap;
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

    @UiHandler("resetBtn")
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
                    LOGGER.error(ex);
                }
            }
        }.getConfirmationMessage("Reset Confirmation", "Are you sure you want to reset the Company Search?");
    }

    @UiHandler("listViewResetBtn")
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
                companyResultsContainer.removeAllItems();
                companySearchResultsTable.resetFilters();

                toCompany = null;
            }
        }.getConfirmationMessage("Reset Confirmation", "Are you sure you want to reset the Results list view?");
    }

    @UiHandler("transferBtn")
    public void transferButtonLogic(Button.ClickEvent event) {
        LOGGER.debug("Entering transferButtonLogic");
        if (fromCompany != null && toCompany != null) {

            int updateFromCount = logic.updateCustomer(fromCompany.getCompanySystemId(), Constants.ZEROSTRING, linkedCustomersSessionId);
            if (updateFromCount == 0) {
                logic.insertCustomer(fromCompany.getCompanySystemId(), fromCompany.getCompanyId(), fromCompany.getCompanyNo(), fromCompany.getCompanyName(), Constants.ZEROSTRING, linkedCustomersSessionId);
            }

            int updateToCount = logic.updateCustomer(toCompany.getCompanySystemId(), fromCompany.getCompanySystemId(), linkedCustomersSessionId);
            if (updateToCount == 0) {
                logic.insertCustomer(toCompany.getCompanySystemId(), toCompany.getCompanyId(), toCompany.getCompanyNo(), toCompany.getCompanyName(), fromCompany.getCompanySystemId(), linkedCustomersSessionId);
            }

            transferCustomerTableLogic.loadSetData(linkedCustomersSessionId, false);
            refreshAllTables();
            fromCompany = null;
            toCompany = null;
            transferFlag = true;
        } else {
            AbstractNotificationUtils.getErrorNotification("Transfer Error", "Please select a company value in both the Selected Customers, \n and the Results list view then try again.");
        }
    }

    @UiHandler("removeBtn")
    public void removeButtonLogic(Button.ClickEvent event) {
        if (logic.isAnyRecordSelectedToRemove(linkedCustomersSessionId)) {
            logic.removeCustomerLink(linkedCustomersSessionId);
            refreshAllTables();
        } else {
            AbstractNotificationUtils.getErrorNotification("No values selected", "Please select a record to remove. Then try again.");
        }

    }

    @UiHandler("NextBtn")
    public void nextButtonLogic(Button.ClickEvent event) {
        if (tabChangeCheck()) {
            tabChangeLogic();
            transferTpForm.getTabSheet().setSelectedTab(transferTpForm.getTabPosition() + 1);
            UI.getCurrent().setFocusedComponent(UI.getCurrent());
        }
    }

    @UiHandler("closeBtn")
    public void closeBtnLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            public void noMethod() {
                // do nothing
            }

            @Override
            public void yesMethod() {
                try {
                    transferTpForm.close();
                } catch (Exception ex) {
                     LOGGER.error(ex);
                }
            }
        }.getConfirmationMessage("Close confirmation", "Are you sure you want to close out? \n No values will be saved. ");

    }

    private String createSearchSessionId() {
        return CommonUtils.createSessionId();
    }

    private void refreshAllTables() {
        companyLogic.setCurrentPage(1);
        selectedCustomersLogic.setCurrentPage(1);
        transferCustomerTableLogic.setCurrentPage(1);
    }

    public BeanItemContainer<TradingPartnerDTO> getSelectedCustomersContainer() {
        return selectedCustomersContainer;
    }

    public List<String> getLinkedCompaniesList() {
        return logic.getLinkedCompaniesList(linkedCustomersSessionId, false);
    }

    public boolean tabChangeCheck() {
        LOGGER.debug("Entering customer selection tabChangeCheck");
        boolean isTabChange = false;
        if (selectedCustomersContainer.size() == 0) {
            isTabChange = true;
        } else {
            AbstractNotificationUtils.getErrorNotification("Cannot Proceed till all Companies are linked", "Please complete the matching of all Companies in the \n ‘Selected Customers’ list view before you proceed. ");
        }
        LOGGER.debug("Exiting customer selection tabChangeCheck");
        return isTabChange;
    }

    public void tabChangeLogic() {
        LOGGER.debug("Entering tabChangeLogic");
        session = CommonUtils.attachSessionId(session);
        session.setPhCompanyMasterSids(getLinkedCompaniesList());
        insertDataIntoTempTable();
        LOGGER.debug("Exiting tabChangeLogic");
    }

    public boolean isTransferFlag() {
        return transferFlag;
    }

    public void setTransferFlag(boolean transferFlag) {
        this.transferFlag = transferFlag;
    }

    private void insertDataIntoTempTable() {
        ContractSelectionLogic logic = new ContractSelectionLogic();
        logic.insertDataIntoTempTable(session.getUserId(), session.getSessionId(), session.getCompanyMasterSids(), TAB_CURRENT_CONTRACT.getConstant(), false);
        logic.insertDataIntoTempTable(session.getUserId(), session.getSessionId(), session.getPhCompanyMasterSids(), TAB_TRANSFER_CONTRACT.getConstant(), false);
        logic.removeStartDateFromTempTable(session.getUserId(), session.getSessionId(), TAB_TRANSFER_CONTRACT.getConstant()); //To remove start date from temp table for Transfer projection details transfer tab
    }
    private void configureSecurityPermissionsForProjectionDetails() {
        try {
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "GCM-Customer Management", "Projection Details Transfer", "CustomerSelctionTab");
            searchBtn.setVisible(CommonLogic.isButtonVisibleAccess("searchBtn", functionHM));
            resetBtn.setVisible(CommonLogic.isButtonVisibleAccess("resetBtn", functionHM));
            searchResultsExport.setVisible(CommonLogic.isButtonVisibleAccess("searchResultsExport", functionHM));
            listViewResetBtn.setVisible(CommonLogic.isButtonVisibleAccess("listViewResetBtn", functionHM));
            transferBtn.setVisible(CommonLogic.isButtonVisibleAccess("transferBtn", functionHM));
            transferCustomersExport.setVisible(CommonLogic.isButtonVisibleAccess("listViewResetBtn", functionHM));
            removeBtn.setVisible(CommonLogic.isButtonVisibleAccess("transferBtn", functionHM));

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}
