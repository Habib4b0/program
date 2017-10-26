/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.accountclose.gtnbalancereport.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.accountclose.gtnbalancereport.dto.LiabilitySummaryDto;
import com.stpl.app.accountclose.gtnbalancereport.logic.tableLogic.BalanceReportLogic;
import com.stpl.app.accountclose.gtnbalancereport.utils.HeaderUtils;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Santanukumar
 */
public class LiabilitySummaryPopup extends Window{
    @UiField("lsummaryLayout")
    public VerticalLayout lsummaryLayout;
    public ExtFilterTable adjustmentImpactTable = new ExtFilterTable();
    private BeanItemContainer<LiabilitySummaryDto> liabilityContainer = new BeanItemContainer<LiabilitySummaryDto>(LiabilitySummaryDto.class);
    SessionDTO sessionDTO;
    @UiField("closeBtn")
    public Button closeBtn;
    BalanceReportLogic reportLogic =new BalanceReportLogic();
    @UiField("exportBtn")
    public Button exportBtn;
    private BeanItemContainer<LiabilitySummaryDto> excelResultBean = new BeanItemContainer<LiabilitySummaryDto>(LiabilitySummaryDto.class);
    private ExtCustomTable excelTable = new ExtCustomTable();
    public static final Logger LOGGER = Logger.getLogger(LiabilitySummaryPopup.class);
    public LiabilitySummaryPopup(SessionDTO sessionDTO) {
        super(StringUtils.EMPTY);
        addStyleName("valo-theme-customwindow");
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        this.sessionDTO=sessionDTO;
        init();
    }

    public void init() {
        setClosable(true);
        setModal(true);
        setContent(Clara.create(getClass().getResourceAsStream("/GTNLiabilitySummaryPopup.xml"), this));
        configureFields();
    }

    private void configureFields() {
        adjustmentImpactTable.setContainerDataSource(liabilityContainer);
        adjustmentImpactTable.setVisibleColumns(HeaderUtils.LIBILITY_SUMMARY_COLUMNS);
        adjustmentImpactTable.setColumnHeaders(HeaderUtils.LIBILITY_SUMMARY_HEADERS);
        adjustmentImpactTable.setImmediate(true);
        adjustmentImpactTable.setWidth("400px");
        adjustmentImpactTable.setHeight("400px");
        adjustmentImpactTable.setColumnAlignment("year", ExtCustomTable.Align.CENTER);
        adjustmentImpactTable.setColumnAlignment("actuals", ExtCustomTable.Align.RIGHT);
        adjustmentImpactTable.setColumnAlignment("accruals", ExtCustomTable.Align.RIGHT);
        adjustmentImpactTable.setColumnAlignment("remainingEstimate", ExtCustomTable.Align.RIGHT);
        lsummaryLayout.addComponent(adjustmentImpactTable);
        loadResultTable();
    }
     /**
     * Close btn logic.
     *
     * @param event the event
     */
    @UiHandler("closeBtn")
    public void closeBtnLogic(Button.ClickEvent event) {
        close();
    }

    private void loadResultTable() {
        
        List<LiabilitySummaryDto> resultList=reportLogic.getLiabilityResults(sessionDTO);
        liabilityContainer.addAll(resultList);
    }
    @UiHandler("exportBtn")
    public void resultsexcelExport(Button.ClickEvent event) {
        try {
            List<LiabilitySummaryDto> list = reportLogic.getLiabilityResults(sessionDTO);
            for (LiabilitySummaryDto summary : list) {
                excelResultBean.addBean(summary);
            }
            configureresultExcelTable();
        } catch (Exception e) {
            LOGGER.error(e);
        }
        try {
            ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(excelTable),"Liability Summary", "Liability Summary", "Liability_Summary.xls", false);
            excel.export();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void configureresultExcelTable() {
        lsummaryLayout.addComponent(excelTable);
        excelTable.setRefresh(Boolean.FALSE);
        excelTable.setVisible(false);
        excelTable.setContainerDataSource(excelResultBean);
        excelTable.setVisibleColumns(adjustmentImpactTable.getVisibleColumns());
        excelTable.setColumnHeaders(adjustmentImpactTable.getColumnHeaders());
    }
}
