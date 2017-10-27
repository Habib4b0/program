package com.stpl.app.global.item.ui.form;

import java.util.Map;

import org.jboss.logging.Logger;

import com.stpl.app.global.item.dto.ItemIrtIdentifierDTO;
import com.stpl.app.global.item.logic.ItemSearchLogic;
import com.stpl.app.global.item.util.UIUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.StplWindow;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.FunctionNameUtil;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

// TODO: Auto-generated Javadoc
/**
 * The Class IdenQualifierEditList.
 */
public final class IdenQualifierEditList extends StplWindow {

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(IdenQualifierEditList.class);

    /**
     * The itemlogic.
     */
    private final ItemSearchLogic itemlogic = new ItemSearchLogic();
    private static final String RECORD_LOCK_STATUS = "recordLockStatus";
    private static final String IDENTIFIER_CODE_QUALIFIER = "identifierCodeQualifier";
    /**
     * The qualifier table.
     */
    @UiField("qualifierTable")
    private ExtFilterTable qualifierTable;
    /**
     * The item qualifier.
     */
    private final BeanItemContainer<ItemIrtIdentifierDTO> itemQualifier = new BeanItemContainer<>(
            ItemIrtIdentifierDTO.class);
    /**
     * The identifier dto.
     */
    private final ItemIrtIdentifierDTO identifierDTO = new ItemIrtIdentifierDTO();
    /**
     * The item identifier.
     */
    @UiField("identifierCodeQualifier")
    private TextField identifierCodeQualifier;
    @UiField("effectiveDates")
    private ComboBox effectiveDates;
    @UiField("identifierCodeQualifierName")
    private TextField identifierCodeQualifierName;
    @UiField("entityCode")
    private ComboBox entityCode;
    @UiField("notes")
    private TextArea notes;

    /**
     * The btn save.
     */
    @UiField("QualifierSave")
    private Button btnSave;
    @UiField("QualifierDelete")
    private Button btnDelete;
    @UiField("IdentifierRemove")
    private Button btnReset;

    @UiField("btnLayout")
    HorizontalLayout btnLayout;

    /**
     * The binder.
     */
    private final ErrorfulFieldGroup binder = new ErrorfulFieldGroup(
            new BeanItem<>(identifierDTO));
    /**
     * The error msg.
     */
    @UiField("errorMsg")
    private ErrorLabel errorMsg;
    boolean pricingFlag = true;
    @UiField("panel1")
    private Panel panel1;
    @UiField("panel2")
    private Panel panel2;

    private List<String> saveList = new ArrayList<>();
    ItemIrtIdentifierDTO selectedItemiden = new ItemIrtIdentifierDTO();
    private final UIUtils uiUtils = UIUtils.getInstance();

