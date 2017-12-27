/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.ui.form;

import com.stpl.app.gcm.copycontract.dto.RsIfpDto;
import com.stpl.app.gcm.copycontract.logic.CopyContractLogic;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;
import org.asi.ui.addons.lazycontainer.BeanDAO;
import org.asi.ui.addons.lazycontainer.BeanSearchCriteria;

/**
 *
 * @author kasiammal.m
 */
class RSSearchDTO implements BeanDAO {
 public static final Logger LOGGER = Logger.getLogger(RSSearchDTO.class);
   
    CopyContractLogic logic = new CopyContractLogic();
    RsIfpDto RsIfpDto = new RsIfpDto();

    RSSearchDTO(RsIfpDto RsIfpDto) {
        this.RsIfpDto = RsIfpDto;
    }

    public int count(BeanSearchCriteria bsc) {
        int count = 0;
        count = logic.getRSSearchCount(RsIfpDto, bsc);
        return count;
    }

    public List find(BeanSearchCriteria bsc, int i, int i1, List list) {
        List<RsIfpDto> resultList = new ArrayList<>();
        return resultList;
    }
}
