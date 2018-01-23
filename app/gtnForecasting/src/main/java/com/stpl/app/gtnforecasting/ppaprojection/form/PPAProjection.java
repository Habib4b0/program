/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ppaprojection.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.dto.ForecastDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.RSFormulaDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.RunnableJob;
import com.stpl.app.gtnforecasting.logic.Utility;
import com.stpl.app.gtnforecasting.lookups.PPAFormulaLookup;
import com.stpl.app.gtnforecasting.ppaprojection.dto.PPAProjectionDTO;
import com.stpl.app.gtnforecasting.ppaprojection.logic.PPAProjectionLogic;
import com.stpl.app.gtnforecasting.ppaprojection.logic.PPAServiceSupport;
import com.stpl.app.gtnforecasting.ppaprojection.logic.tablelogic.PPAProjectionTableLogic;
import com.stpl.app.gtnforecasting.queryUtils.PPAQuerys;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.CommonConstants.SELECT_ONE;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import com.stpl.app.gtnforecasting.utils.FunctionNameUtil;
import com.stpl.app.gtnforecasting.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.utils.NotificationUtils;
import com.stpl.app.gtnforecasting.utils.UISecurityUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTIONS;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_PPA_PROJECTION;
import static com.stpl.app.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.event.FieldEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.ExtCustomTreeTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtListDTO;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.customdatefield.CustomDateField;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.custommenubar.CustomMenuBar.CustomMenuItem;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 *
 * @author Jayaram
 */
public class PPAProjection extends CustomComponent implements View {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger
            .getLogger(PPAProjection.class);
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * The field ddlb.
     */
    @UiField("fieldDdlb")
    private ComboBox fieldDdlb;
    /**
     * The value ddlb.
     */
    @UiField("valueDdlb")
    private CustomComboBox valueDdlb;
    /**
     * The value txt.
     */
    @UiField("valueTxt")
    private TextField valueTxt;
    /**
     * The mass update.
     */
    @UiField("massUpdate")
    private OptionGroup massUpdate;
    /**
     * The mass update.
     */
    @UiField("massDate")
    private PopupDateField massDate;
    /**
     * The populate.
     */
    @UiField("populate")
    private Button populate;
    /**
     * The excel export.
     */
    @UiField("excelExport")
    private Button excelExport;
    /**
     * The start period.
     */
    @UiField("startPeriod")
    private ComboBox startPeriod;
    /**
     * The end period.
     */
    @UiField("endPeriod")
    private ComboBox endPeriod;
    /**
     * The level.
     */
    @UiField("levelDdlb")
    private ComboBox level;
    /**
     * The level ddlb.
     */
    @UiField("levelFilterDdlb")
    private ComboBox levelFilter;
    @UiField("tableLayout")
    private VerticalLayout tableLayout;
    @UiField("lblStart")
    private Label lblStart;
    @UiField("lblEnd")
    private Label lblEnd;
    /**
     * The reset.
     */
    @UiField("expand")
    private Button expandBtn;
    @UiField("generateBtn")
    private Button generateBtn;
    @UiField("resetBtn")
    private Button resetBtn;
    /**
     * The reset.
     */
    @UiField("collapse")
    private Button collapseBtn;
    private ComboBox groupFilterDdlb;
    @UiField("groupLabel")
    private Label groupLabel;
    @UiField("frequencyDdlb")
    protected ComboBox frequencyDdlb;
    @UiField("fromDateDdlb")
    protected ComboBox fromDateDdlb;
    @UiField("toDateDdlb")
    protected ComboBox toDateDdlb;
    @UiField("projectionPeriodOrderOpg")
    protected OptionGroup projectionPeriodOrderOpg;
    @UiField("customMenuBar")
    protected CustomMenuBar customMenuBar;
    @UiField("buttonLayout")
    protected HorizontalLayout buttonLayout;
    private String value;
    /**
     * The massLookup.
     */
    @UiField("massLookup")
    private CustomTextField massLookup;
    private PPAFormulaLookup formulaLookup = null;
    private final Set<String> propertyIdSet = new HashSet<>();
    private final Set<String> hierarchyNoSet = new HashSet<>();
    @UiField("massGroup")
    private CustomComboBox massGroup;
    /**
     * The max split position.
     */
    private final float maxSplitPosition = 1000;
    /**
     * The min split position.
     */
    private final float minSplitPosition = 280;
    /**
     * The split position.
     */
    private final float splitPosition = 400;
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource(
            EXCEL_IMAGE_PATH.getConstant());
    /**
     * The map right visible columns.
     */
    private Map<Object, Object[]> mapRightVisibleColumns = new HashMap<>();
    /**
     * The map left visible columns.
     */
    private Map<Object, Object[]> mapLeftVisibleColumns = new HashMap<>();
    public static final String FORMULA_SYSTEM_SID1 = "formulaSystemSID";
    private final StplSecurity stplSecurity = new StplSecurity();
    private final SessionDTO session;
    private int projectionId;
    private PPAProjectionTableLogic tableLogic = new PPAProjectionTableLogic(this);
    private FreezePagedTreeTable resultsTable = new FreezePagedTreeTable(tableLogic);
    private final PPAProjectionLogic logic = new PPAProjectionLogic();
    private final ProjectionSelectionDTO selection = new ProjectionSelectionDTO();
    private final IndexedContainer groupContainer = new IndexedContainer();
    private ExtCustomTreeTable excelTable = new ExtCustomTreeTable();
    private final CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    private CustomTableHeaderDTO leftdto;
    private ExtTreeContainer<PPAProjectionDTO> excelContainer = new ExtTreeContainer<>(PPAProjectionDTO.class, ExtContainer.DataStructureMode.LIST);
    private ExtTreeContainer<PPAProjectionDTO> resultBeanContainer = new ExtTreeContainer<>(
            PPAProjectionDTO.class, ExtContainer.DataStructureMode.LIST);
    private ExtFilterTreeTable leftTable;
    private ExtFilterTreeTable rightTable;
    private CustomTableHeaderDTO ridhtdto;
    private final Button refresh = new Button("REFRESH");
    private int endQuater;
    private int endYear;
    private boolean groupChangeFlag = false;
    private boolean valueChangeForColumnCheckBox;
    private boolean checkedAllRecords;
    private static final List<Thread> manualSaveRunnableThreads = new ArrayList<>();
    private final Set<String> tableHirarechyNos = new HashSet<>();
    private final List<String> dateList = new ArrayList<>();
    private CustomMenuBar.CustomMenuItem customMenuItem;
    private Object fromDate;
    private Object toDate;
    /**
     * Property file for alert message
     */
    private static final ResourceBundle alertMsg = ResourceBundle.getBundle("properties.alertmessage");
    private final List<String> DEFAULT_VARIABLE_VALUES = new ArrayList<>();
    /**
     * Set to store all the unchecked records in result table this is used while
     * doing mass update To refresh the table
     */
    private final Set<String> UNCHECKED_RECORDS_SET = new HashSet<>();
    /**
     * Map to store DDLB List name
     */
    private static final Map<String, String> helperTableIndentifier = Constant.getHelperTableIdentifier();
    private static final Map<String, List<String>> populateIdentifier = Constant.getPopulateIdentifier();
    private static final Map<String, String> dbColumnIdentifier = Constant.getDatabaseColumnIdentifier();
    private static final Map<String, String> columnHeaderMap = Constant.getColumnHeaderMap();
    private final CommonUtil commonUtil = CommonUtil.getInstance();
    private final HelperDTO defaultValue = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    /**
     * variable to stop executing value change listener every time Except while
     * doing value change
     */
    private static boolean valueChangeAllowed = Boolean.FALSE;
    private boolean generateFlag = true;
    private Date oldDate;
    private final TableFieldFactory leftTableFieldFactory = getLeftTableFieldFactory();
    private final TableFieldFactory rightTableFieldFactory = getRightTableFieldFactory();
    private final ExtCustomTable.ColumnCheckListener olumnCheckListener = getColumnCheckListener();

    /**
     * Instantiates a new SALES_SMALL projection.
     *
     * @param session
     */
    public PPAProjection(SessionDTO session) {
        projectionId = session.getProjectionId();
        this.session = session;
        tableLogic.setSession(session);
        selection.setTabName("ppa");
        selection.setProjectionId(projectionId);
        selection.setScreenName(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED);
    }

