/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.form;

import org.asi.container.ExtTreeContainer;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.dto.SalesTabDTO;
import com.stpl.app.gcm.tp.dto.TabSelectionDTO;
import com.stpl.app.gcm.tp.logic.LoadTabLogic;
import com.stpl.app.gcm.tp.tablelogic.SalesTabTableLogic;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.EXCEL_IMAGE_PATH;
import com.stpl.app.gcm.util.CustomExcelExport;
import com.stpl.app.gcm.util.HeaderUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtCustomTreeTable;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author alok.v
 */
public class SummarySalesProjectionTransfer extends VerticalLayout implements View {

    @UiField("tradingPartnerSalesTableLayout")
    private VerticalLayout tradingPartnerSalesTableLayout;
    @UiField("excelBtn")
    public Button excelBtn;
    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(SummarySalesProjectionTransfer.class);
    private CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    private CustomTableHeaderDTO rightDTO;
    private final ExtTreeContainer<SalesTabDTO> resultBean = new ExtTreeContainer<>(SalesTabDTO.class,ExtContainer.DataStructureMode.MAP);
    /**
     * The map left visible columns.
     */
    private Map<Object, Object[]> mapLeftVisibleColumns = new HashMap<>();
    /**
     * The map right visible columns.
     */
    private Map<Object, Object[]> mapRightVisibleColumns = new HashMap<>();
    private ExtFilterTreeTable leftTable;
    private ExtFilterTreeTable rightTable;
    private final SalesTabTableLogic tableLogic = new SalesTabTableLogic();
    private final FreezePagedTreeTable resultsTable = new FreezePagedTreeTable(tableLogic);
    private TabSelectionDTO selectionDTO = new TabSelectionDTO();
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    private final SessionDTO session;
    private ExtCustomTreeTable exportPeriodViewTable;
    private ExtTreeContainer<SalesTabDTO> excelResultBean = new ExtTreeContainer<>(SalesTabDTO.class, ExtContainer.DataStructureMode.MAP);
    private final LoadTabLogic tabLogic = new LoadTabLogic();

    /**
     * The max split position.
     */
    private final float maxSplitPosition = 1000;
    /**
     * The min split position.
     */
    private final float minSplitPosition = NumericConstants.TWO_HUNDRED;
    /**
     * The split position.
     */
    private final float splitPosition = 300;
    private ExtFilterTable resultTable;

    public SummarySalesProjectionTransfer(ExtFilterTable resultTable, SessionDTO session) {
        this.setResultTable(resultTable);
        this.session = session;
        tableLogic.setSession(session);
        getContent();
    }

    public void getContent() {
        LOGGER.debug("getContent method starts");

        addComponent(Clara.create(getClass().getResourceAsStream("/summarySalesProjectionTransfer.xml"), this));
        configureFields();
        configureTable();
        loadResultTable(true);

        LOGGER.debug("getContent method ends");

    }

    private void configureFields() {
        initializeResultTable();
        excelBtn.setIcon(excelExportImage);
    }

    /**
     * Configure table.
     */
    public void configureTable() {
        fullHeader = new CustomTableHeaderDTO();
         CustomTableHeaderDTO leftDTO = HeaderUtils.getSalesTabLeftTableColumns(fullHeader);
        rightDTO = HeaderUtils.getSalesTabsRightTableColumns(fullHeader, Constants.QUARTERLY);
      
        resultBean.setColumnProperties(leftDTO.getProperties());
        resultBean.setColumnProperties(rightDTO.getProperties());
        tableLogic.setTreeNodeMultiClick(false);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);

