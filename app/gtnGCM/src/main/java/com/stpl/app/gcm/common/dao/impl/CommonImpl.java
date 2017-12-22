/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.common.dao.impl;

import com.stpl.app.gcm.common.dao.CommonDao;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.List;

/**
 *
 * @author Srithar
 */
public class CommonImpl implements CommonDao {

    private static final CommonImpl dao = new CommonImpl();

    private CommonImpl() {
    }

    public static CommonImpl getInstance() {
        return dao;
    }

    public List executeSelect(String query) {
        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    public Object executeUpdate(String query) {
        return HelperTableLocalServiceUtil.executeUpdateQueryCount(query);
    }

}
