/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.utils;

import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.common.dto.SessionDTO;

/**
 * This class is going to act like pojo for internal purpose.
 *
 * @author srithar
 */
public class ReserveSelection {

    SessionDTO session;
    AdjustmentReserveDTO searchBinderDTO;
    AdjustmentReserveDTO windowBinderDTO;
    boolean isGTNDetails;
    int reserveMasterSid;
    int gtnDetailsMasterSid;
    int masterSID;
    String companyNo;
    String division;
    String busUnit;
    boolean isSaved;
    boolean isViewMode;
    boolean isCurrent;
    String tempTableName;
    String adjustmentSummaryTempTableName;
    String balanceSummaryTempTableName;
    int methodology;
    String methodologyDescription;
    String tabNameForExcel;
    int reportType;
    int tabIndex;
    boolean resetLine = false;

    public boolean isIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    public boolean isIsViewMode() {
        return isViewMode;
    }

    public void setIsViewMode(boolean isViewMode) {
        this.isViewMode = isViewMode;
    }

    public int getMasterSID() {
        return masterSID;
    }

    public void setMasterSID(int masterSID) {
        this.masterSID = masterSID;
    }

    public int getGtnDetailsMasterSid() {
        return gtnDetailsMasterSid;
    }

    public void setGtnDetailsMasterSid(int gtnDetailsMasterSid) {
        this.gtnDetailsMasterSid = gtnDetailsMasterSid;
    }

    public boolean isIsGTNDetails() {
        return isGTNDetails;
    }

    public void setIsGTNDetails(boolean isGTNDetails) {
        this.isGTNDetails = isGTNDetails;
    }

    public SessionDTO getSession() {
        return session;
    }

    public void setSession(SessionDTO session) {
        this.session = session;
    }

    public AdjustmentReserveDTO getSearchBinderDTO() {
        return searchBinderDTO;
    }

    public void setSearchBinderDTO(AdjustmentReserveDTO searchBinderDTO) {
        this.searchBinderDTO = searchBinderDTO;
    }

    public AdjustmentReserveDTO getWindowBinderDTO() {
        return windowBinderDTO;
    }

    public void setWindowBinderDTO(AdjustmentReserveDTO windowBinderDTO) {
        this.windowBinderDTO = windowBinderDTO;
    }

    public int getReserveMasterSid() {
        return reserveMasterSid;
    }

    public void setReserveMasterSid(int reserveMasterSid) {
        this.reserveMasterSid = reserveMasterSid;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getBusUnit() {
        return busUnit;
    }

    public void setBusUnit(String busUnit) {
        this.busUnit = busUnit;
    }

    public boolean isIsSaved() {
        return isSaved;
    }

    public void setIsSaved(boolean isSaved) {
        this.isSaved = isSaved;
    }

    public String getTempTableName() {
        return tempTableName;
    }

    public void setTempTableName(String tempTableName) {
        this.tempTableName = tempTableName;
    }

    public String getAdjustmentSummaryTempTableName() {
        return adjustmentSummaryTempTableName;
    }

    public void setAdjustmentSummaryTempTableName(String adjustmentSummaryTempTableName) {
        this.adjustmentSummaryTempTableName = adjustmentSummaryTempTableName;
    }

    public int getMethodology() {
        return methodology;
    }

    public void setMethodology(int methodology) {
        this.methodology = methodology;
    }

    public String getMethodologyDescription() {
        return methodologyDescription;
    }

    public void setMethodologyDescription(String methodologyDescription) {
        this.methodologyDescription = methodologyDescription;
    }

    public String getBalanceSummaryTempTableName() {
        return balanceSummaryTempTableName;
    }

    public void setBalanceSummaryTempTableName(String balanceSummaryTempTableName) {
        this.balanceSummaryTempTableName = balanceSummaryTempTableName;
    }

    public String getTabNameForExcel() {
        return tabNameForExcel;
    }

    public void setTabNameForExcel(String tabNameForExcel) {
        this.tabNameForExcel = tabNameForExcel;
    }

    public int getReportType() {
        return reportType;
    }

    public void setReportType(int reportType) {
        this.reportType = reportType;
    }

    public int getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }

    public boolean isResetLine() {
        return resetLine;
    }

    public void setResetLine(boolean resetLine) {
        this.resetLine = resetLine;
    }

    public ReserveSelection clone(ReserveSelection selection) { // Added for GAL-7973
        selection.setSearchBinderDTO(searchBinderDTO);
        selection.setBusUnit(busUnit);
        selection.setCompanyNo(companyNo);
        selection.setDivision(division);
        selection.setGtnDetailsMasterSid(gtnDetailsMasterSid);
        selection.setIsCurrent(isCurrent);
        selection.setIsGTNDetails(isGTNDetails);
        selection.setIsSaved(isSaved);
        selection.setIsViewMode(isViewMode);
        selection.setMasterSID(masterSID);
        selection.setReserveMasterSid(reserveMasterSid);
        selection.setSession(session);
        selection.setWindowBinderDTO(windowBinderDTO);
        selection.setTempTableName(tempTableName);
        selection.setAdjustmentSummaryTempTableName(adjustmentSummaryTempTableName);
        selection.setMethodology(methodology);
        selection.setMethodologyDescription(methodologyDescription);
        selection.setBalanceSummaryTempTableName(balanceSummaryTempTableName);
        return selection;
    }
}
