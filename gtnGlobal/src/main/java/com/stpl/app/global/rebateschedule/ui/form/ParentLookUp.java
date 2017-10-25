package com.stpl.app.global.rebateschedule.ui.form;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.rebateschedule.dto.RSFilterGenerate;
import org.jboss.logging.Logger;
import com.stpl.app.global.rebateschedule.dto.RebateScheduleSearchDTO;
import com.stpl.app.global.rebateschedule.logic.RebateScheduleLogic;
import com.stpl.app.global.rebateschedule.logic.tablelogic.ParentLookUpTableLogic;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.GeneralCommonUtils;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.ResponsiveUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

import org.apache.commons.lang.StringUtils;

import java.util.Collection;

import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

// TODO: Auto-generated Javadoc
/**
 * The Class for creating a floating pop up window for Parent Look UP.
 */
public final class ParentLookUp extends Window {

    /**
     * The rebate schedule id.
     */
    @UiField("scheduleId")
    private TextField rebateScheduleId;

    /**
     * The rebate schedule name.
     */
    @UiField("scheduleName")
    private TextField rebateScheduleName;

    /**
     * The rebate schedule no.
     */
    @UiField("scheduleNo")
    private TextField rebateScheduleNo;

    /**
     * The rebate schedule status.
     */
    @UiField("scheduleStatus")
    private ComboBox rebateScheduleStatus;

    /**
     * The rebate schedule type.
     */
    @UiField("scheduleType")
    private ComboBox rebateScheduleType;

    /**
     * The item no.
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
     * The rebate schedule id.
     */
    @UiField("scheduleIdLb")
    private Label rebateScheduleIdLb;

    /**
     * The rebate schedule name.
     */
    @UiField("scheduleNameLb")
    private Label rebateScheduleNameLb;

    /**
     * The rebate schedule no.
     */
    @UiField("scheduleNoLb")
    private Label rebateScheduleNoLb;

    /**
     * The rebate schedule status.
     */
    @UiField("scheduleStatusLb")
    private Label rebateScheduleStatusLb;

    /**
     * The rebate schedule type.
     */
    @UiField("scheduleTypeLb")
    private Label rebateScheduleTypeLb;

    /**
     * The item no.
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
     * The rebate schedule trans ref no.
     */
    private String rebateScheduleTransRefNo = StringUtils.EMPTY;

    /**
     * The rebate schedule trans ref name.
     */
    private String rebateScheduleTransRefName = StringUtils.EMPTY;

    /**
     * The rebate schedule trans ref id.
     */
    private String rebateScheduleTransRefID = StringUtils.EMPTY;

    /**
     * The search.
     */
    @UiField("searchBtn")
    private Button searchBtn;

    /**
     * The reset.
     */
    @UiField("resetBtn")
    private Button resetBtn;
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

    @UiField("tablelayout")
    private VerticalLayout tablelayout;

    @UiField("controlLayout")
    private HorizontalLayout controlLayout;

    ParentLookUpTableLogic parentLookUpTableLogic = new ParentLookUpTableLogic();

    private ExtPagedTable table = new ExtPagedTable(parentLookUpTableLogic);

    BeanItemContainer<RebateScheduleSearchDTO> resultsContainer = new BeanItemContainer<>(RebateScheduleSearchDTO.class);
    RebateScheduleSearchDTO rebateschedulesearchdto;

