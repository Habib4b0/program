/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.company.ui.form;

import com.stpl.app.global.company.dto.CompanyMasterDTO;
import com.stpl.app.global.company.ui.view.CompanyAddView;
import com.stpl.app.global.company.util.FiledNameUtils;
import com.stpl.app.global.company.util.UIUtils;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.ifp.logic.IFPLogic;
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
import com.stpl.app.util.HelperUtils;
import com.stpl.app.util.TabNameUtil;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.app.util.ValidationUtils;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
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
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author shyam.duraipandian
 */
public class CompanyParentForm extends StplCustomComponent {

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
    @UiField("errorMsg")
    public ErrorLabel errorMsg = new ErrorLabel();
    /**
     * The Parent Excel Export Button
     */
    @UiField("parentExcelExport")
    private Button parentExcelExport;
    private final Map<Integer, Boolean> reloadVerticalLayoutTabFiveMap = new HashMap<>();
    //Labels
    /**
     * The parentCompanyName
     */
    @UiField("labelParentCompanyName")
    Label labelParentCompanyName;
    /**
     * The parent company no.
     */
    @UiField("labelParentCompanyNo")
    Label labelParentCompanyNo;
    /**
     * The parent start date.
     */
    @UiField("labelParentStartDate")
    Label labelParentStartDate;
    /**
     * The parent end date.
     */
    @UiField("labelParentEndDate")
    Label labelParentEndDate;
    /**
     * The parentCompanyName
     */
    @UiField("parentCompanyName")
    TextField parentCompanyName;
    /**
     * The parent company no.
     */
    @UiField("parentCompanyNo")
    CustomTextField parentCompanyNo;
    /**
     * The parent start date.
     */
    @UiField("parentStartDate")
    PopupDateField parentStartDate;
    /**
     * The parent end date.
     */
    @UiField("parentEndDate")
    PopupDateField parentEndDate;
    /**
     * The parent details table.
     */
    @UiField("parentDetailsTableLayout")
    VerticalLayout parentDetailsTableLayout;
    ExtFilterTable parentDetailsTable = new ExtFilterTable();
    /**
     * The parent sys id.
     */
    @UiField("parentSysId")
    TextField parentSysId;
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
     * The parent company results bean.
     */
    BeanItemContainer<CompanyMasterDTO> parentCompanyResultsBean = new BeanItemContainer(CompanyMasterDTO.class);
     /**
     * The vertical Layout
     */
    @UiField("verticalLayout")
    VerticalLayout verticalLayout;
    /**
     * The selected ID
     */
    private Object selectedId;
    private final String MANDATORY="mandatory";
    /**
     * The Company Master DTO Object
     */
    CompanyMasterDTO companyMasterDTO = new CompanyMasterDTO();
    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<>(companyMasterDTO));
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(CompanyAddView.class);
    CommonUIUtils commonUiUtil = new CommonUIUtils();
    IFPLogic ifpLogic = new IFPLogic();
    ParentCompanyNo lookUp=null;
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    SessionDTO sessionDTO;

    public Component getContent(ErrorfulFieldGroup binder, final BeanItemContainer<CompanyMasterDTO> parentCompanyResultsBean, SessionDTO sessionDTO) {
        VerticalLayout vLayout = new VerticalLayout();
        try {
            this.binder = binder;
            this.parentCompanyResultsBean = parentCompanyResultsBean;
            this.sessionDTO = sessionDTO;

            vLayout.addComponent(Clara.create(getClass().getResourceAsStream("/clara/companyMaster/CompanyParentNo.xml"), this));

            addResponsiveVerticalTabFiveLayout();
            getBinder();
            final StplSecurity stplSecurity = new StplSecurity();
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldCompanyHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPANY_MASTER+ConstantsUtils.COMMA+TabNameUtil.PARENT_COMPANY,false);
            final Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.COMPANY_MASTER+ConstantsUtils.COMMA+TabNameUtil.PARENT_COMPANY);
            configurePermission(fieldCompanyHM, functionHM);
            configureFields();
            setMandatory();

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return vLayout;
    }
    
    /**
     * Configures the permission for the fields and buttons based on the user.
     * @param fieldCompanyHM
     * @param functionHM 
     */
    private void configurePermission(final Map<String, AppPermission> fieldCompanyHM, final Map<String, AppPermission> functionHM) {
        LOGGER.debug("Entering configurePermission");
        try {
        String mode = sessionDTO.getMode();
        
        List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.COMPANY_MASTER,TabNameUtil.PARENT_COMPANY);
        commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldCompanyHM, mode);

            if (functionHM.get(FiledNameUtils.ATTACH_PARENT) != null && ((AppPermission) functionHM.get(FiledNameUtils.ATTACH_PARENT)).isFunctionFlag()) {
                attachButton();
            } else {
                attachButton.setVisible(false);
            }
            if (functionHM.get(FiledNameUtils.REMOVE_PARENT) != null && ((AppPermission) functionHM.get(FiledNameUtils.REMOVE_PARENT)).isFunctionFlag()) {
                removeButton();
            } else {
                removeButton.setVisible(false);
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending configurePermission");
    }

    private ErrorfulFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setBuffered(true);
        binder.setErrorDisplay(errorMsg);
        errorMsg.setId("ErrorMessage");
        return binder;
    }

    public void disableTable() {
        parentDetailsTable.setSelectable(false);
        parentDetailsTable.setEditable(false);
        removeButton.setVisible(false);
         verticalLayout.setVisible(false);
    }

    private void setMandatory() {
        return;
    }

    public void configureFields() throws PortalException, SystemException {
        LOGGER.debug("configureFields");

        parentExcelExport.setIcon(new ThemeResource("../../icons/excel.png"));
        parentExcelExport.setStyleName("link");
        parentExcelExport.setDescription("Export to excel");
        parentExcelExport.setIconAlternateText("Excel export");
        parentExcelExport.setHtmlContentAllowed(true);
        parentExcelExport.setErrorHandler(new ErrorHandler() {
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


        parentCompanyNo.setImmediate(true);
        parentCompanyNo.setReadOnly(true);
        parentCompanyName.setReadOnly(true);
        parentCompanyNo.setValidationVisible(true);
        parentCompanyNo.addValidator(new StringLengthValidator("Parent Company No Should be less than 50 characters", 0, NumericConstants.FIFTY, true));
        parentCompanyNo.addStyleName("searchicon");
        parentCompanyNo.addClickListener(new CustomTextField.ClickListener() {
            /**
             * serialVersionUID
             */
            private static final long serialVersionUID = 1L;

            /**
             * focus listener
             */
            public void click(final CustomTextField.ClickEvent event) {
                if(lookUp==null){
                lookUp = new ParentCompanyNo(parentCompanyNo, parentSysId, parentCompanyName,sessionDTO);
                UI.getCurrent().addWindow(lookUp);
                }
                 lookUp.addCloseListener(new Window.CloseListener() {
                        /**
                         * window
                         */
                        public void windowClose(final Window.CloseEvent e) {
                              lookUp = null;
                        }
                    });
            }
        });
        parentCompanyNo.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(final Property.ValueChangeEvent event) {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                
                parentCompanyNo.setDescription(parentCompanyNo.getValue());
                 
                if (event.getProperty().getValue() == null || parentCompanyNo.getValue().equals(HelperUtils.EMPTY)) {
                  
                    parentStartDate.setEnabled(true);
                    parentEndDate.setEnabled(true);
                    parentStartDate.setValidationVisible(false);
                   labelParentStartDate.removeStyleName(MANDATORY);
                } else {
                     labelParentStartDate.setStyleName(MANDATORY);
        parentStartDate.setImmediate(true);

                    parentStartDate.setEnabled(true);
                    parentEndDate.setEnabled(true);
                    parentStartDate.setValidationVisible(false);
                }

            }
        });

        parentStartDate.setImmediate(true);
        parentStartDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        parentStartDate.setValidationVisible(true);
        parentStartDate.setId("ParentStartDate");
        parentStartDate.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    try {
                        parentStartDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(parentStartDate.getValue()));
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

        parentEndDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        parentEndDate.setValidationVisible(true);
        parentEndDate.setImmediate(true);
        parentEndDate.addValidator(new ParentDateValidator("Parent start date should be before Parent end date"));
        parentEndDate.setId("ParentEndDate");
        parentEndDate.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    try {
                        parentEndDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(parentEndDate.getValue()));
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
        
        //Table
        parentDetailsTable.markAsDirty();
        final StplSecurity stplSecurity = new StplSecurity();
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPANY_MASTER+ConstantsUtils.COMMA+TabNameUtil.PARENT_COMPANY,false);

        String mode = sessionDTO.getMode();
        List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.COMPANY_MASTER, TabNameUtil.PARENT_COMPANY);
        Object[] obj = UIUtils.getInstance().parentCompanyColumns;
        TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldIfpHM, mode);
            if(tableResultCustom.getObjResult().length == 0){
              parentDetailsTable.setVisible(false);
            }
        parentDetailsTable.setContainerDataSource(parentCompanyResultsBean);
        parentDetailsTable.setVisibleColumns(tableResultCustom.getObjResult());
        parentDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        
        parentDetailsTable.addStyleName("table-header-normal");
        parentDetailsTable.addStyleName(ConstantsUtils.FILTER_BAR);
        parentDetailsTable.setPageLength(NumericConstants.THREE);
        parentDetailsTable.setImmediate(true);
        parentDetailsTable.setSelectable(true);
        parentDetailsTable.setSizeFull();
        parentDetailsTable.setFilterBarVisible(true);
        parentDetailsTable.setFilterDecorator(new ExtDemoFilterDecorator());

        parentDetailsTable.setTableFieldFactory(new DefaultFieldFactory() {
            /**
             * field
             */
            public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {

                if (propertyId.equals(ConstantsUtils.PARENT_COMPANY_NO)) {

                    final CustomTextField parentCompanyNo = new CustomTextField();
                    parentCompanyNo.setStyleName("searchicon");
                    parentCompanyNo.setReadOnly(true);
                    parentCompanyNo.addClickListener(new CustomTextField.ClickListener() {
                        @Override
                        public void click(CustomTextField.ClickEvent event) {
                            try {
                                sessionDTO.setParentLookUpId("0000");
                                if(lookUp==null){
                                lookUp = new ParentCompanyNo(parentCompanyNo, parentSysId, parentCompanyName,sessionDTO);
                                UI.getCurrent().addWindow(lookUp);
                                }
                                lookUp.addCloseListener(new Window.CloseListener() {
                                    /**
                                     * window listener
                                     */
                                    public void windowClose(final Window.CloseEvent e) {
                                        if (itemId != null && !sessionDTO.getParentLookUpId().equals("0000")) {
                                                parentDetailsTable.getContainerProperty(itemId, "oldParentId").setValue(
                                                        Integer.valueOf(parentDetailsTable.getContainerProperty(itemId, ConstantsUtils.PARENT_COMPANY_SYS_ID).getValue().toString()));
                                                parentDetailsTable.getContainerProperty(itemId, "priorParentCompanySysId").setValue(
                                                        Integer.valueOf(parentDetailsTable.getContainerProperty(itemId, "parentCompanySysId").getValue().toString()));
                                                parentDetailsTable.getContainerProperty(itemId, "parentCompanySysId").setValue(Integer.valueOf(sessionDTO.getParentSysId()));
                                                parentDetailsTable.getContainerProperty(itemId, "parentStartDate").setValue(null);
                                                lookUp=null;
                                        }
                                    }
                                });
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

                    return parentCompanyNo;
                }
                if (propertyId.equals(ConstantsUtils.PARENT_START_DATE)) {
                    final PopupDateField startDate = new PopupDateField();
                    try {

                        startDate.setDateFormat(ConstantsUtils.DATE_FORMAT);


                        startDate.addValueChangeListener(new Property.ValueChangeListener() {
                            /**
                             * serialVersionUID
                             */
                            private static final long serialVersionUID = 1L;

                            /**
                             * value change
                             */
                            public void valueChange(final Property.ValueChangeEvent event) {
                               startDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(startDate.getValue()));    
                            }
                        });
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
                    return startDate;
                }
                if (propertyId.equals(ConstantsUtils.PARENT_END_DATE)) {

                    final PopupDateField endDate = new PopupDateField();
                    endDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
                    endDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(endDate.getValue()));
                    return endDate;
                } else if (propertyId.equals("createdDate")) {

                    final PopupDateField endDate = new PopupDateField();
                    endDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
                    endDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(endDate.getValue()));
                    endDate.setReadOnly(true);
                    return endDate;
                }
                @SuppressWarnings("rawtypes")
                final Field field = super.createField(container, itemId, propertyId, uiContext);
                field.setReadOnly(true);
                field.setSizeFull();
                // Otherwise use the default field factory
                return field;
            }
        });

        parentDetailsTable.setEditable(true);



        parentDetailsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            /**
             * serialVersionUID
             */
            private static final long serialVersionUID = 1L;

            /**
             * click listener
             */
            public void itemClick(final ItemClickEvent event) {
                selectedId = event.getItemId();

            }
        });
        parentDetailsTable.setErrorHandler(new ErrorHandler() {
            /**
             * serialVersionUID
             */
            private static final long serialVersionUID = 1L;

            /**
             * Error Handler
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                return;
            }
        });

        parentDetailsTableLayout.addComponent(parentDetailsTable);

    }

    public class ParentDateValidator extends AbstractValidator {

        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * The Default Constructor.
         */
        public ParentDateValidator() {
            super(StringUtils.EMPTY);
        }

        /**
         * The Constructor with error message.
         *
         * @param errorMessage the error message
         */
        public ParentDateValidator(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * To validate the parentStartDate.
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         */
        @Override
        public void validate(final Object value) {
            if (parentStartDate.getValue() != null && parentEndDate.getValue() != null) {
                if (parentStartDate.getValue().after(parentEndDate.getValue())) {
                    throw new Validator.InvalidValueException("Parent End Date should be greater than Parent Start Date");
                } else if (parentStartDate.getValue().equals(parentEndDate.getValue())) {
                    throw new Validator.InvalidValueException("Parent Start Date and Parent End Date should not be equal");
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
            boolean isValidValue;
            if (parentStartDate.getValue() != null && parentEndDate.getValue() != null) {
                isValidValue = parentStartDate.getValue().compareTo(parentEndDate.getValue()) <= 0;
                return isValidValue;
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

    public void attachButton() {

        attachButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    boolean flag = false;
                    binder.getErrorDisplay().clearError();
                    StringBuilder errorMessage = new StringBuilder("Information for the following Mandatory fields need to be provided:" + "<br>");
                    if (parentCompanyNo.getValue() == null || parentCompanyNo.getValue().equals(StringUtils.EMPTY)) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Parent Company No");
                        flag = true;
                    }
                    if(parentCompanyNo.getValue().equals(selectedId) && parentStartDate.getValue() == null && flag)
                     {
                            errorMessage.append(ConstantsUtils.COMMA);
                        errorMessage.append("Parent Start Date");
                        flag = true;
                    }
                    if (flag) {
                        binder.getErrorDisplay().setError(errorMessage.toString());
                        return;
                    } else {
                        binder.getFields();
                        final List<CompanyMasterDTO> results = parentCompanyResultsBean.getItemIds();
                        boolean checkflag = false;
                        for (int i = 0; i < results.size(); i++) {
                            if (parentCompanyNo.getValue().equals(results.get(i).getParentCompanyNo()) && ((Date) parentStartDate.getValue()).equals(results.get(i).getParentStartDate())) {
                                checkflag = true;
                            }
                        }
                        if (checkflag) {
                            binder.getErrorDisplay().setError("Selected Company already exists with same start date");
                            return;
                        }

                        final CompanyMasterDTO identForm = new CompanyMasterDTO();
                        identForm.setParentCompanySysId(Integer.valueOf(parentSysId.getValue()));
                        identForm.setParentCompanyNo(String.valueOf(parentCompanyNo.getValue()));
                        identForm.setParentCompanyName(String.valueOf(parentCompanyName.getValue()));
                        if (parentStartDate.getValue() != null) {
                            identForm.setParentStartDate((Date) parentStartDate.getValue());
                        }
                        if (parentEndDate.getValue() != null) {
                            identForm.setParentEndDate((Date) parentEndDate.getValue());
                        }
                        if (identForm.getParentStartDate() == null) {
                                binder.getErrorDisplay().setError(ValidationUtils.PARENT_COMP_START_DATE_VALID);
                                return;
                            }
                        if (identForm.getParentEndDate() != null) {
                            if (identForm.getParentStartDate() == null) {
                                binder.getErrorDisplay().setError(ValidationUtils.PARENT_COMP_START_DATE_VALID);
                                return;
                            } else {
                                final Date psDate = identForm.getParentStartDate();
                                final Date peDate2 = identForm.getParentEndDate();
                                final int differeddate = psDate.compareTo(peDate2);
                                if (differeddate == Constants.ZERO) {
                                    binder.getErrorDisplay().setError("Parent Company Start Date should be less than Parent Company End Date in Parent Company Tab");
                                    return;
                                } else if (differeddate > Constants.ZERO) {
                                    binder.getErrorDisplay().setError("Parent Company Start Date should be less than Parent End Date in Parent Company Tab");
                                    return;
                                }
                            }
                        }
                        identForm.setParentCompanySysId(Integer.valueOf(parentSysId.getValue()));

                        List<CompanyMasterDTO> parentCompanyList = parentCompanyResultsBean.getItemIds();
                        if (parentCompanyList != null) {
                            for (CompanyMasterDTO parentCompanytradeClass : parentCompanyList) {
                                String parentCompanyNo = String.valueOf(parentCompanytradeClass.getParentCompanyNo());
                                String parentCompanyStartDate = String.valueOf(parentCompanytradeClass.getParentStartDate());
                                if (identForm.getParentCompanyNo().equals(parentCompanyNo)) {
                                    binder.getErrorDisplay().setError("Selected Company already exists");
                                    return;
                                }
                                  if (identForm.getParentCompanyNo().equals(parentCompanyNo)
                                        && String.valueOf(identForm.getParentStartDate()).equals(parentCompanyStartDate)) {
                                    binder.getErrorDisplay().setError("Selected Company already exists with same start date");
                                    return;
                                }
                            }
                        }

                        identForm.setCreatedDate(new Date());
                        String createdBy = StplSecurity.userMap.get(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                        identForm.setCreatedBy(createdBy == null ? StringUtils.EMPTY : createdBy);
                        parentCompanyResultsBean.addBean(identForm);

                        parentCompanyNo.setReadOnly(false);
                        parentCompanyNo.setValue(StringUtils.EMPTY);
                        parentCompanyNo.setReadOnly(true);
                        parentCompanyName.setReadOnly(false);
                        parentCompanyName.setValue(StringUtils.EMPTY);
                        parentCompanyName.setReadOnly(true);
                        parentStartDate.setValue(null);
                        parentEndDate.setValue(null);
                        labelParentStartDate.removeStyleName(MANDATORY);
                    }
                } catch (Exception e) {
                    LOGGER.error(e);
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

    }

    public void removeButton() {

        removeButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                final Object value = parentDetailsTable.getValue();
                boolean flag = parentDetailsTable.isSelected(value);

                binder.getErrorDisplay().clearError();
                if (!flag) {
                    binder.getErrorDisplay().setError("Please select a Parent Company from the list view to Remove.");
                    return;
                }
                MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to Remove the Parent Company " + " ?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        try {

                            if (buttonId.name().equals("YES")) {
                                parentCompanyResultsBean.removeItem(selectedId);
                                selectedId = null;
                                Object itemClick = parentDetailsTable.getValue();
                                parentDetailsTable.unselect(itemClick);
                            }
                        } catch (Exception e) {
                            LOGGER.error(e);
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
            }
        });

    }

    @UiHandler("parentExcelExport")
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

    protected void excelExportLogic() throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        LOGGER.debug("Entering excelExportLogic");
        createWorkSheet();
        LOGGER.debug("Ending excelExportLogic");
    }

    private void createWorkSheet() throws SystemException, PortalException, NoSuchMethodException , IllegalAccessException, InvocationTargetException {
        LOGGER.debug("Entering createWorkSheet");
        final long recordCount = parentDetailsTable.getContainerDataSource().size();
        ExcelExportforBB.createWorkSheet(parentDetailsTable.getColumnHeaders(), recordCount, this, getUI(), TabNameUtil.PARENT_COMPANY_EXP);
        LOGGER.debug("Ending createWorkSheet");
    }

    public void createWorkSheetContent(final PrintWriter printWriter) {
        CompanyMasterDTO dto;
        final List<CompanyMasterDTO> searchList;
        SimpleDateFormat format = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
        String date;
        searchList = parentCompanyResultsBean.getItemIds();
        for (int rowCount = 0; rowCount < searchList.size(); rowCount++) {

            dto = searchList.get(rowCount);

            printWriter.print(ConstantsUtils.QUOTE + dto.getParentCompanyNo() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getParentCompanyName() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            if (dto.getParentStartDate() != null) {
                printWriter.print(ConstantsUtils.QUOTE + format.format(dto.getParentStartDate()) + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            } else {
                printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            }

            if (dto.getParentEndDate() != null) {
                printWriter.print(ConstantsUtils.QUOTE + format.format(dto.getParentEndDate()) + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
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


    private static Object[] getCollapsibleOneColumn(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        propertyIds = list.toArray(new Object[list.size()]);

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

    public void addResponsiveVerticalTabFiveLayout() {

        reloadVerticalLayoutTabFiveMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
        reloadVerticalLayoutTabFiveMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
        reloadVerticalLayoutTabFiveMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
        reloadVerticalLayoutTabFiveMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
        reloadVerticalLayoutTabFiveMap.put(NumericConstants.EIGHT_HUNDRED, true);
        reloadVerticalLayoutTabFiveMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
        reloadVerticalLayoutTabFiveMap.put(NumericConstants.THREE_EIGHT_ZERO, true);
        reloadVerticalLayoutTabFiveMap.put(0, true);

        Page.getCurrent().addBrowserWindowResizeListener(
                new Page.BrowserWindowResizeListener() {
                    @Override
                    public void browserWindowResized(
                            final Page.BrowserWindowResizeEvent event) {

                        int browserWidth = Page.getCurrent().getBrowserWindowWidth();
                        if (browserWidth > NumericConstants.ONE_FIVE_ONE_SIX && reloadVerticalLayoutTabFiveMap.get(NumericConstants.ONE_FIVE_ONE_SIX)) {
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.ONE_FIVE_ONE_SIX, false);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.EIGHT_HUNDRED, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.THREE_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFiveMap.put(0, true);
                            getCollapsibleColumnsDefault(parentDetailsTable);
                        } else if (browserWidth < NumericConstants.ONE_FIVE_ONE_SIX && browserWidth > NumericConstants.THOUSAND_THREE_HUNDRED && reloadVerticalLayoutTabFiveMap.get(NumericConstants.THOUSAND_THREE_HUNDRED)) {
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, false);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.EIGHT_HUNDRED, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.THREE_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFiveMap.put(0, true);
                            getCollapsibleColumnsDefault(parentDetailsTable);
                        } else if (browserWidth < NumericConstants.THOUSAND_THREE_HUNDRED && browserWidth > NumericConstants.ONE_ZERO_TWO_FOUR && reloadVerticalLayoutTabFiveMap.get(NumericConstants.ONE_ZERO_TWO_FOUR)) {
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, false);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.EIGHT_HUNDRED, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.THREE_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFiveMap.put(0, true);
                        } else if (browserWidth < NumericConstants.ONE_ZERO_TWO_FOUR && browserWidth > NumericConstants.NINE_SEVEN_EIGHT && reloadVerticalLayoutTabFiveMap.get(NumericConstants.NINE_SEVEN_EIGHT)) {
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.NINE_SEVEN_EIGHT, false);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.EIGHT_HUNDRED, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.THREE_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFiveMap.put(0, true);
                        } else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT && browserWidth > NumericConstants.EIGHT_HUNDRED && reloadVerticalLayoutTabFiveMap.get(NumericConstants.EIGHT_HUNDRED)) {
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.EIGHT_HUNDRED, false);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.THREE_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFiveMap.put(0, true);

                        } else if (browserWidth <= NumericConstants.EIGHT_HUNDRED && browserWidth > NumericConstants.FOUR_EIGHT_ZERO && reloadVerticalLayoutTabFiveMap.get(NumericConstants.FOUR_EIGHT_ZERO)) {
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.EIGHT_HUNDRED, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.FOUR_EIGHT_ZERO, false);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.THREE_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFiveMap.put(0, true);
                        } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.THREE_EIGHT_ZERO && reloadVerticalLayoutTabFiveMap.get(NumericConstants.THREE_EIGHT_ZERO)) {
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.EIGHT_HUNDRED, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.THREE_EIGHT_ZERO, false);
                            reloadVerticalLayoutTabFiveMap.put(0, true);
                            getCollapsibleOneColumn(parentDetailsTable);
                        } else if (browserWidth < NumericConstants.THREE_EIGHT_ZERO && reloadVerticalLayoutTabFiveMap.get(0)) {

                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.EIGHT_HUNDRED, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFiveMap.put(NumericConstants.THREE_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFiveMap.put(0, false);
                            getCollapsibleOneColumn(parentDetailsTable);
                        }

                    }
                });
    }
}
