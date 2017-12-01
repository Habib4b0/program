/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dao;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import java.util.List;

/**
 *
 * @author Manasa
 */
public interface NADataSelectionDAO {
    public List getForecastConfig(final DynamicQuery dynamicQuery) throws SystemException;
}
