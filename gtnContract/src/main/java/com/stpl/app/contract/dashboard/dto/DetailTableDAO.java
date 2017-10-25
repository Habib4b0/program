/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.dashboard.dto;

import com.stpl.app.contract.dashboard.logic.DashboardComponentSearchLogic;
import java.util.List;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author shrihariharan
 */
public class DetailTableDAO implements BeanDAO<DetailSearchDTO> {
    private DashboardComponentSearchLogic logic = new DashboardComponentSearchLogic();
    private int modelSid;
    private String component;
    public DetailTableDAO(int modelSid,String component){
        this.modelSid=modelSid;
        this.component=component;
    }
    
    @Override
    public int count(BeanSearchCriteria bsc) {
        return logic.getComponentDetailsSearchCount(modelSid, component, bsc);
    }

    @Override
    public List<DetailSearchDTO> find(BeanSearchCriteria bsc, int start, int offset, List<OrderByColumn> list) {
        return logic.getComponentDetailsSearch(modelSid, component,bsc, start, offset);
    }
    
}
