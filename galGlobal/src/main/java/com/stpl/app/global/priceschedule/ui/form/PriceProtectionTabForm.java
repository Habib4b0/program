/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.priceschedule.ui.form;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.ui.lookup.FormulaLookup;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.ifp.ui.lazyload.TempItemCriteria;
import com.stpl.app.global.item.util.CommonUtils;
import com.stpl.app.global.priceschedule.dto.PSDTO;
import com.stpl.app.global.priceschedule.dto.PSFilterGenerator;
import com.stpl.app.global.priceschedule.dto.PSIFPDTO;
import com.stpl.app.global.priceschedule.dto.PSNepFormulaDTO;
import com.stpl.app.global.priceschedule.dto.PSTableGenerator;
import com.stpl.app.global.priceschedule.logic.PSLogic;
import static com.stpl.app.global.priceschedule.logic.PSLogic.getCustomizedItemPriceDTO;
import com.stpl.app.global.priceschedule.logic.PSPricingTableLogic;
import com.stpl.app.global.priceschedule.ui.lazyload.PriceTypeCriteria;
import com.stpl.app.global.priceschedule.ui.lazyload.PriceTypeLazyContainer;
import com.stpl.app.global.priceschedule.util.PsUtils;
import com.stpl.app.global.priceschedule.util.UIUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.FunctionNameUtil;
import com.stpl.app.util.ResponsiveUtils;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.app.util.ValidationUtils;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class PriceProtectionTabForm.
 *
 * @author pvinoth
 */
public class PriceProtectionTabForm extends CustomComponent implements View {

    /**
     * The mass check.
     */
    @UiField("massCheck")
    private OptionGroup massCheck;

    @UiField("cssLayout")
    private CssLayout cssLayout;

    /**
     * The mass field.
     */
    @UiField("massField")
    private ComboBox massField;

    /**
     * The mass date.
     */
    @UiField("massDate")
    private PopupDateField priceProtectionMassDate;

    /**
     * The mass value.
     */
    @UiField("massValue")
    private TextField priceProtectionMassValue;

    /**
     * The mass select.
     */
    @UiField("massSelect")
    private ComboBox massSelect;

    /**
     * The btn populate.
     */
    @UiField("btnPopulate")
    private Button btnPopulate;
    /**
     * The btn all populate.
     */
    @UiField("btnAllPopulate")
    private Button btnAllPopulate;

    @UiField("root")
    private VerticalLayout root;

    /**
     * The control layout.
     */
    @UiField("controlLayout")
    private HorizontalLayout controlLayout;

    /**
     * The massLookup.
     */
    @UiField("massLookup")
    private CustomTextField massLookup;

    /**
     *
     */
    @UiField("massLayout")
    private HorizontalLayout massLayout;
        /**
     * The Base Price Type DDLB.
     */
    @UiField("basePriceType")
    private ComboBox basePriceType;
   
    @UiField("basePriceTypeLB")
    private Label basePriceTypeLB;

    /**
     * The selected item result bean.
     */
    private final BeanItemContainer<PSIFPDTO> selectedItemResultBean;

    /**
     * The temp item criteria.
     */
    private final TempItemCriteria tempItemCriteria;

    /**
     * the used id.
     */
    private final String userId;

    /**
     * the session id.
     */
    private final String sessionId;

    /**
     * the temp table records created date.
     */
    private final String tempCreatedDate;

    /**
     * The binder.
     */
    private final ErrorfulFieldGroup binder;

    /**
     * The item details result bean.
     */
    private final BeanItemContainer<PSIFPDTO> itemDetailsResultBean;

    /**
     * The is view mode.
     */
    private final boolean isViewMode;

    /**
     * The mode.
     */
    private final String mode;

    /**
     * The Constant logger.
     */
    private static final Logger logger = Logger.getLogger(PriceProtectionTabForm.class.getName());

    /**
     * The ps logic.
     */
    private PSLogic psLogic;

    private final IFPLogic ifpLogic = new IFPLogic();

    final CommonUtils commonUtils = new CommonUtils();

    CommonUIUtils commonUiUtil = new CommonUIUtils();
    private final Map<String, AppPermission> fieldPsHM;

    private String fieldMass = StringUtils.EMPTY;

    /**
     * The error message.
     */
    final private ErrorLabel errorMsg;

    final private PSDTO psDTO;

    private BeanItemContainer<PSIFPDTO> itemDetailsContainer;

    private PSPricingTableLogic psProtectionTableLogic = new PSPricingTableLogic();

    @UiField("priceProtectionTable")
    private ExtPagedTable priceProtectionTable = new ExtPagedTable(psProtectionTableLogic);
    NEPFormulaLookUp nepFormulaLookup = null;
    FormulaLookup formulaLookup = null;
    /**
     * Map to store DDLB Listname
     */
    private Map<String, String> listValueMap = new HashMap<String, String>();

    private CommonUtil commonUtil = CommonUtil.getInstance();
    @UiField("excel")
    private Button excel;

    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");

    private final PSDTO psMaster;
    SessionDTO sessionDTO;

    /**
         * Record check box
         */
        @UiField("record")
        private OptionGroup recordCheck;
        
        final StplSecurity stplSecurity = new StplSecurity();
        
         private static final Logger LOGGER = Logger.getLogger(PriceProtectionTabForm.class);

