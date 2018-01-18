package com.stpl.app.gtnforecasting.dao;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import java.util.List;

public interface PPAProjectionDao {

	/**
  	 * Gets the discount contract holder summary.
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
  	List getContractHolderSummary(final int projectionId, final String indicator,
	    		final List<Integer> comparisonProjections, final String frequency, final String level, final String discountLevel)
	    				throws SystemException, PortalException;

        /**
         * 
         * @param string
         * @throws SystemException
         * @throws PortalException
         * @throws Exception 
         */
    public List getPPAProjection(Integer projectionId,int levelNo,String parent,boolean last,int startIndex,int endIndex,boolean isCount,String levelName) throws SystemException, PortalException;
    public void massUpdate(Object priceCap, int startQuater, int endQuater, int startYear, int endYear, int projectionId,String parent,String levelValue);
    public void saveCheckRecord(int startQuater, int endQuater, int startYear, int endYear, Double priceCap); 
    public Object executeSelect(String query); 
    public Object executeUpdate(String query); 
/** 
	 * <!-- begin-UML-doc -->
	 * Performs&nbsp;a&nbsp;dynamic&nbsp;query&nbsp;on&nbsp;the&nbsp;database&nbsp;and&nbsp;returns&nbsp;the&nbsp;matching&nbsp;rows.<br><br>@param&nbsp;query<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;dynamic&nbsp;query&nbsp;of&nbsp;ItemPricingQualifier<br>@return&nbsp;list&nbsp;of&nbsp;ItemPricingQualifier<br>@throws&nbsp;SystemException
	 * <!-- end-UML-doc -->
	 * @param query
	 * @return
	 * @throws SystemException
	 * @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List getItemPricingTypeList(DynamicQuery query) throws SystemException;	
}
