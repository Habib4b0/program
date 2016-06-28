/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.dto.BaseRateDTO;
import com.stpl.app.accountclose.dto.BaseRateHistoryDTO;
import com.stpl.app.accountclose.dto.BaseRateSummaryDTO;
import com.stpl.app.accountclose.logic.BaseRateCalculationLogic;
import com.stpl.app.accountclose.logic.SummaryTableLogic;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import static com.stpl.app.accountclose.ui.form.BaseRateHistory.LOGGER;
import com.stpl.app.accountclose.utils.CommonUtils;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.TRANSACTIONAL;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.CUST_PROD;
import static com.stpl.app.accountclose.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import com.stpl.app.accountclose.utils.HeaderUtils;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.vaadin.data.Property;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author alok.v
 */
public class BaseRateHistory extends CustomComponent implements View {

    public static final Logger LOGGER = Logger.getLogger(BaseRateHistory.class);
    SessionDTO session;
    private BaseRateDTO baseRateDTO;
    @UiField("histSelectionRadio")
    public OptionGroup histSelectionRadio;
    @UiField("brHistoryTableLayout")
    public HorizontalLayout brHistoryTableLayout;
    @UiField("brVerHistoryTableLayout")
    public VerticalLayout brVerHistoryTableLayout;
    @UiField("excelBtn")
    public Button excelBtn;
    /**
     * The from date.
     */
    @UiField("fromDate")
    private PopupDateField fromDate;
    /**
     * The to date.
     */
    @UiField("toDate")
    private PopupDateField toDate;
    SummaryTableLogic summaryLogic = new SummaryTableLogic();
    public ExtPagedTreeTable baseRateHistoryTable = new ExtPagedTreeTable(summaryLogic);
    private CustomTreeContainer<BaseRateHistoryDTO> baseRateHistoryContainer = new CustomTreeContainer<BaseRateHistoryDTO>(BaseRateHistoryDTO.class);
    CustomTableHeaderDTO headerDTO;
    @UiField("companyId")
    private TextField companyId;
    @UiField("acctType")
    private TextField acctType;
    @UiField("customerGroup")
    private TextField customerGroup;
    @UiField("productGroup")
    private TextField productGroup;
    @UiField("marketType")
    private TextField marketType;
    @UiField("acctSubType")
    private TextField acctSubType;
    @UiField("contractId")
    private TextField contractId;
    @UiField("productName")
    private TextField productName;

    BaseRateSummaryDTO summaryDto = new BaseRateSummaryDTO();
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO rightDTO;
    CustomTableHeaderDTO leftDTO;
    public CustomTreeContainer<BaseRateSummaryDTO> baseRateSummaryContainer;
    HeaderUtils hUtils = new HeaderUtils();
    CommonUtils cUtils = new CommonUtils();
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    ExtFilterTreeTable excelTable = new ExtFilterTreeTable();
    /* The Excel container */
    CustomTreeContainer<BaseRateSummaryDTO> excelContainer = new CustomTreeContainer<BaseRateSummaryDTO>(BaseRateSummaryDTO.class);
    CustomTreeContainer<BaseRateHistoryDTO> transexcelContainer = new CustomTreeContainer<BaseRateHistoryDTO>(BaseRateHistoryDTO.class);
    BaseRateCalculationLogic brLogic = new BaseRateCalculationLogic();
    HeaderUtils utils = new HeaderUtils();
    @UiField("daterange")
    private Label daterange;

    @UiField("bsMassUpdateLayout")
    private GridLayout bsMassUpdateLayout;

    @UiField("fromdatelabel")
    private Label fromdatelabel;

    @UiField("todatelabel")
    private Label todatelabel;

    @UiField("viewlabel")
    private Label viewlabel;

    @UiField("filterBtn")
    private Button filterBtn;

    public Component getContent() {
        LOGGER.info("getContent method starts");
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(Clara.create(getClass().getResourceAsStream("/baseRateHistory.xml"), this));
        Panel panel = new Panel();
        panel.setContent(layout);
        configureFields();
        LOGGER.info("getContent method ends");
        return panel;
    }

    public BaseRateHistory(final SessionDTO session, final BaseRateDTO baseRateDTO) {
        this.session = session;
        this.baseRateDTO = baseRateDTO;
        summaryDto.setSessionId(session.getSessionId());
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/baseRateHistory.xml"), this));
        configureFields();
    }

