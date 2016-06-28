/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.abstractsearch.ui;

import com.stpl.app.contract.abstractsearch.dto.SearchCriteriaDTO;
import com.stpl.app.contract.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.contract.abstractsearch.logic.AbstractSearchLogic;
import com.stpl.app.contract.abstractsearch.logic.tablelogic.AbstractSearchTableLogic;
import com.stpl.app.contract.abstractsearch.util.CommonUtils;
import com.stpl.app.contract.abstractsearch.util.ConstantUtil;
import com.stpl.app.contract.abstractsearch.util.ValidationUtil;
import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.contractheader.dto.ContractHeaderCriteria;
import com.stpl.app.contract.contractheader.dto.ContractSearchFilterGenerator;
import com.stpl.app.contract.contractheader.ui.form.TradingPartnerLookUp;
import com.stpl.app.contract.contractheader.ui.view.ContractHeaderView;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.CHFunctionNameUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.app.contract.util.ResponsiveUtils;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.UISecurityUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Resource;
import static com.vaadin.server.Sizeable.UNITS_PERCENTAGE;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * The Class AbstractSearchForm.
 *
 * @author pvinoth
 */
public class AbstractSearchForm extends CustomComponent {

    /** The text1. */
    @UiField("text1")
    private TextField text1;
    
    /** The text2. */
    @UiField("text2")
    private TextField text2;
    
    /** The text3. */
    @UiField("text3")
    private TextField text3;
    
    /** The text4. */
    @UiField("text4")
    private TextField text4;
    
    /** The text5. */
    @UiField("text5")
    private CustomTextField text5;

    /** The combo1. */
    @UiField("combo1")
    private ComboBox combo1;
    
    /** The combo2. */
    @UiField("combo2")
    private ComboBox combo2;

    /** The label1. */
    @UiField("label1")
    private Label label1;
    
    /** The label2. */
    @UiField("label2")
    private Label label2;
    
    /** The label3. */
    @UiField("label3")
    private Label label3;
    
    /** The label4. */
    @UiField("label4")
    private Label label4;
    
    /** The label5. */
    @UiField("label5")
    private Label label5;
    
    /** The label6. */
    @UiField("label6")
    private Label label6;
    
    /** The label7. */
    @UiField("label7")
    private Label label7;

    /** The error msg. */
    @UiField("errorMsg")
    private ErrorLabel errorMsg;

    @UiField("tableLayout")
    private VerticalLayout tableLayout;  

    /** The excel. */
    @UiField("excel")
    public Button excel;
    
    @UiField("addBtn")
    public Button addBtn;
    
    @UiField("editBtn")
    public Button editBtn;
    
    @UiField("viewBtn")
    public Button viewBtn;
    
    @UiField("resetBtn")
    public Button resetBtn;
    
    /** The trading partner system id. */
    private TextField tradingPartnerSystemId = new TextField();

    /** The excel export image. */
    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");
    
    AbstractSearchTableLogic tableLogic = new AbstractSearchTableLogic();        
    private ExtPagedTable resultTable = new ExtPagedTable(tableLogic);
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(AbstractSearchForm.class);
    
    /** The search logic. */
    private final AbstractSearchLogic searchLogic = new AbstractSearchLogic();
    
    /** The result bean. */
    private BeanItemContainer<SearchResultsDTO> resultBean = new BeanItemContainer<SearchResultsDTO>(SearchResultsDTO.class);
    
    /** The column bundle. */
    public static ResourceBundle columnBundle = ResourceBundle.getBundle("properties.tablecolumns");
    
    /** The module name. */
    private String moduleName = StringUtils.EMPTY;
    
    /** The binder. */
    private final CustomFieldGroup binder;
    
    /** The common utils. */
    private final CommonUtils commonUtils = new CommonUtils();
    
    /** The search criteria. */
    private ContractHeaderCriteria searchCriteria = new ContractHeaderCriteria();
    
    private CommonUtil util = new CommonUtil();
    
    @UiField("controlLayout")
    private HorizontalLayout controlLayout;
    
    CommonUtil commonUtil = new CommonUtil();
    SessionDTO sessionDTO;
    
