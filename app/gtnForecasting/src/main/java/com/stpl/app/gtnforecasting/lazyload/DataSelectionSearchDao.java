/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.lazyload;

import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.addons.lazycontainer.BeanDAO;
import org.asi.ui.addons.lazycontainer.BeanSearchCriteria;
import org.asi.ui.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author soundarrajan.l
 */
public class DataSelectionSearchDao implements BeanDAO<DataSelectionDTO> {

    public DataSelectionSearchDao() {
        // Empty Constructor
    }

    @Override
    public int count(BeanSearchCriteria sc) {
        return 0;

    }

    @Override
    public List<DataSelectionDTO> find(BeanSearchCriteria sc, int startIndex, int offset, List<OrderByColumn> orderByColumns) {
        return new ArrayList<>();
    }

}