    protected void configureFields() {
        try {
            histSelectionRadio.setImmediate(true);
            histSelectionRadio.addItem(CUST_PROD.getConstant());
            histSelectionRadio.addItem(TRANSACTIONAL.getConstant());
            histSelectionRadio.select(CUST_PROD.getConstant());
            histSelectionRadio.setEnabled(true);
            excelBtn.setIcon(excelExportImage);

            brHistoryTableLayout.addComponent(baseRateHistoryTable);
            configureBaseRateSummaryTable();
            HorizontalLayout controls = summaryLogic.createControls();
            HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
            brVerHistoryTableLayout.addComponent(brHistoryTableLayout);
            brVerHistoryTableLayout.addComponent(controlLayout);

            fromDate.setDateFormat("MM/dd/yyyy");
            toDate.setDateFormat("MM/dd/yyyy");
            fromDate.addStyleName("dateField-align-center");
            toDate.addStyleName("dateField-align-center");

            bsMassUpdateLayout.setComponentAlignment(viewlabel, Alignment.MIDDLE_LEFT);
            bsMassUpdateLayout.setComponentAlignment(histSelectionRadio, Alignment.MIDDLE_LEFT);
            bsMassUpdateLayout.setComponentAlignment(daterange, Alignment.MIDDLE_LEFT);
            bsMassUpdateLayout.setComponentAlignment(fromdatelabel, Alignment.MIDDLE_LEFT);
            bsMassUpdateLayout.setComponentAlignment(fromDate, Alignment.MIDDLE_LEFT);
            bsMassUpdateLayout.setComponentAlignment(todatelabel, Alignment.MIDDLE_LEFT);
            bsMassUpdateLayout.setComponentAlignment(toDate, Alignment.MIDDLE_LEFT);
            bsMassUpdateLayout.setComponentAlignment(filterBtn, Alignment.MIDDLE_LEFT);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public void configureBaseRateSummaryTable() {

        fullHeader = new CustomTableHeaderDTO();
        rightDTO = hUtils.getSummaryHistoryColumns(fullHeader);
        baseRateSummaryContainer = new CustomTreeContainer<BaseRateSummaryDTO>(BaseRateSummaryDTO.class);
        summaryLogic.setControlTable(baseRateHistoryTable);
        baseRateSummaryContainer.setColumnProperties(rightDTO.getProperties());
        summaryLogic.sinkItemPerPageWithPageLength(false);
        summaryLogic.setContainerDataSource(baseRateSummaryContainer);
        summaryLogic.setPageLength(10);

        
        baseRateHistoryTable.setWidth(100, Unit.PERCENTAGE);
        baseRateHistoryTable.markAsDirty();
        baseRateHistoryTable.setVisibleColumns(rightDTO.getSingleColumns().toArray());
        baseRateHistoryTable.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));
        baseRateHistoryTable.setColumnCheckBox("checkRecord", Boolean.TRUE);
        for (Object property : baseRateHistoryTable.getVisibleColumns()) {

            baseRateHistoryTable.setColumnWidth(property, 120);

        }

    }

