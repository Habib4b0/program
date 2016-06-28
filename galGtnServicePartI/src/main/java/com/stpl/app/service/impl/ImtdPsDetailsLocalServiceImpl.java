package com.stpl.app.service.impl;

import java.util.List;
import java.util.Map;

import com.stpl.app.service.base.ImtdPsDetailsLocalServiceBaseImpl;
import com.stpl.app.service.persistence.ImtdPsDetailsFinderUtil;

/**
 * The implementation of the imtd ps details local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.stpl.app.service.ImtdPsDetailsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.ImtdPsDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.ImtdPsDetailsLocalServiceUtil
 */
public class ImtdPsDetailsLocalServiceImpl extends
		ImtdPsDetailsLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.stpl.app.global.service.ImtdPsDetailsLocalServiceUtil} to access the
	 * imtd ps details local service.
	 */

	public Object deleteAll(String userId, String sessionId,
			String dateCreated, String operation, Object future1,
			Object future2, Object future3, Object future4) {
		return ImtdPsDetailsFinderUtil.deleteAll(userId, sessionId,
				dateCreated, operation, future1, future2, future3, future4);
	}

	public Object updateOperation(String userId, String sessionId,
			String createdDate, String operation, Object future1,
			Object future2, Object future3, Object future4) {
		return ImtdPsDetailsFinderUtil.updateOperation(userId, sessionId,
				createdDate, operation, future1, future2, future3, future4);
	}

	public Object updateForPopulate(String userId, String sessionId,
			String createdDate, String operation, Object future1,
			Object future2, Object future3, Object future4) {
		return ImtdPsDetailsFinderUtil.updateForPopulate(userId, sessionId,
				createdDate, operation, future1, future2, future3, future4);
	}

	public Object updateForPopulateAll(String userId, String sessionId,
			String createdDate, String operation, Object future1,
			Object future2, Object future3, Object future4) {
		return ImtdPsDetailsFinderUtil.updateForPopulateAll(userId, sessionId,
				createdDate, operation, future1, future2, future3, future4);
	}

	public List getIFPLazyList(int start, int offset, Object companyIdList,
			Object operation, Object searchValue) {
		return ImtdPsDetailsFinderUtil.getIfpLazyList(start, offset,
				companyIdList, operation, searchValue);

	}

	public Object updateToPsDetails(int ifpMasterSystemId, String userId,
			String sessionId, String createdDate, String operation,
			Object future1, Object future2, Object future3, Object future4) {
		return ImtdPsDetailsFinderUtil.updateToPsDetails(ifpMasterSystemId,
				userId, sessionId, createdDate, operation, future1, future2,
				future3, future4);
	}

	public Object insertTempPsDetailsInADD(String userId, String sessionId,
			String createdDate, String operation, Object future1,
			Object future2, Object future3, Object future4) {
		return ImtdPsDetailsFinderUtil.insertTempPsDetailsInADD(userId,
				sessionId, createdDate, operation, future1, future2, future3,
				future4);
	}

	public Object insertTempPsDetailsInEdit(String userId, String sessionId,
			String createdDate, String operation, Object future1,
			Object future2, Object future3, Object future4) {
		return ImtdPsDetailsFinderUtil.insertTempPsDetailsInEdit(userId,
				sessionId, createdDate, operation, future1, future2, future3,
				future4);
	}

	public Object validateTempPSDeatils(String userId, String sessionId,
			String createdDate, String operation, Object future1,
			Object future2, Object future3, Object future4) {
		return ImtdPsDetailsFinderUtil.validateTempPSDeatils(userId, sessionId,
				createdDate, operation, future1, future2, future3, future4);
	}

	public Object getTempCFPLazyList(String userId, String sessionId,
			String createdDate, String operation, Object future1,
			Object future2, Object future3, Object future4) {
		return ImtdPsDetailsFinderUtil.getTempPSLazyList(userId, sessionId,
				createdDate, operation, future1, future2, future3, future4);
	}

	public List getItemPriceDetails(int start, int offset, String userId,
			String sessionId, String columnName, String orderBy,
			Map<String, Object> parameters) {
		return ImtdPsDetailsFinderUtil.getItemPriceDetails(start, offset,
				userId, sessionId, columnName, orderBy, parameters);
	}
        public Object executeUpdateQuery(String queryList, Object obj1, Object obj2) {
          return ImtdPsDetailsFinderUtil.executeUpdateQuery(queryList, obj1, obj2);
      }
}
