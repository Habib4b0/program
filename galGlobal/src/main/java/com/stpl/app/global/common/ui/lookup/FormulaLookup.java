/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.common.ui.lookup;

import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.abstractsearch.util.Message;
import com.stpl.app.global.abstractsearch.util.MessageUtil;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.rebateschedule.dto.RSFilterGenerate;
import com.stpl.app.global.rebateschedule.dto.RSFormulaDTO;
import com.stpl.app.global.rebateschedule.logic.RebateScheduleLogic;
import com.stpl.app.global.rebateschedule.logic.tablelogic.FormulaTableLogic;
import com.stpl.app.global.rebateschedule.util.RsUtils;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.ResponsiveUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
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
 *
 * @author Sibi
 */
public class FormulaLookup extends Window {

    private static final Logger LOGGER = Logger.getLogger(FormulaLookup.class);

    @UiField("formulaIDLabel")
    private Label formulaIDLabel;

    @UiField("formulaNoLabel")
    private Label formulaNoLabel;

    @UiField("formulaNameLabel")
    private Label formulaNameLabel;    

    @UiField("formulaID")
    private TextField formulaID;

    @UiField("formulaNo")
    private TextField formulaNo;

    @UiField("formulaName")
    private TextField formulaName;      

    @UiField("searchBtn")
    private Button searchBtn;

    @UiField("resetBtn")
    private Button resetBtn;

    @UiField("selectBtn")
    private Button selectBtn;

    @UiField("closeBtn")
    private Button closeBtn;

    @UiField("tableLayout")
    private VerticalLayout tableLayout;
    HorizontalLayout controlLayout=new HorizontalLayout();
    
    @UiField("fdTableLayout")
    VerticalLayout fdTableLayout;
    HorizontalLayout fdControlLayout=new HorizontalLayout();
    
    FormulaTableLogic tableLogic = new FormulaTableLogic();

    private final ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    
    BeanItemContainer<RSFormulaDTO> resultsContainer = new BeanItemContainer<>(RSFormulaDTO.class);    
    
    private RSFormulaDTO rsFormulaDTO = new RSFormulaDTO();
    
    CustomFieldGroup binder;
    
    CommonUtil commonUtil = CommonUtil.getInstance();    
    
    private boolean isSelected;
    
    String itemId;
    
    @UiField("resultsPanel")
    Panel resultsPanel;
    
    private final ExtFilterTable detailsTable = new ExtFilterTable();
    
    private BeanItemContainer<RSFormulaDTO> detailsContainer = new BeanItemContainer<RSFormulaDTO>(RSFormulaDTO.class);
    
    @UiField("formulaType")
    private ComboBox formulaType;
    
    /**
     * The rebate schedule logic.
     */
    private RebateScheduleLogic rebateLogic = new RebateScheduleLogic();
    
    HelperDTO dto = new HelperDTO(0, Constants.SELECT_ONE);
    
    @UiField("detailsBtn")
    private Button detailsBtn;
    
    CustomTextField tierFormula;
    /**
     * Default Constructor to load the formula for Mass Update
     * @throws Exception 
     */
    public FormulaLookup() throws Exception {
        this.itemId = "%";
        init();
    }
    
    public FormulaLookup(CustomTextField tierFormula) throws Exception {
        this.tierFormula = tierFormula;
    }

    /**
     * Constructor with arguments to load the formula for Line level item in Rebate Setup tab.
     * @param itemId
     * @throws Exception 
     */
    public FormulaLookup(String itemId) throws Exception {
        this.itemId = itemId;
        rsFormulaDTO.setItemId(itemId);
        init();
    }

    /**
     *  Configures the Window
     */
    private void init() throws Exception {
        this.setModal(true);
        this.setClosable(true);
        this.center();
        this.setWidth("800px");        
        setCaption("Formula No Lookup");
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        setContent(Clara.create(getClass().getResourceAsStream("/clara/rebateschedule/formula-lookup.xml"), this));        
        configureBinder();        
        configureTable();
    }

    
    
    /**
     * Binds the search criteria field values to the DTO.
     */
    private void configureBinder() {
        binder = new CustomFieldGroup(new BeanItem(rsFormulaDTO));
        binder.bindMemberFields(this);        
    }
    
