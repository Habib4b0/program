/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.accountconfiguration.form;

import com.stpl.app.arm.accountconfiguration.dto.AccountConfigDTO;
import com.stpl.app.arm.accountconfiguration.dto.AccountConfigSelection;
import static com.stpl.app.arm.accountconfiguration.form.AbstractAccountConfig.GTNLOGGER;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

/**
 *
 * @author Srithar.Raju
 */
public class ViewAccountConfig extends AbstractAccountConfig {

    private AccountConfigDTO selectedDto;

    public ViewAccountConfig(String caption, SessionDTO sessionDTO, AccountConfigSelection selection, AccountConfigDTO selectedDto) {
        super(caption, sessionDTO, selection);
        this.selectedDto = selectedDto;
        configureFields();
        securityForButtons();
        securityForFields();
    }

    @Override
    protected void loadSelection() {
        selection.setSession(sessionDTO);
        massValue.setEnabled(false);
        massfieldDdlb.setEnabled(false);
        populateBtn.setEnabled(false);
        resetLineBtn.setEnabled(false);
        addLineBtn.setEnabled(false);
        removeLineBtn.setEnabled(false);
        copyLineBtn.setEnabled(false);
        saveBtn.setEnabled(false);
        selection.setViewMode(true);

    }

    @Override
    protected void addLineBtnLogic() {
        GTNLOGGER.debug("Inside AddLineBtnLogic Method");
    }

    @Override
    protected boolean saveToMaster() {
        return true;
    }

    @Override
    protected void loadTablefirstTime() {
        resultsTable.setFilterBarVisible(false);
        resultsTable.setSortEnabled(false);
        if (selection.isCurrentView()) {
            detailsTableLogic.loadsetData(false, selection);
            resultsTable.addItem(selectedDto);
        } else {
            detailsTableLogic.loadsetData(true, selection);
        }
        resultsTable.setEditable(false);
        resultsTable.setFilterBarVisible(false);
        resultsTable.setSelectable(false);
    }

    @Override
    protected String[] getMassUpdateValues() {
        return new String[0];
    }

    @Override
    protected Object[] getMassUpdateProperties() {
        return new String[0];
    }

    @Override
    protected Object[] getVisibleColumns() {
        return ARMUtils.getAccountConfigViewmodeColumns();
    }

    @Override
    protected String[] getColumnHeaders() {
        return ARMUtils.getAccountConfigViewmodeHeaders();
    }

    @Override
    public int hashCode() {
        GTNLOGGER.debug("Enters the AccountConfig Hashcode");
        int viewAccounthash = 7;
        viewAccounthash = 71 * viewAccounthash + Objects.hashCode(this.selectedDto);
        viewAccounthash = 71 * viewAccounthash + Objects.hashCode(this.commonSecurity);
        return viewAccounthash;
    }

    @Override
    public boolean equals(Object viewAccountObj) {
        if (this == viewAccountObj) {
            return true;
        }
        if (viewAccountObj == null) {
            return false;
        }
        if (getClass() != viewAccountObj.getClass()) {
            return false;
        }
        final ViewAccountConfig other = (ViewAccountConfig) viewAccountObj;
        if (!Objects.equals(this.selectedDto, other.selectedDto)) {
            return false;
        }
        return Objects.equals(this.commonSecurity, other.commonSecurity);
    }

    private void writeObject(ObjectOutputStream viewAccountout) throws IOException {
        viewAccountout.defaultWriteObject();
    }

    private void readObject(ObjectInputStream viewAccountIn) throws IOException, ClassNotFoundException {
        viewAccountIn.defaultReadObject();
    }
}
