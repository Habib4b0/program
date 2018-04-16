/*
 * Copyright 2016 Abhiram.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.asi.calendarfield.client;

import com.google.gwt.aria.client.Roles;
import com.google.gwt.aria.client.SelectedValue;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.BrowserInfo;
import com.vaadin.client.DateTimeService;
import com.vaadin.client.VConsole;
import com.vaadin.client.ui.FocusableFlexTable;
import com.vaadin.client.ui.SubPartAware;
import com.vaadin.shared.ui.datefield.DateTimeResolution;
import com.vaadin.shared.util.SharedUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.asi.calendarfield.client.CalenderFieldUtil.CalendarDate;

/**
 *
 * @author Abhiram
 */
public class VCalendarFieldrPanel extends FocusableFlexTable implements
        KeyDownHandler, KeyPressHandler, MouseOutHandler, MouseDownHandler,
        MouseUpHandler, BlurHandler, FocusHandler, SubPartAware {

    public interface SubmitListener {

        /**
         * Called when calendar user triggers a submitting operation in calendar
         * panel. Eg. clicking on day or hitting enter.
         */
        void onSubmit();

        /**
         * On eg. ESC key.
         */
        void onCancel();
    }

    /**
     * Blur listener that listens to blur event from the panel
     */
    public interface FocusOutListener {

        /**
         * @return true if the calendar panel is not used after focus moves out
         */
        boolean onFocusOut(DomEvent<?> event);
    }


    /**
     * Represents a Date button in the calendar
     */
    private class VEventButton extends Button {

        public VEventButton() {
            addMouseDownHandler(VCalendarFieldrPanel.this);
            addMouseOutHandler(VCalendarFieldrPanel.this);
            addMouseUpHandler(VCalendarFieldrPanel.this);
        }
    }
    private static final String CN_FOCUSED = "focused";
    private static final String CN_TODAY = "today";
    private static final String CN_SELECTED = "selected";
    private static final String CN_OFFMONTH = "offmonth";
    private static final String CN_OUTSIDE_RANGE = "outside-range";
    private static final String CN_DISABLED = "disabled";
    private static final String INVISIBLE= "invisible";
    /**
     * Represents a click handler for when a user selects a value by using the
     * mouse
     */
    private ClickHandler dayClickHandler = new ClickHandler() {
        /*
         * (non-Javadoc)
         * 
         * @see
         * com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt
         * .event.dom.client.ClickEvent)
         */
        @Override
        public void onClick(ClickEvent event) {
            if (!isEnabled() || isReadonly()) {
                return;
            }
            Day clickedDay=(Day) event.getSource();
            Date newDate = clickedDay.getDate();
            if (!isDateInsideRange(newDate, DateTimeResolution.DAY)||!isEnabledDate(newDate)) {
                return;
            }
            if (newDate.getMonth() != displayedMonth.getMonth()
                    || newDate.getYear() != displayedMonth.getYear()) {
                // If an off-month date was clicked, we must change the
                // displayed month and re-render the calendar (#8931)
                displayedMonth.setMonth(newDate.getMonth());
                displayedMonth.setYear(newDate.getYear());
                renderCalendar();
            }
            focusDay(newDate,clickedDay);
            selectFocused();
            onSubmit();
        }
    };
    private VEventButton prevYear;
    private VEventButton nextYear;
    private VEventButton prevMonth;
    private VEventButton nextMonth;
    FlexTable bodyDays = new FlexTable();
    private DateTimeResolution resolution = DateTimeResolution.YEAR;
    private Timer mouseTimer;
    private Set<String> values = new HashSet<String>();
    private DateTimeService dateTimeService;
    private boolean showISOWeekNumbers;
    private CalendarDate displayedMonth;
    private CalendarDate focusedDate;
    private Set<Day> selectedDays = new HashSet<Day>();
    private Day focusedDay;
    private FocusOutListener focusOutListener;
    private SubmitListener submitListener;
    private boolean hasFocus = false;
    private VCalendarField parent;
    private boolean initialRenderDone = false;
    private boolean needHeader = true;
    private boolean prevYearEnabled = true;
    private boolean nextYearEnabled = true;
    private boolean prevMonthEnabled = true;
    private boolean nextMonthEnabled = true;
    private boolean yearVisible = true;
    int totalRow = 1;
    int totalCol = 1;
    boolean isYear = false;
    private List<String> disabledWeekDays = new ArrayList<String>();
    private List<String> disabledMonthlyDays = new ArrayList<String>();
    private List<String> disabledDates = new ArrayList<String>();    
    public VCalendarFieldrPanel() {
        getElement().setId(DOM.createUniqueId());
        setStyleName(VCalendarField.CLASSNAME + "-calendarpanel");
        Roles.getGridRole().set(getElement());

        /*
         * Firefox auto-repeat works correctly only if we use a key press
         * handler, other browsers handle it correctly when using a key down
         * handler
         */
        if (BrowserInfo.get().isGecko()) {
            addKeyPressHandler(this);
        } else {
            addKeyDownHandler(this);
        }
        addFocusHandler(this);
        addBlurHandler(this);
    }

    public void setParentField(VCalendarField parent) {
        this.parent = parent;
    }

    /**
     * Sets the focus to given date in the current view. Used when moving in the
     * calendar with the keyboard.
     *
     * @param date A Date representing the day of month to be focused. Must be
     * one of the days currently visible.
     */
    private void focusDay(Date date,Day curday) {
        // Only used when calender body is present
            if (focusedDay != null) {
                focusedDay.removeStyleDependentName(CN_FOCUSED);
            }

            if (date != null && focusedDate != null) {
                focusedDate.setTime(date.getTime());
                if(curday==null){
                    if (bodyDays.getRowCount() > 0) {
                    int colCount = bodyDays.getCellCount(0);
                    if (colCount > 0) {
                        Widget object = bodyDays;
                        if (isIsYear()) {
                            if (date.getMonth() > -1) {
                                int xInd = date.getMonth() / colCount;
                                int yInd = date.getMonth() % colCount;
                                object = bodyDays.getWidget(xInd, yInd);
                            }
                        }
                        if (object != null && object instanceof FlexTable) {
                            FlexTable days = (FlexTable) object;
                            int rowCount = days.getRowCount();
                            for (int i = 0; i < rowCount; i++) {
                                int cellCount = days.getCellCount(i);
                                for (int j = 0; j < cellCount; j++) {
                                    Widget widget = days.getWidget(i, j);
                                    if (widget != null && widget instanceof Day) {
                                        Day curday1 = (Day) widget;
                                        if (curday1.getDate().equals(date)) {
                                            curday1.addStyleDependentName(CN_FOCUSED);
                                            focusedDay = curday1;
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                }else{
                    curday.addStyleDependentName(CN_FOCUSED);
                focusedDay = curday;  
                } 
            }
    }

    /**
     * Sets the selection highlight to a given day in the current view
     *
     * @param date A Date representing the day of month to be selected. Must be
     * one of the days currently visible.
     *
     */
    private void selectDate(Date date,boolean select) {

        if (bodyDays.getRowCount() > 0) {
            int colCount = bodyDays.getCellCount(0);
            if (colCount > 0) {
                Widget object = bodyDays;
                if (isIsYear()) {
                    if (date.getMonth() > -1) {
                        int xInd = date.getMonth() / colCount;
                        int yInd = date.getMonth() % colCount;
                        object = bodyDays.getWidget(xInd, yInd);
                    }
                }
                if (object != null && object instanceof FlexTable) {
                    FlexTable days = (FlexTable) object;
                    int rowCount = days.getRowCount();
                    for (int i = 0; i < rowCount; i++) {
                        int cellCount = days.getCellCount(i);
                        for (int j = 0; j < cellCount; j++) {
                            Widget widget = days.getWidget(i, j);
                            if (widget != null && widget instanceof Day) {
                                Day curday = (Day) widget;
                                if (curday.getDate().equals(date)) {
                                    if (select) {
                                        curday.addStyleDependentName(CN_SELECTED);
                                        selectedDays.add(curday);
                                        Roles.getGridcellRole().setAriaSelectedState(
                                                curday.getElement(), SelectedValue.TRUE);
                                    } else {
                                        selectedDays.remove(curday);
                                        curday.removeStyleDependentName(CN_SELECTED);
                                        Roles.getGridcellRole().removeAriaSelectedState(
                                                curday.getElement());
                                    }
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }    

    public Set<String> getValues(){        
        return values;
    }
    /**
     * Updates year, month, day from focusedDate to value
     */
    private void selectFocused() {
        if (focusedDate != null && isDateInsideRange(focusedDate, resolution)&&isEnabledDate(focusedDate)) {

            /*
             * #5594 set Date (day) to 1 in order to prevent any kind of
             * wrapping of months when later setting the month. (e.g. 31 ->
             * month with 30 days -> wraps to the 1st of the following month,
             * e.g. 31st of May -> 31st of April = 1st of May)
             */
            CalendarDate value = new CalendarDate();
            value.setDate(1);
            if (value.getYear() != focusedDate.getYear()) {
                value.setYear(focusedDate.getYear());
            }
            if (value.getMonth() != focusedDate.getMonth()) {
                value.setMonth(focusedDate.getMonth());
            }
            if (value.getDate() != focusedDate.getDate()) {
            }
            // We always need to set the date, even if it hasn't changed, since
            // it was forced to 1 above.
            value.setDate(focusedDate.getDate());
            String dateString=value.toString();
            boolean toDo=values.add(dateString);
            if(!toDo){
                values.remove(dateString);            
            }    
            selectDate(focusedDate,toDo);            
        } else {
            VConsole.log("Trying to select a the focused date which is NULL!");
        }
    }

    protected boolean onValueChange() {
        return false;
    }

    public DateTimeResolution getResolution() {
        return resolution;
    }

    public void setResolution(DateTimeResolution resolution) {
        this.resolution = resolution;
    }

    private boolean isReadonly() {
        return parent.isReadonly();
    }

    private boolean isEnabled() {
        return parent.isEnabled();
    }

    @Override
    public void setStyleName(String style) {
        super.setStyleName(style);
        if (initialRenderDone) {
            // Dynamic updates to the stylename needs to render the calendar to
            // update the inner element stylenames
            renderCalendar();
        }
    }

    @Override
    public void setStylePrimaryName(String style) {
        super.setStylePrimaryName(style);
        if (initialRenderDone) {
            // Dynamic updates to the stylename needs to render the calendar to
            // update the inner element stylenames
            renderCalendar();
        }
    }

    private void clearCalendarBody(boolean remove) {
        if (!remove) {
            // Leave the cells in place but clear their contents

            // This has the side effect of ensuring that the calendar always
            // contain 7 rows.
            if (isIsYear()) {
                int rowCount = bodyDays.getRowCount();
                for (int i = 0; i < rowCount; i++) {
                    int cellCount = bodyDays.getCellCount(i);
                    for (int j = 0; j < cellCount; j++) {
                        Widget object = bodyDays.getWidget(i, j);
                        if (object != null && object instanceof FlexTable) {
                            FlexTable days = (FlexTable) object;
                            for (int row = 1; row < 7; row++) {
                                for (int col = 0; col < 8; col++) {
                                    days.setHTML(row, col, "&nbsp;");
                                }
                            }
                        }
                    }
                }
            } else {
                for (int row = 1; row < 7; row++) {
                    for (int col = 0; col < 8; col++) {
                        bodyDays.setHTML(row, col, "&nbsp;");
                    }
                }
            }
        } else if (getRowCount() > 1) {
            removeRow(1);
            bodyDays.clear();
        }
    }

    /**
     * Builds the top buttons and current month and year header.
     *
     * @param needsMonth Should the month buttons be visible?
     */
    private void buildCalendarHeader(boolean needsMonth) {

        getRowFormatter().addStyleName(0,
                parent.getStylePrimaryName() + "-calendarpanel-header");

        if (prevMonth == null && needsMonth) {
            prevMonth = new VEventButton();
            prevMonth.setHTML("&lsaquo;");
            prevMonth.setStyleName("v-button-prevmonth");
            if (isPrevMonthEnabled()) {
                prevMonth.removeStyleName(INVISIBLE);
            } else {
                prevMonth.addStyleName(INVISIBLE);
            }
            prevMonth.setTabIndex(-1);

            nextMonth = new VEventButton();
            nextMonth.setHTML("&rsaquo;");
            nextMonth.setStyleName("v-button-nextmonth");
            if (isNextMonthEnabled()) {
                nextMonth.removeStyleName(INVISIBLE);
            } else {
                nextMonth.addStyleName(INVISIBLE);
            }
            nextMonth.setTabIndex(-1);

            setWidget(0, 3, nextMonth);
            setWidget(0, 1, prevMonth);
        } else if (prevMonth != null && !needsMonth) {
            // Remove month traverse buttons
            remove(prevMonth);
            remove(nextMonth);
            prevMonth = null;
            nextMonth = null;
        }

        if (prevYear == null) {

            prevYear = new VEventButton();
            prevYear.setHTML("&laquo;");
            prevYear.setStyleName("v-button-prevyear");
            if (isPrevYearEnabled()) {
                prevYear.removeStyleName(INVISIBLE);
            } else {
                prevYear.addStyleName(INVISIBLE);
            }
            prevYear.setTabIndex(-1);
            nextYear = new VEventButton();
            nextYear.setHTML("&raquo;");
            nextYear.setStyleName("v-button-nextyear");
            if (isNextYearEnabled()) {
                nextYear.removeStyleName(INVISIBLE);
            } else {
                nextYear.addStyleName(INVISIBLE);
            }
            nextYear.setTabIndex(-1);
            setWidget(0, 0, prevYear);
            setWidget(0, 4, nextYear);
        }

        updateControlButtonRangeStyles(needsMonth);

        
        final int year = displayedMonth.getYear() + 1900;

        getFlexCellFormatter().setStyleName(0, 2,
                parent.getStylePrimaryName() + "-calendarpanel-month");
        getFlexCellFormatter().setStyleName(0, 0,
                parent.getStylePrimaryName() + "-calendarpanel-prevyear");
        getFlexCellFormatter().setStyleName(0, 4,
                parent.getStylePrimaryName() + "-calendarpanel-nextyear");
        getFlexCellFormatter().setStyleName(0, 3,
                parent.getStylePrimaryName() + "-calendarpanel-nextmonth");
        getFlexCellFormatter().setStyleName(0, 1,
                parent.getStylePrimaryName() + "-calendarpanel-prevmonth");
        final String monthName = needsMonth ? getDateTimeService().getMonth(
                displayedMonth.getMonth()) : "";
        setHTML(0, 2, "<span class=\"" + parent.getStylePrimaryName()
                + "-calendarpanel-month\">" + monthName + " " + year
                + "</span>");
    }

    private void updateControlButtonRangeStyles(boolean needsMonth) {

        if (focusedDate == null) {
            return;
        }

        if (needsMonth) {
            Date prevMonthDate = (Date) focusedDate.clone();
            removeOneMonth(prevMonthDate);

            if (!isDateInsideRange(prevMonthDate, DateTimeResolution.MONTH)) {
                prevMonth.addStyleName(CN_OUTSIDE_RANGE);
            } else {
                prevMonth.removeStyleName(CN_OUTSIDE_RANGE);
            }
            Date nextMonthDate = (Date) focusedDate.clone();
            addOneMonth(nextMonthDate);
            if (!isDateInsideRange(nextMonthDate, DateTimeResolution.MONTH)) {
                nextMonth.addStyleName(CN_OUTSIDE_RANGE);
            } else {
                nextMonth.removeStyleName(CN_OUTSIDE_RANGE);
            }
        }

        Date prevYearDate = (Date) focusedDate.clone();
        prevYearDate.setYear(prevYearDate.getYear() - 1);
        if (!isDateInsideRange(prevYearDate, DateTimeResolution.YEAR)) {
            prevYear.addStyleName(CN_OUTSIDE_RANGE);
        } else {
            prevYear.removeStyleName(CN_OUTSIDE_RANGE);
        }

        Date nextYearDate = (Date) focusedDate.clone();
        nextYearDate.setYear(nextYearDate.getYear() + 1);
        if (!isDateInsideRange(nextYearDate, DateTimeResolution.YEAR)) {
            nextYear.addStyleName(CN_OUTSIDE_RANGE);
        } else {
            nextYear.removeStyleName(CN_OUTSIDE_RANGE);
        }

    }

    private DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    /**
     * Returns whether ISO 8601 week numbers should be shown in the value
     * selector or not. ISO 8601 defines that a week always starts with a Monday
     * so the week numbers are only shown if this is the case.
     *
     * @return true if week number should be shown, false otherwise
     */
    public boolean isShowISOWeekNumbers() {
        return showISOWeekNumbers;
    }

    public void setShowISOWeekNumbers(boolean showISOWeekNumbers) {
        this.showISOWeekNumbers = showISOWeekNumbers;
    }

    /**
     * Checks inclusively whether a date is inside a range of dates or not.
     *
     * @param date
     * @return
     */
    private boolean isDateInsideRange(Date date, DateTimeResolution minResolution) {
        assert (date != null);

        return isAcceptedByRangeEnd(date, minResolution)
                && isAcceptedByRangeStart(date, minResolution);
    }

    /**
     * Accepts dates greater than or equal to rangeStart, depending on the
     * resolution. If the resolution is set to DAY, the range will compare on a
     * day-basis. If the resolution is set to YEAR, only years are compared. So
     * even if the range is set to one millisecond in next year, also next year
     * will be included.
     *
     * @param date
     * @param minResolution
     * @return
     */
    private boolean isAcceptedByRangeStart(Date date, DateTimeResolution minResolution) {
        assert (date != null);

        // rangeStart == null means that we accept all values below rangeEnd
        if (rangeStart == null) {
            return true;
        }

        Date valueDuplicate = (Date) date.clone();
        Date rangeStartDuplicate = (Date) rangeStart.clone();

        if (minResolution == DateTimeResolution.YEAR) {
            return valueDuplicate.getYear() >= rangeStartDuplicate.getYear();
        }
        if (minResolution == DateTimeResolution.MONTH) {
            valueDuplicate = clearDateBelowMonth(valueDuplicate);
            rangeStartDuplicate = clearDateBelowMonth(rangeStartDuplicate);
        } else {
            valueDuplicate = clearDateBelowDay(valueDuplicate);
            rangeStartDuplicate = clearDateBelowDay(rangeStartDuplicate);
        }

        return !rangeStartDuplicate.after(valueDuplicate);
    }

    /**
     * Accepts dates earlier than or equal to rangeStart, depending on the
     * resolution. If the resolution is set to DAY, the range will compare on a
     * day-basis. If the resolution is set to YEAR, only years are compared. So
     * even if the range is set to one millisecond in next year, also next year
     * will be included.
     *
     * @param date
     * @param minResolution
     * @return
     */
    private boolean isAcceptedByRangeEnd(Date date, DateTimeResolution minResolution) {
        assert (date != null);

        // rangeEnd == null means that we accept all values above rangeStart
        if (rangeEnd == null) {
            return true;
        }

        Date valueDuplicate = (Date) date.clone();
        Date rangeEndDuplicate = (Date) rangeEnd.clone();

        if (minResolution == DateTimeResolution.YEAR) {
            return valueDuplicate.getYear() <= rangeEndDuplicate.getYear();
        }
        if (minResolution == DateTimeResolution.MONTH) {
            valueDuplicate = clearDateBelowMonth(valueDuplicate);
            rangeEndDuplicate = clearDateBelowMonth(rangeEndDuplicate);
        } else {
            valueDuplicate = clearDateBelowDay(valueDuplicate);
            rangeEndDuplicate = clearDateBelowDay(rangeEndDuplicate);
        }

        return !rangeEndDuplicate.before(valueDuplicate);

    }
    /**
     * Accepts dates earlier than or equal to rangeStart, depending on the
     * resolution. If the resolution is set to DAY, the range will compare on a
     * day-basis. If the resolution is set to YEAR, only years are compared. So
     * even if the range is set to one millisecond in next year, also next year
     * will be included.
     *
     * @param date
     * @param minResolution
     * @return
     */
    private boolean isEnabledDate(Date date) {
        assert (date != null);
        return isAcceptedDisabledDate(date)&&isAcceptedDisabledMonthlyDays(date.getDate())&&isAcceptedDisabledWeekDays(date.getDay());
    }
    private boolean isAcceptedDisabledDate(Date date) {  
        assert (date != null);
        if (disabledDates == null||disabledDates.isEmpty()) {
            return true;
        }
        return !disabledDates.contains(CalenderFieldUtil.getCalendarDate(date).toString());
    }
    private boolean isAcceptedDisabledMonthlyDays(int day) {
        if (disabledMonthlyDays == null||disabledMonthlyDays.isEmpty()) {
            return true;
        }
        return !disabledMonthlyDays.contains(""+day);
    }
    private boolean isAcceptedDisabledWeekDays(int day) {
        if (disabledWeekDays == null||disabledWeekDays.isEmpty()) {
            return true;
        }
        return !disabledWeekDays.contains(""+day);
    }
   
    private static Date clearDateBelowMonth(Date date) {
        date.setDate(1);
        return clearDateBelowDay(date);
    }

    private static Date clearDateBelowDay(Date date) {
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        // Clearing milliseconds
        long time = date.getTime() / 1000;
        date = new Date(time * 1000);
        return date;
    }
    
    /**
     * Builds the day and time selectors of the calendar.
     */
    private void buildCalendarBody() {
        setWidget(1, 0, bodyDays);
        setCellPadding(0);
        setCellSpacing(0);
        getFlexCellFormatter().setColSpan(1, 0, 5);
        getFlexCellFormatter().setStyleName(1, 0,
                parent.getStylePrimaryName() + "-calendarpanel-body");
        List<Date> selectedDates = new ArrayList<>();
        for (String value : values) {
            Date selectedDate = CalenderFieldUtil.getDateFromString(value);
            selectedDates.add(selectedDate);
        }
        Date displayDate = (Date) displayedMonth.clone();
        int monthNo = 0;
        if (!isIsYear()) {
            setTotalRow(1);
            setTotalCol(1);
        }
        for (int myRowNo = 0; myRowNo < getTotalRow(); myRowNo++) {
            for (int myColNo = 0; myColNo < getTotalCol(); myColNo++) {
                FlexTable days = new FlexTable();
                int weekColumn = 0;
                int firstWeekdayColumn = 1;
                int headerRow = 0;
                if (isIsYear()) {
                    headerRow = 1;
                    days.setStyleName(VCalendarField.NEW_CLASSNAME + "-child");
                    bodyDays.setWidget(myRowNo, myColNo, days);
                    displayDate.setMonth(monthNo);
                    final String monthName = getDateTimeService().getMonth(monthNo);
                    days.setHTML(0, 0, "<span class=\"" + parent.getStylePrimaryName()
                            + "-calendarpanel-month\">" + monthName
                            + "</span>");
                    days.getFlexCellFormatter().setColSpan(0, 0, 8);
                    monthNo++;
                } else {
                    days = bodyDays;
                }


                days.getFlexCellFormatter().setStyleName(headerRow, weekColumn,
                        "v-week");
                days.setHTML(headerRow, weekColumn, "<strong></strong>");
                // Hide the week column if week numbers are not to be displayed.
                days.getFlexCellFormatter().setVisible(headerRow, weekColumn,
                        isShowISOWeekNumbers());

                days.getRowFormatter().setStyleName(headerRow,
                        parent.getStylePrimaryName() + "-calendarpanel-weekdays");

                if (isShowISOWeekNumbers()) {
                    days.getFlexCellFormatter().setStyleName(headerRow, weekColumn,
                            "v-first");
                    days.getFlexCellFormatter().setStyleName(headerRow,
                            firstWeekdayColumn, "");
                    days.getRowFormatter()
                            .addStyleName(
                            headerRow,
                            parent.getStylePrimaryName()
                            + "-calendarpanel-weeknumbers");
                } else {
                    days.getFlexCellFormatter().setStyleName(headerRow, weekColumn, "");
                    days.getFlexCellFormatter().setStyleName(headerRow,
                            firstWeekdayColumn, "v-first");
                }

                days.getFlexCellFormatter().setStyleName(headerRow,
                        firstWeekdayColumn + 6, "v-last");

                // Print weekday names
                final int firstDay = getDateTimeService().getFirstDayOfWeek();
                for (int i = 0; i < 7; i++) {
                    int day = i + firstDay;
                    if (day > 6) {
                        day = 0;
                    }

                        days.setHTML(headerRow, firstWeekdayColumn + i, "<strong>"
                                + getDateTimeService().getShortDay(day) + "</strong>");

                    Roles.getColumnheaderRole().set(
                            days.getCellFormatter().getElement(headerRow,
                            firstWeekdayColumn + i));
                }

                // Zero out hours, minutes, seconds, and milliseconds to compare dates
                // without time part
                final Date tmp = new Date();
                final Date today = new Date(tmp.getYear(), tmp.getMonth(),
                        tmp.getDate());



                final int startWeekDay = getDateTimeService().getStartWeekDay(
                        displayDate);
                final Date curr = (Date) displayDate.clone();
                // Start from the first day of the week that at least partially belongs
                // to the current month
                curr.setDate(1 - startWeekDay);
                // No month has more than 6 weeks so 6 is a safe maximum for rows.

                for (int weekOfMonth = headerRow+1; weekOfMonth < (headerRow+7); weekOfMonth++) {
                    for (int dayOfWeek = 0; dayOfWeek < 7; dayOfWeek++) {
                        if (curr.getMonth() == displayDate.getMonth()) {
                            // Actually write the day of month
                            Date dayDate = (Date) curr.clone();
                            Day day = new Day(dayDate);
                            
                            day.setStyleName(parent.getStylePrimaryName()
                                    + "-calendarpanel-day");

                            if (!isDateInsideRange(dayDate, DateTimeResolution.DAY)) {
                                day.addStyleDependentName(CN_OUTSIDE_RANGE);
                            }
                            if (!isEnabledDate(dayDate)) {
                                day.addStyleDependentName(CN_OUTSIDE_RANGE);
                                day.addStyleDependentName(CN_DISABLED);
                            }
                            int dateSize = selectedDates.size();
                            for (int i = 0; i < dateSize; i++) {
                                Date selectedDate = selectedDates.get(i);
                                if (curr.equals(selectedDate)) {
                                    day.addStyleDependentName(CN_SELECTED);
                                    Roles.getGridcellRole().setAriaSelectedState(
                                            day.getElement(), SelectedValue.TRUE);
                                    selectedDays.add(day);
                                    selectedDates.remove(i);
                                    dateSize--;
                                    i--;
                                }
                            }

                            if (curr.equals(today)) {
                                day.addStyleDependentName(CN_TODAY);
                            }
                            if (curr.equals(focusedDate)) {
                                focusedDay = day;
                                if (hasFocus) {
                                    day.addStyleDependentName(CN_FOCUSED);
                                }
                            }
                            if (curr.getMonth() != displayDate.getMonth()) {
                                day.addStyleDependentName(CN_OFFMONTH);
                            }

                            days.setWidget(weekOfMonth, firstWeekdayColumn + dayOfWeek, day);
                            Roles.getGridcellRole().set(
                                    days.getCellFormatter().getElement(weekOfMonth,
                                    firstWeekdayColumn + dayOfWeek));

                            // ISO week numbers if requested
                            days.getCellFormatter().setVisible(weekOfMonth, weekColumn,
                                    isShowISOWeekNumbers());

                            if (isShowISOWeekNumbers()) {
                                final String baseCssClass = parent.getStylePrimaryName()
                                        + "-calendarpanel-weeknumber";
                                String weekCssClass = baseCssClass;

                                int weekNumber = DateTimeService.getISOWeekNumber(curr);

                                days.setHTML(weekOfMonth, weekColumn, "<span class=\""
                                        + weekCssClass + "\"" + ">" + weekNumber
                                        + "</span>");
                            }
                        }else{
                            days.setHTML(weekOfMonth, firstWeekdayColumn + dayOfWeek, "&nbsp;");
                        }
                        curr.setDate(curr.getDate() + 1);
                    }
                }
            }
        }

    }
   
    /**
     * Updates the calendar and text field with the selected dates.
     */
    public void renderCalendar() {
        renderCalendar(true);
    }

    /**
     * For internal use only. May be removed or replaced in the future.
     *
     * Updates the calendar and text field with the selected dates.
     *
     * @param updateDate The value false prevents setting the selected date of
     * the calendar based on focusedDate. That can be used when only the
     * resolution of the calendar is changed and no date has been selected.
     */
    public void renderCalendar(boolean updateDate) {

        super.setStylePrimaryName(parent.getStylePrimaryName()
                + "-calendarpanel");

        if (focusedDate == null) {
            Date now = new Date();
            // focusedDate must have zero hours, mins, secs, millisecs
            focusedDate = new CalendarDate(now.getYear(), now.getMonth(),
                    now.getDate());
            displayedMonth = new CalendarDate(now.getYear(), now.getMonth(), 1);
        }

        final boolean needsMonth = !isIsYear();
        boolean needsBody = true;
        if (isNeedHeader()) {
            buildCalendarHeader(needsMonth);
        }
        clearCalendarBody(!needsBody);
        if (needsBody) {
            buildCalendarBody();
        }
        initialRenderDone = true;
    }

    /**
     * Moves the focus forward the given number of days.
     */
    private void focusNextDay(int days,int shifting) {
        if (focusedDate == null) {
            return;
        }

        Date focusCopy = ((Date) focusedDate.clone());
        focusCopy.setDate(focusedDate.getDate() + (days*shifting));
        if(!isEnabledDate(focusCopy)){
            focusNextDay(days,shifting+1);
        }else{
        if (!isDateInsideRange(focusCopy, resolution)) {
            // If not inside allowed range, then do not move anything
            return;
        }

        int oldMonth = focusedDate.getMonth();
        int oldYear = focusedDate.getYear();
        focusedDate.setDate(focusedDate.getDate() + (days*shifting));

        if (focusedDate.getMonth() == oldMonth
                && focusedDate.getYear() == oldYear) {
            // Month did not change, only move the selection
            focusDay(focusedDate,null);
        } else {

            // If the month changed we need to re-render the calendar
            displayedMonth.setMonth(focusedDate.getMonth());
            displayedMonth.setYear(focusedDate.getYear());
            renderCalendar();
        }
        }
    }

    /**
     * Moves the focus backward the given number of days.
     */
    private void focusPreviousDay(int days,int shifting) {
        focusNextDay(-days,shifting);
    }

    /**
     * Selects the next month
     */
    private void focusNextMonth() {

        if (focusedDate == null) {
            return;
        }
        // Trying to request next month
        Date requestedNextMonthDate = (Date) focusedDate.clone();
        addOneMonth(requestedNextMonthDate);

        if (!isDateInsideRange(requestedNextMonthDate, DateTimeResolution.MONTH)) {
            return;
        }

        // Now also checking whether the day is inside the range or not. If not
        // inside,
        // correct it
        if (!isDateInsideRange(requestedNextMonthDate, DateTimeResolution.DAY)) {
            requestedNextMonthDate = adjustDateToFitInsideRange(requestedNextMonthDate);
        }

        focusedDate.setTime(requestedNextMonthDate.getTime());
        displayedMonth.setMonth(displayedMonth.getMonth() + 1);

        renderCalendar();
    }

    private static void addOneMonth(Date date) {
        int currentMonth = date.getMonth();
        int requestedMonth = (currentMonth + 1) % 12;

        date.setMonth(date.getMonth() + 1);

        /*
         * If the selected value was e.g. 31.3 the new value would be 31.4 but
         * this value is invalid so the new value will be 1.5. This is taken
         * care of by decreasing the value until we have the correct month.
         */
        while (date.getMonth() != requestedMonth) {
            date.setDate(date.getDate() - 1);
        }
    }

    private static void removeOneMonth(Date date) {
        int currentMonth = date.getMonth();

        date.setMonth(date.getMonth() - 1);

        /*
         * If the selected value was e.g. 31.12 the new value would be 31.11 but
         * this value is invalid so the new value will be 1.12. This is taken
         * care of by decreasing the value until we have the correct month.
         */
        while (date.getMonth() == currentMonth) {
            date.setDate(date.getDate() - 1);
        }
    }

    /**
     * Selects the previous month
     */
    private void focusPreviousMonth() {

        if (focusedDate == null) {
            return;
        }
        Date requestedPreviousMonthDate = (Date) focusedDate.clone();
        removeOneMonth(requestedPreviousMonthDate);

        if (!isDateInsideRange(requestedPreviousMonthDate, DateTimeResolution.MONTH)) {
            return;
        }

        if (!isDateInsideRange(requestedPreviousMonthDate, DateTimeResolution.DAY)) {
            requestedPreviousMonthDate = adjustDateToFitInsideRange(requestedPreviousMonthDate);
        }
        focusedDate.setTime(requestedPreviousMonthDate.getTime());
        displayedMonth.setMonth(displayedMonth.getMonth() - 1);

        renderCalendar();
    }

    /**
     * Selects the previous year
     */
    private void focusPreviousYear(int years) {

        if (focusedDate == null) {
            return;
        }
        Date previousYearDate = (Date) focusedDate.clone();
        previousYearDate.setYear(previousYearDate.getYear() - years);
        // Do not focus if not inside range
        if (!isDateInsideRange(previousYearDate, DateTimeResolution.YEAR)) {
            return;
        }
        // If we remove one year, but have to roll back a bit, fit it
        // into the calendar. Also the months have to be changed
        if (!isDateInsideRange(previousYearDate, DateTimeResolution.DAY)) {
            previousYearDate = adjustDateToFitInsideRange(previousYearDate);

            focusedDate.setYear(previousYearDate.getYear());
            focusedDate.setMonth(previousYearDate.getMonth());
            focusedDate.setDate(previousYearDate.getDate());
            displayedMonth.setYear(previousYearDate.getYear());
            displayedMonth.setMonth(previousYearDate.getMonth());
        } else {

            int currentMonth = focusedDate.getMonth();
            focusedDate.setYear(focusedDate.getYear() - years);
            displayedMonth.setYear(displayedMonth.getYear() - years);
            /*
             * If the focused date was a leap day (Feb 29), the new date becomes
             * Mar 1 if the new year is not also a leap year. Set it to Feb 28
             * instead.
             */
            if (focusedDate.getMonth() != currentMonth) {
                focusedDate.setDate(0);
            }
        }

        renderCalendar();
    }

    /**
     * Selects the next year
     */
    private void focusNextYear(int years) {

        if (focusedDate == null) {
            return;
        }
        Date nextYearDate = (Date) focusedDate.clone();
        nextYearDate.setYear(nextYearDate.getYear() + years);
        // Do not focus if not inside range
        if (!isDateInsideRange(nextYearDate, DateTimeResolution.YEAR)) {
            return;
        }
        // If we add one year, but have to roll back a bit, fit it
        // into the calendar. Also the months have to be changed
        if (!isDateInsideRange(nextYearDate, DateTimeResolution.DAY)) {
            nextYearDate = adjustDateToFitInsideRange(nextYearDate);

            focusedDate.setYear(nextYearDate.getYear());
            focusedDate.setMonth(nextYearDate.getMonth());
            focusedDate.setDate(nextYearDate.getDate());
            displayedMonth.setYear(nextYearDate.getYear());
            displayedMonth.setMonth(nextYearDate.getMonth());
        } else {

            int currentMonth = focusedDate.getMonth();
            focusedDate.setYear(focusedDate.getYear() + years);
            displayedMonth.setYear(displayedMonth.getYear() + years);
            /*
             * If the focused date was a leap day (Feb 29), the new date becomes
             * Mar 1 if the new year is not also a leap year. Set it to Feb 28
             * instead.
             */
            if (focusedDate.getMonth() != currentMonth) {
                focusedDate.setDate(0);
            }
        }

        renderCalendar();
    }

    /**
     * Handles a user click on the component
     *
     * @param sender The component that was clicked
     * @param updateVariable Should the value field be updated
     *
     */
    private void processClickEvent(Widget sender) {
        if (!isEnabled() || isReadonly()) {
            return;
        }
        if (sender == prevYear) {
            if (isPrevYearEnabled()) {
                focusPreviousYear(1);
            }
        } else if (sender == nextYear) {
            if (isNextYearEnabled()) {
                focusNextYear(1);
            }
        } else if (sender == prevMonth) {
            if (isPrevMonthEnabled()) {
                focusPreviousMonth();
            }
        } else if (sender == nextMonth) {
            if (isNextMonthEnabled()) {
                focusNextMonth();
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.KeyDownHandler#onKeyDown(com.google.gwt
     * .event.dom.client.KeyDownEvent)
     */
    @Override
    public void onKeyDown(KeyDownEvent event) {
        handleKeyPress(event);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.KeyPressHandler#onKeyPress(com.google
     * .gwt.event.dom.client.KeyPressEvent)
     */
    @Override
    public void onKeyPress(KeyPressEvent event) {
        handleKeyPress(event);
    }

    /**
     * Handles the keypress from both the onKeyPress event and the onKeyDown
     * event
     *
     * @param event The keydown/keypress event
     */
    private void handleKeyPress(DomEvent<?> event) {
        // Special handling for events from time ListBoxes.

        // Check tabs
        int keycode = event.getNativeEvent().getKeyCode();
        if (keycode == KeyCodes.KEY_TAB && event.getNativeEvent().getShiftKey()) {
            if (onTabOut(event)) {
                return;
            }
        }

        // Handle the navigation
        if (handleNavigation(keycode, event.getNativeEvent().getCtrlKey()
                || event.getNativeEvent().getMetaKey(), event.getNativeEvent()
                .getShiftKey())) {
            event.preventDefault();
        }

    }

    /**
     * Notifies submit-listeners of a submit event
     */
    private void onSubmit() {
        if (getSubmitListener() != null) {
            getSubmitListener().onSubmit();
        }
    }

    /**
     * Notifies submit-listeners of a cancel event
     */
    private void onCancel() {
        if (getSubmitListener() != null) {
            getSubmitListener().onCancel();
        }
    }

    

    /**
     * Handle keyboard navigation what the resolution is set to DAY
     *
     * @param keycode The keycode to handle
     * @param ctrl Was the ctrl key pressed?
     * @param shift Was the shift key pressed?
     * @return Return true if the key press was handled by the method, else
     * return false.
     */
    protected boolean handleNavigationDayMode(int keycode, boolean ctrl,
            boolean shift) {

        // Ctrl key is not in use
        if (ctrl) {
            return false;
        }

        /*
         * Jumps to the next day.
         */
        if (keycode == getForwardKey() && !shift) {
            focusNextDay(1,1);
            return true;

            /*
             * Jumps to the previous day
             */
        } else if (keycode == getBackwardKey() && !shift) {
            focusPreviousDay(1,1);
            return true;

            /*
             * Jumps one week forward in the calendar
             */
        } else if (keycode == getNextKey() && !shift) {
            focusNextDay(7,1);
            return true;

            /*
             * Jumps one week back in the calendar
             */
        } else if (keycode == getPreviousKey() && !shift) {
            focusPreviousDay(7,1);
            return true;

            /*
             * Selects the value that is chosen
             */
        } else if (keycode == getSelectKey() && !shift) {
            selectFocused();
            onSubmit(); // submit
            return true;

        } else if (keycode == getCloseKey()) {
            onCancel();
            // TODO close event

            return true;

            /*
             * Jumps to the next month
             */
        } else if (shift && keycode == getForwardKey()) {
            focusNextMonth();
            return true;

            /*
             * Jumps to the previous month
             */
        } else if (shift && keycode == getBackwardKey()) {
            focusPreviousMonth();
            return true;

            /*
             * Jumps to the next year
             */
        } else if (shift && keycode == getPreviousKey()) {
            focusNextYear(1);
            return true;

            /*
             * Jumps to the previous year
             */
        } else if (shift && keycode == getNextKey()) {
            focusPreviousYear(1);
            return true;

            /*
             * Resets the selection
             */
        } else if (keycode == getResetKey() && !shift) {
            // Restore showing value the selected value
            if (!values.isEmpty()) {
                Date value = (Date) values.toArray()[0];
                focusedDate = new CalendarDate(value.getYear(), value.getMonth(),
                        value.getDate());
                displayedMonth = new CalendarDate(value.getYear(), value.getMonth(),
                        1);
            }

            renderCalendar();
            return true;
        }

        return false;
    }

    /**
     * Handles the keyboard navigation
     *
     * @param keycode The key code that was pressed
     * @param ctrl Was the ctrl key pressed
     * @param shift Was the shift key pressed
     * @return Return true if key press was handled by the component, else
     * return false
     */
    protected boolean handleNavigation(int keycode, boolean ctrl, boolean shift) {
        if (!isEnabled() || isReadonly()) {
            return false;
        } else{
            return handleNavigationDayMode(keycode, ctrl, shift);
        }
    }

    /**
     * Returns the reset key which will reset the calendar to the previous
     * selection. By default this is backspace but it can be overriden to change
     * the key to whatever you want.
     *
     * @return
     */
    protected int getResetKey() {
        return KeyCodes.KEY_BACKSPACE;
    }

    /**
     * Returns the select key which selects the value. By default this is the
     * enter key but it can be changed to whatever you like by overriding this
     * method.
     *
     * @return
     */
    protected int getSelectKey() {
        return KeyCodes.KEY_ENTER;
    }

    /**
     * Returns the key that closes the popup window if this is a VPopopCalendar.
     * Else this does nothing. By default this is the Escape key but you can
     * change the key to whatever you want by overriding this method.
     *
     * @return
     */
    protected int getCloseKey() {
        return KeyCodes.KEY_ESCAPE;
    }

    /**
     * The key that selects the next day in the calendar. By default this is the
     * right arrow key but by overriding this method it can be changed to
     * whatever you like.
     *
     * @return
     */
    protected int getForwardKey() {
        return KeyCodes.KEY_RIGHT;
    }

    /**
     * The key that selects the previous day in the calendar. By default this is
     * the left arrow key but by overriding this method it can be changed to
     * whatever you like.
     *
     * @return
     */
    protected int getBackwardKey() {
        return KeyCodes.KEY_LEFT;
    }

    /**
     * The key that selects the next week in the calendar. By default this is
     * the down arrow key but by overriding this method it can be changed to
     * whatever you like.
     *
     * @return
     */
    protected int getNextKey() {
        return KeyCodes.KEY_DOWN;
    }

    /**
     * The key that selects the previous week in the calendar. By default this
     * is the up arrow key but by overriding this method it can be changed to
     * whatever you like.
     *
     * @return
     */
    protected int getPreviousKey() {
        return KeyCodes.KEY_UP;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.MouseOutHandler#onMouseOut(com.google
     * .gwt.event.dom.client.MouseOutEvent)
     */
    @Override
    public void onMouseOut(MouseOutEvent event) {
        if (mouseTimer != null) {
            mouseTimer.cancel();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.MouseDownHandler#onMouseDown(com.google
     * .gwt.event.dom.client.MouseDownEvent)
     */
    @Override
    public void onMouseDown(MouseDownEvent event) {
        // Click-n-hold the left mouse button for fast-forward or fast-rewind.
        // Timer is first used for a 500ms delay after mousedown. After that has
        // elapsed, another timer is triggered to go off every 150ms. Both
        // timers are cancelled on mouseup or mouseout.
        if (event.getNativeButton() == NativeEvent.BUTTON_LEFT
                && event.getSource() instanceof VEventButton) {
            final VCalendarFieldrPanel.VEventButton sender = (VEventButton) event.getSource();
            processClickEvent(sender);
            mouseTimer = new Timer() {
                @Override
                public void run() {
                    mouseTimer = new Timer() {
                        @Override
                        public void run() {
                            processClickEvent(sender);
                        }
                    };
                    mouseTimer.scheduleRepeating(150);
                }
            };
            mouseTimer.schedule(500);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.MouseUpHandler#onMouseUp(com.google.gwt
     * .event.dom.client.MouseUpEvent)
     */
    @Override
    public void onMouseUp(MouseUpEvent event) {
        if (mouseTimer != null) {
            mouseTimer.cancel();
        }
    }

    /**
     * Adjusts a date to fit inside the range, only if outside
     *
     * @param date
     */
    private Date adjustDateToFitInsideRange(Date date) {        
        if (rangeStart != null && rangeStart.after(date)) {
            date = (Date) rangeStart.clone();
        } else if (rangeEnd != null && rangeEnd.before(date)) {
            date = (Date) rangeEnd.clone();
        }
        return date;
    }

    public void manageFocusRange(Date currentDate){
        boolean currentDateWasAdjusted=false;
        if (currentDate != null && !isDateInsideRange(currentDate, resolution)) {
            currentDate = adjustDateToFitInsideRange(currentDate);
            currentDateWasAdjusted = true;
        }

        if (currentDate == null || currentDateWasAdjusted) {
            // If ranges enabled, we may need to focus on a different view to
            // potentially not get stuck
            if (rangeStart != null || rangeEnd != null) {
                Date dateThatFitsInsideRange = adjustDateToFitInsideRange(new Date());
                focusedDate = new CalendarDate(
                        dateThatFitsInsideRange.getYear(),
                        dateThatFitsInsideRange.getMonth(),
                        dateThatFitsInsideRange.getDate());
                displayedMonth = new CalendarDate(
                        dateThatFitsInsideRange.getYear(),
                        dateThatFitsInsideRange.getMonth(), 1);
            } else {
                focusedDate = displayedMonth = null;
            }
        }else{
               
            focusedDate = new CalendarDate(currentDate.getYear(), currentDate.getMonth(),
                    currentDate.getDate());
            values.add(focusedDate.toString());
            displayedMonth = new CalendarDate(currentDate.getYear(), currentDate.getMonth(),
                    1);
        }
        focusDay(focusedDate,null);
        selectFocused();

        if (!hasFocus) {
            focusDay(null,null);
        }
         
    }

    /**
     * Adds the data of the Panel.
     *
     * @param currentDate The date to set
     */
    public void addDate(CalendarDate currentDate) {

        // Check that we are not re-rendering an already active date
        if (currentDate != null && values.contains(currentDate.toString())) {
            return;
        }
        values.add(currentDate.toString());
    }

    /**
     * A widget representing a single day in the calendar panel.
     */
    private class Day extends InlineHTML {
        
        private final Date date;
        private boolean enabled=true;
        Day(Date date) {
            super("" + date.getDate());
            this.date = date;
            addClickHandler(dayClickHandler);
        }

        public Date getDate() {
            return date;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
        
    }    
    

    /**
     * If true should be returned if the panel will not be used after this
     * event.
     *
     * @param event
     * @return
     */
    protected boolean onTabOut(DomEvent<?> event) {
        if (focusOutListener != null) {
            return focusOutListener.onFocusOut(event);
        }
        return false;
    }

    /**
     * A focus out listener is triggered when the panel loosed focus. This can
     * happen either after a user clicks outside the panel or tabs out.
     *
     * @param listener The listener to trigger
     */
    public void setFocusOutListener(FocusOutListener listener) {
        focusOutListener = listener;
    }

    /**
     * The submit listener is called when the user selects a value from the
     * calender either by clicking the day or selects it by keyboard.
     *
     * @param submitListener The listener to trigger
     */
    public void setSubmitListener(SubmitListener submitListener) {
        this.submitListener = submitListener;
    }


    /**
     * Returns the submit listener that listens to selection made from the panel
     *
     * @return The listener or NULL if no listener has been set
     */
    public SubmitListener getSubmitListener() {
        return submitListener;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.BlurHandler#onBlur(com.google.gwt.event
     * .dom.client.BlurEvent)
     */
    @Override
    public void onBlur(final BlurEvent event) {
        if (event.getSource() instanceof VCalendarFieldrPanel) {
            hasFocus = false;
            focusDay(null,null);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.FocusHandler#onFocus(com.google.gwt.event
     * .dom.client.FocusEvent)
     */
    @Override
    public void onFocus(FocusEvent event) {
        if (event.getSource() instanceof VCalendarFieldrPanel) {
            hasFocus = true;

            // Focuses the current day if the calendar shows the days
            if (focusedDay != null) {
                focusDay(focusedDate,null);
            }
        }
    }
    private static final String SUBPART_NEXT_MONTH = "nextmon";
    private static final String SUBPART_PREV_MONTH = "prevmon";
    private static final String SUBPART_NEXT_YEAR = "nexty";
    private static final String SUBPART_PREV_YEAR = "prevy";
    private static final String SUBPART_DAY = "day";
    private static final String SUBPART_MONTH_YEAR_HEADER = "header";
    private Date rangeStart;
    private Date rangeEnd;

    @Override
    public String getSubPartName(com.google.gwt.user.client.Element subElement) {
        if (contains(nextMonth, subElement)) {
            return SUBPART_NEXT_MONTH;
        } else if (contains(prevMonth, subElement)) {
            return SUBPART_PREV_MONTH;
        } else if (contains(nextYear, subElement)) {
            return SUBPART_NEXT_YEAR;
        } else if (contains(prevYear, subElement)) {
            return SUBPART_PREV_YEAR;
        } else if (contains(bodyDays, subElement)) {
            // Day, find out which dayOfMonth and use that as the identifier
            Day day = findWidget(subElement, Day.class);
            if (day != null) {
                Date date = day.getDate();
                int id = date.getDate();
                // Zero or negative ids map to days of the preceding month,
                // past-the-end-of-month ids to days of the following month
                if (date.getMonth() < displayedMonth.getMonth()) {
                    id -= DateTimeService.getNumberOfDaysInMonth(date);
                } else if (date.getMonth() > displayedMonth.getMonth()) {
                    id += DateTimeService
                            .getNumberOfDaysInMonth(displayedMonth);
                }
                return SUBPART_DAY + id;
            }
        } else if (getCellFormatter().getElement(0, 2).isOrHasChild(subElement)) {
            return SUBPART_MONTH_YEAR_HEADER;
        }

        return null;
    }

    /**
     * Checks if subElement is inside the widget DOM hierarchy.
     *
     * @param w
     * @param subElement
     * @return true if {@code w} is a parent of subElement, false otherwise.
     */
    private boolean contains(Widget w, Element subElement) {
        if (w == null || w.getElement() == null) {
            return false;
        }

        return w.getElement().isOrHasChild(subElement);
    }

    @Override
    public com.google.gwt.user.client.Element getSubPartElement(String subPart) {
        if (SUBPART_NEXT_MONTH.equals(subPart)) {
            return nextMonth.getElement();
        }
        if (SUBPART_PREV_MONTH.equals(subPart)) {
            return prevMonth.getElement();
        }
        if (SUBPART_NEXT_YEAR.equals(subPart)) {
            return nextYear.getElement();
        }
        if (SUBPART_PREV_YEAR.equals(subPart)) {
            return prevYear.getElement();
        }

        if (subPart.startsWith(SUBPART_DAY)) {
            // Zero or negative ids map to days in the preceding month,
            // past-the-end-of-month ids to days in the following month
            int dayOfMonth = Integer.parseInt(subPart.substring(SUBPART_DAY
                    .length()));
            Date date = new Date(displayedMonth.getYear(),
                    displayedMonth.getMonth(), dayOfMonth);
            Iterator<Widget> iter = bodyDays.iterator();
            while (iter.hasNext()) {
                Widget w = iter.next();
                if (isIsYear()) {
                    Iterator<Widget> iter1 = ((FlexTable) w).iterator();
                    while (iter1.hasNext()) {
                        Widget w1 = iter1.next();
                        if (w1 instanceof Day) {
                            Day day = (Day) w1;
                            if (day.getDate().equals(date)) {
                                return day.getElement();
                            }
                        }
                    }
                } else {
                    if (w instanceof Day) {
                        Day day = (Day) w;
                        if (day.getDate().equals(date)) {
                            return day.getElement();
                        }
                    }
                }
            }
        }

        if (SUBPART_MONTH_YEAR_HEADER.equals(subPart)) {
            return DOM.asOld((Element) getCellFormatter().getElement(0, 2)
                    .getChild(0));
        }
        return null;
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        if (mouseTimer != null) {
            mouseTimer.cancel();
        }
    }

    /**
     * Sets the start range for this component. The start range is inclusive,
     * and it depends on the current resolution, what is considered inside the
     * range.
     *
     * @param startDate - the allowed range's start date
     */
    public void setRangeStart(Date newRangeStart) {
        if (!SharedUtil.equals(rangeStart, newRangeStart)) {
            rangeStart = newRangeStart;
            if (initialRenderDone) {
                // Dynamic updates to the range needs to render the calendar to
                // update the element stylenames
                renderCalendar();
            }
        }

    }

    /**
     * Sets the end range for this component. The end range is inclusive, and it
     * depends on the current resolution, what is considered inside the range.
     *
     * @param endDate - the allowed range's end date
     */
    public void setRangeEnd(Date newRangeEnd) {
        if (!SharedUtil.equals(rangeEnd, newRangeEnd)) {
            rangeEnd = newRangeEnd;
            if (initialRenderDone) {
                // Dynamic updates to the range needs to render the calendar to
                // update the element stylenames
                renderCalendar();
            }
        }
    }

    /**
     * Helper method to find first instance of given Widget type found by
     * traversing DOM upwards from given element.
     * <p>
     * <strong>Note:</strong> If {@code element} is inside some widget {@code W}
     * , <em>and</em> {@code W} in turn is wrapped in a {@link Composite}
     * {@code C}, this method will not find {@code W}. It returns either
     * {@code C} or null, depending on whether the class parameter matches. This
     * may also be the case with other Composite-like classes that hijack the
     * event handling of their child widget(s).
     *
     * @param element the element where to start seeking of Widget
     * @param class1 the Widget type to seek for
     */
    @SuppressWarnings("unchecked")
    public static <T> T findWidget(Element element,
            Class<? extends Widget> class1) {
        if (element != null) {
            /* First seek for the first EventListener (~Widget) from dom */
            EventListener eventListener = null;
            while (eventListener == null && element != null) {
                eventListener = Event.getEventListener(element);
                if (eventListener == null) {
                    element = element.getParentElement();
                }
            }
            if (eventListener instanceof Widget) {
                /*
                 * Then find the first widget of type class1 from widget
                 * hierarchy
                 */
                Widget w = (Widget) eventListener;
                while (w != null) {
                    if (class1 == null || w.getClass() == class1) {
                        return (T) w;
                    }
                    w = w.getParent();
                }
            }
        }
        return null;
    }

    public boolean isNeedHeader() {
        return needHeader;
    }

    public void setNeedHeader(boolean needHeader) {
        this.needHeader = needHeader;
    }

    public boolean isPrevYearEnabled() {
        return prevYearEnabled;
    }

    public void setPrevYearEnabled(boolean prevYearEnabled) {
        this.prevYearEnabled = prevYearEnabled;
    }

    public boolean isNextYearEnabled() {
        return nextYearEnabled;
    }

    public void setNextYearEnabled(boolean nextYearEnabled) {
        this.nextYearEnabled = nextYearEnabled;
    }

    public boolean isPrevMonthEnabled() {
        return prevMonthEnabled;
    }

    public void setPrevMonthEnabled(boolean prevMonthEnabled) {
        this.prevMonthEnabled = prevMonthEnabled;
    }

    public boolean isNextMonthEnabled() {
        return nextMonthEnabled;
    }

    public void setNextMonthEnabled(boolean nextMonthEnabled) {
        this.nextMonthEnabled = nextMonthEnabled;
    }

    public boolean isYearVisible() {
        return yearVisible;
    }

    public void setYearVisible(boolean yearVisible) {
        this.yearVisible = yearVisible;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getTotalCol() {
        return totalCol;
    }

    public void setTotalCol(int totalCol) {
        this.totalCol = totalCol;
    }

    public boolean isIsYear() {
        return isYear;
    }

    public void setIsYear(boolean isYear) {
        this.isYear = isYear;
    }

    public CalendarDate getFocusedDate() {
        return focusedDate;
    }

    public void setFocusedDate(CalendarDate focusedDate) {
        this.focusedDate = focusedDate;
    }

    public void setDisabledWeekDays(List<String> disableWeekDays) {
        this.disabledWeekDays = disableWeekDays;
    }

    public void setDisabledMonthlyDays(List<String> disableMonthlyDays) {
        this.disabledMonthlyDays = disableMonthlyDays;
    }

    public void setDisabledDates(List<String> disableDates) {
        this.disabledDates = disableDates;
    }
    public void addDisabledDate(String disableDate) {
        if(disableDate!=null){
            this.disabledDates.add(disableDate);
        }        
    }

    public List<String> getDisabledDates() {
        return disabledDates;
    }
    
}
