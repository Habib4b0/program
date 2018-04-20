/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.ui.form;

import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.adjustmentreserveconfiguration.saveaction.SaveMainToTempAction;
import com.stpl.app.arm.adjustmentreserveconfiguration.ui.abstractreserveform.AbstractReserve;
import com.stpl.app.arm.excecutors.Validation;
import com.stpl.app.arm.adjustmentreserveconfiguration.validation.ValidationAddRemoveLine;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.excecutors.ActionExecutor;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.ReserveSelection;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.CommonUtil;
import com.stpl.ifs.util.constants.ARMConstants;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 * @author Srithar.Raju
 */
public class EditAdjustmentReserve extends AbstractReserve {

    private AdjustmentReserveDTO selectedDto;

    public EditAdjustmentReserve(SessionDTO session, AdjustmentReserveDTO dto, ReserveSelection resSelection) {
        super("Adjustment & Reserve Configuration Details", session, resSelection);
        selectedDto = dto;
        configureFields();
    }

    @Override
    protected void loadSelection() {
        try {
            selection.setSession(sessionDTO);
            selection.setSearchBinderDTO(selectedDto);
            getMasterSids();

            ActionExecutor executor = new ActionExecutor();
            executor.callingActionExecution(new SaveMainToTempAction(selection));

            selection.setIsSaved(true);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    @Override
    protected CustomFieldGroup getBinder() {
        return null;
    }

    @Override
    public void configureTabAddLineLogic() {
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
            LOGGER.error("Error in configureTabAddLineLogic :" + ex);
        }
    }

    /**
     * Addline Logic
     *
     */
    @Override
    public void adjustmentSummaryAddLineLogic() {
        Validation validation = new ValidationAddRemoveLine(selection, true);
        if (!validation.doValidate()) {
            AbstractNotificationUtils.getErrorNotification("Error", validation.validationMessage());
            return;
        }
        adjustmentSummaryConfigLogic.addLineLogic(selection);
        adjustmentSummaryTableLogic.loadSetData(true, selection);
    }

    @Override
    protected void balanceSummaryAddLineLogic() {
        Validation validation = new ValidationAddRemoveLine(selection, true);
        if (!validation.doValidate()) {
            AbstractNotificationUtils.getErrorNotification("Error", validation.validationMessage());
            return;
        }
        balanceSummaryLogic.addLineLogic(selection);
        balSummaryConfigurationTableLogic.loadSetData(true, selection);
        LOGGER.debug("balanceSummaryAddLineLogic Method in Edit");
    }

    @Override
    protected boolean saveToMaster() {
        return true;
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
        companyDdlbRes.setEnabled(false);
        businessDdlbRes.setEnabled(false);
        deductionCategoryDdlbRes.setEnabled(false);
        deductionTypeDdlbRes.setEnabled(false);
        deductionProgramDdlbRes.setEnabled(false);
        selection.setCompanyNo(getCompanyNo(Integer.valueOf(companyDdlbRes.getValue().toString())));
        selection.setDivision(getCompanyNo(Integer.valueOf(businessDdlbRes.getValue().toString())));
        selection.setBusUnit(businessDdlbRes.getItemCaption(Integer.valueOf(businessDdlbRes.getValue().toString())));
        resetBtnRes.setEnabled(false);

    }

    @Override
    protected void loadTablefirstTime() {
        detailsTableLogic.loadsetData(true, selection);
    }

    /**
     *
     * Method to Reset the line to as it was
     *
     */
    @Override
    public void resetConfigureTabLine() {
        logic.resetDBRecord(selection);
        logic.insertToTempTable(selection);
        detailsTableLogic.loadsetData(true, selection);
    }

    /**
     * Adjustment summary reset line logic
     *
     */
    @Override
    public void resetAdjustmentSummaryLine() {
        adjustmentSummaryConfigLogic.deleteTempTableRecords(selection);
        adjustmentSummaryConfigLogic.insertAdjSummaryToTempTableFromMainTable(selection);
        adjustmentSummaryTableLogic.loadSetData(true, selection);
        if (adjustmentSummaryTableLogic.getCount() == 0) {
            methodologyDdlb.setValue(0);
        }
        List list = adjustmentSummaryConfigLogic.isAllCheckBoxesAreChecked(selection);
        adjustmentSummaryTable.setColumnCheckBox(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), true, list.size() != 1 ? false : "true".equals(String.valueOf(list.get(0))));
    }

    @Override
    protected void resetBalanceSummaryLine() {
        LOGGER.debug("resetBalanceSummaryLine Method in Copy");
        balanceSummaryLogic.deleteTempTableRecords(selection);
        balanceSummaryLogic.insertBalanceSummaryToTempTableFromMainTable(selection);
        balSummaryConfigurationTableLogic.loadSetData(true, selection);
        if (balSummaryConfigurationTableLogic.getCount() == 0) {
            reportTypeDdlb.setValue(0);
        }
        List list = adjustmentSummaryConfigLogic.isAllCheckBoxesAreChecked(selection);
        balanceSummaryTable.setColumnCheckBox(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), true, list.size() != 1 ? false : "true".equals(String.valueOf(list.get(0))));
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
