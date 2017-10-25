/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.calendar.createcalendar.logic.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Abishek.Ram
 */
public class CalendarDetailsDTO {
    
    final DateFormat format = new SimpleDateFormat("Mm/dd/yyyy");
    private Long id = new Long(0L);
    private String calendarName = StringUtils.EMPTY;
    private String calendarDescription = StringUtils.EMPTY;
    private Integer calendarYear = new Integer(0);
    private String createdBy = StringUtils.EMPTY;
    private String country = StringUtils.EMPTY;
    private String modifiedBy = StringUtils.EMPTY;
    private Date createdDate;
    private Date modifiedDate;
    private Map holidayDays;
     private boolean defaultHolidays = false;
    
    public Long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getCalendarName() {
        return calendarName;
    }
    
    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }
    
    public String getCalendarDescription() {
        return calendarDescription;
    }
    
    public void setCalendarDescription(String calendarDescription) {
        this.calendarDescription = calendarDescription;
    }
    
    public Integer getCalendarYear() {
        return calendarYear;
    }
    
    public void setCalendarYear(int calendarYear) {
        this.calendarYear = calendarYear;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public String getModifiedBy() {
        return modifiedBy;
    }
    
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    
    public Date getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(long createdDate) {
        if (createdDate == 0l) {
            this.createdDate = null;
        } else {
            this.createdDate = new Date(createdDate) {
                @Override
                public String toString() {
                    return format.format(this);
                }
            };
        }
    }
    
    public Date getModifiedDate() {
        return modifiedDate;
    }
    
    public void setModifiedDate(long modifiedDate) {
        if (modifiedDate == 0l) {
            this.modifiedDate = null;
        } else {
            this.modifiedDate = new Date(modifiedDate) {
                @Override
                public String toString() {
                    return format.format(this); //To change body of generated methods, choose Tools | Templates.
                }
            };
        }
    }
    
    public Map getHolidayDays() {
        return holidayDays;
    }
    
    public void setHolidayDays(Map holidayDays) {
        this.holidayDays = holidayDays;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
      public boolean isDefaultHolidays() {
        return defaultHolidays;
    }

    public void setDefaultHolidays(boolean defaultHolidays) {
        this.defaultHolidays = defaultHolidays;
    }
}
