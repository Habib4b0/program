/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.deductioncalendar.ui.form;

import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.abstractsearch.util.Message;
import com.stpl.app.global.abstractsearch.util.MessageUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.deductioncalendar.dto.ItemMasterGenerate;
import com.stpl.app.global.deductioncalendar.dto.SelectionDTO;
import com.stpl.app.global.deductioncalendar.logic.SelectionLogic;
import com.stpl.app.global.deductioncalendar.logic.tablelogic.ItemSelectionAvailableTableLogic;
import com.stpl.app.global.deductioncalendar.logic.tablelogic.ItemSelectionTableLogic;
import com.stpl.app.global.deductioncalendar.ui.util.HeaderUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.StplR2Exception;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.ui.lazyload.BrandContainer;
import com.stpl.app.ui.lazyload.BrandCriteria;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.ExcelExportUtil;
import com.stpl.app.util.HelperDTO;
import com.stpl.app.util.HelperUtils;
import static com.stpl.app.util.ResponsiveUtils.getResponsiveControls;
import com.stpl.app.util.TabNameUtil;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author sibi
 */
public class ItemSelection extends CustomComponent {

    private static final Logger LOGGER = Logger.getLogger(ItemSelection.class);

    CommonUtil commonMsg = CommonUtil.getInstance();

    @UiField("itemTypeDdlb")
    private ComboBox itemTypeDdlb;
    @UiField("searchBtn")
    private Button searchBtn;
    @UiField("resetBtn")
    private Button resetBtn;
    @UiField("addBtn")
    private Button addBtn;
    @UiField("addallBtn")
    private Button addallBtn;
    @UiField("removeBtn")
    private Button removeBtn;
    @UiField("removeallBtn")
    private Button removeallBtn;
    @UiField("exportBtn")
    private Button exportBtn;
    @UiField("exportButton")
    private Button exportButton;
    @UiField("item")
    private TextField item;
    @UiField("itemDesc")
    private TextField itemDesc;
    @UiField("strengthDdlb")
    private ComboBox strengthDdlb;
    @UiField("formDdlb")
    private ComboBox formDdlb;
    @UiField("brandDdlb")
    private ComboBox brandDdlb;

    @UiField("therapeuticclassDdlb")
    private ComboBox therapeuticclassDdlb;
    @UiField("availResultsTableLayout")
    protected VerticalLayout availResultsTableLayout;
    @UiField("selectedResultsTableLayout")
    protected VerticalLayout selectedResultsTableLayout;
    @UiField("itemSearchPanel")
    protected Panel itemSearchPanel;
    @UiField("searchHLayout")
    protected HorizontalLayout searchHLayout;
    @UiField("availableResultsPanel")
    protected Panel availableResultsPanel;
    @UiField("selectedResultsPanel")
    protected Panel selectedResultsPanel;
    @UiField("includeButtonLayout")
    protected HorizontalLayout includeButtonLayout;
    @UiField("excludeButtonLayout")
    protected HorizontalLayout excludeButtonLayout;
    /**
     * The Error Msg.
     */
    @UiField("errorMsg")
    public ErrorLabel errorMsg = new ErrorLabel();
    @UiField("cssLayout2")
    protected CssLayout cssLayout2;
    
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    
    DeductionCalendarForm deductionCalendarForm;
    
    com.stpl.app.util.CommonUIUtils commonUIUtils = new com.stpl.app.util.CommonUIUtils();
    /**
     * Bean container for available result table.
     */
    protected final BeanItemContainer<SelectionDTO> availableResultsContainer = new BeanItemContainer<>(SelectionDTO.class);
    /**
     * Bean container for Selected result table.
     */
    protected final BeanItemContainer<SelectionDTO> selectedResultsContainer = new BeanItemContainer<>(SelectionDTO.class);
    /**
     * Selection Table Logic
     */
    ItemSelectionAvailableTableLogic availableTableLogic = new ItemSelectionAvailableTableLogic();
    ItemSelectionTableLogic selectedTableLogic;
    SelectionLogic selectionLogic = new SelectionLogic();
    SessionDTO sessionDTO ;
    public Boolean excelEligible = false;
    /**
     * Available Customer ExtPagedTable
     */
    ExtPagedTable availableItemTable = new ExtPagedTable(availableTableLogic);
    /**
     * Selected Customer ExtPagedTable
     */
    ExtPagedTable selectedItemTable;
    /**
     * The binder.
     */
    private final ErrorfulFieldGroup binder;
    private HelperDTO dto = new HelperDTO(ConstantsUtils.SELECT_ONE);
    public Boolean isSelected=false;
    private final HeaderUtils headerUtils = new HeaderUtils();

    

