/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.dao;

import com.stpl.app.gcm.copycontract.dto.PSIFPDTO;
import com.stpl.app.gcm.copycontract.logic.CopyContractLogic;
import java.util.List;
import org.asi.ui.addons.lazycontainer.BeanDAO;
import org.asi.ui.addons.lazycontainer.BeanSearchCriteria;
import org.asi.ui.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author kasiammal.m
 */
public class RSSearchDAO implements BeanDAO<PSIFPDTO> {

    CopyContractLogic logic = new CopyContractLogic();

    public int count(BeanSearchCriteria bsc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<PSIFPDTO> find(BeanSearchCriteria bsc, int i, int i1, List<OrderByColumn> list) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
