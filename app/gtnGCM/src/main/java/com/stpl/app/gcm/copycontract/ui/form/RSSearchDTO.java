/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.ui.form;

import com.stpl.app.gcm.copycontract.dto.RsIfpDto;
import com.stpl.app.gcm.copycontract.logic.CopyContractLogic;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.asi.ui.addons.lazycontainer.BeanDAO;
import org.asi.ui.addons.lazycontainer.BeanSearchCriteria;

/**
 *
 * @author kasiammal.m
 */
class RSSearchDTO implements BeanDAO {
 public static final Logger LOGGER = LoggerFactory.getLogger(RSSearchDTO.class);
   
    private final CopyContractLogic logic = new CopyContractLogic();
    private RsIfpDto RsIfpDto = new RsIfpDto();

    RSSearchDTO(RsIfpDto RsIfpDto) {
        this.RsIfpDto = RsIfpDto;
    }

 @Override
    public int count(BeanSearchCriteria bsc) {
        int count = 0;
        count = logic.getRSSearchCount(RsIfpDto, bsc);
        return count;
    }

 @Override
    public List find(BeanSearchCriteria bsc, int i, int i1, List list) {
        List<RsIfpDto> resultList = new ArrayList<>();
        return resultList;
    }
}
