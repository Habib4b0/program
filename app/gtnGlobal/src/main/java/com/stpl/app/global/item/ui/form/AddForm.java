package com.stpl.app.global.item.ui.form;

import com.stpl.app.global.abstractsearch.view.AbstractSearchView;
import com.stpl.app.global.common.ui.InformationLayout;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.item.dto.ItemIrtIdentifierDTO;
import com.stpl.app.global.item.dto.ItemMasterDTO;
import com.stpl.app.global.item.dto.ItemPricingDTO;
import com.stpl.app.global.item.dto.SearchDTO;
import com.stpl.app.global.item.logic.ItemSearchLogic;
import com.stpl.app.global.item.ui.view.ItemAddView;
import com.stpl.app.global.item.util.CommonUtils;
import com.stpl.app.global.item.util.FieldNameUtils;
import com.stpl.app.global.item.util.UIUtils;
import com.stpl.app.model.ItemIdentifier;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.ItemPricingQualifier;
import com.stpl.app.model.ItemQualifier;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.NotesTabForm;
import com.stpl.app.ui.StplCustomComponent;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.FunctionNameUtil;
import com.stpl.app.util.HelperDTO;
import com.stpl.app.util.TabNameUtil;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.app.util.ValidationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import java.util.logging.Level;
import org.asi.ui.custommenubar.CustomMenuBar;


/**
 * The Class AddForm.
 */
public final class AddForm extends StplCustomComponent {

    /**
     * The Constant NAME.
     */
    public static final String NAME = "view";
    /**
     * The error msg.
     */
    @UiField("errorLB")
    private ErrorLabel errorMsg;
    /**
     * The item logic.
     */
    SessionDTO sessionDTO;
    private final ItemSearchLogic itemLogic = new ItemSearchLogic();

    public static final String STRING_COMPARE = "Start Date should be less than End Date";
    /**
     * The space.
     */
    private final Label space = new Label("&nbsp;", ContentMode.HTML);
    private final TextField batchId = new TextField();
    private final TextField newFormulation = new TextField();

    private final TextField labelerCode23 = new TextField();
    private final TextField identifier = new TextField();
    /**
     * The item master dto.
     */
    private ItemMasterDTO itemMasterDTO;
    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder;
    /**
     * The identifier results bean.
     */
    private BeanItemContainer<ItemIrtIdentifierDTO> identifierResultsBean;
    /**
     * The pricing results bean.
     */
    private BeanItemContainer<ItemPricingDTO> pricingResultsBean;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(AddForm.class);
    private HelperDTO dto = new HelperDTO(ConstantsUtils.SELECT_ONE);
    /**
     * The tabSheet.
     */
    @UiField("tabSheet")
    TabSheet tabSheet;
    int selectedTabIndex;
    /**
     * The Item Information Screen.
     */

    ItemInformation itemInformation;
    AdditionalInformation additionalInformation;
    NotesTabForm imAdditionalInfoForm;
    IdentifierResults identifierResults;
    PricingResults pricingResult;
    private com.stpl.ifs.util.HelperDTO defaultdto = new com.stpl.ifs.util.HelperDTO(0, ConstantsUtils.SELECT_ONE);

    /**
     * The identifier list.
     */
    private List<ItemIrtIdentifierDTO> identifierList = new ArrayList<>();
    /**
     * The pricing list.
     */
    private List<ItemPricingDTO> pricingList = new ArrayList<>();

    /**
     * The Removed Item Price List list.
     */
    private List removedItemPriceList = new ArrayList();

    @UiField("saveBtn")
    private Button saveBtn;

    @UiField("backBtn")
    private Button backBtn;

    @UiField("resetBtn")
    private Button resetBtn;

    @UiField("deleteBtn")
    private Button deleteBtn;

    @UiField("infoLayout")
    private VerticalLayout layout;

    private final String mode;

    private final boolean isAddMode;
    private final boolean isEditMode;
    private final boolean isViewMode;
    Label itemSystemId = new Label();
    CommonUtil commonutil = CommonUtil.getInstance();

    /**
     * The Constructor.
     *
     * @param itemMasterDTO
     * @param binder
     * @param identifierResultsBean
     * @param pricingResultsBean
     * @param mode
     * @param sessionDTO
     * @throws PortalException
     * @throws SystemException
     */
    public AddForm(
            final ItemMasterDTO itemMasterDTO,
            final ErrorfulFieldGroup binder,
            final BeanItemContainer<ItemIrtIdentifierDTO> identifierResultsBean,
            final BeanItemContainer<ItemPricingDTO> pricingResultsBean, String mode,
            final SessionDTO sessionDTO)
            throws PortalException, SystemException {
        super();
        this.identifierResultsBean = identifierResultsBean;
        this.pricingResultsBean = pricingResultsBean;
        this.itemMasterDTO = itemMasterDTO;
        this.binder = binder;
        this.mode = mode;
        this.sessionDTO = sessionDTO;
        this.isAddMode = ConstantsUtils.ADD.equals(mode);
        this.isEditMode = ConstantsUtils.EDIT.equals(mode);
        this.isViewMode = ConstantsUtils.VIEW.equals(mode);
        init();

    }

    /**
     * For Initialization.
     *
     * @throws PortalException
     * @throws SystemException
     * @throws Exception
     */
    public void init() throws PortalException, SystemException {
        LOGGER.debug("Enters init method in add form");
        space.setHeight(ConstantsUtils.HEIGHT);
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/itemmaster/tabsheetform.xml"), this));
        layout.addComponent(new InformationLayout("item_Master", itemMasterDTO.getItemId(), itemMasterDTO.getItemNo(), itemMasterDTO.getItemName(), itemMasterDTO.getItemDesc()));
        binder = getBinder();
        configureFields();
        addTabSheet();

        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.ITEM_MASTER + ConstantsUtils.COMMA + "Item Information");
        backButton();
        if (functionCfpHM.get(FunctionNameUtil.ITEM_SAVE) != null && ((AppPermission) functionCfpHM.get(FunctionNameUtil.ITEM_SAVE)).isFunctionFlag()) {
            addButton();
        } else {
            saveBtn.setVisible(false);
        }
        if (functionCfpHM.get(FunctionNameUtil.ITEM_RESET) != null && ((AppPermission) functionCfpHM.get(FunctionNameUtil.ITEM_RESET)).isFunctionFlag()) {
            resetButton();
        } else {
            resetBtn.setVisible(false);
        }
        if (functionCfpHM.get(FunctionNameUtil.ITEM_DELETE) != null && ((AppPermission) functionCfpHM.get(FunctionNameUtil.ITEM_DELETE)).isFunctionFlag()) {
            deleteButton();
        } else {
            deleteBtn.setVisible(false);
        }
        if (isViewMode) {
            saveBtn.setVisible(false);
            resetBtn.setVisible(false);
            deleteBtn.setVisible(false);

        } else if (isEditMode) {
            saveBtn.setEnabled(true);
            saveBtn.setCaption("UPDATE");
            resetBtn.setEnabled(true);
            deleteBtn.setEnabled(true);

        } else {
            deleteBtn.setVisible(false);
            saveBtn.setEnabled(true);
            resetBtn.setEnabled(true);

        }

