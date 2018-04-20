/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.ui.form;

import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.adjustmentreserveconfiguration.saveaction.SaveMainToTempAction;
import com.stpl.app.arm.adjustmentreserveconfiguration.ui.abstractreserveform.AbstractReserve;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.excecutors.ActionExecutor;
import com.stpl.app.arm.utils.ReserveSelection;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.CommonUtil;
import com.stpl.ifs.util.constants.ARMMessages;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Srithar.Raju
 */
public class ViewAdjustmentReserve extends AbstractReserve {

    private AdjustmentReserveDTO selectedDto;

    ViewAdjustmentReserve(SessionDTO session, AdjustmentReserveDTO dto, ReserveSelection resSelection) {
        super("Adjustment & Reserve Configuration Details", session, resSelection);
        selectedDto = dto;
        configureFields();
    }

    @Override
    protected void loadComboBox() {
        companyDdlbRes.addItem(selectedDto.getCompanyDdlbRes());
        companyDdlbRes.setItemCaption(selectedDto.getCompanyDdlbRes(), selectedDto.getCompanyName());
        companyDdlbRes.select(selectedDto.getCompanyDdlbRes());
        businessDdlbRes.addItem(selectedDto.getBusinessDdlbRes());
        businessDdlbRes.setItemCaption(selectedDto.getBusinessDdlbRes(), selectedDto.getBusinessUnitName());
        businessDdlbRes.select(selectedDto.getBusinessDdlbRes());
        deductionCategoryDdlbRes.addItem(0);
        deductionCategoryDdlbRes.setItemCaption(0, selectedDto.getDeductionCategory());
        deductionCategoryDdlbRes.select(0);
        deductionTypeDdlbRes.addItem(0);
        deductionTypeDdlbRes.setItemCaption(0, selectedDto.getDeductionType());
        deductionTypeDdlbRes.select(0);
        deductionProgramDdlbRes.addItem(0);
        deductionProgramDdlbRes.setItemCaption(0, selectedDto.getDeductionProgram());
        deductionProgramDdlbRes.select(0);
        selection.setCompanyNo(getCompanyNo(Integer.valueOf(companyDdlbRes.getValue().toString())));
        selection.setDivision(getCompanyNo(Integer.valueOf(businessDdlbRes.getValue().toString())));
    }

    @Override
    protected CustomFieldGroup getBinder() {
        companyDdlbRes.setValue(selectedDto.getCompanyDdlbRes());
        businessDdlbRes.setValue(selectedDto.getBusinessDdlbRes());
        deductionCategoryDdlbRes.setValue(selectedDto.getDeductionCategoryDdlbRes());
        deductionTypeDdlbRes.setValue(selectedDto.getDeductionTypeDdlbRes());
        deductionProgramDdlbRes.setValue(selectedDto.getDeductionProgramDdlbRes());
        companyDdlbRes.setEnabled(Boolean.FALSE);
        businessDdlbRes.setEnabled(Boolean.FALSE);
        deductionCategoryDdlbRes.setEnabled(Boolean.FALSE);
        deductionTypeDdlbRes.setEnabled(Boolean.FALSE);
        deductionProgramDdlbRes.setEnabled(Boolean.FALSE);
        selection.setCompanyNo(getCompanyNo(Integer.valueOf(companyDdlbRes.getValue().toString())));
        selection.setDivision(getCompanyNo(Integer.valueOf(businessDdlbRes.getValue().toString())));
        selection.setBusUnit(businessDdlbRes.getItemCaption(Integer.valueOf(businessDdlbRes.getValue().toString())));
        resetLineBtnRes.setEnabled(Boolean.FALSE);
        try {
            binder.commit();
        } catch (FieldGroup.CommitException ex) {
            LOGGER.error("Error in getBinder : " + ex);
        }
        return binder;
    }

    @Override
    protected void loadSelection() {
        selection.setSession(sessionDTO);
        selection.setSearchBinderDTO(selectedDto);
        resetBtnRes.setEnabled(Boolean.FALSE);
        massValueRes.setEnabled(Boolean.FALSE);
        massfieldDdlbRes.setEnabled(Boolean.FALSE);
        populateBtn.setEnabled(Boolean.FALSE);
        resetLineBtnRes.setEnabled(Boolean.FALSE);
        addLineBtnRes.setEnabled(Boolean.FALSE);
        removeLineBtnRes.setEnabled(Boolean.FALSE);
        copyLineBtnRes.setEnabled(Boolean.FALSE);
        saveBtnRes.setEnabled(Boolean.FALSE);
        selection.setIsViewMode(Boolean.TRUE);

        getMasterSids();
        try {
            ActionExecutor executor = new ActionExecutor();
            executor.callingActionExecution(new SaveMainToTempAction(selection));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

    }

    @Override
    public void closeBtnLogic() {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                close();
            }

            @Override
            public void noMethod() {
                LOGGER.debug("noMethod Method:");
            }
        }.getConfirmationMessage("Confirmation", ARMMessages.getCloseMessageID008());
    }

    @Override
    public void configureTabAddLineLogic() {
        LOGGER.debug("configureTabAddLineLogic Method:");
    }

    @Override
    public void adjustmentSummaryAddLineLogic() {
        LOGGER.debug("adjustmentSummaryAddLineLogic Method:");
    }

    @Override
    protected void balanceSummaryAddLineLogic() {
        LOGGER.debug("balanceSummaryAddLineLogic Method:");
    }

    @Override
    protected boolean saveToMaster() {
        return Boolean.TRUE;
    }

    @Override
    protected void getMasterSids() {
        selection.setReserveMasterSid(logic.getMasterSids(selection));
        selection.setGtnDetailsMasterSid(selectedDto.getSearchMasterSid());
        selection.setMasterSID(selectedDto.getSearchMasterSid());
    }

    @Override
    protected void loadTablefirstTime() {
        detailsTableLogic.loadsetData(Boolean.TRUE, selection);
    }

    @Override
    protected void resetAdjustmentSummaryLine() {
        LOGGER.debug("resetAdjustmentSummaryLine Method:");
    }

    @Override
    protected void resetConfigureTabLine() {
        LOGGER.debug("resetConfigureTabLine Method:");
    }

    @Override
    protected void resetBalanceSummaryLine() {
        LOGGER.debug("resetBalanceSummaryLine Method:");
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
