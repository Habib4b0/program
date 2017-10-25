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
public class ReserveSelection implements Cloneable {

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

    @Override
    public ReserveSelection clone() throws CloneNotSupportedException { // Added for GAL-7973
        ReserveSelection selection = (ReserveSelection) super.clone();
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
        return selection;
    }
}
