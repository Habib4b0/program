package com.stpl.app.global.company.ui.form;

import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.company.dto.CompanyCrtIdentifierDTO;
import com.stpl.app.global.company.dto.CompanyFilterGenerator;
import com.stpl.app.global.company.dto.CompanyIdentifierTableGenerator;
import com.stpl.app.global.company.logic.CompanySearchLogic;
import com.stpl.app.global.company.ui.lazyload.CompanyQualifierNameContainer;
import com.stpl.app.global.company.ui.lazyload.CompanyQualifierNameCriteria;
import com.stpl.app.global.company.util.CommonUtils;
import com.stpl.app.global.company.util.FiledNameUtils;
import com.stpl.app.global.company.util.LabelUtils;
import com.stpl.app.global.company.util.UIUtils;
import com.stpl.app.model.CompanyIdentifier;
import com.stpl.app.model.CompanyQualifier;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.StplCustomComponent;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.ExcelExportUtil;
import com.stpl.app.util.FunctionNameUtil;
import com.stpl.app.util.HelperDTO;
import com.stpl.app.util.ResponsiveUtils;
import com.stpl.app.util.TabNameUtil;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.app.util.ValidationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
// TODO: Auto-generated Javadoc
/**
 * The Class IdentifierResults.
 */
public final class IdentifierResults extends StplCustomComponent {

