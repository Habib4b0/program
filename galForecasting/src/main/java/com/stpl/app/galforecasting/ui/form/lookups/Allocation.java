/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.ui.form.lookups;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.dto.AlternateHistoryDTO;
import com.stpl.app.galforecasting.dto.ForecastDTO;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.salesprojection.logic.AlternateHistoryLogic;
import com.stpl.app.galforecasting.salesprojection.logic.tablelogic.AltAllocTableLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.galforecasting.utils.HeaderUtils;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.galforecasting.ui.ForecastUI;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.TabNameUtil;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import static com.stpl.app.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.vaadin.ui.HorizontalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.sql.Date;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author nandhakumar
 */
public class Allocation extends CustomComponent implements View {

    SessionDTO session;
    @UiField("selectedCustomerTableLayout")
    protected VerticalLayout selectedCustomerTableLayout;
    @UiField("allocationDetailsLayout")
    protected VerticalLayout allocationDetailsLayout;

    AltAllocTableLogic tableLogic;
    AltAllocTableLogic tableDetLogic = new AltAllocTableLogic();
    AlternateHistoryLogic logic = new AlternateHistoryLogic();
    FreezePagedTable resultsTable ;
    FreezePagedTable resultsDetTable = new FreezePagedTable(tableDetLogic);
    /* The Right Header Dto */
    CustomTableHeaderDTO rightHeader = new CustomTableHeaderDTO();
    /* The Right Header Dto */
    CustomTableHeaderDTO leftHeader = new CustomTableHeaderDTO();
    /* String to be stored during focus of List View text field */
    String focusValue = StringUtils.EMPTY;
    /* String to be stored during blur of List View text field */
    String blurValue = StringUtils.EMPTY;
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
    private final float splitPosition = 900;
    ProjectionSelectionDTO projectionDTO = new ProjectionSelectionDTO();
    ExtFilterTable leftTable;
    ExtFilterTable leftDetTable;
    ExtFilterTable rightTable;
    ExtFilterTable rightDetTable;
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO fullDetHeader = new CustomTableHeaderDTO();
    @UiField("frequency")
    public ComboBox frequency;
    @UiField("from")
    public ComboBox from;
    @UiField("to")
    public ComboBox to;
    @UiField("refreshBtn")
    Button refreshBtn;
    @UiField("export")
    public Button export;
    @UiField("exportBtn")
    public Button exportBtn;
    CustomTableHeaderDTO rightDTO;
    
    DataFormatConverter percentFormat = new DataFormatConverter("#,##0.00", DataFormatConverter.INDICATOR_PERCENT);
    DataFormatConverter salesFormat = new DataFormatConverter("#,##0.00", DataFormatConverter.INDICATOR_DOLLAR);
    DataFormatConverter salesFormatallocation = new DataFormatConverter("#,##0.00", StringUtils.EMPTY);
    private ExtContainer<AlternateHistoryDTO> resultBean = new ExtContainer<AlternateHistoryDTO>(
            AlternateHistoryDTO.class, ExtContainer.DataStructureMode.MAP);
    private ExtContainer<AlternateHistoryDTO> resultDetBean = new ExtContainer<AlternateHistoryDTO>(
            AlternateHistoryDTO.class, ExtContainer.DataStructureMode.MAP);
    AlternateHistoryDTO altDto = new AlternateHistoryDTO();
    CommonUtils commonUtil = new CommonUtils();
    boolean addToQueue = false;
    String projDetSids = StringUtils.EMPTY;
    String changedProperty = StringUtils.EMPTY;
    String checkValue = StringUtils.EMPTY;
    private static final Logger LOGGER = Logger.getLogger(Allocation.class);
    protected final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    private ExtCustomTable exportTable;
    String excelName = "All Item Information";
    private CustomTreeContainer<AlternateHistoryDTO> excelResultBean = new CustomTreeContainer<AlternateHistoryDTO>(AlternateHistoryDTO.class);
//    private int count= 0;
//    private int selCount= 0;
//    static List<Object> removeList = new ArrayList<Object>();
    List<Object> projDetList = new ArrayList<Object>();
    boolean allocation = false;
    Set headerno = new HashSet();
//    Set checkRecord = new HashSet();
    Map allocationCheck = new HashMap<Integer,Set>();
    Integer oldSid=0;
    Date start_stamp;
    Date end_stamp;
    private final List allocatedPeriodsList = new ArrayList();


