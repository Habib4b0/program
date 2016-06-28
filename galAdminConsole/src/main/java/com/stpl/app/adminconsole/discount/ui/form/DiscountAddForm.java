package com.stpl.app.adminconsole.discount.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import com.stpl.app.adminconsole.discount.dto.DiscountSearchDTO;
import com.stpl.app.adminconsole.discount.logic.DiscountLogic;
import com.stpl.app.adminconsole.discount.ui.view.DiscountAddView;
import com.stpl.app.adminconsole.discount.ui.view.DiscountSearchView;
import com.stpl.app.adminconsole.itemgroup.util.UISecurityUtil;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ResponsiveUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.Map;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class DiscountAddForm.
 */
public class DiscountAddForm extends CustomComponent implements View {

    /**
     * The space.
     */
    @UiField("cssLayout")
    private CssLayout cssLayout;
    @UiField("searchCssLayout")
    private CssLayout searchCssLayout;
    @UiField("discountName")
    private TextField discountName;
    @UiField("discountNo")
    private TextField discountNo;
    @UiField("discountDesc")
    private TextField discountDesc;
    @UiField("contractNo")
    private TextField contractNo;
    /**
     * The contractName.
     */
    @UiField("contractName")
    private TextField contractName;
    @UiField("itemNo")
    private TextField itemNo;
    /**
     * The item Name .
     */
    @UiField("itemName")
    private TextField itemName;
    /**
     * The customer no.
     */
    @UiField("customerNo")
    private TextField customerNo;
    /**
     * The customer Name
     */
    @UiField("customerName")
    private TextField customerName;
    @UiField("tradingPartner")
    private TextField tradingPartner;
    @UiField("contractType")
    private ComboBox contractType;
    /**
     * The tradingPartner.
     */
    @UiField("programType")
    private ComboBox programType;
    /**
     * The categoryType.
     */
    @UiField("categoryType")
    private ComboBox categoryType;
    /**
     * The udc2.
     */
    @UiField("udc2Ddlb")
    private ComboBox udc2Ddlb;
    private final Label space = new Label("&nbsp;", ContentMode.HTML);
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(DiscountAddForm.class);
    /**
     * The error msg.
     */
    private final ErrorLabel errorMsg = new ErrorLabel();
    /**
     * The availableRebate.
     */
    private ExtFilterTable availableRebate = new ExtFilterTable();
    /**
     * The selectedRebate.
     */
    private ExtFilterTable selectedRebate = new ExtFilterTable();
    /**
     * The save.
     */
    @UiField("saveBtn")
    private Button saveBtn;
    /**
     * The reset.
     */
    @UiField("resetBtn")
    private Button resetBtn;
    /**
     * The back button.
     */
    @UiField("backBtn")
    private Button backBtn;
    /**
     *
     * /**
     * The discount name.
     */
    /**
     * The programType.
     */
    /**
     * The discount desc.
     */
    /**
     * The discount rule.
     */
    private final NativeSelect discountRule = new NativeSelect();
    /**
     * The item no.
     */
    @UiField("searchBtn")
    private Button searchBtn;
    /**
     * The discount rule.
     */
    @UiField("resetButton")
    private Button resetButton;
    /**
     * The add.
     */
    @UiField("addBtn")
    private Button addBtn;
    /**
     * The addAll.
     */
    @UiField("addallBtn")
    private Button addallBtn;
    /**
     * The remove.
     */
    @UiField("removeBtn")
    private Button removeBtn;
    /**
     * The removeAll.
     */
    @UiField("removeallBtn")
    private Button removeallBtn;
    /**
     * The availableRebateExport excel export button.
     */
    @UiField("availableRebateExport")
    private Button availableRebateExport;
    /**
     * The selectedRebateExport excel export button.
     */
    @UiField("selectedRebateExport")
    private Button selectedRebateExport;
    @UiField("tableLayout")
    private VerticalLayout tableLayout;
    @UiField("controlLayout")
    private HorizontalLayout controlLayout;
    @UiField("tableLayout1")
    private VerticalLayout tableLayout1;
    @UiField("controlLayout1")
    private HorizontalLayout controlLayout1;
    @UiField("searchPanel")
    private Panel searchPanel;
    @UiField("availableRebatePanel")
    private Panel availableRebatePanel;
    @UiField("addBtnsHLayout")
    private HorizontalLayout addBtnsHLayout;
    @UiField("removeBtnsHLayout")
    private HorizontalLayout removeBtnsHLayout;
    CommonUtil commonUtil = new CommonUtil();
    CommonSecurityLogic commonSecurity = new CommonSecurityLogic();

