package com.stpl.app.adminconsole.discount.ui.form;

import org.jboss.logging.Logger;
import com.stpl.app.adminconsole.discount.dto.DiscountSearchDTO;
import com.stpl.app.adminconsole.discount.ui.view.DiscountSearchView;
import com.stpl.app.adminconsole.util.ResponsiveUtils;
import com.stpl.ifs.ui.CustomGridLayout;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;

// TODO: Auto-generated Javadoc
/**
 * The Class DiscountViewForm.
 */
public class DiscountViewForm extends CustomComponent implements View {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(DiscountAddForm.class);
    /**
     * The error msg.
     */
    private final ErrorLabel errorMsg = new ErrorLabel();

    /**
     * The selectedRebate.
     */
    private ExtFilterTable selectedRebate = new ExtFilterTable();

    /**
     * The back button.
     */
    private final Button backBtn = new Button("BACK");

    /**
     * The discount name.
     */
    private final TextField discountName = new TextField();

    /**
     * The discount no.
     */
    private final TextField discountNo = new TextField();

    /**
     * The discount desc.
     */
    private final TextField discountDesc = new TextField();

    /**
     * The Constant DISCOUNT_TABLE_COLUMNS.
     */
    public final Object[] discountTableColumns = new Object[]{
        "rebateName", "rebateNo", "rebateScheduleType", "rebateProgramType", "rebateScheduleCategory", "rebatePlanLevel"};
    /**
     * The Constant DISCOUNT_TABLE_HEADER.
     */
    public final String[] discountTableHeader = new String[]{
        "RebateName", "RebateNo", "RebateScheduleType", "RebateProgramType", "RebateScheduleCategory", "RebatePlanLevel"};

    /**
     * The results bean.
     */
    private BeanItemContainer<DiscountSearchDTO> resultsBean = new BeanItemContainer<>(DiscountSearchDTO.class);

    /**
     * Gets the results bean.
     *
     * @return the results bean
     */
    public BeanItemContainer<DiscountSearchDTO> getResultsBean() {
        return resultsBean;
    }

    /**
     * Sets the results bean.
     *
     * @param resultsBean the new results bean
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
     * Gets the back btn.
     *
     * @return the back btn
     */
    public Button getBackBtn() {
        return backBtn;
    }

    /**
     * Gets the discount name.
     *
     * @return the discount name
     */
    public TextField getDiscountName() {
        return discountName;
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
    public Object[] getDiscountTableColumns() {
        return discountTableColumns;
    }

    /**
     * Gets the discount table header.
     *
     * @return the discount table header
     */
    public String[] getDiscountTableHeader() {
        return discountTableHeader;
    }

    /**
     * Instantiates a new discount view form.
     */
    public DiscountViewForm(final BeanItemContainer selectedRebateBean) {
        super();
        this.resultsBean = selectedRebateBean;
        init();

    }

    public DiscountViewForm() {

    }

    /**
     * Inits the.
     */
    public final void init() {
        LOGGER.debug("init Method Started ");
        setCompositionRoot(addToContent());
        LOGGER.debug("init Method Ended ");
    }

    /**
     * Adds the to content.
     *
     * @return the component
     */
    private Component addToContent() {
        LOGGER.debug("addToContent method started ");
        final VerticalLayout content = new VerticalLayout();

        content.addComponent(errorMsg);
        content.setMargin(true);
        content.setSpacing(true);
        content.addComponent(addFieldsPanel());
        content.addComponent(addResultsTables());
        content.addComponent(addButtons());

        configureFields();
        LOGGER.debug("addToContent method RETURNS content-Component");

        return content;
    }

    /**
     * Adds the customer group search.
     *
     * @return the panel
     */
    private Panel addFieldsPanel() {
        LOGGER.debug("addFieldsPanel Method Started ");
        final VerticalLayout vLayout = new VerticalLayout();
        final Panel panel = new Panel();

        final CustomGridLayout gridLayout = new CustomGridLayout(4, NumericConstants.TWO);
        gridLayout.setMargin(true);
        gridLayout.setSpacing(true);
        gridLayout.addComponent(new Label("Discount Name :"));
        gridLayout.addComponent(discountName);
        gridLayout.addComponent(new Label("Discount No :"));
        gridLayout.addComponent(discountNo);
        gridLayout.addComponent(new Label("Discount Desc :"));
        gridLayout.addComponent(discountDesc);

        vLayout.addComponent(gridLayout);
        panel.setContent(vLayout);
        panel.setCaption("Data Panel: ");
        LOGGER.debug("addFieldsPanel Method RETURNS panel ");
        return panel;
    }

    /**
     * Adds the availableRebate table.
     *
     * @return the filter table
     */
    private Panel addResultsColumnTable() {
        LOGGER.debug("addResultsTables method started ");
        final Panel panel = new Panel();
        panel.setCaption("Selected Rebate");
        VerticalLayout out = new VerticalLayout();
        out.setSpacing(true);
        out.setMargin(true);
        HorizontalLayout butonLayout = new HorizontalLayout();
        butonLayout.setSpacing(true);
        butonLayout.setMargin(true);
        selectedRebate.markAsDirty();
        selectedRebate.setFilterBarVisible(true);
        selectedRebate.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedRebate.setContainerDataSource(resultsBean);
        selectedRebate.setVisibleColumns(discountTableColumns);
        selectedRebate.setColumnHeaders(discountTableHeader);
        selectedRebate.setSelectable(false);
        selectedRebate.setPageLength(NumericConstants.FIVE);
        selectedRebate.setSizeFull();
        selectedRebate.setColumnCollapsingAllowed(true);
        selectedRebate.setImmediate(true);
        out.addComponent(selectedRebate);
        out.addComponent(butonLayout);
        panel.setContent(out);
        LOGGER.debug("addResultsTable method RETURNS Table-results ");

        return panel;
    }

    /**
     * Adds the buttons.
     *
     * @return the component
     */
    private Component addButtons() {
        LOGGER.debug("addButtons method Started ");
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(backBtn);
        backBtn.setEnabled(true);
        LOGGER.debug("addButtons method RETURNS layout ");
        return layout;
    }

    /**
     * Configure fields.
     */
    public void configureFields() {

        backBtn.addClickListener(new ClickListener() {

            @Override
            /**
             * (non-Javadoc)
             *
             * @see
             * com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
             */
            public void buttonClick(final ClickEvent event) {

                getUI().getNavigator().navigateTo(DiscountSearchView.NAME);

            }
        });
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    @Override
    public void enter(final ViewChangeEvent event) {
        return;
    }

    /**
     * Adds the availableRebate tables.
     *
     * @return the panel
     */
    private Panel addResultsTables() {
        final Panel panel = new Panel();
        final VerticalLayout tableLayout = new VerticalLayout();
        tableLayout.setMargin(true);
        tableLayout.setSpacing(true);
        tableLayout.addComponent(ResponsiveUtils.addNaviButton(selectedRebate));
        tableLayout.addComponent(addResultsColumnTable());
        panel.setContent(tableLayout);
        return panel;
    }

    public void groupInfo(DiscountSearchDTO dto) {
        discountName.setValue(dto.getDiscountName());
        discountName.setImmediate(true);
        discountNo.setValue(dto.getDiscountNo());
        discountDesc.setValue(dto.getDiscountDesc());
        discountName.setReadOnly(true);
        discountNo.setReadOnly(true);
        discountDesc.setReadOnly(true);
    }

}
