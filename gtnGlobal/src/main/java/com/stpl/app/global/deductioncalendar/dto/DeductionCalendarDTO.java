/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.deductioncalendar.dto;

import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author sibi
 */
public class DeductionCalendarDTO implements Serializable {

    private String deductionCalendarNo = StringUtils.EMPTY;
    private String deductionCalendarName = StringUtils.EMPTY;
    private String deductionCalendarDesc = StringUtils.EMPTY;
    private HelperDTO categoryDdlb = new HelperDTO(ConstantsUtils.SELECT_ONE);
    private String type = StringUtils.EMPTY;
    private String itemNo = StringUtils.EMPTY;
    private String itemDesc = StringUtils.EMPTY;
    private HelperDTO therapeuticClass = new HelperDTO(ConstantsUtils.SELECT_ONE);
    private String brand = StringUtils.EMPTY;
    private String form = StringUtils.EMPTY;
    private String strength = StringUtils.EMPTY;
    private String dataView = StringUtils.EMPTY;
    private String listView = StringUtils.EMPTY;
    private String dataRange = StringUtils.EMPTY;
    private HelperDTO from = new HelperDTO(ConstantsUtils.SELECT_ONE);
    private HelperDTO to = new HelperDTO(ConstantsUtils.SELECT_ONE);
    private String basis = StringUtils.EMPTY;
    private String variable = StringUtils.EMPTY;
    private String adjustmentPrograms = StringUtils.EMPTY;
    private String adjustmentPeriods = StringUtils.EMPTY;
    private HelperDTO frequency = new HelperDTO(ConstantsUtils.SELECT_ONE);
    private HelperDTO filter = new HelperDTO(ConstantsUtils.SELECT_ONE);
    private String adjustment = StringUtils.EMPTY;
    private HelperDTO allocationMethododlogy = new HelperDTO(ConstantsUtils.SELECT_ONE);
    private HelperDTO field = new HelperDTO(ConstantsUtils.SELECT_ONE);
    private String value = StringUtils.EMPTY;
    private HelperDTO startPeriod = new HelperDTO(ConstantsUtils.SELECT_ONE);
    private HelperDTO endPeriod = new HelperDTO(ConstantsUtils.SELECT_ONE);
    private String deductionCalendarMasterSid = StringUtils.EMPTY;
    private int masterTableSid;
    private String internalNotes= StringUtils.EMPTY;

    public String getDeductionCalendarMasterSid() {
        return deductionCalendarMasterSid;
    }

    public void setDeductionCalendarMasterSid(String deductionCalendarMasterSid) {
        this.deductionCalendarMasterSid = deductionCalendarMasterSid;
    }
    

    public String getDeductionCalendarNo() {
        return deductionCalendarNo;
    }

    public void setDeductionCalendarNo(String deductionCalendarNo) {
        this.deductionCalendarNo = deductionCalendarNo;
    }

    public String getDeductionCalendarName() {
        return deductionCalendarName;
    }

    public void setDeductionCalendarName(String deductionCalendarName) {
        this.deductionCalendarName = deductionCalendarName;
    }

    public String getDeductionCalendarDesc() {
        return deductionCalendarDesc;
    }

    public void setDeductionCalendarDesc(String deductionCalendarDesc) {
        this.deductionCalendarDesc = deductionCalendarDesc;
    }

    public HelperDTO getCategoryDdlb() {
        return categoryDdlb;
    }

    public void setCategoryDdlb(HelperDTO categoryDdlb) {
        this.categoryDdlb = categoryDdlb;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public HelperDTO getTherapeuticClass() {
        return therapeuticClass;
    }

    public void setTherapeuticClass(HelperDTO therapeuticClass) {
        this.therapeuticClass = therapeuticClass;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getDataView() {
        return dataView;
    }

    public void setDataView(String dataView) {
        this.dataView = dataView;
    }

    public String getListView() {
        return listView;
    }

    public void setListView(String listView) {
        this.listView = listView;
    }

    public String getDataRange() {
        return dataRange;
    }

    public void setDataRange(String dataRange) {
        this.dataRange = dataRange;
    }

    public HelperDTO getFrom() {
        return from;
    }

    public void setFrom(HelperDTO from) {
        this.from = from;
    }

    public HelperDTO getTo() {
        return to;
    }

    public void setTo(HelperDTO to) {
        this.to = to;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getAdjustmentPrograms() {
        return adjustmentPrograms;
    }

    public void setAdjustmentPrograms(String adjustmentPrograms) {
        this.adjustmentPrograms = adjustmentPrograms;
    }

    public String getAdjustmentPeriods() {
        return adjustmentPeriods;
    }

    public void setAdjustmentPeriods(String adjustmentPeriods) {
        this.adjustmentPeriods = adjustmentPeriods;
    }

    public HelperDTO getFrequency() {
        return frequency;
    }

    public void setFrequency(HelperDTO frequency) {
        this.frequency = frequency;
    }

    public HelperDTO getFilter() {
        return filter;
    }

    public void setFilter(HelperDTO filter) {
        this.filter = filter;
    }

    public String getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(String adjustment) {
        this.adjustment = adjustment;
    }

    public HelperDTO getAllocationMethododlogy() {
        return allocationMethododlogy;
    }

    public void setAllocationMethododlogy(HelperDTO allocationMethododlogy) {
        this.allocationMethododlogy = allocationMethododlogy;
    }

    public HelperDTO getField() {
        return field;
    }

    public void setField(HelperDTO field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public HelperDTO getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(HelperDTO startPeriod) {
        this.startPeriod = startPeriod;
    }

    public HelperDTO getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(HelperDTO endPeriod) {
        this.endPeriod = endPeriod;
    }

    public int getMasterTableSid() {
        return masterTableSid;
    }

    public void setMasterTableSid(int masterTableSid) {
        this.masterTableSid = masterTableSid;
    }

    public String getInternalNotes() {
        return internalNotes;
    }

    public void setInternalNotes(String internalNotes) {
        this.internalNotes = internalNotes;
    }


}
