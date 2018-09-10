/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.dao;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import java.util.List;

/**
 *
 * @author mohamed.hameed
 */
public interface CommonDAO {
 
    Object executeSelectQuery(String query, Object udc1, Object udc2);
   
    public Object executeSelectQuery(final List input, String queryName1, String queryName2);
   
    List getHierachyDefinitionList(DynamicQuery query) throws SystemException;
   
    List getRelationshipBuilderList(DynamicQuery query) throws SystemException;
    
    List getLookUpData(DynamicQuery query) throws SystemException;
    
    List getColumnValues(String tableName, String fieldName) throws SystemException;
    
    List getHelperTableList(DynamicQuery query) throws SystemException;
    
    List getUsersList(DynamicQuery query) throws SystemException;
    
    List getBrandNameandId(final DynamicQuery query) throws SystemException;
}
