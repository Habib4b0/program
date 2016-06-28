package com.stpl.app.global.abstractsearch.ui;

import com.stpl.app.global.abstractsearch.dto.SearchCriteriaDTO;
import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.global.abstractsearch.logic.AbstractSearchLogic;
import com.stpl.app.global.abstractsearch.logic.tablelogic.AbstractSearchTableLogic;
import com.stpl.app.global.abstractsearch.util.CommonUtils;
import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.abstractsearch.util.Message;
import com.stpl.app.global.abstractsearch.util.MessageUtil;
import com.stpl.app.global.abstractsearch.util.UIUtils;
import com.stpl.app.global.abstractsearch.util.ValidationUtil;
import com.stpl.app.global.cfp.ui.view.CFPAddView;
import com.stpl.app.global.cfp.util.CFPTestGenerator;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.company.dto.CompanyFilterGenerator;
import com.stpl.app.global.company.ui.lazyload.CompanyQualifierNameContainer;
import com.stpl.app.global.company.ui.lazyload.CompanyQualifierNameCriteria;
import com.stpl.app.global.company.ui.view.CompanyAddView;
import com.stpl.app.global.compliancededuction.logic.CDRLogic;
import com.stpl.app.global.compliancededuction.ui.util.ComplianceDeductionFilterGenerator;
import com.stpl.app.global.compliancededuction.ui.view.CDRView;
import com.stpl.app.global.deductioncalendar.logic.SelectionLogic;
import com.stpl.app.global.deductioncalendar.ui.util.DeductionFilterGenerator;
import com.stpl.app.global.deductioncalendar.ui.view.DeductionCalendarView;
import com.stpl.app.global.ifp.ui.view.IFPAddView;
import com.stpl.app.global.item.dto.ItemMasterGenerate;
import com.stpl.app.global.item.ui.lazyload.BrandContainer;
import com.stpl.app.global.item.ui.lazyload.BrandCriteria;
import com.stpl.app.global.item.ui.lazyload.ItemQualifierNameContainer;
import com.stpl.app.global.item.ui.lazyload.ItemQualifierNameCriteria;
import com.stpl.app.global.item.ui.lazyload.Ndc8Container;
import com.stpl.app.global.item.ui.lazyload.Ndc8Criteria;
import com.stpl.app.global.item.ui.lazyload.Ndc9Container;
import com.stpl.app.global.item.ui.lazyload.Ndc9Criteria;
import com.stpl.app.global.item.ui.view.ItemAddView;
import com.stpl.app.global.netsalesformula.dto.NsfFilterGenerator;
import com.stpl.app.global.netsalesformula.logic.NsfLogic;
import com.stpl.app.global.netsalesformula.ui.view.NSFView;
import com.stpl.app.global.priceschedule.dto.PSFilterGenerator;
import com.stpl.app.global.priceschedule.ui.view.PSView;
import com.stpl.app.global.rebateplan.dto.RebatePlanMasterDTO;
import com.stpl.app.global.rebateplan.logic.RebatePlanSearchLogic;
import com.stpl.app.global.rebateplan.ui.view.RebatePlanView;
import com.stpl.app.global.rebateplan.util.RPFilterGenerator;
import com.stpl.app.global.rebateschedule.dto.RSFilterGenerate;
import com.stpl.app.global.rebateschedule.ui.view.RebateScheduleAddView;
import com.stpl.app.model.RebatePlanMaster;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.IFPFilterGenerator;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.FunctionNameUtil;
import com.stpl.app.util.HelperDTO;
import com.stpl.app.util.ResponsiveUtils;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Validator;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import static com.vaadin.server.Sizeable.UNITS_PERCENTAGE;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * The Class AbstractSearchForm.
 */
public final class AbstractSearchForm extends CustomComponent {

    /**
     * The Text Box.
     */
    @UiField("text1")
    private TextField text1;

    /**
     * The Text Box.
     */
    @UiField("text2")
    private TextField text2;

    /**
     * The Text Box.
     */
    @UiField("text3")
    private TextField text3;

    /**
     * The Text Box.
     */
    @UiField("text4")
    private TextField text4;

    /**
     * The Text Box.
     */
    @UiField("text5")
    private TextField text5;

    /**
     * The Combo Box.
     */
    @UiField("combo1")
    private ComboBox combo1;

    /**
     * The Combo Box.
     */
    @UiField("combo2")
    private ComboBox combo2;

    /**
     * The Combo Box.
     */
    @UiField("text6")
    private TextField text6;

    /**
     * The Text Box.
     */
    @UiField("text8")
    private TextField text8;

    /**
     * The Text Box.
     */
    @UiField("text9")
    private TextField text9;

    /**
     * The Combo Box.
     */
    @UiField("combo3")
    private ComboBox combo3;

    /**
     * The Text Box.
     */
    @UiField("combo4")
    private ComboBox combo4;

    /**
     * The Text Box.
     */
    @UiField("combo5")
    private ComboBox combo5;

    /**
     * The Text Box.
     */
    @UiField("text7")
    private TextField text7;

    /**
     * The Combo Box.
     */
    @UiField("combo6")
    private ComboBox combo6;

    /**
     * The Combo Box.
     */
    @UiField("combo7")
    private ComboBox combo7;

    /**
     * The Combo Box.
     */
    @UiField("combo8")
    private ComboBox combo8;

    /**
     * The Button.
     */
    @UiField("searchBtn")
    private Button searchBtn;

    /**
     * The Button.
     */
    @UiField("resetBtn")
    private Button resetBtn;

    @UiField("addBtn")
    private Button addBtn;

    @UiField("editBtn")
    private Button editBtn;

    @UiField("viewBtn")
    private Button viewBtn;

    /**
     * The ErrorLabel.
     */
    @UiField("errorMsg")
    public ErrorLabel errorMsg;

    /**
     * Layout
     */
    @UiField("searchLayout")
    private VerticalLayout searchLayout;

    /**
     * The Label
     */
    @UiField("label1")
    private Label label1;

    /**
     * The Label
     */
    @UiField("label2")
    private Label label2;

    /**
     * The Label
     */
    @UiField("label3")
    private Label label3;

    /**
     * The Label
     */
    @UiField("label4")
    private Label label4;

    /**
     * The Label
     */
    @UiField("label5")
    private Label label5;

    /**
     * The Label
     */
    @UiField("label6")
    private Label label6;

    /**
     * The Label
     */
    @UiField("label7")
    private Label label7;

    /**
     * The Label
     */
    @UiField("label8")
    private Label label8;

    /**
     * The Label
     */
    @UiField("label9")
    private Label label9;

    /**
     * The Label
     */
    @UiField("label10")
    private Label label10;

    /**
     * The Label
     */
    @UiField("label11")
    private Label label11;

    /**
     * The Label
     */
    @UiField("label12")
    private Label label12;

    /**
     * The Label
     */
    @UiField("label13")
    private Label label13;

    /**
     * The Label
     */
    @UiField("label14")
    private Label label14;

    /**
     * The Label
     */
    @UiField("label15")
    private Label label15;

    /**
     * The Label
     */
    @UiField("label16")
    private Label label16;

    /**
     * The Label
     */
    @UiField("label17")
    private Label label17;

    @UiField("copyBtn")
    private Button copyBtn;
    @UiField("deleteBtn")
    private Button deleteBtn;

    @UiField("reset")
    private Button reset;

    @UiField("excel")
    public Button excel;

    @UiField("tableLayout")
    private VerticalLayout tableLayout;

    @UiField("controlLayout")
    private HorizontalLayout controlLayout;

    @UiField("exportBtn")
    public Button exportBtn;

    AbstractSearchTableLogic tableLogic = new AbstractSearchTableLogic();
    private ExtPagedTable resultTable = new ExtPagedTable(tableLogic);
    private BeanItemContainer<SearchResultsDTO> resultBean = new BeanItemContainer<>(SearchResultsDTO.class);
    CommonUtil commonUtil = CommonUtil.getInstance();
    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");
    private static final Logger LOGGER = Logger.getLogger(AbstractSearchForm.class);
    private final AbstractSearchLogic searchLogic = new AbstractSearchLogic();
    public static ResourceBundle columnBundle = ResourceBundle.getBundle("properties.tableColumns");
    private String moduleName = StringUtils.EMPTY;
    final CommonUtils commonsUtil = new CommonUtils();
    SessionDTO sessionDTO;
   CommonUtil commonMsg=CommonUtil.getInstance();
    /**
     * The binder.
     */
    private final ErrorfulFieldGroup binder;

