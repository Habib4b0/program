/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.util.xmlparser;

import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.List;

/**
 *
 * @author srithar
 */
public class CommonImpl implements CommonDAO{

    private static final CommonImpl dao = new CommonImpl();

    private CommonImpl() {
    }

    public static CommonImpl getInstance() {
        return dao;
    }

    @Override
    public List executeSelect(String query) {
        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    @Override
    public Object executeUpdate(String query) {
        return CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
    }
}