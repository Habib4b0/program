/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.supercode;

import com.stpl.app.arm.common.CommonLogic;
import com.vaadin.v7.data.Container;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author Abhiram.Giri
 */
public class SelectionCriteria implements Criteria {

    /**
     * The sort by columns.
     */
    private List<SortByColumn> sortByColumns = null;

    /**
     * The added filters.
     */
    private Set<Container.Filter> filters = null;
    private SelectionDTO selectionDto = null;
    private int start = 0;
    private int offset = 0;
    private Object parent = null;
    private int currentPage, lastPage;
    private int siblingCount;

    public SelectionCriteria() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    @Override
    public List<SortByColumn> getSortByColumns() {
        return CommonLogic.getInstance().getArrayListCloned(sortByColumns);
    }

    public void setSortByColumns(List<SortByColumn> sortByColumns) {
        this.sortByColumns = CommonLogic.getInstance().getArrayListCloned(sortByColumns);
    }

    @Override
    public Set<Container.Filter> getFilters() {
        return filters == null ? null : new HashSet<>(filters);
    }

    public void setFilters(Set<Container.Filter> filters) {
        this.filters = filters == null ? null : new HashSet<>(filters);
    }

    @Override
    public SelectionDTO getSelectionDto() {
        return selectionDto;
    }

    public void setSelectionDto(SelectionDTO selectionDto) {
        this.selectionDto = selectionDto;
    }

    @Override
    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    @Override
    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public Object getParent() {
        return parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    @Override
    public int getSiblingCount() {
        return siblingCount;
    }

    public void setSiblingCount(int siblingCount) {
        this.siblingCount = siblingCount;
    }

}