    public ItemSelection(SessionDTO sessionDTO, DeductionCalendarForm deductionCalendarForm) {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/deduction_calendar/itemSelection.xml"), this));
        binder = getBinder();
        this.sessionDTO = sessionDTO;
        this.deductionCalendarForm=deductionCalendarForm;
        selectedTableLogic = new ItemSelectionTableLogic(sessionDTO);
        selectedItemTable = new ExtPagedTable(selectedTableLogic);
        init();
    }

    private void init() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = sessionDTO.getUserId();
            final Map<String, AppPermission> fieldDCHM = stplSecurity
                    .getFieldOrColumnPermission(userId, ConstantsUtils.DEDUCTION_CALENDAR+ConstantsUtils.COMMA+ConstantsUtils.ITEM_SELECTION,false);
            final Map<String, AppPermission> functionDCHM = stplSecurity.getBusinessFunctionPermission(userId, ConstantsUtils.DEDUCTION_CALENDAR+ConstantsUtils.COMMA+ConstantsUtils.ITEM_SELECTION);
            getResponsiveFirstTab(fieldDCHM);
            configureFields();
            getButtonPermission(functionDCHM);
            if(sessionDTO.getMode().equals("view")){
                disableFieldsOnView();
            }
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(ItemSelection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ItemSelection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    public ErrorfulFieldGroup getBinder() {
        final SelectionDTO bean = new SelectionDTO();
        final ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<>(bean));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
        return binder;
    }

    private void configureFields() {
        try {
            commonMsg.loadComboBox(itemTypeDdlb, "ITEM_TYPE", false);
            commonMsg.loadComboBox(therapeuticclassDdlb, "THERAPEUTIC_CLASS", false);
            commonMsg.loadComboBox(strengthDdlb, "STRENGTH", false);
            commonMsg.loadComboBox(formDdlb, "FORM", false);

            brandDdlb.setPageLength(NumericConstants.SEVEN);
            brandDdlb.setImmediate(true);
            brandDdlb.setNullSelectionAllowed(true);
            brandDdlb.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
            brandDdlb.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
            brandDdlb.markAsDirty();
            SelectionDTO selectionDTO = getBeanFromId(binder.getItemDataSource());
            final LazyContainer identifierTypeDescContainer = new LazyContainer(HelperDTO.class, new BrandContainer(selectionDTO.getBrandDdlb()), new BrandCriteria());
            identifierTypeDescContainer.setMinFilterLength(0);
            brandDdlb.setContainerDataSource(identifierTypeDescContainer);
            brandDdlb.select(dto);
            brandDdlb.setDescription(brandDdlb.getValue().toString());

            availResultsTableLayout.addComponent(availableItemTable);
            availResultsTableLayout.addComponent(getResponsiveControls(availableTableLogic.createControls()));
            availableTableLogic.setContainerDataSource(availableResultsContainer);
            availableItemTable.setSelectable(true);
            availableTableLogic.setPageLength(NumericConstants.TEN);
            availableTableLogic.sinkItemPerPageWithPageLength(false);
            final StplSecurity stplSecurity = new StplSecurity();

            final String userId = sessionDTO.getUserId();
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, ConstantsUtils.DEDUCTION_CALENDAR+ConstantsUtils.COMMA+ConstantsUtils.ITEM_SELECTION,false);
            String mode = sessionDTO.getMode();

            List<Object> resultList = commonUIUtils.getFieldsForSecurity(ConstantsUtils.DEDUCTION_CALENDAR, ConstantsUtils.ITEM_SELECTION);
            Object[] objColumn = headerUtils.itemColumns;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, objColumn, fieldIfpHM, mode.equals("Copy")?"Edit":mode);
            
