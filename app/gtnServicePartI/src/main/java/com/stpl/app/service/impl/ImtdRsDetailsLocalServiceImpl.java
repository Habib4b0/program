package com.stpl.app.service.impl;

import java.util.List;
import java.util.Map;

import com.stpl.app.service.base.ImtdRsDetailsLocalServiceBaseImpl;
import com.stpl.app.service.persistence.ImtdRsDetailsFinderUtil;

/**
 * The implementation of the imtd rs details local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.stpl.app.service.ImtdRsDetailsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.ImtdRsDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.ImtdRsDetailsLocalServiceUtil
 */
public class ImtdRsDetailsLocalServiceImpl extends
		ImtdRsDetailsLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.stpl.app.global.service.ImtdRsDetailsLocalServiceUtil} to access the
	 * imtd rs details local service.
	 */
	public java.lang.Object deleteAll(java.lang.String userId,
			java.lang.String sessionId, java.lang.String dateCreated,
			java.lang.String operation, java.lang.Object future1,
			java.lang.Object future2, java.lang.Object future3,
			java.lang.Object future4) {
		return ImtdRsDetailsFinderUtil.deleteAll(userId, sessionId,
				dateCreated, operation, future1, future2, future3, future4);
	}

	public java.lang.Object updateOperation(java.lang.String userId,
			java.lang.String sessionId, java.lang.String createdDate,
			java.lang.String operation, java.lang.Object future1,
			java.lang.Object future2, java.lang.Object future3,
			java.lang.Object future4) {
		return ImtdRsDetailsFinderUtil.updateOperation(userId, sessionId,
				createdDate, operation, future1, future2, future3, future4);
	}

	public java.lang.Object updateForPopulate(java.lang.String userId,
			java.lang.String sessionId, java.lang.String createdDate,
			java.lang.String operation, java.lang.Object populateField,
			java.lang.Object populateValue, java.lang.Object future3,
			java.lang.Object future4) {
		return ImtdRsDetailsFinderUtil.updateForPopulate(userId, sessionId,
				createdDate, operation, populateField, populateValue, future3,
				future4);
	}

	public java.lang.Object updateForPopulateAll(java.lang.String userId,
			java.lang.String sessionId, java.lang.String createdDate,
			java.lang.String operation, java.lang.Object populateField,
			java.lang.Object populateValue, java.lang.Object future3,
			java.lang.Object future4) {
		return ImtdRsDetailsFinderUtil.updateForPopulateAll(userId, sessionId,
				createdDate, operation, populateField, populateValue, future3,
				future4);
	}

	public java.util.List getIfpLazyList(int start, int offset,
			java.lang.Object ifpIdList, java.lang.Object operation,
			java.lang.Object searchValue) {
		return ImtdRsDetailsFinderUtil.getIfpLazyList(start, offset, ifpIdList,
				operation, searchValue);
	}

	public java.lang.Object updateToRsDetails(int ifpMasterSystemId,
			java.lang.String userId, java.lang.String sessionId,
			java.lang.String createdDate, java.lang.String flag,
			java.lang.Object future1, java.lang.Object future2,
			java.lang.Object future3, java.lang.Object future4) {
		return ImtdRsDetailsFinderUtil.updateToRsDetails(ifpMasterSystemId,
				userId, sessionId, createdDate, flag, future1, future2,
				future3, future4);
	}

	public java.lang.Object insertTempRsDetailsInADD(java.lang.String userId,
			java.lang.String sessionId, java.lang.String createdDate,
			java.lang.String ifpSystemId, java.lang.Object future1,
			java.lang.Object future2, java.lang.Object future3,
			java.lang.Object future4) {
		return ImtdRsDetailsFinderUtil.insertTempRsDetailsInADD(userId,
				sessionId, createdDate, ifpSystemId, future1, future2, future3,
				future4);
	}

	public java.lang.Object insertTempRsDetailsInEdit(java.lang.String userId,
			java.lang.String sessionId, java.lang.String createdDate,
			java.lang.String rsSystemId, java.lang.Object future1,
			java.lang.Object future2, java.lang.Object future3,
			java.lang.Object future4) {
		return ImtdRsDetailsFinderUtil.insertTempRsDetailsInEdit(userId,
				sessionId, createdDate, rsSystemId, future1, future2, future3,
				future4);
	}

	public java.util.List getItemLazyList(int start, int offset,
			java.lang.Object companyIdList, java.lang.Object operation,
			java.lang.Object searchValue) {
		return ImtdRsDetailsFinderUtil.getItemLazyList(start, offset,
				companyIdList, operation, searchValue);
	}

	public java.lang.Object updateToIFPDetails(int ifpMasterSystemId,
			java.lang.String userId, java.lang.String sessionId,
			java.lang.String createdDate, java.lang.String operation,
			java.lang.Object future1, java.lang.Object future2,
			java.lang.Object future3, java.lang.Object future4) {
		return ImtdRsDetailsFinderUtil.updateToIFPDetails(ifpMasterSystemId,
				userId, sessionId, createdDate, operation, future1, future2,
				future3, future4);
	}

	public java.lang.Object insertTempIfpDetailsInADD(java.lang.String userId,
			java.lang.String sessionId, java.lang.String createdDate,
			java.lang.String operation, java.lang.Object future1,
			java.lang.Object future2, java.lang.Object future3,
			java.lang.Object future4) {
		return ImtdRsDetailsFinderUtil.insertTempIfpDetailsInADD(userId,
				sessionId, createdDate, operation, future1, future2, future3,
				future4);
	}

	public java.lang.Object insertTempIfpDetailsInEdit(java.lang.String userId,
			java.lang.String sessionId, java.lang.String createdDate,
			java.lang.String operation, java.lang.Object future1,
			java.lang.Object future2, java.lang.Object future3,
			java.lang.Object future4) {
		return ImtdRsDetailsFinderUtil.insertTempIfpDetailsInEdit(userId,
				sessionId, createdDate, operation, future1, future2, future3,
				future4);
	}

	public java.lang.Object validateTempRSDeatils(java.lang.String userId,
			java.lang.String sessionId, java.lang.String createdDate,
			java.lang.String operation, java.lang.Object future1,
			java.lang.Object future2, java.lang.Object future3,
			java.lang.Object future4) {
		return ImtdRsDetailsFinderUtil.validateTempRSDeatils(userId, sessionId,
				createdDate, operation, future1, future2, future3, future4);
	}

	public java.lang.Object getTempRSLazyList(java.lang.String rsId,
			java.lang.String sessionId, java.lang.String createdDate,
			java.lang.String operation, java.lang.Object future1,
			java.lang.Object future2, java.lang.Object future3,
			java.lang.Object future4) {
		return ImtdRsDetailsFinderUtil.getTempRSLazyList(rsId, sessionId,
				createdDate, operation, future1, future2, future3, future4);
	}

	public List loadIFPItems(java.lang.Integer start, java.lang.Integer end,
			java.lang.Object userID, java.lang.Object sessionID,
			java.lang.Object column, java.lang.Object orderBy,
			java.lang.Object value1, java.lang.Object value2,
			Map<String, Object> parameters) {
		return ImtdRsDetailsFinderUtil.loadIFPItems(start, end, userID,
				sessionID, column, orderBy, value1, value2, parameters);
	}

	public void mergeImtdRsDetailsFormula(int rsdSid, int itemSid,
			String userId, String sessionId) {

		ImtdRsDetailsFinderUtil.mergeImtdRsDetailsFormula(rsdSid, itemSid,
				userId, sessionId);
	}

	public void deleteTempTableRecords(int rsSid, int rsdSid, String userId,
			String sessionId) {
		ImtdRsDetailsFinderUtil.deleteTempTableRecords(rsSid, rsdSid, userId,
				sessionId);
	}

	public void insertFormulaToRsdFrImtd(int rsdSid, String userId,
			String sessionId, String createdDate) {
		ImtdRsDetailsFinderUtil.insertFormulaToRsdFrImtd(rsdSid, userId,
				sessionId, createdDate);
	}

	public void addAllFormulaToRsdFrImtd(int itemSid, String userId,
			String sessionId, String createdDate) {
		ImtdRsDetailsFinderUtil.addAllFormulaToRsdFrImtd(itemSid, userId,
				sessionId, createdDate);
	}

	public void remaoveAllFormulaToRsdFrImtd(int itemSid, String userId,
			String sessionId, String createdDate) {
		ImtdRsDetailsFinderUtil.remaoveAllFormulaToRsdFrImtd(itemSid, userId,
				sessionId, createdDate);
	}

	public void deleteRsdFr(int rsdSid) {
		ImtdRsDetailsFinderUtil.deleteRsdFr(rsdSid);
	}
}
