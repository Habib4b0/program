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
import java.util.List;
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
        return ARMUtils.getAccountConfigCopyModeComboboxProperties();
    }

    @Override
    protected Object[] getVisibleColumns() {
        return ARMUtils.getAccountConfigCopymodeColumns();
    }

    @Override
    protected String[] getColumnHeaders() {
        return ARMUtils.getAccountConfigCopymodeHeaders();
    }

    @Override
    public int hashCode() {
        int copyAcchash = 7;
        copyAcchash = 53 * copyAcchash + Objects.hashCode(this.commonSecurity);
        return copyAcchash;
    }

    @Override
    public boolean equals(Object copyAccobj) {
        if (this == copyAccobj) {
            return true;
        }
        if (copyAccobj == null) {
            return false;
        }
        if (getClass() != copyAccobj.getClass()) {
            return false;
        }
        final CopyAccountConfig other = (CopyAccountConfig) copyAccobj;
        return Objects.equals(this.commonSecurity, other.commonSecurity);
    }

    private void writeObject(ObjectOutputStream copyAccout) throws IOException {
        copyAccout.defaultWriteObject();
    }

    private void readObject(ObjectInputStream copyAccin) throws IOException, ClassNotFoundException {
        copyAccin.defaultReadObject();
    }

}
