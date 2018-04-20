/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.accountconfiguration.logic.tablelogic;

import com.stpl.app.arm.accountconfiguration.dto.AccountConfigDTO;
import com.stpl.app.arm.accountconfiguration.dto.AccountConfigSelection;
import com.stpl.app.arm.accountconfiguration.logic.AccountConfigLogic;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Srithar.Raju
 */
public class AccountConfigTableLogic extends PageTableLogic {

    private boolean isGenerate;
    private AccountConfigLogic logic = AccountConfigLogic.getInstance();
    private AccountConfigSelection selection;
    public static final Logger LOGGER = LoggerFactory.getLogger(AccountConfigTableLogic.class);

    public AccountConfigTableLogic() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    @Override
    public int getCount() {
        try {
            if (isGenerate) {
                return logic.getAccountConfigCount(selection, getFilters());
            }
        } catch (Exception ex) {
            LOGGER.error("Error in getCount :" + ex);
            return 0;
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        try {
            return logic.getAccountConfigData(selection, start, offset, getFilters(), getSortByColumns());
        } catch (Exception ex) {
            LOGGER.error("Error in loadData :" + ex);
            return new ArrayList<>();
        }
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        AccountConfigDTO dto = (AccountConfigDTO) object;
        ((BeanItemContainer<AccountConfigDTO>) container).addBean(dto);
        return dto;
    }

    public void loadsetData(boolean isRest, AccountConfigSelection selection) {
        isGenerate = isRest;
        this.selection = selection;
        clearAll();
        clearFilters();
        setRequiredCount(true);
        setCurrentPage(1);
    }
}
