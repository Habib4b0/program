/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.ui.lazyload;

import com.stpl.app.contract.dashboard.logic.DashBoardLogic;
import com.stpl.ifs.util.HelperDTO;
import java.util.Collections;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.DAO;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author gopinath
 */
public class FormulaIdContainer implements DAO<HelperDTO> {
    private HelperDTO helper;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(FormulaIdContainer.class);

    /**
     * Method used for get Count.
     */
    public FormulaIdContainer(final HelperDTO helper){
       this.helper=helper;
    }

    public FormulaIdContainer() {
        //Empty constructor.
    }
    public int count(final SearchCriteria searchCriteria) {
        try {
            return DashBoardLogic.getLazyTierFormulaIdCount(searchCriteria.getFilter(),helper);
        } catch (Exception portException) {
          LOGGER.error(portException);
                   
                }
        return 0;
    }

    public List<HelperDTO> find(final SearchCriteria searchCriteria, final int startIndex, final int offset, final List<OrderByColumn> list) {
         try {
            return DashBoardLogic.getLazyTierFormulaIdResults(startIndex, startIndex + offset, searchCriteria.getFilter(),helper);
        } catch (Exception portException) {
           LOGGER.error(portException);
                   
                }
         return Collections.emptyList();
    }
    
}
