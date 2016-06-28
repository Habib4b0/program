package com.stpl.app.global.rebateschedule.ui.form;

import com.stpl.app.global.abstractsearch.view.AbstractSearchView;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.ui.InformationLayout;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.rebateschedule.dto.IFPDetailsDTO;
import com.stpl.app.global.rebateschedule.dto.ItemDetailsDTO;
import com.stpl.app.global.rebateschedule.dto.RebateScheduleMasterDTO;
import com.stpl.app.global.rebateschedule.logic.RebateScheduleLogic;
import com.stpl.app.global.rebateschedule.ui.view.RebateScheduleAddView;
import com.stpl.app.global.rebateschedule.util.FieldNameUtils;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.RsModel;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.ImtdRsDetailsLocalServiceUtil;
import com.stpl.app.ui.NotesTabForm;
import com.stpl.app.ui.StplCustomComponent;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.FunctionNameUtil;
import com.stpl.app.util.NotesTabLogic;
import com.stpl.app.util.TabNameUtil;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.domain.global.base.AddBaseForm;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

// TODO: Auto-generated Javadoc
/**
 * The Class for implementing RebateSchedule Add Form in UI.
 */
public final class RebateScheduleAddForm extends StplCustomComponent implements AddBaseForm {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(RebateScheduleAddForm.class);
    /**
     * The Constant NAME.
     */
    public static final String NAME = ConstantsUtils.ADD;

    /**
     * The error msg.
     */
    @UiField("errorMsg")
    private ErrorLabel errorMsg;

    /**
     * The tabsheet.
     */
    TabSheet tabsheet = new TabSheet();

    /**
     * The rebate schedule logic.
     */
    private final RebateScheduleLogic rebateLogic;
    /**
     * The space.
     */
    private Label space = new Label(ConstantsUtils.SPACE, ContentMode.HTML);
    /**
     * The item family plan results bean.
     */
    private BeanItemContainer<IfpModel> itemFamilyplanResultsBean = new BeanItemContainer<IfpModel>(IfpModel.class);

    /**
     * The item result bean.
     */
    private BeanItemContainer<ItemDetailsDTO> itemResultBean;
    // TABBAR 1
    /**
     * The selected table.
     */
    private ExtFilterTable availableTable;

    private ExtFilterTable selectedTable;
    /**
     * The item details table.
     */
    private ExtPagedTable itemDetailsTable;
    private BeanItemContainer<IFPDetailsDTO> itemFamilyPlanResultsBean;

    private final Label selectedIfp = new Label();

    /**
     * The rebate schedule master.
     */
    private RebateScheduleMasterDTO rebateScheduleMaster;

    /**
     * The Rebate Plan Level Change Flag.
     */
    private boolean rpLevelChange;
    /**
     * The Rebate setup items check Flag.
     */
    private boolean itemsLoadedCheck = false;

    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder;

    private final BeanItemContainer<IFPDetailsDTO> selectedItemResultBean;

    @UiField("saveBtn")
    private Button btnUpdate;

    @UiField("backBtn")
    private Button backBtn;
    @UiField("resetBtn")
    private Button resetBtn;

    @UiField("deleteBtn")
    private Button deleteBtn;

    @UiField("informationLayout")
    private VerticalLayout informationLayout;   
    
    private final NotesTabLogic notesLogic =new NotesTabLogic();
    private LazyBeanItemContainer identifierTypeDescContainer;     

    private String ruleName;
    /**
     * The tabsheet.
     */
    @UiField("tabSheet")
    private TabSheet tabSheet;
    
    RSInfoTabForm infoTab;
    RSItemAdditionTabForm itemAdditionTab;
    RSProcessingOptionTabForm poTab;
    RSRebateSetupTabForm rebateSetupTab;
    private String selectedTabName = TabNameUtil.REBATE_SCHEDULE_INFO;

    private NotesTabForm notesTab;

    private final BeanItemContainer<IfpModel> emptyContainer = new BeanItemContainer<IfpModel>(IfpModel.class);

    private final BeanItemContainer<IfpModel> emptyAvailableContainer = new BeanItemContainer<IfpModel>(IfpModel.class);

    private final BeanItemContainer<ItemDetailsDTO> emptyCont = new BeanItemContainer<ItemDetailsDTO>(ItemDetailsDTO.class);
    private final String mode;
    private final boolean isViewMode;
    private final boolean isAddMode;
    CommonUtil commonMsg=CommonUtil.getInstance();
    SessionDTO sessionDTO;
    String prevIfpId;
    String ifpEditIdent;
    String ifpId;

    /**
     * @return the itemFamilyplanResultsBean
     */
    public BeanItemContainer<IfpModel> getItemFamilyplanResultsBean() {
        return itemFamilyplanResultsBean;
    }

    /**
     * @param itemFamilyplanResultsBean the itemFamilyplanResultsBean to set
     */
    public void setItemFamilyplanResultsBean(final BeanItemContainer<IfpModel> itemFamilyplanResultsBean) {
        this.itemFamilyplanResultsBean = itemFamilyplanResultsBean;
    }

    /**
     * @return the itemResultBean
     */
    public BeanItemContainer<ItemDetailsDTO> getItemResultBean() {
        return itemResultBean;
    }

    /**
     * @param itemResultBean the itemResultBean to set
     */
    public void setItemResultBean(final BeanItemContainer<ItemDetailsDTO> itemResultBean) {
        this.itemResultBean = itemResultBean;
    }

    /**
     * @return the rebateScheduleMaster
     */
    public RebateScheduleMasterDTO getRebateScheduleMaster() {
        return rebateScheduleMaster;
    }

    /**
     * @param rebateScheduleMaster the rebateScheduleMaster to set
     */
    public void setRebateScheduleMaster(final RebateScheduleMasterDTO rebateScheduleMaster) {
        this.rebateScheduleMaster = rebateScheduleMaster;
    }

    /**
     * @return the name
     */
    public static String getName() {
        return NAME;
    }

    /**
     * @return the errorMsg
     */
    public ErrorLabel getErrorMsg() {
        return errorMsg;
    }

    /**
     * @return the rebateLogic
     */
    public RebateScheduleLogic getRebateLogic() {
        return rebateLogic;
    }

    /**
     * @return the space
     */
    public Label getSpace() {
        return space;
    }

    /**
     * @return the selectedTable
     */
    public ExtFilterTable getSelectedTable() {
        return selectedTable;
    }

    /**
     * @return the itemDetailsTable
     */
    public ExtPagedTable getItemDetailsTable() {
        return itemDetailsTable;
    }

