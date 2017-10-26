package com.stpl.app.service.impl;

import java.util.List;
import java.util.Map;

import com.stpl.app.service.base.PsDetailsLocalServiceBaseImpl;
import com.stpl.app.service.persistence.PsDetailsFinderUtil;
import com.stpl.app.service.persistence.PsDetailsUtil;

/**
 * The implementation of the ps details local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.stpl.app.service.PsDetailsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.PsDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.PsDetailsLocalServiceUtil
 */
public class PsDetailsLocalServiceImpl extends PsDetailsLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.stpl.app.global.service.PsDetailsLocalServiceUtil} to access the ps
	 * details local service.
	 */

	public java.util.List getItemAndPricingForPs(int psSystemId) {
		return PsDetailsFinderUtil.getItemAndPricingForPs(psSystemId);
	}

	public java.util.List<com.stpl.app.model.PsDetails> findByPriceScheduleMasterDetails(
			int psModelSid)
			throws com.stpl.portal.kernel.exception.SystemException {
		return PsDetailsUtil.findByPriceScheduleMasterDetails(psModelSid);
	}

	public List getPsSearchList(String psId, String psNo, String psName,
			int psStatus, int psType, String itemId, String itemNo,
			String itemName, Map<String, Object> filterMap, int start, int end,
			String column, String orderBy, boolean isCount) {

		return PsDetailsFinderUtil.getPsSearchList(psId, psNo, psName,
				psStatus, psType, itemId, itemNo, itemName, filterMap, start,
				end, column, orderBy, isCount);
	}
}
