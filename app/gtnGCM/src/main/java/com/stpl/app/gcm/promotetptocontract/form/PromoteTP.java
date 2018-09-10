/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.form;

import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.promotetptocontract.dto.PromoteTpToChDto;
import com.stpl.app.gcm.promotetptocontract.logic.CompanySearchLogic;
import com.stpl.app.gcm.promotetptocontract.logic.CompanySearchTableLogic;
import com.stpl.app.gcm.promotetptocontract.logic.PromoteTPLogic;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.ui.form.CompanySearch;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.PROMOTE_TRADING_PARTNER;
import com.stpl.app.gcm.util.Constants.MessageConstants;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.addons.lazycontainer.LazyBeanItemContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author alok.v
 */
public class PromoteTP extends VerticalLayout {

    private SessionDTO session = new SessionDTO();
    private static final Logger LOGGER = LoggerFactory.getLogger(PromoteTP.class);
    
    private final PromoteTpToChDto promoteTpToChDto = new PromoteTpToChDto();
    private ErrorfulFieldGroup promoteTpToChDtoBinder;
    @UiField("promoteTpToChDtoTableLayout")
    public VerticalLayout promoteTpToChDtoTableLayout;
    @UiField("companyType")
    public ComboBox companyType;
    @UiField("companyCategory")
    public ComboBox companyCategory;
    @UiField("tradeClass")
    public ComboBox tradeClassDto;
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
    private final BeanItemContainer<PromoteTpToChDto> resultsContainer = new BeanItemContainer<>(PromoteTpToChDto.class);
    private LazyBeanItemContainer<PromoteTpToChDto> resultsLazyContainer;
    private final HelperDTO ddlbDefaultValue = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
    private final PromoteTPLogic logic = new PromoteTPLogic();
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    private final String updateType = PROMOTE_TRADING_PARTNER.getConstant();
    private ExtCustomTable companyViewTable;
    private String searchSessionId = StringUtils.EMPTY;
    private final CompanySearchTableLogic compLogic = new CompanySearchTableLogic();
    private final ExtPagedTable companySearchResultsTable = new ExtPagedTable(compLogic);
    private ErrorfulFieldGroup dataSelectionBinder = new ErrorfulFieldGroup(new BeanItem<>(promoteTpToChDto));
    private final CommonUtil commonUtil = CommonUtil.getInstance();
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
    public PromoteTP() {
        super();
        addComponent(Clara.create(getClass().getResourceAsStream("/promoteTpToContractHolder.xml"), this));
        configureFields();
        dataSelectionBinder=getBinder();
    }

    /**
     * Configure Field Method
     *
     */
    protected final void configureFields() {
        companyId.setData("maxlengthvalidationhundred,maxlengthvalidationcompanyid,specialchar,specialcharcompanyid");
        companyId.setValidationVisible(true);
        companyId.focus();

        companyNo.setData("maxlengthvalidationhundred,maxlengthvalidationcompanyno,specialchar,specialcharcompanyno");
        companyNo.setValidationVisible(true);

        companyName.setData("maxlengthvalidationhundred,maxlengthvalidationcompanyname,specialchar,specialcharcompanyname");
        companyName.setValidationVisible(true);

        identifier.setData("maxlengthvalidationhundred,maxlengthvalidationcompanyidentifier,specialchar,specialcharcompanyidentifier");
        identifier.setValidationVisible(true);

        companyType.addItem(ddlbDefaultValue);
        companyType.setNullSelectionAllowed(true);
        companyType.setNullSelectionItemId(ddlbDefaultValue);

        companyCategory.addItem(ddlbDefaultValue);
        companyCategory.setNullSelectionAllowed(true);
        companyCategory.setNullSelectionItemId(ddlbDefaultValue);

        tradeClassDto.setNullSelectionAllowed(BooleanConstant.getTrueFlag());
        tradeClassDto.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        tradeClassDto.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        tradeClassDto.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());

