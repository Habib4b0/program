/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.rebateschedule.ui.lookup;

import com.stpl.app.global.abstractsearch.dto.SearchCriteriaDTO;
import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.global.abstractsearch.util.CommonUtils;
import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.abstractsearch.util.UIUtils;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.rebateschedule.logic.tablelogic.CDLookupTableLogic;

import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ResponsiveUtils;
import com.stpl.app.util.TabNameUtil;
import com.stpl.ifs.util.ExcelExportforBB;

import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.Arrays;
import java.util.ResourceBundle;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author maheshj
 */
public class ComplianceDeductionLookUP  extends Window {
    
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



    
    private final static Logger LOGGER = Logger.getLogger(ComplianceDeductionLookUP.class);
    
    CommonUtil commonUtil = CommonUtil.getInstance();

   
    
    @UiField("combo1")
    private CustomComboBox combo1;
    @UiField("combo6")
    private CustomComboBox combo6;
    @UiField("text6")
    private TextField text6;
    @UiField("text7")
    private TextField text7;
    

    
    private SearchCriteriaDTO searchCriteriaDTO = new SearchCriteriaDTO();
    private SearchResultsDTO searchResultsDTO = new SearchResultsDTO();
    
    ErrorfulFieldGroup binder;
    
    CDLookupTableLogic tableLogic = new CDLookupTableLogic();

    private final ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    
    BeanItemContainer<SearchResultsDTO> resultsContainer = new BeanItemContainer<>(SearchResultsDTO.class);  
    
    @UiField("tablelayout")
    private VerticalLayout tablelayout;
    
    @UiField("controlLayout")
    private HorizontalLayout controlLayout;
    
    public boolean isSelected;
    
    final CommonUtils commonsUtil = new CommonUtils();
    
    CustomTextField netSalesNoField;
    
    /**
     * the Excel Export Button
     */
    @UiField("excelExport")
    private Button excelExport;
    
    @UiField("searchLayout")
    GridLayout searchLayout;
    public static ResourceBundle columnBundle = ResourceBundle.getBundle("properties.tableColumns");
    HelperListUtil dfs=HelperListUtil.getInstance();
     com.stpl.app.util.HelperDTO tempDto=null;
    /**
     *
     * @throws Exception
     */
    public ComplianceDeductionLookUP(CustomTextField netSalesNoField, com.stpl.app.util.HelperDTO tempDto,int a) throws Exception {
        
        this.netSalesNoField = netSalesNoField;
        this.tempDto=tempDto;
        this.setModal(true);
        center();
        setClosable(true);
        setModal(true);
        setHeight("775px");
        setWidth("900px");
        setCaption("Compliance Deduction Lookup");        
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        init();
    }
     /**
     *
     * @throws Exception
     */
    public ComplianceDeductionLookUP(com.stpl.app.util.HelperDTO tempDto,int a) throws Exception {
        
        this.tempDto=tempDto;
        this.setModal(true);
        center();
        setClosable(true);
        setModal(true);
        setHeight("775px");
        setWidth("900px");
        setCaption("Compliance Deduction Lookup");        
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        init();
    }
    
    /**
     * Init method to the lookup.
     */
    private void init() throws Exception {
        setContent(Clara.create(getClass().getResourceAsStream("/clara/rebateschedule/complianceDeductionLookup.xml"), this));
        configureBinder(); 
        configureFields();
        configureTable();
    }
    