    /**
     * @return the rebateScheduleId
     */
    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    /**
     * @return the selectedIfp
     */
    public Label getSelectedIfp() {
        return selectedIfp;
    }

    /**
     * @param binder the binder to set
     */
    public void setBinder(final ErrorfulFieldGroup binder) {
        this.binder = binder;
    }

    /**
     * Gets the selected item result bean.
     *
     * @return the selectedItemResultBean
     */
    public BeanItemContainer<IFPDetailsDTO> getSelectedItemResultBean() {
        return selectedItemResultBean;
    }

    /**
     * Instantiates a new rebate schedule add form.
     *
     * @param rebateSchMaster the rebate schedule master
     * @param binder the binder
     * @param selectedItemResultBean the item familyplan results bean
     * @param itemResultBean the item result bean
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    public RebateScheduleAddForm(final RebateScheduleMasterDTO rebateSchMaster, final ErrorfulFieldGroup binder, final BeanItemContainer<IFPDetailsDTO> selectedItemResultBean,
            final BeanItemContainer<ItemDetailsDTO> itemResultBean, String mode, final SessionDTO sessionDTO) throws SystemException, PortalException, Exception {
        super();

        this.rebateScheduleMaster = rebateSchMaster;
        this.selectedItemResultBean = selectedItemResultBean;
        this.itemResultBean = itemResultBean;
        this.binder = binder;
        this.mode = mode;
        this.sessionDTO = sessionDTO;
        rebateLogic = new RebateScheduleLogic(this.sessionDTO);
        this.isViewMode = ConstantsUtils.VIEW.equals(mode);
        this.isAddMode = ConstantsUtils.ADD.equals(mode);
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/common/tabsheetform.xml"), this));         
        init();

        }

    /**
     * 
     * @throws SystemException
     * @throws PortalException
     * @throws Exception 
     */
    public void init() throws SystemException, PortalException, Exception {
        addToContent();
        getBinder();
        configureFields();
        if (isAddMode) {
            configDeductionInclusion();
        }

    }

    /**
     * Method to add buttons, labels, and tabs components .
     */
    public void addToContent() throws SystemException, PortalException, Exception {
        informationLayout.addComponent(new InformationLayout("rebate_Schedule", rebateScheduleMaster.getRebateScheduleId(), rebateScheduleMaster.getRebateScheduleNo(), rebateScheduleMaster.getRebateScheduleName()));
        addTabSheet();
        addPermissionToFooterButtons();

    }

