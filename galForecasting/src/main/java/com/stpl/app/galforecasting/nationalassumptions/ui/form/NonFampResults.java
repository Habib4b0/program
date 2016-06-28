/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.nationalassumptions.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.nationalassumptions.dto.NationalAssumptionsFilterGenerator;
import com.stpl.app.galforecasting.nationalassumptions.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.nationalassumptions.dto.SessionDTO;
import com.stpl.app.galforecasting.nationalassumptions.dto.TableDTO;
import com.stpl.app.galforecasting.nationalassumptions.logic.FcpResultsLogic;
import com.stpl.app.galforecasting.nationalassumptions.logic.tablelogic.NonFampTableLogic;
import com.stpl.app.galforecasting.nationalassumptions.ui.lazyLoad.BrandContainer;
import com.stpl.app.galforecasting.nationalassumptions.ui.lazyLoad.BrandCriteria;
import com.stpl.app.galforecasting.nationalassumptions.ui.lazyLoad.NdcFilterContainer;
import com.stpl.app.galforecasting.nationalassumptions.ui.lazyLoad.NdcFilterCriteria;
import com.stpl.app.galforecasting.nationalassumptions.ui.lazyLoad.TherapeuticContainer;
import com.stpl.app.galforecasting.nationalassumptions.ui.lazyLoad.TherapeuticCriteria;
import com.stpl.app.galforecasting.nationalassumptions.util.CommonUiUtils;
import com.stpl.app.galforecasting.nationalassumptions.util.CommonUtils;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.DESCRIPTION;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.USER_ID;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.ACTUALS;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.AMOUNT;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.ASCENDING;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.BOTH;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.DESCENDING;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.NATIONAL_ASSUMPTIONS;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.NON_FAMP_LOOKUP;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.PERCENTAGE;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.PERIOD;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.PROJECTIONS;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.WindowMessagesName.RESET_CONFIRMATION;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.ExtCustomTreeTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * The Class NonFampResults.
 *
 * @author Vinodhini
 */
public class NonFampResults extends Window {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -5631894964184716246L;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(NonFampResults.class);

    /**
     * The generate btn.
     */
    @UiField("nfGenerateBtn")
    private Button generateBtn;

    /**
     * The reset btn.
     */
    @UiField("nfResetBtn")
    private Button resetBtn;

    /**
     * The history ddlb.
     */
    @UiField("nfHistoryDdlb")
    private ComboBox historyDdlb;

    /**
     * The therapeutic ddlb.
     */
    @UiField("nfTherapeuticDdlb")
    private ComboBox therapeuticDdlb;

    /**
     * The brand ddlb.
     */
    @UiField("nfBrandDdlb")
    private ComboBox brandDdlb;

    /**
     * The variables.
     */
    @UiField("nfVariables")
    private OptionGroup variables;

    /**
     * The actual or proj.
     */
    @UiField("nfActualOrProj")
    private OptionGroup actualOrProj;

    /**
     * The period order.
     */
    @UiField("nfPeriodOrder")
    private OptionGroup periodOrder;

    /**
     * The excel btn.
     */
    @UiField("nfExcelBtn")
    private Button excelBtn;

