/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnutilities.charts.form;

import com.stpl.app.gtnutilities.util.Constants;
import com.stpl.app.gtnutilities.charts.dto.ChartsDTO;
import com.stpl.app.gtnutilities.charts.logic.SearchLogic;
import com.stpl.app.gtnutilities.charts.logic.TableLogic;
import com.stpl.app.gtnutilities.util.CommonMethods;
import com.stpl.app.gtnutilities.util.FilterGenerator;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import static com.vaadin.server.Sizeable.UNITS_PERCENTAGE;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author Karthik.Raja
 */
public class QueryStatistics extends CustomComponent implements View {

    private static final Logger LOGGER = Logger.getLogger(QueryStatistics.class);
    @UiField("cpuBtn")
    private Button cpuBtn;
    @UiField("durationBtn")
    private Button durationBtn;
    @UiField("totalioBtn")
    private Button totalioBtn;
    public BeanItemContainer<ChartsDTO> resultsBean = new BeanItemContainer<ChartsDTO>(ChartsDTO.class);
    @UiField("tableLayout")
    VerticalLayout tableLayout;
    @UiField("selecteDate")
    public PopupDateField selecteDate;
    @UiField("selectType")
    OptionGroup selectType;
    @UiField("databaseDdlb")
    ComboBox databaseDdlb;
    @UiField("excelExport")
    private Button excelExport;
    private HorizontalLayout controlLayout = new HorizontalLayout();
    TableLogic tableLogic = new TableLogic();
    ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    private String selectedParameter = Constants.CPU;
    SearchLogic searchLogic = SearchLogic.getInstance();
    /**
     * Instantiates a new QueryStatistics
     */
    public QueryStatistics() {

        super();
        try {
            init();
            configureFields();
            addButtonListeners();
        } catch (Exception e) {

            LOGGER.debug(e);
        }
    }

    /**
     * Inits the.
     */
    private void init() {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/ChartsQueriesForm.xml"), this));
        configureTable();
    }

