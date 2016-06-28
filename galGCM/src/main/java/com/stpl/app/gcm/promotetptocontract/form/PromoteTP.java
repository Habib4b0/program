/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.form;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.promotetptocontract.dto.PromoteTpToChDto;
import com.stpl.app.gcm.promotetptocontract.logic.CompanySearchLogic;
import com.stpl.app.gcm.promotetptocontract.logic.CompanySearchTableLogic;
import com.stpl.app.gcm.promotetptocontract.logic.PromoteTPLogic;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.dto.IdDescriptionDTO;
import com.stpl.app.gcm.tp.ui.form.CompanySearch;
import com.stpl.app.gcm.ui.errorhandling.ErrorLabel;
import com.stpl.app.gcm.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.PROMOTE_TRADING_PARTNER;
import com.stpl.app.gcm.util.Constants.MessageConstants;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author alok.v
 */
public class PromoteTP extends VerticalLayout {

    SessionDTO session = new SessionDTO();
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(PromoteTP.class);
    public PromoteTpToChDto promoteTpToChDto = new PromoteTpToChDto();
    public CustomFieldGroup promoteTpToChDtoBinder;
    @UiField("promoteTpToChDtoTableLayout")
    public VerticalLayout promoteTpToChDtoTableLayout;
    @UiField("companyType")
    public ComboBox companyType;
    @UiField("companyCategory")
    public ComboBox companyCategory;
    @UiField("tradeClass")
    public ComboBox tradeClass_DTO;
    @UiField("identifierType")
    public ComboBox identifierType;
    @UiField("companyId")
    public TextField companyId;
    @UiField("companyName")
    public TextField companyName;
    @UiField("companyNo")
    public TextField companyNo;
    @UiField("identifier")
    public TextField identifier;
    @UiField("resetBtn1")
    public Button resetBtn1;
    @UiField("resetBtn2")
    public Button resetBtn2;
    @UiField("transferBtn")
    public Button transferBtn;
    @UiField("promoteTpToChPanel1")
    public Panel promoteTpToChPanel1;
    @UiField("promoteTpToChPanel2")
    public Panel promoteTpToChPanel2;
    @UiField("buttonLayout")
    public HorizontalLayout buttonLayout;
    @UiField("addBtn")
    public Button addBtn;
    @UiField("editBtn")
    public Button editBtn;
    @UiField("deleteBtn")
    public Button deleteBtn;
    @UiField("excelBtn")
    public Button excelBtn;
     /**
     * The ErrorLabel.
     */
    @UiField("errorMsg")
    public ErrorLabel errorMsg;
    private BeanItemContainer<PromoteTpToChDto> resultsContainer = new BeanItemContainer<PromoteTpToChDto>(PromoteTpToChDto.class);
    LazyBeanItemContainer<PromoteTpToChDto> resultsLazyContainer;
    HelperDTO ddlbDefaultValue = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
    PromoteTPLogic logic = new PromoteTPLogic();
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    String updateType = PROMOTE_TRADING_PARTNER.getConstant();
    private ExtCustomTable companyViewTable;
    private CustomTreeContainer<PromoteTpToChDto> companyExcelResultBean = new CustomTreeContainer<PromoteTpToChDto>(PromoteTpToChDto.class);
    int parentCompanySid;
    String searchSessionId = StringUtils.EMPTY;
    CompanySearchTableLogic compLogic = new CompanySearchTableLogic();
    public ExtPagedTable companySearchResultsTable = new ExtPagedTable(compLogic);
    public ErrorfulFieldGroup dataSelectionBinder = new ErrorfulFieldGroup(new BeanItem<PromoteTpToChDto>(promoteTpToChDto));
    CommonUtil commonUtil = CommonUtil.getInstance();
    UiUtils UIUtils = new UiUtils();
    public LazyBeanItemContainer<PromoteTpToChDto> getResultsLazyContainer() {
        return resultsLazyContainer;
    }

    public void setResultsLazyContainer(LazyBeanItemContainer<PromoteTpToChDto> resultsLazyContainer) {
        this.resultsLazyContainer = resultsLazyContainer;
    }

