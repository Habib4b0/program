package com.stpl.app.service.impl;

import com.stpl.app.service.base.MSalesProjectionMasterLocalServiceBaseImpl;
import com.stpl.app.service.persistence.MSalesProjectionMasterFinderUtil;
import java.util.List;

/**
 * The implementation of the m sales projection master local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.MSalesProjectionMasterLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.MSalesProjectionMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.MSalesProjectionMasterLocalServiceUtil
 */
public class MSalesProjectionMasterLocalServiceImpl
    extends MSalesProjectionMasterLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.MSalesProjectionMasterLocalServiceUtil} to access the m sales projection master local service.
     */
    public Object executeSelectQuery(String string, Object o, Object o1){
        return MSalesProjectionMasterFinderUtil.executeSelectQuery(string, o1, o1);
    }
    
    public List executeUpdateQuery(String query,Object obj1,Object obj2){
        return MSalesProjectionMasterFinderUtil.executeUpdateQuery(query, obj1, obj2);
    }
    
    public Object executeUpdateQuery(List<StringBuilder> queryList,Object obj1,Object obj2){
        return MSalesProjectionMasterFinderUtil.executeUpdateQuery(queryList, obj1, obj2);
    }
    
    public List getAssumptionResult(List input, String queryName) {
        return MSalesProjectionMasterFinderUtil.getAssumptionResult(input, queryName);
    }
    
    public Object executeUpdateSQL(String query, Object obj1, Object obj2) {
        return MSalesProjectionMasterFinderUtil.executeUpdateSQL(query, obj1, obj2);
    }
     public java.lang.Object executeUpdateQuery(List<?> nmSalesList,
       Object udc1,Object udc2, Object udc3){
     return MSalesProjectionMasterFinderUtil.executeUpdateQuery(nmSalesList, udc1, udc2, udc3);
     }
}
