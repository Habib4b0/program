package com.stpl.app.service.impl;

import java.util.List;
import java.util.Map;

import com.stpl.app.service.base.ImtdItemPriceRebateDetailsLocalServiceBaseImpl;
import com.stpl.app.service.persistence.ImtdItemPriceRebateDetailsFinderUtil;

/**
 * The implementation of the imtd item price rebate details local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.ImtdItemPriceRebateDetailsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.ImtdItemPriceRebateDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.ImtdItemPriceRebateDetailsLocalServiceUtil
 */
public class ImtdItemPriceRebateDetailsLocalServiceImpl
    extends ImtdItemPriceRebateDetailsLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.ImtdItemPriceRebateDetailsLocalServiceUtil} to access the imtd item price rebate details local service.
     */
	 public java.lang.Boolean loadTempItemdetails(
		        java.util.List<java.lang.Object> input, java.lang.Object future) {
		        return ImtdItemPriceRebateDetailsFinderUtil.loadTempItemdetails(input, future);
		    }

		    public java.lang.Boolean massPopulate(
		        java.util.List<java.lang.Object> input, java.lang.Object future) {
		        return ImtdItemPriceRebateDetailsFinderUtil.massPopulate(input, future);
		    }

		    public java.lang.Boolean massPopulateAll(
		        java.util.List<java.lang.Object> input, java.lang.Object future) {
		        return ImtdItemPriceRebateDetailsFinderUtil.massPopulateAll(input, future);
		    }

		    public java.lang.Boolean saveItem(
		        java.util.List<java.lang.Object> input, java.lang.Object future) {
		        return ImtdItemPriceRebateDetailsFinderUtil.saveItem(input, future);
		    }

		    public java.lang.Boolean deleteAll(
		        java.util.List<java.lang.Object> input, java.lang.Object future) {
		        return ImtdItemPriceRebateDetailsFinderUtil.deleteAll(input, future);
		    }

		    public java.lang.Boolean updateAll(
		        java.util.List<java.lang.Object> input, java.lang.Object future) {
		        return ImtdItemPriceRebateDetailsFinderUtil.updateAll(input, future);
		    }
		    
		    public java.lang.Object validateTempRSDeatils(
		        java.lang.String userId, java.lang.String sessionId,
		        java.lang.String createdDate, java.lang.String operation,
		        java.lang.Object future1, java.lang.Object future2,
		        java.lang.Object future3, java.lang.Object future4) {
		        return ImtdItemPriceRebateDetailsFinderUtil.validateTempRSDeatils(userId, sessionId, createdDate,
		            operation, future1, future2, future3, future4);
		    }
		    public List getSelectedItemList(String userID, String sessionID, int start, int offset, String column, 
		            Boolean orderBy,Map<String, Object> filterMap,boolean isCount, Object future1, Object future2, Object future3){
		        return ImtdItemPriceRebateDetailsFinderUtil.getSelectedItemList(userID, sessionID, start, offset, column, 
		            orderBy, filterMap, isCount, future1, future2, future3);
		    }
		    
		    public void mergeImtdRsContractDetailsFormula(int contRsdSid,int itemSid,String userId,String sessionId){
		        ImtdItemPriceRebateDetailsFinderUtil.mergeImtdRsContractDetailsFormula(contRsdSid,itemSid,userId,sessionId);
		    }
		    
		    public void deleteTempRsContractTableRecords(int contRsSid,int contRsdSid,String userId,String sessionId){
		        ImtdItemPriceRebateDetailsFinderUtil.deleteTempRsContractTableRecords(contRsSid,contRsdSid,userId,sessionId);
		    }
		    
		    public void insertFormulaToContractRsdFrImtd(int contRsdSid,String userId,String sessionId,String createdDate){
		        ImtdItemPriceRebateDetailsFinderUtil.insertFormulaToContractRsdFrImtd(contRsdSid,userId,sessionId,createdDate);
		    }
		    
		    public void addAllFormulaToContractRsdFrImtd(int itemSid,String userId,String sessionId,String createdDate){
		        ImtdItemPriceRebateDetailsFinderUtil.addAllFormulaToContractRsdFrImtd(itemSid,userId,sessionId,createdDate);
		    }
		    
		    public void remaoveAllFormulaToContractRsdFrImtd(int itemSid,String userId,String sessionId,String createdDate){
		        ImtdItemPriceRebateDetailsFinderUtil.remaoveAllFormulaToContractRsdFrImtd(itemSid,userId,sessionId,createdDate);
		    }
}
