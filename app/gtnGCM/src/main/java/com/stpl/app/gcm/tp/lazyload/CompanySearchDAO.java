/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.lazyload;

import com.stpl.app.gcm.tp.dto.TradingPartnerDTO;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.addons.lazycontainer.BeanDAO;
import org.asi.ui.addons.lazycontainer.BeanSearchCriteria;
import org.asi.ui.addons.lazycontainer.OrderByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lokeshwari
 */
public class CompanySearchDAO implements BeanDAO<TradingPartnerDTO> {

    private TradingPartnerDTO dto;
    private int parentCompanySid;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanySearchDAO.class);

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
        List<TradingPartnerDTO> resultList = new ArrayList<>();
        
        return resultList;
    }

	public int getParentCompanySid() {
		return parentCompanySid;
	}

	public void setParentCompanySid(int parentCompanySid) {
		this.parentCompanySid = parentCompanySid;
	}

        public TradingPartnerDTO getDto() {
            return dto;
        }

        public void setDto(TradingPartnerDTO dto) {
            this.dto = dto;
        }
        

}
