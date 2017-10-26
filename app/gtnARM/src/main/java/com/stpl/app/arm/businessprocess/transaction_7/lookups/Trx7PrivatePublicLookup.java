
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction_7.lookups;

import com.stpl.app.arm.adjustmentrateconfiguration.dto.ViewLookupDTO;
import com.stpl.app.arm.adjustmentrateconfiguration.logic.AdjustmentRateLogic;
import com.stpl.app.arm.businessprocess.pipelineaccrual.logic.tablelogic.PublicPrivateLookupTableLogic;
import com.stpl.app.arm.dataselection.dto.ViewFilterGenerator;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.StringToDateConverter;
import com.vaadin.ui.Button;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author sathyaseelan.v
 */
public class Trx7PrivatePublicLookup extends Window {

    /**
     * Options will be: Public, Private
     */

    /**
     * Search by View Name, results table will filter based on viewNameEpl
     */
    @UiField("viewNameEpl")
    private TextField viewNameEpl;

    /**
     * To execute the search, will populate views
     */
    @UiField("searchBtn")
    Button searchBtn;
    /**
     * To reset the View Search section
     */
    @UiField("resetBtn")
    Button resetBtn;
    /**
     * Returns the View the user selected back to the main screen that called
     * the lookup. Closes the pop-up
     */
    @UiField("selectBtn")
    Button selectBtn;
    /**
     * Closes the pop-up without returning a selction.
     */
    @UiField("closeBtn")
    Button closeBtn;
    @UiField("resultsTableLayoutEpl")
    private VerticalLayout resultsTableLayoutEpl;
    PublicPrivateLookupTableLogic tableLogic = new PublicPrivateLookupTableLogic();
    ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    private final BeanItemContainer<ViewLookupDTO> availableResultsContainer = new BeanItemContainer<>(ViewLookupDTO.class);
    AdjustmentRateLogic arLogic = new AdjustmentRateLogic();
    String viewMasterSid = StringUtils.EMPTY;
    ViewLookupDTO dtoValue = new ViewLookupDTO();
    String viewValue=StringUtils.EMPTY;
    int userId;
    String detailsName=StringUtils.EMPTY;
    String viewType=StringUtils.EMPTY;
    String viewCategory=StringUtils.EMPTY;
    boolean selectFlag=false;
    public static final Logger LOGGER = Logger.getLogger(Trx7PrivatePublicLookup.class);
    
    public Trx7PrivatePublicLookup(String viewValue,int userId,String detailsName, String viewType, String viewCategory) {
        super();
        this.viewValue=viewValue;
        this.userId=userId;
        this.detailsName=detailsName;
        this.viewType=viewType;
        this.viewCategory = viewCategory;
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        setContent(Clara.create(getClass().getResourceAsStream("/adjustment_rate_config/private_public_lookup.xml"), this));
        configureFields();
    }

