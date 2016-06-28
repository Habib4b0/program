/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.tablelogic;

import com.stpl.app.gcm.tp.dto.TradingPartnerDTO;
import com.stpl.app.gcm.tp.logic.CompanySearchLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author lokeshwari
 */
public class CompanySearchTableLogic extends PageTableLogic {

    boolean firstTime = true;
    CompanySearchLogic logic = new CompanySearchLogic();
    TradingPartnerDTO tpDTo = new TradingPartnerDTO();
    int parentCompanySid = 0;
    String searchSessionid = StringUtils.EMPTY;
    String recordLockStatus = StringUtils.EMPTY;
    String parentCompanyNo = StringUtils.EMPTY;
    String parentCompanyName = StringUtils.EMPTY;
    public boolean isProjSelected = false;
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(CompanySearchTableLogic.class);

    @Override
    public int getCount() {
        try {
            if (!firstTime) {
                return logic.companySearchCount(tpDTo, parentCompanyNo, parentCompanyName, getFilters(), recordLockStatus, searchSessionid);
            }
        } catch (Exception e) {
             LOGGER.error(e.getMessage());
        }

        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        try {
            return logic.searchCompaniesLazy(tpDTo, start, offset, this.getSortByColumns(), parentCompanyNo, parentCompanyName, getFilters(), recordLockStatus, searchSessionid, isProjSelected);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }

    }

    @Override
    public Object configureContainer(Object object, Container container) {
        TradingPartnerDTO dto = (TradingPartnerDTO) object;
        ((BeanItemContainer<TradingPartnerDTO>) container).addBean(dto);
        return dto;
    }

    /**
     * Setting up the datas which we pass to the Logic
     *
     * @param tpDTO
     * @param parentCompanySid
     * @return boolean - count is 0 or not
     */
    public void loadSetData(TradingPartnerDTO tpDTO, String parentCompanyNo, String parentCompanyName, String recordLockStatus, String searchSessionId) {
       
        this.tpDTo = tpDTO;
        this.parentCompanyNo = parentCompanyNo;
        this.parentCompanyName = parentCompanyName;
        this.searchSessionid = searchSessionId;
        this.recordLockStatus = recordLockStatus;
        this.getFilters().clear();
        firstTime = tpDTO.getReset();
        clearAll();
        setRequiredCount(true);
    }

    public boolean isRecordPresent() {
        setCurrentPage(1);
        return getRecordCount() != 0;
    }

}