    public Component getContent() {
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(Clara.create(getClass().getResourceAsStream("/PPAProjection.xml"), this));
        initProduct();

        projectionId = session.getProjectionId();
        try {
            configurefields();
            security();
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex);
        }
        Panel panel = new Panel();
        panel.setContent(layout);
        PPAProjection.valueChangeAllowed = Boolean.TRUE;
        return panel;

    }

    TableFieldFactory getLeftTableFieldFactory() {
        return new DefaultFieldFactory() {
            /**
             * To create editable fields inside resultsTable .
             */
            @Override
            public Field<?> createField(final Container container,
                    final Object itemId, final Object propertyId,
                    final Component uiContext) {
                Field component = null;
                final PPAProjectionDTO dto = (PPAProjectionDTO) itemId;
                if (propertyId.equals(Constant.CHECK_RECORD + ".0")) {
                    Object checkRecordValue = dto.getPropertyValue(propertyId.toString());
                    final ExtCustomCheckBox checkRecord = new ExtCustomCheckBox();
                    checkRecord.setImmediate(true);
                    if (checkRecordValue != null) {
                        checkRecord.setValue(Boolean.valueOf(String.valueOf(checkRecordValue)));
                    }
                    checkRecord.setEnabled(!Constant.VIEW.equals(session.getAction()));
                    checkRecord.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        @Override
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            if (PPAProjection.valueChangeAllowed) {
                                Boolean booleanValue = checkRecord.getValue();
                                savePPAProjection(propertyId.toString(), booleanValue, dto.getHirarechyNo(), Constant.LEFT);
                                try {
                                    updateForTopLevelCheckRecord(booleanValue, dto, propertyId);
                                    updateForChildLevel(booleanValue, itemId, propertyId.toString(), true);
                                    if (checkedAllRecords && !booleanValue) {
                                        valueChangeForColumnCheckBox = Boolean.TRUE;
                                        leftTable.setColumnCheckBox(Constant.CHECK_RECORD + ".0", true, false);
                                        valueChangeForColumnCheckBox = Boolean.FALSE;
                                        checkedAllRecords = Boolean.FALSE;
                                    }

                                    updateUncheckedRecords(booleanValue, itemId);

                                } catch (Exception ex) {
                                    LOGGER.error(ex);
                                }

                                dto.addBooleanProperties(propertyId.toString(), booleanValue);
                            }
                        }
                    });

                    component = checkRecord;

                } else if (propertyId.equals(Constant.GROUP)) {
                    if (((PPAProjectionDTO) itemId).getHirarechyName().equalsIgnoreCase(Constant.TRADING_PARTNER) && !Constant.VIEW.equals(session.getAction())) {
                        final TextField group = new TextField();
                        group.setImmediate(true);
                        group.setMaxLength(NumericConstants.FIFTY);
                        group.setValue(dto.getGroup());
                        group.addFocusListener(new FieldEvents.FocusListener() {
                            @Override
                            public void focus(FieldEvents.FocusEvent event) {

                                group.addValueChangeListener(new Property.ValueChangeListener() {
                                    @Override
                                    public void valueChange(Property.ValueChangeEvent event) {
                                        if (PPAProjection.valueChangeAllowed) {
                                            String groupValue = group.getValue();
                                            if (groupValue == null || StringUtils.EMPTY.equals(groupValue) || Constant.NULL.equals(groupValue)) {
                                                return;
                                            }
                                            savePPAProjection(propertyId.toString(), groupValue, ((PPAProjectionDTO) itemId).getHirarechyNo(), Constant.LEFT);
                                            ((PPAProjectionDTO) itemId).setGroup(groupValue);

                                            groupChangeFlag = Boolean.TRUE;
                                            loadGroupFilter();
                                            groupChangeFlag = Boolean.FALSE;
                                        }
                                    }
                                });
                                group.removeFocusListener(this);
                            }
                        });

                        component = group;
                    }
                } else if (propertyId.equals(Constant.PRICEPROTECTIONSTATUS)) {
                    final ComboBox status = new ComboBox();
                    status.setImmediate(true);
                    try {
                        CommonUtil.getInstance().loadActiveInactiveIntergerDDLB(status, false);
                    } catch (SystemException ex) {
                        LOGGER.error(ex);
                    }
                    status.setValue(dto.getPriceProtectionStatus());
                    status.addFocusListener(new FieldEvents.FocusListener() {
                        @Override
                        public void focus(FieldEvents.FocusEvent event) {
                            status.addValueChangeListener(new Property.ValueChangeListener() {
                                @Override
                                public void valueChange(Property.ValueChangeEvent event) {
                                    try {
                                        if (PPAProjection.valueChangeAllowed) {
                                            int statusValue = Integer.valueOf(status.getValue() == null ? DASH : String.valueOf(status.getValue()));
                                            ((PPAProjectionDTO) itemId).setPriceProtectionStatus(statusValue);
                                            tableHirarechyNos.add(tableLogic.getTreeLevelonCurrentPage(itemId));
                                            savePPAProjection(propertyId.toString(), statusValue, ((PPAProjectionDTO) itemId).getHirarechyNo(), Constant.LEFT);

                                        }
                                    } catch (NumberFormatException e) {
                                        LOGGER.error(e);
                                    }
                                }
                            });
                            status.removeFocusListener(this);
                        }
                    });

                    component = status;
                } else if (propertyId.equals(Constant.PRICEPROTECTIONSTARTDATE) || propertyId.equals(Constant.PRICEPROTECTIONENDDATE)) {
                    final CustomDateField date = new CustomDateField();
                    date.setImmediate(true);
                    date.setDateFormat(Constant.DATE);
                    if (propertyId.equals(Constant.PRICEPROTECTIONSTARTDATE)) {
                        date.setValue(((PPAProjectionDTO) itemId).getPriceProtectionStartDate());
                    } else if (propertyId.equals(Constant.PRICEPROTECTIONENDDATE)) {
                        date.setValue(((PPAProjectionDTO) itemId).getPriceProtectionEndDate());
                    }
                    date.addClickListener(new CustomDateField.ClickListener() {
                        @Override
                        public void click(CustomDateField.ClickEvent event) {
                            oldDate = ((CustomDateField) event.getComponent()).getValue();
                        }
                    });
                    date.addBlurListener(new FieldEvents.BlurListener() {
                        @Override
                        public void blur(FieldEvents.BlurEvent event) {
                            Date newDate = ((CustomDateField) event.getComponent()).getValue();
                            if (!(oldDate == newDate)) {
                                tableHirarechyNos.add(tableLogic.getTreeLevelonCurrentPage(itemId));
                                savePPAProjection(propertyId.toString(), newDate == null ? StringUtils.EMPTY : newDate, ((PPAProjectionDTO) itemId).getHirarechyNo(), Constant.LEFT);
                                if (propertyId.equals(Constant.PRICEPROTECTIONSTARTDATE)) {
                                    ((PPAProjectionDTO) itemId).setPriceProtectionStartDate(newDate);
                                } else if (propertyId.equals(Constant.PRICEPROTECTIONENDDATE)) {
                                    ((PPAProjectionDTO) itemId).setPriceProtectionEndDate(newDate);
                                }
                            }
                        }
                    });
                    component = date;
                }
                return component;
            }
        };
    }

    TableFieldFactory getRightTableFieldFactory() {
        return new DefaultFieldFactory() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            /**
             * To create editable fields inside resultsTable .
             */
            @Override
            public Field<?> createField(final Container container,
                    final Object itemId, final Object propertyId,
                    final Component uiContext) {
                Field component = null;

                String tempProId = ExtListDTO.getPropertyId(propertyId.toString());
                String customizedPropertyId = tempProId.substring(tempProId.indexOf(Constant.Q_SMALL) + NumericConstants.SIX);

                final PPAProjectionDTO dto = (PPAProjectionDTO) itemId;
                try {
                    if (columnHeaderMap.containsKey(customizedPropertyId)) {
                        final String actualColumnName = columnHeaderMap.get(customizedPropertyId);
                        if (populateIdentifier.get(Constant.DDLB_FIELD).contains(actualColumnName)) {
                            final ComboBox comboBox = new ComboBox();
                            if (Constant.PPAVariables.PRICE_PROTECTION_PRICE_TYPE.toString().equals(actualColumnName)
                                    || Constant.PPAVariables.BASE_PRICE_PRICE_TYPE.toString().equals(actualColumnName)
                                    || Constant.PPAVariables.RESET_PRICE_TYPE.toString().equals(actualColumnName)
                                    || Constant.PPAVariables.SUBSEQUENT_PERIOD_PRICE_TYPE.toString().equals(actualColumnName)) {
                                CommonUtil.getInstance().loadIntegerComboBox(comboBox, PPAServiceSupport.getInstance().getPriceResultList());
                            } else {
                                String listName = helperTableIndentifier.get(actualColumnName);
                                commonUtil.loadComboBoxWithInteger(comboBox, listName, false);
                            }

                            comboBox.setWidth("100%");
                            comboBox.addFocusListener(new FieldEvents.FocusListener() {
                                @Override
                                public void focus(FieldEvents.FocusEvent event) {
                                    comboBox.addValueChangeListener(new Property.ValueChangeListener() {
                                        @Override
                                        public void valueChange(Property.ValueChangeEvent event) {
                                            if (PPAProjection.valueChangeAllowed) {
                                                int ddlbValue = Integer.valueOf(String.valueOf(comboBox.getValue()));
                                                if (ddlbValue == -1) {
                                                    return;
                                                }
                                                dto.getHelperList().remove(propertyId.toString());
                                                dto.addProperties(propertyId.toString(), ddlbValue);
                                                savePPAProjection(propertyId.toString(), ddlbValue, ((PPAProjectionDTO) itemId).getHirarechyNo(), Constant.RIGHT);
                                                tableHirarechyNos.add(tableLogic.getTreeLevelonCurrentPage(itemId));
                                            }
                                        }
                                    });
                                    comboBox.removeFocusListener(this);
                                }
                            });

                            comboBox.select(dto.getPropertyValue(propertyId.toString()));
                            if (dto.getHelperList().contains(propertyId.toString())) {
                                comboBox.addItem(-1);
                                comboBox.setItemCaption(-1, Constant.MULTIPLE);
                                comboBox.select(-1);
                            }

                            component = comboBox;
                        } else if (populateIdentifier.get(Constant.TEXT_FIELD).contains(actualColumnName)) {
                            final TextField textField = new TextField();
                            textField.setWidth("100%");
                            textField.setImmediate(true);
                            Object val = dto.getPropertyValue(propertyId.toString());
                            if (val != null) {
                                textField.setValue(val.toString());
                            }
                            textField.addFocusListener(new FieldEvents.FocusListener() {
                                @Override
                                public void focus(FieldEvents.FocusEvent event) {
                                    textField.addValueChangeListener(new Property.ValueChangeListener() {
                                        @Override
                                        public void valueChange(Property.ValueChangeEvent event) {
                                            if (PPAProjection.valueChangeAllowed) {
                                                String textValue = textField.getValue();
                                                if (textValue == null || StringUtils.EMPTY.equals(textValue) || Constant.NULL.equals(textValue)) {
                                                    savePPAProjection(propertyId.toString(), null, ((PPAProjectionDTO) itemId).getHirarechyNo(), Constant.RIGHT);
                                                    return;
                                                }
                                                tableHirarechyNos.add(tableLogic.getTreeLevelonCurrentPage(itemId));
                                                dto.addProperties(propertyId.toString(), textValue);
                                                dto.getHelperList().remove(propertyId.toString());
                                                String propId = String.valueOf(propertyId);
                                                if (propId.contains(Constant.PPAColumns.NEP.getConstant())
                                                        || propId.contains(Constant.PPAColumns.PRICE_TOLERANCE.getConstant())
                                                        || propId.contains(Constant.PPAColumns.MAX_INCREMENTAL_CHANGE.getConstant())) {
                                                    textValue = String.valueOf(Double.valueOf(textValue) / dto.getCCPCount());
                                                }
                                                savePPAProjection(propertyId.toString(), textValue, ((PPAProjectionDTO) itemId).getHirarechyNo(), Constant.RIGHT);

                                            }
                                        }
                                    });
                                    textField.removeFocusListener(this);
                                }
                            });
                            if (dto.getHelperList().contains(propertyId.toString())) {
                                dto.addProperties(propertyId.toString(), StringUtils.EMPTY);
                                textField.setInputPrompt(Constant.MULTIPLE);
                            }

                            component = textField;
                        } else if (populateIdentifier.get(Constant.DATE_FEILD).contains(actualColumnName)) {
                            final CustomDateField dateField = new CustomDateField();
                            dateField.setImmediate(true);
                            dateField.setWidth("100%");
                            dateField.setDateFormat(Constant.DATE);
                            Object val = dto.getPropertyValue(propertyId.toString());
                            if (val != null) {
                                dateField.setValue((Date) val);
                            }
                            dateField.addClickListener(new CustomDateField.ClickListener() {
                                @Override
                                public void click(CustomDateField.ClickEvent event) {
                                    oldDate = dateField.getValue();
                                }
                            });
                            dateField.addBlurListener(new FieldEvents.BlurListener() {
                                @Override
                                public void blur(FieldEvents.BlurEvent event) {
                                    Date newDate = dateField.getValue();
                                    try {
                                        if (!(oldDate == newDate)) {
                                            dto.addProperties(propertyId.toString(), newDate);
                                            dto.getHelperList().remove(propertyId.toString());
                                            tableHirarechyNos.add(tableLogic.getTreeLevelonCurrentPage(itemId));
                                            savePPAProjection(propertyId.toString(), newDate, dto.getHirarechyNo(), Constant.RIGHT);
                                        }
                                    } catch (Exception e) {
                                        LOGGER.error(e);
                                    }
                                }
                            });

                            if (dto.getHelperList().contains(propertyId.toString())) {
                                dateField.setValue(null);
                                dto.addProperties(propertyId.toString(), null);
                                dateField.setInputPrompt(Constant.MULTIPLE);
                            }
                            if (propertyId.toString().contains(Constant.PPAColumns.ATTACHED_DATE.getConstant())) {
                                dateField.setReadOnly(true);
                            }

                            component = dateField;
                        } else if (populateIdentifier.get(Constant.LOOKUP_FIELD).contains(actualColumnName)) {
                            final CustomTextField lookUpField = new CustomTextField();
                            lookUpField.setWidth("100%");
                            lookUpField.addStyleName(Constant.SEARCH_ICON_STYLE);
                            lookUpField.setImmediate(true);
                            Object val = dto.getPropertyValue(propertyId.toString());
                            if (val != null) {
                                lookUpField.setValue(val.toString());
                            }

                            lookUpField.addClickListener(new CustomTextField.ClickListener() {
                                @Override
                                public void click(CustomTextField.ClickEvent event) {
                                    try {
                                        tableHirarechyNos.add(tableLogic.getTreeLevelonCurrentPage(itemId));

                                        if (!propertyIdSet.contains(propertyId) && !hierarchyNoSet.contains(dto.getHirarechyNo())) {
                                            propertyIdSet.add(String.valueOf(propertyId));
                                            hierarchyNoSet.add(String.valueOf(dto.getHirarechyNo()));
                                            formulaLookup = new PPAFormulaLookup(actualColumnName, !Constant.PPAVariables.NEP_FORMULA.getConstant().equalsIgnoreCase(actualColumnName));
                                        } else {
                                            formulaLookup.clearAndRefresh(actualColumnName, !Constant.PPAVariables.NEP_FORMULA.getConstant().equalsIgnoreCase(actualColumnName));

                                        }
                                        formulaLookup.addCloseListener(new Window.CloseListener() {
                                            @Override
                                            public void windowClose(Window.CloseEvent e) {
                                                if (formulaLookup.isSelected()) {
                                                    RSFormulaDTO rSFormulaDTO = formulaLookup.getSelectedItem();
                                                    lookUpField.setValue(rSFormulaDTO.getFormulaNo());
                                                    dto.addProperties(propertyId.toString(), rSFormulaDTO.getFormulaNo());
                                                    dto.getHelperList().remove(propertyId.toString());
                                                    final Map<String, String> map = new HashMap<>();
                                                    map.put("formulaNo", rSFormulaDTO.getFormulaNo());
                                                    map.put("formulaName", rSFormulaDTO.getFormulaName());
                                                    map.put("formulaID", rSFormulaDTO.getFormulaID());
                                                    map.put(FORMULA_SYSTEM_SID1, String.valueOf(rSFormulaDTO.getForectastingFormulaSid()));
                                                    map.put(Constant.FORMULA_TYPE, String.valueOf(rSFormulaDTO.getFormulaType().getId()));
                                                    map.put("formulaTypeDesc", String.valueOf(rSFormulaDTO.getFormulaType().getDescription()));
                                                    lookUpField.setData(map);
                                                    savePPAProjection(propertyId.toString(), rSFormulaDTO.getForectastingFormulaSid(), ((PPAProjectionDTO) itemId).getHirarechyNo(), Constant.RIGHT);
                                                }
                                                formulaLookup.removeCloseListener(this);
                                            }
                                        });
                                        UI.getCurrent().addWindow(formulaLookup);
                                    } catch (IllegalArgumentException | NullPointerException ex) {
                                        LOGGER.error(ex);
                                    }
                                }
                            });
                            if (dto.getHelperList().contains(propertyId.toString())) {
                                dto.addProperties(propertyId.toString(), StringUtils.EMPTY);
                                lookUpField.setValue(StringUtils.EMPTY);
                                lookUpField.setInputPrompt(Constant.MULTIPLE);
                            }

                            component = lookUpField;
                        }
                    }
                } catch (Property.ReadOnlyException | Converter.ConversionException | UnsupportedOperationException e) {
                    LOGGER.error(e);
                }
                return component;
            }
        };
    }

    ExtCustomTable.ColumnCheckListener getColumnCheckListener() {
        return new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                    if ((!valueChangeForColumnCheckBox) && ((Constant.CHECK_RECORD + ".0").equals(event.getPropertyId().toString()))) {
                        String group = String.valueOf(groupFilterDdlb.getValue() == null ? Constant.PERCENT : groupFilterDdlb.getValue()).replace(Constant.PPA, StringUtils.EMPTY);
                        group = group.equals(Constant.ALL_GROUP) ? Constant.PERCENT : group;
                        logic.saveAllcheckRecord(event.isChecked(), session, group);
                        List<String> levels = tableLogic.getAllLevels();
                        for (String hierarchyNo : levels) {
                            boolean presentFlag = true;
                            Object lastParent = tableLogic.getcurrentTreeData(hierarchyNo);
                            if (lastParent == null) {
                                presentFlag = false;
                                lastParent = tableLogic.getParent(hierarchyNo);
                            }
                            if (lastParent != null) {
                                try {
                                    updateRow(event.isChecked(), lastParent, event.getPropertyId().toString(), presentFlag);
                                    checkedAllRecords = Boolean.TRUE;
                                    /**
                                     * Clearing the UNCHECKED_RECORDS_SET
                                     * Because all the records in the table r
                                     * checked
                                     */
                                    UNCHECKED_RECORDS_SET.clear();
                                } catch (Exception ex) {
                                    LOGGER.error(ex);
                                }
                            }
                        }
                    }

            }
        };
    }

    /**
     * Inits the product.
     */
    @SuppressWarnings("serial")
    private void initProduct() {

        resultsTable.markAsDirty();
        resultsTable.setFilterBarVisible(true);
        resultsTable.setSelectable(true);
        resultsTable.setImmediate(true);
        resultsTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setPageLength(NumericConstants.TWENTY);
        leftTable = resultsTable
                .getLeftFreezeAsTable();
        rightTable = resultsTable
                .getRightFreezeAsTable();
        rightTable.setImmediate(true);
        leftTable.setImmediate(true);
        leftTable.setEditable(true);
        rightTable.setEditable(true);
        groupFilterDdlb = new ComboBox();
        groupFilterDdlb.setImmediate(Boolean.TRUE);
        groupFilterDdlb.setContainerDataSource(groupContainer);
        groupFilterDdlb.setNullSelectionAllowed(true);
        groupFilterDdlb.setNullSelectionItemId(Constant.ALL_GROUP);
        groupFilterDdlb.setInputPrompt(Constant.ALL_GROUP);
        groupFilterDdlb.select(Constant.ALL_GROUP);
        groupFilterDdlb.setWidth("100%");
        OnLoadGroupFilter();
        leftTable.addColumnCheckListener(olumnCheckListener);
        leftTable.setTableFieldFactory(leftTableFieldFactory);
        rightTable.setTableFieldFactory(rightTableFieldFactory);

    }

    private List<String> getCheckedVariables() {
        List<String> result = new ArrayList<>();
        List<CustomMenuItem> items = customMenuItem.getChildren();
        for (Iterator<CustomMenuItem> it = items.iterator(); it.hasNext();) {
            CustomMenuItem customMenuItem1 = it.next();
            if (customMenuItem1.isChecked()) {
                if (customMenuItem1.getMenuItem().getCaption().equals(Constant.PPAVariables.CHECK_ALL.toString())) {
                    result.addAll(Arrays.asList(Constant.PPAVariables.getCheckAllVariables()));
                    loadPPASelectedList(result);
                    return result;
                }
                result.add(customMenuItem1.getMenuItem().getCaption());
            }
        }
        loadPPASelectedList(result);
        return result;
    }

    /**
     * Configure Fields.
     */
    private void configurefields() throws PortalException, SystemException {
        LOGGER.debug("Starting configure fields");
        if (Constant.VIEW.equals(session.getAction())) {
            rightTable.setEditable(Boolean.FALSE);
            massUpdate.setEnabled(Boolean.FALSE);
        }
        LOGGER.debug("SessionId->" + session.getSessionId());
        LOGGER.debug("ProjectionId->" + session.getProjectionId());
        tableLayout.removeAllComponents();
        frequencyDdlb.addItem(SELECT_ONE.getConstant());
        frequencyDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        frequencyDdlb.addItem(Constant.ANNUALLY);
        frequencyDdlb.addItem(Constant.SEMI_ANNUALLY);
        frequencyDdlb.addItem(Constant.QUARTERLY);
        frequencyDdlb.addItem(Constant.MONTHLY);
        frequencyDdlb.setValue(Constant.QUARTERLY);
        frequencyDdlb.setReadOnly(true);
        String[] variables = Constant.PPAVariables.toArray();
        customMenuItem = customMenuBar.addItem("-Select Variables-", null);
        CustomMenuItem[] customItem = new CustomMenuItem[variables.length];
        for (int i = 0; i < variables.length; i++) {
            customItem[i] = customMenuItem.addItem(variables[i].trim(), null);
            customItem[i].setCheckable(true);
            customItem[i].setItemClickable(true);

            customItem[i].setItemClickNotClosable(true);
            if (i == 0) {
                customItem[i].setCheckAll(true);
            }
        }

        projectionPeriodOrderOpg.setImmediate(true);
        projectionPeriodOrderOpg.addStyleName(Constant.HORIZONTAL);
        projectionPeriodOrderOpg.addStyleName(Constant.OPTION_GROUP_WIDTH);
        projectionPeriodOrderOpg.addItem(Constant.ASCENDING);
        projectionPeriodOrderOpg.addItem(Constant.DESCENDING);
        projectionPeriodOrderOpg.setValue(Constant.ASCENDING);

        endQuater = session.getForecastDTO().getProjectionEndMonth() / NumericConstants.THREE;
        endYear = session.getForecastDTO().getProjectionEndYear();
        selection.setTpLevel(Utility.getTradingPartnerLevelNo(projectionId,session));
        selection.setRelationshipBuilderSid(selection.getRelationshipBuilderSid());
        boolean isAdd = true;
        if (!Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction())) {
            isAdd = false;
            Map<Object, Object> map = CommonLogic.editProjectionResults(TAB_PPA_PROJECTION.getConstant(), selection);

            if (map != null && map.get(Constant.VARIABLE) != null) {
                String[] arg = map.get(Constant.VARIABLE).toString().split(",");
                for (int i = 0; i < arg.length; i++) {
                    String selectedVariables = arg[i];
                    if (!StringUtils.isBlank(selectedVariables)) {

                        List<CustomMenuItem> items = customMenuItem.getChildren();
                        for (CustomMenuItem customMenuItem1 : items) {
                            if (Constant.PPAVariables.CHECK_ALL.getConstant().equals(selectedVariables)) {
                                customMenuItem1.setChecked(Boolean.TRUE);
                                generateFlag = false;
                            } else {
                                if (Constant.getColumnHeaderMap().containsKey(selectedVariables.trim()) && Constant.getColumnHeaderMap().get(selectedVariables.trim()).equals(customMenuItem1.getText())) {

                                    customMenuItem1.setChecked(Boolean.TRUE);
                                    generateFlag = false;
                                }
                            }
                        }

                    }
                }
            }

            if ((map != null && map.containsKey(Constant.PPA_FROM_DATE)) && (map.get(Constant.PPA_FROM_DATE) != null)) {
                    fromDate = map.get(Constant.PPA_FROM_DATE);
            }
            if ((map != null && map.containsKey(Constant.PPA_TO_DATE)) && (map.get(Constant.PPA_TO_DATE) != null)) {
                    toDate = map.get(Constant.PPA_TO_DATE);
            }

        }

        fromDateDdlb.setNullSelectionAllowed(false);
        toDateDdlb.setNullSelectionAllowed(false);
        loadProjectionSelection(true);
        selection.setActualsOrProjections(PROJECTIONS.getConstant());
        selection.setFrequency(QUARTERLY.getConstant());
        selection.setProjectionOrder(Constant.ASCENDING);
        selection.setHistoryNum(0);
        CommonUtils.getHistoryAndProjectionDetails(selection);
        HeaderUtils.prepareCommonColumnHeaders(selection);
        loadPeriods(selection);

        if (!isAdd) {
            if (fromDate != null) {
                fromDateDdlb.setValue(fromDate);
            }
            if (toDate != null) {
                toDateDdlb.setValue(toDate);
            }

            selection.setForecastDTO(getClonedForecastDTO(selection.getForecastDTO(), false));
        }
        List<String> result = getCheckedVariables();
        configureMassUpdate();
        if (!result.isEmpty()) {
            fieldDdlb.addItems(result);
        }
        loadGroupFilter();
        configureTable(true);
        addResultTable();
        valueDdlb.setNewItemsAllowed(true);
        valueDdlb.setImmediate(Boolean.TRUE);
        valueDdlb.addBlurValue(Boolean.TRUE);
        massDate.setImmediate(true);
        massDate.setDateFormat(Constant.DATE);

        if (resultBeanContainer.getItemIds().size() > 0) {
            LOGGER.debug("PPASubmitFlag: True"); 
        }
        level.addStyleName(Constant.POPUPCONTENTCOMBOSIZE);
        level.setImmediate(true);
        customMenuBar.setScrollable(true);
        customMenuBar.setPageLength(NumericConstants.TEN);
        customMenuBar.setWidth(value);
        refresh.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                waitForSave();
                groupChangeFlag = Boolean.TRUE;
                LOGGER.debug("Refreshing the PPA table for hierarchy No :" + tableHirarechyNos);
                Set<String> finalHirarechyNo = new HashSet<>();
                for (String hirarechyNo : tableHirarechyNos) {
                    finalHirarechyNo.add(hirarechyNo);
                    finalHirarechyNo.addAll(tableLogic.getAllParentLevels(hirarechyNo));
                    finalHirarechyNo.addAll(tableLogic.getAllChildLevels(hirarechyNo));
                }
                LOGGER.debug("Refreshing the PPA table for hierarchy Childs :" + finalHirarechyNo);
                tableLogic.forRefresh(finalHirarechyNo);
                tableLogic.setCurrentPage(tableLogic.getCurrentPage());
                tableHirarechyNos.clear();
                groupChangeFlag = Boolean.FALSE;

            }
        });

    }

    private void configureMassUpdate() {

        massUpdate.addItem(ConstantsUtils.ENABLE);
        massUpdate.addItem(ConstantsUtils.DISABLE);
        massUpdate.select(ConstantsUtils.DISABLE);

        DEFAULT_VARIABLE_VALUES.add(Constant.SELECT_ONE);
        DEFAULT_VARIABLE_VALUES.add(Constant.GROUPFCAPS);
        DEFAULT_VARIABLE_VALUES.add(Constant.PRICE_PROTECTION_STATUS);
        DEFAULT_VARIABLE_VALUES.add(Constant.PRICE_PROTECTION_START_DATE);
        DEFAULT_VARIABLE_VALUES.add(Constant.PRICE_PROTECTION_END_DATE);
        fieldDdlb.addItems(DEFAULT_VARIABLE_VALUES);
        fieldDdlb.setNullSelectionItemId(Constant.SELECT_ONE);

        valueDdlb.setVisible(true);
        valueDdlb.addItem(Constant.SELECT_ONE);
        valueDdlb.setNullSelectionItemId(Constant.SELECT_ONE);
        valueDdlb.setNewItemsAllowed(Boolean.TRUE);
        valueDdlb.setTextInputAllowed(Boolean.TRUE);
        valueDdlb.setImmediate(Boolean.TRUE);
        valueTxt.setVisible(false);
        valueTxt.setMaxLength(NumericConstants.FIFTY);
        valueTxt.setStyleName(Constant.TXT_RIGHT_ALIGN);
        massUpdate.addStyleName("horizantal");
        lblEnd.setImmediate(true);
        lblStart.setImmediate(true);
        startPeriod.addItem(Constant.SELECT_ONE);
        startPeriod.setNullSelectionItemId(Constant.SELECT_ONE);
        endPeriod.addItem(Constant.SELECT_ONE);
        endPeriod.setNullSelectionItemId(Constant.SELECT_ONE);
        massGroup.setVisible(false);
    }

    /**
     * Populate btn logic.
     *
     * @param event the event
     */
    @UiHandler("populate")
    public void populateBtnLogic(Button.ClickEvent event) {

        LOGGER.debug("Inside Populate button");
        waitForSave();
        try {
            valueChangeForColumnCheckBox = Boolean.TRUE;
            boolean isChecked = false;
            Collection<?> chkList = resultsTable.getLeftFreezeAsTable().getItemIds();
            if (!chkList.isEmpty()) {
                for (Object item : chkList) {
                    if ((Boolean) tableLogic.getContainerDataSource().getContainerProperty(item, Constant.CHECK_RECORD + ".0").getValue()) {
                        isChecked = true;
                        break;
                    }
                }
            }
            boolean validationError = false;
            if (fieldDdlb.getValue() != null) {
                PPAProjection.valueChangeAllowed = Boolean.FALSE;
                String fieldValue = fieldDdlb.getValue().toString().trim();
                Object valueDdlbVal = valueDdlb.getValue();
                Object groupValue = massGroup.getValue();
                String valueTxtValue = valueTxt.getValue();
                Date date = massDate.getValue();
                String lookupValue = massLookup.getData() == null ? StringUtils.EMPTY : ((Map<String, String>) massLookup.getData()).get(FORMULA_SYSTEM_SID1);
                Date dateValue = massDate.getValue();
                String groupFilterValue = String.valueOf(groupFilterDdlb.getValue() == null ? Constant.PERCENT : groupFilterDdlb.getValue()).replace(Constant.PPA, StringUtils.EMPTY);
                groupFilterValue = groupFilterValue.equals(Constant.ALL_GROUP) ? Constant.PERCENT : groupFilterValue;

                if ((populateIdentifier.get(Constant.DDLB_FIELD).contains(fieldValue) || Constant.PRICE_PROTECTION_STATUS.equals(fieldValue))
                        && (valueDdlbVal == null || valueDdlbVal.equals(defaultValue))) {
                    validationError = Boolean.TRUE;
                } else if ((Constant.PRICE_PROTECTION_START_DATE.equals(fieldValue) || Constant.PRICE_PROTECTION_END_DATE.equals(fieldValue)
                        || populateIdentifier.get(Constant.DATE_FEILD).contains(fieldValue)) && date == null) {
                    validationError = Boolean.TRUE;
                } else if (Constant.GROUPFCAPS.equals(fieldValue)
                        && (groupValue == null || groupValue.equals(Constant.SELECT_ONE))) {
                    validationError = Boolean.TRUE;
                } else if (populateIdentifier.get(Constant.LOOKUP_FIELD).contains(fieldValue) && lookupValue == null) {
                    validationError = Boolean.TRUE;
                }
                if (validationError) {
                    MessageBox.showPlain(Icon.INFO, Constant.ERROR, alertMsg.getString("PPA_MSG_ID_06").replace(Constant.REPLACE_STRING, fieldValue), ButtonId.OK);
                    return;
                }
                if (!isChecked) {
                    MessageBox.showPlain(Icon.ERROR, "No records selected", "Please select which levels in the list view the Mass Update applies to.", ButtonId.OK);
                    return;
                }
                int startQuater = 0;
                int startYear = 0;
                if (startPeriod.getValue() != null) {
                    startQuater = Integer.valueOf(startPeriod.getValue().toString().charAt(1) - NumericConstants.FORTY_EIGHT);
                    startYear = Integer.valueOf(startPeriod.getValue().toString().substring(NumericConstants.THREE, NumericConstants.SEVEN));
                } else if (startPeriod.isVisible()) {
                    MessageBox.showPlain(Icon.INFO, Constant.ERROR, alertMsg.getString("PPA_MSG_ID_03"), ButtonId.OK);
                    return;
                }
                if (populateIdentifier.get(Constant.FROZEN_FIELDS).contains(fieldValue)) {
                    Object val = null;
                    if (Constant.GROUPFCAPS.equals(fieldValue)) {
                        val = groupValue;
                    } else if (Constant.PRICE_PROTECTION_STATUS.equals(fieldValue)) {
                        val = valueDdlbVal;
                    } else {
                        val = dateValue;
                    }

                    if (val == null) {
                        return;
                    }
                    massUpdatePPAProjection(val, fieldValue, dbColumnIdentifier.get(fieldValue), 0, 0, 0, 0, selection);
                    loadMassGroup();
                    if (Constant.GROUPFCAPS.equals(fieldValue)) {
                        groupChangeFlag = Boolean.TRUE;
                        loadGroupFilter();
                        groupChangeFlag = Boolean.FALSE;
                    }

                    /**
                     * *
                     * Mass Update for Remaining all the variables in Right side
                     * table
                     */
                } else if (populateIdentifier.get(Constant.DDLB_FIELD).contains(fieldValue)) {

                    massUpdatePPAProjection(valueDdlbVal, fieldValue, dbColumnIdentifier.get(fieldValue), startQuater, endQuater, startYear, endYear,  selection);
                } else if (populateIdentifier.get(Constant.TEXT_FIELD).contains(fieldValue)) {

                    massUpdatePPAProjection(valueTxtValue, fieldValue, dbColumnIdentifier.get(fieldValue), startQuater, endQuater, startYear, endYear, selection);
                } else if (populateIdentifier.get(Constant.LOOKUP_FIELD).contains(fieldValue)) {

                    massUpdatePPAProjection(lookupValue, fieldValue, dbColumnIdentifier.get(fieldValue), startQuater, endQuater, startYear, endYear,  selection);
                } else if (populateIdentifier.get(Constant.DATE_FEILD).contains(fieldValue)) {

                    massUpdatePPAProjection(dateValue, fieldValue, dbColumnIdentifier.get(fieldValue), startQuater, endQuater, startYear, endYear, selection);
                } else {

                }
            } else {
                MessageBox.showPlain(Icon.INFO, Constant.ERROR, alertMsg.getString("PPA_MSG_ID_09"), ButtonId.OK);
            }
            PPAProjection.valueChangeAllowed = Boolean.TRUE;
            valueChangeForColumnCheckBox = Boolean.FALSE;
        } catch (NumberFormatException e) {
            LOGGER.error("Error While doing mass Update :" + e.getMessage());
        }
    }

    private void updateForTopLevelCheckRecord(boolean value, Object itemId, Object propertyId)  {
        LOGGER.debug("Entering updateForTopLevelCheckRecord method");

        String treeLevelNO = tableLogic.getTreeLevelonCurrentPage(itemId);
        PPAProjectionDTO ppaProjectionDTO = (PPAProjectionDTO) itemId;
        List<String> hierarchyNos = tableLogic.getAllParentLevels(itemId);
        hierarchyNos.remove(treeLevelNO);
        Collections.reverse(hierarchyNos);
        for (String hierarchyNo : hierarchyNos) {
            boolean presentFlag = true;
            Object lastParent = tableLogic.getcurrentTreeData(hierarchyNo);
            if (lastParent == null) {
                presentFlag = false;
                lastParent = tableLogic.getParent(hierarchyNo);
            }

            if (lastParent != null) {
                int updateValue = 0;
                int currentCount = ppaProjectionDTO.getCCPCount();
                int parentCount = ((PPAProjectionDTO) lastParent).getCheckRecordCount();
                if (value) {
                    updateValue = parentCount + currentCount;
                } else {
                    updateValue = parentCount - currentCount;
                }
                updateRow(updateValue, lastParent, propertyId.toString(), presentFlag);

            }

        }

        LOGGER.debug("End of Populate button");
    }

    private void updateResetRow(String propertyId, Object lastParent, boolean b, boolean presentFlag) {
        ((PPAProjectionDTO) lastParent).addProperties(propertyId, b);
        if (presentFlag) {
            leftTable.getItem(lastParent).getItemProperty(propertyId).setValue(b);
        }
    }

    private void updateForChildLevel(Object value, Object itemId, String propertyId, boolean presentFlag)  {
        Object methodItemId = itemId;
        updateRow(value, methodItemId, propertyId, presentFlag);
        List<String> hierarchyNos = tableLogic.getAllChildLevels(itemId);
        for (String hierarchyNo : hierarchyNos) {
            presentFlag = true;
            Object lastParent = tableLogic.getcurrentTreeData(hierarchyNo);
            if (lastParent == null) {
                presentFlag = false;
                lastParent = tableLogic.getParent(hierarchyNo);
            }
            if (lastParent != null) {
                updateRow(value, lastParent, propertyId, presentFlag);
            }
        }
    }

    private void updateRow(Object value, Object itemId, String propertyId, boolean presentFlag)  {
        if (propertyId != null) {
            Boolean result = false;
            PPAProjectionDTO dto = (PPAProjectionDTO) itemId;
            if (value instanceof Boolean) {
                result = Boolean.valueOf(value.toString());
                if (result) {
                    dto.setCheckRecordCount(dto.getCCPCount());
                } else {
                    dto.setCheckRecordCount(0);
                }
                updateResetRow(propertyId, itemId, result, presentFlag);
            } else if (value instanceof Integer) {

                if (Integer.valueOf(value.toString()) >= dto.getCCPCount()) {
                    result = Boolean.TRUE;
                    dto.setCheckRecordCount(dto.getCCPCount());
                } else {
                    if (Integer.valueOf(value.toString()) < 0) {
                        dto.setCheckRecordCount(0);
                    } else {
                        dto.setCheckRecordCount(Integer.valueOf(value.toString()));
                    }
                    result = Boolean.FALSE;
                }
                updateResetRow(propertyId, itemId, result, presentFlag);
            }

        }
    }

    /**
     * level filter value change listener.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @UiHandler("levelFilterDdlb")
    public void levelFilter(Property.ValueChangeEvent event)  {
        if (!valueChangeForColumnCheckBox) {
            waitForSave();
            loadResults(event.getProperty().getValue());
        }
    }

    /**
     * Collapse button logic.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @UiHandler("collapse")
    public void collapseLvlBtn(Button.ClickEvent event)  {
        waitForSave();
        expandCollapseLevelOption(Boolean.FALSE, level.getValue());
    }

    /**
     * Expand lvl btn.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @UiHandler("expand")
    public void expandLvlBtn(Button.ClickEvent event) {
        waitForSave();
        expandCollapseLevelOption(Boolean.TRUE, level.getValue());
    }

    private void expandCollapseLevelOption(boolean isExpand, Object value) {
        if (value != null) {
            List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(value);
            int levelNo = Integer.valueOf(String.valueOf(levelHierarchy.get(0)));
            if (levelNo > 0) {
                if (selection.isIsFilter()) {
                    valueChangeForColumnCheckBox = Boolean.TRUE;
                    levelFilter.setValue(SELECT_ONE);
                    selection.setIsFilter(false);
                    tableLogic.clearAll();
                    valueChangeForColumnCheckBox = Boolean.FALSE;
                } else {
                    tableLogic.clearAllExceptCurrentPage();
                }
                String hierarchyIndicator = String.valueOf(levelHierarchy.get(1));
                selection.setHierarchyIndicator(hierarchyIndicator);
                if (!isExpand) {
                    levelNo--;
                }
                tableLogic.loadExpandData(levelNo);
            }
        } else {
            try {
                loadResults(null);

            }  catch (Exception ex) {
               LOGGER.error(ex);
            }
        }
    }

    /**
     * Excel export btn.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @UiHandler("excelExport")
    public void excelExportBtn(Button.ClickEvent event) {
        excelExportLogic();
    }

    /**
     * Field ddlb.
     *
     * @param event the event
     */
    @UiHandler("fieldDdlb")
    public void fieldDdlb(Property.ValueChangeEvent event) {
        LOGGER.debug("fieldDdlb value change listener starts");
        try {
            if (fieldDdlb.getValue() != null) {
                value = fieldDdlb.getValue().toString().trim();
                List<String> ppaVaribles = Arrays.asList(Constant.PPAVariables.toArray());
                if (ppaVaribles.contains(value)) {
                    startPeriod.setVisible(true);
                    endPeriod.setVisible(true);
                    lblEnd.setVisible(true);
                    lblStart.setVisible(true);
                } else {
                    startPeriod.setVisible(false);
                    endPeriod.setVisible(false);
                    lblEnd.setVisible(false);
                    lblStart.setVisible(false);
                }
                valueDdlb.setContainerDataSource(null);
                valueDdlb.removeAllItems();
                massGroup.setContainerDataSource(null);
                massGroup.removeAllItems();
                massLookup.setVisible(false);
                valueTxt.setVisible(false);
                valueDdlb.setVisible(false);
                massDate.setVisible(false);
                massGroup.setVisible(false);
                massDate.setValue(null);
                valueTxt.setValue(StringUtils.EMPTY);
                massLookup.setValue(StringUtils.EMPTY);

                if (Constant.GROUPFCAPS.equals(fieldDdlb.getValue())) {
                    massGroup.setVisible(true);
                    loadMassGroup();
                } else if (Constant.PRICE_PROTECTION_STATUS.equals(fieldDdlb.getValue())) {
                    valueDdlb.setVisible(true);
                    CommonUtil.getInstance().loadActiveInactiveIntergerDDLB(valueDdlb, false);
                } else if (Constant.PRICE_PROTECTION_START_DATE.equals(fieldDdlb.getValue())
                        || Constant.PRICE_PROTECTION_END_DATE.equals(fieldDdlb.getValue())
                        || Constant.PPAVariables.BASE_PRICE_DATE.toString().equals(value)
                        || Constant.PPAVariables.RESET_DATE.toString().equals(value)
                        || Constant.PPAVariables.ATTACHED_DATE.toString().equals(value)) {
                    massDate.setVisible(true);
                } else if (Constant.PPAVariables.PRICE_PROTECTION_PRICE_TYPE.toString().equals(fieldDdlb.getValue())
                        || Constant.PPAVariables.BASE_PRICE_PRICE_TYPE.toString().equals(value)
                        || Constant.PPAVariables.RESET_PRICE_TYPE.toString().equals(value)
                        || Constant.PPAVariables.SUBSEQUENT_PERIOD_PRICE_TYPE.toString().equals(value)) {
                    valueDdlb.setVisible(true);

                    CommonUtil.getInstance().loadIntegerComboBox(valueDdlb, PPAServiceSupport.getInstance().getPriceResultList());
                } else if (Constant.PPAVariables.NEP.toString().equals(value)
                        || Constant.PPAVariables.BASE_PRICE_MANUAL.toString().equals(value)
                        || Constant.PPAVariables.PRICE_TOLERANCE.toString().equals(value)
                        || Constant.PPAVariables.MAX_INCREMENTAL_CHANGE.toString().equals(value)) {
                    valueTxt.setVisible(true);

                } else if (Constant.PPAVariables.NEP_FORMULA.toString().equals(value)
                        || Constant.PPAVariables.NET_BASE_PRICE_FORMULA.toString().equals(value)
                        || Constant.PPAVariables.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA.toString().equals(value)
                        || Constant.PPAVariables.NET_RESET_PRICE_FORMULA.toString().equals(value)
                        || Constant.PPAVariables.NET_PRICE_TYPE_FORMULA.toString().equals(value)) {

                    massLookup.setVisible(true);
                } else if (Constant.PPAVariables.PRICE_TOLERANCE_INTERVAL.toString().equals(value)
                        || Constant.PPAVariables.PRICE_TOLERANCE_FREQUENCY.toString().equals(value)
                        || Constant.PPAVariables.PRICE_TOLERANCE_TYPE.toString().equals(value)
                        || Constant.PPAVariables.RESET_TYPE.toString().equals(value)
                        || Constant.PPAVariables.RESET_FREQUENCY.toString().equals(value)
                        || Constant.PPAVariables.RESET_INTERVAL.toString().equals(value)
                        || Constant.PPAVariables.NET_BASE_PRICE.toString().equals(value)
                        || Constant.PPAVariables.NET_SUBSEQUENT_PERIOD_PRICE.toString().equals(value)
                        || Constant.PPAVariables.NET_PRICE_TYPE.toString().equals(value)
                        || Constant.PPAVariables.RESET_ELIGIBLE.toString().equals(value)
                        || Constant.PPAVariables.NET_RESET_PRICE_TYPE.toString().equals(value)
                        || Constant.PPAVariables.BASE_PRICE_TYPE.toString().equals(value)) {
                    valueDdlb.setVisible(true);
                    String listName = helperTableIndentifier.get(value);
                    commonUtil.loadComboBoxWithInteger(valueDdlb, listName, false);
                } else {
                    valueDdlb.setVisible(false);
                    valueTxt.setVisible(false);
                    startPeriod.setVisible(false);
                    endPeriod.setVisible(false);
                    lblEnd.setVisible(false);
                    lblStart.setVisible(false);
                }
            }
        } catch (SystemException | Property.ReadOnlyException | Converter.ConversionException | UnsupportedOperationException e) {
            LOGGER.error(e);
        }
        LOGGER.debug("fieldDdlb value change listener ends");
    }

    @UiHandler("massLookup")
    public void massLookup(TextField.Event event) {
        try {
            LOGGER.debug("inside the Click massLookup listener :");
            if (formulaLookup == null) {
                formulaLookup = new PPAFormulaLookup(value, !Constant.PPAVariables.NEP_FORMULA.getConstant().equalsIgnoreCase(value));
            } else {
                formulaLookup.clearAndRefresh(value, !Constant.PPAVariables.NEP_FORMULA.getConstant().equalsIgnoreCase(value));
            }
            UI.getCurrent().addWindow(formulaLookup);
            formulaLookup.addCloseListener(new Window.CloseListener() {
                @Override
                public void windowClose(Window.CloseEvent e) {
                    if (formulaLookup.isSelected()) {
                        RSFormulaDTO rSFormulaDTO = formulaLookup.getSelectedItem();
                        massLookup.setValue(rSFormulaDTO.getFormulaNo());
                        final Map<String, String> map = new HashMap<>();
                        map.put("formulaNo", rSFormulaDTO.getFormulaNo());
                        map.put("formulaName", rSFormulaDTO.getFormulaName());
                        map.put("formulaID", rSFormulaDTO.getFormulaID());
                        map.put(FORMULA_SYSTEM_SID1, String.valueOf(rSFormulaDTO.getForectastingFormulaSid()));
                        massLookup.setData(map);
                    }
                    formulaLookup.removeCloseListener(this);
                }
            });
        } catch (IllegalArgumentException | NullPointerException ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Mass update.
     *
     * @param event the event
     */
    @UiHandler("massUpdate")
    public void massUpdate(Property.ValueChangeEvent event) {
        LOGGER.debug("massupdate click listener starts");
        if ((Constant.LabelConstants.DISABLE).equals(massUpdate.getValue())) {

            fieldDdlb.setEnabled(Boolean.FALSE);
            valueDdlb.setEnabled(Boolean.FALSE);
            startPeriod.setEnabled(Boolean.FALSE);
            endPeriod.setEnabled(Boolean.FALSE);
            populate.setEnabled(Boolean.FALSE);
            valueTxt.setEnabled(Boolean.FALSE);

        } else {
            fieldDdlb.setEnabled(Boolean.TRUE);
            fieldDdlb.setEnabled(Boolean.TRUE);
            valueDdlb.setEnabled(Boolean.TRUE);
            startPeriod.setEnabled(Boolean.TRUE);
            endPeriod.setEnabled(Boolean.TRUE);
            populate.setEnabled(Boolean.TRUE);
            valueTxt.setEnabled(Boolean.TRUE);
        }
        LOGGER.debug("massupdate click listener ends");
    }

    @UiHandler("startPeriod")
    public void startPeriod(Property.ValueChangeEvent event) {
        if (endPeriod.getValue() != null && startPeriod.getValue() != null) {
            List<String> list = ridhtdto.getDoubleHeaders();
            if (list.indexOf(endPeriod.getValue().toString()) < list.indexOf(startPeriod.getValue().toString())) {
                NotificationUtils.getAlertNotification("ERROR", alertMsg.getString("PPA_MSG_ID_07"));
            }
        }
    }

    @UiHandler("endPeriod")
    public void endPeriod(Property.ValueChangeEvent event) {
        if (endPeriod.getValue() != null && startPeriod.getValue() != null) {
            List<String> list = ridhtdto.getDoubleHeaders();
            if (list.indexOf(endPeriod.getValue().toString()) < list.indexOf(startPeriod.getValue().toString())) {
                NotificationUtils.getAlertNotification("ERROR", alertMsg.getString("PPA_MSG_ID_07"));
            }
        }
    }

    /**
     * Change level.
     *
     * @param property the property
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void loadResults(Object filterValue) {
        LOGGER.debug("loadResults initiated ");
        selection.setHierarchyNo(StringUtils.EMPTY);
        if (!selection.getPeriodList().isEmpty()) {
            selection.setFromDateDdlb(CommonLogic.getPeriodSID(Constant.QUARTERLY, selection.getPeriodListMap().get(selection.getPeriodList().get(0)), true));
            selection.setToDateDdlb(CommonLogic.getPeriodSID(Constant.QUARTERLY, selection.getPeriodListMap().get(selection.getPeriodList().get(selection.getPeriodList().size() - 1)), true));
        }
        loadMassPeriods(selection);
        if (filterValue != null) {
            String filteredValue = filterValue.toString();
            selection.setLevelNo(Integer.valueOf(filteredValue.split("~")[0]));
            selection.setIsFilter(Boolean.TRUE);
            tableLogic.setSelection(selection);
            resetTable();
            selection.setIsFilter(Boolean.FALSE);
        } else {
            selection.setIsFilter(Boolean.FALSE);
            tableLogic.setSelection(selection);
            resetTable();
        }
        LOGGER.debug("loadResults Ended ");
    }

    private void resetTable() {
        tableLogic.setProjectionResultsData(true);
        tableLogic.clearAll();
        tableLogic.setCurrentPage(1);
    }

    /**
     * Excel export logic.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void excelExportLogic()   {
        excelTable = new ExtCustomTreeTable();
        selection.setExcelExport(Boolean.TRUE);
        excelContainer = new ExtTreeContainer<>(PPAProjectionDTO.class, ExtContainer.DataStructureMode.LIST);
        excelContainer.setColumnProperties(fullHeader.getProperties());
        excelContainer.setRecordHeader(fullHeader.getSingleColumns());
        excelTable = new ExtCustomTreeTable();
        tableLayout.addComponent(excelTable);
        excelTable.setRefresh(Boolean.FALSE);
        excelTable.setVisible(Boolean.FALSE);
        excelTable.setContainerDataSource(excelContainer);
        generateButtonlogicForExcel();
        int variableSize=selection.getPpaSelectedVariables().size();
        Object[] leftColumns=new Object[NumericConstants.FIVE+(NumericConstants.FOUR*variableSize)];
        String[] leftHeaders=new String[NumericConstants.FIVE+(NumericConstants.FOUR*variableSize)];
        System.arraycopy(fullHeader.getSingleColumns().toArray(), 0, leftColumns, 0, NumericConstants.FIVE);
        ExcelExport export = null;
        int sheetCount=0;
        for (int i = NumericConstants.FIVE; i < fullHeader.getSingleColumns().toArray().length; i += (NumericConstants.FOUR*variableSize)) {
            System.arraycopy(fullHeader.getSingleColumns().toArray(), i, leftColumns, NumericConstants.FIVE,NumericConstants.FOUR*variableSize);
            System.arraycopy(fullHeader.getSingleHeaders().toArray(), i, leftHeaders, NumericConstants.FIVE, NumericConstants.FOUR*variableSize);
            excelTable.setVisibleColumns(leftColumns);
            excelTable.setColumnHeaders(leftHeaders);
        excelTable.setRefresh(true);
            String sheetName="PPA Projection year "+rightTable.getDoubleHeaderColumnHeaders()[sheetCount].replace("Q1", "");
           ForecastUI.setEXCEL_CLOSE(true);
            if (i == NumericConstants.FIVE) {
                export = new ExcelExport(new ExtCustomTableHolder(excelTable), sheetName,"PPA Projection", "PPA_Projection.xls", false);
            }else{
            export.setNextTableHolder(new ExtCustomTableHolder(excelTable),sheetName);
            }
            sheetCount+=NumericConstants.FOUR;
        export.export();
        }
        tableLayout.removeComponent(excelTable);
    }

    public void generateButtonlogicForExcel()  {
        int count = 0;
        excelContainer.removeAllItems();
        if (levelFilter.getValue() == null) {
            selection.setLevelNo(selection.getCustomerLevelNo());
            selection.setHierarchyNo(null);
            count = NumericConstants.ONE_CRORE;
        } else {
            selection.setIsFilter(Boolean.TRUE);
            selection.setLevelNo(Integer.valueOf(levelFilter.getValue().toString().split("~")[0]));
            selection.setTreeLevelNo(Integer.valueOf(session.getCustomerLevelNumber()));
            count = CommonLogic.getLevelListCount(projectionId, Constant.PPA, Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY, selection.getLevelNo(), StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, Boolean.TRUE, Boolean.FALSE, 0, selection.getGroupFilter(), selection.getUserId(), selection.getSessionId(), selection.getCustRelationshipBuilderSid(), selection.getProdRelationshipBuilderSid(), selection.getDiscountNoList(),selection);
        }
        selection.setExcelExport(Boolean.TRUE);
        List list = (List) logic.getPPAProjectionResults(selection, ridhtdto, 0, count);
        loadDataToContainer(list, null, !selection.isIsFilter());
        selection.setExcelExport(Boolean.FALSE);
        selection.setIsFilter(Boolean.FALSE);
    }

    public void loadDataToContainer(List<PPAProjectionDTO> resultList, Object parentId, boolean isRecursive) {
        for (PPAProjectionDTO dto : resultList) {
            excelContainer.addBean(dto);
            if (parentId != null) {
                excelContainer.setParent(dto, parentId);
            }
            if (dto.getLevelNo() != null && dto.getLevelNo() != 0) {
                excelContainer.setChildrenAllowed(dto, true);
                if (isRecursive) {
                    addLowerLevelsForExport(dto);
                }
            } else {
                excelContainer.setChildrenAllowed(dto, false);
            }
        }
    }

    public void addLowerLevelsForExport(PPAProjectionDTO dto) {
        try {

            selection.setIsProjectionTotal(false);
            selection.setLevelNo(dto.getLevelNo() + 1);
            selection.setHierarchyNo(dto.getHirarechyNo());
            List<PPAProjectionDTO> levelList = (List<PPAProjectionDTO>) logic.getPPAProjectionResults(selection, ridhtdto, 0, NumericConstants.TEN_LAKH);
            loadDataToContainer(levelList, dto, true);
            excelTable.setCollapsed(dto, false);
        } catch (Exception ex) {

            LOGGER.error(ex);
        }
    }

    private void loadPeriods(ProjectionSelectionDTO projSelDTO) {
        dateList.clear();
        fromDateDdlb.removeAllItems();
        toDateDdlb.removeAllItems();
        for (String key : projSelDTO.getPeriodList()) {
            String val = selection.getPeriodListMap().get(key);
            fromDateDdlb.addItem(val);
            toDateDdlb.addItem(val);
            dateList.add(val);
        }
        fromDateDdlb.setValue(dateList.get(0));
        toDateDdlb.setValue(dateList.get(dateList.size() - 1));
    }

    private void loadMassPeriods(ProjectionSelectionDTO projSelDTO) {
        startPeriod.removeAllItems();
        endPeriod.removeAllItems();
        for (String key : projSelDTO.getPeriodList()) {
            String val = selection.getPeriodListMap().get(key);
            startPeriod.addItem(val);
            endPeriod.addItem(val);
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        return;
    }

    private void configureTable(boolean load)  {
        LOGGER.debug("PPA configureTable");
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultsTable.setFilterBarVisible(Boolean.FALSE);
        excelExport.setIcon(excelExportImage);

        if (projectionPeriodOrderOpg.getValue().toString().equals(Constant.ASCENDING)) {
            selection.setProjectionOrder(Constant.ASCENDING);
        } else {
            selection.setProjectionOrder(Constant.DESCENDING);
        }
        fullHeader.getSingleColumns().clear();
        fullHeader.getSingleHeaders().clear(); 
        fullHeader.getDoubleHeaders().clear();
        fullHeader.getDoubleColumns().clear();
        leftdto = HeaderUtils.getPPAProjectionLeftTableColumns(fullHeader);

        if (generateFlag) {
            ridhtdto = HeaderUtils.getPPAOnLoadRightColumns(fullHeader);
        } else {
            ridhtdto = HeaderUtils.getPPAProjectionRightTableColumns(selection, fullHeader);
        }

        selection.setToDateDdlb(CommonLogic.getPeriodSID(Constant.QUARTERLY, String.valueOf(toDate), true));
        rightTable.setSortEnabled(false);
        leftTable.setSortEnabled(false);
        tableLogic.setRightDto(ridhtdto);
        tableLogic.setSelection(selection);
        tableLogic.setSession(session);
        tableLogic.setTreeNodeMultiClick(false);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultBeanContainer = new ExtTreeContainer<>(PPAProjectionDTO.class, ExtContainer.DataStructureMode.LIST);
        tableLogic.setContainerDataSource(resultBeanContainer);
        resultBeanContainer.setColumnProperties(leftdto.getProperties());
        resultBeanContainer.setColumnProperties(ridhtdto.getProperties());
        List<Object> recordHeader = new ArrayList<>();
        if (!generateFlag) {
            recordHeader = new ArrayList<>(ridhtdto.getSingleColumns());
        }
        recordHeader.add(0, Constant.CHECK_RECORD + ".0");
        resultBeanContainer.setRecordHeader(recordHeader);
        loadLevelFilterValue();
        leftTable.setVisibleColumns(leftdto.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftdto.getSingleHeaders().toArray(new String[leftdto.getSingleHeaders().size()]));
        rightTable.setVisibleColumns(ridhtdto.getSingleColumns().toArray());
        rightTable.setColumnHeaders(ridhtdto.getSingleHeaders().toArray(new String[ridhtdto.getSingleHeaders().size()]));
        rightTable.setColumnCollapsingAllowed(true);
        mapLeftVisibleColumns = leftdto.getDoubleHeaderMaps();
        alignCentre();

        resultsTable.setDoubleHeaderVisible(true);
        resultsTable.setHeight(Constant.SIX_FIFTY_PX);
        leftTable.setHeight(Constant.SIX_FIFTY_PX);
        rightTable.setHeight(Constant.SIX_FIFTY_PX);
        leftTable
                .setDoubleHeaderVisibleColumns(leftdto.getDoubleColumns().toArray());
        leftTable
                .setDoubleHeaderColumnHeaders(leftdto.getDoubleHeaders().toArray(new String[leftdto.getDoubleHeaders().size()]));
        rightTable.setDoubleHeaderVisibleColumns(ridhtdto.getDoubleColumns().toArray());
        rightTable.setDoubleHeaderColumnHeaders(ridhtdto.getDoubleHeaders().toArray(new String[ridhtdto.getDoubleHeaders().size()]));
        mapRightVisibleColumns = ridhtdto.getDoubleHeaderMaps();
        rightTable.reConstruct(Boolean.TRUE);
        resultsTable.setDoubleHeaderMap(mapLeftVisibleColumns, mapRightVisibleColumns);
        leftTable.setColumnCheckBox(Constant.CHECK_RECORD + ".0", true);
        LOGGER.debug("!\"view\".equals(session.getAction()) ================= " + !Constant.VIEW.equals(session.getAction()));
        leftTable.setColumnCheckBoxDisable(Constant.CHECK_RECORD + ".0", Constant.VIEW.equals(session.getAction()));
        UiUtils.setExtFilterTreeTableColumnWidth(rightTable, NumericConstants.ONE_FOUR_FIVE, TAB_PPA_PROJECTION.getConstant());
        if (leftdto.getDoubleColumns() != null) {
            leftTable.setDoubleHeaderVisible(false);
            leftTable.setFilterBarVisible(true);
            leftTable.setDoubleHeaderVisibleColumns(leftdto.getDoubleColumns().toArray());
            leftTable.setDoubleHeaderColumnHeaders(leftdto.getDoubleHeaders().toArray(new String[leftdto.getDoubleHeaders().size()]));
        }

        if (!generateFlag) {
            generateTable();
        }
        if (load) {
            loadResults(null);
        }
        generateFlag = false;
        rightTable.setColumnWidth("defaultColumn", NumericConstants.THOUSAND_THREE_HUNDRED);
    }

    private void alignCentre() {
        for (Object obj : ridhtdto.getSingleColumns()) {
            rightTable.setColumnAlignment(obj, ExtCustomTable.Align.CENTER);
        }

    }

    int getQuarterStartMonth(int quarter) {
        return NumericConstants.THREE * (quarter - 1) + 1;
    }

    int getQuarterEndMonth(int quarter) {
        return NumericConstants.THREE * quarter;
    }

    private ForecastDTO getClonedForecastDTO(ForecastDTO forcastDTO, boolean initial) {
        ForecastDTO dto = new ForecastDTO();
        int startmonth = forcastDTO.getProjectionStartMonth();
        int startyear = forcastDTO.getProjectionStartYear();
        int endmonth = forcastDTO.getProjectionEndMonth();
        int endyear = forcastDTO.getProjectionEndYear();
        if (!initial) {
            String frd = StringUtils.EMPTY;
            if (fromDate != null) {
                frd = fromDate.toString().replace(" ", StringUtils.EMPTY).toLowerCase().trim();
            }
                if ((fromDateDdlb != null) && (fromDateDdlb.getValue() != null)) {
                    frd = fromDateDdlb.getValue().toString().replace(" ", StringUtils.EMPTY).toLowerCase().trim();
                }
            String trd = StringUtils.EMPTY;
            if (toDate != null) {
                trd = toDate.toString().replace(" ", StringUtils.EMPTY).toLowerCase().trim();
            }
                if ((toDateDdlb != null) && (toDateDdlb.getValue() != null)) {
                    trd = toDateDdlb.getValue().toString().replace(" ", StringUtils.EMPTY).toLowerCase().trim();
                }
            if (!frd.isEmpty()) {
                startmonth = getQuarterStartMonth(Integer.valueOf(frd.substring(1, frd.length() - NumericConstants.FOUR)));
                startyear = Integer.valueOf(frd.substring(frd.length() - NumericConstants.FOUR));
            }
            if (!trd.isEmpty()) {
                endmonth = getQuarterEndMonth(Integer.valueOf(trd.substring(1, trd.length() - NumericConstants.FOUR)));
                endyear = Integer.valueOf(trd.substring(trd.length() - NumericConstants.FOUR));
            }
        }

        dto.setForecastEndDate((Date) forcastDTO.getForecastEndDate().clone());
        dto.setForecastStartDate((Date) forcastDTO.getForecastStartDate().clone());
        dto.setProjectionStartDate((Date) forcastDTO.getProjectionStartDate().clone());
        dto.setProjectionEndDate((Date) forcastDTO.getProjectionEndDate().clone());
        dto.setHistoryStartDate((Date) forcastDTO.getHistoryStartDate().clone());
        dto.setHistoryEndDate((Date) forcastDTO.getHistoryEndDate().clone());
        dto.setProjectionStartYear(startyear);
        dto.setProjectionStartMonth(startmonth);
        dto.setProjectionEndYear(endyear);
        dto.setProjectionEndMonth(endmonth);
        dto.setForecastStartYear(startyear);
        dto.setForecastStartMonth(startmonth);
        dto.setForecastEndYear(endyear);
        dto.setForecastEndMonth(endmonth);
        dto.setHistoryStartYear(startyear);
        dto.setHistoryStartMonth(startmonth);
        dto.setHistoryEndYear(startyear);
        dto.setHistoryEndMonth(startmonth);
        return dto;
    }

    private void loadProjectionSelection(boolean initial) {
        LOGGER.debug("Enter inside load projection selection");
        selection.setSessionDTO(session);
        selection.setCustomerLevelNo(Integer.valueOf(session.getCustomerLevelNumber()));
        selection.setCustRelationshipBuilderSid(session.getCustRelationshipBuilderSid());
        selection.setRelationshipBuilderSid(selection.getCustRelationshipBuilderSid());
        selection.setHierarchyIndicator(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        selection.setLevelNo(Integer.valueOf(session.getCustomerLevelNumber()));
        selection.setCustomerHierarchyNo(StringUtils.EMPTY);
        selection.setProductHierarchyNo(StringUtils.EMPTY);
        selection.setHierarchyIndicator(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        selection.setIsCustomHierarchy(Boolean.FALSE);
        selection.setCustomId(0);
        selection.setProjectionId(projectionId);
        selection.setSessionId(Integer.valueOf(session.getSessionId()));
        selection.setUserId(Integer.valueOf(session.getUserId()));
        selection.setForecastDTO(getClonedForecastDTO(session.getForecastDTO(), initial));
        LOGGER.debug("END of load projection selection");

    }

    public void loadLevelFilterValue()  {
        valueChangeForColumnCheckBox = Boolean.TRUE;
        LOGGER.debug("loadLevelFilterValue initiated ");
        level.setEnabled(true);
        level.removeAllItems();
        level.addItem(SELECT_ONE);
        level.setNullSelectionItemId(SELECT_ONE);
        level.setValue(SELECT_ONE);
        levelFilter.setEnabled(true);
        levelFilter.removeAllItems();
        levelFilter.addItem(SELECT_ONE);
        levelFilter.setNullSelectionItemId(SELECT_ONE);
        levelFilter.setValue(SELECT_ONE);

        List<Leveldto> hierarchy = null;
        hierarchy = CommonLogic.getCustomerHierarchy(projectionId, selection.getCustomerLevelNo());
        if (hierarchy != null) {
            int endLevelNo = hierarchy.get(hierarchy.size() - 1).getLevelNo();
            for (Leveldto levelDto : hierarchy) {
                String levelFiterSid = levelDto.getTreeLevelNo() + "~" + levelDto.getHierarchyIndicator();
                String caption = Constant.LEVEL+ levelDto.getTreeLevelNo() + " - " + levelDto.getLevel();
                Object itemId = levelFiterSid;
                if (endLevelNo != levelDto.getTreeLevelNo()) {
                    level.addItem(itemId);
                    level.setItemCaption(itemId, caption);
                }
                levelFilter.addItem(itemId);
                levelFilter.setItemCaption(itemId, caption);
            }
        }

        valueChangeForColumnCheckBox = Boolean.FALSE;
        LOGGER.debug("loadLevelFilterValue ends ");
    }

    public void loadGroupFilter() {
        LOGGER.debug("loadGroupFilter initiated ");
        waitForSave();
        groupLabel.setVisible(false);
        groupFilterDdlb.removeAllItems();

        List<String> groupList = CommonLogic.getAllPPAGroup();
        LOGGER.debug("groupList--:" + groupList);
        if (groupList != null && !groupList.isEmpty()) {
            groupContainer.removeAllItems();
            for (String groups : groupList) {

                groupContainer.addItem(groups.replace(Constant.PPA, StringUtils.EMPTY));
            }
            groupFilterDdlb.setContainerDataSource(groupContainer);
            groupFilterDdlb.select(Constant.ALL_GROUP);
            selection.setGroupFilter(Constant.ALL_GROUP);
        }

        LOGGER.debug("loadGroupFilter ends ");
    }

    public void groupChange() {
        waitForSave();
        LOGGER.debug("groupChange initiated ");
        try {
            if (groupFilterDdlb.getValue() != null) {
                selection.setGroupFilter(String.valueOf(Constant.PPA + groupFilterDdlb.getValue()));

            }
            levelFilter.select(SELECT_ONE);

            loadResults(null);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("groupChange Ended ");
    }

    public int getTabNumber() {
        return Constant.FIVE;
    }

    public boolean getSubmitFlag() {
        if (resultBeanContainer.getItemIds().size() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public void savePPAProjection(final String propertyId, final Object valueOf, final String hirarechyNo, String table) {
        LOGGER.debug("Inside savePPAProjection Method");
        String group = String.valueOf(groupFilterDdlb.getValue() == null ? Constant.PERCENT : groupFilterDdlb.getValue()).replace(Constant.PPA, StringUtils.EMPTY);
        RunnableJob runnableJob = new RunnableJob(propertyId, valueOf, hirarechyNo, group, projectionId, session, selection, "savePPAProjection", table);
        Thread t = new Thread(runnableJob);
        manualSaveRunnableThreads.add(t);
        t.start();
        LOGGER.debug("End of savePPAProjection Method");
    }

    public void pushUpdate(String indicator) {
        if (Constants.IndicatorConstants.INDICATOR_REFRESH_UPDATE.getConstant().equals(indicator)) {
            try {
                loadProjectionSelection(false);
                loadLevelFilterValue();
                loadResults(null);
            }  catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        if (Constants.IndicatorConstants.INDICATOR_TIME_PERIOD_CHANGED.getConstant().equals(indicator)) {

        }
    }

    public static void waitForSave() {
        LOGGER.debug("Inside wait For save Method");

        for (Thread t : manualSaveRunnableThreads) {
            if (t.isAlive()) {
                try {

                    synchronized (t) {
                        t.wait();
                    }
                } catch (InterruptedException e) {
                    LOGGER.error(e);
                }
            }
        }
        manualSaveRunnableThreads.clear();

        LOGGER.debug("End of wait For save Method");
    }


    @UiHandler("generateBtn")
    public void generateButton(Button.ClickEvent event) {
        LOGGER.debug("Entering generateButton");
        try {
            generateButtonLogic();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending generateButton");
    }

    public void generateButtonLogic()   {

        PPAProjection.valueChangeAllowed = Boolean.FALSE;
        List<String> result = getCheckedVariables();
        if (result.isEmpty()) {
            MessageBox.showPlain(Icon.INFO, Constant.ERROR, alertMsg.getString("PPA_MSG_ID_08"), ButtonId.OK);
            return;
        }
        fieldDdlb.removeAllItems();
        fieldDdlb.addItems(DEFAULT_VARIABLE_VALUES);
        fieldDdlb.addItems(result);
        tableLayout.removeAllComponents();
        tableLogic = new PPAProjectionTableLogic(this);
        resultsTable = new FreezePagedTreeTable(tableLogic);
        initProduct();

        loadProjectionSelection(false);
        configureTable(true);
        addResultTable();

        PPAProjection.valueChangeAllowed = Boolean.TRUE;
    }

    @UiHandler("resetBtn")
    public void resetButton(Button.ClickEvent event)  {
        LOGGER.debug("Entering Reset Button");
        resetButtonLogic();
        LOGGER.debug("Ending Reset Button");
    }

    private void resetButtonLogic()  {
        fromDateDdlb.setValue(dateList.get(0));
        toDateDdlb.setValue(dateList.get(dateList.size() - 1));
        projectionPeriodOrderOpg.setValue(Constant.ASCENDING);
    }

    public void security() throws PortalException, SystemException {

        final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermissionForNm(String.valueOf(VaadinSession.getCurrent().getAttribute("businessRoleIds")), getCommercialConstant() + "," + UISecurityUtil.PPA_PROJECTION);

        if (!(functionPsHM.get(FunctionNameUtil.GENERATE) != null && ((AppPermission) functionPsHM.get(FunctionNameUtil.GENERATE)).isFunctionFlag())) {

            generateBtn.setVisible(Boolean.FALSE);
            collapseBtn.setVisible(Boolean.FALSE);
            expandBtn.setVisible(Boolean.FALSE);
            populate.setVisible(Boolean.FALSE);

        }
    }

    private void generateTable() {
        int size = selection.getPpaSelectedVariables().size();
        if (size > NumericConstants.THREE && !generateFlag) {
            for (Object object : rightTable.getDoubleHeaderVisibleColumns()) {
                rightTable.setDoubleHeaderColumnExpandIcon(object, false);
                expandCollapseCol(object, true);
            }
            rightTable.addDoubleHeaderColumnExpandIconListener(new ExtCustomTable.DoubleHeaderColumnExpandIconListener() {
                @Override
                public void doubleHeaderColumnExpandIcon(ExtCustomTable.DoubleHeaderColumnExpandIconEvent event) {
                    int width = expandCollapseCol(event.getPropertyId(), !event.isExpanded());
                    if (!event.isExpanded()) {
                        rightTable.setDoubleHeaderColumnWidth(event.getPropertyId(), width);
                    }
                }
            });
        }
    }

    private int expandCollapseCol(Object doubleHeaderColumn, boolean collapsed) {
        int size = selection.getPpaSelectedVariables().size();
        String arr[] = rightTable.getHeaderMapFromDoubleHeader(doubleHeaderColumn.toString());
        int width = 0;
        if (collapsed) {
            for (int i = 0; i < NumericConstants.THREE && i < size; i++) {
                width += rightTable.getColumnWidth(arr[i]);
            }
        }
        for (int i = NumericConstants.THREE; i < size; i++) {
            rightTable.setColumnCollapsed(arr[i], collapsed);
        }
        return width;
    }

    private void massUpdatePPAProjection(Object value, String fieldValue, String columnName, int startQuater, int endQuater, int startYear, int endYear, ProjectionSelectionDTO selection) {

        waitForSave();
        PPAProjection.valueChangeAllowed = Boolean.FALSE;

        List input = null;
        if (populateIdentifier.get(Constant.FROZEN_FIELDS).contains(fieldValue)) {
            input = logic.getInputForMassUpdateGroup(value, columnName, selection);
            PPAQuerys.ppaUpdate(input, "PPA.MAssUpdate-Group");
        } else {
            input = logic.getInputForMassUpdate(startQuater, endQuater, startYear, endYear, value, columnName, selection);
            if (columnName.equals("NEP") || columnName.equals("PRICE_TOLERANCE") || columnName.equals("MAX_INCREMENTAL_CHANGE")) {
                PPAQuerys.ppaUpdate(input, "PPA.MAssUpdate-Price");
            } else {
                PPAQuerys.ppaUpdate(input, "PPA.MAssUpdate-PriceCap");
            }
        }

        Set<String> hierarchies = new HashSet<>();
        /**
         * if the checkedAllRecords == true then refreshing all the data in
         * result table
         */
        if (checkedAllRecords) {
            hierarchies.addAll(tableLogic.getAllLevels());
        }
        /**
         * if the checkedAllRecords == false then removing the unchecked records
         * and refreshing the table
         */
        if (!checkedAllRecords) {
            hierarchies.addAll(tableLogic.getAllLevels());
            hierarchies.removeAll(UNCHECKED_RECORDS_SET);
        }
        /**
         * getting the hierarchy numbers which is TRADING_PARTNER alone
         */
        if (Constant.GROUPFCAPS.equals(fieldValue)) {
            Set<String> tempHierarchies = new HashSet<>(hierarchies);

            for (String hierarchyNo : tempHierarchies) {
                Object tempId = tableLogic.getcurrentTreeData(hierarchyNo);
                if (tempId == null) {
                    tempId = tableLogic.getExpandedTreeValues(hierarchyNo);
                }
                    if ((tempId != null) && (tempId instanceof PPAProjectionDTO)) {
                        PPAProjectionDTO dto = (PPAProjectionDTO) tempId;
                        if (!Constant.TRADING_PARTNER.equals(dto.getHirarechyName())) {

                            hierarchies.remove(hierarchyNo);
                        }
                    }
            }
        }

        if (!hierarchies.isEmpty()) {
            tableLogic.forRefresh(hierarchies);
            tableLogic.setCurrentPage(tableLogic.getCurrentPage());
        }

        PPAProjection.valueChangeAllowed = Boolean.TRUE;

    }

    public static void setValueChangeAllowed(boolean valueChangeAllowed) {
        PPAProjection.valueChangeAllowed = valueChangeAllowed;
    }

    private void updateUncheckedRecords(boolean value, Object item) {
        List<String> childs = tableLogic.getAllChildLevels(item);
        List<String> roots = tableLogic.getAllParentLevels(item);
        String currentItem = tableLogic.getTreeLevelonCurrentPage(item);
        if (!value) {
            if (currentItem != null) {
                UNCHECKED_RECORDS_SET.add(currentItem);
            }
            UNCHECKED_RECORDS_SET.addAll(roots);
            UNCHECKED_RECORDS_SET.addAll(childs);
        }
        if (value) {
            if (currentItem != null) {
                UNCHECKED_RECORDS_SET.remove(currentItem);
            }
            UNCHECKED_RECORDS_SET.removeAll(roots);
            UNCHECKED_RECORDS_SET.removeAll(childs);
        }
    }

    protected void addResultTable() {
        tableLayout.addComponent(resultsTable);
        tableLogic.setPageLength(NumericConstants.TWENTY);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        tableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        HorizontalLayout controls = resultsTable.createControls();
        HorizontalLayout cssControls = CommonLogic.getResponsiveControls(controls);
        cssControls.addStyleName(Constant.RESPONSIVE_PAGED_TABLE);
        cssControls.addComponent(refresh);
        tableLayout.addComponent(cssControls);
    }

    private void loadPPASelectedList(List<String> result) {
        List<String> ppaSelectedColumn = new ArrayList<>();
        if (!result.isEmpty()) {
            for (String string : result) {
                for (String keyset : Constant.getColumnHeaderMap().keySet()) {
                    if (Constant.getColumnHeaderMap().get(keyset).equals(string)) {
                        ppaSelectedColumn.add(keyset);
                    }
                }
            }
            selection.setPpaSelectedVariables(ppaSelectedColumn);
        } else {
            selection.setPpaSelectedVariables(Arrays.asList(Constant.PPAColumns.names()));
        }
    }

    public void OnLoadGroupFilter() {

        leftTable.setFilterGenerator(new ExtFilterGenerator() {
            @Override
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            @Override
            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                return null;
            }

            @Override
            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                if (Constant.GROUP.equals(propertyId)) {
                    return groupFilterDdlb;
                } else if (Constant.PRICEPROTECTIONSTATUS.equals(propertyId)) {
                    TextField metohdologyFilter = new TextField();
                    metohdologyFilter.setReadOnly(true);
                    metohdologyFilter.setWidth("100%");
                    return metohdologyFilter;
                } else if (Constant.PRICEPROTECTIONSTARTDATE.equals(propertyId)) {
                    TextField baseLineFilter = new TextField();
                    baseLineFilter.setReadOnly(true);
                    baseLineFilter.setWidth("100%");
                    return baseLineFilter;
                } else if (Constant.PRICEPROTECTIONENDDATE.equals(propertyId)) {
                    TextField levelField = new TextField();
                    levelField.setReadOnly(true);
                    levelField.setWidth("100%");
                    return levelField;
                } else if (Constant.LEVELNAME.equals(propertyId)) {
                    TextField checkField = new TextField();
                    checkField.setWidth("100%");
                    checkField.setReadOnly(true);
                    return checkField;
                } else if ((Constant.CHECK_RECORD + ".0").equals(propertyId)) {
                    TextField checkField = new TextField();
                    checkField.setWidth("100%");
                    checkField.setReadOnly(true);
                    return checkField;
                }
                return null;
            }

            @Override
            public void filterRemoved(Object propertyId) {
                return;
            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                return;
            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }
        });
    }

    public void setValueChangeForColumnCheckBox(boolean valueChangeForColumnCheckBox) {
        this.valueChangeForColumnCheckBox = valueChangeForColumnCheckBox;
    }

    public void setLevelFilterValue(Object levelFilterValue) {
        this.levelFilter.setValue(levelFilterValue);
    }

    public boolean isGroupChangeFlag() {
        return groupChangeFlag;
    }

    public void savePPAProjectionSelection() throws PortalException, SystemException  {
        LOGGER.debug("save PPA Projection  method starts");
        String builder = StringUtils.EMPTY;
        if (selection.getPpaSelectedVariables() != null) {
            if (selection.getPpaSelectedVariables().size() == NumericConstants.TWENTY_EIGHT) {
                builder = Constant.PPAVariables.CHECK_ALL.getConstant();
            } else {
                builder = selection.getPpaSelectedVariables().toString().replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY);
            }
        }
        if (builder.length() > 0) {
            Map map = new HashMap();
            map.put(Constant.VARIABLE, builder);
            map.put(Constant.PPA_FROM_DATE, fromDateDdlb.getValue());
            map.put(Constant.PPA_TO_DATE, toDateDdlb.getValue());
            CommonLogic.saveProjectionSelection(map, TAB_PPA_PROJECTION.getConstant(), selection);

        }
        LOGGER.debug("Ennd ssavePPAProjectionSelection  method :");
    }

    /**
     * loadMassGroup
     */
    private void loadMassGroup() {
        massGroup.setContainerDataSource(null);
        IndexedContainer massGroupContainer = new IndexedContainer();
        List<String> groupList = CommonLogic.getAllPPAGroup();
        LOGGER.debug("groupList--:" + groupList);
        if (groupList != null && !groupList.isEmpty()) {
            massGroupContainer.removeAllItems();
            for (String groups : groupList) {
                massGroupContainer.addItem(groups.replace(Constant.PPA, StringUtils.EMPTY));
                massGroupContainer.addItem(Constant.SELECT_ONE);
                massGroupContainer.removeItem(Constant.ALL_GROUP);
            }
            massGroup.setContainerDataSource(massGroupContainer);
            massGroup.select(Constant.SELECT_ONE);
            massGroup.setNullSelectionItemId(Constant.SELECT_ONE);
            massGroup.setTextInputAllowed(true);
            massGroup.setNewItemsAllowed(Boolean.TRUE);
        }
    }
}
