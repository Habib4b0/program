/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.accountconfiguration.form;

import com.stpl.app.arm.accountconfiguration.dto.AccountConfigSelection;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Srithar.Raju
 */
public class EditAccountConfig extends AbstractAccountConfig {

    public EditAccountConfig(String caption, SessionDTO sessionDTO, AccountConfigSelection selection) {
        super(caption, sessionDTO, selection);
        configureFields();
        securityForButtons();
        securityForFields();
    }

    @Override
    protected void loadSelection() {
        selection.setSession(sessionDTO);
        List inputList = new ArrayList<>();
        inputList.add(selection.getTempTableName());
        logic.insertToMainTable(selection, "insertDataOnEditClick", inputList);
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

    @Override
    public int hashCode() {
        GTNLOGGER.debug("Enters the Edit AccountConfig Hashcode");
        int editConfighash = 5;
        editConfighash = 47 * editConfighash + Objects.hashCode(this.commonSecurity);
        return editConfighash;
    }

    @Override
    public boolean equals(Object editConfigobj) {
        GTNLOGGER.debug("Enters the Edit AccountConfig Equals");
        if (this == editConfigobj) {
            return true;
        }
        if (editConfigobj == null) {
            return false;
        }
        if (getClass() != editConfigobj.getClass()) {
            return false;
        }
        final EditAccountConfig other = (EditAccountConfig) editConfigobj;
        return Objects.equals(this.commonSecurity, other.commonSecurity);
    }

    private void writeObject(ObjectOutputStream editConfigout) throws IOException {
        GTNLOGGER.debug("Enters the Edit AccountConfig WriteObject");
        editConfigout.defaultWriteObject();
    }

    private void readObject(ObjectInputStream editConfigin) throws IOException, ClassNotFoundException {
        GTNLOGGER.debug("Enters the Edit AccountConfig ReadOject");
        editConfigin.defaultReadObject();
    }

}
