/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnworkflow.logic.tablelogic;

import com.stpl.app.gtnworkflow.dto.InboxDashboardDTO;
import com.stpl.app.gtnworkflow.logic.WorkflowLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author rohitvignesh.s
 */
public class ViewLookuptablelogic extends PageTableLogic {

    private final WorkflowLogic searchLogic = new WorkflowLogic();
    private static final Logger LOGGER = Logger.getLogger(ViewLookuptablelogic.class);
    private boolean isFirstLoad = false;
    private boolean isResultsEmpty;
    private boolean isReset = false;
    InboxDashboardDTO inboxDashboardDTO;

    @Override
    public int getCount() {
        LOGGER.debug("Entering workflowtablelogic WorkflowinboxCount");
        int count = 0;
        try {
            if (isFirstLoad) {
                count = (Integer) searchLogic.ViewResultsCount(inboxDashboardDTO, 0, 0, null, this.getFilters(), true);
            }
            isResultsEmpty = count == 0;
            count = isReset ? 0 : count;
        } catch (Exception ex) {
            LOGGER.error(ex);
         
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        LOGGER.debug("Entering workflowtablelogic loadData");
        List list = new ArrayList();
        if (isFirstLoad) {
            try {
                list = (List) searchLogic.loadviewResults(inboxDashboardDTO, start, offset, this.getSortByColumns(), this.getFilters(), false);
            } catch (Exception ex) {
                LOGGER.error(ex);
               
            }
        }
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        LOGGER.debug("Entering workflowtablelogic configureContainer");
        InboxDashboardDTO inboxDashboardDTO = (InboxDashboardDTO) object;
        ((BeanItemContainer<InboxDashboardDTO>) container).addBean(inboxDashboardDTO);
        return inboxDashboardDTO;
    }

    public void configureSearchData(InboxDashboardDTO inboxDashboardDTO) {
        LOGGER.debug("Entering workflowtablelogic configureSearchData");
        clearAll();
        this.inboxDashboardDTO = inboxDashboardDTO;
        setRequiredCount(true);
        isFirstLoad = true;
        isReset = false;
        LOGGER.debug("Ending workflowtablelogic configureSearchData");
    }

    public boolean isResultsEmpty() {
        return isResultsEmpty;
    }
}
