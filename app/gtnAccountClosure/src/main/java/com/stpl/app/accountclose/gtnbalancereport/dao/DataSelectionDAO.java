/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.dao;

import com.stpl.app.parttwo.model.AccClosureMaster;
import com.stpl.app.parttwo.model.AccClosureViewMaster;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import java.util.List;

/**
 *
 * @author santanukumar
 */
public interface DataSelectionDAO {
    public int getCompaniesCount(DynamicQuery dynamicQuery) throws SystemException, Exception;
    public List<Object[]> getCompanies(DynamicQuery dynamicQuery) throws SystemException, Exception;
    public List getForecastConfig(final DynamicQuery dynamicQuery) throws SystemException, Exception;
    public AccClosureMaster addAcctCloserMaster(AccClosureMaster accClosureMaster)throws SystemException, Exception;

    public AccClosureViewMaster addForecastingViewMaster(AccClosureViewMaster viewMaster)throws SystemException, Exception;

    public AccClosureViewMaster updateForecastingViewMaster(AccClosureViewMaster viewMaster)throws SystemException, Exception;
    User getUser(Long systemId);

    public AccClosureViewMaster deleteForecastingViewMaster(int viewMasterId);

    public AccClosureMaster getAcctCloserMaster(int acctCloserMasterId)throws SystemException, Exception;

    public AccClosureMaster updateAccountCloserMaster(AccClosureMaster accCloserMaster)throws SystemException, Exception;

    public AccClosureViewMaster getAcctViewMaster(int accClosureViewMasterSid) throws SystemException, Exception;
   public java.lang.Object executeSelectQuery(java.util.List input,java.lang.String queryName, java.lang.String quaryName2);

}
