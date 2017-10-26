package com.stpl.app.service.impl;

import java.util.List;
import java.util.Map;

import com.stpl.app.service.base.ContractMasterLocalServiceBaseImpl;
import com.stpl.app.service.persistence.ContractMasterFinderUtil;

/**
 * The implementation of the contract master local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.stpl.app.service.ContractMasterLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.ContractMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.ContractMasterLocalServiceUtil
 */
public class ContractMasterLocalServiceImpl
        extends ContractMasterLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.ContractMasterLocalServiceUtil} to access the contract master local service.
     */

    public java.util.List getContractPriceInfo(int contractSystemId, int cfpId, int ifpId, int psId) {
        return ContractMasterFinderUtil.getContractPriceInfo(contractSystemId, cfpId, ifpId, psId);
    }

    public List getTradingPartnerList(String companyId, String companyNo, String companyName, int companyStatus, int companyType, Map<String, Object> filterMap, int start, int offset, String column, String orederBy) {
        return ContractMasterFinderUtil.getTradingPartnerList(companyId, companyNo, companyName, companyStatus, companyType, filterMap, start, offset, column, orederBy);
    }

    public List getContractList(String contractId, String contractNo, String contractName, int contractStatus, int contractType, String tradeClass,
            int tradingPartner, Map<String, Object> filterMap, String orderBy, String column, int start, int offset, boolean isCount) {

        return ContractMasterFinderUtil.getContractList(contractId, contractNo, contractName, contractStatus, contractType, tradeClass,
                tradingPartner, filterMap, orderBy, column, start, offset, isCount);
    }

    public List fetchFieldsForSecurity(String moduleName, String tabName, Object obj1, Object obj2, Object obj3) {
        return ContractMasterFinderUtil.fetchFieldsForSecurity(moduleName, tabName, null, null, null);
    }

    public java.util.List searchContractsForPromoteTp(
            java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return ContractMasterFinderUtil.searchContractsForPromoteTp(parameters);
    }
    public java.lang.Object executeSelectQueries(java.lang.String[] queries){
        return ContractMasterFinderUtil.executeSelectQueries(queries);
    }
}
