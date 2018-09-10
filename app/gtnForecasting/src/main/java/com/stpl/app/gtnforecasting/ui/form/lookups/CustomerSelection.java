
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.lookups;

import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dto.AlternateHistoryDTO;
import com.stpl.app.gtnforecasting.salesprojection.logic.AlternateHistoryLogic;
import com.stpl.app.gtnforecasting.salesprojection.logic.tablelogic.AlternateHistoryTableLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.BooleanConstant;
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
public class CustomerSelection extends CustomComponent implements View {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerSelection.class);
    
    

    private SessionDTO session;
    
    @UiField("contractHolder")
    private  TextField contractHolder;
    
    @UiField("contractNo")
    private  TextField contractNo;
    
    @UiField("customerNo")
    private  TextField customerNo;
    
    @UiField("marketType")
    private  ComboBox marketType;
    
    @UiField("contractName")
    private  TextField contractName;
    
    @UiField("customerName")
    private  TextField customerName;
    
    @UiField("availableCustomerTableLayout")
    private  VerticalLayout availableCustomerTableLayout;

    @UiField("selectedCustomerTableLayout")
    private  VerticalLayout selectedCustomerTableLayout;
    private AlternateHistoryLogic logic = new AlternateHistoryLogic();
    private String screenName = "Customer_Selection";
    
    @UiField("excelBtn")
    private  Button excelBtn;
    
    @UiField("selectedExport")
    private  Button selectedExport;
    private boolean contractExcelFlag = false;
    private boolean infoExcelFlag = false;

    private AlternateHistoryTableLogic availablecustomerTableLoic = new AlternateHistoryTableLogic();
    private ExtPagedTable availableCustomerTable = new ExtPagedTable(availablecustomerTableLoic);
    private AlternateHistoryTableLogic selectedcustomerTableLoic = new AlternateHistoryTableLogic();
    private ExtPagedTable selectedCustomerTable = new ExtPagedTable(selectedcustomerTableLoic);
    private BeanItemContainer<AlternateHistoryDTO> availableCustomerContainer = new BeanItemContainer<>(AlternateHistoryDTO.class);
    private BeanItemContainer<AlternateHistoryDTO> selectedCustomerContainer = new BeanItemContainer<>(AlternateHistoryDTO.class);
    private AlternateHistoryDTO altHistoryDTO = new AlternateHistoryDTO();
    private CustomFieldGroup customerSearchBinder = new CustomFieldGroup(new BeanItem<>(altHistoryDTO));
    private CommonUtil commonUtil = CommonUtil.getInstance();
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());

    public final String[] availableCustomerHeader = new String[]{
        StringUtils.EMPTY, "Contract Holder", "Market Type", "Contract No", "Contract Name", "Customer No", "Customer Name"};
    public final Object[] availableCustomerColumns = new Object[]{
        Constant.CHECK, "contractHolder", Constant.MARKET_TYPE, "contractNo", "contractName", Constant.CUSTOMER_NO, Constant.CUSTOMER_NAME};

    public CustomerSelection(SessionDTO session) {
        try {
            this.session = session;
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/customerSelection.xml"), this));
            configureFields();
            configureTable();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        LOGGER.debug("Inside overriden method- Do nothing");
    }

    /**
     * Search Button Click Logic
     *
     * @param event
     */
    @UiHandler("searchBtn")
    public void searchBtnClick(Button.ClickEvent event) {
        LOGGER.debug("Entered search method");
        if (contractHolder.getValue().equals(StringUtils.EMPTY) && contractNo.getValue().equals(StringUtils.EMPTY)
                && customerNo.getValue().equals(StringUtils.EMPTY) && marketType.getValue() == null
                && contractName.getValue().equals(StringUtils.EMPTY) && customerName.getValue().equals(StringUtils.EMPTY)) {
            AbstractNotificationUtils.getErrorNotification("Error",
                    "Please Enter Atleast one Search Criteria");
            return;
        } else {
            try {
                availableCustomerContainer.removeAllItems();
                customerSearchBinder.commit();
                altHistoryDTO.setReset(Boolean.FALSE);
                altHistoryDTO.setCustomerNo(String.valueOf(customerNo.getValue()));
                altHistoryDTO.setContractHolder(String.valueOf(contractHolder.getValue()));
                altHistoryDTO.setContractNo(String.valueOf(contractNo.getValue()));
                if (marketType.getValue() != null) {
                    HelperDTO dto = (HelperDTO) marketType.getValue();
                    altHistoryDTO.setMarketType(String.valueOf(dto.getDescription()));
                }
                altHistoryDTO.setContractName(String.valueOf(contractName.getValue()));
                altHistoryDTO.setCustomerName(String.valueOf(customerName.getValue()));
                altHistoryDTO.setScreenName(screenName);
                availableCustomerTable.setColumnCheckBox(Constant.CHECK, true, false);
                availablecustomerTableLoic.loadSetData(customerSearchBinder, altHistoryDTO,  session, BooleanConstant.getTrueFlag());
                if (availableCustomerTable.size() == 0) {
                    AbstractNotificationUtils.getErrorNotification("Error",
                            "There are no records that match the search criteria. Please try again");
                }
            } catch (FieldGroup.CommitException ex) {
                LoggerFactory.getLogger(CustomerSelection.class.getName()).error( StringUtils.EMPTY, ex);
            }
        }
        LOGGER.debug("Ending search method");
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
                LOGGER.debug("Inside overriden method, Do nothing");
            }

            @Override
            public void yesMethod() {
                try {
                    /*Used to Reset to default value */
                    contractHolder.setValue(StringUtils.EMPTY);
                    contractNo.setValue(StringUtils.EMPTY);
                    customerNo.setValue(StringUtils.EMPTY);
                    contractName.setValue(StringUtils.EMPTY);
                    customerName.setValue(StringUtils.EMPTY);
                    marketType.setValue(null);
                } catch (Property.ReadOnlyException ex) {
                   LOGGER.error(ex.getMessage());

                }
            }
        }.getConfirmationMessage("Reset", "Are you sure you want to reset the page to default/previous values?");
        LOGGER.debug("Ending reset method");
    }

    /**
     * Search Button Click Logic Method is used to add customer from available
     * Customer section to Selected Customer section
     *
     * @param event
     */
    @UiHandler("addBtn")
    public void addBtnClick(Button.ClickEvent event) {
        LOGGER.debug("Entered inside addBtn method");
        if(logic.countAvailableCustomerSelection(session)){
            logic.addCustomerSelection(session);
            availablecustomerTableLoic.setFireData(altHistoryDTO, session);
        selectedcustomerTableLoic.loadSetData(customerSearchBinder, altHistoryDTO, session, BooleanConstant.getFalseFlag());
        availableCustomerTable.setColumnCheckBox(Constant.CHECK, true, false);
        } else {
            AbstractNotificationUtils.getErrorNotification("No Value Selected",
                    "Please select a Customer from the list view to Add. Then try again. ");
        }
        LOGGER.debug("Ending search method");
    }

    /**
     * Add all button click listener Method is used to add all the customers
     * from available Customer section to Selected Customer section
     *
     * @param event
     */
    @UiHandler("addAllBtn")
    public void addAllBtnClick(Button.ClickEvent event) {
        LOGGER.debug("Entering inside Addall Button");
         if(logic.countAvailableCustomerSelection(session)){
        logic.addCustomerSelection(session);
        selectedcustomerTableLoic.loadSetData(customerSearchBinder, altHistoryDTO, session, BooleanConstant.getFalseFlag());
        availablecustomerTableLoic.setFireData(altHistoryDTO, session);
        availableCustomerTable.setColumnCheckBox(Constant.CHECK, true, false);
        
         }

        LOGGER.debug("Ending inside Addall Button");
    }

    /**
     * Remove button listener
     *
     * @param event
     */
    @UiHandler("remove")
    public void removeBtnClick(Button.ClickEvent event) {
        LOGGER.debug("Entered inside remove btn method");
        if (logic.countSelectedCustomerSelection(session)) {
                logic.removecustomerSelection(session);
        selectedcustomerTableLoic.loadSetData(customerSearchBinder, altHistoryDTO, session, BooleanConstant.getFalseFlag());
       availablecustomerTableLoic.setFireData(altHistoryDTO, session);
      selectedCustomerTable.setColumnCheckBox(Constant.CHECK, true, false);
        } else {
            AbstractNotificationUtils.getErrorNotification("No Value Selected",
                    "Please select a Customer from the list view to Remove. Then try again.  ");
        }
        LOGGER.debug("Ending remove btn method");
    }

    /**
     * Remove all button listener
     *
     * @param event
     */
    @UiHandler("removeAllBtn")
    public void removeAllBtnBtnClick(Button.ClickEvent event) {
        LOGGER.debug("Entered inside removeAllBtn btn method");

        if (logic.countSelectedCustomerSelection(session)) {
            logic.removecustomerSelection(session);
            selectedcustomerTableLoic.loadSetData(customerSearchBinder, altHistoryDTO, session, BooleanConstant.getFalseFlag());
            availablecustomerTableLoic.setFireData(altHistoryDTO, session);
            selectedCustomerTable.setColumnCheckBox(Constant.CHECK, true, false);
        }
        LOGGER.debug("Ending removeAllBtn btn method");
    }

    protected final void configureFields() {

        altHistoryDTO.setScreenName(screenName);
        altHistoryDTO.setReset(Boolean.FALSE);
        commonUtil.loadComboBox(marketType, "CONTRACT_TYPE", false);
        excelBtn.setIcon(excelExportImage);
        selectedExport.setIcon(excelExportImage);

    }

    public final void configureTable() {

        availableCustomerTableLayout.addComponent(availableCustomerTable);
        HorizontalLayout hLayout;
        hLayout = availablecustomerTableLoic.createControls();
        availableCustomerTableLayout.addComponent(hLayout);
        selectedCustomerTableLayout.addComponent(selectedCustomerTable);
        HorizontalLayout hLayout1;
        hLayout1 = selectedcustomerTableLoic.createControls();
        selectedCustomerTableLayout.addComponent(hLayout1);
        
        availablecustomerTableLoic.setIsAvailable(BooleanConstant.getTrueFlag());
        availableCustomerTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        availableCustomerTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        availableCustomerTable.setHeight(NumericConstants.FOUR_HUNDRED, Unit.PIXELS);
        availableCustomerTable.setPageLength(NumericConstants.FIVE);
        availableCustomerTable.setSortEnabled(false);
        availablecustomerTableLoic.setContainerDataSource(availableCustomerContainer);
        availableCustomerTable.setVisibleColumns(availableCustomerColumns);
        availableCustomerTable.setColumnHeaders(availableCustomerHeader);
        availableCustomerTable.setEditable(true);
        availableCustomerTable.setColumnWidth(Constant.CHECK, NumericConstants.FIFTY);
        availableCustomerTable.setFilterBarVisible(true);
        availableCustomerTable.setFilterDecorator(new ExtDemoFilterDecorator());
        availableCustomerTable.addStyleName(Constant.FILTER_TABLE);
        availableCustomerTable.addStyleName("table-header-normal");
        availableCustomerTable.addStyleName("table-header-center");
        Object[] availableColumn = availableCustomerColumns;
        for (Object objColumn1 : availableColumn) {
            availableCustomerTable.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
        }
        availableCustomerTable.setFilterGenerator(new ExtFilterGenerator() {

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
            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                return null;
            }

            @Override
            public void filterRemoved(Object propertyId) {
                LOGGER.debug("Inside overriden method_ Do nothing");
            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                LOGGER.debug("Inside overriden method: Do nothing");
            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }
        });
        availableCustomerTable.setColumnCheckBox(Constant.CHECK, true);
        availableCustomerTable.setFilterFieldVisible(Constant.CHECK,false);
        availableCustomerTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                logic.checkAllAvailableCstomerSelection(session, altHistoryDTO, availablecustomerTableLoic.getFilters(), event.isChecked());
                availablecustomerTableLoic.setFireData(altHistoryDTO, session);
            }
        });

        availableCustomerTable.setTableFieldFactory(new DefaultFieldFactory() {
            private static final long serialVersionUID = 1L;

            @Override
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                if (propertyId.equals(Constant.CHECK)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    check.setEnabled(true);
                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        @Override
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            AlternateHistoryDTO alternateHistoryDTO = (AlternateHistoryDTO) itemId;
                            if (check.getValue()) {
                                logic.checkAvailableCustomerSelection(session, alternateHistoryDTO);
                            } else {
                                logic.uncheckAvailableCustomerSelection(session, alternateHistoryDTO);
                            }
                        }
                    });
                    return check;
                }

                return null;
            }
        });

        selectedcustomerTableLoic.setIsAvailable(BooleanConstant.getFalseFlag());
        selectedCustomerTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        selectedCustomerTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        selectedCustomerTable.setHeight(NumericConstants.FOUR_HUNDRED, Unit.PIXELS);
        selectedCustomerTable.setPageLength(NumericConstants.FIVE);
        selectedCustomerTable.setSortEnabled(false);
        selectedcustomerTableLoic.setContainerDataSource(selectedCustomerContainer);
        selectedCustomerTable.setVisibleColumns(availableCustomerColumns);
        selectedCustomerTable.setColumnHeaders(availableCustomerHeader);
        selectedCustomerTable.setEditable(true);
        selectedCustomerTable.setFilterBarVisible(true);
        selectedCustomerTable.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedCustomerTable.addStyleName(Constant.FILTER_TABLE);
        selectedCustomerTable.addStyleName("table-header-normal");
        selectedCustomerTable.addStyleName("table-header-center");
        selectedCustomerTable.setColumnWidth(Constant.CHECK, NumericConstants.FIFTY);
        selectedCustomerTable.setColumnCheckBox(Constant.CHECK, true);
        selectedCustomerTable.setFilterFieldVisible(Constant.CHECK,false);
        selectedCustomerTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                try{
               
                logic.checkAllSelectedCustomerSelection(session,event.isChecked());
                selectedcustomerTableLoic.loadSetData(customerSearchBinder, altHistoryDTO, session, BooleanConstant.getFalseFlag());
                }catch(Exception e){
                    LOGGER.error(e.getMessage());
                }
            }
        });

        Object[] selectedColumn = availableCustomerColumns;
        for (Object objColumn1 : selectedColumn) {
            selectedCustomerTable.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
        }

        selectedCustomerTable.setTableFieldFactory(new DefaultFieldFactory() {
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
                            AlternateHistoryDTO alternateHistoryDTO = (AlternateHistoryDTO) itemId;
                            logic.checkUncheckSelectedCustomerSelection(session, alternateHistoryDTO, (boolean) check.getValue());
                        }
                    });
                    return check;
                }
                return null;
            }
        });
    }

    public boolean getSelectedCustomers() {
        boolean flag = false;
        String companyIds = StringUtils.EMPTY;
        String contractIds = StringUtils.EMPTY;
        boolean first = true;
        for (Object items : selectedCustomerContainer.getItemIds()) {
            Boolean checked = (Boolean) selectedCustomerTable.getContainerProperty(items, Constant.CHECK).getValue();
            if (checked) {
                flag = true;
                String companySid = String.valueOf(selectedCustomerTable.getContainerProperty(items, "companymasterSid").getValue());
                String contractSid = String.valueOf(selectedCustomerTable.getContainerProperty(items, Constant.CONTRACT_MASTER_SID).getValue());
                if (first) {
                    companyIds = companySid;
                    contractIds = contractSid;
                    first = false;
                } else {
                    companyIds = companyIds + "," + companySid;
                    contractIds = contractIds + "," + contractSid;
                }
            }
        }
        session.setCompanyIds(companyIds);
        session.setContractIds(contractIds);

        return flag;
    }

    /**
     * Excel Export Logic
     *
     * @param event
     */
    @UiHandler("excelBtn")
    public void availableCustomerExport(Button.ClickEvent event) {
        try {
            contractExcelFlag = true;
            final int recordCount = logic.companySearchCount( altHistoryDTO, availablecustomerTableLoic.getFilters(),session);
            if (recordCount > 0) {
                createWorkSheet("Available_Customer", availableCustomerTable, recordCount);
            }
        } catch (SystemException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            contractExcelFlag = false;
        }
    }

    /* This Method is used to create work sheet content */
    public void createWorkSheet(String moduleName, ExtCustomTable resultTable, int count) throws SystemException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        List<String> header = Arrays.asList(resultTable.getColumnHeaders());
        List<String> list = header.subList(1, header.size());

        ExcelExportforBB.createWorkSheet(list.toArray(new String[list.size()]), count, this, UI.getCurrent(), moduleName);
    }

    /* This Method is used to write the table content in csv */
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter)  {
        try {
            if (end != 0) {
                if (contractExcelFlag) {
                    List<AlternateHistoryDTO> searchList = logic.searchCompany(altHistoryDTO,  availablecustomerTableLoic.getFilters(), start, end,  session);
                    List<Object> visibleColumns = Arrays.asList(availableCustomerTable.getVisibleColumns());
                    visibleColumns = visibleColumns.subList(1, visibleColumns.size());

                    ExcelExportforBB.createFileContent(visibleColumns.toArray(new String[visibleColumns.size()]), searchList, printWriter);
                } else if (infoExcelFlag) {
                    List<AlternateHistoryDTO> searchList = logic.getCompanyList(start, end, selectedcustomerTableLoic.getFilters(), Boolean.FALSE, session);
                    List<Object> visibleColumns = Arrays.asList(selectedCustomerTable.getVisibleColumns());
                    visibleColumns = visibleColumns.subList(1, visibleColumns.size());
                    ExcelExportforBB.createFileContent(visibleColumns.toArray(new String[visibleColumns.size()]), searchList, printWriter);
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e) {
            LOGGER.error(e.getMessage());
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
            final int recordCount = (Integer)logic.getCompanyList(0, 0, selectedcustomerTableLoic.getFilters(), Boolean.TRUE, session).get(0);
            if (recordCount > 0) {
                createWorkSheet("Selected_Customer", selectedCustomerTable, recordCount);
            }
        } catch (SystemException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            infoExcelFlag = false;
        }
    }

    
}