    /**
     * The commonUtils.
     */
    DiscountLogic logic = new DiscountLogic();
    /**
     * The version.
     */
    private int version = 0;
    /**
     * The contractNo.
     */
    /**
     * The contractName.
     */
    /**
     * The Constant DISCOUNT_TABLE_COLUMNS.
     */
    public static final Object[] DISCOUNT_TABLE_COLUMNS = new Object[]{
        "rebateName", "rebateNo", "rebateScheduleType", "rebateProgramType", "rebateScheduleCategory", "rebatePlanLevel"};
    /**
     * The Constant DISCOUNT_TABLE_HEADER.
     */
    public static final String[] DISCOUNT_TABLE_HEADER = new String[]{
        "Rebate Name", "Rebate No", "Rebate Schedule Type", "Rebate Program Type", "Rebate Schedule Category", "Rebate Plan Level"};
    /**
     * The Constant AVAILABLE LIST COLUMN.
     */
    public static final Object[] AVAILABLE_LIST_COLUMN = new Object[]{
        "rebateName", "rebateNo", "rebateScheduleType", "rebateProgramType", "rebateScheduleCategory", "rebatePlanLevel"};
    /**
     * The Constant AVAILABLE LIST HEADER.
     */
    public static final String[] AVAILABLE_LIST_HEADER = new String[]{
        "Rebate Name", "Rebate No", "Rebate Schedule Type", "Rebate Program Type", "Rebate Schedule Category", "Rebate Plan Level"};
    /**
     *
     * The Discount group binder.
     */
    private CustomFieldGroup discountBinder;
    /**
     * The Discount Search dto.
     */
    private DiscountSearchDTO discountSearchDTO;
    /**
     * The discount logic.
     */
    public final DiscountLogic discountLogic;
    /**
     * The available availableRebate bean.
     */
    private BeanItemContainer<DiscountSearchDTO> availableResultsBean = new BeanItemContainer<DiscountSearchDTO>(DiscountSearchDTO.class);
    /**
     * The availableRebate bean.
     */
    private BeanItemContainer<DiscountSearchDTO> resultsBean = new BeanItemContainer<DiscountSearchDTO>(DiscountSearchDTO.class);
    /**
     * The record selected flag.
     */
    private Boolean recordSelectedFlag = false;
    private Label contractLabel = new Label("Contract No:");
    private Label categoryTypeLabel = new Label("Category Type:");
    private Label tpLabel = new Label("Trading Partner:");
    private boolean shouldSave = false;
    /**
     * The Discount Search dto.
     */
    private DiscountSearchDTO deductionGroupInfo;

    SessionDTO sessionDTO;

    /**
     * Gets the availableRebate.
     *
     * @return the availableRebate
     */
    public ExtFilterTable getResults() {
        return availableRebate;
    }

    /**
     * Sets the availableRebate.
     *
     * @param availableRebate the new availableRebate
     */
    public void setResults(final ExtFilterTable results) {
        this.availableRebate = results;
    }

    /**
     * Gets the availableRebate column table.
     *
     * @return the availableRebate column table
     */
    public ExtFilterTable getResultsColumnTable() {
        return selectedRebate;
    }

    /**
     * Sets the availableRebate column table.
     *
     * @param selectedRebate the new availableRebate column table
     */
    public void setResultsColumnTable(final ExtFilterTable resultsColumnTable) {
        this.selectedRebate = resultsColumnTable;
    }

    /**
     * Gets the discount binder.
     *
     * @return the discount binder
     */
    public CustomFieldGroup getDiscountBinder() {
        return discountBinder;
    }

    /**
     * Sets the discount binder.
     *
     * @param discountBinder the new discount binder
     */
    public void setDiscountBinder(final CustomFieldGroup discountBinder) {
        this.discountBinder = discountBinder;
    }

    /**
     * Gets the discount search dto.
     *
     * @return the discount search dto
     */
    public DiscountSearchDTO getDiscountSearchDTO() {
        return discountSearchDTO;
    }

    /**
     * Sets the discount search dto.
     *
     * @param discountSearchDTO the new discount search dto
     */
    public void setDiscountSearchDTO(final DiscountSearchDTO discountSearchDTO) {
        this.discountSearchDTO = discountSearchDTO;
    }

    /**
     * Gets the available availableRebate bean.
     *
     * @return the available availableRebate bean
     */
    public BeanItemContainer<DiscountSearchDTO> getAvailableResultsBean() {
        return availableResultsBean;
    }

    /**
     * Sets the available availableRebate bean.
     *
     * @param availableResultsBean the new available availableRebate bean
     */
    public void setAvailableResultsBean(
            final BeanItemContainer<DiscountSearchDTO> availableResultsBean) {
        this.availableResultsBean = availableResultsBean;
    }

    /**
     * Gets the availableRebate bean.
     *
     * @return the availableRebate bean
     */
    public BeanItemContainer<DiscountSearchDTO> getResultsBean() {
        return resultsBean;
    }

    /**
     * Sets the availableRebate bean.
     *
     * @param resultsBean the new availableRebate bean
     */
    public void setResultsBean(final BeanItemContainer<DiscountSearchDTO> resultsBean) {
        this.resultsBean = resultsBean;
    }

    /**
     * Gets the logger.
     *
     * @return the logger
     */
    public static Logger getLogger() {
        return LOGGER;
    }

    /**
     * Gets the error msg.
     *
     * @return the error msg
     */
    public ErrorLabel getErrorMsg() {
        return errorMsg;
    }

    /**
     * Gets the save btn.
     *
     * @return the save btn
     */
    public Button getSaveBtn() {
        return saveBtn;
    }

    /**
     * Gets the reset btn.
     *
     * @return the reset btn
     */
    public Button getResetBtn() {
        return resetBtn;
    }

    /**
     * Gets the back btn.
     *
     * @return the back btn
     */
    public Button getBackBtn() {
        return backBtn;
    }

    /**
     * Gets the discount no.
     *
     * @return the discount no
     */
    public TextField getDiscountNo() {
        return discountNo;
    }

    /**
     * Gets the discount desc.
     *
     * @return the discount desc
     */
    public TextField getDiscountDesc() {
        return discountDesc;
    }

    /**
     * Gets the discount table columns.
     *
     * @return the discount table columns
     */
    public static Object[] getDiscountTableColumns() {
        return DISCOUNT_TABLE_COLUMNS;
    }

    /**
     * Gets the discount table header.
     *
     * @return the discount table header
     */
    public static String[] getDiscountTableHeader() {
        return DISCOUNT_TABLE_HEADER;
    }

    /**
     * Gets the available list column.
     *
     * @return the available list column
     */
    public static Object[] getAvailableListColumn() {
        return AVAILABLE_LIST_COLUMN;
    }

    /**
     * Gets the available list header.
     *
     * @return the available list header
     */
    public static String[] getAvailableListHeader() {
        return AVAILABLE_LIST_HEADER;
    }

