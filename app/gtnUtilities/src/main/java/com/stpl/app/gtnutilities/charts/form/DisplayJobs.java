package com.stpl.app.gtnutilities.charts.form;

import com.stpl.app.gtnutilities.util.Constants;
import com.stpl.app.gtnutilities.charts.dto.JobDTO;
import com.stpl.app.gtnutilities.charts.logic.SearchLogic;
import com.stpl.app.gtnutilities.charts.logic.TableLogic;
import com.stpl.app.gtnutilities.util.CommonMethods;
import com.stpl.app.gtnutilities.util.FilterGenerator;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import static com.vaadin.server.Sizeable.UNITS_PERCENTAGE;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
public class DisplayJobs extends CustomComponent implements View {

    private static final Logger LOGGER = Logger.getLogger(DisplayJobs.class);
    @UiField("searchBtn")
    private Button searchBtn;
    public BeanItemContainer<JobDTO> resultsBean = new BeanItemContainer<JobDTO>(JobDTO.class);
    @UiField("tableLayout")
    VerticalLayout tableLayout;
    @UiField("jobName")
    TextField jobName;
    @UiField("selectType")
    OptionGroup selectType;
    @UiField("excelExport")
    private Button excelExport;
    private HorizontalLayout controlLayout = new HorizontalLayout();
    TableLogic tableLogic = new TableLogic();
    ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    SearchLogic searchLogic = SearchLogic.getInstance();
    /**
     * Instantiates a new DisplayJobs
     */
    public DisplayJobs() {

        super();
        try {
            init();
            configureFields();
            addButtonListeners();
        } catch (Exception ex) {
            LOGGER.debug(ex);
        }
    }

    /**
     * Initial Configuration
     */
    private void init() {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/DisplayJobs.xml"), this));
        configureTable();
    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        selectType.addItem(Constants.ENABLED);
        selectType.addItem(Constants.DISABLED);
        selectType.addItem(Constants.ALL);
        selectType.setValue(Constants.ALL);
        selectType.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                 tableLogic.configureSearchData(getInputArray(), Constants.Schedule_Job);
                 setFilterConfig();
            }
        });
        CommonMethods.configureExcelButton(excelExport);

    }

    private void configureTable() {
        tableLayout.addComponent(resultsTable);
        CommonMethods.getResponsiveControls(tableLogic.createControls(), controlLayout);
        tableLayout.addComponent(controlLayout);
        tableLogic.setContainerDataSource(resultsBean);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setVisibleColumns(Constants.JOB_TABLE_COLUMNS);
        resultsTable.setColumnHeaders(Constants.JOB_TABLE_HEADER);
        resultsTable.setImmediate(true);
        resultsTable.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
        resultsTable.setColumnWidth("enabled", 250);
        resultsTable.setColumnWidth("description", 250);
        resultsTable.setHeight("250px");
        resultsTable.setSelectable(true);
        resultsTable.markAsDirty();
        resultsTable.setValidationVisible(false);
        tableLogic.configureSearchData(getInputArray(), Constants.Schedule_Job);
        setFilterConfig();
    }

    private void addButtonListeners() {
        searchBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final Button.ClickEvent event) {
                 tableLogic.configureSearchData(getInputArray(), Constants.Schedule_Job);
                 setFilterConfig();
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

    public void setFilterConfig() {
        resultsTable.setFilterBarVisible(true);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setFilterGenerator(new FilterGenerator());
        resultsTable.setFilterFieldVisible("occurs", false);
        resultsTable.setFilterFieldVisible("occurs_detail", false);
        resultsTable.setFilterFieldVisible("frequency", false);
      
    }

    private void createWorkSheet() {
        try {
            List list = searchLogic.fetchDataFromDBForJOb(getInputArray(), NumericConstants.ZERO, NumericConstants.ZERO, true, tableLogic.getFilters(), null, true);
            final long recordCount = list == null ? NumericConstants.ZERO : Integer.valueOf(String.valueOf(list.get(NumericConstants.ZERO)));
            ExcelExportforBB.createWorkSheet(resultsTable.getColumnHeaders(), recordCount, this, getUI(), Constants.Schedule_Job);
        } catch (Exception ex) {
            LOGGER.debug(ex);
        }
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException {
        String value = null;
        try {
            List resultList = searchLogic.fetchDataFromDBForJOb(getInputArray(), start, end, false, tableLogic.getFilters(), null, true);
            ResultSet resultSet = (ResultSet) resultList.get(NumericConstants.ZERO);
            while (resultSet.next()) {
                for (int j = NumericConstants.ONE; j < NumericConstants.NINE; j++) {
                    value = String.valueOf(resultSet.getString(j)).trim().toLowerCase().equals(Constants.NULL_STRING)
                            ? Constants.EMPTY : String.valueOf(resultSet.getString(j)).trim();
                    printWriter.print(String.format(Constants.EXCEL_STRING_FORMAT,value));
                }

                value = String.valueOf(resultSet.getString(NumericConstants.NINE)).trim().toLowerCase().equals(Constants.NULL_STRING)
                        ? Constants.EMPTY : String.valueOf(resultSet.getString(NumericConstants.NINE));
                // adding /n  to Mark  end of a Row
                printWriter.println(String.format(Constants.EXCEL_STRING_FORMAT, value));
            }
            searchLogic.closeConnection();
        } catch (Exception ex) {
            LOGGER.debug(ex);
        }

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    private Object[] getInputArray() {
        String jobNameValue = String.valueOf(jobName.getValue());
        Object[] input = {jobNameValue.replaceAll(Constants.STAR, Constants.PERCENT), selectType.getValue()};
        return input;
    }
}
