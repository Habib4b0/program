/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.security.dao;

import java.util.List;

import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

/**
 *
 * @author santanukumar
 */
public interface UdcLogicDAO {
    public List<HelperTable>getListName(DynamicQuery dynamicQuery)throws SystemException;
     public List<HelperTable>getDescrition(String desc)throws SystemException;
     public List<HelperTable> findByHelperTableDetails(String category)throws SystemException;
     public HelperTable saveHelperTableDetails(HelperTable helperTable)throws SystemException;
     public HelperTable deleteHelperTableByCode(int code)throws SystemException,PortalException;
     public BrandMaster saveBrandTableDetails(BrandMaster brandTable)throws SystemException;
     public BrandMaster deleteBrandTableByCode(BrandMaster code)throws SystemException,PortalException;
     public BrandMaster getBrandMaster(int id)throws SystemException,PortalException;
}
