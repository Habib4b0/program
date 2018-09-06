/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.lookups;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.dto.AlternateHistoryDTO;
import com.stpl.app.gtnforecasting.dto.ForecastDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.salesprojection.logic.AlternateHistoryLogic;
import com.stpl.app.gtnforecasting.salesprojection.logic.tablelogic.AlternateHistoryAllocationTableLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.utils.TabNameUtil;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.DefaultFieldFactory;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtCustomTable.ColumnCheckListener;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTable;
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
public class AltHistorySelection extends CustomComponent implements View {

    
    
    private SessionDTO session;
    
    @UiField("actualOrProj")
    private OptionGroup actualOrProj;
    
    @UiField("periodOrder")
    private OptionGroup periodOrder;
    
    @UiField("frequency")
    private  ComboBox frequency;
   
    @UiField("historyAllocationLayout")
    private VerticalLayout historyAllocationLayout;
    
    @UiField("gridLayout")
    private HorizontalLayout gridLayout;
    private AlternateHistoryAllocationTableLogic tableLogic = new AlternateHistoryAllocationTableLogic();
    private FreezePagedTable resultsTable = new FreezePagedTable(tableLogic);
    private ProjectionSelectionDTO projectionDTO = new ProjectionSelectionDTO();
    private AlternateHistoryDTO altHistoryDTO;
    private ExtContainer<AlternateHistoryDTO> resultBean = new ExtContainer<>(
            AlternateHistoryDTO.class,ExtContainer.DataStructureMode.MAP);
    @UiField("export")
    private Button export;

    @UiField("resetBtn")
    private Button resetBtn;
    
    @UiField("allocate")
    private Button allocate;
    
    @UiField("variables")
    private OptionGroup variables;
    
    @UiField("variablesLabel")
    private Label variablesLabel;
    
