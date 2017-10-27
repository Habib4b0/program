package com.stpl.app.gtnforecasting.dao.impl;

import java.util.List;

import com.stpl.app.gtnforecasting.dao.PPAProjectionDao;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ItemPricingQualifierLocalServiceUtil;
import com.stpl.app.service.NmPpaProjectionMasterLocalServiceUtil;
import com.stpl.app.service.NmSalesProjectionMasterLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.Collections;

public class PPAProjectionDaoImpl implements PPAProjectionDao {

	
	/**
  	 * Gets the PPA contract holder summary.
  	 *
  	 * @param projectionId the projection id
  	 * @param indicator the indicator
  	 * @param comparisonProjections the comparison projections
  	 * @param frequency the frequency
  	 * @param level the level
  	 * @param discountLevel the discount level
  	 * @return the contract holder summary
  	 * @throws SystemException the system exception
  	 * @throws PortalException the portal exception
  	 * @throws Exception the exception
  	 */
	@Override
	public List getContractHolderSummary(int projectionId, String indicator, List<Integer> comparisonProjections, String frequency,
			String level, String discountLevel) throws SystemException, PortalException {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}
/**
 * 
 * @param projectionId
 * @throws SystemException
 * @throws PortalException
 * @throws Exception 
 */
    @Override
    public List getPPAProjection(Integer projectionId,int levelNo,String parent,boolean last,int startIndex,int endIndex,boolean isCount,String levelName) throws SystemException, PortalException{
        return NmPpaProjectionMasterLocalServiceUtil.getPPAProjectionList(projectionId,levelNo,parent,last,startIndex,endIndex,isCount,levelName);
        
    }
     @Override
    public void massUpdate(Object priceCap, int startQuater, int endQuater, int startYear, int endYear, int projectionId,String parent,String levelValue) {
         NmPpaProjectionMasterLocalServiceUtil.setPPAProjectionMassUpdate(priceCap,startQuater,endQuater,startYear,endYear,projectionId, parent, levelValue);
    }

    @Override
    public void saveCheckRecord(int startQuater, int endQuater, int startYear, int endYear, Double priceCap) {
       return;
}

    @Override
    public Object executeSelect(String query) {
       return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    @Override
    public Object executeUpdate(String query) {
       return NmSalesProjectionMasterLocalServiceUtil.executeBulkUpdateQuery(query, null, null);
    }
 /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of ItemPricingQualifier
     * @return list of ItemPricingQualifier
     * @throws SystemException 
     */
    public List getItemPricingTypeList(final DynamicQuery query) throws SystemException {
       return ItemPricingQualifierLocalServiceUtil.dynamicQuery(query);
    }
}
