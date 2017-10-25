/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.ui.form;

import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.adjustmentreserveconfiguration.ui.abstractreserveform.AbstractReserve;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.ReserveSelection;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import static com.stpl.ifs.ui.util.AbstractNotificationUtils.LOGGER;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import java.util.List;
import java.util.Map;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Srithar.Raju
 */
public class CopyAdjustmentReserve extends AbstractReserve {

    AdjustmentReserveDTO selectedDto;
    boolean isFirst;
    boolean isValueChangeAllowed = true;

    public CopyAdjustmentReserve(SessionDTO sessionDTO, AdjustmentReserveDTO dto,ReserveSelection resSelection) {
        super("Adjustment & Reserve Configuration Details", sessionDTO,resSelection);
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
        selection.setSession(sessionDTO);
        selection.setSearchBinderDTO(selectedDto);
        selection.setWindowBinderDTO(binderDto);
        selection.setIsSaved(Boolean.FALSE);
        getMasterSids();
        logic.insertToTempTable(selection);
    }

    @Override
    protected void addLineBtnLogic() {
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
            LOGGER.error(ex);
        }
    }

    @Override
    protected boolean saveToMaster() {
        boolean binderModified = binder.isModified();
        int rev_mas_id = selection.getReserveMasterSid();
        if (binderModified) {
            try {
                binder.commit();
            } catch (FieldGroup.CommitException ex) {
                LOGGER.error(ex);
            }
        }
        if (logic.combinationIsSelected(binderDto)) {
            if (logic.isDuplicateCompany(binderDto) && !selection.isIsSaved()) {
                AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getSaveMessageID002());
                return Boolean.FALSE;
            }
            if (!isFirst) {
                int id = logic.addLineForMaster(selection, 1);
                int id1 = logic.addLineForMaster(selection, 0);
                if (id == 0 && id1 == 0) {
                    AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getSaveMessageID006());
                    return Boolean.FALSE;
                } else {
                    selection.setReserveMasterSid(id1);
                    selection.setGtnDetailsMasterSid(id);
                    if (selection.isIsGTNDetails()) {
                        selection.setMasterSID(selection.getGtnDetailsMasterSid());
                    } else {
                        selection.setMasterSID(selection.getReserveMasterSid());
                    }
                }
                isFirst = Boolean.TRUE;
                logic.updateMasterSid(selectedDto, selection, rev_mas_id);
            } else if (binderModified) {
                int mas_id = selection.getMasterSID();
                selection.setMasterSID(selection.getGtnDetailsMasterSid());
                boolean isGTNSave = !logic.updateMasterTable(selection, binderDto);
                selection.setMasterSID(selection.getReserveMasterSid());
                if (!logic.updateMasterTable(selection, binderDto) || isGTNSave) {
                    selection.setMasterSID(mas_id);
                    AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getSaveMessageID006());
                    return Boolean.FALSE;
                }
                selection.setMasterSID(mas_id);
                selection.setCompanyNo(getCompanyNo(binderDto.getCompanyDdlbRes()));
                selection.setDivision(getCompanyNo(binderDto.getBusinessDdlbRes()));
                selection.setBusUnit(businessDdlbRes.getItemCaption(binderDto.getBusinessDdlbRes()));
            }
            return Boolean.TRUE;
        } else {
            AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getPropertyMessage001());
            return false;
        }
    }

    @Override
    protected void loadTablefirstTime() {
        LOGGER.debug("Inside Load Table Firsrt Time");
        detailsTableLogic.loadsetData(Boolean.TRUE, selection);
    }

    @Override
    protected void loadResetData() {
        if (selection.isIsSaved()) {
            if (configurationTypeOpgRes.getValue().equals(ARMConstants.getReserveDetails())) {
                selection.setMasterSID(selection.getReserveMasterSid());
            } else {
                selection.setMasterSID(selection.getGtnDetailsMasterSid());
            }
            logic.resetDBRecord(selection);
            logic.insertToTempTable(selection);
            detailsTableLogic.loadsetData(Boolean.TRUE, selection);
        } else {
            ReserveSelection tempSelection = null;
            try {
                tempSelection = (ReserveSelection) this.selection.clone();
            } catch (CloneNotSupportedException ex) {
                LOGGER.error(ex);
            }
            tempSelection.setReserveMasterSid(logic.getMasterSids(this.selection));
            tempSelection.setGtnDetailsMasterSid(selectedDto.getSearchMasterSid());
            tempSelection.setMasterSID(selectedDto.getSearchMasterSid());
            logic.resetDBRecord(this.selection);
            logic.insertToTempTable(tempSelection, selection);
            detailsTableLogic.loadsetData(Boolean.TRUE, tempSelection);
        }
    }

    @UiHandler("deductionCategoryDdlbRes")
    public void valueChangeDeductionCategoryDdlbRes(final Property.ValueChangeEvent event) {
        isValueChangeAllowed = Boolean.FALSE;
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
        isValueChangeAllowed = Boolean.TRUE;
    }

    @UiHandler("deductionTypeDdlbRes")
    public void valueChangeDeductionTypeDdlbRes(final Property.ValueChangeEvent event) {
        if (isValueChangeAllowed) {
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
        }
    }

    @UiHandler("resetBtnRes")
    public void resetSelectionButtonLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    binderDto = new AdjustmentReserveDTO();
                    binder.setItemDataSource(new BeanItem<>(binderDto));
                    binder.commit();
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage("Confirmation", ARMMessages.getResetMessageID001());
    }
}
