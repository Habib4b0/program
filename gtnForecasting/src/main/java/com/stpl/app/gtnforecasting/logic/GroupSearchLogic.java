/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic;

import static com.stpl.app.gtnforecasting.logic.CommonLogic.LOGGER;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

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
    boolean loadData = false;
    private boolean isCustomGroup;
    DataSelectionLogic logic = new DataSelectionLogic();
    GroupDTO dto;
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(GroupSearchLogic.class);

    @Override
    public int getCount() {
        int count = 0;
        if (loadData) {
            try {
                count = logic.searchGroupCount(dto, isCustomGroup, getFilters(), getSortByColumns());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<GroupDTO> resultList = new ArrayList<GroupDTO>();
        try {
            resultList = logic.searchGroup(dto, isCustomGroup, getFilters(), getSortByColumns(), start, offset);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return resultList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        GroupDTO dto = (GroupDTO) object;
        ((BeanItemContainer<GroupDTO>) container).addBean(dto);
        return dto;
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
