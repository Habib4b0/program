/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.calendar.ui;

import com.stpl.app.adminconsole.calendar.createcalendar.logic.dto.CalendarDetailsDTO;
import com.stpl.app.adminconsole.calendar.ui.view.CalendarSaveView;
import com.stpl.app.adminconsole.calendar.ui.view.CalendarSearchView;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.asi.calendarfield.CalendarField;
import org.asi.calendarfield.WeekDay;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author Abishek.Ram
 */
public class CalendarSaveIndex extends CalendarAbstractIndex {

    private final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(CalendarSaveIndex.class);

    CalendarField calendarField = new CalendarField();

    CheckBox defaultHoliday;

    @UiField("saveCal")
    private Button saveButton;

    @UiField("closeCal")
    private Button closeButton;

    @UiField("resetCal")
    private Button resetButton;

    public CalendarDetailsDTO calendarDetailsDTO;
    private String mod;
    public boolean resetDefault = true;

    /**
     *
     * @param sessionDTO
     * @param calendarDetailsDTO
     * @param mode
     */
    public CalendarSaveIndex(SessionDTO sessionDTO, CalendarDetailsDTO calendarDetailsDTO, String mode) {
        super(sessionDTO);
        this.calendarDetailsDTO = calendarDetailsDTO;
        this.mod = mode;
        configurePermission();
        configureForSave();
        configureBasedOnMode();
    }

    private void configureForSave() {
        changeVisiblity();
        addCalendarField();
        configureDefaultHoliday();
        configureYearDDLB();
        configureButtons();
    }

    private void loadCalendarField() {
        LOGGER.debug("Current Calendar Field Year :" + currentyearValue);
        calendarField = new CalendarField();
        calendarField.setRangeStart(new Date(currentyearValue, 0, 1));
        calendarField.setRangeEnd(new Date(currentyearValue, NumericConstants.ELEVEN, NumericConstants.THIRTY_ONE));
    }

    private void addCalendarField() {
        loadCalendarField();
        calendarLayout.removeAllComponents();
        calendarLayout.addComponent(calendarField);
        calendarField.setYearResolution(true);
        calendarField.setImmediate(true);
        calendarField.setMatrix(CalendarField.Matrix.ROW_2xCOL_6);

    }

    private void changeVisiblity() {
        calendarLayout.setVisible(true);
        saveAcctionLayout.setVisible(true);
        resultList.setVisible(false);
        insertDefaultHolidayToGridLayout();
        searchActionLayout.setVisible(false);
        searchFormLayoutRes.setVisible(false);
    }

