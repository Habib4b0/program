/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.ui.form;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.NationalAssumptionsFilterGenerator;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.TableDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.CommonLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.FcpResultsLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.tablelogic.FcpResultsTableLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad.BrandContainer;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad.BrandCriteria;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad.NdcFilterContainer;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad.NdcFilterCriteria;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad.TherapeuticContainer;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad.TherapeuticCriteria;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUiUtils;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.*;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.FrequencyConstants.*;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.IndicatorConstants.FCP_RESULTS_SCREEN;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.*;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.WindowMessagesName.RESET_CONFIRMATION;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.v7.ui.themes.Reindeer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.addons.lazycontainer.LazyContainer;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtCustomTreeTable;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * The Class FcpResults.
 *
 * @author Vinodhini
 */
public class FcpResults extends CustomComponent implements View {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -5631894964184716246L;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FcpResults.class);

    /**
     * The generate btn.
     */
    @UiField("generateBtn")
    private Button generateBtn;

    /**
     * The reset btn.
     */
    @UiField("resetBtn")
    private Button resetBtn;

    /**
     * The history ddlb.
     */
    @UiField("historyDdlb")
    protected ComboBox historyDdlb;

    /**
     * The therapeutic ddlb.
     */
    @UiField("therapeuticDdlb")
    private ComboBox therapeuticDdlb;

    /**
     * The brand ddlb.
     */
    @UiField("brandDdlb")
    private ComboBox brandDdlb;

    /**
     * The variables.
     */
    @UiField("variables")
    private OptionGroup variables;

    /**
     * The actual or proj.
     */
    @UiField("actualOrProj")
    private OptionGroup actualOrProj;

    /**
     * The period order.
     */
    @UiField("periodOrder")
    private OptionGroup periodOrder;

    /**
     * The view.
     */
    @UiField(Constant.VIEW)
    private OptionGroup view;

    @UiField("priceBasisDdlb")
    private ComboBox priceBasisDdlb;

    /**
     * The expand.
     */
    @UiField("expand")
    private Button expand;

    /**
     * The collapse.
     */
    @UiField("collapse")
    private Button collapse;

    /**
     * The excel btn.
     */
    @UiField("excelBtn")
    private Button excelBtn;

    /**
     * The non famp btn.
     */
    @UiField("nonFampBtn")
    private Button nonFampBtn;
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());

    /**
     * The max split position.
     */
    private static final float MAX_SPLIT_POS = 1000;

    /**
     * The min split position.
     */
    private static final float MIN_SPLIT_POS = 200;

    /**
     * The split position.
     */
    private static final float SPLIT_POS = 300;

    /**
     * The TableVerticalLayout.
     */
    @UiField("tableVerticalLayout")
    private VerticalLayout tableVerticalLayout;
    @UiField("tablePanel")
    private Panel tablePanel;
    private final CommonLogic commonLogic = new CommonLogic();
    private FcpResultsTableLogic tableLogic = new FcpResultsTableLogic();
    private FreezePagedTreeTable periodTableId = new FreezePagedTreeTable(tableLogic);
    private CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    private ExtTreeContainer<TableDTO> resultBeanContainer = new ExtTreeContainer<>(TableDTO.class,ExtContainer.DataStructureMode.MAP);
    private final ProjectionSelectionDTO projectionDTO = new ProjectionSelectionDTO();
    private final FcpResultsLogic fcpLogic = new FcpResultsLogic();
    private final HelperDTO therapyDto = new HelperDTO(0, SELECT_ONE.getConstant());
    private final HelperDTO brandDto = new HelperDTO(0, SELECT_ONE.getConstant());
    private LazyContainer brandContainer;
    private ExtCustomTreeTable exceltable = new ExtCustomTreeTable();
    private ExtTreeContainer<TableDTO> excelResultBeanContainer = new ExtTreeContainer<>(TableDTO.class,ExtContainer.DataStructureMode.MAP);
    private final int projectionId = (Integer) VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID);
    private final String mode = (String) VaadinSession.getCurrent().getAttribute(Constant.MODE);
    
    @UiField("ndcFilterDdlb")
    private ComboBox ndcFilterDdlb;
    private final HelperDTO ndcFilterDTO = new HelperDTO(0, SELECT_ONE.getConstant());
    private final HelperDTO levelDTO = new HelperDTO(0, SELECT_ONE.getConstant());
    private LazyContainer ndcFilterContainer;
    private final NationalAssumptionsForm form;

    @UiField("levelDdlb")
    private ComboBox levelDdlb;

    
    /**
     * The grid layout.
     */
    @UiField("gridLayout")
    private GridLayout gridLayout;
    /**
     * The price type.
     */
    private final OptionGroup priceType = new OptionGroup();
    
    private HelperDTO brandWorksheetDto = new HelperDTO(0, SELECT_ONE.getConstant());
    private final Property.ValueChangeListener ndcValueChange = getNDCFilterValueChangeListener();
    
    private final SessionDTO sessionDTO;


    public FcpResults(NationalAssumptionsForm form,SessionDTO sessionDTO) {
        super();
        LOGGER.debug("FcpResults Constructor initiated ");
        this.form = form;
        this.sessionDTO=sessionDTO;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/nationalassumption/FcpResults.xml"), this));
        gridLayout.addComponent(priceType, 1, NumericConstants.TWO, NumericConstants.THREE, NumericConstants.TWO);
        priceType.setMultiSelect(true);
        priceType.setImmediate(true);
        priceType.setEnabled(true);
        priceType.addStyleName(Constant.HORIZONTAL);
        configureFields();
        LOGGER.debug("FcpResults Constructor Ended ");
    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        LazyContainer therapeuticContainer;
        try {
            CommonUtils.defaultLoad(therapeuticDdlb, brandDdlb, ndcFilterDdlb, levelDdlb);
            CommonUtils.defaultSelect(therapeuticDdlb, brandDdlb, ndcFilterDdlb, levelDdlb);
            ndcFilterDdlb.select(ndcFilterDTO);
            levelDdlb.select(levelDTO);
            historyDdlb.setContainerDataSource(new IndexedContainer(CommonUtils.loadHistory(ANNUAL.getConstant())));
            if (historyDdlb.containsId(Constant.TWO_YEARS_LABEL)) {
                historyDdlb.setValue(Constant.TWO_YEARS_LABEL);
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

            view.addItem(PERIOD.getConstant());
            view.addItem(PRICE_TYPE.getConstant());
            view.setValue(PERIOD.getConstant());

            priceType.addItem(Constant.WAC);
            priceType.addItem(Constant.FSS);
            priceType.addItem("Non-FAMP");
            priceType.addItem(Constant.CPI_URA);
            priceType.addItem(Constant.FCP);

            excelBtn.setIcon(excelExportImage);
            variables.addStyleName(Constant.OPTION_GROUP_WIDTH);
            periodOrder.addStyleName(Constant.OPTION_GROUP_WIDTH);
            actualOrProj.addStyleName(Constant.OPTION_GROUP_WIDTH);
            view.addStyleName(Constant.OPTION_GROUP_WIDTH);

            priceBasisDdlb.addItem(SELECT_ONE.getConstant());
            priceBasisDdlb.addItem(AVERAGE_QUARTER_WAC.getConstant());
            priceBasisDdlb.addItem(BEGINNING_QUARTER_WAC.getConstant());
            priceBasisDdlb.addItem(ENDING_QUARTER_WAC.getConstant());
            priceBasisDdlb.addItem(MID_QUARTER_WAC.getConstant());
            priceBasisDdlb.addItem(DAY_WEIGHTED_WAC.getConstant());
            priceBasisDdlb.addItem(SALES_WEIGHTED_WAC.getConstant());
            priceBasisDdlb.select(SALES_WEIGHTED_WAC.getConstant());

            initializeResultTable();
            if (loadProjectionSelection()) {
                configureResultTable();
            }
            addResultTable();

            loadBrand();
            therapeuticDdlb.setPageLength(NumericConstants.SEVEN);
            therapeuticDdlb.setImmediate(true);
            therapeuticDdlb.setNullSelectionAllowed(true);
            therapeuticDdlb.setInputPrompt(SELECT_ONE.getConstant());
            therapeuticDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
            therapeuticDdlb.setItemCaptionPropertyId(DESCRIPTION.getConstant());
            therapeuticDdlb.markAsDirty();
            therapeuticContainer = new LazyContainer(HelperDTO.class, new TherapeuticContainer(), new TherapeuticCriteria());
            therapeuticContainer.setMinFilterLength(0);
            therapeuticDdlb.setContainerDataSource(therapeuticContainer);
            therapeuticDdlb.select(therapyDto);

            therapeuticDdlb.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {

                    LOGGER.debug("therapeuticDdlb ValueChangeEvent initiated= {} " , therapeuticDdlb.getValue());

                    if (therapeuticDdlb.getValue() != null && !SELECT_ONE.getConstant().equals(String.valueOf(therapeuticDdlb.getValue()))) {
                        HelperDTO helperDTO = (HelperDTO) therapeuticDdlb.getValue();
                        int theraupeuticId = helperDTO.getId();
                        projectionDTO.setTherapeuticSid(helperDTO);
                        brandContainer.removeAllItems();
                        loadBrand();
                        LOGGER.debug("therapeuticDdlb ValueChangeEvent ends theraupeuticId= {}  " , theraupeuticId);

                    }
                }
            });

            brandDdlb.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {

                    LOGGER.debug("brandDdlb ValueChangeEvent initiated= {} " , brandDdlb.getValue());

                    if (brandDdlb.getValue() != null && !SELECT_ONE.getConstant().equals(String.valueOf(brandDdlb.getValue()))) {
                        HelperDTO helperDTO = (HelperDTO) brandDdlb.getValue();
                        int brandSid = helperDTO.getId();
                        projectionDTO.setBrandSid(helperDTO);
                        projectionDTO.setBrandMasterId(brandSid);
                        LOGGER.debug("brandDdlb ValueChangeEvent ends brandDdlb= {}  " , brandSid);

                    }
                }
            });
            ndcFilterDdlb.addValueChangeListener(ndcValueChange);

            levelDdlb.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {

                    if (levelDdlb.getValue() != null && !SELECT_ONE.getConstant().equals(String.valueOf(levelDdlb.getValue()))) {
                        HelperDTO helperDTO = (HelperDTO) levelDdlb.getValue();
                        projectionDTO.setNdcLevelNo(helperDTO.getId());
                        projectionDTO.setNdcParent(helperDTO.getDescription());
                    } else {
                        projectionDTO.setNdcLevelNo(0);
                    }

                }

            });

            if (Constant.EDIT_SMALL.equalsIgnoreCase(mode) || Constant.VIEW.equalsIgnoreCase(mode)) {
                setProjectionSelection();
                if (Constant.VIEW.equalsIgnoreCase(mode)) {
                    disableFieldsOnView();
                }
            } else {
                selectPriceTypes();
            }
            ndcFilterDdlb.setWidth("176px");
            levelDdlb.setWidth("176px");
        } catch (Property.ReadOnlyException | UnsupportedOperationException e) {
            LOGGER.error(e.getMessage());
        }
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId =sessionDTO.getUserId();
        try {
            final Map<String, AppPermission> tabItemHM = stplSecurity.getBusinessFunctionPermission(userId, NATIONAL_ASSUMPTIONS.getConstant() + "," + FCP_RESULTS.getConstant());
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
            if (tabItemHM.get("expand") != null && tabItemHM.get("expand").isFunctionFlag()) {
                expand.setVisible(true);
            } else {
                expand.setVisible(false);
            }
            if (tabItemHM.get("collapse") != null && tabItemHM.get("collapse").isFunctionFlag()) {
                collapse.setVisible(true);
            } else {
                collapse.setVisible(false);
            }
            if (tabItemHM.get("nonFampBtn") != null && tabItemHM.get("nonFampBtn").isFunctionFlag()) {
                nonFampBtn.setVisible(true);
            } else {
                nonFampBtn.setVisible(false);
            }
        } catch (PortalException | SystemException portal) {
            LOGGER.error(StringUtils.EMPTY,portal);
        }
        tabOrder();
    }

    private Property.ValueChangeListener getNDCFilterValueChangeListener() {
        return new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (ndcFilterDdlb.getValue() != null && !SELECT_ONE.getConstant().equals(String.valueOf(ndcFilterDdlb.getValue()))) {
                    HelperDTO helperDTO = (HelperDTO) ndcFilterDdlb.getValue();
                    projectionDTO.setLevelNo(helperDTO.getId());
                } else {
                    projectionDTO.setLevelNo(0);
                }
                loadResultTable();
            }
        };
    }

    /**
     * Generate btn.
     *
     * @param event the event
     */
    @UiHandler("generateBtn")
    public void generateBtn(Button.ClickEvent event) {
        if (loadProjectionSelection() && checkSelection()) {
            tableVerticalLayout.removeAllComponents();
            tableLogic = new FcpResultsTableLogic();
            periodTableId = new FreezePagedTreeTable(tableLogic);
            initializeResultTable();
            configureResultTable();
            addResultTable();
            generateLogic();
            loadNdcFilter();
            loadNdcLevels();
            HelperDTO brandResultsDto = (HelperDTO) brandDdlb.getValue();
            projectionDTO.setBrandWSdto(brandResultsDto);
            brandWorksheetDto = brandResultsDto;
            configureTableFilter(periodTableId.getLeftFreezeAsTable());
        } else {
            AbstractNotificationUtils.getErrorNotification("Required Fields Missing", "Not all required fields have been selected.  Please select all required fields and try again ");
        }
    }

    /**
     * Reset btn.
     *
     * @param event the event
     */
    @UiHandler("resetBtn")
    public void resetBtn(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
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
                if (Constant.EDIT_SMALL.equalsIgnoreCase(mode) || Constant.VIEW.equalsIgnoreCase(mode)) {
                    setProjectionSelection();
                } else {
                    brandDdlb.select(brandDto);
                    therapeuticDdlb.select(therapyDto);
                    historyDdlb.select(Constant.TWO_YEARS_LABEL);
                    historyDdlb.focus();
                    variables.setValue(PERCENTAGE.getConstant());
                    periodOrder.setValue(ASCENDING.getConstant());
                    actualOrProj.setValue(BOTH.getConstant());
                    view.setValue(PERIOD.getConstant());
                    priceBasisDdlb.setValue("Sales Weighted WAC");
                    selectPriceTypes();
                }
            }
        }.getConfirmationMessage(RESET_CONFIRMATION.getConstant(), "Are you sure you want to reset the page to default/previous values?");

    }

    /**
     * Non famp btn.
     *
     * @param event the event
     */
    @UiHandler("nonFampBtn")
    public void nonFampBtn(final Button.ClickEvent event) {
        final NonFampResults lookUp = new NonFampResults(sessionDTO);
        UI.getCurrent().addWindow(lookUp);
    }

    /**
     * excel click listener.
     *
     * @param event the event
     */
    @UiHandler("excelBtn")
    public void excelBtn(final Button.ClickEvent event) {
        LOGGER.debug("excelBtn click listener started");
        try {
            configureExcelResultTable();
            if (resultBeanContainer.size() > 0) {
                loadExcelResultTable();
            }
            ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(exceltable), Constant.FCP_RESULTS, Constant.FCP_RESULTS, "FCP_Results.xls", false);
            exp.export();
            tableVerticalLayout.removeComponent(exceltable);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("excelBtn click listener ends");
    }

    /**
     * Select price types.
     */
    public void selectPriceTypes() {
        Object[] itemIds = priceType.getItemIds().toArray();
        for (Object itemId : itemIds) {
            priceType.select(itemId);
        }
    }

    /**
     * Initialize Result Table.
     */
    private void initializeResultTable() {
        periodTableId.markAsDirty();
        periodTableId.setSelectable(false);
        periodTableId.setSplitPosition(SPLIT_POS, Sizeable.Unit.PIXELS);
        periodTableId.setMinSplitPosition(MIN_SPLIT_POS, Sizeable.Unit.PIXELS);
        periodTableId.setMaxSplitPosition(MAX_SPLIT_POS, Sizeable.Unit.PIXELS);
        periodTableId.addStyleName("valo-theme-extfiltertable");
        periodTableId.addStyleName("table-header-center");
    }

    /**
     * Add Result Table.
     */
    private void addResultTable() {
        HorizontalLayout controlLayout;
        tableVerticalLayout.addComponent(periodTableId);
        controlLayout = tableLogic.createControls();
        controlLayout.setSizeUndefined();
        controlLayout.addStyleName(Constant.RESPONSIVE_PAGED_TABLE);
        tableLogic.sinkItemPerPageWithPageLength(false);
        tableVerticalLayout.addComponent(controlLayout);
    }

    /**
     * Configure Result Table.
     */
    private void configureResultTable() {
        fullHeader = new CustomTableHeaderDTO();
        tableLogic.setPageLength(NumericConstants.HUNDRED);
        
        CustomTableHeaderDTO leftHeader = CommonUiUtils.getLeftTableColumns(fullHeader);
        CustomTableHeaderDTO rightHeader = CommonUiUtils.getRightTableColumns(projectionDTO, fullHeader, StringUtils.EMPTY);
        resultBeanContainer = new ExtTreeContainer<>(TableDTO.class,ExtContainer.DataStructureMode.MAP);
        resultBeanContainer.setColumnProperties(fullHeader.getProperties());
        tableLogic.setContainerDataSource(resultBeanContainer);
        tableLogic.setTreeNodeMultiClick(false);
        final ExtPagedTreeTable leftTable = periodTableId
                .getLeftFreezeAsTable();
        final ExtPagedTreeTable rightTable = periodTableId
                .getRightFreezeAsTable();
        leftTable.setImmediate(true);
        rightTable.setImmediate(true);
        periodTableId.setHeight(Constant.PX_390);
        leftTable.setHeight(Constant.PX_390);
        rightTable.setHeight(Constant.PX_390);
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

                if (tableDto.getParent() == 1) {
                    Button ndcLink = new Button(tableDto.getGroup());
                    ndcLink.setStyleName(Reindeer.BUTTON_LINK);
                    ndcLink.setHeight(CommonUtils.TWENTYNINEPX); 
                    ndcLink.setData(tableDto.getGroup());
                    ndcLink.addClickListener(new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(final Button.ClickEvent event) {
                            ProjectionSelectionDTO worksheetProjDto = projectionDTO;
                            HelperDTO ndcDto = new HelperDTO();
                            ndcDto.setId(tableDto.getItemMasterSid());
                            HelperDTO brandResultsDto = (HelperDTO) brandDdlb.getValue();
                            if (Constant.SELECT_ONE.equals(brandResultsDto.getDescription())) {
                                HelperDTO brand = commonLogic.getBrand(ndcDto.getId());
                                if (!Constant.NULL.equals(brand.getDescription())) {
                                    worksheetProjDto.setBrand(brand);

                                } else {
                                    worksheetProjDto.setBrand(null);
                                }
                                worksheetProjDto.setBrandSeclected(true);
                            }
                            ndcDto.setDescription(tableDto.getGroup());
                            worksheetProjDto.setNdcWSdto(ndcDto);
                            worksheetProjDto.setNdcSid(ndcDto);
                            worksheetProjDto.setAdjust(false);
                            final List<String> fcpColumns = new ArrayList<>(projectionDTO.getColumns());
                            final MasterFcpWorkSheet lookUp = new MasterFcpWorkSheet(worksheetProjDto,sessionDTO);
                            UI.getCurrent().addWindow(lookUp);
                            lookUp.addCloseListener(new Window.CloseListener() {

                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    projectionDTO.setBrandWSdto(brandWorksheetDto);
                                    projectionDTO.setBrandSeclected(false);
                                    lookUp.closeLogic();
                                    projectionDTO.setColumns(fcpColumns);
                                    projectionDTO.setFrequencyDivision(1);
                                    if (lookUp.isSubmit()) {
                                        loadResultTable();
                                    }
                                }

                            });
                        }
                    });
                    return ndcLink;
                } else {
                    return tableDto.getGroup();
                }
            }
        });
        configureTableFilter(leftTable);
    }

    public boolean loadProjectionSelection() {
        boolean flag = false;
        Object hist = historyDdlb.getValue();

        int historyNum = 0;
        boolean histFlag = false;

            if ((hist != null) && (!SELECT_ONE.getConstant().equals(hist.toString()))) {
                histFlag = true;
                projectionDTO.setHistory(hist.toString());
                historyNum = Integer.parseInt(String.valueOf(hist).substring(0, 1));
            }
        if (histFlag) {
            flag = true;
            Object[] itemIds = priceType.getItemIds().toArray();
            List<String> selectedPrice = new ArrayList<>();
            for (Object itemId : itemIds) {
                if (priceType.isSelected(itemId)) {
                    selectedPrice.add(String.valueOf(itemId));
                }
            }
           selectedPrice.add(Constant.FCP_DISCOUNT);
           for (int i = 0; i < selectedPrice.size(); i++) {
               if (selectedPrice.get(i).contentEquals(Constant.FSS)) {
                   selectedPrice.add(NumericConstants.TWO, Constant.FSS_OGA_DISCOUNT);
               }

           }
           if (!(selectedPrice.contains(Constant.FSS_OGA_DISCOUNT))) {
               selectedPrice.add(Constant.FSS_OGA_DISCOUNT);
           }

            int projId = (Integer) (VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID) == null ? 0 : VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID));
            projectionDTO.setProjectionId(projId);
            projectionDTO.setPriceTypeList(selectedPrice);
            projectionDTO.setFrequency(ANNUALLY.getConstant());
            projectionDTO.setHistoryNum(historyNum);
            projectionDTO.setActualsOrProjections(actualOrProj.getValue().toString());
            projectionDTO.setVariables(String.valueOf(variables.getValue()));
            projectionDTO.setProjectionOrder(periodOrder.getValue().toString());
            projectionDTO.setPivotView(view.getValue().toString());
            com.stpl.app.gtnforecasting.nationalassumptions.dto.SessionDTO startAndTodate = CommonUtils.getSessionDto();
            Date startDate = startAndTodate.getFromDate();
            Date endDate = startAndTodate.getToDate();
            if (startDate != null && endDate != null) {
                projectionDTO.setEndYear(endDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
                projectionDTO.setEndMonth(endDate.getMonth() + 1);
                projectionDTO.setHistProjYear(startDate.getYear() +  NumericConstants.ONE_NINE_ZERO_ZERO);
                projectionDTO.setHistProjMonth(startDate.getMonth() + 1);
                projectionDTO.setProjectionNum(CommonUtils.getProjections(new Date(), endDate, ANNUALLY.getConstant()));
            }
            tablePanel.setCaption(view.getValue().toString() + SPACE.getConstant() + PIVOT_VIEW.getConstant());
        }
        return flag;
    }

    public boolean checkSelection() {
        boolean flag = true;
        if (StringUtils.isBlank(String.valueOf(priceBasisDdlb.getValue())) || priceBasisDdlb.getValue().equals(SELECT_ONE.getConstant())) {
            flag = false;
        }
        return flag;
    }
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener
     * .ViewChangeEvent)
     */

    @Override
    public void enter(ViewChangeEvent event) {
        //Default method
    }

    public void generateLogic() {
        LOGGER.debug("generateLogic starts ");
        try {
        if(!Constant.VIEW.equalsIgnoreCase(mode)){     
            callFcpProcedure();
        }
            loadResultTable();
        } catch (SQLException | NamingException ex) {
            LOGGER.error(ex.getMessage());
        }
        LOGGER.debug("generateLogic ends ");
    }

    private void loadResultTable() {
        tableLogic.clearAll();
        tableLogic.setRefresh(false);
        projectionDTO.clearNonFetchableIndex();
        tableLogic.setProjectionResultsData(projectionDTO,sessionDTO);
        tableLogic.setRefresh(true);

    }

    private void configureExcelResultTable() {
        excelResultBeanContainer = new ExtTreeContainer<>(TableDTO.class,ExtContainer.DataStructureMode.MAP);
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
        int count = fcpLogic.getConfiguredFcpResultsCount(new Object(), projectionDTO);
        projectionDTO.setPageStart(0);
        projectionDTO.setPageOffSet(count);
        List<TableDTO> resultList = fcpLogic.getFcp(projectionDTO);
        loadDataToContainer(resultList, null);
    }

    public void loadDataToContainer(List<TableDTO> resultList, Object parentId) {
        for (TableDTO dto : resultList) {
            excelResultBeanContainer.addBean(dto);
            if (parentId != null) {
                excelResultBeanContainer.setParent(dto, parentId);
            }
            if (dto.getParent() == 1) {
                excelResultBeanContainer.setChildrenAllowed(dto, true);
                addLowerLevelsForExport(dto);
            } else {
                excelResultBeanContainer.setChildrenAllowed(dto, false);
            }
        }
    }

    public void addLowerLevelsForExport(TableDTO id) {
        List<TableDTO> resultList = fcpLogic.getFcpChild(projectionDTO, id.getItemMasterSid(),sessionDTO);
        loadDataToContainer(resultList, id);
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
            targetItem = new BeanItem<>(
                    (TableDTO) id);
        }
        if (targetItem != null) {
            return (TableDTO) targetItem.getBean();
        } else {
            return null;
        }
    }

    public void callFcpProcedure() throws NamingException, SQLException  {
        String priceBasis = String.valueOf(priceBasisDdlb.getValue());

        if (priceBasis.equals("Average Quarter WAC")) {
            priceBasis = "AVGQWAC";
        } else if (priceBasis.equals("Beginning Quarter WAC")) {
            priceBasis = "BQWAC";
        } else if (priceBasis.equals("Ending Quarter WAC")) {
            priceBasis = "EQWAC";
        } else if (priceBasis.equals("Mid-Quarter WAC")) {
            priceBasis = "MQWAC";
        }
        int projectId = sessionDTO.getProjectionId();
        Integer sessionId = Integer.valueOf(sessionDTO.getUserId());
        Long userId = Long.valueOf(sessionDTO.getSessionId());

        projectionDTO.setProjectionId(projectId);
        projectionDTO.setUserId(userId.intValue());
        projectionDTO.setSessionId(sessionId);
        fcpLogic.fcpSetupCook(sessionDTO, priceBasis);

    }

    public void loadBrand() {
        brandDdlb.setPageLength(NumericConstants.SEVEN);
        brandDdlb.setImmediate(true);
        brandDdlb.setNullSelectionAllowed(true);
        brandDdlb.setInputPrompt(SELECT_ONE.getConstant());
        brandDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        brandDdlb.setItemCaptionPropertyId(DESCRIPTION.getConstant());
        brandDdlb.markAsDirty();
        brandContainer = new LazyContainer(HelperDTO.class, new BrandContainer(projectionDTO.getTherapeuticSid(), null), new BrandCriteria());
        brandContainer.setMinFilterLength(0);
        brandDdlb.setContainerDataSource(brandContainer);
        brandDdlb.select(brandDto);
    }

    public void saveFcpSelections() throws PortalException {
        LOGGER.debug("saveSalesProjection method starts");
        final CommonLogic logic = new CommonLogic();
        Map map = new HashMap();

        String tempVariables = priceType.getValue().toString();

        tempVariables = tempVariables.substring(1, tempVariables.length() - 1);
        HelperDTO helperDTO = (HelperDTO) therapeuticDdlb.getValue();
        String therapy = helperDTO.getDescription() + "," + helperDTO.getId();
        HelperDTO dto = (HelperDTO) brandDdlb.getValue();
        String brand = dto.getDescription() + "," + dto.getId();

        map.put(Constant.HISTORY, historyDdlb.getValue() != null ? historyDdlb.getValue().toString() : Constant.SELECT_ONE);
        map.put("therapeutic", therapy);
        map.put(Constant.BRAND, brand);
        map.put("priceType", tempVariables);
        map.put("variable", variables.getValue().toString());
        map.put("actualOrProj", actualOrProj.getValue().toString());
        map.put("periodOrder", periodOrder.getValue().toString());
        map.put(Constant.VIEW, view.getValue().toString());
        map.put("priceBasis", priceBasisDdlb.getValue().toString());

        logic.saveProjectionSelection(map, projectionId, FCP_RESULTS_SCREEN.getConstant());

        LOGGER.debug("saveSalesProjection method ends");

    }

    public void setProjectionSelection() {
        Map<Object, Object> map = CommonLogic.getProjectionSelection(projectionId, FCP_RESULTS_SCREEN.getConstant());
        if (map != null && !map.isEmpty()) {
            Object value = map.get(Constant.HISTORY);

            if (value != null) {
                historyDdlb.setValue(value.toString());
            }
            value = map.get("therapeutic");

            if (value != null) {
                String[] savedTherapy = String.valueOf(value).split(",");

                therapyDto.setDescription(savedTherapy[0]);
                therapyDto.setId(Integer.parseInt(savedTherapy[1]));
                therapeuticDdlb.select(therapyDto);
            }
            value = map.get(Constant.BRAND);

            if (value != null) {

                String[] brand = String.valueOf(value).split(",");

                brandDto.setDescription(brand[0]);
                brandDto.setId(Integer.parseInt(brand[1]));
                brandDdlb.select(brandDto);
                projectionDTO.setBrandSid(brandDto);
                projectionDTO.setBrandMasterId(Integer.parseInt(brand[1]));
            }
            value = map.get("periodOrder");

            if (value != null) {
                periodOrder.setValue(value.toString());
            }
            value = map.get("actualOrProj");

            if (value != null) {
                actualOrProj.setValue(value.toString());
            }
            value = map.get(Constant.VIEW);

            if (value != null) {
                view.setValue(value.toString());
            }
            value = map.get("variable");

            if (value != null) {
                variables.setValue(value.toString());
            }
            value = map.get("priceBasis");

            if (value != null) {
                priceBasisDdlb.setValue(value.toString());
            }

            value = map.get("priceType");

            if (value != null) {
                String value1 = (String) value;
                final String[] col = value1.split(",");

                for (String tempValue : col) {

                    tempValue = tempValue.trim();
                    priceType.select(tempValue);
                }
            }
        }

    }

    public void disableFieldsOnView() {
        historyDdlb.setEnabled(false);
        therapeuticDdlb.setEnabled(false);
        brandDdlb.setEnabled(false);
        variables.setEnabled(false);
        actualOrProj.setEnabled(false);
        priceType.setEnabled(false);
        periodOrder.setEnabled(false);
        view.setEnabled(false);
        resetBtn.setEnabled(false);
        ndcFilterDdlb.setEnabled(false);
        generateBtn.setEnabled(false);
        priceBasisDdlb.setEnabled(false);
        if (loadProjectionSelection() && checkSelection()) {
            generateBtn(null);
        }
    }

    public void loadNdcFilter() {
        ndcFilterDdlb.setImmediate(true);
        ndcFilterDdlb.setPageLength(NumericConstants.SEVEN);
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

    public void loadNdcLevels() {
        LazyContainer ndcLevelContainer;
        levelDdlb.setImmediate(true);
        levelDdlb.setPageLength(NumericConstants.SEVEN);
        levelDdlb.setNullSelectionAllowed(true);
        levelDdlb.setInputPrompt(SELECT_ONE.getConstant());
        levelDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        levelDdlb.setItemCaptionPropertyId(DESCRIPTION.getConstant());
        levelDdlb.markAsDirty();
        ndcLevelContainer = new LazyContainer(HelperDTO.class, new NdcFilterContainer(projectionDTO.getBrandSid(), true, projectionDTO.getTherapeuticSid(), false), new NdcFilterCriteria());
        ndcLevelContainer.setMinFilterLength(0);
        levelDdlb.setContainerDataSource(ndcFilterContainer);
        levelDdlb.select(levelDTO);
    }

    @UiHandler("expand")
    public void expandButtonLogic(Button.ClickEvent event) {
        expandCollapseLevelOption(true);
    }

    private void expandCollapseLevelOption(boolean isExpand) {
        int rowIndex = -1;
        if (levelDdlb.getValue() != null && !SELECT_ONE.getConstant().equals(String.valueOf(levelDdlb.getValue())) && StringUtils.isNotBlank(String.valueOf(levelDdlb.getValue()))) {
            rowIndex = fcpLogic.getFcpRowIndex(projectionDTO);
        }
        tableLogic.setRefresh(false);
        if (ndcFilterDdlb.getValue() != null) {
            HelperDTO helperDTO = (HelperDTO) ndcFilterDdlb.getValue();
            if (helperDTO.getId() != 0) {
                ndcFilterDdlb.removeValueChangeListener(ndcValueChange);
                tableLogic.clearAll();
                ndcFilterDdlb.select(ndcFilterDTO);
                ndcFilterDdlb.addValueChangeListener(ndcValueChange);
            }
        }
        tableLogic.loadExpandData(isExpand, rowIndex);
        tableLogic.setRefresh(true);
    }

    @UiHandler("collapse")
    public void collapseButtonLogic(Button.ClickEvent event)  {
        expandCollapseLevelOption(false);
    }

    private void configureTableFilter(final ExtPagedTreeTable leftTable) {
        leftTable.setFilterBarVisible(true);
        leftTable.setFilterDecorator(new ExtDemoFilterDecorator());
        leftTable.setFilterGenerator(new NationalAssumptionsFilterGenerator(projectionDTO, Constant.FCP_RESULTS));
    }

    public void tabOrder() {
        historyDdlb.focus();
        historyDdlb.setTabIndex(1);
        priceBasisDdlb.setTabIndex(NumericConstants.TWO);
        therapeuticDdlb.setTabIndex(NumericConstants.THREE);
        brandDdlb.setTabIndex(NumericConstants.FOUR);
        variables.setTabIndex(NumericConstants.FIVE);
        actualOrProj.setTabIndex(NumericConstants.SIX);
        priceType.setTabIndex(NumericConstants.SEVEN);
        periodOrder.setTabIndex(NumericConstants.EIGHT);
        view.setTabIndex(NumericConstants.NINE);
        generateBtn.setTabIndex(NumericConstants.TEN);
        resetBtn.setTabIndex(NumericConstants.ELEVEN);
        periodTableId.getLeftFreezeAsTable().setTabIndex(NumericConstants.TWELVE);
        excelBtn.setTabIndex(NumericConstants.THIRTEEN);
        expand.setTabIndex(NumericConstants.FOURTEEN);
        collapse.setTabIndex(NumericConstants.FIFTEEN);
        levelDdlb.setTabIndex(NumericConstants.SIXTEEN);
        nonFampBtn.setTabIndex(NumericConstants.SEVENTEEN);
        form.tabOrder(NumericConstants.SEVENTEEN);

    }
}
