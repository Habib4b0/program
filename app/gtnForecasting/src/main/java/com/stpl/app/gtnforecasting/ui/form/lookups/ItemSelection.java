/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.lookups;

import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dto.AlternateHistoryDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.salesprojection.logic.AlternateHistoryLogic;
import com.stpl.app.gtnforecasting.salesprojection.logic.tablelogic.AlternateHistoryTableLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.HelperListUtil;
import com.stpl.app.serviceUtils.Constants;
import static com.stpl.app.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.UI;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.DefaultFieldFactory;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author nandhakumar
 */
public class ItemSelection extends CustomComponent implements View {

    @UiField("businessUnitNo")
    private TextField businessUnitNo;
    
    @UiField("itemNo")
    private TextField itemNo;
    
    @UiField("itemName")
    private TextField itemName;
    
    @UiField("businessUnitName")
    private TextField businessUnitName;
    
    @UiField("theraputicClass")
    private ComboBox theraputicClass;
    
    @UiField("itemIdentifierType")
    protected ComboBox itemIdentifierType;
    
    @UiField("brand")
    private ComboBox brand;
    
    @UiField("itemIdentifier")
    private TextField itemIdentifier;
    
    @UiField("availableItemsTableLayout")
    private VerticalLayout availableItemsTableLayout;
    
    @UiField("selectedItemsTableLayout")
    private VerticalLayout selectedItemsTableLayout;
    
    private final AlternateHistoryLogic logic = new AlternateHistoryLogic();
    private final String screen_Name = "Item_Selection";
    
    @UiField("excelBtn")
    private Button excelBtn;
    
    @UiField("selectedExport")
    private Button selectedExport;
    
