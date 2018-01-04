/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.tablelogic;

import org.asi.container.ExtTreeContainer;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.dto.SalesTabDTO;
import com.stpl.app.gcm.tp.dto.TabSelectionDTO;
import com.stpl.app.gcm.tp.logic.LoadTabLogic;
import com.vaadin.v7.data.Container;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;

/**
 *
 * @author Lokeshwari
 */
public class SalesTabTableLogic extends PageTreeTableLogic {

    private boolean firstGenerated = false;
    private final LoadTabLogic logic = new LoadTabLogic();
    private TabSelectionDTO tabSelectionDTO = new TabSelectionDTO();
    private int projectionId = 0;
    private SessionDTO session;

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        Map<Integer, Object> map = new HashMap<>();
        List<SalesTabDTO> list = logic.getConfiguredSalesTabResults(getLastParent(), tabSelectionDTO, false);
        int i = start;
        for (SalesTabDTO dto : list) {
            map.put(i, dto);
            i++;
        }
        return map;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (firstGenerated) {
            count = logic.getLevelCount(getLastParent(), tabSelectionDTO, projectionId, session);
        }

        return count;
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
        SalesTabDTO itemId = (SalesTabDTO) object;
        ((ExtTreeContainer<SalesTabDTO>) datasource).addBean(itemId);
        if (itemId.getParent() == 1) {
            ((ExtTreeContainer<SalesTabDTO>) datasource).setChildrenAllowed(itemId, true);
        } else {
            ((ExtTreeContainer<SalesTabDTO>) datasource).setChildrenAllowed(itemId, false);
        }
        return itemId;
    }

    public void setProjectionResultsData(TabSelectionDTO projSelDTO, int projectionId) {
        this.tabSelectionDTO = projSelDTO;
        this.projectionId = projectionId;
        firstGenerated = true;
        clearAll();
        setCurrentPage(1);
    }

    public SessionDTO getSession() {
        return session;
    }

    public void setSession(SessionDTO session) {
        this.session = session;
    }

}