        tableLogic.setContainerDataSource(resultBean);
        leftTable = resultsTable.getLeftFreezeAsTable();
        rightTable = resultsTable.getRightFreezeAsTable();
        leftTable.markAsDirty();
        rightTable.markAsDirty();
        leftTable.setVisibleColumns(leftDTO.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftDTO.getSingleHeaders().toArray(new String[leftDTO.getSingleHeaders().size()]));
        leftTable.setDoubleHeaderVisible(true);
        leftTable.setDoubleHeaderVisibleColumns(leftDTO.getDoubleColumns().toArray());
        leftTable.setDoubleHeaderColumnHeaders(leftDTO.getDoubleHeaders().toArray(new String[leftDTO.getDoubleHeaders().size()]));
        leftTable.setColumnWidth("levelValue", NumericConstants.FOUR_HUNDRED);
        mapLeftVisibleColumns = leftDTO.getDoubleHeaderMaps();
        rightTable.setVisibleColumns(rightDTO.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));
        for (int i = 0; i < rightDTO.getSingleColumns().size(); i++) {
            rightTable.setColumnAlignment(rightDTO.getSingleColumns().get(i), ExtCustomTable.Align.RIGHT);
        }
        rightTable.setDoubleHeaderVisible(true);
        rightTable.setDoubleHeaderVisibleColumns(rightDTO.getDoubleColumns().toArray());
        rightTable.setDoubleHeaderColumnHeaders(rightDTO.getDoubleHeaders().toArray(new String[rightDTO.getDoubleHeaders().size()]));
        for (int i = 0; i < rightDTO.getDoubleColumns().size(); i++) {
            rightTable.setColumnAlignment(rightDTO.getDoubleColumns().get(i), ExtCustomTable.Align.CENTER);
        }
        mapRightVisibleColumns = rightDTO.getDoubleHeaderMaps();

        resultsTable.setDoubleHeaderMap(mapLeftVisibleColumns, mapRightVisibleColumns);
        rightTable.addDoubleHeaderColumnCheckListener(new ExtCustomTable.DoubleHeaderColumnCheckListener() {
            @Override
            public void doubleHeaderColumnCheck(
                    ExtCustomTable.DoubleHeaderColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked()
                        + "\nPrropertyId: " + event.getPropertyId());
            }
        });

        rightTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(
                    ExtCustomTable.ColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked()
                        + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        tradingPartnerSalesTableLayout.addComponent(resultsTable);
        tradingPartnerSalesTableLayout.addComponent(resultsTable.createControls());
    }

    public void loadResultTable(boolean isFirst) {
        selectionDTO.setCompanyMasterSid(Integer.parseInt(session.getCompanyMasterSid()));
        List<String> companyMasterSids = new ArrayList<>();
        companyMasterSids.add(session.getCompanyMasterSid());
        selectionDTO.setCompanyMasterSids(companyMasterSids);
        selectionDTO.setCompanyName(session.getCompanyName());
        int projectionId  ;
        if (isFirst) {
            projectionId = CommonLogic.getProjectionIdForSubmittedContract(session.getSessionId(), false);
        } else {
            List<Object[]> projCOntractIds = CommonLogic.getProjectionIdForPromoteCustomer(session.getSessionId(), session.getUserId());
            projectionId = Integer.parseInt(String.valueOf(projCOntractIds.get(0)[0]));
        }
        selectionDTO.setSessionID(Integer.parseInt(session.getSessionId()));
        selectionDTO.setFrequency("quarter");
        session.setContMasteSid(CommonLogic.getSelectedContractSid(session.getSessionId(), false));
        session.setProjectionId(Integer.valueOf(projectionId));
        tabLogic.getProjectionList(session);
        tableLogic.setProjectionResultsData(selectionDTO, projectionId);
    }

    private void initializeResultTable() {
        resultsTable.markAsDirty();
        resultsTable.setSelectable(false);
        resultsTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        return;
    }

    /**
     * Excel button logic.
     *
     * @param event the event
     */
    @UiHandler("excelBtn")
    public void excelButtonLogic(Button.ClickEvent event) {
        configureExcelResultTable();
        loadExcelResultTable();
        exportPeriodViewTable.setRefresh(Boolean.TRUE);
        Map<String, String> formatter = new HashMap<>();
        formatter.put("currencyNoDecimal", "Sales");
        formatter.put("unitOneDecimal", "Units");
        CustomExcelExport exp = new CustomExcelExport(new ExtCustomTableHolder(exportPeriodViewTable), "Sales Projection", "Sales Projection", "Sales_Projection.xls", false, formatter);
        exp.export();
        tradingPartnerSalesTableLayout.removeComponent(exportPeriodViewTable);
    }

    private void configureExcelResultTable() {
        excelResultBean = new ExtTreeContainer<>(SalesTabDTO.class,ExtContainer.DataStructureMode.MAP);
        exportPeriodViewTable = new ExtFilterTreeTable();
        tradingPartnerSalesTableLayout.addComponent(exportPeriodViewTable);
        exportPeriodViewTable.setRefresh(Boolean.FALSE);
        exportPeriodViewTable.setVisible(false);
        excelResultBean.setColumnProperties(fullHeader.getProperties());
        exportPeriodViewTable.setContainerDataSource(excelResultBean);
        exportPeriodViewTable.setVisibleColumns(fullHeader.getSingleColumns().toArray());
        exportPeriodViewTable.setColumnHeaders(fullHeader.getSingleHeaders().toArray(new String[fullHeader.getSingleHeaders().size()]));
        exportPeriodViewTable
                .setDoubleHeaderVisibleColumns(fullHeader.getDoubleColumns().toArray());
        exportPeriodViewTable
                .setDoubleHeaderColumnHeaders(fullHeader.getDoubleHeaders().toArray(new String[fullHeader.getDoubleHeaders().size()]));

        exportPeriodViewTable.setDoubleHeaderMap(fullHeader.getDoubleHeaderMaps());
    }

    private void loadExcelResultTable() {
        excelResultBean.removeAllItems();
        List<SalesTabDTO> resultList = tabLogic.getConfiguredSalesTabResults(new Object(), selectionDTO, true);
        loadDataToContainer(resultList, null);
    }

    public void loadDataToContainer(List<SalesTabDTO> resultList, Object parentId) {
        for (SalesTabDTO dto : resultList) {
            excelResultBean.addBean(dto);
            if (parentId != null) {
                excelResultBean.setParent(dto, parentId);
            }
            if (dto.getParent() == 1) {
                excelResultBean.setChildrenAllowed(dto, true);
                addLowerLevelsForExport(dto);
            } else {
                excelResultBean.setChildrenAllowed(dto, false);
            }
        }
    }

    public void addLowerLevelsForExport(Object id) {
        List<SalesTabDTO> resultList = tabLogic.getConfiguredSalesTabResults(id, selectionDTO, true);
        loadDataToContainer(resultList, id);
    }

	public ExtFilterTable getResultTable() {
		return resultTable;
	}

	public void setResultTable(ExtFilterTable resultTable) {
		this.resultTable = resultTable;
	}
}
