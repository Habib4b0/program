package com.stpl.app.gtnforecasting.dao.impl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.CommonResultsDAO;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.util.constants.BooleanConstant;
import java.util.List;

/**
 *
 * @author vinodhini
 */
public class CommonResultsDAOImpl implements CommonResultsDAO {
    
    

    @Override
    public Object executeSelectQuery(String query) throws SystemException {
        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    @Override
    public Object executeBulkUpdateQuery(String query) throws SystemException {
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        return BooleanConstant.getTrueFlag();
    }
    @Override
    public Object executeUpdateQuery(String query) throws PortalException {
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        return BooleanConstant.getTrueFlag();
    }
    
    @Override
    public Object executeUpdateQuery(List<StringBuilder> fcpList) throws SystemException {
        for (StringBuilder builder : fcpList) {
            HelperTableLocalServiceUtil.executeUpdateQuery(builder.toString());
        }
        return BooleanConstant.getTrueFlag();
    }
}
