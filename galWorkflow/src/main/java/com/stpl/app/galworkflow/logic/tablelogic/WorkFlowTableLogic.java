/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galworkflow.logic.tablelogic;

import com.stpl.app.galworkflow.dto.InboxDashboardDTO;
import com.stpl.app.galworkflow.logic.WorkflowLogic;
import com.stpl.app.galworkflow.util.CommonUtils;
import com.stpl.ifs.util.QueryUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author manikandaprabu.g
 */
public class WorkFlowTableLogic extends PageTableLogic {

    private static final Logger LOGGER = Logger.getLogger(WorkFlowTableLogic.class);

    private final WorkflowLogic searchLogic = new WorkflowLogic();
    private boolean isEmpty;
    private InboxDashboardDTO inboxdto;
    Boolean firstFlag = false;

    @Override
    public int getCount() {
        int count = 0;
        try {
            if (firstFlag) {
                String query = searchLogic.getWorkflowSearchQuery(inboxdto, 0, 0, null, this.getFilters(), true);
                List resultList = searchLogic.executeSelectQuery(query);
                
                if (resultList != null && !resultList.isEmpty()) {
                    count = Integer.valueOf(String.valueOf(resultList.get(0)));
                } else {
                    LOGGER.info("Workflow inbox Table Count returned empty list");
                }
            }
            isEmpty = (count == 0);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List list = new ArrayList();
        try {
            String query = searchLogic.getWorkflowSearchQuery(inboxdto, start, offset, this.getSortByColumns(), this.getFilters(), false);
            List<String> columnsList = QueryUtil.getColumnNames(query);
            List resultList = searchLogic.executeSelectQuery(query);
            
            if (resultList != null && !resultList.isEmpty() && columnsList!=null && !columnsList.isEmpty()) {
                list = CommonUtils.getCustomizedWorkflowInboxResults(resultList, columnsList);
            } else {
                LOGGER.info("Workflow inbox Table load data returned empty list");
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        InboxDashboardDTO dto = (InboxDashboardDTO) object;
        ((BeanItemContainer<InboxDashboardDTO>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData(final InboxDashboardDTO dto, Boolean isFirst) {
        this.firstFlag = isFirst;
        this.clearAll();
        this.getFilters().clear();
        this.inboxdto = dto;
        this.setRequiredCount(true);
        this.setCurrentPage(1);

    }

    public boolean isResultsEmpty() {
        return isEmpty;
    }
}
