/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction8.form.returnsdata;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.transaction8.dto.RRSelectionDTO;
import com.stpl.app.arm.businessprocess.transaction8.logic.ReturnsDataLogic;
import com.stpl.app.arm.businessprocess.transaction8.logic.tableLogic.ReturnsDataTableLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.supercode.SelectionCriteria;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;

import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.asi.container.ExtContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author
 */
public class ReturnsData extends CustomComponent {

    @UiField("variables")
    protected CustomMenuBar variables;
    @UiField("resultTableLayout")
    protected VerticalLayout resultTableLayout;
    @UiField("export")
    protected Button export;
    protected CustomMenuBar.CustomMenuItem customMenuItem;
    private ExtContainer<AdjustmentDTO> resultsContainer = new ExtContainer<>(AdjustmentDTO.class, ExtContainer.DataStructureMode.LIST);
    private final ExtPagedTable resultsTable;
    private ReturnsDataTableLogic tableLogic;
    private final ReturnsData.CustomNotification notifier = new ReturnsData.CustomNotification();
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private Object[] visibleColumns;
    private String[] visibleHeaders;
    private ReturnsDataLogic logic = new ReturnsDataLogic();
    protected RRSelectionDTO selection;

    public ReturnsData(RRSelectionDTO selection) {
        this.selection = selection;
        tableLogic = new ReturnsDataTableLogic(logic, selection);
        resultsTable = new ExtPagedTable(tableLogic);
        init();
        configureWorkFlow();
    }

    private void init() {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/bussinessprocess/returnsReserve/returnsdata.xml"), this));
        configureFields();
    }

    private void configureFields() {
        customMenuItem = variables.addItem(GlobalConstants.getSelectVariable(), null);
        variables.setScrollable(true);
        variables.setPageLength(NumericConstants.TEN);
        CommonUtils.loadCustomMenu(customMenuItem, ARMUtils.getReturnsDataHeaders(), ARMUtils.getRetrunsDataColumns());
        loadFirstTimeVariables();
        configureTable();
    }

    private void configureTable() {
        tableLogic.setContainerDataSource(resultsContainer);
        setTableHeader();
        resultTableLayout.addComponent(resultsTable);
        HorizontalLayout control = tableLogic.createControls();
        resultTableLayout.addComponent(control);
        export.setPrimaryStyleName("link");
        export.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
    }

    @UiHandler("reset")
    public void resetBtnLogic(Button.ClickEvent event) {
        notifier.setButtonName("reset");
        notifier.getOkCancelMessage(ARMMessages.getResetMessageName_001(), ARMMessages.getResetMessageID004());
    }

    private void loadFirstTimeVariables() {
        List list = Arrays.asList(ARMUtils.getDefaultCheckedCols());
        for (CustomMenuBar.CustomMenuItem object : customMenuItem.getChildren()) {
            object.setChecked(Boolean.FALSE);
            if (list.contains(object.getMenuItem().getWindow())) {
                object.setChecked(Boolean.TRUE);
            }
        }
    }

    public Focusable getDefaultFocusComponent() {
        return variables;
    }

    public class CustomNotification extends AbstractNotificationUtils {

        private String buttonName;

        public CustomNotification() {
            /*
        THE DEFAULT CONSTRUCTOR
             */
        }

        @Override
        public void noMethod() {
            LOGGER.debug("Inside CustomNotification NO Method");
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :{}", buttonName);
            if (null != buttonName && "reset".equals(buttonName)) {
                loadFirstTimeVariables();
            }

        }

        public void setButtonName(String buttonName) {
            this.buttonName = buttonName;
        }

    }

    @UiHandler("generate")
    public void generateBtnLogic(Button.ClickEvent event) {
        List<String> columns = Arrays.asList(ARMUtils.getRetrunsDataColumns());
        selection.setReturnsdatavariables(columns);
        selection.setReturnsdataSelectedvariables(CommonUtils.getSelectedVariables(customMenuItem));
        resultsTable.setFilterBarVisible(true);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        setTableHeader();
        tableLogic.loadsetData(logic, true);
    }

