/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.supercode;

import com.vaadin.data.Container;
import java.util.List;
import java.util.Set;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author Abhiram.Giri
 */
public class SelectionCriteria implements Criteria{
    /** The sort by columns. */
    private List<SortByColumn> sortByColumns=null;
    
    /** The added filters. */
    private Set<Container.Filter> filters=null;
    private SelectionDTO selectionDto=null;
    private int start=0;
    private int offset=0;
    private Object parent=null;
    private int currentPage,lastPage;
    private int siblingCount;
    @Override
    public List<SortByColumn> getSortByColumns() {
        return sortByColumns;
    }

    public void setSortByColumns(List<SortByColumn> sortByColumns) {
        this.sortByColumns = sortByColumns;
    }

    @Override
    public Set<Container.Filter> getFilters() {
        return filters;
    }

    public void setFilters(Set<Container.Filter> filters) {
        this.filters = filters;
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

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getSiblingCount() {
        return siblingCount;
    }

    public void setSiblingCount(int siblingCount) {
        this.siblingCount = siblingCount;
    }
    
}
