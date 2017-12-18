/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.TableDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.MedicaidURAResultsLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.tablelogic.MedicaidWorkSheetTableLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.queryutils.MedicaidQueryUtils;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad.BrandContainer;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad.BrandCriteria;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad.NdcContainer;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad.NdcCriteria;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUiUtils;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.DESCRIPTION;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.*;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.WindowMessagesName.RESET_CONFIRMATION;
import com.stpl.app.gtnforecasting.nationalassumptions.util.NotesTextField;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.ExtCustomTreeTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import com.vaadin.ui.Table;

/**
 * The Class MedicaidUraWorkSheet.
 *
 * @author Vinodhini
 */
public class MedicaidUraWorkSheet extends Window {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -5631894964184716246L;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(MedicaidUraWorkSheet.class);

    /**
     * The excel btn.
     */
    @UiField("excelBtn")
    private Button excelBtn;

    @UiField("brand")
    private ComboBox brandDdlb;

    @UiField("ndc")
    private ComboBox ndcDdlb;

    /**
     * The expand.
     */
    @UiField("calculate")
    private Button calculateBtn;

    @UiField("generate")
    private Button generateBtn;

    /**
     * The reset.
     */
    @UiField("tableReset")
    private Button tableResetBtn;

    /**
     * The submitBtn.
     */
    @UiField("submitBtn")
    private Button submitBtn;

    /**
     * The close.
     */
    @UiField("close")
    private Button closeBtn;

    /**
     * The reset.
     */
    @UiField("reset")
    private Button reset;