        identifierType.addItem(ddlbDefaultValue);
        identifierType.setNullSelectionAllowed(true);
        identifierType.setNullSelectionItemId(ddlbDefaultValue);
        promoteTpToChDtoTableLayout.addComponent(companySearchResultsTable);
        HorizontalLayout hLayout;
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
                        ComboBox tradeClass = new ComboBox();
                        commonUtil.loadComboBox(tradeClass, UiUtils.COMPANY_TRADE_CLASS, true);
                        return tradeClass;
                    } catch (Exception ex) {
                        LoggerFactory.getLogger(CompanySearch.class.getName()).error("", ex);
                    }
                }
                if ("companyType".equals(propertyId)) {
                    try {
                        ComboBox companyTypeComboBox = new ComboBox();
                        commonUtil.loadComboBox(companyTypeComboBox, UiUtils.COMPANY_TYPE, true);
                        return companyTypeComboBox;
                    } catch (Exception ex) {
                        LoggerFactory.getLogger(CompanySearch.class.getName()).error("", ex);
                    }
                }
                if ("companyCategory".equals(propertyId)) {
                    try {
                        ComboBox companyCategoryComboBox = new ComboBox();
                        commonUtil.loadComboBox(companyCategoryComboBox, UiUtils.COMPANY_CATEGORY, true);
                        return companyCategoryComboBox;
                    } catch (Exception ex) {
                        LoggerFactory.getLogger(CompanySearch.class.getName()).error("", ex);
                    }
                }
                if ("state".equals(propertyId)) {
                    try {
                        ComboBox stateComboBox = new ComboBox();
                        commonUtil.loadComboBox(stateComboBox, UiUtils.STATE, true);
                        return stateComboBox;
                    } catch (Exception ex) {
                        LoggerFactory.getLogger(CompanySearch.class.getName()).error("", ex);
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
        });
        
    }

    /**
     * Configure Company Search Results Table
     *
     */
    public void configureCompanySearchResultsTable() {
        companySearchResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        companySearchResultsTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        companySearchResultsTable.setHeight(NumericConstants.FOUR_HUNDRED, Unit.PIXELS);
        companySearchResultsTable.setPageLength(NumericConstants.FIVE);
        compLogic.setContainerDataSource(resultsContainer);

        companySearchResultsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.getItemId() != null) {
                    PromoteTpToChDto tpDTO = (PromoteTpToChDto) event.getItemId();
                    session.setCompanyNo(tpDTO.getCompanyNo());
                    session.setCompanyName(tpDTO.getCompanyName());
                    session.setCompanyType(tpDTO.getCompanyType());
                    session.setCompanyCategory(tpDTO.getCompanyCategory());
                    session.setTradeClass(tpDTO.getTradeClass());
                    session.setCompanyMasterSid(tpDTO.getCompanySystemId());
                    List<String> companyMasterSids = new ArrayList<>();
                    companyMasterSids.add(session.getCompanyMasterSid());
                    session.setCompanyMasterSids(companyMasterSids);
                    session.setSearchSessionId(searchSessionId);
                    LOGGER.debug("createSearchSessionId before callCompanyInsert: {} " , searchSessionId);

                }
            }
        });
        companySearchResultsTable.setVisibleColumns(Constants.getInstance().promoteTpResultsColumns);
        companySearchResultsTable.setColumnHeaders(Constants.getInstance().promoteTpResultsHeaders);
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
         dataSelectionBinder.setItemDataSource(new BeanItem<>(new PromoteTpToChDto()));
              
    }

    /**
     * Transfer Button Logic
     *
     * @param event
     * @throws Exception
     */
    @UiHandler("transferBtn")
    public void transferBtnLogic(Button.ClickEvent event) {
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
            @Override
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    try {
                        companySearchReset();
                    } catch (Exception ex) {
                        LOGGER.error("",ex);
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
        companySearchResultsTable.setVisibleColumns(Constants.getInstance().promoteTpResultsColumns);
        companySearchResultsTable.setColumnHeaders(Constants.getInstance().promoteTpResultsHeaders);

        if (resultsLazyContainer != null) {
            resultsLazyContainer.removeAllItems();
        }
        companySearchResultsTable.removeAllItems();
        if ((StringUtils.isBlank(companyName.getValue()) || Constants.NULL.equals(companyName.getValue()))
                && (StringUtils.isBlank(companyId.getValue()) || Constants.NULL.equals(companyId.getValue()))
                && (StringUtils.isBlank(companyNo.getValue()) || Constants.NULL.equals(companyNo.getValue()))
                && (StringUtils.isBlank(String.valueOf(companyType.getValue())) || Constants.NULL.equals(companyType.getValue()))
                && (StringUtils.isBlank(String.valueOf(companyCategory.getValue())) || Constants.NULL.equals(companyCategory.getValue()))
                && (StringUtils.isBlank(String.valueOf(tradeClassDto.getValue())) || Constants.NULL.equals(tradeClassDto.getValue()))
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
                 LOGGER.error("",commit);
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
                promoteTpToChDto.setTradeClass(String.valueOf(tradeClassDto.getValue()));
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
             LOGGER.error("",ex);
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
            @Override
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    try {
                        searchResultsReset();
                    } catch (Exception ex) {
                        LOGGER.error("",ex);
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
        logic.lazyLoadDdlb(companyType, "getCompanyTypeCountForPromoteTpToCh", "getCompanyTypeForPromoteTpToCh");
    }

    /**
     * Loading Trade Class
     *
     */
    private void loadTradeClass() {
        logic.lazyLoadDdlb(tradeClassDto, "getTradeClassCountForPromoteTpToCh", "getTradeClassForPromoteTpToCh");
    }

    /**
     * Loading Company Identifier Type
     *
     */
    private void loadCompanyQualifier() {
        logic.lazyLoadDdlb(identifierType, "getCompanyQualifierCountForPromoteTpToCh", "getCompanyQualifierForPromoteTpToCh");
    }

    /**
     * Loading Company Category
     *
     */
    private void loadCompanyCategory() {
        logic.lazyLoadDdlb(companyCategory, "getCompanyCategoryCountForPromoteTpToCh", "getCompanyCategoryForPromoteTpToCh");
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
             LOGGER.error("",e);
        }
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
    public final ErrorfulFieldGroup getBinder() {
        final PromoteTpToChDto bean = new PromoteTpToChDto();
        final ErrorfulFieldGroup dataSelectionBinderErrorfulFieldGroup = new ErrorfulFieldGroup(new BeanItem<>(bean));
        dataSelectionBinderErrorfulFieldGroup.setBuffered(true);
        dataSelectionBinderErrorfulFieldGroup.bindMemberFields(this);
        dataSelectionBinderErrorfulFieldGroup.setErrorDisplay(errorMsg);
        return dataSelectionBinderErrorfulFieldGroup;
    }
    
    public void createWorkSheet(String moduleName, ExtPagedTable resultTable,int count) throws   NoSuchMethodException, IllegalAccessException,  InvocationTargetException {
        ExcelExportforBB.createWorkSheet(resultTable.getColumnHeaders(), count, this, UI.getCurrent(), moduleName.replace(' ', '_').toUpperCase(Locale.ENGLISH));

    }
    
    
      public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) {
        try {
            if (end != 0) {
              CompanySearchLogic companySearchLogic = new CompanySearchLogic();
              final List<PromoteTpToChDto> searchList = companySearchLogic.searchCompaniesLazy(promoteTpToChDto, start, end,null, searchSessionId);
              ExcelExportforBB.createFileContent(companySearchResultsTable.getVisibleColumns(), searchList, printWriter);
                    }
        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }

    public ErrorfulFieldGroup getPromoteTpToChDtoBinder() {
            return promoteTpToChDtoBinder;
    }

    public void setPromoteTpToChDtoBinder(ErrorfulFieldGroup promoteTpToChDtoBinder) {
            this.promoteTpToChDtoBinder = promoteTpToChDtoBinder;
    }
    
}