    /**
     * Gets the discount logic.
     *
     * @return the discount logic
     */
    public DiscountLogic getDiscountLogic() {
        return discountLogic;
    }

    /**
     * Instantiates a new discount add form.
     */
    public DiscountAddForm(final BeanItemContainer selectedRebateBean, final BeanItemContainer availableRebateBean, final SessionDTO sessionDTO) throws Exception {
        super();
        discountSearchDTO = new DiscountSearchDTO();
        discountBinder = new CustomFieldGroup(new BeanItem<DiscountSearchDTO>(discountSearchDTO));
        this.resultsBean = selectedRebateBean;
        this.availableResultsBean = availableRebateBean;
        this.sessionDTO = sessionDTO;
        discountLogic = new DiscountLogic(sessionDTO);
        init();
    }

    public DiscountAddForm() {
        discountLogic = new DiscountLogic();
    }

    /**
     * Inits the.
     */
    public final void init() throws Exception {
        LOGGER.info("init Method Started ");
        try {
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/deductiongrouping.xml"), this));
            getBinder();
            configureFields();
            addResultsTable();
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = sessionDTO.getUserId();
            final Map<String, AppPermission> fieldDeductionHM = stplSecurity.getBusinessFieldPermission(userId, UISecurityUtil.DEDUCTION_GROUPING);
            final Map<String, AppPermission> functionDeductionHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.DEDUCTION_GROUPING);
            setFieldSecurity(fieldDeductionHM);
            setButtonSecurity(functionDeductionHM);
            addResultsColumnTable();
            if (sessionDTO.getMode().equals(ConstantsUtils.VIEW)) {
                disableComponentsOnViewMode();
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("init Method Ended ");
    }

    /**
     * Adds the availableRebate tables.
     *
     * @return the panel
     */
    private Panel addResultsTables() throws Exception {
        final Panel panel = new Panel();
        final VerticalLayout tableLayout = new VerticalLayout();
        tableLayout.setMargin(true);
        tableLayout.setSpacing(true);
        tableLayout.addComponent(ResponsiveUtils.addNaviButton(availableRebate));
        tableLayout.addComponent(addResultsTable());
        tableLayout.addComponent(ResponsiveUtils.addNaviButton(selectedRebate));
        tableLayout.addComponent(addResultsColumnTable());
        panel.setContent(tableLayout);
        return panel;
    }

    /**
     * Adds the availableRebate table.
     *
     * @return the filter table
     */
    private ExtFilterTable addResultsTable() throws Exception {
        LOGGER.info("addResultsTables method started ");

        tableLayout.addComponent(availableRebate);
        tableLayout.addComponent(controlLayout);
        availableRebate.markAsDirty();
        availableRebate.setFilterBarVisible(true);
        availableRebate.setFilterDecorator(new ExtDemoFilterDecorator());
        availableRebate.setContainerDataSource(availableResultsBean);

        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = sessionDTO.getUserId();
        final Map<String, AppPermission> fieldIfpHM = stplSecurity.getBusinessFieldPermission(userId, UISecurityUtil.DEDUCTION_GROUPING);
        String mode = sessionDTO.getMode();
        List<Object> resultList = commonUtil.getFieldsForSecurity(UISecurityUtil.DEDUCTION_GROUPING, "Functional  List view");
        Object[] objColumn = AVAILABLE_LIST_COLUMN;
        TableResultCustom tableResultCustom = commonSecurity.getTableColumnsPermission(resultList, objColumn, fieldIfpHM, mode);
        availableRebate.setVisibleColumns(tableResultCustom.getObjResult());
        availableRebate.setColumnHeaders(tableResultCustom.getObjResultHeader());
        availableRebate.setSelectable(true);
        availableRebate.setPageLength(5);
        availableRebate.setSizeFull();
        availableRebate.setColumnCollapsingAllowed(true);
        availableRebate.setImmediate(true);
        availableRebate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             *
             *
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                LOGGER.info("In addItemAvailableResults availableResults.addValueChangeListener started");
                resultsItemClick(event.getProperty().getValue());
                LOGGER.info("In addItemAvailableResults availableResults.addValueChangeListener Ended");
            }
        });
        availableRebate.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            /**
             * Method called when double click on available availableRebate
             * items
             */
            public void itemClick(final ItemClickEvent event) {
                if (event.isDoubleClick()) {
                    LOGGER.info("In addItemAvailableResults availableResults.addItemClickListener started");
                    addItemsDoubleClick(event);
                    LOGGER.info("In addItemAvailableResults availableResults.addItemClickListener Ended");
                }
            }
        });

        LOGGER.info("addResultsTable method RETURNS Table-results ");

        return availableRebate;
    }

    /**
     * Adds the availableRebate table.
     *
     * @return the filter table
     */
    private ExtFilterTable addResultsColumnTable() throws Exception {
        LOGGER.info("addResultsTables method started ");
        tableLayout1.addComponent(selectedRebate);

        tableLayout1.addComponent(controlLayout1);
        selectedRebate.markAsDirty();
        selectedRebate.setFilterBarVisible(true);
        selectedRebate.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedRebate.setContainerDataSource(resultsBean);

        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = sessionDTO.getUserId();
        final Map<String, AppPermission> fieldIfpHM = stplSecurity.getBusinessFieldPermission(userId, UISecurityUtil.DEDUCTION_GROUPING);
        String mode = sessionDTO.getMode();
        List<Object> resultList = commonUtil.getFieldsForSecurity(UISecurityUtil.DEDUCTION_GROUPING, "Functional  List view");
        Object[] objColumn = AVAILABLE_LIST_COLUMN;
        TableResultCustom tableResultCustom = commonSecurity.getTableColumnsPermission(resultList, objColumn, fieldIfpHM, mode);
        selectedRebate.setVisibleColumns(tableResultCustom.getObjResult());
        selectedRebate.setColumnHeaders(tableResultCustom.getObjResultHeader());
        selectedRebate.setSelectable(true);
        selectedRebate.setPageLength(5);
        selectedRebate.setSizeFull();
        selectedRebate.setColumnCollapsingAllowed(true);
        selectedRebate.setImmediate(true);
        selectedRebate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             *
             *
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                LOGGER.info("In addItemSelectedResults selectedResults.addValueChangeListener started");
                resultsItemClick(event.getProperty().getValue());
                LOGGER.info("In addItemSelectedResults selectedResults.addValueChangeListener Ended");
            }
        });

        selectedRebate.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            /**
             * Method is called when available excel export button is clicked
             */
            public void itemClick(final ItemClickEvent event) {
                if (event.isDoubleClick()) {
                    LOGGER.info("In addItemSelectedResults selectedResults.addItemClickListener started");
                    removeItemsDoubleClick(event);
                    LOGGER.info("In addItemSelectedResults selectedResults.addItemClickListener  Ended");
                }
            }
        });

        LOGGER.info("addResultsTable method RETURNS Table-results ");
        return selectedRebate;
    }

    /**
     * Adds the buttons.
     *
     * @return the component
     */
    private Component addButtons() {
        LOGGER.info("addButtons method Started ");
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setStyleName(ConstantsUtils.RESPONSIVE_TAB_GRID);
        layout.setSpacing(true);
        layout.addComponent(backBtn);
        saveBtn.setEnabled(true);
        layout.addComponent(resetBtn);
        resetBtn.setEnabled(true);
        layout.addComponent(saveBtn);
        backBtn.setEnabled(true);
        LOGGER.info("addButtons method RETURNS layout ");
        return layout;
    }

    /**
     * Configure fields.
     */
    @SuppressWarnings("serial")
    public void configureFields() throws Exception {
        discountName.setRequired(true);
        discountName.setRequiredError("Please Enter value for Discount Name");
        discountNo.setRequired(true);
        discountNo.setRequiredError("Please Enter value for Discount No");
        discountDesc.setRequired(true);
        discountDesc.setRequiredError("Please Enter value for Discount Desc");

        discountName.setImmediate(true);
        discountNo.setImmediate(true);
        discountDesc.setImmediate(true);
        discountRule.setImmediate(true);
        discountRule.addItem("Rule1");
        discountRule.addItem("Rule2");

        contractLabel.setWidth("140px");
        categoryTypeLabel.setWidth("140px");
        tpLabel.setWidth("140px");

        availableRebateExport.setIcon(new ThemeResource("../../icons/excel.png"));
        availableRebateExport.setStyleName("link");
        availableRebateExport.setDescription(ConstantsUtils.EXCEL_EXPORT);
        availableRebateExport.setIconAlternateText(ConstantsUtils.EXCEL_EXPORT);

        selectedRebateExport.setIcon(new ThemeResource("../../icons/excel.png"));
        selectedRebateExport.setStyleName("link");
        selectedRebateExport.setDescription(ConstantsUtils.EXCEL_EXPORT);
        selectedRebateExport.setIconAlternateText(ConstantsUtils.EXCEL_EXPORT);

        discountName.setRequired(true);
        discountName.setRequiredError("Please Enter value for Discount Name");
        discountNo.setRequired(true);
        discountNo.setRequiredError("Please Enter value for Discount No");
        discountDesc.setRequired(true);
        discountDesc.setRequiredError("Please Enter value for Discount Desc");

        discountName.setImmediate(true);
        discountNo.setImmediate(true);
        discountDesc.setImmediate(true);

        programType.setNullSelectionAllowed(true);
        programType.setNullSelectionItemId(ConstantsUtils.ZERO_INT);
        new com.stpl.app.adminconsole.util.CommonUtils().getNativeSelect(programType, logic.getDropDownList(ConstantsUtils.REBATE_PROGRAM_TYPE));
        programType.select(ConstantsUtils.ZERO_INT);
        programType.setImmediate(true);
        programType.setDescription(String.valueOf((Integer) programType.getValue()));
        programType.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for lodding-RebateProgramType Dblb ListType
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                programType.setDescription(String.valueOf((Integer) programType.getValue()));
            }
        });

        LOGGER.info("In configureFields lodding-RebateScheduleCategory Dblb ListType=" + ConstantsUtils.REBATE_SCHEDULE_CATEGORY);
        categoryType.setNullSelectionAllowed(true);
        categoryType.setNullSelectionItemId(ConstantsUtils.ZERO_INT);
        new com.stpl.app.adminconsole.util.CommonUtils().getNativeSelect(categoryType, logic.getDropDownList(ConstantsUtils.REBATE_SCHEDULE_CATEGORY));
        categoryType.select(ConstantsUtils.ZERO_INT);
        categoryType.setImmediate(true);
        categoryType.setDescription(String.valueOf((Integer) categoryType.getValue()));
        categoryType.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for Rebate Schedule Category Dblb ListType
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                categoryType.setDescription(String.valueOf((Integer) categoryType.getValue()));
            }
        });

        udc2Ddlb.setNullSelectionAllowed(true);
        udc2Ddlb.setNullSelectionItemId(ConstantsUtils.ZERO_INT);
        new com.stpl.app.adminconsole.util.CommonUtils().getNativeSelect(udc2Ddlb, logic.getDropDownList(ConstantsUtils.UDC2));
        udc2Ddlb.select(ConstantsUtils.ZERO_INT);
        udc2Ddlb.setImmediate(true);
        udc2Ddlb.setDescription(String.valueOf((Integer) udc2Ddlb.getValue()));
        udc2Ddlb.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for lodding-udc2 Dblb ListType
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                udc2Ddlb.setDescription(String.valueOf((Integer) udc2Ddlb.getValue()));
            }
        });
        contractType.setNullSelectionAllowed(true);
        contractType.setNullSelectionItemId(ConstantsUtils.ZERO_INT);
        new com.stpl.app.adminconsole.util.CommonUtils().getNativeSelect(contractType, logic.getDropDownList("CONTRACT_TYPE"));
        contractType.select(ConstantsUtils.ZERO_INT);
        contractType.setImmediate(true);
        contractType.setDescription(String.valueOf((Integer) contractType.getValue()));
        contractType.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for Rebate Schedule Category Dblb ListType
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                contractType.setDescription(String.valueOf((Integer) contractType.getValue()));
            }
        });

        selectedRebateExport.addClickListener(new ClickListener() {
            /**
             * Method is called when available excel export button is clicked
             */
            public void buttonClick(final ClickEvent event) {
            }
        });

        availableRebateExport.addClickListener(new ClickListener() {
            /**
             * Method is called when available excel export button is clicked
             */
            public void buttonClick(final ClickEvent event) {
            }
        });

    }

    @UiHandler("searchBtn")
    public void searchBtnLogic(Button.ClickEvent event) {
        try {
            DiscountSearchDTO searchDTO = new DiscountSearchDTO();
            if (programType.getValue() != null) {
                searchDTO.setRebateProgramType(programType.getItemCaption(programType.getValue()));
            }
            if (categoryType.getValue() != null) {
                searchDTO.setRebateScheduleCategory(categoryType.getItemCaption(categoryType.getValue()));
            }
            if (udc2Ddlb.getValue() != null) {
                searchDTO.setUdc2(udc2Ddlb.getItemCaption(udc2Ddlb.getValue()));
            }
            if (contractName.getValue() != null) {
                searchDTO.setContractName(contractName.getValue());
            }
            if (contractNo.getValue() != null) {
                searchDTO.setContractNo(contractNo.getValue());
            }
            if (contractType.getValue() != null) {
                searchDTO.setRebateContractType(contractType.getItemCaption(contractType.getValue()));
            }
            if (tradingPartner.getValue() != null) {
                searchDTO.setTradingPartner(tradingPartner.getValue());
            }
            if (itemNo.getValue() != null) {
                searchDTO.setItemNo(itemNo.getValue());
            }
            if (itemName.getValue() != null) {
                searchDTO.setItemName(itemName.getValue());
            }
            if (customerNo.getValue() != null) {
                searchDTO.setCustomerNo(customerNo.getValue());
            }
            if (customerName.getValue() != null) {
                searchDTO.setCustomerName(customerName.getValue());
            }
            List<DiscountSearchDTO> list = logic.getSearchResults(searchDTO);
            availableResultsBean.removeAllItems();
            if (list.size() > 0) {
                availableResultsBean.addAll(list);
            } else {
                Notification.show("No Results found");
            }
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }

    @UiHandler("resetBtn")
    public void resetBtnLogic(Button.ClickEvent event) {
        LOGGER.info("resetBtnLogic started");
        MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to reset the page to default/previous values "
                + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {
                    /**
                     * Called when reset button is clicked
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                            shouldSave = false;
                            String pageName = sessionDTO.getLogic();
                            if (pageName.equals("edit")) {
                                getUI().getNavigator().navigateTo(DiscountAddView.NAME);
                            } else {
                                discountName.setValue(ConstantsUtils.EMPTY);
                                discountNo.setValue(ConstantsUtils.EMPTY);
                                discountDesc.setValue(ConstantsUtils.EMPTY);
                                programType.select(ConstantsUtils.ZERO_INT);
                                categoryType.select(ConstantsUtils.ZERO_INT);
                                udc2Ddlb.select(ConstantsUtils.ZERO_INT);
                                contractType.select(ConstantsUtils.ZERO_INT);
                                contractNo.setValue(ConstantsUtils.EMPTY);
                                contractName.setValue(ConstantsUtils.EMPTY);
                                tradingPartner.setValue(ConstantsUtils.EMPTY);
                                itemName.setValue(ConstantsUtils.EMPTY);
                                itemNo.setValue(ConstantsUtils.EMPTY);
                                customerName.setValue(ConstantsUtils.EMPTY);
                                customerNo.setValue(ConstantsUtils.EMPTY);
                                resultsBean.removeAllItems();
                                availableResultsBean.removeAllItems();
                            }

                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
        LOGGER.info("resetBtnLogic Ended");
    }

    @UiHandler("resetButton")
    public void resetButtonLogic(Button.ClickEvent event) {
        LOGGER.info("In configureFields reset.addClickListener started");
        MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to reset the page to default/previous values "
                + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {
                    /**
                     * Called when reset button is clicked
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                            programType.select(ConstantsUtils.ZERO_INT);
                            categoryType.select(ConstantsUtils.ZERO_INT);
                            udc2Ddlb.select(ConstantsUtils.ZERO_INT);
                            contractType.select(ConstantsUtils.ZERO_INT);
                            contractNo.setValue(ConstantsUtils.EMPTY);
                            contractName.setValue(ConstantsUtils.EMPTY);
                            tradingPartner.setValue(ConstantsUtils.EMPTY);
                            itemName.setValue(ConstantsUtils.EMPTY);
                            itemNo.setValue(ConstantsUtils.EMPTY);
                            customerName.setValue(ConstantsUtils.EMPTY);
                            customerNo.setValue(ConstantsUtils.EMPTY);
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
        LOGGER.info("In configureFields reset.addClickListener Ended");
    }

    @UiHandler("backBtn")
    public void backBtnLogic(Button.ClickEvent event) {
        getUI().getNavigator().navigateTo(DiscountSearchView.NAME);
    }

    @UiHandler("addBtn")
    public void addBtnLogic(Button.ClickEvent event) {
        LOGGER.info("addBtnLogic started");
        if (recordSelectedFlag) {
            addItemsButtonClick(event);
            recordSelectedFlag = false;
        } else {
            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No rebate was selected in the Results list view.  Please select at least one rebate and try again.", ButtonId.OK);
        }
        LOGGER.info("addBtnLogic Ended");
    }

    @UiHandler("addallBtn")
    public void addallBtnLogic(Button.ClickEvent event) {
        LOGGER.info("addallBtnLogic started");
        if (availableResultsBean.size() > ConstantsUtils.ZERO_NUM) {
            addAllItemsButtonClick(event);
        } else {
            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No rebates are available in Available rebate list view.", ButtonId.OK);
        }
        LOGGER.info("addallBtnLogic Ended");
    }

    @UiHandler("removeBtn")
    public void removeBtnLogic(Button.ClickEvent event) {
        LOGGER.info("removeBtnLogic");
        if (recordSelectedFlag) {
            removeItemsButtonClick(event);
            recordSelectedFlag = false;
        } else {
            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No rebate was selected in the Results list view.  Please select at least one rebate and try again", ButtonId.OK);
        }
        LOGGER.info("removeBtnLogic");
    }

    @UiHandler("removeallBtn")
    public void removeallBtnLogic(Button.ClickEvent event) {
        LOGGER.info("removeallBtn started");
        if (resultsBean.size() > ConstantsUtils.ZERO_NUM) {
            removeAllItemsButtonClick(event);
        } else {
            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No rebates are available in selected Rebate list view.", ButtonId.OK);
        }
        LOGGER.info("removeallBtn Ended");
    }

    @UiHandler("saveBtn")
    public void saveBtnLogic(Button.ClickEvent event) {
        LOGGER.info("saveBtnLogic started");
        saveButtonClickLogic(true);
        LOGGER.info("saveBtnLogic Ended");
    }

    @UiHandler("availableRebateExport")
    public void availableRebateExportLogic(Button.ClickEvent event) {
        LOGGER.info("availableRebateExportLogic started");
        ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(availableRebate), "Available Deduction Group", "Available Deduction Group", "AvailableDeductionGroup.xls", false);
        excel.export();
        LOGGER.info("availableRebateExportLogic Ended");
    }

    @UiHandler("selectedRebateExport")
    public void selectedRebateExportLogic(Button.ClickEvent event) {
        LOGGER.info("selectedRebateExportLogic started");
        ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(selectedRebate), "Selected Deduction Group", "Selected Deduction Group", "SelectedDeductionGroup.xls", false);
        excel.export();
        LOGGER.info("selectedRebateExportLogic Ended");
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private CustomFieldGroup getBinder() {

        LOGGER.info("getBinder method Started ");
        discountBinder.bindMemberFields(this);
        discountBinder.setItemDataSource(new BeanItem<DiscountSearchDTO>(discountSearchDTO));
        discountBinder.setBuffered(true);
        discountBinder.setErrorDisplay(errorMsg);
        LOGGER.info("getBinder method RETURNS DiscountBinder - Binder ");
        return discountBinder;
    }

    /**
     * Save button click.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected List<Integer> saveButtonClick() throws SystemException, PortalException, Exception {
        List<Integer> idList = new ArrayList<Integer>();
        try {
            LOGGER.info("Entering saveButtonClick method");

            final List selectedRebates = resultsBean.getItemIds();
            idList = discountLogic.saveDiscount(discountBinder, selectedRebates, version, sessionDTO);
            LOGGER.info(" saveButtonClick method Ended");
            return idList;
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return idList;
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    @Override
    /**
     * The button click
     */
    public void enter(final ViewChangeEvent event) {

    }

    /**
     * Adds the items double click.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void addItemsDoubleClick(final ItemClickEvent event) {
        LOGGER.info("addItemsDoubleClick method started");
        final Object itemId = availableRebate.getValue();
        shouldSave = true;
        selectedRebate.addItem(itemId);
        availableRebate.removeItem(itemId);
        LOGGER.info("addItemsDoubleClick method ended");
    }

    /**
     * Results item click.
     *
     * @param obj the id
     * @throws PortalException the portal exception
     */
    protected void resultsItemClick(final Object obj) {
        LOGGER.info("resultsItemClick method started");
        if (obj == null) {
            recordSelectedFlag = false;
        } else {
            recordSelectedFlag = true;
        }
        LOGGER.info("resultsItemClick method Ended");
    }

    /**
     * Adds the items button click.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void addItemsButtonClick(final ClickEvent event) {
        LOGGER.info("addItemsButtonClick method started");
        shouldSave = true;
        DiscountSearchDTO itemMasterDetailsList = (DiscountSearchDTO) availableRebate.getValue();
        resultsBean.addBean(itemMasterDetailsList);
        availableResultsBean.removeItem(itemMasterDetailsList);
        LOGGER.info("addItemsButtonClick method ended");
    }

    /**
     * Adds the all items button click.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void addAllItemsButtonClick(final ClickEvent event) {
        LOGGER.info("addAllItemsButtonClick method started");
        shouldSave = true;
        for (int i = 0; i < availableResultsBean.size(); i++) {
            resultsBean.addItem(availableResultsBean.getIdByIndex(i));
        }
        availableResultsBean.removeAllItems();
        LOGGER.info("addAllItemsButtonClick method ended");
    }

    /**
     * Removes the items button click.
     *
     * @param event the event
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void removeItemsButtonClick(final ClickEvent event) {
        LOGGER.info("addAllItemsButtonClick method started");
        shouldSave = true;
        final Object itemId = selectedRebate.getValue();
        resultsBean.removeItem(itemId);
        availableResultsBean.addItem(itemId);
        DiscountSearchDTO dto = (DiscountSearchDTO) selectedRebate.getValue();
        availableResultsBean.addBean(dto);
        resultsBean.removeItem(dto);
        LOGGER.info("addAllItemsButtonClick method ended");
    }

    /**
     * Removes the items double click.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    protected void removeItemsDoubleClick(final ItemClickEvent event) {
        LOGGER.info("removeItemsDoubleClick method started");
        final Object itemId = selectedRebate.getValue();
        shouldSave = true;
        availableResultsBean.addItem(itemId);
        resultsBean.removeItem(itemId);
        LOGGER.info("removeItemsDoubleClick method ended");
    }

    /**
     * Removes the all items button click.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void removeAllItemsButtonClick(final ClickEvent event) {
        LOGGER.info("removeAllItemsButtonClick method started");
        shouldSave = true;
        for (int i = 0; i < resultsBean.size(); i++) {
            availableResultsBean.addItem(resultsBean.getIdByIndex(i));
        }
        resultsBean.removeAllItems();
        LOGGER.info("removeAllItemsButtonClick method ended");
    }

    public void groupInfo(DiscountSearchDTO dto, Boolean flag) {
        discountName.setValue(dto.getDiscountName());
        discountName.setImmediate(true);
        discountNo.setValue(dto.getDiscountNo());
        discountDesc.setValue(dto.getDiscountDesc());
        if (flag) {
            discountName.setEnabled(true);
            discountNo.setEnabled(true);
            discountDesc.setEnabled(true);
        } else {
            discountName.setEnabled(false);
            discountNo.setEnabled(false);
            discountDesc.setEnabled(false);
        }
    }

    /**
     * Adds the include buttons.
     *
     * @return the component
     */
    private Component addIncludeButtons() {

        LOGGER.info("addIncludeButtons mehod Started ");
        HorizontalLayout includeButtonLayout = new HorizontalLayout();
        includeButtonLayout.setStyleName(ConstantsUtils.RESPONSIVE_GRID);
        includeButtonLayout.addStyleName("horizontalLayoutButton");
        includeButtonLayout.setMargin(true);
        includeButtonLayout.setSpacing(true);
        includeButtonLayout.addComponent(addBtn);
        addBtn.setEnabled(true);
        includeButtonLayout.addComponent(addallBtn);
        addallBtn.setEnabled(true);
        includeButtonLayout.addComponent(availableRebateExport);
        LOGGER.info("addIncludeButtons mehod RETURNS includeButtonLayout - Component ");

        return includeButtonLayout;
    }

    /**
     * Adds the exclude buttons.
     *
     * @return the component
     */
    private Component addExcludeButtons() {

        LOGGER.info("addExcludeButtons mehod Started ");
        HorizontalLayout excludeButtonLayout = new HorizontalLayout();
        excludeButtonLayout.setStyleName(ConstantsUtils.RESPONSIVE_GRID);
        excludeButtonLayout.addStyleName("horizontalLayoutButton");
        excludeButtonLayout.setMargin(true);
        excludeButtonLayout.setSpacing(true);
        excludeButtonLayout.addComponent(removeBtn);
        removeBtn.setEnabled(true);
        excludeButtonLayout.addComponent(removeallBtn);
        removeallBtn.setEnabled(true);
        excludeButtonLayout.addComponent(selectedRebateExport);
        LOGGER.info("addExcludeButtons mehod RETURNS selectedResults ");

        return excludeButtonLayout;
    }

    public void saveButtonClickLogic(boolean saveFlag) {
        try {
            if (StringUtils.isEmpty(String.valueOf(discountName.getValue().trim())) && StringUtils.isEmpty(String.valueOf(discountNo.getValue().trim())) && StringUtils.isEmpty(String.valueOf(discountDesc.getValue().trim()))) {
                MessageBox.showPlain(Icon.ERROR, "Save Error", "Not all required fields have been populated.  Please complete all required fields and try again. ", ButtonId.OK);
                return;
            } else {
                if (resultsBean.getItemIds().isEmpty()) {
                    MessageBox.showPlain(Icon.ERROR, "Save Error", "Please Select atleast one rebate to Selected Rebate list view", ButtonId.OK);
                    return;
                }
                //GTN-786
//                               if (!discountName.getValue().trim().matches(ValidationUtils.ALPHA_NUMERIC)) {
//                              AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Special Characters are not allowed in Discount Name");
//                              return;
//                 } if (!discountNo.getValue().trim().matches(ValidationUtils.ALPHA_NUMERIC)) {
//                              AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Special Characters are not allowed in Discount No");
//                              return;
//                 } if (!discountDesc.getValue().trim().matches(ValidationUtils.ALPHA_NUMERIC)) {
//                              AbstractNotificationUtils.getErrorNotification(ConstantsUtils.FIELD_ERROR, "Special Characters are not allowed in Discount Desc");
//                              return;
//                 }
                discountBinder.commit();
                final int deductionGroupSystemId = sessionDTO.getSystemId();
                if (deductionGroupSystemId == ConstantsUtils.ZERO_NUM) {
                    version = 0;
                    final Map duplicateItemGroupName = logic.getExistingItemgroupNames();
                    String deductionGroupname = discountName.getValue().trim();
                    if (duplicateItemGroupName.containsValue(String.valueOf(deductionGroupname))) {
                        if (deductionGroupSystemId == ConstantsUtils.ZERO_NUM) {
                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Entered Deduction group Name already exists ", ButtonId.OK);
                            return;
                        } else {
                            if (!duplicateItemGroupName.containsKey(String.valueOf(deductionGroupSystemId))) {
                                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Entered Deduction Group Name already exists ", ButtonId.OK);
                                return;
                            }
                        }
                    }
                }
                if (saveFlag) {
                    MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Save record " + discountName.getValue() + " ?", new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         *
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            try {
                                if (buttonId.name().equals(ConstantsUtils.YES)) {
                                    shouldSave = false;
                                    List<Integer> idList = saveButtonClick();
                                    String page = sessionDTO.getLogic();
                                    if (page.equalsIgnoreCase("Add")) {
                                        sessionDTO.setSystemId(idList.get(0));
                                        sessionDTO.setFromViewPage("Edit");
                                        sessionDTO.setLogic("edit");
                                        getUI().getNavigator().navigateTo(DiscountAddView.NAME);
                                    }
                                    final Notification notif = new Notification(
                                            "Deduction Group saved Successfully",
                                            Notification.Type.HUMANIZED_MESSAGE);

                                    notif.setDelayMsec(2000);
                                    notif.setPosition(Position.MIDDLE_CENTER);

                                    notif.show(Page.getCurrent());
                                }
                            } catch (Exception e) {

                                LOGGER.error(e);
                                MessageBox.showPlain(Icon.ERROR, "Required Fields Missing", "Not all required fields have been populated.  Please complete all required fields and try again. ", ButtonId.OK);
                            }

                            LOGGER.info("In configureFields search Button Listener Ended");
                        }
                    }, ButtonId.YES, ButtonId.NO);
                } else {
                    saveButtonClick();
                }
            }
        } catch (Exception e) {

            LOGGER.error(e);
            MessageBox.showPlain(Icon.ERROR, "Required Fields Missing", "Not all required fields have been populated.  Please complete all required fields and try again. ", ButtonId.OK);
        }
    }

    public void disableComponentsOnViewMode() {
        searchPanel.setVisible(false);
        availableRebatePanel.setVisible(false);
        addBtnsHLayout.setVisible(false);
        removeBtnsHLayout.setVisible(false);
        saveBtn.setVisible(false);
        resetBtn.setVisible(false);
    }

    private void setFieldSecurity(final Map<String, AppPermission> fieldItemHM) {
        LOGGER.info("Entering getFirstTab1");
        CommonUtil common = new CommonUtil();
        try {
            String mode = sessionDTO.getMode();
            List<Object> resultList = common.getFieldsForSecurity(UISecurityUtil.DEDUCTION_GROUPING, "Functional Screen");
            commonSecurity.removeComponentOnPermission(resultList, cssLayout, fieldItemHM, mode);
            commonSecurity.removeComponentOnPermission(resultList, searchCssLayout, fieldItemHM, mode);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("Ending getFirstTab1");
    }

    private void setButtonSecurity(Map<String, AppPermission> functionDeductionHM) {
        if (functionDeductionHM.get(ConstantsUtils.SEARCH_BUTTON) != null && ((AppPermission) functionDeductionHM.get(ConstantsUtils.SEARCH_BUTTON)).isFunctionFlag()) {
            searchBtn.setVisible(Boolean.TRUE);
        } else {
            searchBtn.setVisible(Boolean.FALSE);
        }
        if (functionDeductionHM.get(ConstantsUtils.RESET_BUTTON) != null && ((AppPermission) functionDeductionHM.get(ConstantsUtils.RESET_BUTTON)).isFunctionFlag()) {
            resetBtn.setVisible(Boolean.TRUE);
        } else {
            resetBtn.setVisible(Boolean.FALSE);
        }
        if (functionDeductionHM.get(ConstantsUtils.RESET_ALL_BUTTON) != null && ((AppPermission) functionDeductionHM.get(ConstantsUtils.RESET_ALL_BUTTON)).isFunctionFlag()) {
            resetButton.setVisible(Boolean.TRUE);
        } else {
            resetButton.setVisible(Boolean.FALSE);
        }
        if (functionDeductionHM.get(ConstantsUtils.BACK_BUTTON) != null && ((AppPermission) functionDeductionHM.get(ConstantsUtils.BACK_BUTTON)).isFunctionFlag()) {
            backBtn.setVisible(Boolean.TRUE);
        } else {
            backBtn.setVisible(Boolean.FALSE);
        }
        if (functionDeductionHM.get(ConstantsUtils.ADD_BUTTON) != null && ((AppPermission) functionDeductionHM.get(ConstantsUtils.ADD_BUTTON)).isFunctionFlag()) {
            addBtn.setVisible(Boolean.TRUE);
        } else {
            addBtn.setVisible(Boolean.FALSE);
        }
        if (functionDeductionHM.get(ConstantsUtils.ADD_ALL_BUTTON) != null && ((AppPermission) functionDeductionHM.get(ConstantsUtils.ADD_ALL_BUTTON)).isFunctionFlag()) {
            addallBtn.setVisible(Boolean.TRUE);
        } else {
            addallBtn.setVisible(Boolean.FALSE);
        }
        if (functionDeductionHM.get(ConstantsUtils.REMOVE_BUTTON) != null && ((AppPermission) functionDeductionHM.get(ConstantsUtils.REMOVE_BUTTON)).isFunctionFlag()) {
            removeBtn.setVisible(Boolean.TRUE);
        } else {
            removeBtn.setVisible(Boolean.FALSE);
        }
        if (functionDeductionHM.get(ConstantsUtils.REMOVE_ALL_BUTTON) != null && ((AppPermission) functionDeductionHM.get(ConstantsUtils.REMOVE_ALL_BUTTON)).isFunctionFlag()) {
            removeallBtn.setVisible(Boolean.TRUE);
        } else {
            removeallBtn.setVisible(Boolean.FALSE);
        }
        if (functionDeductionHM.get(ConstantsUtils.SAVE_BUTTON) != null && ((AppPermission) functionDeductionHM.get(ConstantsUtils.SAVE_BUTTON)).isFunctionFlag()) {
            saveBtn.setVisible(Boolean.TRUE);
        } else {
            saveBtn.setVisible(Boolean.FALSE);
        }
    }
}