    /**
     * @return the companyId
     */
    public TextField getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId the companyId to set
     */
    public void setCompanyId(final TextField companyId) {
        this.companyId = companyId;
    }

    /**
     * @return the companyNo
     */
    public TextField getCompanyNo() {
        return companyNo;
    }

    /**
     * @param companyNo the companyId to set
     */
    public void setCompanyNo(final TextField companyNo) {
        this.companyNo = companyNo;
    }

    /**
     * @return the companyName
     */
    public TextField getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyId to set
     */
    public void setCompanyName(final TextField companyName) {
        this.companyName = companyName;
    }

    public TextField getIdentifier() {
        return identifier;
    }

    public void setIdentifier(TextField identifier) {
        this.identifier = identifier;
    }

    /**
     *
     * @throws Exception
     */
    public PromoteTP() throws Exception {
        super();
        addComponent(Clara.create(getClass().getResourceAsStream("/promoteTpToContractHolder.xml"), this));
        configureFields();
        dataSelectionBinder=getBinder();
    }

    /**
     * Configure Field Method
     *
     */
    protected void configureFields() {
        companyId.setData("maxlengthvalidationhundred,maxlengthvalidationcompanyid,specialchar,specialcharcompanyid");
        companyId.setImmediate(true);
        companyId.setValidationVisible(true);
        companyId.focus();

        companyNo.setData("maxlengthvalidationhundred,maxlengthvalidationcompanyno,specialchar,specialcharcompanyno");
        companyNo.setImmediate(true);
        companyNo.setValidationVisible(true);

        companyName.setData("maxlengthvalidationhundred,maxlengthvalidationcompanyname,specialchar,specialcharcompanyname");
        companyName.setImmediate(true);
        companyName.setValidationVisible(true);

        identifier.setData("maxlengthvalidationhundred,maxlengthvalidationcompanyidentifier,specialchar,specialcharcompanyidentifier");
        identifier.setImmediate(true);
        identifier.setValidationVisible(true);

        companyType.addItem(ddlbDefaultValue);
        companyType.setNullSelectionAllowed(true);
        companyType.setNullSelectionItemId(ddlbDefaultValue);

        companyCategory.addItem(ddlbDefaultValue);
        companyCategory.setNullSelectionAllowed(true);
        companyCategory.setNullSelectionItemId(ddlbDefaultValue);

        tradeClass_DTO.setNullSelectionAllowed(Boolean.TRUE);
        tradeClass_DTO.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        tradeClass_DTO.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        tradeClass_DTO.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());

