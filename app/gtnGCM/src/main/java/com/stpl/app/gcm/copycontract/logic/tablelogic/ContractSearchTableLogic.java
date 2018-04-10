/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.logic.tablelogic;

import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.transfercontract.dto.ContractSearchDTO;
import com.stpl.app.gcm.transfercontract.logic.ContractSearchLogic;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author srithar
 */
public class ContractSearchTableLogic extends PageTableLogic {

    private final ContractSearchLogic logic = new ContractSearchLogic();
    private ContractSearchDTO binderDTO;
    private boolean generate;

    public ContractSearchTableLogic() {
        super();
    }

    @Override
    public int getCount() {
        if (generate) {
            return CommonUtils.getDataCount("Copy Contract-contract Search Count", logic.getInputForContractSearch(binderDTO,0,0,true,this.getSortByColumns()));
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        return logic.getPlaceHolderContractData(binderDTO,start,offset,this.getSortByColumns());
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        ContractSearchDTO dto = (ContractSearchDTO) object;
        ((BeanItemContainer<ContractSearchDTO>) container).addBean(dto);
        return dto;
    }

    public boolean loadSetDate(ContractSearchDTO binderDTO, boolean isReset) {
        this.binderDTO = binderDTO;
        this.generate = isReset;
        clearAll();
        setRequiredCount(true);
        setCurrentPage(1);
        return recordCount != 0;
    }

}
