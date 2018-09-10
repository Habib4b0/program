/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.dao.impl;

import com.stpl.app.adminconsole.dao.CommonDAO;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.HierarchyDefinitionLocalServiceUtil;
import com.stpl.app.service.RelationshipBuilderLocalServiceUtil;
import com.stpl.app.service.VwUserTablesLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.adminconsole.service.AdminConsoleImpl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mohamed.hameed
 */
public class CommonDAOImpl implements CommonDAO {

    @Override
    public Object executeSelectQuery(String query, Object udc1, Object udc2) {

        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    @Override
    public Object executeSelectQuery(List input, String queryName1, String queryName2) {
        return new AdminConsoleImpl().executeSelectQuery(input, queryName1, queryName2);
    }

    /**
     *
     * @param query
     * @return
     */
    public Object executeSelectQuery(String query) {
        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    public List getHierachyDefinitionList(final DynamicQuery query) throws SystemException {
        return HierarchyDefinitionLocalServiceUtil.dynamicQuery(query);
    }

    public List getRelationshipBuilderList(final DynamicQuery query) throws SystemException {
        return RelationshipBuilderLocalServiceUtil.dynamicQuery(query);
    }

    public List getLookUpData(final DynamicQuery query) throws SystemException {
        return VwUserTablesLocalServiceUtil.dynamicQuery(query);
    }

    public List getColumnValues(String tableName, String fieldName) throws SystemException {
        return new ArrayList();
    }

    public List getHelperTableList(final DynamicQuery query) throws SystemException {
        return HelperTableLocalServiceUtil.dynamicQuery(query);
    }
    
    public List getUsersList(final DynamicQuery query) throws SystemException {
        return UserLocalServiceUtil.dynamicQuery(query);
    }
    
    public List getBrandNameandId(final DynamicQuery query) throws SystemException {
        return BrandMasterLocalServiceUtil.dynamicQuery(query);
    }
}
