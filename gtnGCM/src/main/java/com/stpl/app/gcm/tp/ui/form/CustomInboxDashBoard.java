/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.ui.form;

import com.stpl.app.gtnworkflow.dto.InboxDashboardDTO;
import com.stpl.app.gtnworkflow.logic.WorkflowLogic;
import com.stpl.app.gtnworkflow.ui.form.InboxDashBoard;
import com.stpl.app.gtnworkflow.util.CommonUtils;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.ui.Button;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.JavaScriptFunction;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author Maheshj
 */
public class CustomInboxDashBoard extends InboxDashBoard {

    String projectionId = StringUtils.EMPTY;
    public Button closeBtn = new Button(" CLOSE ");
    WorkFlowLookup workFlowLookup = null;
    private final WorkflowLogic searchLogic = new WorkflowLogic();
    List<InboxDashboardDTO> inboxSearchResults = new ArrayList<InboxDashboardDTO>();
    private static final Logger LOGGER = Logger.getLogger(CustomInboxDashBoard.class);

    public CustomInboxDashBoard(String projectionId, WorkFlowLookup workFlowLookup) {
        super();
        this.projectionId = projectionId;
        this.workFlowLookup = workFlowLookup;
        configureFields();
        workflowInboxSearch();

    }

    /**
     *
     * @param binder
     * @return
     */
    public void workflowInboxSearch() {
        inboxDashboardBean.removeAllItems();
        StringBuilder query = new StringBuilder();
        String businessProcessBinder = inboxDashboardDTO.getBusinessProcess();
        query.append(CustomSQLUtil.get("gcm_wi.inbox_search"));
        if ("Contract Management".equals(businessProcessBinder)) {
            String sql = query.toString().replace("@WFNAME", "CM.CONTRACT_NAME");
            query = new StringBuilder(sql.toString().replace("WM.PROJECTION_MASTER_SID", "WM.CONTRACT_MASTER_SID"));
            query.append("JOIN CONTRACT_MASTER CM on WM.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID ");
        } else {
            String sql = query.toString().replace("@WFNAME", "PM.PROJECTION_NAME");
            query = new StringBuilder(sql.toString().replace("WM.WORKFLOW_DESCRPTION", "PM.PROJECTION_DESCRIPTION"));
            query.append("JOIN PROJECTION_MASTER PM on WM.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID ");
        }
        query.append(" WHERE PM.PROJECTION_MASTER_SID = '").append(projectionId).append("'");
        query.append(" ORDER BY WM.CREATED_DATE DESC,WM.MODIFIED_DATE DESC ");
        String stringQuery = String.valueOf(query);
        LOGGER.debug("stringQuery" + stringQuery);
        List<String> columnsList = QueryUtil.getColumnNames(stringQuery);
        List resultList = searchLogic.executeSelectQuery(stringQuery);
        if (resultList != null && !resultList.isEmpty()) {

            inboxSearchResults = CommonUtils.getCustomizedWorkflowInboxResults(resultList, columnsList);

        }
        resultList = null;
        inboxDashboardBean.addAll(inboxSearchResults);
    }

    private void configureFields() {

        businessProcess.setEnabled(false);
        workflowId.setEnabled(false);
        creationDateRangeFrom.setEnabled(false);
        approvedDateRangeFrom.setEnabled(false);
        privateView.setEnabled(false);
        workflowName.setEnabled(false);
        creationDateRangeTo.setEnabled(false);
        approvedDateRangeTo.setEnabled(false);
        publicView.setEnabled(false);
        workflowDescription.setEnabled(false);
        createdBy.setEnabled(false);
        approvedBy.setEnabled(false);
        search.setEnabled(false);
        reset.setEnabled(false);
        saveProfile.setEnabled(false);
        JavaScript.getCurrent().execute("localStorage.setItem('" + inboxDashboardDTO.getBusinessProcess() + "', 'false');");
        JavaScript.getCurrent().addFunction("storageEventListener", new JavaScriptFunction() {

            @Override
            public void call(JSONArray arguments) throws JSONException {
                try {
                    if (arguments.getBoolean(1)) {
                        workflowInboxSearch();
                        JavaScript.getCurrent().execute("localStorage.setItem('" + arguments.getString(0) + "', 'false');");
                    }
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        });

        closeBtn.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                closeFunction();

            }
        });

        gridLayout.addComponent(closeBtn);

    }

    protected void afterApprove() {
        workFlowLookup.close();
    }

    //@Override
    protected void afterReject() {
        workFlowLookup.close();
    }

    //@Override
    protected void afterCancel() {
        workFlowLookup.close();
    }

    public void closeFunction() {

        MessageBox.showPlain(Icon.QUESTION, "Confirm Close", "Are you sure you want to close the Work Flow Inbox ?", new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equalsIgnoreCase(CommonUtils.YES)) {
                    workFlowLookup.close();
                }
            }

        }, ButtonId.YES, ButtonId.NO);

    }

}