            availableItemTable.setVisibleColumns(tableResultCustom.getObjResult());
            availableItemTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            availableItemTable.setFilterBarVisible(true);
            availableItemTable.setSizeFull();
            availableItemTable.setImmediate(true);
            availableItemTable.setPageLength(NumericConstants.TEN);
            availableItemTable.setMultiSelect(true);
            availableItemTable.setMultiSelectMode(MultiSelectMode.DEFAULT);
            availableItemTable.setFilterGenerator(new ItemMasterGenerate());
            availableItemTable.setFilterDecorator(new ExtDemoFilterDecorator());
            availableItemTable.addStyleName("filtertable");
            availableItemTable.addStyleName("table-header-normal");
            Arrays.asList(headerUtils.itemColumns);
            availableItemTable.setColumnAlignment(ConstantsUtils.ITEM_START_DATE, ExtPagedTable.Align.CENTER);
            availableItemTable.setColumnAlignment(ConstantsUtils.ITEM_END_DATE, ExtPagedTable.Align.CENTER);
            availableItemTable.setColumnAlignment(ConstantsUtils.PACKAGE_SIZE_INTRO_DATE, ExtPagedTable.Align.CENTER);
            availableItemTable.setColumnAlignment(ConstantsUtils.AUTHORIZED_GENERIC_START_DATE, ExtPagedTable.Align.CENTER);
            availableItemTable.setColumnAlignment(ConstantsUtils.AUTHORIZED_GENERIC_END_DATE, ExtPagedTable.Align.CENTER);
            availableItemTable.setColumnAlignment(ConstantsUtils.FIRST_SALE_DATE, ExtPagedTable.Align.CENTER);
            availableItemTable.setColumnAlignment(ConstantsUtils.MARKER_TERMINATION_DATE, ExtPagedTable.Align.CENTER);
            availableItemTable.setColumnAlignment("newFormulationStartDate", ExtPagedTable.Align.CENTER);
            availableItemTable.setColumnAlignment("newFormulationEndDate", ExtPagedTable.Align.CENTER);
            availableItemTable.setColumnAlignment(ConstantsUtils.PEDIATRIC_EXCLUSIVE_END_DATE, ExtPagedTable.Align.CENTER);
            availableItemTable.setColumnAlignment(ConstantsUtils.PEDIATRIC_EXCLUSIVE_START_DATE, ExtPagedTable.Align.CENTER);
            availableItemTable.setColumnAlignment("clottingFactorStartDate", ExtPagedTable.Align.CENTER);
            availableItemTable.setColumnAlignment("clottingFactorEndDate", ExtPagedTable.Align.CENTER);
            availableItemTable.setColumnAlignment(ConstantsUtils.DISCOUNT_DATE, ExtPagedTable.Align.CENTER);
            availableItemTable.setColumnAlignment(ConstantsUtils.LAST_LOT_EXP_DATE, ExtPagedTable.Align.CENTER);
            selectedResultsTableLayout.addComponent(selectedItemTable);
            selectedResultsTableLayout.addComponent(getResponsiveControls(selectedTableLogic.createControls()));
            selectedTableLogic.setContainerDataSource(selectedResultsContainer);
            selectedItemTable.setSelectable(true);
            selectedTableLogic.setPageLength(NumericConstants.TEN);
            selectedTableLogic.sinkItemPerPageWithPageLength(false);
            selectedItemTable.markAsDirty();
            selectedItemTable.setVisibleColumns(tableResultCustom.getObjResult());
            selectedItemTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            selectedItemTable.setMultiSelect(true);
            selectedItemTable.setMultiSelectMode(MultiSelectMode.DEFAULT);
            selectedItemTable.setFilterBarVisible(true);
            selectedItemTable.setSizeFull();
            selectedItemTable.setImmediate(true);
            selectedItemTable.setPageLength(NumericConstants.TEN);
            selectedItemTable.setFilterGenerator(new ItemMasterGenerate());
            selectedItemTable.setFilterDecorator(new ExtDemoFilterDecorator());
            selectedItemTable.addStyleName("filtertable");
            selectedItemTable.addStyleName("table-header-normal");
            selectedItemTable.setColumnAlignment(ConstantsUtils.ITEM_START_DATE, ExtPagedTable.Align.CENTER);
            selectedItemTable.setColumnAlignment(ConstantsUtils.ITEM_END_DATE, ExtPagedTable.Align.CENTER);
            selectedItemTable.setColumnAlignment(ConstantsUtils.PACKAGE_SIZE_INTRO_DATE, ExtPagedTable.Align.CENTER);
            selectedItemTable.setColumnAlignment(ConstantsUtils.AUTHORIZED_GENERIC_START_DATE, ExtPagedTable.Align.CENTER);
            selectedItemTable.setColumnAlignment(ConstantsUtils.AUTHORIZED_GENERIC_END_DATE, ExtPagedTable.Align.CENTER);
            selectedItemTable.setColumnAlignment(ConstantsUtils.FIRST_SALE_DATE, ExtPagedTable.Align.CENTER);
            selectedItemTable.setColumnAlignment(ConstantsUtils.MARKER_TERMINATION_DATE, ExtPagedTable.Align.CENTER);
            selectedItemTable.setColumnAlignment("newFormulationStartDate", ExtPagedTable.Align.CENTER);
            selectedItemTable.setColumnAlignment("newFormulationEndDate", ExtPagedTable.Align.CENTER);
            selectedItemTable.setColumnAlignment(ConstantsUtils.PEDIATRIC_EXCLUSIVE_END_DATE, ExtPagedTable.Align.CENTER);
            selectedItemTable.setColumnAlignment(ConstantsUtils.PEDIATRIC_EXCLUSIVE_START_DATE, ExtPagedTable.Align.CENTER);
            selectedItemTable.setColumnAlignment("clottingFactorStartDate", ExtPagedTable.Align.CENTER);
            selectedItemTable.setColumnAlignment("clottingFactorEndDate", ExtPagedTable.Align.CENTER);
            selectedItemTable.setColumnAlignment(ConstantsUtils.DISCOUNT_DATE, ExtPagedTable.Align.CENTER);
            selectedItemTable.setColumnAlignment(ConstantsUtils.LAST_LOT_EXP_DATE, ExtPagedTable.Align.CENTER);       
            exportBtn.setIcon(new ThemeResource(ConstantsUtils.ICONS_EXCEL_PNG));
            exportBtn.setIcon(new ThemeResource(ConstantsUtils.ICONS_EXCEL_PNG));
            exportBtn.setStyleName("link");
            exportBtn.setDescription("Export to excel");
            exportBtn.setIconAlternateText("Excel export");
            exportBtn.setHtmlContentAllowed(true);

