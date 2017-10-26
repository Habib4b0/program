/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad;

import com.stpl.app.gtnforecasting.nationalassumptions.logic.NationalAssumptionLogic;
import com.stpl.ifs.util.HelperDTO;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.DAO;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author vinodhini
 */
public class TherapeuticContainer implements DAO<HelperDTO> {

    private HelperDTO therapeuticClass;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(TherapeuticContainer.class);

    /**
     * Method used for get Count.
     */
    public TherapeuticContainer() {
       
    }

    /**
     * Method used for get Count.
     *
     * @param therapeuticClass
     */
    public TherapeuticContainer(final HelperDTO therapeuticClass) {
        this.therapeuticClass = therapeuticClass;
    }

    @Override
    public int count(final SearchCriteria searchCriteria) {
        try {
            LOGGER.debug("Entering TherapeuticContainer Count method :");
            return NationalAssumptionLogic.getLazyTherapeuticClassCount(searchCriteria.getFilter()) + 1;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return 0;
    }

    /**
     * Method used for get the results.
     */
    public List<HelperDTO> find(final SearchCriteria searchCriteria, final int startIndex, final int offset, final List<OrderByColumn> list) {
        try {
            LOGGER.debug("Entering TherapeuticContainer find method :");
            return NationalAssumptionLogic.getLazyTherapeuticClassResults(startIndex, startIndex + offset, searchCriteria.getFilter(), therapeuticClass);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return null;
    }

}
