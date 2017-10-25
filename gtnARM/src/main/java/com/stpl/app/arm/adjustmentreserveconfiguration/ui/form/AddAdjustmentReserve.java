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
public class AddAdjustmentReserve extends AbstractReserve {

    boolean isFirst;
    boolean isValueChangeAllowed = true;

    public AddAdjustmentReserve(SessionDTO sessionDTO, ReserveSelection resSelection) {
        super("Adjustment & Reserve Configuration Details", sessionDTO,resSelection);
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
        selection.setIsSaved(Boolean.FALSE);
    }

    @Override
    protected void addLineBtnLogic() {
        try {
            binder.commit();
            if (!isFirst) {
                isFirst = Boolean.TRUE;
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
            LOGGER.error(ex);
        }
    }

    @Override
    protected boolean saveToMaster() {
        boolean binderModified = binder.isModified();
        if (binderModified) {
            try {
                binder.commit();
            } catch (FieldGroup.CommitException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        if (logic.combinationIsSelected(binderDto)) {
            if (logic.isDuplicateCompany(binderDto) && !selection.isIsSaved()) {
                AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getSaveMessageID008()); // Changed as per GAL-5879
                return Boolean.FALSE;
            }
            if (configurationTypeOpgRes.getValue().equals(ARMConstants.getReserveDetails())) {
                selection.setIsGTNDetails(Boolean.FALSE);
                if (selection.getReserveMasterSid() == 0) {
                    int id = logic.addLineForMaster(selection, 0);
                    if (id == 0) {
                        AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getSaveMessageID006());
                        return Boolean.FALSE;
                    } else {
                        selection.setReserveMasterSid(id);
                        selection.setMasterSID(selection.getReserveMasterSid());
                        if (selection.getGtnDetailsMasterSid() == 0) {
                            selection.setGtnDetailsMasterSid(logic.addLineForMaster(selection, 1));
                        }
                        return Boolean.TRUE;
                    }
                } else if ((binderModified) && (!logic.updateMasterTable(selection, binderDto))) {
                    AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getSaveMessageID006());
                    return Boolean.FALSE;
                }
                return Boolean.TRUE;
            } else {
                selection.setIsGTNDetails(Boolean.TRUE);
                if (selection.getGtnDetailsMasterSid() == 0) {
                    int id = logic.addLineForMaster(selection, 1);
                    if (id == 0) {
                        AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getSaveMessageID006());
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
                    AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getSaveMessageID006());
                    return Boolean.FALSE;
                }
                return Boolean.TRUE;
            }
        } else {
            AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getPropertyMessage001());
            return false;
        }
    }

    /**
     * Reset logic for selection criteria.
     *
     * @param event
     */
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

    /**
     * Value change logic of company drop down.
     *
     * @param event
     */
    @UiHandler("companyDdlbRes")
    public void companyDdlbResChangeLogic(Property.ValueChangeEvent event) {
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
    public void valueChangeDeductionCategoryDdlbRes(final Property.ValueChangeEvent event) {
        isValueChangeAllowed = Boolean.FALSE;
        Map<Integer, HelperDTO> idhelper = HelperListUtil.getInstance().getIdHelperDTOMap();
        List<Object> list = logic.getTypeValuesBasedOnCategory((int) deductionCategoryDdlbRes.getValue());
        deductionTypeDdlbRes.removeAllItems();
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
                List<Object> list = logic.getTypeValuesBasedOnType((int) deductionCategoryDdlbRes.getValue(), (int) deductionTypeDdlbRes.getValue());// changed (int) companyDdlbRes.getValue() and (int) businessDdlbRes.getValue() to 0 for GAL-5535
                deductionProgramDdlbRes.removeAllItems();
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

    @Override
    protected void loadTablefirstTime() {
    }

    @Override
    protected void getMasterSids() {
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
            logic.resetDBRecord(selection);
            detailsTableLogic.loadsetData(Boolean.FALSE, selection);
        }
    }
}
