/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.ui.form;

import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.adjustmentreserveconfiguration.ui.abstractreserveform.AbstractReserve;
import com.stpl.app.arm.excecutors.Validation;
import com.stpl.app.arm.adjustmentreserveconfiguration.validation.ValidationAddRemoveLine;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.ReserveSelection;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.ui.Button;
import java.util.List;
import java.util.Map;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Srithar.Raju
 */
public class AddAdjustmentReserve extends AbstractReserve {

    private boolean isFirst;
    private boolean isValueChangeAllowed = true;

    public AddAdjustmentReserve(SessionDTO sessionDTO, ReserveSelection resSelection) {
        super("Adjustment & Reserve Configuration Details", sessionDTO, resSelection);
        super.init();
        super.configureFields();
        this.selection = resSelection;
    }

    /**
     * This method will configure the binder for this page.
     *
     * @return
     */
    @Override
    protected CustomFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<>(binderDto));
        binder.setBuffered(true);
        return binder;
    }

    /**
     * This method is used to load the selectionDTO used in logics
     */
    @Override
    protected void loadSelection() {
        selection.setSession(sessionDTO);
        selection.setWindowBinderDTO(binderDto);
        selection.setIsSaved(false);
    }

    @Override
    protected boolean saveToMaster() {
        boolean binderModified = binder.isModified();
        if (binderModified) {
            try {
                binder.commit();
            } catch (FieldGroup.CommitException ex) {
                LOGGER.error("Error in saveToMaster :", ex);
            }
        }
        if (logic.combinationIsSelected(binderDto)) {
            if (logic.isDuplicateCompany(binderDto) && !selection.isIsSaved()) {
                AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getSaveMessageID008()); // Changed as per GAL-5879
                return false;
            }
            return saveReserveGtnMaster(binderModified);
        } else {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getPropertyMessage001());
            return false;
        }
    }

    private boolean saveReserveGtnMaster(boolean binderModified) {
        if (configurationTypeOpgRes.getValue().equals(ARMConstants.getReserveDetails())) {
            selection.setIsGTNDetails(false);
            if (selection.getReserveMasterSid() == 0) {
                int id = logic.addLineForMaster(selection, 0);
                if (id == 0) {
                    AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getSaveMessageID006());
                    return false;
                } else {
                    selection.setReserveMasterSid(id);
                    selection.setMasterSID(selection.getReserveMasterSid());
                    if (selection.getGtnDetailsMasterSid() == 0) {
                        selection.setGtnDetailsMasterSid(logic.addLineForMaster(selection, 1));
                    }
                    return true;
                }
            } else if ((binderModified) && (!logic.updateMasterTable(selection, binderDto))) {
                AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getSaveMessageID006());
                return false;
            }
            return true;
        } else {
            return saveToMasterForGTN(binderModified);
        }
    }

    private Boolean saveToMasterForGTN(boolean binderModified) {
        selection.setIsGTNDetails(true);
        if (selection.getGtnDetailsMasterSid() == 0) {
            int id = logic.addLineForMaster(selection, 1);
            if (id == 0) {
                AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getSaveMessageID006());
                return Boolean.FALSE;
            } else {
                if (selection.getReserveMasterSid() == 0) {
                    selection.setReserveMasterSid(logic.addLineForMaster(selection, 0));
                }
                selection.setGtnDetailsMasterSid(id);
                selection.setMasterSID(selection.getGtnDetailsMasterSid());
                return Boolean.TRUE;
            }
        } else if ((binderModified) && (!logic.updateMasterTable(selection, binderDto))) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getSaveMessageID006());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * Reset logic for selection criteria.
     *
     * @param resetevent
     */
    @UiHandler("resetBtnRes")
    public void resetSelectionButtonLogic(Button.ClickEvent resetevent) {
        LOGGER.debug(resetevent.toString());
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    LOGGER.debug("Inside Add Reserve Yes Method:");
                    binderDto = new AdjustmentReserveDTO();
                    binder.setItemDataSource(new BeanItem<>(binderDto));
                    LOGGER.debug("Inside Add Reserve Commit:");
                    binder.commit();
                } catch (Exception ex) {
                    LOGGER.error("Error in Add resetSelectionButtonLogic :", ex);
                }
            }

            @Override
            public void noMethod() {
                LOGGER.debug("Inside Add Reserve No Method:");
            }
        }.getConfirmationMessage("Confirmation", ARMMessages.getResetMessageID001());
    }

    /**
     * Value change logic of company drop down.
     *
     * @param event
     */
    @UiHandler("companyDdlbRes")
    public void companyDdlbResChangeLogic(Property.ValueChangeEvent event) {
        LOGGER.debug(event.toString());
        if (companyDdlbRes.getValue() != null) {
            selection.setCompanyNo(getCompanyNo(binderDto.getCompanyDdlbRes()));
            List<AdjustmentReserveDTO> list = detailsTableContainer.getItemIds();
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setCompanyNo(selection.getCompanyNo());
            }
        } else {
            selection.setCompanyNo(null);
        }
    }

    /**
     * Value change logic of company drop down.
     *
     * @param event
     */
    @UiHandler("businessDdlbRes")
    public void businessUnitDdlbResChangeLogic(Property.ValueChangeEvent event) {
        LOGGER.debug(event.toString());
        if ((int) companyDdlbRes.getValue() == 0) {
            selection.setBusUnit(null);
            selection.setDivision(null);
        } else {
            selection.setDivision(getCompanyNo(binderDto.getBusinessDdlbRes()));
            selection.setBusUnit(businessDdlbRes.getItemCaption(binderDto.getBusinessDdlbRes()));
            List<AdjustmentReserveDTO> list = detailsTableContainer.getItemIds();
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setDivision(selection.getDivision());
                list.get(i).setBusinessUnit(businessDdlbRes.getItemCaption(businessDdlbRes.getValue()));
            }
        }
    }

    @UiHandler("deductionCategoryDdlbRes")
    public void valueChangeDeductionCategoryDdlbRes(final Property.ValueChangeEvent valueChangeEvent) {
        LOGGER.debug(valueChangeEvent.toString());
        isValueChangeAllowed = false;
        if ((int) deductionCategoryDdlbRes.getValue() != 0) {
            Map<Integer, HelperDTO> helper = HelperListUtil.getInstance().getIdHelperDTOMap();
            List<Object> resultsList = logic.getTypeValuesBasedOnCategory((int) deductionCategoryDdlbRes.getValue());
            deductionTypeDdlbRes.removeAllItems();
            deductionTypeDdlbRes.addItem(NumericConstants.ZERO);
            deductionTypeDdlbRes.setItemCaption(0, GlobalConstants.getSelectOne());
            for (Object object : resultsList) {
                if (object != null) {
                    deductionTypeDdlbRes.addItem((Integer) object);
                    deductionTypeDdlbRes.setItemCaption((Integer) object, (helper.get((Integer) object)).getDescription());
                }
            }
            deductionTypeDdlbRes.select(NumericConstants.ZERO);
            deductionProgramDdlbRes.removeAllItems();
            deductionProgramDdlbRes.addItem(NumericConstants.ZERO);
            deductionProgramDdlbRes.setItemCaption(0, GlobalConstants.getSelectOne());
            deductionProgramDdlbRes.select(NumericConstants.ZERO);
            isValueChangeAllowed = true;
        } else {
            CommonLogic.configureDropDownsForDeduction(deductionTypeDdlbRes, "getDeductionType");
        }
    }

    @UiHandler("deductionTypeDdlbRes")
    public void valueChangeDeductionTypeDdlbRes(final Property.ValueChangeEvent addValueChangeevent) {
        LOGGER.debug(addValueChangeevent.toString());
        if (isValueChangeAllowed && (int) deductionTypeDdlbRes.getValue() != 0) {
            if (deductionTypeDdlbRes.getValue() != null) {
                Map<Integer, HelperDTO> helper = HelperListUtil.getInstance().getIdHelperDTOMap();
                List<Object> resList = logic.getTypeValuesBasedOnType((int) deductionCategoryDdlbRes.getValue(), (int) deductionTypeDdlbRes.getValue());// changed (int) companyDdlbRes.getValue() and (int) businessDdlbRes.getValue() to 0 for GAL-5535
                deductionProgramDdlbRes.removeAllItems();
                deductionProgramDdlbRes.addItem(NumericConstants.ZERO);
                deductionProgramDdlbRes.setItemCaption(0, GlobalConstants.getSelectOne());
                for (Object ob : resList) {
                    if (ob != null) {
                        deductionProgramDdlbRes.addItem((Integer) ob);
                        deductionProgramDdlbRes.setItemCaption((Integer) ob, (helper.get((Integer) ob)).getDescription());
                    }
                }
            }
            deductionProgramDdlbRes.select(0);
        } else {
            CommonLogic.configureDropDownsForDeduction(deductionProgramDdlbRes, "getDeductionProgram");
        }
    }

    @Override
    protected void loadTablefirstTime() {
        LOGGER.debug("loadTablefirstTime Method:");
    }

    @Override
    protected void getMasterSids() {
        LOGGER.debug("getMasterSids Method:");

    }

    @Override
    public void configureTabAddLineLogic() {
        try {
            binder.commit();
            if (!isFirst) {
                isFirst = true;
                selection.setCompanyNo(getCompanyNo(binderDto.getCompanyDdlbRes()));
                selection.setDivision(getCompanyNo(binderDto.getBusinessDdlbRes()));
                selection.setBusUnit(businessDdlbRes.getItemCaption(binderDto.getBusinessDdlbRes()));
            }
            if (saveToMaster()) {
                if (configurationTypeOpgRes.getValue().equals(ARMConstants.getReserveDetails())) {
                    selection.setMasterSID(selection.getReserveMasterSid());
                } else {
                    selection.setMasterSID(selection.getGtnDetailsMasterSid());
                }
                logic.addLineLogic(selection);

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
        Validation addLinevalidation = new ValidationAddRemoveLine(selection, true);
        if (!addLinevalidation.doValidate()) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, addLinevalidation.validationMessage());
            return;
        }
        adjustmentSummaryConfigLogic.addLineLogic(selection);
        adjustmentSummaryTableLogic.loadSetData(true, selection);
    }

    @Override
    protected void balanceSummaryAddLineLogic() {
        Validation balSumAddLinevalidation = new ValidationAddRemoveLine(selection, true);
        if (!balSumAddLinevalidation.doValidate()) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, balSumAddLinevalidation.validationMessage());
            return;
        }
        balanceSummaryLogic.addLineLogic(selection);
        balSummaryConfigurationTableLogic.loadSetData(true, selection);
        LOGGER.debug("balanceSummaryAddLineLogic Method:");
    }

    /**
     *
     * Method to Reset the line to as it was
     *
     */
    @Override
    public void resetConfigureTabLine() {
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
            logic.resetDBRecord(selection);
            detailsTableLogic.loadsetData(false, selection);
        }
    }

    /**
     * Adjustment summary reset line logic
     *
     */
    @Override
    public void resetAdjustmentSummaryLine() {
        try {
            selection.setResetLine(true);
            if (selection.isIsSaved()) {
                adjustmentSummaryConfigLogic.deleteTempTableRecords(selection);
                adjustmentSummaryConfigLogic.insertAdjSummaryToTempTableFromMainTable(selection);
                adjustmentSummaryTableLogic.loadSetData(true, selection);
                if (adjustmentSummaryTableLogic.getCount() == 0) {
                    methodologyDdlb.setValue(0);
                }
            } else {
                adjustmentSummaryConfigLogic.deleteTempTableRecords(selection);
                methodologyDdlb.setValue(0);
                adjustmentSummaryTableLogic.loadSetData(false, selection);
            }
            selection.setResetLine(false);
            List list = adjustmentSummaryConfigLogic.isAllCheckBoxesAreChecked(selection);
            adjustmentSummaryTable.setColumnCheckBox(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), true, list.size() != 1 ? false : "true".equals(String.valueOf(list.get(0))));
        } catch (Exception ex) {
            LOGGER.error("Error in resetAdjustmentSummaryLine :", ex);
        }

    }

    @Override
    protected void resetBalanceSummaryLine() {
        LOGGER.debug("resetBalanceSummaryLine Method:");
        try {
            selection.setResetLine(true);
            if (selection.isIsSaved()) {
                balanceSummaryLogic.deleteTempTableRecords(selection);
                balanceSummaryLogic.insertBalanceSummaryToTempTableFromMainTable(selection);
                balSummaryConfigurationTableLogic.loadSetData(true, selection);
                if (balSummaryConfigurationTableLogic.getCount() == 0) {
                    reportTypeDdlb.setValue(0);
                }
            } else {
                balanceSummaryLogic.deleteTempTableRecords(selection);
                reportTypeDdlb.setValue(0);
                balSummaryConfigurationTableLogic.loadSetData(false, selection);
            }
            selection.setResetLine(false);
            List list = adjustmentSummaryConfigLogic.isAllCheckBoxesAreChecked(selection);
            balanceSummaryTable.setColumnCheckBox(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), true, list.size() != 1 ? false : "true".equals(String.valueOf(list.get(0))));
        } catch (Exception ex) {
            LOGGER.error("Error in resetBalanceSummaryLine :", ex);
        }
    }
}
