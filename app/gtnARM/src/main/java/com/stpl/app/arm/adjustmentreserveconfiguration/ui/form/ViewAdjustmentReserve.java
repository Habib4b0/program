/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.ui.form;

import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.adjustmentreserveconfiguration.ui.abstractreserveform.AbstractReserve;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.utils.ReserveSelection;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.constants.ARMMessages;
import com.vaadin.data.fieldgroup.FieldGroup;

/**
 *
 * @author Srithar.Raju
 */
public class ViewAdjustmentReserve extends AbstractReserve {

    AdjustmentReserveDTO selectedDto;

    ViewAdjustmentReserve(SessionDTO session, AdjustmentReserveDTO dto,ReserveSelection resSelection) {
        super("Adjustment & Reserve Configuration Details", session,resSelection);
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
            LOGGER.error(ex);
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
            }
        }.getConfirmationMessage("Confirmation", ARMMessages.getCloseMessageID008());
    }

    @Override
    protected void addLineBtnLogic() {

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
    protected void loadResetData() {
    }

}
