
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.company.ui.form;

import com.stpl.app.global.abstractsearch.view.AbstractSearchView;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.company.dto.CompanyCrtIdentifierDTO;
import com.stpl.app.global.company.dto.CompanyMasterDTO;
import com.stpl.app.global.company.dto.FinancialCloseDTO;
import com.stpl.app.global.company.dto.SearchDTO;
import com.stpl.app.global.company.logic.CompanySearchLogic;
import com.stpl.app.global.company.logic.FinancialCloseLogic;
import com.stpl.app.global.company.ui.view.CompanyAddView;
import com.stpl.app.global.company.util.FiledNameUtils;
import com.stpl.app.global.quartz.QuartzListener;
import com.stpl.app.model.CfpContractDetails;
import com.stpl.app.model.CfpDetails;
import com.stpl.app.model.CompanyIdentifier;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.CompanyParentDetails;
import com.stpl.app.model.CompanyQualifier;
import com.stpl.app.model.ImtdCfpDetails;
import com.stpl.app.model.impl.CompanyQualifierImpl;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.CfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.CfpDetailsLocalServiceUtil;
import com.stpl.app.ui.NotesTabForm;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.FunctionNameUtil;
import com.stpl.app.util.GeneralCommonUtils;
import com.stpl.app.util.ResponsiveUtils;
import com.stpl.app.util.TabNameUtil;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.app.util.ValidationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
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
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jboss.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author shyam.duraipandian
 */
