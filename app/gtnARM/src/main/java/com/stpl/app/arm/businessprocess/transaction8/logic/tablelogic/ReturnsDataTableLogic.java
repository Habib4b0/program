/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction8.logic.tablelogic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.transaction8.logic.ReturnsDataLogic;
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
 * @author @param <T>
 *
 */
public class ReturnsDataTableLogic<T extends AdjustmentDTO> extends PageTableLogic implements TableLogicAble<T>, HasLogic<T> {

    private ReturnsDataLogic logic;
    private boolean isGenerated = false;
    private AbstractSelectionDTO selection;

    public ReturnsDataTableLogic(ReturnsDataLogic logic, AbstractSelectionDTO selection) {
        this.logic = logic;
        this.selection = selection;
    }

    @Override
    public int getCount(Criteria criteria) {
        return logic.getCount(criteria);
    }

    @Override
    public DataResult<T> getData(Criteria criteria) {
        if (isGenerated) {
            return logic.getData(criteria);
        }
        return null;
    }

    @Override
    public LogicAble<T> getSummaryLogic() {
        return logic;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (isGenerated) {
            SelectionCriteria criteria = new SelectionCriteria();
            criteria.setFilters(getFilters());
            criteria.setSelectionDto(selection);
            criteria.setParent(null);
            count = getCount(criteria);
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        SelectionCriteria criteria = new SelectionCriteria();
        criteria.setSelectionDto(selection);
        criteria.setFilters(getFilters());
        criteria.setSortByColumns(getSortByColumns());
        criteria.setParent(null);
        criteria.setStart(start);
        criteria.setOffset(offset);
        return getData(criteria).getDataResults();
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        AdjustmentDTO dto = (AdjustmentDTO) object;
        ((ExtContainer<AdjustmentDTO>) container).addBean(dto);
        return dto;
    }

    public void loadsetData(ReturnsDataLogic logic, boolean isGenerated) {
        this.logic = logic;
        this.isGenerated = isGenerated;
        this.clearAll();
        setRequiredCount(true);
        this.setCurrentPage(1);
    }

}