    /**
     * The Constructor.
     *
     * @param moduleName
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    public AbstractSearchForm(String moduleName) throws SystemException, Exception {
        super();
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/abstractsearchform.xml"), this));
        binder = getBinder();
        try {
            this.moduleName = moduleName;
            init();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public AbstractSearchForm(String moduleName, SessionDTO sessionDTO) throws SystemException, Exception {
        super();

        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/abstractsearchform.xml"), this));
        binder = getBinder();
        this.moduleName = moduleName;
        this.sessionDTO = sessionDTO;
        try {
            init();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Inits the.
     *
     * @throws com.stpl.portal.kernel.exception.PortalException
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    public void init() throws PortalException, SystemException, Exception {
        configureFields();
        configureTable();
        securityForAllScreens();
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    public ErrorfulFieldGroup getBinder() {
        final SearchCriteriaDTO bean = new SearchCriteriaDTO();
        final ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<SearchCriteriaDTO>(bean));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
        return binder;
    }

    /**
     * Method has configuration for the fields.
     */
    public void configureFields() throws SystemException, Exception {
        try {
            LOGGER.info("Enters configureFields() ");
            configureLayout(moduleName);
            fieldValidation(moduleName);
            loadComponents(moduleName);
            for (java.lang.reflect.Field field : this.getClass().getDeclaredFields()) {
                if (field.get(this) instanceof Label && ((Label) field.get(this)).isVisible()) {
                    setLabelName(field.get(this), field.getName() + "_" + moduleName);
                    ResponsiveUtils.makeLabel((Label) field.get(this), false);

                } else if (field.get(this) instanceof TextField && ((TextField) field.get(this)).isVisible()) {

                    if (((TextField) field.get(this)).isVisible()) {
                        textValidation(field.get(this), ((TextField) field.get(this)).getData());
                        ((TextField) field.get(this)).setValidationVisible(true);

                    }
                }
            }

            excel.setIcon(excelExportImage);
            excel.setDescription("Export to excel");
            excel.setIconAlternateText("Excel export");
            excel.setHtmlContentAllowed(true);
            LOGGER.info("Exits configureFields() ");
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void configureTable() {
        List<Integer> pageLength = new ArrayList<Integer>();
        pageLength.add(10);
        pageLength.add(15);
        pageLength.add(20);
        pageLength.add(25);
        pageLength.add(50);
        pageLength.add(100);
        tableLogic.getControlConfig().setPageLengthsAndCaptions(pageLength);
        tableLayout.addComponent(resultTable);
        ResponsiveUtils.getResponsiveControls(tableLogic.createControls(), controlLayout);
        tableLayout.addComponent(controlLayout);

        tableLogic.setContainerDataSource(resultBean);
        tableLogic.setPageLength(10);
        tableLogic.sinkItemPerPageWithPageLength(false);

        setTableDefaultConfig(resultTable, ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) || ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) ? moduleName + "W" : moduleName);
        resultTable.setSelectable(true);
        resultTable.markAsDirty();
        resultTable.setComponentError(null);
        resultTable.setFilterBarVisible(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.setFilterGenerator(getFilterGeneratorClass(moduleName));
        resultTable.setValidationVisible(false);
        resultTable.addStyleName(ConstantsUtils.FILTER_BAR);

        resultTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                itemselectLogic(event);
            }
        });
    }

    public void itemselectLogic(final ItemClickEvent event) {
        try {
            if(event != null){
            if (event.getItemId() != null) {
                SearchResultsDTO searchForm = (SearchResultsDTO) event.getItemId();
                if (event.isDoubleClick()) {

                    if (searchForm.isRecordLockStatus()) {
                        if (itemStatusCheck()) {
                            sessionDTO.setMode(ConstantsUtils.VIEW);
                            if (ConstantUtil.COMPANY_MAST.equals(moduleName) || ConstantUtil.ITEM_MASTER.equals(moduleName)) {
                                getUI().getNavigator().navigateTo(CompanyAddView.NAME);
                               
                            } else if(ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
                                 getUI().getNavigator().navigateTo(CDRView.NAME);   
                            }
                            else {
                                getUI().getNavigator().navigateTo(ConstantsUtils.ADD);
                            }
                            editLogic();
                        } else {
                            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Access Denied", "You do not have permission to edit this record", new MessageBoxListener() {
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
                        sessionDTO.setMode(ConstantsUtils.EDIT);
                        if (ConstantUtil.COMPANY_MAST.equals(moduleName) || ConstantUtil.ITEM_MASTER.equals(moduleName)) {
                            getUI().getNavigator().navigateTo(CompanyAddView.NAME);
                         } else if(ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
                                 getUI().getNavigator().navigateTo(CDRView.NAME);   
                          } else if(ConstantUtil.NET_SALES_FORMULA.equals(moduleName)) {
                                 getUI().getNavigator().navigateTo(NSFView.NAME);  
                          }
                        else {
                            getUI().getNavigator().navigateTo(ConstantsUtils.ADD);
                        }
                        editLogic();
                    }
                    
                }
                sessionDTO.setSystemId(Integer.valueOf(searchForm.getSystemID()));
            }
            resultTable.setValue(null);
            }
        } catch (Exception e) {
            LOGGER.error(e);

        }
    }

    public boolean itemStatusCheck() {
        try {

            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            boolean etlCheck = commonsUtil.checkETLUser(Integer.valueOf(userId));

            return etlCheck;
        } catch (Exception ex) {
            LOGGER.error(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1015), new MessageBoxListener() {
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
        return false;
    }

    @UiHandler("searchBtn")
    public void btnSearchLogic(Button.ClickEvent event) {
        binder.getErrorDisplay().clearError();
        List<Object> collapsedColumns = new ArrayList<Object>();
        for (Object item : resultTable.getVisibleColumns()) {
            if (resultTable.isColumnCollapsed(item)) {
                collapsedColumns.add(item);
            }
        }
        if (!searchLogic.checkSearchCriteria(binder)) {
            final MessageBox msg = MessageBox.showPlain(Icon.WARN, MessageUtil.getMessage(Message.SEARCH_CRITERIA_HEADER), MessageUtil.getMessage(Message.SEARCH_CRITERIA_MESSAGE), new MessageBoxListener() {

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
        } else {

            try {
                binder.commit();
                tableLogic.configureSearchData(binder, this.moduleName, this);
                resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
                resultTable.setFilterGenerator(getFilterGeneratorClass(moduleName));
                resultTable.setImmediate(true);
                resultTable.setWidth(99, UNITS_PERCENTAGE);
                resultTable.addStyleName(ConstantsUtils.TABLE_CHECK_BOX);
                resultTable.setSelectable(true);
                if (tableLogic.isResultsEmpty()) {
                    CommonUIUtils.successNotification(ConstantUtil.NO_RESULTS_COMPLETED);
                } else {
                    CommonUIUtils.successNotification(ConstantUtil.SEARCH_COMPLETED);
                }

                resultTable.markAsDirtyRecursive();

            } catch (FieldGroup.CommitException commit) {
                tableLogic.clearAll();
                tableLogic.getFilters().clear();
                resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
                LOGGER.error(commit);
            } catch (Exception exception) {
                final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004), new MessageBoxListener() {
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
                LOGGER.error(exception);
            }
        }

    }

    /**
     * Reset Button Listener
     *
     * @param event
     */
    @UiHandler("resetBtn")
    public void btnResetLogic(Button.ClickEvent event) {

        MessageBox.showPlain(Icon.QUESTION, ConstantUtil.CONFORMATION_MSG, "Are you sure you want to reset the values in the Search Criteria group box?", new MessageBoxListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            public void buttonClicked(final ButtonId buttonId) {
                if (buttonId.name().equalsIgnoreCase(ConstantUtil.YES_VARIABLE)) {
                    LOGGER.info("Entering Reset operation");
                    binder.getErrorDisplay().clearError();
                    binder.setItemDataSource(new BeanItem<SearchCriteriaDTO>(new SearchCriteriaDTO()));
                    if (ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName)) {
                        binder.getField("text7").setEnabled(false);
                    }
                    if(ConstantUtil.NET_SALES_FORMULA.equalsIgnoreCase(moduleName) || ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equalsIgnoreCase(moduleName)){
                     binder.getErrorDisplay().clearError();
                     binder.setItemDataSource(new BeanItem<SearchCriteriaDTO>(new SearchCriteriaDTO()));
                    }else{
                    tableLogic.clearAll();
                    tableLogic.setReset(true);
                    tableLogic.setRequiredCount(true);
                    tableLogic.setCurrentPage(1);
                    tableLogic.setContainerDataSource(resultBean);
                    tableLogic.setPageLength(10);
                    tableLogic.sinkItemPerPageWithPageLength(false);

                    setTableDefaultConfig(resultTable, ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) || ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) ? moduleName + "W" : moduleName);
                    resultTable.setSelectable(true);
                    resultTable.markAsDirty();
                    resultTable.setComponentError(null);
                    resultTable.setFilterBarVisible(true);
                    resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
                    resultTable.setFilterGenerator(getFilterGeneratorClass(moduleName));
                    resultTable.setValidationVisible(false);
                    resultTable.addStyleName(ConstantsUtils.FILTER_BAR);
                    LOGGER.info("Ending Reset operation");
                }
            }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    @UiHandler("reset")
    public void ResetLogic(Button.ClickEvent event) {

        MessageBox.showPlain(Icon.QUESTION, ConstantUtil.CONFORMATION_MSG, "Are you sure you want to reset "
                + "the listview to default/previous values?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equalsIgnoreCase(ConstantUtil.YES_VARIABLE)) {
                            LOGGER.info("Entering Reset operation");
                            binder.getErrorDisplay().clearError();

                            resultTable.removeAllItems();
                            LOGGER.info("Ending Reset operation");
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
    }

    @UiHandler("text7")
    public void valueChangeItemIdentifier(final ValueChangeEvent event) {
        if (ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName)) {
            if (text7.getValidators() != null) {
                combo5.setValidationVisible(false);
            }
        }
    }

    @UiHandler("text8")
    public void valueChangeCompanyIdentifier(final ValueChangeEvent event) {
        if (ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName)) {
            if (text8.getValidators() != null) {
                combo6.setValidationVisible(false);
            }
        }
    }

    @UiHandler("combo5")
    public void valueChangeItemIrtQualifierNameDDLB(final ValueChangeEvent event) {

        if (ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName)) {
            if (event.getProperty() != null && event.getProperty().getValue() != null && StringUtils.EMPTY.equals(event.getProperty().getValue())) {
                combo6.select(ConstantUtil.SELECT_ONE);
            }
            if (combo5.getValue() == null || ConstantUtil.SELECT_ONE.equals(combo5.getValue().toString())) {
                text7.setValidationVisible(false);
                text7.setValue(StringUtils.EMPTY);
                text7.setEnabled(false);

            } else {
                text7.setEnabled(true);
            }
        }
    }

    @UiHandler("combo6")
    public void valueChangeCompanyCrtQualifierNameDDLB(final ValueChangeEvent event) {

        if (ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName)) {
            if (combo6.getValue() == null || ConstantUtil.SELECT_ONE.equals(combo6.getValue().toString())) {
                text8.setValidationVisible(false);
                text8.setValue(StringUtils.EMPTY);
                text8.setEnabled(false);

            } else {
                text8.setEnabled(true);
            }
        }
    }

    /**
     * To Validate Text Fields
     *
     * @param obj
     * @param key
     */
    private void textValidation(Object obj, Object key) {
        try {
            if (obj != null && key != null && !ConstantUtil.NULL.equals(key) && obj instanceof TextField) {
                TextField tempObj = (TextField) obj;
                String[] rules = String.valueOf(key).split(ConstantUtil.COMMA);
                if (rules[0] != null && ValidationUtil.getMessage(rules[0]) != null && StringUtils.isNotEmpty(ValidationUtil.getMessage(rules[0]))) {
                    String[] temp = ValidationUtil.getMessage(rules[0]).split(ConstantUtil.COMMA);
                    tempObj.addValidator(new StringLengthValidator(ValidationUtil.getMessage(rules[1]), Integer.valueOf(temp[0]), Integer.valueOf(temp[1]), Boolean.valueOf(temp[2])));
                }
                if (rules[2] != null && ValidationUtil.getMessage(rules[2]) != null && StringUtils.isNotEmpty(ValidationUtil.getMessage(rules[2]))) {
                    tempObj.addValidator(new RegexpValidator(ValidationUtil.getMessage(rules[2]), ValidationUtil.getMessage(rules[3])));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * To Set Label Value
     *
     * @param obj
     * @param key
     */
    private void setLabelName(Object obj, String key) {
        try {
            Label tempObj = (Label) obj;
            if (ValidationUtil.getLabel(key) != null && StringUtils.isNotEmpty(ValidationUtil.getLabel(key))) {
                tempObj.setValue(ValidationUtil.getLabel(key));
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * To get Visible Columns & Visible Headers
     *
     * @param isColumns
     * @param key
     * @return
     */
    private Object[] getColumns(boolean isColumns, String key) {
        return (columnBundle.getString(isColumns ? "columns" + key : "headers" + key)).split(ConstantUtil.COMMA);

    }

    /**
     *
     * @param resultTable
     * @param key
     */
    public void setTableDefaultConfig(ExtPagedTable resultTable, String key) {
        resultTable.setVisibleColumns(getColumns(true, ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) || ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) ? key : moduleName));
        resultTable.setColumnHeaders(Arrays.copyOf(getColumns(false, ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) || ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) ? key : moduleName), getColumns(false, ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) || ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) ? key : moduleName).length, String[].class));
        resultTable.markAsDirtyRecursive();
        resultTable.setImmediate(true);
        resultTable.setWidth(100, UNITS_PERCENTAGE);
        configureAlignment(key);
    }

    private void configureLayout(String moduleName) {

        if (ConstantUtil.ITEM_MASTER.equals(moduleName)) {
//            text8.setVisible(false);
            label17.setVisible(false);
            text9.setVisible(false);
        } else if (ConstantUtil.COMPANY_MAST.equals(moduleName)) {
            label4.setVisible(false);
            text4.setVisible(false);
            label5.setVisible(false);
            text5.setVisible(false);
            label8.setVisible(false);
            text6.setVisible(false);
            label12.setVisible(false);
            text7.setVisible(false);
            label14.setVisible(false);
            combo7.setVisible(false);
            label15.setVisible(false);
            combo8.setVisible(false);
            label17.setVisible(false);
            text9.setVisible(false);
        } else if (ConstantUtil.COMPANY_FAMILY_PLAN.equals(moduleName) || ConstantUtil.ITEM_FAMILY_PLAN.equals(moduleName) || ConstantUtil.PRICE_SCHEDULE_MASTER.equals(moduleName)) {

            label4.setVisible(false);
            text4.setVisible(false);
            label5.setVisible(false);
            text5.setVisible(false);
            label9.setVisible(false);
            combo3.setVisible(false);
            label10.setVisible(false);
            combo4.setVisible(false);
            label11.setVisible(false);
            combo5.setVisible(false);
            label13.setVisible(false);
            combo6.setVisible(false);
            label14.setVisible(false);
            combo7.setVisible(false);
            label15.setVisible(false);
            combo8.setVisible(false);
            label17.setVisible(false);
            text9.setVisible(false);

        } else if (ConstantUtil.REBATE_SCHEDULE_MASTER.equals(moduleName)) {
            label4.setVisible(false);
            text4.setVisible(false);
            label5.setVisible(false);
            text5.setVisible(false);
            label8.setVisible(false);
            text6.setVisible(false);
            label11.setVisible(false);
            combo5.setVisible(false);
            label15.setVisible(false);
            combo8.setVisible(false);
        } else if (ConstantUtil.REBATE_PLAN.equals(moduleName)) {
            label4.setVisible(false);
            text4.setVisible(false);
            label5.setVisible(false);
            text5.setVisible(false);
            label9.setVisible(false);
            combo3.setVisible(false);
            label10.setVisible(false);
            combo4.setVisible(false);
            label11.setVisible(false);
            combo5.setVisible(false);
            label13.setVisible(false);
            combo6.setVisible(false);
            label14.setVisible(false);
            combo7.setVisible(false);
            label15.setVisible(false);
            combo8.setVisible(false);
            text6.setVisible(false);
            text7.setVisible(false);
            text8.setVisible(false);
            text9.setVisible(false);
            label12.setVisible(false);
            label16.setVisible(false);
            label17.setVisible(false);
            text9.setVisible(false);
            copyBtn.setVisible(true);
            deleteBtn.setVisible(true);
            reset.setVisible(true);
        } else if (ConstantUtil.NET_SALES_FORMULA.equals(moduleName)) {
            label1.setVisible(false);
            text1.setVisible(false);
            label2.setVisible(false);
            text2.setVisible(false);
            label4.setVisible(false);
            text4.setVisible(false);
            label3.setVisible(false);
            text3.setVisible(false);
            label5.setVisible(false);
            text5.setVisible(false);
            label9.setVisible(false);
            label6.setVisible(false);
            combo1.setVisible(false);
            combo3.setVisible(false);
            label10.setVisible(false);
            combo4.setVisible(false);
            label11.setVisible(false);
            combo5.setVisible(false);
            label13.setVisible(false);
            combo6.setVisible(false);
            label14.setVisible(false);
            combo7.setVisible(false);
            label15.setVisible(false);
            combo8.setVisible(false);
            label17.setVisible(false);
            text9.setVisible(false);
            copyBtn.setVisible(true);
            deleteBtn.setVisible(true);
            reset.setVisible(true);
        } else if (ConstantUtil.DEDUCTION_CALENDAR.equals(moduleName)) {
            label4.setVisible(false);
            text4.setVisible(false);
            label5.setVisible(false);
            text5.setVisible(false);
            label7.setVisible(false);
            combo2.setVisible(false);
            label8.setVisible(false);
            text6.setVisible(false);
            label9.setVisible(false);
            combo3.setVisible(false);
            label10.setVisible(false);
            combo4.setVisible(false);
            label11.setVisible(false);
            combo5.setVisible(false);
            label12.setVisible(false);
            text7.setVisible(false);
            label13.setVisible(false);
            combo6.setVisible(false);
            label14.setVisible(false);
            combo7.setVisible(false);
            label15.setVisible(false);
            combo8.setVisible(false);
            label16.setVisible(false);
            text8.setVisible(false);
            label17.setVisible(false);
            text9.setVisible(false);
            excel.setVisible(true);
            copyBtn.setVisible(true);
            deleteBtn.setVisible(true);
            reset.setVisible(true);
            exportBtn.setVisible(false);

        } else if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
            label1.setVisible(false);
            text1.setVisible(false);
            label2.setVisible(false);
            text2.setVisible(false);
            label3.setVisible(false);
            text3.setVisible(false);
            label4.setVisible(false);
            text4.setVisible(false);
            label5.setVisible(false);
            text5.setVisible(false);
            label7.setVisible(false);
            combo2.setVisible(false);
            label9.setVisible(false);
            combo3.setVisible(false);
            label10.setVisible(false);
            combo4.setVisible(false);
            label11.setVisible(false);
            combo5.setVisible(false);
            label14.setVisible(false);
            combo7.setVisible(false);
            label15.setVisible(false);
            combo8.setVisible(false);
            label16.setVisible(false);
            text8.setVisible(false);
            label17.setVisible(false);
            text9.setVisible(false);
            copyBtn.setVisible(true);
            deleteBtn.setVisible(true);
            reset.setVisible(true);
            }
        }

    private void loadComponents(String moduleName) throws Exception {
        if (ConstantUtil.ITEM_MASTER.equals(moduleName)) {
            commonsUtil.loadComboBox(combo1, UIUtils.STATUS);
            commonsUtil.loadComboBox(combo2, UIUtils.ITEM_TYPE);
            final LazyContainer ndc9Container = new LazyContainer(HelperDTO.class, new Ndc9Container(), new Ndc9Criteria());
            commonsUtil.loadLazyComboBox(combo3, ndc9Container);
            commonsUtil.loadComboBox(combo4, UIUtils.FORM1);
            final LazyContainer container = new LazyContainer(HelperDTO.class, new ItemQualifierNameContainer(false), new ItemQualifierNameCriteria());
            commonsUtil.loadLazyComboBox(combo5, container);
            final LazyContainer identifierTypeDescContainer = new LazyContainer(HelperDTO.class, new BrandContainer(false), new BrandCriteria());
            commonsUtil.loadLazyComboBox(combo6, identifierTypeDescContainer);
            final LazyContainer ndc8Container = new LazyContainer(HelperDTO.class, new Ndc8Container(), new Ndc8Criteria());
            commonsUtil.loadLazyComboBox(combo7, ndc8Container);
            commonsUtil.loadComboBox(combo8, UIUtils.STRENGTH1);
        } else if (ConstantUtil.COMPANY_MAST.equals(moduleName)) {

            commonsUtil.loadComboBox(combo1, UIUtils.STATUS);
            commonsUtil.loadComboBox(combo2, UIUtils.COMPANY_TYPE);
            commonsUtil.loadComboBox(combo3, UIUtils.COMPANY_CATEGORY);
            commonsUtil.loadComboBox(combo4, UIUtils.COMPANY_GROUP);
            commonsUtil.loadComboBox(combo5, UIUtils.COMPANY_TRADE_CLASS);
            final LazyContainer identifierTypeDescContainer = new LazyContainer(HelperDTO.class, new CompanyQualifierNameContainer(false), new CompanyQualifierNameCriteria());
            commonsUtil.loadLazyComboBox(combo6, identifierTypeDescContainer);
        } else if (ConstantUtil.COMPANY_FAMILY_PLAN.equals(moduleName)) {
            commonsUtil.loadComboBox(combo1, UIUtils.CFP_TYPES);
            commonsUtil.loadComboBox(combo2, UIUtils.STATUS);
        } else if (ConstantUtil.ITEM_FAMILY_PLAN.equals(moduleName)) {
            commonsUtil.loadComboBox(combo1, UIUtils.STATUS);
            commonsUtil.loadComboBox(combo2, UIUtils.HELPER_IFP_TYPE);
        } else if (ConstantUtil.PRICE_SCHEDULE_MASTER.equals(moduleName)) {
            commonsUtil.loadComboBox(combo1, UIUtils.STATUS);
            commonsUtil.loadComboBox(combo2, UIUtils.PS_TYPE);
        } else if (ConstantUtil.REBATE_SCHEDULE_MASTER.equals(moduleName)) {
            commonsUtil.loadComboBox(combo1, UIUtils.REBATE_SCHEDULE_TYPE);
            commonsUtil.loadComboBox(combo2, UIUtils.STATUS);
            commonsUtil.loadComboBox(combo3, UIUtils.REBATE_PROGRAM_TYPE);
            commonsUtil.loadComboBox(combo4, UIUtils.REBATE_SCHEDULE_CATEGORY);
            commonsUtil.loadComboBox(combo6, UIUtils.REBATE_FREQUENCY);
            commonsUtil.loadComboBox(combo7, UIUtils.CALCULATION_TYPE);
        } else if (ConstantUtil.REBATE_PLAN.equals(moduleName)) {
            commonsUtil.loadComboBox(combo1, UIUtils.STATUS);
            commonsUtil.loadComboBox(combo2, UIUtils.REBATE_PLAN_TYPE);
        } else if (ConstantUtil.NET_SALES_FORMULA.equals(moduleName)) {
            commonsUtil.loadComboBox(combo2, UIUtils.NS_FORMULA_TYPE);
        } else if (ConstantUtil.DEDUCTION_CALENDAR.equals(moduleName)) {
            commonsUtil.loadComboBox(combo1, UIUtils.DEDUCTION_CALENDAR_CATEGORY);
        } else if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
            commonsUtil.loadComboBox(combo1, UIUtils.RULE_TYPE);
            commonsUtil.loadComboBox(combo6, UIUtils.RULE_CATEGORY);
        }
       
    }

    private void fieldValidation(String moduleName) {

        if (ConstantUtil.ITEM_MASTER.equals(moduleName)) {
            text1.setData("maxlengthvalidation,maxlengthvalidationsystemid,specialcharvalidation,specialcharvalidationsystemid");
            text2.setData("maxlengthvalidation,maxlengthvalidationitemid,specialcharvalidation,specialcharvalidationitemid");
            text3.setData("maxlengthvalidationfifty,maxlengthvalidationitemno,specialcharvalidation,specialcharvalidationitemno");
            text4.setData("maxlengthvalidationhundred,maxlengthvalidationitemname,specialcharvalidation,specialcharvalidationitemname");
            text5.setData("maxlengthvalidationtwofifty,maxlengthvalidationitemdesc,alphaNumericCharWithoutStar,allowedSpwecialCharacters");
            text7.setData("maxlengthvalidation,maxlengthvalidationitemidentifier,specialcharvalidation,specialcharvalidationitemidentifier");
            text2.focus();
            text7.setEnabled(false);
            text7.setValidationVisible(true);
            text7.addValidator(new BeanValidator(SearchCriteriaDTO.class, "text7"));
            text7.addValidator(new IdentifierValidator());
        } else if (ConstantUtil.COMPANY_MAST.equals(moduleName)) {
            text1.setData("maxlengthvalidation,maxlengthvalidationcompanyid,alphaNumericChars,alphaNumericCharsMessage");
            text2.setData("maxlengthvalidationfifty,maxlengthvalidationcompanyno,null,null");
            text3.setData("maxlengthvalidationhundred,maxlengthvalidationcompanyname,null,null");
            text8.setData("maxlengthvalidationhundred,maxlengthvalidationcompanyidentifier,alphaNumericChars,alphaNumericCharsMessage");
            text1.focus();
            text8.setEnabled(false);
            text8.setValidationVisible(true);
            text8.addValidator(new BeanValidator(SearchCriteriaDTO.class, "text8"));
            text8.addValidator(new CompanyIdentifierValidator());
        } else if (ConstantUtil.COMPANY_FAMILY_PLAN.equals(moduleName)) {
            text1.focus();
            text1.setData("maxlengthvalidation,maxlengthvalidationcompanyfamilyplanid,null,null");
            text2.setData("maxlengthvalidationfifty,maxlengthvalidationcompanyfamilyplanno,null,null");
            text3.setData("maxlengthvalidationhundred,maxlengthvalidationcompanyfamilyplanname,null,null");
            text6.setData("maxlengthvalidation,maxlengthvalidationcompanyid,alphaNumericChars,alphaNumericCharsMessage");
            text7.setData("maxlengthvalidationfifty,maxlengthvalidationcompanyno,alphaNumericChars,alphaNumericCharsMessage");
            text8.setData("maxlengthvalidationhundred,maxlengthvalidationcompanyname,alphaNumericChars,alphaNumericCharsMessage");
        } else if (ConstantUtil.ITEM_FAMILY_PLAN.equals(moduleName)) {
            text1.focus();
            text1.setData("maxlengthvalidationfifty,maxlengthvalidationitemfamilyplanid,alphaNumericChars,alphaNumericCharsMessage");
            text2.setData("maxlengthvalidationfifty,maxlengthvalidationitemfamilyplanno,alphaNumericChars,alphaNumericCharsMessage");
            text3.setData("maxlengthvalidationhundred,maxlengthvalidationitemfamilyplanname,alphaNumericChars,alphaNumericCharsMessage");
            text6.setData("maxlengthvalidationfifty,maxlengthvalidationitemid,alphaNumericChars,alphaNumericCharsMessage");
            text7.setData("maxlengthvalidationfifty,maxlengthvalidationifpitemno,alphaNumericChars,alphaNumericCharsMessage");
            text8.setData("maxlengthvalidationhundred,maxlengthvalidationifpitemname,alphaNumericChars,alphaNumericCharsMessage");
        } else if (ConstantUtil.PRICE_SCHEDULE_MASTER.equals(moduleName)) {
            text1.focus();
            text1.setData("maxlengthvalidation,maxlengthvalidationpricescheduleid,null,null");
            text2.setData("maxlengthvalidationfifty,maxlengthvalidationpricescheduleno,null,null");
            text3.setData("maxlengthvalidationhundred,maxlengthvalidationpriceschedulename,null,null");

            text6.setData("maxlengthvalidationhundred,maxlengthvalidationitemidhun,null,null");
            text7.setData("maxlengthvalidationhundred,maxlengthvalidationitemnohun,null,null");
            text8.setData("maxlengthvalidationhundred,maxlengthvalidationitemnamehun,null,null");

        } else if (ConstantUtil.REBATE_SCHEDULE_MASTER.equals(moduleName)) {
            text1.focus();
            text1.setData("maxlengthvalidationfifty,maxlengthvalidationrebatescheduleid,null,null");
            text2.setData("maxlengthvalidationfifty,maxlengthvalidationrebatescheduleno,null,null");
            text3.setData("maxlengthvalidationhundred,maxlengthvalidationrebateschedulename,null,null");

        } else if (ConstantUtil.REBATE_PLAN.equals(moduleName)) {
            text1.focus();
            text1.setData("maxlengthvalidation,maxlengthvalidationrebateplanid,null,null");
            text2.setData("maxlengthvalidationfifty,maxlengthvalidationrebateplanno,null,null");
            text3.setData("maxlengthvalidationhundred,maxlengthvalidationrebateplanname,null,null");
        } else if (ConstantUtil.NET_SALES_FORMULA.equals(moduleName)) {
            text6.setData("null,null,alphaNumericChars,alphaNumericCharsMessage");
            text7.setData("null,null,alphaNumericChars,alphaNumericCharsMessage");
            text8.setData("null,null,alphaNumericChars,alphaNumericCharsMessage");
        } else if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
            combo1.focus();
            text6.setData("null,null,specialcharvalidation,specialcharvalidationruleno");
            text7.setData("null,null,specialcharvalidation,specialcharvalidationrulename");
        } else if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
            text1.focus();
            text1.setData("null,null,speccharvalidation,allowedspecialchar");
            text2.setData("null,null,speccharvalidation,allowedspecialchar");
            text3.setData("null,null,speccharvalidation,allowedspecialchar");
        }

        }

    private void configureAlignment(String key) {
        if (moduleName.equals(ConstantUtil.DEDUCTION_CALENDAR)) {
            List<Object> coloumnList = new ArrayList<Object>();
            coloumnList = Arrays.asList(getColumns(true, ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) || ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) ? key : moduleName));
            for (Object object : coloumnList) {
                if (object.equals("dcCreationDate") || object.equals("dcModifiedDate")) {
                    resultTable.setColumnAlignment(object.toString(), ExtPagedTable.Align.CENTER);
                }
            }
        }else if (moduleName.equals(ConstantUtil.COMPLIANCE_DEDUCTION_RULES) || moduleName.equals(ConstantUtil.NET_SALES_FORMULA) ||moduleName.equals(ConstantUtil.REBATE_PLAN)) {
            List<Object> coloumnList = new ArrayList<Object>();
            coloumnList = Arrays.asList(getColumns(true, ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) || ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) ? key : moduleName));
            for (Object object : coloumnList) {
                if (object.equals("cdrCreatedDate") || object.equals("cdrModifiedDate")) {
                    resultTable.setConverter(object.toString(), new DateToStringConverter());
                    resultTable.setColumnAlignment(object.toString(), ExtPagedTable.Align.CENTER);
                }
                if (object.equals("nsfcreatedDate") || object.equals("nsfmodifiedDate")) {
                    resultTable.setConverter(object.toString(), new DateToStringConverter());
                    resultTable.setColumnAlignment(object.toString(), ExtPagedTable.Align.CENTER);
            }
             if (object.equals("rpCreatedDate") || object.equals("rpModifiedDate")) {
                    resultTable.setConverter(object.toString(), new DateToStringConverter());
                    resultTable.setColumnAlignment(object.toString(), ExtPagedTable.Align.CENTER);
        }
    }
    }
    }

    /**
     * The Class IdentifierValidator.
     */
    @SuppressWarnings(ConstantUtil.RAWTYPES)
    private class IdentifierValidator extends AbstractValidator {

        /**
         * The Constructor.
         */
        public IdentifierValidator() {
            super("");
        }

        /**
         * The Constructor.
         *
         * @param errorMessage the error message
         */
        public IdentifierValidator(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Validates the value.
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         */
        @Override
        public void validate(final Object value) throws Validator.InvalidValueException {

            markAsDirty();
            if (!isValidValue(value) && hasValue(combo5.getValue())) {
                throw new Validator.InvalidValueException("Both item Identifier and item IRT Qualifier Name should be present");
            }
        }

        /**
         * Validates the value.
         *
         * @param value the value
         * @return true, if checks if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {

            if (hasValue(combo5.getValue())) {
                return hasValue(text7.getValue());
            } else {
                return !hasValue(text7.getValue());
            }

        }

        /**
         * Checks for value.
         *
         * @param obj the obj
         * @return true, if checks for value
         */
        private boolean hasValue(final Object obj) {
            LOGGER.info("Enters hasValue() " + obj.toString());

            if (obj == null || ConstantUtil.SELECT_ONE.equals(obj.toString())) {
                return false;
            } else {
                return !StringUtils.isEmpty(obj.toString());

            }
        }

        /**
         * Default method.
         *
         * @return the type
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    @UiHandler("addBtn")
    public void btnAddLogic(Button.ClickEvent event) {
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.GLOBAL_FILES_MODE, ConstantsUtils.ADD);
        sessionDTO.setMode(ConstantsUtils.ADD);
        if (ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName)) {
            getUI().getNavigator().navigateTo(CompanyAddView.NAME);
        }
        if (ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName)) {
            getUI().getNavigator().navigateTo(ItemAddView.NAME);
        }
        if (ConstantUtil.COMPANY_FAMILY_PLAN.equalsIgnoreCase(moduleName)) {
            getUI().getNavigator().navigateTo(CFPAddView.NAME);
        }
        if (ConstantUtil.ITEM_FAMILY_PLAN.equalsIgnoreCase(moduleName)) {
            getUI().getNavigator().navigateTo(IFPAddView.NAME);
        }
        if (ConstantUtil.PRICE_SCHEDULE_MASTER.equalsIgnoreCase(moduleName)) {
            getUI().getNavigator().navigateTo(PSView.NAME);
        }
        if (ConstantUtil.REBATE_SCHEDULE_MASTER.equalsIgnoreCase(moduleName)) {
            getUI().getNavigator().navigateTo(RebateScheduleAddView.NAME);
        }
        if (ConstantUtil.REBATE_PLAN.equalsIgnoreCase(moduleName)) {
            getUI().getNavigator().navigateTo(RebatePlanView.NAME);
        }
        if (ConstantUtil.NET_SALES_FORMULA.equals(moduleName)) {
            sessionDTO.setMode(ConstantsUtils.ADD);
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.GLOBAL_FILES_MODE, ConstantUtil.ADD);
            getUI().getNavigator().navigateTo(NSFView.NAME);
        }
        if (ConstantUtil.DEDUCTION_CALENDAR.equals(moduleName)) {
            sessionDTO.setMode(ConstantUtil.ADD);
            getUI().getNavigator().navigateTo(DeductionCalendarView.NAME);
        }
        if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
            sessionDTO.setMode(ConstantsUtils.ADD);
            sessionDTO.setSystemId(0);
            getUI().getNavigator().navigateTo(CDRView.NAME);
        }
    }

    @UiHandler("editBtn")
    public void btnEditLogic(Button.ClickEvent event) {

        if (resultTable.getValue() == null) {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Edit Error", "Please select a record to edit", new MessageBoxListener() {
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
        } else {
            SearchResultsDTO searchForm = (SearchResultsDTO) resultTable.getValue();
                if (searchForm.isRecordLockStatus()) {
                    if (itemStatusCheck()) {
                        editLogic();
                        resultTable.unselect(resultTable.getValue());
                    } else {
                        final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Access Denied", "You do not have permission to edit this record", new MessageBoxListener() {
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
                } else {
                    editLogic();
                    resultTable.unselect(resultTable.getValue());
                }
            }
        }

    public void editLogic() {
        itemselectLogic(null);
        sessionDTO.setMode(ConstantsUtils.EDIT);
        if (ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName)) {
            getUI().getNavigator().navigateTo(CompanyAddView.NAME);
        }
        if (ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName)) {
            getUI().getNavigator().navigateTo(ItemAddView.NAME);
        }
        if (ConstantUtil.COMPANY_FAMILY_PLAN.equalsIgnoreCase(moduleName)) {
            getUI().getNavigator().navigateTo(CFPAddView.NAME);
        }
        if (ConstantUtil.ITEM_FAMILY_PLAN.equalsIgnoreCase(moduleName)) {
            getUI().getNavigator().navigateTo(IFPAddView.NAME);
        }
        if (ConstantUtil.PRICE_SCHEDULE_MASTER.equalsIgnoreCase(moduleName)) {
            getUI().getNavigator().navigateTo(PSView.NAME);
        }
        if (ConstantUtil.REBATE_SCHEDULE_MASTER.equalsIgnoreCase(moduleName)) {
            getUI().getNavigator().navigateTo(RebateScheduleAddView.NAME);
        }
        if (ConstantUtil.REBATE_PLAN.equalsIgnoreCase(moduleName)) {
            getUI().getNavigator().navigateTo(RebatePlanView.NAME);
        }
        if (ConstantUtil.NET_SALES_FORMULA.equals(moduleName)) {
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.GLOBAL_FILES_MODE, ConstantUtil.EDIT);
            getUI().getNavigator().navigateTo(NSFView.NAME);
        }
        if (ConstantUtil.DEDUCTION_CALENDAR.equals(moduleName)) {
            getUI().getNavigator().navigateTo(DeductionCalendarView.NAME);
        }
        if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.GLOBAL_FILES_MODE, ConstantUtil.EDIT);
            sessionDTO.setMode(ConstantsUtils.EDIT);
            getUI().getNavigator().navigateTo(CDRView.NAME);
        }
        }

    @UiHandler("viewBtn")
    public void btnViewLogic(Button.ClickEvent event) {

        if (resultTable.getValue() == null) {

            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "View Error", "Please select a record to view", new MessageBoxListener() {
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
        } else {
            itemselectLogic(null);
            sessionDTO.setMode(ConstantsUtils.VIEW);
            if (ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName)) {
                getUI().getNavigator().navigateTo(CompanyAddView.NAME);
            }
            if (ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName)) {
                getUI().getNavigator().navigateTo(ItemAddView.NAME);
            }
            if (ConstantUtil.COMPANY_FAMILY_PLAN.equalsIgnoreCase(moduleName)) {
                sessionDTO.setMode(ConstantUtil.VIEW_BTN);
                getUI().getNavigator().navigateTo(CFPAddView.NAME);
            }
            if (ConstantUtil.ITEM_FAMILY_PLAN.equalsIgnoreCase(moduleName)) {
                sessionDTO.setMode(ConstantUtil.VIEW_BTN);
                getUI().getNavigator().navigateTo(IFPAddView.NAME);
            }
            if (ConstantUtil.PRICE_SCHEDULE_MASTER.equalsIgnoreCase(moduleName)) {
                getUI().getNavigator().navigateTo(PSView.NAME);
            }
            if (ConstantUtil.REBATE_SCHEDULE_MASTER.equalsIgnoreCase(moduleName)) {
                getUI().getNavigator().navigateTo(RebateScheduleAddView.NAME);
            }
            if (ConstantUtil.REBATE_PLAN.equalsIgnoreCase(moduleName)) {
                getUI().getNavigator().navigateTo(RebatePlanView.NAME);
            }
            if (ConstantUtil.NET_SALES_FORMULA.equals(moduleName)) {
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.GLOBAL_FILES_MODE, ConstantUtil.VIEW_BTN);
                getUI().getNavigator().navigateTo(NSFView.NAME);
            }
            if (ConstantUtil.DEDUCTION_CALENDAR.equals(moduleName)) {
                getUI().getNavigator().navigateTo(DeductionCalendarView.NAME);
            }
            if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.GLOBAL_FILES_MODE, ConstantUtil.VIEW_BTN);
                getUI().getNavigator().navigateTo(CDRView.NAME);
            }
        }
        resultTable.unselect(resultTable.getValue());
    }

    @SuppressWarnings(ConstantUtil.RAWTYPES)
    private class CompanyIdentifierValidator extends AbstractValidator {

        /**
         * The Constructor.
         */
        public CompanyIdentifierValidator() {
            super("");
        }

        /**
         * The Constructor.
         *
         * @param errorMessage the error message
         */
        public CompanyIdentifierValidator(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Validates the value.
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         */
        @Override
        public void validate(final Object value) throws Validator.InvalidValueException {

            markAsDirty();
            if (!isValidValue(value) && hasValue(combo6.getValue())) {
                throw new Validator.InvalidValueException("Both Identifier and Qualifier should be present");
            }
        }

        /**
         * Validates the value.
         *
         * @param value the value
         * @return true, if checks if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {

            if (hasValue(combo6.getValue())) {
                return hasValue(text8.getValue());
            } else {
                return !hasValue(text8.getValue());
            }

        }

        /**
         * Checks for value.
         *
         * @param obj the obj
         * @return true, if checks for value
         */
        private boolean hasValue(final Object obj) {
            LOGGER.info("Enters hasValue() " + obj.toString());

            if (obj == null || ConstantUtil.SELECT_ONE.equals(obj.toString())) {
                return false;
            } else {
                return !StringUtils.isEmpty(obj.toString());

            }
        }

        /**
         * Default method.
         *
         * @return the type
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    private HashMap<String, String> getmodulenames(UISecurityUtil securityUtil) {

        HashMap<String, String> modulename = new HashMap<String, String>();

        modulename.put(ConstantUtil.COMPANY_MAST, securityUtil.COMPANY_MASTER);
        modulename.put(ConstantUtil.ITEM_MASTER, securityUtil.ITEM_MASTER);
        modulename.put(ConstantUtil.COMPANY_FAMILY_PLAN, securityUtil.COMPANY_FAMILY_PLAN);
        modulename.put(ConstantUtil.ITEM_FAMILY_PLAN, securityUtil.ITEM_FAMILY_PLAN);
        modulename.put(ConstantUtil.PRICE_SCHEDULE_MASTER, securityUtil.PRICE_SCHEDULE);
        modulename.put(ConstantUtil.REBATE_SCHEDULE_MASTER, securityUtil.REBATE_SCHEDULE);
        modulename.put(ConstantUtil.REBATE_PLAN, securityUtil.REBATE_PLAN);

        return modulename;

    }

    /**
     * Excel button logic.
     *
     * @param event the event
     */
    @UiHandler("excel")
    public void excelButtonLogic(Button.ClickEvent event) {
        try {
            binder.getErrorDisplay().clearError();
            binder.commit();
            searchLogic.excelExportLogic(moduleName, resultTable, this, binder);
        } catch (FieldGroup.CommitException commit) {
            LOGGER.error(commit);
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    /**
     * return the filter generator instance
     *
     * @param moduleName
     * @return
     */
    private ExtFilterGenerator getFilterGeneratorClass(String moduleName) {
        if (ConstantUtil.COMPANY_MAST.equals(moduleName)) {
            return new CompanyFilterGenerator();
        } else if (ConstantUtil.ITEM_MASTER.equals(moduleName)) {
            return new ItemMasterGenerate();
        } else if (ConstantUtil.COMPANY_FAMILY_PLAN.equals(moduleName)) {
            return new CFPTestGenerator();
        } else if (ConstantUtil.ITEM_FAMILY_PLAN.equals(moduleName)) {
            return new IFPFilterGenerator();
        } else if (ConstantUtil.PRICE_SCHEDULE_MASTER.equals(moduleName)) {
            return new PSFilterGenerator();
        } else if (ConstantUtil.REBATE_SCHEDULE_MASTER.equals(moduleName)) {
            return new RSFilterGenerate();
        } else if (ConstantUtil.REBATE_PLAN.equals(moduleName)) {
            return new RPFilterGenerator();
        } else if (ConstantUtil.NET_SALES_FORMULA.equals(moduleName)) {
            return new NsfFilterGenerator();
        } else if (ConstantUtil.DEDUCTION_CALENDAR.equals(moduleName)) {
            return new DeductionFilterGenerator();
        }else if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
            return new ComplianceDeductionFilterGenerator();
        }
        
        return null;

    }

    /**
     *
     * @param key
     */
    public void setTableDefaultConfig1(String key) {
        resultTable.setVisibleColumns(getColumns(true, ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) || ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) ? key : moduleName));
        resultTable.setColumnHeaders(Arrays.copyOf(getColumns(false, ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) || ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) ? key : moduleName), getColumns(false, ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) || ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) ? key : moduleName).length, String[].class));
        resultTable.markAsDirtyRecursive();
        resultTable.setImmediate(true);
        resultTable.setWidth(100, UNITS_PERCENTAGE);
    }

    @UiHandler("copyBtn")
    public void copyButtonLogic(Button.ClickEvent event) {
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.GLOBAL_FILES_MODE, ConstantUtil.COPY);
        if (resultTable.getValue() == null) {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Copy Error", "Please select a record to Copy", new MessageBoxListener() {
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
        } else if (ConstantUtil.NET_SALES_FORMULA.equals(moduleName)) {
            if (resultTable.getValue() == null) {
                final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "No Record Selected to Copy", "Please select an existing Net Sales Formula record to copy. ", new MessageBoxListener() {
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
            } else {
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.GLOBAL_FILES_MODE, ConstantsUtils.COPY);
                sessionDTO.setMode(ConstantsUtils.COPY);
                getUI().getNavigator().navigateTo(NSFView.NAME);
            }
        } else if (ConstantUtil.DEDUCTION_CALENDAR.equals(moduleName)) {
            if (resultTable.getValue() == null) {
                final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "No Record Selected to Copy", "Please select a Deduction Calendar record. ", new MessageBoxListener() {
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
            } else {
                final MessageBox msg = MessageBox.showPlain(Icon.QUESTION, "Copy Confirmation", "Are you sure you want to copy the selected Deduction Calendar record? ", new MessageBoxListener() {
                    /**
                     * The method is triggered when a button of the message box
                     * is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equalsIgnoreCase(ConstantUtil.YES_VARIABLE)) {
                            sessionDTO.setMode(ConstantUtil.COPY);
                            getUI().getNavigator().navigateTo(DeductionCalendarView.NAME);
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
            }

        }else if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.GLOBAL_FILES_MODE, ConstantUtil.COPY);
            sessionDTO.setMode(ConstantUtil.COPY);
            getUI().getNavigator().navigateTo(CDRView.NAME);
        }
        else if (ConstantUtil.REBATE_PLAN.equals(moduleName)) {
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.GLOBAL_FILES_MODE, ConstantUtil.COPY);
            sessionDTO.setMode(ConstantUtil.COPY);
            getUI().getNavigator().navigateTo(RebatePlanView.NAME);
        }
    }

    @UiHandler("deleteBtn")
    public void deleteBtnLogic(Button.ClickEvent event) {
        LOGGER.info("deleteButtonClickLogic method Started ");
         if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
              if (resultTable.getValue() == null) {
               final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Delete Error", "Please select a record to Delete", new MessageBoxListener() {
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
            } else{
            final SearchResultsDTO searchForm = (SearchResultsDTO) resultTable.getValue();
            final MessageBox msg = MessageBox.showPlain(Icon.INFO, ConstantUtil.CONFORMATION_MSG, "Are you sure you want to Delete the selected record?. ", new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                    try {
                        if (ConstantUtil.DEDUCTION_CALENDAR.equals(moduleName)) {
                            SelectionLogic selLogic = new SelectionLogic();
                            resultTable.removeItem(resultTable.getValue());
                            selLogic.deleteDedutionCalendar(sessionDTO, Integer.valueOf(searchForm.getSystemID()));
                        }
                        if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
                            CDRLogic cdrLogic = new CDRLogic();
                            resultTable.removeItem(resultTable.getValue());
                            cdrLogic.deleteCDRRecords(Integer.valueOf(searchForm.getSystemID()));
                        }
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                }
            }, ButtonId.YES, ButtonId.NO);
            msg.getButton(ButtonId.YES).focus();
              }
        } else if (ConstantUtil.NET_SALES_FORMULA.equals(moduleName)) {
            if (resultTable.getValue() == null) {
               final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Delete Error", "Please select a record to Delete", new MessageBoxListener() {
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
            } else {
                final SearchResultsDTO searchForm = (SearchResultsDTO) resultTable.getValue();
                final MessageBox msg = MessageBox.showPlain(Icon.INFO, ConstantUtil.DELETE_CONFORMATION, "Are you sure you want to Delete the selected Net Sales Formula record? ", new MessageBoxListener() {
                    /**
                     * The method is triggered when a button of the message box
                     * is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    public void buttonClicked(final ButtonId buttonId) {
                        try {
                            if (buttonId.name().equalsIgnoreCase(ConstantUtil.YES_VARIABLE)) {
                                NsfLogic nsfLogic = new NsfLogic();
                                nsfLogic.deleteNsfMasterById(Integer.valueOf(searchForm.getSystemID()));
                                resultTable.removeItem(resultTable.getValue());
                            }
                        } catch (Exception e) {
                            LOGGER.error(e);
                        }

                    }
                }, ButtonId.YES, ButtonId.NO);
                msg.getButton(ButtonId.YES).focus();
            }
        }
        else if (ConstantUtil.REBATE_PLAN.equals(moduleName)) {
            if (resultTable.getValue() == null) {
                final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "No Record Selected to Delete", "Please select a record to continue. ", new MessageBoxListener() {
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
            } else {
                final SearchResultsDTO searchForm = (SearchResultsDTO) resultTable.getValue();
                final MessageBox msg = MessageBox.showPlain(Icon.INFO, ConstantUtil.DELETE_CONFORMATION, "Are you sure you want to delete the record "+searchForm.getRebatePlanName(), new MessageBoxListener() {
                    /**
                     * The method is triggered when a button of the message box
                     * is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    public void buttonClicked(final ButtonId buttonId) {
                        try {
                            if (buttonId.name().equalsIgnoreCase(ConstantUtil.YES_VARIABLE)) {
                                RebatePlanSearchLogic rebatePlanLogic = new RebatePlanSearchLogic();
                                RebatePlanMasterDTO rebatePlanMasterDTO = rebatePlanLogic.getRebatePlanMasterById(Integer.valueOf(searchForm.getSystemID()));
                                final RebatePlanMaster master = rebatePlanLogic.deleteRebatePlanById(Integer.valueOf(searchForm.getSystemID()), rebatePlanMasterDTO);
                                final Notification notif = new Notification(commonMsg.getDeletedSuccessfulMessage(master.getRebatePlanId(), master.getRebatePlanName()), Notification.Type.HUMANIZED_MESSAGE);
                                notif.setPosition(Position.MIDDLE_CENTER);
                                notif.setStyleName(ConstantsUtils.MY_STYLE);
                                notif.show(Page.getCurrent());
                                resultTable.removeItem(resultTable.getValue());
                            }
                        } catch (Exception e) {
                            LOGGER.error(e);
                        }

                    }
                }, ButtonId.YES, ButtonId.NO);
                msg.getButton(ButtonId.YES).focus();
            }
        }
            else if (ConstantUtil.DEDUCTION_CALENDAR.equals(moduleName)) {
            if (resultTable.getValue() == null) {
                final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "No Record Selected to Delete", "Please select an existing Deduction calendar record to delete. ", new MessageBoxListener() {
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
            } else {
                final SearchResultsDTO searchForm = (SearchResultsDTO) resultTable.getValue();
                final MessageBox msg = MessageBox.showPlain(Icon.INFO, ConstantUtil.DELETE_CONFORMATION, "Are you sure you want to delete record <"+searchForm.getDeductionCalendarname()+">?", new MessageBoxListener() {
                    /**
                     * The method is triggered when a button of the message box
                     * is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    public void buttonClicked(final ButtonId buttonId) {
                        try {
                            if (buttonId.name().equalsIgnoreCase(ConstantUtil.YES_VARIABLE)) {
                                SelectionLogic selLogic = new SelectionLogic();
                                resultTable.removeItem(resultTable.getValue());
                                selLogic.deleteDedutionCalendar(sessionDTO, Integer.valueOf(searchForm.getSystemID()));
                            }
                        } catch (Exception e) {
                            LOGGER.error(e);
                        }

                    }
                }, ButtonId.YES, ButtonId.NO);
                msg.getButton(ButtonId.YES).focus();
            }
        }
    }

    public void securityForAllScreens() throws PortalException, SystemException {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent()
                .getAttribute(ConstantsUtils.USER_ID));

        if (ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName)) {

            final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.COMPANY_MASTER + ConstantUtil.COMMA + "Search Screen");
            if (functionCfpHM.get(FunctionNameUtil.RESET) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
                resetBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.ADD) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.ADD)).isFunctionFlag()) {
                addBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.EDIT) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.EDIT)).isFunctionFlag()) {
                editBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.VIEW) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.VIEW)).isFunctionFlag()) {
                viewBtn.setVisible(false);
            }

        }
        if (ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName)) {
            final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.ITEM_MASTER + ConstantUtil.COMMA + "Search Screen");
            if (functionCfpHM.get(FunctionNameUtil.RESET) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
                resetBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.ADD) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.ADD)).isFunctionFlag()) {
                addBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.EDIT) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.EDIT)).isFunctionFlag()) {
                editBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.VIEW) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.VIEW)).isFunctionFlag()) {
                viewBtn.setVisible(false);
            }
        }
        if (ConstantUtil.COMPANY_FAMILY_PLAN.equalsIgnoreCase(moduleName)) {
            final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.COMPANY_FAMILY_PLAN + ConstantUtil.COMMA + "Search Screen");
            if (functionCfpHM.get(FunctionNameUtil.RESET) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
                resetBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.ADD) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.ADD)).isFunctionFlag()) {
                addBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.EDIT) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.EDIT)).isFunctionFlag()) {
                editBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.VIEW) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.VIEW)).isFunctionFlag()) {
                viewBtn.setVisible(false);
            }
        }
        if (ConstantUtil.ITEM_FAMILY_PLAN.equalsIgnoreCase(moduleName)) {
            final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.ITEM_FAMILY_PLAN + ConstantUtil.COMMA + "Search Screen");
            if (functionCfpHM.get(FunctionNameUtil.RESET) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
                resetBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.ADD) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.ADD)).isFunctionFlag()) {
                addBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.EDIT) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.EDIT)).isFunctionFlag()) {
                editBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.VIEW) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.VIEW)).isFunctionFlag()) {
                viewBtn.setVisible(false);
            }
        }
        if (ConstantUtil.PRICE_SCHEDULE_MASTER.equalsIgnoreCase(moduleName)) {
            final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.PRICE_SCHEDULE + ConstantUtil.COMMA + "Search Screen");
            if (functionCfpHM.get(FunctionNameUtil.RESET) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
                resetBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.ADD) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.ADD)).isFunctionFlag()) {
                addBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.EDIT) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.EDIT)).isFunctionFlag()) {
                editBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.VIEW) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.VIEW)).isFunctionFlag()) {
                viewBtn.setVisible(false);
            }
        }
        if (ConstantUtil.REBATE_SCHEDULE_MASTER.equalsIgnoreCase(moduleName)) {
            final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.REBATE_SCHEDULE + ConstantUtil.COMMA + "Search Screen");
            if (functionCfpHM.get(FunctionNameUtil.RESET) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
                resetBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.ADD) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.ADD)).isFunctionFlag()) {
                addBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.EDIT) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.EDIT)).isFunctionFlag()) {
                editBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.VIEW) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.VIEW)).isFunctionFlag()) {
                viewBtn.setVisible(false);
            }
        }
        if (ConstantUtil.REBATE_PLAN.equalsIgnoreCase(moduleName)) {
            final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.REBATE_PLAN + ConstantUtil.COMMA + "Search Screen");
            if (functionCfpHM.get(FunctionNameUtil.RESET) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
                resetBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.ADD) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.ADD)).isFunctionFlag()) {
                addBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.EDIT) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.EDIT)).isFunctionFlag()) {
                editBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.VIEW) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.VIEW)).isFunctionFlag()) {
                viewBtn.setVisible(false);
            }
        }
    }
}
