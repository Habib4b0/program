/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.dao.impl;


import java.util.List;

import com.stpl.app.model.HelperTable;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.RebatePlanMasterLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.domain.contract.contractdashboard.RebateScheduleDAO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

/**
 *
 * @author sibi
 */
public class RebateScheduleLogicDAOImpl implements RebateScheduleDAO {

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 * 
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 */
	public List dynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
		return RsModelLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows that match the dynamic query
	 * 
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 */
	public long dynamicQueryCount(final DynamicQuery dynamicQuery) throws SystemException {
		return RsModelLocalServiceUtil.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rebate schedule masters.
	 * 
	 * @return
	 * @throws SystemException
	 */
	public int getRebateScheduleMastersCount() throws SystemException {
		return RsModelLocalServiceUtil.getRsModelsCount();
	}

	/**
	 * Returns a list of Helper Table.
	 * 
	 * @param type
	 * @return
	 * @throws SystemException
	 */
	public List<HelperTable> findByHelperTableDetails(final String type) throws SystemException {
		return HelperTableLocalServiceUtil.findByHelperTableDetails(type);
	}

        /**
     *
     * @param dynamicQuery
     * @return
     */
    public List<Object[]> getRebatePlanList(DynamicQuery dynamicQuery) throws PortalException, SystemException {
        return RebatePlanMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

}
