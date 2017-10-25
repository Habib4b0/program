/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.rebateschedule.ui.lookup;

import com.stpl.app.global.abstractsearch.util.CommonUtils;
import com.stpl.app.global.item.util.UIUtils;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.rebateschedule.dto.NetSalesFormulaDTO;
import com.stpl.app.global.rebateschedule.dto.RSFilterGenerate;
import com.stpl.app.global.rebateschedule.logic.RebateScheduleLogic;
import com.stpl.app.global.rebateschedule.logic.tablelogic.NetSalesFormulaTableLogic;
import com.stpl.app.global.rebateschedule.util.RsUtils;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import static com.stpl.app.util.ConstantsUtils.QUOTE;
import com.stpl.app.util.ExcelExportUtil;
import com.stpl.app.util.ResponsiveUtils;
import com.stpl.app.util.TabNameUtil;
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
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Asha
 */
public class NetSalesFormulaLookup extends Window {
    
    private final static Logger LOGGER = Logger.getLogger(NetSalesFormulaLookup.class);
    
    CommonUtil commonUtil = CommonUtil.getInstance();
    @UiField("netSalesFormulaId")
    private TextField netSalesFormulaId;

    @UiField("netSalesFormulaNo")
    private TextField netSalesFormulaNo;

    @UiField("netSalesFormulaName")
    private TextField netSalesFormulaName;

    @UiField("netSalesFormulaType")
    private CustomComboBox netSalesFormulaType;   
    
    private NetSalesFormulaDTO netSalesDTO = new NetSalesFormulaDTO();
    
    ErrorfulFieldGroup binder;
    
    NetSalesFormulaTableLogic tableLogic = new NetSalesFormulaTableLogic();

    private final ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    
    BeanItemContainer<NetSalesFormulaDTO> resultsContainer = new BeanItemContainer<>(NetSalesFormulaDTO.class);  
    
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
    
    @UiField("errorMsg")
    public ErrorLabel errorMsg;
    
    @UiField("searchBtn")
    private Button searchBtn;
    
    /**
     *
     * @throws Exception
     */
    public NetSalesFormulaLookup(CustomTextField netSalesNoField) {
        
        this.netSalesNoField = netSalesNoField;
        this.setModal(true);
        center();
        setClosable(true);
        setModal(true);
        setHeight("775px");
        setWidth("900px");
        setCaption("Net Sales Formula Lookup");        
        addStyleName(ConstantsUtils.BOOTSTRAP);
        addStyleName(ConstantsUtils.BOOTSTRAP_BB);
        init();
    }
    
    /**
     * Init method to the lookup.
     */
    private void init() {
        setContent(Clara.create(getClass().getResourceAsStream("/clara/rebateschedule/net-sales-formula-lookup.xml"), this));
        binder = getBinder();
        configureFields();
        configureTable();
    }
    
