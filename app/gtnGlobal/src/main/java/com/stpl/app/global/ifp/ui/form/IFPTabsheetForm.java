package com.stpl.app.global.ifp.ui.form;

import com.stpl.app.global.abstractsearch.view.AbstractSearchView;
import com.stpl.app.global.common.ui.InformationLayout;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.dao.impl.IFPLogicDAOImpl;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.ifp.dto.IFPItemDTO;
import com.stpl.app.global.ifp.dto.ItemFamilyplanMasterDTO;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.ifp.ui.view.IFPAddView;
import com.stpl.app.global.ifp.util.FieldNameUtils;
import com.stpl.app.global.ifp.util.LabelUtils;
import com.stpl.app.global.item.dto.ItemMasterDTO;
import com.stpl.app.model.IfpContract;
import com.stpl.app.model.IfpModel;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.ImtdIfpDetailsLocalServiceUtil;
import com.stpl.app.ui.NotesTabForm;
import com.stpl.app.ui.StplCustomComponent;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.FunctionNameUtil;
import com.stpl.app.util.TabNameUtil;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.app.util.ValidationUtils;
import com.stpl.domain.global.base.AddBaseForm;
import com.stpl.domain.global.itemfamilyplan.ItemFamilyplanDAO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

// TODO: Auto-generated Javadoc
/**
 * The Class IFPTabsheetForm.
 */
public final class IFPTabsheetForm extends StplCustomComponent implements AddBaseForm {

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(IFPTabsheetForm.class);

    /**
     * The ifp logic.
     */
    private final IFPLogic ifpLogic;
    /**
     * The item Family plan SystemId.
     */
    private TextField itemFamilyplanSystemId = new TextField();

    /**
     * The space.
     */
    private final Label space = new Label(ConstantsUtils.SPACE, ContentMode.HTML);

    /**
     * The commitment period.
     */
    private final TextField commitmentPeriod = new TextField();
    /**
     * The item map.
     */

    /**
     * The ifp master.
     */
    private ItemFamilyplanMasterDTO ifpMaster;
    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder;
    IFPItems ifpItems;

    IFPItemAddition ifpItemAddition;
    CommonUtil commonutil = CommonUtil.getInstance();

    Button prevColumn = new Button("<<");
    Button nextColumn = new Button(">>");
    NotesTabForm notesTab;

    String selectedTabIndex = "IFPInformation";

    /**
     * The tabsheet.
     */
    @UiField("tabSheet")
    TabSheet tabSheet;

    @UiField("errorMsg")
    private ErrorLabel errorMsg;

    @UiField("saveBtn")
    private Button saveBtn;

    @UiField("backBtn")
    private Button backBtn;

    @UiField("resetBtn")
    private Button resetBtn;

    @UiField("deleteBtn")
    private Button deleteBtn;

    @UiField("informationLayout")
    private VerticalLayout informationLayout;

    private String mode;
    private final boolean isAddMode;
    private final boolean isEditMode;
    private final boolean isViewMode;

    ItemFamilyplanDAO itemFamilyplanDAO = new IFPLogicDAOImpl();
    final BeanItemContainer<IFPItemDTO> itemDetailsResultsBean;

    IFPInformation ifpInfo;
    SessionDTO sessionDTO;

