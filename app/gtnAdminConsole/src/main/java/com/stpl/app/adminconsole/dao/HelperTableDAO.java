package com.stpl.app.adminconsole.dao;

import java.util.List;

import com.stpl.app.model.HelperTable;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

 
/**
 * The Interface HelperTableDAO.
 */
public interface HelperTableDAO {
	
	/**
	 * Gets the helper table.
	 *
	 * @param helperTableSid the helper table sid
	 * @return the helper table
	 * @throws PortalException the portal exception
	 * @throws SystemException the system exception
	 */
	HelperTable getHelperTable(final int helperTableSid) throws  PortalException;
	
	List getHelperID(final DynamicQuery dynamicQuery) throws SystemException;
}
