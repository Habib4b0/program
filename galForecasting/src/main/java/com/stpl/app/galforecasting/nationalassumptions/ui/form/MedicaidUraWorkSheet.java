/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.nationalassumptions.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.nationalassumptions.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.nationalassumptions.dto.TableDTO;
import com.stpl.app.galforecasting.nationalassumptions.logic.MedicaidURAResultsLogic;
import com.stpl.app.galforecasting.nationalassumptions.logic.tablelogic.MedicaidWorkSheetTableLogic;
import com.stpl.app.galforecasting.nationalassumptions.queryutils.MedicaidQueryUtils;
import com.stpl.app.galforecasting.nationalassumptions.ui.lazyLoad.BrandContainer;
import com.stpl.app.galforecasting.nationalassumptions.ui.lazyLoad.BrandCriteria;
import com.stpl.app.galforecasting.nationalassumptions.ui.lazyLoad.NdcContainer;
import com.stpl.app.galforecasting.nationalassumptions.ui.lazyLoad.NdcCriteria;
import com.stpl.app.galforecasting.nationalassumptions.util.CommonUiUtils;
import com.stpl.app.galforecasting.nationalassumptions.util.CommonUtils;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.DESCRIPTION;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.USER_ID;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.*;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.WindowMessagesName.RESET_CONFIRMATION;
import com.stpl.app.galforecasting.nationalassumptions.util.NotesTextField;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.Constants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.FieldEvents;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.ExtCustomTreeTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
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
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

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

    /**
     * The table control Layout.
     */
    public HorizontalLayout controlLayout;
    /**
     * The TableVerticalLayout.
     */
    @UiField("tableVerticalLayout")
    public VerticalLayout tableVerticalLayout;
    private final HelperDTO brandDto = new HelperDTO(0, SELECT_ONE.getConstant());
    MedicaidWorkSheetTableLogic tableLogic = new MedicaidWorkSheetTableLogic();
    FreezePagedTreeTable periodTableId = new FreezePagedTreeTable(tableLogic);
    CustomTableHeaderDTO leftHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO rightHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    CustomTreeContainer<TableDTO> resultBeanContainer = new CustomTreeContainer<TableDTO>(TableDTO.class);
    MedicaidURAResultsLogic medLogic = new MedicaidURAResultsLogic();
    ProjectionSelectionDTO projectionDTO;
    LazyContainer ndcContainer;
    LazyContainer brandContainer;
    private final HelperDTO dto = new HelperDTO(0, SELECT_ONE.getConstant());
    ExtCustomTreeTable exceltable = new ExtCustomTreeTable();
    CustomTreeContainer<TableDTO> excelResultBeanContainer = new CustomTreeContainer<TableDTO>(TableDTO.class);
    public String mode = (String) VaadinSession.getCurrent().getAttribute(Constant.MODE);
    Property.ValueChangeListener valueChangeListener = null;
    Property.ValueChangeListener valueChangeListenerTA = null;
    private boolean valueChange = false;
    private boolean valueTAChange = false;
    String description = StringUtils.EMPTY;
    final String userId = (String) VaadinSession.getCurrent().getAttribute(USER_ID.getConstant());
    Map<String, String> adjustedValues = new HashMap<String, String>();
    Map<String, String> editedNotes = new HashMap<String, String>();

    Map<String, String> secondAdjustedValues = new HashMap<String, String>();
    Map<String, String> secondEditedNotes = new HashMap<String, String>();

    Map<String, String> thirdAdjustedValues = new HashMap<String, String>();
    Map<String, String> thirdEditedNotes = new HashMap<String, String>();
    MedicaidQueryUtils queryUtil = new MedicaidQueryUtils();

    Map<String, String> bpAdjustedValues = new HashMap<String, String>();
    Map<String, String> bpEditedNotes = new HashMap<String, String>();
    Map<String, String> cpiAdjustedValues = new HashMap<String, String>();
    Map<String, String> cpiEditedNotes = new HashMap<String, String>();
    boolean submitFlag = false;
    boolean submitMsg = false;
    HelperDTO ndcResultdto;
    HelperDTO brandResultdto;

    /**
     * Instantiates a new master fcp work sheet.
     *
     * @param projSelection
     */
    public MedicaidUraWorkSheet(ProjectionSelectionDTO projSelection) {
        super("Medicaid URA Work Sheet");
        LOGGER.info("MedicaidUraWorkSheet Constructor initiated ");
        this.projectionDTO = projSelection;
        this.ndcResultdto = projSelection.getNdcWSdto();
        this.brandResultdto = projSelection.getBrandWSdto();
        init();
        LOGGER.info("MedicaidUraWorkSheet Constructor Ended ");
    }

    /**
     * Inits the.
     */
    public void init() {

        LOGGER.info("Entering init ");
        center();
        setClosable(true);
        setModal(true);
        setWidth(85f, Sizeable.Unit.PERCENTAGE);
        setContent(Clara.create(getClass().getResourceAsStream("/nationalassumption/MedicaidUraWorksheet.xml"), this));
        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        configureFields();
        LOGGER.info(" init Ends");
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
        brandDdlb.setPageLength(7);
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

        ndcDdlb.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                LOGGER.info("ndcDdlb ValueChangeEvent initiated " + ndcDdlb.getValue());

                if (ndcDdlb.getValue() != null && !SELECT_ONE.getConstant().equals(String.valueOf(ndcDdlb.getValue()))) {
                    HelperDTO helperDTO = (HelperDTO) ndcDdlb.getValue();
                    int ndcId = helperDTO.getId();
                    String ndc9 = helperDTO.getDescription();
                    String[] ndcArr = ndc9.split(",");

                    if (ndcArr.length > 0) {
                        if (ndcArr.length > 1) {
                            ndc9 = ndcArr[1].trim();
                        } else {
                            ndc9 = ndcArr[0].trim();
                        }
                    }

                    projectionDTO.setNdcSid(helperDTO);
                    projectionDTO.setNdc9(ndc9);
                    LOGGER.info("ndcDdlb ValueChangeEvent ends theraupeyticId   " + ndcId);

                }
            }
        });

        brandDdlb.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                LOGGER.info("brandDdlb ValueChangeEvent initiated " + brandDdlb.getValue());

                if (brandDdlb.getValue() != null && !SELECT_ONE.getConstant().equals(String.valueOf(brandDdlb.getValue()))) {
                    HelperDTO helperDTO = (HelperDTO) brandDdlb.getValue();
                    projectionDTO.setBrandWSdto(helperDTO);
                    ndcContainer.removeAllItems();
                    loadNdc();
                    ndcDdlb.select(dto);
                    LOGGER.info("brandDdlb ValueChangeEvent ends brandDdlb   " + helperDTO.getId());

                } else {
                    projectionDTO.setBrandWSdto(dto);
                }
            }
        });
        if (Constant.VIEW.equalsIgnoreCase(mode)) {
            disableFieldsOnView();
        }
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(USER_ID.getConstant()));
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
            LOGGER.error(portal.getMessage());
        } catch (SystemException system) {
            LOGGER.error(system.getMessage());
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
        if (!Constant.VIEW.equalsIgnoreCase(mode)) {
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
        tableLogic.setPageLength(100);
        fullHeader = new CustomTableHeaderDTO();
        leftHeader = CommonUiUtils.getMedicaidWorkSheetLeftTableColumns(fullHeader);
        rightHeader = CommonUiUtils.getMedicaidWorkSheetRightTableColumns(projectionDTO, fullHeader);
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
        leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));
        rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
        }

        periodTableId.setDoubleHeaderVisible(true);
        periodTableId.setHeight("353px");
        leftTable.setHeight("353px");
        rightTable.setHeight("353px");
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
                        && tableDto.getGroup().startsWith(Constant.ADJUSTMENT) && !tableDto.getGroup().equalsIgnoreCase("Adjustment CPI (Alt)"))
                        || (String.valueOf(propertyId).equalsIgnoreCase("Base Year") && tableDto.getGroup().equalsIgnoreCase(Constant.AMP)
                        && tableDto.getGroup().equalsIgnoreCase("CPI-U"))) {

                    try {
                        final NotesTextField notesField = new NotesTextField();
                        String adjustPropId = propertyId + "~" + Constant.ADJUSTMENT;
                        String notesPropId = propertyId + "~" + Constant.NOTES;
                        notesField.seTextProperty(container.getContainerProperty(itemId, propertyId + Constant.ADJUSTMENT));
                        notesField.setData(propertyId + "~" + getBeanFromId(itemId).getGroup());
                        notesField.addStyleName(Constant.ALIGN_RIGHT);
                        notesField.setWidth(35, Sizeable.Unit.PERCENTAGE);
                        notesField.setTextData(adjustPropId);
                        Map<String, String[]> notesMap = new HashMap<String, String[]>();

                        String adjustValue = StringUtils.EMPTY;
                        String notesValue = StringUtils.EMPTY;
                        if (tableDto.getGroup().startsWith(Constant.ADJUSTMENT_AMP)) {
                            notesMap = projectionDTO.getNotesMap();
                            adjustValue = adjustedValues.get(adjustPropId);
                            notesValue = editedNotes.get(notesPropId);
                        }
                        if (tableDto.getGroup().startsWith(Constant.ADJUSTMENT_BEST_PRICE)) {
                            notesMap = projectionDTO.getSecondRowNotesMap();
                            adjustValue = secondAdjustedValues.get(adjustPropId);
                            notesValue = secondEditedNotes.get(notesPropId);
                        }
                        if (tableDto.getGroup().startsWith("Adjustment CPI")) {
                            notesMap = projectionDTO.getThirdRowNotesMap();
                            adjustValue = thirdAdjustedValues.get(adjustPropId);
                            notesValue = thirdEditedNotes.get(notesPropId);
                        }

                        String[] noteArr = notesMap.get(String.valueOf(propertyId));
                        if (StringUtils.isNotBlank(adjustValue)) {
                            notesField.setTextfieldValue(adjustValue);
                        } else {
                            if (noteArr != null && noteArr.length != 0) {
                                notesField.setTextfieldValue(noteArr[0]);
                            }
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
                                        if (tableDto.getGroup().startsWith(Constant.ADJUSTMENT_AMP)) {
                                            adjustedValues.put(String.valueOf(((TextField) event.getComponent()).getData()), String.valueOf(((TextField) event.getComponent()).getValue()));
                                        }

                                        if (tableDto.getGroup().startsWith(Constant.ADJUSTMENT_BEST_PRICE)) {
                                            secondAdjustedValues.put(String.valueOf(((TextField) event.getComponent()).getData()), String.valueOf(((TextField) event.getComponent()).getValue()));
                                        }
                                        if (tableDto.getGroup().startsWith("Adjustment CPI")) {
                                            thirdAdjustedValues.put(String.valueOf(((TextField) event.getComponent()).getData()), String.valueOf(((TextField) event.getComponent()).getValue()));
                                        }
                                        valueChange = false;
                                    } catch (Exception ex) {
                                        LOGGER.error(ex.getMessage());
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
                        } else {
                            if (noteArr != null && noteArr.length != 0) {
                                notesField.setNotesValue(noteArr[1]);
                                notesField.addToolTip(noteArr[1]);
                            }
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
                                        String formattedValue = String.valueOf(((TextArea) event.getComponent()).getValue()) + dateTimeFormat.format(new Date()) + " ,<" + CommonUtils.getUserNameById(userId) + ">";
                                        description = formattedValue;
                                        if (tableDto.getGroup().startsWith(Constant.ADJUSTMENT_AMP)) {
                                            editedNotes.put(String.valueOf(((TextArea) event.getComponent()).getData()), formattedValue);
                                        }
                                        if (tableDto.getGroup().startsWith(Constant.ADJUSTMENT_BEST_PRICE)) {
                                            secondEditedNotes.put(String.valueOf(((TextArea) event.getComponent()).getData()), formattedValue);
                                        }
                                        if (tableDto.getGroup().startsWith("Adjustment CPI")) {
                                            thirdEditedNotes.put(String.valueOf(((TextArea) event.getComponent()).getData()), formattedValue);
                                        }
                                        valueTAChange = false;

                                    } catch (Exception ex) {
                                        LOGGER.error(ex.getMessage());
                                    }
                                }
                                notesField.addToolTip(description);
                                detachLisener((AbstractField) event.getComponent());
                            }

                        });
                        if (Constant.VIEW.equalsIgnoreCase(mode)) {
                            notesField.setEnable(false);
                        }

                        return notesField;

                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
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
                        || tableDto.getGroup().equalsIgnoreCase("CPI-U"))) {

                    try {
                        final NotesTextField notesField = new NotesTextField();
                        String adjustPropId = propertyId + "~" + Constant.ADJUSTMENT;
                        String notesPropId = propertyId + "~" + Constant.NOTES;
                        notesField.seTextProperty(container.getContainerProperty(itemId, propertyId + Constant.ADJUSTMENT));
                        notesField.setData(propertyId + "~" + getBeanFromId(itemId).getGroup());
                        notesField.addStyleName(Constant.ALIGN_RIGHT);
                        notesField.setWidth(35, Sizeable.Unit.PERCENTAGE);
                        notesField.setTextData(adjustPropId);
                        String[] noteArr = new String[2];
                        String adjustValue = StringUtils.EMPTY;
                        String notesValue = StringUtils.EMPTY;
                        if (tableDto.getGroup().startsWith(Constant.AMP)) {
                            noteArr[0] = tableDto.getBaseYearAmp();
                            noteArr[1] = tableDto.getBaseYearAmpNotes();
                            adjustValue = bpAdjustedValues.get(adjustPropId);
                            notesValue = bpEditedNotes.get(notesPropId);
                        }
                        if (tableDto.getGroup().startsWith("CPI-U")) {
                            noteArr[0] = tableDto.getBaseYearCpi();
                            noteArr[1] = tableDto.getBaseYearCpiNotes();
                            adjustValue = cpiAdjustedValues.get(adjustPropId);
                            notesValue = cpiEditedNotes.get(notesPropId);
                        }
                        if (StringUtils.isNotBlank(adjustValue)) {
                            notesField.setTextfieldValue(adjustValue);
                        } else {
                            if (noteArr != null && noteArr.length != 0) {
                                notesField.setTextfieldValue(noteArr[0]);
                            }
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

                                        if (tableDto.getGroup().startsWith("CPI-U")) {
                                            cpiAdjustedValues.put(String.valueOf(((TextField) event.getComponent()).getData()), String.valueOf(((TextField) event.getComponent()).getValue()));
                                        }
                                        valueChange = false;
                                    } catch (Exception ex) {
                                        LOGGER.error(ex.getMessage());
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
                        } else {
                            if (noteArr != null && noteArr.length != 0) {
                                notesField.setNotesValue(noteArr[1]);
                                notesField.addToolTip(noteArr[1]);
                            }
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
                                        String formattedValue = String.valueOf(((TextArea) event.getComponent()).getValue()) + dateTimeFormat.format(new Date()) + " ,<" + CommonUtils.getUserNameById(userId) + ">";
                                        description = formattedValue;
                                        if (tableDto.getGroup().startsWith(Constant.AMP)) {
                                            bpEditedNotes.put(String.valueOf(((TextArea) event.getComponent()).getData()), formattedValue);
                                        }
                                        if (tableDto.getGroup().startsWith("CPI-U")) {
                                            cpiEditedNotes.put(String.valueOf(((TextArea) event.getComponent()).getData()), formattedValue);
                                        }

                                        valueTAChange = false;
                                    } catch (Exception ex) {
                                        LOGGER.error(ex.getMessage());
                                    }
                                }
                                notesField.addToolTip(description);
                                detachLisener((AbstractField) event.getComponent());
                            }

                        });
                        notesField.addToolTip(description);
                        if (Constant.VIEW.equalsIgnoreCase(mode)) {
                            notesField.setEnable(false);
                        }
                        return notesField;

                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
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

    void loadNdc() {
        ndcDdlb.setPageLength(7);
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
        LOGGER.info("excelBtn click listener started");
        configureExcelResultTable();
        loadExcelResultTable();
        ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(exceltable), "Medicaid URA Worksheet", "Medicaid URA Worksheet", "Medicaid URA Worksheet.xls", false);
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
        projectionDTO.setExcel(true);
        List<TableDTO> resultList = medLogic.getMedicaidWorksheet(projectionDTO);
        loadDataToContainer(resultList, null);
        projectionDTO.setExcel(false);
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
        projectionDTO.setGroup(id.getGroup());
        List<TableDTO> resultList = medLogic.getMedicaidWorksheetChild(projectionDTO, id.getItemMasterSid());
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

    /**
     * Enter.
     *
     * @param event the event
     */
    public void enter(ViewChangeEvent event) {
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
                    MedicaidQueryUtils queryUtil = new MedicaidQueryUtils();
                    try {
                        queryUtil.updateAdjustment(projectionDTO.getNdc9(), "updateMedicaidAdjustment");
                        submitFlag = true;
                        submitMsg = false;
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
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
                if (!adjustedValues.isEmpty()) {
                    queryUtil.saveNotes(adjustedValues, projectionDTO.getProjectionId(), projectionDTO.getNdc9(), Constant.AMP);
                    adjustFlag = true;
                    adjustedValues.clear();
                }
                if (!editedNotes.isEmpty()) {
                    queryUtil.saveNotes(editedNotes, projectionDTO.getProjectionId(), projectionDTO.getNdc9(), Constant.AMP);
                    notesFlag = true;
                    editedNotes.clear();
                }
                if (!secondAdjustedValues.isEmpty()) {
                    queryUtil.saveNotes(secondAdjustedValues, projectionDTO.getProjectionId(), projectionDTO.getNdc9(), Constant.BEST_PRICE);
                    adjustFlag = true;
                    secondAdjustedValues.clear();
                }
                if (!secondEditedNotes.isEmpty()) {
                    queryUtil.saveNotes(secondEditedNotes, projectionDTO.getProjectionId(), projectionDTO.getNdc9(), Constant.BEST_PRICE);
                    notesFlag = true;
                    secondEditedNotes.clear();
                }
                if (!thirdAdjustedValues.isEmpty()) {
                    queryUtil.saveNotes(thirdAdjustedValues, projectionDTO.getProjectionId(), projectionDTO.getNdc9(), "CPI-U");
                    adjustFlag = true;
                    thirdAdjustedValues.clear();
                }
                if (!thirdEditedNotes.isEmpty()) {
                    queryUtil.saveNotes(thirdEditedNotes, projectionDTO.getProjectionId(), projectionDTO.getNdc9(), "CPI-U");
                    notesFlag = true;
                    thirdEditedNotes.clear();
                }
                if (!cpiAdjustedValues.isEmpty()) {
                    queryUtil.saveBaseYearNotes(cpiAdjustedValues, projectionDTO.getProjectionId(), projectionDTO.getNdc9(), "CPI-U");
                    adjustFlag = true;
                    cpiAdjustedValues.clear();
                }
                if (!cpiEditedNotes.isEmpty()) {
                    queryUtil.saveBaseYearNotes(cpiEditedNotes, projectionDTO.getProjectionId(), projectionDTO.getNdc9(), "CPI-U");
                    notesFlag = true;
                    cpiEditedNotes.clear();
                }
                if (!bpAdjustedValues.isEmpty()) {
                    queryUtil.saveBaseYearNotes(bpAdjustedValues, projectionDTO.getProjectionId(), projectionDTO.getNdc9(), Constant.AMP);
                    adjustFlag = true;
                    bpAdjustedValues.clear();
                }
                if (!bpEditedNotes.isEmpty()) {
                    queryUtil.saveBaseYearNotes(bpEditedNotes, projectionDTO.getProjectionId(), projectionDTO.getNdc9(), Constant.AMP);
                    notesFlag = true;
                    bpEditedNotes.clear();
                }

                if (adjustFlag || notesFlag) {
                    if (adjustFlag) {
                        callAdjustmentProcedure();
                        projectionDTO.setAdjust(true);
                        submitMsg = true;
                    }
                    loadResultTable(0, StringUtils.EMPTY);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
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
            if (StringUtils.isNotBlank(formatedValue)) {
                if (!formatedValue.matches("^[\\+\\-]?\\d{0,5}\\.?\\d{0,4}$")) {
                    formatFlag = true;
                    break;
                }
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
            String priceType = "AMP,BEST PRICE,CPI-U";
            medLogic.workSheetSetupCook(0, priceType, "MEDICAID URA", projectionDTO.getNdc9());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public boolean isSubmit() {
        return submitFlag;
    }

    public void closeLogic() {
        try {
            queryUtil.updateAdjustment(projectionDTO.getNdc9(), "removeMedicaidAdjustment");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