    private final CommonLogic commonLogic = new CommonLogic();
    private final AlternateHistoryTableLogic availableItemTableLoic = new AlternateHistoryTableLogic();
    private final ExtPagedTable availableItemsTable = new ExtPagedTable(availableItemTableLoic);
    private final AlternateHistoryTableLogic selectedItemsTableLoic = new AlternateHistoryTableLogic();
    private final ExtPagedTable selectedItemsTable = new ExtPagedTable(selectedItemsTableLoic);
    private final BeanItemContainer<AlternateHistoryDTO> availableItemsContainer = new BeanItemContainer<>(AlternateHistoryDTO.class);
    private final BeanItemContainer<AlternateHistoryDTO> selectedItemsContainer = new BeanItemContainer<>(AlternateHistoryDTO.class);
    private final AlternateHistoryDTO altHistoryDTO = new AlternateHistoryDTO();
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemSelection.class);
    private final CustomFieldGroup itemSearchBinder = new CustomFieldGroup(new BeanItem<>(altHistoryDTO));
    private Boolean contractExcelFlag = false;
    private Boolean infoExcelFlag = false;
    protected final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    private final CommonUtil commonMsg = CommonUtil.getInstance();

    private final String[] availableItemsHeaders = new String[]{
        StringUtils.EMPTY, "Business Unit No", "Business Unit Name", "Theraputic Class", "Brand", "Item No", "Item Name", "Item Identifier Type", "Item Identifier"};
    private final Object[] availableItemsVisibleColumns = new Object[]{
        Constant.CHECK, "businessUnitNo", "businessUnitName", "theraputicClass", Constant.BRAND, Constant.ITEM_NO, "itemName", "itemIdentifierType", "itemIdentifier"};
    private final String[] availableItemHeaders1 = new String[]{
        StringUtils.EMPTY, "Business Unit No", "Business Unit Name", "Theraputic Class", "Brand", "Item No", "Item Name"};
    private final Object[] availableItemsColumns1 = new Object[]{
        Constant.CHECK, "businessUnitNo", "businessUnitName", "theraputicClass", Constant.BRAND, Constant.ITEM_NO, "itemName"};

    private final SessionDTO session;

    public ItemSelection(SessionDTO session) {
        this.session = session;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/itemSelection.xml"), this));
        configureFields();
        configureTable();
    }

    protected void configureFields() {
        HelperListUtil helperListUtil = HelperListUtil.getInstance();
        helperListUtil.loadValuesWithListName("alternatehistory");
        try {
            commonMsg.loadComboBox(theraputicClass, "THERAPEUTIC_CLASS", false);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

        itemIdentifier.setEnabled(false);        
        itemIdentifierType.addItem(Constants.SELECT_ONE);
        itemIdentifierType.setNullSelectionAllowed(true);
        itemIdentifierType.setNullSelectionItemId(Constants.SELECT_ONE);
        itemIdentifierType.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                itemIdentifier.setEnabled(!(event.getProperty().getValue() == null));
                if(event.getProperty().getValue() == null){
                    itemIdentifier.setValue(StringUtils.EMPTY);
                }                
            }
        });
        
        brand.addItem(Constants.SELECT_ONE);
        brand.setNullSelectionAllowed(true);
        brand.setNullSelectionItemId(Constants.SELECT_ONE);
        List<String> brandList = commonLogic.getBrand();
        brand.addItems(brandList);

        excelBtn.setIcon(excelExportImage);
        selectedExport.setIcon(excelExportImage);

    }

    /**
     * Search Button Click Logic
     *
     * @param event
     */
    @UiHandler("resetBtn")
    public void resetBtnClick(Button.ClickEvent event) {
        LOGGER.debug("Entered inside reset method");
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                LOGGER.debug("Inside overriden method: Do nothing");
            }

            @Override
            public void yesMethod() {
                try {
                    businessUnitName.setValue(StringUtils.EMPTY);
                    businessUnitNo.setValue(StringUtils.EMPTY);
                    itemNo.setValue(StringUtils.EMPTY);
                    itemName.setValue(StringUtils.EMPTY);
                    itemIdentifier.setValue(StringUtils.EMPTY);
                    itemIdentifierType.setValue(null);
                    brand.setValue(null);
                    theraputicClass.setValue(null);
                } catch (Property.ReadOnlyException ex) {
                    LOGGER.error(ex.getMessage());
                }
            }
        }.getConfirmationMessage("Reset", "Are you sure you want to reset the page to default/previous values?");
        LOGGER.debug("Ending reset method");
    }

    public void configureTable() {

        availableItemsTableLayout.addComponent(availableItemsTable);
        HorizontalLayout hLayout;
        hLayout = availableItemTableLoic.createControls();
        availableItemsTableLayout.addComponent(hLayout);
        selectedItemsTableLayout.addComponent(selectedItemsTable);
        HorizontalLayout hLayout1;
        hLayout1 = selectedItemsTableLoic.createControls();
        selectedItemsTableLayout.addComponent(hLayout1);
        availableItemsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        availableItemsTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        availableItemsTable.setHeight(NumericConstants.FOUR_HUNDRED, Unit.PIXELS);
        availableItemsTable.setPageLength(NumericConstants.FIVE);
        availableItemsTable.setSortEnabled(false);
        availableItemTableLoic.setIsAvailable(Boolean.TRUE);
        availableItemTableLoic.setContainerDataSource(availableItemsContainer);
        availableItemsTable.setVisibleColumns(availableItemsVisibleColumns);
        availableItemsTable.setColumnHeaders(availableItemsHeaders);
        availableItemsTable.setFilterBarVisible(true);
        availableItemsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        availableItemsTable.setEditable(true);
        availableItemsTable.addStyleName("table-header-center");

        availableItemsTable.setColumnWidth(Constant.CHECK, NumericConstants.FIFTY);
        availableItemsTable.setColumnCheckBox(Constant.CHECK, true);
        availableItemsTable.setFilterFieldVisible(Constant.CHECK, false);
        availableItemsTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                String checkValue = event.isChecked() ? "1" : "0";
                logic.updateTableOnAddorCheckAll(altHistoryDTO, null, session, false, checkValue);
                availableItemTableLoic.loadSetData(itemSearchBinder, altHistoryDTO, session, true);
            }
        });

        availableItemsTable.setFilterGenerator(new ExtFilterGenerator() {
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
            public void filterRemoved(Object propertyId) {
                LOGGER.debug("Inside overriden method: Do nothing");
            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                LOGGER.debug("Inside overriden method: Do nothing");
            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            @Override
            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        Object[] availableColumn = availableItemsVisibleColumns;
        for (Object objColumn1 : availableColumn) {
            availableItemsTable.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
        }

        availableItemsTable.setTableFieldFactory(new DefaultFieldFactory() {
            private static final long serialVersionUID = 1L;

            @Override
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                if (propertyId.equals(Constant.CHECK)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    check.setEnabled(true);
                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        @Override
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            logic.updateAvailableTableCheckUnCheck(check.getValue(), session, StringUtils.EMPTY + ((AlternateHistoryDTO) itemId).getItemMasterSid());
                        }
                    });
                    return check;
                }

                return null;
            }
        });

        selectedItemsTableLoic.setIsAvailable(Boolean.FALSE);
        selectedItemsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        selectedItemsTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        selectedItemsTable.setHeight(NumericConstants.FOUR_HUNDRED, Unit.PIXELS);
        selectedItemsTable.setPageLength(NumericConstants.FIVE);
        selectedItemsTable.setSortEnabled(false);
        selectedItemsTableLoic.setContainerDataSource(selectedItemsContainer);
        selectedItemsTable.setVisibleColumns(availableItemsVisibleColumns);
        selectedItemsTable.setColumnHeaders(availableItemsHeaders);
        selectedItemsTable.setFilterBarVisible(true);
        selectedItemsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedItemsTable.setEditable(true);
        selectedItemsTable.addStyleName("table-header-center");
        selectedItemsTable.setColumnWidth(Constant.CHECK, NumericConstants.FIFTY);
        selectedItemsTable.setColumnCheckBox(Constant.CHECK, true);
        selectedItemsTable.setFilterFieldVisible(Constant.CHECK, false);
        selectedItemsTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                logic.updateSelectedTableOnCheckAll(event.isChecked(),session);
                selectedItemsTableLoic.loadSetData(itemSearchBinder, altHistoryDTO, session, false);
            }
        });

        Object[] selectedColumn = availableItemsVisibleColumns;
        for (Object objColumn1 : selectedColumn) {
            selectedItemsTable.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
        }

        selectedItemsTable.setTableFieldFactory(new DefaultFieldFactory() {
            private static final long serialVersionUID = 1L;

            @Override
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                if (propertyId.equals(Constant.CHECK)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    check.setEnabled(true);
                    check.setImmediate(true);
                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        @Override
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            logic.updateSelectedTableCheckUnCheck(check.getValue(), session, StringUtils.EMPTY + ((AlternateHistoryDTO) itemId).getItemMasterSid());
                        }
                    });
                    return check;
                }
                return null;
            }
        });

    }

    /**
     * Search Button Click Logic
     *
     * @param event
     */
    @UiHandler("searchBtn")
    public void searchBtnClick(Button.ClickEvent event) {
        LOGGER.debug("Entered search method");
        if (itemIdentifierType.getValue() != null && StringUtils.isBlank(itemIdentifier.getValue())) {
            AbstractNotificationUtils.getErrorNotification("Error",
                    "Please enter an Item Identifier Type and Item Identifier value.");           
        } else {        
        if (businessUnitNo.getValue().equals(StringUtils.EMPTY) && itemNo.getValue().equals(StringUtils.EMPTY) && itemName.getValue().equals(StringUtils.EMPTY) && theraputicClass.getValue() == null
                && businessUnitName.getValue().equals(StringUtils.EMPTY) && itemIdentifierType.getValue() == null && brand.getValue() == null && itemIdentifier.getValue().equals(StringUtils.EMPTY)) {
            AbstractNotificationUtils.getErrorNotification("Error",
                    "Please Enter Atleast one Search Criteria");
            return;
        } else {
            try {
                availableItemsContainer.removeAllItems();
                itemSearchBinder.commit();
                altHistoryDTO.setReset(Boolean.FALSE);
                altHistoryDTO.setItemName(String.valueOf(itemName.getValue()));
                altHistoryDTO.setItemNo(String.valueOf(itemNo.getValue()));
                altHistoryDTO.setScreenName(screen_Name);
                altHistoryDTO.setBusinessUnitName(String.valueOf(businessUnitName.getValue()));
                altHistoryDTO.setBusinessUnitNo(String.valueOf(businessUnitNo.getValue()));
                altHistoryDTO.setTheraputicClass(String.valueOf(theraputicClass.getValue()));
                altHistoryDTO.setBrand(String.valueOf(brand.getValue()));
                altHistoryDTO.setItemIdentifierType(String.valueOf(itemIdentifierType.getValue()));
                altHistoryDTO.setItemIdentifier(String.valueOf(itemIdentifier.getValue()));
                logic.updateAllAvailableCheckBox(session);
                if (!availableItemTableLoic.loadSetData(itemSearchBinder, altHistoryDTO, session, true)) {
                    AbstractNotificationUtils.getErrorNotification("No Results Found",
                            "There are no records that match the search criteria. Please try again");
                }

                if ((!altHistoryDTO.getItemIdentifier().equals("")) || (!"null".equals(altHistoryDTO.getItemIdentifierType()))) {
                    availableItemsTable.setVisibleColumns(availableItemsVisibleColumns);
                    availableItemsTable.setColumnHeaders(availableItemsHeaders);
                    selectedItemsTable.setVisibleColumns(availableItemsVisibleColumns);
                    selectedItemsTable.setColumnHeaders(availableItemsHeaders);
                } else if ((!altHistoryDTO.getBusinessUnitNo().equals("")) || (!altHistoryDTO.getItemNo().equals(""))
                        || (!altHistoryDTO.getItemName().equals("")) || (!altHistoryDTO.getBusinessUnitName().equals(""))
                        || (!"null".equals(altHistoryDTO.getTheraputicClass())) || (!"null".equals(altHistoryDTO.getBrand()))) {
                    availableItemsTable.setVisibleColumns(availableItemsColumns1);
                    availableItemsTable.setColumnHeaders(availableItemHeaders1);
                    selectedItemsTable.setVisibleColumns(availableItemsColumns1);
                    selectedItemsTable.setColumnHeaders(availableItemHeaders1);
                } else {
                    availableItemsTable.setVisibleColumns(availableItemsVisibleColumns);
                    availableItemsTable.setColumnHeaders(availableItemsHeaders);
                    selectedItemsTable.setVisibleColumns(availableItemsVisibleColumns);
                    selectedItemsTable.setColumnHeaders(availableItemsHeaders);
                }
                availableItemsTable.setColumnCheckBox(Constant.CHECK, true, false);
                if (itemIdentifierType.getValue() != null && selectedItemsTable.size() > 0) {
                    selectedItemsTableLoic.loadSetData(itemSearchBinder, altHistoryDTO, session, false);
                }
            } catch (FieldGroup.CommitException ex) {

                LoggerFactory.getLogger(CustomerSelection.class.getName()).error( StringUtils.EMPTY, ex);
            }
        }
        }
        LOGGER.debug("Ending search method");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Search Button Click Logic
     *
     * @param event
     */
    @UiHandler("addBtn")
    public void addBtnClick(Button.ClickEvent event) {
        try {
            LOGGER.debug("Entered inside addBtn method");
            if (logic.checkForAtleastOneCheckedItem(session, true)) {
                selectedItemsContainer.removeAllItems();
                itemSearchBinder.commit();
                altHistoryDTO.setReset(Boolean.FALSE);
                altHistoryDTO.setItemName(String.valueOf(itemName.getValue()));
                altHistoryDTO.setScreenName(screen_Name);
                logic.addItems(session);
                selectedItemsTableLoic.loadSetData(itemSearchBinder, altHistoryDTO,  session, false);
                availableItemTableLoic.loadSetData(itemSearchBinder, altHistoryDTO,  session, true);
                availableItemsTable.setColumnCheckBox(Constant.CHECK, true, false);
            } else {
                AbstractNotificationUtils.getErrorNotification("No Value Selected",
                        "Please select a Item from the list view to Add. Then try again.  ");
            }
            LOGGER.debug("Ending search method");
        } catch (FieldGroup.CommitException ex) {
            LoggerFactory.getLogger(ItemSelection.class.getName()).error( StringUtils.EMPTY, ex);
        }
    }

    @UiHandler("addAllBtn")
    public void addAllBtnClick(Button.ClickEvent event) {
        LOGGER.debug("Entering inside Addall Button");
        selectedItemsContainer.removeAllItems();
        try {
            itemSearchBinder.commit();
        } catch (FieldGroup.CommitException ex) {
            LoggerFactory.getLogger(ItemSelection.class.getName()).error( StringUtils.EMPTY, ex);
        }
        if (logic.checkForAtleastOneCheckedItem(session, true)) {
            altHistoryDTO.setReset(Boolean.FALSE);
            altHistoryDTO.setItemName(String.valueOf(itemName.getValue()));
            altHistoryDTO.setScreenName(screen_Name);
            altHistoryDTO.getSelectedProductSet().clear();
            logic.addItems(session);
            selectedItemsTableLoic.loadSetData(itemSearchBinder, altHistoryDTO, session, false);
            availableItemsTable.setColumnCheckBox(Constant.CHECK, true, false);
        }
        LOGGER.debug("Ending inside Addall Button");
    }

    @UiHandler("remove")
    public void removeBtnClick(Button.ClickEvent event) {
        try {
            LOGGER.debug("Entered inside Remove button method");
            if (logic.checkForAtleastOneCheckedItem(session,false)) {
                logic.removeItems(session);
                selectedItemsTableLoic.setFireData(altHistoryDTO, session);
                altHistoryDTO.getSelectedProductSet().clear();
                logic.getCheckedItemsFromTemptable(altHistoryDTO, null, 0, 0, session);
                selectedItemsTable.setColumnCheckBox(Constant.CHECK, true,false);
            } else {
                AbstractNotificationUtils.getErrorNotification("No Value Selected",
                        "Please select a Item from the list view to Remove. Then try again.");
            }
            LOGGER.debug("Ending Remove button method");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    /**
     * Remove all button listener
     *
     * @param event
     */
    @UiHandler("removeAllBtn")
    public void removeAllBtnBtnClick(Button.ClickEvent event) {
        LOGGER.debug("Entered inside removeAllBtn btn method");
        if (logic.checkForAtleastOneCheckedItem(session, false)) {
            logic.removeItems(session);
            altHistoryDTO.getSelectedProductSet().clear();
            selectedItemsTableLoic.setFireData(altHistoryDTO, session);
            selectedItemsTable.setColumnCheckBox(Constant.CHECK, true, false);
        }
        LOGGER.debug("Ending removeAllBtn btn method");
    }

    public boolean getSelectedItems() {
        boolean flag = false;
        String ccpDetailsId = StringUtils.EMPTY;
        Boolean first = true;
        for (Object items : selectedItemsContainer.getItemIds()) {
            Boolean checked = (Boolean) selectedItemsTable.getContainerProperty(items, Constant.CHECK).getValue();
            if (checked) {
                flag = true;
                String ccpId = String.valueOf(selectedItemsTable.getContainerProperty(items, "ccpDetailsId").getValue());
                if (first) {
                    ccpDetailsId = ccpId;
                    first = false;
                } else {
                    ccpDetailsId = ccpDetailsId + "," + ccpId;
                }
            }
        }
        session.setCcpDetailsId(ccpDetailsId);
        return flag;
    }

    /**
     * Excel Export Logic
     *
     * @param event
     */
    @UiHandler("excelBtn")
    public void availableItemsExport(Button.ClickEvent event) {
        try {
            contractExcelFlag = true;
            final int recordCount = logic.itemsSearchCount(altHistoryDTO, null, session);
            if (recordCount > 0) {
                createWorkSheet("Available_Items", availableItemsTable, recordCount);
            }
        } catch (SystemException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            contractExcelFlag = false;
        }
    }

    /**
     * Excel Export for Component Information
     *
     * @param event
     */
    @UiHandler("selectedExport")
    public void selectedCustomerExport(Button.ClickEvent event) {
        try {
            infoExcelFlag = true;
            final int recordCount = logic.getCheckedItemsCount( altHistoryDTO, null, session);
            if (recordCount > 0) {
                createWorkSheet("Selected_Items", selectedItemsTable, recordCount);
            }
        } catch (SystemException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            infoExcelFlag = false;
        }
    }

    /* This Method is used to create work sheet content */
    public void createWorkSheet(String moduleName, ExtCustomTable resultTable, int count) throws SystemException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
        List<String> header = Arrays.asList(resultTable.getColumnHeaders());
        List<String> list = header.subList(1, header.size());
        ExcelExportforBB.createWorkSheet(list.toArray(new String[list.size()]), count, this, UI.getCurrent(), moduleName);
    }

    /* This Method is used to write the table content in csv */
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) {
        try {
            if (end != 0) {
                if (contractExcelFlag) {
                    List<AlternateHistoryDTO> searchList = logic.searchItems(altHistoryDTO, null, start, end, session);
                    List<Object> visibleColumns = Arrays.asList(availableItemsTable.getVisibleColumns());
                    visibleColumns = visibleColumns.subList(1, visibleColumns.size());
                    ExcelExportforBB.createFileContent(visibleColumns.toArray(new String[visibleColumns.size()]), searchList, printWriter);
                } else if (infoExcelFlag) {
                    List<AlternateHistoryDTO> searchList = logic.getCheckedItemsFromTemptable( altHistoryDTO, null, start, end, session);
                    List<Object> visibleColumns = Arrays.asList(selectedItemsTable.getVisibleColumns());
                    visibleColumns = visibleColumns.subList(1, visibleColumns.size());
                    ExcelExportforBB.createFileContent(visibleColumns.toArray(new String[visibleColumns.size()]), searchList, printWriter);
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e) {
            LOGGER.error(e.getMessage());
        }
    }    

    
}
