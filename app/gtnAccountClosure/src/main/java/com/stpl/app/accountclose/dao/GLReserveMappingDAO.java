/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dao;

import com.stpl.app.model.HelperTable;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;

/**
 *
 * @author kasiammal.m
 */
public interface GLReserveMappingDAO {
      List<HelperTable> getHelperTableList(DynamicQuery dynamicQuery) throws SystemException;
      List exceuteSelectQuery(String query, Object field1, Object field2);
      Object exceuteUpdateQuery(String query, String field1, String field2, List field3);
    
}
