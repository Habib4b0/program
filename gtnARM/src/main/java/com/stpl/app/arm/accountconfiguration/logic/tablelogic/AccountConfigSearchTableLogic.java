/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.accountconfiguration.logic.tablelogic;

import com.stpl.app.arm.accountconfiguration.dto.AccountConfigDTO;
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
public class AccountConfigSearchTableLogic extends PageTableLogic {

    boolean isGenerate;
    AccountConfigDTO binderDto;
    AccountConfigLogic logic = AccountConfigLogic.getInstance();
    public static final Logger LOGGER = Logger.getLogger(AccountConfigSearchTableLogic.class);

    @Override
    public int getCount() {
        if (isGenerate) {
            try {
                binderDto.setCount(true);
                binderDto.setFilters(getFilters());
                int count = logic.getAccountConfigCount(binderDto);
                return count;
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
                return 0;
            }
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        try {
            binderDto.setStart(start);
            binderDto.setOffset(offset);
            binderDto.setCount(false);
            binderDto.setSortByColumns(getSortByColumns());
            return logic.getAccountConfigData(binderDto);
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

    public boolean loadsetData(Boolean isGenerate, AccountConfigDTO binderDto) {
        this.isGenerate = isGenerate;
        this.binderDto = binderDto;
        clearAll();
        clearFilters();
        setRequiredCount(true);
        setCurrentPage(1);
        return getRecordCount() != 0;
    }

}
