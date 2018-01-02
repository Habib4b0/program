/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.dao;

import com.stpl.app.gcm.copycontract.dto.ExistingComponentDTO;
import com.stpl.app.gcm.copycontract.dto.RsIfpDto;
import com.stpl.app.gcm.copycontract.logic.CopyContractLogic;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/**
 *
 * @author kasiammal.m
 */
public class RSAttachedItemDAO implements BeanDAO {
private static final Logger LOGGER = Logger.getLogger(RSAttachedItemDAO.class);
    private final CopyContractLogic logic = new CopyContractLogic();
    private RsIfpDto RsIfpDto = new RsIfpDto();

    public RSAttachedItemDAO(RsIfpDto RsIfpDto) {
        this.RsIfpDto = RsIfpDto;
    }

@Override
    public int count(BeanSearchCriteria bsc) {
        int count = 0;
        count = logic.getRSAttachedItemSearchCount(RsIfpDto, bsc);
        return count;
    }

@Override
    public List find(BeanSearchCriteria bsc, int i, int i1, List list) {
        List<ExistingComponentDTO> resultList = new ArrayList<>();
        try {
            resultList = logic.getRSAttachedItemSearch(RsIfpDto, i, i1, bsc, list);
        } catch (ParseException ex) {
            LOGGER.error(ex);
        }
        return resultList;
    }
}
