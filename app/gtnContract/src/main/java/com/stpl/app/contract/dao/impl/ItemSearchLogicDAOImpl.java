/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dao.impl;


import java.util.List;

import com.stpl.app.model.HelperTable;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.domain.contract.contractdashboard.ItemDAO;
import com.stpl.portal.kernel.exception.SystemException;

/**
 *
 * @author sibi
 */
public class ItemSearchLogicDAOImpl implements ItemDAO {

	/**
	 * Returns a list of Helper Table.
	 * 
	 * @param itemStatus
	 * @return
	 * @throws SystemException
	 */
	public List<HelperTable> findByHelperTableDetails(final String itemStatus) throws SystemException {
		return HelperTableLocalServiceUtil.findByHelperTableDetails(itemStatus);
	}

}
