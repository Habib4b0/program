/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.util.xmlparser;

import java.util.List;

/**
 *
 * @author srithar
 */
public interface CommonDAO {

    public List executeSelect(String query);

    public Object executeUpdate(String query);

}