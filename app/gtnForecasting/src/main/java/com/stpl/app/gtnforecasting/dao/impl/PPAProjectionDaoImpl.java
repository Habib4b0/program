package com.stpl.app.gtnforecasting.dao.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.PPAProjectionDao;
import com.stpl.app.gtnforecasting.service.finderimpl.NmPpaProjectionMasterImpl;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ItemPricingQualifierLocalServiceUtil;
import com.stpl.ifs.util.constants.BooleanConstant;
import java.util.Collections;
import java.util.List;

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
			String level, String discountLevel) throws PortalException {
		return Collections.emptyList();
	}
     @Override
    public void massUpdate(Object priceCap, int startQuater, int endQuater, int startYear, int endYear, int projectionId,String levelValue) {
         new NmPpaProjectionMasterImpl().setPPAProjectionMassUpdate(priceCap,startQuater,endQuater,startYear,endYear,projectionId);
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
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        return BooleanConstant.getTrueFlag();
    }
 /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of ItemPricingQualifier
     * @return list of ItemPricingQualifier
     * @throws SystemException 
     */
        @Override
    public List getItemPricingTypeList(final DynamicQuery query) throws SystemException {
       return ItemPricingQualifierLocalServiceUtil.dynamicQuery(query);
    }
}
