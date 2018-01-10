/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.lazyLoad;

import com.stpl.app.cff.ui.dataSelection.logic.DataSelectionLogic;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author mohamed.hameed
 */
public class GroupSearchLogic extends PageTableLogic{
    public static final String CUSTOMER_INDICATOR = "C";
    public static final String PRODUCT_INDICATOR = "P";
    private boolean loadData = false;
    private List<String> itemsOrCompanySids;
    private String groupName;
    private String groupNo;
    private String groupIdentifier;
    private final DataSelectionLogic logic = new DataSelectionLogic();
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(GroupSearchLogic.class);

    @Override
    public int getCount() {
        int count = 0;
        if (loadData) {
            try {
                count = logic.searchGroupCount(groupName, groupNo, itemsOrCompanySids, "searchGroupCount", groupIdentifier, "count");
            } catch (Exception ex) {
                LOGGER.error(ex + " in getCount");
            }
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<GroupDTO> resultList = new ArrayList<>();
        try {
            resultList = logic.searchGroup(groupName, groupNo, itemsOrCompanySids, "searchGroup", groupIdentifier, "find", start, offset, getFilters(), getSortByColumns());
        } catch (Exception ex) {
            LOGGER.error(ex + " in loadData");
        }
        return resultList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        GroupDTO dto = (GroupDTO) object;
        ((BeanItemContainer<GroupDTO>) container).addBean(dto);
        return dto;
    }

    public boolean fireSetData(List<String> itemsOrCompanySids, String groupName, String groupNo, String groupIdentifier, boolean isReset) {
        this.itemsOrCompanySids = itemsOrCompanySids == null ? itemsOrCompanySids : new ArrayList<>(itemsOrCompanySids);
        this.groupName = groupName;
        this.groupIdentifier = groupIdentifier;
        this.groupNo = groupNo;
        clearAll();
        setRequiredCount(true);
        loadData = !isReset;
        setCurrentPage(1);
        return getRecordCount() != 0;
    }

    @Override
    public void saveCurrentPage() {
        return;
    }

    @Override
    protected void createCurrentPageStart() {
        for (ExtPagedTable extPagedTable : tableList) {
            extPagedTable.setValue(null);
        }
        setRefresh(Boolean.FALSE);
    }

    @Override
    protected void createCurrentPageEnd() {
        setRefresh(Boolean.TRUE);
    }
}
