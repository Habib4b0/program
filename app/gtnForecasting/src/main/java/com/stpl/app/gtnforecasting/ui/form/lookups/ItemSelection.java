/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.lookups;

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
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author nandhakumar
 */
public class ItemSelection extends CustomComponent implements View {

    @UiField("businessUnitNo")
    public TextField businessUnitNo;
    @UiField("itemNo")
    public TextField itemNo;
    @UiField("itemName")
    public TextField itemName;
    @UiField("businessUnitName")
    public TextField businessUnitName;
    @UiField("theraputicClass")
    public ComboBox theraputicClass;
    @UiField("itemIdentifierType")
    public ComboBox itemIdentifierType;
    @UiField("brand")
    public ComboBox brand;
    @UiField("itemIdentifier")
    public TextField itemIdentifier;
    @UiField("searchBtn")
    public Button searchBtn;
    @UiField("resetBtn")
    public Button resetBtn;
    @UiField("availableItemsTableLayout")
    public VerticalLayout availableItemsTableLayout;
    @UiField("selectedItemsTableLayout")
    public VerticalLayout selectedItemsTableLayout;
    AlternateHistoryLogic logic = new AlternateHistoryLogic();
    private String screen_Name = "Item_Selection";
    @UiField("addBtn")
    public Button addBtn;
    @UiField("excelBtn")
    public Button excelBtn;
    @UiField("selectedExport")
    public Button selectedExport;
    @UiField("addAllBtn")
    public Button addAllBtn;

    CommonLogic commonLogic = new CommonLogic();
    AlternateHistoryTableLogic availableItemTableLoic = new AlternateHistoryTableLogic();
    ExtPagedTable availableItemsTable = new ExtPagedTable(availableItemTableLoic);
    AlternateHistoryTableLogic selectedItemsTableLoic = new AlternateHistoryTableLogic();
    ExtPagedTable selectedItemsTable = new ExtPagedTable(selectedItemsTableLoic);
    private BeanItemContainer<AlternateHistoryDTO> availableItemsContainer = new BeanItemContainer<>(AlternateHistoryDTO.class);
    private BeanItemContainer<AlternateHistoryDTO> selectedItemsContainer = new BeanItemContainer<>(AlternateHistoryDTO.class);
    public AlternateHistoryDTO altHistoryDTO = new AlternateHistoryDTO();
    private static final Logger LOGGER = Logger.getLogger(ItemSelection.class);
    public CustomFieldGroup itemSearchBinder = new CustomFieldGroup(new BeanItem<>(altHistoryDTO));
    Boolean contractExcelFlag = false;
    Boolean infoExcelFlag = false;
    protected final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    CommonUtil commonMsg = CommonUtil.getInstance();

    /**
     * The Constant Avilable Customer Header.
     */
    public final String[] availableItemsHeaders = new String[]{
        StringUtils.EMPTY, "Business Unit No", "Business Unit Name", "Theraputic Class", "Brand", "Item No", "Item Name", "Item Identifier Type", "Item Identifier"};
    public final Object[] availableItemsVisibleColumns = new Object[]{
        Constant.CHECK, "businessUnitNo", "businessUnitName", "theraputicClass", Constant.BRAND, Constant.ITEM_NO, "itemName", "itemIdentifierType", "itemIdentifier"};
    public final String[] availableItemHeaders1 = new String[]{
        StringUtils.EMPTY, "Business Unit No", "Business Unit Name", "Theraputic Class", "Brand", "Item No", "Item Name"};
    public final Object[] availableItemsColumns1 = new Object[]{
        Constant.CHECK, "businessUnitNo", "businessUnitName", "theraputicClass", Constant.BRAND, Constant.ITEM_NO, "itemName"};

    SessionDTO session;

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
            LOGGER.error(ex);
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
            public void noMethod() {
                // do nothing
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
                } catch (Exception ex) {
                    LOGGER.error(ex);
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
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                String checkValue = event.isChecked() ? "1" : "0";
                logic.updateTableOnAddorCheckAll(altHistoryDTO, null, session, false, checkValue);
                availableItemTableLoic.loadSetData(itemSearchBinder, altHistoryDTO, session, true);
            }
        });

        availableItemsTable.setFilterGenerator(new ExtFilterGenerator() {
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

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

            public void filterRemoved(Object propertyId) {
                return;
            }

            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                return;
            }

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

                java.util.logging.Logger.getLogger(CustomerSelection.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(ItemSelection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @UiHandler("addAllBtn")
    public void addAllBtnClick(Button.ClickEvent event) {
        LOGGER.debug("Entering inside Addall Button");
        selectedItemsContainer.removeAllItems();
        try {
            itemSearchBinder.commit();
        } catch (FieldGroup.CommitException ex) {
            java.util.logging.Logger.getLogger(ItemSelection.class.getName()).log(Level.SEVERE, null, ex);
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
            LOGGER.error(ex);
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
        } catch (Exception ex) {
            LOGGER.error(ex);
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
        } catch (Exception ex) {
            LOGGER.error(ex);
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
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }    

    
}
