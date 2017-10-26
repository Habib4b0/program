/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.lazyload;

import com.stpl.app.gcm.tp.dto.TradingPartnerDTO;
import com.stpl.app.gcm.tp.logic.CompanySearchLogic;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author lokeshwari
 */
public class CompanySearchDAO implements BeanDAO<TradingPartnerDTO> {

    TradingPartnerDTO dto;
    int parentCompanySid;
    CompanySearchLogic logic = new CompanySearchLogic();
    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(CompanySearchDAO.class);

    public CompanySearchDAO(TradingPartnerDTO tpDto) {
        this.dto = tpDto;
    }

    public CompanySearchDAO(TradingPartnerDTO tpDto, int parentCompanySid) {
        this.dto = tpDto;
        this.parentCompanySid = parentCompanySid;
    }

    @Override
    public int count(BeanSearchCriteria bsc) {
        int count = 0;
        return count;
    }

    @Override
    public List<TradingPartnerDTO> find(BeanSearchCriteria sc, int startIndex, int offset, List<OrderByColumn> orderByColumns) {
        List<TradingPartnerDTO> resultList = new ArrayList<TradingPartnerDTO>();
        try {
        } catch (Exception ex) {
             LOGGER.error(ex);
        }
        return resultList;
    }

}
