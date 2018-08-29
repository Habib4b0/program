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
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Srithar.Raju
 */
public class AddAccountConfig extends AbstractAccountConfig {

    private AccountConfigSelection addSelection;
    private static final Logger ADDLOGGER = LoggerFactory.getLogger(AddAccountConfig.class);

    public AddAccountConfig(String caption, SessionDTO sessionDTO, AccountConfigSelection selection) {
        super(caption, sessionDTO, selection);
        this.addSelection = selection;
        configureFields();
        securityForButtons();
        securityForFields();
    }

    @Override
    protected void loadSelection() {
        GTNLOGGER.debug("Inside loadSelection Method");
    }

    @Override
    protected void addLineBtnLogic() {
        logic.addLine(addSelection);
        detailsTableLogic.loadsetData(true, addSelection);
    }

    @Override
    protected boolean saveToMaster() {
        return true;
    }

    @Override
    protected void loadTablefirstTime() {
        detailsTableLogic.loadsetData(true, addSelection);
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

    @Override
    public int hashCode() {
        ADDLOGGER.debug("Enters the Add Account Config Hash Code");
        int addAccConfigHash = 5;
        addAccConfigHash = 11 * addAccConfigHash + Objects.hashCode(this.commonSecurity);
        return addAccConfigHash;
    }

    @Override
    public boolean equals(Object addAccObject) {
        if (this == addAccObject) {
            return true;
        }
        if (addAccObject == null) {
            return false;
        }
        if (getClass() != addAccObject.getClass()) {
            return false;
        }
        final AddAccountConfig other = (AddAccountConfig) addAccObject;
        return Objects.equals(this.commonSecurity, other.commonSecurity);
    }

    private void writeObject(ObjectOutputStream addAccountOut) throws IOException {
        addAccountOut.defaultWriteObject();
    }

    private void readObject(ObjectInputStream addAccountIn) throws IOException, ClassNotFoundException {
        addAccountIn.defaultReadObject();
    }

}
