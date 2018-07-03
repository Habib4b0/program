/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.commontemplates;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractAdjustmentDetailsLogic;
import com.stpl.app.arm.supercode.Criteria;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.HasLogic;
import com.stpl.app.arm.supercode.LogicAble;
import com.stpl.app.arm.supercode.SelectionCriteria;
import com.stpl.app.arm.supercode.TableLogicAble;
import com.vaadin.v7.data.Container;
import java.util.List;
import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author Karthikeyan.Subraman
 * @param <T>
 */
public class AdjustmentDetailsTableLogic<T extends AdjustmentDTO> extends PageTableLogic implements TableLogicAble<T>, HasLogic<T> {

    private AbstractAdjustmentDetailsLogic logic;
    private AbstractSelectionDTO selection;
    private Boolean isGenerate = Boolean.FALSE;

    public AdjustmentDetailsTableLogic(AbstractAdjustmentDetailsLogic logic, AbstractSelectionDTO selection) {
        this.logic = logic;
        this.selection = selection;
    }

    @Override
    public int getCount(Criteria criteria) {
        return getSummaryLogic().getCount(criteria);
    }

    @Override
    public DataResult getData(Criteria criteria) {
        return getSummaryLogic().getData(criteria);
    }

    @Override
    public LogicAble<T> getSummaryLogic() {
        return logic;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (isGenerate) {
            SelectionCriteria criteria = new SelectionCriteria();
            criteria.setFilters(getFilters());
            criteria.setSelectionDto(selection);
            criteria.setParent(null);
            count = getCount(criteria);
        }
        return count;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        AdjustmentDTO dto = (AdjustmentDTO) object;
        ((ExtContainer<AdjustmentDTO>) container).addBean(dto);
        return dto;
    }

    public boolean loadSetData(Boolean isGenerate) {
        this.isGenerate = isGenerate;
        this.clearAll();
        setRequiredCount(Boolean.TRUE);
        setCurrentPage(1);
        return Boolean.TRUE;
    }

    @Override
    public List<AdjustmentDTO> loadData(int start, int offset) {
        SelectionCriteria criteria = new SelectionCriteria();
        criteria.setFilters(getFilters());
        criteria.setSortByColumns(getSortByColumns());
        criteria.setSelectionDto(selection);
        criteria.setParent(null);
        criteria.setStart(start);
        criteria.setOffset(offset);
        return getData(criteria).getDataResults();
    }

}
