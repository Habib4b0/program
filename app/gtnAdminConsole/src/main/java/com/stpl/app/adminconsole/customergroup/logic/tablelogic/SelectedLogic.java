/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.customergroup.logic.tablelogic;

import com.stpl.app.adminconsole.customergroup.dto.CustomerDetailsDTO;
import com.stpl.app.adminconsole.customergroup.logic.CustomerGroupLogic;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author Mohamed.Shahul
 */
public class SelectedLogic implements TableLogic {

    CustomerGroupLogic logic = new CustomerGroupLogic();

    @Override
    public int getCount(CustomerDetailsDTO dto, Set<Container.Filter> filterSet, List<SortByColumn> sortedSet) {
        int count = 0;
        if (!dto.isEditMode()) {
            dto.setQueryName("getSelectedCustomerDetailsCount");
        } else {
            dto.setQueryName("getSelectedCompanyListCount");
        }

        dto.setAvailableContainer(Boolean.FALSE);
        if (!dto.getMaster_Sid_List().isEmpty()) {
            count = logic.getCompaniesCount(dto, filterSet);
        }
        return count;
    }

    @Override
    public List loadData(CustomerDetailsDTO dto, Set<Container.Filter> filterSet, List<SortByColumn> sortedSet) throws SystemException, PortalException, ParseException {
        if (!dto.isEditMode()) {
            dto.setQueryName("getSelectedCustomerDetails");
        } else {
            dto.setQueryName("getSelectedCompanyList");
        }
        dto.setAvailableContainer(Boolean.FALSE);
        List<CustomerDetailsDTO> resultList = logic.getCompaniesResults(dto, filterSet, sortedSet);
        return resultList;
    }

}
