/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnutilities.charts.form;

import com.stpl.app.gtnutilities.util.Constants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author Karthik.Raja
 */
public class ScheduleJobWindow extends CustomComponent {

    @UiField("name")
    private TextField name;
    @UiField("scheduleType")
    private ComboBox scheduleType;
    @UiField("enabled")
    private CheckBox enabled;
    @UiField("oneTimeDate")
    private PopupDateField oneTimeDate;
    @UiField("occurs")
    private ComboBox occurs;
    @UiField("recursEvery")
    private ComboBox recursEvery;
    @UiField("days")
    private OptionGroup days;
    @UiField("occursOnceSelect")
    private OptionGroup occursOnceSelection;
    @UiField("occursOnceAt")
    private PopupDateField occursOnceAt;
    @UiField("occursEverySelection")
    private OptionGroup occursEverySelection;
    @UiField("occursEvery")
    private ComboBox occursEvery;
    @UiField("occursEveryFrequency")
    private ComboBox occursEveryFrequency;
    @UiField("startingAt")
    private PopupDateField startingAt;
    @UiField("endingAt")
    private PopupDateField endingAt;
    @UiField("endDateSelection")
    private OptionGroup endDateSelection;
    @UiField("startDate")
    private PopupDateField startDate;
    @UiField("endDate")
    private PopupDateField endDate;
    @UiField("description")
    private TextField description;
    @UiField("saveBtn")
    private Button saveBtn;
    @UiField("closeBtn")
    private Button closeBtn;
    DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();

    public ScheduleJobWindow() {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/ScheduleJobWindow.xml"), this));
        configure();
    }

    private void configure() {
        configureOptionGroup();
        configureDateFields();

    }

    private void configureOptionGroup() {
        enabled.setCaption(Constants.ENABLED);
      
        String[] daysArray = new String[7];
        System.arraycopy(dateFormatSymbols.getWeekdays(),NumericConstants.ONE, daysArray, NumericConstants.ZERO, NumericConstants.SEVEN);
        days.addItems(Arrays.asList(daysArray));
        days.setMultiSelect(true);
        occursOnceSelection.addItem(Constants.OCCURS_ONCE_AT);
        occursEverySelection.addItem(Constants.OCCURS_EVERY);
        endDateSelection.addItem(Constants.END_DATE);
        endDateSelection.addItem(Constants.NO_END_DATE);
    }

    private void configureDateFields() {
        Page.Styles styles = Page.getCurrent().getStyles();
        styles.add(".time-only .v-datefield-calendarpanel-header, .time-only .v-datefield-calendarpanel-body { display: none; }");
        oneTimeDate.setResolution(Resolution.SECOND);
        occursOnceAt.setResolution(Resolution.SECOND);
        startingAt.setResolution(Resolution.SECOND);
        endingAt.setResolution(Resolution.SECOND);
        occursOnceAt.addStyleName(Constants.TIMEONLY);
        startingAt.addStyleName(Constants.TIMEONLY);
        endingAt.addStyleName(Constants.TIMEONLY);
        occursOnceAt.setDateFormat(Constants.Time_Format);
        startingAt.setDateFormat(Constants.Time_Format);
        endingAt.setDateFormat(Constants.Time_Format);
        startDate.setDateFormat(Constants.Date_Format);
        endDate.setDateFormat(Constants.Date_Format);
    }
}
