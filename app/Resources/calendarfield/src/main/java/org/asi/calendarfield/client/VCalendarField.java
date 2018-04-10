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

import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasEnabled;
import com.vaadin.client.ApplicationConnection;
import com.vaadin.client.DateTimeService;
import com.vaadin.client.ui.Field;
import com.vaadin.shared.ui.datefield.DateTimeResolution;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author Abhiram
 */
public class VCalendarField extends FlowPanel implements Field, HasEnabled{ 
	
	//private static final Logger logger = org.apache.log4j.LogManager.getLogger(VCalendarField.class);
    public static final String CLASSNAME = "v-inline-datefield";
    public static final String NEW_CLASSNAME = "calendarfield";

    /** For internal use only. May be removed or replaced in the future. */
    public String paintableId;

    /** For internal use only. May be removed or replaced in the future. */
    public ApplicationConnection client;

    /** For internal use only. May be removed or replaced in the future. */
    public boolean immediate = true;

    
    public static final DateTimeResolution RESOLUTION_YEAR = DateTimeResolution.YEAR;
    
    public static final DateTimeResolution RESOLUTION_MONTH = DateTimeResolution.MONTH;
    
    public static final DateTimeResolution RESOLUTION_DAY = DateTimeResolution.DAY;
   
    public static final DateTimeResolution RESOLUTION_HOUR = DateTimeResolution.HOUR;
   
    public static final DateTimeResolution RESOLUTION_MIN = DateTimeResolution.MINUTE;
   
    public static final DateTimeResolution RESOLUTION_SEC = DateTimeResolution.SECOND;

    /** For internal use only. May be removed or replaced in the future. */
    public static String resolutionToString(DateTimeResolution res) {
//        if (res.getCalendarField() > Resolution.DAY.getCalendarField()) {
//            return "full";
//        }
        if (res == DateTimeResolution.DAY) {
            return "day";
        }
        if (res == DateTimeResolution.MONTH) {
            return "month";
        }
        if (res == DateTimeResolution.YEAR) {
            return "year";
        }
        return "full";
    }

    protected DateTimeResolution currentResolution = DateTimeResolution.YEAR;

    protected String currentLocale;

    protected boolean readonly;

    protected boolean enabled;

    /**
     * The date that is selected in the date field. Null if an invalid date is
     * specified.
     */
    private Date date = null;

    /** For internal use only. May be removed or replaced in the future. */
    public DateTimeService dateTimeService;

    protected boolean showISOWeekNumbers = false;
    
//    private FlexTable table=new FlexTable();
    
//    public final List<VExtCalendarPanel> calendarPanels=new ArrayList<VExtCalendarPanel>();
    
    
/** For internal use only. May be removed or replaced in the future. */
    public final VCalendarFieldrPanel headerCalendarPanel;
    public VCalendarField() {
        setStyleName(CLASSNAME);
        addStyleName(NEW_CLASSNAME);
        dateTimeService = new DateTimeService();
        headerCalendarPanel = new VCalendarFieldrPanel();
        headerCalendarPanel.setParentField(this);
        add(headerCalendarPanel);
        
        headerCalendarPanel.setSubmitListener(new VCalendarFieldrPanel.SubmitListener() {
            @Override
            public void onSubmit() {
                updateValueFromPanel();
            }

            @Override
            public void onCancel() {
                // TODO Auto-generated method stub

            }
        });
        headerCalendarPanel.setFocusOutListener(new VCalendarFieldrPanel.FocusOutListener() {
            @Override
            public boolean onFocusOut(DomEvent<?> event) {
//                updateValueFromPanel();
                return false;
            }
        });
    }
   
    
    public int getMilliseconds() {
        return DateTimeService.getMilliseconds(date);
    }

    public void setMilliseconds(int ms) {
        DateTimeService.setMilliseconds(date, ms);
    }

    public DateTimeResolution getCurrentResolution() {
        return currentResolution;
    }

    public void setCurrentResolution(DateTimeResolution currentResolution) {
        this.currentResolution = currentResolution;
    }

    public String getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(String currentLocale) {
        this.currentLocale = currentLocale;
    }


    public boolean isImmediate() {
        return immediate;
    }

    public void setImmediate(boolean immediate) {
        this.immediate = immediate;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    public String getId() {
        return paintableId;
    }

    public ApplicationConnection getClient() {
        return client;
    }

    /**
     * Returns whether ISO 8601 week numbers should be shown in the date
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
     * TODO refactor: almost same method as in VPopupCalendar.updateValue
     * <p>
     * For internal use only. May be removed or replaced in the future.
     */

    @SuppressWarnings("deprecation")
    public void updateValueFromPanel() {
        // If field is invisible at the beginning, client can still be null when
        // this function is called.
        if (getClient() == null) {
            return;
        }
        String[] newValues = new String[headerCalendarPanel.getValues().size()];
        int ix = 0;
        Iterator<String> it = headerCalendarPanel.getValues().iterator();
        while(it.hasNext()) {
        	String var = it.next();
//        	logger.info("varr"+var);
            newValues[ix++]=var;
        }
        getClient().updateVariable(getId(), "focusYear", ""+headerCalendarPanel.getFocusedDate().getYear(),
                    false);
        getClient().updateVariable(getId(), "focusMonth", ""+headerCalendarPanel.getFocusedDate().getMonth(),
                    false);
        getClient().updateVariable(getId(), "focusDate", ""+headerCalendarPanel.getFocusedDate().getDate(),
                    false);
        getClient().updateVariable(getId(), "values", newValues,
                    false);
        
            if (isImmediate()) {
                getClient().sendPendingVariableChanges();
            }
    }

    public void setTabIndex(int tabIndex) {
        headerCalendarPanel.getElement().setTabIndex(tabIndex);
    }

    public int getTabIndex() {
        return headerCalendarPanel.getElement().getTabIndex();
    }
}
