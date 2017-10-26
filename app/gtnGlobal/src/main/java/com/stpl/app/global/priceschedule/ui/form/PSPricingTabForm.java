package com.stpl.app.global.priceschedule.ui.form;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.item.util.CommonUtils;
import com.stpl.app.global.priceschedule.dto.PSDTO;
import com.stpl.app.global.priceschedule.dto.PSFilterGenerator;
import com.stpl.app.global.priceschedule.dto.PSIFPDTO;
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
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

public class PSPricingTabForm extends CustomComponent implements View {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(PSPricingTabForm.class.getName());

    @UiField("cssLayout")
    private CssLayout cssLayout;
    /**
     * The mass check.
     */
    @UiField("massCheck")
    private OptionGroup massCheck;
    /**
     * The mass field.
     */
    @UiField("massField")
    private ComboBox massField;
    /**
     * The mass date.
     */
    @UiField("massDate")
    private PopupDateField massDate;

    /**
     * The mass value.
     */
    @UiField("massValue")
    private TextField massValue;

    /**
     * The mass select.
     */
    @UiField("massSelect")
    private ComboBox massSelect;
    /**
     * The mass price type.
     */
    @UiField("massPriceType")
    private ComboBox massPriceType;

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
    /**
     * Record check box
     */
    @UiField("record")
    private OptionGroup record;

    /**
     * The Item Details Container.
     */
    private BeanItemContainer<PSIFPDTO> itemDetailsContainer;

    private PSPricingTableLogic psPricingTableLogic = new PSPricingTableLogic();

    @UiField("itemDetailsTable")
    private ExtPagedTable itemDetailsTable = new ExtPagedTable(psPricingTableLogic);

    @UiField("controlLayout")
    private HorizontalLayout controlLayout;
    @UiField("massLayout")
    private HorizontalLayout massLayout;

    @UiField("root")
    private VerticalLayout root;

    @UiField("excel")
    private Button excel;
    @UiField("valueforddlb")
    private Label valueforddlb;

    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");

    private final BeanItemContainer<PSIFPDTO> itemDetailsResultBean;

    /*
     * The binder.
     */
    private final ErrorfulFieldGroup binder;

    final private PSDTO psMaster;

    /**
     * The error message.
     */
    final private ErrorLabel errorMsg;

    /**
     * the used id
     */
    private final String userId;
    /**
     * the session id
     */
    private final String sessionId;
    /**
     * the temp table records created date
     */
    private final String tempCreatedDate;
    final private PSLogic psLogic;

    private String fieldMass = StringUtils.EMPTY;
    private final String mode;
    private final boolean isViewMode;
    /**
     * The ps ifp list.
     */
    private final List<PSIFPDTO> psIfpList = new ArrayList<PSIFPDTO>();
    /**
     * The item details results bean.
     */
    private final BeanItemContainer<PSIFPDTO> resultBeans;
    StplSecurity stplSecurity = new StplSecurity();
    private final IFPLogic ifpLogic = new IFPLogic();

    final CommonUtils commonUtils = new CommonUtils();

    CommonUIUtils commonUiUtil = new CommonUIUtils();

    private CommonUtil commonUtil = CommonUtil.getInstance();
    SessionDTO sessionDTO;