    /**
     * Instantiates a new price protection tab form.
     *
     * @param binder the binder
     * @param itemDetailsResultBean the item details result bean
     * @param selectedItemResultBean the selected item result bean
     * @param tempItemCriteria the temp item criteria
     * @param mode the mode
     * @param userId the user id
     * @param sessionId the session id
     * @param tempCreatedDate the temp created date
     */
    public PriceProtectionTabForm(final ErrorfulFieldGroup binder, final BeanItemContainer<PSIFPDTO> itemDetailsResultBean,
            final BeanItemContainer<PSIFPDTO> selectedItemResultBean, final TempItemCriteria tempItemCriteria, final String mode, String userId, String sessionId, String tempCreatedDate, final PSDTO psDTO, ErrorLabel errorMsg, PSLogic psLogic, Map<String, AppPermission> fieldPsHM, BeanItemContainer<PSIFPDTO> itemDetailsContainer, final PSDTO psMaster, final SessionDTO sessionDTO) {
        this.itemDetailsResultBean = itemDetailsResultBean;
        this.selectedItemResultBean = selectedItemResultBean;
        this.binder = binder;
        this.tempItemCriteria = tempItemCriteria;
        this.mode = mode;
        this.isViewMode = ConstantsUtils.VIEW.equals(mode);
        this.userId = userId;
        this.sessionId = sessionId;
        this.tempCreatedDate = tempCreatedDate;
        this.psDTO = psDTO;
        this.errorMsg = errorMsg;
        this.psLogic = psLogic;
        this.fieldPsHM = fieldPsHM;
        this.itemDetailsContainer = itemDetailsContainer;
        this.psMaster = psMaster;

        try {
            this.sessionDTO = sessionDTO;
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/priceschedule/priceprotection.xml"), this));
            getBinder();
            configureFields();
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldRsHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.PRICE_SCHEDULE + "," + "Price Protection", false);
            final Map<String, AppPermission> functionRsHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.PRICE_SCHEDULE + "," + "Price Protection");
            configurePermission(fieldRsHM);
            if (functionRsHM.get(FunctionNameUtil.POPULATE_CFP) != null && ((AppPermission) functionRsHM.get(FunctionNameUtil.POPULATE_CFP)).isFunctionFlag()) {
                configurePopulateBtn();
            } else {
                btnPopulate.setVisible(false);
            }
            if (functionRsHM.get(FunctionNameUtil.POPULATE_ALL_CFP) != null && ((AppPermission) functionRsHM.get(FunctionNameUtil.POPULATE_ALL_CFP)).isFunctionFlag()) {
                configurePopulateAllBtn();
            } else {
                btnAllPopulate.setVisible(false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Enter.
     *
     * @param event the event
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    private void configurePermission(final Map<String, AppPermission> fieldPsHM) {
        logger.info("Entering configurePermission");
        try {
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.PRICE_SCHEDULE, "Price Protection");
            commonUiUtil.removeComponentOnPermission(resultList, cssLayout, fieldPsHM, mode, binder);
        } catch (Exception ex) {
            logger.error(ex);
        }
        logger.info("Ending configurePermission");
    }

    /**
     *
     * @return
     */
    private ErrorfulFieldGroup getBinder() {

        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<PSDTO>(psDTO));
        binder.setBuffered(true);
        binder.setErrorDisplay(errorMsg);
        errorMsg.setId("ErrorMessage");
        return binder;
    }

    /**
     *
     * @param value
     */
    protected void setRecordValue(String value) {
        if (value != null) {
                if (value.contains(ConstantsUtils.CURRENT)) {
                    recordCheck.select(ConstantsUtils.CURRENT);
                } else {
                    recordCheck.unselect(ConstantsUtils.CURRENT);
                }
                if (value.contains(ConstantsUtils.HISTORY)) {
                    recordCheck.select(ConstantsUtils.HISTORY);
                } else {
                    recordCheck.unselect(ConstantsUtils.HISTORY);
                }
                if (value.contains(ConstantsUtils.FUTURE)) {
                    recordCheck.select(ConstantsUtils.FUTURE);
                } else {
                    recordCheck.unselect(ConstantsUtils.FUTURE);
                }
            }
            }
    /**
     * Configure fields.
     */
    private void configureFields() {

        try {
            if (mode.equals("Add")) {
                recordCheck.setReadOnly(true);
            }
            recordCheck.addItems(ConstantsUtils.CURRENT, ConstantsUtils.HISTORY, ConstantsUtils.FUTURE);
            recordCheck.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(ValueChangeEvent event) {
                    String value = String.valueOf(recordCheck.getValue());
                    psMaster.setRecord(value);
                    refreshTable();
                }
            });
            massCheck.addItem(ConstantsUtils.ENABLE);
            massCheck.addItem(ConstantsUtils.DISABLE);
            massCheck.setValue(ConstantsUtils.DISABLE);
            massCheck.setDescription((String) massCheck.getValue());
            basePriceType.setVisible(false);
            basePriceTypeLB.setVisible(false);
            setMassPopulateListName();
            massLookup.addStyleName("searchicon");
             commonUtil.loadComboBox(basePriceType, "BASE_PRICE_TYPE", false);