    /**
     * The Constructor.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public IdenQualifierEditList(boolean pricingFlag) throws SystemException, PortalException {
        super("Identifier Setup Pop-up");
        this.pricingFlag = pricingFlag;
        if (pricingFlag) {
            super.setCaption("Pricing Identifier Setup Pop-up");
        }
        setContent(Clara.create(getClass().getResourceAsStream("/declarativeui/itemmaster/qualifiereditlist.xml"), this));
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<>(new ItemIrtIdentifierDTO()));
        binder.setBuffered(true);
        binder.setErrorDisplay(errorMsg);
        init();

    }

    /**
     * For Initialization.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public void init() throws SystemException, PortalException {
        if (pricingFlag) {
            panel1.setCaption("Price Types");
            panel2.setCaption("Price Type Setup");
            itemQualifier.addAll(itemlogic.getPricingQualifierForEditList());
        } else {
            itemQualifier.addAll(itemlogic.getItemQualifierForEditList());
        }

        center();
        setClosable(true);
        setModal(true);
        setWidth("1300px");
        setHeight("715px");
        addToContent();
        configureFields();
    }

    /**
     * Configure fields.
     *
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public void configureFields() {
        addStyleName(ConstantsUtils.BOOTSTRAP);
        addStyleName(ConstantsUtils.BOOTSTRAP_BB);
        effectiveDates.setNullSelectionAllowed(true);
        effectiveDates.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        effectiveDates.addItem(ConstantsUtils.SELECT_ONE);
        effectiveDates.addItem("Yes");
        effectiveDates.addItem("No");
        effectiveDates.select(ConstantsUtils.SELECT_ONE);

        entityCode.setNullSelectionAllowed(true);
        entityCode.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        entityCode.addItem(ConstantsUtils.SELECT_ONE);
        entityCode.addItem("Yes");
        entityCode.addItem("No");
        entityCode.select(ConstantsUtils.SELECT_ONE);
        notes.setImmediate(true);
        notes.setMaxLength(NumericConstants.THOUSAND);
        notes.addValidator(new StringLengthValidator(" New Note Should be less than 1000 characters", 0, NumericConstants.THOUSAND, true));
        notes.setInputPrompt(String.valueOf("<" + new Date() + ">"));

    }

    /**
     * Adds the components to layout.
     *
     * @return the vertical layout
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public void addToContent() throws PortalException, SystemException {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> functionItemHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.ITEM_MASTER);
        addToTable();
        getResponsiveButtons(functionItemHM);
    }

    public void getResponsiveButtons(final Map<String, AppPermission> functionCompanyHM) {

        try {
            if (functionCompanyHM.get(FunctionNameUtil.ADD) != null && ((AppPermission) functionCompanyHM.get(FunctionNameUtil.ADD)).isFunctionFlag()) {
                saveButton();
            } else {
                btnLayout.removeComponent(btnSave);
            }
            if (functionCompanyHM.get(FunctionNameUtil.DELETE) != null && ((AppPermission) functionCompanyHM.get(FunctionNameUtil.DELETE)).isFunctionFlag()) {
                deleteButton();
            } else {
                btnLayout.removeComponent(btnDelete);
            }
            if (functionCompanyHM.get(FunctionNameUtil.RESET) != null && ((AppPermission) functionCompanyHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
                resetButton();
            } else {
                btnLayout.removeComponent(btnReset);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    /**
     * Adds the properties to table.
     *
     * @return the table
     * @throws Exception the exception
     */
    private ExtFilterTable addToTable() {

        qualifierTable.setContainerDataSource(itemQualifier);
        qualifierTable.setVisibleColumns(uiUtils.qualifierItem);
        qualifierTable.setColumnHeaders(uiUtils.qualifierItemHeader);
        qualifierTable.setPageLength(NumericConstants.SIX);
        qualifierTable.setImmediate(true);
        qualifierTable.setSelectable(true);
        qualifierTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        qualifierTable.setFilterBarVisible(true);
        qualifierTable.addStyleName(ConstantsUtils.FILTER_BAR);
        qualifierTable.setFilterDecorator(new ExtDemoFilterDecorator());
        qualifierTable.setValidationVisible(false);
        qualifierTableItemClickListener();
        return qualifierTable;
    }