    private final Map<Integer, Boolean> reloadVerticalLayoutTabThreeMap = new HashMap<>();

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(IdentifierResults.class);
    /**
     * The space.
     */
    private final Label space = new Label(ConstantsUtils.SPACE, ContentMode.HTML);
    /**
     * The error msg.
     */
    private final ErrorLabel errorMsg = new ErrorLabel();
    /**
     * The company logic.
     */
    private final CompanySearchLogic companyLogic = new CompanySearchLogic();
    /**
     * The identifier form bean.
     */
    public CompanyCrtIdentifierDTO identifierFormBean = new CompanyCrtIdentifierDTO();
    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<>(identifierFormBean));
    /**
     * The identifier results bean.
     */
    private BeanItemContainer<CompanyCrtIdentifierDTO> identifierResultsBean;
    /**
     * The btn remove.
     */
    private final Button btnRemove = new Button("Remove");
    /**
     * The system binder.
     */
    private ErrorfulFieldGroup systemBinder;
    /**
     * The company system id.
     */
    public final TextField companySystemId = new TextField();
    /**
     * The table.
     */
    private final ExtFilterTable table = new ExtFilterTable();
    /**
     * The company crt qualifier name.
     */
    private ComboBox companyCrtQualifierNameDDLB = new ComboBox();
    /**
     * The company identifier.
     */
    private final TextField companyIdentifier = new TextField();
    /**
     * The entity code.
     */
    private final CustomTextField entityCode = new CustomTextField();
    /**
     * The start date.
     */
    private final PopupDateField startDate = new PopupDateField();
    /**
     * The end date.
     */
    private final PopupDateField endDate = new PopupDateField();
    final Button excelExport = new Button(StringUtils.EMPTY);
    /**
     * The identifier status.
     */
    private final ComboBox identifierStatus = new ComboBox();
    private HelperDTO dto = new HelperDTO(ConstantsUtils.SELECT_ONE);
    HelperListUtil helperListUtil=HelperListUtil.getInstance();
    
    /**
     * The Constructor to initialize the identifierResultsBean.
     *
     * @param identifierResultsBean the identifier results bean
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public IdentifierResults(final BeanItemContainer<CompanyCrtIdentifierDTO> identifierResultsBean) throws PortalException, SystemException {
        super();
        this.identifierResultsBean = identifierResultsBean;
        init();
    }

    /**
     * Gets the identifier results bean.
     *
     * @return the identifier results bean
     */
    public BeanItemContainer<CompanyCrtIdentifierDTO> getIdentifierResultsBean() {
        return identifierResultsBean;
    }

    /**
     * Sets the identifier results bean.
     *
     * @param identifierResultsBean the identifier results bean
     */
    public void setIdentifierResultsBean(final BeanItemContainer<CompanyCrtIdentifierDTO> identifierResultsBean) {
        this.identifierResultsBean = identifierResultsBean;
    }

    /**
     * Gets the system binder.
     *
     * @return the system binder
     */
    public ErrorfulFieldGroup getSystemBinder() {
        return systemBinder;
    }

    /**
     * Sets the system binder.
     *
     * @param systemBinder the system binder
     */
    public void setSystemBinder(final ErrorfulFieldGroup systemBinder) {
        this.systemBinder = systemBinder;
    }

    /**
     * Gets the company crt qualifier name.
     *
     * @return the company crt qualifier name
     */
    public ComboBox getCompanyCrtQualifierName() {
        return companyCrtQualifierNameDDLB;
    }

    /**
     * Sets the company crt qualifier name.
     *
     * @param companyCrtQualifierName the company crt qualifier name
     */
    public void setCompanyCrtQualifierName(final ComboBox companyCrtQualifierName) {
        this.companyCrtQualifierNameDDLB = companyCrtQualifierName;
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
     * Gets the error msg.
     *
     * @return the error msg
     */
    public ErrorLabel getErrorMsg() {
        return errorMsg;
    }

    /**
     * Gets the company logic.
     *
     * @return the company logic
     */
    public CompanySearchLogic getCompanyLogic() {
        return companyLogic;
    }

    /**
     * Gets the identifier form bean.
     *
     * @return the identifier form bean
     */
    public CompanyCrtIdentifierDTO getIdentifierFormBean() {
        return identifierFormBean;
    }

    /**
     * Gets the btn remove.
     *
     * @return the btn remove
     */
    public Button getBtnRemove() {
        return btnRemove;
    }

    /**
     * Gets the table.
     *
     * @return the table
     */
    public ExtFilterTable getTable() {
        return table;
    }

    /**
     * Gets the company identifier.
     *
     * @return the company identifier
     */
    public TextField getCompanyIdentifier() {
        return companyIdentifier;
    }

    /**
     * Gets the entity code.
     *
     * @return the entity code
     */
    public TextField getEntityCode() {
        return entityCode;
    }

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    public PopupDateField getStartDate() {
        return startDate;
    }

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    public PopupDateField getEndDate() {
        return endDate;
    }

    /**
     * Gets the identifier status.
     *
     * @return the identifier status
     */
    public ComboBox getIdentifierStatus() {
        return identifierStatus;
    }

    /**
     * The Constructor to initialize the identifierResultsBean and systemBinder.
     *
     * @param systemBinder the System binder
     * @param identifierResultsBean the identifier results bean
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public IdentifierResults(final ErrorfulFieldGroup systemBinder, final BeanItemContainer<CompanyCrtIdentifierDTO> identifierResultsBean) throws PortalException, SystemException {
        super();
        this.identifierResultsBean = identifierResultsBean;
        this.systemBinder = systemBinder;
        init();
    }

    /**
     * Initialize the form.
     *
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private void init() throws PortalException, SystemException {
        space.setHeight(ConstantsUtils.HEIGHT);
        binder.bindMemberFields(this);
        binder.setBuffered(true);
        binder.setErrorDisplay(errorMsg);
        addToContent();
        configureFields();
    }

    /**
     * Adds the content to form.
     *
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    private void addToContent() throws PortalException, SystemException {
        final VerticalLayout content = new VerticalLayout();
        final HorizontalLayout horizontalLayout = new HorizontalLayout();
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> fieldCompanyHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPANY_MASTER,false);
        final Map<String, AppPermission> functionCompanyHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.COMPANY_MASTER);
        content.addComponentAsFirst(space);
        content.addComponent(errorMsg);
        content.addComponentAsFirst(space); 
        VerticalLayout vLayout=new VerticalLayout();
        vLayout.setMargin(true);
        vLayout.setSpacing(true);
        vLayout.addComponent(addToGrid1(fieldCompanyHM));
        if (functionCompanyHM.get(FunctionNameUtil.IDENTIFIER_ATTACH) != null && ((AppPermission) functionCompanyHM.get(FunctionNameUtil.IDENTIFIER_ATTACH)).isFunctionFlag()) {
            vLayout.addComponent(populateButton());
        }
        final Panel identifierSearchPanel = new Panel("Identifier Information");
        identifierSearchPanel.setContent(vLayout);
        content.addComponent(identifierSearchPanel);
        
        content.addComponent(space);
        content.addComponent(space);
        content.addComponent(ResponsiveUtils.addNaviButton(table));
        final Panel identifierResultsPanel = new Panel("Results");
        identifierResultsPanel.setContent(addToTable());
        content.addComponent(identifierResultsPanel);
        content.addComponent(space);
        horizontalLayout.addComponent(removeButton());
        horizontalLayout.addComponent(excelExport);
        content.addComponent(horizontalLayout);
        setCompositionRoot(content);
        addResponsiveVerticalTabThreeLayout();
    }

   private HorizontalLayout addToGrid1(final Map<String, AppPermission> fieldCompanyHM) {
        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.setStyleName("responsiveTabGrid");
        CssLayout cssLayout = new CssLayout();
        cssLayout.setSizeFull();

        Label label = ResponsiveUtils.makeLabel(LabelUtils.COMPANY_CRT_QUALIFIER_NAME, true,ConstantsUtils.PIXEL_ONE_EIGHT_THREE);
        label.addStyleName("mandatoryField");
          ResponsiveUtils.addComponentInCsssLayout(cssLayout, label, companyCrtQualifierNameDDLB,
                (((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_CRT_QUALIFIER_NAME)) == null) ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_CRT_QUALIFIER_NAME)).isEditFlag());
          ResponsiveUtils.addComponentInCsssLayout(cssLayout, ResponsiveUtils.makeLabel(LabelUtils.COMPANY_IDENTIFIER, true,ConstantsUtils.PIXEL_ONE_EIGHT_THREE), companyIdentifier,
                (((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_IDENTIFIER)) == null) ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_IDENTIFIER)).isEditFlag());
         ResponsiveUtils.addComponentInCsssLayout(cssLayout, ResponsiveUtils.makeLabel(LabelUtils.IDENTIFIER_STATUS, true,ConstantsUtils.PIXEL_ONE_EIGHT_THREE), identifierStatus,
                (((AppPermission) fieldCompanyHM.get(FiledNameUtils.IDENTIFIER_STATUS)) == null) ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.IDENTIFIER_STATUS)).isEditFlag());
          ResponsiveUtils.addComponentInCsssLayout(cssLayout, ResponsiveUtils.makeLabel(LabelUtils.START_DATE, true,ConstantsUtils.PIXEL_ONE_EIGHT_THREE), startDate,
                (((AppPermission) fieldCompanyHM.get(FiledNameUtils.START_DATE)) == null) ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.START_DATE)).isEditFlag());
          ResponsiveUtils.addComponentInCsssLayout(cssLayout, ResponsiveUtils.makeLabel(LabelUtils.END_DATE, false,ConstantsUtils.PIXEL_ONE_EIGHT_THREE), endDate,
                (((AppPermission) fieldCompanyHM.get(FiledNameUtils.END_DATE)) == null) ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.END_DATE)).isEditFlag());
        hLayout.addComponent(cssLayout);
        return hLayout;
    }

    /**
     * Adds the table to form.
     *
     * @return the table
     */
    public ExtFilterTable addToTable() {
        table.addStyleName("table-header-normal");
        table.setContainerDataSource(identifierResultsBean);
        table.setVisibleColumns(UIUtils.getInstance().idenFormColOrder);
        table.setColumnHeaders(UIUtils.getInstance().idenFormHeader);
        table.setPageLength(NumericConstants.THREE);
        table.setImmediate(true);
        table.setSelectable(true);
        table.setSizeFull();
        table.setTableFieldFactory(new CompanyIdentifierTableGenerator());
        table.setEditable(true);
        table.setImmediate(true);
        table.setSelectable(true);
        table.setFilterBarVisible(true);
        table.setFilterDecorator(new ExtDemoFilterDecorator());
        table.setFilterGenerator(new CompanyFilterGenerator());
        table.addStyleName(ConstantsUtils.FILTER_BAR);
        return table;
    }

    /**
     * To get the Populate button.
     *
     * @return the button
     */
    public Button populateButton() {
        final Button btnPopulate = new Button("Attach");
        btnPopulate.setWidth(ConstantsUtils.BTN_WIDTH);
        btnPopulate.addClickListener(new ClickListener() {
            /**
             * Logic for button click event.
             *
             * @param event
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.info("Entering inside IdentiferResults Attach  method ");
                    systemBinder.getErrorDisplay().clearError();
                    binder.getErrorDisplay().clearError();
                    binder.getFields();
                    binder.commit();                    
                    boolean flag = false;
                                        StringBuilder errorMessage = new StringBuilder("Information for the following Mandatory fields need to be provided:"+"<br>");
                                        if(binder.getField(FiledNameUtils.COMPANY_CRT_QUALIFIER_NAME_DDLB).getValue().toString().equals(ConstantsUtils.SELECT_ONE)){
                                            if(flag) {
                                            errorMessage.append(ConstantsUtils.COMMA);
                                        }
                                            errorMessage.append("Company Qualifier Name");
                                            flag = true;
                                        }
                                        if(binder.getField(FiledNameUtils.COMPANY_IDENTIFIER).getValue().toString().trim().isEmpty()){ 
                                            if(flag) {
                                            errorMessage.append(ConstantsUtils.COMMA);
                                        }
                                            errorMessage.append("Company Identifier");
                                            flag = true;
                                        }
                                        if(Integer.parseInt(String.valueOf(binder.getField(FiledNameUtils.IDENTIFIER_STATUS).getValue()))==0){  
                                            if(flag) {
                                            errorMessage.append(ConstantsUtils.COMMA);
                                        }
                                            errorMessage.append("Identifier Status");
                                            flag = true;
                                        }                                   
                                        if(binder.getField(ConstantsUtils.START_DATE).getValue()==null){ 
                                            if(flag) {
                                            errorMessage.append(ConstantsUtils.COMMA);
                                        }
                                            errorMessage.append("Start Date");
                                            flag = true;
                                        }
                                       if(flag) {
                                         systemBinder.getErrorDisplay().setError(errorMessage.toString());
                                        return;
                                        }
                                        if (startDate.getValue()!=null && endDate.getValue()!=null && startDate.getValue().after(endDate.getValue())) {
                                             systemBinder.getErrorDisplay().setError("End date should be greater than Start date");
                                        return;
                                        } else if (startDate.getValue()!=null && endDate.getValue()!=null && startDate.getValue().getTime() == endDate.getValue().getTime()) {
                                             systemBinder.getErrorDisplay().setError("Start date and End date should not be  equal");
                                        return;
                                        }
                    final CompanyCrtIdentifierDTO identForm = new CompanyCrtIdentifierDTO();
                    identForm.setCompanyCrtQualifierName(String.valueOf(binder.getField(FiledNameUtils.COMPANY_CRT_QUALIFIER_NAME_DDLB).getValue()).trim());
                    HelperDTO selectedCompanyQualifier = (HelperDTO)companyCrtQualifierNameDDLB.getValue();
                    identForm.setCompanyCrtQualifierSid(selectedCompanyQualifier.getId());
                    identForm.setCompanyIdentifier(String.valueOf(binder.getField(FiledNameUtils.COMPANY_IDENTIFIER).getValue()).trim());
                    identForm.setEntityCode(String.valueOf(binder.getField(ConstantsUtils.ENTITY_CODE).getValue()).trim());
                    identForm.setIdentifierStatus(helperListUtil.getIdHelperDTOMap().get(String.valueOf(binder.getField(FiledNameUtils.IDENTIFIER_STATUS).getValue())));
                    String createdBy = StplSecurity.userMap.get(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                    identForm.setCreatedBy(createdBy == null ? StringUtils.EMPTY : createdBy);
                    identForm.setCreatedDate(new Date());
                    if (binder.getField(ConstantsUtils.START_DATE).getValue() != null) {
                        identForm.setStartDate((Date) binder.getField(ConstantsUtils.START_DATE).getValue());
                    }
                    if (binder.getField(ConstantsUtils.END_DATE).getValue() != null) {
                        identForm.setEndDate((Date) binder.getField(ConstantsUtils.END_DATE).getValue());
                    }
                    final CompanyQualifier qualif = companyLogic.getCompanyCrtQualifierByQualifierName(identForm.getCompanyCrtQualifierName());
                    final DynamicQuery companyIdentifierDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyIdentifier.class);
                    companyIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq("companyIdentifierValue", identForm.getCompanyIdentifier().trim()));
                    companyIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.START_DATE, identForm.getStartDate()));
                    companyIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_QUALIFIER_SID, qualif.getCompanyQualifierSid()));
                    companyIdentifierDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS,ConstantsUtils.INBOUND_STATUS_D));
                    final List<CompanyIdentifier> companyIdentiiferList = companyLogic.getCompanyCrtIdentifierlist(companyIdentifierDynamicQuery);
                    final String systemId = systemBinder.getField(ConstantsUtils.COMPANY_SYSTEM_ID) == null || systemBinder.getField(ConstantsUtils.COMPANY_SYSTEM_ID).getValue() == null
                            || systemBinder.getField(ConstantsUtils.COMPANY_SYSTEM_ID).getValue().equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(systemBinder
                            .getField(ConstantsUtils.COMPANY_SYSTEM_ID).getValue().toString());

                    if (StringUtils.EMPTY.equals(systemId)) {
                        if (companyIdentiiferList.size() > Constants.ZERO) {
                        systemBinder.getErrorDisplay().setError("Company Identifier already exist in another Company");
                            return;
                        }
                    } else {
                        final int primaryId = Integer.parseInt(systemId);
                        companyIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_MASTER_ID, primaryId));
                        final List<CompanyIdentifier> tempList = companyLogic.getCompanyCrtIdentifierlist(companyIdentifierDynamicQuery);
                        if ((companyIdentiiferList.size() - tempList.size()) > Constants.ZERO) {
                            systemBinder.getErrorDisplay().setError("Company Identifier already exist in another Company");
                            return;
                        }
                    }
                    for (int i = 0; i < identifierResultsBean.size(); i++) {
                        if (identifierResultsBean.getIdByIndex(i).getCompanyIdentifier().equals(identForm.getCompanyIdentifier()) && identifierResultsBean.getIdByIndex(i).getStartDate().equals(identForm.getStartDate())) {
                                final CompanyQualifier qual = companyLogic.getCompanyCrtQualifierByQualifierName(identForm.getCompanyCrtQualifierName());
                                final CompanyQualifier qualBean = companyLogic.getCompanyCrtQualifierByQualifierName(identifierResultsBean.getIdByIndex(i).getCompanyCrtQualifierName());
                                if (qual.getCompanyQualifierSid()== qualBean.getCompanyQualifierSid()) {
                                   systemBinder.getErrorDisplay().setError("Company Identifier already added for this Company");
                                    return;
                            }
                        }
                    }
                    identifierResultsBean.addBean(identForm);
                    
                    resetBtnLogic();
                    
                    
                    LOGGER.debug("Ending IdentiferResults Attach  method ");
                } catch (FieldGroup.CommitException ex) {
                    if (ex.getCause().getMessage().equals("Identifier " + " " + ValidationUtils.SEARCH_SPCHAR_MSG)) {
                            binder.getErrorDisplay().clearError();
                            systemBinder.getErrorDisplay().setError("Identifier Allowed Special characters are @,*,#,.,$,&,_,-");
                            return;
                        }
                    if (ex.getCause().getMessage().equals(ValidationUtils.IDENTIFIER_VALID)) {
                            binder.getErrorDisplay().clearError();
                            systemBinder.getErrorDisplay().setError(ValidationUtils.IDENTIFIER_VALID);
                            return;
                        }
                    LOGGER.error(ex);
                    
                } catch (Exception ex) {
                    
                    LOGGER.error(ex);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1002), new MessageBoxListener() {
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
        });
        return btnPopulate;
    }

    /**
     * To get the Remove button.
     *
     * @return the button
     */
    private Button removeButton() {
        btnRemove.setWidth(ConstantsUtils.BTN_WIDTH);
        btnRemove.addClickListener(new ClickListener() {
            /**
             * Logic for button click event.
             *
             * @param event
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering inside IdentiferResults REMOVE method ");

                List<CompanyCrtIdentifierDTO> list = identifierResultsBean.getItemIds();
                final Object itemId = table.getValue();
                boolean flag=table.isSelected(itemId);
                systemBinder.getErrorDisplay().clearError();
                binder.getErrorDisplay().clearError();
                if(list==null || list.isEmpty()){
                    systemBinder.getErrorDisplay().setError("Add at least one identifier in Identifier tab");
                }else if (!flag) {
                    systemBinder.getErrorDisplay().setError("Please select an identifier from the list view to remove.");

                } else {
                    MessageBox.showPlain(Icon.QUESTION, "Remove Confirmation", " Are you sure you want to Remove the Identifier?", new MessageBoxListener() {
                        /**
                         * After clicking button, function will be executed.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals(ConstantsUtils.YES)) {
                                identifierResultsBean.removeItem(itemId);
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);                    
                }
                LOGGER.debug("Ending IdentiferResults REMOVE method ");

            }
        });
        return btnRemove;

    }

    /**
     * Configure the fields.
     *
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public void configureFields() throws PortalException, SystemException {
        LOGGER.debug("configureFields");
        startDate.addStyleName("startdateMandatory");
        startDate.setDescription(ConstantsUtils.DATE_DES);
        endDate.setDescription(ConstantsUtils.DATE_DES);
            
        
        companyCrtQualifierNameDDLB.setPageLength(NumericConstants.SEVEN);
        companyCrtQualifierNameDDLB.setImmediate(true);
        companyCrtQualifierNameDDLB.setNullSelectionAllowed(true);
        companyCrtQualifierNameDDLB.setInputPrompt(ConstantsUtils.SELECT_ONE);
        companyCrtQualifierNameDDLB.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        companyCrtQualifierNameDDLB.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        companyCrtQualifierNameDDLB.markAsDirty();
        final LazyContainer identifierTypeDescContainer = new LazyContainer(HelperDTO.class, new CompanyQualifierNameContainer(true), new CompanyQualifierNameCriteria());
        identifierTypeDescContainer.setMinFilterLength(0);
        companyCrtQualifierNameDDLB.setContainerDataSource(identifierTypeDescContainer);
        companyCrtQualifierNameDDLB.setValue(dto);

        companyIdentifier.setImmediate(true);
        companyIdentifier.setValidationVisible(true);
        companyIdentifier.addValidator(new RegexpValidator(ValidationUtils.SEARCH_SP_CHAR, "Identifier " + " " + ValidationUtils.SEARCH_SPCHAR_MSG));
        companyIdentifier.addValidator(new StringLengthValidator(ValidationUtils.IDENTIFIER_VALID, 0, NumericConstants.FIFTY, true));
        companyIdentifier.setDescription(companyIdentifier.getValue());
        companyIdentifier.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Logic for value change event.
             *
             * @param event
             */
            public void valueChange(final ValueChangeEvent event) {
                companyIdentifier.setDescription(companyIdentifier.getValue());
            }
        });
        startDate.addStyleName(ConstantsUtils.DATE_POP_UP);
        startDate.addStyleName(ConstantsUtils.M_DATE_PICKER);
        startDate.setValidationVisible(true);
        startDate.setImmediate(true);
        startDate.setDateFormat(ConstantsUtils.DATE_FORMAT);        
        startDate.setId("identifierStartDate");
        startDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Logic for value change event.
             *
             * @param event
             */
            public void valueChange(final ValueChangeEvent event) {
                    startDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(startDate.getValue()));
            }
        });
        endDate.addStyleName(ConstantsUtils.DATE_POP_UP);
        endDate.setValidationVisible(true);
        endDate.setImmediate(true);
        endDate.addStyleName(ConstantsUtils.M_DATE_PICKER);
        endDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        endDate.setId("identifierEndDate");
        endDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Logic for value change event.
             *
             * @param event
             */
            public void valueChange(final ValueChangeEvent event) {
                endDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(endDate.getValue()));                
            }
        });
        entityCode.setImmediate(true);
        entityCode.setStyleName("searchicon");
        entityCode.addValidator(new RegexpValidator(ValidationUtils.SEARCH_SP_CHAR, "Entity Code " + " " + ValidationUtils.SEARCH_SPCHAR_MSG));
        entityCode.addValidator(new StringLengthValidator(" Entity should be less than 30 characters", 0, NumericConstants.THIRTY, true));
        entityCode.setValidationVisible(true);
        entityCode.setDescription(entityCode.getValue());
        entityCode.setReadOnly(true);
        entityCode.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Logic for value change event.
             *
             * @param event
             */
            public void valueChange(final ValueChangeEvent event) {
                entityCode.setDescription(entityCode.getValue());
            }
        });
        

        
            entityCode.addClickListener(new CustomTextField.ClickListener() {

                /**
                 * Logic for focus event.
                 *
                 * @param event
                 */
                public void click(final CustomTextField.ClickEvent event) {
                    try {
                        final ParentCompanyNo lookUp = new ParentCompanyNo(entityCode);
                        UI.getCurrent().addWindow(lookUp);
                        lookUp.addCloseListener(new Window.CloseListener() {
                            /**
                             * window
                             */
                            public void windowClose(final Window.CloseEvent e) {
                                endDate.focus();
                            }
                        });
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            });        
        
        
        
        new CommonUtils().getNativeSelect(identifierStatus, companyLogic.getDropDownList(UIUtils.STATUS));
        identifierStatus.setNullSelectionAllowed(true);
        identifierStatus.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        identifierStatus.setImmediate(true);
        identifierStatus.setDescription(String.valueOf((Integer)identifierStatus.getValue()));
        identifierStatus.select(ConstantsUtils.ZERO_INT);
        identifierStatus.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Logic for value change event.
             *
             * @param event
             */
            public void valueChange(final ValueChangeEvent event) {
                identifierStatus.setDescription(String.valueOf((Integer)identifierStatus.getValue()));
            }
        });
        companyCrtQualifierNameDDLB.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Logic for value change event.
             *
             * @param event
             */
            public void valueChange(final ValueChangeEvent event) {
                try {
                    String value;
                    if (companyCrtQualifierNameDDLB.getValue() == null) {
                        value = StringUtils.EMPTY;
                    } else {
                        value = companyCrtQualifierNameDDLB.getValue().toString();
                    }
                    if (UIUtils.getInstance().EDIT_LIST.equals(value)) {
                        final IdenQualifierEditList sub = new IdenQualifierEditList(companyCrtQualifierNameDDLB);
                        UI.getCurrent().addWindow(sub);
                        final LazyContainer identifierTypeDescContainer = new LazyContainer(HelperDTO.class, new CompanyQualifierNameContainer(true), new CompanyQualifierNameCriteria());
                        identifierTypeDescContainer.setMinFilterLength(0);
                        companyCrtQualifierNameDDLB.setContainerDataSource(identifierTypeDescContainer);
                        companyCrtQualifierNameDDLB.setValue(dto);
                        
                        sub.addCloseListener(new Window.CloseListener() {
                            /**
                             * window
                             */
                            public void windowClose(final Window.CloseEvent e) {
                                refreshIdentifierTable();
                            }
                        });                                                                     
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
        });
        
        excelExport.setIcon(new ThemeResource("../../icons/excel.png"));
		excelExport.setStyleName("link");
		excelExport.setDescription("Export to excel");
		excelExport.setIconAlternateText("Excel export");
		excelExport.setHtmlContentAllowed(true);
		excelExport.setErrorHandler(new ErrorHandler() {

			/**
			 * Invoked when an error occurs
			 * 
			 * @param event
			 *            - ErrorEvent
			 */
			@SuppressWarnings("PMD")
			public void error(final com.vaadin.server.ErrorEvent event) {
				// parses the error.
			}
		});
		excelExport.addClickListener(new ClickListener() {
			/**
			 * calls excelExportLogic method on button click
			 *
			 * @param event
			 *            - Mouse Click event
			 */
			public void buttonClick(final ClickEvent event) {
				try {
					LOGGER.debug("Entering EXCEL Export Button Click :::: ");
					binder.getFields();
					excelExportLogic();
					LOGGER.debug(" Ends  EXCEL Export Button Click ::::  ");

				} catch (SystemException sysException) {
                                   final  String errorMsg = ErrorCodeUtil.getErrorMessage(sysException);
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
                                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1011), new MessageBoxListener() {
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
                                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1011), new MessageBoxListener() {
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
		});
    }
    
    protected void excelExportLogic() throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        LOGGER.debug("Entering excelExportLogic");
        createWorkSheet();
        LOGGER.debug("Ending excelExportLogic");
    }
    
    private void createWorkSheet() throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        LOGGER.debug("Entering createWorkSheet");
        final long recordCount = table.getContainerDataSource().size();
        ExcelExportforBB.createWorkSheet(table.getColumnHeaders(), recordCount, this, getUI(), TabNameUtil.IDENTIFIER);
        LOGGER.debug("Ending createWorkSheet");
    }
    
    /**
     * Logic for creating worksheet content.
     *
     * @param start the start
     * @param end the end
     * @param printWriter the print writer
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public void createWorkSheetContent(final PrintWriter printWriter) {
        CompanyCrtIdentifierDTO dto;
        final List<CompanyCrtIdentifierDTO> searchList = identifierResultsBean.getItemIds();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String date;
        for (int rowCount = 0; rowCount < searchList.size(); rowCount++) {

            dto = searchList.get(rowCount);

            printWriter.print(ConstantsUtils.QUOTE + dto.getCompanyCrtQualifierName() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getCompanyIdentifier() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE +CommonUtils.getDescription(dto.getIdentifierStatus().getId())+ ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            
            if(dto.getStartDate()!=null)   {      
                    date = format.format(dto.getStartDate());
                    printWriter.print(ConstantsUtils.QUOTE + date + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            }
            
            if(dto.getEndDate()!=null)   { 
                    date = format.format(dto.getEndDate());
                    printWriter.print(ConstantsUtils.QUOTE + date + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            }
            if(dto.getCreatedDate()!=null)   { 
                date = format.format(dto.getCreatedDate());
                printWriter.print(ConstantsUtils.QUOTE + date + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            
            }else{
                printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            }
            
            printWriter.print(ConstantsUtils.QUOTE + dto.getCreatedBy() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            
            printWriter.print(ConstantsUtils.QUOTE + dto.getModifiedBy()+ ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            if(dto.getModifiedDate()!=null)   { 
                date = format.format(dto.getModifiedDate());
                printWriter.println(ConstantsUtils.QUOTE + date+ ConstantsUtils.QUOTE);
            }else{
                printWriter.println(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE);
            }
            

        }

    }

    /**
     * The Class DateValidator to validate the start and end date.
     */
    public class DateValidator extends AbstractValidator {

        /**
         * The default Constructor.
         */
        public DateValidator() {
            super(StringUtils.EMPTY);
        }

        /**
         * The Constructor to specify the error message.
         *
         * @param errorMessage the error message
         */
        public DateValidator(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * To validate the start and end date.
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         */
        @Override
        public void validate(final Object value) {
            if (startDate.getValue() != null && endDate.getValue() != null) {
                if (startDate.getValue().after(endDate.getValue())) {
                    throw new InvalidValueException("End date should be greater than Start date");
                } else if (startDate.getValue().equals(endDate.getValue())) {
                    throw new InvalidValueException("Start date and End date should not be equal");
                }
            }

        }

        /**
         * To check the value is valid.
         *
         * @param value the value
         * @return true, if checks if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {
            if (startDate.getValue() != null && endDate.getValue() != null) {
                return startDate.getValue().compareTo(startDate.getValue()) <= 0;
            }
            return true;
        }

        /**
         * To get the type.
         *
         * @return the type
         */
        @Override
        public Class getType() {
            return null;
        }
    }

	/**
	 * Gets the binder.
	 *
	 * @return the binder
	 */
	public ErrorfulFieldGroup getBinder() {
		return binder;
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
     * Sets the identifier form bean.
     *
     * @param identifierFormBean the new identifier form bean
     */
    public void setIdentifierFormBean(final CompanyCrtIdentifierDTO identifierFormBean) {
        this.identifierFormBean = identifierFormBean;
    }

    private static Object[] getCollapsibleColumns480Px(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);

        propertyIds = list.toArray(new Object[list.size()]);

        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }

        return propertyIds;
    }

    private static Object[] getCollapsibleColumns978Px(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[NumericConstants.TWO]);
        propertyIds = list.toArray(new Object[list.size()]);
        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }
        return propertyIds;
    }

    private static Object[] getCollapsibleColumnsTwoData(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new Object[list.size()]);
        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }
        return propertyIds;
    }


    private static Object[] getCollapsibleOneColumn(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        propertyIds = list.toArray(new Object[list.size()]);
        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }

        return propertyIds;
    }

    private static Object[] getCollapsibleColumnsDefault(ExtFilterTable table) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<>(Arrays.asList(visibleColumns));
        for (int i = 0, j = list.size(); i < j; i++) {
            list.remove(propertyIds[i]);
        }
        propertyIds = list.toArray(new Object[list.size()]);

        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }

        return propertyIds;
    }

    public void addResponsiveVerticalTabThreeLayout() {

        reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
        reloadVerticalLayoutTabThreeMap.put(0, true);

        Page.getCurrent().addBrowserWindowResizeListener(
                new Page.BrowserWindowResizeListener() {
                    @Override
                    public void browserWindowResized(
                            final Page.BrowserWindowResizeEvent event) {

                                int browserWidth = Page.getCurrent().getBrowserWindowWidth();
                                if (browserWidth > NumericConstants.ONE_FIVE_ONE_SIX && reloadVerticalLayoutTabThreeMap.get(NumericConstants.ONE_FIVE_ONE_SIX)) {
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, false);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                                    reloadVerticalLayoutTabThreeMap.put(0, true);
                                    getCollapsibleColumnsDefault(table);
                                } else if (browserWidth < NumericConstants.ONE_FIVE_ONE_SIX && browserWidth > NumericConstants.THOUSAND_THREE_HUNDRED && reloadVerticalLayoutTabThreeMap.get(NumericConstants.THOUSAND_THREE_HUNDRED)) {
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, false);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                                    reloadVerticalLayoutTabThreeMap.put(0, true);
                                    getCollapsibleColumnsDefault(table);
                                } else if (browserWidth < NumericConstants.THOUSAND_THREE_HUNDRED && browserWidth > NumericConstants.ONE_ZERO_TWO_FOUR && reloadVerticalLayoutTabThreeMap.get(NumericConstants.ONE_ZERO_TWO_FOUR)) {
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, false);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                                    reloadVerticalLayoutTabThreeMap.put(0, true);
                                    getCollapsibleColumns978Px(table);
                                } else if (browserWidth < NumericConstants.ONE_ZERO_TWO_FOUR && browserWidth > NumericConstants.NINE_SEVEN_EIGHT && reloadVerticalLayoutTabThreeMap.get(NumericConstants.NINE_SEVEN_EIGHT)) {
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, false);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                                    reloadVerticalLayoutTabThreeMap.put(0, true);
                                    getCollapsibleColumns978Px(table);
                                } else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT && browserWidth > NumericConstants.EIGHT_HUNDRED && reloadVerticalLayoutTabThreeMap.get(NumericConstants.EIGHT_HUNDRED)) {
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, false);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                                    reloadVerticalLayoutTabThreeMap.put(0, true);
                                    if (table.getItemIds().isEmpty()) {
                                        getCollapsibleColumns978Px(table);
                                    } else {
                                        getCollapsibleColumnsTwoData(table);
                                    }
                                } else if (browserWidth <= NumericConstants.EIGHT_HUNDRED && browserWidth > NumericConstants.FOUR_EIGHT_ZERO && reloadVerticalLayoutTabThreeMap.get(NumericConstants.FOUR_EIGHT_ZERO)) {
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, false);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                                    reloadVerticalLayoutTabThreeMap.put(0, true);
                                    getCollapsibleColumns480Px(table);
                                } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.THREE_TWO_ZERO && reloadVerticalLayoutTabThreeMap.get(NumericConstants.THREE_TWO_ZERO)) {
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, false);
                                    reloadVerticalLayoutTabThreeMap.put(0, true);
                                    getCollapsibleOneColumn(table);
                                } else if (browserWidth < NumericConstants.THREE_TWO_ZERO && reloadVerticalLayoutTabThreeMap.get(0)) {
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                                    reloadVerticalLayoutTabThreeMap.put(0, false);
                                    getCollapsibleOneColumn(table);
                                }

                            }
                });
    }
    public void resetBtnLogic() {
        companyCrtQualifierNameDDLB.setValue(dto);
        identifierStatus.setValue(0);
        companyIdentifier.setValue(StringUtils.EMPTY);
        entityCode.setReadOnly(false);
        entityCode.setValue(StringUtils.EMPTY);
        entityCode.setReadOnly(true);
        startDate.setValue(null);
        endDate.setValue(null);

    }
    
    public void resetBtnLogic(CompanyCrtIdentifierDTO identifierDTO) {
       
        companyCrtQualifierNameDDLB.setValue(dto);
        identifierStatus.setValue(identifierDTO.getIdentifierStatus());
        companyIdentifier.setValue(identifierDTO.getCompanyIdentifier());
        entityCode.setReadOnly(false);
        entityCode.setValue(identifierDTO.getEntityCode());
        entityCode.setReadOnly(true);
        startDate.setValue(identifierDTO.getStartDate());
        endDate.setValue(identifierDTO.getEndDate());

    }
    
    public void setDefaultFocus(){
        companyCrtQualifierNameDDLB.focus();
    }
    
    public void refreshIdentifierTable() {
        Map<Integer, String> companyQualifierMap = new HashMap<>();
        List<CompanyCrtIdentifierDTO> list = new ArrayList<>();        
        try {
            companyQualifierMap = CompanySearchLogic.getCompanyQualifiers();
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        for (int i = 0; i < identifierResultsBean.size(); i++) {
            final CompanyCrtIdentifierDTO iden = (CompanyCrtIdentifierDTO) identifierResultsBean.getIdByIndex(i);
            String companyCrtQualifierName = companyQualifierMap.get(iden.getCompanyCrtQualifierSid());
            iden.setCompanyCrtQualifierName(companyCrtQualifierName==null?StringUtils.EMPTY:companyCrtQualifierName);
            list.add(iden);
        }
        identifierResultsBean.removeAllItems();
        identifierResultsBean.addAll(list);
    }
}
