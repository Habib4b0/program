/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.accountclose.gtnbalancereport.dto.ActualVarianceDTO;
import com.stpl.app.accountclose.gtnbalancereport.dto.BalanceReportDTO;
import com.stpl.app.accountclose.gtnbalancereport.logic.ActualVarianceLogic;
import com.stpl.app.accountclose.gtnbalancereport.logic.tableLogic.ActualVarianceTableLogic;
import com.stpl.app.accountclose.gtnbalancereport.utils.HeaderUtils;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.ResponsiveUtils;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author santanukumar
 */
public class ActualVariance extends CustomWindow {
     private static final Logger LOGGER = Logger.getLogger(ActualVariance.class);
    @UiField("deductionType")
    private TextField deductionType;
    @UiField("actualVarianceTableLayout")
    private VerticalLayout actualVarianceTableLayout;
    @UiField("exportBtn")
    private Button exportBtn;
    @UiField("closeBtn")
    private Button closeBtn;
    /**
     * Default container to results table.
     */
    private BeanItemContainer<ActualVarianceDTO> varianceContainer = new BeanItemContainer<ActualVarianceDTO>(ActualVarianceDTO.class);
    private ActualVarianceTableLogic varianceTableLogic=new ActualVarianceTableLogic();
    public ExtPagedTable actualVarianceTable = new ExtPagedTable(varianceTableLogic);
    SessionDTO sessionDTO;
    BalanceReportDTO balanceReportDTO;
    ActualVarianceDTO dto=new ActualVarianceDTO();
    private BeanItemContainer<ActualVarianceDTO> excelResultBean = new BeanItemContainer<ActualVarianceDTO>(ActualVarianceDTO.class);
    private ExtCustomTable actualvarianceExcelTable = new ExtCustomTable();
    ActualVarianceLogic varianceLogic=new ActualVarianceLogic();
     public ActualVariance(SessionDTO sessionDTO,BalanceReportDTO balanceReportDTO) {
         
        this.sessionDTO=sessionDTO;
        this.balanceReportDTO=balanceReportDTO;
        center();
        setWidth(70, Unit.PERCENTAGE);
        addStyleName("valo-theme-customwindow");
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        setClosable(true);
        setModal(true);
        setContent(Clara.create(getClass().getResourceAsStream("/actualVariance.xml"), this));
        configureField();
    }
     /**
     * Configure the components here.
     */
    protected void configureField() {
        addVarianceTable();
        deductionType.setEnabled(false);
        varianceTableLogic.setContainerDataSource(varianceContainer);
        varianceTableLogic.setPageLength(5);
        varianceTableLogic.sinkItemPerPageWithPageLength(false);
        
        actualVarianceTable.setVisibleColumns(HeaderUtils.ACTUAL_VARIANCE_COLUMNS);
        actualVarianceTable.setColumnHeaders(HeaderUtils.ACTUAL_VARIANCE_HEADERS);
        actualVarianceTable.setSelectable(false);
        actualVarianceTable.setFilterBarVisible(true);
        actualVarianceTable.setFilterDecorator(new ExtDemoFilterDecorator());
        actualVarianceTable.setPageLength(5);
        deductionType.setImmediate(true);
        for (Object propertyId : actualVarianceTable.getVisibleColumns()) {
                actualVarianceTable.setColumnWidth(propertyId, 125);
            }
        loadData();
        LOGGER.info("End of configureField");
    }
    private void addVarianceTable() {
        actualVarianceTableLayout.addComponent(actualVarianceTable);
        HorizontalLayout controls = ResponsiveUtils.getResponsiveControls(varianceTableLogic.createControls());
        actualVarianceTableLayout.addComponent(controls);
    }

    private void loadData() {
        deductionType.setValue(sessionDTO.getDataSelectionDTO().getDeductionType());
        varianceTableLogic.setData(sessionDTO,balanceReportDTO,false);
    }
    @UiHandler("closeBtn")
    public void btnCloseLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Information", "Are you sure you want to close ?" , new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    close();
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }
    
    
    
    
    
     @UiHandler("exportBtn")
    public void excelExport(Button.ClickEvent event) {
        try {
            List<ActualVarianceDTO> list = varianceLogic.getResults(balanceReportDTO,getFilters(),sessionDTO);
            for (ActualVarianceDTO summary : list) {
                excelResultBean.addBean(summary);
}
            configureActualVarianceExcelResultTable();
        } catch (Exception e) {
            LOGGER.error(e);
        }
        try {
            ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(actualvarianceExcelTable), "Results", "Results", "Results.xls", false);
            excel.export();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void configureActualVarianceExcelResultTable() {

        actualVarianceTableLayout.addComponent(actualvarianceExcelTable);
        actualvarianceExcelTable.setRefresh(Boolean.FALSE);
        actualvarianceExcelTable.setVisible(false);
        actualvarianceExcelTable.setContainerDataSource(excelResultBean);
        actualvarianceExcelTable.setVisibleColumns(actualVarianceTable.getVisibleColumns());
        actualvarianceExcelTable.setColumnHeaders(actualVarianceTable.getColumnHeaders());
    }
    
    private Set<Container.Filter> addedFilters = new HashSet<Container.Filter>();

    public Set<Container.Filter> getFilters() {
        return addedFilters;
    }
}
