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

    public CopyAdjustmentReserve(SessionDTO sessionDTO, AdjustmentReserveDTO dto, ReserveSelection resSelection) {
        super("Adjustment & Reserve Configuration Details", sessionDTO, resSelection);
        this.selectedDto = dto;
        configureFields();
    }

    @Override
    protected void getMasterSids() {
        selection.setReserveMasterSid(logic.getMasterSids(selection));
        selection.setGtnDetailsMasterSid(selectedDto.getSearchMasterSid());
        selection.setMasterSID(selectedDto.getSearchMasterSid());
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
            selection.setSession(sessionDTO);
            selection.setSearchBinderDTO(selectedDto);
            selection.setWindowBinderDTO(binderDto);
            selection.setIsSaved(false);
            getMasterSids();

            ActionExecutor executor = new ActionExecutor();
            executor.callingActionExecution(new SaveMainToTempAction(selection));

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    @Override
    public void configureTabAddLineLogic() {
        try {
            binder.commit();
            if (saveToMaster()) {
                logic.addLineLogic(selection);
                if (configurationTypeOpgRes.getValue().equals(ARMConstants.getReserveDetails())) {
                    selection.setMasterSID(selection.getReserveMasterSid());
                } else {
                    selection.setMasterSID(selection.getGtnDetailsMasterSid());
                }
                detailsTableLogic.loadsetData(true, selection);

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
        Validation validation = new ValidationAddRemoveLine(selection, true);
        if (!validation.doValidate()) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, validation.validationMessage());
            return;
        }
        adjustmentSummaryConfigLogic.addLineLogic(selection);
        adjustmentSummaryTableLogic.loadSetData(true, selection);
    }

    @Override
    protected void balanceSummaryAddLineLogic() {
        Validation validation = new ValidationAddRemoveLine(selection, true);
        if (!validation.doValidate()) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, validation.validationMessage());
            return;
        }
        balanceSummaryLogic.addLineLogic(selection);
        balSummaryConfigurationTableLogic.loadSetData(true, selection);
        LOGGER.debug("balanceSummaryAddLineLogic Method in Copy");
    }

    @Override
    protected boolean saveToMaster() {
        boolean binderModified = binder.isModified();
        int revmasid = selection.getReserveMasterSid();
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
                int masid = selection.getMasterSID();
                selection.setMasterSID(selection.getGtnDetailsMasterSid());
                boolean isGTNSave = !logic.updateMasterTable(selection, binderDto);
                selection.setMasterSID(selection.getReserveMasterSid());
                if (!logic.updateMasterTable(selection, binderDto) || isGTNSave) {
                    selection.setMasterSID(masid);
                    AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getSaveMessageID006());
                    return false;
                }
                selection.setMasterSID(masid);
                selection.setCompanyNo(getCompanyNo(binderDto.getCompanyDdlbRes()));
                selection.setDivision(getCompanyNo(binderDto.getBusinessDdlbRes()));
                selection.setBusUnit(businessDdlbRes.getItemCaption(binderDto.getBusinessDdlbRes()));
            }
            return true;
        } else {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getPropertyMessage001());
            return false;
        }
    }

    private boolean copyFunctionalitySave(int revmasid) {
        int id = logic.addLineForMaster(selection, 1);
        int id1 = logic.addLineForMaster(selection, 0);
        if (id == 0 && id1 == 0) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getSaveMessageID006());
            return true;
        } else {
            selection.setReserveMasterSid(id1);
            selection.setGtnDetailsMasterSid(id);
            if (selection.isIsGTNDetails()) {
                selection.setMasterSID(selection.getGtnDetailsMasterSid());
            } else {
                selection.setMasterSID(selection.getReserveMasterSid());
            }
        }
        isFirst = true;
        logic.updateMasterSid(selectedDto, selection, revmasid);
        return false;
    }

    private boolean duplicateCheck() {
        if (logic.isDuplicateCompany(binderDto) && !selection.isIsSaved()) {
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
        detailsTableLogic.loadsetData(true, selection);
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
                    deductionTypeDdlbRes.addItem((int) obj);
                    deductionTypeDdlbRes.setItemCaption((int) obj, (idhelper.get((int) obj)).getDescription());
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
                        deductionProgramDdlbRes.addItem((int) obj);
                        deductionProgramDdlbRes.setItemCaption((int) obj, (idhelper.get((int) obj)).getDescription());
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
        if (selection.isIsSaved()) {
            if (configurationTypeOpgRes.getValue().equals(ARMConstants.getReserveDetails())) {
                selection.setMasterSID(selection.getReserveMasterSid());
            } else {
                selection.setMasterSID(selection.getGtnDetailsMasterSid());
            }
            logic.resetDBRecord(selection);
            logic.insertToTempTable(selection);
            detailsTableLogic.loadsetData(true, selection);
        } else {
            ReserveSelection tempSelection = new ReserveSelection();
            try {

                tempSelection = this.selection.clone(tempSelection);
            } catch (NullPointerException e) {
                LOGGER.error("Error in resetConfigureTabLine :", e);
            } catch (Exception ex) {
                LOGGER.error("Error in resetConfigureTabLine :", ex);
            }
            if (tempSelection != null) {
                tempSelection.setReserveMasterSid(logic.getMasterSids(this.selection));
                tempSelection.setGtnDetailsMasterSid(selectedDto.getSearchMasterSid());
                tempSelection.setMasterSID(selectedDto.getSearchMasterSid());
            }
            logic.resetDBRecord(this.selection);
            logic.insertToTempTable(tempSelection, selection);
            detailsTableLogic.loadsetData(true, tempSelection);
        }
    }

    @Override
    protected void resetAdjustmentSummaryLine() {
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
