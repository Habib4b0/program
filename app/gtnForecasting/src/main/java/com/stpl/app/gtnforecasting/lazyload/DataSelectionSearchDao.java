/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.lazyload;

import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author soundarrajan.l
 */
public class DataSelectionSearchDao implements BeanDAO<DataSelectionDTO> {

    DataSelectionDTO dataSelectionDTO;
    NonMandatedLogic logic = new NonMandatedLogic();

    public DataSelectionSearchDao(DataSelectionDTO dataSelectionDTO) {
        this.dataSelectionDTO = dataSelectionDTO;
    }

    @Override
    public int count(BeanSearchCriteria sc) {
        int count = 0;

        return count;
    }

    @Override
    public List<DataSelectionDTO> find(BeanSearchCriteria sc, int startIndex, int offset, List<OrderByColumn> orderByColumns) {
        List<DataSelectionDTO> resultList = new ArrayList<>();

        return resultList;
    }

}
