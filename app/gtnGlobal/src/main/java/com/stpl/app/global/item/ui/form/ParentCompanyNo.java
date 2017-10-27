package com.stpl.app.global.item.ui.form;

import com.stpl.app.global.item.dto.CompanyContainer;
import com.stpl.app.global.company.dto.CompanyCriteria;
import com.stpl.app.global.company.dto.CompanyFilterGenerator;
import com.stpl.app.global.company.dto.SearchCompanyForm;

import org.jboss.logging.Logger;

import com.stpl.app.global.company.dto.SearchDTO;
import com.stpl.app.global.company.logic.CompanySearchLogic;
import com.stpl.app.global.company.util.CommonUtils;
import com.stpl.app.global.company.util.UIUtils;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.ErrorHandler;
import static com.vaadin.server.Sizeable.UNITS_PERCENTAGE;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class ParentCompanyNo to get the parent companyNo window.
 */
public final class ParentCompanyNo extends Window {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ParentCompanyNo.class);

    /**
     * The error msg.
     */
    @UiField("errorMsg")
    private ErrorLabel errorMsg;
    CompanyCriteria searchCriteria = new CompanyCriteria();

    /**
     * The company no.
     */
    @UiField("companyNoLB")
    private Label companyNoLB;
    @UiField("companyNo")
    private TextField companyNo;

    /**
     * The company id.
     */
    @UiField("companyIdLB")
    private Label companyIdLB;
    @UiField("companyId")
    private TextField companyId;

    /**
     * The company name.
     */
    @UiField("companyNameLB")
    private Label companyNameLB;
    @UiField("companyName")
    private TextField companyName;

    /**
     * The company status.
     */
    @UiField("companyStatusLB")
    private Label companyStatusLB;
    @UiField("companyStatus")
    private ComboBox companyStatus;

    /**
     * The company type.
     */
    @UiField("companyTypeLB")
    private Label companyTypeLB;
    @UiField("companyType")
    private ComboBox companyType;

    @UiField("cssLayout")
    CssLayout cssLayout;
    /**
     * The parent company no.
     */
    @SuppressWarnings("PMD")
    /**
     * The parent company Id.
     */
    private TextField parentCompanyId = new TextField();

    /**
     * The parent company no.
     */
    private TextField parentCompanyNo = new TextField();
    /**
     * The parent company no.
     */
    private TextField parentCompanyName = new TextField();

    /**
     * The btn select.
     */
    @UiField("selectBtn")
    private Button btnSelect;

    @UiField("searchBtn")
    private Button searchBtn;

    @UiField("resetBtn")
    private Button resetBtn;

    /**
     * The table.
     */
    @UiField("table")
    private ExtFilterTable table;

    /**
     * The parent sys id.
     */
    private TextField parentSysId;
    SessionDTO sessionDTO = new SessionDTO();

    /**
     * The parent company number.
     */
    public TextField parentCompanyNumber;
    final ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<>(new SearchDTO()));
    final com.stpl.app.global.abstractsearch.util.CommonUtils abstractUtils = new com.stpl.app.global.abstractsearch.util.CommonUtils();

    /**
     * The Constructor to initialize the parent company numberS.
     *
     * @param parentCompanyNo the parent company no
     * @param parentSysId the parent sys id
     */
    public ParentCompanyNo(final TextField parentCompanyName, final TextField parentCompanyId, final TextField parentSysId) {

        super("Parent CompanyNo");
        addStyleName(ConstantsUtils.BOOTSTRAP);
        addStyleName(ConstantsUtils.BOOTSTRAP_BB);
        this.parentCompanyId = parentCompanyId;
        this.parentSysId = parentSysId;
        this.parentCompanyName = parentCompanyName;
        try {
            setContent(Clara.create(getClass().getResourceAsStream("/declarativeui/itemmaster/entitycodeno.xml"), this));
            init();
            getBinder();
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            LOGGER.error(errorMsg);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
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
        } catch (PortalException ex) {
            LOGGER.error(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
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
        } catch (Exception ex) {
            LOGGER.error(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
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
     * Initialize the the form.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void init() throws SystemException, PortalException {
        center();
        setClosable(true);
        setModal(true);
        setHeight("600px");
        addToContent();

        configureFields();
    }

    /**
     * Adds the contents to form.
     *
     * @return the vertical layout
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    private void addToContent() {
        addToTable();
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private ErrorfulFieldGroup getBinder() {

        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
        return binder;
    }

    /**
     * Configure the fields.
     *
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    protected void configureFields() throws PortalException, SystemException {
        LOGGER.debug("configureFields");
        addStyleName(ConstantsUtils.BOOTSTRAP);
        addStyleName(ConstantsUtils.BOOTSTRAP_BB);

        btnSelect.setEnabled(false);
        setResizable(false);
        table.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
        table.setColumnCollapsingAllowed(true);
        companyName.addValidator(new StringLengthValidator("Company Name Should be less than 100 characters", 0, NumericConstants.HUNDRED, true));
        companyName.setImmediate(true);
        companyName.setValidationVisible(true);

        companyNo.addValidator(new StringLengthValidator("Company No Should be less than 50 characters", 0, NumericConstants.FIFTY, true));
        companyNo.setImmediate(true);
        companyNo.setValidationVisible(true);

        companyId.addValidator(new StringLengthValidator("Company ID Should be less than 38 characters", 0, NumericConstants.THIRTY_EIGHT, true));
        companyId.setImmediate(true);
        companyId.setValidationVisible(true);
        companyId.focus();
        setCloseShortcut(ShortcutAction.KeyCode.ESCAPE);

        CompanySearchLogic companyLogic = new CompanySearchLogic();
        new CommonUtils().getNativeSelect(companyStatus, companyLogic.getDropDownList(UIUtils.STATUS));
        companyStatus.setNullSelectionAllowed(true);
        companyStatus.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);

        new CommonUtils().getNativeSelect(companyType, companyLogic.getCompanyType(UIUtils.COMPANY_TYPE));
        companyType.setNullSelectionAllowed(true);
        companyType.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        btnSelect.setWidth(ConstantsUtils.BTN_WIDTH);
        btnSelect.addClickListener(new ClickListener() {

            /**
             * Logic for select button click Logic.
             *
             * @param event
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering inside  SELECT  method ");
                parentCompanyId.setReadOnly(false);
                parentCompanyNo.setReadOnly(false);
                parentCompanyName.setReadOnly(false);
                parentCompanyId.setValue(sessionDTO.getCompanyId());
                parentCompanyNo.setValue(sessionDTO.getCompanyNo());
                parentCompanyName.setValue(sessionDTO.getCompanyName());
                parentCompanyNo.setReadOnly(true);
                parentCompanyId.setReadOnly(true);
                parentCompanyName.setReadOnly(true);
                parentSysId.setValue(sessionDTO.getParentSysId());

                close();
                LOGGER.debug("Ending PaymentCompanyNo SELECT  method ");
            }
        });
    }

    /**
     * Gets the parent company number.
     *
     * @return the parent company number
     */
    public TextField getParentCompanyNumber() {
        return parentCompanyNumber;
    }

    /**
     * Gets the parent sys id.
     *
     * @return the parent sys id
     */
    public TextField getParentSysId() {
        return parentSysId;
    }

    /**
     * Sets the parent sys id.
     *
     * @param parentSysId the new parent sys id
     */
    public void setParentSysId(final TextField parentSysId) {
        this.parentSysId = parentSysId;
    }

    @UiHandler("searchBtn")
    public void btnSearchLogic(Button.ClickEvent event) {
        LOGGER.debug("Entering btnSearchLogic - ParentCompanyNo Search operation");
        List<Object> collapsedColumns = new ArrayList<>();
        try {
            for (Object item : table.getVisibleColumns()) {
                if (table.isColumnCollapsed(item)) {
                    collapsedColumns.add(item);
                }
            }

            if (binder.getField("companyId").getValue().toString().trim().isEmpty() && binder.getField("companyNo").getValue().toString().trim().isEmpty() && binder.getField("companyName").getValue().toString().trim().isEmpty()
                    && (binder.getField("companyStatus").getValue() == null || (Integer) binder.getField("companyStatus").getValue() == 0)
                    && (binder.getField("companyType").getValue() == null || (Integer) binder.getField("companyType").getValue() == 0)) {
                MessageBox.showPlain(Icon.INFO, "Search Criteria", "Please enter Search Criteria", new MessageBoxListener() {
                    /**
                     * After clicking button, function will be executed.
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        return;

                    }
                }, ButtonId.OK);
            } else {

                searchCriteria.setCustomDirty(true);
                searchCriteria = new CompanyCriteria();

                final LazyBeanItemContainer searchResults = new LazyBeanItemContainer(
                        SearchCompanyForm.class, new CompanyContainer(binder),
                        searchCriteria);

                table.setContainerDataSource(searchResults);
                table.markAsDirtyRecursive();
                table.setImmediate(true);
                table.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
                table.setVisibleColumns(UIUtils.getInstance().parentCompanyNoColumnItem);
                table.setColumnHeaders(UIUtils.getInstance().parentCompanyNoHeadersItem);

                if (searchResults.size() > com.stpl.app.util.GeneralCommonUtils.ZERO) {
                    CommonUIUtils.successNotification(ConstantsUtils.SEARCH_COMPLETED);
                } else {
                    CommonUIUtils.successNotification(ConstantsUtils.NO_RESULTS_COMPLETED);
                }
                table.setSelectable(true);
                table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
                    /**
                     * logic for item click event.
                     *
                     * @param event
                     */
                    @SuppressWarnings("PMD")
                    public void itemClick(final ItemClickEvent event) {
                        LOGGER.debug("Entering btnSearchLogic - ParentCompanyNo Search operation - itemClick");
                        tableItemClickListener(event, searchResults);
                        btnSelect.setEnabled(true);
                        LOGGER.debug("Ending btnSearchLogic - ParentCompanyNo Search operation - itemClick");

                    }
                });

            }
            searchCriteria.setCustomDirty(false);
            for (Object propertyId : collapsedColumns) {
                table.setColumnCollapsed(propertyId, true);
            }
            searchCriteria.setCustomDirty(true);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("Ending btnSearchLogic - ParentCompanyNo Search operation");
    }

    /**
     * Implementation of reset button.
     */
    @UiHandler("resetBtn")
    public void btnResetLogic(Button.ClickEvent event) {
        LOGGER.debug("Entering btnResetLogic - ParentCompanyNo Search operation");
        List<Object> collapsedColumns = new ArrayList<>();
        for (Object item : table.getVisibleColumns()) {
            if (table.isColumnCollapsed(item)) {
                collapsedColumns.add(item);
            }
        }
        table.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
        binder.setItemDataSource(new BeanItem<>(new SearchDTO()));
        binder.getErrorDisplay().clearError();
        final BeanItemContainer<SearchCompanyForm> searchResultbeans = new BeanItemContainer<>(SearchCompanyForm.class);
        table.setContainerDataSource(searchResultbeans);
        table.setVisibleColumns(UIUtils.getInstance().parentCompanyNoColumnItem);
        table.setColumnHeaders(UIUtils.getInstance().parentCompanyNoHeadersItem);
        btnSelect.setEnabled(false);

        searchCriteria.setCustomDirty(false);
        for (Object propertyId : collapsedColumns) {
            table.setColumnCollapsed(propertyId, true);
        }
        searchCriteria.setCustomDirty(true);
        LOGGER.debug("Ending btnResetLogic - ParentCompanyNo Search operation");
    }

    /**
     * Implementation of item select.
     *
     * @param event the event
     * @param searchResults the search results
     * @throws Exception the exception
     */
    public void itemSelectLogic(final ItemClickEvent event, final LazyBeanItemContainer searchResults) {
        LOGGER.debug("Entering itemSelectLogic");
        final SearchCompanyForm searchForm = (SearchCompanyForm) searchResults.getItem(event.getItemId()).getBean();
        LOGGER.debug("selected item--------->" + searchForm.getCompanyName());
        sessionDTO.setCompanyId(searchForm.getCompanyId());
        sessionDTO.setCompanyNo(searchForm.getCompanyNo());
        sessionDTO.setCompanyName(searchForm.getCompanyName());
        sessionDTO.setParentSysId(searchForm.getSystemId());
        btnSelect.setEnabled(true);
        LOGGER.debug("Ending itemSelectLogic");

    }

    /**
     * Sets the column and data source for the table and returns the table.
     *
     * @return Table
     */
    public void addToTable() {
        LOGGER.debug("Entering addToTable ");
        final BeanItemContainer<SearchCompanyForm> searchResultbeans = new BeanItemContainer<>(SearchCompanyForm.class);
        table.markAsDirty();
        table.setContainerDataSource(searchResultbeans);
        table.setVisibleColumns(UIUtils.getInstance().parentCompanyNoColumnItem);
        table.setColumnHeaders(UIUtils.getInstance().parentCompanyNoHeadersItem);
        table.setPageLength(NumericConstants.SIX);
        table.setComponentError(null);
        table.setFilterBarVisible(true);
        table.setFilterDecorator(new ExtDemoFilterDecorator());
        table.setValidationVisible(false);
        table.addStyleName(ConstantsUtils.FILTER_BAR);
        table.setErrorHandler(new ErrorHandler() {
            /**
             * invoked when an error occurs.
             *
             * @param event - ErrorEvent
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                // parses the error.
            }
        });
        table.addStyleName(ConstantsUtils.FILTER_BAR);
        table.setFilterGenerator(new CompanyFilterGenerator());
        LOGGER.debug(" Ends addComponentInGridPosition  ");

    }

    public void tableItemClickListener(final ItemClickEvent event, final LazyBeanItemContainer searchResults) {
        try {
            itemSelectLogic(event, searchResults);
        } catch (Exception ex) {
            LOGGER.error(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
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
}
