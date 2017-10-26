/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.TableDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.FcpResultsLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.MedicaidURAResultsLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.tablelogic.MasterFcpWorkSheetTableLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.queryutils.FcpQueryUtils;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad.BrandContainer;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad.BrandCriteria;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad.NdcContainer;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad.NdcCriteria;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUiUtils;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.*;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.FrequencyConstants.*;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.MASTER_FCP_WORKSHEET_LOOKUP;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.NATIONAL_ASSUMPTIONS;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.NOTES_DATE;
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
import com.stpl.portal.model.User;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.FieldEvents;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * The Class MasterFcpWorkSheet.
 *
 * @author Vinodhini
 */
public class MasterFcpWorkSheet extends Window {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -5631894964184716246L;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(MasterFcpWorkSheet.class);
    /**
     * The reset.
     */
    @UiField("reset")
    private Button reset;
    /**
     * The History.
     */
    @UiField("generate")
    private Button generate;

    /**
     * The brand ddlb.
     */
    @UiField("brand")
    private ComboBox brandDdlb;

    /**
     * The ndc ddlb.
     */
    @UiField("ndc")
    private ComboBox ndcDdlb;

    /**
     * The calculate.
     */
    @UiField("calculate")
    private Button calculate;

    /**
     * The reset btn.
     */
    @UiField("tableReset")
    private Button tableReset;

    /**
     * The submit.
     */
    @UiField("submit")
    private Button submit;

    /**
     * The close.
     */
    @UiField("close")
    private Button close;

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
    MasterFcpWorkSheetTableLogic tableLogic = new MasterFcpWorkSheetTableLogic();
    FreezePagedTreeTable periodTableId = new FreezePagedTreeTable(tableLogic);
    CustomTableHeaderDTO leftHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO rightHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    ExtTreeContainer<TableDTO> resultBeanContainer = new ExtTreeContainer<TableDTO>(TableDTO.class,ExtContainer.DataStructureMode.MAP);
    FcpResultsLogic fcpLogic = new FcpResultsLogic();
    ProjectionSelectionDTO projectionDTO;
    LazyContainer ndcContainer;
    LazyContainer brandContainer;
    private final HelperDTO dto = new HelperDTO(0, SELECT_ONE.getConstant());
    ExtCustomTreeTable exceltable = new ExtCustomTreeTable();
    ExtTreeContainer<TableDTO> excelResultBeanContainer = new ExtTreeContainer<TableDTO>(TableDTO.class,ExtContainer.DataStructureMode.MAP);
    public String mode = (String) VaadinSession.getCurrent().getAttribute(Constant.MODE);
    Property.ValueChangeListener valueChangeListener = null;
    Property.ValueChangeListener valueChangeListenerTA = null;
    private boolean valueChange = false;
    private boolean valueTAChange = false;
    public User user;
    String description = StringUtils.EMPTY;
    Map<String, String> fssAdjustedValues = new HashMap<String, String>();
    Map<String, String> fssEditedNotes = new HashMap<String, String>();
    Map<String, String> ovrAdjustedValues = new HashMap<String, String>();
    Map<String, String> ovrEditedNotes = new HashMap<String, String>();
    FcpQueryUtils queryUtil = new FcpQueryUtils();
    boolean submitFlag = false;
    boolean submitMsg = false;
    HelperDTO ndcResultdto;
    HelperDTO brandResultdto;
    SessionDTO sessionDTO;
    /**
     * Instantiates a new master fcp work sheet.
     *
     * @param projSelection
     */
    public MasterFcpWorkSheet(ProjectionSelectionDTO projSelection,SessionDTO sessionDTO) {
        super("Master FCP Worksheet");
        LOGGER.debug("MasterFcpWorkSheet Constructor initiated ");
        this.projectionDTO = projSelection;
        this.ndcResultdto = projSelection.getNdcWSdto();
        this.brandResultdto = projSelection.getBrandWSdto();
        this.sessionDTO=sessionDTO;
        init();
        LOGGER.debug("MasterFcpWorkSheet Constructor Ended ");
    }

