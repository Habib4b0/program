/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.supercode;

import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.arm.abstractforms.NotesTabForm;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractDemandSummarySelection;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractPipelineSummary;
import com.stpl.app.arm.businessprocess.pipelineaccrual.form.AdjustmentDetailAccural;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.dataselection.ui.form.DataSelectionTab;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;

/**
 *
 * @author Abhiram.Giri
 */
public abstract class AbstractTransaction extends AbstractModule implements SubmitMessageAble, HasSelection, LeaveConfirmMessageAble, LeaveCheckAble, HasSave, GTNOrReserve {

    private DataSelectionTab dataSelectionWF;
    private NotesTabForm notes;
    protected StplSecurity stplSecurity = new StplSecurity();
    /**
     * Custom Field group binder
     */
    private CustomFieldGroup binder;
    private int tabPosition = 0;

    protected AbstractTransaction(TabSheet tabSheet, CustomFieldGroup binder, int id, String name, DataSelectionDTO dataselectionDTO, SessionDTO sessionDTO) throws SystemException {
        super(tabSheet, id, name, dataselectionDTO, sessionDTO);
        this.binder = binder;
        dataSelectionWF = new DataSelectionTab(dataselectionDTO, sessionDTO);
        getNotesTab();
    }

    private void getNotesTab() throws SystemException {
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
        if (!ARMUtils.VIEW_SMALL.equals(sessionDTO.getAction()) && !sessionDTO.getTransactionTables().containsKey(getTransactionName())) {
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

    public abstract void configurePermission();

    public void getSummaryDetailsTab(AbstractPipelineSummary pipelineSummary, AdjustmentDetailAccural pipelineDetails) {
        TabSheet.Tab tab3 = getTabSheet().addTab(pipelineSummary, "Adjustment Summary");
        tab3.setDefaultFocusComponent(pipelineSummary.getDefaultFocusComponent());
        TabSheet.Tab tab4 = getTabSheet().addTab(pipelineDetails, "Adjustment Detail");
        tab4.setDefaultFocusComponent(pipelineDetails.getDefaultFocusComponent());
        TabSheet.Tab tab5 = getTabSheet().addTab(getNotes(), "Additional Information");
        tab5.setDefaultFocusComponent(getNotes().getDefaultFocusComponent());
    }

    public void getSummaryDetailsTab(AbstractDemandSummarySelection demandSummary, AdjustmentDetailAccural demandDetails) {
        TabSheet.Tab tab3 = getTabSheet().addTab(demandSummary, "Adjustment Summary");
        tab3.setDefaultFocusComponent(demandSummary.getDefaultFocusComponent());
        TabSheet.Tab tab4 = getTabSheet().addTab(demandDetails, "Adjustment Detail");
        tab4.setDefaultFocusComponent(demandDetails.getDefaultFocusComponent());
        TabSheet.Tab tab5 = getTabSheet().addTab(getNotes(), "Additional Information");
        tab5.setDefaultFocusComponent(getNotes().getDefaultFocusComponent());
    }
}
