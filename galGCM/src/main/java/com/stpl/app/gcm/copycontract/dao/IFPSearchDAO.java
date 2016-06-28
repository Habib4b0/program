/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.dao;

import com.stpl.app.gcm.copycontract.dto.IFPItemDTO;
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
public class IFPSearchDAO implements BeanDAO<IFPItemDTO> {
private static final Logger LOGGER = Logger.getLogger(IFPSearchDAO.class);
    CopyContractLogic logic = new CopyContractLogic();
    IFPItemDTO IFPItemDTO = new IFPItemDTO();

    public IFPSearchDAO(IFPItemDTO IFPItemDTO) {
        this.IFPItemDTO = IFPItemDTO;
    }

    public int count(BeanSearchCriteria bsc) {
        int count = 0;
        count = logic.getIFPSearchCount(IFPItemDTO, bsc);
        return count;

    }

    public List<IFPItemDTO> find(BeanSearchCriteria bsc, int i, int i1, List<OrderByColumn> list) {
        List<IFPItemDTO> resultList = new ArrayList<IFPItemDTO>();
        return resultList;
    }
}