    /**
     * Inits the.
     */
    public void init() {

        LOGGER.debug("Entering init ");
        center();
        setClosable(true);
        setModal(true);
        setContent(Clara.create(getClass().getResourceAsStream("/nationalassumption/MasterFcpWorksheet.xml"), this));
        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setWidth(NumericConstants.FLOAT_SIXTY_EIGHT, Sizeable.Unit.PERCENTAGE);
        setHeight(NumericConstants.FLOAT_SEVENTY, Sizeable.Unit.PERCENTAGE);
        configureFields();

        LOGGER.debug(" init Ends");
    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        LOGGER.debug("configureFields Method Started ");
        brandDdlb.focus();
        excelBtn.setIcon(excelExportImage);
        try {
            if (ANNUALLY.getConstant().equals(projectionDTO.getFrequency())) {
                projectionDTO.setFrequency(QUARTERLY.getConstant());
                projectionDTO.setHistoryNum(projectionDTO.getHistoryNum() * NumericConstants.FOUR);
            }
            initializeResultTable();
            configureResultTable();
            addResultTable();
            loadResultTable(0, StringUtils.EMPTY, true);

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

                    LOGGER.debug("ndcDdlb ValueChangeEvent initiated " + ndcDdlb.getValue());

                    if (ndcDdlb.getValue() != null && !SELECT_ONE.getConstant().equals(String.valueOf(ndcDdlb.getValue()))) {
                        HelperDTO helperDTO = (HelperDTO) ndcDdlb.getValue();
                        int ndcId = helperDTO.getId();
                        projectionDTO.setNdcSid(helperDTO);
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
            if (Constant.VIEW.equalsIgnoreCase(mode)) {
                disableFieldsOnView();
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = sessionDTO.getUserId();
        try {
            final Map<String, AppPermission> tabItemHM = stplSecurity.getBusinessFunctionPermission(userId, NATIONAL_ASSUMPTIONS.getConstant() + "," + MASTER_FCP_WORKSHEET_LOOKUP.getConstant());
            if (tabItemHM.get("generate") != null && tabItemHM.get("generate").isFunctionFlag()) {
                generate.setVisible(true);
            } else {
                generate.setVisible(false);
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
                calculate.setVisible(true);
            } else {
                calculate.setVisible(false);
            }
            if (tabItemHM.get("tableReset") != null && tabItemHM.get("tableReset").isFunctionFlag()) {
                tableReset.setVisible(true);
            } else {
                tableReset.setVisible(false);
            }
            if (tabItemHM.get("submit") != null && tabItemHM.get("submit").isFunctionFlag()) {
                submit.setVisible(true);
            } else {
                submit.setVisible(false);
            }
            if (tabItemHM.get("close") != null && tabItemHM.get("close").isFunctionFlag()) {
                close.setVisible(true);
            } else {
                close.setVisible(false);
            }
        } catch (PortalException portal) {
            LOGGER.error(portal);
        } catch (SystemException system) {
            LOGGER.error(system);
        }
        LOGGER.debug("configureFields Method ended ");
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
                closeLogic();
                loadResultTable(0, StringUtils.EMPTY, true);
            }
        }.getConfirmationMessage(RESET_CONFIRMATION.getConstant(), "Are you sure you want to reset the page to default/previous values?");
    }

    public boolean isSubmit() {
        return submitFlag;
    }

    /**
     * Submit button click listener.
     *
     * @param event the event
     */
    @UiHandler("submit")
    public void submit(Button.ClickEvent event) {
        if (submitMsg) {
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
                    try {
                        queryUtil.updateAdjustment(projectionDTO.getNdcSid().getId(), "getFcpAdjSumbitUpdate",sessionDTO);
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

    /**
     * Calculate button click listener.
     *
     * @param event the event
     */
    @UiHandler("calculate")
    public void calculate(Button.ClickEvent event) {
        try {
            boolean formatFlag = false;
            String qValue = StringUtils.EMPTY;
            String pricetype = StringUtils.EMPTY;
            formatFlag = checkNotes(fssAdjustedValues);

            if (!formatFlag) {
                formatFlag = checkNotes(ovrAdjustedValues);
            }
            for (String values : fssAdjustedValues.keySet()) {
                String tempValue[] = values.split("~");
                String propertyId = tempValue[0];
                qValue = propertyId.substring(1, NumericConstants.TWO);
            }
            if (formatFlag) {
                AbstractNotificationUtils.getErrorNotification("Calculate Error", "Override values must be entered in dollar format with 2 decimal places.");
            } else {
                boolean notesFlag = false;
                boolean adjustFlag = false;
                if (!fssAdjustedValues.isEmpty()) {
                    queryUtil.saveNotes(fssAdjustedValues, projectionDTO.getProjectionId(), projectionDTO.getNdcSid().getId(), "QFSS",sessionDTO);
                    pricetype = "QFSS";
                    adjustFlag = true;
                    fssAdjustedValues.clear();
                }
                if (!fssEditedNotes.isEmpty()) {
                    queryUtil.saveNotes(fssEditedNotes, projectionDTO.getProjectionId(), projectionDTO.getNdcSid().getId(), "QFSS",sessionDTO);
                    pricetype = "QFSS";
                    notesFlag = true;
                    fssEditedNotes.clear();
                }
                if (!ovrAdjustedValues.isEmpty()) {
                    queryUtil.saveNotes(ovrAdjustedValues, projectionDTO.getProjectionId(), projectionDTO.getNdcSid().getId(), "QNON-FAMP",sessionDTO);
                    pricetype = "QNON-FAMP";
                    adjustFlag = true;
                    ovrAdjustedValues.clear();
                }
                if (!ovrEditedNotes.isEmpty()) {
                    queryUtil.saveNotes(ovrEditedNotes, projectionDTO.getProjectionId(), projectionDTO.getNdcSid().getId(), "QNON-FAMP",sessionDTO);
                    pricetype = "QNON-FAMP";
                    notesFlag = true;
                    ovrEditedNotes.clear();
                }
                if (adjustFlag || notesFlag) {                    
                    if (adjustFlag) {
                        if((pricetype.equals("QFSS") && qValue.equals("4")) || pricetype.equals("QNON-FAMP")) { // Added restriction for CEL-370 CR
                            callAdjustmentProcedure();
                        }
                        projectionDTO.setAdjust(true);
                        submitMsg = true;
                    }
                        loadResultTable(0, StringUtils.EMPTY, true);
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
                     */
                    public void yesMethod() {
                        close();
                    }
                }.getConfirmationMessage("Close Confirmation", "Are you sure you want to close the Worksheet? Unsaved changes will be lost. ");
            }
        } else {
            close();
        }
    }

    @UiHandler("generate")
    public void generate(Button.ClickEvent event) {
        if (checkSelection()) {
            tableVerticalLayout.removeAllComponents();
            tableLogic = new MasterFcpWorkSheetTableLogic();
            periodTableId = new FreezePagedTreeTable(tableLogic);
            initializeResultTable();
            configureResultTable();
            addResultTable();
            loadResultTable(0, StringUtils.EMPTY, true);

        } else {
            AbstractNotificationUtils.getErrorNotification("Generate Error ", "You must select an NDC from the drop down list box.");
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
        leftHeader = CommonUiUtils.getWorkSheetLeftTableColumns(fullHeader);
        rightHeader = CommonUiUtils.getWorkSheetRightTableColumns(projectionDTO, fullHeader);
        resultBeanContainer = new ExtTreeContainer<TableDTO>(TableDTO.class,ExtContainer.DataStructureMode.MAP);
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

                if (tableDto.getParent() == 0 && String.valueOf(propertyId).endsWith(Constant.PROJECTIONS) && tableDto.getGroup().startsWith(Constant.ADJUSTMENT)) {
                    try {
                        final NotesTextField notesField = new NotesTextField();
                        String adjustPropId = propertyId + "~" + Constant.ADJUSTMENT;
                        String notesPropId = propertyId + "~" + Constant.NOTES;
                        notesField.seTextProperty(container.getContainerProperty(itemId, propertyId + Constant.ADJUSTMENT));
                        notesField.setData(propertyId + "~" + getBeanFromId(itemId).getGroup());
                        notesField.addStyleName(Constant.ALIGN_RIGHT);
                        notesField.setWidth(NumericConstants.THIRTY_FIVE, Sizeable.Unit.PERCENTAGE);
                        notesField.setTextData(adjustPropId);
                        Map<String, String[]> notesMap = new HashMap<String, String[]>();

                        String adjustValue = StringUtils.EMPTY;
                        String notesValue = StringUtils.EMPTY;
                        if (tableDto.getGroup().startsWith("Adjustment FSS")) {
                            notesMap = projectionDTO.getNotesMap();
                            adjustValue = fssAdjustedValues.get(adjustPropId);
                            notesValue = fssEditedNotes.get(notesPropId);
                        }
                        if (tableDto.getGroup().startsWith("Adjustment Non-FAMP")) {
                            notesMap = projectionDTO.getSecondRowNotesMap();
                            adjustValue = ovrAdjustedValues.get(adjustPropId);
                            notesValue = ovrEditedNotes.get(notesPropId);
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
                                        if (tableDto.getGroup().startsWith("Adjustment FSS")) {
                                            fssAdjustedValues.put(String.valueOf(((TextField) event.getComponent()).getData()), String.valueOf(((TextField) event.getComponent()).getValue()));
                                        }

                                        if (tableDto.getGroup().startsWith("Adjustment Non-FAMP")) {
                                            ovrAdjustedValues.put(String.valueOf(((TextField) event.getComponent()).getData()), String.valueOf(((TextField) event.getComponent()).getValue()));
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

                                        DateFormat dateTimeFormat = new SimpleDateFormat(NOTES_DATE.getConstant());
                                        String formattedValue = String.valueOf(((TextArea) event.getComponent()).getValue()) + dateTimeFormat.format(new Date()) + " ,<" + CommonUtils.getUserNameById(sessionDTO.getUserId()) + ">";
                                        description = formattedValue;
                                        if (tableDto.getGroup().startsWith("Adjustment FSS")) {
                                            fssEditedNotes.put(String.valueOf(((TextArea) event.getComponent()).getData()), formattedValue);
                                        }
                                        if (tableDto.getGroup().startsWith("Adjustment Non-FAMP")) {
                                            ovrEditedNotes.put(String.valueOf(((TextArea) event.getComponent()).getData()), formattedValue);
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

                        if (Constant.VIEW.equalsIgnoreCase(mode)) {
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

    private void loadResultTable(int levelNo, String hierarchyNo, boolean flag) {
        try {
            tableLogic.clearAll();
            tableLogic.setRefresh(false);
            projectionDTO.clearNonFetchableIndex();
            tableLogic.setProjectionResultsData(projectionDTO, levelNo, hierarchyNo, flag,sessionDTO);
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
            targetItem = new BeanItem<TableDTO>(
                    (TableDTO) id);
        }
        return (TableDTO) targetItem.getBean();
    }

    public void loadNdc() {
        ndcDdlb.setPageLength(NumericConstants.SEVEN);
        ndcDdlb.setImmediate(true);
        ndcDdlb.setNullSelectionAllowed(true);
        ndcDdlb.setInputPrompt(SELECT_ONE.getConstant());
        ndcDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        ndcDdlb.setItemCaptionPropertyId(DESCRIPTION.getConstant());
        ndcDdlb.markAsDirty();
        ndcContainer = new LazyContainer(HelperDTO.class, new NdcContainer(projectionDTO.getBrandWSdto(), true, ndcResultdto), new NdcCriteria());
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
        ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(exceltable), "Master FCP Worksheet", "Master FCP Worksheet", "Master_FCP_Worksheet.xls", false);
        exp.export();
        tableVerticalLayout.removeComponent(exceltable);
        LOGGER.debug("excelBtn click listener ends");
    }

    private void configureExcelResultTable() {
        excelResultBeanContainer = new ExtTreeContainer<TableDTO>(TableDTO.class,ExtContainer.DataStructureMode.MAP);
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
        List<TableDTO> resultList = fcpLogic.getFcpWorksheet(projectionDTO,sessionDTO);
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
        List<TableDTO> resultList = fcpLogic.getFcpWorksheetChild(projectionDTO, id.getItemMasterSid(),sessionDTO);
        loadDataToContainer(resultList, id);
    }

    public void disableFieldsOnView() {

        brandDdlb.setEnabled(false);
        ndcDdlb.setEnabled(false);
        reset.setEnabled(false);
        generate.setEnabled(false);
        calculate.setEnabled(false);
        tableReset.setEnabled(false);
        submit.setEnabled(false);
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

    public boolean checkNotes(Map<String, String> notesValues) {

        boolean formatFlag = false;

        for (String values : notesValues.keySet()) {
            String formatedValue = notesValues.get(values);
            formatedValue = formatedValue.replace("$", StringUtils.EMPTY);
            if (StringUtils.isNotBlank(formatedValue)) {
                if ("-".equals(formatedValue) || "+".equals(formatedValue)) {
                    formatFlag = true;
                    break;
                }
                if (!formatedValue.matches("^[\\+\\-]?\\d{0,5}\\.?\\d{0,2}$")) {
                    formatFlag = true;
                    break;
                }
            }
        }
        return formatFlag;
    }

    public void clearMap() {
        fssAdjustedValues.clear();
        fssEditedNotes.clear();
        ovrAdjustedValues.clear();
        ovrEditedNotes.clear();

    }

    private void callAdjustmentProcedure() {
        try {

            String priceType = "QFSS,QNON-FAMP";
            MedicaidURAResultsLogic medLogic = new MedicaidURAResultsLogic();
            medLogic.workSheetSetupCook(projectionDTO.getNdcSid().getId(), priceType, "FCP WORKSHEET", StringUtils.EMPTY,sessionDTO);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public void closeLogic() {
        try {
            queryUtil.updateAdjustment(projectionDTO.getNdcSid().getId(), "getFcpAdjCloseUpdate",sessionDTO);
        } catch (Exception ex) {
           LOGGER.error(ex);
        }
    }
}
