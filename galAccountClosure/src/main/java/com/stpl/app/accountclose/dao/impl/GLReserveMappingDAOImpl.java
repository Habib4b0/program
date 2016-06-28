/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dao.impl;

import com.stpl.app.accountclose.dao.GLReserveMappingDAO;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;

/**
 *
 * @author kasiammal.m
 */
public class GLReserveMappingDAOImpl implements GLReserveMappingDAO {

    public List<HelperTable> getHelperTableList(final DynamicQuery dynamicQuery) throws SystemException {
        return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    public List exceuteSelectQuery(String query, Object field1, Object field2) {
       return (List) CompanyMasterLocalServiceUtil.executeSelectQuery(query, field1, field2);
    }
    
    public Object exceuteUpdateQuery(String query, String field1, String field2, List field3) {
       return CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
    }
}