    /**
     * The Constructor.
     *
     * @param moduleName the module name
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public AbstractSearchForm(String moduleName, final SessionDTO sessionDTO) throws SystemException, Exception {
        super();
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/abstractsearchform.xml"), this));
        binder = getBinder();
        this.moduleName = moduleName;
        this.sessionDTO=sessionDTO;
        init();
    }

    /**
     * Inits the.
     *
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public void init() throws PortalException, SystemException, Exception {
        configureFields();
        configureTable();
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    public CustomFieldGroup getBinder() {
        final SearchCriteriaDTO bean = new SearchCriteriaDTO();
        final CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<SearchCriteriaDTO>(bean));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
        return binder;
    }

    /**
     * Method has configuration for the fields.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public void configureFields() throws SystemException, Exception {
        try {
            LOGGER.info("Enters configureFields() ");
              final StplSecurity stplSecurity = new StplSecurity();
        LOGGER.info("Enters configureFields method");

        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.CONTRACT_HEADER+","+"Contract Header Search");
        if (functionHM.get(CHFunctionNameUtils.Add) != null && !((AppPermission) functionHM.get(CHFunctionNameUtils.Add)).isFunctionFlag()) {
           addBtn.setVisible(false);
          
        }else{
            btnAddLogic();
        }
        if (functionHM.get(CHFunctionNameUtils.Edit) != null && !((AppPermission) functionHM.get(CHFunctionNameUtils.Edit)).isFunctionFlag()) {
              editBtn.setVisible(false);
        }else{
            btnEditLogic();
          
        }
        if (functionHM.get(CHFunctionNameUtils.View) != null && !((AppPermission) functionHM.get(CHFunctionNameUtils.View)).isFunctionFlag()) {
             viewBtn.setVisible(false);
        }else{
             btnViewLogic();
          
        }
        if (functionHM.get(CHFunctionNameUtils.ResetAbstract) != null && !((AppPermission) functionHM.get(CHFunctionNameUtils.ResetAbstract)).isFunctionFlag()) {
            resetBtn.setVisible(false);
        }else{
           btnResetLogic();
        }
            fieldValidation(moduleName);
            loadComponents(moduleName);
            for (java.lang.reflect.Field field : this.getClass().getDeclaredFields()) {
                if (field.get(this) instanceof Label && ((Label) field.get(this)).isVisible()) {
                    setLabelName(field.get(this), field.getName() + "_" + moduleName);
                    ResponsiveUtils.makeLabel((Label) field.get(this), false);

                } else if (field.get(this) instanceof TextField && ((TextField) field.get(this)).isVisible()) {

                    if (((TextField) field.get(this)).isVisible()) {
                        textValidation(field.get(this), ((TextField) field.get(this)).getData());
                        ((TextField) field.get(this)).setValidationVisible(true);

                    }
                }
            }

            text5.setDescription(text5.getValue());
            text5.addStyleName(Constants.SEARCH_ICON_STYLE);
            text5.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Invoked when the value of the field is changed.
                 *
                 * @param event - ValueChangeEvent
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final Property.ValueChangeEvent event) {
                    text5.setDescription(text5.getValue());
                }
            });

            text5.addClickListener(new CustomTextField.ClickListener() {
                /**
                 * Invoked when the focus is on that field.
                 *
                 * @param event - ValueChangeEvent
                 */
                public void click(final CustomTextField.ClickEvent event) {
                    try {
                        final TradingPartnerLookUp lookUp = new TradingPartnerLookUp(tradingPartnerSystemId, text5);
                        UI.getCurrent().addWindow(lookUp);
                        lookUp.addCloseListener(new Window.CloseListener() {
                            /**
                             * To catch window close event
                             *
                             * @param event - WindowCloseEvent
                             */
                            @SuppressWarnings("PMD")
                            public void windowClose(final Window.CloseEvent e) {
                                text4.focus();
                                text5.setReadOnly(true);
                            }
                        });
                    } catch (SystemException ex) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsg);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    }
                }
            });


            excel.setIcon(excelExportImage);
            excel.setDescription("Export to excel");
            excel.setIconAlternateText("Excel export");
            excel.setHtmlContentAllowed(true);
            LOGGER.info("Exits configureFields() ");
        } catch (Exception e) {
         LOGGER.error(e);
        }
    }

    /**
     * Field validation.
     *
     * @param moduleName the module name
     */
    private void fieldValidation(String moduleName) {
        if (ConstantUtil.CONTRACT_HEADER.equals(moduleName)) {
            text2.setData("maxlengthvalidationfifty,maxlengthvalidationcontractno,splcharvalidation3,specialcharvalidationcontractno");
            text3.setData("maxlengthvalidationhundred,maxlengthvalidationcontractname,splcharvalidation3,specialcharvalidationcontractname");
            text4.setData("maxlengthvalidationfifty,maxlengthvalidationtradeclass,specialcharvalidation,specialcharvalidationtradeclass");
        }
    }

    /**
     * Load components.
     *
     * @param moduleName the module name
     * @throws Exception the exception
     */
    private void loadComponents(String moduleName) throws Exception {
        if (ConstantUtil.CONTRACT_HEADER.equals(moduleName)) {
            commonUtils.loadComboBox(combo1, UIUtils.STATUS,false);
            commonUtils.loadComboBox(combo2, UIUtils.CONTRACT_TYPE,false);
        }
    }

    /**
     * To Set Label Value.
     *
     * @param obj the obj
     * @param key the key
     */
    private void setLabelName(Object obj, String key) {
        try {
            Label tempObj = (Label) obj;
            if (ValidationUtil.getLabel(key) != null && StringUtils.isNotEmpty(ValidationUtil.getLabel(key))) {
                tempObj.setValue(ValidationUtil.getLabel(key));
            }
        } catch (Exception e) {
           LOGGER.error(e);
        }
    }

    /**
     * To Validate Text Fields.
     *
     * @param obj the obj
     * @param key the key
     */
    private void textValidation(Object obj, Object key) {
        try {
            if (obj != null && key != null && !"null".equals(key) && obj instanceof TextField) {
                TextField tempObj = (TextField) obj;
                String[] rules = String.valueOf(key).split(",");
                if (rules[0] != null && ValidationUtil.getMessage(rules[0]) != null && StringUtils.isNotEmpty(ValidationUtil.getMessage(rules[0]))) {
                    String[] temp = ValidationUtil.getMessage(rules[0]).split(",");
                    tempObj.addValidator(new StringLengthValidator(ValidationUtil.getMessage(rules[1]), Integer.valueOf(temp[0]), Integer.valueOf(temp[1]), Boolean.valueOf(temp[2])));
                }
                if (rules[2] != null && ValidationUtil.getMessage(rules[2]) != null && StringUtils.isNotEmpty(ValidationUtil.getMessage(rules[2]))) {
                    tempObj.addValidator(new RegexpValidator(ValidationUtil.getMessage(rules[2]), ValidationUtil.getMessage(rules[3])));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Sets the table default config.
     *
     * @param resultTable the result table
     * @param key the key
     */
    public void setTableDefaultConfig(String key) {
        resultTable.setVisibleColumns(getColumns(true, moduleName));
        resultTable.setColumnHeaders(Arrays.copyOf(getColumns(false, moduleName), getColumns(false, moduleName).length, String[].class));
        resultTable.markAsDirtyRecursive();
        resultTable.setImmediate(true);
        resultTable.setWidth(100, UNITS_PERCENTAGE);

    }

    /**
     * To get Visible Columns & Visible Headers.
     *
     * @param isColumns the is columns
     * @param key the key
     * @return the columns
     */
    private Object[] getColumns(boolean isColumns, String key) {

        return (columnBundle.getString(isColumns ? "columns" + key : "headers" + key)).split(",");

    }

    /**
     * Gets the collapsible columns600 px.
     *
     * @param table the table
     * @return the collapsible columns600 px
     */
    private static String[] getCollapsibleColumns600Px(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * Gets the collapsible columns480 px.
     *
     * @param table the table
     * @return the collapsible columns480 px
     */
    private static String[] getCollapsibleColumns480Px(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * Gets the collapsible columns978 px.
     *
     * @param table the table
     * @return the collapsible columns978 px
     */
    private static String[] getCollapsibleColumns978Px(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        list.remove(propertyIds[3]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * Gets the collapsible columns default1515 px.
     *
     * @param table the table
     * @return the collapsible columns default1515 px
     */
    private static String[] getCollapsibleColumnsDefault1515Px(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        list.remove(propertyIds[3]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * Gets the collapsible columns default.
     *
     * @param table the table
     * @return the collapsible columns default
     */
    private static String[] getCollapsibleColumnsDefault(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        for (int i = 0; i < 10; i++) {
            list.remove(propertyIds[i]);
        }
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }
    
    /**
     * Btn reset logic.
     *
     * @param event the event
     */
    private void btnResetLogic() {
         resetBtn.addClickListener(new Button.ClickListener() {
             @Override
             public void buttonClick(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, util.getHeaderMessage(), util.getResetMessage(), new MessageBoxListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            public void buttonClicked(final ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
            
                    binder.getErrorDisplay().clearError();
                    getBinder();

                    tableLogic.clearAll();
                    tableLogic.getFilters().clear();
                    resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
                    setTableDefaultConfig(moduleName);

                }
            }
        }, ButtonId.YES, ButtonId.NO);
        LOGGER.info("Ending Reset operation");
    }
                 });
    }
    
    /**
     * Btn search logic.
     *
     * @param event the event
     */
    @UiHandler("searchBtn")
    public void btnSearchLogic(Button.ClickEvent event) {
      
        List<Object> collapsedColumns = new ArrayList<Object>();
        for (Object item : resultTable.getVisibleColumns()) {
            if (resultTable.isColumnCollapsed(item)) {
                collapsedColumns.add(item);
            }
        }
        if (!searchLogic.checkSearchCriteria(binder)) {
            resultTable.removeAllItems();
            MessageBox.showPlain(Icon.ERROR, "No Search Criteria", "Please enter Search Criteria", ButtonId.OK);
        } else {

            try {
                binder.commit();

                tableLogic.configureSearchData(binder, this.moduleName, this);
                resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
                resultTable.setFilterGenerator(new ContractSearchFilterGenerator());
                resultTable.setImmediate(true);
                resultTable.setWidth(100, UNITS_PERCENTAGE);
                resultTable.addStyleName("TableCheckBox");
                resultTable.setSelectable(true);
                if (tableLogic.isResultsEmpty()) {
                    CommonUIUtils.successNotification("No results found");
                } else {
                    CommonUIUtils.successNotification("Search Completed");
                }
                
                resultTable.markAsDirtyRecursive();

            } catch (FieldGroup.CommitException commit) {
                LOGGER.error(commit);

            } catch (Exception exception) {
               
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004));
                LOGGER.error(exception);
            }
        }
       

    }
    
    /**
     * Excel button logic.
     *
     * @param event the event
     */
    @UiHandler("excel")
    public void excelButtonLogic(Button.ClickEvent event) {
        try {
            searchLogic.excelExportLogic(moduleName,resultTable,this,binder);
        } catch (Exception e) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1011));
            LOGGER.error(e);
        }
     
        
    }
    
    /**
     * add button listener.
     *
     * @param event the event
     */

     private void btnAddLogic() {
        addBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                sessionDTO.setMode(Constants.ADD);
                getUI().getNavigator().navigateTo(ContractHeaderView.NAME);
            }
        });
    }
       
    
    /**
     * edit button listener.
     *
     * @param event the event
     */
 
     private void btnEditLogic() {
 editBtn.addClickListener(new Button.ClickListener() {
      @Override
             public void buttonClick(Button.ClickEvent event) {
        if (resultTable.getValue() != null) {
            SearchResultsDTO searchResultsDTO = (SearchResultsDTO) resultTable.getValue();
            if ("true".equals(searchResultsDTO.getRecordLockStatus())) {
                if (itemStatusCheck()) {
                    sessionDTO.setMode(Constants.EDIT);
                    if ((Integer) sessionDTO.getSystemId() != 0) {
                        getUI().getNavigator().navigateTo(ContractHeaderView.NAME);
                    } else {
                        AbstractNotificationUtils.getErrorNotification("Edit Error", "Please select a record to Edit");
                    }
                } else {
                    AbstractNotificationUtils.getInfoNotification("Access Denied", "You are not having permission to Edit this record");
                }
            } else {
                sessionDTO.setMode(Constants.EDIT);
                if ((Integer) sessionDTO.getSystemId() != 0) {
                    getUI().getNavigator().navigateTo(ContractHeaderView.NAME);
                } else {
                    AbstractNotificationUtils.getErrorNotification("Edit Error", "Please select a record to Edit");
                }
            }

        } else {
            AbstractNotificationUtils.getErrorNotification("Edit Error", "Please select a record to Edit");
        }

    }
 });
     }
    
    /**
     * view button listener.
     *
     * @param event the event
     */
    private void btnViewLogic() {
 viewBtn.addClickListener(new Button.ClickListener() {
      @Override
             public void buttonClick(Button.ClickEvent event) {
        if (resultTable.getValue() != null) {
        sessionDTO.setMode(Constants.VIEW);
        if ((Integer) sessionDTO.getSystemId() != 0) {
            getUI().getNavigator().navigateTo(ContractHeaderView.NAME);
        }else {
            AbstractNotificationUtils.getErrorNotification("View Error", "Please select a record to view.");
        }
        }else {
            AbstractNotificationUtils.getErrorNotification("View Error", "Please select a record to view.");
        }
    }
 });
    }
    
   
    public void itemselectLogic(final ItemClickEvent event) {
        try {
            LOGGER.info("Entering itemSelectLogic() with event parameter");
            SearchResultsDTO searchResultsDTO = (SearchResultsDTO) getBeanFromId(event.getItemId());
            LOGGER.info("systemId=" + searchResultsDTO.getContractSystemId());
            
            if (event.getItemId() != null) {
                if (event.isDoubleClick()) {
                    if ("true".equals(searchResultsDTO.getRecordLockStatus())) {
                        if (!itemStatusCheck()) {
                            sessionDTO.setMode(Constants.VIEW);
                            getUI().getNavigator().navigateTo(ContractHeaderView.NAME);
                        } else {
                            sessionDTO.setMode(Constants.EDIT);
                            getUI().getNavigator().navigateTo(ContractHeaderView.NAME);
                        }
                    } else {
                            sessionDTO.setMode(Constants.EDIT);
                            getUI().getNavigator().navigateTo(ContractHeaderView.NAME);
                    }
                }
            }
            
            sessionDTO.setSystemId(searchResultsDTO.getContractSystemId());
            LOGGER.info("End of itemSelectLogic() ");
        } catch (Exception ex) {
           LOGGER.error(ex);
        }
    }
    
    public boolean itemStatusCheck() {
        try {

            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
            boolean etlCheck = commonUtil.checkETLUser(Integer.valueOf(userId));
            return etlCheck;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return false;
    }
    
    /**
     * Gets the bean from id.
     *
     * @param obj the obj
     * @return the bean from id
     */
    public SearchResultsDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof SearchResultsDTO) {
            targetItem = new BeanItem<SearchResultsDTO>(
                    (SearchResultsDTO) obj);
        }
        return (SearchResultsDTO) targetItem.getBean();
    }
     private void configureTable() {
        
        tableLayout.addComponent(resultTable);
        ResponsiveUtils.getResponsiveControls(tableLogic.createControls(), controlLayout);
        tableLayout.addComponent(controlLayout);
        
        
        tableLogic.setContainerDataSource(resultBean);
        tableLogic.setPageLength(10);
        tableLogic.sinkItemPerPageWithPageLength(false);
        
        setTableDefaultConfig(moduleName);
        resultTable.setSelectable(true);
        resultTable.markAsDirty();        
        resultTable.setComponentError(null);
        resultTable.setFilterBarVisible(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
         resultTable.setFilterGenerator(new ContractSearchFilterGenerator());
        resultTable.setValidationVisible(false);
        resultTable.addStyleName("filterbar");
        
        resultTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                itemselectLogic(event);
            }
        });
    }
    
}
