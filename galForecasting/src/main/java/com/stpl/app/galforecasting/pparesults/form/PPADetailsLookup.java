/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.pparesults.form;

import com.stpl.app.galforecasting.lazyload.PriceTypeCriteria;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.pparesults.dto.PPADetailsDTO;
import com.stpl.app.galforecasting.pparesults.dto.PPADetailsLazyContainer;
import com.stpl.app.galforecasting.pparesults.dto.PPAHelperDTO;
import com.stpl.app.galforecasting.pparesults.lazyload.PPAItemLazyContainer;
import com.stpl.app.galforecasting.pparesults.logic.PPAProjectionResultsLogic;
import com.stpl.app.galforecasting.pparesults.logic.tablelogic.PPADetailsTableLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.utils.CommonUtil;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.galforecasting.utils.HeaderUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExcelExportUtil;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author pvinoth
 */
public class PPADetailsLookup extends Window {

    private static final Logger LOGGER = Logger.getLogger(PPADetailsLookup.class);

    @UiField("contractLabel")
    private Label contractLabel;

    @UiField("customerLabel")
    private Label formulaNoLabel;

    @UiField("BrandLabel")
    private Label formulaNameLabel;

    @UiField("itemNoLabel")
    private Label itemNoLabel;

    @UiField("itemNameLabel")
    private Label itemNameLabel;

    @UiField("contract")
    private ComboBox contract;

    @UiField("customer")
    private ComboBox customer;

    @UiField("brand")
    private ComboBox brand;

    @UiField("itemNo")
    private ComboBox itemNo;

    @UiField("itemName")
    private ComboBox itemName;

    @UiField("fromDate")
    private ComboBox fromDate;

    @UiField("toDate")
    private ComboBox toDate;

    @UiField("tablelayout")
    private VerticalLayout tablelayout;

    @UiField("searchBtn")
    private Button searchBtn;

    @UiField("closeBtn")
    private Button closeBtn;

    @UiField("excelBtn")
    private Button excelBtn;

    @UiField("timeperiod")
    private Panel timeperiod;

    @UiField("resetBtn")
    private Button resetBtn;

    PPADetailsTableLogic tableLogic = new PPADetailsTableLogic();

    FreezePagedTable resultsTable = new FreezePagedTable(tableLogic);

    ExtFilterTable leftTable;
    ExtFilterTable rightTable;

    private PPAProjectionResultsLogic logic = new PPAProjectionResultsLogic();
    /**
     * The max split position.
     */
    private final float maxSplitPosition = 1000;
    /**
     * The min split position.
     */
    private final float minSplitPosition = 300;
    /**
     * The split position.
     */
    private final float splitPosition = 300;
    BeanItemContainer<PPADetailsDTO> resultsContainer = new BeanItemContainer<>(PPADetailsDTO.class);
    BeanItemContainer<PPADetailsDTO> excelResultsContainer = new BeanItemContainer<>(PPADetailsDTO.class);
    PPAHelperDTO itemDefaultValue = new PPAHelperDTO(0, Constant.SELECT_ONE, Constant.SELECT_ONE);
    private PPAHelperDTO ppaHelperDTO = new PPAHelperDTO();
    private PPADetailsDTO ppaDetailsDTO = new PPADetailsDTO();
    private final ExtCustomTable excelTable = new ExtCustomTable();
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    SessionDTO sessionDTO;
    List<String> forecastPeriods = new ArrayList<String>();
    CommonUtil commonUtil = CommonUtil.getInstance();

    final HelperDTO defaultValue = new HelperDTO(0, Constant.SELECT_ONE);

    int projectionId;

    public PPADetailsLookup(int projectionId, SessionDTO sessionDTO) throws Exception {
        this.projectionId = projectionId;
        this.sessionDTO = sessionDTO;
        ppaDetailsDTO.setProjectionID(projectionId);
        init();
    }

    /**
     * Configures the Window
     */
    private void init() throws Exception {
        this.setModal(true);
        this.setClosable(true);
        this.center();
        this.setHeight("800px");
        this.setWidth("1000px");
        setCaption("PPA Detail Results");
        addStyleName(Constant.BOOTSTRAP);
        addStyleName("bootstrap-bb");
        setContent(Clara.create(getClass().getResourceAsStream("/ppadetailslookup.xml"), this));
        tablelayout.addComponent(resultsTable);
        HorizontalLayout layout = resultsTable.createControls();
        layout.setStyleName(Constant.RESPONSIVE_PAGED_TABLE);
        layout.setMargin(false);
        tablelayout.addComponent(layout);
        configureFields();
        configureTable();
        resetLogic();
        tablelayout.addComponent(excelTable);
        excelTable.setVisible(false);
        excelExport();
    }

