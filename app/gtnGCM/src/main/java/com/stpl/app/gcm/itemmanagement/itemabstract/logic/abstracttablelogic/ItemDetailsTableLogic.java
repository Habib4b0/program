/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.logic.abstracttablelogic;

import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.add.dto.SummaryDTO;
import com.stpl.app.gcm.itemmanagement.add.logic.SummaryLogic;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author porchelvi.g
 */
public class ItemDetailsTableLogic extends PageTableLogic {

    private boolean isGenerated = false;
    private final SummaryLogic logic = new SummaryLogic();
    private SelectionDTO selection;

    public ItemDetailsTableLogic() {
    }

    @Override
    public int getCount() {

        if (isGenerated) {
            selection.setFilters(getFilters());
            return logic.getContractCount(selection);
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        return logic.getContractResults(selection, start, offset);
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        SummaryDTO dto = (SummaryDTO) object;
        ((BeanItemContainer<SummaryDTO>) container).addBean(dto);
        return dto;
    }

    /**
     * Setting up the datas which we pass to the Logic
     *
     * @param binderDto
     * @return boolean - count is 0 or not
     */
    public boolean loadSetData(SelectionDTO selection, boolean isReset) {
        this.selection = selection;
        isGenerated = isReset;
        clearAll();
        setRequiredCount(true);
        setCurrentPage(1);
        return getRecordCount() != 0;
    }
}
