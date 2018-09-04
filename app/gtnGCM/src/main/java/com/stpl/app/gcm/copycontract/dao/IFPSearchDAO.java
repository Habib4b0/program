/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.dao;

import com.stpl.app.gcm.copycontract.dto.IFPItemDTO;
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
public class IFPSearchDAO implements BeanDAO<IFPItemDTO> {
    private final CopyContractLogic logic = new CopyContractLogic();
    private IFPItemDTO ifpItemDTO = new IFPItemDTO();

    public IFPSearchDAO(IFPItemDTO ifpItemDto) {
        this.ifpItemDTO = ifpItemDto;
    }

    @Override
    public int count(BeanSearchCriteria bsc) {
        int count = 0;
        count = logic.getIFPSearchCount(ifpItemDTO, bsc);
        return count;

    }

    @Override
    public List<IFPItemDTO> find(BeanSearchCriteria bsc, int i, int i1, List<OrderByColumn> list) {
        List<IFPItemDTO> resultList = new ArrayList<>();
        return resultList;
    }
}
