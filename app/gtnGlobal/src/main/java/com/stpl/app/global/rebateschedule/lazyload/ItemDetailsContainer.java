/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.rebateschedule.lazyload;

import com.stpl.app.global.rebateschedule.dto.ItemDetailsDTO;
import com.stpl.ifs.ui.CustomePagedFilterTable;
import com.vaadin.data.util.BeanItemContainer;
import java.util.Collections;
import java.util.List;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author Asha
 */
public class ItemDetailsContainer implements BeanDAO<ItemDetailsDTO> {
    public boolean editListFlag;
     int pageLength;
     int pageNo;
     int nextPageStartIndex;
     int nextPageEndIndex;
    CustomePagedFilterTable table;
    BeanItemContainer<ItemDetailsDTO> ItemDetailsResultSaveBean;

    /**
     * Method used for get Count.
     */
    public ItemDetailsContainer() {
        // Empty Constructor
    }
    
    public ItemDetailsContainer(final int pageLength,final int page) {
        this.pageLength = pageLength;
        this.pageNo = page;        
    }

    public ItemDetailsContainer(CustomePagedFilterTable table, BeanItemContainer<ItemDetailsDTO> ItemDetailsResultSaveBean) {
        this.table = table;
        this.ItemDetailsResultSaveBean = ItemDetailsResultSaveBean;
    }

    /**
     *
     * @param searchCriteria
     * @return
     */
    public int count(final BeanSearchCriteria searchCriteria) {
        return 0;
    }

    /**
     * Method used for get the results.
     * @param searchCriteria
     * @param startIndex
     * @param offset
     * @param list
     * @return 
     */
    public List<ItemDetailsDTO> find(final BeanSearchCriteria searchCriteria, final int startIndex, final int end, final List<OrderByColumn> list) {

        return Collections.emptyList();       
    }  
}