     @UiField("cpiTable")
    private Table cpiTable;
     
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());

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
    public static final String ADJUSTMENT_CPI = "Adjustment CPI";
    public static final String CPIU_LABEL = "CPI-U";
    /**
     * The table control Layout.
     */
    private HorizontalLayout controlLayout;
    /**
     * The TableVerticalLayout.
     */
    @UiField("tableVerticalLayout")
    private VerticalLayout tableVerticalLayout;
    
    private final HelperDTO brandDto = new HelperDTO(0, SELECT_ONE.getConstant());
    private MedicaidWorkSheetTableLogic tableLogic = new MedicaidWorkSheetTableLogic();
    private FreezePagedTreeTable periodTableId = new FreezePagedTreeTable(tableLogic);
    private CustomTableHeaderDTO leftHeader = new CustomTableHeaderDTO();
    private CustomTableHeaderDTO rightHeader = new CustomTableHeaderDTO();
    private CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    private ExtTreeContainer<TableDTO> resultBeanContainer = new ExtTreeContainer<>(TableDTO.class,ExtContainer.DataStructureMode.MAP);
    private final MedicaidURAResultsLogic medLogic = new MedicaidURAResultsLogic();
    private final ProjectionSelectionDTO projectionDTO;
    private LazyContainer ndcContainer;
    private LazyContainer brandContainer;
    private final HelperDTO dto = new HelperDTO(0, SELECT_ONE.getConstant());
    private ExtCustomTreeTable exceltable = new ExtCustomTreeTable();
    private ExtTreeContainer<TableDTO> excelResultBeanContainer = new ExtTreeContainer<>(TableDTO.class,ExtContainer.DataStructureMode.MAP);
    public static final String MODE = (String) VaadinSession.getCurrent().getAttribute(Constant.MODE);
    private Property.ValueChangeListener valueChangeListener = null;
    private Property.ValueChangeListener valueChangeListenerTA = null;
    private boolean valueChange = false;
    private boolean valueTAChange = false;
    private String description = StringUtils.EMPTY;
    private Map<String, String> adjustedValues = new HashMap<>();
    private Map<String, String> editedNotes = new HashMap<>();

    private Map<String, String> secondAdjustedValues = new HashMap<>();
    private Map<String, String> secondEditedNotes = new HashMap<>();

    private Map<String, String> thirdAdjustedValues = new HashMap<>();
    private Map<String, String> fourthAdjustedValues = new HashMap<>();
    private Map<String, String> thirdEditedNotes = new HashMap<>();
    private MedicaidQueryUtils queryUtil = new MedicaidQueryUtils();

    private Map<String, String> bpAdjustedValues = new HashMap<>();
    private Map<String, String> bpEditedNotes = new HashMap<>();
    private Map<String, String> cpiAdjustedValues = new HashMap<>();
    private Map<String, String> cpiEditedNotes = new HashMap<>();
    private boolean submitFlag = false;
    private boolean submitMsg = false;
    private HelperDTO ndcResultdto;
    private HelperDTO brandResultdto;
    private SessionDTO sessionDTO;
    private boolean isFirestTimeLoadAMP = true, isFirestTimeLoadCPI = true;
    private Map<String, String> baseYear = new HashMap<>();

    /**
     * Instantiates a new master fcp work sheet.
     *
     * @param projSelection
     */
    public MedicaidUraWorkSheet(ProjectionSelectionDTO projSelection,SessionDTO sessionDTO) {
        super(Constant.MEDICAID_URA_WORKSHEET);
        LOGGER.debug("MedicaidUraWorkSheet Constructor initiated ");
        this.projectionDTO = projSelection;
        this.ndcResultdto = projSelection.getNdcWSdto();
        this.brandResultdto = projSelection.getBrandWSdto();
        this.sessionDTO=sessionDTO;
        init();
        LOGGER.debug("MedicaidUraWorkSheet Constructor Ended ");
    }

    /**
     * Inits the.
     */
    public void init() {

        LOGGER.debug("Entering init ");
        center();
        setClosable(true);
        setModal(true);
        setWidth(NumericConstants.FLOAT_EIGHTY_FIVE, Sizeable.Unit.PERCENTAGE);
        setContent(Clara.create(getClass().getResourceAsStream("/nationalassumption/MedicaidUraWorksheet.xml"), this));
        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        configureFields();
        LOGGER.debug(" init Ends");
    }

    /**
     * Configure fields.
     */
    private void configureFields() {

        excelBtn.setIcon(excelExportImage);

        initializeResultTable();
        configureResultTable();
        addResultTable();
        loadResultTable(0, StringUtils.EMPTY);

        brandDdlb.focus();
        brandDdlb.setPageLength(NumericConstants.SEVEN);
        brandDdlb.setImmediate(true);
        brandDdlb.setNullSelectionAllowed(true);
        brandDdlb.setInputPrompt(SELECT_ONE.getConstant());
        brandDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        brandDdlb.setItemCaptionPropertyId(DESCRIPTION.getConstant());
        brandDdlb.markAsDirty();
        if (!projectionDTO.isBrandSeclected()) {
            projectionDTO.setBrand(brandResultdto);
        }
        if(brandContainer!=null){
            brandContainer.removeAllItems();
        }
        brandContainer = new LazyContainer(HelperDTO.class, new BrandContainer(projectionDTO.getTherapeuticSid(), projectionDTO.getBrand()), new BrandCriteria());
        brandContainer.setMinFilterLength(0);
        brandDdlb.setContainerDataSource(brandContainer);
      if(brandContainer.size()==1){
            projectionDTO.setBrand(brandDto);
        }
        brandDdlb.select(projectionDTO.getBrand());
        loadNdc();
        ndcDdlb.select(ndcResultdto);
        
          cpiTable.addContainerProperty("Item ID", String.class, null);
        cpiTable.addContainerProperty("Item Name", String.class, null);
        if (projectionDTO.getNewFormulation() != null && !projectionDTO.getNewFormulation().isEmpty() && !"null".equals(projectionDTO.getNewFormulation())) {
            Object item = cpiTable.addItem();
            cpiTable.getItem(item).getItemProperty("Item ID").setValue(projectionDTO.getNewFormulation());
            cpiTable.getItem(item).getItemProperty("Item Name").setValue(projectionDTO.getNewFormulationItemId());
        }

        ndcDdlb.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                LOGGER.debug("ndcDdlb ValueChangeEvent initiated " + ndcDdlb.getValue());

                if (ndcDdlb.getValue() != null && !SELECT_ONE.getConstant().equals(String.valueOf(ndcDdlb.getValue()))) {
                    HelperDTO helperDTO = (HelperDTO) ndcDdlb.getValue();
                    int ndcId = helperDTO.getId();
                    projectionDTO.setNdc9(String.valueOf(helperDTO.getId()));
                    LOGGER.debug("ndcDdlb ValueChangeEvent ends theraupeyticId   " + ndcId);

                }
            }
        });

        brandDdlb.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                LOGGER.debug("brandDdlb ValueChangeEvent initiated " + brandDdlb.getValue());

                if (brandDdlb.getValue() != null && !SELECT_ONE.getConstant().equals(String.valueOf(brandDdlb.getValue()))) {
                    HelperDTO helperDTO = (HelperDTO) brandDdlb.getValue();
                    projectionDTO.setBrandWSdto(helperDTO);
                    ndcContainer.removeAllItems();
                    loadNdc();
                    ndcDdlb.select(dto);
                    LOGGER.debug("brandDdlb ValueChangeEvent ends brandDdlb   " + helperDTO.getId());

                } else {
                    projectionDTO.setBrandWSdto(dto);
                }
            }
        });
        if (Constant.VIEW.equalsIgnoreCase(MODE)) {
            disableFieldsOnView();
        }
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId =  sessionDTO.getUserId();
        try {
            final Map<String, AppPermission> tabItemHM = stplSecurity.getBusinessFunctionPermission(userId, NATIONAL_ASSUMPTIONS.getConstant() + "," + MEDICAID_URA_WORKSHEET_LOOKUP.getConstant());
            if (tabItemHM.get("generate") != null && tabItemHM.get("generate").isFunctionFlag()) {
                generateBtn.setVisible(true);
            } else {
                generateBtn.setVisible(false);
            }
            if (tabItemHM.get("reset") != null && tabItemHM.get("reset").isFunctionFlag()) {
                reset.setVisible(true);
            } else {
                reset.setVisible(false);
            }
            if (tabItemHM.get("excelBtn") != null && tabItemHM.get("excelBtn").isFunctionFlag()) {
                excelBtn.setVisible(true);
            } else {
                excelBtn.setVisible(false);
            }
            if (tabItemHM.get("calculate") != null && tabItemHM.get("calculate").isFunctionFlag()) {
                calculateBtn.setVisible(true);
            } else {
                calculateBtn.setVisible(false);
            }
            if (tabItemHM.get("tableReset") != null && tabItemHM.get("tableReset").isFunctionFlag()) {
                tableResetBtn.setVisible(true);
            } else {
                tableResetBtn.setVisible(false);
            }
            if (tabItemHM.get("submitBtn") != null && tabItemHM.get("submitBtn").isFunctionFlag()) {
                submitBtn.setVisible(true);
            } else {
                submitBtn.setVisible(false);
            }
            if (tabItemHM.get("close") != null && tabItemHM.get("close").isFunctionFlag()) {
                closeBtn.setVisible(true);
            } else {
                closeBtn.setVisible(false);
            }
        } catch (PortalException portal) {
            LOGGER.error(portal);
        } catch (SystemException system) {
            LOGGER.error(system);
        }
    }

    @UiHandler("generate")
    public void generate(Button.ClickEvent event) {
        if (checkSelection()) {
            tableVerticalLayout.removeAllComponents();
            tableLogic = new MedicaidWorkSheetTableLogic();
            periodTableId = new FreezePagedTreeTable(tableLogic);
            initializeResultTable();
            configureResultTable();
            addResultTable();
            loadResultTable(0, StringUtils.EMPTY);

        } else {
            AbstractNotificationUtils.getErrorNotification("Generate Error ", "You must select an NDC from the drop down list box.");
        }
    }

    /**
     * Reset button click listener.
     *
     * @param event the event
     */
    @UiHandler("reset")
    public void reset(Button.ClickEvent event) {
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
                brandDdlb.select(dto);
                ndcDdlb.select(dto);
            }
        }.getConfirmationMessage(RESET_CONFIRMATION.getConstant(), "Are you sure you want to reset the page to default/previous values?");
    }

    /**
     * Table reset button click listener.
     *
     * @param event the event
     */
    @UiHandler("tableReset")
    public void tableReset(Button.ClickEvent event) {

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
                clearMap();
                removeBaseYear();
                closeLogic();
                loadResultTable(0, StringUtils.EMPTY);
            }
        }.getConfirmationMessage(RESET_CONFIRMATION.getConstant(), "Are you sure you want to reset the page to default/previous values?");
    }

    /**
     * Close button click listener.
     *
     * @param event the event
     */
    @UiHandler("close")
    public void close(Button.ClickEvent event) {
        if (!Constant.VIEW.equalsIgnoreCase(MODE)) {
            if (submitFlag) {
                new AbstractNotificationUtils() {
                    public void noMethod() {
                        // do nothing
                    }

                    @Override
                    /**
                     * The method is triggered when Yes button of the message
                     */
                    public void yesMethod() {
                         projectionDTO.setBrandSeclected(false);
                        close();
                    }
                }.getOkCancelMessage("Close Confirmation", "Are you sure you want to close the Worksheet? ");
            } else {
                new AbstractNotificationUtils() {
                    public void noMethod() {
                        // do nothing
                    }

                    @Override
                    /**
                     * The method is triggered when Yes button of the message
                     * box is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    public void yesMethod() {
                        close();
                    }
                }.getOkCancelMessage("Close Confirmation", "Are you sure you want to close the Worksheet? Unsaved changes will be lost.");
            }
        } else {
            close();
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
        leftHeader = CommonUiUtils.getMedicaidWorkSheetLeftTableColumns(fullHeader);
        rightHeader = CommonUiUtils.getMedicaidWorkSheetRightTableColumns(projectionDTO, fullHeader);
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
        leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));
        rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
        }

        periodTableId.setDoubleHeaderVisible(true);
        periodTableId.setHeight(Constant.THREE_FIFTY_THREE_PX);
        leftTable.setHeight(Constant.THREE_FIFTY_THREE_PX);
        rightTable.setHeight(Constant.THREE_FIFTY_THREE_PX);
        leftTable
                .setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
        leftTable
                .setDoubleHeaderColumnHeaders(leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));
        rightTable
                .setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
        rightTable
                .setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));

        periodTableId.setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps(), rightHeader.getDoubleHeaderMaps());
        rightTable.setEditable(true);
        leftTable.setEditable(true);
        rightTable.setTableFieldFactory(new DefaultFieldFactory() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            /**
             * To create editable fields inside table .
             */
            public Field<?> createField(final Container container,
                    final Object itemId, final Object propertyId,
                    final Component uiContext) {
                final TableDTO tableDto = getBeanFromId(itemId);

                if (tableDto.getParent() == 0 && (String.valueOf(propertyId).endsWith(Constant.PROJECTIONS)
                        && (tableDto.getGroup().startsWith(Constant.ADJUSTMENT) && !tableDto.getGroup().equalsIgnoreCase("Adjustment CPI (Alt)") || tableDto.getGroup().startsWith(Constant.OVERRIDE)))
                        || (String.valueOf(propertyId).equalsIgnoreCase("Base Year") && tableDto.getGroup().equalsIgnoreCase(Constant.AMP)
                        && tableDto.getGroup().equalsIgnoreCase(CPIU_LABEL))) {

                    try {
                        final NotesTextField notesField = new NotesTextField();
                        String adjustPropId = propertyId + "~" + Constant.ADJUSTMENT;
                        String notesPropId = propertyId + "~" + Constant.NOTES;
                        notesField.seTextProperty(container.getContainerProperty(itemId, propertyId + Constant.ADJUSTMENT));
                        notesField.setData(propertyId + "~" + getBeanFromId(itemId).getGroup());
                        notesField.addStyleName(Constant.ALIGN_RIGHT);
                        notesField.setWidth(NumericConstants.THIRTY_FIVE, Sizeable.Unit.PERCENTAGE);
                        notesField.setTextData(adjustPropId);
                        Map<String, String[]> notesMap = new HashMap<>();

                        String adjustValue = StringUtils.EMPTY;
                        String notesValue = StringUtils.EMPTY;
                        if (tableDto.getGroup().startsWith(Constant.ADJUSTMENT_AMP) || tableDto.getGroup().startsWith(Constant.OVERRIDE_AMP)) {
                            notesMap = projectionDTO.getNotesMap();
                            adjustValue = adjustedValues.get(adjustPropId);
                            notesValue = editedNotes.get(notesPropId);
                        }
                        if (tableDto.getGroup().startsWith(Constant.ADJUSTMENT_BEST_PRICE) || tableDto.getGroup().startsWith(Constant.OVERRIDE_BEST_PRICE)) {
                            notesMap = projectionDTO.getSecondRowNotesMap();
                            adjustValue = secondAdjustedValues.get(adjustPropId);
                            notesValue = secondEditedNotes.get(notesPropId);
                        }
                        if (tableDto.getGroup().startsWith(ADJUSTMENT_CPI)) {
                            notesMap = projectionDTO.getThirdRowNotesMap();
                            adjustValue = thirdAdjustedValues.get(adjustPropId);
                            notesValue = thirdEditedNotes.get(notesPropId);
                        }
                        if (tableDto.getGroup().equalsIgnoreCase(Constant.OVERRIDE_CPI_URA)) {
                            notesMap = projectionDTO.getFourthRowNotesMap();
                            adjustValue = fourthAdjustedValues.get(adjustPropId);
                        }

                        String[] noteArr = notesMap.get(String.valueOf(propertyId));
                        if (StringUtils.isNotBlank(adjustValue)) {
                            notesField.setTextfieldValue(adjustValue);
                        } 
                        else if (noteArr != null && noteArr.length != 0) {
                                notesField.setTextfieldValue(noteArr[0]);
                            }
                        
                        notesField.addTextFieldFocusListener(new FieldEvents.FocusListener() {

                            @Override
                            public void focus(FieldEvents.FocusEvent event) {

                                attachValueChangeListener((AbstractField) event.getComponent());
                            }

                        });
                        notesField.addTextFieldBlurListener(new FieldEvents.BlurListener() {

                            @Override
                            public void blur(FieldEvents.BlurEvent event) {

                                if (valueChange) {
                                    try {
                                        if (tableDto.getGroup().startsWith(Constant.ADJUSTMENT_AMP) || tableDto.getGroup().startsWith(Constant.OVERRIDE_AMP)) {
                                            adjustedValues.put(String.valueOf(((TextField) event.getComponent()).getData()), String.valueOf(((TextField) event.getComponent()).getValue()));
                                        }

                                        if (tableDto.getGroup().startsWith(Constant.ADJUSTMENT_BEST_PRICE) || tableDto.getGroup().startsWith(Constant.OVERRIDE_BEST_PRICE)) {
                                            secondAdjustedValues.put(String.valueOf(((TextField) event.getComponent()).getData()), String.valueOf(((TextField) event.getComponent()).getValue()));
                                        }
                                        if (tableDto.getGroup().startsWith(ADJUSTMENT_CPI)) {
                                            thirdAdjustedValues.put(String.valueOf(((TextField) event.getComponent()).getData()), String.valueOf(((TextField) event.getComponent()).getValue()));
                                        }
                                        if (tableDto.getGroup().equalsIgnoreCase(Constant.OVERRIDE_CPI_URA)) {
                                            fourthAdjustedValues.put(String.valueOf(((TextField) event.getComponent()).getData()), String.valueOf(((TextField) event.getComponent()).getValue()));
                                        }
                                        valueChange = false;
                                    } catch (Exception ex) {
                                        LOGGER.error(ex);
                                    }
                                }
                                detachLisener((AbstractField) event.getComponent());
                            }

                        });
                        // for text area

                        notesField.setTextAreaData(notesPropId);
                        notesField.setNotesProperty(container.getContainerProperty(itemId, propertyId + Constant.NOTES));

                        if (StringUtils.isNotBlank(notesValue)) {
                            notesField.setNotesValue(notesValue);
                            notesField.addToolTip(notesValue);
                        } else   if (noteArr != null && noteArr.length != 0) {
                                notesField.setNotesValue(noteArr[1]);
                                notesField.addToolTip(noteArr[1]);
                            
                        }
                        notesField.addTextAreaFocusListener(new FieldEvents.FocusListener() {

                            @Override
                            public void focus(FieldEvents.FocusEvent event) {

                                attachTAValueChangeListener((AbstractField) event.getComponent());
                            }

                        });

                        notesField.addTextAreaBlurListener(new FieldEvents.BlurListener() {

                            @Override
                            public void blur(FieldEvents.BlurEvent event) {

                                if (valueTAChange) {
                                    try {

                                        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(" MM/dd/YYYY, HH.mm.ss");
                                        String formattedValue = String.valueOf(((TextArea) event.getComponent()).getValue()) + dateTimeFormat.format(new Date()) + " ,<" + CommonUtils.getUserNameById( sessionDTO.getUserId()) + ">";
                                        description = formattedValue;
                                        if (tableDto.getGroup().startsWith(Constant.ADJUSTMENT_AMP) || tableDto.getGroup().startsWith(Constant.OVERRIDE_AMP)) {
                                            editedNotes.put(String.valueOf(((TextArea) event.getComponent()).getData()), formattedValue);
                                        }
                                        if (tableDto.getGroup().startsWith(Constant.ADJUSTMENT_BEST_PRICE) || tableDto.getGroup().startsWith(Constant.OVERRIDE_BEST_PRICE)) {
                                            secondEditedNotes.put(String.valueOf(((TextArea) event.getComponent()).getData()), formattedValue);
                                        }
                                        if (tableDto.getGroup().startsWith(ADJUSTMENT_CPI) || tableDto.getGroup().startsWith(Constant.OVERRIDE_CPI_URA)) {
                                            thirdEditedNotes.put(String.valueOf(((TextArea) event.getComponent()).getData()), formattedValue);
                                        }
                                        valueTAChange = false;

                                    } catch (Exception ex) {
                                        LOGGER.error(ex);
                                    }
                                }
                                notesField.addToolTip(description);
                                detachLisener((AbstractField) event.getComponent());
                            }

                        });
                        if (Constant.VIEW.equalsIgnoreCase(MODE)) {
                            notesField.setEnable(false);
                        }

                        return notesField;

                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }

                return null;

            }
        });
        leftTable.setTableFieldFactory(new DefaultFieldFactory() {

            private static final long serialVersionUID = 1L;

            /**
             * To create editable fields inside table .
             */
            @Override
            public Field<?> createField(final Container container,
                    final Object itemId, final Object propertyId,
                    final Component uiContext) {
                final TableDTO tableDto = getBeanFromId(itemId);
                if (String.valueOf(propertyId).equalsIgnoreCase("baseYear") && (tableDto.getGroup().equalsIgnoreCase(Constant.AMP)
                        || tableDto.getGroup().equalsIgnoreCase(CPIU_LABEL))) {

                    try {
                        final NotesTextField notesField = new NotesTextField();
                        String adjustPropId = propertyId + "~" + Constant.ADJUSTMENT;
                        String notesPropId = propertyId + "~" + Constant.NOTES;
                        notesField.seTextProperty(container.getContainerProperty(itemId, propertyId + Constant.ADJUSTMENT));
                        notesField.setData(propertyId + "~" + getBeanFromId(itemId).getGroup());
                        notesField.addStyleName(Constant.ALIGN_RIGHT);
                        notesField.setWidth(NumericConstants.THIRTY_FIVE, Sizeable.Unit.PERCENTAGE);
                        notesField.setTextData(adjustPropId);
                        String[] noteArr = new String[NumericConstants.TWO];
                        String adjustValue = StringUtils.EMPTY;
                        String notesValue = StringUtils.EMPTY;
                        if (tableDto.getGroup().startsWith(Constant.AMP)) {
                            noteArr[0] = tableDto.getBaseYearAmp();
                            noteArr[1] = tableDto.getBaseYearAmpNotes();
                            adjustValue = bpAdjustedValues.get(adjustPropId);
                            notesValue = bpEditedNotes.get(notesPropId);
                            if(isFirestTimeLoadAMP){
                                baseYear.put(adjustPropId + "~" + Constant.AMP, tableDto.getBaseYearAmp());
                                isFirestTimeLoadAMP = false;
                        }
                        }
                        if (tableDto.getGroup().startsWith(CPIU_LABEL)) {
                            noteArr[0] = tableDto.getBaseYearCpi();
                            noteArr[1] = tableDto.getBaseYearCpiNotes();
                            adjustValue = cpiAdjustedValues.get(adjustPropId);
                            notesValue = cpiEditedNotes.get(notesPropId);
                            if(isFirestTimeLoadCPI){
                                baseYear.put(adjustPropId + "~" + CPIU_LABEL, tableDto.getBaseYearCpi());
                                isFirestTimeLoadCPI = false;
                        }
                        }
                        if (StringUtils.isNotBlank(adjustValue)) {
                            notesField.setTextfieldValue(adjustValue);
                        } else if (noteArr.length != 0) {
                                notesField.setTextfieldValue(noteArr[0]);
                            
                        }
                        notesField.addTextFieldFocusListener(new FieldEvents.FocusListener() {

                            @Override
                            public void focus(FieldEvents.FocusEvent event) {

                                attachValueChangeListener((AbstractField) event.getComponent());
                            }

                        });
                        notesField.addTextFieldBlurListener(new FieldEvents.BlurListener() {

                            @Override
                            public void blur(FieldEvents.BlurEvent event) {

                                if (valueChange) {
                                    try {

                                        if (tableDto.getGroup().startsWith(Constant.AMP)) {
                                            bpAdjustedValues.put(String.valueOf(((TextField) event.getComponent()).getData()), String.valueOf(((TextField) event.getComponent()).getValue()));
                                        }

                                        if (tableDto.getGroup().startsWith(CPIU_LABEL)) {
                                            cpiAdjustedValues.put(String.valueOf(((TextField) event.getComponent()).getData()), String.valueOf(((TextField) event.getComponent()).getValue()));
                                        }
                                        valueChange = false;
                                    } catch (Exception ex) {
                                        LOGGER.error(ex);
                                    }
                                }
                                detachLisener((AbstractField) event.getComponent());
                            }

                        });
                        // for text area 
                        notesField.setTextAreaData(notesPropId);
                        notesField.setNotesProperty(container.getContainerProperty(itemId, propertyId + Constant.NOTES));

                        if (StringUtils.isNotBlank(notesValue)) {
                            notesField.setNotesValue(notesValue);
                            notesField.addToolTip(notesValue);
                        } else  if ( noteArr.length != 0) {
                                notesField.setNotesValue(noteArr[1]);
                                notesField.addToolTip(noteArr[1]);
                            
                        }
                        notesField.addTextAreaFocusListener(new FieldEvents.FocusListener() {

                            @Override
                            public void focus(FieldEvents.FocusEvent event) {

                                attachTAValueChangeListener((AbstractField) event.getComponent());
                            }

                        });

                        notesField.addTextAreaBlurListener(new FieldEvents.BlurListener() {

                            @Override
                            public void blur(FieldEvents.BlurEvent event) {

                                if (valueTAChange) {
                                    try {
                                        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(" MM/dd/YYYY, HH.mm.ss");
                                        String formattedValue = String.valueOf(((TextArea) event.getComponent()).getValue()) + dateTimeFormat.format(new Date()) + " ,<" + CommonUtils.getUserNameById( sessionDTO.getUserId()) + ">";
                                        description = formattedValue;
                                        if (tableDto.getGroup().startsWith(Constant.AMP)) {
                                            bpEditedNotes.put(String.valueOf(((TextArea) event.getComponent()).getData()), formattedValue);
                                        }
                                        if (tableDto.getGroup().startsWith(CPIU_LABEL)) {
                                            cpiEditedNotes.put(String.valueOf(((TextArea) event.getComponent()).getData()), formattedValue);
                                        }

                                        valueTAChange = false;
                                    } catch (Exception ex) {
                                        LOGGER.error(ex);
                                    }
                                }
                                notesField.addToolTip(description);
                                detachLisener((AbstractField) event.getComponent());
                            }

                        });
                        notesField.addToolTip(description);
                        if (Constant.VIEW.equalsIgnoreCase(MODE)) {
                            notesField.setEnable(false);
                        }
                                if (projectionDTO.getNewFormulation() != null && !projectionDTO.getNewFormulation().isEmpty() && !"null".equals(projectionDTO.getNewFormulation())) {

                            if (tableDto.getGroup().equalsIgnoreCase(Constant.AMP)) {
                                container.getContainerProperty(itemId, propertyId).setValue(projectionDTO.getBaeselineAmp());
                                notesField.setTextfieldValue(projectionDTO.getBaeselineAmp());
                            } else if (tableDto.getGroup().equalsIgnoreCase(CPIU_LABEL)) {
                                container.getContainerProperty(itemId, propertyId).setValue(projectionDTO.getBaeselineCpi());
                                notesField.setTextfieldValue(projectionDTO.getBaeselineCpi());
                            }

                        }
                                
                        return notesField;

                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }

                return null;

            }
        });
        valueChangeListener = new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                valueChange = true;
            }
        };

        valueChangeListenerTA = new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                valueTAChange = true;
            }
        };
    }

    public boolean checkSelection() {
        boolean flag = true;
        if (StringUtils.isBlank(String.valueOf(ndcDdlb.getValue()))) {
            flag = false;
        }
        return flag;
    }

    private void loadResultTable(int levelNo, String hierarchyNo) {
        try {
            tableLogic.clearAll();
            tableLogic.setRefresh(false);
            projectionDTO.clearNonFetchableIndex();
            tableLogic.setProjectionResultsData(projectionDTO, levelNo, hierarchyNo,sessionDTO);
            tableLogic.setRefresh(true);

        } catch (Exception e) {
            LOGGER.error(e);
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

    void loadNdc() {
        ndcDdlb.setPageLength(NumericConstants.SEVEN);
        ndcDdlb.setImmediate(true);
        ndcDdlb.setNullSelectionAllowed(true);
        ndcDdlb.setInputPrompt(SELECT_ONE.getConstant());
        ndcDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        ndcDdlb.setItemCaptionPropertyId(DESCRIPTION.getConstant());
        ndcDdlb.markAsDirty();
        ndcContainer = new LazyContainer(HelperDTO.class, new NdcContainer(projectionDTO.getBrandWSdto(), false, ndcResultdto), new NdcCriteria());
        ndcContainer.setMinFilterLength(0);
        ndcDdlb.setContainerDataSource(ndcContainer);

    }

    /**
     * excel click listener.
     *
     * @param event the event
     */
    @UiHandler("excelBtn")
    public void excelBtn(final Button.ClickEvent event) {
        LOGGER.debug("excelBtn click listener started");
        configureExcelResultTable();
        loadExcelResultTable();
        ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(exceltable), Constant.MEDICAID_URA_WORKSHEET, Constant.MEDICAID_URA_WORKSHEET, "Medicaid_URA_Worksheet.xls", false);
        exp.export();
        tableVerticalLayout.removeComponent(exceltable);
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

    private void loadExcelResultTable() {
        excelResultBeanContainer.removeAllItems();
        projectionDTO.setExcel(true);
        List<TableDTO> resultList = medLogic.getMedicaidWorksheet(projectionDTO,sessionDTO);
        loadDataToContainer(resultList, null);
        projectionDTO.setExcel(false);
    }

    public void loadDataToContainer(List<TableDTO> resultList, Object parentId) {
        for (TableDTO tableDto : resultList) {
            excelResultBeanContainer.addBean(tableDto);
            if (parentId != null) {
                excelResultBeanContainer.setParent(tableDto, parentId);
            }
            if (tableDto.getParent() == 1) {
                excelResultBeanContainer.setChildrenAllowed(tableDto, true);
                addLowerLevelsForExport(tableDto);
            } else {
                excelResultBeanContainer.setChildrenAllowed(tableDto, false);
            }
        }
    }

    public void addLowerLevelsForExport(TableDTO id) {
        projectionDTO.setGroup(id.getGroup());
        List<TableDTO> resultList = medLogic.getMedicaidWorksheetChild(projectionDTO, sessionDTO);
        loadDataToContainer(resultList, id);
    }

    public void disableFieldsOnView() {

        brandDdlb.setEnabled(false);
        ndcDdlb.setEnabled(false);
        reset.setEnabled(false);
        generateBtn.setEnabled(false);
        calculateBtn.setEnabled(false);
        tableResetBtn.setEnabled(false);
        submitBtn.setEnabled(false);
    }


    private void attachValueChangeListener(AbstractField component) {

        component.addValueChangeListener(valueChangeListener);

    }

    private void attachTAValueChangeListener(AbstractField component) {

        component.addValueChangeListener(valueChangeListenerTA);

    }

    private void detachLisener(AbstractField abstractField) {
        List<Property.ValueChangeListener> listeners;
        listeners = (List<Property.ValueChangeListener>) abstractField.getListeners(Property.ValueChangeEvent.class);
        for (final Property.ValueChangeListener object : listeners) {
            abstractField.removeValueChangeListener(object);
        }
    }

    @UiHandler("submitBtn")
    public void submitBtn(final Button.ClickEvent event) {
        if (submitMsg) {
            new AbstractNotificationUtils() {
                public void noMethod() {
//
                }

                @Override
                /**
                 * The method is triggered when Yes button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                public void yesMethod() {
                    MedicaidQueryUtils QueryUtil = new MedicaidQueryUtils();
                    try {
                        QueryUtil.updateAdjustment(projectionDTO.getNdc9(), "updateMedicaidAdjustment",sessionDTO);
                        submitFlag = true;
                        submitMsg = false;
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            }.getConfirmationMessage("Submit Confirmation", "Are you sure you want to submit these changes?");

        } else {
            AbstractNotificationUtils.getErrorNotification("Submit Error", "There are no changes to submit. ");
        }
    }

    @UiHandler("calculate")
    public void calculate(Button.ClickEvent event) {
        try {
            boolean formatFlag = false;
            formatFlag = checkNotes(adjustedValues);

            if (!formatFlag) {
                formatFlag = checkNotes(secondAdjustedValues);
            }
            if (!formatFlag) {
                formatFlag = checkNotes(thirdAdjustedValues);
            }
            if (!formatFlag) {
                formatFlag = checkNotes(bpAdjustedValues);
            }
            if (!formatFlag) {
                formatFlag = checkNotes(cpiAdjustedValues);
            }

            if (formatFlag) {
                AbstractNotificationUtils.getErrorNotification("Calculate Error", "Override AMP values must be entered in dollar format with 4 decimal places.");
            } else {
                boolean notesFlag = false;
                boolean adjustFlag = false;
                boolean isCpiUra= false;
               
                if (!editedNotes.isEmpty()) {
                    queryUtil.saveNotes(editedNotes,sessionDTO, projectionDTO.getNdc9(), Constant.AMP);
                    notesFlag = true;
                    editedNotes.clear();
                }
                if (!secondAdjustedValues.isEmpty()) {
                    queryUtil.saveNotes(secondAdjustedValues, sessionDTO, projectionDTO.getNdc9(), Constant.BEST_PRICE);
                    adjustFlag = true;
                    secondAdjustedValues.clear();
                }
                if (!secondEditedNotes.isEmpty()) {
                    queryUtil.saveNotes(secondEditedNotes, sessionDTO, projectionDTO.getNdc9(), Constant.BEST_PRICE);
                    notesFlag = true;
                    secondEditedNotes.clear();
                }
                if (!thirdAdjustedValues.isEmpty()) {
                    queryUtil.saveNotes(thirdAdjustedValues,sessionDTO, projectionDTO.getNdc9(), Constant.CPIURA);
                    adjustFlag = true;
                    thirdAdjustedValues.clear();
                }
                if (!fourthAdjustedValues.isEmpty()) {
                    queryUtil.saveNotes(fourthAdjustedValues,sessionDTO, projectionDTO.getNdc9(), Constant.CPIURA);
                    adjustFlag = true;
                    isCpiUra = true;
                    projectionDTO.setAdjust(true);
                    fourthAdjustedValues.clear();
                }
                 if (!adjustedValues.isEmpty()) {
                    queryUtil.saveNotes(adjustedValues,sessionDTO, projectionDTO.getNdc9(), Constant.AMP);
                    adjustFlag = true;
                    isCpiUra = false;
                    adjustedValues.clear();
                }
                if (!thirdEditedNotes.isEmpty()) {
                    queryUtil.saveNotes(thirdEditedNotes, sessionDTO, projectionDTO.getNdc9(), Constant.CPIURA);
                    notesFlag = true;
                    thirdEditedNotes.clear();
                }
                if (!cpiAdjustedValues.isEmpty()) {
                    queryUtil.saveBaseYear(cpiAdjustedValues, sessionDTO, projectionDTO.getNdc9(), CPIU_LABEL);
                    queryUtil.saveBaseYearNotes(cpiAdjustedValues, sessionDTO, projectionDTO.getNdc9(), CPIU_LABEL);
                    adjustFlag = true;
                    cpiAdjustedValues.clear();
                }
                if (!cpiEditedNotes.isEmpty()) {
                    queryUtil.saveBaseYear(cpiEditedNotes, sessionDTO, projectionDTO.getNdc9(), CPIU_LABEL);
                    queryUtil.saveBaseYearNotes(cpiEditedNotes, sessionDTO, projectionDTO.getNdc9(), CPIU_LABEL);
                    notesFlag = true;
                    cpiEditedNotes.clear();
                }
                if (!bpAdjustedValues.isEmpty()) {
                    queryUtil.saveBaseYear(bpAdjustedValues, sessionDTO, projectionDTO.getNdc9(), Constant.AMP);
                    queryUtil.saveBaseYearNotes(bpAdjustedValues, sessionDTO, projectionDTO.getNdc9(), Constant.AMP);
                    adjustFlag = true;
                    bpAdjustedValues.clear();
                }
                if (!bpEditedNotes.isEmpty()) {
                    queryUtil.saveBaseYear(bpEditedNotes, sessionDTO, projectionDTO.getNdc9(), Constant.AMP);
                    queryUtil.saveBaseYearNotes(bpEditedNotes,sessionDTO, projectionDTO.getNdc9(), Constant.AMP);
                    notesFlag = true;
                    bpEditedNotes.clear();
                }

                if (adjustFlag || notesFlag) {
                    if (adjustFlag && !isCpiUra) {
                        callAdjustmentProcedure();
                        projectionDTO.setAdjust(true);
                        submitMsg = true;
                    }
                    loadResultTable(0, StringUtils.EMPTY);
                     final Notification notif = new Notification("Calculation Complete", Notification.Type.HUMANIZED_MESSAGE);
                    notif.setPosition(Position.TOP_CENTER);
                    notif.setStyleName(ConstantsUtils.MY_STYLE);
                    notif.show(Page.getCurrent());
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public boolean checkNotes(Map<String, String> notesValues) {

        boolean formatFlag = false;

        for (String values : notesValues.keySet()) {
            String formatedValue = notesValues.get(values);
            formatedValue = formatedValue.replace("$", StringUtils.EMPTY);
            if ("-".equals(formatedValue) || "+".equals(formatedValue)) {
                formatFlag = true;
                break;
            }
                if ((StringUtils.isNotBlank(formatedValue)) && (!formatedValue.matches("^[\\+\\-]?\\d{0,5}\\.?\\d{0,4}$"))) {
                    formatFlag = true;
                    break;
                }
            }
        return formatFlag;
    }

    public void clearMap() {
        adjustedValues.clear();
        editedNotes.clear();
        secondAdjustedValues.clear();
        secondEditedNotes.clear();
        thirdAdjustedValues.clear();
        thirdEditedNotes.clear();
        cpiAdjustedValues.clear();
        cpiEditedNotes.clear();
        bpAdjustedValues.clear();
        bpEditedNotes.clear();

    }

    private void callAdjustmentProcedure() {
        try {
            String priceType = "AMP,BEST PRICE";
            medLogic.workSheetSetupCook(projectionDTO.getNdcSid().getId(), priceType, "MEDICAID URA", projectionDTO.getNdc9(),sessionDTO);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public boolean isSubmit() {
        return submitFlag;
    }

    public void closeLogic() {
        try {
            queryUtil.updateAdjustment(projectionDTO.getNdc9(), "removeMedicaidAdjustment",sessionDTO);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public void removeBaseYear() {
        try {
            if (!isSubmit()) {
                if (!baseYear.isEmpty()) {
                    queryUtil.saveBaseYear(baseYear, sessionDTO, projectionDTO.getNdc9(),StringUtils.EMPTY);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public Map<String, String> getBaseYear() {
        return baseYear;
    }

}