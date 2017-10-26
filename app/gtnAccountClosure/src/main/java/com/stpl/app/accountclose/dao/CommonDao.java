/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dao;

import java.util.List;

/**
 *
 * @author mohamed.hameed
 */
public interface CommonDao {

    public Object executeSelectQuery(final List input, String queryName1, String queryName2);

    public Object executeUpdateQuery(final List input, String queryName);

    public String getQuery(List input, String queryName);
}
