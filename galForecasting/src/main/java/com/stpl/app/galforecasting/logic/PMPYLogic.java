/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.logic;

import com.stpl.app.galforecasting.dao.SalesProjectionDAO;
import com.stpl.app.galforecasting.dao.impl.SalesProjectionDAOImpl;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class PMPYLogic.
 *
 * @author lokeshwari
 */
public class PMPYLogic {
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(PMPYLogic.class);
    
    /** The sales dao. */
    SalesProjectionDAO salesDAO = new SalesProjectionDAOImpl();

   

}