    public Allocation(SessionDTO session,String projDetSids) {
        try {
            this.session = session;
            this.projDetSids=projDetSids;

            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/allocation.xml"), this));
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void getContent(SessionDTO session) {
        try {
            this.session = session;
            configureFields();
            initializeResultTable();
            configureDetailsTable();
            configureTable();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void configureFields() throws Exception {
        frequency.addItem(Constant.SELECT_ONE);
        frequency.setNullSelectionItemId(Constant.SELECT_ONE);
        frequency.addItem(Constant.MONTHLY);
        frequency.addItem(Constant.QUARTERLY);
        frequency.addItem(Constant.SEMI_ANNUALLY);
        frequency.addItem(Constant.ANNUALLY);
        if (StringUtils.isNotBlank(session.getFrequency())) {
            frequency.setValue(session.getFrequency());
        } else {
            session.setFrequency(Constant.QUARTERLY);
            frequency.setValue(Constant.QUARTERLY);
        }
        frequency.focus();

        frequency.addValueChangeListener(new Property.ValueChangeListener() {

            public void valueChange(Property.ValueChangeEvent event) {
            }
        });
        loadFrequency(frequency.getValue().toString());
        from.addItem(session.getAltFromPeriod());
        to.addItem(session.getAltToPeriod());
        from.setValue(session.getAltFromPeriod());
        to.setValue(session.getAltToPeriod());
        from.setEnabled(false);
        to.setEnabled(false);
        update_start_end_DateStamps();
        
        export.addStyleName("link");
        export.setIcon(excelExportImage, "Excel Export");
        export.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                AvailableExport();
            }
        });
        exportBtn.addStyleName("link");
        exportBtn.setIcon(excelExportImage, "Excel Export");
        exportBtn.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                SelectionExport();
            }
        });

    }

    /**
     * To add Results table in UI
     */
    private void addResultTable() throws Exception {
        selectedCustomerTableLayout.addComponent(resultsTable);

    }

    /**
     * Initialize Result Table.
     */
    @SuppressWarnings("serial")
    private void initializeResultTable() throws Exception {
        if(resultsTable!=null){
            selectedCustomerTableLayout.removeComponent(resultsTable);
        }
        tableLogic = new AltAllocTableLogic();
        resultsTable = new FreezePagedTable(tableLogic);
        resultsTable.setDoubleHeaderVisible(true);
        resultsTable.setSelectable(false);
        resultsTable.setImmediate(true);
        resultsTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultsTable.getRightFreezeAsTable().setEditable(true);
        tableDetLogic = new AltAllocTableLogic();
        resultsDetTable = new FreezePagedTable(tableDetLogic);
        resultsDetTable.setDoubleHeaderVisible(true);
        resultsDetTable.setSelectable(false);
        resultsDetTable.setImmediate(true);
        resultsDetTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        resultsDetTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        resultsDetTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        resultsDetTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
    }

    /**
     * Configure table.
     */
    public void configureTable() throws Exception {
        LOGGER.info("configureTable method starts");
        projectionDTO.setForecastDTO(getHistoricalPeriods(session.getForecastDTO()));
        projectionDTO.setHistoryNum(0);
        fullHeader = new CustomTableHeaderDTO();
        CustomTableHeaderDTO leftDTO = HeaderUtils.getAltHistAllocLeftTableColumns(fullHeader);
        projectionDTO.setFrequency(frequency.getValue().toString());
        projectionDTO.setProjectionOrder(Constant.ASCENDING);
        projectionDTO.setScreenName(session.getForecastName());
        rightHeader = HeaderUtils.getAltHistAllocRightTableColumns(projectionDTO, session, fullHeader);
        resultBean = new ExtContainer<AlternateHistoryDTO>(AlternateHistoryDTO.class, ExtContainer.DataStructureMode.MAP);
        resultBean.setColumnProperties(leftDTO.getProperties());
        resultBean.setColumnProperties(rightHeader.getProperties());
        tableLogic.sinkItemPerPageWithPageLength(false);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        tableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        tableLogic.setContainerDataSource(resultBean);
        resultsTable.getLeftFreezeAsTable().setFilterBarVisible(true);
        leftTable = resultsTable.getLeftFreezeAsTable();
        rightTable = resultsTable.getRightFreezeAsTable();
        leftTable.markAsDirty();
        rightTable.markAsDirty();
        leftTable.setVisibleColumns(leftDTO.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftDTO.getSingleHeaders().toArray(new String[leftDTO.getSingleHeaders().size()]));
        leftTable.setDoubleHeaderVisible(Boolean.TRUE);
        leftTable.setDoubleHeaderVisibleColumns(leftDTO.getDoubleColumns().toArray());
        leftTable.setDoubleHeaderColumnHeaders(leftDTO.getDoubleHeaders().toArray(new String[leftDTO.getDoubleHeaders().size()]));
        leftTable.addStyleName("table-header-center");
        resultsTable.setHeight("390px");
        leftTable.setHeight("390px");
        rightTable.setHeight("390px");
        rightTable.setEditable(true);
        resultsTable.getRightFreezeAsTable().setEditable(true);
        rightTable.setDoubleHeaderVisible(Boolean.TRUE);

              List<Object> rightVisibleColumns=new ArrayList<Object>();
         rightVisibleColumns=rightHeader.getSingleColumns();
        
        rightTable.setVisibleColumns(rightVisibleColumns.toArray());
        rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
        for (int i = 0; i < rightHeader.getSingleColumns().size(); i++) {
            rightTable.setColumnAlignment(rightHeader.getSingleColumns().get(i), ExtCustomTable.Align.LEFT);
            rightTable.addStyleName("table-header-left");
        }
        rightTable.setDoubleHeaderVisible(true);
        rightTable.setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
        rightTable.setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));
        for (int i = 0; i < rightHeader.getDoubleColumns().size(); i++) {
            rightTable.setColumnAlignment(rightHeader.getDoubleColumns().get(i), ExtCustomTable.Align.CENTER);
        }
        rightTable.setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());
        for (int i = 0; i < leftDTO.getSingleColumns().size(); i++) {
            leftTable.setColumnAlignment(leftDTO.getSingleColumns().get(i), ExtCustomTable.Align.CENTER);
        }
        leftTable.setDoubleHeaderMap(leftDTO.getDoubleHeaderMaps());
        leftTable.setFilterFieldVisible(Constant.CHECKRECORD, false);
        leftTable.setFilterDecorator(new ExtDemoFilterDecorator());
        addToQueue = Boolean.FALSE;
        altDto.setReset(Boolean.TRUE);
        altDto.setFrequency(frequency.getValue().toString());
        session.setEndDate(commonUtil.formDate(session.getAltToPeriod(),altDto.getFrequency(),Boolean.FALSE));
        session.setStartDate(commonUtil.formDate(session.getAltFromPeriod(),altDto.getFrequency(),Boolean.TRUE));
        tableLogic.loadSetData(altDto, session, addToQueue);
        leftTable.setColumnCheckBox(Constant.CHECKRECORD, true,false);
        leftTable.setEditable(true);
        rightTable.addStyleName("table-header-center");
        selectedCustomerTableLayout.removeAllComponents();
        selectedCustomerTableLayout.addComponent(resultsTable);
        HorizontalLayout controls = tableLogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        selectedCustomerTableLayout.addComponent(controlLayout);
        leftTable.setData("available");
        leftTable.addColumnCheckListener(checkListener);
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

            final AlternateHistoryDTO dto = (AlternateHistoryDTO) itemId;
                if (!"Total Alternate History Baseline".equals(dto.getContractNo())) {
                    if (String.valueOf(propertyId).equals(Constant.CHECKRECORD)) {
                        final ExtCustomCheckBox check = new ExtCustomCheckBox();
                        check.setValue(false);
                        check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                            public void click(ExtCustomCheckBox.ClickEvent event) {
                                try {
//                                    updateCheckedRecord(dto, check.getValue() ? 1 : 0);
                                     logic.check_available_allocationTab(dto, session, check.getValue() ? 1 : 0, start_stamp, end_stamp);
                                } catch (Exception ex) {
                                    LOGGER.error(ex);
                                }
                            }
                        });
                        return check;
                    }
                }
