/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.accountconfiguration.logic.tablelogic;

import com.stpl.app.arm.accountconfiguration.dto.AccountConfigDTO;
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
public class AccountConfigSearchTableLogic extends PageTableLogic {

    private boolean isGenerate;
    private AccountConfigDTO binderDto;
    private AccountConfigLogic logic = AccountConfigLogic.getInstance();
    public static final Logger LOGGER = LoggerFactory.getLogger(AccountConfigSearchTableLogic.class);

    public AccountConfigSearchTableLogic() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    @Override
    public int getCount() {
        if (isGenerate) {
            try {
                binderDto.setCount(true);
                binderDto.setFilters(getFilters());
                return logic.getAccountConfigCount(binderDto);
            } catch (Exception ex) {
                LOGGER.error("Error in getCount :", ex);
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
            LOGGER.error("Error in loadData :", ex);
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
        return Double.compare(getRecordCount(), 0.0) != 0;
    }

}
