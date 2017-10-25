/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.logic;

import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.global.dto.CFPCompanyDTO;
import com.stpl.app.contract.global.dto.CompanyMasterDTO;
import com.stpl.app.contract.global.logic.CompanyAdditionLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author nimisha.rakesh
 */
public class AvailableCompanyTableLogic extends PageTableLogic {

    private static final Logger LOGGER = Logger.getLogger(AvailableCompanyTableLogic.class);
    boolean reset = true;
    private CompanyAdditionLogic logic = new CompanyAdditionLogic(null);
    public String searchFields;
    public String searchValue;
    private boolean availableTable = true;

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public boolean isAvailableTable() {
        return availableTable;
    }

    public void setAvailableTable(boolean availableTable) {
        this.availableTable = availableTable;
    }

    public AvailableCompanyTableLogic(boolean availableTable) {
        super();
        this.availableTable = availableTable;
    }

    @Override
    public int getCount() {
        LOGGER.debug("Count Stasted--->> ");
        int count = 0;

        if (!reset) {
            try {
                if (isAvailableTable()) {
                    count = logic.getCompanyAddtionCount(searchFields, searchValue, this.getFilters());
                } else {
                    count = logic.getLazySelectedCompaniesDetailsCount(0, 0, false, null, this.getFilters(), true);
                }
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }

        LOGGER.debug("Count-------->>> " + count);
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List list = new ArrayList();
        if (!reset) {
            try {
                if (isAvailableTable()) {
                    list = logic.getCompaniesForCFP(searchFields, searchValue, start, offset, this.getSortByColumns(), this.getFilters());
                } else {
                    list = logic.getLazySelectedCompaniesDeatils(start, offset, false, this.getSortByColumns(), this.getFilters(), false, StringUtils.EMPTY);
                }
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        LOGGER.debug("loadData---->>>>> " + list.size());
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
          if (isAvailableTable()) {
        CompanyMasterDTO dto = (CompanyMasterDTO) object;
        ((BeanItemContainer<CompanyMasterDTO>) container).addBean(dto);
        return dto;
          }else{
           CFPCompanyDTO dto = (CFPCompanyDTO) object;
        ((BeanItemContainer<CFPCompanyDTO>) container).addBean(dto);   
         return dto;
          }
        
    }

    public boolean fireSetData(final String searchFields, String searchValue, SessionDTO sessionDTO, boolean reset) {
        this.reset = reset;
        logic.setSessionDTO(sessionDTO);
        this.clearAll();
        this.getFilters().clear();
        this.searchFields = searchFields;
        this.searchValue = searchValue;
        setRequiredCount(true);
        setCurrentPage(1);
        return getRecordCount()!=0;
    }

}
