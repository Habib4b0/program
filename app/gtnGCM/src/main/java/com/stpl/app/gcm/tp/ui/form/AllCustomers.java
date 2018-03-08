/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import org.asi.container.ExtTreeContainer;
import static com.stpl.app.gcm.discount.ui.form.ExistingDiscountTab.LOGGER;
import com.stpl.app.gcm.tp.dto.TradingPartnerDTO;
import com.stpl.app.gcm.tp.logic.CompanySearchLogic;
import com.stpl.app.gcm.tp.tablelogic.CompanySearchTableLogic;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.EXCEL_IMAGE_PATH;
import com.stpl.app.gcm.util.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.List;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author lokeshwari
 */
public class AllCustomers extends Window {

    @UiField("tradingPartnerTableLayout")
    public VerticalLayout tradingPartnerTableLayout;
    @UiField("excelBtn")
    public Button excelButton;
    @UiField("closeBtn")
    public Button closeButton;

    
    
    private final CompanySearchTableLogic companyLogic = new CompanySearchTableLogic();
    private final ExtPagedTable companySearchResultsTable = new ExtPagedTable(companyLogic);
    final private BeanItemContainer<TradingPartnerDTO> companyResultsContainer = new BeanItemContainer<>(TradingPartnerDTO.class);
    private List<TradingPartnerDTO> resultList = new ArrayList<>();
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    private ExtCustomTable companyViewTable;
    private ExtTreeContainer<TradingPartnerDTO> companyExcelResultBean = new ExtTreeContainer<>(TradingPartnerDTO.class);
    private final List<String> companyMasterSids;

    public AllCustomers(List<String> companyMasterSids) {
        this.companyMasterSids = companyMasterSids == null ? companyMasterSids : new ArrayList<>(companyMasterSids);
        setContent(Clara.create(getClass().getResourceAsStream("/TradingPartner/allCustomers.xml"), this));
        addStyleName("bootstrap-ui");
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setClosable(false);
        setResizable(false);
        setCaption(" All Customers ");
        setModal(true);
        configureFields();
    }

    protected void configureFields() {
        closeButton.setEnabled(true);
        tradingPartnerTableLayout.addComponent(companySearchResultsTable);
        excelButton.setIcon(excelExportImage);
        configureCompanySearchResultsTable();
    }

    public void configureCompanySearchResultsTable() {
        companySearchResultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        companySearchResultsTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        companySearchResultsTable.setHeight(NumericConstants.FOUR_HUNDRED, Unit.PIXELS);
        companySearchResultsTable.setPageLength(NumericConstants.FIVE);
        companyLogic.setContainerDataSource(companyResultsContainer);

        companySearchResultsTable.setVisibleColumns(Constants.getInstance().tpCompanySearchColumns);
        companySearchResultsTable.setColumnHeaders(Constants.getInstance().tpCompanySearchHeaders);
        companySearchResultsTable.setSelectable(false);
        companySearchResultsTable.focus();
        loadResultTable();
    }

    @UiHandler("closeBtn")
    public void closeBtn(Button.ClickEvent event) {
        close();
    }

    public void loadResultTable() {
        try {
            CompanySearchLogic logic = new CompanySearchLogic();
            resultList.clear();
            resultList = logic.loadAllCustomers(companyMasterSids);
            for (TradingPartnerDTO dto : resultList) {
                companyResultsContainer.addBean(dto);
            }
        } catch (Exception e) {
           LOGGER.error("",e);
        }
    }

    @UiHandler("excelBtn")
    public void excelBtn(Button.ClickEvent event) {
        configureCompanyExcelResultTable();
        for (TradingPartnerDTO dto : resultList) {
            companyExcelResultBean.addBean(dto);
        }
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.EXCEL_CLOSE, Constants.TRUE);
        ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(companyViewTable), "All Customers", "All Customers", "All_Customers.xls", false);
        excel.export();
        tradingPartnerTableLayout.removeComponent(companyViewTable);
        closeButton.setEnabled(true);
        excelButton.setEnabled(true);
    }

    private void configureCompanyExcelResultTable() {
        companyExcelResultBean = new ExtTreeContainer<>(TradingPartnerDTO.class);
        companyViewTable = new ExtCustomTable();
        tradingPartnerTableLayout.addComponent(companyViewTable);
        companyViewTable.setRefresh(BooleanConstant.getFalseFlag());
        companyViewTable.setVisible(false);
        companyViewTable.setContainerDataSource(companyExcelResultBean);
        companyViewTable.setVisibleColumns(companySearchResultsTable.getVisibleColumns());
        companyViewTable.setColumnHeaders(companySearchResultsTable.getColumnHeaders());
    }
}