    public PSPricingTabForm(ErrorLabel errorMsg, final ErrorfulFieldGroup binder, final PSDTO psMaster, BeanItemContainer<PSIFPDTO> itemDetailsResultBean,
            String userId, String sessionId, String tempCreatedDate, PSLogic psLogic,
            final BeanItemContainer<PSIFPDTO> resultBeans, final String mode, BeanItemContainer<PSIFPDTO> itemDetailsContainer, final SessionDTO sessionDTO) throws PortalException, SystemException {
        this.errorMsg = errorMsg;
        this.binder = binder;
        this.psMaster = psMaster;
        this.itemDetailsResultBean = itemDetailsResultBean;

        this.userId = userId;
        this.sessionId = sessionId;
        this.tempCreatedDate = tempCreatedDate;
        this.psLogic = psLogic;
        this.resultBeans = resultBeans;
        this.mode = mode;

        this.isViewMode = ConstantsUtils.VIEW.equals(mode);
        this.sessionDTO = sessionDTO;
        this.itemDetailsContainer = itemDetailsContainer;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/priceschedule/pspricingtabform.xml"), this));
        try {
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldPsHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.PRICE_SCHEDULE, false);

            getBinder();
            configureFields();
            configurePermission(fieldPsHM);
            final Map<String, AppPermission> functionRsHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.PRICE_SCHEDULE + "," + "Pricing");
            if (functionRsHM.get(FunctionNameUtil.POPULATE) != null && ((AppPermission) functionRsHM.get(FunctionNameUtil.POPULATE)).isFunctionFlag()) {
                configurePopulateBtn();
            } else {
                btnPopulate.setVisible(false);
            }
            if (functionRsHM.get(FunctionNameUtil.POPULATE_ALL) != null && ((AppPermission) functionRsHM.get(FunctionNameUtil.POPULATE_ALL)).isFunctionFlag()) {
                configurePopulateAllBtn();
            } else {
                btnAllPopulate.setVisible(false);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private void configurePermission(final Map<String, AppPermission> fieldPsHM) {
        logger.debug("Entering configurePermission");
        try {
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.PRICE_SCHEDULE, "Pricing");
            commonUiUtil.removeComponentOnPermission(resultList, cssLayout, fieldPsHM, mode, binder);
        } catch (Exception ex) {
            logger.error(ex);
        }
        logger.debug("Ending configurePermission");
    }

    private void configureFields() {
        try {
            massDate.setDescription(ConstantsUtils.DATE_DES);
            massCheck.addItem(ConstantsUtils.ENABLE);
            massCheck.addItem(ConstantsUtils.DISABLE);
            massCheck.setValue(ConstantsUtils.DISABLE);
            massCheck.setImmediate(true);
            massCheck.setStyleName("horizontal");
            massCheck.setDescription((String) massCheck.getValue());

            CommonUtils.getComboBox(massField);
            massField.addItem(ConstantsUtils.STATIUS);
            massField.addItem(ConstantsUtils.PRIC);
            massField.addItem(ConstantsUtils.CP_START_DATE);
            massField.addItem(ConstantsUtils.CP_END_DATE);
            massField.addItem(ConstantsUtils.PRICE_TYPE1);
            massField.addItem(Constants.SUGGESTED_PRICE);
            massField.select(ConstantsUtils.SELECT_ONE);
            CommonUtils.getComboBox(massSelect);
            massSelect.setImmediate(true);
            massSelect.setVisible(false);
            valueforddlb.setPrimaryStyleName("labelsize");
            massSelect.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used to mass select logic and its listener.
                 */
                public void valueChange(final ValueChangeEvent event) {
                }
            });

            massPriceType.setPageLength(NumericConstants.SEVEN);
            massPriceType.setImmediate(true);
            massPriceType.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
            massPriceType.markAsDirty();
            massPriceType.setVisible(false);

            massField.setImmediate(true);
            massValue.setVisible(false);
            massValue.setImmediate(true);
            massDate.setImmediate(true);
            massDate.setValidationVisible(true);
            massDate.setId("MassDate");
            massDate.setVisible(false);
            massDate.setDateFormat(ConstantsUtils.DATE_FORMAT);