    public void qualifierTableItemClickListener() {
        qualifierTable.addItemClickListener(new ItemClickListener() {
            
            /**
             * After clicking the item in qualifierTable, function will be
             * executed.
             *
             * @param event
             */
            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                try {

                    binder.commit();
                    event.getItemId();
                    itemClickListener(event);
                } catch (CommitException commitException) {
                    LOGGER.error(commitException);
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

    private void itemClickListener(final ItemClickEvent event) {
        selectedItemiden = itemQualifier.getItem(
                event.getItemId()).getBean();
        binder.setItemDataSource(new BeanItem<>(selectedItemiden));
        if (selectedItemiden != null) {
            if (selectedItemiden.getEffectiveDates() != null && selectedItemiden.getEffectiveDates().isEmpty()) {
                effectiveDates.select(ConstantsUtils.SELECT_ONE);
            }
            if (selectedItemiden.getEntityCode() != null && selectedItemiden.getEntityCode().isEmpty()) {
                entityCode.select(ConstantsUtils.SELECT_ONE);
            }
        }
        if (!qualifierTable.isSelected(selectedItemiden)) {
            btnSave.setCaption(ConstantsUtils.UPDATE);
        } else {
            binder.setItemDataSource(new BeanItem<>(new ItemIrtIdentifierDTO()));
            btnSave.setCaption(ConstantsUtils.SAVE);
        }
    }

    /**
     * Logic for Save button.
     *
     * @return the button
     * @throws Exception the exception
     */
    public Button saveButton() {

        btnSave.setImmediate(true);
        btnSave.addStyleName("button-padding");
        btnSave.addClickListener(new ClickListener() {
            /**
             * After clicking the save button, function will be executed.
             *
             * @param event
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                try {
                    qualifierTable.commit();
                    binder.commit();
                    binder.getErrorDisplay().clearError();

                    if (binderErrorDisplay()) return;

                    if (!ConstantsUtils.SAVE.equals(btnSave.getCaption()) && itemlogic.checkDifferentQualifier(selectedItemiden.getItemIrtQualifierId(), binder.getField(IDENTIFIER_CODE_QUALIFIER).getValue().toString().trim(), pricingFlag)) {
                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Error", "Identifier Code Qualifier cannot be edited.", new MessageBoxListener() {
                            /**
                             * The method is triggered when a button of the
                             * message box is pressed .
                             *
                             * @param buttonId The buttonId of the pressed
                             * button.
                             */
                            @SuppressWarnings("PMD")
                            @Override
                            public void buttonClicked(final ButtonId buttonId) {
                                // Do Nothingreturn

                            }
                        }, ButtonId.OK);
                        msg.getButton(ButtonId.OK).focus();
                        return;
                    }
                    Item item = (Item) binder.getItemDataSource();
                    Boolean recordLockStatus = (item.getItemProperty(RECORD_LOCK_STATUS).getValue() == null) ? false : ((Boolean) item.getItemProperty(RECORD_LOCK_STATUS).getValue());
                    if (recordLockStatus) {
                        final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Access Denied", "You do not have the proper permissions to edit this Qualifier.", new MessageBoxListener() {
                            /**
                             * The method is triggered when a button of the
                             * message box is pressed .
                             *
                             * @param buttonId The buttonId of the pressed
                             * button.
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {

                                // Do Nothingreturn
                                return;
                            }
                        }, ButtonId.OK);
                        msg.getButton(ButtonId.OK).focus();

                        return;
                    }
                    saveConfirmation();
                } catch (CommitException ex) {
                    LOGGER.error(ex);
                }

            }
        });

        return btnSave;
    }

    public void saveConfirmation() {
        MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to save the identifiers?", new MessageBoxListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            public void buttonClicked(final ButtonId buttonId) {
                if (buttonId.name().equals(ConstantsUtils.YES)) {
                    LOGGER.debug("Entering IdenQualifierEditList save operation");
                    saveButtonYesMethod();
                    LOGGER.debug("Ending IdenQualifierEditList save operation");
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    public boolean binderErrorDisplay() {
        if (StringUtils.isEmpty(String.valueOf(binder.getField(IDENTIFIER_CODE_QUALIFIER).getValue()))) {
            binder.getErrorDisplay().setError("Enter Identifier Code Qualifier ");
            return true;
        }
        if (binder.getField("effectiveDates").getValue() == null || effectiveDates.getValue() == null) {
            binder.getErrorDisplay().setError("Select Effective Dates");
            return true;
        }
        if (StringUtils.isEmpty(String.valueOf(binder.getField("identifierCodeQualifierName").getValue()))) {
            binder.getErrorDisplay().setError("Enter Identifier Code Qualifier Name ");
            return true;
        }
        if (binder.getField("entityCode").getValue() == null) {
            binder.getErrorDisplay().setError("Select Entity Code");
            return true;
        }
        return false;
    }

    /**
     * Logic for Delete button.
     *
     * @return the button
     * @throws Exception the exception
     */
    public Button deleteButton() {
        btnDelete.addStyleName("button-padding");
        btnDelete.addClickListener(new ClickListener() {

            /**
             * After clicking the delete button, function will be executed.
             *
             * @param event
             */
            @SuppressWarnings("PMD")

            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering IdenQualifierEditList delete operation");
                try {
                    binder.commit();
                    if (String.valueOf(selectedItemiden.getItemIrtQualifierId()).isEmpty()  || Integer.valueOf(selectedItemiden.getItemIrtQualifierId()) == 0){
                        AbstractNotificationUtils.getErrorNotification(ConstantsUtils.ERROR, "Please select an Identifier from the list view to Delete.");
                    } else {
                        final Item item = (Item) binder.getItemDataSource();
                        Boolean recordLockStatus = (item.getItemProperty(RECORD_LOCK_STATUS).getValue() == null) ? false : ((Boolean) item.getItemProperty(RECORD_LOCK_STATUS).getValue());
                        if (recordLockStatus) {
                            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Access Denied", "You do not have the proper permissions to delete this Qualifier.", new MessageBoxListener() {
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
                        deleteConfirmation(item);
                        LOGGER.debug("Ending IdenQualifierEditList delete operation");
                    }
                } catch (CommitException commitException) {
                    LOGGER.error(commitException);
                }
            }
        });
        return btnDelete;
    }

    public void deleteConfirmation(final Item item) {
        MessageBox.showPlain(Icon.QUESTION, "Delete Confirmation", " Are you sure you want to delete the selected identifier?", new MessageBoxListener() {
            /**
             * After clicking button, function will be executed.
             */
            @SuppressWarnings("PMD")
            public void buttonClicked(final ButtonId buttonId) {
                if (buttonId.name().equals(ConstantsUtils.YES)) {
                    deleteButtonYesMethod(item);
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    /**
     * Logic for Reset button.
     *
     * @return the button
     * @throws Exception the exception
     */
    public Button resetButton() {
        btnReset.addClickListener(new ClickListener() {

            /**
             * After clicking the reset button, function will be executed.
             *
             * @param event
             */
            @SuppressWarnings("PMD")

            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering IdenQualifierEditList reset operation");
                MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to reset the page to default/previous values?" + " ?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                            try {
                                binder.discard();
                                binder.setItemDataSource(new BeanItem<ItemIrtIdentifierDTO>(new ItemIrtIdentifierDTO() {
                                    {
                                        setEffectiveDates(null);
                                        setEntityCode(null);
                                    }
                                }));
                                List<ItemIrtIdentifierDTO> qualifierList = deletePricingQualifier();
                                saveList.clear();
                                setResetValues(qualifierList);
                                qualifierTable.setValue(null);
                                btnSave.setCaption(ConstantsUtils.SAVE);
                            } catch (Exception exception) {
                                LOGGER.error(exception);
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

                LOGGER.debug("Ending IdenQualifierEditList reset operation");

            }
        });

        return btnReset;

    }
    
    public List<ItemIrtIdentifierDTO> deletePricingQualifier() throws SystemException, PortalException {
        List<ItemIrtIdentifierDTO> qualifierList = new ArrayList<>();
        for (String obj : saveList) {
            for (ItemIrtIdentifierDTO dto : itemQualifier.getItemIds()) {
                if (dto.getIdentifierCodeQualifier().equals(obj) && dto.getItemIrtQualifierId() != 0) {
                    if (pricingFlag) {
                        qualifierList = itemlogic.deletePricingQualifer(dto.getItemIrtQualifierId());
                    } else {
                        qualifierList = itemlogic.deleteIrtQualifer(dto.getItemIrtQualifierId());
                    }
                }
            }

        }
        return qualifierList;
    }
    
    public void setResetValues(List<ItemIrtIdentifierDTO> qualifierList) {
        if (!qualifierList.isEmpty()) {
            itemQualifier.removeAllItems();
            itemQualifier.addAll(qualifierList);
            binder.discard();
            binder.setItemDataSource(new BeanItem<>(new ItemIrtIdentifierDTO() {
                {
                    setEffectiveDates(null);
                    setEntityCode(null);
                }
            }));
        }
    }

    public void saveButtonYesMethod() {
        try {
            int val = 0;
            qualifierTable.addItem(binder.getItemDataSource());
            itemQualifier.removeAllItems();

            saveList.add(String.valueOf(binder.getField(IDENTIFIER_CODE_QUALIFIER).getValue()));
            if (!ConstantsUtils.SAVE.equals(btnSave.getCaption())) {
                val = selectedItemiden.getItemIrtQualifierId();
            }
            if (pricingFlag) {
                itemQualifier.addAll(itemlogic.savePricingQualifer(binder, val));
            } else {
                itemQualifier.addAll(itemlogic.saveIrtQualifer(binder, val));
            }
            binder.discard();
            binder.setItemDataSource(new BeanItem<ItemIrtIdentifierDTO>(new ItemIrtIdentifierDTO() {
                {
                    setEffectiveDates(null);
                    setEntityCode(null);

                }
            }));
            btnSave.setCaption(ConstantsUtils.SAVE);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (PortalException portException) {
            LOGGER.error(portException);
        } catch (Exception exception) {
            LOGGER.error(exception);
        }
    }

    public void deleteButtonYesMethod(Item item) {
        try {

            List<ItemIrtIdentifierDTO> qualifierList = new ArrayList<>();
            if (Integer.valueOf(item.getItemProperty(ConstantsUtils.ITEM_IRT_QUALIFIFIERID).getValue().toString()) != 0) {
                if (pricingFlag) {
                    qualifierList = itemlogic.deletePricingQualifer(Integer.valueOf(item.getItemProperty(ConstantsUtils.ITEM_IRT_QUALIFIFIERID).getValue().toString()));
                } else {
                    qualifierList = itemlogic.deleteIrtQualifer(Integer.valueOf(item.getItemProperty(ConstantsUtils.ITEM_IRT_QUALIFIFIERID).getValue().toString()));
                }
            }
            if (saveList.contains(item.getItemProperty(ConstantsUtils.ITEM_IRT_QUALIFIFIERID).getValue().toString())) {
                saveList.remove(item.getItemProperty(ConstantsUtils.ITEM_IRT_QUALIFIFIERID).getValue().toString());
            }
            if (!qualifierList.isEmpty()) {
                itemQualifier.removeAllItems();
                itemQualifier.addAll(qualifierList);
                binder.discard();
                binder.setItemDataSource(new BeanItem<ItemIrtIdentifierDTO>(new ItemIrtIdentifierDTO() {
                    {
                        setEffectiveDates(null);
                        setEntityCode(null);
                    }
                }));
            }
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (PortalException portException) {
            LOGGER.error(portException);
        } catch (Exception exception) {
            LOGGER.error(exception);
        }

    }
}
