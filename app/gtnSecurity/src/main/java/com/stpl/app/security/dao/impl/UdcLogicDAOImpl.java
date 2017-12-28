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

    public List<HelperTable> getListName(DynamicQuery dynamicQuery) throws SystemException {
        return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    public List<HelperTable> getDescrition(String desc) throws SystemException {
        return new ArrayList<>();
    }

    public List<HelperTable> findByHelperTableDetails(String category) throws SystemException {
        return new ArrayList<>();
    }
    

    public HelperTable saveHelperTableDetails(HelperTable helperTable) throws SystemException {
       return HelperTableLocalServiceUtil.addHelperTable(helperTable);
    }

    public HelperTable deleteHelperTableByCode(int code) throws SystemException, PortalException {
        return HelperTableLocalServiceUtil.deleteHelperTable(code);
    }
    
    public BrandMaster saveBrandTableDetails(BrandMaster brandTable) throws SystemException {
       return BrandMasterLocalServiceUtil.addBrandMaster(brandTable);
    }
    
     public BrandMaster deleteBrandTableByCode(BrandMaster code) throws SystemException, PortalException {
        return BrandMasterLocalServiceUtil.updateBrandMaster(code);
    }
     
     public BrandMaster getBrandMaster(int id) throws SystemException, PortalException  {
        return BrandMasterLocalServiceUtil.getBrandMaster(id);
    }
}
