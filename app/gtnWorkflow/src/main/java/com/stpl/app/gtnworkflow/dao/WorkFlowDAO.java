/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnworkflow.dao;

import java.util.List;

/**
 *
 * @author Srithar
 */
public interface WorkFlowDAO {

    public List executeSelect(String query);

    public Object executeUpdate(String query);
}