    public void setTableHeader() {
        logger.debug(" Inside setTableHeader ");
        Map properties = new HashMap();
        List<List> columns = CommonUtils.getSelectedVariables(customMenuItem, Boolean.FALSE);
        visibleColumns = (columns.get(NumericConstants.THREE)).toArray();
        visibleHeaders = Arrays.copyOf(columns.get(1).toArray(), columns.get(1).size(), String[].class);
        for (Object visibleColumn : visibleColumns) {
            properties.put(visibleColumn, String.class);
        }
        resultsContainer.setColumnProperties(properties);
        resultsContainer.setRecordHeader(Arrays.asList(visibleColumns));
        resultsTable.setVisibleColumns(visibleColumns);
        resultsTable.setColumnHeaders(visibleHeaders);
        resultsTable.setSizeFull();
        resultsTable.setSelectable(Boolean.FALSE);
        resultsTable.setFilterBarVisible(true);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setStyleName("filtertable");
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        for (Object column : resultsTable.getVisibleColumns()) {
            if (Arrays.asList(ARMUtils.getPercentTwoDecColumns()).contains(column)) {
                resultsTable.setConverter(column, new DataFormatConverter("#,##0.00", "%"));
            }
            if (Arrays.asList(ARMUtils.getCurrencyTwoDecColumns()).contains(column)) {
                resultsTable.setConverter(column, new DataFormatConverter("#,##0.00", "$"));
            }
            if (Arrays.asList(ARMUtils.getDateAlignColumns()).contains(column)) {
                resultsTable.setColumnAlignment(column, ExtCustomTable.Align.CENTER);
            }
            if (Arrays.asList(ARMUtils.getRightAlignColumns()).contains(column)) {
                resultsTable.setColumnAlignment(column, ExtCustomTable.Align.RIGHT);
            }
        }
        logger.debug(" Ending setTableHeader ");
    }

    @UiHandler("export")
    public void exportButtonLogic(Button.ClickEvent event) {
        createWorkSheet("Returns Data", resultsTable);
    }

    public void createWorkSheet(String moduleName, ExtPagedTable resultTable) {
        long recordCount = 0;
        List<String> visibleList = Arrays.asList(visibleHeaders);
        if (resultTable.size() != 0) {
            SelectionCriteria criteria = new SelectionCriteria();
            criteria.setSelectionDto(selection);
            recordCount = logic.getCount(criteria);
        }
        try {
            ExcelExportforBB.createWorkSheet(visibleList.toArray(new String[visibleList.size()]), recordCount, this, UI.getCurrent(), moduleName.toUpperCase(Locale.ENGLISH));
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            logger.error(ex.getMessage());
        }
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) {

        List visibleList = Arrays.asList(visibleColumns);
        try {
            if (end != 0) {
                SelectionCriteria criteria = new SelectionCriteria();
                criteria.setSelectionDto(selection);
                criteria.setStart(start);
                criteria.setOffset(end);
                final List<AdjustmentDTO> searchList = logic.getData(criteria).getDataResults();
                ExcelExportforBB.createFileContent(visibleList.toArray(), searchList, printWriter);
            }
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            logger.error("Error in createWorkSheetContent :", e);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void loadDetails() {
        List<Object[]> list = CommonLogic.loadReturnReserve(selection.getDataSelectionDTO().getProjectionId(), VariableConstants.RETURNS_DATA_VARIABLES_FIELD);
        if (!list.isEmpty()) {
            Object[] obj = list.get(0);
            String str1 = (String) obj[1];
            String[] str2 = str1.split(",");
            for (String strings : str2) {
                CommonUtils.checkMenuBarItemCaption(customMenuItem, strings);
            }
            generateBtnLogic(null);
        }
    }

    private void configureWorkFlow() {
        if (selection.getSessionDTO().isWorkFlow()) {
            loadDetails();
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
