/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.accountconfiguration.form;

import com.stpl.app.arm.accountconfiguration.dto.AccountConfigDTO;
import com.stpl.app.arm.accountconfiguration.dto.AccountConfigSelection;
import static com.stpl.app.arm.accountconfiguration.form.AbstractAccountConfig.LOGGER;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.ui.HorizontalLayout;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Srithar.Raju
 */
public class ViewAccountConfig extends AbstractAccountConfig {

    AccountConfigDTO selectedDto;
    AbstractAccountConfig cl;

    public ViewAccountConfig(String caption, SessionDTO sessionDTO, AccountConfigSelection selection, AccountConfigDTO selectedDto) throws Exception {
        super(caption, sessionDTO, selection);
        this.selectedDto = selectedDto;
        configureFields();
        securityForButtons();
        securityForFields();
    }
    CommonSecurityLogic commonSecurity = new CommonSecurityLogic();

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
    }

    @Override
    protected boolean saveToMaster() {
        return true;
    }

    @Override
    protected void loadTablefirstTime() {
        resultsTable.setFilterBarVisible(Boolean.FALSE);
        resultsTable.setSortEnabled(Boolean.FALSE);
        if (selection.isCurrentView()) {
            detailsTableLogic.loadsetData(Boolean.FALSE, selection);
            resultsTable.addItem(selectedDto);
        } else {
            detailsTableLogic.loadsetData(Boolean.TRUE, selection);
        }
        resultsTable.setEditable(Boolean.FALSE);
        resultsTable.setFilterBarVisible(Boolean.FALSE);
        resultsTable.setSelectable(Boolean.FALSE);
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
        return ARMUtils.ACCOUNT_CONFIG_VIEWMODE_COLUMNS;
    }

    @Override
    protected String[] getColumnHeaders() {
        return ARMUtils.ACCOUNT_CONFIG_VIEWMODE_HEADERS;
    }

    private void securityForButtons() throws Exception {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(sessionDTO.getUserId());
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Account Configuration" + "," + "Landing screen");
            if (functionHM.get("addLineBtn") != null && !((AppPermission) functionHM.get("addLineBtn")).isFunctionFlag()) {
                getAddLineBtn().setVisible(false);
            } else {
                getAddLineBtn().setVisible(true);
            }
            if (functionHM.get("resetLineBtn") != null && !((AppPermission) functionHM.get("resetLineBtn")).isFunctionFlag()) {
                getResetLineBtn().setVisible(false);
            } else {
                getResetLineBtn().setVisible(true);
            }
            if (functionHM.get("removeLineBtn") != null && !((AppPermission) functionHM.get("removeLineBtn")).isFunctionFlag()) {
                getRemoveLineBtn().setVisible(false);
            } else {
                getRemoveLineBtn().setVisible(true);
            }
            if (functionHM.get("copyLineBtn") != null && !((AppPermission) functionHM.get("copyLineBtn")).isFunctionFlag()) {
                getCopyLineBtn().setVisible(false);
            } else {
                getCopyLineBtn().setVisible(true);
            }
            if (functionHM.get("saveBtn") != null && !((AppPermission) functionHM.get("saveBtn")).isFunctionFlag()) {
                getSaveBtn().setVisible(false);
            } else {
                getSaveBtn().setVisible(true);
            }
            if (functionHM.get("closeBtn") != null && !((AppPermission) functionHM.get("closeBtn")).isFunctionFlag()) {
                getCloseBtn().setVisible(false);
            } else {
                getCloseBtn().setVisible(true);
            }
            if (functionHM.get("exportBtn") != null && !((AppPermission) functionHM.get("exportBtn")).isFunctionFlag()) {
                getExportBtn().setVisible(false);
            } else {
                getExportBtn().setVisible(true);
            }
            if (functionHM.get("populateBtn") != null && !((AppPermission) functionHM.get("populateBtn")).isFunctionFlag()) {
                getPopulateBtn().setVisible(false);
            } else {
                getPopulateBtn().setVisible(true);
            }
            if (functionHM.get("viewOpg") != null && !((AppPermission) functionHM.get("viewOpg")).isFunctionFlag()) {
                getViewOpg().setVisible(false);
                getLabelView().setVisible(false);
            } else {
                getViewOpg().setVisible(true);
                getLabelView().setVisible(true);

            }
        } 

    

    private void securityForFields() throws Exception {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(sessionDTO.getUserId());
            Map<String, AppPermission> functionHMforFields = stplSecurity.getBusinessFieldPermission(userId, "Account Configuration" + "," + "Landing Screen");
            configureFieldPermission(functionHMforFields);
            if (functionHMforFields.get("massfieldDdlb") != null && !((AppPermission) functionHMforFields.get("massfieldDdlb")).isFunctionFlag()) {
                getMassfieldDdlb().setVisible(false);
                getLabelField().setVisible(false);

            } else {
                getMassfieldDdlb().setVisible(true);
                getLabelField().setVisible(true);

            }
            if (functionHMforFields.get("massValue") != null && !((AppPermission) functionHMforFields.get("massValue")).isFunctionFlag()) {
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
            List<Object> resultList = logic.getFieldsForSecurity("Account Configuration", "Landing Screen");
            HorizontalLayout l = getHorizontalDetailsLayout();
            commonSecurity.removeComponentOnPermission(resultList, l, functionHMforFields, CommonSecurityLogic.VIEW);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending configurePermission");

    }

    private void securityForTables() throws PortalException, SystemException {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(sessionDTO.getUserId());
        final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, "Account Configuration" + "," + "Landing Screen", false);
        List<Object> resultList = logic.getFieldsForSecurity("Account Configuration", "Landing Screen");
        Object[] obj = ARMUtils.ACCOUNT_CONFIG_SEARCH_COLUMNS;
        TableResultCustom tableResultCustom = commonSecurity.getTableColumnsPermission(resultList, obj, fieldIfpHM, CommonSecurityLogic.ADD);
        if (tableResultCustom.getObjResult().length > 0) {
            resultsTable.markAsDirty();
            resultsTable.setContainerDataSource(detailsTableContainer);
            resultsTable.setVisibleColumns(tableResultCustom.getObjResult());
            resultsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        } else {
            resultsTableLayout.setVisible(false);
        }
    }
}
