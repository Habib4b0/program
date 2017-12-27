/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.logic;

import com.stpl.app.gcm.promotetptocontract.dto.PromoteTpToChDto;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author alok.v
 */
public class CompanySearchTableLogic extends PageTableLogic {

    boolean firstTime = true;
    CompanySearchLogic compLogic = new CompanySearchLogic();
    PromoteTpToChDto tpDTo = new PromoteTpToChDto();
    String searchSessionid = StringUtils.EMPTY;
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(CompanySearchTableLogic.class);

    @Override
    public int getCount() {
        try {
            if (!firstTime) {
                return compLogic.companySearchCount(tpDTo, getFilters());
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        try {
            return compLogic.searchCompaniesLazy(tpDTo, start, offset,getFilters(), searchSessionid);
        } catch (Exception ex) {
            LOGGER.error(ex);
            return Collections.emptyList();
        }
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        PromoteTpToChDto dto = (PromoteTpToChDto) object;
        ((BeanItemContainer<PromoteTpToChDto>) container).addBean(dto);
        return dto;
    }

    /**
     * Setting up the datas which we pass to the Logic
     *
     * @param tpDTO
     * @param parentCompanySid
     * @return boolean - count is 0 or not
     */
    public boolean loadSetData(PromoteTpToChDto tpDTO, String searchSessionId) {
        this.tpDTo = tpDTO;
        this.searchSessionid = searchSessionId;
        firstTime = tpDTO.getReset();
        clearAll();
        setRequiredCount(true);
        setCurrentPage(1);
        return getRecordCount() != 0;

    }
}
