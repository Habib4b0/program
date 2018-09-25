/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.lazyLoad;

import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.ui.dataSelection.logic.DataSelectionLogic;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.LoggerFactory;

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
    
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GroupSearchLogic.class);

    public GroupSearchLogic() {
        super();
    }

    @Override
    public int getCount() {
        int count = 0;
        if (loadData) {
            try {
                count = logic.searchGroupCount(groupName, groupNo, "searchGroupCount", groupIdentifier, "count");
            } catch (SystemException ex) {
                LOGGER.error(" in getCount= {}", ex);
            }
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<GroupDTO> resultList = new ArrayList<>();
        try {
            resultList = logic.searchGroup(groupName, groupNo, "searchGroup", groupIdentifier, "find", start, offset, getFilters(), getSortByColumns());
        } catch (SystemException ex) {
            LOGGER.error(" in loadData= {}", ex);
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
        setRefresh(BooleanConstant.getFalseFlag());
    }

    @Override
    protected void createCurrentPageEnd() {
        setRefresh(BooleanConstant.getTrueFlag());
    }
}