    /**
     * The close btn.
     */
    @UiField("nfCloseBtn")
    private Button closeBtn;
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());

    /**
     * The table control Layout.
     */
    public HorizontalLayout controlLayout;
    /**
     * The max split position.
     */
    private final float maxSplitPosition = 1000;

    /**
     * The min split position.
     */
    private final float minSplitPosition = 200;

    /**
     * The split position.
     */
    private final float splitPosition = 300;

    /**
     * The TableVerticalLayout.
     */
    @UiField("nfTableVerticalLayout")
    public VerticalLayout tableVerticalLayout;
    NonFampTableLogic tableLogic = new NonFampTableLogic();
    FreezePagedTreeTable periodTableId = new FreezePagedTreeTable(tableLogic);
    CustomTableHeaderDTO leftHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO rightHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    CustomTreeContainer<TableDTO> resultBeanContainer = new CustomTreeContainer<TableDTO>(TableDTO.class);
    ProjectionSelectionDTO projectionDTO = new ProjectionSelectionDTO();
    FcpResultsLogic fcpLogic = new FcpResultsLogic();
    private HelperDTO dto = new HelperDTO(0, SELECT_ONE.getConstant());
    LazyContainer brandContainer;
    LazyContainer therapeuticContainer;
    ExtCustomTreeTable exceltable = new ExtCustomTreeTable();
    CustomTreeContainer<TableDTO> excelResultBeanContainer = new CustomTreeContainer<TableDTO>(TableDTO.class);
    @UiField("nfNdcFilterDdlb")
    private ComboBox ndcFilterDdlb;
    LazyContainer ndcFilterContainer;
    private final HelperDTO ndcFilterDTO = new HelperDTO(0, SELECT_ONE.getConstant());
    private HelperDTO brandWorksheetDto = new HelperDTO(0, SELECT_ONE.getConstant());

    /**
     * Instantiates a new non famp results.
     */
    public NonFampResults() {
        super("Non-FAMP Results");
        LOGGER.info("NonFampResults Constructor initiated ");
        init();
        LOGGER.info("NonFampResults Constructor Ended ");
    }

    /**
     * Inits the.
     */
    public void init() {

        LOGGER.info("Entering init ");
        center();
        setClosable(true);
        setModal(true);
        setContent(Clara.create(getClass().getResourceAsStream("/nationalassumption/NonFampResults.xml"), this));
        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setWidth(75f, Sizeable.Unit.PERCENTAGE);
        setHeight(75f, Sizeable.Unit.PERCENTAGE);
        configureFields();
        LOGGER.info("Ends init ");
    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        try {
            CommonUtils.defaultLoad(therapeuticDdlb, brandDdlb, historyDdlb, ndcFilterDdlb);
            CommonUtils.defaultSelect(therapeuticDdlb, brandDdlb, historyDdlb, ndcFilterDdlb);
            ndcFilterDdlb.select(ndcFilterDTO);
            historyDdlb.setContainerDataSource(new IndexedContainer(CommonUtils.loadHistory(QUARTERLY.getConstant())));
            if (historyDdlb.containsId("2 Quarters")) {
                historyDdlb.select("2 Quarters");
            } else {
                historyDdlb.addItem(Constant.SELECT_ONE);
                historyDdlb.select(Constant.SELECT_ONE);
            }

            historyDdlb.focus();
            historyDdlb.setImmediate(true);

            variables.addItem(PERCENTAGE.getConstant());
            variables.addItem(AMOUNT.getConstant());
            variables.setValue(PERCENTAGE.getConstant());

            periodOrder.addItem(ASCENDING.getConstant());
            periodOrder.addItem(DESCENDING.getConstant());
            periodOrder.setValue(ASCENDING.getConstant());

            actualOrProj.addItem(ACTUALS.getConstant());
            actualOrProj.addItem(PROJECTIONS.getConstant());
            actualOrProj.addItem(BOTH.getConstant());
            actualOrProj.setValue(BOTH.getConstant());

            excelBtn.addStyleName(Reindeer.BUTTON_LINK);
            excelBtn.setIcon(excelExportImage);
            initializeResultTable();
            if (loadProjectionSelection()) {
                configureResultTable();
            }
            addResultTable();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        loadBrand();
        therapeuticDdlb.setPageLength(7);
        therapeuticDdlb.setImmediate(true);
        therapeuticDdlb.setNullSelectionAllowed(true);
        therapeuticDdlb.setInputPrompt(SELECT_ONE.getConstant());
        therapeuticDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        therapeuticDdlb.setItemCaptionPropertyId(DESCRIPTION.getConstant());
        therapeuticDdlb.markAsDirty();
        therapeuticContainer = new LazyContainer(HelperDTO.class, new TherapeuticContainer(), new TherapeuticCriteria());
        therapeuticContainer.setMinFilterLength(0);
        therapeuticDdlb.setContainerDataSource(therapeuticContainer);
        therapeuticDdlb.select(dto);

        therapeuticDdlb.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                LOGGER.info("therapeuticDdlb ValueChangeEvent initiated " + therapeuticDdlb.getValue());

                if (therapeuticDdlb.getValue() != null && !SELECT_ONE.getConstant().equals(String.valueOf(therapeuticDdlb.getValue()))) {
                    HelperDTO helperDTO = (HelperDTO) therapeuticDdlb.getValue();
                    int theraupeuticId = helperDTO.getId();
                    projectionDTO.setTherapeuticSid(helperDTO);
                    brandContainer.removeAllItems();
                    loadBrand();
                    LOGGER.info("therapeuticDdlb ValueChangeEvent ends theraupeuticId   " + theraupeuticId);

                }
            }
        });

        brandDdlb.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                LOGGER.info("brandDdlb ValueChangeEvent initiated " + brandDdlb.getValue());

                if (brandDdlb.getValue() != null && !SELECT_ONE.getConstant().equals(String.valueOf(brandDdlb.getValue()))) {
                    HelperDTO helperDTO = (HelperDTO) brandDdlb.getValue();
                    int brandSid = helperDTO.getId();
                    projectionDTO.setBrandSid(helperDTO);
                    projectionDTO.setBrandMasterId(brandSid);
                    LOGGER.info("brandDdlb ValueChangeEvent ends brandDdlb   " + brandSid);

                }
            }
        });
        ndcFilterDdlb.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                if (ndcFilterDdlb.getValue() != null && !SELECT_ONE.getConstant().equals(String.valueOf(ndcFilterDdlb.getValue()))) {
                    HelperDTO helperDTO = (HelperDTO) ndcFilterDdlb.getValue();
                    projectionDTO.setLevelNo(helperDTO.getId());
                } else {
                    projectionDTO.setLevelNo(0);
                }
                loadResultTable(projectionDTO.getLevelNo(), StringUtils.EMPTY);
            }
        });
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(USER_ID.getConstant()));
        try {
            final Map<String, AppPermission> tabItemHM = stplSecurity.getBusinessFunctionPermission(userId, NATIONAL_ASSUMPTIONS.getConstant() + "," + NON_FAMP_LOOKUP.getConstant());
            if (tabItemHM.get("generateBtn") != null && tabItemHM.get("generateBtn").isFunctionFlag()) {
                generateBtn.setVisible(true);
            } else {
                generateBtn.setVisible(false);
            }
            if (tabItemHM.get("resetBtn") != null && tabItemHM.get("resetBtn").isFunctionFlag()) {
                resetBtn.setVisible(true);
            } else {
                resetBtn.setVisible(false);
            }
            if (tabItemHM.get("excelBtn") != null && tabItemHM.get("excelBtn").isFunctionFlag()) {
                excelBtn.setVisible(true);
            } else {
                excelBtn.setVisible(false);
            }
            if (tabItemHM.get("closeBtn") != null && tabItemHM.get("closeBtn").isFunctionFlag()) {
                closeBtn.setVisible(true);
            } else {
                closeBtn.setVisible(false);
            }
        } catch (PortalException portal) {
            LOGGER.error(portal.getMessage());
        } catch (SystemException system) {
            LOGGER.error(system.getMessage());
        }
        ndcFilterDdlb.setWidth("176px");
        tabOrder();
    }

    /**
     * Reset button click listener.
     *
     * @param event the event
     */
    @UiHandler("nfResetBtn")
    public void resetBtn(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            public void noMethod() {
                // do nothing
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                historyDdlb.setValue("2 Quarters");
                variables.setValue(PERCENTAGE.getConstant());
                periodOrder.setValue(ASCENDING.getConstant());
                actualOrProj.setValue(BOTH.getConstant());
                therapeuticDdlb.select(dto);
                brandDdlb.select(dto);
            }
        }.getConfirmationMessage(RESET_CONFIRMATION.getConstant(), "Are you sure you want to reset the page to default/previous values?");
    }

    /**
     * Close button click listener..
     *
     * @param event the event
     */
    @UiHandler("nfCloseBtn")
    public void closeBtn(final Button.ClickEvent event) {
        close();
    }

    /**
     * Generate button click listener.
     *
     * @param event the event
     */
    @UiHandler("nfGenerateBtn")
    public void generateBtn(Button.ClickEvent event) {
        try {
            if (loadProjectionSelection()) {
                tableVerticalLayout.removeAllComponents();
                tableLogic = new NonFampTableLogic();
                periodTableId = new FreezePagedTreeTable(tableLogic);
                initializeResultTable();
                configureResultTable();
                addResultTable();
                generateLogic();
                loadNdcFilter();
                HelperDTO brandResultsDto = (HelperDTO) brandDdlb.getValue();
                projectionDTO.setBrandWSdto(brandResultsDto);
                brandWorksheetDto = brandResultsDto;
                configureTableFilter(periodTableId.getLeftFreezeAsTable());
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Initialize Result Table.
     */
    private void initializeResultTable() {
        periodTableId.markAsDirty();
        periodTableId.setSelectable(false);
        periodTableId.setImmediate(true);
        periodTableId.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        periodTableId.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        periodTableId.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        periodTableId.addStyleName("valo-theme-extfiltertable");
        periodTableId.addStyleName("table-header-center");
    }

    /**
     * Add Result Table.
     */
    private void addResultTable() {
        tableVerticalLayout.addComponent(periodTableId);
        controlLayout = tableLogic.createControls();
        tableLogic.sinkItemPerPageWithPageLength(false);
        tableVerticalLayout.addComponent(controlLayout);
    }

    /**
     * Configure Result Table.
     */
    private void configureResultTable() {
        tableLogic.setPageLength(100);
        fullHeader = new CustomTableHeaderDTO();
        leftHeader = CommonUiUtils.getLeftTableColumns(fullHeader);
        rightHeader = CommonUiUtils.getRightTableColumns(projectionDTO, fullHeader);
        resultBeanContainer = new CustomTreeContainer<TableDTO>(TableDTO.class);
        resultBeanContainer.setColumnProperties(fullHeader.getProperties());
        tableLogic.setContainerDataSource(resultBeanContainer);
        tableLogic.setTreeNodeMultiClick(false);
        final ExtPagedTreeTable leftTable = periodTableId
                .getLeftFreezeAsTable();
        final ExtPagedTreeTable rightTable = periodTableId
                .getRightFreezeAsTable();
        leftTable.setImmediate(true);
        rightTable.setImmediate(true);
        periodTableId.setHeight("315px");
        leftTable.setHeight("315px");
        rightTable.setHeight("315px");
        leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));
        rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
        }

        periodTableId.setDoubleHeaderVisible(true);
        rightTable
                .setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
        rightTable
                .setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));

        rightTable.setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());

        leftTable.addGeneratedColumn(Constant.GROUP, new ExtPagedTreeTable.ColumnGenerator() {

            @Override
            public Object generateCell(ExtCustomTable source, Object itemId, Object columnId) {

                final TableDTO tableDto = getBeanFromId(itemId);
                Button ndcLink = new Button(tableDto.getGroup());
                ndcLink.setStyleName(Reindeer.BUTTON_LINK);
                ndcLink.setImmediate(true);
                ndcLink.setData(tableDto.getGroup());
                ndcLink.addClickListener(new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    public void buttonClick(final Button.ClickEvent event) {
                        ProjectionSelectionDTO worksheetProjDto = projectionDTO;
                        HelperDTO ndcDto = new HelperDTO();
                        ndcDto.setId(tableDto.getItemMasterSid());
                        ndcDto.setDescription(tableDto.getGroup());
                        worksheetProjDto.setNdcSid(ndcDto);
                        worksheetProjDto.setAdjust(false);
                        worksheetProjDto.setNdcWSdto(ndcDto);
                        final MasterFcpWorkSheet lookUp = new MasterFcpWorkSheet(worksheetProjDto);
                        UI.getCurrent().addWindow(lookUp);
                        lookUp.addCloseListener(new Window.CloseListener() {

                            @Override
                            public void windowClose(Window.CloseEvent e) {
                                projectionDTO.setBrandWSdto(brandWorksheetDto);
                                lookUp.closeLogic();
                                if (lookUp.isSubmit()) {
                                    loadResultTable(0, StringUtils.EMPTY);
                                }
                            }

                        });
                    }
                });
                return ndcLink;
            }
        });
        configureTableFilter(periodTableId.getLeftFreezeAsTable());
    }

    public boolean loadProjectionSelection() {
        boolean flag = false;
        Object hist = historyDdlb.getValue();

        int historyNum = 0;
        boolean histFlag = false;

        if (hist != null) {
            if (!SELECT_ONE.getConstant().equals(hist.toString())) {
                histFlag = true;
                projectionDTO.setHistory(hist.toString());
                historyNum = Integer.valueOf(String.valueOf(hist).trim().substring(0, 2).trim());
            }
        }
        if (histFlag) {
            flag = true;
            int projectionId = (Integer) (VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID) == null ? 0 : VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID));
            projectionDTO.setProjectionId(projectionId);
            projectionDTO.setPivotView(PERIOD.getConstant());
            projectionDTO.setFrequency(QUARTERLY.getConstant());
            projectionDTO.setHistoryNum(historyNum);
            projectionDTO.setActualsOrProjections(actualOrProj.getValue().toString());
            projectionDTO.setVariables(String.valueOf(variables.getValue()));
            projectionDTO.setProjectionOrder(periodOrder.getValue().toString());
            SessionDTO startAndTodate = CommonUtils.sessionDto;
            Date startDate = startAndTodate.getFromDate();
            Date endDate = startAndTodate.getToDate();
            if (startDate != null && endDate != null) {
                projectionDTO.setEndYear(endDate.getYear() + 1900);
                projectionDTO.setEndMonth(endDate.getMonth() + 1);
                projectionDTO.setHistProjYear(startDate.getYear() + 1900);
                projectionDTO.setHistProjMonth(startDate.getMonth() + 1);
                projectionDTO.setProjectionNum(CommonUtils.getProjections(new Date(), endDate, QUARTERLY.getConstant()));
            }
        }
        return flag;
    }

    public void generateLogic() {
        tableLogic.clearAll();
        loadResultTable(0, StringUtils.EMPTY);

    }

    private void loadResultTable(int levelNo, String hierarchyNo) {
        try {
            tableLogic.clearAll();
            tableLogic.setRefresh(false);
            projectionDTO.clearNonFetchableIndex();
            tableLogic.setProjectionResultsData(projectionDTO, levelNo, hierarchyNo);
            tableLogic.setRefresh(true);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Method to getting Bean From Id
     *
     * @param id
     * @return
     */
    public TableDTO getBeanFromId(Object id) {
        BeanItem<?> targetItem = null;
        if (id instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) id;
        } else if (id instanceof TableDTO) {
            targetItem = new BeanItem<TableDTO>(
                    (TableDTO) id);
        }
        return (TableDTO) targetItem.getBean();
    }

    public void loadBrand() {
        brandDdlb.setPageLength(7);
        brandDdlb.setImmediate(true);
        brandDdlb.setNullSelectionAllowed(true);
        brandDdlb.setInputPrompt(SELECT_ONE.getConstant());
        brandDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        brandDdlb.setItemCaptionPropertyId(DESCRIPTION.getConstant());
        brandDdlb.markAsDirty();
        brandContainer = new LazyContainer(HelperDTO.class, new BrandContainer(projectionDTO.getTherapeuticSid(), null), new BrandCriteria());
        brandContainer.setMinFilterLength(0);
        brandDdlb.setContainerDataSource(brandContainer);
        brandDdlb.select(dto);
    }

    /**
     * excel click listener.
     *
     * @param event the event
     */
    @UiHandler("nfExcelBtn")
    public void excelBtn(final Button.ClickEvent event) {
        LOGGER.info("excelBtn click listener started");
        configureExcelResultTable();
        if (resultBeanContainer.size() > 0) {
            loadExcelResultTable();
        }
        ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(exceltable), "Non-FAMP Results", "Non-FAMP Results", "Non-FAMP Results.xls", false);
        exp.export();
        tableVerticalLayout.removeComponent(exceltable);
        LOGGER.info("excelBtn click listener ends");
    }

    private void configureExcelResultTable() {
        excelResultBeanContainer = new CustomTreeContainer<TableDTO>(TableDTO.class);
        excelResultBeanContainer.setColumnProperties(fullHeader.getProperties());
        exceltable = new ExtCustomTreeTable();
        tableVerticalLayout.addComponent(exceltable);
        exceltable.setRefresh(false);
        exceltable.setVisible(false);
        exceltable.setContainerDataSource(excelResultBeanContainer);
        exceltable.setVisibleColumns(fullHeader.getSingleColumns().toArray());
        exceltable.setColumnHeaders(fullHeader.getSingleHeaders().toArray(new String[fullHeader.getSingleHeaders().size()]));
        exceltable.setDoubleHeaderVisible(true);
        exceltable
                .setDoubleHeaderVisibleColumns(fullHeader.getDoubleColumns().toArray());
        exceltable
                .setDoubleHeaderColumnHeaders(fullHeader.getDoubleHeaders().toArray(new String[fullHeader.getDoubleHeaders().size()]));

        exceltable.setDoubleHeaderMap(fullHeader.getDoubleHeaderMaps());
        exceltable.setRefresh(true);
    }

    private void loadExcelResultTable() {
        excelResultBeanContainer.removeAllItems();
        List<TableDTO> resultList = fcpLogic.getNonFamp(projectionDTO);
        loadDataToContainer(resultList, null);
    }

    public void loadDataToContainer(List<TableDTO> resultList, Object parentId) {
        for (TableDTO dto : resultList) {
            excelResultBeanContainer.addBean(dto);
        }
    }

    public void loadNdcFilter() {
        ndcFilterDdlb.setImmediate(true);
        ndcFilterDdlb.setPageLength(7);
        ndcFilterDdlb.setNullSelectionAllowed(true);
        ndcFilterDdlb.setInputPrompt(SELECT_ONE.getConstant());
        ndcFilterDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        ndcFilterDdlb.setItemCaptionPropertyId(DESCRIPTION.getConstant());
        ndcFilterDdlb.markAsDirty();
        ndcFilterContainer = new LazyContainer(HelperDTO.class, new NdcFilterContainer(projectionDTO.getBrandSid(), true, projectionDTO.getTherapeuticSid(), false), new NdcFilterCriteria());
        ndcFilterContainer.setMinFilterLength(0);
        ndcFilterDdlb.setContainerDataSource(ndcFilterContainer);
        ndcFilterDdlb.select(ndcFilterDTO);

    }

    private void configureTableFilter(final ExtPagedTreeTable leftTable) {
        leftTable.setFilterBarVisible(true);
        leftTable.setFilterDecorator(new ExtDemoFilterDecorator());
        leftTable.setFilterGenerator(new NationalAssumptionsFilterGenerator(projectionDTO, "Non FAMP Results"));
    }

    public void tabOrder() {
        historyDdlb.focus();
        historyDdlb.setTabIndex(1);
        therapeuticDdlb.setTabIndex(2);
        brandDdlb.setTabIndex(3);
        variables.setTabIndex(4);
        actualOrProj.setTabIndex(5);
        periodOrder.setTabIndex(6);
        generateBtn.setTabIndex(7);
        resetBtn.setTabIndex(8);
        periodTableId.getLeftFreezeAsTable().setTabIndex(9);
        excelBtn.setTabIndex(10);
        closeBtn.setTabIndex(11);

    }
}
