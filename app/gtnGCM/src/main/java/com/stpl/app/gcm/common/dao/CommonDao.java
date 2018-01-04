/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.common.dao;

import java.util.List;

/**
 *
 * @author Srithar
 */
public interface CommonDao {

    public List executeSelect(String query);

    public Object executeUpdate(String query);
}