    /**
     * Configures the table logic and result table.
     */
    private void configureTable() {
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        tableLogic.setContainerDataSource(resultsContainer);
        tableLogic.sinkItemPerPageWithPageLength(Boolean.FALSE);
        rightTable = resultsTable.getRightFreezeAsTable();
        leftTable = resultsTable.getLeftFreezeAsTable();
        rightTable.setVisibleColumns(HeaderUtils.PPA_DETAILS_VISIBLE_COLUMN_RIGHT);
        leftTable.setVisibleColumns(HeaderUtils.PPA_DETAILS_VISIBLE_COLUMN_LEFT);
        rightTable.setColumnHeaders(HeaderUtils.PPA_DETAILS_VISIBLE_HEADER_RIGHT);
        leftTable.setColumnHeaders(HeaderUtils.PPA_DETAILS_VISIBLE_HEADER_LEFT);
        resultsTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        rightTable.setSortEnabled(false);
        for (Object visiblecolumn : rightTable.getVisibleColumns()) {
            rightTable.setColumnWidth(visiblecolumn, 208);
        }
    }

    private void configureFields() {
        try {
            LazyContainer contractContainer = new LazyContainer(HelperDTO.class, new PPADetailsLazyContainer(null, Constant.CONTRACT, ppaDetailsDTO, projectionId), new PriceTypeCriteria());
            commonUtil.loadLazyComboBox(contract, contractContainer, defaultValue);
            LazyContainer customerContainer = new LazyContainer(HelperDTO.class, new PPADetailsLazyContainer(null, "customer", ppaDetailsDTO, projectionId), new PriceTypeCriteria());
            commonUtil.loadLazyComboBox(customer, customerContainer, defaultValue);
            LazyContainer brandContainer = new LazyContainer(HelperDTO.class, new PPADetailsLazyContainer(null, Constant.BRAND, ppaDetailsDTO, projectionId), new PriceTypeCriteria());
            commonUtil.loadLazyComboBox(brand, brandContainer, defaultValue);
            LazyContainer itemNoContainer = new LazyContainer(PPAHelperDTO.class, new PPAItemLazyContainer(Constant.ITEM_NO, ppaDetailsDTO), new PriceTypeCriteria());
            commonUtil.loadLazyitemComboBox(itemNo, itemNoContainer, itemDefaultValue, Constant.ITEM_NO);
            LazyContainer itemNameContainer = new LazyContainer(PPAHelperDTO.class, new PPAItemLazyContainer("itemName", ppaDetailsDTO), new PriceTypeCriteria());
            commonUtil.loadLazyitemComboBox(itemName, itemNameContainer, itemDefaultValue, "itemName");

            contract.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    if (event.getProperty() == null || (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || Constant.NULL.equals(event.getProperty().getValue())))) {
                        contract.select(defaultValue);
                    } else {
                        Object value1 = contract.getValue();
                        if (value1 == null || value1.equals(defaultValue)) {
                            customer.setValue(defaultValue);
                            brand.setValue(defaultValue);
                            itemNo.setValue(itemDefaultValue);
                            itemName.setValue(itemDefaultValue);
                        } else {
                            ppaDetailsDTO.setSelectedContract(((HelperDTO) contract.getValue()).getId());
                        }

                    }
                }
            });
            customer.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || Constant.NULL.equals(event.getProperty().getValue()))) {
                        customer.select(defaultValue);
                    } 
                    else {
                        ppaDetailsDTO.setSelectedCustomer(((HelperDTO) customer.getValue()).getId());
                    }
                }
            });
            brand.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    if  (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || Constant.NULL.equals(event.getProperty().getValue()))) {
                        brand.select(defaultValue);
                    } else {
                        ppaDetailsDTO.setSelectedBrand(((HelperDTO) brand.getValue()).getId());
                    }
                }
            });
            itemNo.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    if (itemNo.getValue() == null || Constant.NULL.equals(String.valueOf(itemNo.getValue())) || itemNo.getValue().equals(itemDefaultValue)) {
                        itemName.setValue(itemDefaultValue);

                    } else {
                        ppaDetailsDTO.setPpaHelperDTO((PPAHelperDTO) itemNo.getValue());
                        itemName.setValue((PPAHelperDTO) itemNo.getValue());
                    }
                }
            });

            itemName.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    if (itemName.getValue() == null || Constant.NULL.equals(String.valueOf(itemName.getValue())) || itemName.getValue().equals(itemDefaultValue)) {
                        itemNo.setValue(itemDefaultValue);
                    } else {
                        ppaDetailsDTO.setPpaHelperDTO((PPAHelperDTO) itemName.getValue());
                        itemNo.setValue((PPAHelperDTO) itemName.getValue());
                    }
                }
            });

            fromDate.setNullSelectionAllowed(false);
            toDate.setNullSelectionAllowed(false);
            forecastPeriods = CommonUtils.getMonthsofTwoDates(sessionDTO.getFromDate(), sessionDTO.getToDate());
            fromDate.addItems(forecastPeriods);
            toDate.addItems(forecastPeriods);
            if (!forecastPeriods.isEmpty()) {
                fromDate.select(forecastPeriods.get(0));
                toDate.select(forecastPeriods.get(forecastPeriods.size() - 1));
            }
            excelBtn.setIcon(new ThemeResource("../../icons/excel.png"));
            excelBtn.setIcon(new ThemeResource("../../icons/excel.png"));
            excelBtn.setStyleName("link");
            excelBtn.setDescription("Export to excel");
            excelBtn.setIconAlternateText("Excel export");
            excelBtn.setHtmlContentAllowed(true);
            excelBtn.setErrorHandler(new ErrorHandler() {
                /**
                 * Invoked when an error occurs
                 *
                 * @param event - ErrorEvent
                 */
                @SuppressWarnings("PMD")
                public void error(final com.vaadin.server.ErrorEvent event) {
                  
                }
            });
            closeLogic();
        } catch (Exception ex) {
        
        }
    }

    /**
     * Searches for the formula with the entered criteria.
     *
     * @param event
     */
    @UiHandler("searchBtn")
    public void searchLogic(Button.ClickEvent event) {
        try {
            ppaDetailsDTO.setStartPeriod(CommonLogic.getPeriodSID(Constant.MONTHLY, String.valueOf(fromDate.getValue()), false));
            ppaDetailsDTO.setEndPeriod(CommonLogic.getPeriodSID(Constant.MONTHLY, String.valueOf(toDate.getValue()), false));
            if ((contract.getValue() != null && contract.getValue() != defaultValue) && (customer.getValue() != null && customer.getValue() != defaultValue) && (brand.getValue() != null && brand.getValue() != defaultValue)
                    && (itemNo.getValue() != null && itemNo.getValue() != itemDefaultValue) && (itemName.getValue() != null && itemName.getValue() != itemDefaultValue)) {
                if (Integer.valueOf(ppaDetailsDTO.getStartPeriod()) >= Integer.valueOf(ppaDetailsDTO.getEndPeriod())) {
                    MessageBox.showPlain(Icon.ERROR, "Error", "The selected To Time Period must come after the From Time Period", ButtonId.OK);
                } else {
                    ppaDetailsDTO.setProjectionID(projectionId);
                    tableLogic.setSearchData(ppaDetailsDTO, sessionDTO);
                    tableLogic.setCurrentPage(1);
                }
            } else {
                MessageBox.showPlain(Icon.ERROR, "Error", "Please Select a Value from each of the following dropdowns Contract,Customer,Brand,Item", ButtonId.OK);
            }
        } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
        }
    }

    public void closeLogic() {
        closeBtn.addClickListener(new Button.ClickListener() {
            @SuppressWarnings("PMD")
            /**
             * Constant SerialID
             */
            private static final long serialVersionUID = 1L;

            /**
             * Logic for reset button.
             *
             * @param event
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {

                LOGGER.info("Entering Close Logic");
                MessageBox.showPlain(Icon.QUESTION, "Close Confirmation", "Are you sure you want to close?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (ButtonId.YES.equals(buttonId)) {
                            close();
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
            }
        });
    }

    public void resetLogic() throws Exception {
        resetBtn.addClickListener(new Button.ClickListener() {
            @SuppressWarnings("PMD")
            /**
             * Constant SerialID
             */
            private static final long serialVersionUID = 1L;

            /**
             * Logic for reset button.
             *
             * @param event
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {

                LOGGER.info("Entering Reset Logic");
                MessageBox.showPlain(Icon.QUESTION, "Reset Confirmation", "Are you sure you want to reset?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (ButtonId.YES.equals(buttonId)) {
                            contract.setValue(defaultValue);
                            customer.setValue(defaultValue);
                            brand.setValue(defaultValue);
                            itemNo.setValue(itemDefaultValue);
                            itemName.setValue(itemDefaultValue);
                            fromDate.setValue(forecastPeriods.get(0));
                            toDate.setValue(forecastPeriods.get(forecastPeriods.size() - 1));
                        }

                    }
                }, ButtonId.YES, ButtonId.NO);
            }
        });
    }

    public void excelExport() {
        excelBtn.addClickListener(new ClickListener() {
            /**
             * calls excelExportLogic method on button click
             *
             * @param event - Mouse Click event
             */
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.info("Entering EXCEL Export Button Click :::: ");
                    excelExportLogic();
                    LOGGER.info(" Ends  EXCEL Export Button Click ::::  ");

                } catch (SystemException sysException) {

                    final String errorMsg = ErrorCodeUtil.getErrorMessage(sysException);
                    LOGGER.error(errorMsg);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Error", errorMsg, new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                          
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
                } catch (PortalException portException) {

                    LOGGER.error(portException.getMessage());
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Error", "Export Operation Failed", new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                           
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
                } catch (Exception exception) {

                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Error", "Export Operation Failed", new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                           
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
                    LOGGER.error(exception.getMessage());

                }
            }
        });
    }

    protected void excelExportLogic() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering excelExportLogic");
        createWorkSheet();
        LOGGER.info("Ending excelExportLogic");
    }

    private void createWorkSheet() throws SystemException, PortalException, Exception {
            LOGGER.info("Entering createWorkSheet");
            excelTable.setContainerDataSource(excelResultsContainer);
            excelTable.setVisibleColumns(HeaderUtils.PPA_DETAILS_VISIBLE_COLUMN_EXCEL);
            excelTable.setColumnHeaders(HeaderUtils.PPA_DETAILS_VISIBLE_HEADER_EXCEL);
            List<PPADetailsDTO> resultList = (List<PPADetailsDTO>) logic.loadPPADetails(ppaDetailsDTO, sessionDTO, false, 0, 10000, null);
            excelResultsContainer.addAll(resultList);
            final long recordCount = excelResultsContainer.size();
            ExcelExportforBB.createWorkSheet(excelTable.getColumnHeaders(), recordCount, this, getUI(), "PPA_DETAILS");

            LOGGER.info("Ending createWorkSheet");
    }

    /**
     * Logic for creating worksheet content.
     *
     * @param start the start
     * @param end the end
     * @param printWriter the print writer
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, Exception {
        PPADetailsDTO dto;
        final List<PPADetailsDTO> searchList = excelResultsContainer.getItemIds();
        for (int rowCount = 0; rowCount < searchList.size(); rowCount++) {

            dto = searchList.get(rowCount);
            printWriter.print(ConstantsUtils.QUOTE + dto.getPeriod() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getPriceProtectionPriceType() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getPrice() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getPriceChange() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getMap() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getTotalDeductions() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getUnits() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getDeductionPerUnit() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getNetPrice() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getNetMAP() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getPriceProtectionAmountPerUnit() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getPriceProtectionPercent() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getTotalPriceProtectionDeduction() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getNep() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getNepFormula() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getPriceToleranceType() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getPriceTolerance() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getPriceToleranceInterval() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getPriceToleranceFrequency() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getMaxIncrementalChange() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getResetEligible() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getResetType() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            if (dto.getResetDate() == null || dto.getResetDate().trim().isEmpty() || Constant.NULL.equalsIgnoreCase(dto.getResetDate())) {
                printWriter.print(StringUtils.EMPTY + ExcelExportUtil.COMMA);
            } else {
                printWriter.print(dto.getResetDate() + ExcelExportUtil.COMMA);
            }

            printWriter.print(ConstantsUtils.QUOTE + dto.getResetFrequency() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getNetPriceType() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.println(ConstantsUtils.QUOTE + dto.getNetPriceTypeFormula() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

        }

    }
}
