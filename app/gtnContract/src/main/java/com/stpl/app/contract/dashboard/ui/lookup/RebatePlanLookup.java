/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.ui.lookup;

import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.dashboard.dto.RSFilterGenerate;
import com.stpl.app.contract.dashboard.dto.RebatePlanDTO;
import com.stpl.app.contract.dashboard.logic.DashBoardLogic;
import com.stpl.app.contract.dashboard.logic.RebatePlanTableLogic;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.dashboard.util.ExcelExportUtil;
import com.stpl.app.contract.util.ResponsiveUtils;
import static com.stpl.app.serviceUtils.ConstantsUtils.QUOTE;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * Rebate Plan Lookup in Rebate Setup Tab.
 * @author Sibi
 */
public class RebatePlanLookup extends Window {

    private final static Logger LOGGER = Logger.getLogger(RebatePlanLookup.class);

    @UiField("rebatePlanIDLabel")
    private Label rebatePlanIDLabel;

    @UiField("rebatePlanNoLabel")
    private Label rebatePlanNoLabel;

    @UiField("rebatePlanNameLabel")
    private Label rebatePlanNameLabel;

    @UiField("rebatePlanStatusLabel")
    private Label rebatePlanStatusLabel;

    @UiField("rebatePlanTypeLabel")
    private Label rebatePlanTypeLabel;

    @UiField("rebatePlanID")
    private TextField rebatePlanId;

    @UiField("rebatePlanNo")
    private TextField rebatePlanNo;

    @UiField("rebatePlanName")
    private TextField rebatePlanName;

    @UiField("rebatePlanStatus")
    private CustomComboBox rebatePlanStatus;

    @UiField("rebatePlanType")
    private CustomComboBox rebatePlanType;    

    @UiField("searchBtn")
    private Button searchBtn;

    @UiField("resetBtn")
    private Button resetBtn;

    @UiField("selectBtn")
    private Button selectBtn;

    @UiField("closeBtn")
    private Button closeBtn;
    
    @UiField("excelExport")
    private Button excelExport;

    @UiField("tablelayout")
    private VerticalLayout tablelayout;
    
    @UiField("controlLayout")
    private HorizontalLayout controlLayout;
    
    RebatePlanTableLogic tableLogic = new RebatePlanTableLogic();
    DashBoardLogic dashboardLogic=new DashBoardLogic();
    private final ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    
    BeanItemContainer<RebatePlanDTO> resultsContainer = new BeanItemContainer<>(RebatePlanDTO.class);    
    
    private RebatePlanDTO rebatePlanDTO = new RebatePlanDTO();
    
    CustomFieldGroup binder;
    
    CommonUtil commonUtil = CommonUtil.getInstance();    
    
    private boolean isSelected;
    
    /**
     *
     * @throws Exception
     */
    public RebatePlanLookup() throws SystemException {
        this.setModal(true);
         center();
        setClosable(true);
        setModal(true);
        setHeight("775px");
        setWidth("900px");
        setCaption("Rebate Plan ID Lookup");        
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        init();
    }

