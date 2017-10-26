package com.stpl.app.service.impl;

import java.util.List;
import java.util.Map;

import com.stpl.app.service.base.ImtdIfpDetailsLocalServiceBaseImpl;
import com.stpl.app.service.persistence.ImtdIfpDetailsFinderUtil;

/**
 * The implementation of the imtd ifp details local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.stpl.app.service.ImtdIfpDetailsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.ImtdIfpDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.ImtdIfpDetailsLocalServiceUtil
 */
public class ImtdIfpDetailsLocalServiceImpl extends
        ImtdIfpDetailsLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     * 
     * Never reference this interface directly. Always use {@link
     * com.stpl.app.global.service.ImtdIfpDetailsLocalServiceUtil} to access the
     * imtd ifp details local service.
     */

    public Object deleteAll(String userId, String sessionId,
            String dateCreated, String operation, Object future1,
            Object future2, Object future3, Object future4) {

        return ImtdIfpDetailsFinderUtil.deleteAll(userId, sessionId,
                dateCreated, operation, future1, future2, future3, future4);
    }

    public Object updateOperation(String userId, String sessionId,
            String createdDate, String operation, Object future1,
            Object future2, Object future3, Object future4,
            Map<String, Object> parameters) {
        return ImtdIfpDetailsFinderUtil.updateOperation(userId, sessionId,
                createdDate, operation, future1, future2, future3, future4,
                parameters);
    }

    public Object updateForPopulate(String userId, String sessionId,
            String createdDate, String operation, Object future1,
            Object future2, Object future3, Object future4) {
        return ImtdIfpDetailsFinderUtil.updateForPopulate(userId, sessionId,
                createdDate, operation, future1, future2, future3, future4);
    }

    public Object updateForPopulateAll(String userId, String sessionId,
            String createdDate, String operation, Object future1,
            Object future2, Object future3, Object future4) {
        return ImtdIfpDetailsFinderUtil.updateForPopulateAll(userId, sessionId,
                createdDate, operation, future1, future2, future3, future4);
    }

    public List getItemLazyList(int start, int offset, Object companyIdList,
            Object operation, Object searchValue, Object valueList,
            Object future1, Object future2, Object future3, Object future4,
            Map<String, Object> parameters) {
        return ImtdIfpDetailsFinderUtil.getItemLazyList(start, offset,
                companyIdList, operation, searchValue, valueList, future1,
                future2, future3, future4, parameters);

    }

    public Object updateToIFPDetails(int ifpMasterSystemId, String userId,
            String sessionId, String createdDate, String operation,
            Object future1, Object future2, Object future3, Object future4) {
        return ImtdIfpDetailsFinderUtil.updateToIFPDetails(ifpMasterSystemId,
                userId, sessionId, createdDate, operation, future1, future2,
                future3, future4);
    }

    public Object insertTempIfpDetailsInADD(String userId, String sessionId,
            String createdDate, String operation, Object future1,
            Object future2, Object future3, Object future4) {
        return ImtdIfpDetailsFinderUtil.insertTempIfpDetailsInADD(userId,
                sessionId, createdDate, operation, future1, future2, future3,
                future4);
    }

    public Object insertTempIfpDetailsInEdit(String userId, String sessionId,
            String createdDate, String operation, Object future1,
            Object future2, Object future3, Object future4) {
        return ImtdIfpDetailsFinderUtil.insertTempIfpDetailsInEdit(userId,
                sessionId, createdDate, operation, future1, future2, future3,
                future4);
    }

    public Object validateTempIFPDeatils(String userId, String sessionId,
            String createdDate, String operation, Object future1,
            Object future2, Object future3, Object future4) {
        return ImtdIfpDetailsFinderUtil.validateTempIFPDeatils(userId,
                sessionId, createdDate, operation, future1, future2, future3,
                future4);
    }

    public Object getTempCFPLazyList(String userId, String sessionId,
            String createdDate, String operation, Object future1,
            Object future2, Object future3, Object future4) {
        return ImtdIfpDetailsFinderUtil.getTempIFPLazyList(userId, sessionId,
                createdDate, operation, future1, future2, future3, future4);
    }

    public Object checkAll(String userId, String sessionId, String createdDate,
            int value, Object populateField, Object populateValue,
            Object future3, Object future4) {
        return ImtdIfpDetailsFinderUtil.checkAll(userId, sessionId,
                createdDate, value, populateField, populateValue, future3,
                future4);
    }

    public Object updateAll(String userId, String sessionId,
            String dateCreated, String operation, Object tempIfpSystemId,
            Object deleteYesterdayRecordFlag, Object future3, Object future4) {
        return ImtdIfpDetailsFinderUtil.updateAll(userId, sessionId,
                dateCreated, operation, tempIfpSystemId,
                deleteYesterdayRecordFlag, future3, future4);
    }

    public Object updateIFPDetails(int ifpMasterSystemId, String userId,
            String sessionId, String createdDate, String operation,
            Object future1, Object future2, Object future3, Object future4) {
        return ImtdIfpDetailsFinderUtil.updateIFPDetails(ifpMasterSystemId,
                userId, sessionId, createdDate, operation, future1, future2,
                future3, future4);
    }

    public List getIFPSearchList(String ifpId, String ifpNo, String ifpName,
            int ifpType, int ifpStatus, String itemId, String itemNo,
            String itemName, int start, int offset, String column,
            String orderBy, boolean countFlag, Map<String, Object> parameters) {
        return ImtdIfpDetailsFinderUtil.getIFPSearchList(ifpId, ifpNo, ifpName,
                ifpType, ifpStatus, itemId, itemNo, itemName, start, offset,
                column, orderBy, countFlag, parameters);
    }

    public List fetchFieldsForSecurity(String moduleName, String tabName,
            Object obj1, Object obj2, Object obj3) {
        return ImtdIfpDetailsFinderUtil.fetchFieldsForSecurity(moduleName,
                tabName, null, null, null);
    }
    }
