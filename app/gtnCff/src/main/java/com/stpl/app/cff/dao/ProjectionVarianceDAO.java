package com.stpl.app.cff.dao;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.dto.ProjectionSelectionDTO;
import com.stpl.app.cff.ui.projectionVariance.dto.ProjectionVarianceDTO;
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
	    				throws SystemException, PortalException;
	
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
	    				throws SystemException, PortalException;
	 

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
	    				throws SystemException, PortalException;

        /**
	  * Save or updates projection variance custom view hierarchies.
	  *
	  * @param indicator to indicate whether action is SAVE/UPDATE
	  * @param projectionId the projection id
	  */
	 public void saveProjectionVarianceView(final String indicator, final int projectionId);
 	
}
