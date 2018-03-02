
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.ui.form;

import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.dto.CompanyCrtIdentifierDTO;
import com.stpl.app.gcm.tp.dto.IdDescriptionDTO;
import com.stpl.app.gcm.tp.logic.CommmonLogic;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.ADD_TRADING_PARTNER;
import com.stpl.app.gcm.util.Message;
import com.stpl.app.gcm.util.MessageUtil;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataTypeConverter;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author maheshj
 */
public class CompanyAddForm extends VerticalLayout {

    @UiField("companyId")
    public TextField companyId;
    @UiField("companyNo")
    public TextField companyNo;
    @UiField("companyName")
    public TextField companyName;

    @UiField("comapnyStatus")
    public ComboBox comapnyStatus;
    @UiField("companyStartDate")
    public PopupDateField companyStartDate;
    @UiField("companyType")
    public ComboBox companyType;
    @UiField("qualifierName")
    public ComboBox qualifierName;
    @UiField("companyIdentifier")
    public TextField companyIdentifier;
    @UiField("identifierStatus")
    public ComboBox identifierStatus;
    @UiField("startDate")
    public PopupDateField startDate;
    @UiField("endDate")
    public PopupDateField endDate;

    @UiField("identifierAttachBtn")
    public Button identifierAttachBtn;

    @UiField("identifierResults")
    public ExtFilterTable identifierResults;

    @UiField("identifierRemoveBtn")
    public Button identifierRemoveBtn;

    @UiField("tradeClass")
    public ComboBox tradeClass;
    @UiField("tradeStartDate")
    public PopupDateField tradeStartDate;
    @UiField("tradeEndDate")
    public PopupDateField tradeEndDate;

    @UiField("tradeAttachBtn")
    public Button tradeAttachBtn;

    @UiField("tradeResults")
    public ExtFilterTable tradeResults;

    @UiField("tradeRemoveBtn")
    public Button tradeRemoveBtn;

    @UiField("infoResetBtn")
    public Button infoResetBtn;

    @UiField("identifierResetBtn")
    public Button identifierResetBtn;

    @UiField("identifierTableResetBtn")
    public Button identifierTableResetBtn;

    @UiField("tradeResetBtn")
    public Button tradeResetBtn;

    @UiField("tradeTableResetBtn")
    public Button tradeTableResetBtn;

    @UiField("companyIdLabel")
    public Label companyIdLabel;

    @UiField("companyNoLabel")
    public Label companyNoLabel;

    @UiField("companyNameLabel")
    public Label companyNameLabel;

    @UiField("comapnyStatusLabel")
    public Label comapnyStatusLabel;

    @UiField("companyStartDateLabel")
    public Label companyStartDateLabel;

    @UiField("companyTypeLabel")
    public Label companyTypeLabel;

    @UiField("qualifierNameLabel")
    public Label qualifierNameLabel;

    @UiField("companyIdentifierLabel")
    public Label companyIdentifierLabel;

    @UiField("startDateLabel")
    public Label startDateLabel;

    @UiField("endDateLabel")
    public Label endDateLabel;

    @UiField("tradeClassLabel")
    public Label tradeClassLabel;

    @UiField("tradeStartDateLabel")
    public Label tradeStartDateLabel;

    @UiField("tradeEndDateLabel")
    public Label tradeEndDateLabel;

    @UiField("identifierStatusLabel")
    public Label identifierStatusLabel;
    /**
     * The ErrorLabel.
     */
    @UiField("errorMsg")
    public ErrorLabel errorMsg;

    private final BeanItemContainer<CompanyCrtIdentifierDTO> identifierContainer = new BeanItemContainer<>(CompanyCrtIdentifierDTO.class);
    private final BeanItemContainer<CompanyCrtIdentifierDTO> tradeClassContainer = new BeanItemContainer<>(CompanyCrtIdentifierDTO.class);

