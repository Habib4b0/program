/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.company.ui.form;

import com.stpl.app.global.common.util.CommonUtil;

import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.company.dto.CompanyCrtIdentifierDTO;
import com.stpl.app.global.company.dto.CompanyFilterGenerator;
import com.stpl.app.global.company.dto.CompanyIdentifierTableGenerator;
import com.stpl.app.global.company.logic.CompanySearchLogic;
import com.stpl.app.global.company.ui.lazyload.CompanyQualifierNameContainer;
import com.stpl.app.global.company.ui.lazyload.CompanyQualifierNameCriteria;
import com.stpl.app.global.company.ui.view.CompanyAddView;
import com.stpl.app.global.company.util.FiledNameUtils;
import com.stpl.app.global.company.util.UIUtils;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.ifp.logic.IFPLogic;
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
import com.stpl.app.util.HelperDTO;
import com.stpl.app.util.TabNameUtil;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
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
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jboss.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author shyam.duraipandian
 */
public class CompanyIdentifierForm extends StplCustomComponent {

    /**
     * The CsLayout Layout.
     */
    @UiField("cssLayout")
    CssLayout cssLayout;
    /**
     * The Horizontal Layout.
     */
    @UiField("hLayout")
    HorizontalLayout hlayout;
    /**
     * The Error Msg.
     */
    public ErrorLabel errorMsg = new ErrorLabel();
    /**
     * The identifier.
     */
    @UiField("resultsId")
    VerticalLayout resultsId;
    /**
     * The identifier Qualifier Drop down
     */
    @UiField("companyCrtQualifierNameDDLB")
    ComboBox companyCrtQualifierNameDDLB;
    /**
     * The company identifier.
     */
    @UiField(ConstantsUtils.COMPANY_IDENTIFIERID)
    TextField companyIdentifier;
    /**
     * The start date.
     */
    @UiField("startDate")
    PopupDateField startDate;
    /**
     * The end date.
     */
    @UiField("endDate")
    PopupDateField endDate;
    /**
     * The excel export.
     */
    @UiField("excelExport")
    private Button excelExport;
    /**
     * The identifier status.
     */
    @UiField(ConstantsUtils.IDENTIFIER_STATUS)
    ComboBox identifierStatus;
    //Label
    /**
     * The identifier Qualifier Drop down
     */
    @UiField("labelCompanyCrtQualifierNameDDLB")
    Label labelCompanyCrtQualifierNameDDLB;
    /**
     * The company identifier.
     */
    @UiField("labelCompanyIdentifier")
    Label labelCompanyIdentifier;
    /**
     * The start date.
     */
    @UiField("labelStartDate")
    Label labelStartDate;
    /**
     * The end date.
     */
    @UiField("labelEndDate")
    Label labelEndDate;
    /**
     * The identifier status.
     */
    @UiField("labelIdentifierStatus")
    Label labelIdentifierStatus;
    /**
     * The attach button
     */
    @UiField("attachButton")
    Button attachButton;
    /**
     * The attach button
     */
    @UiField("removeButton")
    Button removeButton;
    /**
     * The system binder.
     */
    private ErrorfulFieldGroup systemBinder;

