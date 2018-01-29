package com.stpl.app.common.dao.impl;

import com.stpl.app.common.dao.CommonDao;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.List;

public class CommonImpl implements CommonDao {

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
