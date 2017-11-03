/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.dao.impl;

import com.stpl.app.adminconsole.dao.CommonDAO;
import com.stpl.app.model.HelperTable;
import com.stpl.app.parttwo.service.AccClosureMasterLocalServiceUtil;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.ForecastingMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.HierarchyDefinitionLocalServiceUtil;
import com.stpl.app.service.RelationshipBuilderLocalServiceUtil;
import com.stpl.app.service.VwUserTablesLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.service.UserLocalServiceUtil;
import java.util.List;

/**
 *
 * @author mohamed.hameed
 */
public class CommonDAOImpl implements CommonDAO {

    @Override
    public Object executeSelectQuery(String query, Object udc1, Object udc2) {

        return ForecastingMasterLocalServiceUtil.executeSelectQuery(query, udc1, udc2);
    }

    @Override
    public Object executeBulkUpdateQuery(String query, Object udc1, Object udc2) {
        return ForecastingMasterLocalServiceUtil.executeBulkUpdateQuery(query, udc1, udc2);
    }

    @Override
    public Object executeSelectQuery(List input, String queryName1, String queryName2) {
        return AccClosureMasterLocalServiceUtil.executeSelectQuery(input, queryName1, queryName2);
    }

    /**
     *
     * @param query
     * @return
     */
    public int executeUpdateQuery(String query) {

        return CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
    }

    /**
     *
     * @param query
     * @return
     */
    public Object executeSelectQuery(String query) {

        return CompanyMasterLocalServiceUtil.executeSelectQuery(query, null, null);
    }

    /**
     * This method will retrieve the values from Helper Table based on the
     * listName
     *
     * @param listName
     * @return list of type HelperTable
     * @throws SystemException
     */

    public List<HelperTable> getHelperTableDetailsByListName(String listName) throws SystemException {
        return HelperTableLocalServiceUtil.findByHelperTableDetails(listName);
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
        return RelationshipBuilderLocalServiceUtil.findByTableName(tableName, fieldName);
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
