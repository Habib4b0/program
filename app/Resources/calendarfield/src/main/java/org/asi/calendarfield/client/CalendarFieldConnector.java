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

import com.vaadin.client.ApplicationConnection;
import com.vaadin.client.LocaleNotLoadedException;
import com.vaadin.client.Paintable;
import com.vaadin.client.UIDL;
import com.vaadin.client.VConsole;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractFieldConnector;
import static com.vaadin.client.ui.AbstractComponentConnector.isRealUpdate;
import com.vaadin.shared.ui.Connect;
import com.vaadin.shared.ui.datefield.DateFieldConstants;
import com.vaadin.shared.ui.datefield.DateTimeResolution;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import org.asi.calendarfield.CalendarField;

/**
 *
 * @author Abhiram
 */
@Connect(CalendarField.class)
public class CalendarFieldConnector extends AbstractFieldConnector implements Paintable{
   
   @Override
    @SuppressWarnings("deprecation")
    public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
        if (!isRealUpdate(uidl)) {
            return;
        }

        // Save details
        getWidget().client = client;
        getWidget().paintableId = uidl.getId();
        //getWidget().immediate = getState().immediate;
        getWidget().headerCalendarPanel.setIsYear(uidl.getBooleanAttribute("isYear"));
        getWidget().headerCalendarPanel.setTotalCol(uidl.getIntAttribute("totalCol"));
        getWidget().headerCalendarPanel.setTotalRow(uidl.getIntAttribute("totalRow"));
        getWidget().setReadonly(isReadOnly());
        getWidget().setEnabled(isEnabled());

        if (uidl.hasAttribute("locale")) {
            final String locale = uidl.getStringAttribute("locale");
            try {
                getWidget().dts.setLocale(locale);
                getWidget().setCurrentLocale(locale);
            } catch (final LocaleNotLoadedException e) {
                getWidget().setCurrentLocale(getWidget().dts.getLocale());
                VConsole.error("Tried to use an unloaded locale \"" + locale
                        + "\". Using default locale ("
                        + getWidget().getCurrentLocale() + ").");
                VConsole.error(e);
            }
        }

        // We show week numbers only if the week starts with Monday, as ISO 8601
         //specifies
        getWidget().setShowISOWeekNumbers(
                uidl.getBooleanAttribute(DateFieldConstants.ATTR_WEEK_NUMBERS)
                        && getWidget().dts.getFirstDayOfWeek() == 1);

        

        getWidget().setCurrentResolution(DateTimeResolution.DAY);

        // Add stylename that indicates current resolution
        setWidgetStyleName(
                getWidget().getStylePrimaryName()
                        + "-"
                        + VCalendarField.resolutionToString(getWidget()
                                .getCurrentResolution()), true);
        Date dt=getWidget().headerCalendarPanel.getFocusedDate();
        if(dt==null){
            dt=new Date();
        }
        int focusYear=dt.getYear();
        int focusMonth=dt.getMonth();
        int focusDate=dt.getDate();
        if(uidl.hasAttribute("focusYear")){
            focusYear=Integer.parseInt(uidl.getStringAttribute("focusYear"));
        }
        if(uidl.hasAttribute("focusMonth")){
            focusMonth=Integer.parseInt(uidl.getStringAttribute("focusMonth"));
        }
        if(uidl.hasAttribute("focusDate")){
            focusDate=Integer.parseInt(uidl.getStringAttribute("focusDate"));
        }
        getWidget().headerCalendarPanel.manageFocusRange(new CalenderFieldUtil.CalendarDate(focusYear, focusMonth, focusDate));
        if(uidl.hasAttribute("disabledWeekDays")){
            getWidget().headerCalendarPanel.setDisabledWeekDays(Arrays.asList(uidl.getStringArrayAttribute("disabledWeekDays")));
        }
        if(uidl.hasAttribute("disabledMonthlyDays")){
            getWidget().headerCalendarPanel.setDisabledMonthlyDays(Arrays.asList(uidl.getStringArrayAttribute("disabledMonthlyDays")));
        }
        
        getWidget().headerCalendarPanel.getDisabledDates().clear();
        UIDL disUidl = uidl.getChildByTagName("disabledDates");
        
        if (disUidl != null) {
            Iterator<?> it = disUidl.getChildIterator();
            while (it.hasNext()) {
                final UIDL col = (UIDL) it.next();
                
                int year=col.getIntAttribute("year");
                int month=col.getIntAttribute("month");
                int date=col.getIntAttribute("date");
                getWidget().headerCalendarPanel.addDisabledDate(
                    new CalenderFieldUtil.CalendarDate(year, month, date).toString());
            }
        }
        getWidget().headerCalendarPanel.getValues().clear();
        UIDL vc = uidl.getChildByTagName("paintvalues");
        
        if (vc != null) {
            Iterator<?> it = vc.getChildIterator();
            while (it.hasNext()) {
                final UIDL col = (UIDL) it.next();
                
                int year=col.getIntAttribute("year");
                int month=col.getIntAttribute("month");
                int date=col.getIntAttribute("date");
                getWidget().headerCalendarPanel.addDate(
                    new CalenderFieldUtil.CalendarDate(year, month, date));
            }
        }
        
        
           getWidget().headerCalendarPanel.setShowISOWeekNumbers(getWidget()
                .isShowISOWeekNumbers());
        getWidget().headerCalendarPanel.setDateTimeService(getWidget()
                .getDateTimeService());
        getWidget().headerCalendarPanel.setResolution(getWidget()
                .getCurrentResolution());

        // Update possible changes
        getWidget().headerCalendarPanel.renderCalendar();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
        getWidget().setTabIndex(getState().tabIndex);
        getWidget().headerCalendarPanel.setRangeStart(getState().rangeStart);
        getWidget().headerCalendarPanel.setRangeEnd(getState().rangeEnd);        
    }

    @Override
    public VCalendarField getWidget() {
        return (VCalendarField) super.getWidget();
    }

    @Override
    public CalendarFieldState getState() {
        return (CalendarFieldState) super.getState();
    }
}
