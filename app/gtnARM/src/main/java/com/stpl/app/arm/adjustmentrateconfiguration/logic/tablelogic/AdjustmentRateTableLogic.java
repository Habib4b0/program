/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentrateconfiguration.logic.tablelogic;

import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Container;
import java.util.Map;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;

/**
 *
 * @author
 */
public class AdjustmentRateTableLogic extends PageTreeTableLogic {

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        return null;
    }

    @Override
    public int getCount() {
        return NumericConstants.TWELVE;
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
        return null;
    }

}
