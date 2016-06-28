/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dao.impl;

import com.stpl.app.accountclose.dao.CommonDao;
import com.stpl.app.parttwo.service.AccClosureMasterLocalServiceUtil;
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

    @Override
    public Object executeSelectQuery(List input, String queryName1, String queryName2) {
        return AccClosureMasterLocalServiceUtil.executeSelectQuery(input, queryName1, queryName2);
    }

    @Override
    public Object executeUpdateQuery(List input, String queryName) {
       return AccClosureMasterLocalServiceUtil.executeUpdateQuery(input, queryName);
    }

    @Override
    public String getQuery(List input,String queryName) {
        return AccClosureMasterLocalServiceUtil.getQuery(input,queryName);
    }
}
