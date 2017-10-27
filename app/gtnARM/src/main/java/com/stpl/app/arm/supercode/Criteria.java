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
public interface Criteria {

    List<SortByColumn> getSortByColumns();

    Set<Container.Filter> getFilters();

    SelectionDTO getSelectionDto();

    int getStart();

    int getOffset();

    Object getParent();

    public int getCurrentPage();

    public int getLastPage();

    public int getSiblingCount();
}