    /**
     * Instantiates a new adds the form for ifp.
     *
     * @param ifpMaster the ifp master
     * @param binder the binder
     * @param availableItemResultBean the available item result bean
     * @param selectedItemResultBean the selected item result bean
     * @param itemDetailsResultsBean the item details results bean
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public IFPTabsheetForm(final ItemFamilyplanMasterDTO ifpMaster, final ErrorfulFieldGroup binder, final BeanItemContainer<IFPItemDTO> itemDetailsResultsBean, String mode, final SessionDTO sessionDTO)
            throws PortalException, SystemException {
        super();
        this.itemDetailsResultsBean = itemDetailsResultsBean;
        this.ifpMaster = ifpMaster;
        this.binder = binder;
        this.sessionDTO = sessionDTO;
        ifpLogic = new IFPLogic(this.sessionDTO);
        this.mode = mode;
        this.isAddMode = ConstantsUtils.ADD.equals(mode);
        this.isEditMode = ConstantsUtils.EDIT.equals(mode) || ConstantsUtils.COPY.equals(mode);
        this.isViewMode = ConstantsUtils.VIEW_BTN.equals(mode);
        init();
        itemFamilyplanSystemId.setImmediate(Boolean.TRUE);

    }

    /**
     * Initial method when the Constructor get calls.
     *
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public void init() throws PortalException, SystemException {
        space.setHeight(ConstantsUtils.HEIGHT);
        LOGGER.debug("Entering init()");
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/common/tabsheetform.xml"), this));
        if (ConstantsUtils.COPY.equals(mode)) {
        configInfoLayoutCopy();
        }
        informationLayout.addComponent(new InformationLayout(ConstantsUtils.IFP, ifpMaster.getItemFamilyplanId(), ifpMaster.getItemFamilyplanNo(), ifpMaster.getItemFamilyplanName()));
        addToContent();
        LOGGER.debug("Ending init()");
    }

    /**
     * Adds all contents.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public void addToContent() throws SystemException, PortalException {
        LOGGER.debug("Entering addToContent");

        binder = getBinder();
        addTabSheet();
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> functionIfpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.ITEM_FAMILY_PLAN);
        backBtn();
        if (functionIfpHM.get(FunctionNameUtil.SAVE) != null && ((AppPermission) functionIfpHM.get(FunctionNameUtil.SAVE)).isFunctionFlag()) {
            addButton();
        } else {
            saveBtn.setVisible(false);
        }

        if (functionIfpHM.get(FunctionNameUtil.RESET) != null && ((AppPermission) functionIfpHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
            resetBtn();
        } else {
            resetBtn.setVisible(false);
        }
        if (isEditMode) {
            if (functionIfpHM.get(FunctionNameUtil.DELETE) != null && ((AppPermission) functionIfpHM.get(FunctionNameUtil.DELETE)).isFunctionFlag()) {
                deleteBtn();
            } else {
                deleteBtn.setVisible(false);
            }
            saveBtn.setCaption("Update");
        }
        if (isEditMode && ConstantsUtils.COPY.equals(mode)) {
            saveBtn.setCaption("Save");
            deleteBtn.setVisible(false);
        }
        if (isAddMode) {
            saveBtn.setCaption("Save");
            deleteBtn.setVisible(false);
        }

        backBtn.setEnabled(true);
        saveBtn.setEnabled(true);
        resetBtn.setEnabled(true);
        if (isViewMode) {
            saveBtn.setVisible(false);
            deleteBtn.setVisible(false);
            resetBtn.setVisible(false);
        }
        LOGGER.debug("Ending addToContent");
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private ErrorfulFieldGroup getBinder() {

        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<>(ifpMaster));
        binder.setBuffered(true);
        binder.setErrorDisplay(errorMsg);
        errorMsg.setId("ErrorMessage");
        return binder;
    }

    /**
     * Adds the tab sheet.
     *
     * @return the tab sheet
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public void addTabSheet() throws SystemException, PortalException {
        LOGGER.debug("Entering addTabSheet()");

        try {
            tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
            tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

            tabSheet.setId("responsive-tab");
            tabSheet.setReadOnly(true);

            ifpInfo = new IFPInformation(ifpMaster, ConstantsUtils.VIEW_BTN.equals(mode) ? ConstantsUtils.VIEW_BTN : mode);
            ifpInfo.setCaption(ConstantsUtils.IFP_INFORMATION);
            tabSheet.addTab(ifpInfo, TabNameUtil.IFP_INFO);
            if ((ConstantsUtils.EDIT.equals(mode)) || (ConstantsUtils.COPY.equals(mode))) {
                if ("Child".equalsIgnoreCase(String.valueOf(ifpMaster.getItemFamilyplanDesignation()))) {
                    ifpInfo.enableParent(true);
                } else {
                    ifpInfo.enableParent(false);
                }
                if (ConstantsUtils.COPY.equals(mode)) {
                    ifpMaster.setItemFamilyplanId(StringUtils.EMPTY);
                    ifpMaster.setItemFamilyplanName(StringUtils.EMPTY);
                    ifpMaster.setItemFamilyplanNo(StringUtils.EMPTY);
                    ifpMaster.setModifiedBy(StringUtils.EMPTY);
                }
            }
            ifpInfo.setDefaultFocus();

            ifpItemAddition = new IFPItemAddition(binder, ifpMaster, ConstantsUtils.VIEW_BTN.equals(mode) ? ConstantsUtils.VIEW_BTN : mode, sessionDTO);
            ifpItemAddition.setCaption(ConstantsUtils.ITEM_ADDITION);
            tabSheet.addTab(ifpItemAddition, TabNameUtil.ITEM_ADDITION);

            ifpItems = new IFPItems(binder, sessionDTO);
            ifpItems.setCaption(ConstantsUtils.IFP_ITEMS);
            tabSheet.addTab(ifpItems, TabNameUtil.IFP_ITEMS);

            notesTab = new NotesTabForm(this.binder, "Item Family Plan", "IFP_MODEL", ConstantsUtils.IFP_SYSTEM_ID, ConstantsUtils.VIEW_BTN.equals(mode) ||  ConstantsUtils.COPY.equals(mode)? "Edit" : mode);
            notesTab.setCaption(ConstantsUtils.NOTES);
            tabSheet.addTab(notesTab, TabNameUtil.NOTES);
            notesTab.refreshTable();
            if (isViewMode) {
                notesTab.removeAndDisablingComponents();
            }
            tabSheet.setErrorHandler(new ErrorHandler() {
                @SuppressWarnings("PMD")
                public void error(final com.vaadin.server.ErrorEvent event) {
                    // empty method
                }
            });
            tabSheet.addSelectedTabChangeListener(new SelectedTabChangeListener() {
                @SuppressWarnings("PMD")
                public void selectedTabChange(final SelectedTabChangeEvent event) {

                    binder.getErrorDisplay().clearError();
                    selectedTabIndex = event.getTabSheet().getSelectedTab().getCaption();
                    selectedTabChangeListener();
                }
            });
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("Ending addTabSheet()");
    }

    /**
     * Adds the buttons.
     *
     * @return the vertical layout
     */
    public CssLayout addButtons() {
        final CssLayout layout1 = new CssLayout();

        layout1.setStyleName("NavButtonsForIFP");
        return layout1;

    }

    /**
     * overrided method
     */
    @Override
    public void addLogic() {
        return;
    }

    /**
     * To format the given Date.
     *
     * @param date the date
     * @return the date
     * @throws ParseException the parse exception
     */
    public Date toFormatDate(final Date date) throws ParseException {
        final DateFormat inputFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);

