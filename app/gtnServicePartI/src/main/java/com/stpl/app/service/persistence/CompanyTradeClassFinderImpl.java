/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence;

import java.util.List;

import com.stpl.app.model.CompanyTradeClass;
import com.stpl.portal.kernel.dao.orm.SQLQuery;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author nandhakumar
 */
public class CompanyTradeClassFinderImpl extends BasePersistenceImpl<CompanyTradeClass> implements CompanyTradeClassFinder {
    private static final Logger LOGGER = Logger.getLogger(CompanyTradeClassFinderImpl.class);

	public List getTradeClassDetails(String companySystemId, String tradeClassSysId) {

		Session session = null;
                String sql = StringUtils.EMPTY;
		try {
			session = openSession();
			
			sql = CustomSQLUtil.get("getTradeClassDetails");
			if (companySystemId.length() != 0) {
				sql += " WHERE COMPANY_TRADE_CLASS.COMPANY_MASTER_SID like '" + companySystemId + "' ";
			}
			if (companySystemId.length() != 0) {
				sql += " AND COMPANY_TRADE_CLASS.COMPANY_TRADE_CLASS_SID like '" + tradeClassSysId + "' ";
			}
//			LOGGER.debug("Final sql statement----------->" + sql);
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			return sqlQuery.list();

		} catch (Exception e) {
                    LOGGER.error(e);
                    LOGGER.error(sql);
			return null;
		} finally {
			closeSession(session);
		}

	}

	public List getCompanyparentDetails(String companySystemId, String parentSysId) {

		Session session = null;
                String sql = StringUtils.EMPTY;
		try {
			session = openSession();
			sql = CustomSQLUtil.get("getParentDetails");
			if (companySystemId.length() != 0) {
				sql += " WHERE COMPANY_PARENT_DETAILS.COMPANY_MASTER_SID like '" + companySystemId + "' ";
			}
			if (parentSysId.length() != 0) {
				sql += " AND COMPANY_PARENT_DETAILS.COMPANY_PARENT_DETAILS_SID like '" + parentSysId + "' ";
			}
//			LOGGER.debug("Final sql statement----------->" + sql);
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			return sqlQuery.list();

		} catch (Exception e) {
                    LOGGER.error(e);
                    LOGGER.error(sql);
			return null;
		} finally {
			closeSession(session);
		}

	}

}
