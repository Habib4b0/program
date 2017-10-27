/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.ui.lookup;

import com.stpl.app.contract.abstractsearch.util.ConstantUtil;
import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.dashboard.SearchPriceScheduleDTO;
import com.stpl.app.contract.dashboard.dto.PSContainerParentLookup;
import com.stpl.app.contract.dashboard.dto.PSCriteria;
import com.stpl.app.contract.dashboard.dto.PSDTO;
import com.stpl.app.contract.dashboard.dto.PsParentFilterGenerator;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.ui.ErrorfulFieldGroup;
import com.stpl.app.contract.util.CommonUIUtils;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.app.contract.util.ResponsiveUtils;
import com.stpl.app.serviceUtils.CommonUtils;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Asha
 */
@SuppressWarnings("serial")
public class PsParentLookup extends Window {

    /**
     * The price schedule id.
     */
    @UiField("priceScheduleId")
    private TextField priceScheduleId;
    /**
     * The price schedule no.
     */
    @UiField("priceScheduleNo")
    private TextField priceScheduleNo;
    /**
     * The price schedule name.
     */
    @UiField("priceScheduleName")
    private TextField priceScheduleName;

    /**
     * The price schedule type.
     */
    @UiField("priceScheduleType")
    private ComboBox priceScheduleType;

    /**
     * The price schedule status.
     */
    @UiField("priceScheduleStatus")
    private ComboBox priceScheduleStatus;

    /**
     * The item id.
     */
    @UiField("itemId")
    private TextField itemId;
    /**
     * The item no.
     */
    @UiField("itemNo")
    private TextField itemNo;
    /**
     * The item name.
     */
    @UiField("itemName")
    private TextField itemName;

    /**
     * The price schedule id.
     */
    @UiField("priceScheduleIdLb")
    private Label priceScheduleIdLb;
    /**
     * The price schedule no.
     */
    @UiField("priceScheduleNoLb")
    private Label priceScheduleNoLb;
    /**
     * The price schedule name.
     */
    @UiField("priceScheduleNameLb")
    private Label priceScheduleNameLb;

    /**
     * The price schedule type.
     */
    @UiField("priceScheduleTypeLb")
    private Label priceScheduleTypeLb;

    /**
     * The price schedule status.
     */
    @UiField("priceScheduleStatusLb")
    private Label priceScheduleStatusLb;

    /**
     * The item id.
     */
    @UiField("itemIdLb")
    private Label itemIdLb;
    /**
     * The item no.
     */
    @UiField("itemNoLb")
    private Label itemNoLb;
    /**
     * The item name.
     */
    @UiField("itemNameLb")
    private Label itemNameLb;

    /**
     * The parent price schedule name.
     */
    @UiField("cssLayout")
    private CssLayout cssLayout;

    private String parentPriceScheduleName;
    /**
     * The ps master.
     */
    final private PSDTO psMaster = new PSDTO();
    /**
     * The parent price schedule id.
     */
    private String parentPriceScheduleId;
    /**
     * The search.
     */
    @UiField("searchBtn")
    private Button search;
    /**
     * The reset.
     */
    @UiField("resetBtn")
    private Button reset;
    /**
     * The select button.
     */
    @UiField("selectBtn")
    Button selectBtn;
    /**
     * The close button.
     */
    @UiField("closeBtn")
    Button closeBtn;
    /**
     * The tabel.
     */
    @UiField("table")
    private ExtFilterTable table;
    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder;
    /**
     * The error msg.
     */
    @UiField("errorMsg")
    private ErrorLabel errorMsg;
    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(PsParentLookup.class);

    CommonUtil commonUtil = CommonUtil.getInstance();

    /**
     * The Constructor.
     *
     * @param parentPriceScheduleName the parent price schedule name
     * @param parentPriceScheduleId the parent price schedule id
     */
    public PsParentLookup() throws PortalException, SystemException {
        super("Parent Price Schedule Lookup");
        setContent(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-dashboard/parent-ps-lookup.xml"), this));
        init();
    }

    /**
     * Initialize the UI Content.
     */
    public void init() {
        center();
        setClosable(true);
        setModal(true);
        setWidth("850px");
        setHeight("600px");
        addToContent();
        configureFields();
        getBinder();
    }

    /**
     * To Bind the UI Components with fields.
     *
     * @return the binder
     */
    private ErrorfulFieldGroup getBinder() {

        binder = new ErrorfulFieldGroup(new BeanItem<>(psMaster));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);

        return binder;
    }

