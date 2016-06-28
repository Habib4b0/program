/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galworkflow.logic.tablelogic;

import com.stpl.app.galworkflow.dto.InboxDashboardDTO;
import com.stpl.app.galworkflow.dto.WorkFlowHistoryLookupDTO;
import com.stpl.app.galworkflow.logic.WorkflowLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Image;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author Lokeshwari
 */
public class WorkflowHistoryTableLogic extends PageTableLogic {

    BeanItemContainer<WorkFlowHistoryLookupDTO> wfHistoryLookupitemBean;
    WorkflowLogic workFlowLogic = new WorkflowLogic();
    InboxDashboardDTO historydto = new InboxDashboardDTO();
    Boolean isFirst=false;
    protected Image attachmentPngImage = new Image(null, new ThemeResource("../../icons/attachment.png"));
    
    @Override
    public int getCount() {
        int count = 0;
        if(isFirst){
        count = workFlowLogic.workFlowHistorySearchCount(historydto.getWorkflowMasterSystemId(), getFilters());
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        return (List<WorkFlowHistoryLookupDTO>) workFlowLogic.workFlowHistorySearch(historydto.getWorkflowMasterSystemId(), getFilters(), getSortByColumns());
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        WorkFlowHistoryLookupDTO dto = (WorkFlowHistoryLookupDTO) object;
        if (dto.getCheckbox().equals(true)) {
            dto.setAttachmentLink(attachmentPngImage);
        }
        ((BeanItemContainer<WorkFlowHistoryLookupDTO>) container).addBean(dto);
        return dto;
    }

    /**
     *
     * @param wfHistoryLookupitemBean
     * @param workFlowMasterSystemId
     */
    public void setSearchData(InboxDashboardDTO historydto,Boolean flag) {
        this.isFirst=flag;
        attachmentPngImage.setWidth("20px");
        clearAll();
        this.historydto = historydto;
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }

}
