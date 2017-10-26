/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.priceschedule.ui.form;

import com.stpl.app.global.abstractsearch.util.UIUtils;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.netsalesformula.dto.NsfFilterGenerator;
import com.stpl.app.global.priceschedule.dto.PSNepFormulaDTO;
import com.stpl.app.global.priceschedule.logic.NepFormulaTableLogic;
import com.stpl.app.global.priceschedule.util.PsUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.QueryUtils;
import com.stpl.app.util.ResponsiveUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author shyam.d
 */
public class NEPFormulaLookUp extends Window {

    private static final Logger LOGGER = Logger.getLogger(NEPFormulaLookUp.class);
    
    @UiField("tablelayout")
    private VerticalLayout tablelayout;
    
    @UiField("controlLayout")
    private HorizontalLayout controlLayout;   
    
    @UiField("searchBtn")
    private Button searchBtn;

    @UiField("closeBtn")
    private Button closeBtn;
    
    @UiField("nepFormulaType")
    private ComboBox nepFormulaType;
    @UiField("nepFormulaName")
    private TextField nepFormulaName;
    @UiField("nepFormulaNo")
    private TextField nepFormulaNo;
    @UiField("nepFormulaID")
    private TextField nepFormulaID;
    @UiField("resetBtn")
    private Button resetBtn;
    NepFormulaTableLogic tableLogic = new NepFormulaTableLogic();

    private final ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    
    BeanItemContainer<PSNepFormulaDTO> resultsContainer = new BeanItemContainer<>(PSNepFormulaDTO.class);    

    private PSNepFormulaDTO psNepFormulaDTO = new PSNepFormulaDTO();
    
    ErrorfulFieldGroup binder= new ErrorfulFieldGroup(new BeanItem(new PSNepFormulaDTO()));
    
    private boolean isSelected;
    
    /** The common util. */
    private CommonUtil commonUtil = CommonUtil.getInstance();
     HelperListUtil helperListUtil = HelperListUtil.getInstance();
     boolean flag = false;
     
    /**
     * Constructor with arguments to load the formula for Line level item in Rebate Setup tab.
     * @param itemId
     * @throws Exception 
     */
    public NEPFormulaLookUp(String itemId) {
if("NEP".equals(itemId)){
    flag = true;
}
        init();
    }
    
    /**
     *  Configures the Window
     */
    private void init() {
        this.setModal(true);
        this.setClosable(true);
        this.center();
        this.setWidth("800px");        
        setCaption("Net Sales Formula Lookup");
        addStyleName(ConstantsUtils.BOOTSTRAP);
        addStyleName(ConstantsUtils.BOOTSTRAP_BB);
        setContent(Clara.create(getClass().getResourceAsStream("/declarativeui/priceschedule/nep-formula-lookup.xml"), this));        
        configureBinder();  
        configureFields();
        configureTable();
    }
    /**
     * Binds the search criteria field values to the DTO.
     */
    private void configureBinder() {
        binder.bindMemberFields(this);   
        binder.setBuffered(true);
    }
    
    private void configureFields() {
        
        commonUtil.loadComboBox(nepFormulaType, UIUtils.NS_FORMULA_TYPE, false);
        if (flag) {
            List<HelperDTO> defaultValue = helperListUtil.getListNameMap().get(UIUtils.NS_FORMULA_TYPE);
            for (HelperDTO helperDto : defaultValue) {
                if (!"Contract Deduction".equalsIgnoreCase(helperDto.getDescription()) && helperDto.getId() != 0) {
                    nepFormulaType.removeItem(helperDto);
                }
            }
        }
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
        resultsTable.setVisibleColumns(PsUtils.NEP_FORMULA_LOOKUP);
        resultsTable.setColumnHeaders(PsUtils.NEP_FORMULA_LOOKUP_HEADER);
        resultsTable.setFilterBarVisible(true);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setFilterGenerator(new NsfFilterGenerator());
        resultsTable.setSelectable(true);
        tablelayout.addComponent(resultsTable);
        ResponsiveUtils.getResponsiveControls(tableLogic.createControls(), controlLayout);
        for (Object propertyId : resultsTable.getVisibleColumns()) {
                resultsTable.setColumnWidth(propertyId, NumericConstants.TWO_HUNDRED);
            }
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
     * Returns the selected Item from the table.
     * 
     * @return 
     */
    public PSNepFormulaDTO getSelectedItem(){
      return getBeanFromId(resultsTable.getValue());  
    }
    /**
     * 
     * @param obj
     * @return 
     */   
    public PSNepFormulaDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof PSNepFormulaDTO) {
            targetItem = new BeanItem<PSNepFormulaDTO>(
                    (PSNepFormulaDTO) obj);
        }
        return (PSNepFormulaDTO) targetItem.getBean();
    }
    /**
    * Searches for the formula with the entered criteria.
    * @param event 
    */
    @UiHandler("searchBtn")
    public void searchLogic(Button.ClickEvent event){
        try {            
            binder.commit();
            tableLogic.setSearchData(binder);            
            tableLogic.setCurrentPage(1);            
        } catch (Exception ex) {
            LOGGER.error(ex);
        }        
    }
    @UiHandler("selectBtn")
    public void selectBtn(Button.ClickEvent event) {
        if (resultsTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("No formula Selected",
                    "You have not selected a formula. Please click a formula and try again.");
        } else {
            final PSNepFormulaDTO itemId = (PSNepFormulaDTO) resultsTable.getValue();
            List resultList = QueryUtils.querySelect(new ArrayList(){{add(itemId.getNepFormulaSystemID());}}, "net-sales-ppa-validation", null);
            boolean ppaCheck=resultList.get(0)!=null && "1".equals(String.valueOf(resultList.get(0)));
            if (!flag && !ppaCheck) {
                AbstractNotificationUtils.getErrorNotification(ConstantsUtils.ERROR,
                    "Only formulas that have a Contract Selection of 'Existing Contract' \n"
                            + "and a Formula Type of 'Contract Deduction' may be used. \n"
                            + "Please select another formula and try again.");
            }else{
                setNepFormulaDTO(itemId);
                close();
            }
            
        }
    }
    public PSNepFormulaDTO getNepFormulaDTO() {
        return psNepFormulaDTO;
    }

    public void setNepFormulaDTO(PSNepFormulaDTO groupDTO) {
        this.psNepFormulaDTO = groupDTO;
    }
    @UiHandler("closeBtn")
    public void closeButtonLogic(Button.ClickEvent event) {
        close();
    }
    @UiHandler("resetBtn")
    public void resetButtonLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
                    public void noMethod() {
                        // do nothing
                    }

                    @Override
                    /**
                     * The method is triggered when Yes button of the message
                     * box is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    public void yesMethod() {
			tableLogic.clearAll();
                        tableLogic.setReset(true);
                        tableLogic.setRequiredCount(true);
                        tableLogic.setCurrentPage(1);
                        nepFormulaName.setValue(StringUtils.EMPTY);
                        nepFormulaNo.setValue(StringUtils.EMPTY);
                        nepFormulaID.setValue(StringUtils.EMPTY);
                        try {
                            commonUtil.loadComboBox(nepFormulaType, UIUtils.NS_FORMULA_TYPE, false);
                        } catch (Exception ex) {
                            java.util.logging.Logger.getLogger(NEPFormulaLookUp.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }.getConfirmationMessage("Reset Confirmation", "Are you sure you want to reset the page to default values?");
    }
}
