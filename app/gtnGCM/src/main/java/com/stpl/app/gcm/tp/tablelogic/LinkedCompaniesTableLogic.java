/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.tablelogic;

import com.stpl.app.gcm.tp.dto.CompanyLinkDTO;
import com.stpl.app.gcm.tp.logic.CompanySearchLogic;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sriram
 */
public class LinkedCompaniesTableLogic extends PageTableLogic {

    private final CompanySearchLogic logic = new CompanySearchLogic();
    private final CompanyLinkDTO tpDTo = new CompanyLinkDTO();
    private String searchSessionid = StringUtils.EMPTY;
    private boolean firstTime = true;
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanySearchTableLogic.class);

    @Override
    public int getCount() {
        try {
            if (!firstTime) {
                return logic.getLinkedCompaniesCount(tpDTo, getFilters(), searchSessionid);
            }
        } catch (Exception e) {
            LOGGER.error("",e);
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        try {
            return logic.getLinkedCompanies(tpDTo, start, offset, getFilters(), searchSessionid);
        } catch (Exception e) {
            LOGGER.error("",e);
            return Collections.emptyList();
        }

    }

    @Override
    public Object configureContainer(Object object, Container container) {
        CompanyLinkDTO dto = (CompanyLinkDTO) object;
        ((BeanItemContainer<CompanyLinkDTO>) container).addBean(dto);
        return dto;
    }

    /**
     * Setting up the datas which we pass to the Logic
     *
     * @param searchSessionId
     * @return boolean - count is 0 or not
     */
    public boolean loadSetData(String searchSessionId, boolean firstTime) {
        this.searchSessionid = searchSessionId;
        this.firstTime = firstTime;
        clearAll();
        setRequiredCount(true);
        return getRecordCount() != 0;

    }

}