    /**
     * Init method to the lookup.
     */
    private void init() throws SystemException {
        setContent(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-dashboard/rebate-plan-lookup.xml"), this));
        configureFields();
        configureBinder();        
        configureTable();
    }

    /**
     * Configures the fields in the Search Criteria.
     */
    private void configureFields() throws SystemException  {      
        commonUtil.loadComboBox(rebatePlanStatus,"STATUS", false);
        commonUtil.loadComboBox(rebatePlanType,"REBATE_PLAN_TYPE", false);     
     
        ContractUtils.loadRebatePlanLookupColumnMap();
        rebatePlanId.setImmediate(true);
        rebatePlanId.setMaxLength(NumericConstants.THIRTY_EIGHT);
        rebatePlanId.addValidator(new StringLengthValidator(" Rebate Plan Id Should be less than 38 characters", 0, NumericConstants.THIRTY_EIGHT, true));

        rebatePlanNo.setImmediate(true);
        rebatePlanNo.setMaxLength(NumericConstants.FIFTY);
        rebatePlanNo.addValidator(new StringLengthValidator(" Rebate Plan Id Should be less than 50 characters", 0, NumericConstants.FIFTY, true));

        rebatePlanName.setImmediate(true);
        rebatePlanName.setMaxLength(NumericConstants.HUNDRED);
        rebatePlanName.addValidator(new StringLengthValidator(" Rebate Plan Id Should be less than 38 characters", 0, NumericConstants.HUNDRED, true));
        
        excelExport.setIcon(new ThemeResource("../../icons/excel.png"));
        excelExport.setIcon(new ThemeResource("../../icons/excel.png"));
        excelExport.setStyleName("link");
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
    
    /**
     * Binds the search criteria field values to the DTO.
     */
    private void configureBinder() {
        binder = new CustomFieldGroup(new BeanItem(rebatePlanDTO));
        binder.bindMemberFields(this);        
    }
    
    /**
     * Configures the table logic and result table.
     */
    private void configureTable() {
        tableLogic.setContainerDataSource(resultsContainer);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);    
        resultsTable.setImmediate(true);
        resultsTable.setSizeFull();
        resultsTable.setConverter("createdDate", new DateToStringConverter());
        resultsTable.setConverter("modifiedDate", new DateToStringConverter());
        resultsTable.setVisibleColumns(ContractUtils.REBATE_PLAN_LOOKUP);
        resultsTable.setColumnHeaders(ContractUtils.REBATE_PLAN_LOOKUP_HEADER);
        resultsTable.setFilterBarVisible(true);
        resultsTable.addStyleName("filterbar");
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setFilterGenerator(new RSFilterGenerate());
        resultsTable.setSelectable(true);
        tablelayout.addComponent(resultsTable);        
        ResponsiveUtils.getResponsiveControls(tableLogic.createControls(), controlLayout);
    }
    
   /**
    * Searches for the rebate plan with the entered criteria.
    * @param event 
    */
    @UiHandler("searchBtn")
    public void searchLogic(Button.ClickEvent event){
        try {   

             if (binder.getField("rebatePlanId").getValue().toString().trim().isEmpty()
                            && binder.getField("rebatePlanNo").getValue().toString().trim().isEmpty()
                            && binder.getField("rebatePlanName").getValue().toString().trim().isEmpty()     
                            &&(binder.getField("rebatePlanStatus").getValue() == null || ((HelperDTO) binder.getField("rebatePlanStatus").getValue()).getId() == 0)
                            && (binder.getField("rebatePlanType").getValue() == null || ((HelperDTO) binder.getField("rebatePlanType").getValue()).getId() == 0)) {
                        MessageBox.showPlain(Icon.INFO, "Search Criteria", "Please enter Search Criteria", new MessageBoxListener() {
                            /**
                             * After clicking button, function will be executed.
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {

                            }
                        }, ButtonId.OK);
             }
             else{
            binder.commit();
            tableLogic.setSearchData(rebatePlanDTO,binder);              
            tableLogic.setCurrentPage(1);  
            resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            
             }
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
        rebatePlanDTO = new RebatePlanDTO();
        binder.setItemDataSource(new BeanItem(rebatePlanDTO));
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
            this.close();
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
    public RebatePlanDTO getSelectedItem(){
      return getBeanFromId(resultsTable.getValue());  
    }
    
    /**
     * 
     * @param obj
     * @return 
     */   
    public RebatePlanDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof RebatePlanDTO) {
            targetItem = new BeanItem<RebatePlanDTO>(
                    (RebatePlanDTO) obj);
        }        
        return (RebatePlanDTO) targetItem.getBean();
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

     protected void excelExportLogic() throws SystemException, PortalException {
        LOGGER.debug("Entering excelExportLogic");
        createWorkSheet();
        LOGGER.debug("Ending excelExportLogic");
    }

    private void createWorkSheet() {
        try{
        LOGGER.debug("Entering createWorkSheet");
        final long recordCount = (Integer) dashboardLogic.loadRebatePlan(rebatePlanDTO,0,0,true,null,null);
        ExcelExportforBB.createWorkSheet(resultsTable.getColumnHeaders(), recordCount, this, getUI(), "RebatePlanNo");
        LOGGER.debug("Ending createWorkSheet");
        }catch(Exception e){
        LOGGER.error(e);
        }
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException {
      
        RebatePlanDTO dto;
       List<RebatePlanDTO> resultList = (List<RebatePlanDTO>) dashboardLogic.loadRebatePlan(rebatePlanDTO,start,end,false,null,null); 
    
        for (int rowCount = 0; rowCount < resultList.size(); rowCount++) {

            dto = resultList.get(rowCount);

            printWriter.print(QUOTE + (dto.getRebatePlanId() != null ? dto.getRebatePlanId():StringUtils.EMPTY) + QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(QUOTE + (dto.getRebatePlanNo() != null ? dto.getRebatePlanNo():StringUtils.EMPTY ) + QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(QUOTE + (dto.getRebatePlanName() != null ? dto.getRebatePlanName():StringUtils.EMPTY ) + QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(QUOTE + (dto.getRebatePlanStatus() != null ? dto.getRebatePlanStatus():StringUtils.EMPTY ) + QUOTE + ExcelExportUtil.COMMA);
            
            printWriter.print(QUOTE + StringUtils.EMPTY + QUOTE + ExcelExportUtil.COMMA);
            
            printWriter.print(QUOTE + (dto.getRebateStructure() != null ? dto.getRebateStructure():StringUtils.EMPTY ) + QUOTE + ExcelExportUtil.COMMA);
            
            printWriter.print(QUOTE + (dto.getRangeBasedOn() != null ? dto.getRangeBasedOn():StringUtils.EMPTY ) + QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(QUOTE +(dto.getNetSalesFormula() != null ? dto.getNetSalesFormula():StringUtils.EMPTY )+ QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(QUOTE + (dto.getNetSalesRule() != null ? dto.getNetSalesRule():StringUtils.EMPTY ) + QUOTE + ExcelExportUtil.COMMA);
            
            printWriter.print(QUOTE + (dto.getRebateBasedOn() != null ? dto.getRebateBasedOn():StringUtils.EMPTY ) + QUOTE + ExcelExportUtil.COMMA);

            if (dto.getCreatedDate() != null) {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                String formateddate = formatter.format(dto.getCreatedDate());
                printWriter.print(formateddate + ExcelExportUtil.COMMA);
            } else {
                printWriter.print(StringUtils.EMPTY + ExcelExportUtil.COMMA);
            }

            printWriter.print(QUOTE + ((dto.getCreatedBy() != null || !(StringUtils.isBlank(dto.getCreatedBy()))) ? dto.getCreatedBy():StringUtils.EMPTY ) + QUOTE + ExcelExportUtil.COMMA);

            if (dto.getModifiedDate() != null) {
                SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
                String formateddate = fromUser.format(dto.getModifiedDate());
                printWriter.print(formateddate + ExcelExportUtil.COMMA);
            } else {
                printWriter.print(StringUtils.EMPTY + ExcelExportUtil.COMMA);
            }
            printWriter.println(QUOTE + (dto.getCreatedBy() != null || !(StringUtils.isBlank(dto.getModifiedBy())) ? dto.getModifiedBy():StringUtils.EMPTY ) + QUOTE + ExcelExportUtil.COMMA);
        }
      

    }
    
}
