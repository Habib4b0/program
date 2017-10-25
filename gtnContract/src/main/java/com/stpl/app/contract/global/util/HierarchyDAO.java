package com.stpl.app.contract.global.util;

import java.util.List;

import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 * Lazy Loading interface for DashBoardDAO.
 *
 * @param <E> the generic type
 */
public interface HierarchyDAO<E> {

    /**
     * Returns the record count based on the SearchCriteria.
     *
     * @param criteria - SearchCriteria
     * @return int
     */
    int count(SearchCriteria criteria);

    /**
     * Retrieves the record from database based on the start index and offset.
     *
     * @param criteria - SearchCriteria
     * @param startIndex - int
     * @param offset - int
     * @param columns - List<OrderByColumn>
     * @return List<E>
     */
    List<E> find(SearchCriteria criteria, int startIndex, int offset, List<OrderByColumn> columns);
}