//                else{
//                    dto.setCheckRecord(null);
//                }
                return null;
            }
        });
        
        final String frequencyValue = frequency.getValue() == null ? StringUtils.EMPTY : frequency.getValue().toString();
        
        rightTable.setTableFieldFactory(new DefaultFieldFactory() {
            public Field<?> createField(final Container container, final Object itemId,
                    final Object propertyId, Component uiContext) {
                try {
                   AlternateHistoryDTO dto = (AlternateHistoryDTO)itemId;
                    final String property = String.valueOf(propertyId);
                    String fieldvalue = String.valueOf(container.getContainerProperty(itemId, propertyId).getValue());
                    if (rightHeader.getSingleColumns().contains(property)) {
                        if (Constant.NULL.equals(fieldvalue)) {

                            dto.addStringProperties(propertyId, Constant.DASH);
                            container.getContainerProperty(itemId, propertyId).setValue(Constant.DASH);
                        }
                    }
                    if (!"Total Alternate History Baseline".equals(dto.getContractNo())) {
                        TextField textField = new TextField();
                        textField.setImmediate(Boolean.TRUE);
                        textField.setWidth(100, Unit.PERCENTAGE);
                        textField.addStyleName(Constant.TXT_RIGHT_ALIGN);
                        if (propertyId != null) {

                            if (session.getForecastName().equals("Discount Projection")) {
                                if (String.valueOf(propertyId).contains("%")) {
                                    textField.setConverter(percentFormat);
                                } else {
                                    textField.setConverter(salesFormat);
                                }
                            } else {
                                if (String.valueOf(propertyId).contains("%")) {
                                    textField.setConverter(percentFormat);
                                } else {
                                    textField.setConverter(salesFormatallocation);
                                }
                            }
                        }
                        
                        final int endexForRange = frequencyValue.equals(Constant.MONTHLY) ? 7 : frequencyValue.equals(Constant.ANNUALLY) ? 4 : 6;
                        final String allocatedPeriod = (StringUtils.EMPTY + propertyId).substring(0, endexForRange);
                        
                        if (allocatedPeriodsList.contains(allocatedPeriod)) {
                            textField.addFocusListener(new FieldEvents.FocusListener() {
                                @Override
                                public void focus(FieldEvents.FocusEvent event) {
                                    
                                    focusValue = String.valueOf(container.getContainerProperty(itemId, propertyId).getValue()).replace(" ", StringUtils.EMPTY);
                                    LOGGER.info(" focus Value" + focusValue);
                                }
                            });
                            
                            textField.addBlurListener(new FieldEvents.BlurListener() {
                                @Override
                                public void blur(FieldEvents.BlurEvent event) {
                                    blurValue = String.valueOf(container.getContainerProperty(itemId, propertyId).getValue()).replace(" ", StringUtils.EMPTY);
                                    
                                    LOGGER.info(" blur Value" + blurValue);
                                       String propValue=String.valueOf(propertyId);
                                       String currentProperty=getSelectedColumn(propValue);
                                    if (!focusValue.equals(blurValue)) {
                                        if (StringUtils.isEmpty(changedProperty)) {
                                            String value=String.valueOf(propertyId);
                                            changedProperty=getSelectedColumn(value);
                                        } else if (!currentProperty.equals(changedProperty)) {
                                            container.getContainerProperty(itemId, propertyId).setValue(focusValue);
                                            throwErrorPopUp();
                                            return;
                                        }
                                        Double incOrDec = 0.0;
                                        Double newNumber = 0.0;
                                        Double oldNumber = 0.0;
                                        if (String.valueOf(propertyId).contains(Constant.PERCENT)) {
                                            incOrDec = Double.valueOf(blurValue);
                                        } else {
                                            incOrDec = Double.valueOf(blurValue);
                                        }
                                        AlternateHistoryDTO dto = (AlternateHistoryDTO) itemId;
                                        dto.addStringProperties(propertyId, blurValue);
                                        logic.updateAllocationOnEdit(String.valueOf(propertyId), dto.getProjDetailSid(), incOrDec, oldNumber,(String)frequency.getValue(),session);
//                                        if (String.valueOf(propertyId).contains(Constant.PERCENT)) {
//                                            changedProperty = Constant.PERCENT;
//                                        }
                                    }
                                }
                            });
                        } else {
                            textField.setReadOnly(true);
                        }
                        
                        return textField;

                    }
                    
                } catch (Exception e) {
                    LOGGER.error(e);
                }

                return null;
            }
        });
        Object[] obj = rightTable.getVisibleColumns();
        for (Object obj1 : obj) {
            if (!String.valueOf(obj1).contains("%")) {

                if(session.getForecastName().equals("Discount Projection")){
                rightTable.setConverter(obj1, salesFormat);
                }else{
                rightTable.setConverter(obj1, salesFormatallocation);
                }
            } else {
                rightTable.setConverter(obj1, percentFormat);
            }
        }
        LOGGER.info("configureTable method ends");
    }

    /**
     * Configure table.
     */
    public void configureDetailsTable() throws Exception {
        LOGGER.info("configureDetailsTable method starts");
        projectionDTO.setForecastDTO(getHistoricalPeriods(session.getForecastDTO()));
        projectionDTO.setHistoryNum(0);
        fullDetHeader = new CustomTableHeaderDTO();
        CustomTableHeaderDTO leftDetDTO = HeaderUtils.getAltHistAllocLeftTableColumns(fullDetHeader);

        projectionDTO.setFrequency(frequency.getValue().toString());
        frequency.setEnabled(false);
        projectionDTO.setProjectionOrder(Constant.ASCENDING);
        projectionDTO.setScreenName(session.getForecastName());
        leftHeader = HeaderUtils.getAltHistAllocRightTableColumns(projectionDTO, session, fullDetHeader);

        resultDetBean = new ExtContainer<AlternateHistoryDTO>(AlternateHistoryDTO.class, ExtContainer.DataStructureMode.MAP);
        resultDetBean.setColumnProperties(leftDetDTO.getProperties());
        resultDetBean.setColumnProperties(leftHeader.getProperties());
//        tableDetLogic = new AltAllocTableLogic();
        tableDetLogic.sinkItemPerPageWithPageLength(false);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        tableDetLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        altDto.setReset(Boolean.FALSE);
        addToQueue = Boolean.FALSE;

        tableDetLogic.setContainerDataSource(resultDetBean);
        resultsDetTable.getLeftFreezeAsTable().setFilterBarVisible(true);
        leftDetTable = resultsDetTable.getLeftFreezeAsTable();
        rightDetTable = resultsDetTable.getRightFreezeAsTable();
        leftDetTable.markAsDirty();
        rightDetTable.markAsDirty();
        leftDetTable.setVisibleColumns(leftDetDTO.getSingleColumns().toArray());
        leftDetTable.setColumnHeaders(leftDetDTO.getSingleHeaders().toArray(new String[leftDetDTO.getSingleHeaders().size()]));
        leftDetTable.setDoubleHeaderVisible(Boolean.FALSE);
//        leftDetTable.setDoubleHeaderVisibleColumns(leftDetDTO.getDoubleColumns().toArray());
//        leftDetTable.setDoubleHeaderColumnHeaders(leftDetDTO.getDoubleHeaders().toArray(new String[leftDetDTO.getDoubleHeaders().size()]));
        resultsDetTable.setHeight("390px");
        leftDetTable.setColumnCheckBox(Constant.CHECKRECORD, true);
        leftDetTable.setHeight("390px");
        rightDetTable.setHeight("390px");
        rightDetTable.setDoubleHeaderVisible(Boolean.TRUE);
        rightDetTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        rightDetTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));
        for (int i = 0; i < leftHeader.getSingleColumns().size(); i++) {
            rightDetTable.setColumnAlignment(leftHeader.getSingleColumns().get(i), ExtCustomTable.Align.LEFT);
            rightDetTable.addStyleName("table-header-left");
        }
        rightDetTable.setDoubleHeaderVisible(true);
        rightDetTable.setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
        rightDetTable.setDoubleHeaderColumnHeaders(leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));
        for (int i = 0; i < leftHeader.getDoubleColumns().size(); i++) {
            rightDetTable.setColumnAlignment(leftHeader.getDoubleColumns().get(i), ExtCustomTable.Align.CENTER);
        }
        for (int i = 0; i < leftDetDTO.getSingleColumns().size(); i++) {
            leftDetTable.setColumnAlignment(leftDetDTO.getSingleColumns().get(i), ExtCustomTable.Align.CENTER);
        }
        rightDetTable.setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps());
        rightDetTable.addStyleName("table-header-center");
        leftDetTable.addStyleName("table-header-center");
        leftDetTable.setDoubleHeaderMap(leftDetDTO.getDoubleHeaderMaps());