public class CompanyAddForm extends CustomComponent {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CompanyAddView.class);
    /**
     * The company logic.
     */
    @UiField("errorMsg")
    public ErrorLabel errorMsg;
    /**
     * The Information Panel.
     */
    @UiField("infoPanel")
    private Panel infoPanel;
    /**
     * The Css Layout.
     */
    @UiField("infoCssLayout")
    private CssLayout infoCssLayout;
    /**
     * The Id Test field.
     */
    @UiField("infoId")
    private TextField infoId;
    /**
     * The No Test field.
     */
    @UiField("infoNo")
    private TextField infoNo;
    /**
     * The Name Test field.
     */
    @UiField("infoName")
    private TextField infoName;
    /**
     * Id Labelcompany logic.
     */
    @UiField("labelInfoId")
    private Label labelInfoId;
    /**
     * The No Label.
     */
    @UiField("labelInfoNo")
    private Label labelInfoNo;
    @UiField("labelInfoName")
    private Label labelInfoName;
    @UiField("tabSheet")
    private TabSheet tabSheet;
     /**
     * The Save button.
     */
    @UiField("saveBtn")
    private Button saveBtn;
    @UiField("resetBtn")
    private Button resetBtn;
    /**
     * The Back Button.
     */
    @UiField("backBtn")
    private Button backBtn;
    /**
     * The delete Button.
     */
    @UiField("deleteBtn")
    private Button deleteBtn;
    /**
     * The company logic.
     */
    SessionDTO sessionDTO;
    private CompanySearchLogic companyLogic;
    /**
     * The identifier list.
     */
    private static List<CompanyCrtIdentifierDTO> identifierList = new ArrayList<CompanyCrtIdentifierDTO>();
    /**
     * The company trade list.
     */
    private static List<CompanyMasterDTO> companyTradeList = new ArrayList<CompanyMasterDTO>();
    /**
     * The company trade class list.
     */
    CompanyInformationForm compayInformation = new CompanyInformationForm();
    CompanyAddressForm companyAddress = new CompanyAddressForm();
    CompanyIdentifierForm compayIdentifier = new CompanyIdentifierForm();
    CompanyTradeClassForm compayTradeClass = new CompanyTradeClassForm();
    CompanyParentForm compayParent = new CompanyParentForm();
    CompanyMasterDTO companyMasterDTO = new CompanyMasterDTO();
    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<CompanyMasterDTO>(companyMasterDTO));
    private BeanItemContainer<CompanyCrtIdentifierDTO> identifierResultsBean = new BeanItemContainer(CompanyCrtIdentifierDTO.class);
    private BeanItemContainer<CompanyMasterDTO> tradeClassResultsBean = new BeanItemContainer(CompanyMasterDTO.class);
    private BeanItemContainer<CompanyMasterDTO> parentCompanyResultsBean = new BeanItemContainer(CompanyMasterDTO.class);

    NotesTabForm notesTabForm;
    int selectedTabIndex;
    CommonUtil commmonUtil = CommonUtil.getInstance();
    FinancialClose financialClose;
    FinancialCloseLogic financialCloseLogic = new FinancialCloseLogic();

    public CompanyAddForm(SessionDTO sessionDTO) throws PortalException, SystemException {
        super();
        setSizeFull();
        this.sessionDTO = sessionDTO;
        companyLogic = new CompanySearchLogic(sessionDTO);
        init();
    }

    /**
     * Initialization method of Company Master
     *
     * @param companyMasterDTO
     * @param binder
     * @param identifierResultsBean
     * @param tradeClassResultsBean
     * @param parentCompanyResultsBean
     */
    public CompanyAddForm(final CompanyMasterDTO companyMasterDTO, final ErrorfulFieldGroup binder,
            final BeanItemContainer<CompanyCrtIdentifierDTO> identifierResultsBean, final BeanItemContainer<CompanyMasterDTO> tradeClassResultsBean,
            final BeanItemContainer<CompanyMasterDTO> parentCompanyResultsBean, SessionDTO sessionDTO) {
        super();
        try {

            this.identifierResultsBean = identifierResultsBean;
            this.companyMasterDTO = companyMasterDTO;
            this.binder = binder;
            this.tradeClassResultsBean = tradeClassResultsBean;
            this.parentCompanyResultsBean = parentCompanyResultsBean;
            this.sessionDTO = sessionDTO;
            companyLogic = new CompanySearchLogic(sessionDTO);

            setSizeFull();
            init();

        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void init() throws PortalException, SystemException {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/common/masterTabSheet.xml"), this));
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent()
                .getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.COMPANY_MASTER + ConstantsUtils.COMMA + "Common");
        addTabsheet();
        getBinder();
        backButton();
        if (functionCfpHM.get(FunctionNameUtil.RESET) != null && ((AppPermission) functionCfpHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
            resetButton();
            resetBtn.setVisible(true);
        } else {
            resetBtn.setVisible(false);
        }

        validateFields(binder);

        if ((ConstantsUtils.VIEW).equals(sessionDTO.getMode())) {
            deleteBtn.setVisible(false);
            saveBtn.setVisible(false);
            resetBtn.setVisible(false);
            compayIdentifier.disableTable();
            compayTradeClass.disableTable();
            compayParent.disableTable();
        } else if ((ConstantsUtils.EDIT).equals(sessionDTO.getMode())) {
            saveBtn.setCaption("UPDATE");
            if (functionCfpHM.get(FunctionNameUtil.SAVE) != null && ((AppPermission) functionCfpHM.get(FunctionNameUtil.SAVE)).isFunctionFlag()) {
                updateButton();
                saveBtn.setVisible(true);
            } else {
                saveBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.DELETE) != null && ((AppPermission) functionCfpHM
                    .get(FunctionNameUtil.DELETE)).isFunctionFlag()) {

                deleteButton();
                deleteBtn.setVisible(true);
            } else {
                deleteBtn.setVisible(false);
            }

        } else if ((ConstantsUtils.ADD).equals(sessionDTO.getMode())) {
            if (functionCfpHM.get(FunctionNameUtil.SAVE) != null && ((AppPermission) functionCfpHM.get(FunctionNameUtil.SAVE)).isFunctionFlag()) {
                editButton();
                deleteBtn.setVisible(true);
            } else {
                saveBtn.setVisible(false);
            }

            deleteBtn.setVisible(false);
        }

        infoPanel.setCaption(TabNameUtil.COMPANY_INFO);
        labelInfoId.setValue(ConstantsUtils.COMPANYID);
        infoId.setValue(companyMasterDTO.getCompanyId() != null && companyMasterDTO.getCompanyId() != ConstantsUtils.NULL ? companyMasterDTO.getCompanyId() : StringUtils.EMPTY);
        infoId.setReadOnly(true);
        labelInfoNo.setValue(ConstantsUtils.COMPANYNO);
        infoNo.setValue(companyMasterDTO.getCompanyNo() != null && companyMasterDTO.getCompanyNo() != ConstantsUtils.NULL ? companyMasterDTO.getCompanyNo() : StringUtils.EMPTY);
        infoNo.setReadOnly(true);
        labelInfoName.setValue(ConstantsUtils.COMPANYNAME);
        infoName.setValue(companyMasterDTO.getCompanyName() != null && companyMasterDTO.getCompanyName() != ConstantsUtils.NULL ? companyMasterDTO.getCompanyName() : StringUtils.EMPTY);
        infoName.setReadOnly(true);
        ResponsiveUtils.addComponentInCsssLayout(infoCssLayout, ResponsiveUtils.makeLabel(labelInfoId, false), infoId, true);
        ResponsiveUtils.addComponentInCsssLayout(infoCssLayout, ResponsiveUtils.makeLabel(labelInfoNo, false), infoNo, true);
        ResponsiveUtils.addComponentInCsssLayout(infoCssLayout, ResponsiveUtils.makeLabel(labelInfoName, false), infoName, true);

    }

    public ErrorfulFieldGroup getBinder() {
        binder.setErrorDisplay(errorMsg);
        binder.bindMemberFields(this);
        binder.setBuffered(true);
        errorMsg.setId("ErrorMessage");
        binder.getField("source").setEnabled(false);
        binder.getField("systemId").setEnabled(false);
        return binder;
    }

    public void enableDisableAndInformationTab(String mode) throws PortalException, SystemException {

        compayInformation.hideColumns(mode);
        infoPanel.setCaption(TabNameUtil.COMPANY_INFO);
        labelInfoId.setValue(ConstantsUtils.COMPANYID);
        infoId.setValue(companyMasterDTO.getCompanyId() != null && companyMasterDTO.getCompanyId() != ConstantsUtils.NULL ? companyMasterDTO.getCompanyId() : StringUtils.EMPTY);
        infoId.setReadOnly(true);
        labelInfoNo.setValue(ConstantsUtils.COMPANYNO);
        infoNo.setValue(companyMasterDTO.getCompanyNo() != null && companyMasterDTO.getCompanyNo() != ConstantsUtils.NULL ? companyMasterDTO.getCompanyNo() : StringUtils.EMPTY);
        infoNo.setReadOnly(true);
        labelInfoName.setValue(ConstantsUtils.COMPANYNAME);
        infoName.setValue(companyMasterDTO.getCompanyName() != null && companyMasterDTO.getCompanyName() != ConstantsUtils.NULL ? companyMasterDTO.getCompanyName() : StringUtils.EMPTY);
        infoName.setReadOnly(true);
        ResponsiveUtils.addComponentInCsssLayout(infoCssLayout, ResponsiveUtils.makeLabel(labelInfoId, false), infoId, true);
        ResponsiveUtils.addComponentInCsssLayout(infoCssLayout, ResponsiveUtils.makeLabel(labelInfoNo, false), infoNo, true);
        ResponsiveUtils.addComponentInCsssLayout(infoCssLayout, ResponsiveUtils.makeLabel(labelInfoName, false), infoName, true);
    }

    private void editButton() {
        LOGGER.debug("Entering editButton");
        saveBtn.setWidth(ConstantsUtils.BTN_WIDTH);
        saveBtn.addClickListener(new Button.ClickListener() {
            @SuppressWarnings("PMD")
            /**
             * Constant SerialID
             */
            private static final long serialVersionUID = 1L;

            /**
             * To add the back button.
             */
            /**
             * Logic for Save button.
             *
             * @param event
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    LOGGER.debug("Entering inside  SAVE  method from ADD ");
                    binder.getErrorDisplay().clearError();
                    binder.commit();
                    identifierList = new ArrayList<CompanyCrtIdentifierDTO>();
                    companyTradeList = new ArrayList<CompanyMasterDTO>();

                    boolean flag = false;
                    boolean flag1 = false;
                    StringBuilder errorMessage = new StringBuilder("Information for the following Mandatory fields need to be provided:" + ConstantsUtils.BREAK);
                    if (binder.getField(ConstantsUtils.COMPANY_ID).getValue().toString().trim().isEmpty()) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append(ConstantsUtils.COMPANYID);
                        flag = true;
                    }
                    if (binder.getField(ConstantsUtils.COMPANY_NO).getValue().toString().trim().isEmpty()) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append(ConstantsUtils.COMPANYNO);
                        flag = true;
                    }
                    if (binder.getField(ConstantsUtils.COMPANY_NAME).getValue().toString().trim().isEmpty()) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append(ConstantsUtils.COMPANYNAME);
                        flag = true;
                    }
                    if (binder.getField(ConstantsUtils.COMPANY_STATUS).getValue() == null) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append(ConstantsUtils.COMPANYSTATUS);
                        flag = true;
                    }
                    if (binder.getField(ConstantsUtils.Company_Start_Date).getValue() == null) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append(ConstantsUtils.COMPANYSTARTDATE);
                        flag = true;
                    }
                    if (binder.getField(ConstantsUtils.COM_TYPE).getValue() == null) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append(ConstantsUtils.COMPANYTYPE);
                        flag = true;
                    }
                    if (flag) {
                        errorMessage.append(ConstantsUtils.BREAK);
                    }
                    if (identifierResultsBean != null && identifierResultsBean.size() == 0) {
                        errorMessage.append("Add at least one identifier in Identifier tab");
                        flag = true;
                        flag1 = true;
                    }
                    if (flag1) {
                        errorMessage.append(ConstantsUtils.BREAK);
                    }
                    if (tradeClassResultsBean != null && tradeClassResultsBean.size() == 0) {
                        errorMessage.append(ValidationUtils.TRADE_CLASS_VALID);
                        flag = true;
                    }
                    if (flag) {
                        binder.getErrorDisplay().setError(errorMessage.toString());
                        return;
                    }
                    List duplicateCheckInGrid = new ArrayList();
                    List duplicateDateInGrid = new ArrayList();
                    for (int j = 0; j < tradeClassResultsBean.size(); j++) {

                        CompanyMasterDTO dto = tradeClassResultsBean.getIdByIndex(j);

                        if (dto.getTradeClassSDate() != null) {
                        } else {
                            binder.getErrorDisplay().setError(
                                    "Trade Class Start Date is Mandatory in Company Trade Class Tab");
                            return;
                        }

                        if (!StringUtils.EMPTY.equals(dto.getTradeClass())) {
                        } else {
                            binder.getErrorDisplay().setError(
                                    ValidationUtils.COMP_TRADE_CLASS_VALID);
                            return;
                        }

                        if (dto.getTradeClassEDate() != null) {
                            if (dto.getTradeClassSDate() != null) {
                                Date Date1 = dto.getTradeClassSDate();
                                Date Date2 = dto.getTradeClassEDate();
                                int h = Date1.compareTo(Date2);
                                if (h == 0) {
                                    binder.getErrorDisplay().setError(
                                            "Trade class Start Date should not be equal to Trade class End Date in Company Trade Class  Tab");
                                    return;
                                } else if (h > 0) {
                                    binder.getErrorDisplay().setError("Trade class Start Date should be less than Trade Class End Date in Company Trade Class  Tab");
                                    return;
                                }
                            } else {

                                binder.getErrorDisplay().setError(
                                        "Trade class Start Date is Mandatory in Company Trade Class Tab");
                                return;
                            }

                        }

                        List companyTradeClass = null;

                        if (companyTradeClass != null && companyTradeClass.size() > 0) {
                                binder.getErrorDisplay().setError(
                                        "Please enter different Start Date.Since selected Trade Class and Start Date Combination already exists");
                                return;
                        }
                        duplicateCheckInGrid.add(String.valueOf(dto.getTradeClass1()));
                        duplicateDateInGrid.add((Date) dto.getTradeClassSDate());

                        companyTradeList.add(dto);

                    }
                    Set<String> uniqueSet = new HashSet<String>(duplicateCheckInGrid);
                    for (String temp : uniqueSet) {
                        int count = Collections.frequency(duplicateCheckInGrid, temp);
                        if (count > 1) {
                            binder.getErrorDisplay().setError("Please select different Trade class. Selected Trade Class already exists");
                            return;
                        }
                    }
                    Set<Date> dateUniqueSet = new HashSet<Date>(duplicateDateInGrid);
                    for (Date temp : dateUniqueSet) {
                        int count = Collections.frequency(duplicateDateInGrid, temp);
                        if (count > 1) {
                            binder.getErrorDisplay().setError("Please enter different Start Date. Selected Start Date already exists");
                            return;
                        }
                    }
                    if (companyTradeList.isEmpty()) {
                        binder.getErrorDisplay().setError(
                                ValidationUtils.TRADE_CLASS_VALID);
                        return;
                    }
                    /* End Fourth tab */

 /*Fifth Tab Coing */
                    final List<CompanyMasterDTO> parentCompanyList = new ArrayList<CompanyMasterDTO>();
                    List duplicateCheckInParentGrid = new ArrayList();
                    Map<Integer, CompanyMasterDTO> duplicateCheck = new HashMap<Integer, CompanyMasterDTO>();
                    for (int j = 0; j < parentCompanyResultsBean.size(); j++) {

                        CompanyMasterDTO dto = parentCompanyResultsBean.getIdByIndex(j);

                        if (dto.getParentStartDate() != null) {
                        } else {
                            binder.getErrorDisplay().setError(
                                    ValidationUtils.PARENT_COMP_START_DATE_VALID);
                            return;
                        }

                        if (dto.getParentCompanyNo() != null) {
                        } else {
                            binder.getErrorDisplay().setError(
                                    "Parent Company is Mandatory in Parent Company Tab");
                            return;
                        }

                        if (dto.getParentEndDate() != null) {
                            if (dto.getParentStartDate() != null) {
                                Date Date1 = dto.getParentStartDate();
                                Date Date2 = dto.getParentEndDate();
                                int h = Date1.compareTo(Date2);
                                if (h == 0) {
                                    binder.getErrorDisplay().setError(
                                            "Parent Company Start Date should not be equal to Parent Company  End Date in Parent Company Tab");
                                    return;
                                } else if (h > 0) {
                                    binder.getErrorDisplay().setError("Parent Company Start Date should be less than Parent Company End Date in Parent Company Tab");
                                    return;
                                }
                            } else {

                                binder.getErrorDisplay().setError(
                                        ValidationUtils.PARENT_COMP_START_DATE_VALID);
                                return;
                            }

                        }

                        duplicateCheckInParentGrid.add(dto.getParentCompanySysId());
                        if (!duplicateCheck.containsKey(dto.getParentCompanySysId())) {
                            duplicateCheck.put(dto.getParentCompanySysId(), dto);
                        }
                        parentCompanyList.add(dto);

                    }

                    Set<Integer> unique = new HashSet<Integer>(duplicateCheckInParentGrid);
                    for (int temp : unique) {
                        int count = Collections.frequency(duplicateCheckInParentGrid, temp);
                        if (count > 1) {
                            int dupcount = 0;
                            for (CompanyMasterDTO ob : parentCompanyList) {
                                if (ob.getParentCompanySysId() == temp && (duplicateCheck.get(temp).getParentStartDate()).equals(ob.getParentStartDate())) {
                                        dupcount++;
                                }
                            }
                            if (dupcount > 1) {
                                binder.getErrorDisplay().setError("Please select different Parent company. Selected Parent Company already exists");
                                return;
                            }
                        }
                    }

                    /* Fifth tab code ends here */
                    for (int i = 0; i < identifierResultsBean.size(); i++) {
                        final CompanyCrtIdentifierDTO iden = (CompanyCrtIdentifierDTO) identifierResultsBean.getIdByIndex(i);
                        if (iden.getCompanyCrtQualifierName() == null || StringUtils.isEmpty(iden.getCompanyCrtQualifierName().trim())) {
                            binder.getErrorDisplay().setError("Company Qualifier Name is Mandatory in Identifier Tab");
                            return;
                        }
                        if (iden.getStartDate() == null) {
                            binder.getErrorDisplay().setError(ValidationUtils.START_DATE_VALID);
                            return;
                        }
                        if (iden.getCompanyIdentifier() == null || StringUtils.isEmpty(iden.getCompanyIdentifier().trim())) {
                            binder.getErrorDisplay().setError("Company Identifier is Mandatory in Identifier Tab");
                            return;
                        }
                        if (StringUtils.isEmpty(String.valueOf(iden.getIdentifierStatus()))) {
                            binder.getErrorDisplay().setError("Identifier status is Mandatory in Identifier Tab");
                            return;
                        } else if (iden.getEndDate() != null) {
                            if (iden.getStartDate() == null) {
                                binder.getErrorDisplay().setError(ValidationUtils.START_DATE_VALID);
                                return;
                            } else {
                                final Date date1 = iden.getStartDate();
                                final Date date2 = iden.getEndDate();
                                final int temp = date1.compareTo(date2);
                                if (temp == GeneralCommonUtils.ZERO) {
                                    binder.getErrorDisplay().setError("Start Date should not be equal to End Date in Identifier Tab");
                                    return;
                                } else if (temp > GeneralCommonUtils.ZERO) {
                                    binder.getErrorDisplay().setError("Start Date should be less than End Date in Identifier Tab");
                                    return;
                                }
                            }
                        }

                        CompanyQualifier qualif = null;
                        qualif = companyLogic.getCompanyCrtQualifierByQualifierName(iden.getCompanyCrtQualifierName());
                        List<CompanyIdentifier> companyIdentiiferList = new ArrayList<CompanyIdentifier>();
                        final DynamicQuery companyIdentifierDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyIdentifier.class);
                        companyIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_IDENTIFIER, iden.getCompanyIdentifier().trim()));
                        companyIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.START_DATE, iden.getStartDate()));
                        companyIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_QUALIFIER_SID, qualif.getCompanyQualifierSid()));
                        companyIdentifierDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
                        companyIdentiiferList = companyLogic.getCompanyCrtIdentifierlist(companyIdentifierDynamicQuery);
                        if (!companyIdentiiferList.isEmpty()) {
                            binder.getErrorDisplay().setError(ValidationUtils.COM_IDENTIFIER_VALID);
                            return;
                        }
                        for (int k = 0; k < identifierList.size(); k++) {
                            if (identifierList.get(k).getCompanyIdentifier().equals(iden.getCompanyIdentifier()) && identifierList.get(k).getStartDate().equals(iden.getStartDate())) {
                                    CompanyQualifier qual = new CompanyQualifierImpl();
                                    qual = companyLogic.getCompanyCrtQualifierByQualifierName(iden.getCompanyCrtQualifierName());
                                    CompanyQualifier qualBean = new CompanyQualifierImpl();
                                    qualBean = companyLogic.getCompanyCrtQualifierByQualifierName(identifierList.get(k).getCompanyCrtQualifierName());
                                    if (qual.getCompanyQualifierSid() == qualBean.getCompanyQualifierSid()) {
                                        binder.getErrorDisplay().setError("Company Identifier already added for this Company");
                                        return;
                                }
                            }
                        }
                        identifierList.add(iden);

                    }
                    LOGGER.debug("before calling save method");

                    MessageBox.showPlain(Icon.QUESTION, commmonUtil.getHeaderMessage(), commmonUtil.getSaveMessage(String.valueOf(binder.getField(ConstantsUtils.COMPANY_NAME).getValue())), new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         *
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            try {
                                if (buttonId.name().equals(ConstantsUtils.YES)) {
                                    String msg = ConstantsUtils.SUCCESS;
                                    msg = companyLogic.saveCompanyMaster(binder,
                                            identifierList, companyTradeList, parentCompanyList, notesTabForm.getUploadedData(), notesTabForm.getAddedNotes(), notesTabForm.removeDetailsList());
                                    if (msg.equals(ConstantsUtils.SUCCESS)) {
                                        //This code is needed.Related to ARM Module changes
                                        if (financialClose.getModeDdlb().getValue() != null && String.valueOf(financialClose.getModeDdlb().getValue()).equalsIgnoreCase(ConstantsUtils.MANUAL)) {
                                            boolean isGLComp = ((HelperDTO) compayInformation.companyType.getValue()).getDescription().equalsIgnoreCase(ConstantsUtils.GLCOMP)
                                                    || ((HelperDTO) compayInformation.companyType.getValue()).getDescription().equalsIgnoreCase(ConstantsUtils.DIVISION)
                                                    ||((HelperDTO) compayInformation.companyType.getValue()).getDescription().equalsIgnoreCase(ConstantsUtils.BUSINESS_UNIT);
                                            BeanItemContainer container = financialClose.financialCloseTableLogic.getResultsContainer();
                                            if (isGLComp && container.size() != 0) {
                                                FinancialCloseDTO binderDto = financialClose.financialCloseTableLogic.getBinderDto();
                                                binderDto.setCompanyMasterSid(sessionDTO.getSystemId());
                                                financialCloseLogic.saveManualStatus(binderDto, container);
                                            }
                                        } else {
                                            boolean isGLComp = ((HelperDTO) compayInformation.companyType.getValue()).getDescription().equalsIgnoreCase(ConstantsUtils.GLCOMP)
                                                    ||((HelperDTO) compayInformation.companyType.getValue()).getDescription().equalsIgnoreCase(ConstantsUtils.DIVISION)
                                                    || ((HelperDTO) compayInformation.companyType.getValue()).getDescription().equalsIgnoreCase(ConstantsUtils.BUSINESS_UNIT);
                                            if (isGLComp && financialClose.financialCloseTableLogic.getBinderDto()!=null) {
                                                FinancialCloseDTO binderDto = financialClose.financialCloseTableLogic.getBinderDto();
                                                List<FinancialCloseDTO> addModeList = financialClose.financialCloseTableLogic.getBinderDto().getAddMode_Container_MainList();
                                                if (addModeList != null && !addModeList.isEmpty()) {
                                                binderDto.setCompanyMasterSid(sessionDTO.getSystemId());
                                                financialCloseLogic.saveAutoMode(binderDto, addModeList);
                                                QuartzListener.scheduleCompanyFinancialClose();
                                                }
                                            }
                                        }
                                        if (ConstantsUtils.ADD.equalsIgnoreCase(sessionDTO.getMode())) {
                                            companyLogic.insertToCPDetails(sessionDTO.getSystemId());
                                        }
                                        final Notification notif = new Notification(commmonUtil.getSavedSuccessfulMessage(String.valueOf(binder.getField(ConstantsUtils.COMPANY_ID).getValue()), String.valueOf(binder.getField(ConstantsUtils.COMPANY_NAME).getValue())), Notification.Type.HUMANIZED_MESSAGE);
                                        notif.setPosition(Position.MIDDLE_CENTER);
                                        notif.setStyleName(ConstantsUtils.MY_STYLE);
                                        notif.show(Page.getCurrent());
                                        notif.setDelayMsec(NumericConstants.TWO_THOUSAND);
                                        sessionDTO.setMode(ConstantsUtils.EDIT);
                                        getUI().getNavigator().navigateTo(CompanyAddView.NAME);
                                        binder.discard();
                                        binder = new ErrorfulFieldGroup(new BeanItem<CompanyMasterDTO>(new CompanyMasterDTO()));
                                    } else if ("duplicateNo".equals(msg)) {
                                        binder.getErrorDisplay().setError("Company No already exists.");
                                    } else if (ConstantsUtils.DUPLICATE.equals(msg)) {
                                        binder.getErrorDisplay().setError("Company ID already exists.");
                                    } else if ("duplicateIdentifier".equals(msg)) {
                                        binder.getErrorDisplay().setError("Company Identifier already exists.");
                                    }
                                }
                            } catch (Exception e) {
                                LOGGER.error(e);
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);

                    LOGGER.debug("after calling save method");

                    LOGGER.debug("Ending  SAVE  method from ADD");
                } catch (FieldGroup.CommitException commitEx) {
                    LOGGER.error(commitEx);

                } catch (Exception ex) {
                    LOGGER.error(ex);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1003), new MessageBoxListener() {
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
        LOGGER.debug("Ending editButton");
    }

    /**
     * Back button.
     */
    private void backButton() {
        backBtn.setWidth(ConstantsUtils.BTN_WIDTH);
        backBtn.addClickListener(new Button.ClickListener() {
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
                LOGGER.debug("Entering inside  BACK  method from ADD ");
                if (sessionDTO.getMode().equals(ConstantsUtils.VIEW)) {
                    binder.discard();
                    binder = new ErrorfulFieldGroup(new BeanItem<SearchDTO>(new SearchDTO()));
                    AbstractSearchView.flag = false;
                    getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                } else {
                    MessageBox.showPlain(Icon.WARN,
                            commmonUtil.getHeaderMessage(), commmonUtil.getBackMessage(),
                            new MessageBoxListener() {
                        /**
                         * Yes/No Button logic
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals(ConstantsUtils.YES)) {
                                binder.discard();
                                binder = new ErrorfulFieldGroup(new BeanItem<SearchDTO>(new SearchDTO()));
                                AbstractSearchView.flag = true;
                                getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                            }
                        }
                    },
                            ButtonId.YES,
                            ButtonId.NO);

                    LOGGER.debug("Ending  BACK  method from ADD");
                }
            }
        });
        backBtn.setErrorHandler(new ErrorHandler() {
            /**
             * Constant SerialID
             */
            private static final long serialVersionUID = 1L;

            /**
             * Empty block.
             *
             * @param event
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
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
                    LOGGER.debug("Entering inside  reset  method from ADD ");
                    MessageBox.showPlain(Icon.QUESTION, commmonUtil.getHeaderMessage(), commmonUtil.getResetMessage(), new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         *
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            try {
                                String mode = sessionDTO.getMode();
                                if ((ConstantsUtils.ADD).equals(mode)) {
                                    if (buttonId.name().equals(ConstantsUtils.YES)) {
                                        if (selectedTabIndex == 0) {
                                            TextField id = (TextField) binder.getField(FiledNameUtils.COMPANY_ID);
                                            id.setValue(StringUtils.EMPTY);
                                            TextField no = (TextField) binder.getField(FiledNameUtils.COMPANY_NO);
                                            no.setValue(StringUtils.EMPTY);
                                            TextField name = (TextField) binder.getField(FiledNameUtils.COMPANY_NAME);
                                            name.setValue(StringUtils.EMPTY);
                                            ComboBox status = (ComboBox) binder.getField(FiledNameUtils.COMPANY_STATUS);
                                            status.setValue(null);
                                            PopupDateField startDate = (PopupDateField) binder.getField(FiledNameUtils.COMPANY_START_DATE);
                                            startDate.setValue(null);
                                            PopupDateField endDate = (PopupDateField) binder.getField(FiledNameUtils.COMPANY_END_DATE);
                                            endDate.setValue(null);
                                            ComboBox type = (ComboBox) binder.getField(FiledNameUtils.COMPANY_TYPE);
                                            type.setValue(null);
                                            ComboBox group = (ComboBox) binder.getField(FiledNameUtils.COMPANY_GROUP);
                                            group.setValue(null);
                                            ComboBox category = (ComboBox) binder.getField(FiledNameUtils.COMPANY_CATEGORY);
                                            category.setValue(null);
                                            ComboBox udc1 = (ComboBox) binder.getField(FiledNameUtils.UDC1);
                                            udc1.setValue(null);
                                            ComboBox udc2 = (ComboBox) binder.getField(FiledNameUtils.UDC2);
                                            udc2.setValue(null);
                                            ComboBox udc3 = (ComboBox) binder.getField(FiledNameUtils.UDC3);
                                            udc3.setValue(null);
                                            ComboBox udc4 = (ComboBox) binder.getField(FiledNameUtils.UDC4);
                                            udc4.setValue(null);
                                            ComboBox udc5 = (ComboBox) binder.getField(FiledNameUtils.UDC5);
                                            udc5.setValue(null);
                                            ComboBox udc6 = (ComboBox) binder.getField(FiledNameUtils.UDC6);
                                            udc6.setValue(null);
                                            ComboBox orgKey = (ComboBox) binder.getField(FiledNameUtils.ORGANIZATION_KEY);
                                            orgKey.setValue(null);
                                            TextField financialSys = (TextField) binder.getField(FiledNameUtils.FINANCIAL_SYSTEM);
                                            financialSys.setValue(StringUtils.EMPTY);
                                            TextField lives = (TextField) binder.getField(FiledNameUtils.LIVES);
                                            lives.setValue(StringUtils.EMPTY);
                                            TextField regCode = (TextField) binder.getField(FiledNameUtils.REGION_CODE);
                                            regCode.setValue(StringUtils.EMPTY);
                                        }
                                        if (selectedTabIndex == 1) {
                                            TextField address1 = (TextField) binder.getField(FiledNameUtils.ADDRESS1);
                                            address1.setValue(StringUtils.EMPTY);
                                            TextField address2 = (TextField) binder.getField(FiledNameUtils.ADDRESS2);
                                            address2.setValue(StringUtils.EMPTY);
                                            TextField city = (TextField) binder.getField(FiledNameUtils.CITY);
                                            city.setValue(StringUtils.EMPTY);
                                            TextField zipCode = (TextField) binder.getField(FiledNameUtils.ZIP_CODE);
                                            zipCode.setValue(StringUtils.EMPTY);
                                            ComboBox state = (ComboBox) binder.getField(FiledNameUtils.STATE);
                                            state.setValue(null);
                                            ComboBox country = (ComboBox) binder.getField(FiledNameUtils.COUNTRY);
                                            country.setValue(null);
                                            TextField regionCode = (TextField) binder.getField(FiledNameUtils.REGION_CODE);
                                            regionCode.setValue(StringUtils.EMPTY);
                                        }
                                        if (selectedTabIndex == NumericConstants.TWO) {
                                            compayIdentifier.resetBtnLogic();
                                            identifierResultsBean.removeAllItems();
                                        }
                                        if (selectedTabIndex == NumericConstants.THREE) {
                                            ComboBox tradeClass = (ComboBox) binder.getField("tradeClass1");
                                            tradeClass.setValue(null);
                                            PopupDateField sDate = (PopupDateField) binder.getField("tradeClassSDate");
                                            sDate.setValue(null);
                                            PopupDateField eDate = (PopupDateField) binder.getField("tradeClassEDate");
                                            eDate.setValue(null);
                                            tradeClassResultsBean.removeAllItems();
                                        }
                                        if (selectedTabIndex == NumericConstants.FOUR) {
                                            CustomTextField parentCompany = (CustomTextField) binder.getField("parentCompanyNo");
                                            parentCompany.setReadOnly(false);
                                            parentCompany.setValue(StringUtils.EMPTY);
                                            parentCompany.setReadOnly(true);
                                            PopupDateField sDate = (PopupDateField) binder.getField(ConstantsUtils.PARENT_START_DATE);
                                            sDate.setValue(null);
                                            PopupDateField eDate = (PopupDateField) binder.getField("parentEndDate");
                                            eDate.setValue(null);
                                            parentCompanyResultsBean.removeAllItems();
                                        }
                                        if (selectedTabIndex == NumericConstants.FIVE) {
                                            financialClose.resetAddMode();
                                        }
                                        if (selectedTabIndex == NumericConstants.SIX) {
                                            notesTabForm.resetAddMode();
                                        }
                                    }
                                } else if ((ConstantsUtils.EDIT).equals(mode)) {

                                    final int systemId = sessionDTO.getSystemId();
                                    companyMasterDTO = companyLogic.getCompanyMasterById(Integer.valueOf(systemId));
                                    if (selectedTabIndex == 0) {
                                        TextField id = (TextField) binder.getField(FiledNameUtils.COMPANY_ID);
                                        id.setValue(companyMasterDTO.getCompanyId());
                                        TextField no = (TextField) binder.getField(FiledNameUtils.COMPANY_NO);
                                        no.setValue(companyMasterDTO.getCompanyNo());
                                        TextField name = (TextField) binder.getField(FiledNameUtils.COMPANY_NAME);
                                        name.setValue(companyMasterDTO.getCompanyName());
                                        ComboBox status = (ComboBox) binder.getField(FiledNameUtils.COMPANY_STATUS);
                                        status.setValue(companyMasterDTO.getCompanyStatus());
                                        PopupDateField startDate = (PopupDateField) binder.getField(FiledNameUtils.COMPANY_START_DATE);
                                        startDate.setValue(companyMasterDTO.getCompanyStartDate());
                                        PopupDateField endDate = (PopupDateField) binder.getField(FiledNameUtils.COMPANY_END_DATE);
                                        endDate.setValue(companyMasterDTO.getCompanyEndDate());
                                        ComboBox type = (ComboBox) binder.getField(FiledNameUtils.COMPANY_TYPE);
                                        type.setValue(companyMasterDTO.getCompanyType());
                                        ComboBox group = (ComboBox) binder.getField(FiledNameUtils.COMPANY_GROUP);
                                        group.setValue(companyMasterDTO.getCompanyGroup());
                                        ComboBox category = (ComboBox) binder.getField(FiledNameUtils.COMPANY_CATEGORY);
                                        category.setValue(companyMasterDTO.getCompanyCategory());
                                        ComboBox udc1 = (ComboBox) binder.getField(FiledNameUtils.UDC1);
                                        udc1.setValue(companyMasterDTO.getUdc1());
                                        ComboBox udc2 = (ComboBox) binder.getField(FiledNameUtils.UDC2);
                                        udc2.setValue(companyMasterDTO.getUdc2());
                                        ComboBox udc3 = (ComboBox) binder.getField(FiledNameUtils.UDC3);
                                        udc3.setValue(companyMasterDTO.getUdc3());
                                        ComboBox udc4 = (ComboBox) binder.getField(FiledNameUtils.UDC4);
                                        udc4.setValue(companyMasterDTO.getUdc4());
                                        ComboBox udc5 = (ComboBox) binder.getField(FiledNameUtils.UDC5);
                                        udc5.setValue(companyMasterDTO.getUdc5());
                                        ComboBox udc6 = (ComboBox) binder.getField(FiledNameUtils.UDC6);
                                        udc6.setValue(companyMasterDTO.getUdc6());
                                        ComboBox orgKey = (ComboBox) binder.getField(FiledNameUtils.ORGANIZATION_KEY);
                                        orgKey.setValue(companyMasterDTO.getOrganizationKey());
                                        TextField financialSys = (TextField) binder.getField(FiledNameUtils.FINANCIAL_SYSTEM);
                                        financialSys.setValue(companyMasterDTO.getFinancialSystem());
                                        TextField lives = (TextField) binder.getField(FiledNameUtils.LIVES);
                                        lives.setValue(companyMasterDTO.getLives());
                                        TextField regCode = (TextField) binder.getField(FiledNameUtils.REGION_CODE);
                                        regCode.setValue(companyMasterDTO.getRegionCode());
                                    } else if (selectedTabIndex == 1) {
                                        TextField address1 = (TextField) binder.getField(FiledNameUtils.ADDRESS1);
                                        address1.setValue(companyMasterDTO.getAddress1());
                                        TextField address2 = (TextField) binder.getField(FiledNameUtils.ADDRESS2);
                                        address2.setValue(companyMasterDTO.getAddress2());
                                        TextField city = (TextField) binder.getField(FiledNameUtils.CITY);
                                        city.setValue(companyMasterDTO.getCity());
                                        TextField zipCode = (TextField) binder.getField(FiledNameUtils.ZIP_CODE);
                                        zipCode.setValue(companyMasterDTO.getZipCode());
                                        ComboBox state = (ComboBox) binder.getField(FiledNameUtils.STATE);
                                        state.setValue(companyMasterDTO.getState());
                                        ComboBox country = (ComboBox) binder.getField(FiledNameUtils.COUNTRY);
                                        country.setValue(companyMasterDTO.getCountry());
                                    } else if (selectedTabIndex == NumericConstants.TWO) {
                                        compayIdentifier.resetBtnLogic();
                                        identifierResultsBean.removeAllItems();
                                        identifierResultsBean.addAll(companyMasterDTO.getCompanyIdentifierList());
                                    } else if (selectedTabIndex == NumericConstants.THREE) {
                                        ComboBox tradeClass = (ComboBox) binder.getField("tradeClass1");
                                        tradeClass.setValue(companyMasterDTO.getTradeClass1());
                                        PopupDateField sDate = (PopupDateField) binder.getField("tradeClassSDate");
                                        sDate.setValue(companyMasterDTO.getTradeClassSDate());
                                        PopupDateField eDate = (PopupDateField) binder.getField("tradeClassEDate");
                                        eDate.setValue(companyMasterDTO.getTradeClassEDate());
                                        tradeClassResultsBean.removeAllItems();
                                        tradeClassResultsBean.addAll(companyLogic.getTradeClassTable(systemId));
                                    } else if (selectedTabIndex == NumericConstants.FOUR) {
                                        CustomTextField parentCompany = (CustomTextField) binder.getField("parentCompanyNo");
                                        parentCompany.setReadOnly(false);
                                        parentCompany.setValue(companyMasterDTO.getParentCompanyNo());
                                        parentCompany.setReadOnly(true);
                                        TextField parentCompanyName = (TextField) binder.getField("parentCompanyName");
                                        parentCompanyName.setReadOnly(false);
                                        parentCompanyName.setValue(companyMasterDTO.getParentCompanyNo());
                                        parentCompanyName.setReadOnly(true);
                                        PopupDateField sDate = (PopupDateField) binder.getField(ConstantsUtils.PARENT_START_DATE);
                                        sDate.setValue(companyMasterDTO.getParentStartDate());
                                        PopupDateField eDate = (PopupDateField) binder.getField("parentEndDate");
                                        eDate.setValue(companyMasterDTO.getParentEndDate());
                                        parentCompanyResultsBean.removeAllItems();
                                        parentCompanyResultsBean.addAll(companyLogic.getParentCompanyTable(systemId));
                                    } else if (selectedTabIndex == NumericConstants.FIVE) {
                                        financialClose.resetLogic();
                                    } else if (selectedTabIndex == NumericConstants.SIX) {
                                        notesTabForm.resetBtnLogic(companyMasterDTO.getInternalNotes());
                                    }

                                }
                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);

                }
            });
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void updateButton() {
        saveBtn.setWidth(ConstantsUtils.BTN_WIDTH);
        saveBtn.addClickListener(new Button.ClickListener() {
            /**
             * logic for edit button click event.
             *
             * @param event
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    LOGGER.debug("Entering inside  UPDATE  method from EDIT ");
                    binder.getErrorDisplay().clearError();
                    binder.getFields();

                    binder.commit();

                    final List<CompanyCrtIdentifierDTO> identifierList = new ArrayList<CompanyCrtIdentifierDTO>();
                    companyTradeList = new ArrayList<CompanyMasterDTO>();
                    binder.getErrorDisplay().clearError();

                    boolean flag = false;
                    boolean flag1 = false;
                    StringBuilder errorMessage = new StringBuilder("Information for the following Mandatory fields need to be provided:" + ConstantsUtils.BREAK);
                    if (binder.getField(ConstantsUtils.COMPANY_ID).getValue().toString().trim().isEmpty()) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append(ConstantsUtils.COMPANYID);
                        flag = true;
                    }
                    if (binder.getField(ConstantsUtils.COMPANY_NO).getValue().toString().trim().isEmpty()) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append(ConstantsUtils.COMPANYNO);
                        flag = true;
                    }
                    if (binder.getField(ConstantsUtils.COMPANY_NAME).getValue().toString().trim().isEmpty()) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append(ConstantsUtils.COMPANYNAME);
                        flag = true;
                    }
                    if (binder.getField(ConstantsUtils.COMPANY_STATUS).getValue() == null) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append(ConstantsUtils.COMPANYSTATUS);
                        flag = true;
                    }
                    if (binder.getField(ConstantsUtils.Company_Start_Date).getValue() == null) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append(ConstantsUtils.COMPANYSTARTDATE);
                        flag = true;
                    }
                    if (binder.getField(ConstantsUtils.COM_TYPE).getValue() == null) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append(ConstantsUtils.COMPANYTYPE);
                        flag = true;
                    }
                    if (flag) {
                        errorMessage.append(ConstantsUtils.BREAK);
                    }
                    if (identifierResultsBean != null && identifierResultsBean.size() < 1) {
                        errorMessage.append("Add at least one identifier in Identifier tab");
                        flag = true;
                        flag1 = true;
                    }
                    if (flag1) {
                        errorMessage.append(ConstantsUtils.BREAK);
                    }

                    if (tradeClassResultsBean != null && tradeClassResultsBean.size() == 0) {
                        errorMessage.append(ValidationUtils.TRADE_CLASS_VALID);
                        flag = true;
                    }
                    if (flag) {
                        binder.getErrorDisplay().setError(errorMessage.toString());
                        return;
                    }
                    /* Fouth tab chanes */

                    String systemId = (binder.getField(ConstantsUtils.COMPANY_SYSTEM_ID) != null && binder.getField(ConstantsUtils.COMPANY_SYSTEM_ID)
                            .getValue() != null && !binder
                            .getField(ConstantsUtils.COMPANY_SYSTEM_ID).getValue().equals(ConstantsUtils.NULL)) ? String
                            .valueOf(binder.getField(ConstantsUtils.COMPANY_SYSTEM_ID)
                                    .getValue().toString()) : StringUtils.EMPTY;

                    List duplicateCheckInGrid = new ArrayList();
                    List duplicateDateInGrid = new ArrayList();
                    for (int j = 0; j < tradeClassResultsBean.size(); j++) {

                        CompanyMasterDTO dto = tradeClassResultsBean.getIdByIndex(j);
                        if (dto.getTradeClassSDate() != null) {
                        } else {
                            binder.getErrorDisplay().setError(
                                    "Trade Class Start Date is Mandatory in Company Trade Class Tab");
                            return;
                        }

                        if (dto.getTradeClass1().getId() != 0) {
                        } else {
                            binder.getErrorDisplay().setError(
                                    ValidationUtils.COMP_TRADE_CLASS_VALID);
                            return;
                        }

                        if (dto.getPriorTradeClass() != null) {
                        } else {
                            binder.getErrorDisplay().setError(
                                    ValidationUtils.COMP_TRADE_CLASS_VALID);
                            return;
                        }

                        if (dto.getTradeClassEndDate() != null) {
                            if (dto.getTradeClassSDate() != null) {
                                Date Date1 = dto.getTradeClassSDate();
                                Date Date2 = dto.getTradeClassEndDate();
                                int h = Date1.compareTo(Date2);
                                if (h == 0) {
                                    binder.getErrorDisplay().setError(
                                            "Trade class Start Date should not be equal to Trade class End Date in Company tradeClass Tab");
                                    return;
                                } else if (h > 0) {
                                    binder.getErrorDisplay().setError("Trade class Start Date should be less than Trade Class  End Date in Company Trade Class  Tab");
                                    return;
                                }
                            } else {

                                binder.getErrorDisplay().setError(
                                        "Trade class Start Date is Mandatory in Company Trade Class Tab");
                                return;
                            }

                        }

                        List<CompanyMasterDTO> companyTradeClass = new ArrayList<CompanyMasterDTO>();

                        duplicateCheckInGrid.add(String.valueOf(dto.getTradeClass1()));
                        duplicateDateInGrid.add((Date) dto.getTradeClassSDate());
                        if (companyTradeClass != null) {
                            for (int k = 0; k < companyTradeClass.size(); k++) {
                                if (Integer.parseInt(systemId) != Integer.valueOf(companyTradeClass.get(k).getCompanySystemId())) {
                                    binder.getErrorDisplay().setError("Please enter different Start Date.Since selected Trade Class and Start Date Combination already exists");
                                    return;
                                }
                            }

                        }
                        companyTradeList.add(dto);

                    }
                    if (companyTradeList.isEmpty()) {
                        binder.getErrorDisplay().setError(
                                "Parent tab should contains record");
                    }
                    Set<String> uniqueSet = new HashSet<String>(duplicateCheckInGrid);
                    for (String temp : uniqueSet) {
                        int count = Collections.frequency(duplicateCheckInGrid, temp);
                        if (count > 1) {
                            binder.getErrorDisplay().setError("Please select diffent Trade Class. Selected Trade Class already exists");
                            return;
                        }
                    }
                    Set<Date> dateUniqueSet = new HashSet<Date>(duplicateDateInGrid);
                    for (Date temp : dateUniqueSet) {
                        int count = Collections.frequency(duplicateDateInGrid, temp);
                        if (count > 1) {
                            binder.getErrorDisplay().setError("Please enter different Start Date at Trade Class Tab. Since selected Start Date already exists");
                            return;
                        }
                    }

                    String sysId = (binder.getField(ConstantsUtils.COMPANY_SYSTEM_ID) != null && binder.getField(ConstantsUtils.COMPANY_SYSTEM_ID)
                            .getValue() != null && !binder
                            .getField(ConstantsUtils.COMPANY_SYSTEM_ID).getValue().equals(ConstantsUtils.NULL)) ? String
                            .valueOf(binder.getField(ConstantsUtils.COMPANY_SYSTEM_ID)
                                    .getValue().toString()) : StringUtils.EMPTY;
                    final List<CompanyMasterDTO> parentCompanyList = new ArrayList<CompanyMasterDTO>();
                    List duplicateCheckInParentGrid = new ArrayList();
                    Map<Integer, CompanyMasterDTO> duplicateCheck = new HashMap<Integer, CompanyMasterDTO>();
                    for (int j = 0; j < parentCompanyResultsBean.size(); j++) {

                        CompanyMasterDTO dto = parentCompanyResultsBean.getIdByIndex(j);
                        if (dto.getParentStartDate() != null) {
                        } else {
                            binder.getErrorDisplay().setError(
                                    ValidationUtils.PARENT_COMP_START_DATE_VALID);
                            return;
                        }

                        if (dto.getParentCompanyNo() != null) {
                        } else {
                            binder.getErrorDisplay().setError(
                                    "Parent Company is Mandatory in Parent Company Tab");
                            return;
                        }

                        if (dto.getParentEndDate() != null) {
                            if (dto.getParentStartDate() != null) {
                                Date Date1 = dto.getParentStartDate();
                                Date Date2 = dto.getParentEndDate();
                                int h = Date1.compareTo(Date2);
                                if (h == 0) {
                                    binder.getErrorDisplay().setError(
                                            "Parent Company Start Date should not be equal to Parent Company End Date in Parent Company Tab");
                                    return;
                                } else if (h > 0) {
                                    binder.getErrorDisplay().setError("Parent Company Start Date should be less than Parent Company End Date in Parent Company Tab");
                                    return;
                                }
                            } else {

                                binder.getErrorDisplay().setError(
                                        ValidationUtils.PARENT_COMP_START_DATE_VALID);
                                return;
                            }

                        }

                        duplicateCheckInParentGrid.add(dto.getParentCompanySysId());
                        if (!duplicateCheck.containsKey(dto.getParentCompanySysId())) {
                            duplicateCheck.put(dto.getParentCompanySysId(), dto);
                        }
                        parentCompanyList.add(dto);

                    }

                    Set<Integer> unique = new HashSet<Integer>(duplicateCheckInParentGrid);
                    for (int temp : unique) {
                        int count = Collections.frequency(duplicateCheckInParentGrid, temp);
                        if (count > 1) {
                            int dupcount = 0;
                            for (CompanyMasterDTO ob : parentCompanyList) {
                                if (ob.getParentCompanySysId() == temp && (duplicateCheck.get(temp).getParentStartDate()).equals(ob.getParentStartDate())) {
                                        dupcount++;
                                }
                            }
                            if (dupcount > 1) {
                                binder.getErrorDisplay().setError("Please select different Parent company. Selected Parent Company already exists");
                                return;
                            }
                        }
                    }

                    /* fifth tab chages ends here */
                    for (int i = 0; i < identifierResultsBean.size(); i++) {
                        final CompanyCrtIdentifierDTO iden = (CompanyCrtIdentifierDTO) identifierResultsBean.getIdByIndex(i);
                        if (iden.getCompanyCrtQualifierName() == null || StringUtils.isEmpty(iden.getCompanyCrtQualifierName())) {
                            binder.getErrorDisplay().setError(
                                    "Company Qualifier Name is Mandatory in Identifier Tab");
                            return;
                        }
                        if (iden.getStartDate() == null) {
                            binder.getErrorDisplay().setError(
                                    ValidationUtils.START_DATE_VALID);
                            return;
                        }
                        if (iden.getCompanyIdentifier() == null || StringUtils.isEmpty(iden.getCompanyIdentifier())) {
                            binder.getErrorDisplay().setError(
                                    "Company Identifier is Mandatory in Identifier Tab");
                            return;
                        }
                        if (StringUtils.isEmpty(String.valueOf(iden.getIdentifierStatus()))) {
                            binder.getErrorDisplay().setError(
                                    "Identifier status is Mandatory in Identifier Tab");
                            return;
                        }
                        if (iden.getEndDate() != null) {
                            if (iden.getStartDate() == null) {
                                binder.getErrorDisplay().setError(
                                        ValidationUtils.START_DATE_VALID);
                                return;
                            } else {

                                final Date date1 = iden.getStartDate();
                                final Date date2 = iden.getEndDate();
                                final int temp = date1.compareTo(date2);
                                if (temp == GeneralCommonUtils.ZERO) {
                                    binder.getErrorDisplay().setError(
                                            "Start Date should not be equal to End Date in Identifier Tab");
                                    return;
                                } else if (temp > GeneralCommonUtils.ZERO) {
                                    binder.getErrorDisplay().setError("Start Date should be less than End Date in Identifier Tab");
                                    return;
                                }

                            }
                        }

                        final CompanyQualifier qualif = companyLogic.getCompanyCrtQualifierByQualifierName(iden
                                .getCompanyCrtQualifierName());
                        final DynamicQuery companyIdentifierDynamicQuery = DynamicQueryFactoryUtil
                                .forClass(CompanyIdentifier.class);
                        companyIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_IDENTIFIER,
                                iden.getCompanyIdentifier().trim()));
                        companyIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.START_DATE,
                                iden.getStartDate()));
                        companyIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_QUALIFIER_SID,
                                qualif.getCompanyQualifierSid()));
                        companyIdentifierDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
                        final List<CompanyIdentifier> companyIdentiiferList = companyLogic.getCompanyCrtIdentifierlist(companyIdentifierDynamicQuery);

                        sysId = sysId.replace(ConstantsUtils.COMMA, StringUtils.EMPTY);
                        if (sysId == StringUtils.EMPTY) {
                            if (!companyIdentiiferList.isEmpty()) {
                                binder.getErrorDisplay().setError(
                                        ValidationUtils.COM_IDENTIFIER_VALID);
                                return;
                            }
                        } else {
                            final int primaryId = Integer.parseInt(sysId);
                            companyIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_MASTER_ID,
                                    primaryId));
                            final List<CompanyIdentifier> tempList = companyLogic.getCompanyCrtIdentifierlist(companyIdentifierDynamicQuery);

                            if ((companyIdentiiferList.size() - tempList.size()) > GeneralCommonUtils.ZERO) {
                                binder.getErrorDisplay().setError(
                                        ValidationUtils.COM_IDENTIFIER_VALID);
                                return;
                            }

                        }
                        for (int k = 0; k < identifierList.size(); k++) {
                            if (identifierList.get(k).getCompanyIdentifier().equals(iden.getCompanyIdentifier()) && identifierList.get(k).getStartDate().equals(iden.getStartDate())) {
                                    final CompanyQualifier qual = companyLogic.getCompanyCrtQualifierByQualifierName(iden
                                            .getCompanyCrtQualifierName());

                                    final CompanyQualifier qualBean = companyLogic.getCompanyCrtQualifierByQualifierName(identifierList.get(k).getCompanyCrtQualifierName());
                                    if (qual.getCompanyQualifierSid() == qualBean.getCompanyQualifierSid()) {
                                        binder.getErrorDisplay().setError(
                                                "Company Identifier already added for this Company");
                                        return;
                                    }

                            }
                        }
                        identifierList.add(iden);

                    }

                    MessageBox.showPlain(Icon.QUESTION, commmonUtil.getHeaderMessage(), commmonUtil.getSaveMessage(String.valueOf(binder.getField(ConstantsUtils.COMPANY_NAME).getValue())), new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         *
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals(ConstantsUtils.YES)) {
                               String msg = companyLogic.saveCompanyMaster(binder, identifierList, companyTradeList, parentCompanyList, notesTabForm.getUploadedData(), notesTabForm.getAddedNotes(), notesTabForm.removeDetailsList());
                                //This code is needed.ARM Related changes
                                String companyTypeDesc = ((HelperDTO) compayInformation.companyType.getValue()).getDescription();
                                boolean isGLComp = companyTypeDesc.equalsIgnoreCase(ConstantsUtils.GLCOMP) || companyTypeDesc.equalsIgnoreCase(ConstantsUtils.DIVISION) || companyTypeDesc.equalsIgnoreCase(ConstantsUtils.BUSINESS_UNIT);
                                Integer sid = Integer.valueOf(String.valueOf(binder.getField(ConstantsUtils.COMPANY_SYSTEM_ID).getValue().toString()));
                                if (isGLComp && financialClose.getModeDdlb().getValue() != null && String.valueOf(financialClose.getModeDdlb().getValue()).equalsIgnoreCase(ConstantsUtils.MANUAL)) {
                                    BeanItemContainer container = financialClose.financialCloseTableLogic.getResultsContainer();
                                    FinancialCloseDTO binderDto = financialClose.financialCloseTableLogic.getBinderDto();
                                    binderDto.setCompanyMasterSid(sessionDTO.getSystemId());
                                    financialCloseLogic.saveManualStatus(binderDto, container);

                                    QuartzListener.killJob(String.valueOf(sid));
                                    financialCloseLogic.swapAutoToManualUpdate(sid);
                                    QuartzListener.scheduleCompanyFinancialClose(); // This is the method to schedule all the Jobs
                                } else if (isGLComp && financialClose.getModeDdlb().getValue() != null && String.valueOf(financialClose.getModeDdlb().getValue()).equalsIgnoreCase(ConstantsUtils.AUTOMATIC)) {
                                    FinancialCloseDTO binderDto = financialClose.financialCloseTableLogic.getBinderDto();
                                    List<FinancialCloseDTO> addModeList = financialClose.financialCloseTableLogic.getBinderDto().getAddMode_Container_MainList();
                                    if (addModeList != null && !addModeList.isEmpty()) {
                                        binderDto.setCompanyMasterSid(sid);
                                        financialCloseLogic.saveAutoMode(binderDto, addModeList);
                                        QuartzListener.scheduleCompanyFinancialClose();
                                    }
                                }
                                if (ConstantsUtils.SUCCESS.equals(msg)) {
                                    final Notification notif = new Notification(commmonUtil.getSavedSuccessfulMessage(String.valueOf(binder.getField(ConstantsUtils.COMPANY_ID).getValue()), String.valueOf(binder.getField(ConstantsUtils.COMPANY_NAME).getValue())), Notification.Type.HUMANIZED_MESSAGE);
                                    notif.setPosition(Position.MIDDLE_CENTER);
                                    notif.setStyleName(ConstantsUtils.MY_STYLE);
                                    notif.show(Page.getCurrent());
                                    notif.setDelayMsec(NumericConstants.TWO_THOUSAND);
                                    sessionDTO.setMode(ConstantsUtils.EDIT);
                                    getUI().getNavigator().navigateTo(CompanyAddView.NAME);
                                } else if ("duplicateNo".equals(msg)) {
                                    binder.getErrorDisplay().setError(
                                            "Company No already exists.");
                                } else if (ConstantsUtils.DUPLICATE.equals(msg)) {
                                    binder.getErrorDisplay().setError(
                                            "Company ID already exists.");
                                }
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);

                } catch (FieldGroup.CommitException commitEx) {
                    LOGGER.error(commitEx);

                } catch (Exception ex) {
                    LOGGER.error(ex);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1008), new MessageBoxListener() {
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
                LOGGER.debug("Ending  UPDATE  method from EDIT");
            }
        });
    }

    private void deleteButton() {
        deleteBtn.setWidth(ConstantsUtils.BTN_WIDTH);
        deleteBtn.addClickListener(new Button.ClickListener() {
            /**
             * Logic for the delete button click event.
             *
             * @param event
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("Entering inside  DELETE  method from EDIT");
                binder.getFields();
                final int systemId = Integer.valueOf(binder.getField(ConstantsUtils.COMPANY_SYSTEM_ID).getValue().toString().replace(ConstantsUtils.COMMA, StringUtils.EMPTY));
                try {
                    boolean isParentCompany = false;
                    boolean isInCfp = false;
                    boolean isInContract = false;
                    final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil
                            .forClass(CompanyParentDetails.class);

                    companyDynamicQuery.add(RestrictionsFactoryUtil.eq("parentCompanyMasterSid",
                            systemId));
                    companyDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, "D"));

                    final List<CompanyParentDetails> companyParentDetailsList = companyLogic.getCompanyParentDetailsList(companyDynamicQuery);

                    if (companyParentDetailsList != null && !companyParentDetailsList.isEmpty() && companyParentDetailsList.size() > 0) {
                        isParentCompany = true;
                        final MessageBox msg = MessageBox.showPlain(Icon.WARN, ValidationUtils.CANNOT_DELETE, "Company cannot be deleted as it is associated as parent to another Company", new MessageBoxListener() {
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

                    List<ImtdCfpDetails> cfpDetailsList = new ArrayList<>();
                    if (!isParentCompany) {
                        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil
                                .forClass(CfpDetails.class);

                        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_MASTER_ID,
                                systemId));
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, "D"));

                        cfpDetailsList = CfpDetailsLocalServiceUtil.dynamicQuery(cfpDynamicQuery);
                        if (cfpDetailsList != null && !cfpDetailsList.isEmpty() && cfpDetailsList.size() > 0) {
                            isInCfp = true;
                            final MessageBox msg = MessageBox.showPlain(Icon.WARN, ValidationUtils.CANNOT_DELETE, "Company cannot be deleted as it is associated with CFP", new MessageBoxListener() {
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
                    }
                    List<CfpContractDetails> compContractList = new ArrayList<>();
                    if (!isParentCompany && !isInCfp) {
                        final DynamicQuery contractDynamicQuery = DynamicQueryFactoryUtil.forClass(CfpContractDetails.class);
                        contractDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_MASTER_ID, systemId));
                        contractDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, "D"));

                        compContractList = CfpContractDetailsLocalServiceUtil.dynamicQuery(contractDynamicQuery);
                        if (compContractList != null && !compContractList.isEmpty() && compContractList.size() > 0) {
                            isInContract = true;
                            final MessageBox msg = MessageBox.showPlain(Icon.WARN, ValidationUtils.CANNOT_DELETE, "Company cannot be deleted as it is associated with Contract", new MessageBoxListener() {
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
                    }

                    if (!isParentCompany && !isInCfp && !isInContract && companyParentDetailsList.isEmpty()) {
                            MessageBox.showPlain(Icon.QUESTION, commmonUtil.getHeaderMessage(), commmonUtil.getDeleteMessage(binder.getField(ConstantsUtils.COMPANY_NAME).getValue().toString()), new MessageBoxListener() {
                                /**
                                 * Yes/No Button logic
                                 */
                                @SuppressWarnings("PMD")
                                public void buttonClicked(final ButtonId buttonId) {
                                    if (buttonId.name().equals(ConstantsUtils.YES)) {
                                        try {
                                            final CompanyMaster master = companyLogic.deleteCompanyMasterById(systemId);
                                            companyLogic.deleteNotesTabAttachment(systemId);
                                            final Notification notif = new Notification(commmonUtil.getDeletedSuccessfulMessage(master.getCompanyId(), master.getCompanyName()), Notification.Type.HUMANIZED_MESSAGE);
                                            notif.setPosition(Position.MIDDLE_CENTER);
                                            notif.setStyleName(ConstantsUtils.MY_STYLE);
                                            notif.show(Page.getCurrent());
                                            AbstractSearchView.flag = true;
                                            getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                                        } catch (Exception ex) {
                                            LOGGER.error(ex);
                                            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1012), new MessageBoxListener() {
                                                /**
                                                 * The method is triggered when
                                                 * a button of the message box
                                                 * is pressed .
                                                 *
                                                 * @param buttonId The buttonId
                                                 * of the pressed button.
                                                 */
                                                @SuppressWarnings("PMD")
                                                public void buttonClicked(final ButtonId buttonId) {
                                                    // Do Nothing
                                                }
                                            }, ButtonId.OK);
                                            msg.getButton(ButtonId.OK).focus();
                                        }
                                    } else {
                                    }
                                }
                            },
                                    ButtonId.YES,
                                    ButtonId.NO);
                    }

                } catch (Exception ex) {
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
                LOGGER.debug("Ending  DELETE  method from EDIT");
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

    public void viewModeConfig() {

        notesTabForm.refreshTable();
        notesTabForm.removeAndDisablingComponents();
    }

    public void addTabsheet() throws PortalException, SystemException {
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent()
                .getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> tabItemHM = stplSecurity
                .getBusinessTabPermission(userId, UISecurityUtil.COMPANY_MASTER);
        tabSheet.addTab(compayInformation.getContent(binder, sessionDTO, tabSheet), "Company Information", null);

        Component addressComponent = companyAddress.getContent(binder, sessionDTO);
        tabSheet.addTab(addressComponent, "Address", null);

        tabSheet.addTab(compayIdentifier.getContent(binder, identifierResultsBean, sessionDTO), "Identifier", null);
        tabSheet.addTab(compayTradeClass.getContent(binder, tradeClassResultsBean, sessionDTO), TabNameUtil.TRADE_CLASS, null);
        Component companyComponent = compayParent.getContent(binder, parentCompanyResultsBean, sessionDTO);
        tabSheet.addTab(companyComponent, TabNameUtil.PARENT_COMPANY, null);
        if (tabItemHM.get(TabNameUtil.PARENT_COMPANY) != null
                && !((AppPermission) tabItemHM.get(TabNameUtil.PARENT_COMPANY))
                .isTabFlag()) {

            tabSheet.getTab(companyComponent).setVisible(false);

        }
        //This code is Needed.Related to ARM module changes
        financialClose = new FinancialClose(sessionDTO);
        tabSheet.addTab(financialClose, "Financial Close", null);
        tabSheet.getTab(NumericConstants.FIVE).setVisible(Boolean.FALSE);
        Object companyTypeValue = compayInformation.companyType.getValue();
        if (companyTypeValue != null && (((HelperDTO) companyTypeValue).getDescription().equalsIgnoreCase(ConstantsUtils.GLCOMP) ||((HelperDTO) companyTypeValue).getDescription().equalsIgnoreCase(ConstantsUtils.DIVISION) || ((HelperDTO) companyTypeValue).getDescription().equalsIgnoreCase(ConstantsUtils.BUSINESS_UNIT))){
            tabSheet.getTab(NumericConstants.FIVE).setVisible(Boolean.TRUE);
        } else {
            tabSheet.getTab(NumericConstants.FIVE).setVisible(Boolean.FALSE);
        }
        compayInformation.attachCompanyTypeListener();

        notesTabForm = new NotesTabForm(binder, "Company Master", "COMPANY_MASTER", "companySystemId", "view".equals(sessionDTO.getMode()) ? "view" : sessionDTO.getMode());

        if (tabItemHM.get(TabNameUtil.COMPANY_ADDRESS) != null
                && !((AppPermission) tabItemHM.get(TabNameUtil.COMPANY_ADDRESS))
                .isTabFlag()) {
            tabSheet.getTab(addressComponent).setVisible(false);

        }
        if ("Edit".equals(sessionDTO.getMode())) {
            notesTabForm.readOnlyNotesHistory(true);
            notesTabForm.refreshTable();
        }
        tabSheet.addTab(notesTabForm, "Notes", null);

        tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            /**
             * Listener for Tab Change Event
             */
            public void selectedTabChange(final TabSheet.SelectedTabChangeEvent event) {

                Component component = event.getTabSheet().getSelectedTab();
                selectedTabIndex = event.getTabSheet().getTabPosition(event.getTabSheet().getTab(component));
                 if (selectedTabIndex == NumericConstants.FIVE) {
                     financialClose.setDefaultFocus();
                 }
            }
        });
    }
}
