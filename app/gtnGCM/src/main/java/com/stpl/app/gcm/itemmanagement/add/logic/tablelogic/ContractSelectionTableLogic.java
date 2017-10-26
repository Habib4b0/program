/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.add.logic.tablelogic;

import com.stpl.app.gcm.itemmanagement.add.dto.ItemContractSelectionDTO;
import com.vaadin.data.Container;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author Abishekram.r
 */
public class ContractSelectionTableLogic extends PageTableLogic {

    ItemContractSelectionDTO binderDto = new ItemContractSelectionDTO();

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        return null;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        return null;
    }
}