    private final CompanyCrtIdentifierDTO companyCrtIdentifierDTO = new CompanyCrtIdentifierDTO();
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyAddForm.class);
    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<>(companyCrtIdentifierDTO));
    /**
     * The identifier results bean.
     */
    private final CommonUtil commonUtil = CommonUtil.getInstance();
    /**
     * The btn remove.
     */
    private final CommmonLogic commmonLogic = new CommmonLogic();

    private Object identifierTableBeanId;
    private Object tradeTableBeanId;
    private SessionDTO session = new SessionDTO();

    public CompanyAddForm() {
        addComponent(Clara.create(getClass().getResourceAsStream("/TradingPartner/CompanyAddForm.xml"), this));
        configureFields();
        binder = getBinder();
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    public ErrorfulFieldGroup getBinder() {
        final CompanyCrtIdentifierDTO bean = new CompanyCrtIdentifierDTO();
        final ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<>(bean));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
        return binder;
    }

    protected void configureFields() {

        try {
            companyStartDate.setDateFormat(Constants.MM_DD_YYYY);
            companyStartDate.setStyleName(Constants.DATE_FIELD_CENTER);
            companyStartDate.setRequired(true);
            companyStartDate.setValidationVisible(true);

            startDate.setDateFormat(Constants.MM_DD_YYYY);
            startDate.setStyleName(Constants.DATE_FIELD_CENTER);
            startDate.setRequired(true);
            startDate.setValidationVisible(true);

            endDate.setDateFormat(Constants.MM_DD_YYYY);
            endDate.setStyleName(Constants.DATE_FIELD_CENTER);
            endDate.setRequired(true);
            endDate.setValidationVisible(true);

            tradeStartDate.setDateFormat(Constants.MM_DD_YYYY);
            tradeStartDate.setStyleName(Constants.DATE_FIELD_CENTER);
            tradeStartDate.setRequired(true);
            tradeStartDate.setValidationVisible(true);

            tradeEndDate.setDateFormat(Constants.MM_DD_YYYY);
            tradeEndDate.setStyleName(Constants.DATE_FIELD_CENTER);
            tradeEndDate.setRequired(true);
            tradeEndDate.setValidationVisible(true);

            companyId.setRequired(true);
            companyName.setRequired(true);
            companyNo.setRequired(true);
            comapnyStatus.setRequired(true);
            companyType.setRequired(true);
            qualifierName.setRequired(true);
            companyIdentifier.setRequired(true);
            identifierStatus.setRequired(true);
            companyId.setValidationVisible(true);
            companyName.setValidationVisible(true);
            companyNo.setValidationVisible(true);
            comapnyStatus.setValidationVisible(true);
            companyType.setValidationVisible(true);
            qualifierName.setValidationVisible(true);
            companyIdentifier.setValidationVisible(true);
            identifierStatus.setValidationVisible(true);
            CommmonLogic logic = new CommmonLogic();

            List<IdDescriptionDTO> resultList;
            commonUtil.loadComboBox(comapnyStatus, UiUtils.STATUS, false);
            commonUtil.loadComboBox(companyType, UiUtils.COMPANY_TYPE, false);
            commonUtil.loadComboBox(identifierStatus, UiUtils.STATUS, false);
            commonUtil.loadComboBox(tradeClass, UiUtils.COMPANY_TRADE_CLASS, false);

            resultList = logic.loadCompanyQualifier();
            logic.setIdDescription(resultList, qualifierName);
            resultList.clear();
            configureIdentifierResultTable();
            configureTradeClassResultTable();
            String star = " <span style=\"color: #ed473b; padding: 0 0.2em;\">*</span> ";

            companyIdLabel.setValue(companyIdLabel.getValue() + star);
            companyNoLabel.setValue(companyNoLabel.getValue() + star);
            companyNameLabel.setValue(companyNameLabel.getValue() + star);

            comapnyStatusLabel.setValue(comapnyStatusLabel.getValue() + star);
            companyStartDateLabel.setValue(companyStartDateLabel.getValue() + star);
            companyTypeLabel.setValue(companyTypeLabel.getValue() + star);
            qualifierNameLabel.setValue(qualifierNameLabel.getValue() + star);
            companyIdentifierLabel.setValue(companyIdentifierLabel.getValue() + star);
            identifierStatusLabel.setValue(identifierStatusLabel.getValue() + star);
            startDateLabel.setValue(startDateLabel.getValue() + star);
            endDateLabel.setValue(endDateLabel.getValue() + star);
            tradeClassLabel.setValue(tradeClassLabel.getValue() + star);
            tradeStartDateLabel.setValue(tradeStartDateLabel.getValue() + star);
            tradeEndDateLabel.setValue(tradeEndDateLabel.getValue() + star);
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }

    }

    public void configureIdentifierResultTable() {
        identifierResults.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        identifierResults.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        identifierResults.setHeight(NumericConstants.TWO_HUNDRED, Unit.PIXELS);
        identifierResults.setPageLength(NumericConstants.FIVE);
        identifierResults.setContainerDataSource(identifierContainer);
        identifierResults.setSelectable(true);
        identifierResults.setFilterBarVisible(false);

        identifierResults.setVisibleColumns(Constants.getInstance().idenFormColOrder);
        identifierResults.setColumnHeaders(Constants.getInstance().idenFormColHeader);

        identifierResults.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            @Override
            public void itemClick(final ItemClickEvent event) {
                identifierTableBeanId = event.getItemId();
                BeanItem<?> targetItem;
                if (identifierTableBeanId instanceof BeanItem<?>) {
                    targetItem = (BeanItem<?>) identifierTableBeanId;
                } else if (identifierTableBeanId instanceof CompanyCrtIdentifierDTO) {
                    targetItem = new BeanItem<>((CompanyCrtIdentifierDTO) identifierTableBeanId);
                } else {
                    targetItem = null;
                }
                identifierTableBeanId = (CompanyCrtIdentifierDTO) targetItem.getBean();

            }
        });
    }

    public void configureTradeClassResultTable() {
        tradeResults.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        tradeResults.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        tradeResults.setHeight(NumericConstants.TWO_HUNDRED, Unit.PIXELS);
        tradeResults.setPageLength(NumericConstants.FIVE);
        tradeResults.setContainerDataSource(tradeClassContainer);

        tradeResults.setFilterBarVisible(false);
        tradeResults.setSelectable(true);
        tradeResults.setVisibleColumns(Constants.getInstance().tradeClassColumns);
        tradeResults.setColumnHeaders(Constants.getInstance().tradeClassHeaders);

        tradeResults.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            @Override
            public void itemClick(final ItemClickEvent event) {
                tradeTableBeanId = event.getItemId();
                BeanItem<?> targetItem;
                if (tradeTableBeanId instanceof BeanItem<?>) {
                    targetItem = (BeanItem<?>) tradeTableBeanId;
                } else if (tradeTableBeanId instanceof CompanyCrtIdentifierDTO) {
                    targetItem = new BeanItem<>((CompanyCrtIdentifierDTO) tradeTableBeanId);
                } else {
                    targetItem = null;
                }
                tradeTableBeanId = (CompanyCrtIdentifierDTO) targetItem.getBean();

            }
        });

    }

    public void attachButton1() {
        return;
    }

    @UiHandler("identifierAttachBtn")
    public void identifierAttachBtnLogic(Button.ClickEvent event) {
        final CompanyCrtIdentifierDTO identForm = new CompanyCrtIdentifierDTO();
        int qualifierSid = 0;
        int statusSid = 0;

        if (qualifierName.getValue() != null && !String.valueOf(qualifierName.getValue()).equals(Constants.SELECT_ONE)) {
            qualifierSid = (Integer) qualifierName.getValue();
            identForm.setQualifierName(String.valueOf(qualifierSid));
            identForm.setQualifier(String.valueOf(qualifierName.getItemCaption(qualifierName.getValue())));

        } else {
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.MANDATORY_ERROR), MessageUtil.getErrorCode(Message.QUALIFIER_MSG));
            return;

        }

        if (companyIdentifier.getValue() != null && !String.valueOf(companyIdentifier.getValue()).equals(StringUtils.EMPTY)) {

            identForm.setCompanyIdentifier(companyIdentifier.getValue());

        } else {
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.MANDATORY_ERROR), MessageUtil.getErrorCode(Message.IDENTIFIER_MSG));
            return;

        }
        if (identifierStatus.getValue() != null && !String.valueOf(identifierStatus.getValue()).equals(Constants.SELECT_ONE)) {
            statusSid = Integer.parseInt(identifierStatus.getValue().toString());

            identForm.setIdentifierStatus(String.valueOf(statusSid));
            identForm.setIdentifierStatusName(String.valueOf(identifierStatus.getItemCaption(identifierStatus.getValue())));

        } else {
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.MANDATORY_ERROR), MessageUtil.getErrorCode(Message.IDENTIFIER_STATUS));
            return;

        }
        if (startDate.getValue() != null) {

            identForm.setStartDate(startDate.getValue());

        } else {
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.MANDATORY_ERROR), MessageUtil.getErrorCode(Message.IDENTIFIER_START_DATE));
            return;

        }
        if (endDate.getValue() != null) {

            identForm.setEndDate(endDate.getValue());
        } else {
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.MANDATORY_ERROR), MessageUtil.getErrorCode(Message.IDENTIFIER_END_DATE));
            return;

        }

        if (startDate.getValue() != null && endDate.getValue() != null && startDate.getValue().after(endDate.getValue())) {
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.ERROR_MSG), MessageUtil.getErrorCode(Message.END_START_DATE));
            return;
        } else if (startDate.getValue() != null && endDate.getValue() != null && startDate.getValue().getTime() == endDate.getValue().getTime()) {
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.ERROR_MSG), MessageUtil.getErrorCode(Message.START_END_DATE));
            return;
        }

        int count = commmonLogic.getCompanyCrtQualifierByQualifierName(companyIdentifier.getValue(), qualifierSid);

        if (count > Constants.ZERO) {
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.ERROR_MSG), MessageUtil.getErrorCode(Message.IDENTIFIER_EXIST));
            return;
        }
        try {
            StplSecurity.getUserName();
        } catch (SystemException ex) {
            LOGGER.error("",ex);
        }

        String createdBy = StplSecurity.getUserMap().get(DataTypeConverter.convertObjectToInt(VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));

        identForm.setCreatedBy(createdBy);
        identForm.setCreatedDate(new Date());

        for (CompanyCrtIdentifierDTO crtDto : identifierContainer.getItemIds()) {

            if (String.valueOf(qualifierSid) == crtDto.getQualifierName() && String.valueOf(companyIdentifier.getValue()).equals(crtDto.getCompanyIdentifier())) {
                AbstractNotificationUtils.getErrorNotification("Company Identifier  Already Present", "Company Identifier already added for this Company .");
                return;

            }

        }
        identifierContainer.addItem(identForm);
        qualifierName.setValue(null);
        companyIdentifier.setValue(StringUtils.EMPTY);
        identifierStatus.setValue(null);
        startDate.setValue(null);
        endDate.setValue(null);

    }

    @UiHandler("tradeAttachBtn")
    public void tradeAttachBtnLogic(Button.ClickEvent event) {
        final CompanyCrtIdentifierDTO identForm = new CompanyCrtIdentifierDTO();
        int tradClassSid = 0;
        if (tradeClass.getValue() != null && !String.valueOf(tradeClass.getValue()).equals(Constants.SELECT_ONE)) {
            tradClassSid = Integer.parseInt(tradeClass.getValue().toString());
            identForm.setTradeClass(String.valueOf(tradClassSid));
            identForm.setTradeClassName(String.valueOf(tradeClass.getItemCaption(tradeClass.getValue())));

        } else {
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.MANDATORY_ERROR), MessageUtil.getErrorCode(Message.TRADE_CLASS));
            return;

        }

        if (tradeStartDate.getValue() != null) {
            identForm.setTradeStartDate(tradeStartDate.getValue());

        } else {

            AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.MANDATORY_ERROR), MessageUtil.getErrorCode(Message.TRADE_START));
            return;

        }
        if (tradeEndDate.getValue() != null) {

            identForm.setTradeEndDate(tradeEndDate.getValue());
        } else {

            AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.MANDATORY_ERROR), MessageUtil.getErrorCode(Message.TRADE_END));
            return;

        }

        if (tradeStartDate.getValue() != null && tradeEndDate.getValue() != null && tradeStartDate.getValue().after(tradeEndDate.getValue())) {
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.ERROR_MSG), MessageUtil.getErrorCode(Message.TRADE_END_START));
            return;
        } else if (tradeStartDate.getValue() != null && tradeEndDate.getValue() != null && tradeStartDate.getValue().getTime() == tradeEndDate.getValue().getTime()) {
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.ERROR_MSG), MessageUtil.getErrorCode(Message.TRADE_START_END));
            return;
        }

        try {
            StplSecurity.getUserName();
        } catch (SystemException ex) {
            LOGGER.error("",ex);
        }
        String createdBy = StplSecurity.getUserMap().get(DataTypeConverter.convertObjectToInt(VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));

        identForm.setCreatedBy(createdBy);
        identForm.setCreatedDate(new Date());

        for (CompanyCrtIdentifierDTO crtDto : tradeClassContainer.getItemIds()) {

            if (String.valueOf(tradClassSid) == crtDto.getTradeClass()) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.TRADE_ALERT), MessageUtil.getErrorCode(Message.TRADE_DIFFERENT));
                return;

            }

        }
        tradeClassContainer.addItem(identForm);
        tradeClass.setValue(null);
        tradeStartDate.setValue(null);
        tradeEndDate.setValue(null);

    }

    @UiHandler("identifierRemoveBtn")
    public void identifierRemoveBtnLogic(Button.ClickEvent event) {
        if (identifierTableBeanId != null) {
            identifierContainer.removeItem(identifierTableBeanId);
        } else {
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.NO_RECORD), MessageUtil.getErrorCode(Message.IDENTIFIER_REMOVE));
            return;
        }
    }

    @UiHandler("tradeRemoveBtn")
    public void tradeRemoveBtnLogic(Button.ClickEvent event) {
        if (tradeTableBeanId != null) {
            tradeClassContainer.removeItem(tradeTableBeanId);
        } else {
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.NO_RECORD), MessageUtil.getErrorCode(Message.TRADE_REMOVE));
            return;
        }
    }

    @UiHandler("companyAdd")
    public void companyAddBtnLogic(Button.ClickEvent event) {
        try {
            final CompanyCrtIdentifierDTO companyform = new CompanyCrtIdentifierDTO();

            if (companyId.getValue() != null && !String.valueOf(companyId.getValue()).equals(StringUtils.EMPTY)
                    && companyNo.getValue() != null && !String.valueOf(companyNo.getValue()).equals(StringUtils.EMPTY)
                    && companyName.getValue() != null && !String.valueOf(companyName.getValue()).equals(StringUtils.EMPTY)
                    && comapnyStatus.getValue() != null && !String.valueOf(comapnyStatus.getValue()).equals(Constants.SELECT_ONE)
                    && companyType.getValue() != null && !String.valueOf(companyType.getValue()).equals(Constants.SELECT_ONE) && companyStartDate.getValue() != null) {

                int count = commmonLogic.getCompanyCountByID("COMPANY_ID", " ");
                if (count != 0) {
                    AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.DUPLICATE_ID), MessageUtil.getErrorCode(Message.DUPLICATE_ID_MSG));
                    return;
                }

                if (identifierContainer.size() == 0) {
                    AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.MANDATORY_ERROR), MessageUtil.getErrorCode(Message.ATLEAST_IDENTIFIER));
                    return;

                } else if (tradeClassContainer.size() == 0) {

                    AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.MANDATORY_ERROR), MessageUtil.getErrorCode(Message.ATLEAST_TRADE));
                    return;

                }
                companyform.setCompanyId(String.valueOf(companyId.getValue()));
                companyform.setCompanyNo(String.valueOf(companyNo.getValue()));
                companyform.setCompanyName(String.valueOf(companyName.getValue()));
                companyform.setComapnyStatus(String.valueOf(comapnyStatus.getValue()));
                companyform.setCompanyType(String.valueOf(companyType.getValue()));
                companyform.setCompanyStartDate(companyStartDate.getValue());

                CompanyMaster company = commmonLogic.saveCompanyMaster(companyform, identifierContainer.getItemIds(), tradeClassContainer.getItemIds());

                createSession(ADD_TRADING_PARTNER.getConstant(), company);
                session.setModuleName(ADD_TRADING_PARTNER.getConstant());
                resetForm();
                AddTPForm addForm = new AddTPForm(session);
                UI.getCurrent().addWindow(addForm);

            } else {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.MANDATORY_ERROR), MessageUtil.getErrorCode(Message.MANDATORY_MSG));
                return;

            }
        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }

    private void createSession(String moduleName, CompanyMaster company) {
        session = CommonUtils.attachSessionId(session);
        session.setCompanyNo(company.getCompanyNo());
        session.setCompanyName(company.getCompanyName());
        session.setCompanyType(String.valueOf(companyType.getItemCaption(companyType.getValue())));
        session.setCompanyCategory(" ");
        session.setTradeClass(" ");

        List<String> companyIds = new ArrayList<>();
        companyIds.add(String.valueOf(company.getCompanyMasterSid()));
        session.setCompanyMasterSids(companyIds);
        session.setModuleName(moduleName);
    }

    public void resetForm() {

        companyId.setValue(StringUtils.EMPTY);
        companyNo.setValue(StringUtils.EMPTY);
        companyName.setValue(StringUtils.EMPTY);
        comapnyStatus.setValue(null);
        companyType.setValue(null);
        companyStartDate.setValue(null);

        tradeClass.setValue(null);
        tradeStartDate.setValue(null);
        tradeEndDate.setValue(null);

        tradeClassContainer.removeAllItems();
        qualifierName.setValue(null);
        companyIdentifier.setValue(StringUtils.EMPTY);
        identifierStatus.setValue(null);
        startDate.setValue(null);
        endDate.setValue(null);
        identifierContainer.removeAllItems();
        identifierResults.resetFilters();
    }

    @UiHandler("infoResetBtn")
    public void infoResetBtnLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                // do nothing
            }

            @Override
            /**
             *
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             *
             */
            public void yesMethod() {
                binder.getErrorDisplay().clearError();
                binder.setItemDataSource(new BeanItem<>(new CompanyCrtIdentifierDTO()));
            }
        }.getConfirmationMessage(MessageUtil.getErrorCode(Message.CONFIRM_RESET), MessageUtil.getErrorCode(Message.CONFIRM_RESET_MSG));

    }

    @UiHandler("identifierResetBtn")
    public void identifierResetBtnLogic(Button.ClickEvent event) {

        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                // do nothing
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                binder.getErrorDisplay().clearError();
                binder.setItemDataSource(new BeanItem<>(new CompanyCrtIdentifierDTO()));
            }
        }.getConfirmationMessage(MessageUtil.getErrorCode(Message.CONFIRM_RESET), MessageUtil.getErrorCode(Message.CONFIRM_IDENTIFIER_RESET_MSG));

    }

    @UiHandler("identifierTableResetBtn")
    public void identifierTableResetBtnLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                // do nothsing
            }

            @Override
            /**
             *
             * The method is triggered when Yes button of the message box is
             *
             * pressed .
             *
             *
             *
             * @param buttonId The buttonId of the pressed button.
             *
             */
            public void yesMethod() {
                identifierContainer.removeAllItems();
                identifierResults.resetFilters();

            }
        }.getConfirmationMessage(MessageUtil.getErrorCode(Message.CONFIRMATION), MessageUtil.getErrorCode(Message.IDENTIFIER_RESET));
    }

    @UiHandler("tradeResetBtn")
    public void tradeResetBtnLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                // do nothing
            }

            @Override
            /**
             *
             * The method is triggered when Yes button of the message box is
             *
             * pressed .
             *
             *
             *
             * @param buttonId The buttonId of the pressed button.
             *
             */
            public void yesMethod() {
                binder.getErrorDisplay().clearError();
                binder.setItemDataSource(new BeanItem<>(new CompanyCrtIdentifierDTO()));
                tradeResults.resetFilters();

            }
        }.getConfirmationMessage(MessageUtil.getErrorCode(Message.CONFIRM_RESET), MessageUtil.getErrorCode(Message.TRADE_RESET));
    }

    @UiHandler("tradeTableResetBtn")
    public void tradeTableResetBtnLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                // do nothing
            }

            @Override
            /**
             *
             * The method is triggered when Yes button of the message box is
             *
             * pressed .
             *
             *
             *
             * @param buttonId The buttonId of the pressed button.
             *
             */
            public void yesMethod() {
                tradeClassContainer.removeAllItems();
            }
        }.getConfirmationMessage(MessageUtil.getErrorCode(Message.CONFIRMATION), MessageUtil.getErrorCode(Message.TRADE_RESET_LIST));
    }

}
