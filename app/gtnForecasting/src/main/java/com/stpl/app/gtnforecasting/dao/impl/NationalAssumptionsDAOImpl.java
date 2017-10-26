/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dao.impl;

import com.stpl.app.gtnforecasting.dao.NationalAssumptionsDAO;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.NaProjDetails;
import com.stpl.app.model.StNewNdc;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.app.service.NaProjDetailsLocalServiceUtil;
import com.stpl.app.service.StNewNdcLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;





/**
 * DAO operations for National Assumptions
 *
 * @author Vinodhini
 */
public class NationalAssumptionsDAOImpl implements NationalAssumptionsDAO {

    public List getBrandList(final DynamicQuery brandQuery) throws PortalException, SystemException {
        return BrandMasterLocalServiceUtil.dynamicQuery(brandQuery);
    }

    public List getItemList(final DynamicQuery therapeuticQuery) throws PortalException, SystemException {
        return ItemMasterLocalServiceUtil.dynamicQuery(therapeuticQuery);
    }
    public  List<HelperTable> getHelperTable(final DynamicQuery dynamicQuery)
			throws PortalException, SystemException{
            return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
      }
    public  List<ItemMaster> getItemMaster(final DynamicQuery dynamicQuery)
			throws PortalException, SystemException{
           return ItemMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
      }
    public  List<NaProjDetails> getNaProjDetails(final DynamicQuery dynamicQuery)
			throws PortalException, SystemException{
               return NaProjDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
        }
  public List<StNewNdc> getStNewNdc(final DynamicQuery dynamicQuery)
			throws PortalException, SystemException{
        return StNewNdcLocalServiceUtil.dynamicQuery(dynamicQuery);
        
  }
  
}
