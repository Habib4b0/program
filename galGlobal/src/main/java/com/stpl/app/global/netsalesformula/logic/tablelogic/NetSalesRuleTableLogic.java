/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.netsalesformula.logic.tablelogic;

import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.netsalesformula.dto.NetSalesRuleLookupDto;
import com.stpl.app.global.netsalesformula.logic.NsfLogic;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author vinodhini
 */
public class NetSalesRuleTableLogic extends PageTableLogic {

    private static final Logger LOGGER = Logger.getLogger(NetSalesRuleTableLogic.class);

    private final NsfLogic searchLogic = new NsfLogic();
    private ErrorfulFieldGroup binder;
    private boolean isFirstLoad = false;
    private boolean isResultsEmpty;
    private boolean isReset = false;
    String tabName=StringUtils.EMPTY;

    @Override
    public int getCount() {
        int count = 0;
        try {
            if (isFirstLoad) {
                count = (Integer) searchLogic.getNsRuleCount(binder, this.getFilters(),tabName);
            }
            isResultsEmpty = (count == 0);
            count = isReset ? 0 : count;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List list = new ArrayList();
        if (isFirstLoad) {
            try {
                list = (List) searchLogic.loadNsRuleResults(binder, start, offset, this.getSortByColumns(), this.getFilters(),tabName);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        NetSalesRuleLookupDto dto = (NetSalesRuleLookupDto) object;
        ((BeanItemContainer<NetSalesRuleLookupDto>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData(final ErrorfulFieldGroup binder,String tabName) {
        isFirstLoad = true;
        isReset = false;
        this.clearAll();
        this.getFilters().clear();
        this.binder = binder;
        this.tabName=tabName;
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }

    public boolean isResultsEmpty() {
        return isResultsEmpty;
    }

    public void setReset(boolean isReset) {
        this.isReset = isReset;
    }
    
    public boolean checkSearchCriteria(final ErrorfulFieldGroup binder) {
        boolean isvalid = false;
        for (Object object : binder.getFields()) {
            if (object != null && object instanceof TextField && ((TextField) object).isVisible()) {
                if (StringUtils.isNotBlank(((TextField) object).getValue())) {
                    isvalid = true;
                    break;
                }
            } else if (object != null && object instanceof ComboBox && ((ComboBox) object).isVisible()) {

                if (!ConstantUtil.SELECT_ONE.equals(((ComboBox) object).getValue().toString())) {
                    isvalid = true;
                    break;
                }
            }
        }
        return isvalid;
    }

}
