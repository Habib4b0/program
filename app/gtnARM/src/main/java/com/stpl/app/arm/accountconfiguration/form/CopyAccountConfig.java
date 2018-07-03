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
import com.vaadin.v7.ui.HorizontalLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author
 */
public class CopyAccountConfig extends AbstractAccountConfig {

    public CopyAccountConfig(String caption, SessionDTO sessionDTO, AccountConfigSelection selection) {
        super(caption, sessionDTO, selection);
        configureFields();
        securityForButtons();
        securityForFields();

    }
    private CommonSecurityLogic commonSecurity = new CommonSecurityLogic();

    @Override
    protected void loadSelection() {
        List inputList = new ArrayList<>();
        inputList.add(selection.getTempTableName());
        inputList.add(selection.getSession().getUserId());
        inputList.add(selection.getSession().getUserId());
        logic.insertToMainTable(selection, "insertDataOnCopyClick", inputList);
    }

    @Override
    protected void addLineBtnLogic() {
        logic.addLine(selection);
        detailsTableLogic.loadsetData(true, selection);
    }

    @Override
    protected boolean saveToMaster() {
        return true;
    }

    @Override
    protected void loadTablefirstTime() {
        detailsTableLogic.loadsetData(true, selection);
        resultsTable.setFilterBarVisible(true);
    }

    @Override
    protected String[] getMassUpdateValues() {
        return ARMUtils.getAccountConfigMassUpdateValues();
    }

    @Override
    protected Object[] getMassUpdateProperties() {
        return ARMUtils.getAccountConfigComboboxProperties();
    }

    @Override
    protected Object[] getVisibleColumns() {
        return ARMUtils.getAccountConfigAddmodeColumns();
    }

    @Override
    protected String[] getColumnHeaders() {
        return ARMUtils.getAccountConfigAddmodeHeaders();
    }

    private void securityForButtons() {
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
        securityForButton(functionHM);

    }

    private void securityForFields() {
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
        GTNLOGGER.debug("Entering configurePermission");
        try {
            List<Object> resultList = logic.getFieldsForSecurity(CommonConstant.ACCOUNT_CONFIGURATION, "Landing Screen");
            HorizontalLayout l = getHorizontalDetailsLayout();
            commonSecurity.removeComponentOnPermission(resultList, l, functionHMforFields, CommonSecurityLogic.ADD);
        } catch (Exception ex) {
            GTNLOGGER.error("Error in configureFieldPermission :" + ex);
        }
        GTNLOGGER.debug("Ending configurePermission");

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.commonSecurity);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CopyAccountConfig other = (CopyAccountConfig) obj;
        return Objects.equals(this.commonSecurity, other.commonSecurity);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    private void securityForButton(Map<String, AppPermission> functionHM) {
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

}
