/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.dao;

import java.util.List;

/**
 *
 * @author mohamed.hameed
 */
public interface CommonDao {

    public List executeSelect(String query);

    public Object executeUpdate(String query);
}
