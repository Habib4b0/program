/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.security.dao.impl;

import java.util.List;

import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.security.dao.UdcLogicDAO;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.ArrayList;

/**
 *
 * @author santanukumar
 */
public class UdcLogicDAOImpl implements UdcLogicDAO {

    @Override
    public List<HelperTable> getListName(DynamicQuery dynamicQuery) throws SystemException {
        return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List<HelperTable> getDescrition(String desc) throws SystemException {
        return new ArrayList<>();
    }

    @Override
    public List<HelperTable> findByHelperTableDetails(String category) throws SystemException {
        return new ArrayList<>();
    }
    

    @Override
    public HelperTable saveHelperTableDetails(HelperTable helperTable) throws SystemException {
       return HelperTableLocalServiceUtil.addHelperTable(helperTable);
    }

    @Override
    public HelperTable deleteHelperTableByCode(int code) throws SystemException, PortalException {
        return HelperTableLocalServiceUtil.deleteHelperTable(code);
    }
    
    @Override
    public BrandMaster saveBrandTableDetails(BrandMaster brandTable) throws SystemException {
       return BrandMasterLocalServiceUtil.addBrandMaster(brandTable);
    }
    
    @Override
     public BrandMaster deleteBrandTableByCode(BrandMaster code) throws SystemException, PortalException {
        return BrandMasterLocalServiceUtil.updateBrandMaster(code);
    }
     
    @Override
     public BrandMaster getBrandMaster(int id) throws SystemException, PortalException  {
        return BrandMasterLocalServiceUtil.getBrandMaster(id);
    }
}
