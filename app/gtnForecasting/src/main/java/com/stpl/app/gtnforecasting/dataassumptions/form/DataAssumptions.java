/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dataassumptions.form;

import com.stpl.app.gtnforecasting.dataassumptions.dto.DataAssumptionDTO;
import com.stpl.app.gtnforecasting.dataassumptions.logic.tableLogic.DataAssumptionstableLogic;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import static com.stpl.app.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import com.stpl.app.utils.DateToStringConverter;
import com.stpl.app.utils.TableHeaderColumnsUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CsvExportforPagedTable;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.jboss.logging.Logger;

/**
 *
 * @author Rohit.Vignesh
 */
public class DataAssumptions extends CustomComponent {

    @UiField("daPanel")
    protected Panel daPanel;

    @UiField("excelExport")
    protected Button excelExport;

    @UiField("tableLayout")
    protected VerticalLayout tableLayout;

    @UiField("controlLayout")
    protected HorizontalLayout controlLayout;

    @UiField("excelButtonLayout")
    protected HorizontalLayout excelButtonLayout;

    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    private static final Logger LOGGER = Logger.getLogger(DataAssumptions.class);
    DataAssumptionstableLogic tableLogic = new DataAssumptionstableLogic();
    private final ExtPagedTable resultTable = new ExtPagedTable(tableLogic);
    public BeanItemContainer<DataAssumptionDTO> resultsContainer = new BeanItemContainer<>(DataAssumptionDTO.class);

    protected SessionDTO session;
    DataAssumptionDTO dataAssumptionDTO;

    public DataAssumptions(SessionDTO session) {
        this.session = session;
        init();
        configureTable();
    }

    private void init() {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/dataassumptions.xml"), this));
        excelExport.setIcon(excelExportImage);
    }

    private void configureTable() {

        tableLayout.addComponent(resultTable);
        HorizontalLayout controls = tableLogic.createControls();
        controlLayout = CommonLogic.getResponsiveControls(controls);
        tableLayout.addComponent(controlLayout);

        tableLogic.setContainerDataSource(resultsContainer);
        resultTable.setVisibleColumns(TableHeaderColumnsUtil.getInstance().dataAssumptionColumns);
        resultTable.setColumnHeaders(TableHeaderColumnsUtil.getInstance().dataAssumptionHeaders);
        resultTable.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultTable.setComponentError(null);
        resultTable.setSizeFull();
        resultTable.markAsDirty();
        resultTable.setFilterBarVisible(true);
        resultTable.setImmediate(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.setValidationVisible(false);
        resultTable.addStyleName("filterbar");
        resultTable.setFilterBarVisible(true);
        tableLogic.configureSearchData(dataAssumptionDTO, session);
        resultTable.setColumnAlignment("activeFileFromDate", ExtCustomTable.Align.CENTER);
        resultTable.setColumnAlignment("activeFileFromPeriod", ExtCustomTable.Align.CENTER);
        resultTable.setColumnAlignment("activeFileToPeriod", ExtCustomTable.Align.CENTER);
        resultTable.setConverter("activeFileFromDate", new DateToStringConverter()); 
        resultTable.setConverter("activeFileFromPeriod", new DateToStringConverter()); 
        resultTable.setConverter("activeFileToPeriod", new DateToStringConverter()); 
        
        excelExport.setStyleName("link");
        excelExport.setDescription("Export to excel");
        excelExport.setIconAlternateText("Excel export");
        excelExport.setHtmlContentAllowed(true);
        excelExport.addClickListener(new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    try {
                       createWorkSheet();
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                }
            });
    }
    
    private void createWorkSheet() {
        LOGGER.info("Entering createWorkSheet");
        try {
            CsvExportforPagedTable.createWorkSheet(resultTable.getColumnHeaders(),TableHeaderColumnsUtil.getInstance().dataAssumptionsColumnsExcel , tableLogic, "Data Assumptions");
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
