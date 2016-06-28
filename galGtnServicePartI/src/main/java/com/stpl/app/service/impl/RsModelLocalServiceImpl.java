package com.stpl.app.service.impl;

import java.util.List;
import java.util.Map;

import com.stpl.app.service.base.RsModelLocalServiceBaseImpl;
import com.stpl.app.service.persistence.RsModelFinderUtil;

/**
 * The implementation of the rs model local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.stpl.app.service.RsModelLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.RsModelLocalServiceBaseImpl
 * @see com.stpl.app.service.RsModelLocalServiceUtil
 */
public class RsModelLocalServiceImpl extends RsModelLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.stpl.app.global.service.RsModelLocalServiceUtil} to access the rs
	 * model local service.
	 */
	public java.util.List getItemDetailsOfIfp(java.lang.String ifpId) {
		return RsModelFinderUtil.getItemDetailsOfIfp(ifpId);
	}

	public java.util.List getRebateScheduleDetails(int rebateScheduleSystemId,Object future1, Object future2 ) {
		return RsModelFinderUtil
				.getRebateScheduleDetails(rebateScheduleSystemId,future1,future2);
	}

	public java.util.List getRebateScheduleDetailsUniqueCheck(
			java.lang.String rebateScheduleId, java.lang.String itemId,
			java.util.Date itemStartDate) {
		return RsModelFinderUtil.getRebateScheduleDetailsUniqueCheck(
				rebateScheduleId, itemId, itemStartDate);
	}

	public java.util.List getRebateScheduleDetailsUniqueCheckWithSysId(
			int rebateScheduleSystemId, java.lang.String rebateScheduleId,
			java.lang.String itemId, java.util.Date itemStartDate) {
		return RsModelFinderUtil
				.getRebateScheduleDetailsUniqueCheckWithSysId(
						rebateScheduleSystemId, rebateScheduleId, itemId,
						itemStartDate);
	}

	public List findRSModel(String rsId, String rsNo, String rsName,
			String rsStatus, String rpType, String itemId, String itemNo,
			String itemName, String future, Map<String, Object> parameters) {
		return RsModelFinderUtil.findRSModel(rsId, rsNo, rsName, rsStatus,
				rpType, itemId, itemNo, itemName, future, parameters);
	}

	public List findIFP(String ifpNumber, String ifpName, String ifpType,
			String ifpTypeString, String ifpStartDate, String ifpEndDate,
			String itemNo, String itemName, Map<String, Object> parameters) {
		return RsModelFinderUtil.findIFP(ifpNumber, ifpName, ifpType,
				ifpTypeString, ifpStartDate, ifpEndDate, itemNo, itemName,
				parameters);
	}

	public List getParentRsList(String rebateSchId, String rebateSchNo,
			String rebateSchName, String rebateSchStatus, String rebateSchType,
			String rebateProgType, String itemId, String itemNo,
			String itemName, int start, int offset, String column,
			String orderBy, Map<String, Object> parameters, boolean isCount) {
		return RsModelFinderUtil.getParentRsList(rebateSchId, rebateSchNo,
				rebateSchName, rebateSchStatus, rebateSchType, rebateProgType,
				itemId, itemNo, itemName, start, offset, column, orderBy,
				parameters, isCount);
	}

	public List getIfpList(String ifpNumber, String ifpName, Object ifpType,
			String ifpStartDate, String ifpEndDate, String itemNo,
			String itemName, int start, int offset, String column,
			String orderBy, Map<String, Object> parameters) {
		return RsModelFinderUtil.getIfpList(ifpNumber, ifpName, ifpType,
				ifpStartDate, ifpEndDate, itemNo, itemName, start, offset,
				column, orderBy, parameters);
	}

	public java.lang.Object executeSelectQuery(java.lang.String query,
			java.lang.Object udc1, java.lang.Object udc2) {
		return RsModelFinderUtil.executeSelectQuery(query, udc1, udc2);
	}

	public java.lang.Object executeUpdateQuery(
			java.util.List<java.lang.StringBuilder> queryList,
			java.lang.Object obj1, java.lang.Object obj2) {
		return RsModelFinderUtil.executeUpdateQuery(queryList, obj1, obj2);
	}
        
        public java.lang.Object executeUpdateQuery( java.lang.String queryList, java.lang.Object obj1, java.lang.Object obj2) {
        return RsModelFinderUtil.executeUpdateQuery(queryList, obj1, obj2);
    } 
}
