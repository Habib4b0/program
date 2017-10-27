/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.accountconfiguration.form;

import com.stpl.app.arm.accountconfiguration.dto.AccountConfigSelection;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.ui.HorizontalLayout;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Srithar.Raju
 */
public class EditAccountConfig extends AbstractAccountConfig {

    CommonSecurityLogic commonSecurity = new CommonSecurityLogic();

    public EditAccountConfig(String caption, SessionDTO sessionDTO, AccountConfigSelection selection) throws Exception {
        super(caption, sessionDTO, selection);
        configureFields();
        securityForButtons();
        securityForFields();
    }

    @Override
    protected void loadSelection() {
        selection.setSession(sessionDTO);
        logic.insertToMainTable(selection, "insertDataOnEditClick");
        selection.setSaved(true);
    }

    @Override
    protected void addLineBtnLogic() {
        logic.addLineForEditMode(selection);
        detailsTableLogic.loadsetData(true, selection);
    }

    @Override
    protected boolean saveToMaster() {
        return true;
    }

    @Override
    protected void loadTablefirstTime() {
        resultsTable.setFilterBarVisible(true);
        detailsTableLogic.loadsetData(true, selection);
    }

    @Override
    protected String[] getMassUpdateValues() {
        return Arrays.copyOfRange(ARMUtils.getAccountConfigMassUpdateValues(), 2, ARMUtils.getAccountConfigMassUpdateValues().length);
    }

    @Override
    protected Object[] getMassUpdateProperties() {
        return Arrays.copyOfRange(ARMUtils.getAccountConfigComboboxProperties(), 2, ARMUtils.getAccountConfigComboboxProperties().length);
    }

    @Override
    protected Object[] getVisibleColumns() {
        return ARMUtils.getAccountConfigEditmodeColumns();
    }

    @Override
    protected String[] getColumnHeaders() {
        return ARMUtils.getAccountConfigEditmodeHeaders();
    }

    private void securityForButtons() throws PortalException, SystemException {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(sessionDTO.getUserId());
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, CommonConstant.ACCOUNT_CONFIGURATION + "," + "Landing screen");
        if (functionHM.get("addLineBtn") != null && !(functionHM.get("addLineBtn")).isFunctionFlag()) {
            getAddLineBtn().setVisible(false);
        } else {
            getAddLineBtn().setVisible(true);
        }
        if (functionHM.get("resetLineBtn") != null && !(functionHM.get("resetLineBtn")).isFunctionFlag()) {
            getResetLineBtn().setVisible(false);
        } else {
            getResetLineBtn().setVisible(true);
        }
        if (functionHM.get("removeLineBtn") != null && !(functionHM.get("removeLineBtn")).isFunctionFlag()) {
            getRemoveLineBtn().setVisible(false);
        } else {
            getRemoveLineBtn().setVisible(true);
        }
        if (functionHM.get("copyLineBtn") != null && !(functionHM.get("copyLineBtn")).isFunctionFlag()) {
            getCopyLineBtn().setVisible(false);
        } else {
            getCopyLineBtn().setVisible(true);
        }
        if (functionHM.get("saveBtn") != null && !(functionHM.get("saveBtn")).isFunctionFlag()) {
            getSaveBtn().setVisible(false);
        } else {
            getSaveBtn().setVisible(true);
        }
        if (functionHM.get("closeBtn") != null && !(functionHM.get("closeBtn")).isFunctionFlag()) {
            getCloseBtn().setVisible(false);
        } else {
            getCloseBtn().setVisible(true);
        }
        if (functionHM.get("exportBtn") != null && !(functionHM.get("exportBtn")).isFunctionFlag()) {
            getExportBtn().setVisible(false);
        } else {
            getExportBtn().setVisible(true);
        }
        if (functionHM.get("populateBtn") != null && !(functionHM.get("populateBtn")).isFunctionFlag()) {
            getPopulateBtn().setVisible(false);
        } else {
            getPopulateBtn().setVisible(true);
        }
        if (functionHM.get("viewOpg") != null && !(functionHM.get("viewOpg")).isFunctionFlag()) {
            getViewOpg().setVisible(false);
            getLabelView().setVisible(false);
        } else {
            getViewOpg().setVisible(true);
            getLabelView().setVisible(true);

        }
    }

    private void securityForFields() throws PortalException, SystemException {

        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(sessionDTO.getUserId());
        Map<String, AppPermission> functionHMforFields = stplSecurity.getBusinessFieldPermission(userId, CommonConstant.ACCOUNT_CONFIGURATION + "," + "Landing Screen");
        configureFieldPermission(functionHMforFields);
        if (functionHMforFields.get("massfieldDdlb") != null && !(functionHMforFields.get("massfieldDdlb")).isFunctionFlag()) {
            getMassfieldDdlb().setVisible(false);
            getLabelField().setVisible(false);

        } else {
            getMassfieldDdlb().setVisible(true);
            getLabelField().setVisible(true);

        }
        if (functionHMforFields.get("massValue") != null && !(functionHMforFields.get("massValue")).isFunctionFlag()) {
            getMassValue().setVisible(false);
            getLabelValue().setVisible(false);
        } else {
            getMassValue().setVisible(true);
            getLabelValue().setVisible(true);

        }

    }

    private void configureFieldPermission(Map<String, AppPermission> functionHMforFields) {
        LOGGER.debug("Entering configurePermission");
        try {
            List<Object> resultList = logic.getFieldsForSecurity(CommonConstant.ACCOUNT_CONFIGURATION, "Landing Screen");
            HorizontalLayout l = getHorizontalDetailsLayout();
            commonSecurity.removeComponentOnPermission(resultList, l, functionHMforFields, CommonSecurityLogic.EDIT);
        } catch (Exception ex) {
            LOGGER.error("Error in configureFieldPermission :"+ex);
        }
        LOGGER.debug("Ending configurePermission");

    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
