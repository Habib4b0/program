/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractAdjustmentDetailsLogic;
import com.stpl.app.arm.businessprocess.commontemplates.AdjustmentDetailsTableLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.supercode.DefaultFocusable;
import com.stpl.app.arm.supercode.SelectionCriteria;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import static com.stpl.ifs.ui.util.AbstractNotificationUtils.LOGGER;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.asi.container.ExtContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Jayaram.LeelaRam
 */


public abstract class AbstractAdjustmentDetails extends VerticalLayout implements Details, DefaultFocusable {

    /**
     * Level option group
     */
    @UiField("level")
    protected OptionGroup level;
    /**
     * Result Table Layout
     */
    @UiField("resultTableLayout")
    private VerticalLayout resultTableLayout;
    /**
     * Excel Export Button
     */
    @UiField("export")
    private Button export;
    /**
     * Reserve Account ComboBox
     */
    @UiField("reserveAccount")
    protected CustomMenuBar reserveAccount;

    protected CustomMenuBar.CustomMenuItem reserveMenuItem;
    /**
     * Variables ComboBox
     */
    @UiField("variables")
    protected CustomMenuBar variables;
    /**
     * Amount Filter ComboBox
     */
    @UiField("amountFilterDdlb")
    protected CustomMenuBar amountFilter;
    /**
     * Amount Filter Label
     */
    @UiField("amountFilterLabel")
    protected Label amountFilterLabel;

    protected CustomMenuBar.CustomMenuItem amountFilterItem;

    protected CustomMenuBar.CustomMenuItem customMenuItem;

    protected AdjustmentDetailsTableLogic tableLogic;
    protected ExtPagedTable resultsTable;
    public ExtContainer<AdjustmentDTO> resultsContainer = new ExtContainer<>(AdjustmentDTO.class, ExtContainer.DataStructureMode.LIST);
    public Object[] visibleColumns;
    public String[] visibleHeaders;
    public String[] variableHeader;
    public String[] variableValue;

    protected AbstractSelectionDTO selection;
    protected AbstractAdjustmentDetailsLogic logic;

    @UiField("reset")
    protected Button reset;
    @UiField("generate")
    protected Button generate;
    boolean generateFlag;

    /**
     * Parameterized constructor
     *
     * @param logic
     */
    public AbstractAdjustmentDetails(AbstractAdjustmentDetailsLogic logic, AbstractSelectionDTO selection) {
        this.logic = logic;
        this.selection = selection;
        tableLogic = new AdjustmentDetailsTableLogic(logic, selection);
        resultsTable = new ExtPagedTable(tableLogic);
    }

