/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dao.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.NACommonResultsDAO;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.util.constants.BooleanConstant;
import java.util.List;

/**
 *
 * @author vinodhini
 */
public class NACommonResultsDAOImpl implements NACommonResultsDAO {

    private static final BooleanConstant BOOLEAN_CONSTANT = new BooleanConstant();
    
    @Override
    public Object executeSelectQuery(String query) throws PortalException, SystemException {
        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    @Override
    public Object executeBulkUpdateQuery(String query) throws PortalException, SystemException {
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        return BOOLEAN_CONSTANT.getTrueFlag();

    }

    @Override
    public Object executeUpdateQuery(String query) throws SystemException, PortalException {
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        return BOOLEAN_CONSTANT.getTrueFlag();
    }

    @Override
    public Object executeUpdateQuery(List<StringBuilder> fcpList) throws PortalException, SystemException {
        for (StringBuilder builder : fcpList) {
            HelperTableLocalServiceUtil.executeUpdateQuery(builder.toString());
        }
        return BOOLEAN_CONSTANT.getTrueFlag();
    }
}
