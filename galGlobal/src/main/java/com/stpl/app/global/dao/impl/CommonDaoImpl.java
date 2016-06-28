/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.dao.impl;

import com.stpl.app.global.dao.CommonDao;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.List;

/**
 *
 * @author mohamed.hameed
 */
public class CommonDaoImpl implements CommonDao {

    private static final CommonDaoImpl dao = new CommonDaoImpl();

    private CommonDaoImpl() {
    }

    public static CommonDaoImpl getInstance() {
        return dao;
    }

    public List executeSelect(String query) {
        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    public Object executeUpdate(String query) {
        return CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
    }
}