            massCheck.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Value change Listener for Mass Check
                 */
                public void valueChange(final ValueChangeEvent event) {
                    try {
                        massCheck.setDescription((String) massCheck.getValue());
                        massCheck.focus();
                        massField.select(ConstantsUtils.SELECT_ONE);
                        massCheckOnChangeEvent(event.getProperty().getValue());
                    } catch (Exception exception) {
                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
                            /**
                             * The method is triggered when a button of the
                             * message box is pressed .
                             *
                             * @param buttonId The buttonId of the pressed
                             * button.
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                // Do Nothing  
                            }
                        }, ButtonId.OK);
                        msg.getButton(ButtonId.OK).focus();
                        logger.error(exception);

                    }
                    massCheck.focus();
                }
            });

            CommonUtils.getComboBox(massField);
            String[] massFieldOptions = new String[]{Constants.PRICE_PROTECTION_STATUS, Constants.PRICE_PROTECTION_START_DATE, Constants.PRICE_PROTECTION_END_DATE, Constants.PRICE_PROTECTION_PRICE_TYPE, Constants.NEP, Constants.NEP_FORMULA, Constants.BASE_PRICE, Constants.PRICE_TOLERANCE_INTERVAL,
                Constants.PRICE_TOLERANCE_FREQUENCY, Constants.PRICE_TOLERANCE_TYPE, Constants.MAX_INCREMENTAL_CHANGE, Constants.PRICE_TOLERENCE, Constants.RESET_ELIGIBLE, Constants.RESET_TYPE, Constants.RESET_DATE, Constants.RESET_INTERVAL, Constants.RESET_FREQUENCY, Constants.NET_PRICE_TYPE, Constants.NET_PRICE_TYPE_FORMULA
            };
            for (int i = 0; i < massFieldOptions.length; i++) {
                massField.addItem(massFieldOptions[i]);
            }
            massField.select(ConstantsUtils.SELECT_ONE);
            massField.setEnabled(false);

            massField.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used to mass field logic and its listener.
                 */
                @Override
                public void valueChange(final ValueChangeEvent event) {
                    basePriceType.setVisible(false);
                    basePriceTypeLB.setVisible(false);
                    final String value = massField.getValue() == null ? StringUtils.EMPTY : String.valueOf(massField.getValue());
                    if (Constants.PRICE_PROTECTION_STATUS.equals(value)
                            || Constants.PRICE_TOLERANCE_INTERVAL.equals(value) || Constants.PRICE_TOLERANCE_FREQUENCY.equals(value)
                            || Constants.PRICE_TOLERANCE_TYPE.equals(value) || Constants.RESET_TYPE.equals(value) || Constants.RESET_FREQUENCY.equals(value)
                            || Constants.RESET_INTERVAL.equals(value)) {
                        try {
                            priceProtectionMassValue.setVisible(false);
                            priceProtectionMassDate.setVisible(false);
                            massSelect.setVisible(true);
                            massLookup.setVisible(false);
                            commonUtil.loadComboBox(massSelect, listValueMap.get(value), false);
                        } catch (Exception ex) {
                            logger.error(ex);
                        }
                    } else if (Constants.RESET_ELIGIBLE.equals(value) || Constants.NET_PRICE_TYPE.equals(value)) {
                        try {
                            priceProtectionMassValue.setVisible(false);
                            priceProtectionMassDate.setVisible(false);
                            massSelect.setVisible(true);
                            massLookup.setVisible(false);
//                            commonUtil.loadYesNoDDLB(massSelect, false);
//                            final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
//                            HelperDTO defaultValue = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
//                            helperList.add(defaultValue);
//                            helperList.add(new HelperDTO(1213, ConstantsUtils.YES_VARIABLE));
//                            helperList.add(new HelperDTO(1214, ConstantsUtils.NO_VARIABLE));
                            massSelect.removeAllItems();
                            massSelect.setNullSelectionAllowed(true);
                            massSelect.setImmediate(true);
                            commonUtil.loadComboBox(massSelect, "LOCKED_STATUS", false);
//                            massSelect.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
//                            massSelect.markAsDirty();
//                            BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<>(HelperDTO.class);
//                            resultContainer.addAll(helperList);
//                            massSelect.setContainerDataSource(resultContainer);
//                            massSelect.setNullSelectionItemId(resultContainer.getIdByIndex(0));
                        } catch (Exception ex) {
                            logger.error(ex);
                        }
                    } else if (Constants.PRICE_PROTECTION_START_DATE.equals(value) || Constants.PRICE_PROTECTION_END_DATE.equals(value)
                            || Constants.RESET_DATE.equals(value)) {
                        priceProtectionMassDate.setValue(null);
                        priceProtectionMassValue.setVisible(false);
                        priceProtectionMassDate.setVisible(true);
                        massSelect.setVisible(false);
                        massLookup.setVisible(false);
                    } else if (Constants.NEP.equals(value) || Constants.MAX_INCREMENTAL_CHANGE.equals(value)
                            || Constants.PRICE_TOLERENCE.equals(value)) {
                        priceProtectionMassValue.setValue(StringUtils.EMPTY);
                        priceProtectionMassValue.setVisible(true);
                        priceProtectionMassDate.setVisible(false);
                        massSelect.setVisible(false);
                        massLookup.setVisible(false);
                    } else if (Constants.NEP_FORMULA.equals(value) || Constants.NET_PRICE_TYPE_FORMULA.equals(value)) {
                        massLookup.setValue(StringUtils.EMPTY);
                        priceProtectionMassValue.setVisible(false);
                        priceProtectionMassDate.setVisible(false);
                        massSelect.setVisible(false);
                        massLookup.setVisible(true);
                    } else if (StringUtils.isBlank(value) || ConstantsUtils.SELECT_ONE.equals(value)) {
                        massSelect.setVisible(false);
                        priceProtectionMassValue.setVisible(false);
                        priceProtectionMassDate.setVisible(false);
                        massLookup.setVisible(false);
                    } else if (Constants.PRICE_PROTECTION_PRICE_TYPE.equals(value)) {
                        priceProtectionMassValue.setVisible(false);
                        priceProtectionMassDate.setVisible(false);
                        massSelect.removeAllItems();
                        massSelect.setVisible(true);
                        massLookup.setVisible(false);
                        massSelect.setPageLength(7);
                        massSelect.setImmediate(true);
                        massSelect.setNullSelectionAllowed(true);
                        massSelect.setInputPrompt(ConstantsUtils.SELECT_ONE);
                        massSelect.setNullSelectionItemId(new HelperDTO(0, ConstantsUtils.SELECT_ONE));
                        massSelect.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
                        massSelect.markAsDirty();
                        final LazyContainer priceTypeContainer = new LazyContainer(HelperDTO.class, new PriceTypeLazyContainer(null), new PriceTypeCriteria());
                        priceTypeContainer.setMinFilterLength(0);
                        massSelect.setContainerDataSource(priceTypeContainer);
                        massSelect.select(new HelperDTO(0, ConstantsUtils.SELECT_ONE));
                    }
                    else if (Constants.BASE_PRICE.equals(value)) {
                        priceProtectionMassValue.setValue(StringUtils.EMPTY);
                        priceProtectionMassValue.setVisible(false);
                        priceProtectionMassDate.setVisible(false);
                        massSelect.setVisible(false);
                        massLookup.setVisible(false);
                        basePriceType.setVisible(true);
                        basePriceTypeLB.setVisible(true);
                    }
                }
            });
            basePriceType.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used to mass field logic and its listener.
                 */
                @Override
                public void valueChange(final ValueChangeEvent event) {
               if(basePriceType.getValue()!=null &&((HelperDTO)basePriceType.getValue())!=null &&((HelperDTO)basePriceType.getValue()).getId()!=0){
                       priceProtectionMassValue.setValue(StringUtils.EMPTY);
                       priceProtectionMassValue.setVisible(false);
                       priceProtectionMassDate.setVisible(false);
                       massSelect.setVisible(false);
                       if (Constants.MANUAL.equals(((HelperDTO) basePriceType.getValue()).getDescription())) {
                           priceProtectionMassValue.setVisible(true);

                       } else if (Constants.DATE.equals(((HelperDTO) basePriceType.getValue()).getDescription())) {
                           priceProtectionMassDate.setVisible(true);
                       } else if (Constants.PRICE_TYPE.equals(((HelperDTO) basePriceType.getValue()).getDescription())) {
                           PSLogic psLogic = new PSLogic();
                           Container priceTypeContainer = new BeanItemContainer(HelperDTO.class, psLogic.getConfiguredPriceTypes(psMaster.getItemPricingQualifierMap(), false));
                           massSelect.setContainerDataSource(priceTypeContainer);
                           massSelect.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
                           massSelect.setImmediate(true);
                           massSelect.setNullSelectionAllowed(true);
                           massSelect.setNullSelectionItemId(new HelperDTO(0, ConstantsUtils.SELECT_ONE));
                           massSelect.setVisible(true);
                        }
                    }
                }
                });

            massLookup.addClickListener(new CustomTextField.ClickListener() {

                @Override
                public void click(CustomTextField.ClickEvent event) {
                    try {
                        if (massField.getValue() != null && Constants.NEP_FORMULA.equals(massField.getValue())
                                || massField.getValue() != null && Constants.NET_PRICE_TYPE_FORMULA.equals(massField.getValue())) {
                            if (nepFormulaLookup == null) {
                                nepFormulaLookup = new NEPFormulaLookUp(Constants.NEP_FORMULA.equals(massField.getValue()) ? "NEP" : "");
                                UI.getCurrent().addWindow(nepFormulaLookup);
                            }
                            nepFormulaLookup.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    PSNepFormulaDTO psNepFormulaDTO = nepFormulaLookup.getNepFormulaDTO();
                                    massLookup.setValue(psNepFormulaDTO.getNepFormulaNo());
                                    final Map<String, String> map = new HashMap<>();
                                    map.put("formulaNo", psNepFormulaDTO.getNepFormulaNo());
                                    map.put("formulaName", psNepFormulaDTO.getNepFormulaName());
                                    map.put("formulaID", psNepFormulaDTO.getNepFormulaID());
                                    map.put("formulaSystemSID", String.valueOf(psNepFormulaDTO.getNepFormulaSystemID()));
                                    massLookup.setData(map);
                                    nepFormulaLookup = null;
                                }
                            });
                        }
                    } catch (Exception ex) {
                    LOGGER.error(ex);
                    logger.error(ex);
                }

                }
            });

            massSelect.setVisible(false);
            priceProtectionMassValue.setVisible(false);
            priceProtectionMassDate.setDateFormat(Constants.DATE_FORMAT);
            priceProtectionMassDate.setVisible(false);
            priceProtectionMassDate.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Notifies this listener that the Property's value has changed.
                 */
                public void valueChange(final ValueChangeEvent event) {
                    priceProtectionMassDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(priceProtectionMassDate.getValue()));
                }
            });
            massLookup.setVisible(false);
            btnPopulate.setEnabled(false);
            btnPopulate.setImmediate(true);
            btnAllPopulate.setEnabled(false);
            btnAllPopulate.setImmediate(true);
            configurePriceProtectionTable();
            ResponsiveUtils.getResponsiveControls(psProtectionTableLogic.createControls(), controlLayout);

            excel.setIcon(excelExportImage);
            excel.setDescription("Export to excel");
            excel.setIconAlternateText("Excel export");
            excel.setHtmlContentAllowed(true);

        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    /**
     * Excel button logic.
     *
     * @param event the event
     */
    @UiHandler("excel")
    public void excelButtonLogic(Button.ClickEvent event) {
        try {
            excelExportLogic();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * Mass check on change event.
     *
     * @param value the value
     * @throws Exception the exception
     */
    protected void massCheckOnChangeEvent(final Object value) throws Exception {

        if (!String.valueOf(value).isEmpty() && Constants.ENABLE.equals(String.valueOf(value))) {

            massField.setEnabled(true);
            btnPopulate.setEnabled(true);
            btnAllPopulate.setEnabled(true);

        } else if (value != null && Constants.DISABLE.equals(value.toString())) {
            priceProtectionMassValue.setValue(StringUtils.EMPTY);
            priceProtectionMassDate.setValue(null);
            massSelect.setVisible(false);
            massField.setEnabled(false);
            priceProtectionMassValue.setVisible(false);
            priceProtectionMassDate.setVisible(false);
            btnPopulate.setEnabled(false);
            btnAllPopulate.setEnabled(false);
        }

    }

    /**
     * Configure Price Protection Table.
     */
    private void configurePriceProtectionTable() {
        priceProtectionTable.markAsDirty();
        priceProtectionTable.setFilterGenerator(new PSFilterGenerator(psMaster));
        priceProtectionTable.addStyleName(ConstantsUtils.TABLE_CHECK_BOX);
        priceProtectionTable.addStyleName(ConstantsUtils.FILTER_BAR);
        priceProtectionTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);

        psProtectionTableLogic.setContainerDataSource(itemDetailsContainer);
        psProtectionTableLogic.sinkItemPerPageWithPageLength(false);
        psProtectionTableLogic.setPageLength(7);

        psProtectionTableLogic.setSearchData(itemDetailsResultBean, binder, mode, psMaster, sessionDTO);
        psProtectionTableLogic.setCurrentPage(1);
        priceProtectionTable.setVisibleColumns(PsUtils.PRICE_PROTECTION_COL);
        priceProtectionTable.setColumnHeaders(PsUtils.PRICE_PROTECTION_COL_HEADER);

        priceProtectionTable.setFilterBarVisible(!isViewMode);
        priceProtectionTable.setFilterDecorator(new ExtDemoFilterDecorator());
        priceProtectionTable.setFilterFieldVisible(ConstantsUtils.CHECK_RECORD, false);
        priceProtectionTable.setFilterFieldVisible("basePriceValue", false);
        priceProtectionTable.setImmediate(true);
        priceProtectionTable.setSizeFull();

        if (!isViewMode) {
            priceProtectionTable.setSelectable(true);
            priceProtectionTable.setColumnCheckBox(ConstantsUtils.CHECK_RECORD, true, false);
            priceProtectionTable.setTableFieldFactory(new PSTableGenerator(priceProtectionTable, itemDetailsResultBean, "Add",psMaster));
            priceProtectionTable.setEditable(true);
        } else {
            priceProtectionTable.setVisibleColumns(Arrays.copyOfRange(PsUtils.PRICE_PROTECTION_COL, 1, PsUtils.PRICE_PROTECTION_COL.length));
            priceProtectionTable.setColumnHeaders(Arrays.copyOfRange(PsUtils.PRICE_PROTECTION_COL_HEADER, 1, PsUtils.PRICE_PROTECTION_COL_HEADER.length));
        }

        priceProtectionTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {

            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                if (ConstantsUtils.CHECK_RECORD.equals(event.getPropertyId().toString())) {
                    if (event.isChecked()) {
                        try {
                            psLogic.populateLogic(userId, sessionId, tempCreatedDate, StringUtils.EMPTY, StringUtils.EMPTY, true, "Add", 0, "checked");
                            for (PSIFPDTO pSIFPDTO : itemDetailsResultBean.getItemIds()) {
                                pSIFPDTO.setCheckRecord(Boolean.TRUE);

                    }
                                psProtectionTableLogic.setCurrentPage(psProtectionTableLogic.getCurrentPage());
                            priceProtectionTable.setColumnCheckBox(ConstantsUtils.CHECK_RECORD, true, true);
                        } catch (Exception ex) {
                            logger.error(ex);
                        }

                    } else {
                        try {
                            psLogic.populateLogic(userId, sessionId, tempCreatedDate, StringUtils.EMPTY, StringUtils.EMPTY, true, "Add", 0, "unChecked");
                            for (PSIFPDTO pSIFPDTO : itemDetailsResultBean.getItemIds()) {
                                pSIFPDTO.setCheckRecord(Boolean.FALSE);

                            }
                            // this setCurrentPage is used to refresh the
                            // companyDetailsResultLazyBean lazy conatiner
                            psProtectionTableLogic.setCurrentPage(psProtectionTableLogic.getCurrentPage());
                            priceProtectionTable.setColumnCheckBox(ConstantsUtils.CHECK_RECORD, true, false);
                        } catch (Exception ex) {
                            logger.error(ex);
                        }
                    }
                }
            }
        });

    }

    /**
     * Set key & values
     */
    private void setMassPopulateListName() {
        listValueMap.put(Constants.PRICE_PROTECTION_STATUS, UIUtils.STATUS);
        listValueMap.put(Constants.RESET_INTERVAL, UIUtils.PRICE_TOLERANCE_INTERVAL);
        listValueMap.put(Constants.RESET_FREQUENCY, UIUtils.PRICE_TOLERANCE_FRERQUENCY);
        listValueMap.put(Constants.PRICE_PROTECTION_PRICE_TYPE, UIUtils.STATUS);
        listValueMap.put(Constants.PRICE_TOLERANCE_INTERVAL, UIUtils.PRICE_TOLERANCE_INTERVAL);
        listValueMap.put(Constants.PRICE_TOLERANCE_FREQUENCY, UIUtils.PRICE_TOLERANCE_FRERQUENCY);
        listValueMap.put(Constants.PRICE_TOLERANCE_TYPE, UIUtils.PRICE_TOLERANCE_TYPE);
        listValueMap.put(Constants.RESET_TYPE, UIUtils.RESET_TYPE);
        listValueMap.put(Constants.NET_PRICE_TYPE,"NET_PRICE_TYPE");
    }

    /**
     * To refresh Table
     */
    public void refreshTable() {
        psProtectionTableLogic.setSearchData(itemDetailsResultBean, binder, mode, psMaster, sessionDTO);
        psProtectionTableLogic.setCurrentPage(1);
    }

    /**
     * to reset mass check
     *
     * @param values
     */
    public void enableOrDisableMassCheck(String values) {
        massCheck.setValue(values);
    }

    /**
     * save table details
     */
    public void commitPriceProtectionTable() {
        priceProtectionTable.commit();
    }

    /**
     * Adds the btn populate.
     *
     * @return the button
     * @throws Exception the exception
     */
    public void configurePopulateBtn() throws Exception {

        btnPopulate.setReadOnly(true);
        btnPopulate.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             *
             * @param event the fired event.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                // empty block
            }
        });

        btnPopulate.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                logger.debug("Entering PSTabsheetForm Populate operation");
                try {
                    populateLogic(false);

                } catch (FieldGroup.CommitException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    logger.error(errorMsg);
                } catch (SystemException ex) {
                    LOGGER.error(ex);
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    logger.error(errorMsg);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            // Do Nothing  
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
                } catch (Exception exception) {

                    exception.printStackTrace();
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1002), new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            // Do Nothing  
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
                    logger.error(exception.getMessage());
                }
                logger.debug("Ending PSTabsheetForm Populate operation");
            }

        });

    }

    /**
     *
     * @param isPopulateAll
     * @throws com.vaadin.data.fieldgroup.FieldGroup.CommitException
     * @throws PortalException
     * @throws SystemException
     * @param isPopulateAll
     * @throws com.vaadin.data.fieldgroup.FieldGroup.CommitException
     * @throws PortalException
     * @throws SystemException
     */
    private void populateLogic(boolean isPopulateAll) throws FieldGroup.CommitException, PortalException, SystemException {
        binder.getErrorDisplay().clearError();

        binder.commit();
        String massPopulateValue = priceProtectionMassValue.getValue().trim();

        if (massField.getValue() != null) {
            if ("Base Price".equals(String.valueOf(massField.getValue()))) {
                if(basePriceType.getValue()==null||((HelperDTO)basePriceType.getValue())==null||((HelperDTO)basePriceType.getValue()).getId()==0){
                     final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Populate Error", "Please select Value for Base Price Type to populate Base Price", new MessageBoxListener() {
                            /**
                             * The method is triggered when a button of the
                             * message box is pressed .
                             *
                             * @param buttonId The buttonId of the pressed
                             * button.
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                // Do Nothing  
                            }
                        }, ButtonId.OK);
                        msg.getButton(ButtonId.OK).focus();
                        return;
                }
            }

            if (StringUtils.isNotBlank(massPopulateValue)) {
                if (!massPopulateValue.matches(ValidationUtils.REBATE_SCHEDULE_AMT_DOUBLE)) {
                    binder.getErrorDisplay().setError(massField.getValue() + " can contain only digits");
                    priceProtectionMassValue.setValue(StringUtils.EMPTY);
                    return;
                } else if (!massPopulateValue.matches(ValidationUtils.REBATE_SCHEDULE_AMT_SAVE)) {
                    binder.getErrorDisplay().setError(massField.getValue() + " should be less than 15 digits with 2 decimal places");
                    priceProtectionMassValue.setValue(StringUtils.EMPTY);
                    return;
                }
            }

            Object priceTypeValue = (ConstantsUtils.SELECT_ONE).equals(String.valueOf(massSelect.getValue())) ? null : massSelect.getValue();
            Object massSelectValue = massSelect.getValue() == null || (ConstantsUtils.SELECT_ONE).equals(((HelperDTO) massSelect.getValue()).getDescription()) ? null : massSelect.getValue();

            if (!priceProtectionMassValue.getValue().trim().isEmpty() || massSelectValue != null || priceTypeValue != null || priceProtectionMassDate.getValue() != null || (massLookup.getValue() != null && StringUtils.isNotEmpty(massLookup.getValue()))) {
                String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
                String sessionId = String.valueOf(sessionDTO.getUiSessionId());
                String tempCreatedDate = String.valueOf(sessionDTO.getSessionDate());
                if (itemDetailsResultBean.size() > 0) {
                    psLogic.saveToTemp(itemDetailsResultBean.getItemIds(), binder);
                    itemDetailsResultBean.removeAllItems();
                }

                if (!isPopulateAll) {
                    List<Object> itemList = psLogic.validateNull(userId, sessionId, tempCreatedDate, "tempCheckedCount");
                    if (itemList.isEmpty()) {
                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Populate Error", "Please select an item to populate", new MessageBoxListener() {
                            /**
                             * The method is triggered when a button of the
                             * message box is pressed .
                             *
                             * @param buttonId The buttonId of the pressed
                             * button.
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                // Do Nothing  
                            }
                        }, ButtonId.OK);
                        msg.getButton(ButtonId.OK).focus();
                        return;
                    }
                }

                if (massField.getValue() != null && (StringUtils.isNotEmpty(priceProtectionMassValue.getValue()) || priceProtectionMassDate.getValue() != null || massSelect.getValue() != null || StringUtils.isNotEmpty(massLookup.getValue()))) {
                    final String populateField;
                    final String populateValue;
                    final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                    fieldMass = massField.getValue().toString();
                    if (Constants.PRICE_PROTECTION_START_DATE.equals(fieldMass)) {
                        populateField = "PS_DETAILS_PRIC_PRTCN_STDATE";
                        populateValue = fmt.format(priceProtectionMassDate.getValue());
                    } else if ("Price".equals(fieldMass)) {
                        populateField = "PS_DETAILS_PRICE";
                        populateValue = String.valueOf(priceProtectionMassValue.getValue());
                    } else if (Constants.PRICE_PROTECTION_END_DATE.equals(fieldMass)) {
                        populateField = "PS_DETAILS_PRIC_PRTCN_EDDATE";
                        populateValue = fmt.format(priceProtectionMassDate.getValue());
                    } else if ("CP Start Date".equals(fieldMass)) {
                        populateField = "PS_DTLS_CONT_PRICE_STARTDATE";
                        populateValue = fmt.format(priceProtectionMassDate.getValue());
                    } else if ("CP End Date".equals(fieldMass)) {
                        populateField = "PS_DTLS_CONT_PRICE_ENDDATE";
                        populateValue = fmt.format(priceProtectionMassDate.getValue());
                    } else if ("Base Price".equals(fieldMass)) {
                        if (Constants.MANUAL.equals(((HelperDTO) basePriceType.getValue()).getDescription())) {
                            populateField = "BASE_PRICE_ENTRY";
                            populateValue = String.valueOf(priceProtectionMassValue.getValue());

                        } else if (Constants.DATE.equals(((HelperDTO) basePriceType.getValue()).getDescription())) {
                            populateField = "BASE_PRICE_DATE";
                            populateValue = fmt.format(priceProtectionMassDate.getValue());
                        } else if (Constants.PRICE_TYPE.equals(((HelperDTO) basePriceType.getValue()).getDescription())) {
                            populateField = "BASE_PRICE_DDLB";
                            populateValue = String.valueOf(((HelperDTO) massSelect.getValue()).getId());
                        } else {
                            return;
                        }
                    } else if ("Contract Price".equals(fieldMass)) {
                        populateField = "PS_DETAILS_CONTRACT_PRICE";
                        populateValue = String.valueOf(priceProtectionMassValue.getValue());
                    } else if ("Price Tolerance".equals(fieldMass)) {
                        populateField = "PS_DETAILS_PRICE_TOLERANCE";
                        populateValue = String.valueOf(priceProtectionMassValue.getValue());
                    } else if ("Price Tolerance Type".equals(fieldMass)) {
                        populateField = "PS_DTLS_PRICE_TOLERANCE_TYPE";
                        populateValue = String.valueOf(((HelperDTO) massSelect.getValue()).getId());
                    } else if ("Price Tolerance Interval".equals(fieldMass)) {
                        populateField = "PS_DTLS_PRICE_TOLERANCE_INTRVL";
                        populateValue = String.valueOf(((HelperDTO) massSelect.getValue()).getId());
                    } else if ("Price Tolerance Frequency".equals(fieldMass)) {
                        populateField = "PS_DTLS_PRICE_TOLERANCE_FREQ";
                        populateValue = String.valueOf(((HelperDTO) massSelect.getValue()).getId());
                    } else if ("Revision Date".equals(fieldMass)) {
                        populateField = "PS_DETAILS_REVISION_DATE";
                        populateValue = fmt.format(priceProtectionMassDate.getValue());
                    } else if (Constants.PRICE_PROTECTION_STATUS.equals(fieldMass)) {
                        populateField = "PRICE_PROTECTION_STATUS";
                        populateValue = String.valueOf(((HelperDTO) massSelect.getValue()).getId());
                    } else if (Constants.NEP.equals(fieldMass)) {
                        populateField = "NEP";
                        populateValue = String.valueOf(priceProtectionMassValue.getValue());
                    } else if (Constants.MAX_INCREMENTAL_CHANGE.equals(fieldMass)) {
                        populateField = "MAX_INCREMENTAL_CHANGE";
                        populateValue = String.valueOf(priceProtectionMassValue.getValue());
                    } else if (Constants.RESET_ELIGIBLE.equals(fieldMass)) {
                        populateField = "RESET_ELIGIBLE";
                        //  populateValue = fmt.format(priceProtectionMassDate.getValue());
                        populateValue = String.valueOf(((HelperDTO) massSelect.getValue()).getId());
                    } else if (Constants.RESET_TYPE.equals(fieldMass)) {
                        populateField = "RESET_TYPE";
                        populateValue = String.valueOf(((HelperDTO) massSelect.getValue()).getId());
                    } else if (Constants.RESET_DATE.equals(fieldMass)) {
                        populateField = "RESET_DATE";
                        populateValue = fmt.format(priceProtectionMassDate.getValue());
                    } else if (Constants.RESET_INTERVAL.equals(fieldMass)) {
                        populateField = "RESET_INTERVAL";
                        populateValue = String.valueOf(((HelperDTO) massSelect.getValue()).getId());
                    } else if (Constants.RESET_FREQUENCY.equals(fieldMass)) {
                        populateField = "RESET_FREQUENCY";
                        populateValue = String.valueOf(((HelperDTO) massSelect.getValue()).getId());
                    } else if (Constants.NET_PRICE_TYPE.equals(fieldMass)) {
                        populateField = "NET_PRICE_TYPE";
                        populateValue = String.valueOf(((HelperDTO) massSelect.getValue()).getId());

                    } else if (Constants.PRICE_PROTECTION_PRICE_TYPE.equals(fieldMass)) {
                        populateField = "PRICE_PROTECTION_PRICE_TYPE";
                        populateValue = String.valueOf(((HelperDTO) massSelect.getValue()).getId());

                    } else if (Constants.NEP_FORMULA.equals(fieldMass)) {
                        populateField = "NEP_FORMULA";
                        Map<String, String> map = (HashMap) massLookup.getData();
                        populateValue = String.valueOf(map.get("formulaSystemSID"));

                    } else if (Constants.NET_PRICE_TYPE_FORMULA.equals(fieldMass)) {
                        populateField = "NET_PRICE_TYPE_FORMULA";
                        Map<String, String> map = (HashMap) massLookup.getData();
                        populateValue = String.valueOf(map.get("formulaSystemSID"));
                    } else {
                        populateField = StringUtils.EMPTY;
                        populateValue = StringUtils.EMPTY;
                    }
                    psLogic.populateLogic(userId, sessionId, tempCreatedDate, populateField, populateValue, isPopulateAll, "Add", 0, "Populate");

                    psProtectionTableLogic.setCurrentPage(psProtectionTableLogic.getCurrentPage());
                    logger.debug("Ending POPULATE method");
                    System.out.println("Ending POPULATE method");
                }
                priceProtectionMassValue.setValue(StringUtils.EMPTY);
                priceProtectionMassDate.setValue(null);
                massField.setValue(StringUtils.EMPTY);

            } else {
                final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Populate Error", "Please enter value for the " + massField.getValue(), new MessageBoxListener() {
                    /**
                     * The method is triggered when a button of the message box
                     * is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        // Do Nothing  
                    }
                }, ButtonId.OK);
                msg.getButton(ButtonId.OK).focus();
            }
        } else {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Populate Error", "Please Select a field to Populate", new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing  
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        }
    }

    /**
     *
     */
    private void configurePopulateAllBtn() {
        btnAllPopulate.setReadOnly(true);
        btnAllPopulate.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             *
             * @param event the fired event.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                // empty block
            }
        });

        btnAllPopulate.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                logger.debug("Entering PSTabsheetForm Populate operation");
                try {
                    populateLogic(true);

                } catch (FieldGroup.CommitException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    logger.error(errorMsg);
                } catch (SystemException ex) {
                    LOGGER.error(ex);
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    logger.error(errorMsg);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            // Do Nothing     
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();

                } catch (Exception exception) {

                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1002), new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            // Do Nothing     
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
                    logger.error(exception);
                }
                logger.debug("Ending PSTabsheetForm Populate operation");
            }

        });
    }

    /**
     *
     */
    public void removeAndDisablingComponents() {
        root.removeComponent(massLayout);

    }

    public void clearTable() throws Exception {
        psProtectionTableLogic.clearAll();
    }

    public ExtPagedTable<?> getPriceProtectionTable() {
        return priceProtectionTable;
    }

    /**
     *
     * @throws PortalException
     * @throws SystemException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws Exception
     */
    public void excelExportLogic() throws PortalException, SystemException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, Exception {
        logger.info("Entering excelExportLogic");
        createWorkSheet();
        logger.info("Ending excelExportLogic");
    }

    /**
     *
     * @throws PortalException
     * @throws SystemException
     * @throws IllegalArgumentException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws Exception
     */
    private void createWorkSheet() throws PortalException, SystemException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, Exception {
        logger.info("Entering createWorkSheet");
        int recordCount = 0;

        List<Object[]> list = psLogic.getItemPriceDetails(null, 0, 0, binder, new ArrayList<SortByColumn>(), mode, psMaster, true);
        recordCount = Integer.valueOf(String.valueOf(list.get(0)));
        String[] strArray = new String[(priceProtectionTable.getColumnHeaders().length - 1)];
        if (sessionDTO.getMode().equalsIgnoreCase("View")) {
            strArray = new String[(priceProtectionTable.getColumnHeaders().length)];
        }
        int count = 0;
        for (String columns : priceProtectionTable.getColumnHeaders()) {
            if (!columns.isEmpty()) {
                strArray[count] = columns;
                count++;
            }
        }
        ExcelExportforBB.createWorkSheet(strArray, recordCount, this, getUI(), "Price_Protection");
        logger.info("Ending createWorkSheet");
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws PortalException, SystemException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException {

        logger.info("Entering createWorkSheetContent method");
        List<PSIFPDTO> exportCompany = null;
        List<Object[]> returnList = psLogic.getItemPriceDetails(null, start, end, binder, null, mode, psMaster, false);
        exportCompany = getCustomizedItemPriceDTO(returnList, binder, mode, psMaster);
        Object[] columns = priceProtectionTable.getVisibleColumns();
        columns = ArrayUtils.removeElement(columns, ConstantsUtils.CHECK_RECORD);

        ExcelExportforBB.createFileContent(columns, exportCompany, printWriter);
        logger.info("End of createWorkSheetContent method");

    }
}
