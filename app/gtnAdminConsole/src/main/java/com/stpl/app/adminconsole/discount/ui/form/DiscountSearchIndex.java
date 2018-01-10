package com.stpl.app.adminconsole.discount.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.stpl.app.adminconsole.archive.ui.form.ArchiveIndex;
import com.stpl.app.adminconsole.discount.dto.DiscountSearchDTO;
import com.stpl.app.adminconsole.discount.logic.DiscountLogic;
import com.stpl.app.adminconsole.discount.ui.view.DiscountAddView;
import com.stpl.app.adminconsole.discount.ui.view.DiscountView;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.app.adminconsole.util.ResponsiveUtils;
import com.stpl.app.adminconsole.util.ValidationUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.validator.RegexpValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.ui.errorhandling.ErrorLabel;


/**
 * The Class DiscountSearchIndex.
 */
public class DiscountSearchIndex extends CustomComponent implements View {

    /**
     * The space.
     */
    private final Label space = new Label("&nbsp;", ContentMode.HTML);
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ArchiveIndex.class);
    /**
     * The error msg.
     */
    private final ErrorLabel errorMsg = new ErrorLabel();
    /**
     * The Native Select.
     */

    private final NativeSelect businessProcess = new NativeSelect();

    /**
     * The from.
     */
    private PopupDateField from = new PopupDateField();
    /**
     * The date to.
     */
    private PopupDateField toDate = new PopupDateField();

    /**
     * The results.
     */
    private ExtPagedFilterTable results = new ExtPagedFilterTable();
    /**
     * The resultsColumnTable.
     */
    private ExtFilterTable resultsColumnTable = new ExtFilterTable();

    /**
     * The archive.
     */
    private final Button searchBtn = new Button("SEARCH");
    /**
     * The delete.
     */
    private final Button resetBtn = new Button("RESET");

    /**
     * The add btn.
     */
    private final Button addBtn = new Button("ADD");

    /**
     * The edit btn.
     */
    private final Button editBtn = new Button("EDIT");

    /**
     * The view btn.
     */
    private final Button viewBtn = new Button("VIEW");

    /**
     * The delete btn.
     */
    private final Button deleteBtn = new Button("DELETE");

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
    public final Object[] discountTableColumns = new Object[]{"discountName", "discountNo", "discountDesc", "version", "createdBy", "createdDate"};

    /**
     * The Constant DISCOUNT_TABLE_HEADER.
     */
    public final String[] discountTableHeader = new String[]{"Discount Name", "Discount No", "Discount Desc", "Version", "Created By", "Created Date"};

    /**
     * The results bean.
     */
    private BeanItemContainer<DiscountSearchDTO> resultsBean = new BeanItemContainer<>(DiscountSearchDTO.class);

    /**
     * The Discount Search dto.
     */
    private DiscountSearchDTO discountSearchDTO;
    /**
     * The Discount group binder.
     */
    private CustomFieldGroup discountBinder;

    /**
     * The discount logic.
     */
    private DiscountLogic discountLogic = new DiscountLogic();

    /**
     * The discount logic.
     */
    private int deductionGroupSid;
    /**
     *
     * /**
     * The Constant NULLOBJECT.
     */
    private static final BeanItem<?> NULLOBJECT = null;

    /**
     * The excel export button.
     */
    private final Button excelExport = new Button();

    /**
     * Instantiates a new discount search index.
     */
    public DiscountSearchIndex() {
        super();
        discountSearchDTO = new DiscountSearchDTO();
        discountBinder = new CustomFieldGroup(new BeanItem<>(discountSearchDTO));
        init();
    }

    /**
     * Gets the from.
     *
     * @return the from
     */
    public PopupDateField getFrom() {
        return from;
    }

    /**
     * Sets the from.
     *
     * @param from the new from
     */
    public void setFrom(final PopupDateField from) {
        this.from = from;
    }

    /**
     * Gets the to.
     *
     * @return the to
     */
    public PopupDateField getTo() {
        return toDate;
    }

    /**
     * Sets the to.
     *
     * @param toDate the new to
     */
    public void setTo(final PopupDateField toDate) {
        this.toDate = toDate;
    }

    /**
     * Gets the results.
     *
     * @return the results
     */
    public ExtPagedFilterTable getResults() {
        return results;
    }

    /**
     * Sets the results.
     *
     * @param results the new results
     */
    public void setResults(final ExtPagedFilterTable results) {
        this.results = results;
    }

    /**
     * Gets the results column tableMap.
     *
     * @return the results column tableMap
     */
    public ExtFilterTable getResultsColumnTable() {
        return resultsColumnTable;
    }

    /**
     * Sets the results column tableMap.
     *
     * @param resultsColumnTable the new results column tableMap
     */
    public void setResultsColumnTable(final ExtFilterTable resultsColumnTable) {
        this.resultsColumnTable = resultsColumnTable;
    }

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
     * Gets the discount logic.
     *
     * @return the discount logic
     */
    public DiscountLogic getDiscountLogic() {
        return discountLogic;
    }

    /**
     * Sets the discount logic.
     *
     * @param discountLogic the new discount logic
     */
    public void setDiscountLogic(final DiscountLogic discountLogic) {
        this.discountLogic = discountLogic;
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
     * Gets the business process.
     *
     * @return the business process
     */
    public NativeSelect getBusinessProcess() {
        return businessProcess;
    }

    /**
     * Gets the search btn.
     *
     * @return the search btn
     */
    public Button getSearchBtn() {
        return searchBtn;
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
     * Gets the adds the btn.
     *
     * @return the adds the btn
     */
    public Button getAddBtn() {
        return addBtn;
    }

    /**
     * Gets the edits the btn.
     *
     * @return the edits the btn
     */
    public Button getEditBtn() {
        return editBtn;
    }

    /**
     * Gets the view btn.
     *
     * @return the view btn
     */
    public Button getViewBtn() {
        return viewBtn;
    }

    /**
     * Gets the delete btn.
     *
     * @return the delete btn
     */
    public Button getDeleteBtn() {
        return deleteBtn;
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
     * Gets the discount tableMap columns.
     *
     * @return the discount tableMap columns
     */
    public Object[] getDiscountTableColumns() {
        return discountTableColumns;
    }

    /**
     * Gets the discount tableMap header.
     *
     * @return the discount tableMap header
     */
    public String[] getDiscountTableHeader() {
        return discountTableHeader;
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
        final VerticalLayout out = new VerticalLayout();
        final Panel panel = new Panel();
        final Panel tablePanel = new Panel();
        panel.setCaption("Deduction Group Search Criteria");
        tablePanel.setCaption("Results");
        out.setMargin(true);
        out.setSpacing(true);
        out.addComponent(addSearchPanel());
        out.addComponent(addButtons());
        panel.setContent(out);
        content.addComponent(panel);
        content.addComponent(ResponsiveUtils.addNaviButton(results));
        tablePanel.setContent(addResultsTable());
        content.addComponent(tablePanel);
        content.addComponent(ResponsiveUtils.getResponsiveControls(results.createControls()));
        content.addComponent(space);
        content.addComponent(space);
        content.addComponent(addNavigationButtons());
        content.setStyleName("bootstrap-search-criteria");
        getBinder();
        configureFields();
        LOGGER.debug("addToContent method RETURNS content-Component");

        return content;
    }

    /**
     * Adds the customer group search.
     *
     * @return the panel
     */
    private HorizontalLayout addSearchPanel() {
        LOGGER.debug("addFirstPanel Method Started ");
        final HorizontalLayout hlayout = new HorizontalLayout();
        hlayout.setStyleName("responsiveGrid");

        final CssLayout gridLayout = new CssLayout();
        gridLayout.setSizeFull();
        gridLayout.addComponent(new Label("Discount Name :"));
        gridLayout.addComponent(discountName);
        gridLayout.addComponent(new Label("Discount No :"));
        gridLayout.addComponent(discountNo);
        gridLayout.addComponent(new Label("Discount Desc :"));
        gridLayout.addComponent(discountDesc);

        hlayout.addComponent(gridLayout);
        LOGGER.debug("addFirstPanel Method RETURNS panel ");
        return hlayout;
    }

    /**
     * Adds the results tableMap.
     *
     * @return the filter tableMap
     */
    private ExtFilterTable addResultsTable() {
        LOGGER.debug("addResultsTables method started ");

        results.markAsDirty();
        results.setFilterBarVisible(true);
        results.setFilterDecorator(new ExtDemoFilterDecorator());
        results.setContainerDataSource(resultsBean);
        results.setCaption("Results");
        results.setVisibleColumns(discountTableColumns);
        results.setColumnHeaders(discountTableHeader);
        results.setPageLength(NumericConstants.EIGHT);
        results.setWidth("100%");
        results.setSizeFull();
        results.setColumnCollapsingAllowed(true);
        results.setImmediate(true);
        results.setSelectable(true);
        results.sinkItemPerPageWithPageLength(false);
        results.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             *
             *
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                LOGGER.debug("In results results.addValueChangeListener started");
                resultsItemClick(event.getProperty().getValue());
                LOGGER.debug("In results results.addValueChangeListener Ended");
            }
        });

        LOGGER.debug("addResultsTable method RETURNS Table-results ");

        return results;
    }

    /**
     * Adds the buttons.
     *
     * @return the component
     */
    private Component addButtons() {
        LOGGER.debug("addButtons method Started ");
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setStyleName("responsiveGrid");
        layout.setSpacing(true);
        layout.addComponent(searchBtn);
        searchBtn.setEnabled(true);
        layout.addComponent(resetBtn);
        resetBtn.setEnabled(true);
        LOGGER.debug("addButtons method RETURNS layout ");

        return layout;
    }

    /**
     * Adds the navigation buttons.
     *
     * @return the component
     */
    private Component addNavigationButtons() {
        LOGGER.debug("addNavigationButtons method Started ");
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setStyleName("responsiveTabGrid");
        layout.setSpacing(true);
        layout.addComponent(addBtn);
        addBtn.setEnabled(true);
        layout.addComponent(editBtn);
        editBtn.setEnabled(true);
        layout.addComponent(viewBtn);
        viewBtn.setEnabled(true);
        layout.addComponent(deleteBtn);
        deleteBtn.setEnabled(true);
        layout.addComponent(excelExport);
        LOGGER.debug("addNavigationButtons method RETURNS layout ");

        return layout;
    }

    /**
     * Configure fields.
     */
    @SuppressWarnings("serial")
    public void configureFields() {

        discountName.addValidator(new RegexpValidator(ValidationUtils.SEARCH_SPECIAL_CHARACTER, "Discount Name can contain only " + ValidationUtils.SEARCH_SPCL_CHAR_MSG));
        discountNo.addValidator(new RegexpValidator(ValidationUtils.SEARCH_SPECIAL_CHARACTER, "Discount Number can contain only " + ValidationUtils.SEARCH_SPCL_CHAR_MSG));
        discountDesc.addValidator(new RegexpValidator(ValidationUtils.SEARCH_SPECIAL_CHARACTER, "Discount Desc can contain only " + ValidationUtils.SEARCH_SPCL_CHAR_MSG));

        discountName.setImmediate(true);
        discountNo.setImmediate(true);
        discountDesc.setImmediate(true);

        excelExport.setIcon(new ThemeResource("../../icons/excel.png"));
        excelExport.setStyleName("link");
        excelExport.setDescription(ConstantsUtils.EXCEL_EXPORT);
        excelExport.setIconAlternateText(ConstantsUtils.EXCEL_EXPORT);

        searchBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In configureFields searchBtn listener started");
                try {
                    if (StringUtils.isEmpty(String.valueOf(discountName.getValue().trim())) && StringUtils.isEmpty(String.valueOf(discountNo.getValue().trim()))
                            && StringUtils.isEmpty(String.valueOf(discountDesc.getValue().trim()))) {
                        AbstractNotificationUtils.getErrorNotification("Search Error", "No search criteria have been entered.  Please enter search criteria and try again.");
                        return;
                    } else {
                        searchButtonClickLogic();
                    }

                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4005));
                }
                LOGGER.debug("In configureFields search Button Listener Ended");
            }
        });

        addBtn.addClickListener(new ClickListener() {

            @Override
            /**
             * The button click
             */
            public void buttonClick(final ClickEvent event) {

                VaadinSession.getCurrent().setAttribute(ConstantsUtils.DEDUCTION_GROUP_SYS_ID, 0);
                VaadinSession.getCurrent().setAttribute(CommonUtil.LOGIC, "add");
                getUI().getNavigator().navigateTo(DiscountAddView.NAME);

            }
        });
        editBtn.addClickListener(new ClickListener() {
            @Override
            /**
             * The button click
             */
            public void buttonClick(final ClickEvent event) {
                if (deductionGroupSid == 0) {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, ConstantsUtils.NO_DEDUCTION_ERROR_INFO, ButtonId.OK);
                } else {
                    try {
                        VaadinSession.getCurrent().setAttribute(ConstantsUtils.DEDUCTION_GROUP_SYS_ID, deductionGroupSid);
                        VaadinSession.getCurrent().setAttribute(ConstantsUtils.FRONT_VIEW_PAGE, "Edit");
                        VaadinSession.getCurrent().setAttribute(CommonUtil.LOGIC, "edit");
                        getUI().getNavigator().navigateTo(DiscountAddView.NAME);
                    } catch (Exception ex) {
                       LOGGER.error(ex.getMessage());
                    }
                }

            }
        });
        viewBtn.addClickListener(new ClickListener() {

            @Override
            /**
             * The button click
             */

            public void buttonClick(final ClickEvent event) {
                if (deductionGroupSid == 0) {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, ConstantsUtils.NO_DEDUCTION_ERROR_INFO, ButtonId.OK);
                } else {
                    VaadinSession.getCurrent().setAttribute(ConstantsUtils.DEDUCTION_GROUP_SYS_ID, deductionGroupSid);
                    VaadinSession.getCurrent().setAttribute(CommonUtil.LOGIC, "view");
                    getUI().getNavigator().navigateTo(DiscountView.NAME);

                }
            }
        });

        deleteBtn.addClickListener(new ClickListener() {
            @Override
            /**
             * The button click
             */
            public void buttonClick(final ClickEvent event) {
                deleteButtonClickLogic();


            }
        });

        resetBtn.addClickListener(new ClickListener() {
            @Override
            /**
             * The button click
             */
            public void buttonClick(final ClickEvent event) {
                MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to reset the page to default/previous values " + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {
                    /**
                     * Method is called when save button is clicked.
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                            try {
                                resetButtonClickLogic();
                            } catch (Exception e) {
                                LOGGER.error(e.getMessage());
                                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4002));
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

            }
        });

        excelExport.addClickListener(new ClickListener() {
            /**
             * Method is called when available excel export button is clicked
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields selectedResultsExcelExport.addClickListener started");

                ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(results), "Deduction Group Search", "Deduction Group Search", "DeductionGroupSearch.xls", false);
                excel.export();

                LOGGER.debug("In configureFields selectedResultsExcelExport.addClickListener Ended");
            }
        });

    }

    /**
     * Search button click logic.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void searchButtonClickLogic() throws FieldGroup.CommitException {
        LOGGER.debug("searchButtonClickLogic method Started ");
        discountBinder.commit();
        resultsBean.removeAllItems();
        DiscountSearchDTO searchDTO = new DiscountSearchDTO();
        if (discountName.getValue().trim() != null && !discountName.getValue().trim().equals(StringUtils.EMPTY)) {
            searchDTO.setDiscountName(discountName.getValue().trim());
        }
        if (discountNo.getValue().trim() != null && !discountNo.getValue().trim().equals(StringUtils.EMPTY)) {
            searchDTO.setDiscountNo(discountNo.getValue().trim());
        }
        if (discountDesc.getValue().trim() != null && !discountDesc.getValue().trim().equals(StringUtils.EMPTY)) {
            searchDTO.setDiscountDesc(discountDesc.getValue().trim());
        }

        final List<DiscountSearchDTO> searchResults = new ArrayList<>();
        resultsBean.removeAllItems();
        if (searchResults.isEmpty()) {
            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No results could be found that match the entered search criteria.", ButtonId.OK);
        } else {
            resultsBean.addAll(searchResults);
            results.firePagedChangedEvent();
            CommonUIUtils.getMessageNotification(ConstantsUtils.SEARCH_COMPLETED);
        }
        LOGGER.debug("searchButtonClickLogic method ENDED ");
    }

    /**
     * Reset button click logic.
     *
     */
    protected void resetButtonClickLogic() {

        LOGGER.debug("resetButtonClickLogic Started");
        discountBinder.getErrorDisplay().clearError();
        discountName.setValue(ConstantsUtils.EMPTY);
        discountNo.setValue(ConstantsUtils.EMPTY);
        discountDesc.setValue(ConstantsUtils.EMPTY);
        resultsBean.removeAllItems();

        // This is used to reset paged filter table no of page to 1
        results.firePagedChangedEvent();

        deductionGroupSid = 0;
        LOGGER.debug("resetButtonClickLogic Ended");

    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private CustomFieldGroup getBinder() {

        LOGGER.debug("getBinder method Started ");
        discountBinder.bindMemberFields(this);
        discountBinder.setItemDataSource(new BeanItem<>(discountSearchDTO));
        discountBinder.setBuffered(true);
        discountBinder.setErrorDisplay(errorMsg);
        LOGGER.debug("getBinder method RETURNS DiscountBinder - Binder ");

        return discountBinder;
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    @Override
    public void enter(final ViewChangeEvent event) {

        resetButtonClickLogic();

    }

    /**
     * Results item click.
     *
     * @param obj the id
     */
    protected void resultsItemClick(final Object obj) {
        LOGGER.debug("resultsItemClick Method Started");
        if (obj == null) {
            deductionGroupSid = 0;
        } else {
            BeanItem<?> targetItem;
            if (obj instanceof BeanItem<?>) {
                targetItem = (BeanItem<?>) obj;
            } else if (obj instanceof DiscountSearchDTO) {
                targetItem = new BeanItem<>((DiscountSearchDTO) obj);
            } else {
                targetItem = NULLOBJECT;
            }
            deductionGroupSid = ((DiscountSearchDTO) targetItem.getBean()).getDeductionGroupSid();
        }
        LOGGER.debug("resultsItemClick Method Ended");
    }

    /**
     * Delete button click logic.
     *
     * @param event the event
     */
    protected void deleteButtonClickLogic() {
        LOGGER.debug("deleteButtonClickLogic Method started");
        if (deductionGroupSid == 0) {
            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, ConstantsUtils.NO_DEDUCTION_ERROR_INFO, ButtonId.OK);
        } else {
            DiscountSearchDTO dTO = (DiscountSearchDTO) results.getValue();
            MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to delete record " + dTO.getDiscountName()
                   
                    + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {
                        /**
                         * Method is called when Delete button is clicked
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals(ConstantsUtils.YES)) {
                                try {
                                    final String deletedDeductionGroupName = discountLogic.deletedeductionGroup(deductionGroupSid);
                                    if (deletedDeductionGroupName != null && !ConstantsUtils.EMPTY.equals(deletedDeductionGroupName)) {
                                        CommonUIUtils.getMessageNotification("Deduction Group " + deletedDeductionGroupName + " deleted Successfully");
                                        resultsBean.removeAllItems();
                                        DiscountSearchDTO searchDTO = new DiscountSearchDTO();
                                        if (discountName.getValue() != null && !discountName.getValue().equals(StringUtils.EMPTY)) {
                                            searchDTO.setDiscountName(discountName.getValue());
                                        }
                                        if (discountNo.getValue() != null && !discountNo.getValue().equals(StringUtils.EMPTY)) {
                                            searchDTO.setDiscountNo(discountNo.getValue());
                                        }
                                        if (discountDesc.getValue() != null && !discountDesc.getValue().equals(StringUtils.EMPTY)) {
                                            searchDTO.setDiscountDesc(discountDesc.getValue());
                                        }
                                        final List<DiscountSearchDTO> searchResults = new ArrayList<>();

                                        resultsBean.addAll(searchResults);
                                    }
                                    deductionGroupSid = 0;

                                } catch (SystemException e) {
                                    final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
                                    LOGGER.error(e.getMessage());
                                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                                } catch (PortalException e) {
                                    LOGGER.error(e.getMessage());
                                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4004));
                                } catch (Exception e) {
                                    LOGGER.error(e.getMessage());
                                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4004));
                                }
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);
        }

        LOGGER.debug("deleteButtonClickLogic Method ended");
    }

}