            exportButton.setIcon(new ThemeResource(ConstantsUtils.ICONS_EXCEL_PNG));
            exportButton.setIcon(new ThemeResource(ConstantsUtils.ICONS_EXCEL_PNG));
            exportButton.setStyleName("link");
            exportButton.setDescription("Export to excel");
            exportButton.setIconAlternateText("Excel export");
            exportButton.setHtmlContentAllowed(true);
            
            dateConverter();

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
    
    private void getButtonPermission(Map<String, AppPermission> functionDCHM) {
        if (functionDCHM.get(ConstantsUtils.RESET_BUTTON) != null && !((AppPermission) functionDCHM.get(ConstantsUtils.RESET_BUTTON)).isFunctionFlag()) {
            resetBtn.setVisible(false);
        }else{
            resetButton();
        }
        if (functionDCHM.get(ConstantsUtils.ADD_BUTTON) != null && !((AppPermission) functionDCHM.get(ConstantsUtils.ADD_BUTTON)).isFunctionFlag()) {
            addBtn.setVisible(false);
        }else{
            add();
        }
        if (functionDCHM.get(ConstantsUtils.ADD_ALL_BUTTON) != null && !((AppPermission) functionDCHM.get(ConstantsUtils.ADD_ALL_BUTTON)).isFunctionFlag()) {
            addallBtn.setVisible(false);
        }else{
            addAll();
        }
        if (functionDCHM.get(ConstantsUtils.REMOVE_BUTTON) != null && !((AppPermission) functionDCHM.get(ConstantsUtils.REMOVE_BUTTON)).isFunctionFlag()) {
            removeBtn.setVisible(false);
        }else{
            removeBtn();
        }
        if (functionDCHM.get(ConstantsUtils.REMOVE_ALL_BUTTON) != null && !((AppPermission) functionDCHM.get(ConstantsUtils.REMOVE_ALL_BUTTON)).isFunctionFlag()) {
            removeallBtn.setVisible(false);
        }else{
            removeallBtn();
        }
    }

