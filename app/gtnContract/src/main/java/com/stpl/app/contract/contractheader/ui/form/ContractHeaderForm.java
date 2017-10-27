package com.stpl.app.contract.contractheader.ui.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.app.contract.common.InformationLayout;
import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.contractheader.dto.ContractAliasMasterDTO;
import com.stpl.app.contract.contractheader.dto.ContractMasterDTO;
import com.stpl.app.contract.contractheader.logic.ContractHeaderLogic;
import com.stpl.app.contract.contractheader.ui.view.ContractHeaderSearchView;
import com.stpl.app.contract.contractheader.ui.view.ContractHeaderView;
import com.stpl.app.contract.global.logic.NotesTabLogic;
import com.stpl.app.contract.ui.NotesTabForm;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.CHFunctionNameUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CHFieldNameUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.TabNameUtil;
import com.stpl.ifs.util.UISecurityUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

/**
 * Contract Header Form for add.
 *
 * @author
 */
public final class ContractHeaderForm extends CustomComponent {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ContractHeaderForm.class);
    @UiField("tabSheet")
    TabSheet tabsheet;
    @UiField("vLayout")
    VerticalLayout vLayout;
    @UiField("errorLB")
    ErrorLabel errorMsg;
    @UiField("saveBtn")
    Button btnSave;
    @UiField("backBtn")
    Button backButton;
    @UiField("resetBtn")
    Button resetButton;
    @UiField("deleteBtn")
    private Button deleteBtn;
    @UiField("excelBtn")
    private Button excelBtn;
    @UiField("buttonLayout")
    private HorizontalLayout buttonLayout;
    /**
     * Object for contract master dto.
     */
    private ContractMasterDTO contractMasterDTO;
    AliasResults aliasResults = new AliasResults();
    /**
     * Binder.
     */
    private CustomFieldGroup binder;
    /**
     * Object for contract header logic.
     */
    /**
     * Alias results bean container.
     */
    private BeanItemContainer<ContractAliasMasterDTO> aliasResultsBean;
    ContractHeader contractHeader;
    ContractInformation contractInformation;
    NotesTabForm notesTabForm;
    CommonUtil commonutil = new CommonUtil();
    private final String mode;
    SessionDTO sessionDTO;
    private final boolean isViewMode;
    private final boolean isEditMode;
    private final ContractHeaderLogic contractHL;
    private final NotesTabLogic notesLogic = new NotesTabLogic();
    
    int selectedTabIndex = 0;
    /**
     * Gets the contract master dto.
     *
     * @return the contract master dto
     *
     */
    ContractMasterDTO masterDto = new ContractMasterDTO();
    private final List<ContractAliasMasterDTO> aliasList = new ArrayList<>();

    public ContractMasterDTO getContractMasterDTO() {
        return contractMasterDTO;
    }

    /**
     * Sets the contract master dto.
     *
     * @param contractMasterDTO the contract master dto
     */
    public void setContractMasterDTO(final ContractMasterDTO contractMasterDTO) {
        this.contractMasterDTO = contractMasterDTO;
    }

    /**
     * Gets the alias results bean.
     *
     * @return the alias results bean
     */
    public BeanItemContainer<ContractAliasMasterDTO> getAliasResultsBean() {
        return aliasResultsBean;
    }

    /**
     * Sets the alias results bean.
     *
     * @param aliasResultsBean the alias results bean
     */
    public void setAliasResultsBean(final BeanItemContainer<ContractAliasMasterDTO> aliasResultsBean) {
        this.aliasResultsBean = aliasResultsBean;
    }

    /**
     * Sets the binder.
     *
     * @param binder the binder
     */
    public void setBinder(final CustomFieldGroup binder) {
        this.binder = binder;
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
     * Parameterized Constructor for initializing
     * ContractMasterDTO,CustomFieldGroup and BeanItemContainer.
     *
     * @param contractMasterDTO the contract master dto
     * @param binder the binder
     * @param aliasResultsBean the alias results bean
     */
    public ContractHeaderForm(final ContractMasterDTO contractMasterDTO, final CustomFieldGroup binder, final BeanItemContainer<ContractAliasMasterDTO> aliasResultsBean, final String mode
                        ,final SessionDTO sessionDTO) throws SystemException, PortalException {
        super();
        LOGGER.debug("Entering AddForm constructor ");
        this.contractMasterDTO = contractMasterDTO;
        this.aliasResultsBean = aliasResultsBean;
        this.binder = binder;
        this.mode = mode;
        this.sessionDTO = sessionDTO;
        contractHL = new ContractHeaderLogic(this.sessionDTO);
        this.isViewMode = Constants.VIEW.equals(mode);
        this.isEditMode = Constants.EDIT.equals(mode);
        init();
        LOGGER.debug(" AddForm constructor ends");
    }

    /**
     * Adds the entire form to the UI and make initial configuration.
     */
    private void init() throws SystemException, PortalException {
        LOGGER.debug("Entering init method ");
        addToContent();
        addToTabbar();

        configureFields();
        configureBinder();
        LOGGER.debug(" init method ends");
    }

    /**
     * Adds the components in the page to layout.
     */
    private void addToContent() {
        LOGGER.debug("Entering addToContent method ");
        this.setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarative-ui/tab-sheet-form.xml"), this));
        vLayout.addComponent(new InformationLayout(Constants.CONTRACT, String.valueOf(contractMasterDTO.getContractId()), String.valueOf(contractMasterDTO.getContractNo()), String.valueOf(contractMasterDTO.getContractName())));
        LOGGER.debug(" addToContent method ends");
    }

    /**
     * Binds the CustomFieldGroup to ContractMasterDTO.
     *
     * @return CustomFieldGroup.
     */
    private CustomFieldGroup configureBinder() {
        LOGGER.debug("Entering getBinder method ");
        binder.bindMemberFields(this);
        binder.setBuffered(true);
        binder.setErrorDisplay(errorMsg);
        LOGGER.debug(" getBinder method ends");
        return binder;
    }

    /**
     * Configures the field in the Layout.
     *
     * @throws Exception
     * @throws PortalException
     */
    private void configureFields() throws PortalException, SystemException {
        if (isViewMode) {
            btnSave.setVisible(false);
            resetButton.setVisible(false);
            deleteBtn.setVisible(false);

        }
        if (isEditMode) {
            deleteBtn.setVisible(true);
            deleteBtn.setEnabled(true);
            btnSave.setCaption("Update");
        }

        final StplSecurity stplSecurity = new StplSecurity();
        LOGGER.debug("Enters configureFields method");

        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.CONTRACT_HEADER+Constants.COMMA+"Contract Header");
        if (functionHM.get(CHFunctionNameUtils.SAVE) != null && !((AppPermission) functionHM.get(CHFunctionNameUtils.SAVE)).isFunctionFlag()) {
            btnSave.setVisible(false);
           
        }else{
            editButton();
        }
        excelBtn.setVisible(false);
        if (functionHM.get(CHFunctionNameUtils.RESET_BUTTON) != null && !((AppPermission) functionHM.get(CHFunctionNameUtils.RESET_BUTTON)).isFunctionFlag()) {
             resetButton.setVisible(false);
        }else{
           resetButton();
            
        }
        if (functionHM.get(CHFunctionNameUtils.DELETE) != null && !((AppPermission) functionHM.get(CHFunctionNameUtils.DELETE)).isFunctionFlag()) {
               deleteBtn.setVisible(false); 
        }else{
                  
            deleteButton();
        }
        backButton();


        LOGGER.debug(" init method ends");

    }

    /**
     * Adds the Tabs to the FormLayout.
     *
     * @return TabSheet.
     * @throws com.stpl.portal.kernel.exception.PortalException
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    public TabSheet addToTabbar() throws PortalException, SystemException {

        LOGGER.debug("Entering addToTabbar method ");
        tabsheet.setReadOnly(true);
        tabsheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabsheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        deleteBtn.setVisible(false);
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));

        final Map<String, AppPermission> tabContractHeaderHM = stplSecurity.getBusinessTabPermission(userId, UISecurityUtil.CONTRACT_HEADER);
            final VerticalLayout tab1 = new VerticalLayout();
            tab1.setSizeFull();
            tab1.setMargin(true);
            contractHeader = new ContractHeader(binder, contractMasterDTO,mode);
            tab1.addComponent(contractHeader);
            tabsheet.addTab(tab1, TabNameUtil.ContractHeader);
        
            final VerticalLayout tab2 = new VerticalLayout();
            tab2.setSizeFull();
            tab2.setMargin(true);
            contractInformation = new ContractInformation(binder,mode);
            tab2.addComponent(contractInformation);
            tabsheet.addTab(tab2, TabNameUtil.ContractInformation);
        if (tabContractHeaderHM.get(TabNameUtil.ContractInformation) != null
                && !((AppPermission) tabContractHeaderHM.get(TabNameUtil.ContractInformation))
                .isTabFlag()) {
            tabsheet.getTab(contractInformation).setVisible(false);
        }
            final VerticalLayout tab3 = new VerticalLayout();
            tab3.setMargin(true);
            aliasResultsBean.addAll(contractMasterDTO.getContracAliasMasterList());
            aliasResults = new AliasResults(binder, aliasResultsBean, mode);
            tab3.addComponent(aliasResults);
            tabsheet.addTab(tab3, TabNameUtil.Alias);
        if (tabContractHeaderHM.get(TabNameUtil.Alias) != null
                && !((AppPermission) tabContractHeaderHM.get(TabNameUtil.Alias))
                .isTabFlag()) {
            tabsheet.getTab(aliasResults).setVisible(false);
        }
        notesTabForm = new NotesTabForm(this.binder, "Contract Header", "CONTRACT_MASTER", "contractSystemId", mode);
        final VerticalLayout tab4 = new VerticalLayout();
        tab4.setMargin(true);
        tab4.setSizeFull();
        tab4.addComponent(notesTabForm);
        tabsheet.addTab(tab4, "Notes");
        notesTabForm.refreshTable();

        tabsheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            /**
             * selectedTabChange method for tabe change event
             */
            public void selectedTabChange(final TabSheet.SelectedTabChangeEvent event) {
                try {
                    Component component = event.getTabSheet().getSelectedTab();

                    selectedTabIndex = event.getTabSheet().getTabPosition(event.getTabSheet().getTab(component));
                    if (selectedTabIndex ==NumericConstants.THREE ) {
                        notesTabForm.callJavaScriptForButton();
                        notesTabForm.focusNewNote();
                        notesTabForm.setUploaderValue(StringUtils.EMPTY);
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });

        LOGGER.debug(" addToTabbar method returns tabsheet and ends ");
        return tabsheet;
    }

    /**
     * Configures and adds the button this layout.
     */
    private void backButton() {
        LOGGER.debug("Entering backButton method");

        backButton.setWidth("75");
        backButton.addClickListener(new ClickListener() {
            /**
             * Invoked when the button is clicked.
             *
             * @param event - ClickEvent
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("entering backButton listener ");

                if (isViewMode) {
                    ContractHeaderSearchView.flag=false;
                    getUI().getNavigator().navigateTo(ContractHeaderSearchView.NAME);
                } else {
                    MessageBox.showPlain(Icon.QUESTION, commonutil.getHeaderMessage(), commonutil.getBackMessage(), new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         *
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals(Constants.YES)) {
                                try {
                                    ContractHeaderSearchView.flag=false;
                                    getUI().getNavigator().navigateTo(ContractHeaderSearchView.NAME);
                                } catch (Exception exception) {
                                    LOGGER.error(exception);

                                } finally {
                                    binder = null;
                                    aliasResultsBean = null;
                                    tabsheet = null;
                                    notesTabForm = null;
                                }
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);
                    LOGGER.debug(" backButton listener ends ");

                }
            }
        });
        backButton.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             *
             * @param event - ErrorEvent
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                return;
            }
        });
        LOGGER.debug(" backButton method is ended");
    }

    /**
     * Configures and adds the button this layout.
     */
    private void editButton() {
        LOGGER.debug(" entering EditButton method");
        if (!isViewMode) {
            btnSave.addClickListener(new ClickListener() {
                /**
                 * Invoked when the button is clicked.
                 *
                 * @param event - ClickEvent
                 */
                @SuppressWarnings("PMD")
                public void buttonClick(final ClickEvent event) {
                    try {
                        if (isEditMode) {
                            updateMethod();
                        } else {
                            saveMethod();
                        }
                    } catch (PortalException ex) {
                        LOGGER.error(ex);
                    } catch (SystemException ex) {
                        LOGGER.error(ex);
                    }

                }
            });
        }
        LOGGER.debug(" EditButton method ends");
    }

    /**
     * the delete button logic
     */
    private void deleteButton() {
        LOGGER.debug("Enters DeleteButton method ");
        deleteBtn.setWidth("75");
        deleteBtn.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             *
             * @param event - ErrorEvent
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                return;
            }
        });
        deleteBtn.addClickListener(new ClickListener() {
            /**
             * Invoked when the button is clicked.
             *
             * @param event - ClickEvent
             */
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.debug("btnDelete listener is called");
                    final int systemId = Integer.valueOf(binder.getField(Constants.CONTRACT_SYSTEM_ID).getValue().toString().replace(Constants.COMMA, StringUtils.EMPTY));
                    if (!contractHL.getProcessStatus(systemId)) {
                        MessageBox.showPlain(Icon.QUESTION,commonutil.getHeaderMessage(),commonutil.getDeleteMessage(String.valueOf(binder.getField(Constants.CONTRACT_NAME).getValue())), new MessageBoxListener() {
                            /**
                             * Invoked when yes is clicked.
                             *
                             * @param event - ClickEvent
                             */
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(Constants.YES)) {
                                    try {

                                        /**
                                         * this condition is commented for
                                         * future use-sooriya
                                         */
                                        contractHL.deleteContractMasterById(systemId);
                                        final Notification notif = new Notification(commonutil.getDeletedSuccessfulMessage(String.valueOf(binder.getField(Constants.CONTRACT_ID).getValue()),String.valueOf(binder.getField(Constants.CONTRACT_NAME).getValue())), Notification.Type.HUMANIZED_MESSAGE);
                                        notif.setPosition(Position.MIDDLE_CENTER);
                                        notif.setStyleName(Constants.MYSTYLE);
                                        notif.show(Page.getCurrent());
                                        ContractHeaderSearchView.flag=true;
                                        getUI().getNavigator().navigateTo(ContractHeaderSearchView.NAME);
                                    }
                                    catch (Exception e) {
                                        LOGGER.error(e);
                                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_2004));
                                    } finally {
                                        binder = null;
                                        aliasResultsBean = null;
                                        tabsheet = null;
                                        notesTabForm = null;
                                    }
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
                    } else {
                        AbstractNotificationUtils.getErrorNotification("Delete Error", "Selected Contract Header cannot be deleted as it is already used in a contract");
                    }
                    LOGGER.debug("btnDelete listener ends");
                } catch (Exception e) {
                    LOGGER.error(e);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_2004));
                }
            }
        });
        LOGGER.debug(" DeleteButton method ends");
    }

    private void resetButton() {
        try {
            resetButton.setWidth("75");
            resetButton.addClickListener(new ClickListener() {
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
                            if (buttonId.name().equals(Constants.YES)) {
                                if (isEditMode) {
                                    resetLogicForEditMode();
                                } else {
                                    resetLogicForAddMode();
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

    public void updateMethod() throws PortalException, SystemException {
        try {
            LOGGER.debug("Entering btnUpdate listener");
            binder.getFields();
            binder.commit();
            
            boolean flag = false;
            StringBuilder errorMessage = new StringBuilder("Information for Mandatory fields need to be provided on Contract Header tab: ");
             errorMessage.append(System.getProperty("line.separator"));
           if (StringUtils.isBlank(String.valueOf(binder.getField(Constants.CONTRACT_ID).getValue()))) {
                 if (flag) {
                            errorMessage.append(Constants.COMMA);
                        }
                        errorMessage.append(Constants.CONTRACT_ID1);
                        flag = true;
                
            } 
           /**
            * cel-211
            */
           if (StringUtils.isBlank(String.valueOf(binder.getField(Constants.TRADING_PARTNER_NAME).getValue()))) {
                 if (flag) {
                            errorMessage.append(Constants.COMMA);
                        }
                        errorMessage.append(Constants.TRADING_PARTNER_NAME);
                        flag = true;
                
            } 

            if (StringUtils.isBlank(String.valueOf(binder.getField(Constants.CONTRACT_NO).getValue()))) {
                 if (flag) {
                            errorMessage.append(Constants.COMMA);
                        }
                        errorMessage.append(Constants.CONTRACT_NO1);
                        flag = true;
            }  
             if (StringUtils.isBlank(String.valueOf(binder.getField(Constants.CONTRACT_NAME).getValue()))) {
                 if (flag) {
                            errorMessage.append(Constants.COMMA);
                        }
                        errorMessage.append(Constants.CONTRACT_NAME1);
                        flag = true;
            } 
            if (StringUtils.isBlank(String.valueOf(binder.getField(Constants.CONTRACT_NAME).getValue()))) {
                 if (flag) {
                            errorMessage.append(Constants.COMMA);
                        }
                        errorMessage.append(Constants.CONTRACT_NAME1);
                        flag = true;
            }  
             
            if (binder.getField(Constants.CONTRACT_TYPE).getValue() == null || ((com.stpl.ifs.util.HelperDTO) binder.getField(Constants.CONTRACT_TYPE).getValue()).getId() == 0) {
               
                if (flag) {
                            errorMessage.append(Constants.COMMA);
                        }
                        errorMessage.append(Constants.CONTRACT_TYPE_HEADER);
                        flag = true;
                
            }
            if (binder.getField(Constants.CONTRACT_STATUS).getValue() == null || ((com.stpl.ifs.util.HelperDTO) binder.getField(Constants.CONTRACT_STATUS).getValue()).getId() == 0) {
                if (flag) {
                            errorMessage.append(Constants.COMMA);
                        }
                        errorMessage.append(Constants.CONTRACT_STATUS1);
                        flag = true;
            }
            if (String.valueOf(binder.getField(Constants.START_DATE).getValue()).equals(Constants.NULL)) {
               if (flag) {
                            errorMessage.append(Constants.COMMA);
                        }
                        errorMessage.append(Constants.START_DATE_SP);
                        flag = true;
            }
            if (String.valueOf(binder.getField(Constants.RENEGOTIATION_START_DATE).getValue()).equals(Constants.NULL)
                    && !(String.valueOf(binder.getField(Constants.RENEGOTIATION_END_DATE).getValue()).equals(Constants.NULL) || String.valueOf(binder.getField(Constants.RENEGOTIATION_END_DATE).getValue()).equals(StringUtils.EMPTY))) {
                if (flag) {
                            errorMessage.append(Constants.COMMA);
                        }
                        errorMessage.append("Re-Negotiation Start Date");
                        flag = true;
            } 
            if (String.valueOf(binder.getField(Constants.PRICE_PROTECTION_START_DATE).getValue()).equals(Constants.NULL)
                    && !(String.valueOf(binder.getField(Constants.PRICE_PROTECTION_END_DATE).getValue()).equals(Constants.NULL) || String.valueOf(binder.getField(Constants.PRICE_PROTECTION_END_DATE).getValue()).equals(StringUtils.EMPTY))) {
               if (flag) {
                            errorMessage.append(Constants.COMMA);
                        }
                        errorMessage.append("Price Protection Start Date");
                        flag = true;
                
            } 
            
             if (flag) {
                         binder.getErrorDisplay().setError(errorMessage.toString());
                        return;
                    }  else{
            
                for (int i = 0; i < aliasResultsBean.size(); i++) {
                    final ContractAliasMasterDTO iden = (ContractAliasMasterDTO) aliasResultsBean.getIdByIndex(i);
                    aliasList.add(iden);
                }
                notesLogic.deleteUploadedFile(notesTabForm.removeDetailsList());
                MessageBox.showPlain(Icon.QUESTION, commonutil.getHeaderMessage(), commonutil.getSaveMessage(String.valueOf(binder.getField(Constants.CONTRACT_NAME).getValue())), new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(Constants.YES)) {
                            try {
                                final String msg = contractHL.saveContractMaster(binder, aliasList, notesTabForm.getUploadedData(), notesTabForm.getAddedNotes());
                                if (Constants.SUCCESS.equals(msg)) {
                                    final Notification notif = new Notification(commonutil.getSavedSuccessfulMessage(String.valueOf(binder.getField(Constants.CONTRACT_ID).getValue()),String.valueOf(binder.getField(Constants.CONTRACT_NAME).getValue())), Notification.Type.HUMANIZED_MESSAGE);
                                    notif.setDelayMsec(Constants.NOTIF_DEPALY_MSEC);
                                    notif.setPosition(Position.MIDDLE_CENTER);
                                    notif.setStyleName(Constants.MYSTYLE);
                                    notif.setDelayMsec(NumericConstants.TWO_THOUSAND);
                                    notif.show(Page.getCurrent());
                                    getUI().getNavigator().navigateTo(ContractHeaderView.NAME);
                                } else {
                                    binder.getErrorDisplay().setError("Please enter different Contract ID or No since the Contract ID or No already exists");
                                }
                            } catch (SystemException e) {
                                 LOGGER.debug(e);
                                final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
                                LOGGER.error(errorMsg);
                                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                            } catch (PortalException e) {
                                LOGGER.error(e);
                                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_2003));
                            } finally {
                                binder = null;
                                aliasResultsBean = null;
                                tabsheet = null;
                                notesTabForm = null;
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

                LOGGER.debug(" btnUpdate listener ends");
            }
        } catch (CommitException ex) {
             LOGGER.debug(ex);
            if (ex.getCause().getMessage()== null || ex.getCause().getMessage() == Constants.NULL) {
                            binder.getErrorDisplay().clearError();
                            binder.getErrorDisplay().setError("Please enter valid value.");
                            return;
                        }
        }
    }

    public void saveMethod() throws PortalException, SystemException {
         boolean flag = false;
        try {
            LOGGER.debug("enters btnSave click listener ");
            binder.getErrorDisplay().clearError();
            if (binder.getFields() != null && !binder.getFields().isEmpty()) {
                binder.getFields();
                binder.commit();
            }


 String errorMessage = new String("Information for Mandatory fields need to be provided on Contract Header tab: ")+(System.getProperty("line.separator"));
            if (StringUtils.isBlank(String.valueOf(binder.getField(Constants.CONTRACT_ID).getValue()))) {
                 if (flag) {
                            errorMessage = errorMessage+Constants.COMMA;
                        }
                        errorMessage = errorMessage + (Constants.CONTRACT_ID1);
                        flag = true;
                
            } 
            if (StringUtils.isBlank(String.valueOf(binder.getField(Constants.CONTRACT_NO).getValue()))) {
                 if (flag) {
                             errorMessage = errorMessage+Constants.COMMA;
                        }
                        errorMessage = errorMessage +(Constants.CONTRACT_NO1);
                        flag = true;
            }  
             if (StringUtils.isBlank(String.valueOf(binder.getField(Constants.CONTRACT_NAME).getValue()))) {
                 if (flag) {
                             errorMessage = errorMessage+Constants.COMMA;
                        }
                        errorMessage = errorMessage +(Constants.CONTRACT_NAME1);
                        flag = true;
            } 
            if (binder.getField(Constants.CONTRACT_TYPE).getValue() == null || ((com.stpl.ifs.util.HelperDTO) binder.getField(Constants.CONTRACT_TYPE).getValue()).getId() == 0) {
               
                if (flag) {
                              errorMessage = errorMessage+Constants.COMMA;
                        }
                         errorMessage = errorMessage +(Constants.CONTRACT_TYPE_HEADER);
                        flag = true;
                
            }
          
            
            if (StringUtils.isBlank(String.valueOf(binder.getField(Constants.TRADING_PARTNER_NAME).getValue()))) {
               
                if (flag) {
                              errorMessage = errorMessage+Constants.COMMA;
                        }
                         errorMessage = errorMessage +(Constants.TRADING_PARTNER_NAME);
                        flag = true;
                
            }
            if (binder.getField(Constants.CONTRACT_STATUS).getValue() == null || ((com.stpl.ifs.util.HelperDTO) binder.getField(Constants.CONTRACT_STATUS).getValue()).getId() == 0) {
                if (flag) {
                             errorMessage = errorMessage+Constants.COMMA;
                        }
                         errorMessage = errorMessage +(Constants.CONTRACT_STATUS1);
                        flag = true;
            }
            if (String.valueOf(binder.getField(Constants.START_DATE).getValue()).equals(Constants.NULL)) {
               if (flag) {
                              errorMessage = errorMessage+Constants.COMMA;
                        }
                         errorMessage = errorMessage +(Constants.START_DATE_SP);
                        flag = true;
            }
            if (String.valueOf(binder.getField(Constants.RENEGOTIATION_START_DATE).getValue()).equals(Constants.NULL)
                    && !(String.valueOf(binder.getField(Constants.RENEGOTIATION_END_DATE).getValue()).equals(Constants.NULL) || String.valueOf(binder.getField(Constants.RENEGOTIATION_END_DATE).getValue()).equals(StringUtils.EMPTY))) {
                if (flag) {
                              errorMessage = errorMessage+Constants.COMMA;
                        }
                        errorMessage = errorMessage +("Re-Negotiation Start Date");
                        flag = true;
            } 
            if (String.valueOf(binder.getField(Constants.PRICE_PROTECTION_START_DATE).getValue()).equals(Constants.NULL)
                    && !(String.valueOf(binder.getField(Constants.PRICE_PROTECTION_END_DATE).getValue()).equals(Constants.NULL) || String.valueOf(binder.getField(Constants.PRICE_PROTECTION_END_DATE).getValue()).equals(StringUtils.EMPTY))) {
               if (flag) {
                            errorMessage = errorMessage+Constants.COMMA;
                        }
                         errorMessage = errorMessage +("Price Protection Start Date");
                        flag = true;
                
            } 
            
            
             if (flag) {
                         binder.getErrorDisplay().setError(errorMessage.toString());
                        return;
                    }  else{
            
                for (int i = 0; i < aliasResultsBean.size(); i++) {
                    final ContractAliasMasterDTO iden = (ContractAliasMasterDTO) aliasResultsBean.getIdByIndex(i);
                    aliasList.add(iden);
                }
                notesLogic.deleteUploadedFile(notesTabForm.removeDetailsList());
                MessageBox.showPlain(Icon.QUESTION, commonutil.getHeaderMessage(),commonutil.getSaveMessage(String.valueOf(binder.getField(Constants.CONTRACT_NAME).getValue())), new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(Constants.YES)) {
                            try {
                                final String msg = contractHL.saveContractMaster(binder, aliasList, notesTabForm.getUploadedData(), notesTabForm.getAddedNotes());
                                if (Constants.SUCCESS.equals(msg)) {
                                    final Notification notif = new Notification(commonutil.getSavedSuccessfulMessage(String.valueOf(binder.getField(Constants.CONTRACT_ID).getValue()),String.valueOf(binder.getField(Constants.CONTRACT_NAME).getValue())), Notification.Type.HUMANIZED_MESSAGE);
                                    notif.setDelayMsec(Constants.NOTIF_DEPALY_MSEC);
                                    notif.setPosition(Position.MIDDLE_CENTER);
                                    notif.setStyleName(Constants.MYSTYLE);
                                    notif.setDelayMsec(NumericConstants.TWO_THOUSAND);
                                    notif.show(Page.getCurrent());
                                    sessionDTO.setMode(Constants.EDIT);
                                    getUI().getNavigator().navigateTo(ContractHeaderView.NAME);

                                } else if (Constants.DUPLICATE_NO.equals(msg)) {
                                    binder.getErrorDisplay().setError("Please enter different Contract No since the Contract No already exists");
                                } else {
                                    binder.getErrorDisplay().setError("Please enter different Contract ID or No since the Contract ID or No already exists");
                                }
                            } catch (PortalException ex) {
                                LOGGER.error(ex);
                                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_2002));
                            } catch (SystemException ex) {
                                final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                                LOGGER.error(errorMsg);
                                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                            } finally {
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

            }
           
        } catch (CommitException ex) {
             LOGGER.debug(ex);
             if (ex.getCause().getMessage()== null || ex.getCause().getMessage() == Constants.NULL) {
                            binder.getErrorDisplay().clearError();
                            binder.getErrorDisplay().setError("Please enter valid value.");
                            return;
                        }
        }
    }

    private void resetLogicForAddMode() {
        try {
            ContractMasterDTO masterDto = new ContractMasterDTO();
            LOGGER.debug("Entering inside  reset  method from ADD ");

            if (selectedTabIndex == 0) {
                TextField id = (TextField) binder.getField(CHFieldNameUtils.contractId);
                id.setValue(StringUtils.EMPTY);
                TextField no = (TextField) binder.getField(CHFieldNameUtils.contractNo);
                no.setValue(StringUtils.EMPTY);
                TextField name = (TextField) binder.getField(CHFieldNameUtils.contractName);
                name.setValue(StringUtils.EMPTY);
                ComboBox type = (ComboBox) binder.getField(CHFieldNameUtils.contractType);
                type.select(null);
                ComboBox status = (ComboBox) binder.getField(CHFieldNameUtils.contractStatus);
                status.setValue(masterDto.getContractStatus());
                ComboBox docType = (ComboBox) binder.getField(CHFieldNameUtils.docType);
                docType.setValue(null);
                PopupDateField startDateField = (PopupDateField) binder.getField(CHFieldNameUtils.startDate);
                startDateField.setValue(null);
                PopupDateField endDateField = (PopupDateField) binder.getField(CHFieldNameUtils.endDate);
                endDateField.setValue(null);
                ComboBox docClass = (ComboBox) binder.getField("docClass");
                docClass.setValue(null);

                TextField companyNameValue = (TextField) binder.getField("companyLabel");
                companyNameValue.setReadOnly(false);
                companyNameValue.setValue(StringUtils.EMPTY);
                TextField companyName = (TextField) binder.getField(Constants.COMPANY_NAME);
                companyName.setValue("0");

                ComboBox tradeClass = (ComboBox) binder.getField(CHFieldNameUtils.tradeClass);
                tradeClass.setValue(null);

                TextField tradingPartner = (TextField) binder.getField(CHFieldNameUtils.tradingPartnerName);
                tradingPartner.setReadOnly(false);
                tradingPartner.setValue(StringUtils.EMPTY);

                TextField tradingPartnerSystemId = (TextField) binder.getField(Constants.TRADING_PARTNER_SYS_ID);
                tradingPartnerSystemId.setValue("0");

                PopupDateField reDate = (PopupDateField) binder.getField(CHFieldNameUtils.renegotiationStartDate);
                reDate.setValue(null);
                PopupDateField reEndDate = (PopupDateField) binder.getField(CHFieldNameUtils.renegotiationEndDate);
                reEndDate.setValue(null);

                PopupDateField ppStartDate = (PopupDateField) binder.getField(CHFieldNameUtils.priceprotectionStartDate);
                ppStartDate.setValue(null);

                PopupDateField ppEndDate = (PopupDateField) binder.getField(CHFieldNameUtils.priceprotectionEndDate);
                ppEndDate.setValue(null);

                ComboBox manufctr = (ComboBox) binder.getField(CHFieldNameUtils.companySystemId);
                manufctr.select(new HelperDTO(0, Constants.SELECT_ONE));
            }
            if (selectedTabIndex == 1) {
                TextField id = (TextField) binder.getField(CHFieldNameUtils.insideOwner);
                id.setValue(StringUtils.EMPTY);
                TextField no = (TextField) binder.getField(CHFieldNameUtils.insidePhone);
                no.setValue(StringUtils.EMPTY);
                TextField name = (TextField) binder.getField(CHFieldNameUtils.insideAuthor);
                name.setValue(StringUtils.EMPTY);
                TextField type = (TextField) binder.getField(CHFieldNameUtils.insideAdditional);
                type.setValue(StringUtils.EMPTY);
                TextField status = (TextField) binder.getField(CHFieldNameUtils.insideAdditionalName);
                status.setValue(StringUtils.EMPTY);
                TextField docType = (TextField) binder.getField(CHFieldNameUtils.insideAdditionalPhone);
                docType.setValue(StringUtils.EMPTY);

                TextField startDateField = (TextField) binder.getField(CHFieldNameUtils.outsideOwner);
                startDateField.setValue(StringUtils.EMPTY);
                TextField endDateField = (TextField) binder.getField(CHFieldNameUtils.outsidePhone);
                endDateField.setValue(StringUtils.EMPTY);
                TextField docClass = (TextField) binder.getField(CHFieldNameUtils.outsideAuthor);
                docClass.setValue(StringUtils.EMPTY);

                TextField outsideAdditional = (TextField) binder.getField(CHFieldNameUtils.outsideAdditional);
                outsideAdditional.setValue(StringUtils.EMPTY);
                TextField outsideAdditionalName = (TextField) binder.getField(CHFieldNameUtils.outsideAdditionalName);
                outsideAdditionalName.setValue(StringUtils.EMPTY);
                TextField outsideAdditionalPhone = (TextField) binder.getField(CHFieldNameUtils.outsideAdditionalPhone);
                outsideAdditionalPhone.setValue(StringUtils.EMPTY);

                TextField noticeDays = (TextField) binder.getField(CHFieldNameUtils.advanceNoticeDays);
                noticeDays.setValue(StringUtils.EMPTY);
                TextField acInfo = (TextField) binder.getField(CHFieldNameUtils.affiliatedContractInfo);
                acInfo.setValue(StringUtils.EMPTY);
                TextField shipTerms = (TextField) binder.getField(CHFieldNameUtils.shippingTerms);
                shipTerms.setValue(StringUtils.EMPTY);
                PopupDateField pStartDate = (PopupDateField) binder.getField("proposedStartDate");
                pStartDate.setValue(null);

                PopupDateField pEndDate = (PopupDateField) binder.getField("proposedEndDate");
                pEndDate.setValue(null);

                PopupDateField oStartDate = (PopupDateField) binder.getField(CHFieldNameUtils.originalStartDate);
                oStartDate.setValue(null);

                PopupDateField oEndDate = (PopupDateField) binder.getField(CHFieldNameUtils.originalEndDate);
                oEndDate.setValue(null);

                ComboBox awarStatus = (ComboBox) binder.getField(CHFieldNameUtils.awardStatus);
                awarStatus.setValue(null);
                PopupDateField lastUpdateDate = (PopupDateField) binder.getField(CHFieldNameUtils.lastUpdatedDate);
                lastUpdateDate.setValue(null);

                TextField peClause = (TextField) binder.getField(CHFieldNameUtils.priceEscalationClause);
                peClause.setValue(StringUtils.EMPTY);
                TextField lowPrice = (TextField) binder.getField(CHFieldNameUtils.exemptFromLowPrice);
                lowPrice.setValue(StringUtils.EMPTY);
                ComboBox indicator = (ComboBox) binder.getField(CHFieldNameUtils.priceResetIndicator);
                indicator.setValue(null);

                TextField cancellationClause = (TextField) binder.getField(CHFieldNameUtils.cancellationClause);
                cancellationClause.setValue(StringUtils.EMPTY);
                TextField nation = (TextField) binder.getField(CHFieldNameUtils.mostFavoredNation);
                nation.setValue(StringUtils.EMPTY);
                TextField category = (TextField) binder.getField(CHFieldNameUtils.category);
                category.setValue(StringUtils.EMPTY);

                TextField currency = (TextField) binder.getField(CHFieldNameUtils.currency);
                currency.setValue(StringUtils.EMPTY);
                TextField minimumOrder = (TextField) binder.getField(CHFieldNameUtils.minimumOrder);
                minimumOrder.setValue(StringUtils.EMPTY);
                ComboBox paymentTerms = (ComboBox) binder.getField(CHFieldNameUtils.paymentTerms);
                paymentTerms.setValue(null);
            }
            if (selectedTabIndex == NumericConstants.TWO) {
                aliasResults.reset();
            }
            if (selectedTabIndex == NumericConstants.THREE) {
                notesTabForm.resetAddMode();
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void resetLogicForEditMode() {
        try {
            LOGGER.debug("Entering inside  reset  method from ADD ");
            final int systemId = (Integer) sessionDTO.getSystemId();
            LOGGER.debug("ContractsystemId=" + systemId);
            LOGGER.debug("getContractMasterById(contractSystemId" + systemId + ")");

            masterDto = contractHL.getContractMasterById(Integer.valueOf(systemId));
            if (selectedTabIndex == 0) {
                TextField id = (TextField) binder.getField(CHFieldNameUtils.contractId);
                id.setValue(masterDto.getContractId());
                TextField no = (TextField) binder.getField(CHFieldNameUtils.contractNo);
                no.setValue(masterDto.getContractNo());
                TextField name = (TextField) binder.getField(CHFieldNameUtils.contractName);
                name.setValue(masterDto.getContractName());
                ComboBox type = (ComboBox) binder.getField(CHFieldNameUtils.contractType);
                type.setValue(masterDto.getContractType());
                ComboBox status = (ComboBox) binder.getField(CHFieldNameUtils.contractStatus);
                status.setValue(masterDto.getContractStatus());
                ComboBox docType = (ComboBox) binder.getField(CHFieldNameUtils.docType);
                docType.setValue(masterDto.getDocType());
                PopupDateField startDateField = (PopupDateField) binder.getField(CHFieldNameUtils.startDate);
                startDateField.setValue(masterDto.getStartDate());
                PopupDateField endDateField = (PopupDateField) binder.getField(CHFieldNameUtils.endDate);
                endDateField.setValue(masterDto.getEndDate());
                ComboBox docClass = (ComboBox) binder.getField("docClass");
                docClass.setValue(masterDto.getDocClass());

                TextField companyNameValue = (TextField) binder.getField("companyLabel");
                if (StringUtils.isNotBlank(masterDto.getCompanyLabel())) {
                    companyNameValue.setReadOnly(false);
                    companyNameValue.setValue(masterDto.getCompanyLabel());
                    companyNameValue.setReadOnly(true);
                } else {
                    companyNameValue.setReadOnly(false);
                    companyNameValue.setValue(StringUtils.EMPTY);
                    TextField companyName = (TextField) binder.getField(Constants.COMPANY_NAME);
                    companyName.setValue("0");
                }

                ComboBox tradeClass = (ComboBox) binder.getField(CHFieldNameUtils.tradeClass);
                tradeClass.setValue(String.valueOf(masterDto.getTradeClass()));
                TextField tradingPartner = (TextField) binder.getField(CHFieldNameUtils.tradingPartnerName);
                if (StringUtils.isNotBlank(masterDto.getTradingPartnerName())) {
                    tradingPartner.setReadOnly(false);
                    tradingPartner.setValue(String.valueOf(masterDto.getTradingPartnerName()));
                    tradingPartner.setReadOnly(true);
                } else {
                    tradingPartner.setReadOnly(false);
                    tradingPartner.setValue(StringUtils.EMPTY);
                    TextField tradingPartnerSystemId = (TextField) binder.getField(Constants.TRADING_PARTNER_SYS_ID);
                    tradingPartnerSystemId.setValue("0");
                }

                PopupDateField reDate = (PopupDateField) binder.getField(CHFieldNameUtils.renegotiationStartDate);
                reDate.setValue(masterDto.getRenegotiationStartDate());
                PopupDateField reEndDate = (PopupDateField) binder.getField(CHFieldNameUtils.renegotiationEndDate);
                reEndDate.setValue(masterDto.getRenegotiationEndDate());

                PopupDateField ppStartDate = (PopupDateField) binder.getField(CHFieldNameUtils.priceprotectionStartDate);
                ppStartDate.setValue(masterDto.getPriceprotectionStartDate());

                PopupDateField ppEndDate = (PopupDateField) binder.getField(CHFieldNameUtils.priceprotectionEndDate);
                ppEndDate.setValue(masterDto.getPriceprotectionEndDate());

                ComboBox manufctr = (ComboBox) binder.getField(CHFieldNameUtils.companySystemId);
                manufctr.select(masterDto.getCompanySystemId());
            }
            if (selectedTabIndex == 1) {
                TextField id = (TextField) binder.getField(CHFieldNameUtils.insideOwner);
                id.setValue(masterDto.getInsideOwner());
                TextField no = (TextField) binder.getField(CHFieldNameUtils.insidePhone);
                no.setValue(masterDto.getInsidePhone());
                TextField name = (TextField) binder.getField(CHFieldNameUtils.insideAuthor);
                name.setValue(masterDto.getInsideAuthor());
                TextField type = (TextField) binder.getField(CHFieldNameUtils.insideAdditional);
                type.setValue(masterDto.getInsideAdditional());
                TextField status = (TextField) binder.getField(CHFieldNameUtils.insideAdditionalName);
                status.setValue(masterDto.getInsideAdditionalName());
                TextField docType = (TextField) binder.getField(CHFieldNameUtils.insideAdditionalPhone);
                docType.setValue(masterDto.getInsideAdditionalPhone());

                TextField startDateField = (TextField) binder.getField(CHFieldNameUtils.outsideOwner);
                startDateField.setValue(masterDto.getOutsideOwner());
                TextField endDateField = (TextField) binder.getField(CHFieldNameUtils.outsidePhone);
                endDateField.setValue(masterDto.getOutsidePhone());
                TextField docClass = (TextField) binder.getField(CHFieldNameUtils.outsideAuthor);
                docClass.setValue(masterDto.getOutsideAuthor());

                TextField outsideAdditional = (TextField) binder.getField(CHFieldNameUtils.outsideAdditional);
                outsideAdditional.setValue(masterDto.getOutsideAdditional());
                TextField outsideAdditionalName = (TextField) binder.getField(CHFieldNameUtils.outsideAdditionalName);
                outsideAdditionalName.setValue(masterDto.getOutsideAdditionalName());
                TextField outsideAdditionalPhone = (TextField) binder.getField(CHFieldNameUtils.outsideAdditionalPhone);
                outsideAdditionalPhone.setValue(masterDto.getOutsideAdditionalPhone());

                TextField noticeDays = (TextField) binder.getField(CHFieldNameUtils.advanceNoticeDays);
                noticeDays.setValue(String.valueOf(masterDto.getAdvanceNoticeDays()));
                TextField acInfo = (TextField) binder.getField(CHFieldNameUtils.affiliatedContractInfo);
                acInfo.setValue(masterDto.getAffiliatedContractInfo());
                TextField shipTerms = (TextField) binder.getField(CHFieldNameUtils.shippingTerms);
                shipTerms.setValue(masterDto.getShippingTerms());
                if (masterDto.getProposedStartDate() != null) {
                    PopupDateField pStartDate = (PopupDateField) binder.getField("proposedStartDate");
                    pStartDate.setValue(masterDto.getProposedStartDate());
                }
                if (masterDto.getProposedEndDate() != null) {
                    PopupDateField pEndDate = (PopupDateField) binder.getField("proposedEndDate");
                    pEndDate.setValue(masterDto.getProposedEndDate());
                }
                if (masterDto.getOriginalStartDate() != null) {
                    PopupDateField oStartDate = (PopupDateField) binder.getField(CHFieldNameUtils.originalStartDate);
                    oStartDate.setValue(masterDto.getOriginalStartDate());
                }
                if (masterDto.getOriginalEndDate() != null) {
                    PopupDateField oEndDate = (PopupDateField) binder.getField(CHFieldNameUtils.originalEndDate);
                    oEndDate.setValue(masterDto.getOriginalEndDate());
                }
                ComboBox awarStatus = (ComboBox) binder.getField(CHFieldNameUtils.awardStatus);
                awarStatus.setValue(masterDto.getAwardStatus());
                PopupDateField lastUpdateDate = (PopupDateField) binder.getField(CHFieldNameUtils.lastUpdatedDate);
                lastUpdateDate.setValue(masterDto.getLastUpdatedDate());

                TextField peClause = (TextField) binder.getField(CHFieldNameUtils.priceEscalationClause);
                peClause.setValue(masterDto.getPriceEscalationClause());
                TextField lowPrice = (TextField) binder.getField(CHFieldNameUtils.exemptFromLowPrice);
                lowPrice.setValue(masterDto.getExemptFromLowPrice());
                ComboBox indicator = (ComboBox) binder.getField(CHFieldNameUtils.priceResetIndicator);
                indicator.setValue(masterDto.getPriceResetIndicator());

                TextField cancellationClause = (TextField) binder.getField(CHFieldNameUtils.cancellationClause);
                cancellationClause.setValue(masterDto.getCancellationClause());
                TextField nation = (TextField) binder.getField(CHFieldNameUtils.mostFavoredNation);
                nation.setValue(masterDto.getMostFavoredNation());
                TextField category = (TextField) binder.getField(CHFieldNameUtils.category);
                category.setValue(masterDto.getCategory());

                TextField currency = (TextField) binder.getField(CHFieldNameUtils.currency);
                currency.setValue(masterDto.getCurrency());
                TextField minimumOrder = (TextField) binder.getField(CHFieldNameUtils.minimumOrder);
                minimumOrder.setValue(masterDto.getMinimumOrder());
                ComboBox paymentTerms = (ComboBox) binder.getField(CHFieldNameUtils.paymentTerms);
                paymentTerms.setValue(masterDto.getPaymentTerms());
            }
            if (selectedTabIndex == NumericConstants.TWO) {
                aliasResultsBean.removeAllItems();
                aliasResultsBean.addAll(masterDto.getContracAliasMasterList());
                aliasResults.reset();

            }

            if (selectedTabIndex == NumericConstants.THREE) {
                notesTabForm.resetBtnLogic(masterDto.getInternalNotes());                      
            }


        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