//        leftDetTable.setDoubleHeaderMap(leftDetDTO.getDoubleHeaderMaps());
        leftDetTable.setFilterFieldVisible(Constant.CHECKRECORD, false);
        leftDetTable.setFilterDecorator(new ExtDemoFilterDecorator());
        leftDetTable.setEditable(true);
        leftDetTable.setData("selected");
        leftDetTable.addColumnCheckListener(checkListener);
        leftDetTable.setTableFieldFactory(new DefaultFieldFactory() {
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
                 final AlternateHistoryDTO dto = (AlternateHistoryDTO) itemId;
                if (String.valueOf(propertyId).equals(Constant.CHECKRECORD)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    check.setValue(false);
                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            try {
                                logic.check_selected_allocationTab(dto, session, check.getValue() ? 1 : 0, start_stamp, end_stamp);
//                                if (check.getValue()) {
//                                    removeList.add(itemId);
//                                } else {
//                                    removeList.remove(itemId);
//                                }
//                                selCount++;
                            } catch (Exception ex) {
                                LOGGER.error(ex);

                            }
                        }
                    });
                    return check;
                }
                return null;
            }
        });
        allocationDetailsLayout.removeAllComponents();
        allocationDetailsLayout.addComponent(resultsDetTable);
        HorizontalLayout controls = tableDetLogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        allocationDetailsLayout.addComponent(controlLayout);
        LOGGER.info("configureDetailsTable method ends");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    private ForecastDTO getHistoricalPeriods(final ForecastDTO forecastDTO) {
        ForecastDTO dto = new ForecastDTO();
        String[] sDate = session.getStartDate().split("-");
        String[] eDate = session.getEndDate().split("-");
        int startMonth = Integer.parseInt(sDate[1]);
        int endMonth = Integer.parseInt(eDate[1]);
        int startYear = Integer.parseInt(sDate[0]);
        int endYear = Integer.parseInt(eDate[0]);
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
        
        return dto;
    }
    ExtCustomTable.ColumnCheckListener checkListener = new ExtCustomTable.ColumnCheckListener() {
        @Override
        public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {

            
            if (Constant.CHECKRECORD.equals(event.getPropertyId().toString())) {
                String table = (String) ((ExtCustomTable) event.getComponent()).getData();
                if ("available".equalsIgnoreCase(table)) {
                    logic.checkAll_available_allocationTab(session, event.isChecked() ? 1 : 0, start_stamp, end_stamp);
                    altDto.setReset(true);
                    tableLogic.loadSetData(altDto, session, false);
                } else {
                    logic.checkAll_selected_allocationTab(session, event.isChecked() ? 1 : 0, start_stamp, end_stamp);
                    altDto.setReset(true);
                    tableDetLogic.loadSetData(altDto, session, true);
                }
               
            }
        }
    };
    /**
     * add to queue button logic.
     *
     * @param event the event
     */
    @UiHandler("addBtn")
    public void addBtnClick(Button.ClickEvent event) {
        LOGGER.info("Inside addBtnClick");
        try {
            if (logic.count_avalibale_allocationTab(session, start_stamp, end_stamp) == 0) {
                AbstractNotificationUtils.getErrorNotification("No Record Selected",
                        "Please verify that a CCP record is selected.");
                return;
            }
            if (logic.validateAllocation(session.getForecastName(), session, allocatedPeriodsList)) {
                String msg = session.getForecastName().equals(TabNameUtil.DISCOUNT_PROJECTION)
                        ? "Please verify that all Historic Payments have been allocated to the selected CCP."
                        : "Please verify that all Historic Units have been allocated to the selected CCP.";
                AbstractNotificationUtils.getErrorNotification("Not all Historic Time Periods have been Allocated", msg);
                return;
            }
            leftTable.setColumnCheckBox(Constant.CHECKRECORD, true, false);
            logic.addToQueue(session, start_stamp, end_stamp);
            altDto.setReset(true);
            tableLogic.loadSetData(altDto, session, false);
            tableDetLogic.loadSetData(altDto, session, true);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        LOGGER.info("Ending addBtnClick");
    }

    /**
     * remove button logic.
     *
     * @param event the event
     */
    @UiHandler("removeBtn")
    public void removeBtnClick(Button.ClickEvent event) {
        try {
            LOGGER.info("Inside removeBtnClick");
            if (logic.count_selected_allocationTab(session, start_stamp, end_stamp) == 0) {
                AbstractNotificationUtils.getErrorNotification("No Record Selected ",
                        "Please check mark a CCP record in the ‘Allocation Details’ list view to remove. ");
                return;
            }
            leftDetTable.setColumnCheckBox(Constant.CHECKRECORD, true, false);
            logic.remove_selected_allocationTab(session, start_stamp, end_stamp);
            altDto.setReset(true);
            tableDetLogic.loadSetData(altDto, session, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("Ending removeBtnClick");
    }

    /**
     * remove all button logic.
     *
     * @param event the event
     */
    @UiHandler("removeAllBtn")
    public void removeAllBtnClick(Button.ClickEvent event) {
        LOGGER.info("Inside removeAllBtnClick");
        MessageBox.showPlain(Icon.QUESTION, "Remove All Confirmation",
                "Are you sure you want to remove all CCP records from the ‘Allocation Details’ list view?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals("OK")) {
//                            if (logic.count_selected_allocationTab(session, start_stamp, end_stamp) > 0) {
                                leftDetTable.setColumnCheckBox(Constant.CHECKRECORD, true, false);
                                logic.removeAll_selected_allocationTab(session);
                                altDto.setReset(true);
                                tableDetLogic.loadSetData(altDto, session, true);
//                            }
                        }
                    }
                }, ButtonId.OK, ButtonId.CANCEL);

        LOGGER.info("Ending removeAllBtnClick");
    }

    /**
     * remove all button logic.
     *
     * @param event the event
     */
    @UiHandler("resetBtn")
    public void resetBtnClick(Button.ClickEvent event) {

        new AbstractNotificationUtils() {
            public void noMethod() {

            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                LOGGER.info("Entering resetBtn method");
//                resultDetBean.removeAllItems();
//                resultBean.removeAllItems();
                boolean flag = logic.resetAllocationTab(session);
                if (flag) {
                    tableLogic.clearAll();
                    tableDetLogic.clearAll();
                }
                LOGGER.info("End of resetBtn method");
    }
        }.getConfirmationMessage("Confirm Reset", "Are you sure you want to reset the page to default/previous values?");
    }
    
    @UiHandler("refreshBtn")
    public void refreshBtnClick(Button.ClickEvent event) {
        LOGGER.info("Inside refresh Button Click");
        changedProperty = StringUtils.EMPTY;
        tableLogic.loadSetData(altDto, session, false);
        LOGGER.info("Ending  refresh Button Click");
    }
    public boolean validateOnValueChange(){


    
        return true;

    }

    public void throwErrorPopUp(){


     AbstractNotificationUtils.getErrorNotification(" Error", "Please change either Allocation Percentage or "+(session.getForecastName().equals("Sales Projection")?"Units":"Payment")+" for all periods.");
            
    }
    
    private void loadFrequency(String frequency) {
        loadDateRange(frequency);
    }
    
    private void loadDateRange(String freq) {
        LOGGER.info("loadDateRange method starts");
        List<String> dateList = new ArrayList<String>();
    
        int currentQuarter = 0;
        int currentsemiannual = 0;
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        
        if (month >= 0 && 2 >= month) {
            currentQuarter = 1;
            currentsemiannual = 1;
}
        if (month >= 3 && 5 >= month) {
            currentQuarter = 2;
            currentsemiannual = 1;
        }
        if (month >= 6 && 8 >= month) {
            currentQuarter = 2;
            currentsemiannual = 2;
        }
        if (month >= 9 && 11 >= month) {
            currentQuarter = 2;
            currentsemiannual = 2;
        }

        if (freq.equals(Constant.QUARTERLY)) {
            for (int i = 0; i < 11; i++) {
                for (int j = currentQuarter; j > 0; j--) {
                    if (currentQuarter != 1) {
                        String common = Constant.Q + j + year;
                        dateList.add(common);
                        currentQuarter = currentQuarter - 1;
                        i++;
                    } else {
                        year = year - 1;
                    }
                    for (int k = 4; k > 0; k--) {
                        String common = Constant.Q + k + year;
                        dateList.add(common);
                        i++;
                    }

                }
            }
        } /* This Condition is used to load From and To Combobox, If frequency is SEMI_ANNUAL selected */ else if (freq.equals(Constant.SEMI_ANNUALLY)) {
            for (int i = 0; i < 8; i++) {
                for (int j = currentsemiannual; j > 0; j--) {
                    if (currentsemiannual != 1) {
                        String common = Constant.S + j + year;
                        dateList.add(common);
                        currentsemiannual = currentsemiannual - 1;
                        i++;
                    } else {
                        year = year - 1;
                    }

                    for (int k = 2; k > 0; k--) {
                        String common = Constant.S + k + year;
                        dateList.add(common);
                        i++;
                    }

                }
            }
        } /* This Condition is used to load From and To Combobox, If frequency is ANNUAL selected */ else if (freq.equals(Constant.ANNUALLY)) {
            for (int i = 0; i < 3; i++) {
                year = year - 1;
                String commonColumn = String.valueOf(year);
                dateList.add(commonColumn);
            }
        }else{

         for(int i=0;i<36;i++){
                if(month==0){
                month=11;
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
        LOGGER.info("loadDateRange method Ends");
    }
    public static String getMonthForInt(int num) {
        String month = "";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }
    
    public void AvailableExport() {
        configureSelectionExcelResultTable();
        AlternateHistoryLogic logic = new AlternateHistoryLogic();
        List<AlternateHistoryDTO> list = logic.getAlloc(altDto, session, false, tableLogic.getFilters(), 0, 0, Boolean.TRUE);
        excelResultBean.addAll(list);
        ForecastUI.EXCEL_CLOSE=true;
        ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(exportTable), excelName, excelName, excelName + ".xls", false);
        excel.export();
        selectedCustomerTableLayout.removeComponent(exportTable);
    }

    private void configureSelectionExcelResultTable() {

        excelResultBean = new CustomTreeContainer<AlternateHistoryDTO>(AlternateHistoryDTO.class);
        exportTable = new ExtCustomTable();
        selectedCustomerTableLayout.addComponent(exportTable);
        exportTable.setRefresh(Boolean.FALSE);
        exportTable.setVisible(false);
        excelResultBean.setColumnProperties(fullHeader.getProperties());
        exportTable.setContainerDataSource(excelResultBean);
        exportTable.setVisibleColumns(fullHeader.getSingleColumns().toArray());
        exportTable.setColumnHeaders(fullHeader.getSingleHeaders().toArray(new String[fullHeader.getSingleHeaders().size()]));
    }

    public void SelectionExport() {
        configureAvailableExcelResultTable();
        AlternateHistoryLogic logic = new AlternateHistoryLogic();
        List<AlternateHistoryDTO> list = logic.getAlloc(altDto, session, true, tableDetLogic.getFilters(), 0, 0, Boolean.TRUE);
        excelResultBean.addAll(list);
        ForecastUI.EXCEL_CLOSE=true;
        ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(exportTable), excelName, excelName, excelName + ".xls", false);
        excel.export();
        allocationDetailsLayout.removeComponent(exportTable);
    }

    private void configureAvailableExcelResultTable() {

        excelResultBean = new CustomTreeContainer<AlternateHistoryDTO>(AlternateHistoryDTO.class);
        exportTable = new ExtCustomTable();
        allocationDetailsLayout.addComponent(exportTable);
        exportTable.setRefresh(Boolean.FALSE);
        exportTable.setVisible(false);
        excelResultBean.setColumnProperties(fullDetHeader.getProperties());
        exportTable.setContainerDataSource(excelResultBean);
        exportTable.setVisibleColumns(fullDetHeader.getSingleColumns().toArray());
        exportTable.setColumnHeaders(fullDetHeader.getSingleHeaders().toArray(new String[fullDetHeader.getSingleHeaders().size()]));
    }

    

    private void update_start_end_DateStamps() {

        String from_val = session.getAltFromPeriod();
        String to_val = session.getAltToPeriod();
        String frequency_val = this.frequency.getValue().toString();
        int startFrom, yearFrom, startTo, yearTo;

        if (Constant.ANNUALLY.equalsIgnoreCase(frequency_val)) {
            startFrom = 0;
            startTo = 11;
            yearFrom = Integer.valueOf(from_val.trim());
            yearTo = Integer.valueOf(to_val.trim());
        } else if (Constant.MONTHLY.equalsIgnoreCase(frequency_val)) {
            DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
            List<String> months = Arrays.asList(dateFormatSymbols.getShortMonths());
            startFrom = (months.indexOf(StringUtils.capitalize(from_val.substring(0, 3))) );
            startTo = (months.indexOf(StringUtils.capitalize(to_val.substring(0, 3))) );
            yearFrom = Integer.valueOf(from_val.substring(3, 7));
            yearTo = Integer.valueOf(to_val.substring(3, 7));
        } else {
            if (Constant.QUARTERLY.equalsIgnoreCase(frequency_val)) {
                startFrom = Integer.valueOf(from_val.substring(1, 2));
                startFrom = (startFrom * 3) - 3;
                startTo = Integer.valueOf(to_val.substring(1, 2));
                startTo = (startTo * 3)-1;
            } else {
                startFrom = Integer.valueOf(from_val.substring(1, 2));
                startFrom = (startFrom * 6) - 6;
                startTo = Integer.valueOf(to_val.substring(1, 2));
                startTo = (startTo * 6)-1;
}
            yearFrom = Integer.valueOf(from_val.substring(2, 6));
            yearTo = Integer.valueOf(to_val.substring(2, 6));
        }
        if (start_stamp == null) {
            start_stamp = new Date((yearFrom - 1900), startFrom, 1);
        } else {
            start_stamp.setYear(yearFrom - 1900);
            start_stamp.setMonth(startFrom);
            start_stamp.setDate(1);
        }
        if (end_stamp == null) {
            end_stamp = new Date((yearTo - 1900), startTo, 1);
        } else {
            end_stamp.setYear(yearTo - 1900);
            end_stamp.setMonth(startTo);
            end_stamp.setDate(1);
        }
        LOGGER.info("Start_stamp :" + start_stamp);
        LOGGER.info("End_stamp :" + end_stamp);
    }
    
    public void setAllocatedPeriods(final List allocatedPeriods) {
        allocatedPeriodsList.clear();
        allocatedPeriodsList.addAll(allocatedPeriods);
    }
    
    private String getSelectedColumn(String propertyId){
       String returnValue=StringUtils.EMPTY;
        String value = String.valueOf(propertyId);
        if (value.contains("Actual")) {
            returnValue = "Actual";
        } else if (value.contains("Projected")) {
            returnValue = "Projected";
        } else if (value.contains("ActAllocation%")) {
            returnValue = "ActAllocation";
        }else {
            returnValue = "ProjAllocation";
        }
        return returnValue;
    }
    
}
