/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.ui.form;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.TableDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.MedicaidURAResultsLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.PhsResultsLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.tablelogic.PhsWorksheetTableLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.queryutils.FcpQueryUtils;
import com.stpl.app.gtnforecasting.nationalassumptions.queryutils.PhsQueryUtils;
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
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.DefaultFieldFactory;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.TextArea;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
/**
 * The Class MasterPHSWorksheet.
 *
 * @author Vinodhini
 */
public class MasterPhsWorksheet extends Window {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -5631894964184716246L;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MasterPhsWorksheet.class);
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
     * The max split position.
     */
    private static final float MAXSPLIT_POSITION_PHS = 1000;

    /**
     * The min split position.
     */
    private static final float MINSPLIT_POSITION_PHS = 200;

    /**
     * The split position.
     */
    private static final float SPLIT_POSITION_PHS = 300;
    private final String mode = (String) VaadinSession.getCurrent().getAttribute(Constant.MODE);
    /**
     * The TableVerticalLayout.
     */
    @UiField("tableVerticalLayout")
    private VerticalLayout tableVerticalLayout;

    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    private final HelperDTO brandDto = new HelperDTO(0, SELECT_ONE.getConstant());
    private PhsWorksheetTableLogic tableLogic = new PhsWorksheetTableLogic();
    private FreezePagedTreeTable periodTableId = new FreezePagedTreeTable(tableLogic);
    private CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    public static final String MASTER_PHS_WORKSHEET = "Master PHS Worksheet";
    private final ProjectionSelectionDTO projectionDTO;
    private LazyContainer ndcContainer;
    private final HelperDTO dto = new HelperDTO(0, SELECT_ONE.getConstant());
    private final PhsResultsLogic phsResLogic = new PhsResultsLogic();
    private ExtCustomTreeTable exceltable = new ExtCustomTreeTable();
    private ExtTreeContainer<TableDTO> excelResultBeanContainer = new ExtTreeContainer<>(TableDTO.class,ExtContainer.DataStructureMode.MAP);
    private Property.ValueChangeListener valueChangeListener = null;
    private Property.ValueChangeListener valueChangeListenerTA = null;
    private boolean valueChange = false;
    private boolean valueTAChange = false;
    private String description = StringUtils.EMPTY;
    private final Map<String, String> adjustedValues = new HashMap<>();
    private final Map<String, String> editedNotes = new HashMap<>();
    private final PhsQueryUtils queryUtil = new PhsQueryUtils();
    private final FcpQueryUtils fcpQueryUtil = new FcpQueryUtils();    
    private boolean submitFlag = false;
    private boolean submitMsg = false;
    private final HelperDTO ndcResultdto;
    private final HelperDTO brandResultdto;
    private final SessionDTO sessionDTO;

    /**
     * Instantiates a new master PHS work sheet.
     *
     * @param projSelection
     */
    public MasterPhsWorksheet(ProjectionSelectionDTO projSelection,SessionDTO sessionDTO) {
        super(MASTER_PHS_WORKSHEET);
        LOGGER.debug("MasterPhsWorksheet Constructor initiated ");
        this.projectionDTO = projSelection;
        this.ndcResultdto = projSelection.getNdcWSdto();
        this.brandResultdto = projSelection.getBrandWSdto();
        this.sessionDTO=sessionDTO;
        init();
        LOGGER.debug("MasterPhsWorksheet Constructor Ended ");
    }

    /**
     * Inits the.
     */
    public final void init() {

        LOGGER.debug("Entering init ");
        center();
        setClosable(true);
        setModal(true);
        setWidth(NumericConstants.FLOAT_EIGHTY_FIVE, Unit.PERCENTAGE);
        setContent(Clara.create(getClass().getResourceAsStream("/nationalassumption/MasterPhsWorksheet.xml"), this));
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
        brandDdlb.focus();
        try {
            initializeResultTable();
            configureResultTable();
            addResultTable();
            loadResultTable();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
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
        LazyContainer brandContainer = new LazyContainer(HelperDTO.class, new BrandContainer(projectionDTO.getTherapeuticSid(), projectionDTO.getBrand()), new BrandCriteria());
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

                LOGGER.debug("ndcDdlb ValueChangeEvent initiated= {} " , ndcDdlb.getValue());

                if (ndcDdlb.getValue() != null && !SELECT_ONE.getConstant().equals(String.valueOf(ndcDdlb.getValue()))) {
                    HelperDTO helperDTO = (HelperDTO) ndcDdlb.getValue();
                    int ndcId = helperDTO.getId();
                    projectionDTO.setNdcSid(helperDTO);
                    LOGGER.debug("ndcDdlb ValueChangeEvent ends theraupeyticId= {}  " , ndcId);

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
                    projectionDTO.setBrandWSdto(helperDTO);
                    ndcContainer.removeAllItems();
                    loadNdc();
                    ndcDdlb.select(dto);
                    LOGGER.debug("brandDdlb ValueChangeEvent ends brandDdlb= {}  " , brandSid);

                } else {
                    projectionDTO.setBrandWSdto(dto);
                }
            }
        });
        if (Constant.VIEW.equalsIgnoreCase(mode)) {
            disableFieldsOnView();
        }
         final StplSecurity stplSecurity = new StplSecurity();
        final String userId = sessionDTO.getUserId();
        try {
            final Map<String, AppPermission> tabItemHM = stplSecurity.getBusinessFunctionPermission(userId, NATIONAL_ASSUMPTIONS.getConstant()+","+ MASTER_PHS_WORKSHEET_LOOKUP.getConstant());
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
        } catch (PortalException | SystemException portal) {
            LOGGER.error(StringUtils.EMPTY,portal);
        }
    }

    /**
     * Initialize Result Table.
     */
    private void initializeResultTable() {
        periodTableId.markAsDirty();
        periodTableId.setSelectable(false);
        periodTableId.setSplitPosition(SPLIT_POSITION_PHS, Sizeable.Unit.PIXELS);
        periodTableId.setMinSplitPosition(MINSPLIT_POSITION_PHS, Sizeable.Unit.PIXELS);
        periodTableId.setMaxSplitPosition(MAXSPLIT_POSITION_PHS, Sizeable.Unit.PIXELS);
        periodTableId.addStyleName("valo-theme-extfiltertable");
        periodTableId.addStyleName("table-header-center");
    }

    private void configureResultTable()  {
           
        tableLogic.setPageLength(NumericConstants.HUNDRED);
        fullHeader = new CustomTableHeaderDTO();
        CustomTableHeaderDTO leftHeader = CommonUiUtils.getPhsWorkSheetLeftTableColumns(fullHeader);
        CustomTableHeaderDTO rightHeader = CommonUiUtils.getPhsWorkSheetRightTableColumns(projectionDTO, fullHeader);
        ExtTreeContainer<TableDTO> resultBeanContainer = new ExtTreeContainer<>(TableDTO.class,ExtContainer.DataStructureMode.MAP);
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
        rightTable.setTableFieldFactory(new DefaultFieldFactory() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            /**
             * To create editable fields inside table .
             */
            @Override
            public Field<?> createField(final Container container,
                    final Object itemId, final Object propertyId,
                    final Component uiContext) {
                TableDTO tableDto = getBeanFromId(itemId);

                if (tableDto.getParent() == 0 && String.valueOf(propertyId).endsWith(Constant.PROJECTIONS) && (tableDto.getGroup().startsWith(Constant.ADJUSTMENT) || tableDto.getGroup().startsWith(Constant.OVERRIDE))) {

                    try {
                        final NotesTextField notesField = new NotesTextField();
                        String adjustPropId = propertyId + "~" + Constant.ADJUSTMENT;
                        notesField.seTextProperty(container.getContainerProperty(itemId, propertyId + Constant.ADJUSTMENT));
                        notesField.setData(propertyId + "~" + getBeanFromId(itemId).getGroup());
                        notesField.addStyleName(Constant.ALIGN_RIGHT);
                        notesField.setWidth(NumericConstants.THIRTY_FIVE, Sizeable.Unit.PERCENTAGE);

                        notesField.setTextData(adjustPropId);

                        Map<String, String[]> notesMap = projectionDTO.getNotesMap();
                        String[] noteArr = notesMap.get(String.valueOf(propertyId));
                        String adjustValue = adjustedValues.get(adjustPropId);

                        if (StringUtils.isNotBlank(adjustValue)) {
                            notesField.setTextfieldValue(adjustValue);
                        } else {
                            if (noteArr != null && noteArr.length != 0) {
                                notesField.setTextfieldValue(noteArr[0]);
                            }
                        }
                        notesField.addTextFieldFocusListener(new FocusListener() {

                            @Override
                            public void focus(FocusEvent event) {

                                attachValueChangeListener((AbstractField) event.getComponent());
                            }

                        });
                        notesField.addTextFieldBlurListener(new BlurListener() {

                            @Override
                            public void blur(BlurEvent event) {

                                if (valueChange) {
                                    try {
                                        adjustedValues.put(String.valueOf(((TextField) event.getComponent()).getData()), String.valueOf(((TextField) event.getComponent()).getValue()));
                                        valueChange = false;
                                    } catch (Exception ex) {
                                        LOGGER.error(ex.getMessage());
                                    }
                                }
                                detachLisener((AbstractField) event.getComponent());
                            }

                        });
                        // for text area

                        String notesPropId = propertyId + "~" + Constant.NOTES;
                        notesField.setTextAreaData(notesPropId);
                        notesField.setNotesProperty(container.getContainerProperty(itemId, propertyId + Constant.NOTES));
                        String notesValue = editedNotes.get(notesPropId);

                        if (StringUtils.isNotBlank(notesValue)) {
                            notesField.setNotesValue(notesValue);
                            notesField.addToolTip(notesValue);
                        } else {
                            if (noteArr != null && noteArr.length != 0) {
                                notesField.setNotesValue(noteArr[1]);
                                notesField.addToolTip(noteArr[1]);
                            }
                        }
                        notesField.addTextAreaFocusListener(new FocusListener() {

                            @Override
                            public void focus(FocusEvent event) {

                                attachTAValueChangeListener((AbstractField) event.getComponent());
                            }

                        });

                        notesField.addTextAreaBlurListener(new BlurListener() {

                            @Override
                            public void blur(BlurEvent event) {

                                if (valueTAChange) {
                                    try {
                                        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(NOTES_DATE.getConstant());
                                        String formattedValue = String.valueOf(((TextArea) event.getComponent()).getValue()) + dateTimeFormat.format(new Date()) + " ,<" + CommonUtils.getUserNameById(sessionDTO.getUserId()) + ">";
                                        description = formattedValue;
                                        editedNotes.put(String.valueOf(((TextArea) event.getComponent()).getData()), formattedValue);
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

    /**
     * Add Result Table.
     */
    private void addResultTable() {
        tableVerticalLayout.addComponent(periodTableId);
        HorizontalLayout controlLayout = tableLogic.createControls();
        controlLayout.addStyleName(Constant.RESPONSIVE_PAGED_TABLE);
        controlLayout.setSizeUndefined();
        tableLogic.sinkItemPerPageWithPageLength(false);
        tableVerticalLayout.addComponent(controlLayout);
    }

    @UiHandler("generate")
    public void generate(Button.ClickEvent event) {
        if (checkSelection()) {
            tableVerticalLayout.removeAllComponents();
            tableLogic = new PhsWorksheetTableLogic();
            periodTableId = new FreezePagedTreeTable(tableLogic);
            initializeResultTable();
            configureResultTable();
            addResultTable();
            loadResultTable();

        } else {
            AbstractNotificationUtils.getErrorNotification("Generate Error ", "You must select an NDC from the drop down list box.");
        }
    }

    public boolean checkSelection() {
        boolean flag = true;
        if (StringUtils.isBlank(String.valueOf(ndcDdlb.getValue()))) {
            flag = false;
        }
        return flag;
    }

    private void loadResultTable() {
        try {
            tableLogic.clearAll();
            tableLogic.setRefresh(false);
            projectionDTO.clearNonFetchableIndex();
            tableLogic.setProjectionResultsData(projectionDTO, sessionDTO);
            tableLogic.setRefresh(true);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @UiHandler("calculate")
    public void calculate(Button.ClickEvent event) {
        try {
            boolean formatFlag = false;
            for (Map.Entry<String, String> entry : adjustedValues.entrySet()) {
                String formatedValue = entry.getValue();
                formatedValue = formatedValue.replace("$", StringUtils.EMPTY);
                if (StringUtils.isNotBlank(formatedValue)) {
                    if ("-".equals(formatedValue) || "+".equals(formatedValue)) {
                        formatFlag = true;
                        break;
                    }
                    if (!formatedValue.matches("^[\\+\\-]?\\d{0,5}\\.?\\d{0,4}$")) {
                        formatFlag = true;
                        break;
                    }
                }
            }
            if (formatFlag) {
                AbstractNotificationUtils.getErrorNotification("Calculate Error", "Override AMP values must be entered in dollar format with 4 decimal places.");
            } else {

                if (!adjustedValues.isEmpty() || !editedNotes.isEmpty()) {
                    if (!adjustedValues.isEmpty()) {
                        queryUtil.saveNotes(adjustedValues, sessionDTO, projectionDTO.getNdcSid().getId());
                        adjustedValues.clear();
                        callAdjustmentProcedure();
                        projectionDTO.setAdjust(true);
                        submitMsg = true;
                    }
                    if (!editedNotes.isEmpty()) {
                        queryUtil.saveNotes(editedNotes,sessionDTO, projectionDTO.getNdcSid().getId());
                        editedNotes.clear();
                    }
                    loadResultTable();
                    final Notification notif = new Notification("Calculation Complete", Notification.Type.HUMANIZED_MESSAGE);
                    notif.setPosition(Position.TOP_CENTER);
                    notif.setStyleName(ConstantsUtils.MY_STYLE);
                    notif.show(Page.getCurrent());
                }
            }
        } catch (SystemException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @UiHandler("reset")
    public void reset(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                // do nothing
            }

            @Override
            /**
             * The method is triggered when Yes button of the message
             */
            public void yesMethod() {
                brandDdlb.select(dto);
                ndcDdlb.select(dto);
            }
        }.getConfirmationMessage(RESET_CONFIRMATION.getConstant(), "Are you sure you want to reset the page to default/previous values?");
    }

    @UiHandler("tableReset")
    public void tableReset(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                // do nothing
            }

            @Override
            /**
             * The method is triggered when Yes button of the message
             */
            public void yesMethod() {
                adjustedValues.clear();
                editedNotes.clear();
                closeLogic();
                loadResultTable();
            }
        }.getConfirmationMessage(RESET_CONFIRMATION.getConstant(), "Are you sure you want to reset the page to default/previous values?");

    }

    @UiHandler("close")
    public void close(Button.ClickEvent event) {
        if (!Constant.VIEW.equalsIgnoreCase(mode)) {
            if (submitFlag) {
                new AbstractNotificationUtils() {
                    @Override
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
                    @Override
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
                }.getConfirmationMessage("Close Confirmation", "Are you sure you want to close? Any un-submitted changes will be lost. ");
            }
        } else {
            close();
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
        if (targetItem != null) {
            return (TableDTO) targetItem.getBean();
        } else {
            return null;
        }
    }

    void loadNdc() {
        ndcDdlb.setPageLength(NumericConstants.SEVEN);
        ndcDdlb.setImmediate(true);
        ndcDdlb.setNullSelectionAllowed(true);
        ndcDdlb.setInputPrompt(SELECT_ONE.getConstant());
        ndcDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        ndcDdlb.setItemCaptionPropertyId(DESCRIPTION.getConstant());
        ndcDdlb.markAsDirty();
        ndcContainer = new LazyContainer(HelperDTO.class, new NdcContainer(projectionDTO.getBrandWSdto(), true,ndcResultdto), new NdcCriteria());
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
        ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(exceltable), MASTER_PHS_WORKSHEET, MASTER_PHS_WORKSHEET, "Master_PHS_Worksheet.xls", false);
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
        List<TableDTO> resultList = phsResLogic.getPHSWorksheet(projectionDTO,sessionDTO);
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
        List<TableDTO> resultList = phsResLogic.getPhsWorksheetChild(projectionDTO,sessionDTO);
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

    private void callAdjustmentProcedure() {

        try {
            String priceType = "PHS";
            MedicaidURAResultsLogic medLogic = new MedicaidURAResultsLogic();
            medLogic.workSheetSetupCook(projectionDTO.getNdcSid().getId(), priceType, "PHS WORKSHEET", StringUtils.EMPTY,sessionDTO);
        } catch (SQLException | NamingException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public boolean isSubmit() {
        return submitFlag;
    }

    @UiHandler("submit")
    public void submit(Button.ClickEvent event) {
        if (submitMsg) {
            new AbstractNotificationUtils() {
                @Override
                public void noMethod() {
                    //Default method
                }

                @Override
                /**
                 * The method is triggered when Yes button of the message
                 */
                public void yesMethod() {
                    try {
                        fcpQueryUtil.updateBeforeAdjustment("getPhsBeforeAdjSubmitUpdate", sessionDTO);
                        fcpQueryUtil.updateAdjustment(projectionDTO.getNdcSid().getId(), "getPhsAdjSumbitUpdate",sessionDTO);
                        submitFlag = true;
                        submitMsg = false;
                    } catch (PortalException | SystemException ex) {
                       LOGGER.error(ex.getMessage());
                    }
                }
            }.getConfirmationMessage("Submit Confirmation ", "Are you sure you want to submit these AMP changes?");
        } else {
            AbstractNotificationUtils.getErrorNotification("Submit Error", "There are no changes to submit. ");
        }
    }

    public void closeLogic() {
        try {
            fcpQueryUtil.updateAdjustment(projectionDTO.getNdcSid().getId(), "getPhsAdjCloseUpdate",sessionDTO);
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
