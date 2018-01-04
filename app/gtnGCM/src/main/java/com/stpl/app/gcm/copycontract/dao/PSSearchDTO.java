/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.dao;

import com.stpl.app.gcm.copycontract.dto.PSIFPDTO;
import com.stpl.app.gcm.copycontract.logic.CopyContractLogic;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.addons.lazycontainer.BeanDAO;
import org.asi.ui.addons.lazycontainer.BeanSearchCriteria;
import org.asi.ui.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author kasiammal.m
 */
public class PSSearchDTO implements BeanDAO<PSIFPDTO> {
    private final CopyContractLogic logic = new CopyContractLogic();
    private PSIFPDTO PSIFPDTO = new PSIFPDTO();

    public PSSearchDTO(PSIFPDTO PSIFPDTO) {
        this.PSIFPDTO = PSIFPDTO;
    }

    @Override
    public int count(BeanSearchCriteria bsc) {

        int count = 0;
        count = logic.getPSSearchCount(PSIFPDTO, bsc);
        return count;

    }

    @Override
    public List<PSIFPDTO> find(BeanSearchCriteria bsc, int i, int i1, List<OrderByColumn> list) {
        List<PSIFPDTO> resultList = new ArrayList<>();
        return resultList;

    }
}
