/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.adminconsole.filemanagement.logic.tablelogic;

import com.stpl.app.adminconsole.filemanagement.dto.ItemSearchDTO;
import com.stpl.app.adminconsole.filemanagement.logic.FileManagementLogic;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author vinodhini
 */
public class ItemSearchTableLogic extends PageTableLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileResultsTableLogic.class);
    private final FileManagementLogic searchLogic = new FileManagementLogic();
    private ItemSearchDTO resultDTO;
    private boolean isFirstLoad = false;
    private boolean isResultsEmpty;
    private boolean isReset = false;

    public ItemSearchTableLogic(){
    	super();
    }
    @Override
    public int getCount() {
        int count = 0;
        try {
            if (isFirstLoad) {
                count = (Integer) searchLogic.getItemSearchResults(resultDTO, 0, 0, this.getSortByColumns(), this.getFilters(), true);
            }
            isResultsEmpty = count == 0;
            count = isReset ? 0 : count;
        } catch (Exception ex) {
           LOGGER.error(ex.getMessage());
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List list = new ArrayList();
        if (isFirstLoad) {
            try {
                list = (List) searchLogic.getItemSearchResults(resultDTO, start, offset, this.getSortByColumns(), this.getFilters(), false);
            } catch (Exception ex) {
               LOGGER.error(ex.getMessage());
            }
        }
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        ItemSearchDTO dto = (ItemSearchDTO) object;
        ((BeanItemContainer<ItemSearchDTO>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData(ItemSearchDTO resultDTO) {
        isFirstLoad = true;
        isReset = false;
        this.clearAll();
        this.getFilters().clear();
        this.resultDTO = resultDTO;
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }

    public boolean isResultsEmpty() {
        return isResultsEmpty;
    }

    public void setReset(boolean isReset) {
        this.isReset = isReset;
    }

}