    @UiHandler("searchBtn")
    public void btnSearchLogic(Button.ClickEvent event) {

        try {
        binder.commit();
        excelEligible=true;
        SelectionDTO selectionDTO = getBeanFromId(binder.getItemDataSource());
        
        if ((selectionDTO.getItemTypeDdlb() != null && !ConstantUtil.SELECT_ONE.equals(selectionDTO.getItemTypeDdlb().getDescription()) && !selectionDTO.getItemTypeDdlb().getDescription().trim().isEmpty()) 
                    || (selectionDTO.getBrandDdlb() != null && !ConstantUtil.SELECT_ONE.equals(selectionDTO.getBrandDdlb().getDescription()) && !selectionDTO.getBrandDdlb().getDescription().trim().isEmpty()) 
                    || (selectionDTO.getFormDdlb() != null && !ConstantUtil.SELECT_ONE.equals(selectionDTO.getFormDdlb().getDescription()) && !selectionDTO.getFormDdlb().getDescription().trim().isEmpty()) 
                    || (selectionDTO.getStrengthDdlb() != null && !ConstantUtil.SELECT_ONE.equals(selectionDTO.getStrengthDdlb().getDescription()) && !selectionDTO.getStrengthDdlb().getDescription().trim().isEmpty()) 
                    || (selectionDTO.getTherapeuticclassDdlb() != null && !ConstantUtil.SELECT_ONE.equals(selectionDTO.getTherapeuticclassDdlb().getDescription()) && !selectionDTO.getTherapeuticclassDdlb().getDescription().trim().isEmpty()) 
                    || (selectionDTO.getItemDesc() != null && !selectionDTO.getItemDesc().trim().isEmpty()) 
                    || (selectionDTO.getItem() != null && !selectionDTO.getItem().trim().isEmpty())) {
            boolean searchValue=availableTableLogic.fireSetData(binder, false);
            if(!searchValue){
                    CommonUIUtils.successNotification(com.stpl.app.serviceUtils.ConstantUtil.NO_RESULTS_COMPLETED);
                    } else {
                    CommonUIUtils.successNotification(com.stpl.app.serviceUtils.ConstantUtil.SEARCH_COMPLETED);
                    }
        
        } else {
        final MessageBox msg = MessageBox.showPlain(Icon.WARN, MessageUtil.getMessage(Message.SEARCH_CRITERIA_HEADER), MessageUtil.getMessage(Message.SEARCH_CRITERIA_MESSAGE), new MessageBoxListener() {
              
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
        
        
        
        
        } catch (FieldGroup.CommitException ex) {
            java.util.logging.Logger.getLogger(ItemSelection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void resetButton() {
        resetBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
             MessageBox.showPlain(Icon.QUESTION, MessageUtil.getMessage(Message.DC_RESET_ALL_HEADER), "Are you sure you want to reset the Item Search group box back to its default settings", new MessageBoxListener() {
                    /**
                     * Click event. This event is thrown, when the button is
                     * clicked.
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                          resetBtnLogic();
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
       
            }
        });        
    }
    public SelectionDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof SelectionDTO) {
            targetItem = new BeanItem<>(
                    (SelectionDTO) obj);
        }
        return (SelectionDTO) targetItem.getBean();
    }
    protected void excelExportLogic() throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException,  InvocationTargetException {
        LOGGER.debug("Entering excelExportLogic");
        createWorkSheet();
        LOGGER.debug("Ending excelExportLogic");
    }

    private void createWorkSheet() throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException,  InvocationTargetException {
        LOGGER.debug("Entering createWorkSheet");
        final int recordCount = isSelected ? selectionLogic.getSelectedTableCount(sessionDTO,null):selectionLogic.getAvailableTableCount(binder,null,null);
        ExcelExportforBB.createWorkSheet(headerUtils.itemHeaders, recordCount, this, getUI(), TabNameUtil.ITEM_SELECTION_EXPORT);
        LOGGER.debug("Ending createWorkSheet");
    }
    
    @UiHandler("exportBtn")
    public void excelExportBtnClick(final Button.ClickEvent event) {
        isSelected=false;
        if(excelEligible)
            excelExport();
    }
    @UiHandler("exportButton")
    public void excelExportButtonClick(final Button.ClickEvent event) {
        isSelected=true;
        excelExport();
    }

    public void excelExport(){
        try {
            LOGGER.debug("Entering EXCEL Export Button Click :::: ");
            excelExportLogic();
            LOGGER.debug(" Ends  EXCEL Export Button Click ::::  ");

        } catch (SystemException sysException) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(sysException);
            LOGGER.error(errorMsg);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
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
        } catch (PortalException portException) {
            LOGGER.error(portException);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1011), new MessageBoxListener() {
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
        } catch (Exception exception) {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1011), new MessageBoxListener() {
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
            LOGGER.error(exception);

        }
    }
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) {
        SelectionDTO dto;
        final List<SelectionDTO> searchList = isSelected ? selectionLogic.getSelectedTableResult(sessionDTO, start, end, null, null) : selectionLogic.getAvailableTableResult(binder, start, end,null,null);
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        for (int rowCount = 0; rowCount < searchList.size(); rowCount++) {
            dto = searchList.get(rowCount);

            printWriter.print(ConstantsUtils.QUOTE + dto.getItemId() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getItem() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getItemCode() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getItemName() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getItemDesc() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + (dto.getItemStartDate() != null ? format.format(dto.getItemStartDate()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + (dto.getItemEndDate() != null ? format.format(dto.getItemEndDate()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getItemStatus() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getTherapeuticClass() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getBrand() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getForm() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getStrength() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getPackageSizeCode() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + (dto.getPackageSizeIntroDate() != null ? format.format(dto.getPackageSizeIntroDate()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getPsUP() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getManufacturerID() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getManufacturerNO() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getManufacturerName() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getLabelerCode() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getProductOrganizationKey() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + (dto.getAcquisitionDate() != null ? format.format(dto.getAcquisitionDate()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getAuthorizedGeneric() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + (dto.getAuthorizedGenericStartDate() != null ? format.format(dto.getAuthorizedGenericStartDate()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + (dto.getAuthorizedGenericEndDate() != null ? format.format(dto.getAuthorizedGenericEndDate()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + (dto.getFirstSaleDate() != null ? format.format(dto.getFirstSaleDate()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getItemTypeIndicator() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getItemClass() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getItemType() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + (dto.getMarketTerminationDate() != null ? format.format(dto.getMarketTerminationDate()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getNewFormulationIndicator() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getNewFormulation() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + (dto.getNewFormulationStartDate() != null ? format.format(dto.getNewFormulationStartDate()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + (dto.getNewFormulationEndDate() != null ? format.format(dto.getNewFormulationEndDate()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getPediatricExclusiveIndicator() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + (dto.getPediatricExclusiveEndDate() != null ? format.format(dto.getPediatricExclusiveEndDate()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + (dto.getPediatricExclusiveStartDate() != null ? format.format(dto.getPediatricExclusiveStartDate()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getClottingFactorIndicator() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + (dto.getClottingFactorStartDate() != null ? dto.getClottingFactorStartDate() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + (dto.getClottingFactorEndDate() != null ? dto.getClottingFactorEndDate() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getPrimaryUOM() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getSecondaryUOM() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getShelfLife() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getShelfLifeType() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getDualPricingIndicator() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getItemFamilyID() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getPsUDC1() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getPsUDC2() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getPsUDC3() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getPsUDC4() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getPsUDC5() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getPsUDC6() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getAcquiredAMP() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getAcquiredBAMP() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getPsOBRABAMP() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getPsDRA() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getDosesperUnit() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + (dto.getDiscontinuationDate() != null ? format.format(dto.getDiscontinuationDate()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + (dto.getLastLotExpirationDate() != null ? format.format(dto.getLastLotExpirationDate()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getPsNDC9() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getPsNDC8() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getDisplayBrand() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getInnovatorCode() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getBaselineAMP() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.println(ConstantsUtils.QUOTE + (dto.getBaseYearCPI() != null ? format.format(dto.getBaseYearCPI()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
        }
        }
    /**
     * logic for add button.
     *
     * @return the button
     * @throws StplR2Exception the stpl r2 exception
     */
    public void add() {

        addBtn.setImmediate(true);
        addBtn.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.debug("Error in Single record move button");
            }
        });
        addBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    binder.getErrorDisplay().clearError();
                    LOGGER.debug("Entering inside  Add Item Method");
                    final Set<SelectionDTO> selectionDTO = (Set<SelectionDTO>) availableItemTable.getValue();

                    if (selectionDTO.isEmpty()) {
                        AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_RECORD_SELECTED_HEADER), MessageUtil.getMessage(Message.NO_RECORD_SELECTED_MESSAGE));
                        return;
                    }
                    List<Integer> selectedItemSID= new ArrayList<>();
                    for (SelectionDTO selection : selectionDTO) {
                        if (selection != null && StringUtils.isNotBlank(selection.getItemSystemID())) {
                            selectedItemSID.add(Integer.valueOf(selection.getItemSystemID()));
                        }
                    }
                    if (!selectedItemSID.isEmpty()) {
                        deductionCalendarForm.setNeedRefresh(true);
                        addAllItemsButtonClick(false, selectedItemSID);
                        loadInEdit();
                    }
                    LOGGER.debug("Ending  MOVE_RIGHT Method ");
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        });
    }

    /**
     * Adds the Add all button.
     *
     * @return the button
     */
    public void addAll() {

        addallBtn.setImmediate(true);
        addallBtn.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error("Error in All button");
            }
        });
        addallBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("Entering inside  MOVE_ALL_RIGHT Method ");
                binder.getErrorDisplay().clearError();
                if (availableResultsContainer.size() > 0) {
                    deductionCalendarForm.setNeedRefresh(true);
                    addAllItemsButtonClick(true, null);

                } else {
                    AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_RECORD_SELECTED_HEADER), MessageUtil.getMessage(Message.NO_RECORD_SELECTED_MESSAGE));
                }
                LOGGER.debug("ENDING  MOVE_ALL_RIGHT Method ");
            }
        });
    }

    /**
     * Adds the removeBtn button.
     *
     * @return the button
     */
    public void removeBtn() {

        removeBtn.setImmediate(true);
        removeBtn.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error("Error in All button");
            }
        });
        removeBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("Entering inside  Remove  Method");
                try {
                    binder.getErrorDisplay().clearError();
                    final Set<SelectionDTO> selectionDTO = (Set<SelectionDTO>) selectedItemTable.getValue();

                    if (selectionDTO.isEmpty()) {
                        AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_RECORD_SELECTED_HEADER), MessageUtil.getMessage(Message.NO_RECORD_SELECTED_MESSAGE1));
                        return;
                    }
                    List<String> ids= new ArrayList<>();
                    for (SelectionDTO selection : selectionDTO) {
                        if (selection != null) {
                            ids.add(selection.getItemSystemID());
                        }
                    }
                    selectionLogic.removeItem(StringUtils.join(ids, ","), sessionDTO, false);
                    deductionCalendarForm.setNeedRefresh(true);
                    loadInEdit();
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
                LOGGER.debug("ENDING  Remove  Method ");
            }
        });
    }

    /**
     * Adds the removeBtn button.
     *
     * @return the button
     */
    public void removeallBtn() {

        removeallBtn.setImmediate(true);
        removeallBtn.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error("Error in Remove All Item button");
            }
        });
        removeallBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("Entering inside Remove All Item Method");
                try {
                    // method to save to temp table
                    selectionLogic.removeItem(HelperUtils.EMPTY, sessionDTO, true);
                                // here we are setting the lazyUtilDTO and getting inside the SelectedCompaniesContainer
                    // that is used to pass the  used id , sessionid
                    deductionCalendarForm.setNeedRefresh(true);
                    loadInEdit();
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(ItemSelection.class.getName()).log(Level.SEVERE, null, ex);
                }
                LOGGER.debug("ENDING  Remove All Item Method ");
            }
        });
    }
    
    public void loadInEdit() {
        selectedTableLogic.clearAll();
        selectedTableLogic.setRequiredCount(true);
        selectedTableLogic.setCurrentPage(1);
    }
    public void disableFieldsOnView(){
         resetBtn.setVisible(false);
         searchBtn.setVisible(false);
         addBtn.setVisible(false);
         addallBtn.setVisible(false);
         removeBtn.setVisible(false);
         removeallBtn.setVisible(false);
         
         itemSearchPanel.setVisible(false);
         searchHLayout.setVisible(false);
         includeButtonLayout.setVisible(false);
         availableResultsPanel.setVisible(false);
         selectedResultsPanel.setVisible(true);
     }
    
    private void getResponsiveFirstTab(final Map<String, AppPermission> fieldItemHM) {
        try {
            String mode = sessionDTO.getMode();
            List<Object> resultList = commonUIUtils.getFieldsForSecurity(ConstantsUtils.DEDUCTION_CALENDAR, ConstantsUtils.ITEM_SELECTION);
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout2, fieldItemHM, mode.equals("Copy")?"Edit":mode);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
    public  void resetBtnLogic(){
       LOGGER.debug("Entering reset Button in Deduction Calendar Form");
       binder.getErrorDisplay().clearError();
       binder.setItemDataSource(new BeanItem<>(new SelectionDTO()));
       brandDdlb.select(dto);
       excelEligible = false;
       availableItemTable.setFilterGenerator(new ItemMasterGenerate());
       availableItemTable.setFilterDecorator(new ExtDemoFilterDecorator());
       availableTableLogic.clearAll();
       availableTableLogic.getFilters().clear();
       LOGGER.debug("Ending reset Button in Deduction Calendar Form");
    }
     public String parseDateLogic(Object object) {  
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String date = formatter.format(object);
        return date;
    }
     
     private void addAllItemsButtonClick(boolean isAddAll, List<Integer> selectedItemSID) {
         selectionLogic.addAllItemsAndSaveToTempTable(sessionDTO,binder, isAddAll, selectedItemSID,availableTableLogic.getFilters());
         loadInEdit();
            }

    private void dateConverter() {
        
        availableItemTable.setConverter(ConstantsUtils.ITEM_START_DATE, new DateToStringConverter());
        availableItemTable.setConverter(ConstantsUtils.ITEM_END_DATE, new DateToStringConverter());
        availableItemTable.setConverter(ConstantsUtils.PACKAGE_SIZE_INTRO_DATE, new DateToStringConverter());
        availableItemTable.setConverter("acquisitionDate", new DateToStringConverter());
        availableItemTable.setConverter(ConstantsUtils.AUTHORIZED_GENERIC_START_DATE, new DateToStringConverter());
        availableItemTable.setConverter(ConstantsUtils.AUTHORIZED_GENERIC_END_DATE, new DateToStringConverter());
        availableItemTable.setConverter(ConstantsUtils.PEDIATRIC_EXCLUSIVE_START_DATE, new DateToStringConverter());
        availableItemTable.setConverter(ConstantsUtils.PEDIATRIC_EXCLUSIVE_END_DATE, new DateToStringConverter());
        availableItemTable.setConverter(ConstantsUtils.PACKAGE_SIZE_INTRO_DATE, new DateToStringConverter());
        availableItemTable.setConverter(ConstantsUtils.MARKER_TERMINATION_DATE, new DateToStringConverter());
        availableItemTable.setConverter(ConstantsUtils.LAST_LOT_EXP_DATE, new DateToStringConverter());
        availableItemTable.setConverter(ConstantsUtils.DISCOUNT_DATE, new DateToStringConverter());
        availableItemTable.setConverter(ConstantsUtils.FIRST_SALE_DATE, new DateToStringConverter());
        availableItemTable.setConverter("baseYearCPI", new DateToStringConverter());

        selectedItemTable.setConverter(ConstantsUtils.ITEM_START_DATE, new DateToStringConverter());
        selectedItemTable.setConverter(ConstantsUtils.ITEM_END_DATE, new DateToStringConverter());
        selectedItemTable.setConverter(ConstantsUtils.PACKAGE_SIZE_INTRO_DATE, new DateToStringConverter());
        selectedItemTable.setConverter("acquisitionDate", new DateToStringConverter());
        selectedItemTable.setConverter(ConstantsUtils.AUTHORIZED_GENERIC_START_DATE, new DateToStringConverter());
        selectedItemTable.setConverter(ConstantsUtils.AUTHORIZED_GENERIC_END_DATE, new DateToStringConverter());
        selectedItemTable.setConverter(ConstantsUtils.PEDIATRIC_EXCLUSIVE_START_DATE, new DateToStringConverter());
        selectedItemTable.setConverter(ConstantsUtils.PEDIATRIC_EXCLUSIVE_END_DATE, new DateToStringConverter());
        selectedItemTable.setConverter(ConstantsUtils.PACKAGE_SIZE_INTRO_DATE, new DateToStringConverter());
        selectedItemTable.setConverter(ConstantsUtils.MARKER_TERMINATION_DATE, new DateToStringConverter());
        selectedItemTable.setConverter(ConstantsUtils.LAST_LOT_EXP_DATE, new DateToStringConverter());
        selectedItemTable.setConverter(ConstantsUtils.DISCOUNT_DATE, new DateToStringConverter());
        selectedItemTable.setConverter(ConstantsUtils.FIRST_SALE_DATE, new DateToStringConverter());
        selectedItemTable.setConverter("baseYearCPI", new DateToStringConverter());
    }
}