    /**
     * Configures the fields in the Search Criteria.
     */
    private void configureFields() {  
        
        commonUtil.loadComboBox(netSalesFormulaType,UIUtils.NS_FORMULA_TYPE,false);

        netSalesFormulaId.setImmediate(true);
        netSalesFormulaId.setMaxLength(NumericConstants.THIRTY_EIGHT);
        netSalesFormulaId.addValidator(new StringLengthValidator(" Net Sales Formula Id Should be less than 38 characters", 0, NumericConstants.THIRTY_EIGHT, true));

        netSalesFormulaNo.setImmediate(true);
        netSalesFormulaNo.setMaxLength(NumericConstants.FIFTY);
        netSalesFormulaNo.addValidator(new StringLengthValidator(" Net Sales Formula No Should be less than 50 characters", 0, NumericConstants.FIFTY, true));

        netSalesFormulaName.setImmediate(true);
        netSalesFormulaName.setMaxLength(NumericConstants.HUNDRED);
        netSalesFormulaName.addValidator(new StringLengthValidator(" Net Sales Formula Name Should be less than 38 characters", 0, NumericConstants.HUNDRED, true));
        
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
                netSalesDTO = (NetSalesFormulaDTO) event.getItemId();
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
    
    /**
     * Gets the binder.
     *
     * @return the binder
     */
    public ErrorfulFieldGroup getBinder() {

        final ErrorfulFieldGroup deductionBinder;
        deductionBinder = new ErrorfulFieldGroup(new BeanItem<>(new NetSalesFormulaDTO()));
        deductionBinder.setBuffered(true);
        deductionBinder.bindMemberFields(this);
        deductionBinder.setErrorDisplay(errorMsg);
        return deductionBinder;
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
        resultsTable.setVisibleColumns(RsUtils.NET_SALES_LOOKUP);
        resultsTable.setColumnHeaders(RsUtils.NET_SALES_LOOKUP_HEADER);    
        resultsTable.setFilterBarVisible(true);
        resultsTable.addStyleName(ConstantsUtils.FILTER_BAR);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setFilterGenerator(new RSFilterGenerate());
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
             if (binder.getField("netSalesFormulaId").getValue().toString().trim().isEmpty()
                            && binder.getField("netSalesFormulaNo").getValue().toString().trim().isEmpty()
                            && binder.getField("netSalesFormulaName").getValue().toString().trim().isEmpty()                        
                            && (binder.getField("netSalesFormulaType").getValue() == null || ((HelperDTO) binder.getField("netSalesFormulaType").getValue()).getId() == 0)) {
                        MessageBox.showPlain(Icon.INFO, "Search Criteria", "Please enter Search Criteria", new MessageBoxListener() {
                            /**
                             * After clicking button, function will be executed.
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {

                            }
                        }, ButtonId.OK);
             }
             else
             {
            binder.commit();
            tableLogic.setSearchData(binder);            
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
    public void resetLogic(Button.ClickEvent event) {
        
        binder.getErrorDisplay().clearError();
        binder.setItemDataSource(new BeanItem<>(new NetSalesFormulaDTO()));

        tableLogic.clearAll();
        tableLogic.setReset(true);
        tableLogic.setRequiredCount(true);
        tableLogic.setCurrentPage(1);
        tableLogic.setContainerDataSource(resultsContainer);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        tableLogic.fireSetData(binder, false);

        setTableDefaultConfig();
        resultsTable.setSelectable(true);
        resultsTable.markAsDirty();
        resultsTable.setComponentError(null);
        resultsTable.setFilterBarVisible(true);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setFilterGenerator(new RSFilterGenerate());
        resultsTable.setValidationVisible(false);
        resultsTable.addStyleName(ConstantsUtils.FILTER_BAR);
              
    }
    
    /**
     * Selects the item in the table and closes the window.
     * @param event 
     */
    @UiHandler("selectBtn")
    public void selectLogic(Button.ClickEvent event) {
        if (null != resultsTable.getValue()) {
            netSalesNoField.setValue(netSalesDTO.getNetSalesFormulaNo());
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
    public NetSalesFormulaDTO getSelectedItem(){
      return getBeanFromId(resultsTable.getValue());  
    }
    
    /**
     * 
     * @param obj
     * @return 
     */   
    public NetSalesFormulaDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if(obj!=null){
            if (obj instanceof BeanItem<?>) {
                targetItem = (BeanItem<?>) obj;
            } else if (obj instanceof NetSalesFormulaDTO) {
                targetItem = new BeanItem<NetSalesFormulaDTO>(
                        (NetSalesFormulaDTO) obj);
            }
        }
        return obj!=null?(NetSalesFormulaDTO) targetItem.getBean():new NetSalesFormulaDTO();
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

    public NetSalesFormulaDTO getNetSalesDTO() {
        return netSalesDTO;
    }

    public void setNetSalesDTO(NetSalesFormulaDTO netSalesDTO) {
        this.netSalesDTO = netSalesDTO;
    }
    
    protected void excelExportLogic() throws SystemException, PortalException, ParseException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        LOGGER.debug("Entering excelExportLogic");
        createWorkSheet();
        LOGGER.debug("Ending excelExportLogic");
    }

    private void createWorkSheet() throws SystemException, PortalException, ParseException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        LOGGER.debug("Entering createWorkSheet");
        final long recordCount =  (Integer) new RebateScheduleLogic().getNsfCount(binder, null);
        ExcelExportforBB.createWorkSheet(resultsTable.getColumnHeaders(), recordCount, this, getUI(), TabNameUtil.RS_REBATE_SETUP);
        LOGGER.debug("Ending createWorkSheet");
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, ParseException {
        NetSalesFormulaDTO dto;
        final List<SortByColumn> columns = new ArrayList<>();
        final List<NetSalesFormulaDTO> nsfList = new RebateScheduleLogic().loadNsfResults(binder, start, end, columns, null);
        for (int rowCount = 0; rowCount < nsfList.size(); rowCount++) {

            dto = nsfList.get(rowCount);

                        printWriter.print(QUOTE + (dto.getNetSalesFormulaType() == null ? StringUtils.EMPTY : dto.getNetSalesFormulaType()) + QUOTE + ExcelExportUtil.COMMA);

                        printWriter.print(QUOTE + dto.getNetSalesFormulaId()+ QUOTE + ExcelExportUtil.COMMA);

                        printWriter.print(QUOTE + dto.getNetSalesFormulaNo()+ QUOTE + ExcelExportUtil.COMMA);

                        printWriter.print(QUOTE + dto.getNetSalesFormulaName() + QUOTE + ExcelExportUtil.COMMA);

                        if (dto.getNsfcreateDate() != null) {
                            printWriter.print(dto.getNsfcreateDate() + ExcelExportUtil.COMMA);
                        } else {
                            printWriter.print(StringUtils.EMPTY + ExcelExportUtil.COMMA);
                        }

                        printWriter.print(QUOTE + (dto.getNsfcreatedBy() == null ? StringUtils.EMPTY : dto.getNsfcreatedBy()) + QUOTE + ExcelExportUtil.COMMA);

                        if (dto.getNsfmodifyDate() != null) {
                            printWriter.print(dto.getNsfmodifyDate() + ExcelExportUtil.COMMA);
                        } else {
                            printWriter.print(StringUtils.EMPTY + ExcelExportUtil.COMMA);
                        }
                        printWriter.println(QUOTE + (StringUtils.isBlank(dto.getNsfmodifiedBy()) ? StringUtils.EMPTY : dto.getNsfmodifiedBy()) + QUOTE + ExcelExportUtil.COMMA);
        }
        
        
    }
    
    public void setTableDefaultConfig() {
        resultsTable.setConverter("nsfcreatedDate", new DateToStringConverter());
        resultsTable.setConverter("nsfmodifiedDate", new DateToStringConverter());
        resultsTable.setVisibleColumns(RsUtils.NET_SALES_LOOKUP);
        resultsTable.setColumnHeaders(RsUtils.NET_SALES_LOOKUP_HEADER);
        resultsTable.markAsDirtyRecursive();
        resultsTable.setImmediate(true);

    }
    
}
