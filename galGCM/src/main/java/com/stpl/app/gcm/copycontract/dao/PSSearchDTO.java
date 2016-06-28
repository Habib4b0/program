/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.dao;

import com.stpl.app.gcm.copycontract.dto.PSIFPDTO;
import com.stpl.app.gcm.copycontract.logic.CopyContractLogic;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author kasiammal.m
 */
public class PSSearchDTO implements BeanDAO<PSIFPDTO> {
private static final Logger LOGGER = Logger.getLogger(PSSearchDTO.class);
    CopyContractLogic logic = new CopyContractLogic();
    PSIFPDTO PSIFPDTO = new PSIFPDTO();

    public PSSearchDTO(PSIFPDTO PSIFPDTO) {
        this.PSIFPDTO = PSIFPDTO;
    }

    public int count(BeanSearchCriteria bsc) {

        int count = 0;
        count = logic.getPSSearchCount(PSIFPDTO, bsc);
        return count;

    }

    public List<PSIFPDTO> find(BeanSearchCriteria bsc, int i, int i1, List<OrderByColumn> list) {
        List<PSIFPDTO> resultList = new ArrayList<PSIFPDTO>();
        return resultList;

    }
}