    /**
     * The error msg.
     */
    public final ErrorLabel errorMsg = new ErrorLabel();

    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder;
    @UiField("lookup")
    Panel lookup;
    @UiField("cssLayout")
    CssLayout cssLayout;

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ParentLookUp.class);
    private boolean isItemCliked = false;

    /**
     * Parent Lookup
     */
    public ParentLookUp() {
        super();
        try {
            setContent(Clara.create(getClass().getResourceAsStream("/clara/common/parentlookup.xml"), this));

            init();

        } catch (SystemException ex) {
             LOGGER.error(ex);

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
        } catch (Exception e) {
            // TODO Auto-generated catch block
            LOGGER.error(e);
            LOGGER.debug("Unable to load the parent lookup :");
        }

    }

    /**
     * Gets the error msg.
     *
     * @return the errorMsg
     */
    public ErrorLabel getErrorMsg() {
        return errorMsg;
    }

    /**
     * Gets the rebate schedule id.
     *
     * @return the rebateScheduleId
     */
    public TextField getRebateScheduleId() {
        return rebateScheduleId;
    }

    /**
     * Gets the rebate schedule name.
     *
     * @return the rebateScheduleName
     */
    public TextField getRebateScheduleName() {
        return rebateScheduleName;
    }

    /**
     * Gets the rebate schedule no.
     *
     * @return the rebateScheduleNo
     */
    public TextField getRebateScheduleNo() {
        return rebateScheduleNo;
    }

    /**
     * Gets the rebate schedule type.
     *
     * @return the rebateScheduleType
     */
    public ComboBox getRebateScheduleType() {
        return rebateScheduleType;
    }

    /**
     * Gets the search.
     *
     * @return the search
     */
    public Button getSearch() {
        return searchBtn;
    }

    /**
     * Gets the reset.
     *
     * @return the reset
     */
    public Button getReset() {
        return resetBtn;
    }

    /**
     * Gets the tabel.
     *
     * @return the tabel
     */
    public ExtFilterTable getTabel() {
        return table;
    }

    /**
     * Sets the binder.
     *
     * @param binder the binder to set
     */
    public void setBinder(final ErrorfulFieldGroup binder) {
        this.binder = binder;
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private ErrorfulFieldGroup getBinder() {

        binder = new ErrorfulFieldGroup(new BeanItem<RebateScheduleSearchDTO>(new RebateScheduleSearchDTO()));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);

        return binder;
    }

    /**
     * Initializes the width, height, modality and adds the components of the
     * look up.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public void init() throws SystemException {
        center();
        setClosable(true);
        setModal(true);
        setHeight("800px");
        setWidth("50%");
        addToContent();
        configureFields();
        getBinder();
        validateFields();
    }

    /**
     * Method to add buttons, labels, and table components to the look up.
     *
     * @return the panel
     */
    private void addToContent() {
        addToGrid();
        addTable();
    }

    /**
     *
     */
    protected void addToGrid() {
        ResponsiveUtils.addComponentInCssLayout(cssLayout, rebateScheduleIdLb, rebateScheduleId, true);
        ResponsiveUtils.addComponentInCssLayout(cssLayout, rebateScheduleNameLb, rebateScheduleName, true);
        ResponsiveUtils.addComponentInCssLayout(cssLayout, rebateScheduleNoLb, rebateScheduleNo, true);
        ResponsiveUtils.addComponentInCssLayout(cssLayout, rebateScheduleTypeLb, rebateScheduleType, true);
        ResponsiveUtils.addComponentInCssLayout(cssLayout, rebateScheduleStatusLb, rebateScheduleStatus, true);

        ResponsiveUtils.addComponentInCssLayout(cssLayout, itemIdLb, itemId, true);
        ResponsiveUtils.addComponentInCssLayout(cssLayout, itemNoLb, itemNo, true);
        ResponsiveUtils.addComponentInCssLayout(cssLayout, itemNameLb, itemName, true);

    }

    /**
     * Method to add Rebate Schedule table.
     *
     * @return the table
     */
    private void addTable() {

        parentLookUpTableLogic.setContainerDataSource(resultsContainer);
        parentLookUpTableLogic.setPageLength(NumericConstants.TEN);
        parentLookUpTableLogic.sinkItemPerPageWithPageLength(false);

        table.setImmediate(true);
        table.setSizeFull();
        table.setVisibleColumns(CommonUIUtils.REBATE_SCHEDULE_COLUMNS);
        table.setColumnHeaders(CommonUIUtils.REBATE_SCHEDULE_HEADER);
        table.setFilterBarVisible(true);
        table.addStyleName(ConstantsUtils.FILTER_BAR);
        table.setFilterDecorator(new ExtDemoFilterDecorator());
        table.setFilterGenerator(new RSFilterGenerate());
        table.setSelectable(true);
        tablelayout.addComponent(table);
        ResponsiveUtils.getResponsiveControls(parentLookUpTableLogic.createControls(), controlLayout);

        table.addItemClickListener(new ItemClickListener() {

            /**
             * Method used for table item click listener
             */
            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                try {

                    final RebateScheduleSearchDTO companySearchDto = getBeanFromId(event.getItemId());              
                    isItemCliked = true;
                    rebateScheduleTransRefNo = companySearchDto.rebateScheduleNo;
                    rebateScheduleTransRefName = companySearchDto.rebateScheduleName;
                    rebateScheduleTransRefID = companySearchDto.rebateScheduleId;
                } catch (Exception exception) {
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1014), new MessageBoxListener() {
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
                    LOGGER.error(exception);
                }
            }
        });
    }

    /**
     * Method to put together the drop down list boxes, buttons and its click
     * listeners for the look up.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    protected void configureFields() throws SystemException {
        addStyleName(ConstantsUtils.BOOTSTRAP);
        addStyleName(ConstantsUtils.BOOTSTRAP_BB);
        rebateScheduleIdLb.setValue("Rebate Schedule ID");
        rebateScheduleNameLb.setValue("Rebate Schedule Name");
        rebateScheduleNoLb.setValue("Rebate Schedule No");
        rebateScheduleStatusLb.setValue("Rebate Schedule Status");
        rebateScheduleTypeLb.setValue("Rebate Schedule Type");
        itemIdLb.setValue("Item ID");
        itemNoLb.setValue("Item No");
        itemNameLb.setValue("Item Name");

        RebateScheduleLogic rebateLogic = new RebateScheduleLogic();
        rebateScheduleStatus.setNullSelectionAllowed(true);
        rebateScheduleStatus.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        rebateScheduleStatus.setContainerDataSource(new IndexedContainer(rebateLogic.getHelperDetails(GeneralCommonUtils.STATUS)));
        rebateScheduleStatus.select(ConstantsUtils.SELECT_ONE);
        rebateScheduleStatus.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Logic for value change event.
             *
             * @param event
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && StringUtils.EMPTY.equals(event.getProperty().getValue())) {
                    rebateScheduleStatus.select(ConstantsUtils.SELECT_ONE);
                }

            }
        });

        LOGGER.debug("In configureFields lodding-RebateScheduleType Dblb ListType=" + GeneralCommonUtils.REBATE_SCHEDULE_TYPE);
        rebateScheduleType.setNullSelectionAllowed(true);
        rebateScheduleType.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        rebateScheduleType.setContainerDataSource(new IndexedContainer(rebateLogic.getHelperDetails(GeneralCommonUtils.REBATE_SCHEDULE_TYPE)));
        rebateScheduleType.select(ConstantsUtils.SELECT_ONE);
        rebateScheduleType.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Logic for value change event.
             *
             * @param event
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && StringUtils.EMPTY.equals(event.getProperty().getValue())) {
                    rebateScheduleType.select(ConstantsUtils.SELECT_ONE);
                }

            }
        });

        rebateScheduleId.focus();
        rebateScheduleName.setData("maxlengthvalidationhundred,reflookupmaxlengthvalidationrebateschedulename,null,null");

        rebateScheduleName.setImmediate(true);
        rebateScheduleName.setValidationVisible(true);

        rebateScheduleNo.setImmediate(true);
        rebateScheduleNo.setValidationVisible(true);

        rebateScheduleNo.setData("maxlengthvalidationfifty,reflookupmaxlengthvalidationrebatescheduleno,null,null");

        rebateScheduleId.setImmediate(true);
        rebateScheduleId.setValidationVisible(true);

        rebateScheduleId.setData("maxlengthvalidationfifty,reflookupmaxlengthvalidationrebatescheduleid,null,null");

        itemId.setImmediate(true);
        itemId.setValidationVisible(true);

        itemName.setImmediate(true);
        itemName.setValidationVisible(true);

        itemNo.setImmediate(true);
        itemNo.setValidationVisible(true);

        setCloseShortcut(ShortcutAction.KeyCode.ESCAPE);
        
        closeBtn.addClickListener(new ClickListener() {

            /**
             * Method used for close button logic
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                rebateScheduleId.setReadOnly(false);
        rebateScheduleName.setReadOnly(false);
        rebateScheduleNo.setReadOnly(false);
        rebateScheduleTransRefNo = StringUtils.EMPTY;
        rebateScheduleTransRefName = StringUtils.EMPTY;
        rebateScheduleTransRefID = StringUtils.EMPTY;
        rebateScheduleId.setValue(StringUtils.EMPTY);
        rebateScheduleName.setValue(StringUtils.EMPTY);
        rebateScheduleNo.setValue(StringUtils.EMPTY);
        rebateScheduleId.setReadOnly(true);
        rebateScheduleName.setReadOnly(true);
        rebateScheduleNo.setReadOnly(true);
        close();
            }
        });

        searchBtn.addClickListener(new ClickListener() {

            /**
             * Method used for search button logic
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.debug("Entering ParetnLookUp Search operation");
                    try {
                        binder.commit();
                    } catch (FieldGroup.CommitException ex) {
                        LOGGER.error(ex);
                    }

                    String rsId = binder.getField("rebateScheduleId").getValue().toString();
                    String rsNo = binder.getField(ConstantsUtils.REBATE_SCHEDULE_NO).getValue().toString();
                    String rsName = binder.getField(ConstantsUtils.REBATE_SCHEDULE_NAME).getValue().toString();
                    String rsStatus = (binder.getField(ConstantsUtils.REBATE_SCHEDULE_STATUS).getValue() != null) ? binder.getField(ConstantsUtils.REBATE_SCHEDULE_STATUS).getValue().toString() : null;
                    String rsType = (binder.getField(ConstantsUtils.REBATE_SCHEDULE_TYPE).getValue() != null) ? binder.getField(ConstantsUtils.REBATE_SCHEDULE_TYPE).getValue().toString() : null;
                    String itemId = binder.getField("itemId").getValue().toString();
                    String itemNo = binder.getField(ConstantsUtils.ITEM_NO).getValue().toString();
                    String itemName = binder.getField(ConstantsUtils.ITEM_NAME).getValue().toString();
                    if (StringUtils.isBlank(rsId) && StringUtils.isBlank(rsNo) && StringUtils.isBlank(rsName) && StringUtils.isBlank(itemId) && StringUtils.isBlank(itemNo)
                            && StringUtils.isBlank(itemName) && StringUtils.isBlank(rsStatus) && StringUtils.isBlank(rsType)) {
                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "No Search Criteria", "Please enter search criteria", new MessageBoxListener() {
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

                    parentLookUpTableLogic.setSearchData(binder);
                    parentLookUpTableLogic.setCurrentPage(1);
                    table.setFilterDecorator(new ExtDemoFilterDecorator());
                    if (table.getContainerDataSource().size() > Constants.ZERO) {
                        CommonUIUtils.successNotification(ConstantsUtils.SEARCH_COMPLETED);
                    } else {
                        CommonUIUtils.successNotification(ConstantsUtils.NO_RESULTS_COMPLETED);
                    }

                    LOGGER.debug("Ending ParetnLookUp Search operation");
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }

        });

        resetBtn.addClickListener(new ClickListener() {

            /**
             * Method used for reset button logic
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering ParetnLookUp Reset operation");
                MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to reset the page to default/previous values " + " ?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {
                            try {

                                refeshLookup();
                            } catch (Exception exception) {
                                final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1006), new MessageBoxListener() {
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
                                LOGGER.error(exception);

                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

                LOGGER.debug("Ending ParetnLookUp Reset operation");
            }
        });
        selectBtn.addClickListener(new ClickListener() {
            /**
             * Logic for select button click Logic.
             *
             * @param event
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering inside  SELECT  method ");
                if (null != table.getValue()) {
                    close();
                } else {
                    final MessageBox msg = MessageBox.showPlain(Icon.WARN, "Select error", "Please select a record.", new MessageBoxListener() {

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
                }
                LOGGER.debug("Ending Payment RS ID SELECT  method ");
            }
        });
    }

    public String getRebateScheduleTransRefNo() {
        return rebateScheduleTransRefNo;
    }

    public String getRebateScheduleTransRefName() {
        return rebateScheduleTransRefName;
    }

    public String getRebateScheduleTransRefID() {
        return rebateScheduleTransRefID;
    }

    public void refeshLookup() {
        isItemCliked = false;
        binder.getErrorDisplay().clearError();
        binder.setItemDataSource(new BeanItem<RebateScheduleSearchDTO>(new RebateScheduleSearchDTO()));
        table.removeAllItems();
        parentLookUpTableLogic.clearAll();
        parentLookUpTableLogic.setReset(true);
        parentLookUpTableLogic.fireSetData(binder, false);
    }

    public boolean isItemCliked() {
        return isItemCliked;
    }

    public void setItemCliked(boolean isItemCliked) {
        this.isItemCliked = isItemCliked;
    }

    public void validateFields() {
        Collection<Field<?>> collection = binder.getFields();
        CommonUtil commmonUtil = CommonUtil.getInstance();
        for (Field field : collection) {
            if (field instanceof TextField) {
                TextField textField = (TextField) field;
                commmonUtil.textValidation(textField, textField.getData());
            }
        }
    }

    /**
     *
     * @param obj
     * @return
     */
    public RebateScheduleSearchDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof RebateScheduleSearchDTO) {
            targetItem = new BeanItem<RebateScheduleSearchDTO>(
                    (RebateScheduleSearchDTO) obj);
        }
        return (RebateScheduleSearchDTO) targetItem.getBean();
    }

}