    private AlternateHistory altHistory=null;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(AltHistorySelection.class);
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());

    private DataFormatConverter formatsalesunit = new DataFormatConverter("#,##0.0");
    private DataFormatConverter priceFormat = new DataFormatConverter("#,##0.00", DataFormatConverter.INDICATOR_DOLLAR);
    private String excelName = "All Item Information";
    private Set ccpSet = new HashSet();
    private AlternateHistoryLogic logic = new AlternateHistoryLogic();
    private ExtTreeContainer<AlternateHistoryDTO> excelResultBean = new ExtTreeContainer<>(AlternateHistoryDTO.class,ExtContainer.DataStructureMode.MAP);
    /**
     * The map right visible columns.
     */
    private ExtPagedTable rightTable;
    private CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();

    /**
     * The max split position.
     */
    private final float maxSplitPosition = 1000;

    /**
     * The min split position.
     */
    private final float minSplitPosition = 600;

    /**
     * The split position.
     */
    private final float splitPosition = 850;

    private SimpleDateFormat formatter = new SimpleDateFormat(Constant.DATE_FORMAT);

    @UiField("from")
    private  ComboBox from;
    
    @UiField("to")
    private ComboBox to;
    private ExtCustomTable exportPeriodViewTable;   
    private boolean isAllocationChanged = false;
    private Set<String> checkboxList=new HashSet<>();

    public AltHistorySelection(SessionDTO session, AlternateHistory altHistory) {
        this.altHistoryDTO = new AlternateHistoryDTO();
        try {
            this.session = session;
            this.altHistory = altHistory;
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/altHistorySelection.xml"), this));
            configureFields();
            initializeResultTable();
            configureTable(false);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    protected final void configureFields()  {
        actualOrProj.addItem(Constant.ACTUALS);
        actualOrProj.addItem(Constant.PROJECTIONS);
        actualOrProj.addItem(Constant.BOTH);
        actualOrProj.setValue(Constant.BOTH);
        projectionDTO.setActualsOrProjections(Constant.BOTH);
        actualOrProj.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                projectionDTO.setActualsOrProjections(actualOrProj.getValue().toString());
            }
        });

        periodOrder.addItem(Constant.ASCENDING);
        periodOrder.addItem(Constant.DESCENDING);
        periodOrder.setValue(Constant.ASCENDING);

        frequency.addItem(Constant.SELECT_ONE);
        frequency.setNullSelectionItemId(Constant.SELECT_ONE);
        frequency.addItem(Constant.MONTHLY);
        frequency.addItem(Constant.QUARTERLY);
        frequency.addItem(Constant.SEMI_ANNUALLY);
        frequency.addItem(Constant.ANNUALLY);
        frequency.setValue(Constant.QUARTERLY);
         
        frequency.focus();

        frequency.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                loadFrequency(frequency.getValue().toString());
            }
        });

        if (TabNameUtil.DISCOUNT_PROJECTION.equals(session.getForecastName())) {
            variables.setVisible(false);
            variablesLabel.setVisible(false);
        } else {
            variables.setImmediate(true);
            variables.addItem(Constants.LabelConstants.SALES.getConstant());
            variables.addItem(Constants.LabelConstants.UNITS.getConstant());
            variables.select(Constants.LabelConstants.SALES.getConstant());
            variables.select(Constants.LabelConstants.UNITS.getConstant());
        }
        
        historyAllocationLayout.addComponent(resultsTable);
        HorizontalLayout controls = tableLogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        historyAllocationLayout.addComponent(controlLayout);
        historyAllocationLayout.addComponent(gridLayout);

        loadDateRange(Constant.QUARTERLY);

        export.addStyleName("link");
        export.setIcon(excelExportImage, "Excel Export");
      

        export.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                altSelectionExport();
            }
        });
    
        
        resetBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                MessageBox.showPlain(Icon.QUESTION, "Reset Confirmation", "Are you sure you want to reset the ‘Selections’ section?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {
                            resetButtonLogic();

                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
                LOGGER.debug("Ending Reset operation");
            }
        });
                
                
                
        allocate.addClickListener(new Button.ClickListener() {
        
            @Override
            public void buttonClick(Button.ClickEvent event) {
                    altHistory.changeTab();
                }
        });
        
     

    }

    @UiHandler("generateBtn")
    public void generateBtnClick(Button.ClickEvent event) {
        LOGGER.debug("Entered inside generateBtnClick method");
        try {
            if (frequency.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification(Constant.ERROR,
                        "Please Select Frequency");
                return;
            }
            if (from.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification(Constant.ERROR,
                        "Please Select From Period");
                return;
            }
            if (to.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification(Constant.ERROR,
                        "Please Select To Period");
                return;
            }

            String fromDate = String.valueOf(from.getValue());
            String toDate = String.valueOf(to.getValue());
            String startDate = formDate(fromDate, String.valueOf(frequency.getValue()), true);
            String endDate = formDate(toDate, String.valueOf(frequency.getValue()), false);
            Date stDate = formatter.parse(startDate);
            Date edDate = formatter.parse(endDate);
            if (edDate.before(stDate)) {
                AbstractNotificationUtils.getErrorNotification("Historic Time Interval Error",
                        "The selected ‘To’ Historic Time Interval must come after the selected ‘From’ period.");
                return;
            }
            ccpSet.clear();
            historyAllocationLayout.removeAllComponents();
            tableLogic = new AlternateHistoryAllocationTableLogic();
            resultsTable = new FreezePagedTable(tableLogic);
            historyAllocationLayout.addComponent(resultsTable);
            List<Integer> pagelength = CommonLogic.getPageNumber();
            tableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
            HorizontalLayout controls = tableLogic.createControls();
            HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
            historyAllocationLayout.addComponent(controlLayout);
            historyAllocationLayout.addComponent(gridLayout);
            initializeResultTable();
            configureTable(true);
            session.setFrequency(String.valueOf(frequency.getValue()));
            getnerateLogic();

        } catch (ParseException ex) {
            LOGGER.error(ex.getMessage());
        }
        LOGGER.debug("Ending generateBtnClick method");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        LOGGER.debug("Inside Overriden method: do nothing");
    }

    private void loadFrequency(String frequency) {
        loadDateRange(frequency);
    }

    /**
     * Configure table.
     *
     * @param isGenarate
     */
    public final void configureTable(boolean isGenarate) {
        LOGGER.debug("configureTable method starts");
        altHistoryDTO.setFrequency(String.valueOf(frequency.getValue()));
        altHistoryDTO.setActualsOrProjections(projectionDTO.getActualsOrProjections());
        projectionDTO.setForecastDTO(getHistoricalPeriods(session.getForecastDTO(), isGenarate));
        projectionDTO.setHistoryNum(0);
        fullHeader = new CustomTableHeaderDTO();
        projectionDTO.setIsSumarry(false);
        CustomTableHeaderDTO leftDTO = HeaderUtils.getAlternateHistoryLeftTableColumns(fullHeader);
        projectionDTO.setFrequency(frequency.getValue().toString());
        projectionDTO.setProjectionOrder(String.valueOf(periodOrder.getValue()));
                
        projectionDTO.getVariableList().clear();
        projectionDTO.getVariableList().addAll((Set)variables.getValue());        
        CustomTableHeaderDTO rightDTO = HeaderUtils.getAlternateHistoryRightTableColumns(projectionDTO, session, fullHeader);

        resultBean = new ExtContainer<>(AlternateHistoryDTO.class,ExtContainer.DataStructureMode.MAP);
        resultBean.setColumnProperties(leftDTO.getProperties());
        resultBean.setColumnProperties(rightDTO.getProperties());
        tableLogic.sinkItemPerPageWithPageLength(false);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        tableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        tableLogic.setContainerDataSource(resultBean);
        ExtPagedTable leftTable = resultsTable.getLeftFreezeAsTable();
        rightTable = resultsTable.getRightFreezeAsTable();
        leftTable.markAsDirty();
        rightTable.markAsDirty();
        leftTable.setVisibleColumns(leftDTO.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftDTO.getSingleHeaders().toArray(new String[leftDTO.getSingleHeaders().size()]));
        leftTable.setDoubleHeaderVisible(false);
        leftTable.setDoubleHeaderVisibleColumns(leftDTO.getDoubleColumns().toArray());
        leftTable.setDoubleHeaderColumnHeaders(leftDTO.getDoubleHeaders().toArray(new String[leftDTO.getDoubleHeaders().size()]));
        leftTable.addStyleName("table-header-center");
        resultsTable.addStyleName("valo-theme-extfiltertable-center");
        resultsTable.setHeight(Constant.PX_390);
        leftTable.setHeight(Constant.PX_390);
        leftTable.setFilterBarVisible(true);
        leftTable.setFilterDecorator(new ExtDemoFilterDecorator());
        leftTable.setFilterFieldVisible(Constant.CHECK, false);
        rightTable.setHeight(Constant.PX_390);
        rightTable.setDoubleHeaderVisible(BooleanConstant.getTrueFlag());
        rightTable.setVisibleColumns(rightDTO.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));
        for (int i = 0; i < rightDTO.getSingleColumns().size(); i++) {
            rightTable.setColumnAlignment(rightDTO.getSingleColumns().get(i), ExtCustomTable.Align.RIGHT);
        }
    
        rightTable.addStyleName("checkbox-center");
        rightTable.setDoubleHeaderVisible(true);
        rightTable.setDoubleHeaderVisibleColumns(rightDTO.getDoubleColumns().toArray());
        rightTable.setDoubleHeaderColumnHeaders(rightDTO.getDoubleHeaders().toArray(new String[rightDTO.getDoubleHeaders().size()]));
        for (int i = 0; i < rightDTO.getDoubleColumns().size(); i++) {
            rightTable.setDoubleHeaderColumnAlignment(rightDTO.getDoubleColumns().get(i), ExtCustomTable.Align.CENTER);
        }
        rightTable.setDoubleHeaderMap(rightDTO.getDoubleHeaderMaps());
        leftTable.setDoubleHeaderMap(leftDTO.getDoubleHeaderMaps());
        altHistoryDTO.setScreenName("alt-hist");
        for (int i = 0; i < leftDTO.getSingleColumns().size(); i++) {
            leftTable.setColumnAlignment(leftDTO.getSingleColumns().get(i), ExtCustomTable.Align.CENTER);
        }
        leftTable.setEditable(true);
        leftTable.setImmediate(true);
        leftTable.setColumnCheckBox(Constant.CHECK, true, false);
        leftTable.setColumnWidth(Constant.CHECK, NumericConstants.ONE_TWO_ZERO);
        leftTable.addColumnCheckListener(checkListener);
        rightTable.addColumnCheckListener(new ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                isAllocationChanged = true;
            }
        });
        formatTableData();
        leftTable.setTableFieldFactory(new DefaultFieldFactory() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            /**
             * To create editable fields inside table .
             */
            @Override
            public Field<?> createField(final Container container,
                    final Object itemId, final Object propertyId,
                    final Component uiContext) {
                if (String.valueOf(propertyId).equals(Constant.CHECK)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    check.setValue(BooleanConstant.getFalseFlag());
                    check.setImmediate(true);
                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        @Override
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            try {
                                boolean isCheck = (boolean) container.getContainerProperty(itemId, Constant.CHECK).getValue();
                                Object ccpdetailsSID = container.getContainerProperty(itemId, "ccpDetailsId").getValue();
                                if (isCheck) {
                                    ccpSet.add(ccpdetailsSID);
                                } else {
                                    ccpSet.remove(ccpdetailsSID);
                                }
                            } catch (Exception ex) {
                                LOGGER.error(ex.getMessage());
                            }
                        }
                    });
                    return check;
                }
                return null;
            }
        });

        Object[] obj = rightTable.getDoubleHeaderVisibleColumns();
        for (Object cb : obj) {
            rightTable.setDoubleHeaderColumnCheckBox(cb, true, false);
        }
        resultsTable.addStyleName("stableheader");
        Object[] singleCol = rightTable.getVisibleColumns();
        for (Object object : singleCol) {
            if ((TabNameUtil.DISCOUNT_PROJECTION.equals(session.getForecastName())) || ((Constant.SALES_PROJECTION.equals(session.getForecastName())) && (String.valueOf(object).contains("Units")))) {
                rightTable.setColumnCheckBox(object, true, false);
            }
        }

    }

    private void formatTableData() {
        LOGGER.debug("Start formatTableData");
        for (Object propertyId : resultsTable.getRightFreezeAsTable().getVisibleColumns()) {
            if (String.valueOf(propertyId).contains(Constant.ACTUAL_PAYMENTS)) {
                resultsTable.getRightFreezeAsTable().setConverter(propertyId, priceFormat);
            } else if (String.valueOf(propertyId).contains(Constant.PROJECTION_PAYMENTS)) {
                resultsTable.getRightFreezeAsTable().setConverter(propertyId, priceFormat);
            }else if ((String.valueOf(propertyId).contains(Constant.ACTUAL_UNITS_PROPERTY)) || (String.valueOf(propertyId).contains(Constant.PROJECTION_UNITS))) {
                resultsTable.getRightFreezeAsTable().setConverter(propertyId, formatsalesunit);
            } else if ((String.valueOf(propertyId).contains("actualSales")) || (String.valueOf(propertyId).contains("projectionSales"))) {
                resultsTable.getRightFreezeAsTable().setConverter(propertyId, priceFormat);
            }
        }
        LOGGER.debug("End formatTableData");
    }

    /**
     * Initialize Result Table.
     */
    @SuppressWarnings("serial")
    private void initializeResultTable()  {
        resultsTable.setDoubleHeaderVisible(true);
        resultsTable.setSelectable(false);
        resultsTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);

    }

    private void loadDateRange(String freq) {
        LOGGER.debug("loadDateRange method starts");
        List<String> dateList = new ArrayList<>();
        
        int currentQuarter = 0;
        int currentsemiannual = 0;
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        
        if (month >= 0 && NumericConstants.TWO >= month) {
            currentQuarter = 1;
            currentsemiannual = 1;
        }
        if (month >= NumericConstants.THREE && NumericConstants.FIVE >= month) {
            currentQuarter = NumericConstants.TWO;
            currentsemiannual = 1;
        }
        if (month >= NumericConstants.SIX && NumericConstants.EIGHT >= month) {
            currentQuarter = NumericConstants.THREE;
            currentsemiannual = NumericConstants.TWO;
        }
        if (month >= NumericConstants.NINE && NumericConstants.ELEVEN >= month) {
            currentQuarter = NumericConstants.FOUR;
            currentsemiannual = NumericConstants.TWO;
        }
            
        if (freq.equals(Constant.QUARTERLY)) {
        for (int i = 0; i <= NumericConstants.ELEVEN;) {
            for (int j = currentQuarter; currentQuarter != 1 && year == now.get(Calendar.YEAR) && j > 0; j--) {
                String common = Constant.Q + (j - 1) + year;
                dateList.add(common);
                currentQuarter = currentQuarter - 1;
                i++;
            }
            year = year - 1;
            for (int k = NumericConstants.FOUR; i <= NumericConstants.ELEVEN && k > 0; i++, k--) {
                String common = Constant.Q + k + year;
                dateList.add(common);
            }
        }
        
        }
        /* This Condition is used to load From and To Combobox, If frequency is SEMI_ANNUAL selected */ else if (freq.equals(Constant.SEMI_ANNUALLY)) {
            for (int i = 0; i < NumericConstants.EIGHT; i++) {
                for (int j = currentsemiannual; j > 0; j--) {
                    if (currentsemiannual != 1) {
                        String common = Constant.S + j + year;
                        dateList.add(common);
                        currentsemiannual = currentsemiannual - 1;
                        i++;
                    } else {
                        year = year - 1;
                    }

                    for (int k = NumericConstants.TWO; k > 0; k--) {
                        String common = Constant.S + k + year;
                        dateList.add(common);
                        i++;
                    }

                }
            }
        } /* This Condition is used to load From and To Combobox, If frequency is ANNUAL selected */ else if (freq.equals(Constant.ANNUALLY)) {
            for (int i = 0; i < NumericConstants.THREE; i++) {
                year = year - 1;
                String commonColumn = String.valueOf(year);
                dateList.add(commonColumn);
            }
        }else{

         for(int i=0;i<NumericConstants.THIRTY_SIX+month+NumericConstants.TWO;i++){
                if(month==0){
                month=NumericConstants.ELEVEN;
                year=year-1;
                }else{
                month=month-1;
                }
                dateList.add(getMonthForInt(month)+year);
            
            }
            
        }

        Collections.reverse(dateList);
        from.removeAllItems();
        to.removeAllItems();
        from.addItems(dateList);
        to.addItems(dateList);
        LOGGER.debug("loadDateRange method Ends");
    }

    /* This Method is used to how long table header needs to display based on selected from and To Combo box */
    private ForecastDTO getHistoricalPeriods(final ForecastDTO forecastDTO, boolean isGenerate) {
        LOGGER.debug("getHistoricalPeriods method Starts");
        ForecastDTO dto = new ForecastDTO();
        String startPeriod = String.valueOf(from.getValue());
        String endPeriod = String.valueOf(to.getValue());
        String freq = String.valueOf(frequency.getValue());
        String fromYear = startPeriod.substring(startPeriod.length() - NumericConstants.FOUR, startPeriod.length());
        String toYear = endPeriod.substring(endPeriod.length() - NumericConstants.FOUR, endPeriod.length());
        int startMonth = 0;
        int endMonth = 0;
        int startYear = 0;
        int endYear = 0;

        /* If method is loading when clicking generate button, if will go inside if loop. 
         If this method is loading when tab change it will go to else loop*/
        if (isGenerate) {
            if (freq.equals(Constant.QUARTERLY)) {
                startPeriod = startPeriod.replace(Constant.Q, StringUtils.EMPTY);
                endPeriod = endPeriod.replace(Constant.Q, StringUtils.EMPTY);
                startMonth = Integer.parseInt(startPeriod.substring(0, 1));
                endMonth = Integer.parseInt(endPeriod.substring(0, 1));
               
                switch (startMonth) {
                    case NumericConstants.FOUR:
                        startMonth = NumericConstants.TEN;
                        break;
                    case NumericConstants.THREE:
                        startMonth = NumericConstants.SEVEN;
                        break;
                    case NumericConstants.TWO:
                        startMonth = NumericConstants.FOUR;
                        break;
                    default:
                        break;
                }

                if (endMonth == NumericConstants.THREE) {
                    endMonth = NumericConstants.NINE;
                }
                if (endMonth == 1) {
                    endMonth = NumericConstants.THREE;
                }
                if (endMonth == NumericConstants.TWO) {
                    endMonth = NumericConstants.SIX;
                }
                if (endMonth == NumericConstants.FOUR) {
                    endMonth = NumericConstants.TWELVE;
                }
                startYear = Integer.parseInt(fromYear);
                endYear = Integer.parseInt(toYear);
            }
            else if (freq.equals(Constant.SEMI_ANNUALLY)) {
                startPeriod = startPeriod.replace(Constant.S, StringUtils.EMPTY);
                endPeriod = endPeriod.replace(Constant.S, StringUtils.EMPTY);
                startMonth = Integer.parseInt(startPeriod.substring(0, 1));
                endMonth = Integer.parseInt(endPeriod.substring(0, 1));
                if (startMonth == 1) {
                    startMonth = 1;
                }
                if (startMonth == NumericConstants.TWO) {
                    startMonth = NumericConstants.SEVEN;
                }
                if (endMonth == 1) {
                    endMonth = NumericConstants.SIX;
                }
                if (endMonth == NumericConstants.TWO) {
                    endMonth = NumericConstants.TWELVE;
                }
                startYear = Integer.parseInt(fromYear);
                endYear = Integer.parseInt(toYear);

            }else if(freq.equals(Constant.ANNUALLY)){
                
                startYear = Integer.parseInt(fromYear);
                endYear = Integer.parseInt(toYear);
                startMonth =  1;
                endMonth = NumericConstants.TWELVE;
            }else if(freq.equals(Constant.MONTHLY)){
                Map<String, Integer> monthMap = getMonthMap();
               
                int startMon = monthMap.get(startPeriod.substring(startPeriod.length() - NumericConstants.SEVEN, startPeriod.length() - NumericConstants.FOUR));
                int endMon = monthMap.get(endPeriod.substring(endPeriod.length() - NumericConstants.SEVEN, endPeriod.length() - NumericConstants.FOUR));
                startMonth = startMon + 1;
                endMonth = endMon + 1;
                startYear = Integer.parseInt(fromYear);
                endYear = Integer.parseInt(toYear);
            }
        } else {
            startMonth = forecastDTO.getHistoryStartMonth();
            endMonth = forecastDTO.getHistoryEndMonth();
            startYear = forecastDTO.getHistoryStartYear();
            endYear = forecastDTO.getHistoryEndYear();
        }
        dto.setHistoryStartMonth(startMonth);
        dto.setHistoryEndMonth(startMonth);
        dto.setHistoryStartYear(startYear);
        dto.setHistoryEndYear(startYear);

        dto.setProjectionStartMonth(startMonth);
        dto.setProjectionEndMonth(endMonth);
        dto.setProjectionStartYear(startYear);
        dto.setProjectionEndYear(endYear);

        dto.setForecastStartMonth(startMonth);
        dto.setForecastEndMonth(endMonth);
        dto.setForecastStartYear(startYear);
        dto.setForecastEndYear(endYear);
        LOGGER.debug("getHistoricalPeriods method Ends");
        return dto;
    }

     /* This method is used to form the date based on selected from/to period */
    private String formDate(String value, String freq, boolean isFromDate) {
        LOGGER.debug("formDate method Starts");
        String date = StringUtils.EMPTY;
        String valueDate = value;
        String year = valueDate.substring(valueDate.length() - NumericConstants.FOUR, valueDate.length());
        if (freq.equals(Constant.QUARTERLY)) {
            valueDate = valueDate.replace(Constant.Q, StringUtils.EMPTY);
            String quarter = valueDate.substring(0, 1);
            if (quarter.equals(Constant.STRING_ONE)) {
                if (isFromDate) {
                    date = year + Constant.ONE_ONE;
                } else {
                    date = year + "-03-31";
                }
            }
            if (quarter.equals("2")) {
                if (isFromDate) {
                    date = year + "-04-01";
                } else {
                    date = year + Constant.SIX_THIRTY;
                }
            }
            if (quarter.equals("3")) {
                if (isFromDate) {
                    date = year + Constant.STRING_SEVEN_ONE;
                } else {
                    date = year + "-09-30";
                }
            }
            if (quarter.equals("4")) {
                if (isFromDate) {
                    date = year + "-10-01";
                } else {
                    date = year + Constant.TWELVE_THIRTY_ONE;
                }
            }
        }else if (freq.equals(Constant.SEMI_ANNUALLY)) {
            valueDate = valueDate.toUpperCase(Locale.ENGLISH).replace(Constant.S, StringUtils.EMPTY);
            String semiAnnual = valueDate.substring(0, 1);
            if (semiAnnual.equals(Constant.STRING_ONE)) {
                if (isFromDate) {
                    date = year + Constant.ONE_ONE;
                } else {
                    date = year + Constant.SIX_THIRTY;
                }
            }
            if (semiAnnual.equals("2")) {
                if (isFromDate) {
                    date = year + Constant.STRING_SEVEN_ONE;
                } else {
                    date = year + Constant.TWELVE_THIRTY_ONE;
                }
            }
        }else if(freq.equals(Constant.ANNUALLY)){
                if(isFromDate){
                 date = year + Constant.ONE_ONE;
                }else{
                 date = year + Constant.TWELVE_THIRTY_ONE;
                
                }
              
            }else{
                
                Map<String, Integer> monthMap = getMonthMap();
               
                int period = monthMap.get(StringUtils.capitalize(valueDate.substring(valueDate.length() - NumericConstants.SEVEN, valueDate.length() - NumericConstants.FOUR)));
                
               int startMonth = period + 1;
              
                if(startMonth==1){
                if(isFromDate){
                 date = year + Constant.ONE_ONE;
                }else{
                 date = year + "-01-31";
                }
                }else if(startMonth==NumericConstants.TWO){
                if(isFromDate){
                 date = year + "-02-01";
                }else{
                 date = year + "-02-28";
                }
                }else if(startMonth==NumericConstants.THREE){
                if(isFromDate){
                 date = year + "-03-01";
                }else{
                 date = year + "-03-31";
                }
                }else if(startMonth==NumericConstants.FOUR){
                if(isFromDate){
                 date = year + "-04-01";
                }else{
                 date = year + "-04-30";
                }
                }else if(startMonth==NumericConstants.FIVE){
                if(isFromDate){
                 date = year + "-05-01";
                }else{
                 date = year + "-05-31";
                }
                }else if(startMonth==NumericConstants.SIX){
                if(isFromDate){
                 date = year + "-06-01";
                }else{
                 date = year + Constant.SIX_THIRTY;
                }
                }else if(startMonth==NumericConstants.SEVEN){
                if(isFromDate){
                 date = year + Constant.STRING_SEVEN_ONE;
                }else{
                 date = year + "-07-31";
                }
                }else if(startMonth==NumericConstants.EIGHT){
                if(isFromDate){
                 date = year + "-08-01";
                }else{
                 date = year + "-08-31";
                }
                }else if(startMonth==NumericConstants.NINE){
                if(isFromDate){
                 date = year + "-09-01";
                }else{
                 date = year + "-09-30";
                }
                }else if(startMonth==NumericConstants.TEN){
                if(isFromDate){
                 date = year + "-10-01";
                }else{
                 date = year + "-10-31";
                }
                }else if(startMonth==NumericConstants.ELEVEN){
                if(isFromDate){
                 date = year + "-11-01";
                }else{
                 date = year + "-11-30";
                }
                }else if(startMonth==NumericConstants.TWELVE){
                if(isFromDate){
                 date = year + "-12-01";
                }else{
                 date = year + Constant.TWELVE_THIRTY_ONE;
                }
                }
            }

        return date;
    }

    private void getnerateLogic() {
        LOGGER.debug("getnerateLogic method Starts");
        String fromDate = String.valueOf(from.getValue());
        String toDate = String.valueOf(to.getValue());
       
        String startDate = formDate(fromDate, String.valueOf(frequency.getValue()), true);
        String endDate = formDate(toDate, String.valueOf(frequency.getValue()), false);
        altHistoryDTO.setStartDate(startDate);
        altHistoryDTO.setEndDate(endDate);

        tableLogic.setProjectionResultsData(altHistoryDTO, session,true,ccpSet);
    }

    public void checkBoxList(Object checkBoxValue) {
        if (TabNameUtil.SALES_PROJECTION.equals(session.getForecastName())) {
            if (rightTable.getColumnCheckBox(String.valueOf(checkBoxValue) + Constant.ACTUAL_UNITS_PROPERTY)
                    || rightTable.getColumnCheckBox(String.valueOf(checkBoxValue) + Constant.PROJECTION_UNITS)) {
                checkboxList.add(String.valueOf(checkBoxValue));
            } else {
                checkboxList.remove(String.valueOf(checkBoxValue));
            }
        } else if (TabNameUtil.DISCOUNT_PROJECTION.equals(session.getForecastName())) {
            if (rightTable.getColumnCheckBox(String.valueOf(checkBoxValue) + Constant.ACTUAL_PAYMENTS)
                    || rightTable.getColumnCheckBox(String.valueOf(checkBoxValue) + Constant.PROJECTION_PAYMENTS)) {
                checkboxList.add(String.valueOf(checkBoxValue));
            } else {
                checkboxList.remove(String.valueOf(checkBoxValue));
            }
        }
    }

    
 public List getselectedPeriod() {
        List<String> datePeriod = new ArrayList();
        StringBuilder datePeriodUnchecked = new StringBuilder();
        boolean isChecked = false;
        boolean isHeaderChecked =  false;
        Object[] obj = rightTable.getDoubleHeaderVisibleColumns();
        for (Object cb : obj) {
            checkBoxList(cb);
            if (rightTable.getDoubleHeaderColumnCheckBox(cb)) {
                checkboxList.remove(cb);
                isHeaderChecked=true;
                datePeriod.add(String.valueOf(cb));
                if (TabNameUtil.DISCOUNT_PROJECTION.equals(session.getForecastName())) {
                    if ("Both".equals(actualOrProj.getValue().toString())) {
                        if (!((rightTable.getColumnCheckBox(String.valueOf(cb) + Constant.ACTUAL_PAYMENTS))
                                || (rightTable.getColumnCheckBox(String.valueOf(cb) + Constant.PROJECTION_PAYMENTS)))) {
                            isChecked = true;
                            datePeriodUnchecked.append(rightTable.getDoubleHeaderColumnHeader(cb)).append(" ,");
                        }
                    } else if (Constant.ACTUALS.equals(actualOrProj.getValue().toString())) {
                        if (!(rightTable.getColumnCheckBox(String.valueOf(cb) + Constant.ACTUAL_PAYMENTS))) {
                            isChecked = true;
                            datePeriodUnchecked.append(rightTable.getDoubleHeaderColumnHeader(cb)).append(" ,");
                        }
                    } else {
                        if (!(rightTable.getColumnCheckBox(String.valueOf(cb) + Constant.PROJECTION_PAYMENTS))) {
                            isChecked = true;
                            datePeriodUnchecked.append(rightTable.getDoubleHeaderColumnHeader(cb)).append(" ,");
                        }

                    }
                 } else if (TabNameUtil.SALES_PROJECTION.equals(session.getForecastName())) {
                    if ("Both".equals(actualOrProj.getValue().toString())) {
                        if (!((rightTable.getColumnCheckBox(String.valueOf(cb) + Constant.ACTUAL_UNITS_PROPERTY))
                                || (rightTable.getColumnCheckBox(String.valueOf(cb) + Constant.PROJECTION_UNITS)))) {
                            isChecked = true;
                            datePeriodUnchecked.append(rightTable.getDoubleHeaderColumnHeader(cb)).append(" ,");
                        }
                    } else if (Constant.ACTUALS.equals(actualOrProj.getValue().toString())) {
                        if (!(rightTable.getColumnCheckBox(String.valueOf(cb) + Constant.ACTUAL_UNITS_PROPERTY))) {
                            isChecked = true;
                            datePeriodUnchecked.append(rightTable.getDoubleHeaderColumnHeader(cb)).append(" ,");
                        }
                    } else if (!(rightTable.getColumnCheckBox(String.valueOf(cb) + Constant.PROJECTION_UNITS))) {
                        isChecked = true;
                        datePeriodUnchecked.append(rightTable.getDoubleHeaderColumnHeader(cb)).append(" ,");
                    }
                    }
                }
            }
        if ((!isChecked && !isHeaderChecked)||!checkboxList.isEmpty()) {
            AbstractNotificationUtils.getErrorNotification(Constant.ERROR, "Please select Time Period.");
            return Collections.emptyList();
        }
        if (isChecked) {
            datePeriodUnchecked.deleteCharAt(datePeriodUnchecked.lastIndexOf(","));
            if (TabNameUtil.DISCOUNT_PROJECTION.equals(session.getForecastName())) {
                AbstractNotificationUtils.getErrorNotification("Not all required fields selected", "The following Time Period " + datePeriodUnchecked + " must have either the ‘Actual Payments’ or ‘Projected Payments’ check box selected.");
            } else if (TabNameUtil.SALES_PROJECTION.equals(session.getForecastName())) {
                AbstractNotificationUtils.getErrorNotification("Not all required fields selected", "The following Time Period " + datePeriodUnchecked + " must have either the ‘Actual Units’ or ‘Projected Units’ check box selected.");
            }
            return Collections.emptyList();
        }
        
        if (datePeriod.size() > 0) {
            int size = datePeriod.size();
            String startPeriod = datePeriod.get(0);
            String endPeriod = datePeriod.get(size - 1);

            String freq = String.valueOf(frequency.getValue());
            if (freq.equals(Constant.QUARTERLY)) {
                startPeriod = startPeriod.replace('q', 'Q');
                endPeriod = endPeriod.replace('q', 'Q');
            }
            String stDate = formDate(startPeriod, freq, true);
            String eDate = formDate(endPeriod, freq, false);
            session.setStartDate(stDate);
            session.setAltFromPeriod(startPeriod);
            session.setEndDate(eDate);
            session.setAltToPeriod(endPeriod);
        }
    return datePeriod;
    }

    public boolean getSelectedItems() {
        boolean flag = false;
        if (ccpSet.isEmpty()) {
            AbstractNotificationUtils.getErrorNotification("No CCP selected", "Please select a CCP to use as a baseline for the Alternate History Allocation process.");
        } else {
            String ccpDetailsId = ccpSet.toString();
            ccpDetailsId = StringUtils.deleteWhitespace(ccpDetailsId.substring(1, ccpDetailsId.length() - 1));            
            session.setCcps(ccpDetailsId);
            flag = true;
        }
        return flag;
    }
    
     public void altSelectionExport() {
        configureExcelResultTable();
        AlternateHistoryLogic altHistLogic;
        altHistLogic = new AlternateHistoryLogic();
        List<AlternateHistoryDTO> list = altHistLogic.alternateSelectionList(session, altHistoryDTO, null,0,0,true,ccpSet);
        excelResultBean.addAll(list);
        ForecastUI.setEXCEL_CLOSE(true);
        ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(exportPeriodViewTable), excelName, excelName, excelName.replace(' ', '_') + ".xls", false);
        excel.export();
        historyAllocationLayout.removeComponent(exportPeriodViewTable);
    }

    private void configureExcelResultTable() {
        excelResultBean = new ExtTreeContainer<>(AlternateHistoryDTO.class,ExtContainer.DataStructureMode.MAP);
     
        exportPeriodViewTable = new ExtCustomTable();
        historyAllocationLayout.addComponent(exportPeriodViewTable);
        exportPeriodViewTable.setRefresh(BooleanConstant.getFalseFlag());
        exportPeriodViewTable.setVisible(false);
        excelResultBean.setColumnProperties(fullHeader.getProperties());
        exportPeriodViewTable.setContainerDataSource(excelResultBean);
        exportPeriodViewTable.setVisibleColumns(fullHeader.getSingleColumns().toArray());
        exportPeriodViewTable.setColumnHeaders(fullHeader.getSingleHeaders().toArray(new String[fullHeader.getSingleHeaders().size()]));
    }
    private ColumnCheckListener checkListener = new ColumnCheckListener() {
        @Override
        public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
            if (Constant.CHECK.equals(event.getPropertyId().toString())) {
                ccpSet.clear();
                if (event.isChecked()) {
                    ccpSet.addAll(logic.getSelectedCCPS(session));
                }
                Collection<?> salesList = resultsTable.getLeftFreezeAsTable().getItemIds();
                if (!salesList.isEmpty()) {
                    for (Object item : salesList) {
                        resultBean.getContainerProperty(item, Constant.CHECK).setValue(event.isChecked());
                    }
                }
            }
        }
    };