    /**
     * (non-Javadoc).
     *
     * @param event the event
     * @see
     * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        return;
    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        cpuBtn.addStyleName(Reindeer.BUTTON_LINK);
        durationBtn.addStyleName(Reindeer.BUTTON_LINK);
        totalioBtn.addStyleName(Reindeer.BUTTON_LINK);
        selectType.addItem(Constants.Long_Running_Queries);
        selectType.addItem(Constants.Stored_Procedure_Info);
        selectType.addItem(Constants.Index_Fragmentation_Statistics);
        selectType.select(Constants.Long_Running_Queries);
        selecteDate.setValue(CommonMethods.getYesterdayDate());
        selecteDate.setDateFormat(Constants.Hour_Date_Format);
        selecteDate.setResolution(Resolution.SECOND);
        selecteDate.setImmediate(true);
        Property.ValueChangeListener listener = new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                refreshTable();

            }
        };
        selecteDate.addValueChangeListener(listener);
        selectType.addValueChangeListener(listener);
        loadDatabaseDdlb();
        databaseDdlb.addValueChangeListener(listener);
        CommonMethods.configureExcelButton(excelExport);
    }

    private void configureTable() {
        tableLayout.addComponent(resultsTable);
        CommonMethods.getResponsiveControls(tableLogic.createControls(), controlLayout);
        tableLayout.addComponent(controlLayout);
        tableLogic.setContainerDataSource(resultsBean);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        setVisibleColHeaders(Constants.getQueryTableColumns(), Constants.getQueryTableHeader());
        resultsTable.setFilterBarVisible(true);
        resultsTable.setImmediate(true);
        resultsTable.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
        resultsTable.setHeight("250px");
        resultsTable.setSelectable(true);
        resultsTable.markAsDirty();
        resultsTable.setValidationVisible(false);
    }

    private void addButtonListeners() {
        totalioBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final Button.ClickEvent event) {
                selectedParameter = Constants.IO;
                refreshTable();
            }
        });
        durationBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final Button.ClickEvent event) {
                selectedParameter = Constants.LONG;
                refreshTable();
            }
        });
        cpuBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final Button.ClickEvent event) {
                selectedParameter = Constants.CPU;
                refreshTable();
            }
        });
        excelExport.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    createWorkSheet();
                } catch (Exception ex) {
                   LOGGER.debug(ex);
                }
            }
        });
    }

    public void refreshTable() {
        String typeValue = String.valueOf(selectType.getValue());
        tableLogic.configureSearchData(getInputArray(), Constants.Query_Statistics);
        if (typeValue.equals(Constants.Long_Running_Queries)) {
            setButtonVisibility(true);
            setVisibleColHeaders(Constants.getQueryTableColumns(), Constants.getQueryTableHeader());
        } else if (typeValue.equals(Constants.Stored_Procedure_Info)) {
            setButtonVisibility(true);
            setVisibleColHeaders(Constants.getProcedureTableColumns(), Constants.getProcedureTableHeader());
        } else {
            setButtonVisibility(false);
            setVisibleColHeaders(Constants.getIndexTableColumns(), Constants.getIndexTableHeader());
        }
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setFilterGenerator(new FilterGenerator());
    }

    void setButtonVisibility(boolean visibility) {
        cpuBtn.setVisible(visibility);
        durationBtn.setVisible(visibility);
        totalioBtn.setVisible(visibility);
    }
    void setVisibleColHeaders(Object[] columns, String[] headers) {
        resultsTable.setVisibleColumns(columns);
        resultsTable.setColumnHeaders(headers);
    }

    private void loadDatabaseDdlb() {
        databaseDdlb.addItem(Constants.SELECT_ONE);
        databaseDdlb.setNullSelectionAllowed(true);
        databaseDdlb.setNullSelectionItemId(Constants.SELECT_ONE);
        databaseDdlb.addItems(HelperTableLocalServiceUtil.executeSelectQuery("select name from sys.databases"));
        databaseDdlb.select(Constants.SELECT_ONE);
    }
    private void createWorkSheet() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
      
        List list = searchLogic.fetchDataFromDB(getInputArray(),NumericConstants.ZERO, NumericConstants.ZERO, true, tableLogic.getFilters(), null, true);
        final long recordCount = list == null ? NumericConstants.ZERO : Integer.valueOf(String.valueOf(list.get(NumericConstants.ZERO)));
        ExcelExportforBB.createWorkSheet(resultsTable.getColumnHeaders(), recordCount, this, getUI(),
                (String.valueOf(selectType.getValue()) + Constants.UNDERSCORE + selectedParameter));
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter)  {
        String value = null;
        List<Object[]> resultList = searchLogic.fetchDataFromDB(getInputArray(), start, end, false, tableLogic.getFilters(), null, true);
        for (int i = NumericConstants.ZERO; i < resultList.size(); i++) {
            Object[] objects = resultList.get(i);
            for (int j = NumericConstants.ZERO; j < objects.length - NumericConstants.ONE; j++) {
                value = String.valueOf(objects[j]).trim().toLowerCase().equals(Constants.NULL_STRING)
                        ? Constants.EMPTY : String.valueOf(objects[j]).trim();
                 printWriter.print(String.format(Constants.EXCEL_STRING_FORMAT,  value.replaceAll(Constants.QUOTE, Constants.DOUBLE_QUOTE)));
            }
            value = String.valueOf(objects[objects.length - NumericConstants.ONE]).trim().toLowerCase().equals(Constants.NULL_STRING)
                    ? Constants.EMPTY : String.valueOf(objects[objects.length - NumericConstants.ONE]).trim();
            if (String.valueOf(selectType.getValue()).equals(Constants.Stored_Procedure_Info)
                    || String.valueOf(selectType.getValue()).equals(Constants.Long_Running_Queries)) {
                value = searchLogic.truncateValuesBasedOnType(selectedParameter,  value);
            }
            // adding /n  to Mark  end of a Row
           printWriter.println(String.format(Constants.EXCEL_STRING_FORMAT,value));
        }

    }

    private Object[] getInputArray() {
       String typeValue = String.valueOf(selectType.getValue());
        String sqlDate = new SimpleDateFormat(Constants.yyyy_MM_dd_HH_mm_ss).format(selecteDate.getValue());
        String dbValue = String.valueOf(databaseDdlb.getValue());
        String database = databaseDdlb.getValue() == null || dbValue.equals(Constants.SELECT_ONE) ? Constants.EMPTY : dbValue;
        Object[] input = {selectedParameter, sqlDate, typeValue, selecteDate.getValue(), database};
        return input;
    }
}
