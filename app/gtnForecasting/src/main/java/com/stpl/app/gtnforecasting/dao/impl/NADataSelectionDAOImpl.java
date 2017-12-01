/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dao.impl;

import com.stpl.app.gtnforecasting.dao.NADataSelectionDAO;
import com.stpl.app.service.ForecastConfigLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import java.util.List;



/**
 *
 * @author Manasa
 */
public class NADataSelectionDAOImpl implements NADataSelectionDAO{
    @Override
    public List getForecastConfig(DynamicQuery dynamicQuery) throws SystemException {
        return ForecastConfigLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}