public void resetButtonLogic() {
        frequency.setValue(Constant.QUARTERLY);
        periodOrder.setValue("Ascending");
        actualOrProj.setValue("Both");
        loadDateRange("Quarterly");
    }
    
 public static String getMonthForInt(int num) {
        String month = "";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();
        if (num >= 0 && num <= NumericConstants.ELEVEN) {
            month = months[num];
        }
        return month;
    }
 
  public static Map<String,Integer> getMonthMap() {
      Map<String,Integer> monthMap=new HashMap<>();
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();
        List<String> temp=Arrays.asList(months);
        int i=0;
        for(String m:temp){
            monthMap.put(m, i);
             i++;
        }
      
        return monthMap;
    }
 
    public String getCcpIds() {
        String ccpIds = StringUtils.EMPTY;
        if (ccpSet != null && !ccpSet.isEmpty()) {
            ccpIds = ccpSet.toString();
            ccpIds = StringUtils.deleteWhitespace(ccpIds.substring(1, ccpIds.length() - 1));
}
        return ccpIds;
    }

    public boolean getAllocationCheck() {
        return isAllocationChanged;
    }

    public boolean setAllocationCheck(boolean isAllocationChangeChecked) {
        return isAllocationChanged = isAllocationChangeChecked;
    }

}