    /**
     * This method load the clara. To initialize the fields and sets the default
     * values to the fields. To initialize the table
     */
    protected void init() {
        addComponent(Clara.create(getClass().getResourceAsStream("/bussinessprocess/adjustmentDetail.xml"), this));
        configureFields();
        configureTable();
        configureWorkFlow();
        generate();
        reset();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Configures the fields and its default values
     */
    protected void configureFields() {

        level.addItem(GlobalConstants.getReserveDetail());
        level.addItem(GlobalConstants.getGTNDetail());
        level.select(GlobalConstants.getReserveDetail());
        selection.setDetail_Level(level.getValue().toString());
        variables.setScrollable(true);
        variables.setPageLength(NumericConstants.TWENTY);
        reserveAccount.setScrollable(true);
        reserveAccount.setPageLength(NumericConstants.TEN);
        reserveMenuItem = reserveAccount.addItem(GlobalConstants.getSelectVariable(), null);
        customMenuItem = variables.addItem(GlobalConstants.getSelectVariable(), null);
        amountFilterItem = amountFilter.addItem(GlobalConstants.getSelectVariable(), null);
        //Method for setting header value based on Level option group value selected
        loadVariable();
        //For loading Variable menubar
        CommonUtils.loadCustomMenu(customMenuItem, variableHeader, variableValue);
        //For selecting default values inside Variable menubar
        variableDefaultSelection();
        //For loading reserve menubar
        loadReserveAccount();
        //Method for loading amount filter menubar
        loadAmountFilter();

        /**
         * Level value change
         */
        level.addValueChangeListener(levelValueChange);
    }

    protected void configureTable() {
        tableLogic.setContainerDataSource(resultsContainer);
        setTableHeader();
        resultsTable.setSizeFull();
        resultsTable.setSelectable(Boolean.FALSE);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultTableLayout.addComponent(resultsTable);
        HorizontalLayout control = tableLogic.createControls();
        resultTableLayout.addComponent(control);
        export.setPrimaryStyleName("link");
        export.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
    }
    private final CustomNotification notifier = new CustomNotification();

    class CustomNotification extends AbstractNotificationUtils {

        String buttonName;

        @Override
        public void noMethod() {
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :" + buttonName);
            if (null != buttonName) {
                switch (buttonName) {
                    case "reset":
                        // Reset Logic
                        resetBtn();
                        break;
                }
            }
        }

        public void setButtonName(String buttonName) {
            this.buttonName = buttonName;
        }

    }

    public void reset() {
        reset.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                {
                    LOGGER.debug(" Inside Reset Button ");
                    try {
                        notifier.setButtonName("reset");
                        notifier.getOkCancelMessage(ARMMessages.getResetMessageName_001(), ARMMessages.getResetMessageID004());
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                    LOGGER.debug(" Ending Reset Button ");
                }
            }
        });
    }

    public void generate() {
        generate.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                LOGGER.debug(" Inside Generate Button ");
                try {
                    // Generate Logic
                    generateFlag = true;
                    generateBtn();
                } catch (Exception e) {
                    LOGGER.error(e);
                }
                LOGGER.debug(" Ending Generate Button ");
            }
        });
    }

    /**
     * This method sets the header based on the value select in the variable
     * menu. This method will be called while generating on the data selection
     * and during generate in Adjustment details tab
     */
    public void setTableHeader() {
        LOGGER.debug(" Inside setTableHeader ");
        Map properties = new HashMap();
        List<List> columns = CommonUtils.getSelectedVariables(customMenuItem, Boolean.FALSE);
        visibleColumns = ((List) columns.get(0)).toArray();
        visibleHeaders = Arrays.copyOf(columns.get(1).toArray(), columns.get(1).size(), String[].class);
        for (int i = 0; i < visibleColumns.length; i++) {
            properties.put(visibleColumns[i], String.class);
        }
        resultsContainer.setColumnProperties(properties);
        resultsContainer.setRecordHeader(Arrays.asList(visibleColumns));
        resultsTable.setVisibleColumns(visibleColumns);
        resultsTable.setColumnHeaders(visibleHeaders);
        for (int i = 0; i < visibleColumns.length; i++) {
            if (visibleColumns[i].toString().contains("debit") || visibleColumns[i].toString().contains("credit")
                    || visibleColumns[i].toString().contains(ARMConstants.getAmount()) || visibleColumns[i].toString().contains(ARMConstants.getRate())) {
                resultsTable.setColumnAlignment(visibleColumns[i], ExtCustomTable.Align.RIGHT);
            }
            if (visibleColumns[i].toString().contains("debit") || visibleColumns[i].toString().contains("credit")
                    || visibleColumns[i].toString().contains(ARMConstants.getAmount())) {
                resultsTable.setConverter(visibleColumns[i], new DataFormatConverter("#,##0.00", "$"));
            }
            if (visibleColumns[i].toString().contains("Date")) {
                resultsTable.setColumnAlignment(visibleColumns[i], ExtCustomTable.Align.CENTER);
            }
            if ((visibleColumns[i].toString().contains("deductionAmount")) || (visibleColumns[i].toString().contains("deductionRate"))) {
                resultsTable.setColumnAlignment(visibleColumns[i], ExtCustomTable.Align.RIGHT);
            }
        }
        columnAlignmentChanges();
        LOGGER.debug(" Ending setTableHeader ");
    }

    /**
     * Reset Button logic
     */
    private void resetBtnLogic() {
        level.removeValueChangeListener(levelValueChange);
        level.select(GlobalConstants.getReserveDetail());
        level.addValueChangeListener(levelValueChange);
        loadReserveAccount();
        loadVariable();
        CommonUtils.loadCustomMenu(customMenuItem, variableHeader, variableValue);
        variableDefaultSelection();
        loadAmountFilter();
    }

    protected void generateBtn() {
        setTableHeader();
    }

    protected void resetBtn() {
        resetBtnLogic();
    }

    protected abstract void loadReserveAccount();

    protected abstract void loadVariable();

    protected abstract void variableDefaultSelection();

    protected abstract void columnAlignmentChanges();

    @Override
    public boolean saveAssets() {
        return true;
    }

    private void configureFieldsOnViewMode() {
        reset.setEnabled(false);
    }

    public void loadDetails() {

        List<Object[]> list = CommonLogic.loadPipelineAccrual(selection.getProjectionMasterSid());
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);
            if ("detail_Level".equals(String.valueOf(obj[0]))) {
                level.setValue(String.valueOf(obj[1]));
            } else if ("detail_variables".equals(String.valueOf(obj[0]))) {
                String str1 = (String) obj[1];
                String[] str2 = str1.split(",");
                String str3 = null;
                for (String strings : str2) {
                    str3 = strings;
                    CommonUtils.checkMenuBarItem(customMenuItem, str3);
                }
                selection.setSave_detail_variables(Arrays.asList(str2));
            } else if ("detail_reserveAcount".equals(String.valueOf(obj[0]))) {
                reserveMenuItem.removeChildren();

                loadReserveAccount();
                String str1 = (String) obj[1];
                String[] str2 = str1.split(",");
                String str3 = null;
                for (String strings : str2) {
                    str3 = strings;
                    CommonUtils.checkMenuBarItem(reserveMenuItem, str3);
                }
                selection.setDetail_reserveAcount(Arrays.asList(str2));

            } else if (VariableConstants.DETAIL_AMOUNT_FILTER.equals(String.valueOf(obj[0]))) {
                amountFilterItem.removeChildren();
                loadAmountFilter();
                CommonUtils.unCheckMenuBarItem(amountFilterItem);
                String str1 = (String) obj[1];
                String[] str2 = str1.split(",");
                String str3 = null;
                for (String strings : str2) {
                    str3 = strings;
                    CommonUtils.checkMenuBarItemCaption(amountFilterItem, str3);
                }
                selection.setDetail_amountFilter(Arrays.asList(str2));

            } else if (!"sales_variables".equals(String.valueOf(obj[0])) && !"summary_deductionValues".equals(String.valueOf(obj[0]))
                    && !"summary_variables".equals(String.valueOf(obj[0])) && !"rate_DeductionValue".equals(String.valueOf(obj[0]))) {
                try {
                    BeanUtils.setProperty(selection, String.valueOf(obj[0]), obj[1]);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }

            }
        }

    }

    private void configureWorkFlow() {
        if (selection.getSessionDTO().isWorkFlow()) {
            generateFlag = false;
            loadDetails();
            generateBtn();
            if (ARMUtils.VIEW_SMALL.equals(selection.getSessionDTO().getAction())) {
                configureFieldsOnViewMode();
            }
        }
    }

    /**
     * Excel export button logic.
     *
     * @param event
     */
    @UiHandler("export")
    public void exportButtonLogic(Button.ClickEvent event) throws PortalException, SystemException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        createWorkSheet("Adjustment Detail", resultsTable);
    }

    public void createWorkSheet(String moduleName, ExtPagedTable resultTable) throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        long recordCount = 0;
        List<String> visibleList = Arrays.asList(visibleHeaders);
        if (resultTable.size() != 0) {
            SelectionCriteria criteria = new SelectionCriteria();
            criteria.setSelectionDto(selection);
            recordCount = logic.getCount(criteria);
        }
        ExcelExportforBB.createWorkSheet(visibleList.toArray(new String[visibleList.size()]), recordCount, this, UI.getCurrent(), moduleName.toUpperCase());
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException {

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
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public boolean isGenerateFlag() {
        return generateFlag;
    }

    @Override
    public Focusable getDefaultFocusComponent() {
        return level;
    }

    @Override
    public boolean isGenerated() {
        return resultsContainer.size() > 0;
    }

    /**
     * method for default selection on Menu
     */
    protected void loadAmountFilter() {
        CommonUtils.loadCustomMenu(amountFilterItem, Arrays.asList(VariableConstants.AMOUNT_FILTER_OPTIONS));
        List list = Arrays.asList(level.getValue().toString().equals(GlobalConstants.getReserveDetail())
                ? VariableConstants.AMOUNT_FILTER_OPTIONS_RESERVE
                : VariableConstants.AMOUNT_FILTER_OPTIONS_GTN);
        for (CustomMenuBar.CustomMenuItem object : amountFilterItem.getChildren()) {
            if (list.contains(object.getMenuItem().getCaption())) {
                object.setChecked(Boolean.TRUE);
            }
        }
    }

    Property.ValueChangeListener levelValueChange = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            loadReserveAccount();
            loadVariable();
            CommonUtils.loadCustomMenu(customMenuItem, variableHeader, variableValue);
            variableDefaultSelection();
            loadAmountFilter();
        }
    };
    public Button getGenerate() {
        return generate;
    }

    public Button getReset() {
        return reset;
    }

    public Button getExport() {
        return export;
    }
}
