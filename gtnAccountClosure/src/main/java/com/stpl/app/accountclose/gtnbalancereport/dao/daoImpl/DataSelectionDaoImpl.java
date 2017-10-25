/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.dao.daoImpl;

import com.stpl.app.accountclose.gtnbalancereport.dao.DataSelectionDAO;
import com.stpl.app.parttwo.model.AccClosureMaster;
import com.stpl.app.parttwo.model.AccClosureViewMaster;
import com.stpl.app.parttwo.service.AccClosureMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.AccClosureViewMasterLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.ForecastConfigLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import java.util.List;
import org.jboss.logging.Logger;

/**
 *
 * @author santanukumar
 */
public class DataSelectionDaoImpl implements DataSelectionDAO {

    public static final Logger LOGGER = Logger.getLogger(DataSelectionDaoImpl.class);

    public int getCompaniesCount(DynamicQuery dynamicQuery) throws SystemException, Exception {
        return (int) CompanyMasterLocalServiceUtil.dynamicQueryCount(dynamicQuery);
    }

    public List<Object[]> getCompanies(DynamicQuery dynamicQuery) throws SystemException, Exception {
        return CompanyMasterLocalServiceUtil.dynamicQuery(dynamicQuery);

    }

    public List getForecastConfig(DynamicQuery dynamicQuery) throws SystemException, Exception {
        return ForecastConfigLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    public AccClosureMaster addAcctCloserMaster(AccClosureMaster accClosureMaster) throws SystemException, Exception {
        return AccClosureMasterLocalServiceUtil.addAccClosureMaster(accClosureMaster);
    }

    public AccClosureViewMaster addForecastingViewMaster(AccClosureViewMaster viewMaster) throws SystemException, Exception {
        return AccClosureViewMasterLocalServiceUtil.addAccClosureViewMaster(viewMaster);
    }

    public AccClosureViewMaster updateForecastingViewMaster(AccClosureViewMaster viewMaster) throws SystemException, Exception {
        return AccClosureViewMasterLocalServiceUtil.updateAccClosureViewMaster(viewMaster);
    }

    @Override
    public User getUser(Long systemId) {
        try {
            return UserLocalServiceUtil.getUser(systemId);
        } catch (PortalException ex) {
            LOGGER.error(ex);
            return null;
        } catch (SystemException ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    @Override
    public AccClosureViewMaster deleteForecastingViewMaster(int systemId) {
        try {
            return AccClosureViewMasterLocalServiceUtil.deleteAccClosureViewMaster(systemId);
        } catch (PortalException ex) {
            LOGGER.error(ex);
            return null;
        } catch (SystemException ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    public AccClosureMaster getAcctCloserMaster(int acctCloserMasterId) throws SystemException, Exception {
        return AccClosureMasterLocalServiceUtil.getAccClosureMaster(acctCloserMasterId);
    }

    public AccClosureMaster updateAccountCloserMaster(AccClosureMaster accCloserMaster) throws SystemException, Exception {
        return AccClosureMasterLocalServiceUtil.updateAccClosureMaster(accCloserMaster);
    }

    public AccClosureViewMaster getAcctViewMaster(int accClosureViewMasterSid) throws SystemException, Exception {
        return AccClosureViewMasterLocalServiceUtil.getAccClosureViewMaster(accClosureViewMasterSid);
    }

    @Override
    public Object executeSelectQuery(List input, String queryName, String quaryName2) {
        return AccClosureMasterLocalServiceUtil.executeSelectQuery(input, queryName, quaryName2);
    }
}