    /**
     * Adds the UI Content with components.
     *
     * @return layout
     */
    private void addToContent() {
        addResponsivenessToGrid();
        configureTabel();
    }

    /**
     * Adds the Selection Grid to UI.
     *
     * @return grid
     */
    protected void addResponsivenessToGrid() {
        ResponsiveUtils.addComponentInCssLayout(cssLayout, ResponsiveUtils.makeLabel(priceScheduleIdLb, false), priceScheduleId, true);
        ResponsiveUtils.addComponentInCssLayout(cssLayout, ResponsiveUtils.makeLabel(priceScheduleNoLb, false), priceScheduleNo, true);
        ResponsiveUtils.addComponentInCssLayout(cssLayout, ResponsiveUtils.makeLabel(priceScheduleNameLb, false), priceScheduleName, true);
        ResponsiveUtils.addComponentInCssLayout(cssLayout, ResponsiveUtils.makeLabel(priceScheduleTypeLb, false), priceScheduleType, true);
        ResponsiveUtils.addComponentInCssLayout(cssLayout, ResponsiveUtils.makeLabel(priceScheduleStatusLb, false), priceScheduleStatus, true);
        ResponsiveUtils.addComponentInCssLayout(cssLayout, ResponsiveUtils.makeLabel(itemIdLb, false), itemId, true);
        ResponsiveUtils.addComponentInCssLayout(cssLayout, ResponsiveUtils.makeLabel(itemNoLb, false), itemNo, true);
        ResponsiveUtils.addComponentInCssLayout(cssLayout, ResponsiveUtils.makeLabel(itemNameLb, false), itemName, true);
    }

    /**
     * Adds the search result table to UI.
     *
     * @return table
     */
    private ExtFilterTable configureTabel() {
        final LazyBeanItemContainer searchResults = new LazyBeanItemContainer(SearchPriceScheduleDTO.class, new PSContainerParentLookup(), new PSCriteria());
        table.setPageLength(NumericConstants.TEN);
        table.setImmediate(true);
        table.setSelectable(true);
        table.setContainerDataSource(searchResults);
        table.setVisibleColumns(ContractUtils.getInstance().psSearchTable);
        table.setColumnHeaders(ContractUtils.getInstance().psColHeaders);
        table.setFilterBarVisible(true);
        table.setFilterGenerator(new PsParentFilterGenerator());
        table.setFilterDecorator(new ExtDemoFilterDecorator());
        table.addStyleName("filterbar");
        table.addStyleName("table-header-normal");
        table.setWidth(NumericConstants.NINETY_EIGHT, Unit.PERCENTAGE);
        selectBtn.setEnabled(false);
        closeBtn.setEnabled(true);
        table.addItemClickListener(new ItemClickListener() {

            /**
             * Method used to table value an dits listener.
             */
            @SuppressWarnings("PMD")
            @UiHandler("selectBtn")
            public void itemClick(final ItemClickEvent event) {
                try {
                    final SearchPriceScheduleDTO companySearchDto = (SearchPriceScheduleDTO) searchResults.getItem(event.getItemId()).getBean();
                    parentPriceScheduleName = companySearchDto.getPriceScheduleName();
                    parentPriceScheduleId = String.valueOf(companySearchDto.getPriceScheduleId());
                    selectBtn.setEnabled(true);
                    closeBtn.setEnabled(true);
                } catch (Exception exception) {
                    LOGGER.error(exception);

                }

            }
        });
        return table;
    }

