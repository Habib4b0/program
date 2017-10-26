/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.ui.form;

import com.stpl.app.contract.abstractsearch.util.ConstantUtil;
import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.contractheader.logic.ContractHeaderLogic;
import com.stpl.app.contract.contractheader.util.CommonUtils;
import com.stpl.app.contract.dashboard.dto.IfpAvailableTableGenerator;
import com.stpl.app.contract.dashboard.dto.IfpFilterGenerator;
import com.stpl.app.contract.dashboard.dto.TempPricingDTO;
import com.stpl.app.contract.dashboard.logic.ItemDetailsTableLogic;
import com.stpl.app.contract.dashboard.logic.ItemTableLogic;
import com.stpl.app.contract.dashboard.ui.lazyload.LazyLoadCriteria;
import com.stpl.app.contract.dashboard.ui.lookup.ParentIFPIdLookup;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.dashboard.util.ExcelExportUtil;
import com.stpl.app.contract.global.dto.IFPItemsTableGenerator;
import com.stpl.app.contract.global.dto.IfpSearchDTO;
import com.stpl.app.contract.global.dto.VwContractPriceInfoDTO;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.CHFunctionNameUtils;
import com.stpl.app.contract.util.CommonUIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.app.contract.util.ResponsiveUtils;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.ifs.util.UISecurityUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
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
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author karthikraja.k
 */
public class Items extends CustomComponent {

    private static final Logger LOGGER = Logger.getLogger(Items.class);
    /**
     * CSS layout for fields
     */
    @UiField("cssLayout")
    CssLayout cssLayout;
    /**
     * ItemFamilyplanId Label
     */
    @UiField("itemFamilyplanIdLB")
    private Label itemFamilyplanIdLB;
    @UiField("itemFamilyplanId")
    private TextField itemFamilyplanId;
    @UiField("itemFamilyplanNoLB")
    private Label itemFamilyplanNoLB;
    @UiField("itemFamilyplanNo")
    private TextField itemFamilyplanNo;
    @UiField("itemFamilyplanNameLB")
    private Label itemFamilyplanNameLB;
    @UiField("itemFamilyplanName")
    private TextField itemFamilyplanName;
    @UiField("itemFamilyplanStatusLB")
    private Label itemFamilyplanStatusLB;
    @UiField("itemFamilyplanStatus")
    private ComboBox itemFamilyplanStatus;
    @UiField("itemFamilyplanStartDateLB")
    private Label itemFamilyplanStartDateLB;
    @UiField("itemFamilyplanStartDate")
    private PopupDateField startDate;
    @UiField("itemFamilyplanEndDateLB")
    private Label itemFamilyplanEndDateLB;
    @UiField("ifpEndDate")
    private PopupDateField endDate;
    @UiField("itemFamilyplanDesignationLB")
    private Label itemFamilyplanDesignationLB;
    @UiField("ifpDesignation")
    private ComboBox itemFamilyplanDesignation;
    @UiField("parentItemFamilyplanIdLB")
    private Label parentItemFamilyplanIdLB;
    @UiField("parentIfpId")
    private CustomTextField parentItemFamilyplanId;
    @UiField("parentItemFamilyplanNameLB")
    private Label parentItemFamilyplanNameLB;
    @UiField("parentIfpName")
    private CustomTextField parentItemFamilyplanName;
    @UiField("itemFamilyplanTypeLB")
    private Label itemFamilyplanTypeLB;
    @UiField("ifpType")
    private ComboBox itemFamilyplanType;
    @UiField("createdByLB")
    private Label createdByLB;
    @UiField("createdBy")
    private TextField createdBy;
    @UiField("createdDateLB")
    private Label createdDateLB;
    @UiField("createdDate")
    private PopupDateField createdDate;
    @UiField("itemFamilyplanCategoryLB")
    private Label itemFamilyplanCategoryLB;
    @UiField("ifpCategory")
    private ComboBox itemFamilyplanCategory;
    @UiField("modifiedByLB")
    private Label modifiedByLB;
    @UiField("modifiedBy")
    private TextField modifiedBy;
    @UiField("modifiedDateLB")
    private Label modifiedDateLB;
    @UiField("level")
    OptionGroup level;
    @UiField("view")
    OptionGroup view;
    @UiField("tableLayout")
    VerticalLayout tableLayout;
    @UiField("infoPanel")
    Panel infoPanel;
    @UiField("tablePanel")
    Panel tablePanel;
    @UiField("itemsLayout")
    VerticalLayout itemsLayout;
    @UiField("viewLB")
    Label viewLB;
    @UiField("resultsTableLayout")
    private VerticalLayout resultsTableLayout;
    @UiField("massCheck")
    private OptionGroup massCheck;
    @UiField("massField")
    private ComboBox massField;
    @UiField("massDate")
    private PopupDateField massDate;
    @UiField("massValue")
    private ComboBox massValue;
    @UiField("btnPopulate")
    private Button btnPopulate;
    @UiField("btnAllPopulate")
    private Button btnAllPopulate;
    @UiField("massPanel")
    private Panel massPanel;
    @UiField("remove")
    Button remove;
    /**
     * Record checkbox
     */
    @UiField("record")
    private OptionGroup record;
    ItemTableLogic tableLogic = new ItemTableLogic();
    ExtPagedTable table = new ExtPagedTable(tableLogic);
    ItemDetailsTableLogic itemDetailsTableLogic = new ItemDetailsTableLogic();
    ExtPagedTable itemDetailsTable = new ExtPagedTable(itemDetailsTableLogic);
    private CommonUtil commonUtil = CommonUtil.getInstance();
    BeanItemContainer<IfpSearchDTO> searchResultBeans = new BeanItemContainer<>(IfpSearchDTO.class);
    BeanItemContainer<VwContractPriceInfoDTO> itemDetailsBeans = new BeanItemContainer<>(VwContractPriceInfoDTO.class);
    @UiField("modifiedDate")
    private PopupDateField modifiedDate;
    LazyLoadCriteria lazyLoadCriteria = new LazyLoadCriteria();
    List ifpItemList = new ArrayList<>();
    private BeanItemContainer<TempPricingDTO> saveContainer = new BeanItemContainer<TempPricingDTO>(TempPricingDTO.class);
    SessionDTO sessionDTO;
    boolean isEditable;
    Map<Integer, String> priceType = new HashMap<>();
    private Map<String, List> tempDate;
    public CustomFieldGroup binder;
    private ParentIFPIdLookup lookUp = null;
    Date[] dates = new Date[NumericConstants.TWO];
    Object[] fieldFactoryDates = new Object[NumericConstants.TWO];
    