    /**
     * The vertical Layout
     */
    @UiField("verticalLayout")
    VerticalLayout verticalLayout;
    HelperListUtil helperListUtil = HelperListUtil.getInstance();
    private HelperDTO dto = new HelperDTO(ConstantsUtils.SELECT_ONE);
    public CompanyCrtIdentifierDTO identifierFormBean = new CompanyCrtIdentifierDTO();
    ExtFilterTable table = new ExtFilterTable();
    BeanItemContainer<CompanyCrtIdentifierDTO> identifierResultsBean = new BeanItemContainer(CompanyCrtIdentifierDTO.class);
    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<>(identifierFormBean));
    private CompanySearchLogic companyLogic = new CompanySearchLogic();
    private static final Logger LOGGER = Logger.getLogger(CompanyAddView.class);
    private final Map<Integer, Boolean> reloadVerticalLayoutTabThreeMap = new HashMap<>();
    CommonUIUtils commonUiUtil = new CommonUIUtils();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    /**
     * The common util.
     */
    private CommonUtil commonUtil = CommonUtil.getInstance();
    IFPLogic ifpLogic = new IFPLogic();
    CommonUtil commmonUtil = CommonUtil.getInstance();
    SessionDTO sessionDTO;

    /**
     * To add the component
     *
     * @param binder
     * @param identifierResultsBean
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public Component getContent(ErrorfulFieldGroup binder, final BeanItemContainer<CompanyCrtIdentifierDTO> identifierResultsBean, final SessionDTO sessionDTO) throws PortalException, SystemException {
        VerticalLayout vLayout = new VerticalLayout();
        this.systemBinder = binder;
        this.identifierResultsBean = identifierResultsBean;
        this.sessionDTO = sessionDTO;
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
        final Map<String, AppPermission> fieldCompanyHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPANY_MASTER + ConstantsUtils.COMMA + ConstantsUtils.IDENTIFIER1, false);
        final Map<String, AppPermission> functionCompanyHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.COMPANY_MASTER + ConstantsUtils.COMMA + ConstantsUtils.IDENTIFIER1);
        vLayout.addComponent(Clara.create(getClass().getResourceAsStream("/clara/companyMaster/CompanyIdentifierForm.xml"), this));
        getBinder();
        addToGrid1(fieldCompanyHM, functionCompanyHM);
        configureFields();
        addResponsiveVerticalTabThreeLayout();
        return vLayout;
    }

    private ErrorfulFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setBuffered(true);
        binder.setErrorDisplay(errorMsg);
        errorMsg.setId("ErrorMessage");
        return binder;
    }

    public void disableTable() {
        table.setSelectable(false);
        table.setEditable(false);
        binder.setReadOnly(true);
        removeButton.setVisible(false);
        verticalLayout.setVisible(false);
    }

    public void configureFields() {
        binder.bindMemberFields(this);
        LOGGER.debug("configureFields");
        try {
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
            companyIdentifier.setData("maxlengthvalidationhundred,maxlengthvalidationidentifier,null,null");
            companyIdentifier.setDescription(companyIdentifier.getValue());
            companyIdentifier.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    companyIdentifier.setDescription(companyIdentifier.getValue());
                }
            });
            startDate.addStyleName(ConstantsUtils.DATE_POP_UP);
            startDate.addStyleName(ConstantsUtils.M_DATE_PICKER);
            startDate.setValidationVisible(true);
            startDate.setImmediate(true);
            startDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            startDate.setId(ConstantsUtils.IDENTIFIER_START_DATE);
            startDate.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                  startandEnddateEvent("start");
                }
            });
            endDate.addStyleName(ConstantsUtils.DATE_POP_UP);
            endDate.setValidationVisible(true);
            endDate.setImmediate(true);
            endDate.addStyleName(ConstantsUtils.M_DATE_PICKER);
            endDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            endDate.setId(ConstantsUtils.IDENTIFIER_START_DATE);
            endDate.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    startandEnddateEvent("End");
                }
            });

            commonUtil.loadComboBox(identifierStatus, UIUtils.STATUS, false);

            companyCrtQualifierNameDDLB.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                  companyCrtQualifierNameDDLBListener();
                }
            });

            excelExport.setIcon(new ThemeResource("../../icons/excel.png"));
            excelExport.setIcon(new ThemeResource("../../icons/excel.png"));
            excelExport.setStyleName("link");
            excelExport.setDescription("Export to excel");
            excelExport.setIconAlternateText("Excel export");
            excelExport.setHtmlContentAllowed(true);
            excelExport.setErrorHandler(new ErrorHandler() {
                /**
                 * Invoked when an error occurs
                 *
                 * @param event - ErrorEvent
                 */
                @SuppressWarnings("PMD")
                public void error(final com.vaadin.server.ErrorEvent event) {
                    // parses the error.
                }
            });

            resultsId.addComponent(table);

            table.addStyleName("table-header-normal");

            table.setRequired(true);

            final StplSecurity stplSecurity = new StplSecurity();
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPANY_MASTER + ConstantsUtils.COMMA + "Identifier Header", false);

            String mode = sessionDTO.getMode();
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.COMPANY_MASTER, "Identifier Header");
            Object[] obj = UIUtils.getInstance().idenFormColOrder;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldIfpHM, mode);
            if (tableResultCustom.getObjResult().length == 0) {
                table.setVisible(false);
            }
            table.setContainerDataSource(identifierResultsBean);
            table.setVisibleColumns(tableResultCustom.getObjResult());
            table.setColumnHeaders(tableResultCustom.getObjResultHeader());

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
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        validateFields(binder);
    }

    public void refreshIdentifierTable() {
        Map<Integer, String> companyQualifierMap = new HashMap<>();
        List<CompanyCrtIdentifierDTO> list = new ArrayList<>();
        try {
            companyQualifierMap = CompanySearchLogic.getCompanyQualifiers();
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex);
        }
        for (int i = 0; i < identifierResultsBean.size(); i++) {
            final CompanyCrtIdentifierDTO iden = (CompanyCrtIdentifierDTO) identifierResultsBean.getIdByIndex(i);
            String companyCrtQualifierName = companyQualifierMap.get(iden.getCompanyCrtQualifierSid());
            iden.setCompanyCrtQualifierName(companyCrtQualifierName == null ? StringUtils.EMPTY : companyCrtQualifierName);
            list.add(iden);
        }
        identifierResultsBean.removeAllItems();
        identifierResultsBean.addAll(list);
    }

    /**
     * Excel Logic
     *
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
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

    public void attachButton() {

        attachButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    LOGGER.debug("Entering inside IdentiferResults Attach  method ");
                    systemBinder.getErrorDisplay().clearError();
                    binder.getErrorDisplay().clearError();
                    binder.getFields();
                    binder.commit();
                    boolean flag = false;
                    StringBuilder errorMessage = new StringBuilder("Information for the following Mandatory fields need to be provided:" + "<br>");
                    if (binder.getField(FiledNameUtils.COMPANY_CRT_QUALIFIER_NAME_DDLB).getValue().toString().equals(ConstantsUtils.SELECT_ONE)) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Company Qualifier Name");
                        flag = true;
                    }
                    if (binder.getField(ConstantsUtils.COMPANY_IDENTIFIERID).getValue().toString().trim().isEmpty()) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Company Identifier");
                        flag = true;
                    }
                    if (binder.getField(ConstantsUtils.IDENTIFIER_STATUS).getValue() == null || StringUtils.EMPTY.equals(String.valueOf(binder.getField(ConstantsUtils.IDENTIFIER_STATUS).getValue()))) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Identifier Status");
                        flag = true;
                    }
                    if (binder.getField(ConstantsUtils.START_DATE).getValue() == null) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Start Date");
                        flag = true;
                    }
                    if (flag) {
                        systemBinder.getErrorDisplay().setError(errorMessage.toString());
                        return;
                    }
                    if (startDate.getValue() != null && endDate.getValue() != null && startDate.getValue().after(endDate.getValue())) {
                        systemBinder.getErrorDisplay().setError("End date should be greater than Start date");
                        return;
                    } else if (startDate.getValue() != null && endDate.getValue() != null && startDate.getValue().getTime() == endDate.getValue().getTime()) {
                        systemBinder.getErrorDisplay().setError("Start date and End date should not be  equal");
                        return;
                    }
                    final CompanyCrtIdentifierDTO identForm = new CompanyCrtIdentifierDTO();
                    identForm.setCompanyCrtQualifierName(String.valueOf(binder.getField(FiledNameUtils.COMPANY_CRT_QUALIFIER_NAME_DDLB).getValue()).trim());
                    HelperDTO selectedCompanyQualifier = (HelperDTO) companyCrtQualifierNameDDLB.getValue();
                    identForm.setCompanyCrtQualifierSid(selectedCompanyQualifier.getId());
                    identForm.setCompanyIdentifier(String.valueOf(binder.getField(ConstantsUtils.COMPANY_IDENTIFIERID).getValue()).trim());

                    identForm.setIdentifierStatus((com.stpl.ifs.util.HelperDTO) (binder.getField(ConstantsUtils.IDENTIFIER_STATUS).getValue()));
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
                    companyIdentifierDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
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
                            if (qual.getCompanyQualifierSid() == qualBean.getCompanyQualifierSid()) {
                                systemBinder.getErrorDisplay().setError("Company Identifier already added for this Company");
                                return;
                            }
                        }
                    }
                    identifierResultsBean.addBean(identForm);

                    resetBtnLogic();

                    LOGGER.debug("Ending IdentiferResults Attach  method ");
                } catch (FieldGroup.CommitException ex) {
                    if (ex.getCause().getMessage().trim().equals("Special Characters are not allowed")) {
                        binder.getErrorDisplay().clearError();
                        systemBinder.getErrorDisplay().setError("Special Characters are not allowed");
                        return;
                    }
                    if (ex.getCause().getMessage().equals("Identifier Should be less than 100 characters")) {
                        binder.getErrorDisplay().clearError();
                        systemBinder.getErrorDisplay().setError("Identifier Should be less than 100 characters");
                        return;
                    }
                    LOGGER.error(ex);

                } catch (Exception ex) {
                    LOGGER.error(ex);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1002), new MessageBoxListener() {
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

    public void resetBtnLogic() {
        companyCrtQualifierNameDDLB.setValue(dto);
        identifierStatus.setValue(null);
        companyIdentifier.setValue(StringUtils.EMPTY);
        startDate.setValue(null);
        endDate.setValue(null);

    }

    public void removeButton() {
        LOGGER.debug("Entering inside IdentiferResults REMOVE method ");
        removeButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                List<CompanyCrtIdentifierDTO> list = identifierResultsBean.getItemIds();
                final Object itemId = table.getValue();
                boolean flag = table.isSelected(itemId);
                systemBinder.getErrorDisplay().clearError();
                binder.getErrorDisplay().clearError();
                if (list == null || list.isEmpty()) {
                    systemBinder.getErrorDisplay().setError("Add at least one identifier in Identifier tab");
                } else if (!flag) {
                    systemBinder.getErrorDisplay().setError("Please select an identifier from the list view to remove.");

                } else {
                    MessageBox.showPlain(Icon.QUESTION, "Remove Confirmation", " Are you sure you want to Remove the Identifier?", new MessageBoxListener() {
                        /**
                         * After clicking button, function will be executed.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals("YES")) {
                                identifierResultsBean.removeItem(itemId);
                                table.unselect(itemId);
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);
                }
            }
        });

        LOGGER.debug("Ending IdentiferResults REMOVE method ");

    }

    private void addToGrid1(final Map<String, AppPermission> fieldCompanyHM, final Map<String, AppPermission> functionHM) {

        LOGGER.debug("Entering addToGrid1");
        try {
            String mode = sessionDTO.getMode();

            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.COMPANY_MASTER, ConstantsUtils.IDENTIFIER1);
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldCompanyHM, mode);
            if (functionHM.get(FiledNameUtils.ATTACH_IDENTIFIER) != null && ((AppPermission) functionHM.get(FiledNameUtils.ATTACH_IDENTIFIER)).isFunctionFlag()) {
                attachButton();
            } else {
                attachButton.setVisible(false);
            }
            if (functionHM.get(FiledNameUtils.REMOVE_IDENTIFIER) != null && ((AppPermission) functionHM.get(FiledNameUtils.REMOVE_IDENTIFIER)).isFunctionFlag()) {
                removeButton();
            } else {
                removeButton.setVisible(false);
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending addToGrid1");

    }

    @UiHandler("excelExport")
    public void excelExportButtonClick(final Button.ClickEvent event) {
        try {
            LOGGER.debug("Entering EXCEL Export Button Click :::: ");
            binder.getFields();
            excelExportLogic();
            LOGGER.debug(" Ends  EXCEL Export Button Click ::::  ");

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

    public void createWorkSheetContent(final PrintWriter printWriter) {
        CompanyCrtIdentifierDTO dto;
        final List<CompanyCrtIdentifierDTO> searchList = identifierResultsBean.getItemIds();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String date;
        for (int rowCount = 0; rowCount < searchList.size(); rowCount++) {

            dto = searchList.get(rowCount);

            printWriter.print(ConstantsUtils.QUOTE + dto.getCompanyCrtQualifierName() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getCompanyIdentifier() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getIdentifierStatus() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            if (dto.getStartDate() != null) {
                date = format.format(dto.getStartDate());
                printWriter.print(ConstantsUtils.QUOTE + date + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            } else {
                printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            }

            if (dto.getEndDate() != null) {
                date = format.format(dto.getEndDate());
                printWriter.print(ConstantsUtils.QUOTE + date + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            } else {
                printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            }
            if (dto.getCreatedDate() != null) {
                date = format.format(dto.getCreatedDate());
                printWriter.print(ConstantsUtils.QUOTE + date + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            } else {
                printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            }

            printWriter.print(ConstantsUtils.QUOTE + dto.getCreatedBy() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getModifiedBy() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            if (dto.getModifiedDate() != null) {
                date = format.format(dto.getModifiedDate());
                printWriter.println(ConstantsUtils.QUOTE + date + ConstantsUtils.QUOTE);
            } else {
                printWriter.println(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE);
            }

        }

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
                } else if (browserWidth < NumericConstants.ONE_FIVE_ONE_SIX && browserWidth > NumericConstants.THOUSAND_THREE_HUNDRED && reloadVerticalLayoutTabThreeMap.get(NumericConstants.THOUSAND_THREE_HUNDRED)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                } else if (browserWidth < NumericConstants.THOUSAND_THREE_HUNDRED && browserWidth > NumericConstants.ONE_ZERO_TWO_FOUR && reloadVerticalLayoutTabThreeMap.get(NumericConstants.ONE_ZERO_TWO_FOUR)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                } else if (browserWidth < NumericConstants.ONE_ZERO_TWO_FOUR && browserWidth > NumericConstants.NINE_SEVEN_EIGHT && reloadVerticalLayoutTabThreeMap.get(NumericConstants.NINE_SEVEN_EIGHT)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                } else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT && browserWidth > NumericConstants.EIGHT_HUNDRED && reloadVerticalLayoutTabThreeMap.get(NumericConstants.EIGHT_HUNDRED)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                } else if (browserWidth <= NumericConstants.EIGHT_HUNDRED && browserWidth > NumericConstants.FOUR_EIGHT_ZERO && reloadVerticalLayoutTabThreeMap.get(NumericConstants.FOUR_EIGHT_ZERO)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.THREE_TWO_ZERO && reloadVerticalLayoutTabThreeMap.get(NumericConstants.THREE_TWO_ZERO)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, false);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                } else if (browserWidth < NumericConstants.THREE_TWO_ZERO && reloadVerticalLayoutTabThreeMap.get(0)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(0, false);
                }

            }
        });
    }

    public void validateFields(ErrorfulFieldGroup binder) {
        Collection collection = binder.getFields();

        for (Object field : collection) {
            if (field instanceof TextField) {
                TextField textField = (TextField) field;
                commmonUtil.textValidation(textField, textField.getData());

            }
        }

    }

    public void startandEnddateEvent(String value) {
        try {
            if(value.equalsIgnoreCase("start")){
            startDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(startDate.getValue()));
            }else{
            endDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(endDate.getValue()));
            }
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
    
    public void companyCrtQualifierNameDDLBListener() {
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
}