    /**
     * Configures the fields in the Search Criteria.
     */
    private void configureFields() throws Exception  {  
        
       commonsUtil.loadComboBox(combo1, UIUtils.RULE_TYPE);
       commonsUtil.loadComboBox(combo6, UIUtils.RULE_CATEGORY);
       combo1.select(tempDto);
       combo1.setEnabled(false);
        
        resultsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                itemselectLogic(event);
            }
        });
        
        excelExport.setCaption("EXPORT");
        excelExport.setDescription("Export to excel");
        excelExport.setIconAlternateText("Excel export");
        excelExport.setHtmlContentAllowed(true);
        excelExport.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs
             *
             * @param event - ErrorEvent
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                // parses the error.
            }
        });
        excelExport.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    binder.getFields();
                    excelExportLogic();
                } catch (Exception exception) {
                    LOGGER.error(exception);
                }
            }
        });
        
    }
    
    public void itemselectLogic(final ItemClickEvent event) {
        try {
            if (event.getItemId() != null) {
                searchResultsDTO = (SearchResultsDTO) event.getItemId();
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
    
    /**
     * Binds the search criteria field values to the DTO.
     */
    private void configureBinder() {
        binder = new ErrorfulFieldGroup(new BeanItem<>(searchCriteriaDTO));
        binder.setBuffered(true);
        binder.bindMemberFields(this);      
    }
    
    /**
     * Configures the table logic and result table.
     */
    private void configureTable() {
      
        tableLogic.setContainerDataSource(resultsContainer);
        tableLogic.setPageLength(10);
        tableLogic.sinkItemPerPageWithPageLength(false);    
        resultsTable.setImmediate(true);
        resultsTable.setSizeFull();
        resultsTable.setVisibleColumns(getColumns(true, ConstantUtil.COMPLIANCE_DEDUCTION_RULES));
        resultsTable.setColumnHeaders(Arrays.copyOf(getColumns(false,  ConstantUtil.COMPLIANCE_DEDUCTION_RULES),getColumns(false, ConstantUtil.COMPLIANCE_DEDUCTION_RULES).length, String[].class));
    
        resultsTable.setFilterBarVisible(true);
        resultsTable.addStyleName(ConstantsUtils.FILTER_BAR);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setSelectable(true);
        tablelayout.addComponent(resultsTable);        
        ResponsiveUtils.getResponsiveControls(tableLogic.createControls(), controlLayout);
    }
    
    /**
    * Searches for the Net Sales with the entered criteria.
    * @param event 
    */
    @UiHandler("searchBtn")
    public void searchLogic(Button.ClickEvent event){
        try {   
            binder.commit();
            tableLogic.setSearchData(searchResultsDTO, binder);            
            tableLogic.setCurrentPage(1);       
            resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        } catch (FieldGroup.CommitException ex) {
            LOGGER.error(ex);
        }        
    }
    
    /**
     * Resets the search fields.
     * @param event 
     */
    @UiHandler("resetBtn")
    public void resetLogic(Button.ClickEvent event){
        binder.setItemDataSource(new BeanItem<>(new SearchResultsDTO()));
        resultsTable.removeAllItems();
        tableLogic.clearAll();
    }
    
    /**
     * Selects the item in the table and closes the window.
     * @param event 
     */
    @UiHandler("selectBtn")
    public void selectLogic(Button.ClickEvent event) {
        if (null != resultsTable.getValue()) {
            isSelected = true;
            close();
        } else {
            final MessageBox msg = MessageBox.showPlain(Icon.WARN, "Select error", "Please select a record.", new MessageBoxListener() {
              
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
            	@SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        }
    }
    
     /**
     * Closes the window.
     * @param event 
     */
    @UiHandler("closeBtn")
    public void closeLogic(Button.ClickEvent event){        
        this.close();
    }
   
    /**
     * Returns the selected Item from the table.
     * @return 
     */
    public SearchResultsDTO getSelectedItem(){
      return getBeanFromId(resultsTable.getValue());  
    }
    
    /**
     * 
     * @param obj
     * @return 
     */   
    public SearchResultsDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if(obj!=null){
            if (obj instanceof BeanItem<?>) {
                targetItem = (BeanItem<?>) obj;
            } else if (obj instanceof SearchResultsDTO) {
                targetItem = new BeanItem<SearchResultsDTO>(
                        (SearchResultsDTO) obj);
            }
        }
        return obj!=null?(SearchResultsDTO) targetItem.getBean():new SearchResultsDTO();
    }

    /**
     * Returns the flag.
     * true - if item is selected in the table.
     * false - if item is not selected.
     * @return 
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Sets the flag as true when an item is selected in the table.
     * @param isSelected 
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public SearchResultsDTO getNetSalesDTO() {
        return searchResultsDTO;
    }

    public void setNetSalesDTO(SearchResultsDTO netSalesDTO) {
        this.searchResultsDTO = netSalesDTO;
    }
    
    protected void excelExportLogic() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering excelExportLogic");
        createWorkSheet();
        LOGGER.info("Ending excelExportLogic");
    }

    private void createWorkSheet() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering createWorkSheet");
        final long recordCount = resultsTable.getContainerDataSource().size();
        ExcelExportforBB.createWorkSheet(resultsTable.getColumnHeaders(), recordCount, this, getUI(), TabNameUtil.RS_REBATE_SETUP);
        LOGGER.info("Ending createWorkSheet");
    }

     /**
     * To get Visible Columns & Visible Headers
     *
     * @param isColumns
     * @param key
     * @return
     */
    private Object[] getColumns(boolean isColumns, String key) {
        return (columnBundle.getString(isColumns ? "columns" + key : "headers" + key)).split(",");

    }
    
}