        identifierType.addItem(ddlbDefaultValue);
        identifierType.setNullSelectionAllowed(true);
        identifierType.setNullSelectionItemId(ddlbDefaultValue);
        promoteTpToChDtoTableLayout.addComponent(companySearchResultsTable);
        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout = compLogic.createControls();
        promoteTpToChDtoTableLayout.addComponent(hLayout);
        configureCompanySearchResultsTable();
        excelBtn.setIcon(excelExportImage);
        loadCompanyType();
        loadTradeClass();
        loadCompanyQualifier();
        loadCompanyCategory();
        disableButtonLogic();
        
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
                com.stpl.app.gcm.tp.logic.CompanySearchLogic companyLogic = new com.stpl.app.gcm.tp.logic.CompanySearchLogic();
                List<IdDescriptionDTO> resultList = new ArrayList<IdDescriptionDTO>();
                if ("tradeClass".equals(propertyId)) {
                    try {
                        ComboBox tradeClass = new ComboBox();
                        commonUtil.loadComboBox(tradeClass, UIUtils.COMPANY_TRADE_CLASS, true);
                        return tradeClass;
                    } catch (Exception ex) {
                        Logger.getLogger(CompanySearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if ("companyType".equals(propertyId)) {
                    try {
                        ComboBox companyType = new ComboBox();
                        commonUtil.loadComboBox(companyType, UIUtils.COMPANY_TYPE, true);
                        return companyType;
                    } catch (Exception ex) {
                        Logger.getLogger(CompanySearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if ("companyCategory".equals(propertyId)) {
                    try {
                        ComboBox companyCategory = new ComboBox();
                        commonUtil.loadComboBox(companyCategory, UIUtils.COMPANY_CATEGORY, true);
                        return companyCategory;
                    } catch (Exception ex) {
                        Logger.getLogger(CompanySearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if ("state".equals(propertyId)) {
                    try {
                        ComboBox state = new ComboBox();
                        commonUtil.loadComboBox(state, UIUtils.STATE, true);
                        return state;
                    } catch (Exception ex) {
                        Logger.getLogger(CompanySearch.class.getName()).log(Level.SEVERE, null, ex);
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
        });
        
    }

    /**
     * Configure Company Search Results Table
     *
     */
    public void configureCompanySearchResultsTable() {
        companySearchResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        companySearchResultsTable.setWidth(100, Unit.PERCENTAGE);
        companySearchResultsTable.setHeight(400, Unit.PIXELS);
        companySearchResultsTable.setPageLength(5);
        compLogic.setContainerDataSource(resultsContainer);

        companySearchResultsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            public void itemClick(ItemClickEvent event) {
                if (event.getItemId() != null) {
                    PromoteTpToChDto tpDTO = (PromoteTpToChDto) event.getItemId();
                    session.setCompanyNo(tpDTO.getCompanyNo());
                    session.setCompanyName(tpDTO.getCompanyName());
                    session.setCompanyType(tpDTO.getCompanyType());
                    session.setCompanyCategory(tpDTO.getCompanyCategory());
                    session.setTradeClass(tpDTO.getTradeClass());
                    session.setCompanyMasterSid(tpDTO.getCompanySystemId());
                    List<String> companyMasterSids = new ArrayList<String>();
                    companyMasterSids.add(session.getCompanyMasterSid());
                    session.setCompanyMasterSids(companyMasterSids);
                    session.setSearchSessionId(searchSessionId);
                    LOGGER.info("createSearchSessionId before callCompanyInsert:" + searchSessionId);

                }
            }
        });
        companySearchResultsTable.setVisibleColumns(Constants.PROMOTE_TP_RESULTS_COLUMNS);
        companySearchResultsTable.setColumnHeaders(Constants.PROMOTE_TP_RESULTS_HEADERS);
        companySearchResultsTable.setSelectable(true);
        companySearchResultsTable.setFilterBarVisible(true);
        companySearchResultsTable.addStyleName("filterbar");
        companySearchResultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
    }

    /**
     * Reset Company Search Criteria
     *
     */
    public void companySearchReset() {
         dataSelectionBinder.getErrorDisplay().clearError();
         dataSelectionBinder.setItemDataSource(new BeanItem<PromoteTpToChDto>(new PromoteTpToChDto()));
              
    }

    /**
     * Transfer Button Logic
     *
     * @param event
     * @throws Exception
     */
    @UiHandler("transferBtn")
    public void transferBtnLogic(Button.ClickEvent event) throws Exception {
        createSession(updateType);
        if (companySearchResultsTable.getValue() != null) {
            PromoteTpToChWindow window = new PromoteTpToChWindow(session, companySearchResultsTable);
            UI.getCurrent().addWindow(window);
        } else {
            AbstractNotificationUtils.getErrorNotification(MessageConstants.NO_TP_SELECTED_TO_PROMOTE.getConstant(), MessageConstants.EMPTY_SPACE.getConstant() + "Please select a value in the Results list view then try again.");
        }
    }

    /**
     * Reset Button Functionality
     *
     * @param event
     */
    @UiHandler("resetBtn1")
    public void resetBtn1Logic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Confirm Reset", " Are you sure you want to reset the Company Search to default/previous values?\n ", new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    try {
                        companySearchReset();
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    /**
     * Search Button Logic
     *
     * @param event
     * @throws Exception
     */
    @UiHandler("searchBtn")
    public void searchBtnLogic(Button.ClickEvent event) {
        resultsContainer.removeAllItems();
        companySearchResultsTable.setContainerDataSource(resultsContainer);
        companySearchResultsTable.setVisibleColumns(Constants.PROMOTE_TP_RESULTS_COLUMNS);
        companySearchResultsTable.setColumnHeaders(Constants.PROMOTE_TP_RESULTS_HEADERS);

        if (resultsLazyContainer != null) {
            resultsLazyContainer.removeAllItems();
        }
        companySearchResultsTable.removeAllItems();
        if ((StringUtils.isBlank(companyName.getValue()) || Constants.NULL.equals(companyName.getValue()))
                && (StringUtils.isBlank(companyId.getValue()) || Constants.NULL.equals(companyId.getValue()))
                && (StringUtils.isBlank(companyNo.getValue()) || Constants.NULL.equals(companyNo.getValue()))
                && (StringUtils.isBlank(String.valueOf(companyType.getValue())) || Constants.NULL.equals(companyType.getValue()))
                && (StringUtils.isBlank(String.valueOf(companyCategory.getValue())) || Constants.NULL.equals(companyCategory.getValue()))
                && (StringUtils.isBlank(String.valueOf(tradeClass_DTO.getValue())) || Constants.NULL.equals(tradeClass_DTO.getValue()))
                && (StringUtils.isBlank(String.valueOf(identifierType.getValue())) || Constants.NULL.equals(identifierType.getValue()))
                && (StringUtils.isBlank(identifier.getValue()) || Constants.NULL.equals(identifier.getValue()))) {
            AbstractNotificationUtils.getErrorNotification(MessageConstants.NO_SEARCH_CRITERIA_TITLE.getConstant(),
                    MessageConstants.NO_SEARCH_CRITERIA_BODY.getConstant());
        } else {
            try {
                dataSelectionBinder.commit();
                bindSearchSelectionDtoToSave();
                createSearchSessionId();
                promoteTpToChDto.setReset(Boolean.FALSE);
                if (!compLogic.loadSetData(promoteTpToChDto, searchSessionId)) {
                    AbstractNotificationUtils.getErrorNotification("No Matching Records",
                            "There were no records matching the search criteria.  Please try again.");
                } else {
                    CommonUIUtils.getMessageNotification("Search Completed");
                }
            } catch (FieldGroup.CommitException commit) {
                LOGGER.error(commit.getMessage() + " at commit");
            }
        }
    }

    /**
     * Binder method to hold details for search criteria
     *
     */
    private void bindSearchSelectionDtoToSave() {
        try {
            if (companyName.getValue() != null) {
                String compValue = companyName.getValue();
                String compId = companyId.getValue();
                String compNo = companyNo.getValue();

                promoteTpToChDto.setCompanyName(compValue);
                promoteTpToChDto.setCompanyId(compId);
                promoteTpToChDto.setCompanyNo(compNo);
                promoteTpToChDto.setCompanyType(String.valueOf(companyType.getValue()));
                promoteTpToChDto.setCompanyCategory(String.valueOf(companyCategory.getValue()));
                promoteTpToChDto.setTradeClass(String.valueOf(tradeClass_DTO.getValue()));
                promoteTpToChDto.setIdentifierType(String.valueOf(identifierType.getValue()));
                promoteTpToChDto.setIdentifier(identifier.getValue());

            } else {
                promoteTpToChDto.setCompanyName(String.valueOf(0));
                promoteTpToChDto.setCompanyId(String.valueOf(0));
                promoteTpToChDto.setCompanyNo(String.valueOf(0));
                promoteTpToChDto.setCompanyCategory(String.valueOf(0));
                promoteTpToChDto.setTradeClass(String.valueOf(0));
                promoteTpToChDto.setIdentifierType(String.valueOf(0));
                promoteTpToChDto.setIdentifier(String.valueOf(0));
            }
        } catch (Exception ex) {
           
            LOGGER.error(ex.getMessage() + " in binding for save, can't parse dates");
        }
    }

    /**
     * Reset Button Logic - Results Section
     *
     * @param event
     */
    @UiHandler("resetBtn2")
    public void resetBtn2Logic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Confirm Reset", " Are you sure you want to reset the page to default/previous values?\n ", new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    try {
                        searchResultsReset();
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    /**
     * Search Result Reset Logic
     *
     */
    public void searchResultsReset() {
        promoteTpToChDto.setReset(Boolean.TRUE);
        compLogic.loadSetData(promoteTpToChDto, searchSessionId);

    }

    /**
     * Loading Company Type
     *
     */
    private void loadCompanyType() {
        logic.LazyLoadDdlb(companyType, "getCompanyTypeCountForPromoteTpToCh", "getCompanyTypeForPromoteTpToCh");
    }

    /**
     * Loading Trade Class
     *
     */
    private void loadTradeClass() {
        logic.LazyLoadDdlb(tradeClass_DTO, "getTradeClassCountForPromoteTpToCh", "getTradeClassForPromoteTpToCh");
    }

    /**
     * Loading Company Identifier Type
     *
     */
    private void loadCompanyQualifier() {
        logic.LazyLoadDdlb(identifierType, "getCompanyQualifierCountForPromoteTpToCh", "getCompanyQualifierForPromoteTpToCh");
    }

    /**
     * Loading Company Category
     *
     */
    private void loadCompanyCategory() {
        logic.LazyLoadDdlb(companyCategory, "getCompanyCategoryCountForPromoteTpToCh", "getCompanyCategoryForPromoteTpToCh");
    }

    public void disableButtonLogic() {
        addBtn.setEnabled(false);
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
    }

    /**
     * Excel Export at Company Search Screen
     *
     * @param event
     */
    @UiHandler("excelBtn")
    public void excelExport(Button.ClickEvent event) {
        try {
             int recordCount = companySearchResultsTable.getContainerDataSource().size();
             CompanySearchLogic companySearchLogic = new CompanySearchLogic();
             if (recordCount > 0) {
               recordCount = companySearchLogic.companySearchCount(promoteTpToChDto, null);
                 createWorkSheet("Company_Details", companySearchResultsTable,recordCount);
            }
             promoteTpToChDtoTableLayout.removeComponent(companyViewTable);
        } catch (Exception e) {
            LOGGER.error(e.getMessage() + "at excel export");
        }
    }

    /**
     * Configure table for excel export
     *
     */
    private void configureCompanyExcelResultTable() {
        companyExcelResultBean = new CustomTreeContainer<PromoteTpToChDto>(PromoteTpToChDto.class);
        companyViewTable = new ExtCustomTable();
        promoteTpToChDtoTableLayout.addComponent(companyViewTable);
        companyViewTable.setRefresh(Boolean.FALSE);
        companyViewTable.setVisible(false);
        companyViewTable.setContainerDataSource(companyExcelResultBean);
        companyViewTable.setVisibleColumns(companySearchResultsTable.getVisibleColumns());
        companyViewTable.setColumnHeaders(companySearchResultsTable.getColumnHeaders());
    }

    /**
     * The create session method
     *
     * @param moduleName
     */
    private void createSession(String moduleName) {
        session = CommonUtils.attachSessionId(session);
        session.setModuleName(moduleName);
    }

    private void createSearchSessionId() {
        searchSessionId = CommonUtils.createSessionId();
    }
    public ErrorfulFieldGroup getBinder() {
        final PromoteTpToChDto bean = new PromoteTpToChDto();
        final ErrorfulFieldGroup dataSelectionBinder = new ErrorfulFieldGroup(new BeanItem<PromoteTpToChDto>(bean));
        dataSelectionBinder.setBuffered(true);
        dataSelectionBinder.bindMemberFields(this);
        dataSelectionBinder.setErrorDisplay(errorMsg);
        return dataSelectionBinder;
    }
    
    public void createWorkSheet(String moduleName, ExtPagedTable resultTable,int count) throws SystemException, PortalException, Exception {
        ExcelExportforBB.createWorkSheet(resultTable.getColumnHeaders(), count, this, UI.getCurrent(), moduleName.toUpperCase());

    }
    
    
      public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, Exception {
        try {
            if (end != 0) {
              CompanySearchLogic companySearchLogic = new CompanySearchLogic();
              final List<OrderByColumn> columns = new ArrayList<OrderByColumn>();
              final List<PromoteTpToChDto> searchList = companySearchLogic.searchCompaniesLazy(promoteTpToChDto, start, end, columns, null, searchSessionId);
              ExcelExportforBB.createFileContent(companySearchResultsTable.getVisibleColumns(), searchList, printWriter);
                    }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
    
}
