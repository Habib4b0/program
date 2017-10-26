/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.lazyload;

import com.stpl.app.gcm.promotetptocontract.dto.PromoteTpToChDto;
import com.stpl.app.gcm.promotetptocontract.logic.PromoteTPLogic;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author alok.v
 */
public class PromoteTpSearchDao implements BeanDAO<PromoteTpToChDto> {

    public static final Logger LOGGER = Logger.getLogger(PromoteTpSearchDao.class);
    PromoteTpToChDto promoteTpToChDto;
    PromoteTPLogic logic = new PromoteTPLogic();

    public PromoteTpSearchDao(PromoteTpToChDto promoteTpToChDto) {
        this.promoteTpToChDto = promoteTpToChDto;
    }

    @Override
    public int count(BeanSearchCriteria sc) {
        int count = 0;
        try {
            count = logic.searchCompanyCount(promoteTpToChDto);
        } catch (Exception ex) {

            LOGGER.error(ex);
        }
        LOGGER.debug("count in PromoteTpSearchDao---->" + count);
        return count;
    }

    @Override
    public List<PromoteTpToChDto> find(BeanSearchCriteria sc, int startIndex, int offset, List<OrderByColumn> orderByColumns) {
        List<PromoteTpToChDto> resultList = new ArrayList<PromoteTpToChDto>();
        try {
            resultList = logic.searchCompaniesLazy(promoteTpToChDto, startIndex, offset,orderByColumns);
        } catch (Exception ex) {

            LOGGER.error(ex);
        }
        return resultList;
    }
}
