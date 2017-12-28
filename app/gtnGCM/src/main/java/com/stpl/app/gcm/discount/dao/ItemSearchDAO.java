/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.dao;

import com.stpl.app.gcm.discount.dto.ContractsDetailsDto;
import com.stpl.app.gcm.discount.logic.DiscountLogic;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.asi.ui.addons.lazycontainer.DAO;
import org.asi.ui.addons.lazycontainer.OrderByColumn;
import org.asi.ui.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author santanukumar
 */
public class ItemSearchDAO implements DAO<ContractsDetailsDto> {

    DiscountLogic logic = new DiscountLogic();
    ContractsDetailsDto removeDiscountDto = new ContractsDetailsDto();
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemSearchDAO.class);

    public ItemSearchDAO() {

    }

    public ItemSearchDAO(ContractsDetailsDto removeDiscountDto) {
        this.removeDiscountDto = removeDiscountDto;
    }

    public int count(SearchCriteria bsc) {
        int count = 0;

        try {
            count = logic.getItemSearchCount();
            LOGGER.debug("count--> " + count);
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
        return count;
    }

    public List<ContractsDetailsDto> find(SearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list) {
        List<ContractsDetailsDto> resultList = new ArrayList<>();
        try {
            LOGGER.debug("find--> " + startIndex + "     " + offset);
            resultList = logic.getItemSearch(removeDiscountDto);
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
        return resultList;
    }

}
