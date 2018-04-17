/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.periodconfiguration.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.periodconfiguration.constants.GtnFrameworkPeriodConfigurationContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.periodconfig.GtnWsPeriodConfigurationRequest;
import java.util.Date;

public class GtnFrameworkPeriodConfigurationValidation
        implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        return;
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        validationForConfig(componentId);
    }

    public void validationForConfig(String componentId) throws GtnFrameworkGeneralException {

        GtnWsPeriodConfigurationRequest periodConfigurationRequest = new GtnWsPeriodConfigurationRequest();

        periodConfigurationRequest.setUserId(Integer.valueOf(GtnUIFrameworkGlobalUI.getCurrentUser()));

        loadPeriodConfigurationFields(periodConfigurationRequest, componentId);
        validateStartDate(periodConfigurationRequest, componentId);
        validateFromDefaultdates(periodConfigurationRequest, componentId);
        validateDefaultdates(periodConfigurationRequest, componentId);
        saveConformation(periodConfigurationRequest, componentId);
    }

    private void validateDefaultdates(GtnWsPeriodConfigurationRequest periodConfigurationRequest, String componentId)
            throws GtnFrameworkGeneralException {
        validateDefaultFromDate(periodConfigurationRequest, componentId);
        if (GtnFrameworkPeriodConfigurationContants.MULTIPLE
                .equalsIgnoreCase(periodConfigurationRequest.getPeriodView())) {
            validateDefaultToDate(periodConfigurationRequest, componentId);
            validateDateRange(periodConfigurationRequest, componentId);
            validateToDefaultFromandTodates(periodConfigurationRequest, componentId);
        }
    }

    private void validateDefaultToDate(GtnWsPeriodConfigurationRequest periodConfigurationRequest, String componentId)
            throws GtnFrameworkGeneralException {
        Calendar temptoDate = getCalendar(periodConfigurationRequest.getDateTo());
        Calendar temptoDefDate = getCalendar(periodConfigurationRequest.getDefaultDateTo());
        if (temptoDefDate.compareTo(temptoDate) > 0) {
            alertError(GtnFrameworkPeriodConfigurationContants.VALIDATE_TO_DATES, componentId);
            throw new GtnFrameworkSkipActionException("From Default validation failed");
        }

    }

    private void validateDefaultFromDate(GtnWsPeriodConfigurationRequest periodConfigurationRequest, String componentId)
            throws GtnFrameworkGeneralException {
        Calendar today = getTodayDate();
        Calendar fromDate = getCalendar(periodConfigurationRequest.getDateFrom());
        Calendar defaltFromDate = getCalendar(periodConfigurationRequest.getDefaultDateFrom());
        if (defaltFromDate.compareTo(today) <= 0 && defaltFromDate.compareTo(fromDate) >= 0) {
            return;
        }
        alertError(GtnFrameworkPeriodConfigurationContants.PERIOD_CONFIGURATION_DEFAULT_DATE_RANGE, componentId);
        throw new GtnFrameworkSkipActionException("From Default validation failed");
    }

    private Calendar getCalendar(String dateAsString) throws GtnFrameworkGeneralException {
        Calendar fromDate = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(GtnFrameworkPeriodConfigurationContants.PERIOD_DATE_FORMAT);
        try {
            fromDate.setTime(dateFormat.parse(dateAsString));
        } catch (ParseException e) {
            throw new GtnFrameworkGeneralException("Unable to format date", e);
        }
        trunckTimePart(fromDate);
        return fromDate;
    }

    private Calendar getTodayDate() {
        Calendar today = Calendar.getInstance();
        trunckTimePart(today);
        return today;
    }

    private void trunckTimePart(Calendar today) {
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
    }

    private void loadPeriodConfigurationFields(GtnWsPeriodConfigurationRequest periodConfigurationRequest,
            String componentId) throws GtnFrameworkGeneralException {
        loadSelectionField(periodConfigurationRequest, componentId);
        loadPeriodConfigurationField(periodConfigurationRequest, componentId);

    }

    private void loadPeriodConfigurationField(GtnWsPeriodConfigurationRequest periodConfigurationRequest,
            String componentId) throws GtnFrameworkGeneralException {

        String viewMode = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.PERIOD_VIEW, componentId)
                .getStringFromField();
        boolean isMultiple = GtnFrameworkPeriodConfigurationContants.MULTIPLE.equalsIgnoreCase(viewMode);
        periodConfigurationRequest.setPeriodView(viewMode);
        loadFromPeriod(periodConfigurationRequest, componentId);
        if (isMultiple) {
            loadToPeriod(periodConfigurationRequest, componentId);
        }
    }

    private void loadToPeriod(GtnWsPeriodConfigurationRequest periodConfigurationRequest, String componentId)
            throws GtnFrameworkGeneralException {
        loadToFields(periodConfigurationRequest, componentId);
        loadDefaultToFields(periodConfigurationRequest, componentId);

    }

    private void loadDefaultToFields(GtnWsPeriodConfigurationRequest periodConfigurationRequest, String componentId)
            throws GtnFrameworkGeneralException {
        GtnUIFrameworkBaseComponent defalutModeTo = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.DEFAULT_MODE_TO);
        boolean isDefaultModeAuto = GtnFrameworkPeriodConfigurationContants.AUTO
                .equalsIgnoreCase(defalutModeTo.getCaptionFromComboBox());
        validateMandatoryCheck(defalutModeTo, componentId);
        periodConfigurationRequest.setDefaultModeTo(defalutModeTo.getIntegerFromField());

        if (isDefaultModeAuto) {
            GtnUIFrameworkBaseComponent defaultFrequencyTO = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.DEFAULT_FREQUENCY_TO);
            validateMandatoryCheck(defaultFrequencyTO, componentId);
            periodConfigurationRequest.setDefaultFrequencyTo(defaultFrequencyTO.getIntegerFromField());
            GtnUIFrameworkBaseComponent defaultPeriodTextBox = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_TO_TEXT_BOX);
            validatePeriodTextBoxData(defaultPeriodTextBox, componentId);
            periodConfigurationRequest.setDefaultPeriodToTextBox(defaultPeriodTextBox.getStringFromField());
        } else {
            GtnUIFrameworkBaseComponent defaultPeriodTo = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_TO);
            validateMandatoryCheck(defaultPeriodTo, componentId);
            periodConfigurationRequest.setDefaultPeriodTo(defaultPeriodTo.getDateFromDateField());
        }
        GtnUIFrameworkBaseComponent defaultDate = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.DEFAULT_DATE_TO);
        periodConfigurationRequest.setDefaultDateTo(defaultDate.getStringFromField());
    }

    private void loadToFields(GtnWsPeriodConfigurationRequest periodConfigurationRequest, String componentId)
            throws GtnFrameworkGeneralException {
        GtnUIFrameworkBaseComponent modeTo = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.MODE_TO);
        validateMandatoryCheck(modeTo, componentId);
        boolean isModeAuto = GtnFrameworkPeriodConfigurationContants.AUTO
                .equalsIgnoreCase(modeTo.getCaptionFromComboBox());
        periodConfigurationRequest.setModeTo(modeTo.getIntegerFromField());

        if (isModeAuto) {
            GtnUIFrameworkBaseComponent frequencyTo = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.FREQUENCY_TO);
            validateMandatoryCheck(frequencyTo, componentId);
            periodConfigurationRequest.setFrequencyTo(frequencyTo.getIntegerFromField());
            GtnUIFrameworkBaseComponent toPeriodTextBox = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.PERIOD_TO_TEXT_BOX);
            validatePeriodTextBoxData(toPeriodTextBox, componentId);
            periodConfigurationRequest.setPeriodToTextBox(toPeriodTextBox.getStringFromField());
        } else {
            GtnUIFrameworkBaseComponent toPeriodDate = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.PERIOD_TO);
            validateMandatoryCheck(toPeriodDate, componentId);
            periodConfigurationRequest.setPeriodTo(toPeriodDate.getDateFromDateField());
        }
        GtnUIFrameworkBaseComponent toDate = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.DATE_TO);
        periodConfigurationRequest.setDateTo(toDate.getStringFromField());

    }

    private void loadFromPeriod(GtnWsPeriodConfigurationRequest periodConfigurationRequest, String componentId)
            throws GtnFrameworkGeneralException {
        loadFromFields(periodConfigurationRequest, componentId);
        loadDefaultFromFields(periodConfigurationRequest, componentId);

    }

    private void loadDefaultFromFields(GtnWsPeriodConfigurationRequest periodConfigurationRequest, String componentId)
            throws GtnFrameworkGeneralException {
        GtnUIFrameworkBaseComponent defalutModeFrom = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.DEFAULT_MODE_FROM_COMPONENT);
        boolean isDefaultModeAuto = GtnFrameworkPeriodConfigurationContants.AUTO
                .equalsIgnoreCase(defalutModeFrom.getCaptionFromComboBox());
        validateMandatoryCheck(defalutModeFrom, componentId);
        periodConfigurationRequest.setDefaultModeFrom(defalutModeFrom.getIntegerFromField());

        if (isDefaultModeAuto) {
            GtnUIFrameworkBaseComponent defaultFrequencyFrom = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.DEFAULT_FREQUENCY_FROM);
            validateMandatoryCheck(defaultFrequencyFrom, componentId);
            periodConfigurationRequest.setDefaultFrequencyFrom(defaultFrequencyFrom.getIntegerFromField());
            GtnUIFrameworkBaseComponent defaultPeriodTextBox = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_FROM_TEXT_BOX);
            validatePeriodTextBoxData(defaultPeriodTextBox, componentId);
            periodConfigurationRequest.setDefaultPeriodFromTextBox(defaultPeriodTextBox.getStringFromField());
        } else {
            GtnUIFrameworkBaseComponent defaultPeriodFrom = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_FROM);
            validateMandatoryCheck(defaultPeriodFrom, componentId);
            periodConfigurationRequest.setDefaultPeriodFrom(defaultPeriodFrom.getDateFromDateField());
        }
        GtnUIFrameworkBaseComponent defaultDate = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.DEFAULT_DATE_FROM);
        periodConfigurationRequest.setDefaultDateFrom(defaultDate.getStringFromField());
    }

    private void loadFromFields(GtnWsPeriodConfigurationRequest periodConfigurationRequest, String componentId)
            throws GtnFrameworkGeneralException {
        GtnUIFrameworkBaseComponent modeFrom = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.MODE_FROM_COMPONENT);
        validateMandatoryCheck(modeFrom, componentId);
        boolean isModeAuto = GtnFrameworkPeriodConfigurationContants.AUTO
                .equalsIgnoreCase(modeFrom.getCaptionFromComboBox());
        periodConfigurationRequest.setModeFrom(modeFrom.getIntegerFromField());

        if (isModeAuto) {
            GtnUIFrameworkBaseComponent frequencyFrom = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.FREQUENCY_FROM_COMPONENT);
            validateMandatoryCheck(frequencyFrom, componentId);
            periodConfigurationRequest.setFrequencyFrom(frequencyFrom.getIntegerFromField());
            GtnUIFrameworkBaseComponent periodTextBox = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.PERIOD_FROM_TEXT_BOX);
            validatePeriodTextBoxData(periodTextBox, componentId);
            periodConfigurationRequest.setPeriodFromTextBox(periodTextBox.getStringFromField());
        } else {
            GtnUIFrameworkBaseComponent periodDate = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.PERIOD_FROM);
            validateMandatoryCheck(periodDate, componentId);
            periodConfigurationRequest.setPeriodFrom(periodDate.getDateFromDateField());
        }
        GtnUIFrameworkBaseComponent date = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.DATE_FROM);
        periodConfigurationRequest.setDateFrom(date.getStringFromField());

    }

    private void validatePeriodTextBoxData(GtnUIFrameworkBaseComponent periodTextBox, String componentId)
            throws GtnFrameworkGeneralException {
        if (periodTextBox.getValueFromComponent() == null || periodTextBox.getStringFromField().isEmpty()) {
            alertError(GtnFrameworkPeriodConfigurationContants.PERIOD_CONFIGURATION_MANDATORY_CHECK_ALERT, componentId);
            throw new GtnFrameworkSkipActionException("Mantatory check failed");
        }
        if (!periodTextBox.isValidFieldValue()) {
            alertError(GtnFrameworkPeriodConfigurationContants.PERIOD_TEXT_BOX_VALIDATION, componentId);
            throw new GtnFrameworkSkipActionException("Period Text box might  have wrong data");
        }

    }

    private void loadSelectionField(GtnWsPeriodConfigurationRequest periodConfigurationRequest, String componentId)
            throws GtnFrameworkGeneralException {
        GtnUIFrameworkBaseComponent module = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.MODULE);
        validateMandatoryCheck(module, componentId);
        periodConfigurationRequest.setModule(module.getIntegerFromField());
        GtnUIFrameworkBaseComponent businessProcess = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.BUSINESS_PROCESS);
        validateMandatoryCheck(module, componentId);
        periodConfigurationRequest.setBusinessProcess(businessProcess.getIntegerFromField());
        GtnUIFrameworkBaseComponent company = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.COMPANY);
        validateMandatoryCheck(company, componentId);
        periodConfigurationRequest.setCompany(company.getIntegerFromField());
        GtnUIFrameworkBaseComponent busineesUnit = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.BUSINESS_UNIT);
        validateMandatoryCheck(busineesUnit, componentId);
        periodConfigurationRequest.setBusinessUnit(busineesUnit.getIntegerFromField());

    }

    private void validateMandatoryCheck(GtnUIFrameworkBaseComponent component, String componentId)
            throws GtnFrameworkGeneralException {
        if (!component.isValidFieldValue()) {
            alertError(GtnFrameworkPeriodConfigurationContants.PERIOD_CONFIGURATION_MANDATORY_CHECK_ALERT, componentId);
            throw new GtnFrameworkSkipActionException("Mantatory check failed");
        }

    }

    public void alertError(String message, String componentId) throws GtnFrameworkGeneralException {
        final GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
        final GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();
        alertActionConfig.setActionParameterList(
                Arrays.asList(new Object[]{GtnFrameworkCommonStringConstants.ERROR, message}));
        alertAction.doAction(componentId, alertActionConfig);
        throw new GtnFrameworkValidationFailedException(message);
    }

    private void saveConformation(GtnWsPeriodConfigurationRequest periodConfigurationRequest, String componentId)
            throws GtnFrameworkGeneralException {
        GtnUIFrameWorkActionConfig customActionForConformation = new GtnUIFrameWorkActionConfig();
        customActionForConformation.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
        customActionForConformation.addActionParameter(GtnFrameworkPeriodConfigurationContants.CONFORMATION);
        customActionForConformation
                .addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_CONFIGURATION_SAVE_ALERT);

        List<GtnUIFrameWorkActionConfig> actionConfigSaveList = new ArrayList<>();
        GtnUIFrameWorkActionConfig customActionForSave = new GtnUIFrameWorkActionConfig();
        customActionForSave.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
        customActionForSave.addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_CONFIG_SAVE_ACTION);
        customActionForSave.addActionParameter(periodConfigurationRequest);
        actionConfigSaveList.add(customActionForSave);
        customActionForConformation.addActionParameter(actionConfigSaveList);
        GtnUIFrameworkActionExecutor.executeSingleAction(componentId, customActionForConformation);
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

    private void validateStartDate(GtnWsPeriodConfigurationRequest periodConfigurationRequest, String componentId) throws GtnFrameworkGeneralException {

        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR) - 5, cal.get(Calendar.MONTH), 1);
        Date currentMinusSixty = cal.getTime();

        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR) - 5, cal.get(Calendar.MONTH), calendar.getActualMaximum(Calendar.DATE));
        Date currentMinusSixtyForTo = cal.getTime();
        String fromValue = periodConfigurationRequest.getDateFrom();
        Date tempFromDate = new Date(fromValue);

        String defaultFromValue = periodConfigurationRequest.getDefaultDateFrom();
        Date tempDefaultFromDate = new Date(defaultFromValue);

        Boolean fromDateVal = tempFromDate.after(currentMinusSixty) || (tempFromDate.getMonth() == currentMinusSixty.getMonth() && tempFromDate.getYear() == currentMinusSixty.getYear());
        Boolean defaultFromDateVal = tempDefaultFromDate.after(currentMinusSixty) || (tempDefaultFromDate.getMonth() == currentMinusSixty.getMonth() && tempDefaultFromDate.getYear() == currentMinusSixty.getYear());
        Boolean toDateVal = true;
        Boolean defaultToDateVal = true;
        String viewMode = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.PERIOD_VIEW, componentId)
                .getStringFromField();
        if (GtnFrameworkPeriodConfigurationContants.MULTIPLE.equalsIgnoreCase(viewMode)) {
            String toValue = periodConfigurationRequest.getDateTo();
            Date tempToDate = new Date(toValue);
            String defaultToValue = periodConfigurationRequest.getDefaultDateTo();
            Date tempDefaultToDate = new Date(defaultToValue);
            toDateVal = tempToDate.after(currentMinusSixtyForTo) || (tempToDate.getMonth() == currentMinusSixtyForTo.getMonth() && tempToDate.getYear() == currentMinusSixtyForTo.getYear());
            defaultToDateVal = tempDefaultToDate.after(currentMinusSixtyForTo) || (tempDefaultToDate.getMonth() == currentMinusSixtyForTo.getMonth() && tempDefaultToDate.getYear() == currentMinusSixtyForTo.getYear());
        }
        if (!(fromDateVal && defaultFromDateVal && toDateVal && defaultToDateVal)) {
             alertError(GtnFrameworkPeriodConfigurationContants.VALIDATE_START_DATES, componentId);
            throw new GtnFrameworkSkipActionException("From Default validation failed");
        }

    }

    private void validateFromDefaultdates(GtnWsPeriodConfigurationRequest periodConfigurationRequest, String componentId) throws GtnFrameworkGeneralException {
        Date tempfromDate = new Date(periodConfigurationRequest.getDateFrom());
        Date tempfromDefDate = new Date(periodConfigurationRequest.getDefaultDateFrom());
        if (!(tempfromDate.getTime() == tempfromDefDate.getTime() || tempfromDefDate.after(tempfromDate))) {
            alertError(GtnFrameworkPeriodConfigurationContants.VALIDATE_FROM_DEFAULT_DATES, componentId);
            throw new GtnFrameworkSkipActionException("From Default validation failed");
        }

    }

    private void validateDateRange(GtnWsPeriodConfigurationRequest periodConfigurationRequest, String componentId) throws GtnFrameworkGeneralException {

        String toValue = periodConfigurationRequest.getDateTo();
        Date tempToDate = new Date();
        Date currentDate = new Date();
        currentDate.setDate(1);

        String fromValue = periodConfigurationRequest.getDateFrom();
        Date tempFromDate = new Date(fromValue);
        if (!toValue.isEmpty()) {
            tempToDate = new Date(toValue);
        }

        if (!((tempFromDate.before(currentDate) || (tempFromDate.getMonth() == currentDate.getMonth() && tempFromDate.getYear() == currentDate.getYear()))
                && ((tempFromDate.getMonth() == tempToDate.getMonth() && tempFromDate.getYear() == tempToDate.getYear()) || (tempFromDate.before(tempToDate))))) {
           alertError(GtnFrameworkPeriodConfigurationContants.VALIDATE_DATE_RANGE, componentId);
            throw new GtnFrameworkSkipActionException("From Default validation failed");
        }

    }

    private void validateToDefaultFromandTodates(GtnWsPeriodConfigurationRequest periodConfigurationRequest, String componentId) throws GtnFrameworkGeneralException {
        Date tempfromDefDate = new Date(periodConfigurationRequest.getDefaultDateFrom());
        Date temptoDefDate = new Date(periodConfigurationRequest.getDefaultDateTo());
        if (!(tempfromDefDate.getTime() == temptoDefDate.getTime() || tempfromDefDate.before(temptoDefDate))) {
            alertError(GtnFrameworkPeriodConfigurationContants.VALIDATE_DEFAULT_DATES, componentId);
            throw new GtnFrameworkSkipActionException("From Default validation failed");
        }

    }

}