    IfpLogic logic;
      /**
     * The cpntract header logic.
     */
    private final ContractHeaderLogic contractLogic = new ContractHeaderLogic();
       
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    
    boolean valueChange = false;
    
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
    
    List<Integer> pageLength = new ArrayList<Integer>();
    
    @UiField("excelBtn")
    private Button excelExport;
    
    /**
     * Dummy lazy bean container to clear the Table
     */
    private final BeanItemContainer<TempPricingDTO> tableContainer = new BeanItemContainer<TempPricingDTO>(TempPricingDTO.class);

    public Items(final BeanItemContainer<TempPricingDTO> saveContainer,
            final BeanItemContainer<VwContractPriceInfoDTO> itemDetailsResultsBean,final boolean isEditable, final SessionDTO sessionDTO) {
        this.setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-dashboard/ifpitems.xml"), this));
        this.saveContainer = saveContainer;
        this.itemDetailsBeans = itemDetailsResultsBean;
        this.isEditable = isEditable;
        this.sessionDTO = sessionDTO;
        logic = new IfpLogic(sessionDTO);
        init();
    }

        private void init() {
        try {
            configureFields();
            configureFieldsForInfoTab();
            configureBinder();
            setDefaultFocus(isEditable);
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
            final Map<String, AppPermission> contractDashboard = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + Constants.COMMA + Constants.ITEMS, false);
            final Map<String, AppPermission> funContractDashboard = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + Constants.COMMA + "Items Detail");
            if ((funContractDashboard.get(CHFunctionNameUtils.Remove) == null) || !((AppPermission) funContractDashboard.get(CHFunctionNameUtils.Remove)).isFunctionFlag()) {
                remove.setVisible(false);
            }
            securityLogic(contractDashboard);
            valueChange = Boolean.TRUE;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void configureFields() {
        try {
            level.addItem(Constants.HEADER);
            level.addItem(Constants.DETAILS);
            level.select(Constants.HEADER);
            view.addItem(Constants.HISTORY);
            view.addItem(Constants.CURRENT);
            view.addItem(Constants.PENDING);
            view.select(Constants.CURRENT);
            massCheck.addItem(Constants.ENABLE);
            massCheck.addItem(Constants.DISABLE);
            massCheck.setValue(Constants.DISABLE);
            massCheck.setImmediate(true);
            massCheck.setMultiSelect(false);
            remove.setVisible(isEditable);
            massCheck.setImmediate(true);
            massCheck.focus();
            massField.setNullSelectionAllowed(true);
            massField.addItem(Constants.SELECT_ONE);
            massField.setNullSelectionItemId(Constants.SELECT_ONE);
            massField.addItem(Constants.IFP_START_DATE_SP);
            massField.addItem(Constants.IFP_END_DATE_CAPS);
            massField.addItem(Constants.IFP_STATUS);
            massField.setImmediate(true);
            massField.select(Constants.SELECT_ONE);
            massField.setEnabled(false);


            excelExport.setIcon(new ThemeResource(ExcelExportUtil.EXCEL_EXPORT_IMAGE));
            excelExport.setStyleName("link");
            excelExport.setDescription(Constants.EXCEL_EXPORT);
            excelExport.setIconAlternateText(Constants.EXCEL_EXPORT);
            btnExportLogic();
            excelExport.setVisible(false);
            massValue.setImmediate(true);
            massValue.setVisible(false);
            massValue.select(Constants.SELECT_ONE);

            massDate.setImmediate(true);
            massDate.setVisible(false);
            massDate.setDateFormat(Constants.MM_DD_YYYY);
            massDate.setDescription(Constants.DATE);
            massDate.setId("ItemMassDate");
         
            massDate.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used to mass date logic and its listener.
                 *
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final Property.ValueChangeEvent event) {
                    massDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(massDate.getValue()));
                }
            });
            massCheck.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                /**
                 * Method used to mass check logic and its listener.
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    if (massCheck.getValue().equals(Constants.DISABLE)) {
                        massField.setEnabled(false);
                        massValue.setValue(StringUtils.EMPTY);
                        massValue.setVisible(false);
                        massDate.setVisible(false);
                        btnPopulate.setEnabled(false);
                        btnPopulate.setReadOnly(true);
                        btnAllPopulate.setEnabled(false);
                        btnAllPopulate.setReadOnly(true);
                        if (itemDetailsBeans != null) {
                            final List<VwContractPriceInfoDTO> contractPriceDtoList = itemDetailsBeans.getItemIds();

                            for (int i = 0; i < contractPriceDtoList.size(); i++) {
                                final VwContractPriceInfoDTO dto = contractPriceDtoList.get(i);
                                if (dto.getCheckbox()) {
                                    dto.setCheckbox(false);
                                }
                                ifpItemList.add(dto);
                            }
                            if (!ifpItemList.isEmpty()) {
                                itemDetailsBeans.removeAllItems();
                                itemDetailsBeans.addAll(ifpItemList);
                            }
                        }
                        markAsDirty();

                    } else if (massCheck.getValue().equals(Constants.ENABLE)) {
                        massField.setEnabled(true);
                        btnPopulate.setEnabled(true);
                        btnAllPopulate.setEnabled(true);
                        markAsDirty();
                    }
                    massCheck.focus();
                }
            });
            record.addItems(Constants.HISTORY, Constants.CURRENT, Constants.FUTURE, Constants.PENDING);
            record.setMultiSelect(true);
            record.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    String value = String.valueOf(record.getValue());
                if (!isEditable) {
                    
                } else {
                    itemDetailsTableLogic.setRecord(value);
                    itemDetailsTableLogic.configureSearchData(itemDetailsTable, saveContainer, sessionDTO, priceType);
                }
                }
            });
            itemsLayout.setVisible(false);
            view.setVisible(true);
            viewLB.setVisible(true);
            tablePanel.setVisible(false);
            infoPanel.setVisible(true);
            tableLayout.addComponent(table);
            tableLogic.setContainerDataSource(searchResultBeans);
            configureTable();
            addDetailsTable();
            commonUtil.loadComboBox(itemFamilyplanStatus, Constants.DB_STATUS, false);
            commonUtil.loadComboBox(itemFamilyplanType, "IFP_TYPE", false);
            commonUtil.loadComboBox(itemFamilyplanCategory, "IFP_CATEGORY", false);
            commonUtil.loadComboBox(itemFamilyplanDesignation, "IFP_DESIGNATION", false);
            HorizontalLayout layout = ResponsiveUtils.getResponsiveControls(tableLogic.createControls(), true);
            tableLayout.addComponent(layout);
            view.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    excelExport.setVisible(true);
                    if (String.valueOf(level.getValue()).equals(Constants.HEADER)) {
                        if (String.valueOf(view.getValue()).equals(Constants.HISTORY)) {
                            tablePanel.setVisible(true);
                            infoPanel.setVisible(false);
                        } else {
                            tablePanel.setVisible(false);
                            infoPanel.setVisible(true);
                        }
                         if (String.valueOf(level.getValue()).equals("Header") && !String.valueOf(view.getValue()).equals("History")) {
                            excelExport.setVisible(false);
                        }
                    }
                }
            });
            level.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    excelExport.setVisible(true);
                    if (String.valueOf(level.getValue()).equals(Constants.HEADER)) {
                        view.select(Constants.CURRENT);
                        itemsLayout.setVisible(false);
                        view.setVisible(true);
                        viewLB.setVisible(true);
                        tablePanel.setVisible(false);
                        infoPanel.setVisible(true);
                    } else {
                        tablePanel.setVisible(false);
                        infoPanel.setVisible(false);
                        view.setVisible(false);
                        viewLB.setVisible(false);
                        itemsLayout.setVisible(true);
                        fieldFactoryDates[0] = startDate.getValue();
                        fieldFactoryDates[1] = endDate.getValue();
                    }
                    if (String.valueOf(level.getValue()).equals("Header") && !String.valueOf(view.getValue()).equals("History")) {
                        excelExport.setVisible(false);
                    }
                }
            });
            itemFamilyplanDesignation.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * This method listens to data source value changes and passes
                 * the changes forwards.
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    if (event.getProperty() != null && event.getProperty().getValue() != null && StringUtils.EMPTY.equals(event.getProperty().getValue().toString())) {
                        itemFamilyplanDesignation.setValue(ConstantsUtils.SELECT_ONE);
                    }
                    String caption = itemFamilyplanDesignation.getItemCaption(itemFamilyplanDesignation.getValue());
                    itemFamilyplanDesignation.setDescription(caption);
                    if (Constants.CHILD.equalsIgnoreCase(caption)) {
                        parentItemFamilyplanId.setEnabled(true);
                        parentItemFamilyplanName.setEnabled(true);
                        parentItemFamilyplanId.setReadOnly(true);
                        parentItemFamilyplanName.setReadOnly(true);
                    } else {
                        parentItemFamilyplanId.setReadOnly(false);
                        parentItemFamilyplanId.setEnabled(true);
                        parentItemFamilyplanId.setValue(StringUtils.EMPTY);
                        parentItemFamilyplanId.setReadOnly(true);
                        parentItemFamilyplanId.setEnabled(false);
                        parentItemFamilyplanName.setReadOnly(false);
                        parentItemFamilyplanName.setEnabled(true);
                        parentItemFamilyplanName.setValue(StringUtils.EMPTY);
                        parentItemFamilyplanName.setReadOnly(true);
                        parentItemFamilyplanName.setEnabled(false);

                    }
                }
            });
          massField.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used for mass field logic and its listener
                 */
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    try {

                        if (massField.getValue() != null) {
                            final String value = String.valueOf(massField.getValue());
                            if (Constants.IFP_START_DATE_SP.equalsIgnoreCase(value) || Constants.IFP_END_DATE_CAPS.equals(value)) {
                                massValue.setVisible(false);
                                massDate.setVisible(true);
                                massDate.setValue(null);
                                btnPopulate.setReadOnly(false);
                                btnAllPopulate.setReadOnly(false);
                            } else if (Constants.IFP_STATUS.equals(value)) {
                                massValue.setVisible(true);
                                massDate.setVisible(false);
                                btnPopulate.setReadOnly(false);
                                btnAllPopulate.setReadOnly(false);
                                massValue.addItem(0);
                                massValue.setItemCaption(0, Constants.SELECT_ONE);
                                CommonUtils.getSelectNull(massValue);
                                commonUtil.loadComboBox(massValue, Constants.DB_STATUS, false);
                                massValue.select(0);
                            }
                        } else {
                            massValue.setVisible(false);
                            massDate.setVisible(false);
                        }
                    } catch (Exception ex) {
                      LOGGER.error(ex);  
                    }
                }
            });

   
            addAllBtnPopulate();
            addBtnPopulate();
            removeBtnLogic();
        } catch (Exception ex) {

            LOGGER.error("Error in Configure Fields" + ex);
        }

    }

    private void configureTable() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
            final Map<String, AppPermission> contractDashboard = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + Constants.COMMA + "Items History", false);
            List<Object> resultList = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, "Items History");
            Object[] obj = ContractUtils.IFP_ITEMS_COL;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, contractDashboard,Constants.EDIT);
            if (tableResultCustom.getObjResult().length > 0) {
                table.setVisibleColumns(tableResultCustom.getObjResult());
                table.setColumnHeaders(tableResultCustom.getObjResultHeader());
            } else {
                table.setVisible(false);
            }
          table.setFilterBarVisible(true);
          table.setFilterDecorator(new ExtDemoFilterDecorator());
          table.setFilterGenerator(new IfpAvailableTableGenerator());
          table.setValidationVisible(false);
          table.addStyleName(Constants.FILTER_BAR);
          table.setWidth("100%");
        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void addDetailsTable() {
        try {
           
            final StplSecurity stplSecurity = new StplSecurity();
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
            final Map<String, AppPermission> contractDashboard = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + Constants.COMMA + "Items Detail", false);
            List<Object> resultList = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, "Items Detail");
            Object[] obj = ContractUtils.IFP_ITEM_DETAILS_COL;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, contractDashboard, Constants.EDIT);
            tempDate = new HashMap<>();
            resultsTableLayout.addComponent(itemDetailsTable);
            HorizontalLayout tempLayout = ResponsiveUtils.getResponsiveControls(itemDetailsTableLogic.createControls());
            ContractUtils.getCustomizedComboBox(
                    (ComboBox) ((CssLayout) tempLayout.getComponent(0)).getComponent(1));
            resultsTableLayout.addComponent(tempLayout);
            itemDetailsTableLogic.setContainerDataSource(tableContainer);
            itemDetailsTable.setSizeFull();
            itemDetailsTableLogic.isIfpItemsTab = true;
            itemDetailsTableLogic.configureSearchData(itemDetailsTable, saveContainer, sessionDTO, priceType);
            if (tableResultCustom.getObjResult().length > 0) {
                itemDetailsTable.setVisibleColumns(tableResultCustom.getObjResult());
                itemDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            }else{
                itemDetailsTable.setVisible(false);
            }
            if(!isEditable){
                itemDetailsTable.setColumnCollapsingAllowed(true);
                itemDetailsTable.setColumnCollapsed(Constants.CHECK_BOX, true);
            }
            else{
            itemDetailsTable.addStyleName(Constants.FILTER_BAR);
            itemDetailsTable.setFilterGenerator(new IfpFilterGenerator());
            itemDetailsTable.setFilterBarVisible(true);
            itemDetailsTable.setFilterDecorator(new ExtDemoFilterDecorator());
             tempDate = new HashMap<>();
            itemDetailsTable.setTableFieldFactory(new IFPItemsTableGenerator(saveContainer,fieldFactoryDates,tempDate, isEditable));
            itemDetailsTable.setValidationVisible(false);
            itemDetailsTable.addStyleName(Constants.FILTER_BAR);
            itemDetailsTable.setColumnCheckBox(Constants.CHECK_BOX, true, false);
            itemDetailsTable.setEditable(true);
            itemDetailsTableLogic.setPageLength(NumericConstants.SIX);
            itemDetailsTableLogic.sinkItemPerPageWithPageLength(false);
            itemDetailsTable.setFilterFieldVisible(Constants.CHECK_BOX, false);
            itemDetailsTable.addStyleName(Constants.TABLE_CHECK_BOX);
             itemDetailsTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {

                    @Override
                    public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                        if (Constants.CHECK_BOX.equals(event.getPropertyId().toString())) {
                            if (event.isChecked()) {
                                try {
                                    IfpLogic.saveToTempIFP(saveContainer.getItemIds(), isEditable);
                                    saveContainer.removeAllItems();
                                    logic.populateToTempIFP(Constants.CHECK_BOX1, 1, Boolean.TRUE);
                                    itemDetailsTableLogic.configureSearchData(itemDetailsTable, saveContainer, sessionDTO, priceType);
                                    itemDetailsTable.setColumnCheckBox(Constants.CHECK_BOX, true, true);
                                } catch (PortalException ex) {
                                    LOGGER.error(ex);
                                } catch (SystemException ex) {
                                    LOGGER.error(ex);
                                }

                            } else {
                                try {
                                    IfpLogic.saveToTempIFP(saveContainer.getItemIds(), isEditable);
                                    saveContainer.removeAllItems();
                                    logic.populateToTempIFP(Constants.CHECK_BOX1, 0, Boolean.TRUE);
                                    itemDetailsTableLogic.configureSearchData(itemDetailsTable, saveContainer, sessionDTO, priceType);
                                    itemDetailsTable.setColumnCheckBox(Constants.CHECK_BOX, true, true);
                                } catch (PortalException ex) {
                                    LOGGER.error(ex);
                                } catch (SystemException ex) {
                                    LOGGER.error(ex);
                                }

                            }
                        }
                    }
                });
            }
        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void configureBinder() {
        try {
            LOGGER.debug("Entering Configure Binder ");
            final int ifpSystemId = (Integer) (sessionDTO.getIfpSystemId());
            logic = new IfpLogic(sessionDTO);
            TempPricingDTO dto = logic.getIFPById(ifpSystemId);
            binder = new CustomFieldGroup(new BeanItem<>(dto));
            binder.bindMemberFields(this);
            binder.setItemDataSource(new BeanItem<>(dto));
        } catch (Exception ex) {
            LOGGER.error(ex);  
        }
    }

    private void configureFieldsForInfoTab() throws SystemException {
        endDate.setDescription(ConstantsUtils.DATE_DES);
        startDate.setDescription(ConstantsUtils.DATE_DES);
        itemFamilyplanId.setImmediate(true);
        itemFamilyplanId.focus();
        itemFamilyplanId.setData("maxlengthvalidationfifty,maxlengthvalidationitemfamilyplanid,alphaNumericCharsWithoutStar,alphaNumericCharsMessage");
        itemFamilyplanId.setValidationVisible(true);
        itemFamilyplanId.setDescription(itemFamilyplanId.getValue());
        itemFamilyplanId.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                itemFamilyplanId.setDescription(itemFamilyplanId.getValue());
                if (itemFamilyplanStatus.getValue() != null && (StringUtils.EMPTY).equals(itemFamilyplanStatus.getValue())) {
                    itemFamilyplanStatus.select(null);
                }
            }
        });

        itemFamilyplanNo.setData("maxlengthvalidationfifty,maxlengthvalidationitemfamilyplanno,alphaNumericCharsWithoutStar,alphaNumericCharsMessage");
        itemFamilyplanNo.setImmediate(true);
        itemFamilyplanNo.setValidationVisible(true);
        itemFamilyplanNo.setDescription(itemFamilyplanNo.getValue());
        itemFamilyplanNo.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                itemFamilyplanNo.setDescription(itemFamilyplanNo.getValue());
            }
        });
        itemFamilyplanName.setData("maxlengthvalidationhundred,maxlengthvalidationitemfamilyplanname,alphaNumericCharsWithoutStar,alphaNumericCharsMessage");
        itemFamilyplanName.setImmediate(true);
        itemFamilyplanName.setValidationVisible(true);
        itemFamilyplanName.setDescription(itemFamilyplanName.getValue());
        itemFamilyplanName.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                itemFamilyplanName.setDescription(itemFamilyplanName.getValue());
            }
        });
        commonUtil.loadComboBox(itemFamilyplanStatus, Constants.DB_STATUS, false);

        startDate.setImmediate(true);
        startDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        startDate.setId("IFPStartDate");
        attachListeners(startDate,Constants.START_DATE_CAPS);

        endDate.setImmediate(true);
        endDate.setValidationVisible(true);
        endDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        endDate.setId("IFPEndDate");
        attachListeners(endDate,Constants.END_DATE_CAPS);

        if (Constants.CHILD.equalsIgnoreCase(itemFamilyplanDesignation.getItemCaption(itemFamilyplanDesignation.getValue()))) {
            parentItemFamilyplanId.setEnabled(true);
            parentItemFamilyplanName.setEnabled(true);
            parentItemFamilyplanId.setReadOnly(true);
            parentItemFamilyplanName.setReadOnly(true);
        } else {
            parentItemFamilyplanId.setReadOnly(true);
            parentItemFamilyplanId.setValue(StringUtils.EMPTY);
            parentItemFamilyplanId.setEnabled(false);

            parentItemFamilyplanName.setReadOnly(true);
            parentItemFamilyplanName.setValue(StringUtils.EMPTY);
            parentItemFamilyplanName.setEnabled(false);

        }


        itemFamilyplanDesignation.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && StringUtils.EMPTY.equals(event.getProperty().getValue().toString())) {
                    itemFamilyplanDesignation.setValue(ConstantsUtils.SELECT_ONE);
                }
                String caption = itemFamilyplanDesignation.getItemCaption(itemFamilyplanDesignation.getValue());
                itemFamilyplanDesignation.setDescription(caption);
                if (Constants.CHILD.equalsIgnoreCase(caption)) {
                    parentItemFamilyplanId.setEnabled(true);
                    parentItemFamilyplanName.setEnabled(true);
                    parentItemFamilyplanId.setReadOnly(true);
                    parentItemFamilyplanName.setReadOnly(true);
                } else {
                    parentItemFamilyplanId.setReadOnly(false);
                    parentItemFamilyplanId.setEnabled(true);
                    parentItemFamilyplanId.setValue(StringUtils.EMPTY);
                    parentItemFamilyplanId.setReadOnly(true);
                    parentItemFamilyplanId.setEnabled(false);
                    parentItemFamilyplanName.setReadOnly(false);
                    parentItemFamilyplanName.setEnabled(true);
                    parentItemFamilyplanName.setValue(StringUtils.EMPTY);
                    parentItemFamilyplanName.setReadOnly(true);
                    parentItemFamilyplanName.setEnabled(false);

                }
            }
        });

        parentItemFamilyplanName.setStyleName("searchicon");
        parentItemFamilyplanName.setImmediate(true);
        parentItemFamilyplanName.setReadOnly(true);
        parentItemFamilyplanId.setEnabled(false);

        parentItemFamilyplanId.setStyleName("searchicon");
        parentItemFamilyplanId.setImmediate(true);
        parentItemFamilyplanId.setReadOnly(true);
        parentItemFamilyplanName.setEnabled(false);
        parentItemFamilyplanId.addClickListener(new CustomTextField.ClickListener() {
            /**
             * Adding click listener to parentItemFamilyplanId text box.
             *
             * @param event
             */
            public void click(final CustomTextField.ClickEvent event) {
                try {
                    if (lookUp == null) {
                        LOGGER.debug("Entering parent Item Family plan Id look up");
                        lookUp = new ParentIFPIdLookup(parentItemFamilyplanId, parentItemFamilyplanName);
                        UI.getCurrent().addWindow(lookUp);
                    }
                    lookUp.addCloseListener(new Window.CloseListener() {
                        /**
                         * Called when the user closes a window.
                         *
                         * @param e Event containing
                         */
                        @SuppressWarnings("PMD")
                        public void windowClose(final Window.CloseEvent e) {
                            parentItemFamilyplanId.setReadOnly(true);
                            parentItemFamilyplanName.setReadOnly(true);
                            lookUp = null;
                            LOGGER.debug("Ending parent Item Family plan Id look up");
                        }
                    });
                } catch (SystemException exception) {
                    LOGGER.error(exception);
                }

            }
        });
        parentItemFamilyplanName.addClickListener(new CustomTextField.ClickListener() {
            /**
             * Adding click listener to parentItemFamilyplanId text box.
             *
             * @param event
             */
            public void click(final CustomTextField.ClickEvent event) {
                try {
                    if (lookUp == null) {
                        LOGGER.debug("Entering parent Item Family plan Id look up");
                        lookUp = new ParentIFPIdLookup(parentItemFamilyplanId, parentItemFamilyplanName);
                        UI.getCurrent().addWindow(lookUp);
                    }
                    lookUp.addCloseListener(new Window.CloseListener() {
                        /**
                         * Called when the user closes a window.
                         *
                         * @param e Event containing
                         */
                        @SuppressWarnings("PMD")
                        public void windowClose(final Window.CloseEvent e) {
                            parentItemFamilyplanId.setReadOnly(true);
                            parentItemFamilyplanName.setReadOnly(true);
                            lookUp = null;
                            LOGGER.debug("Ending parent Item Family plan Id look up");
                        }
                    });

                } catch (Exception exception) {
                    LOGGER.error(exception);
                }

            }
        });


    }

    public void setDefaultFocus(boolean isEditable) {
        itemFamilyplanId.focus();
        String caption = itemFamilyplanDesignation.getItemCaption(itemFamilyplanDesignation.getValue());
        itemFamilyplanDesignation.setDescription(caption);
        parentItemFamilyplanId.setEnabled(false);
        parentItemFamilyplanName.setEnabled(false);

        parentItemFamilyplanId.setReadOnly(true);
        parentItemFamilyplanName.setReadOnly(true);
        itemFamilyplanId.setReadOnly(true);
        itemFamilyplanName.setReadOnly(false);
        itemFamilyplanNo.setReadOnly(false);
        createdBy.setReadOnly(false);
        createdBy.setEnabled(true);
        String userId =  String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        createdBy.setValue(IfpLogic.getUseName(userId));
        createdBy.setImmediate(true);
        createdBy.setReadOnly(true);
        createdBy.setEnabled(false);

        createdDate.setReadOnly(false);
        createdDate.setEnabled(true);
//        createdDate.setValue(new Date());
        createdDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        createdDate.setImmediate(true);
        createdDate.setReadOnly(true);
        createdDate.setEnabled(false);
        
        modifiedBy.setValue(IfpLogic.getUseName(String.valueOf(modifiedBy.getValue())));
        modifiedBy.setImmediate(true);
        modifiedBy.setReadOnly(true);
        modifiedDate.setImmediate(true);
        modifiedDate.setReadOnly(true);
        modifiedDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        parentItemFamilyplanId.setReadOnly(true);
        parentItemFamilyplanName.setReadOnly(true);
        if (Constants.CHILD.equalsIgnoreCase(caption)) {
            parentItemFamilyplanId.setEnabled(true);
            parentItemFamilyplanName.setEnabled(true);
            parentItemFamilyplanId.setReadOnly(true);
            parentItemFamilyplanName.setReadOnly(true);
        } else {
            parentItemFamilyplanId.setReadOnly(true);
            parentItemFamilyplanName.setReadOnly(true);
        }

        createdBy.setEnabled(false);
        createdBy.setReadOnly(true);
        createdDate.setReadOnly(true);
        createdDate.setEnabled(false);
        createdDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        modifiedBy.setEnabled(false);
        modifiedBy.setReadOnly(true);
        modifiedDate.setReadOnly(true);
        modifiedDate.setEnabled(false);
        modifiedDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        if (!isEditable) {
            massPanel.setVisible(false);
            itemFamilyplanStatus.setReadOnly(true);
            itemFamilyplanDesignation.setReadOnly(true);
            itemFamilyplanType.setReadOnly(true);
            itemFamilyplanCategory.setReadOnly(true);
            startDate.setReadOnly(true);
            endDate.setReadOnly(true);
            parentItemFamilyplanId.setEnabled(false);
            parentItemFamilyplanName.setEnabled(false);
            itemFamilyplanName.setReadOnly(true);
            itemFamilyplanNo.setReadOnly(true);
        }
    }

    public CustomFieldGroup getBinder() {
        return binder;
    }

    public Button addAllBtnPopulate() {
        LOGGER.debug("Entering addAllBtnPopulate method");
        btnAllPopulate.setReadOnly(true);
        btnAllPopulate.setErrorHandler(new ErrorHandler() {
            /**
             * Method used to populate all button logic and its listener.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });

        btnAllPopulate.addClickListener(new Button.ClickListener() {
            /**
             * Method used to all button populate logic and its listener.
             */
            @SuppressWarnings("empty-statement")
            public void buttonClick(final Button.ClickEvent event) {
                String value = StringUtils.EMPTY;
                final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                if (massField.getValue() != null) {
                    try {
                        if (Constants.IFP_START_DATE_SP.equals(String.valueOf(massField.getValue()))) {
                            if(startDate.getValue()==null){
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please provide IFP Start date and try again.");
                                return;
                            } else if (massDate.getValue() == null) {
                                 AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please provide date and try again.");
                               return;
                            } else if (massDate.getValue().before(startDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be before " + new SimpleDateFormat("MM/dd/YYYY").format(startDate.getValue()));
                                return;
                            } else if (endDate.getValue() != null && massDate.getValue().after(endDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be after " + new SimpleDateFormat("MM/dd/YYYY").format(endDate.getValue()));
                                return;
                            } else {
                                value = fmt.format(massDate.getValue());
                            }
                        }else if (Constants.IFP_END_DATE_CAPS.equals(String.valueOf(massField.getValue()))){
                            if(startDate.getValue()==null){
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please provide IFP Start date and try again.");
                                return;
                            } else if (massDate.getValue() == null) {
                              AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please provide date and try again.");
                               return;
                            } else if (endDate.getValue() != null && massDate.getValue().after(endDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "End date cannot be after " + new SimpleDateFormat("MM/dd/YYYY").format(endDate.getValue()));
                                return;
                            } else if (massDate.getValue().before(startDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "End date cannot be before " + new SimpleDateFormat("MM/dd/YYYY").format(startDate.getValue()));
                                return;
                            } else {
                                value = fmt.format(massDate.getValue());
                            }
                        }
                        else if (Constants.IFP_STATUS.equals(String.valueOf(massField.getValue()))) {
                           
                            if (massValue.getValue() == null) {
                             AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please select status and try again.");
                               return;
                            } else {
                                value = String.valueOf(((HelperDTO) (massValue.getValue())).getId());
                            }
                        } 
                        IfpLogic.saveToTempIFP(saveContainer.getItemIds(), isEditable);
                        saveContainer.removeAllItems();
                        logic.populateToTempIFP(massField.getValue(), value, Boolean.TRUE);
                        loadTempIfp();
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                } else {
                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, Constants.POPULATE_MSG);
                }
            }
        });
        LOGGER.debug("End of addAllBtnPopulate method");
        return btnAllPopulate;
    }

    public void loadTempIfp() {
        try {
            itemDetailsTableLogic.configureSearchData(itemDetailsTable, saveContainer, sessionDTO, priceType);
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }
     /**
     * Adds and configures the populate button.
     *
     * @return the button
     */
    public Button addBtnPopulate() {
        LOGGER.debug("Entering addBtnPopulate method");

        btnPopulate.setReadOnly(true);
        btnPopulate.setErrorHandler(new ErrorHandler() {
            /**
             * Method used for populate button logic and its listener.
             *
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });

        btnPopulate.addClickListener(new Button.ClickListener() {
            /**
             * Method used to populate button logic and its listener.
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("Entering btnPopulate buttonClick method");
                String value = StringUtils.EMPTY;
                final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                if (massField.getValue() != null) {
                    try {
                      if (Constants.IFP_START_DATE_SP.equals(String.valueOf(massField.getValue()))) {

                            if(startDate.getValue()==null){
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please provide IFP Start date and try again.");
                                return;
                            } else if (massDate.getValue() == null) {
                               AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please provide date and try again.");
                               return;
                            }else if (massDate.getValue().before(startDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be before " + new SimpleDateFormat("MM/dd/YYYY").format(startDate.getValue()));
                                return;
                            } else if (endDate.getValue() != null && massDate.getValue().after(endDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be after " + new SimpleDateFormat("MM/dd/YYYY").format(endDate.getValue()));
                                return;
                            } else {
                                value = fmt.format(massDate.getValue());
                            }

                        }else if (Constants.IFP_END_DATE_CAPS.equals(String.valueOf(massField.getValue()))){
                            if(startDate.getValue()==null){
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please provide IFP Start date and try again.");
                                return;
                            } else if (massDate.getValue() == null) {
                               AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please provide date and try again.");
                               return;
                            } else if (endDate.getValue() != null && massDate.getValue().after(endDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "End date cannot be after " + new SimpleDateFormat("MM/dd/YYYY").format(endDate.getValue()));
                                return;
                            } else if (massDate.getValue().before(startDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "End date cannot be before " + new SimpleDateFormat("MM/dd/YYYY").format(startDate.getValue()));
                                return;
                            } else {
                                value = fmt.format(massDate.getValue());
                            }
                        }
                        else if (Constants.IFP_STATUS.equals(String.valueOf(massField.getValue()))) {
                           
                            if (massValue.getValue() == null) {
                               AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please select status and try again.");
                               return;
                            } else {
                                value = String.valueOf(((HelperDTO) (massValue.getValue())).getId());
                            }
                        } 
                         IfpLogic.saveToTempIFP(saveContainer.getItemIds(), isEditable);
                        if(IfpLogic.isCheckedValidation("IMTD_ITEM_PRICE_REBATE_DETAILS",String.valueOf(sessionDTO.getUiSessionId()))){
                             AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, Constants.POPULATE_MSG );
                             return;
                        }
                       saveContainer.removeAllItems();
                       logic.populateToTempIFP(massField.getValue(), value, Boolean.FALSE);
                       loadTempIfp();
                    } catch (SystemException ex) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsg);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    } catch (PortalException ex) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsg);
                    }
                } else {
                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, Constants.POPULATE_MSG);
                }
                LOGGER.debug("End of btnPopulate buttonClick method");
            }
        });
        LOGGER.debug("End of addBtnPopulate method");
        return btnPopulate;
    }
     public void btnExportLogic() throws SystemException, PortalException {
        LOGGER.debug("Entering btnExportLogic method");
            excelExport.addClickListener(new ClickListener() {
            /**
             * Invoked when a button is clicked
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                try {
                    IfpLogic.saveToTempIFP(saveContainer.getItemIds(), isEditable);
                } catch (PortalException ex) {
                    java.util.logging.Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SystemException ex) {
                    java.util.logging.Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                 if (String.valueOf(level.getValue()).equals(Constants.DETAILS)) {
                     try {
                         List list = logic.getLazyItemPricingDeatils(0, 0, null, true, itemDetailsTableLogic.getRecord(), false, Boolean.FALSE);
                         int recordCount = 0;
                         if (list != null && !list.isEmpty()) {
                             recordCount = Integer.valueOf(String.valueOf(list.get(0)));
                         }
                         if (recordCount > 0) {
                             try {
                                 createWorkSheet("Items_Details", itemDetailsTable, recordCount);
                             } catch (Exception ex) {
                                 java.util.logging.Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         }
                     } catch (PortalException ex) {
                         java.util.logging.Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (SystemException ex) {
                         java.util.logging.Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
                     }

         } else {
              List   list=tableLogic.loadData(0, 0);
             int recordCount = 0;
             if (list != null && !list.isEmpty()) {
                 recordCount = Integer.valueOf(String.valueOf(list.get(0)));
             }
             if (recordCount >0) {
                  try {
                      createWorkSheet("Items_History", table, recordCount);
                  } catch (SystemException ex) {
                      java.util.logging.Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
                  } catch (PortalException ex) {
                      java.util.logging.Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
                  } catch (Exception ex) {
                      java.util.logging.Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
                  }
             }
         }
        LOGGER.debug("End of btnExportLogic method");
            }
        });
        
    }
    
     public void createWorkSheet(String moduleName, ExtCustomTable resultTable,int count) throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
         String[] header=resultTable.getColumnHeaders();
         header = (String[]) ArrayUtils.removeElement(header, StringUtils.EMPTY); 
         header = (String[]) ArrayUtils.removeElement(header, ConstantUtil.BLANK_SPACE); 
         ExcelExportforBB.createWorkSheet(header, count, this, UI.getCurrent(), moduleName.toUpperCase());
    }
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        LOGGER.debug("Entering createWorkSheetContent method");   
        if (String.valueOf(level.getValue()).equals(Constants.DETAILS)) {
            List<Object[]> returnList = logic.getLazyItemPricingDeatils(start, end, null, false, itemDetailsTableLogic.getRecord(), true, Boolean.FALSE);
            List exportCompany = logic.getCustomizedPricingDTO(returnList, true, itemDetailsTableLogic.getRecord());

            Object[] columns = itemDetailsTable.getVisibleColumns();
            columns = ArrayUtils.removeElement(columns, Constants.CHECK_BOX);
            ExcelExportforBB.createFileContent(columns, exportCompany, printWriter);
        } else {
            List exportCompany = new ArrayList();
            Object[] columns = table.getVisibleColumns();
            ExcelExportforBB.createFileContent(columns, exportCompany, printWriter);
        }
     
        LOGGER.debug("End of createWorkSheetContent method");
    }
    public void removeBtnLogic() {
        LOGGER.debug("Entering remove method");

        remove.setWidth("70");
        remove.setErrorHandler(new ErrorHandler() {
            /**
             * Method used to remove button error handling and its technique
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });
        remove.addClickListener(new ClickListener() {
            /**
             * Method used for remove button logic and its listener.
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug(" buttonClick ,( ClickEvent eventv ) name=" + event.getButton().getCaption());
                 final int count=logic.isEmpty(sessionDTO,Boolean.TRUE);
                if (count>0) {
                    MessageBox.showPlain(Icon.QUESTION, "Remove Confirmation", "Are you sure you want to remove the record from the Contract?", new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         *
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (ButtonId.YES.equals(buttonId)) {
                                try {
                                     List<Object[]> returnList = logic.getLazyItemPricingDeatils(0, count, null, false, itemDetailsTableLogic.getRecord(), true, Boolean.TRUE);
                                     List<TempPricingDTO> list = logic.getCustomizedPricingDTO(returnList, true, itemDetailsTableLogic.getRecord());
                                    for (TempPricingDTO temp : list) {
                                        if (logic.validateCCPActuals(temp.getItemSystemId()) != 0) {
                                            AbstractNotificationUtils.getErrorNotification("Halt", "The selected record " + temp.getItemName() + " cannot be removed as there are Actuals associated to it.");
                                            return;
                                        }
                                    }
                                    for (TempPricingDTO temp : list) {
                                        try {
                                            if (saveContainer.size() > 0) {
                                                IfpLogic.saveToTempIFP(saveContainer.getItemIds(), isEditable);
                                                saveContainer.removeAllItems();
                                            }
                                            logic.removeItem(Integer.parseInt(temp.getTempItemPriceRebateSystemId()));
                                        } catch (SystemException ex) {
                                            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                                            LOGGER.error(errorMsg);
                                            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                                        } catch (PortalException ex) {
                                            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                                            LOGGER.error(errorMsg);
                                        }
                                    }
                                    itemDetailsTableLogic.configureSearchData(itemDetailsTable, saveContainer, sessionDTO, priceType);
                                } catch (Exception e) {
                                    LOGGER.error(e);
                                }
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);

                } else {
                    AbstractNotificationUtils.getErrorNotification("Halt", "Please check mark a row for removal");
                }
            }
        });
        LOGGER.debug("End of remove method");

    }

    private void securityLogic(final Map<String, AppPermission> contractDashboard) {
        try {
            List<Object> resultList = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD,Constants.ITEMS);
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, contractDashboard,Constants.EDIT);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadDates(final Date startDate, final Date endDate) {
        dates[0] = startDate;
        dates[1] = endDate;
        this.startDate.validate();
        this.endDate.validate();
    }
    
    public void attachListeners(final AbstractField field, final String component) {
        field.setImmediate(true);
        field.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                try {
                    if (valueChange) {
                        if (component.equals(Constants.START_DATE_CAPS)) {
                            if (event != null) {
                                Date contractSD = (Date) dates[0];
                                if (!((PopupDateField) field).getValue().before(contractSD) && dates[1] == null) {
                                    ((PopupDateField) field).setDescription(com.stpl.app.contract.global.util.CommonUtils.convertDateToString(((PopupDateField) field).getValue()));
                                } else if (((PopupDateField) field).getValue().before(contractSD)) {
                                    AbstractNotificationUtils.getWarningNotification(Constants.START_DATE_SP, "Start Date cannot be before " + format.format(contractSD));
                                    detachListeners(field);
                                    ((PopupDateField) field).setValue(null);
                                    attachListeners(field, Constants.START_DATE_CAPS);
                                } else if (dates[1] != null && ((PopupDateField) field).getValue().after((Date) dates[1])) {
                                    AbstractNotificationUtils.getWarningNotification(Constants.START_DATE_SP, "Start Date cannot be after " + format.format((Date) dates[1]));
                                    detachListeners(field);
                                    ((PopupDateField) field).setValue(null);
                                    attachListeners(field, Constants.START_DATE_CAPS);
                                }
                            }
                        } else if (component.equals(Constants.END_DATE_CAPS) && event != null) {
                                Date contractSD = (Date) dates[0];
                                if ((Date) dates[1] == null && !((PopupDateField) field).getValue().before(contractSD)) {
                                    ((PopupDateField) field).setDescription(com.stpl.app.contract.global.util.CommonUtils.convertDateToString(((PopupDateField) field).getValue()));
                                } else if (((PopupDateField) field).getValue().before(contractSD)) {
                                    AbstractNotificationUtils.getWarningNotification(Constants.END_DATE_SP, "End Date cannot be before " + format.format(contractSD));
                                    detachListeners(field);
                                    ((PopupDateField) field).setValue(null);
                                    attachListeners(field, Constants.END_DATE_CAPS);
                                } else if (dates[1] != null && ((PopupDateField) field).getValue().after((Date) dates[1])) {
                                    AbstractNotificationUtils.getWarningNotification(Constants.END_DATE_SP, "End Date cannot be after " + format.format((Date) dates[1]));
                                    detachListeners(field);
                                    ((PopupDateField) field).setValue(null);
                                    attachListeners(field, Constants.END_DATE_CAPS);
                                }
                            }
                        }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });
    }

    /**
     * method for detaching listener.
     *
     * @param field
     */
    public void detachListeners(final AbstractField field) {

        List<Property.ValueChangeListener> listeners;

        listeners = (List<Property.ValueChangeListener>) field.getListeners(Property.ValueChangeEvent.class);
        for (final Property.ValueChangeListener object : listeners) {
            field.removeValueChangeListener(object);
        }
    }

    public PopupDateField getStartDate() {
        return startDate;
    }

    public void setStartDate(PopupDateField startDate) {
        this.startDate = startDate;
    }

    public PopupDateField getEndDate() {
        return endDate;
    }

    public void setEndDate(PopupDateField endDate) {
        this.endDate = endDate;
    }
    
    @SuppressWarnings("rawtypes")
    public class DateValidator extends AbstractValidator{
        String field;
        /**
         * The Constructor.
         */
        public DateValidator() {
            super("");
        }

        /**
         * The Constructor.
         *
         * @param errorMessage the error message
         */
        public DateValidator(final String field) {
            super("");
            this.field = field;
        }

        /**
         * Method used for validate validation of start date.
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         */
        @Override
        public void validate(final Object value) throws Validator.InvalidValueException {
            LOGGER.debug("Entering validate method");

            if (startDate.getValue() != null && endDate.getValue() != null) {
                if (startDate.getValue().after(endDate.getValue())) {
                    throw new Validator.InvalidValueException("IFP End Date should be greater than IFP Start Date");
                } else if (startDate.getValue().equals(endDate.getValue())) {
                    throw new Validator.InvalidValueException("Start date and End date are equal");
                }
            }
            if (dates.length > 0) {
                if ("StartDate".equals(field) && startDate.getValue() != null) {
                    if (dates[0] != null && startDate.getValue().before((Date) dates[0])) {
                        throw new Validator.InvalidValueException("Select IFP Start date after " + format.format(dates[0]));
                    } else if (dates[1] != null && startDate.getValue().after((Date) dates[1])) {
                        throw new Validator.InvalidValueException("Select IFP Start date before " + format.format(dates[1]));
                    }
                }
                if ("EndDate".equals(field) && endDate.getValue() != null && dates[1] != null && endDate.getValue().after((Date) dates[1])) {
                        throw new Validator.InvalidValueException("Select IFP End date before " + format.format(dates[1]));
                    }
                }
            LOGGER.debug("End of validate method");
        }

        /**
         * Method return boolean value validate isValidValue.
         *
         * @param value the value
         * @return true, if checks if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {
            LOGGER.debug("Entering isValidValue method");

            if (startDate.getValue() != null && endDate.getValue() != null) {
                return startDate.getValue().compareTo(endDate.getValue()) <= 0;
            }

            LOGGER.debug("End of isValidValue method");
            return true;
        }

        /**
         * Method used for get type.
         *
         * @return the type
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    public String textFieldEmptyChecks() {
        List<String> textFieldName = new ArrayList<>();
        String footerMsg = " should be entered on Items tab";
        if (itemFamilyplanNo.getValue().isEmpty()) {
            textFieldName.add("IFP No");
        }
        if (itemFamilyplanName.getValue().isEmpty()) {
            textFieldName.add("IFP Name");
        }
        return !textFieldName.isEmpty() ? StringUtils.join(textFieldName, ",") + footerMsg : StringUtils.EMPTY;
    }
}
