package com.stpl.app.global.company.ui.form;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.company.dto.CompanyCrtIdentifierDTO;
import com.stpl.app.global.company.dto.CompanyFilterGenerator;
import com.stpl.app.global.company.logic.CompanySearchLogic;
import com.stpl.app.global.company.ui.lazyload.CompanyQualifierNameContainer;
import com.stpl.app.global.company.ui.lazyload.CompanyQualifierNameCriteria;
import com.stpl.app.global.company.util.FiledNameUtils;
import com.stpl.app.global.company.util.LabelUtils;
import com.stpl.app.global.company.util.UIUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.ui.CustomGridLayout;
import com.stpl.app.ui.StplWindow;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.FunctionNameUtil;
import com.stpl.app.util.HelperDTO;
import com.stpl.app.util.ResponsiveUtils;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class IdenQualifierEditList.
 */
public final class IdenQualifierEditList extends StplWindow {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(IdenQualifierEditList.class);
    /**
     * The space.
     */
    private final Label space = new Label(ConstantsUtils.SPACE, ContentMode.HTML);
    /**
     * The companylogic.
     */
    private final CompanySearchLogic companylogic = new CompanySearchLogic();
    
    
    @UiField("errorMsg")
    private final ErrorLabel errorMsg = new ErrorLabel();
    /**
     * The qualifier table.
     */
    @UiField("qualifierTable") 
    private ExtFilterTable qualifierTable;
    
    /**
     * The company qualifier bean.
     */
    private final BeanItemContainer<CompanyCrtIdentifierDTO> companyQualifierBean = new BeanItemContainer<CompanyCrtIdentifierDTO>(
            CompanyCrtIdentifierDTO.class);
    /**
     * The identifier dto.
     */
    private final CompanyCrtIdentifierDTO identifierDTO = new CompanyCrtIdentifierDTO();
    /**
     * The company qualifier name.
     */
    private ComboBox companyQualifierName;
    /**
     * The company qualifier.
     */
    @UiField("companyQualifier")
    private TextField companyQualifier = new TextField();
    /**
     * The company crt qualifier name.
     */
    @UiField("companyCrtQualifierName")
    private TextField companyCrtQualifierName = new TextField();
    
    @UiField("effectiveDates")
    private ComboBox effectiveDates= new ComboBox();
    
    @UiField("notes")
    private TextArea notes = new TextArea();
    /**
     * The company crt qualifier id.
     */
    private final TextField companyCrtQualifierId = new TextField("Company Qualifier Id");
    /**
     * The btn save.
     */
    private final Button btnSave = new Button(ConstantsUtils.SAVE);
    /**
     * The binder.
     */
    private final ErrorfulFieldGroup binder = new ErrorfulFieldGroup(
    new BeanItem<CompanyCrtIdentifierDTO>(identifierDTO));
    private HelperDTO dto = new HelperDTO(ConstantsUtils.SELECT_ONE);

    /**
     * The btn delete.
     */
    @UiField("QualifierDelete")
    private Button QualifierDelete;
    /**
     * The btn reset.
     */
    @UiField("IdentifierRemove")
    private Button IdentifierRemove;

 
    /**
     * Gets the binder.
     *
     * @return the binder
     */
    public ErrorfulFieldGroup getBinder() {
        return binder;
    }
    
    CommonUtil commmonUtil=CommonUtil.getInstance();
    
    CompanyCrtIdentifierDTO selectedItemiden  = new CompanyCrtIdentifierDTO();

