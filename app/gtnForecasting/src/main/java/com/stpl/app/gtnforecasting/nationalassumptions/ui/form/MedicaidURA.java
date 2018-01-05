package com.stpl.app.gtnforecasting.nationalassumptions.ui.form;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.NationalAssumptionsFilterGenerator;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.TableDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.CommonLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.MedicaidURAResultsLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.tablelogic.MedicaidURAResultsTableLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad.BrandContainer;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad.BrandCriteria;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad.NdcFilterContainer;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad.NdcFilterCriteria;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad.TherapeuticContainer;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad.TherapeuticCriteria;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUiUtils;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.*;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.IndicatorConstants.URA_RESULTS_SCREEN;
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
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * The Class MedicaidURA.
 *
 * @author Vinodhini
 */
public class MedicaidURA extends CustomComponent implements View {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -5631894964184716246L;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicaidURA.class);

    /**
     * The reset btn.
     */
    @UiField("resetBtn")
    private Button resetBtn;

    /**
     * The generate btn.
     */
    @UiField("generateBtn")
    private Button generateBtn;

    /**
     * The history ddlb.
     */
    @UiField("historyDdlb")
    public ComboBox historyDdlb;

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

    /**
     * The price type.
     */
    private final OptionGroup priceType = new OptionGroup();

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
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());

    /**
     * The grid layout.
     */
    @UiField("gridLayout")
    private GridLayout gridLayout;

    @UiField("ndcFilterDdlb")
    private ComboBox ndcFilterDdlb;

    @UiField("levelDdlb")
    private ComboBox levelDdlb;

    /**
     * The TableVerticalLayout.
     */
    @UiField("tableVerticalLayout")
    private VerticalLayout tableVerticalLayout;
    private final CommonLogic commonLogic = new CommonLogic();
    private final HelperDTO therapyDto = new HelperDTO(0, SELECT_ONE.getConstant());
    private final HelperDTO brandDto = new HelperDTO(0, SELECT_ONE.getConstant());
    private final HelperDTO ndcFilterDto = new HelperDTO(0, SELECT_ONE.getConstant());
    private final HelperDTO levelDTO = new HelperDTO(0, SELECT_ONE.getConstant());

    private LazyContainer therapeuticContainer;

    private LazyContainer ndcFilterContainer;

    private LazyContainer brandContainer;
    public static final String MEDICAID_URA_RESULTS = "Medicaid URA Results";
    private LazyContainer ndcLevelContainer;

    private final ProjectionSelectionDTO projectionDTO = new ProjectionSelectionDTO();

    private MedicaidURAResultsTableLogic tableLogic = new MedicaidURAResultsTableLogic();

    private FreezePagedTreeTable periodTableId = new FreezePagedTreeTable(tableLogic);

    private CustomTableHeaderDTO leftHeader = new CustomTableHeaderDTO();
    private CustomTableHeaderDTO rightHeader = new CustomTableHeaderDTO();
    private CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    private ExtTreeContainer<TableDTO> resultBeanContainer = new ExtTreeContainer<>(TableDTO.class,ExtContainer.DataStructureMode.MAP);

    private final MedicaidURAResultsLogic medResLogic = new MedicaidURAResultsLogic();

    private ExtCustomTreeTable exceltable = new ExtCustomTreeTable();

    private ExtTreeContainer<TableDTO> excelResultBeanContainer = new ExtTreeContainer<>(TableDTO.class,ExtContainer.DataStructureMode.MAP);

    /**
     *
     */
    @UiField("panel2")
    private  Panel panel2;

    /**
     * The table control Layout.
     */
    private HorizontalLayout controlLayout;

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
    private final int projectionId = (Integer) VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID);
    private final String mode = (String) VaadinSession.getCurrent().getAttribute(Constant.MODE);
    private final NationalAssumptionsForm form;

    @UiField("priceBasisDdlb")
    private ComboBox priceBasisDdlb;
    private HelperDTO brandWorksheetDto = new HelperDTO(0, SELECT_ONE.getConstant());
    private final Property.ValueChangeListener ndcValueChange = getNDCFilterValueChangeListener();
    private final SessionDTO sessionDTO;
    public static final String MEDICAID_URA_WORSHEET_HELPER_TABLE = "Medicaid_Ura_Worsheet_Helper_table";
    public static final String MEDICAID_URA_TOTAL_URA = "MEDICAID_URA_TOTAL_URA";
    public static final String MEDICAID_URA_CPI_URA = "MEDICAID_URA_CPI_URA";
    public static final String MEDICAID_URA_BASIC_URA = "MEDICAID_URA_BASIC_URA";
    public static final String MEDICAID_URA_BEST_PRICE = "MEDICAID_URA_BEST_PRICE";
    public static final String LOWEST_COMMERCIAL_NET_PRICE = "Lowest Commercial Net Price";
    private final Map<String,String> priceTypeReplaceMap = new HashMap();
    private final Map<String,String> loadPriceActualMap = new HashMap();

    
    public MedicaidURA(NationalAssumptionsForm form,SessionDTO sessionDTO) {

        LOGGER.debug("MedicaidURA Constructor initiated ");
        this.form = form;
        this.sessionDTO=sessionDTO;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/nationalassumption/MedicaidURA.xml"), this));
        gridLayout.addComponent(priceType, 1, NumericConstants.TWO, NumericConstants.FOUR, NumericConstants.TWO);
        priceType.setMultiSelect(true);
        priceType.setImmediate(true);
        priceType.setEnabled(true);
        priceType.addStyleName(Constant.HORIZONTAL);

        configureFields();
        LOGGER.debug("MedicaidURA Constructor Ended ");
    }

    /**
     * Returns the caption.
     *
     * @return the caption
     */
    @Override
    public String getCaption() {
        LOGGER.debug("Inside MedicaidURA getCaption");
        return MEDICAID_URA_RESULTS;

    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        CommonUtils.defaultLoad(therapeuticDdlb, brandDdlb, ndcFilterDdlb, levelDdlb);
        CommonUtils.defaultSelect(therapeuticDdlb, brandDdlb, ndcFilterDdlb, levelDdlb);
        historyDdlb.setContainerDataSource(new IndexedContainer(CommonUtils.loadHistory(QUARTERLY.getConstant())));
        if (historyDdlb.containsId(Constant.TWO_QUARTERS)) {
            historyDdlb.select(Constant.TWO_QUARTERS);
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
        loadPriceTypes();
        priceBasisDdlb.addItem(SELECT_ONE.getConstant());
        priceBasisDdlb.addItem(AVERAGE_QUARTER_WAC.getConstant());
        priceBasisDdlb.addItem(BEGINNING_QUARTER_WAC.getConstant());
        priceBasisDdlb.addItem(ENDING_QUARTER_WAC.getConstant());
        priceBasisDdlb.addItem(MID_QUARTER_WAC.getConstant());
        priceBasisDdlb.addItem(DAY_WEIGHTED_WAC.getConstant());
        priceBasisDdlb.addItem(SALES_WEIGHTED_WAC.getConstant());
        priceBasisDdlb.select(SALES_WEIGHTED_WAC.getConstant());

        excelBtn.setIcon(excelExportImage);

        variables.addStyleName(Constant.OPTION_GROUP_WIDTH);
        periodOrder.addStyleName(Constant.OPTION_GROUP_WIDTH);
        actualOrProj.addStyleName(Constant.OPTION_GROUP_WIDTH);
        view.addStyleName(Constant.OPTION_GROUP_WIDTH);

        ndcFilterDdlb.select(ndcFilterDto);
        levelDdlb.select(levelDTO);

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

                LOGGER.debug("therapeuticDdlb ValueChangeEvent initiated " + therapeuticDdlb.getValue());

                if (therapeuticDdlb.getValue() != null && !SELECT_ONE.getConstant().equals(String.valueOf(therapeuticDdlb.getValue()))) {
                    HelperDTO helperDTO = (HelperDTO) therapeuticDdlb.getValue();
                    int theraupeuticId = helperDTO.getId();
                    projectionDTO.setTherapeuticSid(helperDTO);
                    brandContainer.removeAllItems();
                    loadBrand();
                    LOGGER.debug("therapeuticDdlb ValueChangeEvent ends theraupeuticId   " + theraupeuticId);

                }
            }
        });

        brandDdlb.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                LOGGER.debug("brandDdlb ValueChangeEvent initiated " + brandDdlb.getValue());

                if (brandDdlb.getValue() != null && !SELECT_ONE.getConstant().equals(String.valueOf(brandDdlb.getValue()))) {
                    HelperDTO helperDTO = (HelperDTO) brandDdlb.getValue();
                    int brandSid = helperDTO.getId();
                    projectionDTO.setBrandSid(helperDTO);
                    projectionDTO.setBrandMasterId(brandSid);
                    LOGGER.debug("brandDdlb ValueChangeEvent ends brandDdlb   " + brandSid);

                }
            }
        });

        ndcFilterDdlb.addValueChangeListener(ndcValueChange);

        levelDdlb.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (levelDdlb.getValue() != null && !SELECT_ONE.getConstant().equals(String.valueOf(levelDdlb.getValue()))) {
                    HelperDTO helperDTO = (HelperDTO) levelDdlb.getValue();
                    String ndc9 = helperDTO.getDescription();
                    if (!SELECT_ONE.getConstant().equals(ndc9)) {
                        String[] ndcArr = ndc9.split(",");
                        if (ndcArr.length > 0) {
                            if (ndcArr.length > 1) {
                                ndc9 = ndcArr[1].trim();
                            } else {
                                ndc9 = ndcArr[0].trim();
                            }
                        }
                        projectionDTO.setNdc9LevelNo(ndc9);

                    } else {
                        projectionDTO.setNdc9LevelNo(StringUtils.EMPTY);
                    }

                    projectionDTO.setNdcParent(helperDTO.getDescription());
                } else {
                    projectionDTO.setNdc9LevelNo(StringUtils.EMPTY);
                }
            }
        });

        initializeResultTable();
        if (loadProjectionSelection()) {
            configureResultTable();
        }
        addResultTable();
        if (Constant.EDIT_SMALL.equalsIgnoreCase(mode) || Constant.VIEW.equalsIgnoreCase(mode)) {
            setProjectionSelection();
            if (Constant.VIEW.equalsIgnoreCase(mode)) {
                disableFieldsOnView();
            }

        } else {
            selectPriceTypes();
        }
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = sessionDTO.getUserId();
        try {
            final Map<String, AppPermission> tabItemHM = stplSecurity.getBusinessFunctionPermission(userId, NATIONAL_ASSUMPTIONS.getConstant() + "," + MEDICAID_URA.getConstant());
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
        } catch (PortalException | SystemException portal) {
            LOGGER.error(StringUtils.EMPTY,portal);
        }
        ndcFilterDdlb.setWidth("176px");
        levelDdlb.setWidth("176px");
        tabOrder();
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
            tableLogic = new MedicaidURAResultsTableLogic();
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
                    historyDdlb.setValue(Constant.TWO_QUARTERS);
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
     * Select price types.
     */
    public void selectPriceTypes() {
        Object[] itemIds = priceType.getItemIds().toArray();
        for (Object itemId : itemIds) {
            priceType.select(itemId);
        }
    }

    /**
     *
     * @return
     */
    public boolean loadProjectionSelection() {
        boolean flag = false;
        Object hist = historyDdlb.getValue();

        int historyNum = 0;
        boolean histFlag = false;

            if ((hist != null) && (!SELECT_ONE.getConstant().equals(hist.toString()))) {
                histFlag = true;
                projectionDTO.setHistory(hist.toString());
                historyNum = Integer.valueOf(String.valueOf(hist).trim().substring(0, NumericConstants.TWO).trim());
            }
        if (histFlag) {
            flag = true;
            Object[] itemIds = priceType.getItemIds().toArray();
            List<String> selectedPrice = new ArrayList<>();
            for (Object itemId : itemIds) {
                if (priceType.isSelected(itemId)) {
                    selectedPrice.add(priceTypeReplaceMap.get(String.valueOf(itemId)));
                }
            }
            int projId = (Integer) (VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID) == null ? 0 : VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID));
            projectionDTO.setProjectionId(projId);
            projectionDTO.setPriceTypeList(selectedPrice);
            projectionDTO.setFrequency(QUARTERLY.getConstant());
            projectionDTO.setHistoryNum(historyNum);
            projectionDTO.setActualsOrProjections(actualOrProj.getValue().toString());
            projectionDTO.setVariables(String.valueOf(variables.getValue()));
            projectionDTO.setProjectionOrder(periodOrder.getValue().toString());
            projectionDTO.setPivotView(view.getValue().toString());
            com.stpl.app.gtnforecasting.nationalassumptions.dto.SessionDTO startAndTodate = CommonUtils.sessionDto;
            Date startDate = startAndTodate.getFromDate();
            Date endDate = startAndTodate.getToDate();
            if (startDate != null && endDate != null) {
                projectionDTO.setEndYear(endDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
                projectionDTO.setEndMonth(endDate.getMonth() + 1);
                projectionDTO.setHistProjYear(startDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
                projectionDTO.setHistProjMonth(startDate.getMonth() + 1);
                projectionDTO.setProjectionNum(CommonUtils.getProjections(new Date(), endDate, QUARTERLY.getConstant()));
            }
            panel2.setCaption(view.getValue().toString() + SPACE.getConstant() + PIVOT_VIEW.getConstant());
        }
        return flag;
    }

    /**
     *
     * @return
     */
    public boolean checkSelection() {
        boolean flag = true;

        if (StringUtils.isBlank(String.valueOf(priceBasisDdlb.getValue())) || priceBasisDdlb.getValue().equals(SELECT_ONE.getConstant())) {
            flag = false;
        }
        return flag;
    }

    /**
     * /**
     * Initialize Result Table.
     */
    private void initializeResultTable() {
        periodTableId.markAsDirty();
        periodTableId.setSelectable(false);
        periodTableId.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        periodTableId.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        periodTableId.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        periodTableId.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        periodTableId.addStyleName("table-header-center");
    }

    /**
     * Add Result Table.
     */
    private void addResultTable() {
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
        tableLogic.setPageLength(NumericConstants.HUNDRED);
        fullHeader = new CustomTableHeaderDTO();
        leftHeader = CommonUiUtils.getLeftTableColumns(fullHeader);
        rightHeader = CommonUiUtils.getRightTableColumns(projectionDTO, fullHeader, "URA");
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
                    ndcLink.setData(tableDto.getGroup());
                    ndcLink.addClickListener(new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(final Button.ClickEvent event) {
                            ProjectionSelectionDTO worksheetProjDto = projectionDTO;
                            worksheetProjDto.setNdc9(tableDto.getNdc9());
                            worksheetProjDto.setAdjust(false);
                            HelperDTO ndcDto = new HelperDTO();
                            ndcDto.setId(tableDto.getItemMasterSid());
                            ndcDto.setDescription(tableDto.getGroup());                            
                            worksheetProjDto.setNdcSid(ndcDto);
                            worksheetProjDto.setNdcWSdto(ndcDto);
                            worksheetProjDto.setNewFormulation(tableDto.getNewFormulation());
                            worksheetProjDto.setNewFormulationItemId(tableDto.getNewFormulationItemId());
                            worksheetProjDto.setBaeselineAmp(tableDto.getBaeselineAmp());
                            worksheetProjDto.setBaeselineCpi(tableDto.getBaeselineCpi());
                            HelperDTO brandResultsDto = (HelperDTO) brandDdlb.getValue();
                            if (Constant.SELECT_ONE.equals(brandResultsDto.getDescription())) {
                                HelperDTO brand = commonLogic.getBrandForMedicaid(tableDto.getNdc9());
                                if (!Constant.NULL.equals(brand.getDescription())) {
                                    worksheetProjDto.setBrand(brand);

                                } else {
                                    worksheetProjDto.setBrand(null);
                                }
                                worksheetProjDto.setBrandSeclected(true);
                            }
                            final MedicaidUraWorkSheet lookUp = new MedicaidUraWorkSheet(worksheetProjDto,sessionDTO);
                            UI.getCurrent().addWindow(lookUp);

                            lookUp.addCloseListener(new Window.CloseListener() {

                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    projectionDTO.setBrandSeclected(false);
                                    projectionDTO.setBrandWSdto(brandWorksheetDto);
                                    lookUp.closeLogic();
                                    lookUp.removeBaseYear();
                                    lookUp.getBaseYear().clear();
                                    if (lookUp.isSubmit()) {
                                        loadResultTable(0, StringUtils.EMPTY);
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

        configureTableFilter(periodTableId.getLeftFreezeAsTable());
    }

    /**
     *
     */
    public void generateLogic() {
        if(!Constant.VIEW.equalsIgnoreCase(mode)){     
        callProcedure();
        }
        loadResultTable(0, StringUtils.EMPTY);

    }

    private void loadResultTable(int levelNo, String hierarchyNo) {
        try {
            tableLogic.clearAll();
            tableLogic.setRefresh(false);
            projectionDTO.clearNonFetchableIndex();
            tableLogic.setProjectionResultsData(projectionDTO, levelNo, hierarchyNo,sessionDTO);
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
            targetItem = new BeanItem<>(
                    (TableDTO) id);
        }
        return (TableDTO) targetItem.getBean();
    }

    public void callProcedure() {
        try {
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
            medResLogic.medicaidProcSetupDataCook(sessionDTO, priceBasis);
        } catch (SQLException | NamingException ex) {
            LOGGER.error(ex.getMessage());
        }
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
            ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(exceltable), MEDICAID_URA_RESULTS, MEDICAID_URA_RESULTS, "Medicaid_URA_Results.xls", false);
            exp.export();
            tableVerticalLayout.removeComponent(exceltable);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("excelBtn click listener ends");
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

    private void loadExcelResultTable()  {
        excelResultBeanContainer.removeAllItems();
        int count = medResLogic.getConfiguredMedicaidResultsCount(null, projectionDTO);
        projectionDTO.setPageStart(0);
        projectionDTO.setPageOffSet(count);
        List<TableDTO> resultList = medResLogic.getMedicaid(projectionDTO);
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
        List<TableDTO> resultList = medResLogic.getMedicaidChild(projectionDTO, id.getNdc9(),sessionDTO);
        loadDataToContainer(resultList, id);
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
        return;
    }

    public void saveMedicaidSelections() throws PortalException {
        LOGGER.debug("saveSalesProjection method starts");
        final CommonLogic logic = new CommonLogic();
        Map map = new HashMap();

        String tempVariables = priceType.getValue().toString();

        tempVariables = tempVariables.substring(1, tempVariables.length() - 1);
        HelperDTO helperDTO = (HelperDTO) therapeuticDdlb.getValue();
        String therapy = helperDTO.getDescription() + "," + helperDTO.getId();
        HelperDTO DTO = (HelperDTO) brandDdlb.getValue();
        String brand = DTO.getDescription() + "," + DTO.getId();
        map.put(Constant.HISTORY, historyDdlb.getValue().toString());
        map.put("therapeutic", therapy);
        map.put(Constant.BRAND, brand);
        map.put("priceType", tempVariables);
        map.put("variable", variables.getValue().toString());
        map.put("actualOrProj", actualOrProj.getValue().toString());
        map.put("periodOrder", periodOrder.getValue().toString());
        map.put(Constant.VIEW, view.getValue().toString());
        map.put("priceBasis", priceBasisDdlb.getValue().toString());

        logic.saveProjectionSelection(map, projectionId, URA_RESULTS_SCREEN.getConstant());
        LOGGER.debug("saveSalesProjection method ends");

    }

    public void setProjectionSelection() {
        Map<Object, Object> map = CommonLogic.getProjectionSelection(projectionId, URA_RESULTS_SCREEN.getConstant());
        if (map != null && !map.isEmpty()) {
            Object value = map.get(Constant.HISTORY);

            if (value != null) {
                historyDdlb.setValue(value.toString());
                historyDdlb.focus();
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
                periodOrder.select(value.toString());
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
        ndcFilterContainer = new LazyContainer(HelperDTO.class, new NdcFilterContainer(projectionDTO.getBrandSid(), false, projectionDTO.getTherapeuticSid(), false), new NdcFilterCriteria());
        ndcFilterContainer.setMinFilterLength(0);
        ndcFilterDdlb.setContainerDataSource(ndcFilterContainer);
        ndcFilterDdlb.select(ndcFilterDto);

    }

    public void loadNdcLevels() {
        levelDdlb.setImmediate(true);
        levelDdlb.setPageLength(NumericConstants.SEVEN);
        levelDdlb.setNullSelectionAllowed(true);
        levelDdlb.setInputPrompt(SELECT_ONE.getConstant());
        levelDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        levelDdlb.setItemCaptionPropertyId(DESCRIPTION.getConstant());
        levelDdlb.markAsDirty();
        ndcLevelContainer = new LazyContainer(HelperDTO.class, new NdcFilterContainer(projectionDTO.getBrandSid(), false, projectionDTO.getTherapeuticSid(), false), new NdcFilterCriteria());
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
            rowIndex = medResLogic.getMedicaidRowIndex(projectionDTO);
        }
        tableLogic.setRefresh(false);
        if (ndcFilterDdlb.getValue() != null) {
            HelperDTO helperDTO = (HelperDTO) ndcFilterDdlb.getValue();
            if (!SELECT_ONE.getConstant().equals(helperDTO.getDescription())) {
                ndcFilterDdlb.removeValueChangeListener(ndcValueChange);
                ndcFilterDdlb.setImmediate(true);
                tableLogic.clearAll();
                ndcFilterDdlb.addValueChangeListener(ndcValueChange);
                ndcFilterDdlb.select(ndcFilterDto);
            }
        }
        tableLogic.loadExpandData(isExpand, rowIndex);
        tableLogic.setRefresh(true);
    }

    @UiHandler("collapse")
    public void collapseButtonLogic(Button.ClickEvent event) {
        expandCollapseLevelOption(false);
    }

    Property.ValueChangeListener getNDCFilterValueChangeListener() {
        return new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (ndcFilterDdlb.getValue() != null) {
                    HelperDTO helperDTO = (HelperDTO) ndcFilterDdlb.getValue();
                    String ndc9 = helperDTO.getDescription();
                    projectionDTO.setMedicaidSelectedNdc(ndc9);
                    if (!SELECT_ONE.getConstant().equals(ndc9)) {
                        String[] ndcArr = ndc9.split(",");
                        if (ndcArr.length > 0) {
                            if (ndcArr.length > 1) {
                                ndc9 = ndcArr[1].trim();
                            } else {
                                ndc9 = ndcArr[0].trim();
                            }
                        }
                        projectionDTO.setLevelValue(ndc9);

                    } else {
                        projectionDTO.setLevelValue(StringUtils.EMPTY);
                    }
                } else {
                    projectionDTO.setLevelValue(StringUtils.EMPTY);
                }
                loadResultTable(projectionDTO.getLevelNo(), StringUtils.EMPTY);
            }
        };
    }

    private void configureTableFilter(final ExtPagedTreeTable leftTable) {
        leftTable.setFilterBarVisible(true);
        leftTable.setFilterDecorator(new ExtDemoFilterDecorator());
        leftTable.setFilterGenerator(new NationalAssumptionsFilterGenerator(projectionDTO, "Medicaid URA"));
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
        form.tabOrder(NumericConstants.FIFTEEN);
    }
    
    private void loadPriceTypes() {
        Map<String, String> priceTypeMap = CommonUtils.getPriceTypeNameDynamic("MEDICAID_URA", MEDICAID_URA_WORSHEET_HELPER_TABLE);
        projectionDTO.setLoadUraPriceMap(priceTypeMap);
        loadPriceTypeReplaceMap(priceTypeMap);
        loadPriceActualMap(priceTypeMap);
        projectionDTO.setLoadPriceActualMap(loadPriceActualMap);
        priceType.addItem(Constant.WAC);
        priceType.addItem(Constant.AMP);
        if (!StringUtils.isBlank(medResLogic.getGroupName(priceTypeMap.get(MEDICAID_URA_BEST_PRICE)))) {
            priceType.addItem(medResLogic.getGroupName(priceTypeMap.get(MEDICAID_URA_BEST_PRICE)));
        }
        priceType.addItem(LOWEST_COMMERCIAL_NET_PRICE);
        if (!StringUtils.isBlank(medResLogic.getGroupName(priceTypeMap.get(MEDICAID_URA_BASIC_URA)))) {
            priceType.addItem(priceTypeMap.get(MEDICAID_URA_BASIC_URA));
        }

        if (!StringUtils.isBlank(medResLogic.getGroupName(priceTypeMap.get(MEDICAID_URA_CPI_URA)))) {
            priceType.addItem(priceTypeMap.get(MEDICAID_URA_CPI_URA));
        }

        if (!StringUtils.isBlank(medResLogic.getGroupName(priceTypeMap.get(MEDICAID_URA_TOTAL_URA)))) {
            priceType.addItem(priceTypeMap.get(MEDICAID_URA_TOTAL_URA));
        }
    }
    
    private void loadPriceTypeReplaceMap(final Map<String, String> priceTypeMap) {
        priceTypeReplaceMap.put(Constant.WAC, Constant.WAC);
        priceTypeReplaceMap.put(Constant.AMP, Constant.AMP);
        priceTypeReplaceMap.put(priceTypeMap.get(MEDICAID_URA_BEST_PRICE), Constant.BEST_PRICE_LOWERCASE);
        priceTypeReplaceMap.put(LOWEST_COMMERCIAL_NET_PRICE, LOWEST_COMMERCIAL_NET_PRICE);
        priceTypeReplaceMap.put(priceTypeMap.get(MEDICAID_URA_BASIC_URA), "Basic URA");
        priceTypeReplaceMap.put(priceTypeMap.get(MEDICAID_URA_CPI_URA), Constant.CPIURA);
        priceTypeReplaceMap.put(priceTypeMap.get(MEDICAID_URA_TOTAL_URA), "Total URA");
    }
    
    private void loadPriceActualMap(final Map<String, String> priceTypeMap) {
        loadPriceActualMap.put(Constant.WAC, Constant.WAC);
        loadPriceActualMap.put(Constant.AMP, Constant.AMP);
        loadPriceActualMap.put(Constant.BEST_PRICE_LOWERCASE, priceTypeMap.get(MEDICAID_URA_BEST_PRICE));
        loadPriceActualMap.put(LOWEST_COMMERCIAL_NET_PRICE, LOWEST_COMMERCIAL_NET_PRICE);
        loadPriceActualMap.put("Basic URA", priceTypeMap.get(MEDICAID_URA_BASIC_URA));
        loadPriceActualMap.put(Constant.CPIURA, priceTypeMap.get(MEDICAID_URA_CPI_URA));
        loadPriceActualMap.put("Total URA", priceTypeMap.get(MEDICAID_URA_TOTAL_URA));
    }

}
