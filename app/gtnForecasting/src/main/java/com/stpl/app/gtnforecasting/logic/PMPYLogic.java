/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic;

import com.stpl.app.gtnforecasting.dao.SalesProjectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.SalesProjectionDAOImpl;


/**
 * The Class PMPYLogic.
 *
 * @author lokeshwari
 */
public class PMPYLogic {
    
    /** The Constant LOGGER. */
    
    /** The sales dao. */
	private SalesProjectionDAO salesDAO = new SalesProjectionDAOImpl();

	public SalesProjectionDAO getSalesDAO() {
		return salesDAO;
	}

	public void setSalesDAO(SalesProjectionDAO salesDAO) {
		this.salesDAO = salesDAO;
	}

   

}
