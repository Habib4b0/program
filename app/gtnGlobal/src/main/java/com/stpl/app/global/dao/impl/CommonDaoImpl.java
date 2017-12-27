/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.dao.impl;

import com.stpl.app.global.dao.CommonDao;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ItemQualifierLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
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

    public List executeSelect(String query) {
        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }
    
    public List getBrandList(final DynamicQuery ifpDynamicQuery) throws PortalException, SystemException {
        return BrandMasterLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
    }
    
    public List itemIrtQualifierNameList(final DynamicQuery ifpDynamicQuery) throws PortalException,SystemException{
        return ItemQualifierLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
    }
    
     /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of HelperTable
     * @return list of HelperTable
     * @throws SystemException 
     */
    public List<HelperTable> getHelperTableList(final DynamicQuery query) throws PortalException,SystemException {
        return HelperTableLocalServiceUtil.dynamicQuery(query);
    }
    public List<HelperTable> getHelperTableDetailsByListName() throws SystemException {
        return HelperTableLocalServiceUtil.getHelperTables(0, HelperTableLocalServiceUtil.getHelperTablesCount());
    }
}