    /**
     * Method to provide an easy way of binding fields to data and handling
     * commits of fields.
     *
     * @return the binder
     */
    private ErrorfulFieldGroup getBinder() {

        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<RebateScheduleMasterDTO>(rebateScheduleMaster));
        binder.setBuffered(true);
        binder.setErrorDisplay(errorMsg);
        errorMsg.setId("ErrorMessage");
        return binder;
    }

    /**
     * Method to add the tabs in a specific position and to attach its
     * listeners.
     *
     * @return the tab sheet
     */
    public void addTabSheet() throws PortalException, SystemException, Exception {
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheet.setId("responsive-tab");
        tabSheet.setReadOnly(true);
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        LOGGER.info("addTabSheet  userId=" + userId + " UISecurityUtil.REBATE_SCHEDULE=" + UISecurityUtil.REBATE_SCHEDULE);
        final Map<String, AppPermission> tabRsHM = stplSecurity.getBusinessTabPermission(userId, UISecurityUtil.REBATE_SCHEDULE);
        LOGGER.info("addTabSheet  userId=" + userId + " UISecurityUtil.REBATE_SCHEDULE=" + UISecurityUtil.REBATE_SCHEDULE);

        infoTab = new RSInfoTabForm(rebateLogic, this, binder,mode);
            infoTab.setCaption(TabNameUtil.REBATE_SCHEDULE_INFO);
            tabSheet.addTab(infoTab, TabNameUtil.REBATE_SCHEDULE_INFO);
        if (tabRsHM.get(TabNameUtil.PROCESSING_OPTION) != null && ((AppPermission) tabRsHM.get(TabNameUtil.PROCESSING_OPTION)).isTabFlag()) {
        
        poTab = new RSProcessingOptionTabForm(binder, mode);
        poTab.setCaption(TabNameUtil.PROCESSING_OPTION);
        tabSheet.addTab(poTab, TabNameUtil.PROCESSING_OPTION);
       
        }
         rebateSetupTab = new RSRebateSetupTabForm(binder, rebateLogic, itemResultBean, identifierTypeDescContainer, infoTab, mode,sessionDTO);
            itemAdditionTab = new RSItemAdditionTabForm(binder, rebateScheduleMaster, itemFamilyplanResultsBean, selectedItemResultBean,itemResultBean,itemDetailsTable, rebateLogic, selectedIfp, mode,rebateSetupTab,sessionDTO);            
            itemAdditionTab.setCaption(TabNameUtil.RS_ITEM_ADDITION);
            tabSheet.addTab(itemAdditionTab, TabNameUtil.RS_ITEM_ADDITION);
            availableTable = itemAdditionTab.getAvailableTable();
            selectedTable = itemAdditionTab.getSelectedTable();
            rebateSetupTab.setCaption(TabNameUtil.RS_REBATE_SETUP);
            tabSheet.addTab(rebateSetupTab, TabNameUtil.RS_REBATE_SETUP);
            itemDetailsTable = rebateSetupTab.getItemDetailsTable();

        
        try {
                notesTab = new NotesTabForm(binder, "Rebate Schedule", "RS_MODEL","rebateScheduleSystemId",mode);
                notesTab.setCaption(TabNameUtil.NOTES);
                tabSheet.addTab(notesTab, TabNameUtil.NOTES);

        } catch (Exception ex) {
            LOGGER.error(ex);
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
             * Method used for Handling error for tabsheet
             */
            public void selectedTabChange(final SelectedTabChangeEvent event) {

                try {
                    final String selectedIfpId = selectedIfp.getValue();
                    prevIfpId=selectedIfpId;
                    selectedTabName = event.getTabSheet().getSelectedTab().getCaption();
                    LOGGER.info("selectedTabIndex :" + selectedTabName);
                    if (selectedTabName.equals(TabNameUtil.REBATE_SCHEDULE_INFO)) { // Rebate Schedule Information
                        infoTab.focusRebateScheduleId();
                        binder.getErrorDisplay().clearError();
                    } else if (selectedTabName.equals(TabNameUtil.RS_ITEM_ADDITION)) {// Item Addition
                        itemAdditionTab.focusIfpNo();
                        binder.getErrorDisplay().clearError();
                    } else if (selectedTabName.equals(TabNameUtil.RS_REBATE_SETUP)) { // Rebate setup
                        if (isViewMode) {
                            rebateSetupTab.loadBasedOnCalculationType(String.valueOf(binder.getField("calculationType").getValue()));
                            rebateSetupTab.refreshTable();
                        } else if (isAddMode) {
                            rebateSetupTab.focusMassCheck();
                            binder.getErrorDisplay().clearError();                            
                            rebateSetupTab.loadBasedOnCalculationType(String.valueOf(binder.getField("calculationType").getValue()));
                            itemsLoadedCheck = true;  
                            if (!itemResultBean.getItemIds().isEmpty()) {
                                rebateLogic.saveToTemp(itemResultBean.getItemIds(), true);
                                itemResultBean.removeAllItems();
                                itemDetailsTable.commit();
                            } 
                            rebateSetupTab.loadItemDetailsTable();
                        } else {
                            rebateSetupTab.focusMassCheck();
                            binder.getErrorDisplay().clearError();                           
                            rebateSetupTab.loadBasedOnCalculationType( String.valueOf(binder.getField("calculationType").getValue()));
                            if (!itemResultBean.getItemIds().isEmpty()) {
                                rebateLogic.saveToTemp(itemResultBean.getItemIds(), true);
                                itemResultBean.removeAllItems();
                                itemDetailsTable.commit();                                
                            } 
                            rebateSetupTab.loadItemDetailsTable();
                        }
                    } else if (selectedTabName.equals(TabNameUtil.NOTES)) { // Notes
                        notesTab.focusNewNote();
                        notesTab.setUploderValue();
                        notesTab.callJavaScriptForButton();
                        notesTab.refreshTable();
                        binder.getErrorDisplay().clearError();
                        if(isViewMode){
                            notesTab.removeAndDisablingComponents();
                        }
                    }

                } catch (Exception exception) {
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
                    LOGGER.error(exception);

                }
            }
        }
        );

    }

    private void addPermissionToFooterButtons() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering init  :::: ");
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        LOGGER.info("init  userId=" + userId + " UISecurityUtil.REBATE_SCHEDULE=" + UISecurityUtil.REBATE_SCHEDULE);
        final Map<String, AppPermission> functionRsHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.REBATE_SCHEDULE+","+"Common");
       
        if (functionRsHM.get(FunctionNameUtil.SAVE) != null && ((AppPermission) functionRsHM.get(FunctionNameUtil.SAVE)).isFunctionFlag()) {
            addButton();
        }else{
            btnUpdate.setVisible(false);
        }
            backButton();
        if (functionRsHM.get(FunctionNameUtil.RESET) != null && ((AppPermission) functionRsHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
            resetButton();
        }else{
            resetBtn.setVisible(false);
        }
        if (functionRsHM.get(FunctionNameUtil.DELETE) != null && ((AppPermission) functionRsHM.get(FunctionNameUtil.DELETE)).isFunctionFlag()) {
            deleteButton();
        }else{
            deleteBtn.setVisible(false);
        }

    }

    /**
     * Method to put together the drop down list boxes, text fields, buttons and
     * its click listeners.
     */
    public void configureFields() throws SystemException, Exception {
        deleteBtn.setVisible(false);
        if (isViewMode) {
            btnUpdate.setVisible(false);
            resetBtn.setVisible(false);

        } else if (ConstantsUtils.EDIT.equals(mode)) {
            btnUpdate.setCaption("Update");
            deleteBtn.setVisible(true);

        }
    }

    private void addButton() {
            LOGGER.info("Entering Add Buuton Method  :::: ");
            btnUpdate.setCaption(ConstantsUtils.SAVE);
            btnUpdate.setWidth(ConstantsUtils.BTN_WIDTH);
            btnUpdate.addClickListener(new ClickListener() {
                /**
                 * After clicking update button, function will be executed.
                 */
                @SuppressWarnings("PMD")
                public void buttonClick(final ClickEvent event) {
                    LOGGER.info("Entering Rebate Schedule Save operation from Add");
                    updateLogic();
                    LOGGER.info("Ending Rebate Schedule Save operation from Add");
                }
            });
    }

    /**
     * back button.
     */
    private void backButton() {

        LOGGER.info("Entering init  :::: ");
        backBtn.setWidth(ConstantsUtils.BTN_WIDTH);
        backBtn.addClickListener(new ClickListener() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            /**
             * Listener is called when an Button is clicked
             *
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("Entering Rebate Schedule back button operation from Add");
                if (mode.equals(ConstantsUtils.VIEW)) {
                     AbstractSearchView.flag=false;
                    getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                } else {
                    try {
                        MessageBox.showPlain(Icon.QUESTION, "Confirmation", commonMsg.getBackMessage(), new MessageBoxListener() {
                            /**
                             * Called when a Button has been clicked .
                             *
                             */
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(ConstantsUtils.YES)) {
                                    try {
                                        LOGGER.info("NavigateTo Rebate Schedule SearchView page");
                                          AbstractSearchView.flag=false;
                                        getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                                        rebateLogic.removeAllFromTempTable(true);
                                    } catch (SystemException ex) {
                                       LOGGER.error(ex);
                                    } catch (PortalException ex) {
                                        LOGGER.error(ex);
                                    }
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
                    } catch (Exception exception) {
                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1009), new MessageBoxListener() {  
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
                    LOGGER.info("Ending Rebate Schedule back button operation from Add");
                }
            }
        });
        backBtn.setErrorHandler(new ErrorHandler() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            /**
             * Back button UI error handler
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(String.valueOf(event));
            }
        });
    }

    /**
     * reset button.
     */
    private void resetButton() {

        resetBtn.setCaption(ConstantsUtils.RESET);
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
                MessageBox.showPlain(Icon.QUESTION, "Confirmation", commonMsg.getResetMessage(), new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                            if (isAddMode) {
                                resetForAddMode();
                            } else {
                                resetForEdit();
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

            }

        });

    }

    /**
     * ViewChangeEvent represent the view and view return enter method.
     *
     * @param event the event
     */
    public void enter(final ViewChangeEvent event) {
        // Method used to view return enter method.
    }

    public void setRpLevelChange(boolean value) {
        rpLevelChange = value;
    }

    public RSInfoTabForm getInfoTab() {
        return infoTab;
    }

    public RSRebateSetupTabForm getRebateSetupTab() {
        return rebateSetupTab;
    }

    @Override
    public void addLogic() {
        // TODO Auto-generated method stub

    }

    /**
     * delete button.
     */
    private void deleteButton() throws Exception {

        deleteBtn.setCaption("Delete");
        deleteBtn.setWidth(ConstantsUtils.BTN_WIDTH);
        deleteBtn.setErrorHandler(new ErrorHandler() {
            /**
             * Method used for error handling for delete button
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }

        });
        deleteBtn.addClickListener(new ClickListener() {
            /**
             * Method used to delete button logic for click event listener
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("Entering Rebate Schedule delete operation in Edit");

                binder.getFields();

                final String value = String.valueOf(binder.getField(ConstantsUtils.REBATE_SCHEDULE_ID).getValue());
                final int idValue = Integer.valueOf(binder.getField(ConstantsUtils.REBATE_SCHEDULE_SYSTEM_ID).getValue().toString().replaceAll(ConstantsUtils.COMMA, StringUtils.EMPTY));
                LOGGER.info("DeleteButton id=" + idValue);
                List psContractList = rebateLogic.getRsContractList(idValue);

                if (psContractList != null && !psContractList.isEmpty() && psContractList.size() > 0) {
                    final MessageBox msg = MessageBox.showPlain(Icon.WARN, "Cannot Delete", "Rebate Schedule cannot be deleted as it is associated with Contract", new MessageBoxListener() {

                        @Override
                        public void buttonClicked(final ButtonId buttonId) {
                            // Do Nothing
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();

                } else {
                
                MessageBox.showPlain(Icon.QUESTION, "Confirmation",commonMsg.getDeleteMessage(binder.getField(ConstantsUtils.REBATE_SCHEDULE_NAME).getValue().toString()), new MessageBoxListener() {
                    /**
                     * Method used to delete button logic for click event
                     * listener
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        try {
                            if (buttonId.name().equals(ConstantsUtils.YES)) {
                              
                                final RsModel master = rebateLogic.deleteRebateScheduleById(idValue);
                                final Notification notif = new Notification(commonMsg.getDeletedSuccessfulMessage(master.getRsId() , master.getRsName()), Notification.Type.HUMANIZED_MESSAGE);
                                notif.setPosition(Position.MIDDLE_CENTER);
                                notif.setStyleName(ConstantsUtils.MY_STYLE);
                                notif.show(Page.getCurrent());
                                  AbstractSearchView.flag=true;
                                getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                            }
                        } catch (SystemException sysException) {
                            final String errorMsg = ErrorCodeUtil.getErrorMessage(sysException);
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
                }, ButtonId.YES, ButtonId.NO);
                            }
                LOGGER.info("Ending Rebate Schedule delete operation in Edit");
            }
        });

    }

    /**
     * update logic for Add.
     */
    public void updateLogic() {

        try {
            binder.getErrorDisplay().clearError();
            binder.getFields();
            binder.commit();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
            Boolean saveFlag = true;
            boolean flag = false;
            String systemId = String.valueOf(prevIfpId);
            String ifpSysId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.IFP_EDIT_IDENT));
            StringBuilder errorMessage = new StringBuilder("Information for the following Mandatory fields need to be provided:" + ConstantsUtils.BREAK);
            if (binder.getField("rebateScheduleId").getValue().toString().isEmpty()) {
                if (flag) {
                    errorMessage.append(",");
                }
                errorMessage.append("Rebate Schedule ID");
                flag = true;
            }
            if (binder.getField("rebateScheduleNo").getValue().toString().isEmpty()) {
                if (flag) {
                    errorMessage.append(", ");
                }
                errorMessage.append("Rebate Schedule No");
                flag = true;
            }
            if (binder.getField("rebateScheduleName").getValue().toString().isEmpty()) {
                if (flag) {
                    errorMessage.append(", ");
                }
                errorMessage.append("Rebate Schedule Name");
                flag = true;
            }   
            if (binder.getField("rebateScheduleStatus").getValue() == null 
                    || ConstantsUtils.SELECT_ONE.equals(binder.getField("rebateScheduleStatus").getValue()) 
                    || ConstantsUtils.NULL.equals(binder.getField("rebateScheduleStatus").getValue()) 
                    || ConstantsUtils.SELECT_ONE.equals(binder.getField("rebateScheduleStatus").getDescription())) {
                if (flag) {
                    errorMessage.append(", ");
                }
                errorMessage.append("Rebate Schedule Status");
                flag = true;
            }
            
             if (binder.getField("startDate").getValue() == null) {
                if (flag) {
                    errorMessage.append(", ");
                }
                errorMessage.append("Rebate Schedule Start Date");
                flag = true;
            }
  
             if (binder.getField("deductionInclusion").getValue() == null 
                    || ConstantsUtils.SELECT_ONE.equals(binder.getField("deductionInclusion").getValue()) 
                    || ConstantsUtils.NULL.equals(binder.getField("deductionInclusion").getValue()) 
                    || ConstantsUtils.SELECT_ONE.equals(binder.getField("deductionInclusion").getDescription())) {
                if (flag) {
                    errorMessage.append(", ");
                }
                errorMessage.append("Deduction Inclusion");
                flag = true;
            }
             
             if (binder.getField("calendar").getValue() == null 
                    || ConstantsUtils.SELECT_ONE.equals(binder.getField("calendar").getValue()) 
                    || ConstantsUtils.NULL.equals(binder.getField("calendar").getValue()) 
                    || ConstantsUtils.SELECT_ONE.equals(binder.getField("calendar").getDescription())) {
                if (flag) {
                    errorMessage.append(", ");
                }
                errorMessage.append("Calendar");
                flag = true;
            }
             
             
            if (binder.getField("rebateScheduleType").getValue() == null || ConstantsUtils.SELECT_ONE.equals(binder.getField("rebateScheduleType").getValue())
                    || ConstantsUtils.NULL.equals(binder.getField("rebateScheduleType").getValue()) 
                    || ConstantsUtils.SELECT_ONE.equals(binder.getField("rebateScheduleType").getDescription())) {
                if (flag) {
                    errorMessage.append(", ");
                }
                errorMessage.append("Rebate Schedule Type");
                flag = true;
            }
            if (binder.getField("rebateProgramType").getValue() == null 
                    || ConstantsUtils.SELECT_ONE.equals(binder.getField("rebateProgramType").getValue())
                    || ConstantsUtils.NULL.equals(binder.getField("rebateProgramType").getValue()) 
                    || ConstantsUtils.SELECT_ONE.equals(binder.getField("rebateProgramType").getDescription())) {
                if (flag) {
                    errorMessage.append(", ");
                }
                errorMessage.append("Rebate Program Type");
                flag = true;
            }
            if (binder.getField("rebateScheduleCategory").getValue() == null 
                    || ConstantsUtils.SELECT_ONE.equals(binder.getField("rebateScheduleCategory").getValue())
                    || ConstantsUtils.NULL.equals(binder.getField("rebateScheduleCategory").getValue()) 
                    || ConstantsUtils.SELECT_ONE.equals(binder.getField("rebateScheduleCategory").getDescription())) {
                if (flag) {
                    errorMessage.append(", ");
                }
                errorMessage.append("Rebate Schedule Category");
                flag = true;
            }
            if (binder.getField("paymentFrequency").getValue() == null 
                    || ConstantsUtils.SELECT_ONE.equals(binder.getField("paymentFrequency").getValue())
                    || ConstantsUtils.NULL.equals(binder.getField("paymentFrequency").getValue()) 
                    || ConstantsUtils.SELECT_ONE.equals(binder.getField("paymentFrequency").getDescription())) {
                if (flag) {
                    errorMessage.append(", ");
                }
                errorMessage.append("Payment Frequency");
                flag = true;
            }
            if (binder.getField("paymentMethod").getValue() == null 
                    || ConstantsUtils.SELECT_ONE.equals(binder.getField("paymentMethod").getValue())
                    || ConstantsUtils.NULL.equals(binder.getField("paymentMethod").getValue()) 
                    || ConstantsUtils.SELECT_ONE.equals(binder.getField("paymentMethod").getDescription())) {
                if (flag) {
                    errorMessage.append(", ");
                }
                errorMessage.append("Payment Method");
                flag = true;
            }  
            if (flag) {
                errorMessage.append(ConstantsUtils.BREAK);
            }
            if (selectedTable.size() > 0) {

            } else {
                errorMessage.append("Select atleast one IFP");
                flag = true;
            }
            if (flag) {
                binder.getErrorDisplay().setError(errorMessage.toString());
                return;
            }
	
            rebateLogic.saveToTemp(itemResultBean.getItemIds(), false);
            itemResultBean.removeAllItems();
            if (saveFlag) {
                saveFlag = rebateLogic.saveValidation("CheckRecord");
                long startdatecount=rebateLogic.startDateValidation(userId,sessionId);
                if (saveFlag || (!systemId.equals(ifpSysId) && ifpSysId != null && !(ConstantsUtils.NULL).equals(ifpSysId))) {
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Cannot Save Rebate Schedule", "No items have been selected.  Please select at least one item.", new MessageBoxListener() {  
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
                    return;
                } else if(startdatecount>0){
                    binder.getErrorDisplay().setError("RS Start Date is less than RS End date");
                    return;
                }else{
                    
                    saveFlag = true;
                }
                if (saveFlag) {
                    saveFlag = rebateLogic.saveValidation("ItemRebateStartDate");                    
                    if (!saveFlag) {
                        binder.getErrorDisplay().setError("Start Date is Mandatory");
                        return;
                    }
                    if (saveFlag) {
                        saveFlag = rebateLogic.saveValidation("ItemRebateScheduleStatus");                        
                        if (!rebateLogic.saveValidation("ItemRebateScheduleStatus")) {
                            binder.getErrorDisplay().setError("RS Status is Mandatory");
                            return;
                        }
                    }
                }
            }
            
            if (binder.getField(ConstantsUtils.REBATE_SCHEDULE_ID).getValue() != null) {
            }

            final List<ItemDetailsDTO> ifpList = new ArrayList<ItemDetailsDTO>();

            if (saveFlag) {

                /**
                 * After clicking delete button, function will be executed.
                 */
                List<NotesDTO> removedDetailsList = notesTab.removeDetailsList();

                if (removedDetailsList.size() != 0) {

                    for (int i = 0; i < removedDetailsList.size(); i++) {
                        NotesDTO dtoValue = removedDetailsList.get(i);

                        if (dtoValue.getDocDetailsId() != 0) {

                            notesLogic.deleteUploadedFile(dtoValue.getDocDetailsId(), StringUtils.EMPTY, dtoValue.getDocumentFullPath());
                        }
                    }
                }
                      
                MessageBox.showPlain(Icon.QUESTION, "Confirmation", commonMsg.getSaveMessage( binder.getField(ConstantsUtils.REBATE_SCHEDULE_NAME).getValue().toString()), new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                            try {
                                final String msg = rebateLogic.saveRS(binder, ifpList, notesTab.getUploadedData(), notesTab.getAddedNotes());
                                if (ConstantsUtils.SUCCESS.equals(msg)) {
                                   
                                    final Notification notif = new Notification(commonMsg.getSavedSuccessfulMessage(binder.getField(ConstantsUtils.REBATE_SCHEDULE_ID).getValue().toString(),binder.getField(ConstantsUtils.REBATE_SCHEDULE_NAME).toString()), Notification.Type.HUMANIZED_MESSAGE);
                                    notif.setPosition(Position.MIDDLE_CENTER);
                                    notif.setStyleName("mystyle");
                                    notif.show(Page.getCurrent());
                                     sessionDTO.setMode(ConstantsUtils.EDIT);
                                    getUI().getNavigator().navigateTo(RebateScheduleAddView.NAME);
                                } else if (ConstantsUtils.DUPLICATE.equals(msg)) {
                                    binder.getErrorDisplay().setError("Rebate Schedule ID is already exist. Please enter different Rebate Schedule ID");
                                } else if (ConstantsUtils.DUPLICATENO.equals(msg)) {
                                    binder.getErrorDisplay().setError("Rebate Schedule No already exists. Please enter different Rebate Schedule No");
                                } else if (ConstantsUtils.DUPLICATENAME.equals(msg)) {
                                    binder.getErrorDisplay().setError("Rebate Schedule Name already exists. Please enter different Rebate Schedule Name");
                                }
                              
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
                    }
                }, ButtonId.YES, ButtonId.NO);

            }

            LOGGER.info("Ending Rebate Schedule update operation in Edit");

        } catch (FieldGroup.CommitException ex) {
            LOGGER.error(ex);
        } catch (SystemException sysException) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(sysException);
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
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1003), new MessageBoxListener() {  
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
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1003), new MessageBoxListener() {  
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

    /**
     * reset logic for Edit.
     */
    public void resetForEdit() {
        try {
            LOGGER.info("Entering inside  reset  method from ADD ");
            final String idValue = String.valueOf(sessionDTO.getSystemId());
            binder.getErrorDisplay().clearError();
            final int systemId = Integer.valueOf(idValue);
            rebateScheduleMaster = rebateLogic
                    .getRebateScheduleMasterById(systemId);
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final String sessionId = String.valueOf(sessionDTO.getUiSessionId());

            if (selectedTabName.equals(TabNameUtil.REBATE_SCHEDULE_INFO)) {
                TextField id = (TextField) binder.getField(FieldNameUtils.REBATE_SCHEDULE_ID);
                id.setValue(rebateScheduleMaster.getRebateScheduleId());
                TextField no = (TextField) binder.getField(FieldNameUtils.REBATE_SCHEDULE_NO);
                no.setValue(rebateScheduleMaster.getRebateScheduleNo());
                TextField name = (TextField) binder.getField(FieldNameUtils.REBATE_SCHEDULE_NAME);
                name.setValue(rebateScheduleMaster.getRebateScheduleName());
                ComboBox status = (ComboBox) binder.getField(FieldNameUtils.REBATE_SCHEDULE_STATUS);
                status.setValue(rebateScheduleMaster.getRebateScheduleStatus());
                ComboBox type = (ComboBox) binder.getField(FieldNameUtils.REBATE_SCHEDULE_TYPE);
                type.setValue(rebateScheduleMaster.getRebateScheduleType());
                ComboBox programType = (ComboBox) binder.getField(FieldNameUtils.REBATE_PROGRAM_TYPE);
                programType.setValue(rebateScheduleMaster.getRebateProgramType());
                ComboBox category = (ComboBox) binder.getField(FieldNameUtils.REBATE_SCHEDULE_CATEGORY);
                category.setValue(rebateScheduleMaster.getRebateScheduleCategory());
                ComboBox tradeClass = (ComboBox) binder.getField(FieldNameUtils.TRADE_CLASS);
                tradeClass.setValue(rebateScheduleMaster.getTradeClass());
                ComboBox freq = (ComboBox) binder.getField(FieldNameUtils.REBATE_FREQUENCY);
                freq.setValue(rebateScheduleMaster.getRebateFrequency());
                CustomTextField parentCompany = (CustomTextField) binder.getField(FieldNameUtils.REBATE_SCHEDULE_TRANS_REF_NO);
                parentCompany.setReadOnly(false);          
                parentCompany.setValue(rebateScheduleMaster.getRebateScheduleTransRefNo());                
                parentCompany.setReadOnly(true);
                TextField refName = (TextField) binder.getField(FieldNameUtils.REBATE_SCHEDULE_TRANS_REF_NAME);
                refName.setReadOnly(false);
                refName.setValue(rebateScheduleMaster.getRebateScheduleTransRefName());
                refName.setReadOnly(true);
                TextField alias = (TextField) binder.getField("rebateScheduleAlias");
                alias.setValue(rebateScheduleMaster.getRebateScheduleAlias());
                ComboBox designation = (ComboBox) binder.getField(FieldNameUtils.REBATE_SCHEDULE_DESIGNATION);
                designation.setValue(rebateScheduleMaster.getRebateScheduleDesignation());
                CustomTextField parentRS = (CustomTextField) binder.getField(FieldNameUtils.PARENT_REBATE_SCHEDULE_ID);
                parentRS.setReadOnly(false);
                parentRS.setEnabled(true);
                parentRS.setValue(rebateScheduleMaster.getParentRebateScheduleId());
                parentRS.setReadOnly(true);
                String caption = designation.getItemCaption(designation.getValue());
                if (!"child".equalsIgnoreCase(caption)) {
                    parentRS.setEnabled(false);
                }
                TextField rsName = (TextField) binder.getField(FieldNameUtils.PARENT_REBATE_SCHEDULE_NAME);
                rsName.setReadOnly(false);
                rsName.setValue(rebateScheduleMaster.getParentRebateScheduleName());
                rsName.setReadOnly(true);
                ComboBox rebatePlan = (ComboBox) binder.getField(FieldNameUtils.REBATE_PLAN_LEVEL);
                rebatePlan.setValue(rebateScheduleMaster.rebatePlanLevel);
                ComboBox payment = (ComboBox) binder.getField(FieldNameUtils.PAYMENT_METHOD);
                payment.setValue(rebateScheduleMaster.getPaymentMethod());
                ComboBox term = (ComboBox) binder.getField(FieldNameUtils.PAYMENT_TERM);
                term.setValue(rebateScheduleMaster.getPaymentTerms());
                ComboBox udc1 = (ComboBox) binder.getField(FieldNameUtils.UDC1);
                udc1.setValue(rebateScheduleMaster.getUdc1());
                ComboBox udc4 = (ComboBox) binder.getField(FieldNameUtils.UDC4);
                udc4.setValue(rebateScheduleMaster.getUdc4());
                ComboBox calendar = (ComboBox) binder.getField(FieldNameUtils.CALENDAR);
                calendar.setValue(rebateScheduleMaster.getCalendar());
                ComboBox udc2 = (ComboBox) binder.getField(FieldNameUtils.UDC2);
                udc2.setValue(rebateScheduleMaster.getUdc2());
                ComboBox udc5 = (ComboBox) binder.getField(FieldNameUtils.UDC5);
                udc5.setValue(rebateScheduleMaster.getUdc5());
                ComboBox paymentFreq = (ComboBox) binder.getField(FieldNameUtils.PAYMENT_FREQUENCY);
                paymentFreq.setValue(rebateScheduleMaster.getPaymentFrequency());
                ComboBox udc3 = (ComboBox) binder.getField(FieldNameUtils.UDC3);
                udc3.setValue(rebateScheduleMaster.getUdc3());
                ComboBox udc6 = (ComboBox) binder.getField(FieldNameUtils.UDC6);
                udc6.setValue(rebateScheduleMaster.getUdc6());
                TextField gracePeriod = (TextField) binder.getField(FieldNameUtils.PAYMENT_GRACE_PERIOD);
                gracePeriod.setValue(rebateScheduleMaster.getPaymentGracePeriod());
                ComboBox calculationType = (ComboBox) binder.getField("calculationType");
                calculationType.setValue(rebateScheduleMaster.getCalculationType());
                ComboBox calculationLevel = (ComboBox) binder.getField("calculationLevel");
                calculationLevel.setValue(rebateScheduleMaster.getCalculationLevel());
                ComboBox interestBearingIndicator = (ComboBox) binder.getField("interestBearingIndicator");
                interestBearingIndicator.select(rebateScheduleMaster.getInterestBearingIndicator());
                ComboBox interestBearingBasisInfo = (ComboBox) binder.getField("interestBearingBasisInfo");
                interestBearingBasisInfo.select(rebateScheduleMaster.getInterestBearingBasisInfo());
                ComboBox rebateRuleType = (ComboBox) binder.getField("rebateRuleType");
                rebateRuleType.select(rebateScheduleMaster.getRebateRuleType());
                ComboBox paymentLevel = (ComboBox) binder.getField("paymentLevel");
                paymentLevel.select(rebateScheduleMaster.getPaymentLevel());
                ComboBox calculationRuleLevel = (ComboBox) binder.getField("calculationRuleLevel");
                calculationRuleLevel.select(rebateScheduleMaster.getCalculationRuleLevel());
                ComboBox evaluationRuleType = (ComboBox) binder.getField("evaluationRuleType");
                evaluationRuleType.select(rebateScheduleMaster.getEvaluationRuleType());
                ComboBox evaluationRuleLevel = (ComboBox) binder.getField("evaluationRuleLevel");
                evaluationRuleLevel.select(rebateScheduleMaster.getEvaluationRuleLevel());
                 TextField calculationRule = (TextField) binder.getField("calculationRule");
                calculationRule.setValue(rebateScheduleMaster.getCalculationRule());
                TextField evaluationRuleAssociation = (TextField) binder.getField("evaluationRuleAssociation");
                evaluationRuleAssociation.setValue(rebateScheduleMaster.getEvaluationRuleAssociation());
                 ComboBox deductionInclusion = (ComboBox) binder.getField("deductionInclusion");
                deductionInclusion.select(rebateScheduleMaster.getDeductionInclusion());
            }
            if (selectedTabName.equals(TabNameUtil.PROCESSING_OPTION)) {
                ComboBox validationProfile = (ComboBox) binder.getField("validationProfile");
                validationProfile.select(rebateScheduleMaster.getValidationProfile());
                ComboBox interestBearingBasisDDLB = (ComboBox) binder.getField("interestBearingBasisDDLB");
                interestBearingBasisDDLB.select(rebateScheduleMaster.getInterestBearingBasisDDLB());
                ComboBox interestBearingIndicatorDDLB = (ComboBox) binder.getField("interestBearingIndicatorDDLB");
                interestBearingIndicatorDDLB.select(rebateScheduleMaster.getInterestBearingIndicatorDDLB());
                ComboBox rebateProcessingTypeDDLB = (ComboBox) binder.getField("rebateProcessingTypeDDLB");
                rebateProcessingTypeDDLB.select(rebateScheduleMaster.getRebateProcessingTypeDDLB());
            }
            if (selectedTabName.equals(TabNameUtil.RS_ITEM_ADDITION)) {
                itemResultBean.removeAllItems();
                itemAdditionTab.resetItemAdditionSearchCiteria();
                availableTable.setContainerDataSource(emptyAvailableContainer);
                availableTable.setVisibleColumns(CommonUIUtils.IFP_COLUMNS_IN_RS);
                availableTable.setColumnHeaders(CommonUIUtils.IFP_HEADER_IN_RS);                 
                ImtdRsDetailsLocalServiceUtil.deleteAll(userId, sessionId, null, "All", null, null, null, null);                
                List<IFPDetailsDTO> list = rebateLogic.getItemFamilyPlanFromRSID(systemId);
                final IFPDetailsDTO obj = list.get(0);
                int ifpId = obj.getItemFamilyplanSystemId();
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.IFP_SYS_ID, ifpId);
                prevIfpId=String.valueOf(ifpId);
                selectedItemResultBean.addAll(list);               
                selectedIfp.setValue(String.valueOf(ifpId));
                rebateLogic.loadItemDetailsFromIfp(idValue);  
                itemResultBean.removeAllItems();                
                rebateSetupTab.clearRebateSetupData(); 
            }
            if (selectedTabName.equals(TabNameUtil.RS_REBATE_SETUP)) {
                rebateSetupTab.getMassCheck().setValue(ConstantsUtils.DISABLE);
                selectedItemResultBean.removeAllItems();
                selectedItemResultBean.addAll(rebateLogic.getItemFamilyPlanFromRSID(systemId));                
                itemResultBean.removeAllItems();
                ImtdRsDetailsLocalServiceUtil.deleteAll(userId, sessionId, null, "All", null, null, null, null);
                rebateLogic.loadItemDetailsFromIfp(idValue);
                rebateSetupTab.refreshTable();
            } else {
                notesTab.resetBtnLogic(rebateScheduleMaster.getInternalNotes());
            }

        } catch (Exception e) {  
            LOGGER.error(e);
        }
    }

    private void resetForAddMode() {
        try {            
            LOGGER.info("Entering inside  reset  method from ADD with tab index :" + selectedTabName);
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
            binder.getErrorDisplay().clearError();
            if (selectedTabName.equals(TabNameUtil.REBATE_SCHEDULE_INFO)) { // information tab
                TextField id = (TextField) binder.getField(FieldNameUtils.REBATE_SCHEDULE_ID);
                id.setValue(StringUtils.EMPTY);
                TextField no = (TextField) binder.getField(FieldNameUtils.REBATE_SCHEDULE_NO);
                no.setValue(StringUtils.EMPTY);
                TextField name = (TextField) binder.getField(FieldNameUtils.REBATE_SCHEDULE_NAME);
                name.setValue(StringUtils.EMPTY);
                ComboBox status = (ComboBox) binder.getField(FieldNameUtils.REBATE_SCHEDULE_STATUS);
                status.select(null);
                ComboBox type = (ComboBox) binder.getField(FieldNameUtils.REBATE_SCHEDULE_TYPE);
                type.select(null);
                ComboBox programType = (ComboBox) binder.getField(FieldNameUtils.REBATE_PROGRAM_TYPE);
                programType.select(null);
                ComboBox category = (ComboBox) binder.getField(FieldNameUtils.REBATE_SCHEDULE_CATEGORY);
                category.select(null);
                ComboBox tradeClass = (ComboBox) binder.getField(FieldNameUtils.TRADE_CLASS);
                tradeClass.select(null);
                CustomTextField parentCompany = (CustomTextField) binder.getField(FieldNameUtils.REBATE_SCHEDULE_TRANS_REF_NO);
                parentCompany.setReadOnly(false);
                parentCompany.setValue(StringUtils.EMPTY);
                parentCompany.setReadOnly(true);
                TextField refName = (TextField) binder.getField(FieldNameUtils.REBATE_SCHEDULE_TRANS_REF_NAME);
                refName.setReadOnly(false);
                refName.setValue(StringUtils.EMPTY);
                refName.setReadOnly(true);
                TextField alias = (TextField) binder.getField("rebateScheduleAlias");
                alias.setValue(StringUtils.EMPTY);
                ComboBox designation = (ComboBox) binder.getField(FieldNameUtils.REBATE_SCHEDULE_DESIGNATION);
                designation.setValue(null);
                CustomTextField parentRS = (CustomTextField) binder.getField(FieldNameUtils.PARENT_REBATE_SCHEDULE_ID);
                parentRS.setReadOnly(false);
                parentRS.setValue(StringUtils.EMPTY);
                parentRS.setReadOnly(true);
                TextField rsName = (TextField) binder.getField(FieldNameUtils.PARENT_REBATE_SCHEDULE_NAME);
                rsName.setReadOnly(false);
                rsName.setValue(StringUtils.EMPTY);
                rsName.setReadOnly(true);
                ComboBox profile = (ComboBox) binder.getField(FieldNameUtils.VALIDATION_PROFILE);
                profile.select(null);
                infoTab.setRebatePlanLevelValue(0);
                ComboBox payment = (ComboBox) binder.getField(FieldNameUtils.PAYMENT_METHOD);
                payment.select(null);
                ComboBox term = (ComboBox) binder.getField(FieldNameUtils.PAYMENT_TERM);
                term.select(null);
                ComboBox udc1 = (ComboBox) binder.getField(FieldNameUtils.UDC1);
                udc1.select(null);
                ComboBox udc4 = (ComboBox) binder.getField(FieldNameUtils.UDC4);
                udc4.select(null);
                ComboBox freq = (ComboBox) binder.getField(FieldNameUtils.REBATE_FREQUENCY);
                freq.select(null);
                ComboBox calendar = (ComboBox) binder.getField(FieldNameUtils.CALENDAR);
                calendar.select(null);
                ComboBox udc2 = (ComboBox) binder.getField(FieldNameUtils.UDC2);
                udc2.select(null);
                ComboBox udc5 = (ComboBox) binder.getField(FieldNameUtils.UDC5);
                udc5.select(null);
                ComboBox paymentFreq = (ComboBox) binder.getField(FieldNameUtils.PAYMENT_FREQUENCY);
                paymentFreq.select(null);
                ComboBox udc3 = (ComboBox) binder.getField(FieldNameUtils.UDC3);
                udc3.select(null);
                ComboBox udc6 = (ComboBox) binder.getField(FieldNameUtils.UDC6);
                udc6.select(null);
                TextField gracePeriod = (TextField) binder.getField(FieldNameUtils.PAYMENT_GRACE_PERIOD);
                gracePeriod.setValue(StringUtils.EMPTY);
                ComboBox calculationType = (ComboBox) binder.getField("calculationType");
                calculationType.select(null);
                ComboBox calculationLevel = (ComboBox) binder.getField("calculationLevel");
                calculationLevel.select(null);
                ComboBox interestBearingIndicator = (ComboBox) binder.getField("interestBearingIndicator");
                interestBearingIndicator.select(null);
                ComboBox interestBearingBasisInfo = (ComboBox) binder.getField("interestBearingBasisInfo");
                interestBearingBasisInfo.select(null);
                ComboBox rebateRuleType = (ComboBox) binder.getField("rebateRuleType");
                rebateRuleType.select(null);
                ComboBox paymentLevel = (ComboBox) binder.getField("paymentLevel");
                paymentLevel.select(null);
                ComboBox calculationRuleLevel = (ComboBox) binder.getField("calculationRuleLevel");
                calculationRuleLevel.select(null);
                ComboBox evaluationRuleType = (ComboBox) binder.getField("evaluationRuleType");
                evaluationRuleType.select(null);
                ComboBox evaluationRuleLevel = (ComboBox) binder.getField("evaluationRuleLevel");
                evaluationRuleLevel.select(null);
                TextField calculationRule = (TextField) binder.getField("calculationRule");
                calculationRule.setValue(StringUtils.EMPTY);
                TextField evaluationRuleAssociation = (TextField) binder.getField("evaluationRuleAssociation");
                evaluationRuleAssociation.setValue(StringUtils.EMPTY);
                ComboBox deductionInclusion = (ComboBox) binder.getField("deductionInclusion");
                deductionInclusion.select(null);
                
            }
            if (selectedTabName.equals(TabNameUtil.PROCESSING_OPTION)) {
                poTab.resetProcessingOptions();
            }
            if (selectedTabName.equals(TabNameUtil.RS_ITEM_ADDITION)) { // Item Addition tab
                itemAdditionTab.resetItemAdditionSearchCiteria();

                availableTable.setContainerDataSource(emptyAvailableContainer);
                availableTable.setVisibleColumns(CommonUIUtils.IFP_COLUMNS_IN_RS);
                availableTable.setColumnHeaders(CommonUIUtils.IFP_HEADER_IN_RS);
                selectedIfp.setValue(StringUtils.EMPTY);
                ImtdRsDetailsLocalServiceUtil.deleteAll(userId, sessionId, null, "All", null, null, null, null);
                rebateSetupTab.clearRebateSetupData();                
            }
            if (selectedTabName.equals(TabNameUtil.RS_REBATE_SETUP)) { // rebate setup tab                
                rebateSetupTab.setMassCheckValue(ConstantsUtils.DISABLE);
                itemResultBean.removeAllItems();
                rebateSetupTab.setDefaultOnReset(String.valueOf(binder.getField("calculationType").getValue()));
                rebateSetupTab.refreshTable();        
            }  else if (selectedTabName.equals(TabNameUtil.NOTES)) { // Notes tab
                notesTab.resetAddMode();
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
    
     private void configDeductionInclusion() {
        HelperListUtil helperListUtil = HelperListUtil.getInstance();
        List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        helperList = helperListUtil.getListNameMap().get("LOCKED_STATUS");
      
//        for (HelperDTO dto : helperList) {
//             if (dto.getDescription().equalsIgnoreCase(ConstantsUtils.YES)) {
//                infoTab.getDeductionInclusion().setValue(dto);
//                 break;
//            }
//        }
     //   binder.getField("deductionInclusion").setReadOnly(true);
    }
}
