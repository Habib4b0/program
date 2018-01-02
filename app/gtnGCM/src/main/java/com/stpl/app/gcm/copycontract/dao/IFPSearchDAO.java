/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.dao;

import com.stpl.app.gcm.copycontract.dto.IFPItemDTO;
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
public class IFPSearchDAO implements BeanDAO<IFPItemDTO> {
    private final CopyContractLogic logic = new CopyContractLogic();
    private IFPItemDTO IFPItemDTO = new IFPItemDTO();

    public IFPSearchDAO(IFPItemDTO IFPItemDTO) {
        this.IFPItemDTO = IFPItemDTO;
    }

    @Override
    public int count(BeanSearchCriteria bsc) {
        int count = 0;
        count = logic.getIFPSearchCount(IFPItemDTO, bsc);
        return count;

    }

    @Override
    public List<IFPItemDTO> find(BeanSearchCriteria bsc, int i, int i1, List<OrderByColumn> list) {
        List<IFPItemDTO> resultList = new ArrayList<>();
        return resultList;
    }
}