    /**
     * Configures the table logic and result table.
     */
    private void configureTable() throws SystemException {
        tableLogic.setContainerDataSource(resultsContainer);
        tableLogic.setPageLength(10);
        tableLogic.sinkItemPerPageWithPageLength(false);    
        resultsTable.setImmediate(true);
        resultsTable.setSizeFull();
        resultsTable.setVisibleColumns(RsUtils.FORMULA_LOOKUP);
        resultsTable.setColumnHeaders(RsUtils.FORMULA_LOOKUP_HEADER);    
        resultsTable.setFilterBarVisible(true);
        resultsTable.setSelectable(true);
        tableLayout.addComponent(resultsTable);
        ResponsiveUtils.getResponsiveControls(tableLogic.createControls(), controlLayout);  
        tableLayout.addComponent(controlLayout);
        fdTableLayout.addComponent(detailsTable);
        
        detailsTable.setHeight("500px");
        detailsTable.setWidth("512px");
        detailsTable.setContainerDataSource(detailsContainer);
        detailsTable.setImmediate(true);
        detailsTable.setVisibleColumns("formula");
        detailsTable.setColumnHeaders("");
        
        resultsTable.addStyleName(ConstantsUtils.FILTER_BAR);
        resultsTable.setFilterGenerator(new RSFilterGenerate());
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        
        BeanItemContainer<HelperDTO> container = new BeanItemContainer<HelperDTO>(HelperDTO.class);
        container.addAll(rebateLogic.getHelperIdDetails(RsUtils.REBATE_PLAN_FORMULA_TYPE));
        formulaType.setContainerDataSource(container);
        formulaType.setNullSelectionItemId(dto);
       
        formulaType.setNullSelectionAllowed(true);
        formulaType.setImmediate(true);
        formulaType.setItemCaptionPropertyId("description");
        formulaType.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Logic for value change event.
             *
             * @param event
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && StringUtils.EMPTY.equals(event.getProperty().getValue())) {
                    formulaType.select(new HelperDTO(0, Constants.SELECT_ONE));
                }
                formulaNo.setValue("");
                formulaID.setValue("");
                formulaName.setValue("");
            }
        });
        
        detailsBtn.addClickListener(new Button.ClickListener() {
            /**
             * Method used for btnAllPopulate logic
             */
            public void buttonClick(final Button.ClickEvent event) {

                if (resultsTable.getValue() != null) {
                    detailsLogic(resultsTable.getValue());

                } else {
                    AbstractNotificationUtils.getWarningNotification("Details error", "Please select a record.");
                }

            }

        });

    }
    
    private void detailsLogic(Object value) {
        RSFormulaDTO dto = (RSFormulaDTO) value;
        detailsContainer.removeAllItems();
        detailsContainer.addAll(rebateLogic.loadRSDetails(dto));
    }
    
   /**
    * Searches for the formula with the entered criteria.
    * @param event 
    */
    @UiHandler("searchBtn")
    public void searchLogic(Button.ClickEvent event) {
        try {
            String formulaNo = binder.getField("formulaNo").getValue().toString();
            String formulaName = binder.getField("formulaName").getValue().toString();
            String formulaType = (binder.getField("formulaType").getValue() != null) ? binder.getField("formulaType").getValue().toString() : StringUtils.EMPTY;
            String formulaid = binder.getField("formulaID").getValue().toString();
            if (StringUtils.isBlank(formulaNo) && StringUtils.isBlank(formulaName) && StringUtils.isBlank(formulaid)
                    && StringUtils.isBlank(formulaType)) {
                final MessageBox msg = MessageBox.showPlain(Icon.WARN, MessageUtil.getMessage(Message.SEARCH_CRITERIA_HEADER), MessageUtil.getMessage(Message.SEARCH_CRITERIA_MESSAGE), new MessageBoxListener() {

                    /**
                     * The method is triggered when a button of the message box
                     * is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        // Do Nothing
                    }
                }, ButtonId.OK);
                msg.getButton(ButtonId.OK).focus();
            } else {
                
                binder.commit();
                tableLogic.setSearchData(rsFormulaDTO,binder);
                tableLogic.setCurrentPage(1);
            resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
                if (tableLogic.isResultsEmpty()) {
                    CommonUIUtils.successNotification(ConstantUtil.NO_RESULTS_COMPLETED);
                } else {
                    CommonUIUtils.successNotification(ConstantUtil.SEARCH_COMPLETED);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
    
    /**
     * Resets the search fields.
     * @param event 
     */
    @UiHandler("resetBtn")
    public void resetLogic(Button.ClickEvent event){
          MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to reset the Search Criteria" + " ?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {
                            try {
                                rsFormulaDTO = new RSFormulaDTO();
                                rsFormulaDTO.setItemId(itemId);
                                binder.setItemDataSource(new BeanItem(rsFormulaDTO));
                                resultsTable.removeAllItems();
                                tableLogic.clearAll();
                            } catch (Exception exception) {
                                final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1006), new MessageBoxListener() {
                                    /**
                                     * The method is triggered when a button of
                                     * the message box is pressed .
                                     *
                                     * @param buttonId The buttonId of the
                                     * pressed button.
                                     */
                                    @SuppressWarnings("PMD")
                                    public void buttonClicked(final ButtonId buttonId) {
                                        // Do Nothing              
                                    }
                                }, ButtonId.OK);
                                msg.getButton(ButtonId.OK).focus();
                                LOGGER.error(exception);

                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
    }
    
    /**
     * Selects the item in the table and closes the window.
     * @param event 
     */
    @UiHandler("selectBtn")
    public void selectLogic(Button.ClickEvent event) {
        if (null != resultsTable.getValue()) {
            final RSFormulaDTO itemId = (RSFormulaDTO) resultsTable.getValue();
            setRsFormulaDTO(itemId);
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
     * 
     * @return 
     */
    public RSFormulaDTO getSelectedItem(){
      return getBeanFromId(resultsTable.getValue());  
    }
    
    /**
     * 
     * @param obj
     * @return 
     */   
    public RSFormulaDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof RSFormulaDTO) {
            targetItem = new BeanItem<RSFormulaDTO>(
                    (RSFormulaDTO) obj);
        }
        return (RSFormulaDTO) targetItem.getBean();
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

    public RSFormulaDTO getRsFormulaDTO() {
        return rsFormulaDTO;
    }

    public void setRsFormulaDTO(RSFormulaDTO rsFormulaDTO) {
        this.rsFormulaDTO = rsFormulaDTO;
    }

    
    
}