            massDate.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Notifies this listener that the Property's value has changed.
                 */
                public void valueChange(final ValueChangeEvent event) {
                    massDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(massDate.getValue()));
                }
            });
            btnPopulate.setEnabled(false);
            massField.setEnabled(false);
            btnAllPopulate.setEnabled(false);
            btnAllPopulate.setImmediate(true);
            massField.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used to mass field logic and its listener.
                 */
                public void valueChange(final ValueChangeEvent event) {
                    try {
                        final String value = massField.getValue() == null ? StringUtils.EMPTY : String.valueOf(massField.getValue());
                        massValue.setValue(StringUtils.EMPTY);
                        massDate.setValue(null);
                        if (Constants.CONTRACT_PRICE.equals(value) || Constants.BASE_PRICE.equals(value) || Constants.PRICE_TOLERENCE.equals(value) || Constants.PRICE.equals(value)
                                || Constants.SUGGESTED_PRICE.equals(value)) {

                            massValue.setVisible(true);
                            massDate.setVisible(false);
                            massSelect.setVisible(false);
                            massPriceType.setVisible(false);
                            btnPopulate.setEnabled(true);
                        } else if (Constants.CP_START_DATE.equals(value) || Constants.CP_END_DATE.equals(value) || Constants.PRICE_PROTECTION_START_DATE.equals(value)
                                || Constants.PRICE_PROTECTION_END_DATE.equals(value) || Constants.REVISION_DATE.equals(value)) {
                            massValue.setVisible(false);
                            massDate.setVisible(true);
                            massSelect.setVisible(false);
                            massPriceType.setVisible(false);
                            btnPopulate.setEnabled(true);

                        } else if (ConstantsUtils.PRICE_TYPE1.equals(value)) {
                            massValue.setVisible(false);
                            massDate.setVisible(false);
                            massSelect.setVisible(false);
                            massPriceType.setVisible(true);
                            massPriceType.removeAllItems();
                            btnPopulate.setEnabled(true);
                            massPriceType.setNullSelectionItemId(new HelperDTO(0, ConstantsUtils.SELECT_ONE));

                            final LazyContainer priceTypeContainer = new LazyContainer(HelperDTO.class, new PriceTypeLazyContainer(null), new PriceTypeCriteria());
                            priceTypeContainer.setMinFilterLength(0);
                            massPriceType.setContainerDataSource(priceTypeContainer);

                        } else if ("Price Tolerance Type".equals(value)) {
                            massValue.setVisible(false);
                            massDate.setVisible(false);
                            massPriceType.setVisible(false);
                            massSelect.setVisible(true);
                            massSelect.removeAllItems();
                            btnPopulate.setEnabled(true);
                            massSelect.setContainerDataSource(new IndexedContainer(psLogic.getPriceToleranceType()));
                            massSelect.select(new HelperDTO(0, ConstantsUtils.SELECT_ONE));
                        } else if ("Price Tolerance Interval".equals(value)) {
                            massValue.setVisible(false);
                            massDate.setVisible(false);
                            massPriceType.setVisible(false);
                            massSelect.setVisible(true);
                            massSelect.removeAllItems();
                            btnPopulate.setEnabled(true);
                            massSelect.setContainerDataSource(new IndexedContainer(psLogic.getPriceToleranceInterval()));
                            massSelect.select(new HelperDTO(0, ConstantsUtils.SELECT_ONE));

                        } else if ("Price Tolerance Frequency".equals(value)) {
                            massValue.setVisible(false);
                            massDate.setVisible(false);
                            massPriceType.setVisible(false);
                            massSelect.setVisible(true);
                            massSelect.removeAllItems();
                            btnPopulate.setEnabled(true);
                            massSelect.setContainerDataSource(new IndexedContainer(psLogic.getPriceToleranceFrequency()));
                            massSelect.select(new HelperDTO(0, ConstantsUtils.SELECT_ONE));
                        } else if (ConstantsUtils.STATIUS.equalsIgnoreCase(value)) {
                            massValue.setVisible(false);
                            massDate.setVisible(false);
                            massPriceType.setVisible(false);
                            massSelect.setVisible(true);
                            massSelect.removeAllItems();
                            btnPopulate.setEnabled(true);
                            commonUtil.loadComboBox(massSelect, UIUtils.STATUS, false);
                        } else {
                            massValue.setVisible(false);
                            massDate.setVisible(false);
                            massPriceType.setVisible(false);
                            massSelect.setVisible(false);
                        }
                    } catch (SystemException ex) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        logger.error(errorMsg);
                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
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
                }
            });
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

            addItemDetailsTable();
            ResponsiveUtils.getResponsiveControls(psPricingTableLogic.createControls(), controlLayout);

            excel.setIcon(excelExportImage);
            excel.setDescription("Export to excel");
            excel.setIconAlternateText("Excel export");
            excel.setHtmlContentAllowed(true);

            record.addItems(ConstantsUtils.CURRENT, ConstantsUtils.HISTORY, ConstantsUtils.FUTURE);
            record.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(ValueChangeEvent event) {
                    String value = String.valueOf(record.getValue());
                    psMaster.setRecord(value);
                    refreshTable();
                }
            });

        } catch (Exception e) {
            logger.error(e);
        }

    }

    /**
     *
     * @param value
     */
    protected void setRecordValue(String value) {
        if (value != null) {
            if (value.contains(ConstantsUtils.CURRENT)) {
                record.select(ConstantsUtils.CURRENT);
            } else {
                record.unselect(ConstantsUtils.CURRENT);
            }
            if (value.contains(ConstantsUtils.HISTORY)) {
                record.select(ConstantsUtils.HISTORY);
            } else {
                record.unselect(ConstantsUtils.HISTORY);
            }
            if (value.contains(ConstantsUtils.FUTURE)) {
                record.select(ConstantsUtils.FUTURE);
            } else {
                record.unselect(ConstantsUtils.FUTURE);
            }
        }
    }

    private ErrorfulFieldGroup getBinder() {

        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<PSDTO>(psMaster));
        binder.setBuffered(true);
        binder.setErrorDisplay(errorMsg);
        errorMsg.setId("ErrorMessage");
        return binder;
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub

    }

    public void addItemDetailsTable() {

        itemDetailsTable.markAsDirty();
        itemDetailsTable.setFilterGenerator(new PSFilterGenerator(psMaster));
        itemDetailsTable.addStyleName(ConstantsUtils.TABLE_CHECK_BOX);
        itemDetailsTable.addStyleName(ConstantsUtils.FILTER_BAR);
        itemDetailsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        psPricingTableLogic.setContainerDataSource(itemDetailsContainer);
        psPricingTableLogic.sinkItemPerPageWithPageLength(false);
        psPricingTableLogic.setPageLength(NumericConstants.SEVEN);

        psPricingTableLogic.setSearchData(itemDetailsResultBean, binder, mode, psMaster, sessionDTO);
        psPricingTableLogic.setCurrentPage(1);

        itemDetailsTable.setVisibleColumns(PsUtils.ITEM_DETAILS_COL);
        itemDetailsTable.setColumnHeaders(PsUtils.ITEM_DETAILS_COL_HEADER);

        itemDetailsTable.setFilterBarVisible(!isViewMode);
        itemDetailsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        itemDetailsTable.setFilterFieldVisible(ConstantsUtils.CHECK_RECORD, false);
        itemDetailsTable.setPageLength(NumericConstants.SEVEN);
        itemDetailsTable.setImmediate(true);
        itemDetailsTable.setSizeFull();

        if (!isViewMode) {
            itemDetailsTable.setSelectable(true);
            itemDetailsTable.setColumnCheckBox(ConstantsUtils.CHECK_RECORD, true, false);
            itemDetailsTable.setTableFieldFactory(new PSTableGenerator(itemDetailsTable, itemDetailsResultBean, "Add", psMaster));
            itemDetailsTable.setEditable(true);
        } else {
            itemDetailsTable.setVisibleColumns(Arrays.copyOfRange(PsUtils.ITEM_DETAILS_COL, 1, PsUtils.ITEM_DETAILS_COL.length));
            itemDetailsTable.setColumnHeaders(Arrays.copyOfRange(PsUtils.ITEM_DETAILS_COL_HEADER, 1, PsUtils.ITEM_DETAILS_COL_HEADER.length));
        }
        itemDetailsTable.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs .
             *
             * @param event the fired event .
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                // empty block
            }
        });

        itemDetailsTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {

            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                if (ConstantsUtils.CHECK_RECORD.equals(event.getPropertyId().toString())) {
                    if (event.isChecked()) {
                        try {
                            psLogic.populateLogic(userId, sessionId, tempCreatedDate, StringUtils.EMPTY, StringUtils.EMPTY, true, "Add", 0, "checked");
                            for (PSIFPDTO pSIFPDTO : itemDetailsResultBean.getItemIds()) {
                                pSIFPDTO.setCheckRecord(Boolean.TRUE);

                            }
                            // this setCurrentPage is used to refresh the
                            // companyDetailsResultLazyBean lazy conatiner
                            psPricingTableLogic.setCurrentPage(psPricingTableLogic.getCurrentPage());
                            itemDetailsTable.setColumnCheckBox(ConstantsUtils.CHECK_RECORD, true, true);
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
                            psPricingTableLogic.setCurrentPage(psPricingTableLogic.getCurrentPage());
                            itemDetailsTable.setColumnCheckBox(ConstantsUtils.CHECK_RECORD, true, false);
                        } catch (Exception ex) {
                            logger.error(ex);
                        }
                    }
                }
            }
        });

    }

    public void refreshTable() {
        psPricingTableLogic.setSearchData(itemDetailsResultBean, binder, mode, psMaster, sessionDTO);
        psPricingTableLogic.setCurrentPage(1);
    }

    public void clearTable() {
        psPricingTableLogic.clearAll();
    }

    public void enableOrDisableMassCheck(String values) {
        massCheck.setValue(values);
    }

    /**
     * Adds the btn populate.
     *
     * @return the button
     * @throws Exception the exception
     */
    public void configurePopulateBtn() {

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

        btnPopulate.addClickListener(new ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final ClickEvent event) {
                logger.debug("Entering PSTabsheetForm Populate operation");
                try {

                    binder.getErrorDisplay().clearError();

                    binder.commit();
                    String massPopulateValue = massValue.getValue().trim();

                    if (massField.getValue() != null) {

                        if (StringUtils.isNotBlank(massPopulateValue)) {
                            if (!massPopulateValue.matches(ValidationUtils.REBATE_SCHEDULE_AMT_DOUBLE)) {
                                binder.getErrorDisplay().setError(massField.getValue() + " can contain only digits");
                                massValue.setValue(StringUtils.EMPTY);
                                return;
                            } else if (!massPopulateValue.matches(ValidationUtils.REBATE_SCHEDULE_AMT_SAVE)) {
                                binder.getErrorDisplay().setError(massField.getValue() + " should be less than NumericConstants.FIFTEEN digits with 2 decimal places");
                                massValue.setValue(StringUtils.EMPTY);
                                return;
                            }
                        }
                        if (!massValue.getValue().trim().isEmpty()
                                || (massSelect.getValue() != null)
                                || (massPriceType.getValue() != null && (((HelperDTO) massPriceType.getValue()).getId()) != 0)
                                || massDate.getValue() != null) {
                            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
                            String sessionId = String.valueOf(sessionDTO.getUiSessionId());
                            String tempCreatedDate = String.valueOf(sessionDTO.getSessionDate());
                            if (itemDetailsResultBean.size() > 0) {
                                psLogic.saveToTemp(itemDetailsResultBean.getItemIds(), binder);
                                itemDetailsResultBean.removeAllItems();
                            }

                            List<Object> itemList = psLogic.validateNull(userId, sessionId, tempCreatedDate, "tempCheckedCount");
                            if (itemList.isEmpty()) {
                                final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Populate Error", "Please select an item to populate", new MessageBoxListener() {
                                    /**
                                     * The method is triggered when a button of
                                     * the message box is pressed .
                                     *
                                     * @param buttonId The buttonId of the
                                     * pressed button.
                                     */
                                    @SuppressWarnings("PMD")
                                    public void buttonClicked(final ButtonId buttonId) {
                                        // Do Nothing     
                                    }
                                }, ButtonId.OK);
                                msg.getButton(ButtonId.OK).focus();
                                return;
                            }
                            if (massField.getValue() != null && (massValue.getValue() != null || massDate.getValue() != null)) {
                                final String populateField;
                                final String populateValue;
                                final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                                fieldMass = massField.getValue().toString();
                                if ("Price Protection Start Date".equals(fieldMass)) {
                                    populateField = "PS_DETAILS_PRIC_PRTCN_STDATE";
                                    populateValue = fmt.format(massDate.getValue());
                                } else if (ConstantsUtils.PRIC.equals(fieldMass)) {
                                    populateField = "PS_DETAILS_PRICE";
                                    populateValue = String.valueOf(massValue.getValue());
                                } else if ("Price Protection End Date".equals(fieldMass)) {
                                    populateField = "PS_DETAILS_PRIC_PRTCN_EDDATE";
                                    populateValue = fmt.format(massDate.getValue());
                                } else if (ConstantsUtils.CP_START_DATE.equals(fieldMass)) {
                                    populateField = "PS_DTLS_CONT_PRICE_STARTDATE";
                                    populateValue = fmt.format(massDate.getValue());
                                } else if (ConstantsUtils.CP_END_DATE.equals(fieldMass)) {
                                    populateField = "PS_DTLS_CONT_PRICE_ENDDATE";
                                    populateValue = fmt.format(massDate.getValue());
                                } else if ("Base Price".equals(fieldMass)) {
                                    populateField = "PS_DETAILS_BASE_PRICE";
                                    populateValue = String.valueOf(massValue.getValue());
                                } else if ("Contract Price".equals(fieldMass)) {
                                    populateField = "PS_DETAILS_CONTRACT_PRICE";
                                    populateValue = String.valueOf(massValue.getValue());
                                } else if (ConstantsUtils.PRICE_TYPE1.equals(fieldMass)) {
                                    populateField = "PS_DETAILS_PRICETYPE";
                                    populateValue = String.valueOf(((HelperDTO) massPriceType.getValue()).getId());
                                } else if ("Price Tolerance".equals(fieldMass)) {
                                    populateField = "PS_DETAILS_PRICE_TOLERANCE";
                                    populateValue = String.valueOf(massValue.getValue());
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
                                    populateValue = fmt.format(massDate.getValue());
                                } else if (ConstantsUtils.STATIUS.equals(fieldMass)) {
                                    populateField = "STATUS";
                                    populateValue = String.valueOf(((HelperDTO) massSelect.getValue()).getId());
                                } else if (Constants.SUGGESTED_PRICE.equals(fieldMass)) {
                                    populateField = "PS_DETAILS_SUGGESTED_PRICE";
                                    populateValue = String.valueOf(massValue.getValue());

                                } else {
                                    populateField = StringUtils.EMPTY;
                                    populateValue = StringUtils.EMPTY;
                                }
                                psLogic.populateLogic(userId, sessionId, tempCreatedDate, populateField, populateValue, false, "Add", 0, "Populate");
                                psPricingTableLogic.setCurrentPage(psPricingTableLogic.getCurrentPage());
                                logger.debug("Ending POPULATE method");
                            }
                            massValue.setValue(StringUtils.EMPTY);
                            massDate.setValue(null);
                            massField.setValue(StringUtils.EMPTY);

                        } else {
                            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Populate Error", "Please enter value for the " + massField.getValue(), new MessageBoxListener() {
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
                        }
                    } else {
                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Populate Error", "Please Select a field to Populate", new MessageBoxListener() {
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
                    }
                } catch (FieldGroup.CommitException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    logger.error(errorMsg);
                } catch (SystemException ex) {

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
                    logger.error(exception);
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
     * Adds the all btn populate.
     *
     * @return the button
     * @throws Exception the exception
     */
    public void configurePopulateAllBtn() {

        btnAllPopulate.setReadOnly(true);
        btnAllPopulate.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs .
             *
             * @param event the fired event .
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                // event the fired event.
            }
        });

        btnAllPopulate.addClickListener(new ClickListener() {
            /**
             * Method used to populate all button logic and its listener.
             *
             * @return the void.
             */
            public void buttonClick(final ClickEvent event) {
                logger.debug("Entering PSTabsheetForm Populate All operation");
                try {

                    if (massField.getValue() != null) {

                        binder.getErrorDisplay().clearError();
                        binder.commit();
                        String massPopulateValue = massValue.getValue().trim();
                        if (StringUtils.isNotBlank(massPopulateValue)) {
                            if (!massPopulateValue.matches(ValidationUtils.REBATE_SCHEDULE_AMT_DOUBLE)) {
                                binder.getErrorDisplay().setError(massField.getValue() + " can contain only digits");
                                massValue.setValue(StringUtils.EMPTY);
                                return;
                            } else if (!massPopulateValue.matches(ValidationUtils.REBATE_SCHEDULE_AMT_SAVE)) {
                                binder.getErrorDisplay().setError(massField.getValue() + " should be less than NumericConstants.FIFTEEN digits with 2 decimal places");
                                massValue.setValue(StringUtils.EMPTY);
                                return;
                            }
                        }
                        Object priceTypeValue = null;
                        if (massPriceType.getValue() != null) {
                            priceTypeValue = ((HelperDTO) massPriceType.getValue()).getId() == 0 || (ConstantsUtils.SELECT_ONE).equals(String.valueOf((HelperDTO) massPriceType.getValue())) ? null : massPriceType.getValue();
                        }
                        Object massSelectValue = null;
                        if (massSelect.getValue() != null) {
                            massSelectValue = (ConstantsUtils.SELECT_ONE).equals(String.valueOf(massSelect.getValue())) ? null : massSelect.getValue();
                        }
                        if (!massValue.getValue().trim().isEmpty() || massSelectValue != null || priceTypeValue != null || massDate.getValue() != null) {
                            if (itemDetailsResultBean.size() > 0) {
                                psLogic.saveToTemp(itemDetailsResultBean.getItemIds(), binder);
                                itemDetailsResultBean.removeAllItems();
                            }
                            if (massField.getValue() != null && (massValue.getValue() != null || massDate.getValue() != null)) {
                                final String populateField;
                                final String populateValue;
                                final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                                fieldMass = massField.getValue().toString();
                                if ("Price Protection Start Date".equals(fieldMass)) {
                                    populateField = "PS_DETAILS_PRIC_PRTCN_STDATE";
                                    populateValue = fmt.format(massDate.getValue());
                                } else if (ConstantsUtils.PRIC.equals(fieldMass)) {
                                    populateField = "PS_DETAILS_PRICE";
                                    populateValue = String.valueOf(massValue.getValue());
                                } else if (Constants.SUGGESTED_PRICE.equals(fieldMass)) {
                                    populateField = "PS_DETAILS_SUGGESTED_PRICE";
                                    populateValue = String.valueOf(massValue.getValue());
                                } else if ("Price Protection End Date".equals(fieldMass)) {
                                    populateField = "PS_DETAILS_PRIC_PRTCN_EDDATE";
                                    populateValue = fmt.format(massDate.getValue());
                                } else if (ConstantsUtils.CP_START_DATE.equals(fieldMass)) {
                                    populateField = "PS_DTLS_CONT_PRICE_STARTDATE";
                                    populateValue = fmt.format(massDate.getValue());
                                } else if (ConstantsUtils.CP_END_DATE.equals(fieldMass)) {
                                    populateField = "PS_DTLS_CONT_PRICE_ENDDATE";
                                    populateValue = fmt.format(massDate.getValue());
                                } else if ("Base Price".equals(fieldMass)) {
                                    populateField = "PS_DETAILS_BASE_PRICE";
                                    populateValue = String.valueOf(massValue.getValue());
                                } else if ("Contract Price".equals(fieldMass)) {
                                    populateField = "PS_DETAILS_CONTRACT_PRICE";
                                    populateValue = String.valueOf(massValue.getValue());
                                } else if (ConstantsUtils.PRICE_TYPE1.equals(fieldMass)) {
                                    populateField = "PS_DETAILS_PRICETYPE";
                                    populateValue = String.valueOf(((HelperDTO) massPriceType.getValue()).getId());
                                } else if ("Price Tolerance".equals(fieldMass)) {
                                    populateField = "PS_DETAILS_PRICE_TOLERANCE";
                                    populateValue = String.valueOf(massValue.getValue());
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
                                    populateValue = fmt.format(massDate.getValue());
                                } else if (ConstantsUtils.STATIUS.equals(fieldMass)) {
                                    populateField = "STATUS";
                                    populateValue = String.valueOf(((HelperDTO) massSelect.getValue()).getId());
                                } else {
                                    populateField = StringUtils.EMPTY;
                                    populateValue = StringUtils.EMPTY;
                                }
                                psLogic.populateLogic(userId, sessionId, tempCreatedDate, populateField, populateValue, true, "Add", 0, "Populate");
                                psPricingTableLogic.setCurrentPage(psPricingTableLogic.getCurrentPage());
                            }
                        } else {
                            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Populate Error", "Please enter value for the " + massField.getValue(), new MessageBoxListener() {
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
                        }
                    } else {
                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Populate Error", "Please Select a field to Populate", new MessageBoxListener() {
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
                    }
                    massValue.setValue(StringUtils.EMPTY);
                    massDate.setValue(null);
                    massField.setValue(StringUtils.EMPTY);
                } catch (FieldGroup.CommitException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    logger.error(errorMsg);
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
                logger.debug("Ending PSTabsheetForm Populate All operation");
            }
        });

    }

    /**
     * Mass check on change event.
     *
     * @param value the value
     * @throws Exception the exception
     */
    protected void massCheckOnChangeEvent(final Object value) {

        if (!String.valueOf(value).isEmpty() && Constants.ENABLE.equals(String.valueOf(value))) {
            valueforddlb.setEnabled(true);
            massField.setEnabled(true);
            btnPopulate.setEnabled(true);
            btnAllPopulate.setEnabled(true);
        } else if (value != null && Constants.DISABLE.equals(value.toString())) {
            massValue.setValue(StringUtils.EMPTY);
            massDate.setValue(null);
            massSelect.setVisible(false);
            massField.setEnabled(false);
            massValue.setVisible(false);
            massDate.setVisible(false);
            btnPopulate.setEnabled(false);
            btnAllPopulate.setEnabled(false);
            massPriceType.setVisible(false);
            if (resultBeans != null) {
                final List<PSIFPDTO> psBeanList = resultBeans.getItemIds();
                psIfpList.clear();
                for (int i = 0; i < psBeanList.size(); i++) {
                    final PSIFPDTO dto = psBeanList.get(i);

                    if (dto.getCheckRecord()) {
                        dto.setCheckRecord(Boolean.FALSE);
                    }
                    psIfpList.add(dto);
                }

                if (!psIfpList.isEmpty()) {
                    resultBeans.removeAllItems();
                    resultBeans.addAll(psIfpList);
                }
            }
        }

    }

    public void commitItemDetailsTable() {
        itemDetailsTable.commit();
    }

    public OptionGroup getMassCheck() {
        return massCheck;
    }

    public ComboBox getMassField() {
        return massField;
    }

    public PopupDateField getMassDate() {
        return massDate;
    }

    public TextField getMassValue() {
        return massValue;
    }

    public ComboBox getMassSelect() {
        return massSelect;
    }

    public ComboBox getMassPriceType() {
        return massPriceType;
    }

    public BeanItemContainer<PSIFPDTO> getItemDetailsResultBean() {
        return itemDetailsResultBean;
    }

    public ExtPagedTable<?> getItemDetailsTable() {
        return itemDetailsTable;
    }

    public void loadItemDetailsTable() {
        psPricingTableLogic.clearFilters();
        psPricingTableLogic.clearSortByColumns();
        psPricingTableLogic.setSearchData(itemDetailsResultBean, binder, mode, psMaster, sessionDTO);
        psPricingTableLogic.setCurrentPage(1);
    }

    public void removeAndDisablingComponents() {
        root.removeComponent(massLayout);

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
            logger.error(e);
        }

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
    public void excelExportLogic() throws PortalException, SystemException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        logger.debug("Entering excelExportLogic");
        if (itemDetailsResultBean.size() > 0) {
            psLogic.saveToTemp(itemDetailsResultBean.getItemIds(), binder);
            itemDetailsResultBean.removeAllItems();
        }
        createWorkSheet();
        logger.debug("Ending excelExportLogic");
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
    private void createWorkSheet() throws PortalException, SystemException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        logger.debug("Entering createWorkSheet");
        int recordCount = 0;
        List<Object[]> list = psLogic.getItemPriceDetails(null, 0, 0, binder, new ArrayList<SortByColumn>(), mode, psMaster, true);
        recordCount = Integer.valueOf(String.valueOf(list.get(0)));
        String[] header = itemDetailsTable.getColumnHeaders();
        header = (String[]) ArrayUtils.removeElement(header, StringUtils.EMPTY);
        header = (String[]) ArrayUtils.removeElement(header, ConstantsUtils.BLANK_SPACE);
        ExcelExportforBB.createWorkSheet(header, recordCount, this, getUI(), "Pricing");
        logger.debug("Ending createWorkSheet");
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws PortalException, SystemException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        logger.debug("Entering createWorkSheetContent");
        if (itemDetailsTable.size() > 0) {
            final List<SortByColumn> columns = new ArrayList<SortByColumn>();
            List<PSIFPDTO> dtoList = null;
            List<Object[]> list = psLogic.getItemPriceDetails(null, start, end, binder, columns, mode, psMaster, false);
            dtoList = getCustomizedItemPriceDTO(list, mode, psMaster);

            Object[] column = itemDetailsTable.getVisibleColumns();
            column = ArrayUtils.removeElement(column, "checkRecord");
            ExcelExportforBB.createFileContent(column, dtoList, printWriter);
            logger.debug("Ending createWorkSheetContent");
        }
    }

}
