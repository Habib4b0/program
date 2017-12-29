/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.dao;

import com.stpl.app.gcm.copycontract.dto.CFPCompanyDTO;
import com.stpl.app.gcm.copycontract.logic.CopyContractLogic;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author kasiammal.m
 */
public class CompanySearchDAO implements BeanDAO<CFPCompanyDTO> {
    CopyContractLogic logic = new CopyContractLogic();
    CFPCompanyDTO CFPCompanyDTO = new CFPCompanyDTO();

    public CompanySearchDAO() {
    }

    public CompanySearchDAO(CFPCompanyDTO CFPCompanyDTO) {
        this.CFPCompanyDTO = CFPCompanyDTO;
    }

    @Override
    public int count(BeanSearchCriteria bsc) {
        int count = 0;
        count = logic.getCompanySearchCount(CFPCompanyDTO, bsc);
        return count;
    }

    @Override
    public List<CFPCompanyDTO> find(BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list) {
        List<CFPCompanyDTO> resultList = new ArrayList<>();
        return resultList;
    }
}