        LOGGER.debug("Ending init");
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private ErrorfulFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<>(itemMasterDTO));
        binder.setBuffered(true);
        binder.setErrorDisplay(errorMsg);
        errorMsg.setId("ErrorMessage");
        return binder;
    }

    /**
     * Adds the tab sheet.
     *
     */
    public void addTabSheet() throws PortalException, SystemException {
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> tabItemHM = stplSecurity
                .getBusinessTabPermission(userId, UISecurityUtil.ITEM_MASTER);

        itemInformation = new ItemInformation(binder, mode, itemMasterDTO.getUdc1() != null ? itemMasterDTO.getUdc1().getId() : 0);
        tabSheet.addTab(itemInformation, TabNameUtil.ITEM_INFORMATION, null);

        additionalInformation = new AdditionalInformation(binder, mode, sessionDTO);
        tabSheet.addTab(additionalInformation, TabNameUtil.ADDITIONAL_INFO, null);
        if (tabItemHM.get(TabNameUtil.ADDITIONAL_INFO) != null
                && !((AppPermission) tabItemHM.get(TabNameUtil.ADDITIONAL_INFO))
                .isTabFlag()) {
            tabSheet.getTab(additionalInformation).setVisible(false);
        }

        identifierResultsBean.addAll(itemMasterDTO.getItemIdentifierList());
        identifierResults = new IdentifierResults(binder, identifierResultsBean, mode);
        tabSheet.addTab(identifierResults, TabNameUtil.IDENTIFIER, null);

        pricingResultsBean.addAll(itemMasterDTO.getPricingIdentifierList());

        pricingResult = new PricingResults(binder, pricingResultsBean, removedItemPriceList, mode, sessionDTO);
        tabSheet.addTab(pricingResult, TabNameUtil.PRICING, null);

        imAdditionalInfoForm = new NotesTabForm(binder, "Item Master", "ITEM_MASTER", ConstantsUtils.SYSTEM_ID, mode);
        tabSheet.addTab(imAdditionalInfoForm, TabNameUtil.NOTES, null);
        imAdditionalInfoForm.focusNewNote();
        if (!isAddMode) {
            imAdditionalInfoForm.refreshTable();

        }
        tabSheet.setErrorHandler(new ErrorHandler() {
            /**
             * Method used to handling error for tab sheet
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });

        tabSheet.addSelectedTabChangeListener(new SelectedTabChangeListener() {
            /**
             * Listener for Tab Change Event
             */
            public void selectedTabChange(final SelectedTabChangeEvent event) {
                Component component = event.getTabSheet().getSelectedTab();
                selectedTabIndex = event.getTabSheet().getTabPosition(event.getTabSheet().getTab(component));
                try {
                    if (selectedTabIndex == 0) {
                        binder.getErrorDisplay().clearError();
                    } else if (selectedTabIndex == 1) {
                        binder.getErrorDisplay().clearError();
                        labelerCode23.focus();

                    } else if (selectedTabIndex == NumericConstants.TWO) {
                        binder.getErrorDisplay().clearError();
                        identifierResults.setDefaultFocus();
                    } else if (selectedTabIndex == NumericConstants.THREE) {
                        binder.getErrorDisplay().clearError();
                        pricingResult.setDefaultFocus();
                    } else {
                        binder.getErrorDisplay().clearError();
                        imAdditionalInfoForm.focusNewNote();
                        imAdditionalInfoForm.callJavaScriptForButton();
                        imAdditionalInfoForm.focusUploaderField();
                        if (isViewMode) {
                            imAdditionalInfoForm.removeAndDisablingComponents();
                        }
                    }
                } catch (Exception exception) {
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
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
     * Configure fields.
     */
    public void configureFields() {
        LOGGER.debug("Enters configureFields()");
        labelerCode23.setImmediate(true);
        labelerCode23.setValidationVisible(true);
        labelerCode23
                .addValidator(new RegexpValidator(ValidationUtils.SPECIAL_CHAR,
                        ValidationUtils.SPECIAL_CHAR_MSG));
        labelerCode23.addValidator(new StringLengthValidator(
                "Labeler code should be less than 25 characters", 0, NumericConstants.TWENTY_FIVE, true));
        labelerCode23.setDescription(labelerCode23.getValue());
        labelerCode23.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * After changing the value in labelerCode, function will be
             * executed.
             */
            public void valueChange(final ValueChangeEvent event) {
                labelerCode23.setDescription(labelerCode23.getValue());
            }
        });
        identifier.addValidator(new RegexpValidator(
                "([0-9|a-z|A-Z|\\_|\\$|\\.|\\*|\\s])*",
                "value can contain only digits,alphabets or _ or . or $"));
        identifier.setValidationVisible(true);
        identifier.addValidator(new BeanValidator(SearchDTO.class,
                ConstantsUtils.ITEM_IDENTIFIER));
        identifier.addValidator(new IdentifierValidator());
        identifier.setImmediate(true);
        identifier.setDescription(identifier.getValue());
        identifier.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * After changing the value in identifier, function will be
             * executed.
             *
             * @param event
             */
            public void valueChange(final ValueChangeEvent event) {
                identifier.setDescription(identifier.getValue());
            }
        });

        batchId.setImmediate(true);
        batchId.addValidator(new RegexpValidator(ValidationUtils.SPECIAL_CHAR,
                ValidationUtils.SPECIAL_CHAR_MSG));
        batchId.addValidator(new StringLengthValidator(
                " Batch ID Should be 25 characters", 0, NumericConstants.TWENTY_FIVE, true));
        batchId.setDescription(batchId.getValue());
        batchId.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * After changing the value in batchId, function will be executed.
             *
             * @param event
             */
            public void valueChange(final ValueChangeEvent event) {
                batchId.setDescription(batchId.getValue());
            }
        });

        newFormulation.setImmediate(true);
        newFormulation
                .addValidator(new RegexpValidator(ValidationUtils.SPECIAL_CHAR,
                        ValidationUtils.SPECIAL_CHAR_MSG));
        newFormulation.addValidator(new StringLengthValidator(
                "New Formulation should be less than 50 characters", 0, NumericConstants.THIRTY_EIGHT,
                true));

        tabSheet.addSelectedTabChangeListener(new SelectedTabChangeListener() {
            /**
             * selectedTabChange method for tabe change event
             */
            public void selectedTabChange(final SelectedTabChangeEvent event) {
                try {

                    if (selectedTabIndex == 1) {
                        imAdditionalInfoForm.focusUploaderField();
                        imAdditionalInfoForm.callJavaScriptForButton();
                    }
                } catch (Exception exception) {
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
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
     * The Class IdentifierValidator.
     */
    private class IdentifierValidator extends AbstractValidator {

        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * The Constructor.
         */
        public IdentifierValidator() {
            super(StringUtils.EMPTY);
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
         * Validating th values.
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         * @see
         * com.vaadin.data.validator.AbstractValidator#validate(java.lang.Object)
         */
        @Override
        public void validate(final Object value) {

            markAsDirty();
            if (!isValidValue(value)) {
                throw new InvalidValueException(
                        "Both identifier and qualifier should be entered");
            }
        }

        /**
         * Checking the value is valid or not.
         *
         * @param value the value
         * @return true, if is valid value
         * @see
         * com.vaadin.data.validator.AbstractValidator#isValidValue(java.lang.Object)
         */
        @Override
        protected boolean isValidValue(final Object value) {

            return true;
        }

        /**
         * (non-Javadoc).
         *
         * @return the type
         * @see com.vaadin.data.validator.AbstractValidator#getType()
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    /**
     * (non-Javadoc).
     *
     * @param event the event
     * @see
     * com.stpl.app.ui.StplCustomComponent#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    @Override
    public void enter(final ViewChangeEvent event) {
        /**
         * Empty enter method.
         */
    }

    /**
     * Back button.
     */
    private void backButton() {
        backBtn.setEnabled(true);
        backBtn.setWidth(ConstantsUtils.BTN_WIDTH);
        backBtn.setEnabled(true);
        backBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a {@link Button} has been clicked. A reference to the
             * button is given by {@link ClickEvent#getButton()}.
             *
             * @param event An event containing information about the click.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {
                try {

                    if (isViewMode) {
                        LOGGER.debug("Entering back button method from Add");
                        AbstractSearchView.flag = false;
                        getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                        LOGGER.debug("Ending back button method fron Add");
                    } else {
                        MessageBox.showPlain(Icon.QUESTION, commonutil.getHeaderMessage(), commonutil.getBackMessage(), new MessageBoxListener() {
                            /**
                             * Called when a Button has been clicked .
                             *
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(ConstantsUtils.YES)) {
                                    LOGGER.debug("Entering back button method from Add");
                                    AbstractSearchView.flag = false;
                                    getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                                    LOGGER.debug("Ending back button method fron Add");
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
                    }
                } catch (Exception exception) {
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1009), new MessageBoxListener() {
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
        backBtn.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             *
             * @param event the fired event.
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                /**
                 * Error event handling
                 */
            }
        });
    }

    /**
     * reset button.
     */
    private void resetButton() {
        try {
            resetBtn.setWidth(ConstantsUtils.BTN_WIDTH);
            resetBtn.addClickListener(new Button.ClickListener() {
                @SuppressWarnings("PMD")
                /**
                 * Constant SerialID
                 */
                private static final long serialVersionUID = 1L;

                /**
                 * Logic for back button.
                 *
                 * @param event
                 */
                @SuppressWarnings("PMD")
                public void buttonClick(final Button.ClickEvent event) {
                    MessageBox.showPlain(Icon.QUESTION, commonutil.getHeaderMessage(), commonutil.getResetMessage(), new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         *
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {

                            if (buttonId.name().equals(ConstantsUtils.YES)) {
                                LOGGER.debug("Entering inside resetButton method");
                                if (isAddMode) {
                                    resetlogicforadd();
                                } else {
                                    resetlogicforedit();
                                }
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);

                }
            });

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Delete button.
     */
    private void deleteButton() {
        deleteBtn.setWidth(ConstantsUtils.BTN_WIDTH);
        deleteBtn.setErrorHandler(new ErrorHandler() {
            /**
             * Error Event.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                /**
                 * Error Event Handling
                 */
            }
        });
        deleteBtn.addClickListener(new Button.ClickListener() {

            /**
             * After clicking delete button, function will be executed.
             */
            public void buttonClick(final Button.ClickEvent event) {

                binder.getFields();
                List itemContractList = itemLogic.getItemContractList(Integer.valueOf(itemMasterDTO.getItemSystemId()));
                List itemifpList = itemLogic.getItemIfpList(Integer.valueOf(itemMasterDTO.getItemSystemId()));

                if (itemContractList != null && !itemContractList.isEmpty() && itemContractList.size() > 0) {
                    final MessageBox msg = MessageBox.showPlain(Icon.WARN, ValidationUtils.CANNOT_DELETE, "Item cannot be deleted as it is associated with Contract", new MessageBoxListener() {

                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            // Do Nothing
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();

                } else if (itemifpList != null && !itemifpList.isEmpty() && itemifpList.size() > 0) {
                    final MessageBox msg = MessageBox.showPlain(Icon.WARN, ValidationUtils.CANNOT_DELETE, "Item cannot be deleted as it is associated with Ifp", new MessageBoxListener() {

                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            // Do Nothing
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();

                } else {

                    confirmationForDelete();
                }
            }
        });
    }

    private void confirmationForDelete() {
        MessageBox.showPlain(Icon.QUESTION, commonutil.getHeaderMessage(), commonutil.getDeleteMessage(binder.getField(ConstantsUtils.ITEM_NAME).getValue().toString()), new MessageBoxListener() {
            /**
             * After clicking delete button, function will be
             * executed.
             */
            @SuppressWarnings("PMD")
            public void buttonClicked(final ButtonId buttonId) {
                try {
                    LOGGER.debug("Entering delete operation from Edit");
                    deleteSuccessful(buttonId);
                    LOGGER.debug("Ending delete operation from Edit");
                } catch (SystemException ex) {
                    final String errorMsgCode = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(errorMsgCode);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsgCode, new MessageBoxListener() {
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
                } catch (PortalException portException) {
                    LOGGER.error(portException);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1012), new MessageBoxListener() {
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
                } catch (Exception exception) {
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1012), new MessageBoxListener() {
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

        }, ButtonId.YES, ButtonId.NO);
    }
    
    private void deleteSuccessful(final ButtonId buttonId) throws PortalException, SystemException, CommitException {
        if (buttonId.name().equals(ConstantsUtils.YES)) {
            binder.commit();
//
            final int identifierById = Integer.valueOf(itemMasterDTO.getItemSystemId());

            final ItemMaster master = itemLogic.deleteItemMasterById(identifierById);

            final Notification notif = new Notification(commonutil.getDeletedSuccessfulMessage(master.getItemId(), master.getItemName()), Notification.Type.HUMANIZED_MESSAGE);
            notif.setPosition(Position.MIDDLE_CENTER);
            notif.setStyleName(ConstantsUtils.MY_STYLE);
            notif.show(Page.getCurrent());
            AbstractSearchView.flag = true;
            getUI().getNavigator().navigateTo(AbstractSearchView.NAME);

        }
    }
    /**
     * Adds the button.
     */
    private void addButton() {
        if (isAddMode) {
            saveBtn.addClickListener(new Button.ClickListener() {
                /**
                 * Called when a {@link Button} has been clicked. A reference to
                 * the button is given by {@link ClickEvent#getButton()}.
                 *
                 * @param event An event containing information about the click.
                 */
                @SuppressWarnings("PMD")
                public void buttonClick(final Button.ClickEvent event) {
                    LOGGER.debug("Entering Item Save operation");
                    try {
                        pricingResult.clearFilters();
                        binder.getErrorDisplay().clearError();
                        binder.commit();

                        identifierList = new ArrayList<>();
                        pricingList = new ArrayList<>();
                        boolean flag = false;
                        boolean flag1 = false;
                        StringBuilder errorMessage = new StringBuilder(ConstantsUtils.INFORMATION_MANDATORY_FIELDS + ConstantsUtils.BREAK);
                        if (StringUtils.isBlank(binder.getField(ConstantsUtils.ITEMID).getValue().toString())) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEMS_ID);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.ITEM_NO).getValue().toString().isEmpty()) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEMS_NO);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.ITEM_NAME).getValue().toString().isEmpty()) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEM_NAMES);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.ITEM_STATUS).getValue() == null || ((com.stpl.ifs.util.HelperDTO) binder.getField(ConstantsUtils.ITEM_STATUS).getValue()).getId() == 0) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEMSTATUS);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.ITEM_START_DATE).getValue() == null) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEM_STARTDATE);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.ITEM_CODE).getValue().toString().isEmpty()) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEM_CODE1);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.STRENGTH).getValue() == null || ((com.stpl.ifs.util.HelperDTO) binder.getField(ConstantsUtils.STRENGTH).getValue()).getId() == 0) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.STRENGH);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.FORM).getValue() == null || ((com.stpl.ifs.util.HelperDTO) binder.getField(ConstantsUtils.FORM).getValue()).getId() == 0) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.FORM1);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.NDC8).getValue().toString().isEmpty()) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.NDC_8);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.NDC9).getValue().toString().isEmpty()) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.NDC_9);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.BRANDDDLB).getValue() == null || ConstantsUtils.SELECT_ONE.equals(binder.getField(ConstantsUtils.BRANDDDLB).getValue().toString())) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.BRAND1);
                            flag = true;
                        }

                        if ("0".equals(binder.getField(ConstantsUtils.ORGANIZATION_KEY).getValue().toString()) || ConstantsUtils.SELECT_ONE.equals(binder.getField(ConstantsUtils.ORGANIZATION_KEY).getValue().toString())) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ORGANIZATION_KEY_STRING);
                            flag = true;
                        }
                        if (flag) {
                            errorMessage.append(ConstantsUtils.BREAK);
                        }
                        if (identifierResultsBean != null && identifierResultsBean.size() == 0) {
                            errorMessage.append(ConstantsUtils.ONE_IDENTIFIER_IN_IDENTIFIER_TAB);
                            flag = true;
                            flag1 = true;
                        }
                        if (flag1) {
                            errorMessage.append(ConstantsUtils.BREAK);
                        }
                        if (pricingResultsBean != null && pricingResultsBean.size() == 0) {
                            errorMessage.append(ConstantsUtils.PRICING_IDENTIFIER_MANDATORY);
                            flag = true;
                        }
                        if (flag) {
                            binder.getErrorDisplay().setError(errorMessage.toString());
                            return;
                        }

                        for (int i = 0; i < identifierResultsBean.size(); i++) {
                            final ItemIrtIdentifierDTO iden = (ItemIrtIdentifierDTO) identifierResultsBean.getIdByIndex(i);

                            if (StringUtils.isEmpty(iden.getItemIrtQualifierName())) {
                                binder.getErrorDisplay().setError("Item Qualifier Name is Mandatory in Identifier Tab");
                                return;
                            }

                            if (iden.getStartDate() == null) {
                                binder.getErrorDisplay().setError(ValidationUtils.START_DATE_VALID);
                                return;
                            }

                            if (StringUtils.isEmpty(iden.getItemIdentifier())) {
                                binder.getErrorDisplay().setError("Item Identifier is Mandatory in Identifier Tab");
                                return;
                            }
                            if (iden.getIdentifierStatus().getId() == 0) {
                                binder.getErrorDisplay().setError("Identifier status is Mandatory in Identifier Tab");
                                return;
                            }

                            if (iden.getEndDate() != null) {
                                if (iden.getStartDate() == null) {
                                    binder.getErrorDisplay().setError(ValidationUtils.SD_VALID);
                                    return;
                                } else {
                                    final Date date1 = iden.getStartDate();
                                    final Date date2 = iden.getEndDate();
                                    final int value = date1.compareTo(date2);
                                    if (value == CommonUtils.ZERO || value > CommonUtils.ZERO) {
                                        binder.getErrorDisplay().setError(STRING_COMPARE);
                                        return;
                                    } 

                                }

                            }

                            final ItemQualifier qualif = itemLogic.getItemIrtQualifierByName(iden.getItemIrtQualifierName());
                            final DynamicQuery itemIdentifierDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemIdentifier.class);
                            itemIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_IDENTIFIER_VALUE, iden.getItemIdentifier()));
                            itemIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.START_DATE, iden.getStartDate()));
                            itemIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_QUALIFIER_SID, qualif.getItemQualifierSid()));
                            itemIdentifierDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
                            final List<ItemIdentifier> itemIdentiiferList = itemLogic.getItemIrtIdentifierList(itemIdentifierDynamicQuery);

                            if (itemIdentiiferList.size() > CommonUtils.ZERO) {
                                binder.getErrorDisplay().setError(ConstantsUtils.ITEM_IDENTIFIER_ALREADY_EXITS);
                                return;
                            }

                            for (int k = 0; k < identifierList.size(); k++) {
                                if (identifierList.get(k).getItemIdentifier().equals(iden.getItemIdentifier()) && identifierList.get(k).getStartDate().equals(iden.getStartDate())) {

                                    final ItemQualifier qual = itemLogic.getItemIrtQualifierByName(iden.getItemIrtQualifierName());

                                    final ItemQualifier qualBean = itemLogic.getItemIrtQualifierByName(identifierList.get(k).getItemIrtQualifierName());

                                    if (qual.getItemQualifierSid() == qualBean.getItemQualifierSid()) {
                                        binder.getErrorDisplay().setError(ConstantsUtils.ITEM_IDENTIFIER_ALREADY_ADDED);
                                        return;
                                    }

                                }
                            }
                            identifierList.add(iden);

                        }
                        for (int i = 0; i < pricingResultsBean.size(); i++) {
                            final ItemPricingDTO iden = (ItemPricingDTO) pricingResultsBean.getIdByIndex(i);
                            if (StringUtils.isEmpty(iden.getIdentifierCodeQualifierName())) {
                                binder.getErrorDisplay().setError("Pricing Qualifier Name is Mandatory");
                                return;
                            }

                            if (iden.getStartDate() == null) {
                                binder.getErrorDisplay().setError(ValidationUtils.SD_VALID);
                                return;
                            }

                            if (iden.getItemUom().getId() == 0) {
                                binder.getErrorDisplay().setError("Item UOM is Mandatory");
                                return;
                            }
                            final String test = iden.getItemPrice().toString();
                            if (StringUtils.isEmpty(test)) {
                                binder.getErrorDisplay().setError("Item Price is Mandatory");
                                return;
                            }
                            if (iden.getPricingCodeStatus().getId() == 0) {
                                binder.getErrorDisplay().setError("Pricing status is Mandatory");
                                return;
                            }

                            if (iden.getEndDate() != null) {
                                if (iden.getStartDate() == null) {
                                    binder.getErrorDisplay().setError(ValidationUtils.SD_VALID);
                                    return;
                                } else {
                                    final Date date1 = iden.getStartDate();
                                    final Date date2 = iden.getEndDate();
                                    final int value = date1.compareTo(date2);
                                    if (value == CommonUtils.ZERO || value > CommonUtils.ZERO) {
                                        binder.getErrorDisplay().setError(STRING_COMPARE);
                                        return;
                                    } 

                                }

                            }

                            for (int k = 0; k < pricingList.size(); k++) {
                                if (pricingList.get(k).getPricingCodeStatus().getId() == (iden.getPricingCodeStatus().getId()) && pricingList.get(k).getStartDate().equals(iden.getStartDate())) {

                                    final ItemPricingQualifier qual = itemLogic.getItemPricingQualifierByCodeQualifierName(iden.getIdentifierCodeQualifierName());
                                    final ItemPricingQualifier qualBean = itemLogic.getItemPricingQualifierByCodeQualifierName(pricingList.get(k).getIdentifierCodeQualifierName());

                                    if (qual.getItemPricingQualifierSid() == qualBean.getItemPricingQualifierSid() && pricingList.get(k).getItemUom().getId() == (iden.getItemUom().getId())) {
                                        binder.getErrorDisplay().setError(ConstantsUtils.ITEM_IDENTIFIER_ALREADY_ADDED);
                                        return;

                                    }

                                }
                            }
                            pricingList.add(iden);

                        }

                        MessageBox.showPlain(Icon.QUESTION, commonutil.getHeaderMessage(), commonutil.getSaveMessage(binder.getField(ConstantsUtils.ITEM_NAME).getValue().toString()), new MessageBoxListener() {
                            /**
                             * Called when a Button has been clicked .
                             *
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(ConstantsUtils.YES)) {
                                    String msg = addButtonListenerYesMethod();
                                    if (msg.equals(ConstantsUtils.SUCCESS)) {
                                        CommonUIUtils.successNotification(commonutil.getSavedSuccessfulMessage(binder.getField(ConstantsUtils.ITEM_ID).getValue().toString(), binder.getField(ConstantsUtils.ITEM_NAME).getValue().toString()));

                                        sessionDTO.setMode("Edit");
                                        getUI().getNavigator().navigateTo(ItemAddView.NAME);

                                    } else if (msg.equals(ConstantsUtils.DUPLICATE)) {
                                        binder.getErrorDisplay().setError("Item ID already exists.");
                                    } else if (ConstantsUtils.DUPLICATE_ITEM_NO.equals(msg)) {
                                        binder.getErrorDisplay().setError("Item No already exists.");
                                    } else if ("duplicate_ndc".equals(msg)) {
                                        binder.getErrorDisplay().setError("NDC8 already exists.");
                                    }
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);

                    } catch (FieldGroup.CommitException commitException) {

                        LOGGER.error(commitException);
                        boolean flag = false;
                        boolean flag1 = false;
                        StringBuilder errorMessage = new StringBuilder(ConstantsUtils.INFORMATION_MANDATORY_FIELDS + ConstantsUtils.BREAK);
                        if (commitException.getCause().getMessage().equals("Date format not recognized")) {
                            return;
                        }
                        if (StringUtils.isBlank(binder.getField(ConstantsUtils.ITEMID).getValue().toString())) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEMS_ID);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.ITEM_NO).getValue().toString().isEmpty()) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEMS_NO);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.ITEM_NAME).getValue().toString().isEmpty()) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEM_NAMES);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.ITEM_STATUS).getValue() == null || ((com.stpl.ifs.util.HelperDTO) binder.getField(ConstantsUtils.ITEM_STATUS).getValue()).getId() == 0) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEMSTATUS);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.ITEM_START_DATE).getValue() == null) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEM_STARTDATE);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.ITEM_CODE).getValue().toString().isEmpty()) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEM_CODE1);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.STRENGTH).getValue() == null || ((com.stpl.ifs.util.HelperDTO) binder.getField(ConstantsUtils.STRENGTH).getValue()).getId() == 0) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.STRENGH);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.FORM).getValue() == null || ((com.stpl.ifs.util.HelperDTO) binder.getField(ConstantsUtils.FORM).getValue()).getId() == 0) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.FORM1);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.NDC8).getValue().toString().isEmpty()) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.NDC_8);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.NDC9).getValue().toString().isEmpty()) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.NDC_9);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.BRANDDDLB).getValue() == null || ConstantsUtils.SELECT_ONE.equals(binder.getField(ConstantsUtils.BRANDDDLB).getValue().toString())) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.BRAND1);
                            flag = true;
                        }

                        if ("0".equals(binder.getField(ConstantsUtils.ORGANIZATION_KEY).getValue().toString()) || ConstantsUtils.SELECT_ONE.equals(binder.getField(ConstantsUtils.ORGANIZATION_KEY).getValue().toString())) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ORGANIZATION_KEY_STRING);
                            flag = true;
                        }
                        if (flag) {
                            errorMessage.append(ConstantsUtils.BREAK);
                        }
                        if (identifierResultsBean != null && identifierResultsBean.size() == 0) {
                            errorMessage.append(ConstantsUtils.ONE_IDENTIFIER_IN_IDENTIFIER_TAB);
                            flag = true;
                            flag1 = true;
                        }
                        if (flag1) {
                            errorMessage.append(ConstantsUtils.BREAK);
                        }
                        if (pricingResultsBean != null && pricingResultsBean.size() == 0) {
                            errorMessage.append(ConstantsUtils.PRICING_IDENTIFIER_MANDATORY);
                            flag = true;
                        }
                        if (flag) {
                            binder.getErrorDisplay().setError(errorMessage.toString());
                            return;
                        }
                    } catch (SystemException ex) {
                        final String errorMsgErrorCode = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsgErrorCode);
                        final MessageBox msgBox = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsgErrorCode, new MessageBoxListener() {
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
                        msgBox.getButton(ButtonId.OK).focus();
                    } catch (PortalException portException) {
                        LOGGER.error(portException);
                        final MessageBox msgBox = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1007), new MessageBoxListener() {
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
                        msgBox.getButton(ButtonId.OK).focus();
                    } catch (Exception exception) {
                        final MessageBox msgBox = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1007), new MessageBoxListener() {
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
                        msgBox.getButton(ButtonId.OK).focus();
                        LOGGER.error(exception);

                    }
                    LOGGER.debug("Ending Item Save operation");
                }
            });
        } else if (isEditMode) {
            LOGGER.debug("Enters Editmode in addform");
            saveBtn.setWidth(ConstantsUtils.BTN_WIDTH);
            saveBtn.setErrorHandler(new ErrorHandler() {

                /**
                 * Error Event.
                 */
                @SuppressWarnings("PMD")
                public void error(final com.vaadin.server.ErrorEvent event) {
                    /**
                     * Error Event Handling
                     */
                }
            });
            saveBtn.addClickListener(new ClickListener() {
                private String STRING_STARTDATE;

                /**
                 * After clicking update button, function will be executed.
                 */
                public void buttonClick(final ClickEvent event) {
                    LOGGER.debug("Entering update operation from Edit");
                    try {
                        pricingResult.clearFilters();
                        binder.getErrorDisplay().clearError();
                        binder.getFields();
                        binder.commit();

                        identifierList = new ArrayList<>();
                        pricingList = new ArrayList<>();
                        boolean flag = false;
                        boolean flag1 = false;
                        StringBuilder errorMessage = new StringBuilder(ConstantsUtils.INFORMATION_MANDATORY_FIELDS + ConstantsUtils.BREAK);
                        LOGGER.debug("checking binder fields");

                        if (binder.getField(ConstantsUtils.ITEMID).getValue().toString().isEmpty()) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEMS_ID);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.ITEM_NO).getValue().toString().isEmpty()) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEMS_NO);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.ITEM_NAME).getValue().toString().isEmpty()) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEM_NAMES);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.ITEM_STATUS).getValue() == null || ((com.stpl.ifs.util.HelperDTO) binder.getField(ConstantsUtils.ITEM_STATUS).getValue()).getId() == 0) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEMSTATUS);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.ITEM_START_DATE).getValue() == null) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEM_STARTDATE);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.ITEM_CODE).getValue().toString().isEmpty()) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEM_CODE1);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.STRENGTH).getValue() == null || ((com.stpl.ifs.util.HelperDTO) binder.getField(ConstantsUtils.STRENGTH).getValue()).getId() == 0) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.STRENGH);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.FORM).getValue() == null || ((com.stpl.ifs.util.HelperDTO) binder.getField(ConstantsUtils.FORM).getValue()).getId() == 0) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.FORM1);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.NDC8).getValue().toString().isEmpty()) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.NDC_8);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.NDC9).getValue().toString().isEmpty()) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.NDC_9);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.BRANDDDLB).getValue() == null || ConstantsUtils.SELECT_ONE.equals(binder.getField(ConstantsUtils.BRANDDDLB).getValue().toString())) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.BRAND1);
                            flag = true;
                        }
                        if ("0".equals(binder.getField(ConstantsUtils.ORGANIZATION_KEY).getValue().toString()) || ConstantsUtils.SELECT_ONE.equals(binder.getField(ConstantsUtils.ORGANIZATION_KEY).getValue().toString())) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ORGANIZATION_KEY_STRING);
                            flag = true;
                        }
                        if (flag) {
                            errorMessage.append(ConstantsUtils.BREAK);
                        }
                        if (identifierResultsBean != null && identifierResultsBean.size() == 0) {
                            errorMessage.append(ConstantsUtils.ONE_IDENTIFIER_IN_IDENTIFIER_TAB);
                            flag = true;
                            flag1 = true;
                        }
                        if (flag1) {
                            errorMessage.append(ConstantsUtils.BREAK);
                        }
                        if (pricingResultsBean != null && pricingResultsBean.size() == 0) {
                            errorMessage.append(ConstantsUtils.PRICING_IDENTIFIER_MANDATORY);
                            flag = true;
                        }
                        if (flag) {
                            binder.getErrorDisplay().setError(errorMessage.toString());
                            return;
                        }

                        for (int i = 0; i < identifierResultsBean.size(); i++) {
                            final ItemIrtIdentifierDTO iden = (ItemIrtIdentifierDTO) identifierResultsBean.getIdByIndex(i);

                            if (StringUtils.isEmpty(iden.getItemIrtQualifierName())) {
                                binder.getErrorDisplay().setError("Item Qualifier Name is Mandatory");
                                return;
                            }

                            if (iden.getStartDate() == null) {
                                binder.getErrorDisplay().setError(STRING_STARTDATE);
                                return;
                            }

                            if (StringUtils.isEmpty(iden.getItemIdentifier())) {
                                binder.getErrorDisplay().setError("Item Identifier is Mandatory");
                                return;
                            }
                            if (iden.getIdentifierStatus().getId() == 0) {
                                binder.getErrorDisplay().setError("Identifier status is Mandatory");
                                return;
                            }

                            if (iden.getEndDate() != null) {
                                if (iden.getStartDate() == null) {
                                    binder.getErrorDisplay().setError(STRING_STARTDATE);
                                    return;
                                } else {
                                    final Date date1 = iden.getStartDate();
                                    final Date date2 = iden.getEndDate();
                                    final int compare = date1.compareTo(date2);
                                    if (compare == CommonUtils.ZERO || compare > CommonUtils.ZERO) {
                                        binder.getErrorDisplay().setError(STRING_COMPARE);
                                        return;
                                    } 

                                }

                            }

                            final ItemQualifier qualif = itemLogic.getItemIrtQualifierByName(iden.getItemIrtQualifierName());
                            final DynamicQuery itemIdentifierDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemIdentifier.class);
                            itemIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_IDENTIFIER_VALUE, iden.getItemIdentifier()));
                            itemIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.START_DATE, iden.getStartDate()));
                            itemIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_QUALIFIER_SID, qualif.getItemQualifierSid()));
                            itemIdentifierDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
                            final List<ItemIdentifier> itemIdentiiferList = itemLogic.getItemIrtIdentifierList(itemIdentifierDynamicQuery);

                            final String systemId = binder.getField(ConstantsUtils.SYSTEM_ID) == null || binder.getField(ConstantsUtils.SYSTEM_ID).getValue() == null
                                    || binder.getField(ConstantsUtils.SYSTEM_ID).getValue().equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(binder.getField(ConstantsUtils.SYSTEM_ID)
                                    .getValue().toString());

                            if (systemId == StringUtils.EMPTY) {
                                if (itemIdentiiferList.size() > CommonUtils.ZERO) {
                                    binder.getErrorDisplay().setError(ConstantsUtils.ITEM_IDENTIFIER_ALREADY_EXITS);
                                    return;
                                }

                            } else {
                                final int intItemSystemId = Integer.parseInt(systemId);
                                itemIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_MASTER_SID, intItemSystemId));
                                final List<ItemIdentifier> tempList = itemLogic.getItemIrtIdentifierList(itemIdentifierDynamicQuery);

                                if ((itemIdentiiferList.size() - tempList.size()) > CommonUtils.ZERO) {
                                    binder.getErrorDisplay().setError(ConstantsUtils.ITEM_IDENTIFIER_ALREADY_EXITS);
                                    return;
                                }

                            }

                            for (int k = 0; k < identifierList.size(); k++) {
                                if (identifierList.get(k).getItemIdentifier().equals(iden.getItemIdentifier()) && identifierList.get(k).getStartDate().equals(iden.getStartDate())) {
                                    final ItemQualifier qual = itemLogic.getItemIrtQualifierByName(iden.getItemIrtQualifierName());

                                    final ItemQualifier qualBean = itemLogic.getItemIrtQualifierByName(identifierList.get(k).getItemIrtQualifierName());
                                    if (qual.getItemQualifierSid() == qualBean.getItemQualifierSid()) {
                                        binder.getErrorDisplay().setError(ConstantsUtils.ITEM_IDENTIFIER_ALREADY_ADDED);
                                        return;
                                    }

                                }
                            }
                            identifierList.add(iden);

                        }
                        for (int i = 0; i < pricingResultsBean.size(); i++) {

                            final ItemPricingDTO iden = (ItemPricingDTO) pricingResultsBean.getIdByIndex(i);
                            if (StringUtils.isEmpty(iden.getIdentifierCodeQualifierName())) {
                                binder.getErrorDisplay().setError("Pricing Qualifier Name is Mandatory");
                                return;
                            }

                            if (iden.getStartDate() == null) {
                                binder.getErrorDisplay().setError(STRING_STARTDATE);
                                return;
                            }

                            if (iden.getItemUom().getId() == 0) {
                                binder.getErrorDisplay().setError("Item UOM is Mandatory");
                                return;
                            }

                            String testEdit = iden.getItemPrice().toString();
                            testEdit(testEdit);
                            if (iden.getPricingCodeStatus().getId() == 0) {
                                binder.getErrorDisplay().setError("Pricing status is Mandatory");
                                return;
                            }

                            if (iden.getPricingEndDate() != null) {
                                if (iden.getPricingStartDate() == null) {
                                    binder.getErrorDisplay().setError(STRING_STARTDATE);
                                    return;
                                } else {
                                    final Date date1 = iden.getPricingStartDate();
                                    final Date date2 = iden.getPricingEndDate();
                                    final int compare = date1.compareTo(date2);
                                    if (compare == CommonUtils.ZERO || compare > CommonUtils.ZERO) {
                                        binder.getErrorDisplay().setError(STRING_COMPARE);
                                        return;
                                    }

                                }

                            }
                            for (int h = 0; h < pricingList.size(); h++) {
                                if (pricingList.get(h).getItemUom().getId() == iden.getItemUom().getId() && pricingList.get(h).getStartDate().equals(iden.getStartDate())) {
                                    final ItemPricingQualifier qual = itemLogic.getItemPricingQualifierByCodeQualifierName(iden.getIdentifierCodeQualifierName());

                                    final ItemPricingQualifier qualBean = itemLogic.getItemPricingQualifierByCodeQualifierName(pricingList.get(h).getIdentifierCodeQualifierName());
                                    if (qual.getItemPricingQualifierSid() == qualBean.getItemPricingQualifierSid() && pricingList.get(h).getPricingCodeStatus().getId() == iden.getPricingCodeStatus().getId()) {
                                        binder.getErrorDisplay().setError("Item Pricing is already added for this Item");
                                        return;
                                    }
                                }
                            }
                            pricingList.add(iden);

                        }

                        MessageBox.showPlain(Icon.QUESTION, commonutil.getHeaderMessage(), commonutil.getSaveMessage(binder.getField(ConstantsUtils.ITEM_NAME).getValue().toString()), new MessageBoxListener() {
                            /**
                             * Called when a Button has been clicked .
                             *
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                String msg = StringUtils.EMPTY;
                                if (buttonId.name().equals(ConstantsUtils.YES)) {
                                    msg = saveItemMasterListener(msg);

                                    if (msg.equals(ConstantsUtils.SUCCESS)) {
                                        CommonUIUtils.successNotification(commonutil.getSavedSuccessfulMessage(binder.getField(ConstantsUtils.ITEM_ID).getValue().toString(), binder.getField(ConstantsUtils.ITEM_NAME).getValue().toString()));
                                        getUI().getNavigator().navigateTo(ItemAddView.NAME);

                                    } else if (msg.equals(ConstantsUtils.DUPLICATE)) {
                                        binder.getErrorDisplay().setError("Item ID already exists.");
                                    } else if (ConstantsUtils.DUPLICATE_ITEM_NO.equals(msg)) {
                                        binder.getErrorDisplay().setError("Item No already exists.");
                                    } else if ("duplicate_ndc".equals(msg)) {
                                        binder.getErrorDisplay().setError("NDC8 already exists.");
                                    }
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);

                    } catch (CommitException commitException) {
                        LOGGER.error(commitException);
                        boolean flag = false;
                        boolean flag1 = false;
                        StringBuilder errorMessage = new StringBuilder(ConstantsUtils.INFORMATION_MANDATORY_FIELDS + ConstantsUtils.BREAK);
                        if (commitException.getCause().getMessage().equals("Date format not recognized")) {
                            return;
                        }
                        if (binder.getField(ConstantsUtils.ITEMID).getValue().toString().isEmpty()) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEMS_ID);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.ITEM_NO).getValue().toString().isEmpty()) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEMS_NO);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.ITEM_NAME).getValue().toString().isEmpty()) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEM_NAMES);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.ITEM_STATUS).getValue() == null || ((com.stpl.ifs.util.HelperDTO) binder.getField(ConstantsUtils.ITEM_STATUS).getValue()).getId() == 0) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEMSTATUS);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.ITEM_START_DATE).getValue() == null) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEM_STARTDATE);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.ITEM_CODE).getValue().toString().isEmpty()) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ITEM_CODE1);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.STRENGTH).getValue() == null || ((com.stpl.ifs.util.HelperDTO) binder.getField(ConstantsUtils.STRENGTH).getValue()).getId() == 0) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.STRENGH);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.FORM).getValue() == null || ((com.stpl.ifs.util.HelperDTO) binder.getField(ConstantsUtils.FORM).getValue()).getId() == 0) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.FORM1);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.NDC8).getValue().toString().isEmpty()) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.NDC_8);
                            flag = true;
                        }
                        if (binder.getField(ConstantsUtils.NDC9).getValue().toString().isEmpty()) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.NDC_9);
                            flag = true;
                        }
                        if ("0".equals(binder.getField(ConstantsUtils.ORGANIZATION_KEY).getValue().toString()) || ConstantsUtils.SELECT_ONE.equals(binder.getField(ConstantsUtils.ORGANIZATION_KEY).getValue().toString())) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append(ConstantsUtils.ORGANIZATION_KEY_STRING);
                            flag = true;
                        }
                        if (binder.getField("displayBrand").getValue() == null) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.COMMA);
                            }
                            errorMessage.append("Display Brand");
                            flag = true;
                        }
                        if (flag) {
                            errorMessage.append(ConstantsUtils.BREAK);
                        }
                        if (identifierResultsBean != null && identifierResultsBean.size() == 0) {
                            errorMessage.append(ConstantsUtils.ONE_IDENTIFIER_IN_IDENTIFIER_TAB);
                            flag = true;
                            flag1 = true;
                        }
                        if (flag1) {
                            errorMessage.append(ConstantsUtils.BREAK);
                        }
                        if (pricingResultsBean != null && pricingResultsBean.size() == 0) {
                            errorMessage.append(ConstantsUtils.PRICING_IDENTIFIER_MANDATORY);
                            flag = true;
                        }
                        if (flag) {
                            binder.getErrorDisplay().setError(errorMessage.toString());
                            return;
                        }
                    } catch (SystemException ex) {
                        final String errorMsgSystemExp = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsgSystemExp);
                        final MessageBox msgBox = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsgSystemExp, new MessageBoxListener() {
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
                        msgBox.getButton(ButtonId.OK).focus();
                    } catch (PortalException portException) {
                        LOGGER.error(portException);
                        final MessageBox msgBox = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1008), new MessageBoxListener() {
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
                        msgBox.getButton(ButtonId.OK).focus();
                    } catch (Exception exception) {
                        final MessageBox msgBox = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1008), new MessageBoxListener() {
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
                        msgBox.getButton(ButtonId.OK).focus();
                        LOGGER.error(exception);

                    }
                    LOGGER.debug("Ending update operation from Edit");
                }
            });
        }
    }

    public void resetlogicforedit() {
        try {
            LOGGER.debug("Entering inside resetlogic for edit");
            final int systemId = sessionDTO.getSystemId();
            itemMasterDTO = itemLogic.getItemMasterById(Integer.valueOf(systemId));
            if (selectedTabIndex == 0) {
                TextField id = (TextField) binder.getField(FieldNameUtils.ITEMID);
                id.setValue(itemMasterDTO.getItemId());
                TextField no = (TextField) binder.getField(FieldNameUtils.ITEMNO);
                no.setValue(itemMasterDTO.getItemNo());
                TextField name = (TextField) binder.getField(FieldNameUtils.ITEMNAME);
                name.setValue(itemMasterDTO.getItemName());
                ComboBox status = (ComboBox) binder.getField(FieldNameUtils.ITEMSTATUS);
                status.setValue(itemMasterDTO.getItemStatus());
                PopupDateField startDate = (PopupDateField) binder.getField(FieldNameUtils.ITEMSTARTDATE);
                startDate.setValue(itemMasterDTO.getItemStartDate());
                PopupDateField endDate = (PopupDateField) binder.getField(FieldNameUtils.ITEMENDDATE);
                endDate.setValue(itemMasterDTO.getItemEndDate());
                ComboBox type = (ComboBox) binder.getField(FieldNameUtils.ITEMTYPE);
                type.setValue(itemMasterDTO.getItemType());
                TextField desc = (TextField) binder.getField(FieldNameUtils.ITEMDESC);
                desc.setValue(itemMasterDTO.getItemDesc());
                TextField code = (TextField) binder.getField(FieldNameUtils.ITEMCODE);
                code.setValue(itemMasterDTO.getItemCode());
                ComboBox itemClass = (ComboBox) binder.getField(FieldNameUtils.ITEMCLASS);
                itemClass.setValue(itemMasterDTO.getItemClass());
                ComboBox primaryUom = (ComboBox) binder.getField(FieldNameUtils.PRIMARYUOM);
                primaryUom.setValue(itemMasterDTO.getPrimaryUom());
                ComboBox secondaryUom = (ComboBox) binder.getField(FieldNameUtils.SECONDARYUOM);
                secondaryUom.setValue(itemMasterDTO.getSecondaryUom());
                ComboBox strength = (ComboBox) binder.getField(FieldNameUtils.STRENGTH);
                strength.setValue(itemMasterDTO.getStrength());
                ComboBox form = (ComboBox) binder.getField(FieldNameUtils.FORM);
                form.setValue(itemMasterDTO.getForm());
                ComboBox packageSize = (ComboBox) binder.getField(FieldNameUtils.PACKAGESIZE);
                packageSize.setValue(itemMasterDTO.getPackageSize());
                TextField ndc8 = (TextField) binder.getField(FieldNameUtils.NDC8);
                ndc8.setValue(itemMasterDTO.getNdc8());
                TextField ndc9 = (TextField) binder.getField(FieldNameUtils.NDC9);
                ndc9.setValue(itemMasterDTO.getNdc9());
                ComboBox therapeutic = (ComboBox) binder.getField(FieldNameUtils.THERAPEUTICCLASS);
                therapeutic.setValue(itemMasterDTO.getTherapeuticClass());
                PopupDateField saleDate = (PopupDateField) binder.getField(FieldNameUtils.FIRSTSALEDATE);
                saleDate.setValue(itemMasterDTO.getFirstSaleDate());
                ComboBox udc1 = (ComboBox) binder.getField(FieldNameUtils.UDC1);
                udc1.setValue(itemMasterDTO.getUdc1());
                ComboBox udc2 = (ComboBox) binder.getField(FieldNameUtils.UDC2);
                if (CommonUtils.GALDERMA.equals(System.getProperty(CommonUtils.CLIENT_NAME))) {
                    CustomMenuBar.CustomMenuItem customMenuItem = itemInformation.getCustomMenuItem();
                    com.stpl.ifs.util.HelperDTO helper = HelperListUtil.getInstance().getIdHelperDTOMap().get(itemMasterDTO.getUdc1().getId());
                    String descp = helper.getDescription();
                    String[] description = descp.split(",");
                    for (String des : description) {
                        int sid = HelperListUtil.getInstance().getIdByDesc(UIUtils.ARM_UDC1, des.trim());
                        CommonUtils.unCheckMenuBarItem(customMenuItem);
                        CommonUtils.checkMenuBarItem(customMenuItem, sid);
                    }
                }
                udc2.setValue(itemMasterDTO.getUdc2());
                ComboBox udc3 = (ComboBox) binder.getField(FieldNameUtils.UDC3);
                udc3.setValue(itemMasterDTO.getUdc3());
                ComboBox udc4 = (ComboBox) binder.getField(FieldNameUtils.UDC4);
                udc4.setValue(itemMasterDTO.getUdc4());
                ComboBox udc5 = (ComboBox) binder.getField(FieldNameUtils.UDC5);
                udc5.setValue(itemMasterDTO.getUdc5());
                ComboBox udc6 = (ComboBox) binder.getField(FieldNameUtils.UDC6);
                udc6.setValue(itemMasterDTO.getUdc6());
                ComboBox manufacturer = (ComboBox) binder.getField("manufacturerIdDDLB");
                manufacturer.setValue(itemMasterDTO.getManufacturerIdDDLB());
                TextField createdBy = (TextField) binder.getField(FieldNameUtils.CREATEDBY);
                createdBy.setValue(itemMasterDTO.getCreatedBy());
                TextField modifiedBy = (TextField) binder.getField(FieldNameUtils.MODIFIEDBY);
                modifiedBy.setValue(itemMasterDTO.getModifiedBy());
                PopupDateField createdDate = (PopupDateField) binder.getField(FieldNameUtils.CREATEDDATE);
                createdDate.setValue(itemMasterDTO.getCreatedDate());
                PopupDateField modifiedDate = (PopupDateField) binder.getField(FieldNameUtils.MODIFIEDDATE);
                modifiedDate.setValue(itemMasterDTO.getModifiedDate());
                TextField labelerCode = (TextField) binder.getField(FieldNameUtils.LABELERCODE);
                labelerCode.setValue(itemMasterDTO.getLabelerCode());
                ComboBox itemTypeIndication = (ComboBox) binder.getField(FieldNameUtils.ITEMTYPEINDICATION);
                itemTypeIndication.setValue(itemMasterDTO.getItemTypeIndication());
                TextField upps = (TextField) binder.getField(FieldNameUtils.UPPS);
                upps.setValue(itemMasterDTO.getUpps());
                PopupDateField date = (PopupDateField) binder.getField(FieldNameUtils.PACKAGESIZEINTRODATE);
                date.setValue(itemMasterDTO.getPackageSizeIntroDate());
                TextField packageSizeCode = (TextField) binder.getField(ConstantsUtils.PACKAGE_SIZE_CODE);
                packageSizeCode.setValue(itemMasterDTO.getPackageSizeCode());
                ComboBox itemCategory = (ComboBox) binder.getField(FieldNameUtils.ITEMCATEGORY);
                itemCategory.setValue(itemMasterDTO.getItemCategory());
                ComboBox brand = (ComboBox) binder.getField(ConstantsUtils.BRANDDDLB);
                brand.setValue(itemMasterDTO.getBrandDdlb());
                TextField brandId = (TextField) binder.getField(FieldNameUtils.BRANDID);
                brandId.setReadOnly(false);
                brandId.setValue(itemMasterDTO.getBrandId());
                brandId.setReadOnly(true);
                TextField displayBrand = (TextField) binder.getField(FieldNameUtils.DISPLAYBRAND);
                displayBrand.setReadOnly(false);
                displayBrand.setValue(itemMasterDTO.getDisplayBrand());
                displayBrand.setReadOnly(true);
                ComboBox orgKey = (ComboBox) binder.getField(ConstantsUtils.ORGANIZATION_KEY);
                orgKey.setValue(itemMasterDTO.getOrganizationKey());

            }
            if (selectedTabIndex == 1) {
                OptionGroup clotting = (OptionGroup) binder.getField(FieldNameUtils.CLOTTINGFACTORINDICATOR);
                clotting.setValue(itemMasterDTO.getClottingFactorIndicator());
                PopupDateField clottingStartDate = (PopupDateField) binder.getField(FieldNameUtils.CLOTTINGFACTORSTARTDATE);
                clottingStartDate.setValue(itemMasterDTO.getClottingFactorStartDate());
                PopupDateField clottingEndDate = (PopupDateField) binder.getField(FieldNameUtils.CLOTTINGFACTORENDDATE);
                clottingEndDate.setValue(itemMasterDTO.getClottingFactorEndDate());
                OptionGroup pediatric = (OptionGroup) binder.getField(FieldNameUtils.PEDIATRICEXCLUSIVEINDICATOR);
                pediatric.setValue(itemMasterDTO.getPediatricExclusiveIndicator());
                PopupDateField pediatricStartDate = (PopupDateField) binder.getField(FieldNameUtils.PEDIATRICEXCLUSIVESTARTDATE);
                pediatricStartDate.setValue(itemMasterDTO.getPediatricExclusiveStartDate());
                PopupDateField pediatricEndDate = (PopupDateField) binder.getField(FieldNameUtils.PEDIATRICEXCLUSIVEENDDATE);
                pediatricEndDate.setValue(itemMasterDTO.getPediatricExclusiveEndDate());
                TextField packageCode = (TextField) binder.getField(ConstantsUtils.PACKAGE_SIZE_CODE);
                packageCode.setValue(itemMasterDTO.getPackageSizeCode());
                PopupDateField acquisitionDate = (PopupDateField) binder.getField(FieldNameUtils.ACQUISITIONDATE);
                acquisitionDate.setValue(itemMasterDTO.getAcquisitionDate());
                OptionGroup generic = (OptionGroup) binder.getField(FieldNameUtils.AUTHORIZEDGENERIC);
                generic.setValue(itemMasterDTO.getAuthorizedGeneric());
                PopupDateField genericStartDate = (PopupDateField) binder.getField(FieldNameUtils.AUTHORIZEDGENERICSTARTDATE);
                genericStartDate.setValue(itemMasterDTO.getAuthorizedGenericStartDate());
                PopupDateField genericEndDate = (PopupDateField) binder.getField(FieldNameUtils.AUTHORIZEDGENERICENDDATE);
                genericEndDate.setValue(itemMasterDTO.getAuthorizedGenericEndDate());
                PopupDateField terminationDate = (PopupDateField) binder.getField(FieldNameUtils.MARKETTERMINATIONDATE);
                terminationDate.setValue(itemMasterDTO.getMarketTerminationDate());
                OptionGroup indicator = (OptionGroup) binder.getField(FieldNameUtils.NEWFORMULATIONINDICATOR);
                indicator.setValue(itemMasterDTO.getNewFormulationIndicator());
                PopupDateField formulationStartDate = (PopupDateField) binder.getField(FieldNameUtils.NEWFORMULATIONSTARTDATE);
                formulationStartDate.setValue(itemMasterDTO.getNewFormulationStartDate());
                PopupDateField formulationEndDate = (PopupDateField) binder.getField(FieldNameUtils.NEWFORMULATIONENDDATE);
                formulationEndDate.setValue(itemMasterDTO.getNewFormulationEndDate());
                TextField newFormulationFromBinder = (TextField) binder.getField(FieldNameUtils.NEWFORMULATION);
                newFormulationFromBinder.setValue(itemMasterDTO.getNewFormulation());
                TextField shelfLife = (TextField) binder.getField(FieldNameUtils.SHELFLIFE);
                shelfLife.setValue(itemMasterDTO.getShelfLife());
                ComboBox state = (ComboBox) binder.getField(FieldNameUtils.SHELFLIFETYPE);
                state.setValue(itemMasterDTO.getShelfLifeType());
                OptionGroup dualPrice = (OptionGroup) binder.getField(FieldNameUtils.DUALPRICINGINDICATOR);
                dualPrice.setValue(itemMasterDTO.getDualPricingIndicator());
                TextField acquiredAmp = (TextField) binder.getField(FieldNameUtils.ACQUIREDAMP);
                acquiredAmp.setValue(itemMasterDTO.getAcquiredAmp());
                TextField obraAmp = (TextField) binder.getField(FieldNameUtils.OBRABAMP);
                obraAmp.setValue(itemMasterDTO.getObraBamp());
                TextField acquiredBamp = (TextField) binder.getField("acquiredBamp");
                acquiredBamp.setValue(itemMasterDTO.getAcquiredBamp());
                TextField dra = (TextField) binder.getField(FieldNameUtils.DRA);
                dra.setValue(itemMasterDTO.getDra());
                TextField doses = (TextField) binder.getField(FieldNameUtils.DOSESPERUNIT);
                doses.setValue(itemMasterDTO.getDosesPerUnit());
                TextField baseLine = (TextField) binder.getField(FieldNameUtils.BASELINEAMP);
                baseLine.setValue(itemMasterDTO.getBaselineAmp());
                TextField baseCpi = (TextField) binder.getField(FieldNameUtils.BASECPI);
                baseCpi.setValue(itemMasterDTO.getBaseCpi());
                PopupDateField discontinuation = (PopupDateField) binder.getField(FieldNameUtils.DISCONTINUATIONDATE);
                discontinuation.setValue(itemMasterDTO.getDiscontinuationDate());
                PopupDateField expiration = (PopupDateField) binder.getField(FieldNameUtils.LASTLOTEXPIRATIONDATE);
                expiration.setValue(itemMasterDTO.getLastLotExpirationDate());
                PopupDateField divestiture = (PopupDateField) binder.getField(FieldNameUtils.DIVESTITUREDATE);
                divestiture.setValue(itemMasterDTO.getDivestitureDate());
                PopupDateField nonFederal = (PopupDateField) binder.getField(FieldNameUtils.NONFEDERALEXPIRATIONDATE);
                nonFederal.setValue(itemMasterDTO.getNonFederalExpirationDate());
                PopupDateField baseCpiPeriod = (PopupDateField) binder.getField(FieldNameUtils.BASECPIPERIOD);
                baseCpiPeriod.setValue(itemMasterDTO.getBaseCpiPeriod());
            }
            if (selectedTabIndex == NumericConstants.TWO) {
                identifierResults.resetBtnLogic();
                identifierResultsBean.removeAllItems();
                identifierResultsBean.addAll(itemMasterDTO.getItemIdentifierList());
            }
            if (selectedTabIndex == NumericConstants.THREE) {
                pricingResult.resetBtnLogic();
                pricingResultsBean.removeAllItems();
                pricingResultsBean.addAll(itemMasterDTO.getPricingIdentifierList());
            }
            if (selectedTabIndex == NumericConstants.FOUR) {
                imAdditionalInfoForm.resetBtnLogic(itemMasterDTO.getInternalNotes());
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("Ending resetlogic for edit");
    }

    public void resetlogicforadd() {

        try {

            LOGGER.debug("Entering inside resetlogic for add ");

            if (selectedTabIndex == 0) {
                TextField id = (TextField) binder.getField(FieldNameUtils.ITEMID);
                id.setValue(StringUtils.EMPTY);
                TextField no = (TextField) binder.getField(FieldNameUtils.ITEMNO);
                no.setValue(StringUtils.EMPTY);
                TextField name = (TextField) binder.getField(FieldNameUtils.ITEMNAME);
                name.setValue(StringUtils.EMPTY);
                ComboBox status = (ComboBox) binder.getField(FieldNameUtils.ITEMSTATUS);
                status.setValue(null);
                PopupDateField startDate = (PopupDateField) binder.getField(FieldNameUtils.ITEMSTARTDATE);
                startDate.setValue(null);
                PopupDateField endDate = (PopupDateField) binder.getField(FieldNameUtils.ITEMENDDATE);
                endDate.setValue(null);
                ComboBox type = (ComboBox) binder.getField(FieldNameUtils.ITEMTYPE);
                type.setValue(null);
                TextField desc = (TextField) binder.getField(FieldNameUtils.ITEMDESC);
                desc.setValue(StringUtils.EMPTY);
                TextField code = (TextField) binder.getField(FieldNameUtils.ITEMCODE);
                code.setValue(StringUtils.EMPTY);
                ComboBox itemClass = (ComboBox) binder.getField(FieldNameUtils.ITEMCLASS);
                itemClass.setValue(null);
                ComboBox primaryUom = (ComboBox) binder.getField(FieldNameUtils.PRIMARYUOM);
                primaryUom.setValue(null);
                ComboBox secondaryUom = (ComboBox) binder.getField(FieldNameUtils.SECONDARYUOM);
                secondaryUom.setValue(null);
                ComboBox strength = (ComboBox) binder.getField(FieldNameUtils.STRENGTH);
                strength.setValue(null);
                ComboBox form = (ComboBox) binder.getField(FieldNameUtils.FORM);
                form.setValue(null);

                ComboBox packageSize = (ComboBox) binder.getField(FieldNameUtils.PACKAGESIZE);
                packageSize.setValue(null);
                TextField ndc8 = (TextField) binder.getField(FieldNameUtils.NDC8);
                ndc8.setValue(StringUtils.EMPTY);
                TextField ndc9 = (TextField) binder.getField(FieldNameUtils.NDC9);
                ndc9.setValue(StringUtils.EMPTY);
                ComboBox brand = (ComboBox) binder.getField(ConstantsUtils.BRANDDDLB);
                brand.setValue(dto);
                ComboBox therapeutic = (ComboBox) binder.getField(FieldNameUtils.THERAPEUTICCLASS);
                therapeutic.setValue(null);
                PopupDateField saleDate = (PopupDateField) binder.getField(FieldNameUtils.FIRSTSALEDATE);
                saleDate.setValue(null);
                ComboBox udc1 = (ComboBox) binder.getField(FieldNameUtils.UDC1);
                udc1.setValue(null);
                ComboBox udc2 = (ComboBox) binder.getField(FieldNameUtils.UDC2);
                if (CommonUtils.GALDERMA.equals(System.getProperty(CommonUtils.CLIENT_NAME))) {
                    CustomMenuBar.CustomMenuItem customMenuItem = itemInformation.getCustomMenuItem();
                    CommonUtils.unCheckMenuBarItem(customMenuItem);
                }
                udc2.setValue(null);
                ComboBox udc3 = (ComboBox) binder.getField(FieldNameUtils.UDC3);
                udc3.setValue(null);
                ComboBox udc4 = (ComboBox) binder.getField(FieldNameUtils.UDC4);
                udc4.setValue(null);
                ComboBox udc5 = (ComboBox) binder.getField(FieldNameUtils.UDC5);
                udc5.setValue(null);
                ComboBox udc6 = (ComboBox) binder.getField(FieldNameUtils.UDC6);
                udc6.setValue(null);
                ComboBox manufacturer = (ComboBox) binder.getField("manufacturerIdDDLB");
                manufacturer.setValue(dto);
                TextField labelerCode = (TextField) binder.getField(FieldNameUtils.LABELERCODE);
                labelerCode.setValue(StringUtils.EMPTY);
                TextField packageSizeCode = (TextField) binder.getField(ConstantsUtils.PACKAGE_SIZE_CODE);
                packageSizeCode.setValue(StringUtils.EMPTY);
                TextField upps = (TextField) binder.getField("upps");
                upps.setValue(StringUtils.EMPTY);
                PopupDateField date = (PopupDateField) binder.getField(FieldNameUtils.PACKAGESIZEINTRODATE);
                date.setValue(null);
                ComboBox itemmanufacturer = (ComboBox) binder.getField(FieldNameUtils.ITEMTYPEINDICATION);
                itemmanufacturer.setValue(defaultdto);
                ComboBox itemCategory = (ComboBox) binder.getField("itemCategory");
                itemCategory.setValue(defaultdto);
                ComboBox organizationKey = (ComboBox) binder.getField(ConstantsUtils.ORGANIZATION_KEY);
                organizationKey.select(0);
            }
            if (selectedTabIndex == 1) {

                PopupDateField date = (PopupDateField) binder.getField(FieldNameUtils.PACKAGESIZEINTRODATE);
                date.setValue(null);
                TextField upps = (TextField) binder.getField(FieldNameUtils.UPPS);
                upps.setValue(StringUtils.EMPTY);
                OptionGroup clotting = (OptionGroup) binder.getField(FieldNameUtils.CLOTTINGFACTORINDICATOR);
                clotting.setValue(ConstantsUtils.NO_VARIABLE);
                PopupDateField clottingStartDate = (PopupDateField) binder.getField(FieldNameUtils.CLOTTINGFACTORSTARTDATE);
                clottingStartDate.setValue(null);
                PopupDateField clottingEndDate = (PopupDateField) binder.getField(FieldNameUtils.CLOTTINGFACTORENDDATE);
                clottingEndDate.setValue(null);
                OptionGroup pediatric = (OptionGroup) binder.getField(FieldNameUtils.PEDIATRICEXCLUSIVEINDICATOR);
                pediatric.setValue(ConstantsUtils.NO_VARIABLE);
                PopupDateField pediatricStartDate = (PopupDateField) binder.getField(FieldNameUtils.PEDIATRICEXCLUSIVESTARTDATE);
                pediatricStartDate.setValue(null);
                PopupDateField pediatricEndDate = (PopupDateField) binder.getField(FieldNameUtils.PEDIATRICEXCLUSIVEENDDATE);
                pediatricEndDate.setValue(null);
                TextField packageCode = (TextField) binder.getField(ConstantsUtils.PACKAGE_SIZE_CODE);
                packageCode.setValue(StringUtils.EMPTY);
                PopupDateField acquisitionDate = (PopupDateField) binder.getField(FieldNameUtils.ACQUISITIONDATE);
                acquisitionDate.setValue(null);
                OptionGroup generic = (OptionGroup) binder.getField(FieldNameUtils.AUTHORIZEDGENERIC);
                generic.setValue(ConstantsUtils.NO_VARIABLE);
                PopupDateField genericStartDate = (PopupDateField) binder.getField(FieldNameUtils.AUTHORIZEDGENERICSTARTDATE);
                genericStartDate.setValue(null);
                PopupDateField genericEndDate = (PopupDateField) binder.getField(FieldNameUtils.AUTHORIZEDGENERICENDDATE);
                genericEndDate.setValue(null);
                ComboBox manufacturer = (ComboBox) binder.getField(FieldNameUtils.ITEMTYPEINDICATION);
                manufacturer.setValue(0);
                PopupDateField terminationDate = (PopupDateField) binder.getField(FieldNameUtils.MARKETTERMINATIONDATE);
                terminationDate.setValue(null);
                OptionGroup indicator = (OptionGroup) binder.getField(FieldNameUtils.NEWFORMULATIONINDICATOR);
                indicator.setValue(ConstantsUtils.NO_VARIABLE);
                PopupDateField formulationStartDate = (PopupDateField) binder.getField(FieldNameUtils.NEWFORMULATIONSTARTDATE);
                formulationStartDate.setValue(null);
                PopupDateField formulationEndDate = (PopupDateField) binder.getField(FieldNameUtils.NEWFORMULATIONENDDATE);
                formulationEndDate.setValue(null);
                TextField shelfLife = (TextField) binder.getField(FieldNameUtils.SHELFLIFE);
                shelfLife.setValue(StringUtils.EMPTY);
                ComboBox state = (ComboBox) binder.getField(FieldNameUtils.SHELFLIFETYPE);
                state.setValue(new com.stpl.ifs.util.HelperDTO(0, ConstantsUtils.SELECT_ONE));
                OptionGroup dualPrice = (OptionGroup) binder.getField(FieldNameUtils.DUALPRICINGINDICATOR);
                dualPrice.setValue(ConstantsUtils.NO_VARIABLE);
                TextField acquiredAmp = (TextField) binder.getField(FieldNameUtils.ACQUIREDAMP);
                acquiredAmp.setValue(StringUtils.EMPTY);
                TextField obraAmp = (TextField) binder.getField(FieldNameUtils.OBRABAMP);
                obraAmp.setValue(StringUtils.EMPTY);
                TextField acquiredBamp = (TextField) binder.getField("acquiredBamp");
                acquiredBamp.setValue(StringUtils.EMPTY);
                TextField dra = (TextField) binder.getField(FieldNameUtils.DRA);
                dra.setValue(StringUtils.EMPTY);
                TextField doses = (TextField) binder.getField(FieldNameUtils.DOSESPERUNIT);
                doses.setValue(StringUtils.EMPTY);
                TextField baseLine = (TextField) binder.getField(FieldNameUtils.BASELINEAMP);
                baseLine.setValue(StringUtils.EMPTY);
                TextField baseCpi = (TextField) binder.getField(FieldNameUtils.BASECPI);
                baseCpi.setValue(StringUtils.EMPTY);
                PopupDateField discontinuation = (PopupDateField) binder.getField(FieldNameUtils.DISCONTINUATIONDATE);
                discontinuation.setValue(null);
                PopupDateField expiration = (PopupDateField) binder.getField(FieldNameUtils.LASTLOTEXPIRATIONDATE);
                expiration.setValue(null);
                PopupDateField divestiture = (PopupDateField) binder.getField(FieldNameUtils.DIVESTITUREDATE);
                divestiture.setValue(null);
                PopupDateField nonFederal = (PopupDateField) binder.getField(FieldNameUtils.NONFEDERALEXPIRATIONDATE);
                nonFederal.setValue(null);
                PopupDateField baseCpiPeriod = (PopupDateField) binder.getField(FieldNameUtils.BASECPIPERIOD);
                baseCpiPeriod.setValue(null);
            }
            if (selectedTabIndex == NumericConstants.TWO) {
                identifierResults.resetBtnLogic();
                identifierResultsBean.removeAllItems();
            }
            if (selectedTabIndex == NumericConstants.THREE) {
                pricingResult.resetBtnLogic();
                pricingResultsBean.removeAllItems();
            }
            if (selectedTabIndex == NumericConstants.FOUR) {
                imAdditionalInfoForm.resetBtnLogic(StringUtils.EMPTY);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("Ending resetlogic for add");
    }

    public String addButtonListenerYesMethod() {
        String msg = StringUtils.EMPTY;
        try {
            List<List> udcList;
            if (CommonUtils.GALDERMA.equals(System.getProperty(CommonUtils.CLIENT_NAME))) {
                udcList = CommonUtil.getSelectedVariables(itemInformation.getCustomMenuItem());
                msg = itemLogic.saveItemMaster(binder, identifierList, pricingList, imAdditionalInfoForm.getUploadedData(),
                        imAdditionalInfoForm.getAddedNotes(), imAdditionalInfoForm.removeDetailsList(), sessionDTO, removedItemPriceList, udcList);
            } else {
                udcList = Collections.EMPTY_LIST;
                msg = itemLogic.saveItemMaster(binder, identifierList, pricingList, imAdditionalInfoForm.getUploadedData(),
                        imAdditionalInfoForm.getAddedNotes(), imAdditionalInfoForm.removeDetailsList(), sessionDTO, removedItemPriceList, udcList);
            }
            LOGGER.debug(msg);
            itemLogic.insertToCPDetails(sessionDTO.getSystemId());
                        return msg;

        } catch (Exception exception) {
            final MessageBox msgBox = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1007), new MessageBoxListener() {
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
            msgBox.getButton(ButtonId.OK).focus();
            LOGGER.error(exception);
        }
                return msg;

    }

    public void testEdit(String testEdit) {
        try {
            testEdit = testEdit.replace(ConstantsUtils.DOLLAR, StringUtils.EMPTY);
            testEdit = testEdit.replace(ConstantsUtils.COMMA, StringUtils.EMPTY);
            if (StringUtils.isEmpty(testEdit)) {
                binder.getErrorDisplay().setError("item Price is required");
                return;
            } else if (Double.isNaN(Double.valueOf(testEdit))) {
                binder.getErrorDisplay().setError("item Price should be numeric");
                return;
            }
        } catch (Exception ex) {
            LOGGER.error("Exception in updateButton - buttonClick - itemPrice -" + ex);
            binder.getErrorDisplay().setError("item Price should be numeric");

        }
    }
    public String saveItemMasterListener(String msg) {

        try {
            List<List> udcList = new ArrayList<>();
            if (CommonUtils.GALDERMA.equals(System.getProperty(CommonUtils.CLIENT_NAME))) {
                udcList = CommonUtil.getSelectedVariables(itemInformation.getCustomMenuItem());
            }
            msg = itemLogic.saveItemMaster(binder, identifierList, pricingList, imAdditionalInfoForm.getUploadedData(), 
                    imAdditionalInfoForm.getAddedNotes(), imAdditionalInfoForm.removeDetailsList(), 
                    sessionDTO, removedItemPriceList, udcList);
            return msg;
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(AddForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(AddForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;

    }

}
