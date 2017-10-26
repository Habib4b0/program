package com.stpl.app.service.impl;

import java.util.List;
import java.util.Map;

import com.stpl.app.service.base.ImtdCfpDetailsLocalServiceBaseImpl;
import com.stpl.app.service.persistence.ImtdCfpDetailsFinderUtil;

/**
 * The implementation of the imtd cfp details local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.stpl.app.service.ImtdCfpDetailsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.ImtdCfpDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.ImtdCfpDetailsLocalServiceUtil
 */
public class ImtdCfpDetailsLocalServiceImpl extends
		ImtdCfpDetailsLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:	
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.stpl.app.global.service.ImtdCfpDetailsLocalServiceUtil} to access the
	 * imtd cfp details local service.
	 */
	public Object deleteAll(String userId, String sessionId,
			String dateCreated, String operation, Object future1,
			Object future2, Object future3, Object future4) {

		return ImtdCfpDetailsFinderUtil.deleteAll(userId, sessionId,
				dateCreated, operation, future1, future2, future3, future4);
	}

	public Object updateOperation(String userId, String sessionId,
			String createdDate, String operation, Object future1,
			Object future2, Object future3, Object future4) {
		return ImtdCfpDetailsFinderUtil.updateOperation(userId, sessionId,
				createdDate, operation, future1, future2, future3, future4);
	}

	public Object updateForPopulate(String userId, String sessionId,
			String createdDate, String operation, Object future1,
			Object future2, Object future3, Object future4) {
		return ImtdCfpDetailsFinderUtil.updateForPopulate(userId, sessionId,
				createdDate, operation, future1, future2, future3, future4);
	}

	public Object updateForPopulateAll(String userId, String sessionId,
			String createdDate, String operation, Object future1,
			Object future2, Object future3, Object future4) {
		return ImtdCfpDetailsFinderUtil.updateForPopulateAll(userId, sessionId,
				createdDate, operation, future1, future2, future3, future4);
	}

	public List getCompanyLazyList(int start, int offset, Object companyIdList,
			Object operation, Object searchValue, String column,String orderBy,
			Map<Object, Object> filterMap) {
		return ImtdCfpDetailsFinderUtil.getCompanyLazyList(start, offset,
				companyIdList, operation, searchValue,column,orderBy, filterMap);

	}

	public List getSelectedCompanies(int start, int offset, String userId,
			String sessionId, String columnName, String orderBy,
			Map<Object, Object> filterMap,String operation) {
		return ImtdCfpDetailsFinderUtil.getSelectedCompanies(start, offset,
				userId, sessionId, columnName, orderBy, filterMap,operation);
	}

	public Object updateToCFPDetails(int ifpMasterSystemId, String userId,
			String sessionId, String createdDate, String operation,
			Object future1, Object future2, Object future3, Object future4) {
		return ImtdCfpDetailsFinderUtil.updateToCFPDetails(ifpMasterSystemId,
				userId, sessionId, createdDate, operation, future1, future2,
				future3, future4);
	}

	public Object insertTempCfpDetailsInADD(String userId, String sessionId,
			String createdDate, String operation, Object future1,
			Object future2, Object future3, Object future4) {
		return ImtdCfpDetailsFinderUtil.insertTempCfpDetailsInADD(userId,
				sessionId, createdDate, operation, future1, future2, future3,
				future4);
	}

	public Object insertTempCfpDetailsInEdit(String userId, String sessionId,
			String createdDate, String operation, Object future1,
			Object future2, Object future3, Object future4) {
		return ImtdCfpDetailsFinderUtil.insertTempCfpDetailsInEdit(userId,
				sessionId, createdDate, operation, future1, future2, future3,
				future4);
	}

	public Object validateTempCFPDeatils(String userId, String sessionId,
			String createdDate, String operation, Object future1,
			Object future2, Object future3, Object future4) {
		return ImtdCfpDetailsFinderUtil.validateTempCFPDeatils(userId,
				sessionId, createdDate, operation, future1, future2, future3,
				future4);
	}

	public Object getTempCFPLazyList(String userId, String sessionId,
			String createdDate, String operation, Object future1,
			Object future2, Object future3, Object future4,
			Map<Object, Object> filterMap) {
		return ImtdCfpDetailsFinderUtil.getTempCFPLazyList(userId, sessionId,
				createdDate, operation, future1, future2, future3, future4,
				filterMap);
	}

	public Object updateAll(String userId, String sessionId,
			String dateCreated, String operation, Object tempCfpSystemId,
			Object deleteYesterdayRecordFlag, Object future3, Object future4) {
		return ImtdCfpDetailsFinderUtil.updateAll(userId, sessionId,
				dateCreated, operation, tempCfpSystemId,
				deleteYesterdayRecordFlag, future3, future4);
	}

	public Object updateCFPDetails(int ifpMasterSystemId, String userId,
			String sessionId, String createdDate, String operation,
			Object future1, Object future2, Object future3, Object future4) {
		return ImtdCfpDetailsFinderUtil.updateCFPDetails(ifpMasterSystemId,
				userId, sessionId, createdDate, operation, future1, future2,
				future3, future4);
	}

	public java.util.List getTempCfpDetails(java.lang.String userId,
			java.lang.String sessionId, int start, int offset,
			java.lang.String column, java.lang.String orederBy,
			Map<Object, Object> filterMap) {
		return ImtdCfpDetailsFinderUtil.getTempCfpDetails(userId, sessionId,
				start, offset, column, orederBy, filterMap);
	}
	
	public java.lang.Boolean loadTempCompanydetails(
	        java.util.List<java.lang.Object> input, java.lang.Object future) {
	        return ImtdCfpDetailsFinderUtil.loadTempCompanydetails(input, future);
	    }

	    public java.lang.Boolean massPopulate(
	        java.util.List<java.lang.Object> input, java.lang.Object future) {
	        return ImtdCfpDetailsFinderUtil.massPopulate(input, future);
	    }

	    public java.lang.Boolean massPopulateAll(
	        java.util.List<java.lang.Object> input, java.lang.Object future) {
	        return ImtdCfpDetailsFinderUtil.massPopulateAll(input, future);
	    }

	    public java.lang.Boolean saveCompany(
	        java.util.List<java.lang.Object> input, java.lang.Object future) {
	        return ImtdCfpDetailsFinderUtil.saveCompany(input, future);
	    }

	    public java.lang.Boolean deleteAll(
	        java.util.List<java.lang.Object> input, java.lang.Object future) {
	        return ImtdCfpDetailsFinderUtil.deleteAll(input, future);
	    }

	    public java.lang.Boolean updateAll(
	        java.util.List<java.lang.Object> input, java.lang.Object future) {
	        return ImtdCfpDetailsFinderUtil.updateAll(input, future);
	    }

	    public java.lang.Object getOverlapedCompanies(
	        java.util.List<java.lang.Object> input, java.lang.Object future) {
	        return ImtdCfpDetailsFinderUtil.getOverlapedCompanies(input, future);
	    }
	    
	    public List getSelectedCompanies(String userID, String sessionID, int start, int offset, String column, String orderBy, boolean flag, Object future1,Map<String, Object> filterMap,boolean isCount) {
	        return ImtdCfpDetailsFinderUtil.getSelectedCompanies(userID, sessionID, start, offset, column, orderBy, flag, future1,filterMap,isCount);
	    }
}
