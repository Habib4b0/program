
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.index.logic;

import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author mohamed.hameed
 */
public class ItemIndexTableLogic extends PageTableLogic {

    private ItemIndexDto binderDto = new ItemIndexDto();
    private final ItemLogic logic = new ItemLogic();
    private boolean generate = true;
    private SelectionDTO selection = new SelectionDTO();
    private List<ItemIndexDto> selecteditemList;

    /**
     * Record count logic
     *
     * @return integer as count
     */
    @Override
    public int getCount() {
        if (!generate) {
            binderDto.setIsCount(false);
            selection.setFilters(getFilters());
                return logic.getSearchCount(binderDto, selection);
            }
        return 0;
    }

    /**
     * Loading Grid
     *
     * @param start
     * @param offset
     * @return List of results
     */
    @Override
    public List loadData(int start, int offset) {
            binderDto.setStartIndex(start);
            binderDto.setEndIndex(offset);
            binderDto.setIsCount(true);
            selection.setFilters(getFilters());
        List<ItemIndexDto> resultList = logic.getSearchResults(binderDto, selection, selecteditemList);
         return resultList;
    }

    /**
     * Configure container
     *
     * @param object
     * @param container
     * @return Object
     */
    @Override
    public Object configureContainer(Object object, Container container) {
        ItemIndexDto dto = (ItemIndexDto) object;
        ((BeanItemContainer<ItemIndexDto>) container).addBean(dto);
        return dto;
    }

    /**
     * Method to setting up the data which we pass to logic
     *
     * @param binderDto
     */
    public boolean loadSetData(ItemIndexDto binderDto, SelectionDTO selection, List<ItemIndexDto> selecteditemList) {
        this.binderDto = binderDto;
        this.selection = selection;
        this.selecteditemList = selecteditemList;
        clearAll();
        setRequiredCount(true);
        generate = selection.isReset();
        setCurrentPage(1);
        return recordCount != 0;
    }
}
