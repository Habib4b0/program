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
import com.stpl.ifs.util.constants.ARMConstants;
import com.vaadin.data.fieldgroup.FieldGroup;

/**
 *
 * @author Srithar.Raju
 */
public class EditAdjustmentReserve extends AbstractReserve {

    AdjustmentReserveDTO selectedDto;

    EditAdjustmentReserve(SessionDTO session, AdjustmentReserveDTO dto,ReserveSelection resSelection) {
        super("Adjustment & Reserve Configuration Details", session,resSelection);
        selectedDto = dto;
        configureFields();
    }

    @Override
    protected void loadSelection() {
        selection.setSession(sessionDTO);
        selection.setSearchBinderDTO(selectedDto);
        getMasterSids();
        logic.insertToTempTable(selection);
        selection.setIsSaved(Boolean.TRUE);
    }

    @Override
    protected CustomFieldGroup getBinder() {
        return null;
    }

    @Override
    protected void addLineBtnLogic() {
        try {
            binder.commit();
            logic.addLineLogic(selection);
            if (configurationTypeOpgRes.getValue().equals(ARMConstants.getReserveDetails())) {
                selection.setMasterSID(selection.getReserveMasterSid());
            } else {
                selection.setMasterSID(selection.getGtnDetailsMasterSid());
            }
            detailsTableLogic.loadsetData(true, selection);
        } catch (FieldGroup.CommitException ex) {
            LOGGER.error(ex.getMessage());
        }
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
    protected void loadComboBox() {
        companyDdlbRes.addItem(selectedDto.getCompanyDdlbRes());
        companyDdlbRes.setItemCaption(selectedDto.getCompanyDdlbRes(), selectedDto.getCompanyName());
        companyDdlbRes.select(selectedDto.getCompanyDdlbRes());
        businessDdlbRes.addItem(selectedDto.getBusinessDdlbRes());
        businessDdlbRes.setItemCaption(selectedDto.getBusinessDdlbRes(), selectedDto.getBusinessUnitName());
        businessDdlbRes.select(selectedDto.getBusinessDdlbRes());
        deductionCategoryDdlbRes.addItem(selectedDto.getDeductionCategoryDdlbRes()); // Changed to fix mass populate issue on edit
        deductionCategoryDdlbRes.setItemCaption(selectedDto.getDeductionCategoryDdlbRes(), selectedDto.getDeductionCategory());// Changed to fix mass populate issue on edit
        deductionCategoryDdlbRes.select(selectedDto.getDeductionCategoryDdlbRes());// Changed to fix mass populate issue on edit
        deductionTypeDdlbRes.addItem(selectedDto.getDeductionTypeDdlbRes());
        deductionTypeDdlbRes.setItemCaption(selectedDto.getDeductionTypeDdlbRes(), selectedDto.getDeductionType());
        deductionTypeDdlbRes.select(selectedDto.getDeductionTypeDdlbRes());
        deductionProgramDdlbRes.addItem(selectedDto.getDeductionProgramDdlbRes());
        deductionProgramDdlbRes.setItemCaption(selectedDto.getDeductionProgramDdlbRes(), selectedDto.getDeductionProgram());
        deductionProgramDdlbRes.select(selectedDto.getDeductionProgramDdlbRes());
        companyDdlbRes.setEnabled(Boolean.FALSE);
        businessDdlbRes.setEnabled(Boolean.FALSE);
        deductionCategoryDdlbRes.setEnabled(Boolean.FALSE);
        deductionTypeDdlbRes.setEnabled(Boolean.FALSE);
        deductionProgramDdlbRes.setEnabled(Boolean.FALSE);
        selection.setCompanyNo(getCompanyNo(Integer.valueOf(companyDdlbRes.getValue().toString())));
        selection.setDivision(getCompanyNo(Integer.valueOf(businessDdlbRes.getValue().toString())));
        selection.setBusUnit(businessDdlbRes.getItemCaption(Integer.valueOf(businessDdlbRes.getValue().toString())));
        resetBtnRes.setEnabled(Boolean.FALSE);

    }

    @Override
    protected void loadTablefirstTime() {
        detailsTableLogic.loadsetData(Boolean.TRUE, selection);
    }

    @Override
    protected void loadResetData() {
        logic.resetDBRecord(selection);
        logic.insertToTempTable(selection);
        detailsTableLogic.loadsetData(Boolean.TRUE, selection);
    }
}
