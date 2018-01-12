/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.dao.impl;

import com.stpl.app.global.dao.CommonDao;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ItemQualifierLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;

/**
 *
 * @author mohamed.hameed
 */
public class CommonDaoImpl implements CommonDao {

    private static final CommonDaoImpl dao = new CommonDaoImpl();

    private CommonDaoImpl() {
    }

    public static CommonDaoImpl getInstance() {
        return dao;
    }

    @Override
    public List executeSelect(String query) {
        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    @Override
    public Object executeUpdate(String query) {
        return CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
    }
    
    @Override
    public List getBrandList(final DynamicQuery ifpDynamicQuery) throws PortalException, SystemException {
        return BrandMasterLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
    }
    
    @Override
    public List itemIrtQualifierNameList(final DynamicQuery ifpDynamicQuery) throws PortalException,SystemException{
        return ItemQualifierLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
    }
    
     /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of HelperTable
     * @return list of HelperTable
     * @throws SystemException 
     */
    @Override
    public List<HelperTable> getHelperTableList(final DynamicQuery query) throws PortalException,SystemException {
        return HelperTableLocalServiceUtil.dynamicQuery(query);
    }
    @Override
    public List<HelperTable> getHelperTableDetailsByListName() throws SystemException {
        return HelperTableLocalServiceUtil.getHelperTables(0, HelperTableLocalServiceUtil.getHelperTablesCount());
    }
}