    private void configureDefaultHoliday() {
        defaultHoliday.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (resetDefault) {
                    selectDefaultHoliday();
                }
            }
        });
    }

    private void selectDefaultHoliday() {
        if (defaultHoliday.getValue()) {
            calendarField.setSelectedWeekDays(WeekDay.SATURDAY, WeekDay.SUNDAY);
        } else {
            calendarField.setSelectedWeekDays();
        }
    }

    private void configureYearDDLB() {
        calendarYearDDLB.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (!validateNull(calendarYearDDLB.getValue())) {
                } else {
                    currentyearValue = (Integer) calendarYearDDLB.getValue() - NumericConstants.ONE_NINE_ZERO_ZERO;
                    addCalendarField();
                    defaultHoliday.setValue(Boolean.FALSE);
                }
            }

        });
    }

    private void configureButtons() {
        configureSaveButton();
        configureCloseButton();
        configureResetButton();
    }

    private void configureSaveButton() {
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
//                Are you sure you want to save the Calendar?
                bindValues();
                new AbstractNotificationUtils() {
                    @Override
                    public void noMethod() {
                        return;
                    }

                    @Override
                    public void yesMethod() {
                        if (mod.equals("copy")) {
                            calendarDetailsDTO.setId(0L);
                        }
                        int masterId = clLogic.saveCalendar(calendarDetailsDTO, sessionDTO);
                        calendarDetailsDTO.setId(masterId);
                        navigateToEditView();
                        Notification.show("Saved Successfully");
                    }
                }.getConfirmationMessage(ConstantsUtils.CONFORMATION, " Are you sure you want to save the Calendar?");
            }

        });
    }

    private void bindValues() {
        calendarDetailsDTO.setCalendarName(getMandatoryValueFromComponent(calendarNameTB));
        calendarDetailsDTO.setCalendarDescription(getMandatoryValueFromComponent(calendarDescTB));
        calendarDetailsDTO.setCalendarYear(Integer.valueOf(getMandatoryValueFromComponent(calendarYearDDLB)));
        calendarDetailsDTO.setCountry(getMandatoryValueFromComponent(countryDDLB));
        calendarDetailsDTO.setDefaultHolidays(defaultHoliday.getValue());
        getholidays();
    }

    private String getMandatoryValueFromComponent(AbstractField field) {
        String valueFromcomponent = String.valueOf(field.getValue());
        if (valueFromcomponent.equals("null") || valueFromcomponent.isEmpty()) {
            AbstractNotificationUtils.getAlertNotification(ConstantsUtils.ERROR, "Please complete all mandatory fields");
            throw new IllegalArgumentException(field.getCaption() + "is null");
        }
        return valueFromcomponent;
    }

    private void getholidays() {
        if (calendarDetailsDTO.getHolidayDays() == null) {
            calendarDetailsDTO.setHolidayDays(new HashMap<>());
        }
        calendarDetailsDTO.getHolidayDays().put("newHolidays", calendarField.getValue());
    }

    private void configureBasedOnMode() {
        switch (mod) {
            case "edit":
                configureForEdit();
                break;
            case "view":
                configureForView();
                break;
            case "copy":
                configureForCopy();
                break;
            case "add":
                configureForAdd();
        }
    }

    private void loadHolidays() {
        List holidays = clLogic.getHolidaysForCalendar(calendarDetailsDTO.getId());
        List<Date> forCalendarField = new ArrayList();
        Set<WeekDay> selectedList = new HashSet<>();
        for (Object holiday : holidays) {
            forCalendarField.add(new Date(((java.sql.Timestamp) holiday).getTime()));
            if (defaultHoliday.getValue() && (((java.sql.Timestamp) holiday).getDay() == 0 || ((java.sql.Timestamp) holiday).getDay() == NumericConstants.SIX)) {
                selectedList.add(WeekDay.getWeekDay(((java.sql.Timestamp) holiday).getDay()));
            }
        }
        if (defaultHoliday.getValue()) {
            calendarField.setUpdateDateValue(false);
            calendarField.setSelectedWeekDays(selectedList.toArray(new WeekDay[selectedList.size()]));
            calendarField.setUpdateDateValue(true);
            calendarField.setValue(holidays);
        }
    }

    private void configureForEdit() {
        loadValuesFromPrevious();
        loadHolidays();
    }

    private void loadValuesFromPrevious() {
        setYear();
        setCountry();
        setCalendarName();
        setCalendarDescription();
        setCountry();
        setDefaultHolidays();
    }

    private void setYear() {
        calendarYearDDLB.setValue(calendarDetailsDTO.getCalendarYear() == 0 ? null : calendarDetailsDTO.getCalendarYear());
    }

    private void setCountry() {
        countryDDLB.setValue(calendarDetailsDTO.getCountry().equals("") ? null : calendarDetailsDTO.getCountry());
    }

    private void setCalendarName() {
        calendarNameTB.setValue(calendarDetailsDTO.getCalendarName());
    }

    private void setCalendarDescription() {
        calendarDescTB.setValue(calendarDetailsDTO.getCalendarDescription());
    }

    private void setDefaultHolidays() {
        resetDefault = false;
        defaultHoliday.setValue((calendarDetailsDTO.isDefaultHolidays() == true) ? true : false);
        selectDefaultHoliday();
        resetDefault = true;
    }

    private void configureCloseButton() {
        closeButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (mod.equals("view")) {
                    navigateToSearchView();
                } else {
                    new AbstractNotificationUtils() {
                        @Override
                        public void noMethod() {
                            return;
                        }

                        @Override
                        public void yesMethod() {
                            navigateToSearchView();
                        }
                    }.getConfirmationMessage(ConstantsUtils.CONFORMATION, "Are you sure you want to close the Calendar Configuration screen?  Nothing will be saved.");

                }

            }
        });
    }

    private void navigateToSearchView() {
        getUI().getNavigator().navigateTo(CalendarSearchView.NAME);
    }

    private void navigateToEditView() {
        getUI().getNavigator().addView(CalendarSaveView.NAME, new CalendarSaveView(sessionDTO, calendarDetailsDTO, "edit"));
        getUI().getNavigator().navigateTo(CalendarSaveView.NAME);
    }

    private void configureForView() {
        loadValuesFromPrevious();
        loadHolidays();
        makeReadOnlyForFields();
    }

    private void makeReadOnlyForFields() {
        countryDDLB.setReadOnly(true);
        calendarField.setReadOnly(true);
        calendarNameTB.setReadOnly(true);
        calendarDescTB.setReadOnly(true);
        calendarYearDDLB.setReadOnly(true);
        defaultHoliday.setReadOnly(true);
        saveButton.setEnabled(false);
        resetButton.setEnabled(false);
    }

    private void configureForCopy() {
        loadValuesFromPrevious();
        loadHolidays();
        calendarNameTB.setValue("");
        calendarDescTB.setValue("");
    }

    private void configureForAdd() {
        currentyearValue = Calendar.getInstance().getTime().getYear();
        addCalendarField();
        countryDDLB.setValue(null);
        calendarNameTB.setValue("");
        calendarNameTB.focus();
        calendarDescTB.setValue("");
        calendarYearDDLB.setValue(null);
        defaultHoliday.setValue(Boolean.FALSE);
        calendarField.setSelectedMonthlyDays();
        calendarField.setSelectedWeekDays();
    }

    private void setToDefault() {
        calendarField.clearAllValue();
        configureBasedOnMode();
    }

    private void configureResetButton() {
        resetButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                new AbstractNotificationUtils() {
                    @Override
                    public void noMethod() {
                        return;
                    }

                    @Override
                    public void yesMethod() {
                        setToDefault();
                    }
                }.getConfirmationMessage(ConstantsUtils.CONFORMATION, "Are you sure you want to Reset the screen?");

            }

        });
    }

    private void insertDefaultHolidayToGridLayout() {
        Label empty = new Label();
        defaultHoliday = new CheckBox("Default Holidays");
        searchLayout.addComponent(defaultHoliday);
        searchLayout.addComponent(empty);
        searchLayout.replaceComponent(searchLayout.getComponent(NumericConstants.SIX, 0), defaultHoliday);
        searchLayout.replaceComponent(searchLayout.getComponent(NumericConstants.SEVEN, 0), empty);
    }

    public void configurePermission() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(sessionDTO.getUserId());
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Calender Configuration" + "," + "Calender Configuration Add screen");
            if (functionHM.get("saveButton") != null && !((AppPermission) functionHM.get("saveButton")).isFunctionFlag()) {
                saveButton.setVisible(false);
            } else {
                saveButton.setVisible(true);
            }
            if (functionHM.get("closeButton") != null && !((AppPermission) functionHM.get("closeButton")).isFunctionFlag()) {
                closeButton.setVisible(false);
            } else {
                closeButton.setVisible(true);
            }
            if (functionHM.get("resetButton") != null && !((AppPermission) functionHM.get("resetButton")).isFunctionFlag()) {
                resetButton.setVisible(false);
            } else {
                resetButton.setVisible(true);
            }

        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }
}