    /**
     * The Constructor to initialize the companyQualifierName.
     *
     * @param companyQualifierName the company qualifier name
     */
    public IdenQualifierEditList(final ComboBox companyQualifierName) throws SystemException, PortalException {

        super("Company Identifier Setup Pop-up.");
        this.companyQualifierName = companyQualifierName;
        companyQualifierBean.addAll(companylogic.getCompanyQualifierForEditList());
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<CompanyCrtIdentifierDTO>(new CompanyCrtIdentifierDTO()));
        binder.setBuffered(true);
        binder.setErrorDisplay(errorMsg);
        init();
    }

    /**
     * Initialize the form.
     */
    public void init() throws SystemException, PortalException {
        center();
        setClosable(true);
        setModal(true);
        setHeight("600px");
        setWidth("1600px");
        setContent(addToContent());
        configureFields();
        addToTable();
    }

    /**
     * Configure the fields.
     */
    public void configureFields() {
        LOGGER.debug("configureFields");
        addStyleName(ConstantsUtils.BOOTSTRAP);
        addStyleName(ConstantsUtils.BOOTSTRAP_BB);
        companyQualifier.setImmediate(true);
        companyQualifier.setValidationVisible(true);
        companyQualifier.addValidator(new StringLengthValidator(
                "Company Qualifier Should be less than 25 characters", 0, NumericConstants.TWENTY_FIVE, true));
        companyCrtQualifierName.setImmediate(true);
        companyCrtQualifierId.setImmediate(true);
        companyCrtQualifierName.setValidationVisible(true);
        companyCrtQualifierName.addValidator(new StringLengthValidator(
                "Company CRT Qualifier Name Should be less than 100 characters", 0, NumericConstants.HUNDRED, true));
        
        effectiveDates.setPageLength(NumericConstants.SEVEN);
        effectiveDates.setImmediate(true);
        effectiveDates.addItem(ConstantsUtils.SELECT_ONE);
        effectiveDates.addItem("Yes");
        effectiveDates.addItem("No");        
        effectiveDates.setNullSelectionAllowed(true);
        effectiveDates.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        effectiveDates.setValidationVisible(true);
       btnSave.setImmediate(true);
        
        notes.setImmediate(true);
        notes.setMaxLength(NumericConstants.THOUSAND);
        notes.addValidator(new StringLengthValidator(" New Note Should be less than 1000 characters", 0, NumericConstants.THOUSAND, true));
        notes.setInputPrompt(String.valueOf("<" + new Date() + ">"));
  
    }

    /**
     * Adds content to the form.
     *
     * @return the vertical layout
     */
    public VerticalLayout addToContent() throws SystemException, PortalException {
        final VerticalLayout content = new VerticalLayout();
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> functionCompanyHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.COMPANY_MASTER);
        content.addComponent(Clara.create(getClass().getResourceAsStream("/clara/companyMaster/CompanyQualifierForm.xml"), this));
        getResponsiveButtons(functionCompanyHM);
        return content;
    }
    public VerticalLayout addToContent1() throws SystemException, PortalException {
        final VerticalLayout content = new VerticalLayout();
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> fieldCompanyHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPANY_MASTER,false);


        content.setMargin(true);
        content.setSpacing(true);
        content.addComponentAsFirst(space);
        content.addComponent(errorMsg);
        content.addComponent(space);
        content.addComponent(space);
        content.addComponent(space);
        content.addComponent(addToForm1(fieldCompanyHM));
        content.addComponent(space);
        content.addComponent(space);
        content.addComponent(space);
        return content;
    }

    /**
     * Gets the buttons.
     *
     * @param functionCompanyHM the function company hm
     * @return the buttons
     */
    public GridLayout getButtons(final Map<String, AppPermission> functionCompanyHM) {
        final GridLayout layout = new GridLayout(NumericConstants.TWO, NumericConstants.ONE);

        layout.setSpacing(true);
        layout.setWidth(null);
        if (functionCompanyHM.get(FunctionNameUtil.QUALIFIER_SAVE) != null && ((AppPermission) functionCompanyHM.get(FunctionNameUtil.QUALIFIER_SAVE)).isFunctionFlag()) {
        }
        if (functionCompanyHM.get(FunctionNameUtil.QUALIFIER_DELETE) != null && ((AppPermission) functionCompanyHM.get(FunctionNameUtil.QUALIFIER_DELETE)).isFunctionFlag()) {
        }
        if (functionCompanyHM.get(FunctionNameUtil.QUALIFIER_RESET) != null && ((AppPermission) functionCompanyHM.get(FunctionNameUtil.QUALIFIER_RESET)).isFunctionFlag()) {
        }
        return layout;
    }

    public void getResponsiveButtons(final Map<String, AppPermission> functionCompanyHM) {
        
        if (functionCompanyHM.get(FunctionNameUtil.QUALIFIER_SAVE) != null && ((AppPermission) functionCompanyHM.get(FunctionNameUtil.QUALIFIER_SAVE)).isFunctionFlag()) {
            btnSave.setEnabled(false);
        }
        if (functionCompanyHM.get(FunctionNameUtil.QUALIFIER_DELETE) != null && ((AppPermission) functionCompanyHM.get(FunctionNameUtil.QUALIFIER_DELETE)).isFunctionFlag()) {
            QualifierDelete.setEnabled(false);
        }
        if (functionCompanyHM.get(FunctionNameUtil.QUALIFIER_RESET) != null && ((AppPermission) functionCompanyHM.get(FunctionNameUtil.QUALIFIER_RESET)).isFunctionFlag()) {
              IdentifierRemove.setEnabled(false);
        }
    }

    /**
     * Adds table to the form.
     *
     * @return the table
     */
    private void addToTable()  {
        qualifierTable.setContainerDataSource(companyQualifierBean);
        qualifierTable.setVisibleColumns(UIUtils.QUALIFIER_COMPANY);
        qualifierTable.setColumnHeaders(UIUtils.QUALIFIER_COMPANY_HEADERS);
        qualifierTable.setPageLength(NumericConstants.SEVEN);
        qualifierTable.setImmediate(true);
        qualifierTable.setSelectable(true);
        qualifierTable.setSizeFull();
        qualifierTable.setFilterBarVisible(true);
        qualifierTable.setFilterDecorator(new ExtDemoFilterDecorator());
        qualifierTable.setFilterGenerator(new CompanyFilterGenerator());

        qualifierTable.addItemClickListener(new ItemClickListener() {
            /**
             * Logic for Item click event.
             *
             * @param event
             */
            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                selectedItemiden = companyQualifierBean.getItem(event.getItemId()).getBean();
                binder.setItemDataSource(new BeanItem<CompanyCrtIdentifierDTO>(selectedItemiden));
                btnSave.setCaption(ConstantsUtils.UPDATE);
            }
        });
    }

    /**
     * Adds company qualifier and name to the form.
     *
     * @param fieldCompanyHM the field company hm
     * @return the grid layout
     */
    public GridLayout addToForm(final Map<String, AppPermission> fieldCompanyHM) {
        final CustomGridLayout grid = new CustomGridLayout(NumericConstants.TWO, NumericConstants.ONE);
        grid.setWidth(null);
        grid.setSpacing(true);
        grid.addComponentInGrid(new Label(LabelUtils.EDIT_LIST_COMPANY_CRT_QUALIFIER), companyQualifier, (fieldCompanyHM.get(FiledNameUtils.EDIT_LIST_COMPANY_CRT_QUALIFIER)) == null ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.EDIT_LIST_COMPANY_CRT_QUALIFIER)).isEditFlag());
        grid.addComponentInGrid(new Label(LabelUtils.EDIT_LIST_COMPANY_CRT_QUALIFIER_NAME), companyCrtQualifierName, (fieldCompanyHM.get(FiledNameUtils.EDIT_LIST_COMPANY_CRT_QUALIFIER_NAME)) == null ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.EDIT_LIST_COMPANY_CRT_QUALIFIER_NAME)).isEditFlag());
        return grid;

    }

    public VerticalLayout addToForm1(final Map<String, AppPermission> fieldCompanyHM) {
        
        final VerticalLayout vLayout = new VerticalLayout();
        
        final HorizontalLayout hLayout1= new HorizontalLayout();
        hLayout1.setStyleName(LabelUtils.EDIT_LIST);
        final HorizontalLayout hLayout2= new HorizontalLayout();
        hLayout2.setStyleName(LabelUtils.EDIT_LIST);
        
        final CssLayout cssLayout1 = new CssLayout();
        final CssLayout cssLayout2 = new CssLayout();
        cssLayout1.setSizeFull();
        cssLayout2.setSizeFull();
        
        ResponsiveUtils.addComponentInCsssLayout(cssLayout1, ResponsiveUtils.makeLabel(LabelUtils.EDIT_LIST_COMPANY_CRT_QUALIFIER, true), companyQualifier, (fieldCompanyHM.get(FiledNameUtils.EDIT_LIST_COMPANY_CRT_QUALIFIER)) == null ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.EDIT_LIST_COMPANY_CRT_QUALIFIER)).isEditFlag());
        ResponsiveUtils.addComponentInCsssLayout(cssLayout2, ResponsiveUtils.makeLabel(LabelUtils.EDIT_LIST_COMPANY_CRT_QUALIFIER_NAME, true), companyCrtQualifierName, (fieldCompanyHM.get(FiledNameUtils.EDIT_LIST_COMPANY_CRT_QUALIFIER_NAME)) == null ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.EDIT_LIST_COMPANY_CRT_QUALIFIER_NAME)).isEditFlag());
        hLayout1.addComponent(cssLayout1);
        hLayout2.addComponent(cssLayout2);
        
        vLayout.addComponent(hLayout1);
        vLayout.addComponent(hLayout2);

        return vLayout;

    }

    /**
     * To get the add button.
     *
     * @return the button
     */

    @UiHandler("QualifierSave")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering inside ItemQualifierEditList SAVE  method ");
                try {
                    binder.commit();
                    qualifierTable.addItem(binder.getItemDataSource());                    
                    final String companyIdentifier = binder.getField("companyQualifier").getValue().toString().trim();                    
                    if(companyIdentifier.isEmpty()){                        
                        binder.getErrorDisplay().setError("Enter Qualifier");
                        return;
                    }                    
                    
                    final String companyCrtQualifierName = binder.getField("companyCrtQualifierName").getValue().toString().trim();
                    if(companyCrtQualifierName.isEmpty()){
                        binder.getErrorDisplay().setError("Enter Qualifier Name");
                        return;
                    }                  
                    
                    if(StringUtils.isBlank(String.valueOf(binder.getField("effectiveDates").getValue())) || "null".equals(String.valueOf(binder.getField("effectiveDates").getValue()))){
                        binder.getErrorDisplay().setError("Please select Effective Dates");
                        return;
                    }  
                    
                    if (!ConstantsUtils.SAVE.equals(btnSave.getCaption()) && companylogic.checkDifferentQualifier(selectedItemiden.getCompanyCrtQualifierId(), binder.getField("companyQualifier").getValue().toString().trim())) {
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
                    Boolean recordLockStatus =  (item.getItemProperty(ConstantsUtils.RECORD_LOCKED_STATUS).getValue()==null)? false : ((Boolean)item.getItemProperty(ConstantsUtils.RECORD_LOCKED_STATUS).getValue());
                    if(recordLockStatus){
                        final MessageBox msg = MessageBox.showPlain(Icon.INFO, commmonUtil.getAccessDeniedHeaderMessage(), commmonUtil.getPermissionDeniedToEdit(), new MessageBoxListener() {
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
                    }                             
                    MessageBox.showPlain(Icon.QUESTION, commmonUtil.getHeaderMessage(), commmonUtil.getSaveMessage(companyQualifier.getValue()), new MessageBoxListener() {
                /**
                 * Called when a Button has been clicked .
                 *
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    try {
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                            companyQualifierBean.removeAllItems();
                            companyQualifierBean.addAll(companylogic.saveCrtQualifer(binder));
                            companyQualifierName.removeAllItems();
                            LazyContainer identifierTypeDescContainer = new LazyContainer(HelperDTO.class, new CompanyQualifierNameContainer(true), new CompanyQualifierNameCriteria());
                            identifierTypeDescContainer.setMinFilterLength(0);
                            companyQualifierName.setContainerDataSource(identifierTypeDescContainer);
                            companyQualifierName.setValue(dto);
                            binder.discard(); 
                            binder.setItemDataSource(new BeanItem<CompanyCrtIdentifierDTO>(new CompanyCrtIdentifierDTO()));
                            btnSave.setCaption(ConstantsUtils.SAVE);
                        }
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
                    } catch (Exception ex) {
                        LOGGER.error(ex);
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
                    }
                }
            }, ButtonId.YES, ButtonId.NO);
            LOGGER.debug("Ending ItemQualifierEditList SAVE  method ");
        } catch (FieldGroup.CommitException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
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
        }
    }

    /**
     * To get the Delete button.
     *
     * @return the button
     */
        @UiHandler("QualifierDelete")
            public void deleteButtonClick(final ClickEvent event) {
                try {
                    LOGGER.debug("Entering inside ItemQualifierEditList  DELETE  method ");
                    binder.commit();        
                    Object selectValue = qualifierTable.getValue();
                    boolean flag = qualifierTable.isSelected(selectValue);
                    Item item = (Item) binder.getItemDataSource();
                    Boolean recordLockStatus =  (item.getItemProperty(ConstantsUtils.RECORD_LOCKED_STATUS).getValue()==null)? false : ((Boolean)item.getItemProperty(ConstantsUtils.RECORD_LOCKED_STATUS).getValue());
                    if (recordLockStatus) {
                        final MessageBox msg = MessageBox.showPlain(Icon.INFO, commmonUtil.getAccessDeniedHeaderMessage(), commmonUtil.getPermissionDeniedToDelete(), new MessageBoxListener() {
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
                    }   
                    if(!flag){
                    final MessageBox msg = MessageBox.showPlain(Icon.INFO, commmonUtil.getHeaderMessage(), "Please select an Identifier from the list view to Delete.", new MessageBoxListener() {
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
                    } else {
                    MessageBox.showPlain(Icon.QUESTION, commmonUtil.getHeaderMessage(), commmonUtil.getDeleteMessage(companyQualifier.getValue()), new MessageBoxListener() {
                        /**
                         * After clicking button, function will be executed.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals(ConstantsUtils.YES)) {
                                try {
                                    List<CompanyCrtIdentifierDTO> resultList = companylogic.deleteCrtQualifer(Integer.valueOf(binder.getField("companyCrtQualifierId").getValue().toString()));
                                    if (!resultList.isEmpty()) {
                                        companyQualifierBean.removeAllItems();
                                        companyQualifierBean.addAll(resultList);
                                        companyQualifierName.removeAllItems();
                                        LazyContainer identifierTypeDescContainer = new LazyContainer(HelperDTO.class, new CompanyQualifierNameContainer(true), new CompanyQualifierNameCriteria());
                                        identifierTypeDescContainer.setMinFilterLength(0);
                                        companyQualifierName.setContainerDataSource(identifierTypeDescContainer);
                                        companyQualifierName.setValue(dto);
                                        binder.discard();
                                        binder.setItemDataSource(new BeanItem<CompanyCrtIdentifierDTO>(new CompanyCrtIdentifierDTO()));
                                    }
                                } catch (SystemException ex) {
                                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                                    LOGGER.error(errorMsg);
                                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001),errorMsg, new MessageBoxListener() {
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
                                } catch (Exception ex) {
                                    LOGGER.error(ex);
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
                                }
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);
                    LOGGER.debug("Ending ItemQualifierEditList  DELETE  method ");
                    }
                } catch (FieldGroup.CommitException ex) {
                    LOGGER.error(ex);
                } catch (Exception ex) {
                    LOGGER.error(ex);
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
                }
            }

    /**
     * To get the Reset button.
     *
     * @return the button
     */
        @UiHandler("IdentifierRemove")
            public void removeButtonClick(final ClickEvent event) {
                MessageBox.showPlain(Icon.QUESTION, commmonUtil.getHeaderMessage(), commmonUtil.getResetMessage(), new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                            LOGGER.debug("Entering inside ItemQualifierEditList RESET  method ");
                            binder.discard();
                            binder.setItemDataSource(new BeanItem<CompanyCrtIdentifierDTO>(new CompanyCrtIdentifierDTO()));
                            btnSave.setCaption(ConstantsUtils.SAVE);
                            Object selectValue = qualifierTable.getValue();
                            qualifierTable.unselect(selectValue);
                            LOGGER.debug("Ending ItemQualifierEditList RESET  method ");
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

            }
    
}
