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
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.excecutors.ActionExecutor;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.ReserveSelection;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.ui.Button;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Srithar.Raju
 */
public class CopyAdjustmentReserve extends AbstractReserve {

    private AdjustmentReserveDTO selectedDto;
    private boolean isFirst;
    private boolean isValueChangeAllowed = true;
    private ReserveSelection copyResSelection;

    public CopyAdjustmentReserve(SessionDTO sessionDTO, AdjustmentReserveDTO dto, ReserveSelection resSelection) {
        super("Adjustment & Reserve Configuration Details", sessionDTO, resSelection);
        this.copyResSelection = resSelection;
        super.init();
        this.selectedDto = dto;
        configureFields();
    }

    @Override
    protected void getMasterSids() {
        copyResSelection.setReserveMasterSid(logic.getMasterSids(copyResSelection));
        copyResSelection.setGtnDetailsMasterSid(selectedDto.getSearchMasterSid());
        copyResSelection.setMasterSID(selectedDto.getSearchMasterSid());
    }

    @Override
    protected CustomFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<>(binderDto));
        binder.setBuffered(true);
        return binder;
    }

    @Override
    protected void loadSelection() {
        try {
            copyResSelection.setSession(sessionDTO);
            copyResSelection.setSearchBinderDTO(selectedDto);
            copyResSelection.setWindowBinderDTO(binderDto);
            copyResSelection.setIsSaved(false);
            getMasterSids();

            ActionExecutor executor = new ActionExecutor();
            executor.callingActionExecution(new SaveMainToTempAction(copyResSelection));

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    @Override
    public void configureTabAddLineLogic() {
        try {
            binder.commit();
            if (saveToMaster()) {
                logic.addLineLogic(copyResSelection);
                if (configurationTypeOpgRes.getValue().equals(ARMConstants.getReserveDetails())) {
                    copyResSelection.setMasterSID(copyResSelection.getReserveMasterSid());
                } else {
                    copyResSelection.setMasterSID(copyResSelection.getGtnDetailsMasterSid());
                }
                detailsTableLogic.loadsetData(true, copyResSelection);

            }
        } catch (FieldGroup.CommitException ex) {
            LOGGER.error("Error in configureTabAddLineLogic :", ex);
        }
    }

    /**
     * Addline Logic
     *
     */
    @Override
    public void adjustmentSummaryAddLineLogic() {
        Validation validation = new ValidationAddRemoveLine(copyResSelection, true);
        if (!validation.doValidate()) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, validation.validationMessage());
            return;
        }
        adjustmentSummaryConfigLogic.addLineLogic(copyResSelection);
        adjustmentSummaryTableLogic.loadSetData(true, copyResSelection);
    }

    @Override
    protected void balanceSummaryAddLineLogic() {
        Validation validation = new ValidationAddRemoveLine(copyResSelection, true);
        if (!validation.doValidate()) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, validation.validationMessage());
            return;
        }
        balanceSummaryLogic.addLineLogic(copyResSelection);
        balSummaryConfigurationTableLogic.loadSetData(true, copyResSelection);
        LOGGER.debug("balanceSummaryAddLineLogic Method in Copy");
    }

    @Override
    protected boolean saveToMaster() {
        boolean binderModified = binder.isModified();
        int revmasid = copyResSelection.getReserveMasterSid();
        commitBinder(binderModified);
        return saveLogic(revmasid, binderModified);
    }

    private boolean saveLogic(int revmasid, boolean binderModified) {
        if (logic.combinationIsSelected(binderDto)) {
            if (duplicateCheck()) {
                return false;
            }
            if (!isFirst) {
                if (copyFunctionalitySave(revmasid)) {
                    return false;
                }
            } else if (binderModified) {
                int masid = copyResSelection.getMasterSID();
                copyResSelection.setMasterSID(copyResSelection.getGtnDetailsMasterSid());
                boolean isGTNSave = !logic.updateMasterTable(copyResSelection, binderDto);
                copyResSelection.setMasterSID(copyResSelection.getReserveMasterSid());
                if (!logic.updateMasterTable(copyResSelection, binderDto) || isGTNSave) {
                    copyResSelection.setMasterSID(masid);
                    AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getSaveMessageID006());
                    return false;
                }
                copyResSelection.setMasterSID(masid);
                copyResSelection.setCompanyNo(getCompanyNo(binderDto.getCompanyDdlbRes()));
                copyResSelection.setDivision(getCompanyNo(binderDto.getBusinessDdlbRes()));
                copyResSelection.setBusUnit(businessDdlbRes.getItemCaption(binderDto.getBusinessDdlbRes()));
            }
            return true;
        } else {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getPropertyMessage001());
            return false;
        }
    }

    private boolean copyFunctionalitySave(int revmasid) {
        int id = logic.addLineForMaster(copyResSelection, 1);
        int id1 = logic.addLineForMaster(copyResSelection, 0);
        if (id == 0 && id1 == 0) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getSaveMessageID006());
            return true;
        } else {
            copyResSelection.setReserveMasterSid(id1);
            copyResSelection.setGtnDetailsMasterSid(id);
            if (copyResSelection.isIsGTNDetails()) {
                copyResSelection.setMasterSID(copyResSelection.getGtnDetailsMasterSid());
            } else {
                copyResSelection.setMasterSID(copyResSelection.getReserveMasterSid());
            }
        }
        isFirst = true;
        logic.updateMasterSid(selectedDto, copyResSelection, revmasid);
        return false;
    }

    private boolean duplicateCheck() {
        if (logic.isDuplicateCompany(binderDto) && !copyResSelection.isIsSaved()) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getSaveMessageID002());
            return true;
        }
        return false;
    }

    private void commitBinder(boolean binderModified) {
        if (binderModified) {
            try {
                binder.commit();
            } catch (FieldGroup.CommitException ex) {
                LOGGER.error("Error in saveToMaster :", ex);
            }
        }
    }

    @Override
    protected void loadTablefirstTime() {
        LOGGER.debug("Inside Load Table Firsrt Time");
        detailsTableLogic.loadsetData(true, copyResSelection);
    }

    @UiHandler("deductionCategoryDdlbRes")
    public void valueChangeDeductionCategoryDdlbRes(final Property.ValueChangeEvent event) {
        LOGGER.debug(event.toString());
        isValueChangeAllowed = false;
        if ((int) deductionCategoryDdlbRes.getValue() != 0) {
            Map<Integer, HelperDTO> idhelper = HelperListUtil.getInstance().getIdHelperDTOMap();
            List<Object> list = logic.getTypeValuesBasedOnCategory((int) deductionCategoryDdlbRes.getValue());
            if (!deductionTypeDdlbRes.getItemIds().isEmpty()) {
                deductionTypeDdlbRes.removeAllItems();
            }

            deductionTypeDdlbRes.addItem(0);
            deductionTypeDdlbRes.setItemCaption(0, GlobalConstants.getSelectOne());
            for (Object obj : list) {
                if (obj != null) {
                    deductionTypeDdlbRes.addItem((Integer) obj);
                    deductionTypeDdlbRes.setItemCaption((Integer) obj, (idhelper.get((Integer) obj)).getDescription());
                }
            }
            deductionTypeDdlbRes.select(0);
            deductionProgramDdlbRes.removeAllItems();
            deductionProgramDdlbRes.addItem(0);
            deductionProgramDdlbRes.setItemCaption(0, GlobalConstants.getSelectOne());
            deductionProgramDdlbRes.select(0);
            isValueChangeAllowed = true;
        } else {
            CommonLogic.configureDropDownsForDeduction(deductionTypeDdlbRes, "getDeductionType");
        }
    }

    @UiHandler("deductionTypeDdlbRes")
    public void valueChangeDeductionTypeDdlbRes(final Property.ValueChangeEvent event) {
        LOGGER.debug(event.toString());
        if (isValueChangeAllowed && (int) deductionTypeDdlbRes.getValue() != 0) {
            if (deductionTypeDdlbRes.getValue() != null) {
                Map<Integer, HelperDTO> idhelper = HelperListUtil.getInstance().getIdHelperDTOMap();
                List<Object> list = logic.getTypeValuesBasedOnType((int) deductionCategoryDdlbRes.getValue(), (int) deductionTypeDdlbRes.getValue());
                if (!deductionProgramDdlbRes.getItemIds().isEmpty()) {
                    deductionProgramDdlbRes.removeAllItems();
                }
                deductionProgramDdlbRes.addItem(0);
                deductionProgramDdlbRes.setItemCaption(0, GlobalConstants.getSelectOne());
                for (Object obj : list) {
                    if (obj != null) {
                        deductionProgramDdlbRes.addItem((Integer) obj);
                        deductionProgramDdlbRes.setItemCaption((Integer) obj, (idhelper.get((Integer) obj)).getDescription());
                    }
                }
            }
            deductionProgramDdlbRes.select(0);
        } else {
            CommonLogic.configureDropDownsForDeduction(deductionProgramDdlbRes, "getDeductionProgram");
        }
    }

    @UiHandler("resetBtnRes")
    public void resetSelectionButtonLogic(Button.ClickEvent copyEvent) {
        LOGGER.debug(copyEvent.toString());
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    LOGGER.debug("Enters Reset Button Copy Mode");
                    binderDto = new AdjustmentReserveDTO();
                    binder.setItemDataSource(new BeanItem<>(binderDto));
                    binder.commit();
                } catch (Exception ex) {
                    LOGGER.error("Error in Copy resetSelectionButtonLogic :", ex);
                }
            }

            @Override
            public void noMethod() {
                LOGGER.debug("noMethod Method:");
            }
        }.getConfirmationMessage("Confirmation", ARMMessages.getResetMessageID001());
    }

    @Override
    protected void resetConfigureTabLine() {
        if (copyResSelection.isIsSaved()) {
            if (configurationTypeOpgRes.getValue().equals(ARMConstants.getReserveDetails())) {
                copyResSelection.setMasterSID(copyResSelection.getReserveMasterSid());
            } else {
                copyResSelection.setMasterSID(copyResSelection.getGtnDetailsMasterSid());
            }
            logic.resetDBRecord(copyResSelection);
            logic.insertToTempTable(copyResSelection);
            detailsTableLogic.loadsetData(true, copyResSelection);
        } else {
            ReserveSelection tempSelection = new ReserveSelection();
            try {

                tempSelection = this.copyResSelection.clone(tempSelection);
            } catch (NullPointerException e) {
                LOGGER.error("Error in resetConfigureTabLine :", e);
            } catch (Exception ex) {
                LOGGER.error("Error in resetConfigureTabLine :", ex);
            }
            if (tempSelection != null) {
                tempSelection.setReserveMasterSid(logic.getMasterSids(this.copyResSelection));
                tempSelection.setGtnDetailsMasterSid(selectedDto.getSearchMasterSid());
                tempSelection.setMasterSID(selectedDto.getSearchMasterSid());
            }
            logic.resetDBRecord(this.copyResSelection);
            logic.insertToTempTable(tempSelection, copyResSelection);
            detailsTableLogic.loadsetData(true, tempSelection);
        }
    }

    @Override
    protected void resetAdjustmentSummaryLine() {
        adjustmentSummaryConfigLogic.deleteTempTableRecords(copyResSelection);
        adjustmentSummaryConfigLogic.insertAdjSummaryToTempTableFromMainTable(copyResSelection);
        adjustmentSummaryTableLogic.loadSetData(true, copyResSelection);
        if (adjustmentSummaryTableLogic.getCount() == 0) {
            methodologyDdlb.setValue(0);
        }
        List list = adjustmentSummaryConfigLogic.isAllCheckBoxesAreChecked(copyResSelection);
        adjustmentSummaryTable.setColumnCheckBox(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), true, list.size() != 1 ? false : "true".equals(String.valueOf(list.get(0))));
    }

    @Override
    protected void resetBalanceSummaryLine() {
        LOGGER.debug("resetBalanceSummaryLine Method in Copy");
        balanceSummaryLogic.deleteTempTableRecords(copyResSelection);
        balanceSummaryLogic.insertBalanceSummaryToTempTableFromMainTable(copyResSelection);
        balSummaryConfigurationTableLogic.loadSetData(true, copyResSelection);
        if (balSummaryConfigurationTableLogic.getCount() == 0) {
            reportTypeDdlb.setValue(0);
        }
        List list = adjustmentSummaryConfigLogic.isAllCheckBoxesAreChecked(copyResSelection);
        balanceSummaryTable.setColumnCheckBox(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), true, list.size() != 1 ? false : "true".equals(String.valueOf(list.get(0))));

    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
