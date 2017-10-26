package com.stpl.app.service.impl;

import java.util.List;

import com.stpl.app.service.base.HelperTableLocalServiceBaseImpl;
import com.stpl.app.service.persistence.HelperTableFinderUtil;
import com.stpl.app.service.persistence.HelperTableUtil;

/**
 * The implementation of the helper table local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.stpl.app.service.HelperTableLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.HelperTableLocalServiceBaseImpl
 * @see com.stpl.app.service.HelperTableLocalServiceUtil
 */
public class HelperTableLocalServiceImpl extends
		HelperTableLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.stpl.app.global.service.HelperTableLocalServiceUtil} to access the
	 * helper table local service.
	 */
	public java.util.List<com.stpl.app.model.HelperTable> findByHelperTableDetails(
			java.lang.String listName)
			throws com.stpl.portal.kernel.exception.SystemException {
		return HelperTableUtil.findByHelperTableDetails(listName);
	}
	
	/**
	    * Custom query to select records from data base
	    * @param query - Framed SQL Query
	    * @return List<Object[]> result list
	    */
	    public List executeSelectQuery(String query) {
	       return HelperTableFinderUtil.executeSelectQuery(query);
	    }
	    
	    /**
	     *  Updates sql statement
	     * @param query
	     */
	    public void executeUpdateQuery(String query) {
	         HelperTableFinderUtil.executeUpdateQuery(query);
	     }
            
            public int executeUpdateQueryCount(String query) {
               return HelperTableFinderUtil.executeUpdateQueryCount(query);
            }
            
}
