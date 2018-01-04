/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.lazyload;

import com.stpl.app.gcm.promotetptocontract.dto.PromoteTpToChDto;
import com.stpl.app.gcm.promotetptocontract.logic.PromoteTPLogic;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.addons.lazycontainer.BeanDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.asi.ui.addons.lazycontainer.BeanSearchCriteria;
import org.asi.ui.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author alok.v
 */
public class PromoteTpSearchDao implements BeanDAO<PromoteTpToChDto> {

    public static final Logger LOGGER = LoggerFactory.getLogger(PromoteTpSearchDao.class);
    private final PromoteTpToChDto promoteTpToChDto;
    private final PromoteTPLogic logic = new PromoteTPLogic();

    public PromoteTpSearchDao(PromoteTpToChDto promoteTpToChDto) {
        this.promoteTpToChDto = promoteTpToChDto;
    }

    @Override
    public int count(BeanSearchCriteria sc) {
        int count = 0;
        try {
            count = logic.searchCompanyCount(promoteTpToChDto);
        } catch (Exception ex) {

            LOGGER.error("",ex);
        }
        LOGGER.debug("count in PromoteTpSearchDao---->" + count);
        return count;
    }

    @Override
    public List<PromoteTpToChDto> find(BeanSearchCriteria sc, int startIndex, int offset, List<OrderByColumn> orderByColumns) {
        List<PromoteTpToChDto> resultList = new ArrayList<>();
        try {
            resultList = logic.searchCompaniesLazy(promoteTpToChDto, startIndex, offset,orderByColumns);
        } catch (Exception ex) {

            LOGGER.error("",ex);
        }
        return resultList;
    }
}
