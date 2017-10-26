/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.accountconfiguration.logic.tablelogic;

import com.stpl.app.arm.accountconfiguration.dto.AccountConfigDTO;
import com.stpl.app.arm.accountconfiguration.dto.AccountConfigSelection;
import com.stpl.app.arm.accountconfiguration.logic.AccountConfigLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author Srithar.Raju
 */
public class AccountConfigTableLogic extends PageTableLogic {

    boolean isGenerate;
    AccountConfigLogic logic = AccountConfigLogic.getInstance();
    AccountConfigSelection selection;
    public static final Logger LOGGER = Logger.getLogger(AccountConfigTableLogic.class);

    @Override
    public int getCount() {
        try {
            if (isGenerate) {
                return logic.getAccountConfigCount(selection, getFilters());
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return 0;
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        try {
            return logic.getAccountConfigData(selection, start, offset, getFilters(), getSortByColumns());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
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
