/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.accountconfiguration.dto;

import com.stpl.app.arm.common.dto.SessionDTO;

/**
 *
 * @author Srithar.Raju
 */
public class AccountConfigSelection implements Cloneable {

    SessionDTO session;
    String tempTableName;
    boolean saved;
    boolean currentView = true;
    boolean viewMode;
    AccountConfigDTO searchAccConfigDTO;

    public SessionDTO getSession() {
        return session;
    }

    public void setSession(SessionDTO session) {
        this.session = session;
    }

    public String getTempTableName() {
        return tempTableName;
    }

    public void setTempTableName(String tempTableName) {
        this.tempTableName = tempTableName;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public boolean isCurrentView() {
        return currentView;
    }

    public void setCurrentView(boolean currentView) {
        this.currentView = currentView;
    }

    public boolean isViewMode() {
        return viewMode;
    }

    public void setViewMode(boolean viewMode) {
        this.viewMode = viewMode;
    }

    public AccountConfigDTO getSearchAccConfigDTO() {
        return searchAccConfigDTO;
    }

    public void setSearchAccConfigDTO(AccountConfigDTO searchAccConfigDTO) {
        this.searchAccConfigDTO = searchAccConfigDTO;
    }

    protected AccountConfigDTO clone() throws CloneNotSupportedException {
        return (AccountConfigDTO) super.clone();
    }

}