        return inputFormat.parse(inputFormat.format(date));

    }

    /**
     * Gets the ifp master.
     *
     * @return the ifp master
     */
    public ItemFamilyplanMasterDTO getIfpMaster() {
        return ifpMaster;
    }

    public void setIfpMaster(ItemFamilyplanMasterDTO ifpMaster) {
        this.ifpMaster = ifpMaster;
    }

    /**
     * Sets the binder.
     *
     * @param binder the new binder
     */
    public void setBinder(final ErrorfulFieldGroup binder) {
        this.binder = binder;
    }

    /**
     * Gets the commitment period.
     *
     * @return the commitment period
     */
    public TextField getCommitmentPeriod() {
        return commitmentPeriod;
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
     * Gets the ifp logic.
     *
     * @return the ifp logic
     */
    public IFPLogic getIfpLogic() {
        return ifpLogic;
    }

    /**
     * Gets the space.
     *
     * @return the space
     */
    public Label getSpace() {
        return space;
    }

    /**
     * Adds the button.
     *
     * @throws Exception the exception
     */
    private void addButton() {

        // Commit button
        try {
            saveBtn.setWidth(ConstantsUtils.BTN_WIDTH);
            saveBtn.addClickListener(new ClickListener() {

                /**
                 * Called when a Button has been clicked.
                 */
                // @SuppressWarnings("unchecked")
                @SuppressWarnings("PMD")
                public void buttonClick(final ClickEvent event) {
                    LOGGER.debug("Entering IFPAddActionButtonLayout addButton ");
                    if (isAddMode || ConstantsUtils.COPY.equals(mode)) {
                        LOGGER.debug("Entering save ");
                        saveButtonLogic();
                    }
                    if (isEditMode && !(ConstantsUtils.COPY.equals(mode))) {
                        LOGGER.debug("Entering update ");
                        updateButtonLogic();
                    }
                    LOGGER.debug("Ending IFPAddActionButtonLayout addButton ");
                }
            });
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Back button properties.
     *
     * @throws Exception the exception
     */
    private void backBtn() {

        backBtn.setWidth(ConstantsUtils.BTN_WIDTH);
        backBtn.addClickListener(new ClickListener() {

            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering IFPAddActionButtonLayout backBtn ");
                if (sessionDTO.getMode().equals(ConstantsUtils.VIEW_BTN)) {
                    ifpLogic.removeAllFromTempTable(true);
                    AbstractSearchView.flag = false;
                    getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                    LOGGER.debug("Ending IFPAddActionButtonLayout backBtn");
                } else {
                    MessageBox.showPlain(Icon.QUESTION, commonutil.getHeaderMessage(), commonutil.getBackMessage(), new MessageBoxListener() {

                        @Override
                        public void buttonClicked(ButtonId buttonId) {

                            if (buttonId.name().equals(ConstantsUtils.YES)) {
                                ifpLogic.removeAllFromTempTable(true); /**
                                 * The method is triggered when a button
                                 * of the message box is pressed .
                                 *
                                 * @param buttonId The buttonId of the
                                 * pressed button.
                                 */ // Do Nothing
                                /**
                                 * The method is triggered when a button
                                 * of the message box is pressed .
                                 *
                                 * @param buttonId The buttonId of the
                                 * pressed button.
                                 */
                                // Do Nothing
                                AbstractSearchView.flag = false;
                                getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                                LOGGER.debug("Ending IFPAddActionButtonLayout backBtn");
                                /**
                                 * The method is triggered when a button
                                 * of the message box is pressed .
                                 *
                                 * @param buttonId The buttonId of the
                                 * pressed button.
                                 */
                                // Do Nothing
                                
                            }
                        }

                    }, ButtonId.YES, ButtonId.NO);
                }

            }
        });
        backBtn.setErrorHandler(new ErrorHandler() {

            /**
             * Called when a Button has been clicked.
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                // empty method
            }
        });
    }

    /**
     * reset button.
     */
    private void resetBtn() {
        resetBtn.setWidth(ConstantsUtils.BTN_WIDTH);
        resetBtn.addClickListener(new ClickListener() {
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
            public void buttonClick(final ClickEvent event) {
                MessageBox.showPlain(Icon.QUESTION, commonutil.getHeaderMessage(), commonutil.getResetMessage(), new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                            try {
                                LOGGER.debug("Entering inside  reset  method from ADD ");
                                if (isAddMode) {
                                    if (selectedTabIndex.equals(ConstantsUtils.IFP_INFORMATION)) {
                                        ifpInfo.resetBinder(new ItemFamilyplanMasterDTO());
                                        if (ConstantsUtils.COPY.equals(sessionDTO.getMode())) {
                                            ifpMaster.setIfpId(StringUtils.EMPTY);
                                            ifpMaster.setIfpName(StringUtils.EMPTY);
                                            ifpMaster.setIfpNo(StringUtils.EMPTY);
                                        }
                                        ifpInfo.setDefaultFocus();
                                    } else if (selectedTabIndex.equals(ConstantsUtils.ITEM_ADDITION)) {
                                        TextField value = (TextField) binder.getField(ConstantsUtils.SEARCH_VALUE);
                                        value.setValue(StringUtils.EMPTY);
                                        ComboBox field = (ComboBox) binder.getField(ConstantsUtils.SEARCH_FIELDS);
                                        field.setValue(ConstantsUtils.SELECT_ONE);
                                        ifpItemAddition.resetItemAdditionTab();

                                    } else if (selectedTabIndex.equals(ConstantsUtils.IFP_ITEMS)) {
                                        ifpItems.resetItemsTab();
                                        ImtdIfpDetailsLocalServiceUtil.deleteAll(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID)),
                                                String.valueOf(sessionDTO.getUiSessionId()),
                                                null, null, null, ConstantsUtils.DELETE1, null, null);
                                        ifpItems.addItemDetailsTable();

                                    } else if (selectedTabIndex.equals(ConstantsUtils.NOTES)) {
                                        notesTab.resetAddMode();
                                    }
                                }
                                if (isEditMode) {
                                    LOGGER.debug("Entering inside  reset  method from ADD ");
                                    final int systemId = (Integer) sessionDTO.getSystemId();
                                    final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
                                    ifpMaster = ifpLogic.getIFPById(Integer.valueOf(systemId), ifpMaster);
                                    if (selectedTabIndex.equals(ConstantsUtils.IFP_INFORMATION)) {
                                        ifpInfo.resetBinder(ifpMaster);
                                        ifpInfo.setDefaultFocus();
                                    }
                                    if (selectedTabIndex.equals(ConstantsUtils.ITEM_ADDITION)) {
                                        TextField value = (TextField) binder.getField(ConstantsUtils.SEARCH_VALUE);
                                        value.setValue(StringUtils.EMPTY);
                                        ComboBox field = (ComboBox) binder.getField(ConstantsUtils.SEARCH_FIELDS);
                                        field.setValue(ConstantsUtils.SELECT_ONE);
                                        ifpItems.saveRecordsInTempTable();
                                        ImtdIfpDetailsLocalServiceUtil.deleteAll(userId, sessionDTO.getUiSessionId(), null, null, null, ConstantsUtils.DELETE1, null, null);
                                        ifpLogic.loadTempTable(systemId);
                                        ifpItemAddition.resetAvailableTable();
                                        ifpItemAddition.loadSelectedTable();
                                    }
                                    if (selectedTabIndex.equals(ConstantsUtils.IFP_ITEMS)) {
                                        ifpItems.getMassCheck().setValue(ConstantsUtils.DISABLE);
                                        ifpItems.saveRecordsInTempTable();
                                        ImtdIfpDetailsLocalServiceUtil.deleteAll(userId, sessionDTO.getUiSessionId(), null, null, null, ConstantsUtils.DELETE1, null, null);
                                        ifpLogic.loadTempTable(systemId);
                                        ifpItems.loadDetailsTable();
                                    } else if (selectedTabIndex.equals(ConstantsUtils.NOTES)) {
                                        notesTab.refreshTable();
                                        notesTab.readOnlyNotesHistory(false);
                                        notesTab.setNotesHistoryValue(ifpMaster.getInternalNotes());
                                        notesTab.readOnlyNotesHistory(true);
                                    }
                                }
                                if (ConstantsUtils.COPY.equals(sessionDTO.getMode())) {
                                            ifpMaster.setIfpId(StringUtils.EMPTY);
                                            ifpMaster.setIfpName(StringUtils.EMPTY);
                                            ifpMaster.setIfpNo(StringUtils.EMPTY);
                                        }
                                

                            } catch (Exception e) {
                                LOGGER.error(e);
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

            }

        });
    }

    public IFPItemAddition getIfpItemAddition() {
        return ifpItemAddition;
    }

    @Override
    public void configureFields() throws PortalException, SystemException {
        // TODO Auto-generated method stub

    }

    private void deleteBtn() {
        deleteBtn.setVisible(true);
        deleteBtn.setWidth(ConstantsUtils.BTN_WIDTH);
        deleteBtn.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             *
             * @param event the fired event.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.getThrowable());
            }
        });
        deleteBtn.addClickListener(new ClickListener() {
            /**
             * Called when a Button has been clicked.
             *
             * @param event An event containing information about the click.
             */
            public void buttonClick(final ClickEvent event) {
                binder = ifpInfo.getBindedFields();
                binder.setErrorDisplay(errorMsg);
                binder.getFields();
                final String ifpId = binder.getField(ConstantsUtils.IFP_ID).getValue().toString();
                try {
                    LOGGER.debug("Entering IFPEditActionButtonLayout deleteButton");
                    final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpModel.class);
                    ifpDynamicQuery.add(RestrictionsFactoryUtil.eq("parentIfpId", ifpId));
                    ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
                    final List<IfpModel> ifpDetailsList = itemFamilyplanDAO.getItemFamilyplanMasterList(ifpDynamicQuery);
                    if (!ifpDetailsList.isEmpty()) {
                        final MessageBox msg = MessageBox.showPlain(Icon.WARN, ValidationUtils.CANNOT_DELETE, "IFP cannot be deleted, associated with another IFP", new MessageBoxListener() {
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
                    if (ifpDetailsList.isEmpty()) {

                        String systemId = binder.getField(ConstantsUtils.IFP_SYSTEM_ID)
                                .getValue() == null && ConstantsUtils.NULL.equals(binder
                                        .getField(ConstantsUtils.IFP_SYSTEM_ID).getValue()) ? ConstantsUtils.ZERO : String
                                .valueOf(binder.getField(ConstantsUtils.IFP_SYSTEM_ID).getValue()).replace(ConstantsUtils.COMMA, StringUtils.EMPTY);
                        List<IfpContract> ifpContractList = new ArrayList<>();
                        if (!ConstantsUtils.ZERO.equals(systemId)) {
                            final DynamicQuery contractDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpContract.class);
                            contractDynamicQuery.add(RestrictionsFactoryUtil.eq("ifpModelSid", Integer.valueOf(systemId)));
                            contractDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
                            ifpContractList = itemFamilyplanDAO.getItemFamilyplanContractList(contractDynamicQuery);
                            if (ifpContractList != null && !ifpContractList.isEmpty() && ifpContractList.size() > 0) {
                                final MessageBox msg = MessageBox.showPlain(Icon.WARN, ValidationUtils.CANNOT_DELETE, "Item Family Plan cannot be deleted as it is associated with Contract", new MessageBoxListener() {

                                    @SuppressWarnings("PMD")
                                    public void buttonClicked(final ButtonId buttonId) {
                                        // Do Nothing
                                    }
                                }, ButtonId.OK);
                                msg.getButton(ButtonId.OK).focus();

                            }
                        }

                        if (ifpContractList != null && ifpContractList.isEmpty()) {
                            MessageBox.showPlain(Icon.QUESTION, commonutil.getHeaderMessage(), commonutil.getDeleteMessage(binder.getField(ConstantsUtils.IFP_NAME).toString()), new MessageBoxListener() {
                                /**
                                 * Called when a Button has been clicked.
                                 *
                                 * @param event An event containing information
                                 * about the click.
                                 */
                                @SuppressWarnings("PMD")
                                public void buttonClicked(final ButtonId buttonId) {
                                    if (ConstantsUtils.YES.equals(buttonId.name())) {
                                        deleteButtonYesMethod();
                                    }
                                }
                            }, ButtonId.YES, ButtonId.NO);
                            LOGGER.debug("Ending IFPEditActionButtonLayout deleteButton");
                        }
                    }

                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(errorMsg);
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
                } catch (PortalException ex) {
                    LOGGER.error(ex);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1012), new MessageBoxListener() {
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
            }
        });

    }

    private void saveButtonLogic() {
        try {
            binder = ifpInfo.getBindedFields();
            binder.setErrorDisplay(errorMsg);
            binder.getErrorDisplay().clearError();
            binder.commit();
            binder.getFields();
            int count = ifpLogic.tempTableCount(null);
            long startdateValidationCount;
            boolean flag = false;
            final int userId = Integer.parseInt(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID)));
            final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
            StringBuilder errorMessage = new StringBuilder(ConstantsUtils.INFORMATION_MANDATORY_FIELDS + ConstantsUtils.BREAK);
            if (binder.getField(ConstantsUtils.IFP_ID).getValue().toString().isEmpty()) {
                if (flag) {
                    errorMessage.append(ConstantsUtils.COMMA);
                }
                errorMessage.append(LabelUtils.ITEMFAMILYPLANID);
                flag = true;
            }
            if (binder.getField(ConstantsUtils.IFP_NO).getValue().toString().isEmpty()) {
                if (flag) {
                    errorMessage.append(ConstantsUtils.COMMA);
                }
                errorMessage.append(LabelUtils.ITEMFAMILYPLANNO);
                flag = true;
            }
            if (binder.getField(ConstantsUtils.IFP_NAME).getValue().toString().isEmpty()) {
                if (flag) {
                    errorMessage.append(ConstantsUtils.COMMA);
                }
                errorMessage.append(LabelUtils.ITEMFAMILYPLANNAME);
                flag = true;
            }
            if (binder.getField("itemFamilyplanStatus").getValue() == null
                    || ((com.stpl.ifs.util.HelperDTO) binder.getField("itemFamilyplanStatus").getValue()).getId() == 0) {
                if (flag) {
                    errorMessage.append(ConstantsUtils.COMMA);
                }
                errorMessage.append(LabelUtils.ITEMFAMILYPLANSTATUS);
                flag = true;
            }
            if (binder.getField(FieldNameUtils.ITEMFAMILYPLANSTARTDATE).getValue() == null) {
                if (flag) {
                    errorMessage.append(ConstantsUtils.COMMA);
                }
                errorMessage.append(LabelUtils.ITEMFAMILYPLANSTARTDATE);
                flag = true;
            }
            if (flag) {
                errorMessage.append(ConstantsUtils.BREAK);
            }
            if (count == ConstantsUtils.ZERO_INT) {
                errorMessage.append(ConstantsUtils.ADD_ATLEAST_ONE_ITEM_FOR_IFP);
                flag = true;
            }
            if (flag) {
                binder.getErrorDisplay().setError(errorMessage.toString());
                return;
            }
            ifpItems.saveRecordsInTempTable();
            startdateValidationCount = ifpLogic.startDateValidation(userId, sessionId);
            if (!ifpLogic.saveValidation("checkRecord")) {
                binder.getErrorDisplay().setError("Check Aleast one Item to save in Items tab");
                return;
            }
            if (startdateValidationCount > 0) {
                binder.getErrorDisplay().setError("IFP End date Should be Greater than IFP Start Date");
                return;
            }
            List<Object> itemIdList = ifpLogic.validateNull("StartDate");
            if (itemIdList.size() > 0) {
                Object itemNo = itemIdList.get(0);

                if (itemIdList.size() > 1) {
                    binder.getErrorDisplay().setError("Start Date required for selected items in Items tab");
                    return;
                } else {
                    binder.getErrorDisplay().setError(ValidationUtils.SD_ITEM_ID_VALID + itemNo + ConstantsUtils.IN_ITEMS_TAB);
                    return;
                }
            }
            itemIdList = ifpLogic.validateNull("Status");
            if (itemIdList.size() > 0) {
                Object itemNo = itemIdList.get(0);

                if (itemIdList.size() > 1) {
                    binder.getErrorDisplay().setError("Status required for selected items in Items tab");
                    return;
                } else {
                    binder.getErrorDisplay().setError(ValidationUtils.STATUS_VALID + itemNo + ConstantsUtils.IN_ITEMS_TAB);
                    return;
                }
            }

            final List<IFPItemDTO> ifpList = new ArrayList<>();

            
                MessageBox.showPlain(Icon.QUESTION, commonutil.getHeaderMessage(), commonutil.getSaveMessage((String) binder.getField(ConstantsUtils.IFP_NAME).getValue()), new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        String msg = StringUtils.EMPTY;
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                            msg = saveButtonYesmethod(ifpList, msg);
                            LOGGER.debug("------------msg----" + msg);
                            if (ConstantsUtils.SUCCESS.equals(msg)) {
                                final Notification notif = new Notification(commonutil.getSavedSuccessfulMessage(binder.getField(ConstantsUtils.IFP_ID).getValue().toString(), binder.getField(ConstantsUtils.IFP_NAME).getValue().toString()));
                                notif.setPosition(Position.MIDDLE_CENTER);
                                notif.setStyleName(ConstantsUtils.MY_STYLE);
                                notif.show(Page.getCurrent());
                                notif.setDelayMsec(NumericConstants.THOUSAND);
                                sessionDTO.setMode(ConstantsUtils.EDIT);
                                getUI().getNavigator().navigateTo(IFPAddView.NAME);
                            } else if (ConstantsUtils.DUPLICATE.equals(msg)) {
                                binder.getErrorDisplay().setError("Item Family Plan ID already exists.");

                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

            
            binder.commit();

        } catch (FieldGroup.CommitException ex) {
            LOGGER.error(ex);
            boolean flag = false;
            int count = 0;
            try {
                count = ifpLogic.tempTableCount(null);
            } catch (SystemException ex1) {
                LOGGER.error(ex1);
            }
            StringBuilder errorMessage = new StringBuilder(ConstantsUtils.INFORMATION_MANDATORY_FIELDS + ConstantsUtils.BREAK);
            if (ex.getCause().getMessage().equals("Date format not recognized")) {
                return;
            }
            if (binder.getField(FieldNameUtils.ITEMFAMILYPLANID).getValue().toString().isEmpty()) {
                if (flag) {
                    errorMessage.append(ConstantsUtils.COMMA);
                }
                errorMessage.append(LabelUtils.ITEMFAMILYPLANID);
                flag = true;
            }
            if (binder.getField(FieldNameUtils.ITEMFAMILYPLANSTATUS).getValue() == null
                    || ((com.stpl.ifs.util.HelperDTO) binder.getField(FieldNameUtils.ITEMFAMILYPLANSTATUS).getValue()).getId() == 0) {
                if (flag) {
                    errorMessage.append(ConstantsUtils.COMMA);
                }
                errorMessage.append(LabelUtils.ITEMFAMILYPLANSTATUS);
                flag = true;
            }
            if (binder.getField(FieldNameUtils.ITEMFAMILYPLANSTARTDATE).getValue() == null) {
                if (flag) {
                    errorMessage.append(ConstantsUtils.COMMA);
                }
                errorMessage.append(LabelUtils.ITEMFAMILYPLANSTARTDATE);
                flag = true;
            }
            if (flag) {
                errorMessage.append(ConstantsUtils.BREAK);
            }
            if (count == ConstantsUtils.ZERO_INT) {
                errorMessage.append(ConstantsUtils.ADD_ATLEAST_ONE_ITEM_FOR_IFP);
                flag = true;
            }
            if (flag) {
                binder.getErrorDisplay().setError(errorMessage.toString());
                return;
            }

        } catch (SystemException ex) {
            LOGGER.error(ex);
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            LOGGER.error(errorMsg);
            final MessageBox msgBox = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
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
        } catch (PortalException portException) {
            LOGGER.error(portException);
            final MessageBox msgBox = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1003), new MessageBoxListener() {
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
        } catch (Exception exception) {
            LOGGER.error(exception);
            final MessageBox msgBox = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1003), new MessageBoxListener() {
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
    }

    private void updateButtonLogic() {
        try {
            binder = ifpInfo.getBindedFields();
            binder.setErrorDisplay(errorMsg);
            binder.getErrorDisplay().clearError();
            binder.commit();
            long startdateValidationCount;
            final int userId = Integer.parseInt(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID)));
            final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
            int count = ifpLogic.tempTableCount(null);
            ifpItems.configureInTabChange();
            boolean flag = false;
            StringBuilder errorMessage = new StringBuilder(ConstantsUtils.INFORMATION_MANDATORY_FIELDS + ConstantsUtils.BREAK);

            if (binder.getField(FieldNameUtils.ITEMFAMILYPLANID).getValue().toString().isEmpty()) {
                if (flag) {
                    errorMessage.append(ConstantsUtils.COMMA);
                }
                errorMessage.append(LabelUtils.ITEMFAMILYPLANID);
                flag = true;
            }
            if (binder.getField(ConstantsUtils.IFP_NO).getValue().toString().isEmpty()) {
                if (flag) {
                    errorMessage.append(ConstantsUtils.COMMA);
                }
                errorMessage.append(LabelUtils.ITEMFAMILYPLANNO);
                flag = true;
            }
            if (binder.getField(ConstantsUtils.IFP_NAME).getValue().toString().isEmpty()) {
                if (flag) {
                    errorMessage.append(ConstantsUtils.COMMA);
                }
                errorMessage.append(LabelUtils.ITEMFAMILYPLANNAME);
                flag = true;
            }
            if (binder.getField(FieldNameUtils.ITEMFAMILYPLANSTATUS).getValue() == null
                    || ((com.stpl.ifs.util.HelperDTO) binder.getField(FieldNameUtils.ITEMFAMILYPLANSTATUS).getValue()).getId() == 0) {
                if (flag) {
                    errorMessage.append(ConstantsUtils.COMMA);
                }
                errorMessage.append(LabelUtils.ITEMFAMILYPLANSTATUS);
                flag = true;
            }
            if (binder.getField(FieldNameUtils.ITEMFAMILYPLANSTARTDATE).getValue() == null) {
                if (flag) {
                    errorMessage.append(ConstantsUtils.COMMA);
                }
                errorMessage.append(LabelUtils.ITEMFAMILYPLANSTARTDATE);
                flag = true;
            }
            if (flag) {
                errorMessage.append(ConstantsUtils.BREAK);
            }
            if (count == ConstantsUtils.ZERO_INT) {
                errorMessage.append(ConstantsUtils.ADD_ATLEAST_ONE_ITEM_FOR_IFP);
                flag = true;
            }
            if (flag) {
                binder.getErrorDisplay().setError(errorMessage.toString());
                return;
            }

            final List<IFPItemDTO> ifpList = new ArrayList<>();
            ifpItems.updateRecordsInTempTable();
            if (!ifpLogic.saveValidation("checkRecord")) {
                binder.getErrorDisplay().setError("Check Aleast one Item to save in Items tab");
                return;
            }
            startdateValidationCount = ifpLogic.startDateValidation(userId, sessionId);
            if (startdateValidationCount > 0) {
                binder.getErrorDisplay().setError("IFP End date Should be Greater than IFP Start Date");
                return;
            }
            List<Object> itemIdList = ifpLogic.validateNull("StartDate");
            if (itemIdList.size() > 0) {
                Object itemNo = itemIdList.get(0);

                if (itemIdList.size() > 1) {
                    int itemCount = itemIdList.size() - 1;
                    binder.getErrorDisplay().setError(ValidationUtils.SD_ITEM_ID_VALID + itemNo + " and " + itemCount + " items in Items tab");
                    return;
                } else {
                    binder.getErrorDisplay().setError(ValidationUtils.SD_ITEM_ID_VALID + itemNo + ConstantsUtils.IN_ITEMS_TAB);
                    return;
                }
            }
            itemIdList = ifpLogic.validateNull("Status");
            if (itemIdList.size() > 0) {
                Object itemNo = itemIdList.get(0);

                if (itemIdList.size() > 1) {
                    int itemCount = itemIdList.size() - 1;
                    binder.getErrorDisplay().setError(ValidationUtils.STATUS_VALID + itemNo + " and " + itemCount + " items in Items tab");
                    return;
                } else {
                    binder.getErrorDisplay().setError(ValidationUtils.STATUS_VALID + itemNo + ConstantsUtils.IN_ITEMS_TAB);
                    return;
                }
            }
            LOGGER.debug("saveIFP with binder and ifpList");

            MessageBox.showPlain(Icon.QUESTION, commonutil.getHeaderMessage(), commonutil.getSaveMessage((String) binder.getField(ConstantsUtils.IFP_NAME).getValue()), new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        String success = StringUtils.EMPTY;
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                            success = updateButtonLogicYesMethod(ifpList, success);
                            if (ConstantsUtils.SUCCESS.equals(success)) {

                                final Notification notif = new Notification(binder.getField(ConstantsUtils.IFP_ID).getValue() + ", " + binder.getField(ConstantsUtils.IFP_NAME).getValue()
                                        + " has been successfully Saved", Notification.Type.HUMANIZED_MESSAGE);
                                notif.setPosition(Position.MIDDLE_CENTER);
                                notif.setStyleName(ConstantsUtils.MY_STYLE);
                                notif.show(Page.getCurrent());
                                notif.setDelayMsec(NumericConstants.THOUSAND);
                                sessionDTO.setMode(ConstantsUtils.EDIT);
                                getUI().getNavigator().navigateTo(IFPAddView.NAME);
                            } else if (ConstantsUtils.DUPLICATE.equals(success)) {
                                binder.getErrorDisplay().setError("Item Family Plan ID already exists.");

                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);


        } catch (FieldGroup.CommitException ex) {
            LOGGER.error(ex);
            boolean flag = false;
            int count = 0;
            try {
                count = ifpLogic.tempTableCount(null);
            } catch (SystemException ex1) {
                LOGGER.error(ex1);
            }
            StringBuilder errorMessage = new StringBuilder(ConstantsUtils.INFORMATION_MANDATORY_FIELDS + ConstantsUtils.BREAK);
            if (ex.getCause().getMessage().equals("Date format not recognized")) {
                return;
            }
            if (binder.getField(FieldNameUtils.ITEMFAMILYPLANID).getValue().toString().isEmpty()) {
                if (flag) {
                    errorMessage.append(ConstantsUtils.COMMA);
                }
                errorMessage.append(LabelUtils.ITEMFAMILYPLANID);
                flag = true;
            }
            if (binder.getField(FieldNameUtils.ITEMFAMILYPLANSTATUS).getValue() == null
                    || ((com.stpl.ifs.util.HelperDTO) binder.getField(FieldNameUtils.ITEMFAMILYPLANSTATUS).getValue()).getId() == 0) {
                if (flag) {
                    errorMessage.append(ConstantsUtils.COMMA);
                }
                errorMessage.append(LabelUtils.ITEMFAMILYPLANSTATUS);
                flag = true;
            }
            if (binder.getField(FieldNameUtils.ITEMFAMILYPLANSTARTDATE).getValue() == null) {
                if (flag) {
                    errorMessage.append(ConstantsUtils.COMMA);
                }
                errorMessage.append(LabelUtils.ITEMFAMILYPLANSTARTDATE);
                flag = true;
            }
            if (flag) {
                errorMessage.append(ConstantsUtils.BREAK);
            }
            if (count == ConstantsUtils.ZERO_INT) {
                errorMessage.append(ConstantsUtils.ADD_ATLEAST_ONE_ITEM_FOR_IFP);
                flag = true;
            }
            if (flag) {
                binder.getErrorDisplay().setError(errorMessage.toString());
                return;
            }
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            LOGGER.error(ex);
            LOGGER.error(errorMsg);
            final MessageBox msgBox = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
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
        } catch (PortalException portException) {
            LOGGER.error(portException);
            final MessageBox msgBox = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1008), new MessageBoxListener() {
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
        } catch (Exception exception) {
            LOGGER.error(exception);
            final MessageBox msgBox = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1008), new MessageBoxListener() {
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
    }

    public void setReadOnlyBinder() {
        binder.setReadOnly(true);
    }

    public void deleteButtonYesMethod() {
        try {

            final int ifpSystemId = Integer.valueOf(binder.getField(ConstantsUtils.IFP_SYSTEM_ID).getValue().toString().replace(ConstantsUtils.COMMA, StringUtils.EMPTY));
            LOGGER.debug("deleteIFPMasterById method with IFP_SYSTEM_ID " + ifpSystemId);
            ifpLogic.deleteNotesTabAttachment(ifpSystemId);
            final IfpModel master = ifpLogic.deleteIFPMasterById(ifpSystemId);

            final Notification notif = new Notification(master.getIfpId() + ", " + master.getIfpName() + " has been successfully deleted",
                    Notification.Type.HUMANIZED_MESSAGE);
            notif.setPosition(Position.MIDDLE_CENTER);
            notif.setStyleName(ConstantsUtils.MY_STYLE);
            notif.show(Page.getCurrent());
            LOGGER.debug("navigateTo ItemSearchView");
            AbstractSearchView.flag = true;
            getUI().getNavigator().navigateTo(AbstractSearchView.NAME);

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
        } catch (PortalException portException) {
            LOGGER.error(portException);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1012), new MessageBoxListener() {
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
        } catch (Exception exception) {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1012), new MessageBoxListener() {
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
            LOGGER.error(exception);

        }
    }

    public void selectedTabChangeListener() {
        try {
            if (isEditMode) {
                ifpItems.updateRecordsInTempTable();

            } else  {
                ifpItems.saveRecordsInTempTable();
            }
            if (selectedTabIndex.equals(ConstantsUtils.IFP_INFORMATION)) {

                ifpItems.configureInTabChange();

            } else if (selectedTabIndex.equals(ConstantsUtils.ITEM_ADDITION)) {
            } else if (selectedTabIndex.equals(ConstantsUtils.IFP_ITEMS)) {
                ifpItems.configureInTabChange();
            } else {
                notesTab.focusNewNote();
                notesTab.callJavaScriptForButton();

                notesTab.focusUploaderField();
                if (isViewMode) {
                    notesTab.removeAndDisablingComponents();
                }

            }
        } catch (Exception exception) {
            LOGGER.error(exception);
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

    public String saveButtonYesmethod(final List<IFPItemDTO> ifpList, String msg) {
        try {
            msg = ifpLogic.saveIFP(binder, ifpList, notesTab.getUploadedData(), notesTab.getAddedNotes(), notesTab.removeDetailsList());
            return msg;
        } catch (Exception exception) {
            LOGGER.error(exception);
            final MessageBox msgBox = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1003), new MessageBoxListener() {
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
        return null;
    }

    public String updateButtonLogicYesMethod(final List<IFPItemDTO> ifpList, String success) {
        try {
            success = ifpLogic.saveIFP(binder, ifpList, notesTab.getUploadedData(), notesTab.getAddedNotes(), notesTab.removeDetailsList());
            return success;
        } catch (Exception exception) {
            final MessageBox msgBox = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1008), new MessageBoxListener() {
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
        return null;
    }
    
    public void configInfoLayoutCopy() {
        ifpMaster.setItemFamilyplanId(StringUtils.EMPTY);
        ifpMaster.setItemFamilyplanName(StringUtils.EMPTY);
        ifpMaster.setItemFamilyplanNo(StringUtils.EMPTY);
    }
}
