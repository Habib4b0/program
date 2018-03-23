/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author karthikraja.k
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
public class GroupSearchLogic extends PageTableLogic {

    public static final String CUSTOMER_INDICATOR = "C";
    public static final String PRODUCT_INDICATOR = "P";
    private boolean loadData = false;
    private boolean isCustomGroup;
    private final DataSelectionLogic logic = new DataSelectionLogic();
    private GroupDTO dto;
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupSearchLogic.class);

    public GroupSearchLogic() {
    }
    

    @Override
    public int getCount() {
        int count = 0;
        if (loadData) {
            try {
                count = logic.searchGroupCount(dto, isCustomGroup, getFilters(), getSortByColumns());
            } catch (PortalException | SystemException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<GroupDTO> resultList = new ArrayList<>();
        try {
            resultList = logic.searchGroup(dto, isCustomGroup, getFilters(), getSortByColumns(), start, offset);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return resultList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        GroupDTO groupdto = (GroupDTO) object;
        ((BeanItemContainer<GroupDTO>) container).addBean(groupdto);
        return groupdto;
    }

    public boolean fireSetData(GroupDTO dto, final boolean isCustomGroup, boolean isReset) {
        this.dto = dto;
        this.isCustomGroup = isCustomGroup;
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