    private void configureFields() {
        try {
            setCaption("View Look Up");
            setDraggable(true);
            setClosable(false);
            center();
            setWidth("900px");
            setHeight("600px");
            setModal(true);
            setResizable(false);
            resultsTableLayoutEpl.addComponent(resultsTable);
            configureTable();
            dtoValue.setViewName(StringUtils.EMPTY);
            viewNameEpl.setValue(StringUtils.EMPTY);
            viewNameEpl.focus();
            
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void configureTable()  {
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setFilterGenerator(new ViewFilterGenerator());
        tableLogic.setContainerDataSource(availableResultsContainer);
        resultsTable.setSelectable(true);
        resultsTable.setWidth("870px");
        resultsTable.setHeight("290px");   
        resultsTable.setImmediate(true);
        resultsTable.setSizeFull();
        resultsTable.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setColumnAlignment("createdDate", ExtCustomTable.Align.CENTER);  
        resultsTable.setColumnAlignment("modifiedDate", ExtCustomTable.Align.CENTER);  
        resultsTable.setFilterBarVisible(true);  
        resultsTable.addStyleName("table-header-normal");
        resultsTable.addStyleName("filterbar");
        resultsTable.addStyleName("filtertable");
        if(viewCategory.equals("Customer") || viewCategory.equals("Customer Group")){
            resultsTable.setVisibleColumns(ARMUtils.INVENTORY_VIEW_LOOKUP_COLUMNS);
            resultsTable.setColumnHeaders(ARMUtils.INVENTORY_VIEW_LOOKUP_HEADERS);
        } else {
            resultsTable.setVisibleColumns(ARMUtils.VIEW_LOOKUP_COLUMNS);
            resultsTable.setColumnHeaders(ARMUtils.VIEW_LOOKUP_HEADERS);
        }
        resultsTable.setConverter("createdDate", new StringToDateConverter() {
                @Override
                public DateFormat getFormat(Locale locale) {
                    return new SimpleDateFormat("MM/dd/YYYY hh:mm:ss");
                }
        });
        resultsTable.setConverter("modifiedDate", new StringToDateConverter() {
                @Override
                public DateFormat getFormat(Locale locale) {
                    return new SimpleDateFormat("MM/dd/YYYY hh:mm:ss");
                }
        });
       
    }

    @UiHandler("searchBtn")
    public void searchButtonClick(Button.ClickEvent event) {
        try {
            if (StringUtils.EMPTY.equals(viewNameEpl.getValue())) {
                AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateErrorHeaderMessage(), ARMMessages.getSaveMessageID005());
            } else {
                availableResultsContainer.removeAllItems();
                dtoValue.setViewTypeFlag("privateView".equalsIgnoreCase(viewType));
                dtoValue.setCreatedBy(""+userId);
                dtoValue.setViewName(String.valueOf(viewNameEpl));
                dtoValue.setViewType(viewType);
                dtoValue.setDetailsValue(detailsName);
                tableLogic.configureSearchData(dtoValue, true,viewCategory);
                if(resultsTable.size()==0){
                AbstractNotificationUtils.getErrorNotification("Invalid Search", "There are no Views that match the search criteria.  Please try again.");
            }
            }
            

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @UiHandler("resetBtn")
    public void resetButtonClick(Button.ClickEvent event) {
        try {
            notifier.getOkCancelMessage(ARMMessages.getResetConfirmationMessage(), ARMMessages.getResetMessage_views());
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @UiHandler("selectBtn")
    public void selectButtonClick(Button.ClickEvent event) {
        try {
            if (resultsTable.size()!= 0 && resultsTable.getValue() != null) {
                ViewLookupDTO viewDTO = (ViewLookupDTO) resultsTable.getValue();
                viewDTO.setCheckFlag(true);
                setSelectFlag(true);
                setDtoValue(viewDTO);
                this.close();
            } else {
                setSelectFlag(false);
                AbstractNotificationUtils.getErrorNotification("No View Selected", "There is no view selected. Please select a saved view and try again.");
            }
        } catch (Exception e) {
           LOGGER.error(e);
        }
    }

    @UiHandler("closeBtn")
    public void closeButtonClick(Button.ClickEvent event) {
        try {
            setSelectFlag(false);
            this.close();
            viewNameEpl.setValue(StringUtils.EMPTY);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
    private final CustomNotification notifier = new CustomNotification();

    class CustomNotification extends AbstractNotificationUtils {

        @Override
        public void noMethod() {
        }

        @Override
        public void yesMethod() {
            viewNameEpl.setValue(StringUtils.EMPTY);
        }

    }

    public ViewLookupDTO getDtoValue() {
        return dtoValue;
    }

    public void setDtoValue(ViewLookupDTO dtoValue) {
        this.dtoValue = dtoValue;
    }
    public void reloadScreen(String viewValue,int userId,String detailsName,String viewName){
    availableResultsContainer.removeAllItems();
    viewNameEpl.setValue(StringUtils.EMPTY);
    this.viewValue=viewValue;
    this.userId=userId;
    this.detailsName=detailsName;
    this.viewType=viewName;
    }

    public boolean isSelectFlag() {
        return selectFlag;
    }

    public void setSelectFlag(boolean selectFlag) {
        this.selectFlag = selectFlag;
    }

}
