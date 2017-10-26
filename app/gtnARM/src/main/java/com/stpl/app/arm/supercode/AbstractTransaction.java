/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.supercode;

import com.stpl.app.arm.AbstractForms.NotesTabForm;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.dataselection.ui.form.DataSelectionTab;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;

/**
 *
 * @author Abhiram.Giri
 */
public abstract class AbstractTransaction extends AbstractModule implements SubmitMessageAble, HasSelection, LeaveConfirmMessageAble, LeaveCheckAble, HasSave, GTNOrReserve {

    private DataSelectionTab dataSelectionWF;
    private NotesTabForm notes;
    public StplSecurity stplSecurity = new StplSecurity();
    /**
     * Custom Field group binder
     */
    private CustomFieldGroup binder;
    int tabPosition = 0;

    protected AbstractTransaction(TabSheet tabSheet, CustomFieldGroup binder, int id, String name, DataSelectionDTO dataselectionDTO, SessionDTO sessionDTO) throws SystemException  {
        super(tabSheet, id, name, dataselectionDTO, sessionDTO);
        this.binder = binder;
        dataSelectionWF = new DataSelectionTab(dataselectionDTO, sessionDTO);
        notes = new NotesTabForm(getBinder(), "businessProcess", getSessionDTO(), getName());
        createDynamicTempTables();
    }

    public DataSelectionTab getDataSelectionWF() {
        return dataSelectionWF;
    }

    public CustomFieldGroup getBinder() {
        return binder;
    }

    public abstract int getTransactionNo();

    /**
     * Method to create a dynamic temp tables
     *
     */
    protected void createDynamicTempTables() {
        sessionDTO.setScreenName(getTransactionName());
        if (!sessionDTO.getTransactionTables().containsKey(getTransactionName())) {
            sessionDTO.setCurrentTableNames(QueryUtils.createTempTables(sessionDTO.getScreenName(), sessionDTO.getProjectionId(), sessionDTO.getUserId().toString(), sessionDTO.getSessionId().toString()));
        }
    }

    public abstract String getTransactionName();

    public NotesTabForm getNotes() {
        return notes;
    }

    @Override
    public boolean checkLeave() {
        Component selectedTab = getTabSheet().getTab(getTabPosition()).getComponent();
        boolean changeTab = true;
        if (selectedTab instanceof LeaveCheckAble) {
            changeTab = ((LeaveCheckAble) selectedTab).checkLeave();
        }
        return changeTab;
    }

    @Override
    public String leaveConfirmationHeader() {
        Component selectedTab = getTabSheet().getTab(getTabPosition()).getComponent();
        String message = "";
        if (selectedTab instanceof LeaveConfirmMessageAble) {
            message = ((LeaveConfirmMessageAble) selectedTab).leaveConfirmationHeader();
        }
        return message;
    }

    @Override
    public String leaveConfirmationMessage() {
        Component selectedTab = getTabSheet().getTab(getTabPosition()).getComponent();
        String message = "";
        if (selectedTab instanceof LeaveConfirmMessageAble) {
            message = ((LeaveConfirmMessageAble) selectedTab).leaveConfirmationMessage();
        }
        return message;
    }

    @Override
    public boolean isRestrict() {
        Component selectedTab = getTabSheet().getTab(getTabPosition()).getComponent();
        boolean changeTab = false;
        if (selectedTab instanceof LeaveCheckAble) {
            changeTab = ((LeaveCheckAble) selectedTab).isRestrict();
        }
        return changeTab;
    }

    @Override
    public String leaveRestrictionHeader() {
        Component selectedTab = getTabSheet().getTab(getTabPosition()).getComponent();
        String message = "";
        if (selectedTab instanceof LeaveConfirmMessageAble) {
            message = ((LeaveConfirmMessageAble) selectedTab).leaveRestrictionHeader();
        }
        return message;
    }

    @Override
    public String leaveRestrictionMessage() {
        Component selectedTab = getTabSheet().getTab(getTabPosition()).getComponent();
        String message = "";
        if (selectedTab instanceof LeaveConfirmMessageAble) {
            message = ((LeaveConfirmMessageAble) selectedTab).leaveRestrictionMessage();
        }
        return message;
    }

    public int getTabPosition() {
        return tabPosition;
    }

    public void setTabPosition(int tabPosition) {
        this.tabPosition = tabPosition;
    }
    public abstract void configurePermission() throws Exception;

}