    /**
     * Customizing the Fields.
     */
    protected void configureFields() {

        itemName.addValidator(new RegexpValidator(ConstantsUtils.SPECIAL_CHAR, "Item Name can only be Alphanumeric"));
        itemName.addValidator(new StringLengthValidator("Item Name should be less than NumericConstants.HUNDRED characters", 0, NumericConstants.HUNDRED, true));
        itemName.setImmediate(true);
        itemName.setValidationVisible(true);

        itemNo.setImmediate(true);
        itemNo.setValidationVisible(true);
        itemNo.addValidator(new RegexpValidator(ConstantsUtils.SPECIAL_CHAR, "Item No can only be Alphanumeric"));
        itemNo.addValidator(new StringLengthValidator("Item No should be less than 50 characters", 0, NumericConstants.FIFTY, true));

        itemId.setImmediate(true);
        itemId.setValidationVisible(true);
        itemId.addValidator(new RegexpValidator(ConstantsUtils.SPECIAL_CHAR, "Item Id can only be Alphanumeric"));
        itemId.addValidator(new StringLengthValidator("Item ID should be less than 38 characters", 0, NumericConstants.THIRTY_EIGHT, true));

        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        priceScheduleName.addValidator(new StringLengthValidator("Price Schedule Name should be less than 100 characters", 0, NumericConstants.HUNDRED, true));
        priceScheduleName.addValidator(new RegexpValidator(ConstantUtil.AZ_AZ_S, "Price Schedule Name  can contain only digits,alphabets"));
        priceScheduleName.setImmediate(true);
        priceScheduleName.setValidationVisible(true);
        priceScheduleName.setDescription((String) priceScheduleName.getValue());
        priceScheduleName.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used to Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {

                priceScheduleName.setDescription((String) priceScheduleName.getValue());

            }
        });
        priceScheduleNo.setImmediate(true);
        priceScheduleNo.setValidationVisible(true);
        priceScheduleNo.addValidator(new StringLengthValidator("Price Schedule No should be less than 50 characters", 0, NumericConstants.FIFTY, true));
        priceScheduleNo.addValidator(new RegexpValidator(ConstantUtil.AZ_AZ_S, "Price Schedule No can contain only digits,alphabets"));
        priceScheduleNo.setDescription((String) priceScheduleNo.getValue());
        priceScheduleNo.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used to Called when a Button has been clicked.
             */
            public void valueChange(final Property.ValueChangeEvent event) {

                priceScheduleNo.setDescription((String) priceScheduleNo.getValue());

            }
        });
        priceScheduleId.setImmediate(true);
        priceScheduleId.setValidationVisible(true);
        priceScheduleId.focus();
        setCloseShortcut(ShortcutAction.KeyCode.ESCAPE);
        priceScheduleId.addValidator(new StringLengthValidator("Price Schedule Id should be less than 38 characters", 0, NumericConstants.THIRTY_EIGHT, true));
        priceScheduleId.addValidator(new RegexpValidator(ConstantUtil.AZ_AZ_S, "Price Schedule Id can contain only digits,alphabets"));
        priceScheduleId.setDescription((String) priceScheduleId.getValue());
        priceScheduleId.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used to Called when a Button has been clicked.
             */
            public void valueChange(final Property.ValueChangeEvent event) {

                priceScheduleId.setDescription((String) priceScheduleId.getValue());

            }
        });

        commonUtil.loadComboBox(priceScheduleStatus, UIUtils.STATUS, false);
        commonUtil.loadComboBox(priceScheduleType, UIUtils.PS_TYPE, false);

        search.addClickListener(new ClickListener() {

            /**
             * Method used to Called when a Button has been clicked.
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering ParentLookup search operation");
                try {
                    PSCriteria searchCriteria = new PSCriteria();
                    List<Object> collapsedColumns = new ArrayList<>();
                    for (Object item : table.getVisibleColumns()) {
                        if (table.isColumnCollapsed(item)) {
                            collapsedColumns.add(item);
                        }
                    }

                    binder.commit();

                    if (StringUtils.isBlank(String.valueOf(binder.getField("priceScheduleId").getValue())) && StringUtils.isBlank(String.valueOf(binder.getField("priceScheduleNo").getValue()))
                            && (binder.getField("priceScheduleType").getValue() == null || ConstantsUtils.SELECT_ONE.equals(String.valueOf(binder.getField("priceScheduleType").getValue()))) 
                            && (binder.getField("priceScheduleStatus").getValue() == null  || ConstantsUtils.SELECT_ONE.equals(String.valueOf(binder.getField("priceScheduleStatus").getValue())))
                            && StringUtils.isBlank(String.valueOf(binder.getField("priceScheduleName").getValue())) && StringUtils.isBlank(String.valueOf(binder.getField("itemId").getValue()))
                            && StringUtils.isBlank(String.valueOf(binder.getField("itemNo").getValue())) && StringUtils.isBlank(String.valueOf(binder.getField("itemName").getValue()))) {
                        MessageBox.showPlain(Icon.ERROR, "Search Error", "Please enter Search Criteria", ButtonId.OK);
                        
                        final BeanItemContainer<PSDTO> searchResultbeans = new BeanItemContainer<>(PSDTO.class);
                        table.setContainerDataSource(searchResultbeans);
                        table.setVisibleColumns(ContractUtils.getInstance().psSearchTable);
                        table.setColumnHeaders(ContractUtils.getInstance().psColHeaders);
                    } else {
                        searchCriteria.setCustomDirty(true);
                        final LazyBeanItemContainer searchResults = new LazyBeanItemContainer(SearchPriceScheduleDTO.class, new PSContainerParentLookup(binder), searchCriteria);
                        table.setContainerDataSource(searchResults);
                        if (searchResults.size() > CommonUtils.ZERO) {
                            CommonUIUtils.successNotification(ConstantsUtils.SEARCH_COMPLETED);
                        } else {
                            CommonUIUtils.successNotification(ConstantsUtils.NO_RESULTS_COMPLETED);
                        }
                        table.setVisibleColumns(ContractUtils.getInstance().psSearchTable);
                        table.setColumnHeaders(ContractUtils.getInstance().psColHeaders);
                        table.setWidth(NumericConstants.HUNDRED, UNITS_PERCENTAGE);
                        table.setSelectable(true);
                        searchCriteria.setCustomDirty(false);
                        for (Object propertyId : collapsedColumns) {
                            table.setColumnCollapsed(propertyId, true);
                        }
                        searchCriteria.setCustomDirty(true);
                    }
                } catch (FieldGroup.CommitException ex) {
                    LOGGER.error(ex);
                } catch (Exception exception) {
                    LOGGER.error(exception);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004), new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            return;
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
                    LOGGER.error(exception);
                }
                LOGGER.debug("Ending ParentLookup search operation");
            }
        });

        reset.addClickListener(new ClickListener() {

            /**
             * Method used to Called when a Button has been clicked.
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering ParentLookup reset operation");
                MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to reset the page to default/previous values " + " ?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {
                            selectBtn.setEnabled(false);
                            closeBtn.setEnabled(true);
                            refresh();
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

                LOGGER.debug("Ending ParentLookup reset operation");
            }
        });

    }

    public String getParentPriceScheduleNameValue() {
        return parentPriceScheduleName;
    }

    public String getParentPriceScheduleIdValue() {
        return parentPriceScheduleId;
    }

    public void refresh() {
        try {
            List<Object> collapsedColumns = new ArrayList<>();
            for (Object item : table.getVisibleColumns()) {
                if (table.isColumnCollapsed(item)) {
                    collapsedColumns.add(item);
                }
            }
            binder.setItemDataSource(new BeanItem<>(new PSDTO()));

            binder.getErrorDisplay().clearError();
            final BeanItemContainer<PSDTO> searchResultbeans = new BeanItemContainer<>(PSDTO.class);
            table.setContainerDataSource(searchResultbeans);
            table.setVisibleColumns(ContractUtils.getInstance().psSearchTable);
            table.setColumnHeaders(ContractUtils.getInstance().psColHeaders);
            binder.getErrorDisplay().clearError();
            for (Object propertyId : collapsedColumns) {
                table.setColumnCollapsed(propertyId, true);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);

        }
    }

    @UiHandler("closeBtn")
    public void closeBtn(Button.ClickEvent event) {
        parentPriceScheduleName = StringUtils.EMPTY;
        parentPriceScheduleId = StringUtils.EMPTY;
        close();
    }

    @UiHandler("selectBtn")
    public void selectBtn(Button.ClickEvent event) {
        close();
    }

}
