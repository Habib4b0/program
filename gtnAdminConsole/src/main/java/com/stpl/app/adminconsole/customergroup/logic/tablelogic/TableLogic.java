/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.customergroup.logic.tablelogic;

import com.stpl.app.adminconsole.customergroup.dto.CustomerDetailsDTO;
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
public interface TableLogic {

    int getCount(CustomerDetailsDTO dto, Set<Container.Filter> filterSet, List<SortByColumn> sortedSet);

    List loadData(CustomerDetailsDTO dto, Set<Container.Filter> filterSet, List<SortByColumn> sortedSet) throws SystemException, PortalException, ParseException ;
}
