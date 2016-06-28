package com.stpl.app.adminconsole.dao.impl;

import java.util.List;

import com.stpl.app.adminconsole.dao.HelperTableDAO;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

// TODO: Auto-generated Javadoc
/**
 * The Class HelperTableDAOImpl.
 */
public class HelperTableDAOImpl implements HelperTableDAO{
	 
 	/** (non-Javadoc)
 	 * @see com.stpl.app.adminconsole.dao.HelperTableDAO#getHelperTable(int)
 	 */
 	public HelperTable getHelperTable(final int helperTableSid) throws  PortalException, SystemException{		    
	        return HelperTableLocalServiceUtil.getHelperTable(helperTableSid);
	    }
 	public List getHelperID(final DynamicQuery dynamicQuery) throws  PortalException, SystemException
 	{
 		return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
 	}
}