    public void configureTransaction() {
        fullHeader = new CustomTableHeaderDTO();
        rightDTO = hUtils.getSummHistTableColumns(fullHeader);
        baseRateSummaryContainer = new CustomTreeContainer<BaseRateSummaryDTO>(BaseRateSummaryDTO.class);
        summaryLogic.setControlTable(baseRateHistoryTable);
        baseRateSummaryContainer.setColumnProperties(rightDTO.getProperties());
        summaryLogic.sinkItemPerPageWithPageLength(false);
        summaryLogic.setContainerDataSource(baseRateSummaryContainer);
        summaryLogic.setPageLength(10);

        
        baseRateHistoryTable.setWidth(100, Unit.PERCENTAGE);
        baseRateHistoryTable.markAsDirty();
        baseRateHistoryTable.setVisibleColumns(rightDTO.getSingleColumns().toArray());
        baseRateHistoryTable.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public void setSummaryValues(BaseRateCalculation baseRateCalc) {

        companyId.setValue(String.valueOf(baseRateCalc.company.getItemCaption(baseRateCalc.company.getValue())));
        acctType.setValue(String.valueOf(baseRateCalc.acctType.getItemCaption(baseRateCalc.acctType.getValue())));
        acctSubType.setValue(String.valueOf(baseRateCalc.acctSubType.getItemCaption(baseRateCalc.acctSubType.getValue())));
        customerGroup.setValue(String.valueOf(baseRateCalc.customerGroup.getValue()));
        productGroup.setValue(String.valueOf(baseRateCalc.productGroup.getValue()));
        marketType.setValue(String.valueOf(baseRateCalc.marketType.getItemCaption(baseRateCalc.marketType.getValue())));
        contractId.setValue(String.valueOf(baseRateCalc.contract.getItemCaption(baseRateCalc.contract.getValue())));
        productName.setValue(String.valueOf(baseRateCalc.productName.getItemCaption(baseRateCalc.productName.getValue())));
        summaryDto.setAcMasterSid(baseRateCalc.baseRateDTO.getAcMasterSid());
        summaryLogic.setData(summaryDto, true);
    }

    @UiHandler("histSelectionRadio")
    public void histSelectionRadio(Property.ValueChangeEvent event) {
        LOGGER.info("histSelectionRadio Changed");
        String value = histSelectionRadio.getValue().toString();
        boolean query = true;
        if (TRANSACTIONAL.getConstant().equals(value)) {
            configureTransaction();
            query = false;
        } else {
            configureBaseRateSummaryTable();
            query = true;
        }
        summaryLogic.setData(summaryDto, query);
        LOGGER.info("histSelectionRadio change event Ended");
    }

    @UiHandler("filterBtn")
    public void filterBtnClick(Button.ClickEvent event) {
        String from = String.valueOf(fromDate.getValue());
        String to = String.valueOf(toDate.getValue());
        if (!cUtils.getNull(from) && !cUtils.getNull(to)) {
            summaryDto.setStartDate(from);
            summaryDto.setEndDate(to);
            summaryLogic.setData(summaryDto, false);
        } else {
            CommonUIUtils.getMessageNotification("Please enter from and to date");
        }
    }

    @UiHandler("excelBtn")
    public void excelBtnClick(Button.ClickEvent event) {

        try {
            String value = histSelectionRadio.getValue().toString();
            brHistoryTableLayout.addComponent(excelTable);
            excelTable.setVisible(false);
            excelContainer = new CustomTreeContainer<BaseRateSummaryDTO>(BaseRateSummaryDTO.class);
            if (TRANSACTIONAL.getConstant().equals(value)) {
                transactionExcel();

            } else {
                custProdExcel();
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void loadDataToContainer(List<BaseRateSummaryDTO> resultList, Object parentId, boolean isRecursive) {
        try {
            LOGGER.info("Inside loadDataToContainer" + resultList.size());
            for (BaseRateSummaryDTO dto : resultList) {

                excelContainer.addBean(dto);
                if (parentId != null) {
                    excelContainer.setParent(dto, parentId);
                }
                if (dto.getLevelNo() != 5) {
                    excelContainer.setChildrenAllowed(dto, true);
                    if (isRecursive) {
                        addLowerLevelsForExport(dto);
                    }
                } else {
                    excelContainer.setChildrenAllowed(dto, false);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ended loadDataToContainer");

    }

    public void addLowerLevelsForExport(BaseRateSummaryDTO dto) {
        LOGGER.info("Inside addLowerLevelsForExport");
        try {
            if (dto.getLevelNo() == 5) {
                summaryDto.setLevelNo(dto.getLevelNo());
            } else {
                summaryDto.setLevelNo(dto.getLevelNo() + 1);
            }
            List<BaseRateSummaryDTO> searchResults = brLogic.configureLevels(summaryDto);
            loadDataToContainer(searchResults, dto, true);

            excelTable.setCollapsed(dto, false);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ended addLowerLevelsForExport");
    }

    public void custProdExcel() {

        CustomTableHeaderDTO excelHeader = new CustomTableHeaderDTO();
        excelHeader = utils.getSummTableColumns(fullHeader);
        excelContainer.setColumnProperties(excelHeader.getProperties());
        excelTable.setContainerDataSource(excelContainer);

        excelTable.setVisibleColumns(excelHeader.getSingleColumns().toArray());
        excelTable.setColumnHeaders(excelHeader.getSingleHeaders().toArray(new String[excelHeader.getSingleHeaders().size()]));
        summaryDto.setLevelNo(1);
        List<BaseRateSummaryDTO> searchResults = brLogic.configureLevels(summaryDto);
        loadDataToContainer(searchResults, null, true);
        Map<String, String> formatter = new HashMap<String, String>();
        formatter.put("currencyTwoDecimal", "Amount");
        formatter.put("percentTwoDecimal", "Rate");
        LOGGER.info("excelContainer" + excelContainer.size() + excelTable.size());
        ExcelExport export = new ExcelExport(new ExtCustomTableHolder(excelTable), "Base Rate History", "Base Rate History", "BRHistory.xls", false);

        export.export();
        brHistoryTableLayout.removeComponent(excelTable);
    }

    public void transactionExcel() {
        brHistoryTableLayout.addComponent(excelTable);
        excelTable.setVisible(false);
        transexcelContainer = new CustomTreeContainer<BaseRateHistoryDTO>(BaseRateHistoryDTO.class);
        CustomTableHeaderDTO excelHeader = new CustomTableHeaderDTO();
        excelHeader = utils.getSummTableColumns(fullHeader);
        excelContainer.setColumnProperties(excelHeader.getProperties());
        excelTable.setContainerDataSource(excelContainer);

        excelTable.setVisibleColumns(excelHeader.getSingleColumns().toArray());
        excelTable.setColumnHeaders(excelHeader.getSingleHeaders().toArray(new String[excelHeader.getSingleHeaders().size()]));
        List<BaseRateSummaryDTO> searchResults = brLogic.configureTransData(summaryDto);
        for (BaseRateSummaryDTO summary : searchResults) {
            excelContainer.addBean(summary);
        }
        Map<String, String> formatter = new HashMap<String, String>();
        formatter.put("currencyTwoDecimal", "Amount");
        formatter.put("percentTwoDecimal", "Rate");
        LOGGER.info("excelContainer" + excelContainer.size() + excelTable.size());
        ExcelExport export = new ExcelExport(new ExtCustomTableHolder(excelTable), "Base Rate History", "Base Rate History", "BRHistory.xls", false);

        export.export();
        brHistoryTableLayout.removeComponent(excelTable);
    }
}
