package com.stpl.app.gtnforecasting.dao;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionVarianceDTO;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface ProjectionVarianceDAO.
 */
public interface ProjectionVarianceDAO {
	
	
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
	    				throws PortalException;
	
	 /**
 	 * Generate projection variance.
 	 *
 	 * @param projectionId the projection id
 	 * @param indicator the indicator
 	 * @param comparisonProjections the comparison projections
 	 * @param frequency the frequency
 	 * @param level the level
 	 * @param discountLevel the discount level
 	 * @return the list
 	 * @throws SystemException the system exception
 	 * @throws PortalException the portal exception
 	 * @throws Exception the exception
 	 */
 	public List generateProjectionVariance(final int projectionId, final String indicator,
	    		final List<Integer> comparisonProjections, final String frequency, final String level, final String discountLevel,int levelNo,String sales, List<Integer> periods,ProjectionSelectionDTO projSelDTO) 
	    				throws PortalException;
	 

	 /**
	 * Generate Pivot projection variance.
	 *
	 * @param projectionId the projection id
	 * @param indicator the indicator
	 * @param comparisonProjections the comparison projections
	 * @param frequency the frequency
	 * @param level the level
	 * @param discountLevel the discount level
	 * @return the list
	 * @throws SystemException the system exception
	 * @throws PortalException the portal exception
	 * @throws Exception the exception
	 */
	public List<ProjectionVarianceDTO> generatePivotProjectionVariance(final int projectionId, final String indicator,
	    		final List<Integer> comparisonProjections, final String frequency, final String level, final String discountLevel) 
	    				throws PortalException;
	 
 	
	 /**
 	 * Search for projections in comparison lookup.
 	 *
 	 * @param workflowStatus the workflow status
 	 * @param marketType the market type
 	 * @param brand the brand
 	 * @param projName the proj name
 	 * @param contHldr the cont hldr
 	 * @param ndcNo the ndc no
 	 * @param ndcName the ndc name
 	 * @param desc the desc
 	 * @param contract the contract
 	 * @param from the from
 	 * @param to the to
 	 * @return the list
 	 * @throws SystemException the system exception
 	 * @throws PortalException the portal exception
 	 * @throws Exception the exception
 	 */
 	public List searchForProjections(final String workflowStatus, final String marketType, final String brand, 
			 final String projName, final String contHldr, final String ndcNo, final String ndcName, final String desc, 
			 final String contract, final String from, final String to) throws PortalException;
 	
 	
 	
 	/**
	  * Save or updates projection variance custom view hierarchies.
	  *
	  * @param indicator to indicate whether action is SAVE/UPDATE
	  * @param projectionId the projection id
	  */
	 public void saveProjectionVarianceView(final String indicator, final int projectionId);
 	
	/**Get the Projection Results Sales
     * 
     * @param projectionId
     * @param frequency
     * @param periods
     * @param actualsOrProjections
     * @param parentName
     * @param year
     * @param levelNo
     * @param sales
     * @return 
     */
    List getPVResultsSales(int projectionId, String frequency,List<Integer> periods, String actualsOrProjections,String parentName, String year, int levelNo, String sales); 
	
     /**Get the Projection Results Discount
     * 
     * @param projectionId
     * @param frequency
     * @param periods
     * @param discountTotal
     * @param parentName
     * @param discountList
     * @param year
     * @param levelNo
     * @param sales
     * @return 
     */
    List getPVResultsDiscount(int projectionId, String frequency,List<Integer> periods, String discountTotal,String parentName, List<String> discountList, String year, int levelNo, String sales);
}
