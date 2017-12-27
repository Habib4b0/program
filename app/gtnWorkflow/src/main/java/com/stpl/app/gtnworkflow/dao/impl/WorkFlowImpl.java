/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnworkflow.dao.impl;


import com.stpl.app.gtnworkflow.dao.WorkFlowDAO;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.List;

/**
 *
 * @author Srithar
 */
public class WorkFlowImpl implements WorkFlowDAO {

    private static final WorkFlowImpl dao = new WorkFlowImpl();

    private WorkFlowImpl() {
    }

    public static WorkFlowImpl getInstance() {
        return dao;
    }

    public List executeSelect(String query) {
        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

}
