/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.company.dto;

import com.stpl.app.util.HelperDTO;
import com.vaadin.data.Container;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author sathyaseelan.v
 */
public class FinancialCloseDTO implements Comparator<FinancialCloseDTO> {

    private String mode = StringUtils.EMPTY,
            calendar = StringUtils.EMPTY,
            status = StringUtils.EMPTY,
            createdBy = StringUtils.EMPTY,
            yearDdlb = StringUtils.EMPTY,
            businessDayDdlb = StringUtils.EMPTY,
            hourDdlb = StringUtils.EMPTY,
            minuteValue = StringUtils.EMPTY;
    boolean automatic = Boolean.FALSE;
    private Date createdDate;
    private Date dateTime;
    private HelperDTO modeDdlb, monthDdlb, minuteDdlb, statusDdlb, createdByDdlb;
    private Integer calenderDdlb;
    Set<Container.Filter> filters;
    List<SortByColumn> sortedColumns;
    private int start, end;
    private boolean count;
    private boolean generate = false;
    private boolean reset = false;
    private boolean firstTimeEdit = false;

    private String year;
    private String month;
    private int periodSid;
    private String openClose;
    private String companyMode;
    private int companyMasterSid;
    private Map<Integer, String> monthMap = new HashMap<>();
    private List<FinancialCloseDTO> addMode_Container_MainList;
    private List<FinancialCloseDTO> initialRecordList = null;
    private int counter;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public HelperDTO getModeDdlb() {
        return modeDdlb;
    }

    public void setModeDdlb(HelperDTO modeDdlb) {
        this.modeDdlb = modeDdlb;
    }

    public HelperDTO getMonthDdlb() {
        return monthDdlb;
    }

    public void setMonthDdlb(HelperDTO monthDdlb) {
        this.monthDdlb = monthDdlb;
    }

    public String getBusinessDayDdlb() {
        return businessDayDdlb;
    }

    public void setBusinessDayDdlb(String businessDayDdlb) {
        this.businessDayDdlb = businessDayDdlb;
    }

    public String getHourDdlb() {
        return hourDdlb;
    }

    public void setHourDdlb(String hourDdlb) {
        this.hourDdlb = hourDdlb;
    }

    public HelperDTO getMinuteDdlb() {
        return minuteDdlb;
    }

    public void setMinuteDdlb(HelperDTO minuteDdlb) {
        this.minuteDdlb = minuteDdlb;
    }

    public Integer getCalenderDdlb() {
        return calenderDdlb;
    }

    public void setCalenderDdlb(Integer calenderDdlb) {
        this.calenderDdlb = calenderDdlb;
    }

    public String getYearDdlb() {
        return yearDdlb;
    }

    public void setYearDdlb(String yearDdlb) {
        this.yearDdlb = yearDdlb;
    }

    public Set<Container.Filter> getFilters() {
        return filters;
    }

    public void setFilters(Set<Container.Filter> filters) {
        this.filters = filters;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public boolean isCount() {
        return count;
    }

    public void setCount(boolean count) {
        this.count = count;
    }

    public List<SortByColumn> getSortedColumns() {
        return sortedColumns;
    }

    public void setSortedColumns(List<SortByColumn> sortedColumns) {
        this.sortedColumns = sortedColumns;
    }

    public boolean isGenerate() {
        return generate;
    }

    public void setGenerate(boolean generate) {
        this.generate = generate;
    }
    
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getPeriodSid() {
        return periodSid;
    }

    public void setPeriodSid(int periodSid) {
        this.periodSid = periodSid;
    }

    public HelperDTO getStatusDdlb() {
        return statusDdlb;
    }

    public void setStatusDdlb(HelperDTO statusDdlb) {
        this.statusDdlb = statusDdlb;
    }

    public String getOpenClose() {
        return openClose;
    }

    public void setOpenClose(String openClose) {
        this.openClose = openClose;
    }

    public String getCompanyMode() {
        return companyMode;
    }

    public void setCompanyMode(String companyMode) {
        this.companyMode = companyMode;
    }

    public List<FinancialCloseDTO> getAddMode_Container_MainList() {
        return addMode_Container_MainList;
    }

    public void setAddMode_Container_MainList(List<FinancialCloseDTO> addMode_Container_MainList) {
        this.addMode_Container_MainList = addMode_Container_MainList;
    }

    public HelperDTO getCreatedByDdlb() {
        return createdByDdlb;
    }

    public void setCreatedByDdlb(HelperDTO createdByDdlb) {
        this.createdByDdlb = createdByDdlb;
    }

    public int getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(int companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    public Map<Integer, String> getMonthMap() {
        return monthMap;
    }

    public void setMonthMap(Map<Integer, String> monthMap) {
        this.monthMap = monthMap;
    }

    public String getMinuteValue() {
        return minuteValue;
    }

    public void setMinuteValue(String minuteValue) {
        this.minuteValue = minuteValue;
    }

    @Override
    public int compare(FinancialCloseDTO o1, FinancialCloseDTO o2) {
        return o1.getDateTime().compareTo(o2.getDateTime());
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public boolean isAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public boolean isFirstTimeEdit() {
        return firstTimeEdit;
    }

    public void setFirstTimeEdit(boolean firstTimeEdit) {
        this.firstTimeEdit = firstTimeEdit;
    }

    public List<FinancialCloseDTO> getInitialRecordList() {
        return initialRecordList;
    }

    public void setInitialRecordList(List<FinancialCloseDTO> initialRecordList) {
        this.initialRecordList = initialRecordList;
    }
    
    }
